-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 06, 2021 at 10:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yudityae_excent`
--

-- --------------------------------------------------------

--
-- Table structure for table `table_ajuan`
--

CREATE TABLE `table_ajuan` (
  `id_ajuan` int(11) NOT NULL,
  `id_ec` int(11) DEFAULT NULL COMMENT '"NULL" terjadi jika ekstrakurikuler terhapus',
  `id_user` int(11) DEFAULT NULL COMMENT 'Ini merupakan id_user pengaju (Siswa only);\r\n"NULL" terjadi jika user terhapus',
  `type_ajuan` varchar(25) NOT NULL,
  `status_ajuan` varchar(25) NOT NULL DEFAULT 'Pending',
  `isian_ajuan` varchar(255) DEFAULT NULL,
  `date_ajuan` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_ajuan`
--

INSERT INTO `table_ajuan` (`id_ajuan`, `id_ec`, `id_user`, `type_ajuan`, `status_ajuan`, `isian_ajuan`, `date_ajuan`) VALUES
(4, 1, 20, 'DAFTAR', 'Approved', 'Jawaban pendaftaran 1', '2021-03-25 05:50:29'),
(6, 13, 17, 'DAFTAR', 'Pending', 'Jawaban pendaftaran 3', '2021-03-25 14:32:41'),
(7, 8, 20, 'DAFTAR', 'Approved', 'Saya ingin daftar', '2021-03-26 00:32:23'),
(8, 8, 20, 'KELUAR', 'Approved', 'Saya ingin keluar', '2021-03-26 00:39:02'),
(9, 1, 21, 'DAFTAR', 'Approved', 'Daftar basket Daftar basket Daftar basket', '2021-03-28 20:07:01'),
(10, 1, 22, 'DAFTAR', 'Not Approved', 'Daftar basket Daftar basket Daftar basket', '2021-03-28 20:07:01'),
(12, 1, 20, 'KELUAR', 'Approved', 'Saya ada kesibukan lain', '2021-03-31 00:49:33'),
(13, 16, 22, 'DAFTAR', 'Pending', NULL, '2021-04-03 16:21:17'),
(15, 1, 21, 'KELUAR', 'Pending', '', '2021-04-03 16:26:18'),
(16, 1, 20, 'KELUAR', 'Pending', 'Saya ada kesibukan lain', '2021-04-04 09:45:09'),
(18, 77, 20, 'DAFTAR', 'Pending', 'Saya ingin mendalami rakit PC', '2021-04-04 09:47:33'),
(21, 14, 20, 'DAFTAR', 'Pending', 'ya', '2021-04-05 02:00:00'),
(22, 14, 20, 'KELUAR', 'Approved', 'Saya ada kesibukan lain', '2021-04-05 02:27:02');

-- --------------------------------------------------------

--
-- Table structure for table `table_ec`
--

CREATE TABLE `table_ec` (
  `id_ec` int(11) NOT NULL,
  `name_ec` varchar(50) NOT NULL,
  `desc_ec` text NOT NULL,
  `id_user` int(11) NOT NULL,
  `reg_question` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_ec`
--

INSERT INTO `table_ec` (`id_ec`, `name_ec`, `desc_ec`, `id_user`, `reg_question`) VALUES
(1, 'Basket', 'Permainan bola basket itu adalah salah satu dari permainan yang tadi yang menggunakan teknik pantul dan lempar, serta bola basket juga adalah permainan yang dapat ditentukan poinnya dengan cara dimasukan kedalam ring. Jadi bola basket itu adalah permainan yang unik, meskipun ada beberapa permainan yang hampir mirip dengan bola basket.', 5, 'Berapa tinggi (cm) dan berat badan (Kg) Anda?'),
(8, 'Kimia', 'Ini adalah ekstrakurikuler dalam rangka menyiapkan kandidat-kandidat dalam olimpiade Kimia di berbagai tingkat', 12, NULL),
(12, 'Gamelan', 'Ini merupakan deskripsi ekstrakurikuler gamelan', 12, 'Apakah Anda pernah bergabung ekstrakurikuler serupa saat di SMP? (Ya/Tidak)'),
(13, 'Musik', 'Ini merupakan deskripsi ekstrakurikuler musik.', 5, 'Apakah Anda pernah bergabung ekstrakurikuler serupa saat di SMP? (Ya/Tidak)'),
(14, 'PKS', 'Ini merupakan deskripsi dari ekstrakurikuler PKS', 18, 'Apakah Anda memiliki sensitifitas yang tinggi terhadap darah? (Ya/Tidak)'),
(16, 'Membaca Cepat', 'Ini merupakan deskripsi ekstrakurikuler membaca cepat SMK N 1 Sedayu.', 12, 'pertanyaan 1'),
(77, 'Rakit PC', 'Deskripsi merakit PC di sini', 5, '');

-- --------------------------------------------------------

--
-- Table structure for table `table_ev`
--

CREATE TABLE `table_ev` (
  `id_ev` int(11) NOT NULL,
  `name_ev` varchar(50) NOT NULL,
  `desc_ev` text NOT NULL,
  `date_ev` date NOT NULL,
  `time_ev` time NOT NULL,
  `string_loc_ev` varchar(255) NOT NULL,
  `latitude_loc_ev` decimal(10,8) DEFAULT NULL,
  `longitude_loc_ev` decimal(11,8) DEFAULT NULL,
  `id_ec` int(11) NOT NULL,
  `name_ec` varchar(50) NOT NULL,
  `pembina_ec` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_ev`
--

INSERT INTO `table_ev` (`id_ev`, `name_ev`, `desc_ev`, `date_ev`, `time_ev`, `string_loc_ev`, `latitude_loc_ev`, `longitude_loc_ev`, `id_ec`, `name_ec`, `pembina_ec`) VALUES
(1, 'Event 1', 'Event 1 Desc', '2021-04-01', '18:49:35', 'Jalan Muja Muju No 1 Yogyakarta', '-7.79706800', '110.37052900', 1, 'Basket', 'I Gede Yudhitya Suarbawa2');

-- --------------------------------------------------------

--
-- Table structure for table `table_join`
--

CREATE TABLE `table_join` (
  `id_join` int(11) NOT NULL,
  `id_user` int(11) NOT NULL COMMENT 'Null terjadi jika pengguna dihapus',
  `id_ec` int(11) NOT NULL COMMENT 'Null terjadi jika ekstrakurikuler dihapus',
  `date_join` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_join`
--

INSERT INTO `table_join` (`id_join`, `id_user`, `id_ec`, `date_join`) VALUES
(3, 21, 1, '2021-03-31 00:40:13');

-- --------------------------------------------------------

--
-- Table structure for table `table_media`
--

CREATE TABLE `table_media` (
  `id_media` int(11) NOT NULL,
  `id_ec` int(11) DEFAULT NULL,
  `id_ev` int(11) DEFAULT NULL,
  `media_type` varchar(10) NOT NULL,
  `media_path` varchar(255) DEFAULT NULL,
  `media_desc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_media`
--

INSERT INTO `table_media` (`id_media`, `id_ec`, `id_ev`, `media_type`, `media_path`, `media_desc`) VALUES
(1, 1, NULL, 'image', 'data-ec/img-ec/ec1_1.jpeg', 'desc img basket 1'),
(2, 1, NULL, 'image', 'data-ec/img-ec/ec1_2.jpeg', 'desc img basket 2'),
(5, 1, NULL, 'image', 'data-ec/img-ec/ec1_5.jpeg', 'desc img basket 3'),
(6, 1, NULL, 'image', 'data-ec/img-ec/ec1_6.jpeg', NULL),
(10, 8, NULL, 'image', 'data-ec/img-ec/ec8_10.jpeg', '');

-- --------------------------------------------------------

--
-- Table structure for table `table_pengguna`
--

CREATE TABLE `table_pengguna` (
  `id_user` int(11) NOT NULL,
  `email` varchar(300) NOT NULL,
  `password` text NOT NULL,
  `nama_lengkap` varchar(255) NOT NULL,
  `status` varchar(11) NOT NULL,
  `nip_nis` bigint(18) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `no_hp` varchar(25) NOT NULL,
  `img_user` varchar(255) DEFAULT 'data-pengguna/img-user/no_profile_picture.jpeg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_pengguna`
--

INSERT INTO `table_pengguna` (`id_user`, `email`, `password`, `nama_lengkap`, `status`, `nip_nis`, `kelas`, `gender`, `no_hp`, `img_user`) VALUES
(5, 'he.is_tya22@hotmail.com', 'thisispassword', 'I Gede Yudhitya Suarbawa2', 'Guru', 12520249004, 'PTI G', 'Laki-laki', '087739303330', 'data-pengguna/img-user/no_profile_picture.jpeg'),
(12, 'this_email277@gmail.com', 'thisispassword22277', 'Johnny277', 'Guru', 1987063020010809077, 'JPTK 4', 'Laki-laki', '+6285677345727', 'data-pengguna/img-user/12.jpeg'),
(15, 'swulunggani@gmail.com', 'passwordddd', 'I Made Dwiana Swulunggani', 'Guru', 762329844628466648, 'JPTK 6', 'Perempuan', '087239679891', 'data-pengguna/img-user/no_profile_picture.jpeg'),
(17, 'swulunggxani@gmail.com', 'passWWordddd7^&+', 'I Made Dwiana Swulunggani', 'Siswa', 12520249003, 'PTI G22', 'Perempuan', '087239679891', 'data-pengguna/img-user/no_profile_picture.jpeg'),
(18, 'swulunggani77@gmail.com', '%**567GYtytER==', 'Ni Wayan Suasti', 'Guru', 1976562419974565, 'VIII B', 'Perempuan', '+62(81) 239 479 363', 'data-pengguna/img-user/no_profile_picture.jpeg'),
(19, 'wyn_suasti@yahoo.co.id', '%**567GYtytER==', 'Ni Wayan Suasti', 'Guru', 1976562419974565, 'VIII B', 'Perempuan', '+62(81) 239 479 363', 'data-pengguna/img-user/no_profile_picture.jpeg'),
(20, 'tanjiro_kun@ufotable.com', 'tanjitanji', 'Kamado Tanjiro 897', 'Siswa', 16032021, 'Sun Breathing', 'Laki-laki', '+62(86) 567 456 543', '/data-pengguna/img-user/20.jpeg'),
(21, 'student111@gmail.com', 'student111', 'Student Satu Example', 'Siswa', 1111111111111, 'Kelas 1', 'Laki-laki', '+6286111222333', 'data-pengguna/img-user/21.jpeg'),
(22, 'student222@yahoo.com', 'student222', 'Student 2 Example', 'Siswa', 2222222222222, 'Kelas 2', 'Perempuan', '+6282222222222', 'data-pengguna/img-user/22.jpeg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `table_ajuan`
--
ALTER TABLE `table_ajuan`
  ADD PRIMARY KEY (`id_ajuan`),
  ADD KEY `id_ec` (`id_ec`,`id_user`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `table_ec`
--
ALTER TABLE `table_ec`
  ADD PRIMARY KEY (`id_ec`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `table_ev`
--
ALTER TABLE `table_ev`
  ADD PRIMARY KEY (`id_ev`),
  ADD KEY `id_ec` (`id_ec`);

--
-- Indexes for table `table_join`
--
ALTER TABLE `table_join`
  ADD PRIMARY KEY (`id_join`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_ec` (`id_ec`);

--
-- Indexes for table `table_media`
--
ALTER TABLE `table_media`
  ADD PRIMARY KEY (`id_media`),
  ADD KEY `id_ec` (`id_ec`);

--
-- Indexes for table `table_pengguna`
--
ALTER TABLE `table_pengguna`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `table_ajuan`
--
ALTER TABLE `table_ajuan`
  MODIFY `id_ajuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `table_ec`
--
ALTER TABLE `table_ec`
  MODIFY `id_ec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `table_ev`
--
ALTER TABLE `table_ev`
  MODIFY `id_ev` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `table_join`
--
ALTER TABLE `table_join`
  MODIFY `id_join` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `table_media`
--
ALTER TABLE `table_media`
  MODIFY `id_media` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `table_pengguna`
--
ALTER TABLE `table_pengguna`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `table_ajuan`
--
ALTER TABLE `table_ajuan`
  ADD CONSTRAINT `table_ajuan_ibfk_1` FOREIGN KEY (`id_ec`) REFERENCES `table_ec` (`id_ec`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `table_ajuan_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `table_pengguna` (`id_user`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `table_ec`
--
ALTER TABLE `table_ec`
  ADD CONSTRAINT `table_ec_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `table_pengguna` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `table_join`
--
ALTER TABLE `table_join`
  ADD CONSTRAINT `table_join_ibfk_1` FOREIGN KEY (`id_ec`) REFERENCES `table_ec` (`id_ec`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `table_join_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `table_pengguna` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `table_media`
--
ALTER TABLE `table_media`
  ADD CONSTRAINT `table_media_ibfk_1` FOREIGN KEY (`id_ec`) REFERENCES `table_ec` (`id_ec`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
