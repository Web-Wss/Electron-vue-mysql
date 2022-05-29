/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.27 : Database - second_hand_campus_platform_mysql
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`second_hand_campus_platform_mysql` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `sort_id` int NOT NULL COMMENT '分类id',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `goods_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品价格',
  `goods_describe` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品描述',
  `goods_time` date NOT NULL COMMENT '商品发布时间',
  `user_id` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`sort_id`,`goods_name`,`goods_price`,`goods_describe`,`goods_time`,`user_id`) values (1,1,'女装test','50','这是一件女装','2022-05-16',2),(2,8,'篮球','20','篮球','2022-05-16',2),(3,4,'Mac笔记本','3000','Mac笔记本','2022-05-16',2),(4,6,'手表','200','手表','2022-05-16',1),(5,7,'JavaScript','50','JavaScript','2022-05-16',1),(6,5,'可乐','2','可乐','2022-05-16',1),(7,3,'美妆蛋','30','但是佛海送红色的公司的公司爱丁堡哦啊发布的技术大版i尽快部署多个i波斯的归属地股市大幅啊的发表idbf地方把快递放假表单i哦亲俄边防打不过u深度不够你是打工i给你我欸额分为偶分帮我而非那边为根本哦文档哦i五年广东i温暖的i欧文iu 的方法问单轨定位','2022-05-16',1),(8,2,'男装','100','男装','2022-05-16',1),(9,3,'水乳','100','水乳','2022-05-19',2);

/*Table structure for table `goods_img` */

DROP TABLE IF EXISTS `goods_img`;

CREATE TABLE `goods_img` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NOT NULL,
  `goods_img` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `goods_img_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=546 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods_img` */

insert  into `goods_img`(`id`,`goods_id`,`goods_img`) values (363,1,'http://localhost:8080/electron_vue_server/statics/imgs/46ea5aa0-07e1-43ba-97f9-a0900844f2c1.webp'),(364,1,'http://localhost:8080/electron_vue_server/statics/imgs/59ebfda4-1d4a-428a-8402-a51e51e1ea2a.webp'),(365,2,'http://localhost:8080/electron_vue_server/statics/imgs/c44032ec-78ea-487f-9696-f16eee906d22.webp'),(366,2,'http://localhost:8080/electron_vue_server/statics/imgs/7165939f-e192-427f-8b61-f79ecf3789bd.webp'),(367,3,'http://localhost:8080/electron_vue_server/statics/imgs/ec564f42-5d7b-4887-b202-9fb9494dceeb.webp'),(368,3,'http://localhost:8080/electron_vue_server/statics/imgs/52c1e739-8a49-48da-9f4e-fcb43814cafd.webp'),(369,4,'http://localhost:8080/electron_vue_server/statics/imgs/b8b23864-665f-4548-ab56-ab80731e215a.webp'),(370,4,'http://localhost:8080/electron_vue_server/statics/imgs/ca86ece9-1549-46d8-bba1-7aa49b0353b7.webp'),(371,5,'http://localhost:8080/electron_vue_server/statics/imgs/4763f752-915d-46d8-acac-c95e3efd6b06.webp'),(372,5,'http://localhost:8080/electron_vue_server/statics/imgs/b9adf05a-68eb-4b4a-aee5-00b379a76a83.jpg'),(373,6,'http://localhost:8080/electron_vue_server/statics/imgs/0668b8f8-8631-41ea-b0fd-80f0a89c8045.jpg'),(374,6,'http://localhost:8080/electron_vue_server/statics/imgs/f1b5aa6e-6f58-4c82-b5d9-ea804d6183f2.jpg'),(375,7,'http://localhost:8080/electron_vue_server/statics/imgs/2731fdb1-a8ed-4bb6-abc7-802f0dbb5f92.webp'),(376,7,'http://localhost:8080/electron_vue_server/statics/imgs/6b6089ed-5d35-44e6-8380-99f082d37510.webp'),(377,8,'http://localhost:8080/electron_vue_server/statics/imgs/0279339e-1c57-442c-a600-0892b7b5199d.webp'),(378,8,'http://localhost:8080/electron_vue_server/statics/imgs/00616bda-1423-41b1-942c-b846bbed65d4.webp'),(525,9,'http://localhost:8080/electron_vue_server/statics/imgs/00616bda-1423-41b1-942c-b846bbed65d4.webp');

/*Table structure for table `goods_love` */

DROP TABLE IF EXISTS `goods_love`;

CREATE TABLE `goods_love` (
  `love_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `goods_img` varchar(255) NOT NULL COMMENT '商品图片',
  `goods_price` varchar(255) NOT NULL COMMENT '商品价格',
  `user_id` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`love_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods_love` */

insert  into `goods_love`(`love_id`,`goods_id`,`goods_name`,`goods_img`,`goods_price`,`user_id`) values (2,2,'篮球','http://localhost:8080/Interfaces/statics/imgs/c44032ec-78ea-487f-9696-f16eee906d22.webp','20',1),(7,4,'手表','http://localhost:8080/Interfaces/statics/imgs/b8b23864-665f-4548-ab56-ab80731e215a.webp','200',1),(8,6,'可乐','http://localhost:8080/Interfaces/statics/imgs/0668b8f8-8631-41ea-b0fd-80f0a89c8045.jpg','2',1),(9,7,'美妆蛋','http://localhost:8080/Interfaces/statics/imgs/2731fdb1-a8ed-4bb6-abc7-802f0dbb5f92.webp','30',1);

/*Table structure for table `goods_sort` */

DROP TABLE IF EXISTS `goods_sort`;

CREATE TABLE `goods_sort` (
  `sort_id` int NOT NULL COMMENT '商品分类id',
  `sort_name` varchar(128) NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`sort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods_sort` */

insert  into `goods_sort`(`sort_id`,`sort_name`) values (1,'女生服饰'),(2,'男生服饰'),(3,'美妆护肤'),(4,'手机数码'),(5,'食品饮料'),(6,'珠宝饰品'),(7,'图书文具'),(8,'体育用具');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户自增id',
  `user_username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `user_password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `user_nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户手机',
  `user_qq` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户qq',
  `user_wx` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户微信',
  `user_img` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户头像地址',
  `user_status` int NOT NULL DEFAULT '1' COMMENT '用户状态，0禁用，1启用',
  `user_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户收货地址',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_username` (`user_username`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_username`,`user_password`,`user_nickname`,`user_phone`,`user_qq`,`user_wx`,`user_img`,`user_status`,`user_address`) values (1,'21121301037','123456','前端菜鸟','13359074748','1306822660','13359074748','https://img2.baidu.com/it/u=2234869807,755593456&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,'7栋407'),(2,'21121301031','123456','我是昵称123','18888888888','17008888','13359074748','https://img0.baidu.com/it/u=4212151755,1031151310&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,'7栋407'),(3,'21121301001','123456','我是001','123','123','123','https://img2.baidu.com/it/u=2209097056,2254936076&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,'7#407'),(31,'77','77','词穷了。。。','77','77',NULL,'https://img0.baidu.com/it/u=4274595430,3160335357&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',0,NULL),(35,'1234','1234','中文真好','1234','1234',NULL,'https://img0.baidu.com/it/u=1068176978,1604468940&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,NULL),(36,'12345','12345','你在说什么','12345','12345',NULL,'https://img0.baidu.com/it/u=4274595430,3160335357&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,NULL),(38,'1234567','1234567','巴拉巴拉','1234567','1234567',NULL,'https://img1.baidu.com/it/u=3847764214,2704084831&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',0,NULL),(39,'12345678','12345678','原来如此','12345678','12345678',NULL,'https://img0.baidu.com/it/u=1068176978,1604468940&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,NULL),(40,'123456789','123456789','在说啥呢？','123456789','123456789',NULL,'https://img0.baidu.com/it/u=4274595430,3160335357&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,NULL),(41,'1234567890','123456789','我是昵称哈哈','123456789','123456789',NULL,'https://img1.baidu.com/it/u=3847764214,2704084831&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',0,NULL),(46,'52454','234','我是昵称2','52','235',NULL,'https://img2.baidu.com/it/u=1743155949,3677764314&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=499',1,NULL),(47,'234','123456','修改后昵称123','324','235',NULL,'https://img2.baidu.com/it/u=1743155949,3677764314&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=499',0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
