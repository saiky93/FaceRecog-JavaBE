-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2017 at 07:18 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `facerecognition`
--

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id` smallint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `state` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `name`, `state`, `country`) VALUES
(1, 'Hillside', 'New Jersey', 'USA'),
(2, 'springfield', 'New Jersey', 'USA'),
(3, 'Parsippany', 'New Jersey', 'USA'),
(4, 'Fairfield', 'Iowa', 'USA'),
(5, 'Morristown', 'New Jersey', 'USA'),
(6, 'Denvor', 'Colorado', 'USA'),
(7, 'Cairo', 'Big Cairo', 'Egypt'),
(8, 'Giza', 'Big Cairo', 'Egypt'),
(9, 'Union', 'New Jersey', 'USA'),
(10, 'Summit', 'New Jersey', 'USA'),
(11, 'New providence', 'New Jersey', 'USA'),
(12, 'Long branch', 'New Jersey', 'USA'),
(13, 'Nellore', 'Andhra Pradesh', 'India'),
(14, 'Kurnool', 'Andhra Pradesh', 'India'),
(15, 'New york city', 'New York', 'United States'),
(16, 'Tampa', 'Florida', 'United States');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `ein` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `website` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `zip_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `ein`, `name`, `phone`, `website`, `description`, `address_line1`, `address_line2`, `city`, `state`, `country`, `zip_code`) VALUES
(16, '1112226547', 'Macrosoft inc', '9874569658', 'www.macrosoftinc.com', 'Macrosoft has a powerful combination of business experience and technological expertise that provides clients best-in-class solutions in software development projects.\nMacrosoft delivers high-quality, cost-effective, full lifecycle solutions to complex software development projects.', '2 Sylvan Way 3rd Floor', '', 'Parsippany', 'New Jersey', 'United States', '07054');

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` mediumint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `name`) VALUES
(2, 'Egypt'),
(3, 'India'),
(1, 'United States');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `ssn` varchar(15) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `position` varchar(20) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `company_id` int(11) NOT NULL,
  `picture` varchar(150) DEFAULT NULL,
  `address_line1` varchar(50) DEFAULT NULL,
  `address_line2` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `zip_code` varchar(10) DEFAULT NULL,
  `taken_picture` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `ssn`, `first_name`, `last_name`, `position`, `phone`, `company_id`, `picture`, `address_line1`, `address_line2`, `city`, `state`, `country`, `zip_code`, `taken_picture`) VALUES
(55, '111111111', 'Hamada', 'Ibrahim', 'engineer', '9088385490', 16, '55.png', '608 Tillman St,', '', 'Hillside', 'New Jersey', 'United States', '07205', 1),
(56, '7777777777777', 'Abhishek', 'Shrivastava', 'Software Dev', '7043400692', 16, '56.png', '627 Knoll Rd', '', 'Hillside', 'New Jersey', 'United States', '07005', 1),
(57, '55335533', 'Saikrishna', 'Naripeddi', 'Software Dev', '3302853896', 16, '57.png', '2 Sylvan Way', '', 'Parsippany', 'New Jersey', 'United States', '07005', 1),
(58, '00000000', 'Tahir', 'Ali', 'Business Dev Mng', '9738890500-1232', 16, '58.png', '2 Sylvan Way', '', 'Parsippany', 'New Jersey', 'United States', '07054', 1),
(59, NULL, 'Aida', 'Nelson', NULL, '9738890500-1247', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(60, NULL, 'Albina', 'Asani', NULL, '9738890500-1268', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(61, NULL, 'Alpan', 'Atalay', NULL, '9738890500-1231', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(62, NULL, 'Alper', 'Ozocak', NULL, '9738890500-1223', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(63, NULL, 'Arif', 'Khan', NULL, '9738890500-1224', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(64, NULL, 'Baris', 'Tikiz', NULL, '9738890500-1251', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(65, NULL, 'Basit', 'Qari', NULL, '9738890500-1233', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(66, NULL, 'Buraq', 'Tansug', NULL, '9738890500-1235', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(67, NULL, 'Demirhan', 'Yenigun', NULL, '9738890500-1236', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(68, NULL, 'Ed', 'Sable', NULL, '9738890500-1254', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(69, NULL, 'G. N. ', 'Shah', NULL, '9738890500-1249', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(70, NULL, 'Imran', 'Salahuddin', NULL, '9738890500-1225', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(71, NULL, 'Jamil', 'Ahmad', NULL, '9738890500-1237', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(72, NULL, 'Jason', 'Singer', NULL, '9738890500-1263', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(73, NULL, 'Joe', 'Rafanelli', NULL, '9738890500-1253', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(74, NULL, 'John', 'Kullmann', NULL, '9738890500-1272', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(75, NULL, 'Ken', 'Link', NULL, '9738890500-1252', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(76, NULL, 'M. Salman', 'Shah', NULL, '9738890500-1227', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(77, NULL, 'Maryum', 'Farhan', NULL, '9738890500-1226', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(78, NULL, 'Rahat', 'Ali', NULL, '9738890500-1229', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(79, NULL, 'Ronald O', 'Mueller', NULL, '9738890500-1234', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(80, NULL, 'Tenzeel', 'Fayyaz', NULL, '9738890500-1228', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(81, NULL, 'Aamir', 'Jadoon', NULL, '9738890500-1248', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(82, NULL, 'Muzaffar Ali', 'Ismail', NULL, '9738890500-1244', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(83, NULL, 'Camilo', 'Brun', NULL, '9738890500-1272', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(84, NULL, 'Jonathan', 'Goldberg', NULL, '9738890500-1272', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(85, NULL, 'Joy', 'Gao', NULL, '9738890500-1272', 16, '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(86, '12345676', 'sariya', 'khan', 'Software Developer', '2345678901', 16, '', '304 harrison ave', '', 'Parsippany', 'New Jersey', 'United States', '07032', 1);

-- --------------------------------------------------------

--
-- Table structure for table `security_question`
--

CREATE TABLE `security_question` (
  `id` tinyint(20) NOT NULL,
  `question` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `security_question`
--

INSERT INTO `security_question` (`id`, `question`) VALUES
(9, 'In what town or city did you meet your spouse/partner?'),
(12, 'In what town or city did your mother and father meet?'),
(8, 'In what town or city was your first full time job?'),
(2, 'What is the last name of the teacher who gave you your first failing grade?'),
(1, 'What is your favorite song'),
(10, 'What is your grandmother\'s (on your mother\'s side) maiden name?'),
(7, 'What is your oldest cousin\'s first and last name?'),
(11, 'What is your spouse or partner\'s mother\'s maiden name?'),
(14, 'What time of the day was your first child born? (hh:mm)'),
(13, 'What time of the day were you born? (hh:mm)'),
(6, 'What was the name of your elementary / primary school?'),
(3, 'What was the name of your second cat?'),
(4, 'When you were young, what did you want to be when you grew up?'),
(5, 'Who was your childhood hero? ');

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE `state` (
  `id` mediumint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `country` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`id`, `name`, `country`) VALUES
(1, 'New Jersey', 'United States'),
(2, 'New York', 'United States'),
(3, 'Iowa', 'United States'),
(4, 'Andhra Pradesh', 'India'),
(5, 'Colorado', 'United States'),
(6, 'Florida', 'United States'),
(7, 'Big Cairo', 'Egypt');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `changed_password` tinyint(1) NOT NULL,
  `security_question1` varchar(250) DEFAULT NULL,
  `security_answer1` varchar(250) DEFAULT NULL,
  `security_question2` varchar(250) DEFAULT NULL,
  `security_answer2` varchar(250) DEFAULT NULL,
  `security_question3` varchar(250) DEFAULT NULL,
  `security_answer3` varchar(250) DEFAULT NULL,
  `user_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `company_id`, `employee_id`, `email`, `password`, `changed_password`, `security_question1`, `security_answer1`, `security_question2`, `security_answer2`, `security_question3`, `security_answer3`, `user_type`) VALUES
(52, 16, 0, 'CampusProgram@macrosoftinc.com', 'CampusProgram', 0, 'In what town or city was your first full time job?', 'Mansoura', 'What was the name of your elementary / primary school?', 'Naser', 'What is your oldest cousin\'s first and last name?', 'Ashraf gabr', 1),
(53, 0, 55, 'Hamada.G.Ibrahim@gmail.com', 'hggi2011', 1, 'In what town or city did your mother and father meet?', 'diarb', 'When you were young, what did you want to be when you grew up?', 'spain', 'What is your grandmother\'s (on your mother\'s side) maiden name?', 'mostafa', 2),
(54, 0, 56, 'ashriva4@icloud.com', 'abhi123', 1, 'In what town or city did you meet your spouse/partner?', 'parsippany', 'Who was your childhood hero?', 'spiderman', 'What is your favorite song', 'nothing else matters', 2),
(55, 0, 57, 'saikrishna.naripeddi@gmail.com', 'just2gitam', 1, 'What is your favorite song', 'Don\'t you worry child.', 'Who was your childhood hero?', 'Dad', 'In what town or city did your mother and father meet?', 'Ongole', 2),
(56, 0, 58, 'tali@macrosoftinc.com', 'Macrosoft incAli', 0, '', '', '', '', '', '', 2),
(57, 0, 59, 'asavona@macrosoftinc.com', 'Macrosoft incNelson', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(58, 0, 60, 'aasani@macrosoftinc.com', 'Macrosoft incAsani', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(59, 0, 61, 'aatalay@macrosoftinc.com', 'Macrosoft incAtalay', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(60, 0, 62, 'aozocak@macrosoftinc.com', 'Macrosoft incOzocak', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(61, 0, 63, 'karif@macrosoftinc.com', 'Macrosoft incKhan', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(62, 0, 64, 'btikiz@macrosoftinc.com', 'Macrosoft incTikiz', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(63, 0, 65, 'mbq@macrosoftinc.com', 'Macrosoft incQari', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(64, 0, 66, 'btansug@macrosoftinc.com', 'Macrosoft incTansug', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(65, 0, 67, 'dyenigun@macrosoftinc.com', 'Macrosoft incYenigun', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(66, 0, 68, 'esable@macrosoftinc.com', 'Macrosoft incSable', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(67, 0, 69, 'gnshah@macrosoftinc.com', 'Macrosoft incShah', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(68, 0, 70, 'isalahuddin@macrosoftinc.com', 'Macrosoft incSalahuddin', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(69, 0, 71, 'jahmad@macrosoftinc.com', 'Macrosoft incAhmad', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(70, 0, 72, 'jsinger@macrosoftinc.com', 'Macrosoft incSinger', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(71, 0, 73, 'jrafanelli@macrosoftinc.com', 'Macrosoft incRafanelli', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(72, 0, 74, 'jkullmann@macrosoftinc.com', 'Macrosoft incKullmann', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(73, 0, 75, 'klink@macrosoftinc.com', 'Macrosoft incLink', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(74, 0, 76, 'mshah@macrosoftinc.com', 'Macrosoft incShah', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(75, 0, 77, 'mfarhan@macrosoftinc.com', 'Macrosoft incFarhan', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(76, 0, 78, 'rali@macrosoftinc.com', 'Macrosoft incAli', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(77, 0, 79, 'rom@macrosoftinc.com', 'Macrosoft incMueller', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(78, 0, 80, 'tfayyaz@macrosoftinc.com', 'Macrosoft incFayyaz', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(79, 0, 81, 'aamir@macrosoftinc.com', 'Macrosoft incJadoon', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(80, 0, 82, 'mismail@macrosoftinc.com', 'Macrosoft incIsmail', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(81, 0, 83, 'cbrun@macrosoftinc.com', 'Macrosoft incBrun', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(82, 0, 84, 'jgoldberg@macrosoftinc.com', 'Macrosoft incGoldberg', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(83, 0, 85, 'jgao@macrosoftinc.com', 'Macrosoft incGao', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(84, 0, 86, 'Spathan@macrosoftinc.com', 'Macrosoft inckhan', 0, '', '', '', '', '', '', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `security_question`
--
ALTER TABLE `security_question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question` (`question`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`id`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` smallint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` mediumint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT for table `security_question`
--
ALTER TABLE `security_question`
  MODIFY `id` tinyint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `id` mediumint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
