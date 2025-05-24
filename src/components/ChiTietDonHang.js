import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import styles from './styles/ChiTietDonHang.module.css';
import { format } from 'date-fns';
import { 
    fetchDonHangById,        // Import hàm đã đóng gói
    fetchChiTietDonHangNhap, // Import hàm đã đóng gói
    fetchChiTietDonHangXuat  // Import hàm đã đóng gói
} from './apiDonHang';

function OrderDetail() {
    const { DH_ID } = useParams(); // Tên tham số vẫn là DH_ID từ route
    const orderId = parseInt(DH_ID); 

    const [donHang, setDonHang] = useState(null);
    const [chiTietNhap, setChiTietNhap] = useState([]);
    const [chiTietXuat, setChiTietXuat] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (isNaN(orderId)) {
            console.error("ID đơn hàng không hợp lệ:", DH_ID);
            setError("ID đơn hàng không hợp lệ.");
            setLoading(false);
            return;
        }

        const fetchOrderDetails = async () => {
            try {
                setLoading(true);
                setError(null);

                // Sử dụng các hàm đã đóng gói
                const mainOrder = await fetchDonHangById(orderId);
                setDonHang(mainOrder);

                // Dựa vào việc khoNhap/khoXuat có tồn tại hay không để quyết định gọi API chi tiết nào
                if (mainOrder && mainOrder.khoNhap) {
                    const detailsNhap = await fetchChiTietDonHangNhap(orderId);
                    setChiTietNhap(detailsNhap || []); // Đảm bảo luôn là mảng
                } else {
                    setChiTietNhap([]);
                }

                if (mainOrder && mainOrder.khoXuat) {
                    const detailsXuat = await fetchChiTietDonHangXuat(orderId);
                    setChiTietXuat(detailsXuat || []); // Đảm bảo luôn là mảng
                } else {
                    setChiTietXuat([]);
                }

            } catch (err) {
                console.error("Lỗi khi tải chi tiết đơn hàng:", err);
                setError("Không thể tải chi tiết đơn hàng. Vui lòng thử lại: " + err.message); 
            } finally {
                setLoading(false);
            }
        };
        
        fetchOrderDetails();
    }, [DH_ID, orderId]); // Thêm DH_ID vào dependency array để đảm bảo useEffect chạy khi DH_ID thay đổi

    const formatDate = (dateString) => {
        if (!dateString) return '';
        try {
            // new Date() có thể nhận cả timestamp và ISO string
            const date = new Date(dateString); 
            if (isNaN(date.getTime())) {
                throw new Error("Invalid date string");
            }
            return format(date, 'yyyy-MM-dd HH:mm:ss');
        } catch (error) {
            console.error('Lỗi khi format ngày:', error);
            return dateString;
        }
    };

    if (loading) {
        return <div className={styles.loading}>Đang tải chi tiết đơn hàng...</div>;
    }

    if (error) {
        return <div className={styles.error}>Lỗi: {error}</div>;
    }

    if (!donHang) {
        return <div className={styles.notFound}>Không tìm thấy đơn hàng hoặc dữ liệu rỗng.</div>;
    }

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Chi tiết Đơn hàng: {donHang.maDH || "N/A"}</h2> 
            
            <div className={styles.orderInfo}>
                <p className={styles.infoItem}>
                    <strong>Mã đơn hàng:</strong> {donHang.maDH || "N/A"}
                </p>
                <p className={styles.infoItem}>
                    <strong>Ngày đặt:</strong> {donHang.ngayDatHang ? formatDate(donHang.ngayDatHang) : "N/A"} 
                </p>
                <p className={styles.infoItem}>
                    <strong>Trạng thái:</strong> {donHang.trangThai || "N/A"} 
                </p>
                <p className={styles.infoItem}>
                    <strong>Tổng tiền:</strong> {donHang.tongTien ? donHang.tongTien.toLocaleString('vi-VN') : "0"} VNĐ
                </p>
                <p className={styles.infoItem}>
                    <strong>Ghi chú:</strong> {donHang.ghiChu || "N/A"} 
                </p>
            </div>

            {/* Hiển thị thông tin kho nhập/xuất */}
            {donHang.khoNhap && (
                <div className={styles.shippingInfo}>
                    <h3>Thông tin Kho Nhập</h3>
                    <p className={styles.infoItem}>
                        <strong>Tên Kho:</strong> {donHang.khoNhap.tenKho || "N/A"}
                    </p>
                    <p className={styles.infoItem}>
                        <strong>Địa chỉ:</strong> {donHang.khoNhap.diaChi || "N/A"}
                    </p>
                </div>
            )}
            {donHang.khoXuat && ( 
                <div className={styles.shippingInfo}>
                    <h3>Thông tin Kho Xuất</h3>
                    <p className={styles.infoItem}>
                        <strong>Tên Kho:</strong> {donHang.khoXuat.tenKho || "N/A"}
                    </p>
                    <p className={styles.infoItem}>
                        <strong>Địa chỉ:</strong> {donHang.khoXuat.diaChi || "N/A"}
                    </p>
                </div>
            )}

            {/* Hiển thị chi tiết đơn hàng nhập nếu có */}
            <div className={styles.section}>
                <h3>Chi tiết Nhập</h3>
                {chiTietNhap.length > 0 ? (
                    <table className={styles.table}>
                        <thead>
                            <tr>
                                <th>Mã sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                                <th>Thành tiền</th>
                                <th>Ngày nhập kho</th>
                            </tr>
                        </thead>
                        <tbody>
                            {chiTietNhap.map((item, index) => ( 
                                <tr key={item.ctdhnId || index}> 
                                    <td>{item.sanPham ? item.sanPham.maSP : "N/A"}</td> {/* Giả định item.sanPham.maSP */}
                                    <td>{item.sanPham ? item.sanPham.tenSP : "N/A"}</td> {/* Giả định item.sanPham.tenSP */}
                                    <td>{item.soLuong || 0}</td>
                                    <td>{item.donGia ? item.donGia.toLocaleString('vi-VN') : 0} VNĐ</td>
                                    <td>{item.thanhTien ? item.thanhTien.toLocaleString('vi-VN') : 0} VNĐ</td>
                                    <td>{item.ngayNhapKho ? formatDate(item.ngayNhapKho) : "N/A"}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>Không có chi tiết nhập.</p>
                )}
            </div>

            {/* Hiển thị chi tiết đơn hàng xuất nếu có */}
            <div className={styles.section}>
                <h3>Chi tiết Xuất</h3>
                {chiTietXuat.length > 0 ? (
                    <table className={styles.table}>
                        <thead>
                            <tr>
                                <th>Mã sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                                <th>Thành tiền</th>
                                <th>Ngày xuất kho</th>
                            </tr>
                        </thead>
                        <tbody>
                            {chiTietXuat.map((item, index) => ( 
                                <tr key={item.ctdhxId || index}> 
                                    <td>{item.sanPham ? item.sanPham.maSP : "N/A"}</td> {/* Giả định item.sanPham.maSP */}
                                    <td>{item.sanPham ? item.sanPham.tenSP : "N/A"}</td> {/* Giả định item.sanPham.tenSP */}
                                    <td>{item.soLuong || 0}</td>
                                    <td>{item.donGia ? item.donGia.toLocaleString('vi-VN') : 0} VNĐ</td>
                                    <td>{item.thanhTien ? item.thanhTien.toLocaleString('vi-VN') : 0} VNĐ</td>
                                    <td>{item.ngayXuatKho ? formatDate(item.ngayXuatKho) : "N/A"}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>Không có chi tiết xuất.</p>
                )}
            </div>
        </div>
    );
}

export default OrderDetail;