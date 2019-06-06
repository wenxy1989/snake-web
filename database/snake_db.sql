-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: snake_db
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inter_application`
--

DROP TABLE IF EXISTS `inter_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_application` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `name_` varchar(20) DEFAULT NULL,
  `code_` varchar(20) DEFAULT NULL,
  `type_` varchar(20) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_application`
--

LOCK TABLES `inter_application` WRITE;
/*!40000 ALTER TABLE `inter_application` DISABLE KEYS */;
INSERT INTO `inter_application` VALUES (1,'血糖管理','glucose_management','service','动态血糖管理平台','2019-06-05 05:40:45',1);
/*!40000 ALTER TABLE `inter_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inter_group`
--

DROP TABLE IF EXISTS `inter_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_group` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(4) DEFAULT NULL,
  `name_` varchar(20) DEFAULT NULL,
  `model_` varchar(20) DEFAULT NULL,
  `status_` int(1) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_group`
--

LOCK TABLES `inter_group` WRITE;
/*!40000 ALTER TABLE `inter_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `inter_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inter_model`
--

DROP TABLE IF EXISTS `inter_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_model` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(4) DEFAULT NULL,
  `name_` varchar(20) DEFAULT NULL,
  `code_` varchar(20) DEFAULT NULL,
  `status_` int(1) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_model`
--

LOCK TABLES `inter_model` WRITE;
/*!40000 ALTER TABLE `inter_model` DISABLE KEYS */;
INSERT INTO `inter_model` VALUES (1,1,'测量血糖','Glucose',0,'测量血糖','2019-06-05 05:42:37',1),(2,1,'测量血糖','Glucose',0,'测量血糖','2019-06-05 05:47:39',1);
/*!40000 ALTER TABLE `inter_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inter_model_parameter`
--

DROP TABLE IF EXISTS `inter_model_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_model_parameter` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `model_id` bigint(4) DEFAULT NULL,
  `key_type` int(1) DEFAULT NULL,
  `name_` varchar(20) DEFAULT NULL,
  `code_` varchar(20) DEFAULT NULL,
  `type_` varchar(20) DEFAULT NULL,
  `is_array` int(1) DEFAULT NULL,
  `allow_blank` int(1) DEFAULT NULL,
  `length_` int(1) DEFAULT NULL,
  `regex_` varchar(20) DEFAULT NULL,
  `example_` varchar(20) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_model_parameter`
--

LOCK TABLES `inter_model_parameter` WRITE;
/*!40000 ALTER TABLE `inter_model_parameter` DISABLE KEYS */;
INSERT INTO `inter_model_parameter` VALUES (1,2,1,'ID','id','Long',0,0,11,'*','11','主键','2019-06-05 05:47:39',NULL),(2,2,0,'已删除','deleted','Integer',0,0,1,'*','0','逻辑删除标志','2019-06-05 05:47:39',NULL),(3,2,0,'创建时间','createTime','String',0,0,19,'*','2016-12-27 14:59:16','时间格式字符串','2019-06-05 05:47:39',NULL),(4,2,0,'创建人ID','createId','Long',0,0,11,'*','11','创建人ID','2019-06-05 05:47:39',NULL),(5,2,0,'创建人名称','createUser','String',0,0,50,'*','wenxy','创建人名称','2019-06-05 05:47:39',NULL),(6,2,0,'更新时间','updateTime','String',0,0,19,'*','2016-12-27 15:11:55','更新时间','2019-06-05 05:47:39',NULL),(7,2,0,'更新人ID','updateId','Long',0,0,11,'*','11','更新人ID','2019-06-05 05:47:39',NULL),(8,2,0,'更信人名称','updateUser','String',0,0,50,'*','wenxy','更信人名称','2019-06-05 05:47:39',NULL),(9,1,0,'主键','id','Long',NULL,0,4,'[0-9]+','1','自增长整形数字','2019-06-05 06:00:14',NULL);
/*!40000 ALTER TABLE `inter_model_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inter_parameter`
--

DROP TABLE IF EXISTS `inter_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_parameter` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `name_` varchar(20) DEFAULT NULL,
  `code_` varchar(20) DEFAULT NULL,
  `type_` varchar(20) DEFAULT NULL,
  `is_array` int(1) DEFAULT NULL,
  `allow_blank` int(1) DEFAULT NULL,
  `length_` int(1) DEFAULT NULL,
  `regex_` varchar(20) DEFAULT NULL,
  `example_` varchar(20) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_parameter`
--

LOCK TABLES `inter_parameter` WRITE;
/*!40000 ALTER TABLE `inter_parameter` DISABLE KEYS */;
INSERT INTO `inter_parameter` VALUES (1,'主键','id','Long',NULL,NULL,4,'[0-9]+','1','自增长整形数字','2019-06-05 05:59:32',1);
/*!40000 ALTER TABLE `inter_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inter_result_parameter`
--

DROP TABLE IF EXISTS `inter_result_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_result_parameter` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `url_id` bigint(4) DEFAULT NULL,
  `name_` varchar(20) DEFAULT NULL,
  `code_` varchar(20) DEFAULT NULL,
  `type_` varchar(20) DEFAULT NULL,
  `is_array` int(1) DEFAULT NULL,
  `allow_blank` int(1) DEFAULT NULL,
  `length_` int(1) DEFAULT NULL,
  `regex_` varchar(20) DEFAULT NULL,
  `example_` varchar(20) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_result_parameter`
--

LOCK TABLES `inter_result_parameter` WRITE;
/*!40000 ALTER TABLE `inter_result_parameter` DISABLE KEYS */;
/*!40000 ALTER TABLE `inter_result_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inter_upload_parameter`
--

DROP TABLE IF EXISTS `inter_upload_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_upload_parameter` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `url_id` bigint(4) DEFAULT NULL,
  `name_` varchar(20) DEFAULT NULL,
  `code_` varchar(20) DEFAULT NULL,
  `type_` varchar(20) DEFAULT NULL,
  `is_array` int(1) DEFAULT NULL,
  `allow_blank` int(1) DEFAULT NULL,
  `length_` int(1) DEFAULT NULL,
  `regex_` varchar(20) DEFAULT NULL,
  `example_` varchar(20) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_upload_parameter`
--

LOCK TABLES `inter_upload_parameter` WRITE;
/*!40000 ALTER TABLE `inter_upload_parameter` DISABLE KEYS */;
/*!40000 ALTER TABLE `inter_upload_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inter_url`
--

DROP TABLE IF EXISTS `inter_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inter_url` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(4) DEFAULT NULL,
  `group_id` bigint(4) DEFAULT NULL,
  `name_` varchar(20) DEFAULT NULL,
  `url_` varchar(20) DEFAULT NULL,
  `type_` int(1) DEFAULT NULL,
  `position_` int(1) DEFAULT NULL,
  `logic_` varchar(50) DEFAULT NULL,
  `operate_` int(1) DEFAULT NULL,
  `regex_` varchar(20) DEFAULT NULL,
  `upload_type` int(1) DEFAULT NULL,
  `upload_data` varchar(20) DEFAULT NULL,
  `upload_example` varchar(20) DEFAULT NULL,
  `result_type` int(1) DEFAULT NULL,
  `result_data` varchar(20) DEFAULT NULL,
  `result_example` varchar(20) DEFAULT NULL,
  `status_` bigint(1) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inter_url`
--

LOCK TABLES `inter_url` WRITE;
/*!40000 ALTER TABLE `inter_url` DISABLE KEYS */;
INSERT INTO `inter_url` VALUES (1,1,0,'获取佩戴血糖','glucose_by_wear',0,0,'select * from sam_glucose where wear_id=#{wearId}',NULL,NULL,3,NULL,NULL,3,NULL,NULL,0,'根据佩戴记录Id获取佩戴的血糖记录','2019-06-05 05:53:36',1);
/*!40000 ALTER TABLE `inter_url` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_function`
--

DROP TABLE IF EXISTS `sys_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_function`
--

LOCK TABLES `sys_function` WRITE;
/*!40000 ALTER TABLE `sys_function` DISABLE KEYS */;
INSERT INTO `sys_function` VALUES (1,0,'系统管理','system',1,'',NULL,9,'2016-03-25 19:17:54',NULL),(2,1,'菜单管理','menu',1,'/function/page',1,9,'2016-03-25 18:44:49',1),(3,1,'用户管理','user',1,'/user/page',NULL,0,'2016-03-25 19:15:11',NULL),(4,1,'角色管理','role',1,'/role/page',NULL,0,'2016-03-25 19:14:53',NULL),(23,1,'系统参数','sys_parameter',1,'/sys/parameter/page',NULL,4,'2016-11-02 18:42:56',NULL),(24,1,'首页设置','sys_index',1,'/sys/index/page',NULL,10,'2016-11-03 10:50:41',NULL),(42,0,'应用管理','application',1,'',NULL,10,'2019-06-05 13:38:27',NULL),(43,42,'参数管理','inter_parameter',1,'/inter/parameter/page',NULL,1,'2019-06-05 13:56:16',NULL),(44,42,'应用管理','inter_application',1,'/inter/application/page',NULL,2,'2019-06-05 13:57:07',NULL),(45,0,'模型管理','template',1,'',NULL,11,'2019-06-05 14:03:19',NULL),(46,45,'模型信息','template_info',1,'/template/info/page',NULL,1,'2019-06-05 14:04:10',NULL);
/*!40000 ALTER TABLE `sys_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_index`
--

DROP TABLE IF EXISTS `sys_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_index` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(50) DEFAULT NULL,
  `url_` varchar(100) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `created_time` varchar(19) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_index`
--

LOCK TABLES `sys_index` WRITE;
/*!40000 ALTER TABLE `sys_index` DISABLE KEYS */;
INSERT INTO `sys_index` VALUES (1,'菜单管理','/function/page',1,1,'2016-03-25 19:13:46',1);
/*!40000 ALTER TABLE `sys_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_parameter`
--

DROP TABLE IF EXISTS `sys_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_parameter`
--

LOCK TABLES `sys_parameter` WRITE;
/*!40000 ALTER TABLE `sys_parameter` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'admin','admin','1','超级管理员，应该把此角色写入代码中，作为静态变量，并且可以不需要设置访问任何的接口','2016-03-25 18:40:38',1),(3,'超级管理员','super_admin','1','超级管理员，拥有本应用大部分权限','2016-12-08 15:49:28',1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_function`
--

DROP TABLE IF EXISTS `sys_role_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_function` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `function_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_function`
--

LOCK TABLES `sys_role_function` WRITE;
/*!40000 ALTER TABLE `sys_role_function` DISABLE KEYS */;
INSERT INTO `sys_role_function` VALUES (1,1,1),(4,1,21),(5,1,22),(9,1,21),(10,1,22),(11,2,25),(14,2,22),(16,2,29),(17,2,31),(18,2,36),(19,2,35),(21,2,30),(22,3,25),(25,3,14),(26,3,17),(27,3,18),(28,3,20),(29,3,19),(32,3,34),(33,3,36),(34,3,30),(35,3,35),(36,3,22),(37,3,31),(38,3,29),(39,3,21),(40,3,27),(42,3,1),(43,3,4),(44,3,3),(45,3,23),(46,3,24),(47,3,2),(48,3,37),(49,2,38),(50,2,39),(51,2,27);
/*!40000 ALTER TABLE `sys_role_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,1,'管理员','admin','21232f297a57a5a743894a0e4a801fc3','1','2016-03-25 18:37:43',1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template_group`
--

DROP TABLE IF EXISTS `template_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template_group` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `name_` varchar(20) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template_group`
--

LOCK TABLES `template_group` WRITE;
/*!40000 ALTER TABLE `template_group` DISABLE KEYS */;
INSERT INTO `template_group` VALUES (1,'spring-boot-mybatis','后端接口模板组,使用spring-boot-web和mybatis实现','2019-06-05 06:12:00',1);
/*!40000 ALTER TABLE `template_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template_info`
--

DROP TABLE IF EXISTS `template_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template_info` (
  `id_` bigint(4) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(4) DEFAULT NULL,
  `group_` varchar(20) DEFAULT NULL,
  `name_` varchar(20) DEFAULT NULL,
  `type_` varchar(20) DEFAULT NULL,
  `update_type` int(4) DEFAULT NULL,
  `save_path_model` varchar(50) DEFAULT NULL,
  `save_file_model` varchar(50) DEFAULT NULL,
  `remark_` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template_info`
--

LOCK TABLES `template_info` WRITE;
/*!40000 ALTER TABLE `template_info` DISABLE KEYS */;
INSERT INTO `template_info` VALUES (1,1,'controller','Controller','service',0,'controller/${model}Controller.java','controller/controller.ftl','控制器模板,生成查询/创建/修改/删除方法','2019-06-05 06:20:21',NULL);
/*!40000 ALTER TABLE `template_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06 17:05:00
