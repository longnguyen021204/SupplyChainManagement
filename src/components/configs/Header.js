import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styles from './Header.module.css';

const Header = ({ handleLogout }) => {
  const navigate = useNavigate();

  return (
    <header className={styles.appHeader}>
      <div className={styles.headerContainer}>
        {/* Phần bên trái - Logo và tên công ty */}
        <div className={styles.headerLeft}>
          <div className={styles.logoContainer}>
            <img
              src="https://image.similarpng.com/file/similarpng/very-thumbnail/2021/08/Illustration-of-Business-Finance-logo-design-on-transparent-background-PNG.png"
              alt="Logo công ty"
              className={styles.logo}
              onClick={() => navigate('/')}
            />
          </div>
          <h1
            className={styles.appTitle}
            onClick={() => navigate('/')}
          >
            Hệ Thống Quản Lý Chuỗi Cung Ứng
          </h1>
        </div>

        {/* Phần bên phải - Navigation */}
        <div className={styles.headerRight}>
          <nav className={styles.appNav}>
            <ul className={styles.navList}>
              <li className={styles.navItem}>
                <Link to="/suppliers" className={styles.navLink}>
                  <i className="fas fa-truck"></i> Nhà Cung Cấp
                </Link>
              </li>
              <li className={styles.navItem}>
                <Link to="/orders" className={styles.navLink}>
                  <i className="fas fa-clipboard-list"></i> Đơn Hàng
                </Link>
              </li>
              <li className={styles.navItem}>
                <Link to="/warehouses" className={styles.navLink}>
                  <i className="fas fa-warehouse"></i> Kho Hàng
                </Link>
              </li>
              <li className={styles.navItem}>
                <Link to="/payments" className={styles.navLink}>
                  <i className="fas fa-payments"></i>Thanh Toán
                  </Link>
              </li>
              <li className={styles.navItem}>
                <Link to="/support" className={styles.navLink}>
                  <i className="fas fa-headset"></i> Hỗ Trợ
                </Link>
              </li>
              <li className={styles.navItem}>
                <button onClick={handleLogout} className={styles.logoutButton}>
                  <i className="fas fa-sign-out-alt"></i> Đăng Xuất
                </button>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;