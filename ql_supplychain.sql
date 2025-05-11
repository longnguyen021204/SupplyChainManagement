CREATE DATABASE  IF NOT EXISTS `qlchuoicungung` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlchuoicungung`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: qlchuoicungung
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chiphi`
--

DROP TABLE IF EXISTS `chiphi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chiphi` (
  `CP_ID` int NOT NULL AUTO_INCREMENT,
  `LoaiChiPhi` varchar(100) NOT NULL,
  `MoTa` varchar(255) DEFAULT NULL,
  `SoTien` decimal(15,2) NOT NULL,
  `NgayPhatSinh` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `DH_ID` int DEFAULT NULL,
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CP_ID`),
  KEY `DH_ID` (`DH_ID`),
  CONSTRAINT `chiphi_ibfk_1` FOREIGN KEY (`DH_ID`) REFERENCES `donhang` (`DH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chiphi`
--

LOCK TABLES `chiphi` WRITE;
/*!40000 ALTER TABLE `chiphi` DISABLE KEYS */;
/*!40000 ALTER TABLE `chiphi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietdonhangnhap`
--

DROP TABLE IF EXISTS `chitietdonhangnhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietdonhangnhap` (
  `CTDHN_ID` int NOT NULL AUTO_INCREMENT,
  `DH_ID` int NOT NULL,
  `NCC_ID` int NOT NULL,
  `MaSanPham` varchar(50) NOT NULL,
  `TenSanPham` varchar(255) NOT NULL,
  `SoLuong` int NOT NULL DEFAULT '0',
  `DonGia` decimal(15,2) NOT NULL DEFAULT '0.00',
  `ThanhTien` decimal(15,2) DEFAULT '0.00',
  `NgayNhapKho` timestamp NULL DEFAULT NULL,
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CTDHN_ID`),
  KEY `DH_ID` (`DH_ID`),
  KEY `NCC_ID` (`NCC_ID`),
  CONSTRAINT `chitietdonhangnhap_ibfk_1` FOREIGN KEY (`DH_ID`) REFERENCES `donhang` (`DH_ID`),
  CONSTRAINT `chitietdonhangnhap_ibfk_2` FOREIGN KEY (`NCC_ID`) REFERENCES `nhacungcap` (`NCC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietdonhangnhap`
--

LOCK TABLES `chitietdonhangnhap` WRITE;
/*!40000 ALTER TABLE `chitietdonhangnhap` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietdonhangnhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietdonhangxuat`
--

DROP TABLE IF EXISTS `chitietdonhangxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietdonhangxuat` (
  `CTDHX_ID` int NOT NULL AUTO_INCREMENT,
  `DH_ID` int NOT NULL,
  `MaSanPham` varchar(50) NOT NULL,
  `TenSanPham` varchar(255) NOT NULL,
  `SoLuong` int NOT NULL DEFAULT '0',
  `DonGia` decimal(15,2) NOT NULL DEFAULT '0.00',
  `ThanhTien` decimal(15,2) DEFAULT '0.00',
  `NgayXuatKho` timestamp NULL DEFAULT NULL,
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CTDHX_ID`),
  KEY `DH_ID` (`DH_ID`),
  CONSTRAINT `chitietdonhangxuat_ibfk_1` FOREIGN KEY (`DH_ID`) REFERENCES `donhang` (`DH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietdonhangxuat`
--

LOCK TABLES `chitietdonhangxuat` WRITE;
/*!40000 ALTER TABLE `chitietdonhangxuat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietdonhangxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doitacvanchuyen`
--

DROP TABLE IF EXISTS `doitacvanchuyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doitacvanchuyen` (
  `DTVC_ID` int NOT NULL AUTO_INCREMENT,
  `TenDoiTac` varchar(255) NOT NULL,
  `ThongTinLienHe` varchar(255) DEFAULT NULL,
  `DiemDanhGia` float DEFAULT '0',
  `SoLanDanhGia` int DEFAULT '0',
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`DTVC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doitacvanchuyen`
--

LOCK TABLES `doitacvanchuyen` WRITE;
/*!40000 ALTER TABLE `doitacvanchuyen` DISABLE KEYS */;
/*!40000 ALTER TABLE `doitacvanchuyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dongia`
--

DROP TABLE IF EXISTS `dongia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dongia` (
  `DG_ID` int NOT NULL AUTO_INCREMENT,
  `MaSanPham` varchar(50) NOT NULL,
  `NCC_ID` int NOT NULL,
  `DonGiaMua` decimal(15,2) NOT NULL,
  `NgayApDung` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`DG_ID`),
  UNIQUE KEY `MaSanPham` (`MaSanPham`,`NCC_ID`),
  KEY `NCC_ID` (`NCC_ID`),
  CONSTRAINT `dongia_ibfk_1` FOREIGN KEY (`NCC_ID`) REFERENCES `nhacungcap` (`NCC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dongia`
--

LOCK TABLES `dongia` WRITE;
/*!40000 ALTER TABLE `dongia` DISABLE KEYS */;
/*!40000 ALTER TABLE `dongia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donhang`
--

DROP TABLE IF EXISTS `donhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donhang` (
  `DH_ID` int NOT NULL AUTO_INCREMENT,
  `MaDH` varchar(50) NOT NULL,
  `NgayDatHang` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TrangThai` varchar(50) DEFAULT 'Mới',
  `TongTien` decimal(15,2) DEFAULT '0.00',
  `GhiChu` text,
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`DH_ID`),
  UNIQUE KEY `MaDH` (`MaDH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donhang`
--

LOCK TABLES `donhang` WRITE;
/*!40000 ALTER TABLE `donhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `donhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `HD_ID` int NOT NULL AUTO_INCREMENT,
  `MaHD` varchar(50) NOT NULL,
  `DH_ID` int NOT NULL,
  `NgayLapHD` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TongTien` decimal(15,2) NOT NULL,
  `TrangThaiHD` tinyint(1) DEFAULT '0',
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`HD_ID`),
  UNIQUE KEY `MaHD` (`MaHD`),
  KEY `DH_ID` (`DH_ID`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`DH_ID`) REFERENCES `donhang` (`DH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotrokhachhang`
--

DROP TABLE IF EXISTS `hotrokhachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotrokhachhang` (
  `HTKH_ID` int NOT NULL AUTO_INCREMENT,
  `DH_ID` int DEFAULT NULL,
  `TenKhachHang` varchar(255) DEFAULT NULL,
  `EmailKhachHang` varchar(100) DEFAULT NULL,
  `SoDienThoaiKhachHang` varchar(20) DEFAULT NULL,
  `TieuDe` varchar(255) NOT NULL,
  `NoiDung` text NOT NULL,
  `TrangThaiHT` varchar(50) DEFAULT 'Mới',
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`HTKH_ID`),
  KEY `DH_ID` (`DH_ID`),
  CONSTRAINT `hotrokhachhang_ibfk_1` FOREIGN KEY (`DH_ID`) REFERENCES `donhang` (`DH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotrokhachhang`
--

LOCK TABLES `hotrokhachhang` WRITE;
/*!40000 ALTER TABLE `hotrokhachhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotrokhachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khohang`
--

DROP TABLE IF EXISTS `khohang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khohang` (
  `Kho_ID` int NOT NULL AUTO_INCREMENT,
  `TenKho` varchar(255) NOT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Kho_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khohang`
--

LOCK TABLES `khohang` WRITE;
/*!40000 ALTER TABLE `khohang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khohang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `NCC_ID` int NOT NULL AUTO_INCREMENT,
  `TenNCC` varchar(255) NOT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `SoDienThoai` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `ThongTinLienHe` varchar(255) DEFAULT NULL,
  `DieuKienThanhToan` text,
  `DiemDanhGia` float DEFAULT '0',
  `SoLanDanhGia` int DEFAULT '0',
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`NCC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quanlykho`
--

DROP TABLE IF EXISTS `quanlykho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quanlykho` (
  `QLK_ID` int NOT NULL AUTO_INCREMENT,
  `Kho_ID` int NOT NULL,
  `MaSanPham` varchar(50) NOT NULL,
  `TenSanPham` varchar(255) NOT NULL,
  `SoLuongTon` int NOT NULL DEFAULT '0',
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`QLK_ID`),
  UNIQUE KEY `Kho_ID` (`Kho_ID`,`MaSanPham`),
  CONSTRAINT `quanlykho_ibfk_1` FOREIGN KEY (`Kho_ID`) REFERENCES `khohang` (`Kho_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quanlykho`
--

LOCK TABLES `quanlykho` WRITE;
/*!40000 ALTER TABLE `quanlykho` DISABLE KEYS */;
/*!40000 ALTER TABLE `quanlykho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `TK_ID` int NOT NULL AUTO_INCREMENT,
  `TenDangNhap` varchar(50) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `HoTen` varchar(255) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `SoDienThoai` varchar(20) DEFAULT NULL,
  `VaiTro` varchar(50) NOT NULL DEFAULT 'nhanvien',
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`TK_ID`),
  UNIQUE KEY `TenDangNhap` (`TenDangNhap`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanhtoan`
--

DROP TABLE IF EXISTS `thanhtoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanhtoan` (
  `TT_ID` int NOT NULL AUTO_INCREMENT,
  `DH_ID` int NOT NULL,
  `LoaiThanhToan` varchar(50) NOT NULL,
  `SoTien` decimal(15,2) NOT NULL,
  `NgayThanhToan` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TrangThaiTT` varchar(50) DEFAULT 'Chưa thanh toán',
  `ThongTinTT` varchar(255) DEFAULT NULL,
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`TT_ID`),
  KEY `DH_ID` (`DH_ID`),
  CONSTRAINT `thanhtoan_ibfk_1` FOREIGN KEY (`DH_ID`) REFERENCES `donhang` (`DH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thanhtoan`
--

LOCK TABLES `thanhtoan` WRITE;
/*!40000 ALTER TABLE `thanhtoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `thanhtoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vanchuyen`
--

DROP TABLE IF EXISTS `vanchuyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vanchuyen` (
  `VC_ID` int NOT NULL AUTO_INCREMENT,
  `DH_ID` int NOT NULL,
  `DoiTacVC_ID` int DEFAULT NULL,
  `NgayLenLich` timestamp NULL DEFAULT NULL,
  `TrangThaiVC` varchar(50) DEFAULT 'Chờ xử lý',
  `ThongTinTheoDoi` varchar(255) DEFAULT NULL,
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`VC_ID`),
  KEY `DH_ID` (`DH_ID`),
  KEY `DoiTacVC_ID` (`DoiTacVC_ID`),
  CONSTRAINT `vanchuyen_ibfk_1` FOREIGN KEY (`DH_ID`) REFERENCES `donhang` (`DH_ID`),
  CONSTRAINT `vanchuyen_ibfk_2` FOREIGN KEY (`DoiTacVC_ID`) REFERENCES `doitacvanchuyen` (`DTVC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vanchuyen`
--

LOCK TABLES `vanchuyen` WRITE;
/*!40000 ALTER TABLE `vanchuyen` DISABLE KEYS */;
/*!40000 ALTER TABLE `vanchuyen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-11 12:47:25
