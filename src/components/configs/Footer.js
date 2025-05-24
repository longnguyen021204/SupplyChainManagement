import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Footer.module.css';

const Footer = () => {
  return (
    <footer className={styles.appFooter}>
      <div className={styles.footerContainer}>
        <div className={styles.footerContent}>
          <div className={styles.footerSection}>
            <h4>Liên hệ</h4>
            <p><i className="fas fa-map-marker-alt"></i> 123 Đường ABC, Quận 1, TP.HCM</p>
            <p><i className="fas fa-phone"></i> (028) 1234 5678</p>
            <p><i className="fas fa-envelope"></i> bsknpefoo20@gmail.com</p>
          </div>

          <div className={styles.footerSection}>
            <h4>Chức năng</h4>
            <ul>
              <li><Link to="/suppliers">Quản lý nhà cung cấp</Link></li>
              <li><Link to="/orders">Quản lý đơn hàng</Link></li>
              <li><Link to="/warehouses">Quản lý kho hàng</Link></li>
            </ul>
          </div>

          <div className={styles.footerSection}>
            <h4>Hỗ trợ</h4>
            <ul>
              <li><Link to="/support">Trung tâm hỗ trợ</Link></li>
              <li><a href="#">Hướng dẫn sử dụng</a></li>
              <li><a href="#">Câu hỏi thường gặp</a></li>
            </ul>
          </div>
        </div>

        <div className={styles.footerBottom}>
          <p>&copy; {new Date().getFullYear()} Hệ Thống Quản Lý Chuỗi Cung Ứng - Bản quyền thuộc về Công Ty ABC</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;