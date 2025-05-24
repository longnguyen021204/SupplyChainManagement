import React, { useState, useEffect } from 'react';
import styles from './styles/QLKhoHang.module.css';

const API = {
  WAREHOUSE: 'http://localhost:8080/SupplyChainManagement/api/khohang/',
  INVENTORY: 'http://localhost:8080/SupplyChainManagement/api/quanlykho',
};

export default function QLKhoHang() {
  const [searchTerm, setSearchTerm] = useState('');
  const [warehouses, setWarehouses] = useState([]);
  const [inventory, setInventory] = useState([]);
  const [selected, setSelected] = useState(null);
  const [tab, setTab] = useState('info');
  const [newProduct, setNewProduct] = useState({ maSanPham: '', tenSanPham: '', soLuongTon: 0 });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    try {
      const [wRes, iRes] = await Promise.all([
        fetch(API.WAREHOUSE),
        fetch(API.INVENTORY)
      ]);
      setWarehouses(await wRes.json());
      setInventory(await iRes.json());
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchData(); }, []);

  const handleApi = async (method, url, data, cb) => {
    try {
      const res = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: data ? JSON.stringify(data) : undefined
      });
      const resData = await res.json();
      if (!res.ok) throw new Error(resData.message || 'Error');
      cb && cb(resData);
    } catch (err) {
      setError(err.message);
    }
  };

  const getProducts = (id) => inventory.filter(i => i.khoHang?.khoId === id);

  const addProduct = () => {
    const payload = { ...newProduct, khoHang: { khoId: selected.khoId } };
    handleApi('POST', `${API.INVENTORY}/add`, payload, (newProd) => {
      setInventory(prev => [...prev, newProd]);
      setSelected(s => ({ ...s, products: [...(s.products || []), newProd] }));
      setNewProduct({ maSanPham: '', tenSanPham: '', soLuongTon: 0 });
    });
  };

  const showOutOfStock = () => {
    handleApi('GET', `${API.INVENTORY}/het-hang`, null, console.log);
  };

  if (loading) return <div className={styles.loading}>Đang tải...</div>;
  if (error) return <div className={styles.error}>Lỗi: {error}</div>;

  return (
    <div className={styles.container}>
      <h2>Quản lý Kho Hàng</h2>
      <div className={styles.controls}>
        <input
          value={searchTerm}
          onChange={e => setSearchTerm(e.target.value)}
          placeholder="Tìm kho..."
          className={styles.searchInput}
        />
        <button onClick={showOutOfStock} className={styles.warningButton}>Xem SP hết hàng</button>
      </div>

      <table className={styles.table}>
        <thead><tr><th>ID</th><th>Tên kho</th><th>Địa chỉ</th><th>Số SP</th><th>Hành động</th></tr></thead>
        <tbody>
          {warehouses.filter(w => w.tenKho.toLowerCase().includes(searchTerm.toLowerCase()) || w.diaChi.toLowerCase().includes(searchTerm.toLowerCase()))
            .map(w => (
              <tr key={w.khoId}>
                <td>{w.khoId}</td>
                <td>{w.tenKho}</td>
                <td>{w.diaChi}</td>
                <td>{getProducts(w.khoId).length}</td>
                <td><button onClick={() => setSelected({ ...w, products: getProducts(w.khoId) })}>Chi tiết</button></td>
              </tr>
            ))}
        </tbody>
      </table>

      {selected && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <div className={styles.modalHeader}>
              <h3>Kho: {selected.tenKho}</h3>
              <button onClick={() => setSelected(null)}>&times;</button>
            </div>
            <div className={styles.tabs}>
              {['info', 'products'].map(t => (
                <button key={t} onClick={() => setTab(t)} className={tab === t ? styles.active : ''}>
                  {t === 'info' ? 'Thông tin' : `Sản phẩm (${selected.products?.length || 0})`}
                </button>
              ))}
            </div>

            {tab === 'info' ? (
              <div className={styles.infoTab}>
                <p><strong>ID:</strong> {selected.khoId}</p>
                <p><strong>Địa chỉ:</strong> {selected.diaChi}</p>
              </div>
            ) : (
              <div className={styles.productsTab}>
                <div className={styles.productForm}>
                  <input placeholder="Mã SP" value={newProduct.maSanPham} onChange={e => setNewProduct({ ...newProduct, maSanPham: e.target.value })} />
                  <input placeholder="Tên SP" value={newProduct.tenSanPham} onChange={e => setNewProduct({ ...newProduct, tenSanPham: e.target.value })} />
                  <input type="number" placeholder="Số lượng" value={newProduct.soLuongTon} onChange={e => setNewProduct({ ...newProduct, soLuongTon: +e.target.value || 0 })} />
                  <button onClick={addProduct}>Thêm sản phẩm</button>
                </div>
                <table className={styles.productsTable}>
                  <thead><tr><th>Mã SP</th><th>Tên SP</th><th>Số lượng</th></tr></thead>
                  <tbody>
                    {selected.products?.map(p => (
                      <tr key={p.qlkId}><td>{p.maSanPham}</td><td>{p.tenSanPham}</td><td>{p.soLuongTon}</td></tr>
                    ))}
                  </tbody>
                </table>
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}
