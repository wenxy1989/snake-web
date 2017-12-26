/*
SQLyog Ultimate v8.32 
MySQL - 5.5.16 : Database - snake_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `sys_function` */

DROP TABLE IF EXISTS `sys_function`;

CREATE TABLE `sys_function` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL DEFAULT '0',
  `name_` varchar(50) DEFAULT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `url_` varchar(100) DEFAULT NULL,
  `level_` int(11) DEFAULT NULL,
  `order_` int(11) NOT NULL DEFAULT '0',
  `created_time` varchar(19) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `sys_function` */

LOCK TABLES `sys_function` WRITE;

insert  into `sys_function`(`id_`,`parent_id`,`name_`,`code_`,`type_`,`url_`,`level_`,`order_`,`created_time`,`creator_id`) values (1,0,'系统管理','system',1,'',NULL,9,'2016-03-25 19:17:54',NULL),(2,1,'菜单管理','menu',1,'/function/page',1,9,'2016-03-25 18:44:49',1),(3,1,'用户管理','user',1,'/user/page',NULL,0,'2016-03-25 19:15:11',NULL),(4,1,'角色管理','role',1,'/role/page',NULL,0,'2016-03-25 19:14:53',NULL),(23,1,'系统参数','sys_parameter',1,'/sys/parameter/page',NULL,4,'2016-11-02 18:42:56',NULL),(24,1,'首页设置','sys_index',1,'/sys/index/page',NULL,10,'2016-11-03 10:50:41',NULL);

UNLOCK TABLES;

/*Table structure for table `sys_index` */

DROP TABLE IF EXISTS `sys_index`;

CREATE TABLE `sys_index` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(50) DEFAULT NULL,
  `url_` varchar(100) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `created_time` varchar(19) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_index` */

LOCK TABLES `sys_index` WRITE;

insert  into `sys_index`(`id_`,`name_`,`url_`,`type_`,`object_id`,`created_time`,`creator_id`) values (1,'菜单管理','/function/page',1,1,'2016-03-25 19:13:46',1);

UNLOCK TABLES;

/*Table structure for table `sys_parameter` */

DROP TABLE IF EXISTS `sys_parameter`;

CREATE TABLE `sys_parameter` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(50) DEFAULT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `int_value` int(11) DEFAULT NULL,
  `string_value` varchar(100) DEFAULT NULL,
  `double_value` decimal(10,0) DEFAULT NULL,
  `long_value` bigint(20) DEFAULT NULL,
  `char_value` char(1) DEFAULT NULL,
  `boolean_value` tinyint(1) DEFAULT NULL,
  `remark_` varchar(300) DEFAULT NULL,
  `created_time` varchar(19) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_parameter` */

LOCK TABLES `sys_parameter` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(50) DEFAULT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `status_` char(1) DEFAULT NULL,
  `remark_` varchar(300) DEFAULT NULL,
  `created_time` varchar(19) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

LOCK TABLES `sys_role` WRITE;

insert  into `sys_role`(`id_`,`name_`,`code_`,`status_`,`remark_`,`created_time`,`creator_id`) values (1,'admin','admin','1','超级管理员，应该把此角色写入代码中，作为静态变量，并且可以不需要设置访问任何的接口','2016-03-25 18:40:38',1),(3,'超级管理员','super_admin','1','超级管理员，拥有本应用大部分权限','2016-12-08 15:49:28',1);

UNLOCK TABLES;

/*Table structure for table `sys_role_function` */

DROP TABLE IF EXISTS `sys_role_function`;

CREATE TABLE `sys_role_function` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `function_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_function` */

LOCK TABLES `sys_role_function` WRITE;

insert  into `sys_role_function`(`id_`,`role_id`,`function_id`) values (1,1,1),(4,1,21),(5,1,22),(9,1,21),(10,1,22),(11,2,25),(14,2,22),(16,2,29),(17,2,31),(18,2,36),(19,2,35),(21,2,30),(22,3,25),(25,3,14),(26,3,17),(27,3,18),(28,3,20),(29,3,19),(32,3,34),(33,3,36),(34,3,30),(35,3,35),(36,3,22),(37,3,31),(38,3,29),(39,3,21),(40,3,27),(42,3,1),(43,3,4),(44,3,3),(45,3,23),(46,3,24),(47,3,2),(48,3,37),(49,2,38),(50,2,39),(51,2,27);

UNLOCK TABLES;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `login_name` varchar(50) DEFAULT NULL,
  `login_pwd` varchar(100) DEFAULT NULL,
  `status_` char(1) DEFAULT NULL,
  `created_time` varchar(19) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

LOCK TABLES `sys_user` WRITE;

insert  into `sys_user`(`id_`,`role_id`,`user_name`,`login_name`,`login_pwd`,`status_`,`created_time`,`creator_id`) values (1,1,'管理员','admin','45842360a0bcfbe48ab0c6ed3b1911d3','1','2016-03-25 18:37:43',1);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
