// ThanhToan.js
import React from 'react';
import styles from './styles/ThanhToan.module.css';

const payments = [
  {
    TT_ID: 1,
    DH_ID: 1,
    LoaiThanhToan: 'Chuyển khoản',
    SoTien: 1500000,
    NgayThanhToan: '2024-05-10 09:30',
    TrangThaiTT: 'Đã thanh toán',
    ThongTinTT: 'Giao dịch qua ngân hàng ACB',
  },
  {
    TT_ID: 2,
    DH_ID: 2,
    LoaiThanhToan: 'Tiền mặt',
    SoTien: 750000,
    NgayThanhToan: null,
    TrangThaiTT: 'Chưa thanh toán',
    ThongTinTT: '',
  },
  {
    TT_ID: 3,
    DH_ID: 3,
    LoaiThanhToan: 'Ví điện tử',
    SoTien: 2500000,
    NgayThanhToan: '2024-05-12 14:20',
    TrangThaiTT: 'Đã thanh toán',
    ThongTinTT: 'ZaloPay giao dịch 123456',
  },
];

function ThanhToan() {
  return (
    <div className={styles.container}>
      <h2 className={styles.title}>Quản Lý Thanh Toán</h2>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>Mã TT</th>
            <th>Đơn Hàng</th>
            <th>Loại</th>
            <th>Số Tiền</th>
            <th>Ngày Thanh Toán</th>
            <th>Trạng Thái</th>
            <th>Thông Tin</th>
          </tr>
        </thead>
        <tbody>
          {payments.map((tt) => (
            <tr key={tt.TT_ID}>
              <td>{tt.TT_ID}</td>
              <td>{tt.DH_ID}</td>
              <td>{tt.LoaiThanhToan}</td>
              <td>{tt.SoTien.toLocaleString()} VND</td>
              <td>{tt.NgayThanhToan || '---'}</td>
              <td className={styles[`status_${tt.TrangThaiTT.replace(/\s/g, '').toLowerCase()}`]}>
                {tt.TrangThaiTT}
              </td>
              <td>{tt.ThongTinTT || 'Không có'}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ThanhToan;
