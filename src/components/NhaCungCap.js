import React, { useState, useEffect } from 'react';
import styles from './styles/NhaCungCap.module.css';
import { format } from 'date-fns';

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

  // ************** BỔ SUNG CHO TÍNH NĂNG ĐÁNH GIÁ **************
  const [ratingInput, setRatingInput] = useState({
    chatLuong: 0,
    giaoHang: 0,
    giaCa: 0,
    binhLuan: '',
  });
  // **********************************************************

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

  const formatDate = (dateString) =>
    dateString ? format(new Date(dateString), 'yyyy-MM-dd HH:mm:ss') : '';

  // ************** HÀM MỚI ĐỂ XỬ LÝ ĐÁNH GIÁ TRÊN FRONTEND **************
  const handleRatingSubmit = (supplierId) => {
    const { chatLuong, giaoHang, giaCa } = ratingInput;
    // Tính điểm trung bình (có thể tùy chỉnh trọng số)
    const newAverageRating = (parseInt(chatLuong) + parseInt(giaoHang) + parseInt(giaCa)) / 3;

    setSuppliers(prevSuppliers =>
      prevSuppliers.map(supplier => {
        if (supplier.nccId === supplierId) {
          // Tính lại tổng điểm và số lần đánh giá
          const currentTotalRating = supplier.diemDanhGia * supplier.soLanDanhGia;
          const newTotalRating = currentTotalRating + newAverageRating;
          const newNumberOfRatings = supplier.soLanDanhGia + 1;
          const updatedAverageRating = newTotalRating / newNumberOfRatings;

          return {
            ...supplier,
            diemDanhGia: parseFloat(updatedAverageRating.toFixed(1)), // Làm tròn 1 chữ số thập phân
            soLanDanhGia: newNumberOfRatings,
            ngayCapNhat: new Date().toISOString(), // Cập nhật thời gian
            // Có thể lưu thêm bình luận nếu muốn hiển thị
            // comments: [...(supplier.comments || []), ratingInput.binhLuan]
          };
        }
        return supplier;
      })
    );
    setModal({ type: null, data: null }); // Đóng modal
    setRatingInput({ chatLuong: 0, giaoHang: 0, giaCa: 0, binhLuan: '' }); // Reset input
  };
  // *******************************************************************

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
              rate: `Đánh giá Nhà Cung Cấp: ${modal.data?.tenNCC}` // Tiêu đề mới cho modal đánh giá
            }[modal.type]}</h3>

            {/* Các trường form Thêm/Sửa */}
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
            ) : modal.type === 'rate' ? ( // ************** GIAO DIỆN ĐÁNH GIÁ MỚI **************
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
            ) : ( // Hiển thị chi tiết (view)
              modal.data && Object.entries({
                nccId: 'ID',
                tenNCC: 'Tên',
                diaChi: 'Địa chỉ',
                soDienThoai: 'Số điện thoại',
                email: 'Email',
                thongTinLienHe: 'Người liên hệ',
                dieuKienThanhToan: 'Điều kiện thanh toán',
                diemDanhGia: 'Điểm đánh giá',
                soLanDanhGia: 'Số lần đánh giá',
                ngayTao: 'Ngày tạo',
                ngayCapNhat: 'Ngày cập nhật'
              }).map(([key, label]) => (
                <p key={key} className={styles.detailsInfo}>
                  <strong>{label}:</strong> {key.includes('ngay') ? formatDate(modal.data[key]) : modal.data[key]}
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
              {/* ************** NÚT LƯU CHO ĐÁNH GIÁ MỚI ************** */}
              {modal.type === 'rate' && (
                <button
                  className={styles.buttonSave}
                  onClick={() => handleRatingSubmit(modal.data.nccId)}
                >
                  Gửi đánh giá
                </button>
              )}
              {/* ***************************************************** */}
              <button
                className={styles.buttonCancel}
                onClick={() => {
                  setModal({ type: null, data: null });
                  setRatingInput({ chatLuong: 0, giaoHang: 0, giaCa: 0, binhLuan: '' }); // Reset khi hủy
                }}
              >
                {modal.type === 'view' ? 'Đóng' : 'Hủy'}
              </button>
            </div>
          </div>
        </div>
      )}

      <table className={styles.table}>
        <thead>
          <tr>
            {['ID', 'Tên', 'Địa chỉ', 'SĐT', 'Email', 'Người LH', 'ĐKTT', 'Điểm', 'Lần ĐG', 'Ngày tạo', 'Ngày cập nhật', 'Hành động'].map(header => (
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
              <td>{formatDate(supplier.ngayTao)}</td>
              <td>{formatDate(supplier.ngayCapNhat)}</td>
              <td className={styles.actions}>
                <button onClick={() => setModal({ type: 'view', data: supplier })}>Xem</button>
                <button onClick={() => setModal({ type: 'edit', data: supplier })}>Sửa</button>
                {/* ************** NÚT ĐÁNH GIÁ MỚI ************** */}
                <button
                  className={styles.buttonRate}
                  onClick={() => setModal({ type: 'rate', data: supplier })}
                >
                  Đánh giá
                </button>
                {/* ********************************************* */}
                <button onClick={() =>
                  handleSupplierAction('DELETE', `${API_URL}${supplier.nccId}`, null, () =>
                    setSuppliers(suppliers.filter(sp => sp.nccId !== supplier.nccId)))
                }>
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