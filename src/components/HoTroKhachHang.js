import React, { useState } from 'react';
import styles from './styles/CustomerSupport.module.css';
import emailjs from '@emailjs/browser';

function CustomerSupport() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        topic: 'order',
        message: ''
    });

    const [submitted, setSubmitted] = useState(false);
    const [faqSearchTerm, setFaqSearchTerm] = useState(''); // State for FAQ search input
    const [openFaqIndex, setOpenFaqIndex] = useState(null); // State to manage which FAQ item is open

    // Sample FAQ data for INTERNAL/SUPPLIER SUPPORT
    const faqs = [
        {
            question: "Làm thế nào để thêm một nhà cung cấp mới vào hệ thống?",
            answer: "Bạn có thể thêm nhà cung cấp mới bằng cách truy cập mục 'Quản lý Nhà Cung Cấp' từ menu chính, sau đó nhấp vào nút 'Thêm Nhà Cung Cấp Mới' và điền thông tin chi tiết vào biểu mẫu."
        },
        {
            question: "Cách cập nhật trạng thái đơn hàng nhập kho từ nhà cung cấp?",
            answer: "Trong mục 'Quản lý Đơn Hàng', tìm kiếm đơn hàng nhập kho cần cập nhật. Bạn có thể thay đổi trạng thái như 'Đã xác nhận', 'Đang vận chuyển', 'Đã nhận hàng' và cập nhật số lượng tồn kho."
        },
        {
            question: "Tôi phải làm gì nếu thông tin vận chuyển của đơn hàng bị sai?",
            answer: "Vui lòng vào 'Quản lý Vận Chuyển và Giao Hàng', tìm đơn hàng bị lỗi. Nếu chưa đến thời điểm giao hàng, bạn có thể chỉnh sửa trực tiếp. Nếu hàng đã trên đường, hãy gửi yêu cầu hỗ trợ ngay với tiêu đề 'Sai thông tin vận chuyển' để đội ngũ kỹ thuật can thiệp."
        },
        {
            question: "Làm thế nào để xem báo cáo hiệu suất của một nhà cung cấp cụ thể?",
            answer: "Bạn có thể xem báo cáo hiệu suất chi tiết của nhà cung cấp trong mục 'Báo Cáo và Phân Tích'. Chọn 'Báo cáo hiệu suất nhà cung cấp' và lọc theo tên nhà cung cấp để xem các chỉ số như chất lượng, giao hàng đúng hạn và giá cả."
        },
        {
            question: "Hệ thống quản lý kho hàng cập nhật tồn kho như thế nào?",
            answer: "Số lượng tồn kho được cập nhật tự động khi có đơn hàng nhập (tăng) hoặc đơn hàng xuất (giảm). Bạn có thể kiểm tra chi tiết tồn kho hiện tại trong mục 'Quản lý Đơn Hàng', 'Theo dõi tồn kho'."
        },
        {
            question: "Tôi không thể truy cập vào một số tính năng của hệ thống. Đây có phải là lỗi không?",
            answer: "Quyền truy cập vào các tính năng có thể khác nhau tùy thuộc vào vai trò người dùng của bạn. Nếu bạn cho rằng mình cần quyền truy cập vào một tính năng cụ thể mà không thể sử dụng, vui lòng gửi yêu cầu hỗ trợ với chi tiết về vấn đề và vai trò của bạn."
        },
        {
            question: "Làm thế nào để xuất dữ liệu báo cáo ra file Excel?",
            answer: "Hầu hết các báo cáo trong mục 'Báo Cáo và Phân Tích' đều có tùy chọn 'Xuất Excel' hoặc biểu tượng tải xuống ở góc trên bên phải của báo cáo. Nhấp vào đó để tải dữ liệu về."
        }
    ];

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleFaqSearchChange = (e) => {
        setFaqSearchTerm(e.target.value);
        // Reset open FAQ when searching
        setOpenFaqIndex(null);
    };

    const toggleFaq = (index) => {
        // If the clicked FAQ is already open, close it. Otherwise, open the clicked FAQ.
        setOpenFaqIndex(openFaqIndex === index ? null : index);
    };

    const topicLabels = {
        supplier: "Quản lý Nhà Cung Cấp",
        order: "Quản lý Đơn Hàng",
        shipment: "Quản lý Vận Chuyển",
        payment: "Quản lý Thanh Toán",
        report: "Báo Cáo & Phân Tích",
        technical: "Lỗi Kỹ Thuật",
        other: "Khác"
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Tạo payload cho email gửi đến admin
        const adminPayload = {
            name: formData.name,
            email: formData.email,
            topic: topicLabels[formData.topic] || formData.topic,
            message: formData.message,
            time: new Date().toLocaleString('vi-VN')
        };

        // Tạo payload cho email auto-reply gửi đến người dùng
        const userPayload = {
            name: formData.name,
            email: formData.email,
            topic: topicLabels[formData.topic] || formData.topic,
            message: formData.message,
            time: new Date().toLocaleString('vi-VN'),
            support_email: "support@yourcompany.com", // Thêm email hỗ trợ
            phone_support: "0123 456 789" // Thêm số điện thoại hỗ trợ
        };

        // Gửi email đến admin (template_bp26qv6)
        emailjs.send(
            'service_qsnc70d',
            'template_bp26qv6',
            adminPayload,
            'vbFM6lHSY7OqUO9Ba'
        ).then(() => {
            console.log('Email gửi admin thành công');

            // Sau khi gửi cho admin thành công, gửi auto-reply cho người dùng
            // Sử dụng template khác cho auto-reply (tạo template mới trong EmailJS)
            return emailjs.send(
                'service_qsnc70d', // Same service ID
                'template_7wqvzb5', // Tạo template mới cho auto-reply
                userPayload,
                'vbFM6lHSY7OqUO9Ba'
            );
        }).then(() => {
            console.log('Auto-reply gửi thành công');
            setSubmitted(true);
            setFormData({
                name: '',
                email: '',
                topic: 'order',
                message: ''
            });
        }).catch((error) => {
            console.error('Lỗi gửi email:', error);
            alert('Đã có lỗi khi gửi yêu cầu. Vui lòng thử lại sau.');
        });
    };
    // Filter FAQs based on search term
    const filteredFaqs = faqs.filter(faq =>
        faq.question.toLowerCase().includes(faqSearchTerm.toLowerCase()) ||
        faq.answer.toLowerCase().includes(faqSearchTerm.toLowerCase())
    );

    return (
        <div className={styles.supportContainer}>
            <h2 className={styles.title}>Trung Tâm Hỗ Trợ Người Dùng</h2>
            <p className={styles.description}>
                Đây là kênh hỗ trợ dành cho người quản lý chuỗi cung ứng, các phòng ban nội bộ và đối tác. Vui lòng tìm kiếm câu hỏi thường gặp hoặc gửi yêu cầu hỗ trợ mới.
            </p>

            {/* FAQ Search Section */}
            <div className={styles.faqSearchSection}>
                <h3>Tìm kiếm Câu Hỏi Thường Gặp (FAQ)</h3>
                <input
                    type="text"
                    placeholder="Tìm kiếm câu hỏi..."
                    value={faqSearchTerm}
                    onChange={handleFaqSearchChange}
                    className={styles.faqSearchInput}
                />
            </div>

            <div className={styles.faqListSection}>
                {filteredFaqs.length > 0 ? (
                    filteredFaqs.map((faq, index) => (
                        <div key={index} className={`${styles.faqItem} ${openFaqIndex === index ? styles.open : ''}`}>
                            <h4 onClick={() => toggleFaq(index)} className={styles.faqQuestion}>
                                {faq.question}
                                <span className={styles.toggleIcon}>{openFaqIndex === index ? '−' : '+'}</span>
                            </h4>
                            <div className={styles.faqAnswer}>
                                <p>{faq.answer}</p>
                            </div>
                        </div>
                    ))
                ) : (
                    <p className={styles.noFaqFound}>Không tìm thấy câu hỏi phù hợp.</p>
                )}
            </div>

            <p className={styles.submitPrompt}>
                Bạn vẫn chưa tìm được câu trả lời cho vấn đề của mình? Hãy liên hệ với chúng tôi!
            </p>

            <hr className={styles.divider} />

            <h2 className={styles.title}>Gửi Yêu Cầu Hỗ Trợ Mới</h2>
            {submitted ? (
                <div className={styles.successMessage}>
                    Yêu cầu của bạn đã được gửi thành công. Chúng tôi sẽ phản hồi qua email của bạn trong thời gian sớm nhất.
                </div>
            ) : (
                <form className={styles.form} onSubmit={handleSubmit}>
                    <div className={styles.formGroup}>
                        <label htmlFor="name">Họ và tên</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            required
                            value={formData.name}
                            onChange={handleChange}
                        />
                    </div>

                    <div className={styles.formGroup}>
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            required
                            value={formData.email}
                            onChange={handleChange}
                        />
                    </div>

                    <div className={styles.formGroup}>
                        <label htmlFor="topic">Chủ đề</label>
                        <select
                            id="topic"
                            name="topic"
                            value={formData.topic}
                            onChange={handleChange}
                        >
                            <option value="supplier">Quản lý Nhà Cung Cấp</option>
                            <option value="order">Quản lý Đơn Hàng</option>
                            <option value="shipment">Quản lý Vận Chuyển</option>
                            <option value="payment">Quản lý Thanh Toán</option>
                            <option value="report">Báo Cáo & Phân Tích</option>
                            <option value="technical">Lỗi Kỹ Thuật</option>
                            <option value="other">Khác</option>
                        </select>
                    </div>

                    <div className={styles.formGroup}>
                        <label htmlFor="message">Nội dung</label>
                        <textarea
                            id="message"
                            name="message"
                            rows="5"
                            required
                            value={formData.message}
                            onChange={handleChange}
                        ></textarea>
                    </div>

                    <button type="submit" className={styles.submitButton}>
                        Gửi yêu cầu
                    </button>
                </form>
            )}
        </div>
    );
}

export default CustomerSupport;