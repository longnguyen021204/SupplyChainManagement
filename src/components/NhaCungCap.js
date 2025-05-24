import React, { useState, useEffect } from 'react';
import styles from './styles/NhaCungCap.module.css';
import { format } from 'date-fns';
import { useLocation } from 'react-router-dom';

const API_URL = 'http://localhost:8080/SupplyChainManagement/api/suppliers/';

const initialSupplierState = {
  tenNCC: '',
  diaChi: '',
  soDienThoai: '',
  email: '',
  thongTinLienHe: '',
  dieuKienThanhToan: '',
  diemDanhGia: 0,
  soLanDanhGia: 0,
};

function SupplierList() {
  const [searchTerm, setSearchTerm] = useState('');
  const [suppliers, setSuppliers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [modal, setModal] = useState({
    type: null,
    data: null
  });
  const [deleteConfirm, setDeleteConfirm] = useState({
    show: false,
    supplierId: null,
    supplierName: ''
  });
  const [ratingInput, setRatingInput] = useState({
    chatLuong: 0,
    giaoHang: 0,
    giaCa: 0,
    binhLuan: '',
  });

  useEffect(() => {
    const fetchSuppliers = async () => {
      try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error(`Lỗi: ${response.status}`);
        setSuppliers(await response.json());
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchSuppliers();
  }, []);

  const handleSupplierAction = async (method, url, body, successCallback) => {
    try {
      const options = { method };
      if (body) {
        options.headers = { 'Content-Type': 'application/json' };
        options.body = JSON.stringify(body);
      }

      const response = await fetch(url, options);
      if (!response.ok) {
        let errorMessage = `Lỗi: ${response.status}`;
        try {
          const errorData = await response.json();
          errorMessage = errorData.message || errorMessage;
        } catch (_) {
        }
        throw new Error(errorMessage);
      }

      let data = null;
      if (response.status !== 204) {
        const contentType = response.headers.get('Content-Type');
        if (contentType && contentType.includes('application/json')) {
          data = await response.json();
        }
      }

      successCallback(data);
      setModal({ type: null, data: null });
    } catch (err) {
      setError(err.message);
    }
  };

  const location = useLocation();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    if (params.get('mode') === 'add') {
      setModal({ type: 'add', data: initialSupplierState });
    }
  }, [location.search]);

  const formatDate = (dateString) =>
    dateString ? format(new Date(dateString), 'yyyy-MM-dd HH:mm:ss') : '';

  const handleRatingSubmit = (supplierId) => {
    const { chatLuong, giaoHang, giaCa } = ratingInput;
    const newAverageRating = (parseInt(chatLuong) + parseInt(giaoHang) + parseInt(giaCa)) / 3;

    setSuppliers(prevSuppliers =>
      prevSuppliers.map(supplier => {
        if (supplier.nccId === supplierId) {
          const currentTotalRating = supplier.diemDanhGia * supplier.soLanDanhGia;
          const newTotalRating = currentTotalRating + newAverageRating;
          const newNumberOfRatings = supplier.soLanDanhGia + 1;
          const updatedAverageRating = newTotalRating / newNumberOfRatings;

          return {
            ...supplier,
            diemDanhGia: parseFloat(updatedAverageRating.toFixed(1)),
            soLanDanhGia: newNumberOfRatings,
            ngayCapNhat: new Date().toISOString(),
          };
        }
        return supplier;
      })
    );
    setModal({ type: null, data: null });
    setRatingInput({ chatLuong: 0, giaoHang: 0, giaCa: 0, binhLuan: '' });
  };

  const handleDeleteClick = (supplierId, supplierName) => {
    setDeleteConfirm({
      show: true,
      supplierId,
      supplierName
    });
  };

  const handleDeleteConfirm = async () => {
    if (deleteConfirm.supplierId) {
      await handleSupplierAction(
        'DELETE', 
        `${API_URL}${deleteConfirm.supplierId}`, 
        null, 
        () => setSuppliers(suppliers.filter(sp => sp.nccId !== deleteConfirm.supplierId))
      );
      setDeleteConfirm({ show: false, supplierId: null, supplierName: '' });
    }
  };

  if (loading) return <div>Đang tải nhà cung cấp...</div>;
  if (error) return <div>Lỗi: {error}</div>;

  const filteredSuppliers = suppliers.filter(supplier =>
    supplier.tenNCC?.toLowerCase().includes(searchTerm.toLowerCase()) ||
    supplier.diaChi?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className={styles.container}>
      <h2 className={styles.title}>Quản lý Nhà Cung Cấp</h2>

      <div className={styles.searchContainer}>
        <button className={styles.buttonAdd} onClick={() => setModal({ type: 'add', data: initialSupplierState })}>
          Thêm mới
        </button>
        
        <input
          type="text"
          placeholder="Tìm kiếm theo tên hoặc địa chỉ..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className={styles.searchInput}
        />
      </div>

      {modal.type && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h3>{{
              add: 'Thêm Nhà Cung Cấp Mới',
              edit: 'Sửa Nhà Cung Cấp',
              view: 'Chi tiết Nhà Cung Cấp',
              rate: `Đánh giá Nhà Cung Cấp: ${modal.data?.tenNCC}`
            }[modal.type]}</h3>

            {['add', 'edit'].includes(modal.type) ? (
              <>
                {['tenNCC', 'diaChi', 'soDienThoai', 'email', 'thongTinLienHe'].map(field => (
                  <div key={field} className={styles.formGroup}>
                    <label htmlFor={field}>{{
                      tenNCC: 'Tên nhà cung cấp',
                      diaChi: 'Địa chỉ',
                      soDienThoai: 'Số điện thoại',
                      email: 'Email',
                      thongTinLienHe: 'Thông tin liên hệ'
                    }[field]}:</label>
                    <input
                      type={field === 'email' ? 'email' : 'text'}
                      id={field}
                      name={field}
                      value={modal.data[field] || ''}
                      onChange={(e) => setModal(prev => ({
                        ...prev,
                        data: { ...prev.data, [field]: e.target.value }
                      }))}
                    />
                  </div>
                ))}
                <div className={styles.formGroup}>
                  <label htmlFor="dieuKienThanhToan">Điều kiện thanh toán:</label>
                  <textarea
                    id="dieuKienThanhToan"
                    name="dieuKienThanhToan"
                    value={modal.data.dieuKienThanhToan || ''}
                    onChange={(e) => setModal(prev => ({
                      ...prev,
                      data: { ...prev.data, dieuKienThanhToan: e.target.value }
                    }))}
                  />
                </div>
              </>
            ) : modal.type === 'rate' ? (
              <>
                <div className={styles.formGroup}>
                  <label htmlFor="chatLuong">Chất lượng (1-5):</label>
                  <input
                    type="number"
                    id="chatLuong"
                    min="1"
                    max="5"
                    value={ratingInput.chatLuong}
                    onChange={(e) => setRatingInput(prev => ({ ...prev, chatLuong: e.target.value }))}
                  />
                </div>
                <div className={styles.formGroup}>
                  <label htmlFor="giaoHang">Giao hàng đúng hạn (1-5):</label>
                  <input
                    type="number"
                    id="giaoHang"
                    min="1"
                    max="5"
                    value={ratingInput.giaoHang}
                    onChange={(e) => setRatingInput(prev => ({ ...prev, giaoHang: e.target.value }))}
                  />
                </div>
                <div className={styles.formGroup}>
                  <label htmlFor="giaCa">Giá cả (1-5):</label>
                  <input
                    type="number"
                    id="giaCa"
                    min="1"
                    max="5"
                    value={ratingInput.giaCa}
                    onChange={(e) => setRatingInput(prev => ({ ...prev, giaCa: e.target.value }))}
                  />
                </div>
                <div className={styles.formGroup}>
                  <label htmlFor="binhLuan">Bình luận:</label>
                  <textarea
                    id="binhLuan"
                    value={ratingInput.binhLuan}
                    onChange={(e) => setRatingInput(prev => ({ ...prev, binhLuan: e.target.value }))}
                  />
                </div>
              </>
            ) : (
              modal.data && Object.entries({
                nccId: 'ID',
                tenNCC: 'Tên',
                diaChi: 'Địa chỉ',
                soDienThoai: 'Số điện thoại',
                email: 'Email',
                thongTinLienHe: 'Người liên hệ',
                dieuKienThanhToan: 'Điều kiện thanh toán',
                diemDanhGia: 'Điểm đánh giá',
                soLanDanhGia: 'Số lần đánh giá'
              }).map(([key, label]) => (
                <p key={key} className={styles.detailsInfo}>
                  <strong>{label}:</strong> {modal.data[key]}
                </p>
              ))
            )}

            <div className={styles.modalActions}>
              {modal.type === 'add' && (
                <button
                  className={styles.buttonSave}
                  onClick={() => handleSupplierAction(
                    'POST',
                    `${API_URL}add`,
                    modal.data,
                    (newSupplier) => setSuppliers([...suppliers, newSupplier])
                  )}
                >
                  Lưu
                </button>
              )}
              {modal.type === 'edit' && (
                <button
                  className={styles.buttonSave}
                  onClick={() => handleSupplierAction(
                    'POST',
                    `${API_URL}add`,
                    modal.data,
                    (updatedSupplier) => setSuppliers(suppliers.map(sp =>
                      sp.nccId === updatedSupplier.nccId ? updatedSupplier : sp))
                  )}
                >
                  Lưu
                </button>
              )}
              {modal.type === 'rate' && (
                <button
                  className={styles.buttonSave}
                  onClick={() => handleRatingSubmit(modal.data.nccId)}
                >
                  Gửi đánh giá
                </button>
              )}
              <button
                className={styles.buttonCancel}
                onClick={() => {
                  setModal({ type: null, data: null });
                  setRatingInput({ chatLuong: 0, giaoHang: 0, giaCa: 0, binhLuan: '' });
                }}
              >
                {modal.type === 'view' ? 'Đóng' : 'Hủy'}
              </button>
            </div>
          </div>
        </div>
      )}

      {deleteConfirm.show && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h3>Xác nhận xóa</h3>
            <p>Bạn có chắc chắn muốn xóa nhà cung cấp "{deleteConfirm.supplierName}"?</p>
            <div className={styles.modalActions}>
              <button 
                className={styles.buttonDeleteConfirm}
                onClick={handleDeleteConfirm}
              >
                Xác nhận xóa
              </button>
              <button 
                className={styles.buttonCancel}
                onClick={() => setDeleteConfirm({ show: false, supplierId: null, supplierName: '' })}
              >
                Hủy
              </button>
            </div>
          </div>
        </div>
      )}

      <table className={styles.table}>
        <thead>
          <tr>
            {['ID', 'Tên', 'Địa chỉ', 'SĐT', 'Email', 'Người LH', 'ĐKTT', 'Điểm', 'Lần ĐG', 'Hành động'].map(header => (
              <th key={header}>{header}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {filteredSuppliers.map(supplier => (
            <tr key={supplier.nccId}>
              {['nccId', 'tenNCC', 'diaChi', 'soDienThoai', 'email', 'thongTinLienHe'].map(field => (
                <td key={field}>{supplier[field]}</td>
              ))}
              <td>{supplier.dieuKienThanhToan?.substring(0, 50)}...</td>
              <td>{supplier.diemDanhGia}</td>
              <td>{supplier.soLanDanhGia}</td>
              <td className={styles.actions}>
                <button onClick={() => setModal({ type: 'view', data: supplier })}>Xem</button>
                <button onClick={() => setModal({ type: 'edit', data: supplier })}>Sửa</button>
                <button
                  className={styles.buttonRate}
                  onClick={() => setModal({ type: 'rate', data: supplier })}
                >
                  Đánh giá
                </button>
                <button 
                  onClick={() => handleDeleteClick(supplier.nccId, supplier.tenNCC)}
                >
                  Xóa
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table> 
    </div>
  );
}

export default SupplierList;