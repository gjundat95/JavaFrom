-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 05, 2016 at 03:43 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 7.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `QuanLySinhVien`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblSinhVien`
--

CREATE TABLE `tblSinhVien` (
  `MaSV` int(11) NOT NULL,
  `TenSV` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `DiaChi` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgaySinh` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `TenLop` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `tblSinhVien`
--

INSERT INTO `tblSinhVien` (`MaSV`, `TenSV`, `DiaChi`, `NgaySinh`, `TenLop`) VALUES
(1, 'qwq', 'wqwqw', 'qwq', 'wqw'),
(2, 'qwqqwq', 'wqfertt', 'qwqttt', 'wqwt'),
(3, 'qwqqwqgfjhgjhk', 'j', 'qwqtttjk', 'wqwthjkgjghj');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblSinhVien`
--
ALTER TABLE `tblSinhVien`
  ADD PRIMARY KEY (`MaSV`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
