-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 23, 2020 at 08:37 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `latihan1`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_bayi`
--

CREATE TABLE `detail_bayi` (
  `id_bayi` varchar(4) NOT NULL,
  `berat_badan` int(5) NOT NULL,
  `tinggi_badan` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bayi`
--

CREATE TABLE `tb_bayi` (
  `id_bayi` varchar(4) NOT NULL,
  `nama_bayi` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `level` enum('1','2','3') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `unique_id`, `nama`, `email`, `encrypted_password`, `salt`, `no_telp`, `level`) VALUES
(1, '5f0ffb4f429309.88523551', 'anty', 'anty@gmail.com', 'wbCfjRdGJMjrvl+xmWjYCkR6ZngyMTkxNzEzM2Zm', '21917133ff', '082245655379', '1'),
(3, '5f13e1a8c050a9.13017690', 'aaa', 'bbb', 'XwcbeefPyHOEDd/c/PB6xvvZYwQ3MzYxMzBkNDY2', '736130d466', '0812345678901', '3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_bayi`
--
ALTER TABLE `detail_bayi`
  ADD KEY `id_bayi` (`id_bayi`);

--
-- Indexes for table `tb_bayi`
--
ALTER TABLE `tb_bayi`
  ADD PRIMARY KEY (`id_bayi`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_bayi`
--
ALTER TABLE `detail_bayi`
  ADD CONSTRAINT `detail_bayi_ibfk_1` FOREIGN KEY (`id_bayi`) REFERENCES `tb_bayi` (`id_bayi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_bayi`
--
ALTER TABLE `tb_bayi`
  ADD CONSTRAINT `tb_bayi_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
