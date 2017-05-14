-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2017 at 04:57 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EmployeeNo` int(11) DEFAULT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `itemdetails`
--

CREATE TABLE `itemdetails` (
  `ITemNo` varchar(255) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `Information` varchar(255) DEFAULT NULL,
  `IPrate` varchar(255) DEFAULT NULL,
  `Kelvin` varchar(255) DEFAULT NULL,
  `Wattage` varchar(255) DEFAULT NULL,
  `ColorTemp` varchar(255) DEFAULT NULL,
  `BatchNo` varchar(255) DEFAULT NULL,
  `RowNo` varchar(255) DEFAULT NULL,
  `RackNo` varchar(255) DEFAULT NULL,
  `LocationNo` varchar(255) DEFAULT NULL,
  `ProductionDate` varchar(255) DEFAULT NULL,
  `Image` blob,
  `BeamAngle` varchar(255) DEFAULT NULL,
  `Lumens` varchar(255) DEFAULT NULL,
  `CRI` varchar(255) DEFAULT NULL,
  `Power` varchar(255) DEFAULT NULL,
  `Size` varchar(255) DEFAULT NULL,
  `AC` varchar(255) DEFAULT NULL,
  `DC` varchar(255) DEFAULT NULL,
  `MinQuantity` varchar(255) DEFAULT NULL,
  `Remarks` varchar(255) DEFAULT NULL,
  `Barcode` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
