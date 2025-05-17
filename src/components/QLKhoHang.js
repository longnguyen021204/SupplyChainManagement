import React, { useState, useEffect } from 'react';
import styles from './QLKhoHang.module.css'; // Import CSS Module
import { format } from 'date-fns'; // Để format ngày tháng

function WarehouseList() {
    const [warehouses, setWarehouses] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedWarehouse, setSelectedWarehouse] = useState(null);
    const [isAdding, setIsAdding] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    const [newWarehouse, setNewWarehouse] = useState({
        TenKho: '',
        DiaChi: '',
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const API_URL = '/api/khohang'; // **ĐỒNG ĐỘI CHỈ CẦN THAY ĐỔI URL NÀY**

    // Fetch data khi component mount
    useEffect(() => {
        const fetchWarehouses = async () => {
            setLoading(true);
            setError(null);
            try {
                const response = await fetch(API_URL);
                if (!response.ok) {
                    throw new Error(`Lỗi: ${response.status}`);
                }
                const data = await response.json();
                setWarehouses(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchWarehouses();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredWarehouses = warehouses.filter(warehouse =>
        warehouse.TenKho.toLowerCase().includes(searchTerm.toLowerCase()) ||
        warehouse.DiaChi.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleViewDetails = (warehouse) => {
        setSelectedWarehouse(warehouse);
    };

    const handleCloseDetails = () => {
        setSelectedWarehouse(null);
        setIsAdding(false);
        setIsEditing(false);
    };

    const handleAddWarehouseClick = () => {
        setIsAdding(true);
        setNewWarehouse({ TenKho: '', DiaChi: '' });
    };

    const handleEditWarehouseClick = (warehouse) => {
        setIsEditing(true);
        setSelectedWarehouse({ ...warehouse }); // Copy warehouse vào selectedWarehouse để chỉnh sửa
    };

    const handleAddWarehouse = async () => {
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newWarehouse),
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Lỗi thêm kho: ${response.status}`);
            }
            const data = await response.json();
            setWarehouses([...warehouses, data]);
            setIsAdding(false);
        } catch (error) {
            setError(error.message);
        }
    };

    const handleUpdateWarehouse = async () => {
        try {
            const response = await fetch(`${API_URL}/${selectedWarehouse.Kho_ID}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(selectedWarehouse),
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Lỗi cập nhật kho: ${response.status}`);
            }
            const data = await response.json();
            setWarehouses(warehouses.map(wh => (wh.Kho_ID === selectedWarehouse.Kho_ID ? data : wh)));
            setIsEditing(false);
            setSelectedWarehouse(null);
        } catch (error) {
            setError(error.message);
        }
    };

    const handleDeleteWarehouse = async (id) => {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Lỗi xóa kho: ${response.status}`);
            }
            setWarehouses(warehouses.filter(wh => wh.Kho_ID !== id));
            setSelectedWarehouse(null);
        } catch (error) {
            setError(error.message);
        }
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        if (isAdding) {
            setNewWarehouse({ ...newWarehouse, [name]: value });
        } else if (isEditing) {
            setSelectedWarehouse({ ...selectedWarehouse, [name]: value });
        }
    };

    if (loading) {
        return <div>Đang tải...</div>;
    }

    if (error) {
        return <div>Lỗi: {error}</div>;
    }

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Quản lý Kho hàng</h2>
            <div className={styles.actionContainer}>
                <button className={styles.buttonAdd} onClick={handleAddWarehouseClick}>Thêm mới</button>
                <input
                    type="text"
                    placeholder="Tìm kiếm theo tên hoặc địa chỉ..."
                    value={searchTerm}
                    onChange={handleSearch}
                    className={styles.searchInput}
                />
            </div>

            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên kho</th>
                        <th>Địa chỉ</th>
                        <th>Ngày tạo</th>
                        <th>Ngày cập nhật</th>
                        <th className={styles.actions}>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredWarehouses.map(warehouse => (
                        <tr key={warehouse.Kho_ID}>
                            <td>{warehouse.Kho_ID}</td>
                            <td>{warehouse.TenKho}</td>
                            <td>{warehouse.DiaChi}</td>
                            <td>{format(new Date(warehouse.NgayTao), 'yyyy-MM-dd HH:mm:ss')}</td>
                            <td>{format(new Date(warehouse.NgayCapNhat), 'yyyy-MM-dd HH:mm:ss')}</td>
                            <td className={styles.actions}>
                                <button onClick={() => handleViewDetails(warehouse)}>Xem</button>
                                <button onClick={() => handleEditWarehouseClick(warehouse)}>Sửa</button>
                                <button onClick={() => handleDeleteWarehouse(warehouse.Kho_ID)}>Xóa</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {selectedWarehouse && (isEditing || selectedWarehouse) && (
                <div className={styles.modal}>
                    <div className={styles.modalContent}>
                        <h3 className={styles.modalTitle}>{isEditing ? 'Sửa Kho hàng' : 'Chi tiết Kho hàng'}</h3>
                        <form>
                            <div className={styles.formGroup}>
                                <label htmlFor="TenKho">Tên kho:</label>
                                <input
                                    type="text"
                                    id="TenKho"
                                    name="TenKho"
                                    value={isEditing ? selectedWarehouse.TenKho : selectedWarehouse.TenKho}
                                    onChange={handleInputChange}
                                    readOnly={!isEditing}
                                />
                            </div>
                            <div className={styles.formGroup}>
                                <label htmlFor="DiaChi">Địa chỉ:</label>
                                <input
                                    type="text"
                                    id="DiaChi"
                                    name="DiaChi"
                                    value={isEditing ? selectedWarehouse.DiaChi : selectedWarehouse.DiaChi}
                                    onChange={handleInputChange}
                                    readOnly={!isEditing}
                                />
                            </div>
                            <div className={styles.formGroup}>
                                <label htmlFor="NgayTao">Ngày tạo:</label>
                                <input
                                    type="text"
                                    id="NgayTao"
                                    name="NgayTao"
                                    value={format(new Date(selectedWarehouse.NgayTao), 'yyyy-MM-dd HH:mm:ss')}
                                    readOnly
                                />
                            </div>
                            <div className={styles.formGroup}>
                                <label htmlFor="NgayCapNhat">Ngày cập nhật:</label>
                                <input
                                    type="text"
                                    id="NgayCapNhat"
                                    name="NgayCapNhat"
                                    value={format(new Date(selectedWarehouse.NgayCapNhat), 'yyyy-MM-dd HH:mm:ss')}
                                    readOnly
                                />
                            </div>
                            <div className={styles.modalActions}>
                                {isEditing ? (
                                    <>
                                        <button type="button" className={styles.buttonSave} onClick={handleUpdateWarehouse}>
                                            Lưu
                                        </button>
                                        <button type="button" className={styles.buttonCancel} onClick={handleCloseDetails}>
                                            Hủy
                                        </button>
                                    </>
                                ) : (
                                    <button type="button" className={styles.buttonClose} onClick={handleCloseDetails}>
                                        Đóng
                                    </button>
                                )}
                            </div>
                        </form>
                    </div>
                </div>
            )}

            {isAdding && (
                <div className={styles.modal}>
                    <div className={styles.modalContent}>
                        <h3 className={styles.modalTitle}>Thêm Kho hàng mới</h3>
                        <form>
                            <div className={styles.formGroup}>
                                <label htmlFor="TenKho">Tên kho:</label>
                                <input type="text" id="TenKho" name="TenKho" value={newWarehouse.TenKho} onChange={handleInputChange} />
                            </div>
                            <div className={styles.formGroup}>
                                <label htmlFor="DiaChi">Địa chỉ:</label>
                                <input type="text" id="DiaChi" name="DiaChi" value={newWarehouse.DiaChi} onChange={handleInputChange} />
                            </div>
                            <div className={styles.modalActions}>
                                <button type="button" className={styles.buttonSave} onClick={handleAddWarehouse}>
                                    Lưu
                                </button>
                                <button type="button" className={styles.buttonCancel} onClick={handleCloseDetails}>
                                    Hủy
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}

export default WarehouseList;