/*
SQLyog Ultimate v8.32 
MySQL - 5.0.22-community-nt : Database - base
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`base_node` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `base_node`;

/*Table structure for table `node_group` */

DROP TABLE IF EXISTS `node_group`;

CREATE TABLE `node_group` (
  `ID_` bigint(20) NOT NULL,
  `RIGHT_` bigint(20) default NULL,
  `NAME_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `CREATE_ID` bigint(20) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `node_group` */

insert  into `node_group`(`ID_`,`RIGHT_`,`NAME_`,`TITLE_`,`CREATE_TIME`,`CREATE_ID`,`STATUS_`) values (1,NULL,'笔记分组1','1.添加笔记分组测试，\r\n2.修改笔记分组测试','2013-12-28 13:23:27',NULL,NULL);

/*Table structure for table `node_info` */

DROP TABLE IF EXISTS `node_info`;

CREATE TABLE `node_info` (
  `ID_` bigint(20) default NULL,
  `NAME_` varchar(50) default NULL,
  `PARENT_ID` bigint(20) default NULL,
  `EXPLAIN_` varchar(765) default NULL,
  `CREATE_TIME` varchar(20) default NULL,
  `FIELD_` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  `FIELD_ID` bigint(20) default NULL,
  `CREATE_ID` bigint(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `node_info` */

insert  into `node_info`(`ID_`,`NAME_`,`PARENT_ID`,`EXPLAIN_`,`CREATE_TIME`,`FIELD_`,`TITLE_`,`STATUS_`,`FIELD_ID`,`CREATE_ID`) values (1,'root根元素',0,'作为所有元素根元素，只有子元素，没有父元素','2012-06-20 18:01:59',1,NULL,NULL,1,NULL),(2,'wen',1,'wen','2012-07-29 10:17:18',2,NULL,NULL,2,NULL),(3,'Family',2,'家家网，简化的虚拟世界（网页版、规划中）','2012-06-20 18:01:59',2,NULL,NULL,3,NULL),(8,'基础元素node',87,'包括id，name，explain，feild，date等','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(9,'角色设计',3,'家家网角色暂时分为普通用户和管理员，普通用户按照执行的动作的不同又分为本人、陌生人、及亲友','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(10,'功能模块设计',3,'按照功能，操作步骤的不同，普通用户的动作模块被分为权限验证（登陆，重置密码，激活账户等）、信息管理（查找添加亲友、查看同意新申请，发送信息申请）、关系访问（通过已有的关系网访问其亲友基本信息并可以发送申请）','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(13,'功能具备',3,'除了具备信息的添加、检索、删除、自动识别外、还具有图片上传功能','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(18,'功能描述',87,'基本的增删改查。直接添加其他元素为子元素。展示用户所属信息，及其他用户共享的信息','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(19,'基本功能',18,'node自身的增删改查。只展示用户自身及其被其共享的其他用户的数据。','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(20,'高级功能',18,'一个元素可以从用户（待添加）自身的资源库（node集合）添加或剪切别的元素（被添加或更改的元素仅限于自身，不包含子元素），并且通过用户之间的共享可以同样是用其他用户的资源。使用方式视用户设定的共享权限级别而定','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(21,'添加方式',20,'复制、剪切（对其子元素有影响）\r\n添加方式采用ajax单条添加','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(24,'项目命名',23,'今后我得项目用，文科对应腾讯，用AA等对应QQ','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(25,'界面效果',21,'筛选条件确定后，显示待选列表，每个元素后面跟两个超链接（复制、剪切），选中一个待选元素后，此元素从待选列表中转移到子元素列表中并且带有取消按钮。当点击取消按钮后，被点元素回到待选列表中','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(26,'复制链接',25,'新建立一个元素，数据来自提供的id','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(27,'剪切链接',25,'修改提供id的from属性，因此可继承其所有子元素','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(28,'id',8,'唯一标示属性','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(29,'name',8,'属性简略的描述，在同一个父元素下唯一表示一个元素','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(31,'from',8,'父元素id，通过此属性链接根元素（表明所属的模块）','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(32,'level',8,'可有可无，表示元素距离root的距离，数值越大说明所属模块越复杂（可有可无）','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(33,'son',8,'子元素的个数，数目越大，表明此属性设计的越详细（可有可无）','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(34,'explain',8,'元素功能的纤细介绍，并包含一些相关的想法','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(37,'闪现灵感',2,'偶尔闪现的创造性的想法','2012-06-20 18:01:59',2,NULL,NULL,37,NULL),(38,'主角光环',37,'当用户在线时，其概率性有益性动作发生概率加大','2012-06-20 18:01:59',2,NULL,NULL,38,NULL),(39,'资源角色',37,'游戏中的活动单位，无论是否为用户创建的角色，都算作游戏中的资源，是可以被其他单位利用的','2012-06-20 18:01:59',2,NULL,NULL,39,NULL),(40,'网站：和谐社会（类似于糗事百科）',37,'以各自的行业身份为背景，对提供的工作服务行为提出正当的解释，解释自己不喝常理的反应，促进社会和谐发展，人与人和谐相处','2012-06-20 18:01:59',2,NULL,NULL,40,NULL),(41,'评价方式',40,'多选、包含（理解、支持、迷惑、谴责）','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(42,'理解',41,'于楼主想法类似，符合人情','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(43,'支持',41,'在同等情况下也会像楼主一样做','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(44,'迷惑',41,'不理解楼主所言，求解释','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(45,'谴责',41,'提出自己的想法，反对楼主这样做','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(47,'版本0.1',87,'区分用户，每个用户仅能管理自己的资源，也可以用户之间信息共享（单方面）','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(48,'isfieldroot',8,'是否作为权限的起始节点，如果为真，则其子孙节点的field为此元素id；若为假，此节点field同其父节点','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(49,'field',8,'统一权限的基本单位，可以和互相包含','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(50,'user',8,'创建node的用户，仅作为标记使用','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(51,'time',8,'创建时间','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(52,'Node设定',47,'id,name,explain,from,user,date,详细见Node','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(53,'Share',47,'id,from(数据源),to（可查看用户）,type（共享权限）,date','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(55,'分页',19,'对于数目不定的信息，要分页，并且指定显示区域大小','2012-06-20 18:01:59',2,NULL,NULL,NULL,NULL),(56,'level',0,'可有可无，表示元素距离root的距离，数值越大说明所属模块越复杂（可有可无）','2012-06-20 19:25:23',2,NULL,NULL,56,NULL),(57,'权限系统',47,'权限向下兼容，不向上兼容','2012-06-21 10:37:54',2,NULL,NULL,NULL,NULL),(58,'用户元素',47,'包含用户的验证，用户关系，用户消息','2012-06-21 10:40:25',2,NULL,NULL,NULL,NULL),(59,'root根元素',28,'作为所有元素根元素，只有子元素，没有父元素','2012-06-21 10:44:58',2,NULL,NULL,NULL,NULL),(64,'0.1功能性修改',2,'所有节点皆为共享资源，用户注册后自动获取一个根元素子元素的“删除权限（级别4）”；添加“剪切”功能','2012-06-21 11:01:40',2,NULL,NULL,64,NULL),(65,'权限设定',8,'从低到高分别为：“无权限（级别0）”，“只读（级别1）”，“添加（级别2）”，“修改（级别3）”，“删除（级别4）”','2012-06-21 11:04:36',2,NULL,NULL,NULL,NULL),(66,'删除权限',65,'最高权限，可以对此共享级别的元素作任何操作','2012-06-21 11:05:25',2,NULL,NULL,NULL,NULL),(67,'修改权限',65,'不可以删除、剪切共享元素，其余的 操作均可','2012-06-21 11:06:21',2,NULL,NULL,NULL,NULL),(68,'添加权限',65,'可以查看、添加元素','2012-06-21 11:06:55',2,NULL,NULL,NULL,NULL),(69,'只读权限',65,'只可以查看元素','2012-06-21 11:07:19',2,NULL,NULL,NULL,NULL),(71,'删除修改',64,'所有的删除都改为逻辑删除','2012-06-21 11:13:34',2,NULL,NULL,NULL,NULL),(72,'编程结构修改',64,'所有的model类要实现一个数据操作借口并继承一个数据操作类，数据操作直接基于model类进行','2012-06-21 11:19:30',2,NULL,NULL,NULL,NULL),(73,'召集模式',38,'游戏玩家使用若干个已知账号，联络同等数目的其他玩家，进入游戏。每个玩家进入游戏后，自选角色，成团队型进行攻防战','2012-06-21 13:13:42',2,NULL,NULL,73,NULL),(74,'思考与娱乐',37,'在等车或者坐车的时候，思考要比娱乐更方便','2012-06-24 18:57:57',2,NULL,NULL,74,NULL),(75,'版本0.2',87,'资源采用域共享模式，用户新建立后自动获得一个域的最高权限。','2012-06-25 10:11:33',2,NULL,NULL,NULL,NULL),(76,'方法一：催眠',74,'在等车或者坐车的时候，思考要比娱乐更方便催眠使之克服当时的不适之感。如：炎热时：催眠受者在和可乐（或是在洗澡），使受者感觉舒适。','2012-06-25 11:06:47',2,NULL,NULL,NULL,NULL),(77,'编程模式',75,'在action中每个动作都先准备好参数，通过调用其他private方法执行；删除舍去物理删除，采用逻辑删除','2012-06-25 11:10:43',2,NULL,NULL,NULL,NULL),(78,'样式设计',75,'使用清华大学新门户的样式,样式不重要。接下来应该风富功能，使使用起来更方便','2012-06-25 11:53:17',2,NULL,NULL,NULL,NULL),(79,'废物',75,'删除操作','2012-07-28 23:02:01',2,NULL,NULL,NULL,NULL),(81,'资源与共享',39,'资源是单机版的，若要使资源产生关系就需要共享了。每种资源都有自己的属性，当有相同属性的资源相遇时，两种资源就会相互影响。','2012-07-29 10:17:18',2,NULL,NULL,NULL,NULL),(82,'共享域field',87,'共享的基本单位。最基本的属性（id、beginNodeId）','2012-07-29 10:35:08',2,NULL,NULL,NULL,NULL),(83,'分享记录share',87,'不可或缺的属性（field，user，right），可选属性（name，creater）','2012-07-29 15:44:09',2,NULL,NULL,NULL,NULL),(84,'最高境界-九鼎记观后感',37,'成为虚境只要领悟一种道，虚境大成则即悟道的最高境界，不是领悟所有的道，而是领悟能构成一个完整世界的所有道','2012-08-12 02:09:51',2,NULL,NULL,84,NULL),(85,'资源比重',39,'所有玩家被杀死后，所有资源掉落，自身也化为一定资源存在于服务器中，可被所有人拥有。玩家所值资源按玩家裸身实习划分','2012-08-14 15:57:53',2,NULL,NULL,NULL,NULL),(86,'人工智能语言',37,'人工智能依赖于事件返回。语言方面则依赖于记忆。','2012-08-22 12:59:15',2,NULL,NULL,86,NULL),(87,'Node',2,'为毕业而写，并带有一定基础性的软件，今后能在此设计的基础上再进行扩展或开发，使以后的同类工作得以简单一些','2012-06-20 18:01:59',2,NULL,NULL,87,NULL),(180,'node需求深度解读',2,'node的优点，可优化性思考','2012-07-13 20:20:44',2,NULL,NULL,180,NULL),(190,'工程流程设计',2,'设计业务的流程，包含业务逻辑，直面化设计。。。设计免费，导出收费。。。\r\n功能\r\n1.网站流程设计\r\n2.业务流程设计\r\n视图\r\n1.表格式，利于对比同级流程\r\n2.图形类，方便观察单一流程，及其信息','2012-07-16 15:25:23',2,NULL,NULL,190,NULL),(200,'wen',2,'该怎么说呢wen','2012-06-29 19:19:29',2,NULL,NULL,200,NULL),(300,'工作的由来',2,'工作是为了规范化，批量化，并收取一定费用的为他人服务的过程。\r\n工作本来就是为了帮助他人而产生的。并对自身有利的一件事。','2012-06-29 19:23:00',2,NULL,NULL,300,NULL),(400,'1318753215@qq.com',2,'1318753215@qq.com','2012-07-01 11:16:35',2,NULL,NULL,400,NULL),(500,'786129907@qq.com',2,'786129907@qq.com','2012-07-01 11:50:09',2,NULL,NULL,500,NULL),(600,'工作的由来',2,'zanwu','2012-07-01 12:08:44',2,NULL,NULL,600,NULL),(700,'今日工作安排',2,'完善用户登陆注册激活重置等功能。弄清系统其他未完善部分信息','2012-07-01 12:10:07',2,NULL,NULL,700,NULL),(800,'好友功能添加',2,'在好友模块中展示好友列表，点击任意好友后显示下拉列表显示与此好友聊天信息。添加用户搜索功能','2012-07-01 13:30:18',2,NULL,NULL,800,NULL),(900,'信箱修改',2,'信箱内默认显示用户最近聊天记录，无排序。点击任意其他用户后在新页面显示相应聊天记录','2012-07-01 13:31:59',2,NULL,NULL,900,NULL),(1000,'修改安排',2,'注册，密码重置界面更换；\r\n给每个页添加title；\r\n删除无用代码及页面','2012-07-04 21:54:42',2,NULL,NULL,1000,NULL),(2000,'漂流瓶算法',2,'抛出漂流瓶后，生成漂流瓶信息（信息，排序，状态）。当用户试图键漂流瓶时，随机生成一个数字。然后找到数字于序号匹配的漂流瓶，如果漂流瓶状态为“漂流中”，则置状态为以“靠岸”，并返回给用户漂流瓶信息；其他均返回未找到漂流瓶','2012-07-21 21:42:12',2,NULL,NULL,2000,NULL),(2001,'智能系统接口',86,'系统提供一系列的接口，应用程序可以依照系统接口提供解决方法或建议，当某一个应用调用一个系统接口后就比如说：用户口述一条命令“what date tody？”，语音软件解析单词，然后调用系统接口。各个实现了此条命令软件都可以给出自己放案：1.当天日期/纪念日等等日历信息2.家人生日/代办事务等等时间表上的记录3.约会/短信上的待处理问题。。。。等等。如果选中了某个软件提供的信息，还可以根据选中的信息继续调用系统接口。\r\n','2013-05-26 22:39:25',2,NULL,NULL,NULL,NULL),(2002,'test',2,'删除测试','2013-06-02 12:46:06',2,NULL,NULL,2002,NULL),(2003,'软件程序设计、开发辅助系统',37,'开发一个新项目，所有人员统一设计新项目的平台。\r\n功能：设计工程、生成文档、初始代码\r\n基本单位：方法、类变量、系统变量。\r\n特点：方法添加后即可见，不需要同步，更新等操作。修改某些类名包名不需要修改其他（每个基本单位以id区分，文档代码以名称为准）','2013-06-15 18:16:53',2,NULL,NULL,2003,NULL),(2004,'方法设计',2003,'基本属性：id、方法名、参数、返回类型、默认返回值、方法体、是否为静态、方法详述（以项目经理看懂为合格）\r\n功能：可以评论并提醒原设计者，方便多个开发人员交流','2013-06-15 18:32:43',2,NULL,NULL,NULL,NULL),(2005,'方法参数设计',2004,'属性：类型、合法值域（默认不能为空，生成代码时也应体现出这点）、默认值（自动生成代码后即可查看是否符合设计）\r\n详述：可评论、修改仅限原设计者','2013-06-15 18:35:18',2,NULL,NULL,NULL,NULL),(2006,'类及模块设计',2003,'两者同为基本单位集合\r\n属性：id、名称、详述','2013-06-15 18:40:47',2,NULL,NULL,NULL,NULL),(2007,'单位设计',2003,'每个单位id为唯一标示符。无删除功能，紧区别是否有效，默认不展示无效单位。无效单位与有效单位展示时分开','2013-06-15 18:42:47',2,NULL,NULL,NULL,NULL),(2008,'手机端服务器',87,'现在使用的web服务器不能保留手机端的session，手机端服务器需要自己设计使用socket服务器','2013-06-17 23:52:32',2,NULL,NULL,NULL,NULL),(2009,'（编程与小说世界）兴趣集合',37,'将先在网络上好看的小说作为需求文档，通过javaEE程序构建小说中描绘的世界。首先从罪恶之城开始。\r\n设定\r\n1.速度即时间。\r\n2.基本单位：灵魂\r\n3.级别系统：力量、爵位','2013-12-11 13:23:08',2,NULL,NULL,2009,NULL),(2010,'游戏玩家操作领域',37,'一个游戏世界中，玩家的实力由等级、属性加成、速度以及智慧等决定，可以添加其他的因素，如：计算能力\r\n一个标准的魔法包含准备时间、魔力消耗、魔法效果（伤害等）等因素，可以设计以魔法效果更改为代价减少或增加某些属性的消耗','2013-12-29 21:09:08',2,NULL,NULL,2010,NULL),(2011,'玩家引导',37,'战斗只能摧毁财富、转移财富，但是不能创造财富。\r\n设置各种辅助职业，如铁匠、炼药师等，利用规则将财富向辅助职业转移','2013-12-30 16:09:55',NULL,NULL,NULL,37,2),(2012,'玩家操作之诅咒',37,'玩家可以花费资源来诅咒另外的玩家角色、被诅咒的角色会带有一定的负面状态。如：在一定时间内显示为装备掉落、被击杀奖励等。被诅咒的角色同样可以花费一定资源解除或削弱诅咒效果','2013-12-31 18:38:22',NULL,NULL,NULL,37,NULL);

/*Table structure for table `node_purview` */

DROP TABLE IF EXISTS `node_purview`;

CREATE TABLE `node_purview` (
  `ID_` bigint(20) NOT NULL,
  `GROUP_ID` bigint(20) default NULL,
  `RIGHT_` bigint(20) default NULL,
  `PURVIEW_TYPE` tinyblob,
  `NAME_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `CREATE_ID` bigint(20) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `node_purview` */

/*Table structure for table `noval_character` */

DROP TABLE IF EXISTS `noval_character`;

CREATE TABLE `noval_character` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  `USER_ID` bigint(20) default NULL,
  `CREATE_ID` bigint(20) default NULL,
  `RACE_ID` bigint(20) default NULL,
  `WORLD_ID` bigint(20) default NULL,
  `FAMILY_ID` bigint(20) default NULL,
  `LONGTH_` bigint(20) default NULL,
  `WIDTH_` bigint(20) default NULL,
  `HEIGHT_` bigint(20) default NULL,
  `WEIGHT_` bigint(20) default NULL,
  `ENERGY_` bigint(20) default NULL,
  `SPIRIT_` bigint(20) default NULL,
  `HARDNESS_` bigint(20) default NULL,
  `LIVE_` bigint(20) default NULL,
  `ATK_` bigint(20) default NULL,
  `ARMOR_` bigint(20) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_character` */

insert  into `noval_character`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`,`USER_ID`,`CREATE_ID`,`RACE_ID`,`WORLD_ID`,`FAMILY_ID`,`LONGTH_`,`WIDTH_`,`HEIGHT_`,`WEIGHT_`,`ENERGY_`,`SPIRIT_`,`HARDNESS_`,`LIVE_`,`ATK_`,`ARMOR_`) values (29,'李察',1,'罪恶之城主角，身具恶魔、与高等精灵血脉，同时身为最富有的构装师','人物出生年月',NULL,0,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1000,45,10),(30,'哥顿',1,'李察的父亲，奇迹般的男人，被门萨、约瑟夫、熊彼得等家族陷害在骆琦位面，最终被莫德雷德送往深渊最底层阿比斯深渊，现生死不知','人物出生年月',NULL,0,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,'流沙',1,NULL,'人物出生年月',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'无面',1,NULL,'人物出生年月',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'山与海',1,NULL,'人物出生年月',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,'迦兰帝君',1,NULL,'人物出生年月',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,'伊兰妮',1,NULL,'人物出生年月',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'苏海伦',1,NULL,'人物出生年月',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,'索伦',1,'侯爵爵位，阿克蒙德家族较古老的强大分支','人物出生年月',NULL,0,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,'哥利亚',1,'哥顿的哥哥，继承了父亲的伯爵爵位及大部分领地，阿克蒙德家族三大分支之一','人物出生年月',NULL,0,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,'独角兽子爵',1,NULL,'人物出生年月',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'龙驭',1,'请填写标题（标志）',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,'白夜',1,'诺兰德铁血大公之女、阿伽门农之姐，战力超强，常驻绝域战场：黄昏之地','2013-12-16 14:43:42',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,'要离',1,'罪恶之城中蛮族法师','2013-12-16 15:12:40',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,'古尔扎巴',1,'踏风部落最出色的勇士之一，与李察第一次踏上卡兰多大陆时，曾阻拦过李察前进','2013-12-16 15:25:06',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,'丕平.撒尔逊',1,'带领撒尔逊家族脱离千年帝国的首领，之后将李察送上了卡兰多大陆','2013-12-16 15:33:06',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,'巴力巴力',1,'天岚部落年轻勇士中的第一人，当李察第一次踏上卡兰多大陆时与李察两败俱伤','2013-12-16 15:57:05',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,'黑乌萨',1,'当李察与巴力巴力第一次斗得两败俱伤时突然出现意图渔翁得利。为人邪恶、力量中很大一部分来自死亡。','2013-12-16 16:02:46',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,'拉玛卓娅',1,'擅使长鞭，受人指使，意图强奸李察破坏李察与山与海的约定','2013-12-16 16:09:34',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,'韩立',2,'排行老二，村人称其为二愣子','2013-12-22 22:47:19',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,'南宫婉',2,'韩立之妻','2013-12-22 22:51:16',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'青元子',2,'青元剑诀创立人，算是主角的半个师傅，后来遭遇变故与异族融合','2013-12-22 22:54:52',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,'卢诺.雷.法比奥',1,'在李察之前的圣神同盟的皇家构装师，可以制作三阶构装','2013-12-26 16:42:16',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'我的角色',NULL,'自建角色，带所属用户信息及种族、家族信息','',NULL,2,2,56,NULL,19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1000,68,20);

/*Table structure for table `noval_element` */

DROP TABLE IF EXISTS `noval_element`;

CREATE TABLE `noval_element` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `BELONG_ID` bigint(20) default NULL,
  `PATH_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_element` */

insert  into `noval_element`(`ID_`,`NAME_`,`BELONG_ID`,`PATH_`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (1,'人物',1,'character/list.do','小说内的人物名称','2013-12-22 21:38:47',NULL),(2,'位面',1,'world/list.do','以规则环境等划分类别，以晶壁划分地理的世界','2013-12-22 21:40:02',NULL),(3,'家族',1,'family/list.do','以血脉为纽带的生物集体','2013-12-22 21:48:55',NULL),(4,'种族',1,'race/list.do','从生物学划分的族群','2013-12-22 21:50:21',NULL),(5,'装备',1,'equipment/list.do','人物附属的物品','2013-12-22 22:06:42',NULL),(6,'人物',2,'character/list.do','修仙角色','2013-12-22 22:44:14',NULL),(7,'技能',2,'skill/list.do','法术、仪式等','2013-12-25 22:14:39',NULL),(8,'组织',2,'organization/list.do','属于非典型的各种集合体','2013-12-25 22:45:49',NULL),(9,'组织',1,'organization/list.do','各种非典型集合体，如所罗门堡','2013-12-25 22:46:30',NULL),(10,'职业',2,'profession/list.do','如罪恶之城的构装师，如斗破苍穹的炼药师','2013-12-26 15:49:14',NULL),(11,'职业',1,'profession/list.do','各种能力结合所能完成一些事物时，具有的属性','2013-12-26 17:00:59',NULL),(12,'职业',29,'profession/list.do','构装师、魔法师','2013-12-27 01:11:42',NULL);

/*Table structure for table `noval_energy` */

DROP TABLE IF EXISTS `noval_energy`;

CREATE TABLE `noval_energy` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_energy` */

/*Table structure for table `noval_equipment` */

DROP TABLE IF EXISTS `noval_equipment`;

CREATE TABLE `noval_equipment` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_equipment` */

insert  into `noval_equipment`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (6,'强袭装甲',NULL,'人物出生年月',NULL,NULL),(7,'蛮牛之力',NULL,'能够增加一定比例力量的一阶标准构装','2013-12-26 16:04:40',NULL),(9,'初阶力量',NULL,'可增幅39%力量的一阶构装','2013-12-26 16:24:05',NULL),(10,'初阶防御',NULL,'李察发布的蛮荒骑士套装中第二件一阶构装','2013-12-26 16:34:13',NULL),(11,'活力',NULL,'李察发布的蛮荒骑士套装中第三件一阶构装，非标准构装','2013-12-26 16:35:00',NULL),(12,'生命守护',NULL,'李察发布的蛮荒骑士套装中第三件一阶构装，可以增加防御力，并可以通过吸收主体的生命能量以及战场上游离的各种能量为构装提供能量，多余的能量还可以储存，在有意激发的情况下，储存下来的能量可以反馈给主体，转换成体力、生命力和斗气魔力等等。蛮荒守护核心构装','2013-12-26 16:37:46',NULL),(14,'荒野旅途',NULL,'非标准二阶构装，为主体提供少许的防御力加成，同时略微增加移动速度，并且提高在复杂地形环境下的通过能力，可以用在骑士身上，也可以用在座骑之上','2013-12-26 16:40:17',NULL),(15,'蛮荒壁垒',NULL,'李察第一次发布的构装套装，套装能力激发后，可以形成防御护盾，护盾强度随构装骑士自身的等级增加，可以抵挡等级高出自身三级以下的对手的全力一击。一生命守护为核心。','2013-12-26 16:45:38',NULL),(16,'蛮荒打击',NULL,'以力量爆发为核心，加上初阶力量、守护、蛮荒旅途、活力构成的构装套装。','2013-12-26 16:49:40',NULL),(17,'力量爆发',NULL,'一定力量增幅外，在激活时还可以额外大幅提高力量，和爆发类似，蛮荒打击核心构装','2013-12-26 16:50:32',NULL),(18,'魔纹构装',NULL,'将魔法阵结合并实现一些特定功能的魔法正组浓缩并置于生物体上，协助宿主增强或添加某些功能的物品\r\n最基础、也是必须的三个部分是控制部分、主魔法功能部分及魔力供应部分。\r\n一阶构装同一时间只能放出一个魔法','2013-12-30 13:50:04',NULL);

/*Table structure for table `noval_family` */

DROP TABLE IF EXISTS `noval_family`;

CREATE TABLE `noval_family` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_family` */

insert  into `noval_family`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (18,'阿克蒙德',1,'罪恶之城主角李察所属家族，具有深渊恶魔血脉，主要由各个分支组成，没有固定的直系',NULL,NULL),(19,'撒尔逊',1,'原为海盗，在卡兰多大陆有一个小岛作为领地，拥有连接诺德兰与卡兰多大陆的远程传送阵。因未答应迦兰帝君使用传送阵的要求，被迫称谓独立贵族，依附于神圣联盟','2013-12-16 14:24:17',NULL),(20,'踏风部落',1,'与纳尔逊家族交好的蛮族部落','2013-12-16 14:25:58',NULL),(21,'逐风部落',1,'李察第一次踏上卡兰多大陆时，第二个遇到的部落，期间打赢了踏风部落从第二十到第一的七位年轻战士','2013-12-16 15:38:36',NULL),(22,'夜风部落',1,'部落成员如同流氓，在李察第一次踏上卡兰多大陆时第三个遇上的部落，趁夜袭击李察，最后反而被李察所灭','2013-12-16 15:41:24',NULL),(23,'天岚部落',1,'李察第一次踏上卡兰多大陆时，遇到了巴力巴力（天岚部落勇士）','2013-12-16 15:46:03',NULL),(24,'奥萨家族',1,'以黑曜龙为族徽，圣树王朝七大豪门之一','2013-12-16 16:50:50',NULL),(25,'铁血家族',1,'神圣同盟中，实力仅次于皇室的浮岛豪门。代表人物：阿伽门农、白夜、铁血大公。姓氏 奥尔良','2013-12-26 17:14:13',NULL),(26,'神圣同盟皇室',1,'神圣同盟最大的贵族，代表人物：尼瑞斯、嗜血的飞利浦、无定女皇','2013-12-26 17:15:30',NULL);

/*Table structure for table `noval_info` */

DROP TABLE IF EXISTS `noval_info`;

CREATE TABLE `noval_info` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `AUTHOR_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_info` */

insert  into `noval_info`(`ID_`,`NAME_`,`AUTHOR_`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (1,'罪恶之城','烟雨江南','烟雨江南最新作品，连载中','2013-12-22 20:00:37',NULL),(2,'凡人修仙传','忘语','作者忘语，修仙类精品','2013-12-22 22:43:27',NULL),(3,'斗破苍穹','天蚕土豆','以斗气为基础构建的能量体系世界','2013-12-26 17:06:28',NULL),(4,'佣兵天下','说不得大师','充满了兄弟情谊、铁血征程及神话传说的世界，主角是智慧的艾米','2013-12-26 17:08:07',NULL),(5,'异人傲世录','眀寐','特种兵穿越到魔法世界，向神与魔争夺人类平等权利的过程，主角性格奇特','2013-12-26 17:11:25',NULL);

/*Table structure for table `noval_organization` */

DROP TABLE IF EXISTS `noval_organization`;

CREATE TABLE `noval_organization` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_organization` */

insert  into `noval_organization`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (27,'所罗门堡',1,'由传奇学者发现所罗门堡之后，创建的组织。宗旨为引领诺德兰位面，想当然的认为自己代表整个位面的利益','2013-12-25 22:53:30',NULL),(28,'圣树王朝',1,'由光辉主神后代及其光辉教派创建的大帝国，为诺德兰大三人类帝国之一','2013-12-25 22:54:40',NULL);

/*Table structure for table `noval_peerage` */

DROP TABLE IF EXISTS `noval_peerage`;

CREATE TABLE `noval_peerage` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_peerage` */

/*Table structure for table `noval_place` */

DROP TABLE IF EXISTS `noval_place`;

CREATE TABLE `noval_place` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_place` */

insert  into `noval_place`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (54,'诺德兰',NULL,'罪恶之城中描写的故事发生的主要地点','2013-12-22 20:25:18',NULL);

/*Table structure for table `noval_profession` */

DROP TABLE IF EXISTS `noval_profession`;

CREATE TABLE `noval_profession` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_profession` */

insert  into `noval_profession`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (1,'魔法师',NULL,'可以操控各种魔法能量，并以一定效果释放的职业。诺德兰大陆文明的核心职业','2013-12-30 13:10:38',NULL),(2,'魔纹构装师',NULL,'将魔法阵浓缩绘制于特制魔法插件或者直接绘制在生物身体上的职业。魔纹构装可以通过魔法阵提升宿主的各种能力。','2013-12-30 13:12:26',NULL);

/*Table structure for table `noval_race` */

DROP TABLE IF EXISTS `noval_race`;

CREATE TABLE `noval_race` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  `LONGTH_` bigint(20) default NULL,
  `WIDTH_` bigint(20) default NULL,
  `HEIGHT_` bigint(20) default NULL,
  `WEIGHT_` bigint(20) default NULL,
  `ENERGY_` bigint(20) default NULL,
  `SPIRIT_` bigint(20) default NULL,
  `HARDNESS_` bigint(20) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_race` */

insert  into `noval_race`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`,`LONGTH_`,`WIDTH_`,`HEIGHT_`,`WEIGHT_`,`ENERGY_`,`SPIRIT_`,`HARDNESS_`) values (55,'人族',NULL,'没有什么特殊技能的普通智慧种族，可以较好的吸收其他种族血脉及能力',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'精灵',NULL,'森林之子，身材纤弱，相貌美丽，对魔法元素亲和力较强',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,'兽人',NULL,'面貌体型酷似各种野兽，为各种兽类进化而成的智慧种族',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,'龙族',NULL,'身长双翅，体表附鳞，智慧卓绝，寿命超长，但生育力低下',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,'人族',1,'诺德兰大陆主导种族，具有强大的血脉兼容能力','2013-12-26 16:53:17',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60,'银月精灵',1,'诺德兰大陆精灵中的王族，原生活在永夜森林的精灵王庭中，后全部迁移到了诺德兰的青苍大陆','2013-12-26 16:55:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,'食人魔',1,'身体高大，食谱广泛的类人智慧种族，代表角色：提拉米苏、三分熟','2013-12-26 16:56:11',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `noval_skill` */

DROP TABLE IF EXISTS `noval_skill`;

CREATE TABLE `noval_skill` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_skill` */

insert  into `noval_skill`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (62,'烈焰焚城',NULL,NULL,'人物出生年月',NULL);

/*Table structure for table `noval_world` */

DROP TABLE IF EXISTS `noval_world`;

CREATE TABLE `noval_world` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `NOVAL_ID` bigint(20) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noval_world` */

insert  into `noval_world`(`ID_`,`NAME_`,`NOVAL_ID`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values (63,'诺德兰',1,'李察出生成长的位面，已知最高等级主位面之一，人类主导了一半的陆地，位面战争盛行','',NULL),(64,'法兰',1,'李察最初进入的次级位面，已知位面等级仅次于诺德兰。为李察核心位面，母巢生活在这里','',NULL),(65,'绿森',1,'哥顿遗留三位面之一，生命气息浓郁，位面规则单一。原为龙法师尤娜驻守，李察第二核心位面。','',NULL),(66,'巨龙位面',1,'与法罗位面相连的一个以巨龙主导的位面，邪龙提亚马特、五色龙都属于这个位面','2013-12-30 13:00:26',NULL),(67,'磐石高地',1,'哥顿遗留三个位面中的一个，位面由一块块互相分离的陆地组成，没有特殊产物，位面土著是各种巨人及魔兽','2013-12-30 13:01:56',NULL),(68,'休兰位面',1,'只有一块陆地的位面，陆地之外是没有任何生物存在的海洋。在休兰位面最高的神巢顶端可以看到疑似远古战场的遗迹。据推测，休兰是被收割者摧毁后遗留的大陆形成的','2013-12-30 13:04:29',NULL),(69,'鎏金山谷',1,'哥顿遗留三位面之一，位面能量丰富，富含各种含有魔法能量的矿产','2013-12-30 13:05:27',NULL);

/*Table structure for table `sys_auth` */

DROP TABLE IF EXISTS `sys_auth`;

CREATE TABLE `sys_auth` (
  `ROLE_CODE` varchar(255) default NULL,
  `USER_NAME` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `ID_` bigint(20) NOT NULL auto_increment,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_auth` */

insert  into `sys_auth`(`ROLE_CODE`,`USER_NAME`,`CREATE_TIME`,`ID_`) values ('ROLE_ADMIN','wenxy','2013-12-28 20:35:15',5),('ROLE_ADMIN','admin','2013-12-28 20:42:01',6);

/*Table structure for table `sys_category` */

DROP TABLE IF EXISTS `sys_category`;

CREATE TABLE `sys_category` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  `ORDER_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_category` */

insert  into `sys_category`(`ID_`,`NAME_`,`TITLE_`,`CREATE_TIME`,`STATUS_`,`ORDER_`) values (1,'系统管理','系统相关内容管理模块','2013-12-11 06:01:41',NULL,NULL),(2,'小说','以小说世界为中心创建的程序模型','2013-12-11 09:58:24',NULL,NULL),(3,'笔记','做笔记的小系统','2013-12-11 09:59:28',NULL,NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `ID_` bigint(20) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  `URL_` varchar(255) default NULL,
  `PARENT_ID` bigint(20) default NULL,
  `parentId` bigint(20) default NULL,
  `CREATE_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`ID_`,`NAME_`,`TITLE_`,`CREATE_TIME`,`STATUS_`,`URL_`,`PARENT_ID`,`parentId`,`CREATE_ID`) values (1,'系统管理',NULL,NULL,NULL,NULL,0,NULL,NULL),(2,'小说',NULL,NULL,NULL,NULL,0,NULL,NULL),(3,'笔记',NULL,NULL,NULL,NULL,0,NULL,NULL),(4,'菜单管理','首页菜单管理页面','2013-12-11 06:02:20',NULL,'/menu/list.do',1,NULL,NULL),(5,'人物角色',NULL,'2013-12-11 09:58:47',NULL,'/character/list.do',2,NULL,NULL),(6,'我的笔记',NULL,'2013-12-11 09:59:54',NULL,'/node/list.do',3,NULL,NULL),(7,'装备',NULL,'2013-12-11 11:18:46',NULL,'/equipment/list.do',2,NULL,NULL),(8,'技能','魔法技能、武技技能、精神技能等','2013-12-12 12:44:41',NULL,'/skill/list.do',2,NULL,NULL),(9,'种族','具有典型血脉，能力的族群','',NULL,'/race/list.do',2,NULL,NULL),(10,'位面','一个完整的生物圈，一个星球及其所在的一个恒星系，拥有一个外围保护晶壁','',NULL,'/world/list.do',2,NULL,NULL),(11,'能源','人工，斗气，魔法，化学，神力等等','',NULL,'/energy/list.do',2,NULL,NULL),(12,'家族','以姓氏及血脉为联系而组成的一个整体，主要相关属性为：荣誉、血脉及各种资源',NULL,NULL,'/family/list.do',2,NULL,NULL),(13,'爵位','依据统治领地大小划分而成的领主等级称谓',NULL,NULL,'/peerage/list.do',2,NULL,NULL),(14,'小说信息','存储一本小说相关的信息','2013-12-22 19:58:37',NULL,'/noval/list.do',2,NULL,NULL),(15,'地方','地点、统称','2013-12-22 20:17:26',NULL,'/place/list.do',2,NULL,NULL),(16,'元素','小说元素，如：人物、技能等','2013-12-22 20:59:01',NULL,'/element/list.do',2,NULL,NULL),(17,'用户角色','区分管理者及普通用户的角色','2013-12-27 16:17:11',NULL,'/role/list.do',1,NULL,NULL),(18,'用户管理','网站用户管理','2013-12-27 17:18:07',NULL,'/list.do',1,NULL,NULL),(19,'笔记组','一个组可以对应多个笔记，组内吧笔记不重复。一个笔记可以对应多个组','2013-12-28 13:19:21',NULL,'/nodegroup/list.do',3,NULL,NULL),(20,'笔记组用户权限','个人用户对应的笔记组的访问权限','2013-12-28 13:20:14',NULL,'/nodepurview/list.do',3,NULL,NULL),(21,'登陆跳转首页',NULL,NULL,NULL,'/index.do',2,NULL,NULL);

/*Table structure for table `sys_parameter` */

DROP TABLE IF EXISTS `sys_parameter`;

CREATE TABLE `sys_parameter` (
  `KEY_` varchar(255) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `LONG_VALUE` bigint(20) default NULL,
  `STRING_VALUE` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_parameter` */

insert  into `sys_parameter`(`KEY_`,`NAME_`,`TITLE_`,`LONG_VALUE`,`STRING_VALUE`,`CREATE_TIME`,`STATUS_`) values ('unique_id','系统主键','系统内唯一的长整形主键',2,NULL,'2013-12-27 22:42:03',NULL),('user_unique_id','系统主键','系统内唯一的长整形主键',5,NULL,'2013-12-27 22:51:42',NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ROLE_CODE` varchar(255) NOT NULL default '',
  `NAME_` varchar(255) default NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ROLE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`ROLE_CODE`,`NAME_`,`TITLE_`,`CREATE_TIME`,`STATUS_`) values ('ROLE_ADMIN','普通管理员','全站的访问权限','2013-12-28 20:14:10',NULL),('ROLE_SUPER','系统超级管理员','拥有最高权限','2013-12-27 16:30:25',NULL),('ROLE_USER','普通用户','普通用户，注册后激活即可获得的角色','2013-12-28 13:27:31',NULL);

/*Table structure for table `sys_url_access` */

DROP TABLE IF EXISTS `sys_url_access`;

CREATE TABLE `sys_url_access` (
  `ID_` bigint(20) NOT NULL,
  `URL_ID` bigint(20) default NULL,
  `ROLE_CODE` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `CREATE_ID` bigint(20) default NULL,
  `STATUS_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_url_access` */

insert  into `sys_url_access`(`ID_`,`URL_ID`,`ROLE_CODE`,`CREATE_TIME`,`CREATE_ID`,`STATUS_`) values (1,1,'ROLE_ADMIN','2013-12-29 15:33:55',2,NULL),(2,2,'ROLE_ADMIN','2013-12-29 15:33:57',2,NULL),(3,3,'ROLE_ADMIN','2013-12-29 15:34:00',2,NULL),(4,4,'ROLE_ADMIN','2013-12-29 15:34:02',2,NULL),(5,5,'ROLE_ADMIN','2013-12-29 15:34:05',2,NULL),(6,6,'ROLE_ADMIN','2013-12-29 15:34:07',2,NULL),(7,7,'ROLE_ADMIN','2013-12-29 15:34:09',2,NULL),(8,8,'ROLE_ADMIN','2013-12-29 15:34:13',2,NULL),(9,9,'ROLE_ADMIN','2013-12-29 15:34:15',2,NULL),(10,10,'ROLE_ADMIN','2013-12-29 15:34:17',2,NULL),(11,11,'ROLE_ADMIN','2013-12-29 15:34:20',2,NULL),(12,12,'ROLE_ADMIN','2013-12-29 15:34:22',2,NULL),(13,13,'ROLE_ADMIN','2013-12-29 15:34:25',2,NULL),(14,14,'ROLE_ADMIN','2013-12-29 15:34:28',2,NULL),(15,15,'ROLE_ADMIN','2013-12-29 15:34:31',2,NULL),(16,16,'ROLE_ADMIN','2013-12-29 15:34:33',2,NULL),(17,17,'ROLE_ADMIN','2013-12-29 15:34:36',2,NULL),(18,18,'ROLE_ADMIN','2013-12-29 15:34:39',2,NULL),(19,19,'ROLE_ADMIN','2013-12-29 21:25:43',2,NULL),(20,20,'ROLE_ADMIN','2013-12-29 21:25:46',2,NULL),(21,21,'ROLE_ADMIN','2013-12-29 21:25:48',2,NULL);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `USER_NAME` varchar(255) NOT NULL,
  `TITLE_` varchar(255) default NULL,
  `CREATE_TIME` varchar(255) default NULL,
  `STATUS_` int(11) default NULL,
  `PASS_WORD` varchar(255) default NULL,
  `ID_` bigint(20) default NULL,
  PRIMARY KEY  (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`USER_NAME`,`TITLE_`,`CREATE_TIME`,`STATUS_`,`PASS_WORD`,`ID_`) values ('admin','管理员用户，密码为123','2013-12-27 22:51:42',NULL,'b594510740d2ac4261c1b2fe87850d08',1),('gang',NULL,'2013-12-27 22:59:36',NULL,'d42738a93e327042625519319a236529',4),('wenxy','管理员及普通用户','2013-12-27 22:51:42',NULL,'591675b4e7a3c9ea37117290e27d1c94',2),('xue',NULL,'2013-12-27 22:57:44',NULL,'8e7d685ba09531e9c53b80fbd04357ca',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
