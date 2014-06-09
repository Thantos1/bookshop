
DROP TABLE IF EXISTS `ORDERS`;
DROP TABLE IF EXISTS `LEAVEWORD`;
DROP TABLE IF EXISTS `CART_MERCHANDISE`;
DROP TABLE IF EXISTS `CART`;
DROP TABLE IF EXISTS `MERCHANDISE`;
DROP TABLE IF EXISTS `CATEGORY`;
DROP TABLE IF EXISTS `MEMBER`;
DROP TABLE IF EXISTS `MEMBERLEVEL`;
DROP TABLE IF EXISTS `ADMINS`;

COMMIT;

CREATE TABLE `ADMINS` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `ADMINTYPE` int(4) DEFAULT NULL,
  `NAME` char(12) DEFAULT NULL,
  `USERNAME` char(12) NOT NULL UNIQUE,
  `PASSWORD` char(12) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;



CREATE TABLE `MEMBERLEVEL` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `LEVELNAME` char(20) NOT NULL UNIQUE,
  `FAVOURABLE` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;



CREATE TABLE `MEMBER` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `MEMBERLEVEL_ID` int(4) NOT NULL,
  `USERNAME` char(12) NOT NULL UNIQUE,
  `PASSWORD` char(12) NOT NULL,
  `NAME` char(20) DEFAULT NULL,
  `PHONE` char(15) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `ZIPCODE` char(10) DEFAULT NULL,
  `REGISTERDATE` datetime DEFAULT NULL,
  `LASTLOGINDATE` datetime DEFAULT NULL,
  `LOGINTIMES` int(4) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (MEMBERLEVEL_ID) REFERENCES MEMBERLEVEL(ID)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gb2312;



CREATE TABLE `CATEGORY` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `CATENAME` char(40) NOT NULL UNIQUE,
  `CATEDESC` text DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;



CREATE TABLE `MERCHANDISE` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` int(4) NOT NULL,
  `MERNAME` char(40) NOT NULL,
  `PRICE` decimal(8,2) DEFAULT NULL,
  `SPRICE` decimal(8,2) DEFAULT NULL,
  `MERMODEL` char(40) DEFAULT NULL,
  `PICTURE` varchar(100) DEFAULT NULL,
  `MERDESC` text DEFAULT NULL,
  `MANUFACTURER` char(60) DEFAULT NULL,
  `LEAVEFACTORYDATE` datetime DEFAULT NULL,
  `SPECIAL` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gb2312;



CREATE TABLE `CART` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `MEMBER_ID` int(4) NOT NULL,
  `MONEY` decimal(9,2) DEFAULT NULL,
  `CARTSTATUS` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(ID)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;



CREATE TABLE `CART_MERCHANDISE` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `CART_ID` int(4) NOT NULL,
  `MERCHANDISE_ID` int(4) NOT NULL,
  `MERNUMBER` int(4) NOT NULL DEFAULT '1',
  `PRICE` decimal(8,2) NOT NULL DEFAULT '0.00',
  `MONEY` decimal(9,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`ID`),
  FOREIGN KEY (CART_ID) REFERENCES CART(ID),
  FOREIGN KEY (MERCHANDISE_ID) REFERENCES MERCHANDISE(ID)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gb2312;



CREATE TABLE `LEAVEWORD` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `MEMBER_ID` int(4) NOT NULL,
  `ADMIN_ID` int(4) DEFAULT NULL,
  `TITLE` char(60) DEFAULT NULL,
  `CONTENT` text NOT NULL,
  `LEAVEDATE` datetime DEFAULT NULL,
  `ANSWERCONTENT` text DEFAULT NULL,
  `ANSWERDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(ID),
  FOREIGN KEY (ADMIN_ID) REFERENCES ADMINS(ID)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;



CREATE TABLE `ORDERS` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `MEMBER_ID` int(4) NOT NULL,
  `CART_ID` int(4) NOT NULL,
  `ORDERNO` char(20) NOT NULL,
  `ORDERDATE` datetime DEFAULT NULL,
  `ORDERSTATUS` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(ID),
  FOREIGN KEY (CART_ID) REFERENCES CART(ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312;


INSERT INTO `ADMINS` VALUES ('1', '1', '商品管理员', 'Admin1', 'Admin1'), ('2', '2', '订单管理员', 'Admin2', 'Admin2'), ('3', '3', '会员管理员', 'Admin3', 'Admin3'), ('4', '4', '系统管理员', 'Admin4', 'Admin4');

INSERT INTO `MEMBERLEVEL` VALUES ('1', '普通会员', '95'), ('2', '黄金会员', '90'), ('3', '白金会员', '85'), ('4', '钻石会员', '80');

INSERT INTO `MEMBER` VALUES ('1', '4', 'liuqiao', 'liuqiao', '刘桥', '13971559323', '湖北省武汉市洪山区', '432200', '2009-04-20 18:40:30', '2009-04-23 17:40:08', '7', 'liuqiao1982@sina.com');
INSERT INTO `MEMBER` VALUES ('2', '1', 'jinwu', 'jinwu', '金武', '13456754323', '广东省深圳市', '435567', '2009-04-20 21:05:58', '2009-04-21 15:33:41', '2', 'jinwu@sina.com');
INSERT INTO `MEMBER` VALUES ('3', '1', 'hujie', 'hujie', '胡杰', '13456789067', '武汉市洪山区鲁磨路', '432254', '2009-04-20 21:17:08', '2009-04-21 15:32:36', '3', 'hujie@sohu.com');
INSERT INTO `MEMBER` VALUES ('8', '3', 'chenlin', 'chenlin', '陈林', '13456756789', '湖北省武汉市新洲区', '432543', '2009-04-20 22:44:30', '2009-04-21 15:33:08', '2', 'chenlin@sohu.com');
INSERT INTO `MEMBER` VALUES ('9', '1', 'ggg', 'ggg', '该干', '66771111111', 'ggg', 'ggg', '2009-04-24 15:44:50', '2009-04-24 15:44:50', '0', 'gg@ff.cn');
INSERT INTO `MEMBER` VALUES ('10', '1', 'ggg1', '111', '该干', '66771111111', 'ggg', 'ggg', '2009-04-24 16:11:16', '2009-04-24 16:11:16', '0', 'gg@ff.cn');

INSERT INTO `CATEGORY` VALUES ('1', '计算机类', '计算机相关的各类书籍'), ('2', '管理类', '管理相关的各类书籍'), ('3', '英语类', '英语相关的各类书籍'), ('4', '小说类', '各类小说');

INSERT INTO `MERCHANDISE` VALUES ('1', '1', 'JavaScript 高级程序设计', '45.00', '34.00', 'c1', 'Picture/9211839-1_b.jpg', 'JavaScript是目前Web客户端开发的主要编程语言，也是Ajax的核心技术之一', '人民邮电出版社', '2009-04-21 00:00:00', '1');

INSERT INTO MERCHANDISE VALUES('2', '1', 'Ajax实战', '65.00', '0.00', 'c2', 'Picture/9161326-1_b.jpg', '本书是目前Ajax领域最为全面深入的一本著作，其中不仅有对于基础知识的介绍，还有对于Ajax开发中重大的体系架构问题的深入探讨。', '人民邮电出版社', '2009-04-21 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('3', '1', 'CSS基础教程', '54.00', '43.00', 'c3', 'Picture/9211839-1_b.jpg', '本书是优秀的CSS 入门书，重点讲述了如何使用CSS 实现基于Web 标准的网站开发，实现网站内容和表现相分离。\r\n　　本书包括两部分。第一部分介绍了CSS 的基础知识和基本概念，再利用CSS 分别对网页创建中的一些基本元素加上样式，包括：文本、图像、列表、链接、表格、表单等。第二部分主要讨论了基于CSS 来实现网页基本布局的相关概念与技术，包括浮动、流体布局等。本书最后还给出了一个真实的案例，将本书的所有内容进行了综合讨论。', '机械工业出版社', '2002-04-09 00:00:00', '1');

INSERT INTO MERCHANDISE VALUES('4', '1', '精通CSS：高级Web标准解决方案', '44.00', '33.00', 'c3', 'Picture/9221944-1_b.jpg', '本书将最有用的CSS技术汇总在一起，在介绍基本的CSS概念和最佳实践之后，讨论了核心的CSS技术，例如图像、链接、列表操纵、表单设计、数据表格设计以及纯CSS布局。', '机械工业出版社', '2004-04-15 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('5', '1', '无懈可击的Web设计:利用XHTML和CSS提高网站的灵活性与适应性', '50.00', '0.00', 'c5', 'Picture/9232841-1_b.jpg', '有大量的传统table布局和现在的div布局进行比较。', '科学出版社', '2009-04-15 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('6', '3', '新编考研英语读真题记单词（2010年版）', '65.00', '0.00', 'e1', 'Picture/20511678-1_b.jpg', '本书对历年真题选项中的疑难词语也进行了逐一分析，并且根据大纲要求进行了注解，旨在保障考生在考试时不要输在题干和选项上，这就为考生彻底扫清了考试中的障碍词语。', '机械工业出版社', '2000-05-10 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('7', '3', '奥巴马演说集', '65.00', '54.00', 'e2', 'Picture/20525298-1_b.jpg', '本书中共收录奥巴马激情演说十四篇（包含就职演说），每篇演说后都附有详尽的背景注释与难词解析，是了解美国历史与现状、了解奥巴马心路历程的绝佳资料，也是学习英文及演讲技巧的最好范例', '科学出版社', '2006-04-12 00:00:00', '1');

INSERT INTO MERCHANDISE VALUES('8', '4', '藏地密码6', '45.00', '33.00', 'n1', 'Picture/20516672-1_b.jpg', '从来没有一本小说，能像《藏地密码》这样，奇迹般地赢得专家、学者、名人、书店、媒体、全球最知名的出版机构以及成千上万普通读者的狂热追捧，《藏地密码》是当下中国数千万“西藏迷”了解西藏的首选读本，也是当下最畅销的华语小说，目前销量已达到惊人的200多万册。', '科学出版社', '2006-04-12 00:00:00', '1');

INSERT INTO MERCHANDISE VALUES('9', '4', '身份的证明', '55.00', '0.00', 'n2', 'Picture/20373098-1_b.jpg', '本书超越了一般意义的反特故事，它将跨度拉长到近半个世纪，把5个主要人物的命运由每个历史时期的案件串联起来，故事的终极意义在于通过人物过去的付出和现时的遭遇，再现国家发展过程中那些默默无闻的幕后英雄精神不屈的本质。全书讲述的是一个我党地下情报人员跨越47年的人生经历，书中通过几个特殊历史时期的变迁，用全新的视角再现了一个隐蔽战线上“用特殊材料制成的”共产党人，并通过一个人身份的证明，折射了共和国从新生走向成熟的历史光影。', '文艺出版社', '2009-04-08 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('10', '2', '巴菲特教你读财报', '75.00', '0.00', 'm4', 'Picture/20522936-1_b.jpg', '金融传奇天才巴菲特的前儿媳玛丽?巴菲特与研究巴菲特法则的权威人士戴维?克拉克探秘沃伦?巴菲特的思想精髓，在本书中深入浅出地介绍巴菲特分析公司财务报表的黄金法则。菲特对哪类股价便宜的公司避而远之。', '文艺出版社', '2002-03-20 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('11', '4', '货币战争', '56.00', '45.00', 'm9', 'Picture/9304959-1_b.jpg', '为什么你不知道美联储是私有的中央银行？诺曼底登陆一线部队的伤亡率？人类财富的阴谋史。', '清华大学出版社', '2005-03-16 00:00:00', '1');

INSERT INTO MERCHANDISE VALUES('12', '4', '贫民窟的百万富翁', '66.00', '45.00', 'n6', 'Picture/20525341-1_b.jpg', '十八岁的酒吧服务员罗摩，生活在孟买的贫民窟里。', '文艺出版社', '2009-04-09 00:00:00', '1');

INSERT INTO MERCHANDISE VALUES('13', '1', '士兵突击', '36.00', '0.00', 'n5', 'Picture/9255064-1_b.jpg', '这真的不是一部小说，它是哲学、是人生，是我们成长的历史。每一位读者都能在许三多身上找到自己的一些影子。', '文艺出版社', '2009-04-09 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('14', '2', '领导力是什么', '66.00', '0.00', 'm5', 'Picture/9250656-1_b.jpg', '《领导力是什么》汇集了众多新文章，集中讨论如何领导变革、如何处理复杂局面和不确定因素、如何建立学习型组织、如何促进创新以及在当今国际性组织中管理知识型员工必备的新技能等问题。新的领导方法，为促进下一代领导力的发展提供了新的技巧，为危机管理提供了新的领导方法，也为打造在竞争中获胜的灵活组织提供了新的路径。', '文艺出版社', '2009-04-09 00:00:00', '0');

INSERT INTO MERCHANDISE VALUES('15', '2', '管理是什么', '56.00', '50.00', 'm8', 'Picture/8768781-1_b.jpg', '本书是过去１０年管理思想革新的简洁实用的总结。书中对各种新的管理理念做了详尽的介绍，并对如何将之运用到复杂的日常组织中进行了阐述。书中建议管理者从旧有的控制性思维中转变到弹性的、重视网络关系、充满活力并能持续学习的思维中。新时代的竞争形势对管理者提出了新的挑战，企业的学习潜力、反应速度和适应能力取代了传统的资产规模、市场份额和资产收益率指标。以客户为中心不再只是营销部门的主导思想，它现在主宰整个企业。本书不是要你抛弃传统的管理实践，它要做的是带领你在纷纭的商业环境中寻找最有效的管理思想，以实现企业绩效的重大突破。', '商务出版社', '2009-01-14 00:00:00', '1');

INSERT INTO `LEAVEWORD` VALUES ('1', '2', null, '非常感谢', '非常感谢，很方便！', '2009-04-20 21:14:44', null, null), ('2', '2', null, '不错', '不错的电子商务网站！', '2009-04-20 21:15:40', null, null), ('3', '3', null, '支持', '支持电子商务！', '2009-04-20 22:38:58', null, null);

COMMIT;