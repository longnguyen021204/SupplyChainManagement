import React, { useState, useEffect } from 'react';
import styles from './NhaCungCap.module.css';
import { format } from 'date-fns';

function SupplierList() {
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedSupplier, setSelectedSupplier] = useState(null);
    const [suppliers, setSuppliers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isAdding, setIsAdding] = useState(false);
    const [newSupplier, setNewSupplier] = useState({
        tenNCC: '',
        diaChi: '',
        soDienThoai: '',
        email: '',
        thongTinLienHe: '',
        dieuKienThanhToan: '',
        diemDanhGia: 0,
        soLanDanhGia: 0,
    });

    const API_URL = 'http://localhost:8080/SupplyChainManagement/api/suppliers';

    useEffect(() => {
        const fetchSuppliers = async () => {
            setLoading(true);
            setError(null);
            try {
                const response = await fetch(API_URL);
                if (!response.ok) throw new Error(`Lỗi: ${response.status}`);
                const data = await response.json();
                setSuppliers(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        fetchSuppliers();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredSuppliers = suppliers.filter(supplier =>
        supplier.tenNCC?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        supplier.diaChi?.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleViewDetails = (supplier) => {
        setSelectedSupplier(supplier);
    };

    const handleCloseDetails = () => {
        setSelectedSupplier(null);
    };

    const handleAddSupplierClick = () => {
        setIsAdding(true);
        setNewSupplier({
            tenNCC: '',
            diaChi: '',
            soDienThoai: '',
            email: '',
            thongTinLienHe: '',
            dieuKienThanhToan: '',
            diemDanhGia: 0,
            soLanDanhGia: 0,
        });
    };

    const handleNewSupplierChange = (event) => {
        const { name, value } = event.target;
        setNewSupplier(prev => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleAddSupplier = async () => {
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newSupplier),
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Lỗi thêm nhà cung cấp: ${response.status}`);
            }
            const data = await response.json();
            setSuppliers([...suppliers, data]);
            setIsAdding(false);
        } catch (error) {
            setError(error.message);
        }
    };

    const handleUpdateSupplier = async (updatedSupplier) => {
        try {
            const response = await fetch(`${API_URL}/${updatedSupplier.nccId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedSupplier),
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Lỗi cập nhật nhà cung cấp: ${response.status}`);
            }
            const data = await response.json();
            setSuppliers(suppliers.map(sp => (sp.nccId === updatedSupplier.nccId ? data : sp)));
            setSelectedSupplier(null);
        } catch (error) {
            setError(error.message);
        }
    };

    const handleDeleteSupplier = async (supplierId) => {
        try {
            const response = await fetch(`${API_URL}/${supplierId}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Lỗi xóa nhà cung cấp: ${response.status}`);
            }
            setSuppliers(suppliers.filter(sp => sp.nccId !== supplierId));
            setSelectedSupplier(null);
        } catch (error) {
            setError(error.message);
        }
    };

    const formatDate = (dateString) => {
        if (!dateString) return '';
        try {
            return format(new Date(dateString), 'yyyy-MM-dd HH:mm:ss');
        } catch {
            return dateString;
        }
    };

    if (loading) return <div>Đang tải nhà cung cấp...</div>;
    if (error) return <div>Lỗi: {error}</div>;

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Quản lý Nhà Cung Cấp</h2>
            <div style={{ marginBottom: '20px', display: 'flex', alignItems: 'center' }}>
                <button className={styles.buttonAdd} onClick={handleAddSupplierClick}>
                    Thêm mới
                </button>
                <input
                    type="text"
                    placeholder="Tìm kiếm theo tên hoặc địa chỉ..."
                    value={searchTerm}
                    onChange={handleSearch}
                    className={styles.searchInput}
                    style={{ marginLeft: '10px' }}
                />
            </div>

            {isAdding && (
                <div className={styles.modal}>
                    <div className={styles.modalContent}>
                        <h3 className={styles.modalTitle}>Thêm Nhà Cung Cấp Mới</h3>
                        <div className={styles.formGroup}>
                            <label htmlFor="tenNCC">Tên nhà cung cấp:</label>
                            <input type="text" id="tenNCC" name="tenNCC" value={newSupplier.tenNCC} onChange={handleNewSupplierChange} />
                        </div>
                        <div className={styles.formGroup}>
                            <label htmlFor="diaChi">Địa chỉ:</label>
                            <input type="text" id="diaChi" name="diaChi" value={newSupplier.diaChi} onChange={handleNewSupplierChange} />
                        </div>
                        <div className={styles.formGroup}>
                            <label htmlFor="soDienThoai">Số điện thoại:</label>
                            <input type="text" id="soDienThoai" name="soDienThoai" value={newSupplier.soDienThoai} onChange={handleNewSupplierChange} />
                        </div>
                        <div className={styles.formGroup}>
                            <label htmlFor="email">Email:</label>
                            <input type="email" id="email" name="email" value={newSupplier.email} onChange={handleNewSupplierChange} />
                        </div>
                        <div className={styles.formGroup}>
                            <label htmlFor="thongTinLienHe">Thông tin liên hệ:</label>
                            <input type="text" id="thongTinLienHe" name="thongTinLienHe" value={newSupplier.thongTinLienHe} onChange={handleNewSupplierChange} />
                        </div>
                        <div className={styles.formGroup}>
                            <label htmlFor="dieuKienThanhToan">Điều kiện thanh toán:</label>
                            <textarea id="dieuKienThanhToan" name="dieuKienThanhToan" value={newSupplier.dieuKienThanhToan} onChange={handleNewSupplierChange} />
                        </div>
                        <div className={styles.modalActions}>
                            <button className={styles.buttonSave} onClick={handleAddSupplier}>Lưu</button>
                            <button className={styles.buttonCancel} onClick={() => setIsAdding(false)}>Hủy</button>
                        </div>
                    </div>
                </div>
            )}

            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên nhà cung cấp</th>
                        <th>Địa chỉ</th>
                        <th>Số điện thoại</th>
                        <th>Email</th>
                        <th>Thông tin liên hệ</th>
                        <th>Điều kiện thanh toán</th>
                        <th>Điểm đánh giá</th>
                        <th>Số lần đánh giá</th>
                        <th>Ngày tạo</th>
                        <th>Ngày cập nhật</th>
                        <th className={styles.actions}>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredSuppliers.map((supplier) => (
                        <tr key={supplier.nccId}>
                            <td>{supplier.nccId}</td>
                            <td>{supplier.tenNCC}</td>
                            <td>{supplier.diaChi}</td>
                            <td>{supplier.soDienThoai}</td>
                            <td>{supplier.email}</td>
                            <td>{supplier.thongTinLienHe}</td>
                            <td>{supplier.dieuKienThanhToan?.substring(0, 50)}...</td>
                            <td>{supplier.diemDanhGia}</td>
                            <td>{supplier.soLanDanhGia}</td>
                            <td>{formatDate(supplier.ngayTao)}</td>
                            <td>{formatDate(supplier.ngayCapNhat)}</td>
                            <td className={styles.actions}>
                                <button onClick={() => handleViewDetails(supplier)}>Xem chi tiết</button>
                                <button onClick={() => handleUpdateSupplier(supplier)}>Sửa</button>
                                <button onClick={() => handleDeleteSupplier(supplier.nccId)}>Xóa</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {selectedSupplier && (
                <div className={styles.modal}>
                    <div className={styles.modalContent}>
                        <h3 className={styles.modalTitle}>Chi tiết Nhà Cung Cấp</h3>
                        <p className={styles.detailsInfo}><strong>ID:</strong> {selectedSupplier.nccId}</p>
                        <p className={styles.detailsInfo}><strong>Tên:</strong> {selectedSupplier.tenNCC}</p>
                        <p className={styles.detailsInfo}><strong>Địa chỉ:</strong> {selectedSupplier.diaChi}</p>
                        <p className={styles.detailsInfo}><strong>Số điện thoại:</strong> {selectedSupplier.soDienThoai}</p>
                        <p className={styles.detailsInfo}><strong>Email:</strong> {selectedSupplier.email}</p>
                        <p className={styles.detailsInfo}><strong>Người liên hệ:</strong> {selectedSupplier.thongTinLienHe}</p>
                        <p className={styles.detailsInfo}><strong>Điều kiện thanh toán:</strong> {selectedSupplier.dieuKienThanhToan}</p>
                        <p className={styles.detailsInfo}><strong>Điểm đánh giá:</strong> {selectedSupplier.diemDanhGia}</p>
                        <p className={styles.detailsInfo}><strong>Số lần đánh giá:</strong> {selectedSupplier.soLanDanhGia}</p>
                        <p className={styles.detailsInfo}><strong>Ngày tạo:</strong> {formatDate(selectedSupplier.ngayTao)}</p>
                        <p className={styles.detailsInfo}><strong>Ngày cập nhật:</strong> {formatDate(selectedSupplier.ngayCapNhat)}</p>
                        <div className={styles.modalActions}>
                            <button className={styles.buttonClose} onClick={handleCloseDetails}>Đóng</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default SupplierList;
