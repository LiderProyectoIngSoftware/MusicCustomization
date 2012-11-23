-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 22-11-2012 a las 18:52:56
-- Versión del servidor: 5.5.24
-- Versión de PHP: 5.3.10-1ubuntu3.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `appmus`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cancion`
--

CREATE TABLE IF NOT EXISTS `Cancion` (
  `IdCancion` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Artista` varchar(45) DEFAULT NULL,
  `Pais` varchar(45) DEFAULT NULL,
  `Ano` int(11) DEFAULT NULL,
  `Ruta` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdCancion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `Cancion`
--

INSERT INTO `Cancion` (`IdCancion`, `Nombre`, `Artista`, `Pais`, `Ano`, `Ruta`) VALUES
(1, 'Nothing else matters', 'Metallica', 'Estados Unidos', 1992, NULL),
(2, 'Samba de Janeiro', 'Bellini', 'Africa', 1982, NULL),
(3, 'Master of Puppets', 'Metallica', 'Estados Unidos', 1992, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GeneroCancion`
--

CREATE TABLE IF NOT EXISTS `GeneroCancion` (
  `IdCancion` int(11) NOT NULL,
  `Rock` double DEFAULT '0',
  `Pop` double DEFAULT '0',
  `Reggae` double DEFAULT '0',
  `Reggaeton` double DEFAULT '0',
  `Merengue` double DEFAULT '0',
  `Salsa` double DEFAULT '0',
  `Cumbia` double DEFAULT '0',
  `Clasica` double DEFAULT '0',
  `Jazz` double DEFAULT '0',
  `Metal` double DEFAULT '0',
  `Samba` double DEFAULT '0',
  `Disco` double DEFAULT '0',
  PRIMARY KEY (`IdCancion`),
  KEY `fk_GeneroCancion_1_idx` (`IdCancion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `GeneroCancion`
--

INSERT INTO `GeneroCancion` (`IdCancion`, `Rock`, `Pop`, `Reggae`, `Reggaeton`, `Merengue`, `Salsa`, `Cumbia`, `Clasica`, `Jazz`, `Metal`, `Samba`, `Disco`) VALUES
(1, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 0.8, 0, 0),
(2, 0, 0, 0.2, 0, 0.3, 0.2, 0.2, 0, 0, 0, 1, 0),
(3, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 0.8, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GeneroUsuario`
--

CREATE TABLE IF NOT EXISTS `GeneroUsuario` (
  `IdUsuario` int(11) NOT NULL,
  `Rock` double DEFAULT NULL,
  `Pop` double DEFAULT NULL,
  `Reggae` double DEFAULT NULL,
  `Reggaeton` double DEFAULT NULL,
  `Merengue` double DEFAULT NULL,
  `Salsa` double DEFAULT NULL,
  `Cumbia` double DEFAULT NULL,
  `Clasica` double DEFAULT NULL,
  `Jazz` double DEFAULT NULL,
  `Metal` double DEFAULT NULL,
  `Samba` double DEFAULT NULL,
  `Disco` double DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`),
  KEY `fk_GeneroUsuario_1_idx` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `GeneroUsuario`
--

INSERT INTO `GeneroUsuario` (`IdUsuario`, `Rock`, `Pop`, `Reggae`, `Reggaeton`, `Merengue`, `Salsa`, `Cumbia`, `Clasica`, `Jazz`, `Metal`, `Samba`, `Disco`) VALUES
(1, 0.45714285714285713, 0, 0.0857142857142857, 0, 0.12857142857142856, 0.0857142857142857, 0.0857142857142857, 0, 0, 0.45714285714285713, 0.42857142857142855, 0),
(4, 0.6000000000000001, 0, 0.05, 0, 0.075, 0.05, 0.05, 0, 0, 0.6000000000000001, 0.25, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Historia`
--

CREATE TABLE IF NOT EXISTS `Historia` (
  `IdHistoria` int(11) NOT NULL AUTO_INCREMENT,
  `IdUsuario` int(11) DEFAULT NULL,
  `IdCancion` int(11) DEFAULT NULL,
  `Fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`IdHistoria`),
  KEY `fk_Historia_1_idx` (`IdUsuario`),
  KEY `fk_Historia_1_idx1` (`IdCancion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=31 ;

--
-- Volcado de datos para la tabla `Historia`
--

INSERT INTO `Historia` (`IdHistoria`, `IdUsuario`, `IdCancion`, `Fecha`) VALUES
(1, 1, 1, '0000-00-00 00:00:00'),
(2, 1, 2, '0000-00-00 00:00:00'),
(3, 1, 3, '0000-00-00 00:00:00'),
(4, 1, 2, '2012-11-22 18:32:08'),
(5, 1, 2, '2012-11-22 18:32:45'),
(6, 1, 2, '2012-11-22 18:33:07'),
(7, 1, 1, '2012-11-22 18:33:12'),
(8, 1, 3, '2012-11-22 18:33:19'),
(9, 1, 3, '2012-11-22 18:33:26'),
(10, 1, 2, '2012-11-22 18:37:47'),
(11, 1, 2, '2012-11-22 18:37:52'),
(12, 1, 1, '2012-11-22 18:37:57'),
(13, 1, 1, '2012-11-22 18:38:02'),
(14, 1, 3, '2012-11-22 18:38:55'),
(15, 1, 3, '2012-11-22 18:39:06'),
(16, 1, 3, '2012-11-22 18:39:08'),
(17, 1, 3, '2012-11-22 18:39:08'),
(18, 1, 2, '2012-11-22 18:39:11'),
(19, 1, 2, '2012-11-22 18:39:16'),
(20, 1, 1, '2012-11-22 18:39:47'),
(21, 1, 2, '2012-11-22 18:39:53'),
(27, 4, 2, '2012-11-22 18:51:14'),
(28, 4, 1, '2012-11-22 18:51:20'),
(29, 4, 3, '2012-11-22 18:51:27'),
(30, 4, 1, '2012-11-22 18:51:33');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE IF NOT EXISTS `Usuario` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Usuario` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`IdUsuario`, `Nombre`, `Usuario`, `Password`) VALUES
(1, 'Alberto', 'alberto', 'alberto'),
(4, 'nuevo', 'nuevo', 'nuevo');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `GeneroCancion`
--
ALTER TABLE `GeneroCancion`
  ADD CONSTRAINT `fk_GeneroCancion_Cancion` FOREIGN KEY (`IdCancion`) REFERENCES `Cancion` (`IdCancion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `GeneroUsuario`
--
ALTER TABLE `GeneroUsuario`
  ADD CONSTRAINT `fk_GeneroUsuario_Usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `Usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Historia`
--
ALTER TABLE `Historia`
  ADD CONSTRAINT `fk_Historia_Cancion` FOREIGN KEY (`IdCancion`) REFERENCES `Cancion` (`IdCancion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Historia_Usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `Usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
