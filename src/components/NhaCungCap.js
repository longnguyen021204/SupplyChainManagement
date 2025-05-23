import React, { useState, useEffect } from 'react';
import styles from './styles/NhaCungCap.module.css';
import { format } from 'date-fns';

const API_URL = 'http://localhost:8080/SupplyChainManagement/api/suppliers/';
const initState = {
  tenNCC: '', diaChi: '', soDienThoai: '', email: '', thongTinLienHe: '',
  dieuKienThanhToan: '', diemDanhGia: 0, soLanDanhGia: 0
};

function SupplierList() {
  const [suppliers, setSuppliers] = useState([]);
  const [search, setSearch] = useState('');
  const [modal, setModal] = useState({ type: null, data: null });
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(API_URL)
      .then(res => res.ok ? res.json() : Promise.reject(res.status))
      .then(setSuppliers)
      .catch(err => setError(`Lỗi: ${err}`));
  }, []);

  const handleAPI = async (method, url, body, cb) => {
    try {
      const res = await fetch(url, {
        method, headers: body && { 'Content-Type': 'application/json' },
        body: body && JSON.stringify(body)
      });
      if (!res.ok) throw await res.text();
      const data = await res.json();
      cb(data);
      setModal({ type: null, data: null });
    } catch (err) {
      try { setError(JSON.parse(err).message); } catch { setError(err); }
    }
  };

  const filtered = suppliers.filter(s =>
    s.tenNCC?.toLowerCase().includes(search.toLowerCase()) ||
    s.diaChi?.toLowerCase().includes(search.toLowerCase())
  );
  const fmt = d => d ? format(new Date(d), 'yyyy-MM-dd HH:mm:ss') : '';

  return (
    <div className={styles.container}>
      <h2 className={styles.title}>Quản lý Nhà Cung Cấp</h2>
      <div className={styles.searchContainer}>
        <button className={styles.buttonAdd} onClick={() => setModal({ type: 'add', data: initState })}>Thêm mới</button>
        <input className={styles.searchInput} placeholder="Tìm kiếm..." value={search} onChange={e => setSearch(e.target.value)} />
      </div>
      {error && <div className={styles.error}>Lỗi: {error}</div>}
      {modal.type && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h3>{modal.type === 'add' ? 'Thêm' : modal.type === 'edit' ? 'Sửa' : 'Chi tiết'} Nhà Cung Cấp</h3>
            {['add', 'edit'].includes(modal.type) ? (
              <>
                {['tenNCC', 'diaChi', 'soDienThoai', 'email', 'thongTinLienHe'].map(f => (
                  <div key={f} className={styles.formGroup}>
                    <label>{f}</label>
                    <input type={f === 'email' ? 'email' : 'text'} value={modal.data[f]} onChange={e =>
                      setModal(m => ({ ...m, data: { ...m.data, [f]: e.target.value } }))} />
                  </div>
                ))}
                <div className={styles.formGroup}>
                  <label>Điều kiện thanh toán</label>
                  <textarea value={modal.data.dieuKienThanhToan} onChange={e =>
                    setModal(m => ({ ...m, data: { ...m.data, dieuKienThanhToan: e.target.value } }))} />
                </div>
              </>
            ) : (
              Object.entries({
                nccId: 'ID', tenNCC: 'Tên', diaChi: 'Địa chỉ', soDienThoai: 'SĐT', email: 'Email',
                thongTinLienHe: 'Người LH', dieuKienThanhToan: 'ĐKTT', diemDanhGia: 'Điểm',
                soLanDanhGia: 'Lần ĐG', ngayTao: 'Ngày tạo', ngayCapNhat: 'Ngày cập nhật'
              }).map(([k, label]) => (
                <p key={k}><strong>{label}:</strong> {k.includes('ngay') ? fmt(modal.data[k]) : modal.data[k]}</p>
              ))
            )}
            <div className={styles.modalActions}>
              {modal.type === 'add' &&
                <button className={styles.buttonSave} onClick={() =>
                  handleAPI('POST', `${API_URL}add`, modal.data, d => setSuppliers([...suppliers, d]))}>Lưu</button>}
              {modal.type === 'edit' &&
                <button className={styles.buttonSave} onClick={() =>
                  handleAPI('POST', `${API_URL}add`, modal.data, d =>
                    setSuppliers(suppliers.map(s => s.nccId === d.nccId ? d : s)))}>Lưu</button>}
              <button className={styles.buttonCancel} onClick={() => setModal({ type: null, data: null })}>
                {modal.type === 'view' ? 'Đóng' : 'Hủy'}</button>
            </div>
          </div>
        </div>
      )}
      <table className={styles.table}>
        <thead>
          <tr>{['ID', 'Tên', 'Địa chỉ', 'SĐT', 'Email', 'Người LH', 'ĐKTT', 'Điểm', 'Lần ĐG', 'Ngày tạo', 'Ngày cập nhật', 'Hành động'].map(h => <th key={h}>{h}</th>)}</tr>
        </thead>
        <tbody>
          {filtered.map(s => (
            <tr key={s.nccId}>
              {[s.nccId, s.tenNCC, s.diaChi, s.soDienThoai, s.email, s.thongTinLienHe,
              `${s.dieuKienThanhToan?.slice(0, 50)}...`, s.diemDanhGia, s.soLanDanhGia,
              fmt(s.ngayTao), fmt(s.ngayCapNhat)].map((v, i) => <td key={i}>{v}</td>)}
              <td className={styles.actions}>
                <button onClick={() => setModal({ type: 'view', data: s })}>Xem</button>
                <button onClick={() => setModal({ type: 'edit', data: s })}>Sửa</button>
                <button onClick={() => handleAPI('DELETE', `${API_URL}${s.nccId}`, null, () =>
                  setSuppliers(suppliers.filter(x => x.nccId !== s.nccId)))}>Xóa</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default SupplierList;
