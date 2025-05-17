import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link, useNavigate } from 'react-router-dom';
import styles from './App.module.css';
import SupplierList from './components/NhaCungCap';
import OrderList from './components/QLDonHang';
import WarehouseList from './components/QLKhoHang';
import OrderDetail from './components/ChiTietDonHang';
import Login from './components/Login';

function AppWrapper() {
  return (
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  const [dashboardData, setDashboardData] = useState({
    totalSuppliers: 0,
    totalOrders: 0,
    totalProductsInStock: 0,
  });

  useEffect(() => {
    const storedLoginState = localStorage.getItem('isLoggedIn');
    if (storedLoginState === 'true') {
      setIsLoggedIn(true);
    }
  }, []);

  useEffect(() => {
    if (isLoggedIn) {
      const fetchDashboardData = async () => {
        try {
          const response = await fetch('/api/dashboard'); // Thay bằng endpoint thực tế
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          const data = await response.json();
          setDashboardData(data);
        } catch (error) {
          console.error("Could not fetch dashboard data:", error);
        }
      };

      fetchDashboardData();
    }
  }, [isLoggedIn]);

  const handleLogout = () => {
    localStorage.removeItem('isLoggedIn');
    setIsLoggedIn(false);
    navigate('/login');
  };

  const handleAddSupplier = () => {
    navigate('/suppliers/add');
  };

  const handleCreateOrder = () => {
    navigate('/orders/create');
  };

  const handleViewStock = () => {
    navigate('/warehouses');
  };

  return (
    <div className={styles.appContainer}>
      {isLoggedIn ? (
        <>
          <header className={styles.appHeader}>
            <div className={styles.headerContainer}>
              <h1 className={styles.appTitle}>Hệ Thống Quản Lý Chuỗi Cung Ứng</h1>
              <nav className={styles.appNav}>
                <ul className={styles.navList}>
                  <li className={styles.navItem}>
                    <Link to="/suppliers" className={styles.navLink}>Nhà Cung Cấp</Link>
                  </li>
                  <li className={styles.navItem}>
                    <Link to="/orders" className={styles.navLink}>Đơn Hàng</Link>
                  </li>
                  <li className={styles.navItem}>
                    <Link to="/warehouses" className={styles.navLink}>Kho Hàng</Link>
                  </li>
                  <li className={styles.navItem}>
                    <button onClick={handleLogout}>Đăng xuất</button>
                  </li>
                </ul>
              </nav>
            </div>
          </header>

          <main className={styles.appMain}>
            <div className={styles.mainContainer}>
              <Routes>
                <Route path="/suppliers" element={<SupplierList />} />
                <Route path="/orders" element={<OrderList />} />
                <Route path="/warehouses" element={<WarehouseList />} />
                <Route path="/order-detail/:id" element={<OrderDetail />} />
                <Route path="/" element={
                  <div className={styles.dashboard}>
                    <h2 className={styles.sectionTitle}>Tổng Quan</h2>
                    <div className={styles.summaryCards}>
                      <div className={styles.card}>
                        <h3>🏭 Tổng Nhà Cung Cấp</h3>
                        <p className={styles.value}>{dashboardData.totalSuppliers}</p>
                      </div>
                      <div className={styles.card}>
                        <h3>📦 Tổng Đơn Hàng</h3>
                        <p className={styles.value}>{dashboardData.totalOrders}</p>
                      </div>
                      <div className={styles.card}>
                        <h3>📦 Sản Phẩm Trong Kho</h3>
                        <p className={styles.value}>{dashboardData.totalProductsInStock}</p>
                      </div>
                    </div>

                    <h2 className={styles.sectionTitle}>Truy Cập Nhanh</h2>
                    <div className={styles.actionCards}>
                      <button className={styles.actionBtn} onClick={handleAddSupplier}>➕ Thêm Nhà Cung Cấp</button>
                      <button className={styles.actionBtn} onClick={handleCreateOrder}>📝 Tạo Đơn Hàng</button>
                      <button className={styles.actionBtn} onClick={handleViewStock}>🔍 Xem Tồn Kho</button>
                    </div>
                  </div>
                } />
                <Route path="/login" element={<Login onLogin={() => setIsLoggedIn(true)} />} />
              </Routes>
            </div>
          </main>

          <footer className={styles.appFooter}>
            <div className={styles.footerContainer}>
              <p>&copy; {new Date().getFullYear()} Hệ Thống Quản Lý Chuỗi Cung Ứng</p>
            </div>
          </footer>
        </>
      ) : (
        <Login onLogin={() => setIsLoggedIn(true)} />
      )}
    </div>
  );
}

export default AppWrapper;
