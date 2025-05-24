import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link, useNavigate } from 'react-router-dom';
import styles from './App.module.css';
import SupplierList from './components/NhaCungCap';
import OrderList from './components/QLDonHang';
import WarehouseList from './components/QLKhoHang';
import OrderDetail from './components/ChiTietDonHang';
import Login from './components/Login';
import CustomerSupport from './components/HoTroKhachHang';
import Header from './components/configs/Header';
import Footer from './components/configs/Footer';
import ThanhToan from './components/ThanhToan';

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

  // State modal & new supplier (từ phần mới)
  const [showAddModal, setShowAddModal] = useState(false);
  const [newSupplier, setNewSupplier] = useState({
    tenNCC: '',
    diaChi: '',
    soDienThoai: '',
    email: '',
    thongTinLienHe: '',
    dieuKienThanhToan: ''
  });

  useEffect(() => {
    const storedLoginState = localStorage.getItem('isLoggedIn');
    if (storedLoginState === 'true') {
      setIsLoggedIn(true);
    }
  }, []);

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        const res = await fetch('http://localhost:8080/SupplyChainManagement/api/suppliers/');
        const data = await res.json();
        if (!Array.isArray(data)) throw new Error('Dữ liệu không phải mảng');

        setDashboardData(prev => ({
          ...prev,
          totalSuppliers: data.length,
        }));
      } catch (error) {
        console.error('❌ Lỗi khi lấy nhà cung cấp:', error.message);
      }
    };

    if (isLoggedIn) {
      fetchDashboardData();
    }
  }, [isLoggedIn]);

  const handleLogout = () => {
    localStorage.removeItem('isLoggedIn');
    setIsLoggedIn(false);
    navigate('/login');
  };

  const handleAddSupplier = () => {
    setShowAddModal(true);
  };

  const handleCreateOrder = () => {
    navigate('/orders/create');
  };

  const handleViewStock = () => {
    navigate('/warehouses');
  };

  const handleSaveNewSupplier = async () => {
    try {
      const res = await fetch('http://localhost:8080/SupplyChainManagement/api/suppliers/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newSupplier)
      });
      if (!res.ok) throw new Error('Lỗi khi thêm nhà cung cấp!');
      await res.json();
      alert('✅ Đã thêm nhà cung cấp!');
      setShowAddModal(false);
      setNewSupplier({
        tenNCC: '',
        diaChi: '',
        soDienThoai: '',
        email: '',
        thongTinLienHe: '',
        dieuKienThanhToan: ''
      });
      setDashboardData(prev => ({
        ...prev,
        totalSuppliers: prev.totalSuppliers + 1
      }));
    } catch (err) {
      alert('❌ ' + err.message);
    }
  };

  return (
    <div className={styles.appContainer}>
      {isLoggedIn ? (
        <>
          <Header handleLogout={handleLogout} />
          
          <main className={styles.appMain}>
            <div className={styles.mainContainer}>
              <Routes>
                <Route path="/suppliers" element={<SupplierList />} />
                <Route path="/orders" element={<OrderList />} />
                <Route path="/warehouses" element={<WarehouseList />} />
<Route path="/order-detail/:DH_ID" element={<OrderDetail />} />
                <Route path="/payments" element={<ThanhToan />} />
                <Route path="/support" element={<CustomerSupport />} />
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

          <Footer />

          {/* Modal thêm nhà cung cấp */}
          {showAddModal && (
            <div className={styles.modal}>
              <div className={styles.modalContent}>
                <h3>Thêm Nhà Cung Cấp Mới</h3>
                {['tenNCC', 'diaChi', 'soDienThoai', 'email', 'thongTinLienHe'].map(field => (
                  <div key={field} className={styles.formGroup}>
                    <label>{{
                      tenNCC: 'Tên nhà cung cấp',
                      diaChi: 'Địa chỉ',
                      soDienThoai: 'Số điện thoại',
                      email: 'Email',
                      thongTinLienHe: 'Thông tin liên hệ'
                    }[field]}</label>
                    <input
                      type={field === 'email' ? 'email' : 'text'}
                      value={newSupplier[field]}
                      onChange={e => setNewSupplier(prev => ({ ...prev, [field]: e.target.value }))}
                    />
                  </div>
                ))}
                <div className={styles.formGroup}>
                  <label>Điều kiện thanh toán:</label>
                  <textarea
                    value={newSupplier.dieuKienThanhToan}
                    onChange={e => setNewSupplier(prev => ({ ...prev, dieuKienThanhToan: e.target.value }))}
                  />
                </div>
                <div className={styles.modalActions}>
                  <button className={styles.buttonSave} onClick={handleSaveNewSupplier}>Lưu</button>
                  <button className={styles.buttonCancel} onClick={() => setShowAddModal(false)}>Hủy</button>
                </div>
              </div>
            </div>
          )}
        </>
      ) : (
        <Login onLogin={() => setIsLoggedIn(true)} />
      )}
    </div>
  );
}

export default AppWrapper;