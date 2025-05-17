import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Để chuyển trang
import styles from './Login.module.css';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    // **Ở ĐÂY, BẠN SẼ THAY BẰNG LOGIC ĐĂNG NHẬP THỰC TẾ (gọi API)**
    if (username === 'admin' && password === '123') {
      // Đăng nhập thành công (MÔ PHỎNG)
      localStorage.setItem('isLoggedIn', 'true'); // Lưu trạng thái đăng nhập
      navigate('/'); // Chuyển đến trang chủ
      window.location.reload(); // Reload lại trang sau khi chuyển hướng
    } else {
      setError('Tên đăng nhập hoặc mật khẩu không đúng.');
    }
  };

  return (
    <div className={styles.loginContainer}>
      <h2 className={styles.loginTitle}>Đăng Nhập</h2>
      {error && <p className={styles.loginError}>{error}</p>}
      <form onSubmit={handleLogin} className={styles.loginForm}>
        <div className={styles.formGroup}>
          <label htmlFor="username">Tên đăng nhập:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="password">Mật khẩu:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className={styles.loginButton}>Đăng nhập</button>
      </form>
    </div>
  );
}

export default Login;