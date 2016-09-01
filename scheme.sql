-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.10-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table tennisfolks.challenge_match
DROP TABLE IF EXISTS `challenge_match`;
CREATE TABLE IF NOT EXISTS `challenge_match` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstPlayer` int(10) unsigned NOT NULL,
  `secondPlayer` int(10) unsigned NOT NULL,
  `firstPlayerScore` int(10) unsigned DEFAULT NULL,
  `secondPlayerScore` int(10) unsigned DEFAULT NULL,
  `detailedScore` text,
  `matchReview` text,
  `surfaceType` varchar(50) DEFAULT NULL,
  `accepted` bit(1) NOT NULL DEFAULT b'0',
  `rejected` bit(1) NOT NULL DEFAULT b'0',
  `finished` bit(1) NOT NULL DEFAULT b'0',
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  `description` text,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_challengematch_user` (`firstPlayer`),
  KEY `FK_challengematch_user_2` (`secondPlayer`),
  KEY `FK_challenge_match_location` (`location`),
  CONSTRAINT `FK_challenge_match_location` FOREIGN KEY (`location`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_challengematch_user` FOREIGN KEY (`firstPlayer`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_challengematch_user_2` FOREIGN KEY (`secondPlayer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.chapter
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE IF NOT EXISTS `chapter` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `language` varchar(50) NOT NULL DEFAULT 'en',
  `url` varchar(250) NOT NULL,
  `name` text NOT NULL,
  `title` text,
  `keywords` text,
  `description` text,
  `published` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `creationDate` datetime DEFAULT NULL,
  `lastModified` datetime DEFAULT NULL,
  `creator` int(10) unsigned NOT NULL,
  `editor` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `language` (`language`,`url`),
  KEY `FK_chapter_user` (`creator`),
  KEY `FK_chapter_user_2` (`editor`),
  CONSTRAINT `FK_chapter_language` FOREIGN KEY (`language`) REFERENCES `language` (`code`),
  CONSTRAINT `FK_chapter_user` FOREIGN KEY (`creator`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_chapter_user_2` FOREIGN KEY (`editor`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.chapter_page
DROP TABLE IF EXISTS `chapter_page`;
CREATE TABLE IF NOT EXISTS `chapter_page` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `language` varchar(50) NOT NULL DEFAULT 'en',
  `url` varchar(250) NOT NULL,
  `header` text NOT NULL,
  `title` text,
  `keywords` text,
  `description` text,
  `text` text,
  `published` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `creationDate` datetime DEFAULT NULL,
  `lastModified` datetime DEFAULT NULL,
  `creator` int(10) unsigned NOT NULL,
  `editor` int(10) unsigned NOT NULL,
  `chapter` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `language` (`language`,`url`),
  KEY `FK_ChapterPage_user` (`creator`),
  KEY `FK_ChapterPage_user_2` (`editor`),
  KEY `FK_ChapterPage_chapter` (`chapter`),
  CONSTRAINT `FK_ChapterPage_chapter` FOREIGN KEY (`chapter`) REFERENCES `chapter` (`id`),
  CONSTRAINT `FK_ChapterPage_language` FOREIGN KEY (`language`) REFERENCES `language` (`code`),
  CONSTRAINT `FK_ChapterPage_user` FOREIGN KEY (`creator`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ChapterPage_user_2` FOREIGN KEY (`editor`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.geonames_admin
DROP TABLE IF EXISTS `geonames_admin`;
CREATE TABLE IF NOT EXISTS `geonames_admin` (
  `id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `adminCode` varchar(17) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `asciiname` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `geonameId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`geonameId`),
  UNIQUE KEY `id` (`id`),
  KEY `state` (`adminCode`),
  KEY `country` (`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.geonames_admin_alternate
DROP TABLE IF EXISTS `geonames_admin_alternate`;
CREATE TABLE IF NOT EXISTS `geonames_admin_alternate` (
  `alternateId` int(10) unsigned NOT NULL,
  `geonameId` int(10) unsigned DEFAULT NULL,
  `language` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`alternateId`),
  KEY `geonameId` (`geonameId`),
  KEY `language` (`language`),
  KEY `name` (`name`),
  CONSTRAINT `FK_geonames_admin_alternate_geonames_admin_code` FOREIGN KEY (`geonameId`) REFERENCES `geonames_admin` (`geonameId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.geonames_city
DROP TABLE IF EXISTS `geonames_city`;
CREATE TABLE IF NOT EXISTS `geonames_city` (
  `geonameId` int(10) unsigned NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `asciiname` varchar(250) DEFAULT NULL,
  `latitude` float(10,6) DEFAULT NULL,
  `longitude` float(10,6) DEFAULT NULL,
  `countryCode` varchar(2) DEFAULT NULL,
  `adminCode` varchar(17) DEFAULT NULL,
  `population` int(10) unsigned DEFAULT '0',
  `timezone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`geonameId`),
  KEY `countryCode` (`countryCode`),
  KEY `adminCode` (`adminCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.geonames_city_alternate
DROP TABLE IF EXISTS `geonames_city_alternate`;
CREATE TABLE IF NOT EXISTS `geonames_city_alternate` (
  `alternateId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `geonameId` int(10) unsigned NOT NULL,
  `language` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`alternateId`),
  KEY `geonameId` (`geonameId`),
  KEY `language` (`language`),
  KEY `name` (`name`),
  CONSTRAINT `FK_geonames_alternate_geonames_city` FOREIGN KEY (`geonameId`) REFERENCES `geonames_city` (`geonameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.image
DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` mediumblob NOT NULL,
  `mimetype` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `userId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.language
DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `code` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.league
DROP TABLE IF EXISTS `league`;
CREATE TABLE IF NOT EXISTS `league` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `description` text,
  `logoId` int(10) unsigned DEFAULT NULL,
  `coverPhotoId` int(10) unsigned DEFAULT NULL,
  `country` varchar(2) NOT NULL,
  `cityId` int(10) unsigned DEFAULT NULL,
  `stateId` int(10) unsigned DEFAULT NULL,
  `open` bit(1) NOT NULL DEFAULT b'1',
  `membershipFee` float(10,2) unsigned NOT NULL DEFAULT '0.00',
  `balance` float(10,2) unsigned NOT NULL DEFAULT '0.00',
  `creationDate` datetime DEFAULT NULL,
  `creator` int(10) unsigned NOT NULL,
  `manager` int(10) unsigned NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_league_user` (`creator`),
  KEY `FK_league_user_2` (`manager`),
  KEY `geonameId` (`cityId`),
  KEY `name` (`name`),
  KEY `country` (`country`),
  KEY `stateId` (`stateId`),
  CONSTRAINT `FK_league_geonames_admin` FOREIGN KEY (`stateId`) REFERENCES `geonames_admin` (`geonameId`),
  CONSTRAINT `FK_league_geonames_city` FOREIGN KEY (`cityId`) REFERENCES `geonames_city` (`geonameId`),
  CONSTRAINT `FK_league_user` FOREIGN KEY (`creator`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_league_user_2` FOREIGN KEY (`manager`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.league_blog_entry
DROP TABLE IF EXISTS `league_blog_entry`;
CREATE TABLE IF NOT EXISTS `league_blog_entry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `header` text,
  `text` text,
  `league` int(10) unsigned NOT NULL,
  `author` int(10) unsigned NOT NULL,
  `published` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_LeagueBlogEntry_league` (`league`),
  CONSTRAINT `FK_LeagueBlogEntry_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.league_blog_entry_comment
DROP TABLE IF EXISTS `league_blog_entry_comment`;
CREATE TABLE IF NOT EXISTS `league_blog_entry_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `text` text NOT NULL,
  `author` int(10) unsigned NOT NULL,
  `entry` int(10) unsigned NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_LeagueBlogEntryComment_user` (`author`),
  KEY `FK_LeagueBlogEntryComment_leagueblogentry` (`entry`),
  CONSTRAINT `FK_LeagueBlogEntryComment_leagueblogentry` FOREIGN KEY (`entry`) REFERENCES `league_blog_entry` (`id`),
  CONSTRAINT `FK_LeagueBlogEntryComment_user` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.league_localization
DROP TABLE IF EXISTS `league_localization`;
CREATE TABLE IF NOT EXISTS `league_localization` (
  `id` varchar(90) NOT NULL,
  `league` int(10) unsigned NOT NULL,
  `language` varchar(50) NOT NULL DEFAULT 'en',
  `name` text NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `FK_league_localization_league` (`league`),
  KEY `FK_league_localization_language` (`language`),
  CONSTRAINT `FK_league_localization_language` FOREIGN KEY (`language`) REFERENCES `language` (`code`),
  CONSTRAINT `FK_league_localization_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.league_member
DROP TABLE IF EXISTS `league_member`;
CREATE TABLE IF NOT EXISTS `league_member` (
  `id` varchar(50) NOT NULL,
  `league` int(10) unsigned NOT NULL,
  `user` int(10) unsigned NOT NULL,
  `accepted` bit(1) NOT NULL DEFAULT b'0',
  `rejected` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `paymentDate` datetime DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `tournamentPoints` int(10) unsigned NOT NULL DEFAULT '0',
  `challengePoints` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_LeagueMember_league` (`league`),
  KEY `FK_LeagueMember_user` (`user`),
  CONSTRAINT `FK_LeagueMember_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`),
  CONSTRAINT `FK_LeagueMember_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.location
DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(2) NOT NULL,
  `state` varchar(17) DEFAULT '',
  `city` varchar(250) NOT NULL,
  `stateId` int(10) unsigned DEFAULT NULL,
  `cityId` int(10) unsigned DEFAULT NULL,
  `address` varchar(550) NOT NULL,
  `fullAddress` varchar(1000) NOT NULL,
  `formattedAddress` varchar(1000) NOT NULL,
  `latitude` float(10,6) NOT NULL,
  `longitude` float(10,6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `country` (`country`),
  KEY `state` (`state`),
  KEY `cityId` (`cityId`),
  KEY `stateId` (`stateId`),
  KEY `latitude` (`latitude`),
  KEY `longitude` (`longitude`),
  KEY `fullAddress` (`fullAddress`),
  KEY `formattedAddress` (`formattedAddress`),
  CONSTRAINT `FK_location_geonames_admin` FOREIGN KEY (`stateId`) REFERENCES `geonames_admin` (`geonameId`),
  CONSTRAINT `FK_location_geonames_city` FOREIGN KEY (`cityId`) REFERENCES `geonames_city` (`geonameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.log_entry
DROP TABLE IF EXISTS `log_entry`;
CREATE TABLE IF NOT EXISTS `log_entry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activityDate` datetime DEFAULT NULL,
  `user` int(10) unsigned NOT NULL,
  `notes` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__user` (`user`),
  CONSTRAINT `FK__user` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.message
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `body` text NOT NULL,
  `sender` int(10) unsigned NOT NULL,
  `recipient` int(10) unsigned NOT NULL,
  `dialogueId` varchar(50) NOT NULL,
  `read` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_Message_user` (`sender`),
  KEY `FK_Message_user_2` (`recipient`),
  CONSTRAINT `FK_Message_user` FOREIGN KEY (`sender`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Message_user_2` FOREIGN KEY (`recipient`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.open_challenge
DROP TABLE IF EXISTS `open_challenge`;
CREATE TABLE IF NOT EXISTS `open_challenge` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `matchId` int(10) unsigned NOT NULL,
  `firstPlayer` int(10) unsigned NOT NULL,
  `accepted` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `startDate` datetime DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  `description` text,
  `surfaceType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_open_challenge_user` (`firstPlayer`),
  KEY `FK_open_challenge_location` (`location`),
  CONSTRAINT `FK_open_challenge_location` FOREIGN KEY (`location`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_open_challenge_user` FOREIGN KEY (`firstPlayer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.tournament
DROP TABLE IF EXISTS `tournament`;
CREATE TABLE IF NOT EXISTS `tournament` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `description` text,
  `targetLevel` int(10) unsigned NOT NULL DEFAULT '3',
  `levelShift` int(10) unsigned NOT NULL DEFAULT '1',
  `numberOfPlayers` int(10) unsigned NOT NULL DEFAULT '16',
  `league` int(10) unsigned NOT NULL DEFAULT '0',
  `supervisor` int(10) unsigned NOT NULL DEFAULT '0',
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `jsonData` mediumtext,
  `finished` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_tournament_league` (`league`),
  KEY `FK_tournament_user` (`supervisor`),
  CONSTRAINT `FK_tournament_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`),
  CONSTRAINT `FK_tournament_user` FOREIGN KEY (`supervisor`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.tournament_match
DROP TABLE IF EXISTS `tournament_match`;
CREATE TABLE IF NOT EXISTS `tournament_match` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tournament` int(10) unsigned NOT NULL,
  `firstPlayer` int(10) unsigned NOT NULL,
  `secondPlayer` int(10) unsigned NOT NULL,
  `firstPlayerScore` int(10) unsigned DEFAULT '0',
  `secondPlayerScore` int(10) unsigned DEFAULT '0',
  `detailedScore` text,
  `matchReview` text,
  `stage` int(10) unsigned DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `finished` bit(1) NOT NULL DEFAULT b'0',
  `approved` bit(1) NOT NULL DEFAULT b'0',
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `surfaceType` varchar(50) DEFAULT NULL,
  `description` text,
  `location` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tournament_match_tournament` (`tournament`),
  KEY `FK_tournament_match_user` (`firstPlayer`),
  KEY `FK_tournament_match_user_2` (`secondPlayer`),
  KEY `location` (`location`),
  CONSTRAINT `FK_tournament_match_location` FOREIGN KEY (`location`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_tournament_match_tournament` FOREIGN KEY (`tournament`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_tournament_match_user` FOREIGN KEY (`firstPlayer`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_tournament_match_user_2` FOREIGN KEY (`secondPlayer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.tournament_match_photo
DROP TABLE IF EXISTS `tournament_match_photo`;
CREATE TABLE IF NOT EXISTS `tournament_match_photo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `match` int(10) unsigned NOT NULL,
  `tournament` int(10) unsigned NOT NULL,
  `league` int(10) unsigned NOT NULL,
  `author` int(10) unsigned NOT NULL,
  `url` text NOT NULL,
  `description` text NOT NULL,
  `uploadDate` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_tournament_match_photo_tournament_match` (`match`),
  KEY `FK_tournament_match_photo_tournament` (`tournament`),
  KEY `FK_tournament_match_photo_league` (`league`),
  KEY `FK_tournament_match_photo_user` (`author`),
  CONSTRAINT `FK_tournament_match_photo_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`),
  CONSTRAINT `FK_tournament_match_photo_tournament` FOREIGN KEY (`tournament`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_tournament_match_photo_tournament_match` FOREIGN KEY (`match`) REFERENCES `tournament_match` (`id`),
  CONSTRAINT `FK_tournament_match_photo_user` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.tournament_participant
DROP TABLE IF EXISTS `tournament_participant`;
CREATE TABLE IF NOT EXISTS `tournament_participant` (
  `id` varchar(30) NOT NULL,
  `tournament` int(10) unsigned NOT NULL,
  `user` int(10) unsigned NOT NULL,
  `confirmed` bit(1) NOT NULL DEFAULT b'0',
  `top` int(10) unsigned NOT NULL DEFAULT '0',
  `registrationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tournament_participant_tournament` (`tournament`),
  KEY `FK_tournament_participant_user` (`user`),
  CONSTRAINT `FK_tournament_participant_tournament` FOREIGN KEY (`tournament`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_tournament_participant_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) DEFAULT NULL,
  `firstName` varchar(250) NOT NULL,
  `lastName` varchar(250) NOT NULL,
  `fullName` varchar(500) NOT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `country` varchar(2) DEFAULT NULL,
  `state` varchar(17) DEFAULT NULL,
  `city` varchar(500) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `latitude` float(10,6) DEFAULT NULL,
  `longitude` float(10,6) DEFAULT NULL,
  `cityId` int(10) unsigned DEFAULT NULL,
  `stateId` int(10) unsigned DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `levelShift` int(10) DEFAULT NULL,
  `description` text,
  `photoId` int(10) unsigned DEFAULT NULL,
  `lastActivityDate` datetime DEFAULT NULL,
  `registrationDate` datetime DEFAULT NULL,
  `language` varchar(50) DEFAULT 'en',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_user_language` (`language`),
  KEY `FK_user_location` (`location`),
  KEY `geonameId` (`cityId`),
  KEY `password` (`password`),
  KEY `firstName` (`firstName`),
  KEY `lastName` (`lastName`),
  KEY `state` (`state`),
  KEY `country` (`country`),
  KEY `stateId` (`stateId`),
  KEY `fullName` (`fullName`),
  CONSTRAINT `FK_user_geonames_admin` FOREIGN KEY (`stateId`) REFERENCES `geonames_admin` (`geonameId`),
  CONSTRAINT `FK_user_geonames_city` FOREIGN KEY (`cityId`) REFERENCES `geonames_city` (`geonameId`),
  CONSTRAINT `FK_user_language` FOREIGN KEY (`language`) REFERENCES `language` (`code`),
  CONSTRAINT `FK_user_location` FOREIGN KEY (`location`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.user_blog_entry
DROP TABLE IF EXISTS `user_blog_entry`;
CREATE TABLE IF NOT EXISTS `user_blog_entry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `author` int(10) unsigned NOT NULL,
  `user` int(10) unsigned NOT NULL,
  `published` bit(1) NOT NULL DEFAULT b'1',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `creationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_blog_entry_user` (`author`),
  KEY `user` (`user`),
  CONSTRAINT `FK_user_blog_entry_user` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_user_blog_entry_user_2` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.user_blog_entry_comment
DROP TABLE IF EXISTS `user_blog_entry_comment`;
CREATE TABLE IF NOT EXISTS `user_blog_entry_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `author` int(10) unsigned NOT NULL,
  `entry` int(10) unsigned NOT NULL,
  `text` text NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_user_blog_entry_comment_user` (`author`),
  KEY `FK_user_blog_entry_comment_user_blog_entry` (`entry`),
  CONSTRAINT `FK_user_blog_entry_comment_user` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_user_blog_entry_comment_user_blog_entry` FOREIGN KEY (`entry`) REFERENCES `user_blog_entry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.user_checkin
DROP TABLE IF EXISTS `user_checkin`;
CREATE TABLE IF NOT EXISTS `user_checkin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.user_follower
DROP TABLE IF EXISTS `user_follower`;
CREATE TABLE IF NOT EXISTS `user_follower` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `follower` int(10) unsigned NOT NULL,
  `following` int(10) unsigned NOT NULL,
  `subscriptionDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_friend_user` (`follower`),
  KEY `FK_user_friend_user_2` (`following`),
  KEY `subscriptionDate` (`subscriptionDate`),
  CONSTRAINT `user_follower_ibfk_1` FOREIGN KEY (`follower`) REFERENCES `user` (`id`),
  CONSTRAINT `user_follower_ibfk_2` FOREIGN KEY (`following`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.user_friend
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE IF NOT EXISTS `user_friend` (
  `id` varchar(50) NOT NULL,
  `firstUser` int(10) unsigned NOT NULL,
  `secondUser` int(10) unsigned NOT NULL,
  `approved` bit(1) NOT NULL DEFAULT b'0',
  `firstSubscribed` bit(1) NOT NULL DEFAULT b'0',
  `secondSubscribed` bit(1) NOT NULL DEFAULT b'1',
  `requestDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_friend_user` (`firstUser`),
  KEY `FK_user_friend_user_2` (`secondUser`),
  CONSTRAINT `FK_user_friend_user` FOREIGN KEY (`firstUser`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_user_friend_user_2` FOREIGN KEY (`secondUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table tennisfolks.user_milestone
DROP TABLE IF EXISTS `user_milestone`;
CREATE TABLE IF NOT EXISTS `user_milestone` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
