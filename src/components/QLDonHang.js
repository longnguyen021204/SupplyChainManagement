import React, { useState, useEffect } from 'react';
import styles from './styles/QLDonHang.module.css';
import { useNavigate } from 'react-router-dom';
import { format } from 'date-fns';
import { 
    OrderAPI, 
    apiCall, 
    WarehouseAPI,
    generateMaDonHangNhap, // Import hàm sinh mã mới
    generateMaDonHangXuat, // Import hàm sinh mã mới
    fetchDonHang, // Import hàm lấy danh sách đơn hàng đã đóng gói
    cancelDonHang // Import hàm hủy đơn hàng đã đóng gói
} from './apiDonHang'; // Đảm bảo đường dẫn đúng

const orderStatuses = ['Tất cả', 'Mới', 'Đã xác nhận', 'Đang xử lý', 'Đang giao', 'Đã giao', 'Đã hủy'];

function OrderList() {
    const [statusFilter, setStatusFilter] = useState('Tất cả');
    const [searchTerm, setSearchTerm] = useState('');
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [formType, setFormType] = useState(null); // 'nhap' | 'xuat'

    // Đổi tên các trường formData để khớp với camelCase của Java POJO
    // và thêm khoNhap/khoXuat cùng trạng thái, tổng tiền ban đầu
    const [formData, setFormData] = useState({
        maDH: '',
        ngayDatHang: format(new Date(), 'yyyy-MM-dd'), // Mặc định ngày hiện tại
        ghiChu: '',
        trangThai: 'Mới', // Mặc định trạng thái khi tạo mới
        tongTien: 0, // Mặc định tổng tiền ban đầu
        khoNhap: null, // Sẽ là { khoId: selectedKhoId } nếu là đơn nhập
        khoXuat: null  // Sẽ là { khoId: selectedKhoId } nếu là đơn xuất
    });

    const [warehouses, setWarehouses] = useState([]);
    const [selectedKhoId, setSelectedKhoId] = useState('');

    const navigate = useNavigate();

    const handleStatusFilterChange = (event) => setStatusFilter(event.target.value);
    const handleSearch = (event) => setSearchTerm(event.target.value);
    const handleViewDetail = (orderId) => navigate(`/order-detail/${orderId}`);

    const handleCancelOrder = async (orderId) => {
        if (!window.confirm('Bạn có chắc chắn muốn hủy đơn hàng này?')) return;
        try {
            const orderToCancel = orders.find(order => order.dhId === orderId);
            if (!orderToCancel) {
                alert('Không tìm thấy đơn hàng để hủy.');
                return;
            }
            // Sử dụng hàm cancelDonHang đã đóng gói
            await cancelDonHang(orderToCancel.maDH); // Truyền maDH
            setOrders(prev => prev.filter(order => order.dhId !== orderId));
            alert("Đơn hàng đã được hủy thành công!");
        } catch (err) {
            console.error("Lỗi khi hủy đơn hàng:", err);
            setError(err.message);
        }
    };

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const [ordersData, warehousesData] = await Promise.all([
                    fetchDonHang(), // Sử dụng hàm đã đóng gói
                    apiCall('GET', WarehouseAPI.LIST) // Lấy danh sách kho
                ]);
                setOrders(ordersData);
                setWarehouses(warehousesData);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };
        fetchInitialData();
    }, []);

    const formatDate = (dateString) => {
        if (!dateString) return '';
        try {
            const date = new Date(dateString);
            return format(date, 'yyyy-MM-dd');
        } catch {
            return dateString;
        }
    };

    const filteredOrders = orders.filter(order => {
        if (!order || !order.maDH) return false;
        const statusMatch = statusFilter === 'Tất cả' || order.trangThai === statusFilter;
        const searchMatch = order.maDH.toLowerCase().includes(searchTerm.toLowerCase());
        return statusMatch && searchMatch;
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    // Hàm xử lý khi chọn loại đơn hàng (nhập/xuất)
    const handleFormTypeChange = async (type) => {
        setFormType(type);
        setFormData({ // Reset formData khi thay đổi loại form
            maDH: '',
            ngayDatHang: format(new Date(), 'yyyy-MM-dd'),
            ghiChu: '',
            trangThai: 'Mới',
            tongTien: 0,
            khoNhap: null,
            khoXuat: null
        });
        setSelectedKhoId(''); // Reset selected kho id

        try {
            let generatedMaDH = '';
            if (type === 'nhap') {
                generatedMaDH = await generateMaDonHangNhap();
            } else if (type === 'xuat') {
                generatedMaDH = await generateMaDonHangXuat();
            }
            setFormData(prev => ({ ...prev, maDH: generatedMaDH }));
        } catch (err) {
            console.error("Lỗi khi sinh mã đơn hàng:", err);
            alert('Lỗi khi sinh mã đơn hàng: ' + err.message);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!selectedKhoId) {
            alert('Vui lòng chọn một kho!');
            return;
        }
        if (!formData.maDH) {
            alert('Mã đơn hàng chưa được sinh hoặc bị thiếu!');
            return;
        }

        try {
            const newOrderData = {
                maDH: formData.maDH,
                // Chuyển ngày đặt hàng sang định dạng ISO 8601 string hoặc timestamp
                // Backend của bạn xử lý timestamp (milliseconds), nên gửi getTime()
                ngayDatHang: formData.ngayDatHang ? new Date(formData.ngayDatHang).toISOString() : new Date().toISOString(),
                trangThai: formData.trangThai,
                ghiChu: formData.ghiChu,
                tongTien: formData.tongTien, // Có thể để mặc định 0 hoặc cho người dùng nhập
                
                // Đảm bảo chỉ một trong khoNhap/khoXuat có giá trị và đúng cấu trúc
                khoNhap: formType === 'nhap' ? { khoId: parseInt(selectedKhoId) } : null,
                khoXuat: formType === 'xuat' ? { khoId: parseInt(selectedKhoId) } : null,
                
                chiTietDonHangNhap: [], // Khởi tạo rỗng, sẽ được thêm sau nếu cần
                chiTietDonHangXuat: []  // Khởi tạo rỗng, sẽ được thêm sau nếu cần
            };

            const createdOrder = await apiCall('POST', OrderAPI.CREATE, newOrderData);
            alert(`Tạo đơn hàng ${formType === 'nhap' ? 'nhập' : 'xuất'} thành công với mã ${createdOrder.maDH}!`);
            setShowForm(false);
            setFormData({ maDH: '', ngayDatHang: format(new Date(), 'yyyy-MM-dd'), ghiChu: '', trangThai: 'Mới', tongTien: 0, khoNhap: null, khoXuat: null });
            setSelectedKhoId('');
            
            // Tải lại danh sách đơn hàng sau khi tạo thành công
            const updatedOrders = await fetchDonHang(); // Sử dụng hàm đã đóng gói
            setOrders(updatedOrders);

        } catch (err) {
            console.error("Lỗi khi tạo đơn hàng:", err);
            alert('Lỗi khi tạo đơn hàng: ' + (err.message || "Đã xảy ra lỗi không xác định."));
        }
    };

    if (loading) return <div className={styles.loading}>Đang tải dữ liệu...</div>;
    if (error) return <div className={styles.error}>Lỗi: {error}</div>;

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Quản lý Đơn Hàng</h2>

            <div className={styles.filterContainer}>
                <button className={styles.buttonCreate} onClick={() => { setShowForm(true); handleFormTypeChange('nhap'); }}>
                    Tạo đơn nhập
                </button>
                <button className={styles.buttonCreate} onClick={() => { setShowForm(true); handleFormTypeChange('xuat'); }}>
                    Tạo đơn xuất
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
            </div>

            {showForm && (
                <div className={styles.modal}>
                    <div className={styles.modalContent}>
                        <form onSubmit={handleSubmit}>
                            <h3>{formType === 'nhap' ? 'Tạo Đơn Nhập' : 'Tạo Đơn Xuất'}</h3>

                            <div className={styles.formGroup}>
                                <label>Mã đơn hàng</label>
                                <input 
                                    name="maDH" 
                                    placeholder="Mã đơn hàng" 
                                    value={formData.maDH} 
                                    onChange={handleChange} 
                                    readOnly // Đặt là readOnly vì mã được sinh tự động
                                    required 
                                />
                            </div>

                            <div className={styles.formGroup}>
                                <label>Ngày đặt hàng</label>
                                <input 
                                    name="ngayDatHang" 
                                    type="date" 
                                    value={formData.ngayDatHang} 
                                    onChange={handleChange} 
                                    required 
                                />
                            </div>

                            <div className={styles.formGroup}>
                                <label>Kho {formType === 'nhap' ? 'nhập' : 'xuất'}</label>
                                <select 
                                    name="khoId" 
                                    value={selectedKhoId} 
                                    onChange={(e) => setSelectedKhoId(e.target.value)} 
                                    required
                                >
                                    <option value="">-- Chọn kho --</option>
                                    {warehouses.map(kho => (
                                        <option key={kho.khoId} value={kho.khoId}>
                                            {kho.tenKho} ({kho.diaChi})
                                        </option>
                                    ))}
                                </select>
                            </div>

                            <div className={styles.formGroup}>
                                <label>Ghi chú</label>
                                <textarea name="ghiChu" placeholder="Ghi chú" value={formData.ghiChu} onChange={handleChange} />
                            </div>

                            <div className={styles.modalButtons}>
                                <button type="submit">Tạo</button>
                                <button type="button" onClick={() => setShowForm(false)}>Hủy</button>
                            </div>
                        </form>
                    </div>
                </div>
            )}

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
                        <tr key={order.dhId}> 
                            <td>{order.dhId}</td>
                            <td>{order.maDH}</td>
                            <td>{formatDate(order.ngayDatHang)}</td>
                            <td>{order.trangThai}</td>
                            <td>{order.tongTien ? order.tongTien.toLocaleString('vi-VN') : '0'} VNĐ</td>
                            <td>{order.ghiChu}</td>
                            <td className={styles.actions}>
                                <button onClick={() => handleViewDetail(order.dhId)}>Xem chi tiết</button>
                                <button onClick={() => handleCancelOrder(order.dhId)}>Hủy</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default OrderList;