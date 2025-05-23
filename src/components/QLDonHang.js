import React, { useState, useEffect } from 'react';
import styles from './styles/QLDonHang.module.css'; // Import CSS Module
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import { format } from 'date-fns'; // Import thư viện format ngày tháng

const orderStatuses = ['Tất cả', 'Mới', 'Đã xác nhận', 'Đang xử lý', 'Đang giao', 'Đã giao', 'Đã hủy'];

function OrderList() {
    const [statusFilter, setStatusFilter] = useState('Tất cả');
    const [searchTerm, setSearchTerm] = useState('');
    const [orders, setOrders] = useState([]); // State để quản lý danh sách đơn hàng từ API
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const API_URL = '/api/donhang'; // **ĐỒNG ĐỘI: Thay URL này bằng URL API lấy danh sách đơn hàng**

    useEffect(() => {
        const fetchOrders = async () => {
            setLoading(true);
            setError(null);
            try {
                const response = await fetch(API_URL);
                if (!response.ok) {
                    throw new Error(`Lỗi: ${response.status}`);
                }
                const data = await response.json();
                setOrders(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchOrders();
    }, []);

    const handleStatusFilterChange = (event) => {
        setStatusFilter(event.target.value);
        // **ĐỒNG ĐỘI: Nếu cần lọc ở backend, gọi API với tham số lọc**
        // Ví dụ: fetch(`${API_URL}?status=${event.target.value}`)
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
        // **ĐỒNG ĐỘI: Nếu cần tìm kiếm ở backend, gọi API với tham số tìm kiếm**
        // Ví dụ: fetch(`${API_URL}?search=${event.target.value}`)
    };

    const handleViewDetail = (orderId) => {
        navigate(`/order-detail/${orderId}`);
    };

    const handleCancelOrder = async (orderId) => {
        // **ĐỒNG ĐỘI: Gọi API để hủy đơn hàng ở backend**
        try {
            const response = await fetch(`${API_URL}/${orderId}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                throw new Error(`Lỗi hủy đơn hàng: ${response.status}`);
            }
            console.log(`Đã hủy đơn hàng với ID: ${orderId}`);
            // Cập nhật lại danh sách đơn hàng sau khi hủy thành công
            setOrders(prevOrders => prevOrders.filter(order => order.DH_ID !== orderId));
        } catch (error) {
            setError(error.message);
            console.error('Lỗi khi hủy đơn hàng:', error);
        }
    };

    const formatDate = (dateString) => {
        if (!dateString) return '';
        try {
            const date = new Date(dateString);
            return format(date, 'yyyy-MM-dd'); // Format theo định dạng bạn muốn
        } catch (error) {
            console.error('Lỗi khi format ngày:', error);
            return dateString;
        }
    };

    const filteredOrders = orders.filter(order => {
        const statusMatch = statusFilter === 'Tất cả' || order.TrangThai === statusFilter;
        const searchMatch = order.MaDH.toLowerCase().includes(searchTerm.toLowerCase());
        return statusMatch && searchMatch;
    });

    if (loading) {
        return <div>Đang tải đơn hàng...</div>;
    }

    if (error) {
        return <div>Lỗi: {error}</div>;
    }

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Quản lý Đơn Hàng</h2>
            <div className={styles.filterContainer}>
                <button className={styles.buttonCreate} onClick={() => navigate('/create-order')}>
                    Tạo mới đơn hàng
                </button>
                <label className={styles.filterLabel}>Trạng thái:</label>
                <select
                    className={styles.filterSelect}
                    value={statusFilter}
                    onChange={handleStatusFilterChange}
                >
                    {orderStatuses.map(status => (
                        <option key={status} value={status}>{status}</option>
                    ))}
                </select>
                <input
                    type="text"
                    placeholder="Tìm kiếm theo mã đơn hàng..."
                    value={searchTerm}
                    onChange={handleSearch}
                    className={styles.searchInput}
                />
                {/* Thêm bộ lọc ngày nếu cần */}
            </div>
            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã đơn hàng</th>
                        <th>Ngày đặt</th>
                        <th>Trạng thái</th>
                        <th>Tổng tiền</th>
                        <th>Ghi chú</th>
                        <th className={styles.actions}>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredOrders.map((order) => (
                        <tr key={order.DH_ID}>
                            <td>{order.DH_ID}</td>
                            <td>{order.MaDH}</td>
                            <td>{formatDate(order.NgayDatHang)}</td>
                            <td>{order.TrangThai}</td>
                            <td>{order.TongTien}</td>
                            <td>{order.GhiChu}</td>
                            <td className={styles.actions}>
                                <button onClick={() => handleViewDetail(order.DH_ID)}>Xem chi tiết</button>
                                <button onClick={() => handleCancelOrder(order.DH_ID)}>Hủy</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default OrderList;