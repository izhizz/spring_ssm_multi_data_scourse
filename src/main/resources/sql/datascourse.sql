/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.22-log : Database - data_source
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`data_source` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `data_source`;

/*Table structure for table `scourse` */

DROP TABLE IF EXISTS `scourse`;

CREATE TABLE `scourse` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dbKey` varchar(50) DEFAULT NULL COMMENT '标识',
  `driver` varchar(50) DEFAULT NULL COMMENT '驱动信息',
  `url` varchar(50) DEFAULT NULL COMMENT '数据库地址',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `scourse` */

insert  into `scourse`(`id`,`dbKey`,`driver`,`url`,`username`,`password`) values (3,'dataScourse2','com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/dataScourse2','root','123456'),(10,'dataScourse3','com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/dataScourse3','root','123456'),(11,'dataScourse1','com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/dataScourse1','root','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
