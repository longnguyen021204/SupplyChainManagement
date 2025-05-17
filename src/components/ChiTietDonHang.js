import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom'; // Nếu bạn đang dùng React Router
import styles from './ChiTietDonHang.module.css'; // Import CSS Module
import { format } from 'date-fns'; // Import thư viện format ngày tháng

function OrderDetail() {
    const { DH_ID } = useParams(); // Lấy DH_ID từ URL (nếu dùng React Router)
    const [donHang, setDonHang] = useState(null);
    const [chiTietNhap, setChiTietNhap] = useState([]);
    const [chiTietXuat, setChiTietXuat] = useState([]);
    const [hoaDon, setHoaDon] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // **ĐỒNG ĐỘI: Thay đổi URL API ở đây**
    const API_URL = `/api/donhang/${DH_ID}/chitiet`; // Hoặc '/api/chitietdonhang/${DH_ID}'

    useEffect(() => {
        const fetchChiTietDonHang = async () => {
            setLoading(true);
            setError(null);
            try {
                const response = await fetch(API_URL);
                if (!response.ok) {
                    throw new Error(`Lỗi: ${response.status}`);
                }
                const data = await response.json();
                console.log("API Response:", data); // Log the entire response

                // Ensure data structure is as expected, handle missing fields
                setDonHang(data.donHang || null);
                setChiTietNhap(data.chiTietNhap || []);
                setChiTietXuat(data.chiTietXuat || []);
                setHoaDon(data.hoaDon || null);

            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchChiTietDonHang();
    }, [DH_ID]);

    const formatDate = (dateString) => {
        if (!dateString) return '';
        try {
            const date = new Date(dateString);
            return format(date, 'yyyy-MM-dd HH:mm:ss'); // Format đầy đủ
        } catch (error) {
            console.error('Lỗi khi format ngày:', error);
            return dateString;
        }
    };

    if (loading) {
        return <div>Đang tải chi tiết đơn hàng...</div>;
    }

    if (error) {
        return <div>Lỗi: {error}</div>;
    }

    if (!donHang) {
        return <div>Không tìm thấy đơn hàng.</div>;
    }

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Chi tiết Đơn hàng: {donHang.MaDH || "N/A"}</h2>
            <div className={styles.orderInfo}>
                <p className={styles.infoItem}>
                    <strong>Mã đơn hàng:</strong> {donHang.MaDH || "N/A"}
                </p>
                <p className={styles.infoItem}>
                    <strong>Ngày đặt:</strong> {donHang.NgayDatHang ? formatDate(donHang.NgayDatHang) : "N/A"}
                </p>
                <p className={styles.infoItem}>
                    <strong>Trạng thái:</strong> {donHang.TrangThai || "N/A"}
                </p>
                <p className={styles.infoItem}>
                    <strong>Tổng tiền:</strong> {donHang.TongTien || "0"}
                </p>
                <p className={styles.infoItem}>
                    <strong>Ghi chú:</strong> {donHang.GhiChu || "N/A"}
                </p>
            </div>

            {donHang.DTVC && (
                <div className={styles.shippingInfo}>
                    <h3>Thông tin Vận Chuyển</h3>
                    <p className={styles.infoItem}>
                        <strong>Đối tác vận chuyển:</strong> {donHang.DTVC.TenDoiTac || "N/A"}
                    </p>
                    <p className={styles.infoItem}>
                        <strong>Thông tin liên hệ:</strong> {donHang.DTVC.ThongTinLienHe || "N/A"}
                    </p>
                </div>
            )}


            <div className={styles.section}>
                <h3>Chi tiết Nhập</h3>
                {chiTietNhap && chiTietNhap.length > 0 ? (
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
                            {chiTietNhap.map((item) => (
                                <tr key={item.CTDHN_ID}>
                                    <td>{item.MaSanPham || "N/A"}</td>
                                    <td>{item.TenSanPham || "N/A"}</td>
                                    <td>{item.SoLuong || 0}</td>
                                    <td>{item.DonGia || 0}</td>
                                    <td>{item.ThanhTien || 0}</td>
                                    <td>{item.NgayNhapKho ? formatDate(item.NgayNhapKho) : "N/A"}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>Không có chi tiết nhập.</p>
                )}
            </div>

            <div className={styles.section}>
                <h3>Chi tiết Xuất</h3>
                {chiTietXuat && chiTietXuat.length > 0 ? (
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
                            {chiTietXuat.map((item) => (
                                <tr key={item.CTDHX_ID}>
                                    <td>{item.MaSanPham || "N/A"}</td>
                                    <td>{item.TenSanPham || "N/A"}</td>
                                    <td>{item.SoLuong || 0}</td>
                                    <td>{item.DonGia || 0}</td>
                                    <td>{item.ThanhTien || 0}</td>
                                    <td>{item.NgayXuatKho ? formatDate(item.NgayXuatKho) : "N/A"}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>Không có chi tiết xuất.</p>
                )}
            </div>

            {hoaDon && (
                <div className={styles.section}>
                    <h3>Thông tin Hóa Đơn</h3>
                    <p>
                        <strong>Mã HĐ:</strong> {hoaDon.MaHD || "N/A"}
                    </p>
                    <p>
                        <strong>Ngày Lập HĐ:</strong> {hoaDon.NgayLapHD ? formatDate(hoaDon.NgayLapHD) : "N/A"}
                    </p>
                    <p>
                        <strong>Tổng Tiền:</strong> {hoaDon.TongTien || 0}
                    </p>
                    <p>
                        <strong>Trạng Thái HĐ:</strong> {hoaDon.TrangThaiHD === 1 ? "Đã thanh toán" : "Chưa thanh toán"}
                    </p>
                </div>
            )}
        </div>
    );
}

export default OrderDetail;
