// apiDonHang.js

// Định nghĩa các URL Endpoint
export const OrderAPI = {
    // Quản lý đơn hàng (Chung)
    LIST: 'http://localhost:8080/SupplyChainManagement/api/donhang',
    CREATE: 'http://localhost:8080/SupplyChainManagement/api/donhang/new',
    DETAIL: (id) => `http://localhost:8080/SupplyChainManagement/api/donhang/${id}`,
    CANCEL: (maDH) => `http://localhost:8080/SupplyChainManagement/api/donhang/cancel/${maDH}`, // Endpoint hủy đơn hàng

    // Đơn hàng nhập
    LIST_NHAP: 'http://localhost:8080/SupplyChainManagement/api/nhapkho', // Endpoint này có vẻ không dùng trực tiếp
    DETAIL_NHAP: (id) => `http://localhost:8080/SupplyChainManagement/api/nhapkho/${id}`, // Lấy chi tiết nhập theo id DonHang
    CREATE_NHAP: 'http://localhost:8080/SupplyChainManagement/api/nhap-hang/new', // Có vẻ là tạo chi tiết nhập? (cần xem backend của cái này)

    // Đơn hàng xuất
    LIST_XUAT: 'http://localhost:8080/SupplyChainManagement/api/xuatkho', // Endpoint này có vẻ không dùng trực tiếp
    DETAIL_XUAT: (id) => `http://localhost:8080/SupplyChainManagement/api/xuatkho/${id}`, // Lấy chi tiết xuất theo id DonHang
    CREATE_XUAT: 'http://localhost:8080/SupplyChainManagement/api/xuat-hang/new', // Có vẻ là tạo chi tiết xuất? (cần xem backend của cái này)

    // Filter theo kho
    BY_INPUT_WAREHOUSE: (khoId) => `http://localhost:8080/SupplyChainManagement/api/donhang/donhang-nhap/${khoId}`,
    BY_OUTPUT_WAREHOUSE: (khoId) => `http://localhost:8080/SupplyChainManagement/api/donhang/donhang-xuat/${khoId}`,

    // === THÊM CÁC ENDPOINT SINH MÃ ĐƠN HÀNG ===
    GENERATE_MA_NHAP: 'http://localhost:8080/SupplyChainManagement/api/donhang/generate-ma-nhap',
    GENERATE_MA_XUAT: 'http://localhost:8080/SupplyChainManagement/api/donhang/generate-ma-xuat',
};

// THÊM API CHO KHO HÀNG
export const WarehouseAPI = {
    LIST: 'http://localhost:8080/SupplyChainManagement/api/khohang/',
    DETAIL: (id) => `http://localhost:8080/SupplyChainManagement/api/khohang/${id}`,
    // ... có thể thêm các API khác của kho hàng nếu cần
};

// Hàm gọi API chung
export async function apiCall(method, url, data = null) {
    const options = {
        method,
        headers: { 'Content-Type': 'application/json' },
    };

    if (data) options.body = JSON.stringify(data);

    const res = await fetch(url, options);
    if (!res.ok) {
        // Cố gắng đọc thông báo lỗi từ backend
        const errorText = await res.text();
        let errorMessage = `Lỗi: ${res.status} ${res.statusText}`;
        try {
            // Nếu backend trả về JSON lỗi, parse nó
            const errorJson = JSON.parse(errorText);
            errorMessage = errorJson.message || JSON.stringify(errorJson);
        } catch (e) {
            // Nếu không phải JSON, dùng nguyên văn
            errorMessage = errorText;
        }
        throw new Error(errorMessage);
    }
    return res.json();
}

// === CÁC HÀM GỌI API CỤ THỂ SỬ DỤNG apiCall ===

// Lấy danh sách đơn hàng
export const fetchDonHang = async () => {
    return apiCall('GET', OrderAPI.LIST);
};

// Lấy chi tiết một đơn hàng theo ID (primary key của DonHang)
export const fetchDonHangById = async (dhId) => {
    return apiCall('GET', OrderAPI.DETAIL(dhId));
};

// Tạo đơn hàng mới
export const createDonHang = async (donHangData) => {
    return apiCall('POST', OrderAPI.CREATE, donHangData);
};

// Hủy đơn hàng
export const cancelDonHang = async (maDH) => {
    return apiCall('DELETE', OrderAPI.CANCEL(maDH));
};

// === HÀM MỚI: SINH MÃ ĐƠN HÀNG ===
export const generateMaDonHangNhap = async () => {
    try {
        const response = await fetch('http://localhost:8080/SupplyChainManagement/api/donhang/generate-ma-nhap', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Vẫn nên gửi headers này nếu backend mong đợi JSON dù body trống
            },
            body: JSON.stringify({}) // Gửi body rỗng nếu backend cần một body hợp lệ
        });

        if (!response.ok) {
            const errorText = await response.text(); // Đọc lỗi dưới dạng text
            throw new Error(`HTTP error! Status: ${response.status}, Message: ${errorText}`);
        }

        const data = await response.text(); // <-- Thay response.json() bằng response.text()
        console.log("Mã đơn hàng nhận được:", data); // Kiểm tra dữ liệu
        return data; // 'DHN007'
    } catch (error) {
        console.error("Lỗi khi sinh mã đơn hàng nhập:", error);
        throw error;
    }
};

// Tương tự cho generateMaDonHangXuat
export const generateMaDonHangXuat = async () => {
    try {
        const response = await fetch('http://localhost:8080/SupplyChainManagement/api/donhang/generate-ma-xuat', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({})
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`HTTP error! Status: ${response.status}, Message: ${errorText}`);
        }

        const data = await response.text(); // <-- Thay response.json() bằng response.text()
        console.log("Mã đơn hàng nhận được:", data);
        return data; // 'DHX007'
    } catch (error) {
        console.error("Lỗi khi sinh mã đơn hàng xuất:", error);
        throw error;
    }
};

// === HÀM MỚI: LẤY CHI TIẾT ĐƠN HÀNG NHẬP/XUẤT THEO DH_ID ===
export const fetchChiTietDonHangNhap = async (donHangId) => {
    return apiCall('GET', OrderAPI.DETAIL_NHAP(donHangId));
};

export const fetchChiTietDonHangXuat = async (donHangId) => {
    return apiCall('GET', OrderAPI.DETAIL_XUAT(donHangId));
};

// === HÀM MỚI: LẤY DANH SÁCH KHO HÀNG ===
export const fetchWarehouses = async () => {
    return apiCall('GET', WarehouseAPI.LIST);
};

// Nếu bạn có các hàm tạo/xem chi tiết nhập/xuất riêng biệt, hãy thêm vào đây
// Ví dụ:
// export const createChiTietDonHangNhap = async (chiTietData) => {
//     return apiCall('POST', OrderAPI.CREATE_NHAP, chiTietData);
// };
// export const createChiTietDonHangXuat = async (chiTietData) => {
//     return apiCall('POST', OrderAPI.CREATE_XUAT, chiTietData);
// };