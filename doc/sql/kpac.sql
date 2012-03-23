-- phpMyAdmin SQL Dump
-- version 3.3.2deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-03-2012 a las 09:24:31
-- Versión del servidor: 5.1.41
-- Versión de PHP: 5.3.2-1ubuntu4.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `kpac`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Game`
--

CREATE TABLE IF NOT EXISTS `Game` (
  `idGame` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `grantPublicAccess` tinyint(1) DEFAULT '1',
  `secretGame` varchar(150) DEFAULT NULL,
  `privateKey` text,
  PRIMARY KEY (`idGame`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=124 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameAccess`
--

CREATE TABLE IF NOT EXISTS `GameAccess` (
  `idGroup` int(11) NOT NULL,
  `idGame` int(11) NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'allow',
  `recursive` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`idGroup`,`idGame`),
  KEY `GameAccess_Group` (`idGroup`),
  KEY `GameAccess_Game` (`idGame`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameInstance`
--

CREATE TABLE IF NOT EXISTS `GameInstance` (
  `idGameInstance` int(11) NOT NULL AUTO_INCREMENT,
  `idGame` int(11) NOT NULL,
  `state` longtext,
  PRIMARY KEY (`idGameInstance`),
  KEY `GameInstance_Game` (`idGame`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameLike`
--

CREATE TABLE IF NOT EXISTS `GameLike` (
  `likeId` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `containerId` int(11) NOT NULL,
  PRIMARY KEY (`likeId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameScore`
--

CREATE TABLE IF NOT EXISTS `GameScore` (
  `idScore` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idGame` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`idScore`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Group`
--

CREATE TABLE IF NOT EXISTS `Group` (
  `idGroup` int(11) NOT NULL,
  `description` longtext,
  `idGroupParent` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGroup`),
  KEY `GROUP_GROUP` (`idGroupParent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Realm`
--

CREATE TABLE IF NOT EXISTS `Realm` (
  `idRealm` int(11) NOT NULL AUTO_INCREMENT,
  `alias` char(15) NOT NULL,
  PRIMARY KEY (`idRealm`),
  UNIQUE KEY `alias_UNIQUE` (`alias`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `session`
--

CREATE TABLE IF NOT EXISTS `session` (
  `idSession` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `startTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `campusSession` varchar(250) NOT NULL,
  PRIMARY KEY (`idSession`),
  KEY `Session_User` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Session`
--

CREATE TABLE IF NOT EXISTS `Session` (
  `idSession` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `startTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `campusSession` varchar(250) NOT NULL,
  PRIMARY KEY (`idSession`),
  KEY `Session_User` (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=104 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `secret` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=104 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserGameInstance`
--

CREATE TABLE IF NOT EXISTS `UserGameInstance` (
  `idUser` int(11) NOT NULL,
  `idGameInstance` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idGameInstance`),
  KEY `UserGameInstance_User` (`idUser`),
  KEY `UserGameInstance_GameInstance` (`idGameInstance`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserGroup`
--

CREATE TABLE IF NOT EXISTS `UserGroup` (
  `idUser` int(11) NOT NULL,
  `idGroup` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idGroup`),
  KEY `UserGroup_User` (`idUser`),
  KEY `UserGroup_Group` (`idGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserRealm`
--

CREATE TABLE IF NOT EXISTS `UserRealm` (
  `idUser` int(11) NOT NULL,
  `idRealm` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idRealm`),
  KEY `UserRealm_User` (`idUser`),
  KEY `UserRealm_Realm` (`idRealm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
