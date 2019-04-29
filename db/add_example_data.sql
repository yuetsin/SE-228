DELETE FROM book_library;
DELETE FROM book_type;
DELETE FROM s_role_permission;
DELETE FROM s_user_role;
DELETE FROM s_user;
DELETE FROM s_role;
DELETE FROM s_permission;

INSERT INTO book_type
VALUES ('外文小说');

INSERT INTO book_type
VALUES ('中文小说');

INSERT INTO book_type
VALUES ('散文');

INSERT INTO book_library
VALUES ('质数的孤独', 'Paolo Giordano', '外文小说', '马蒂亚是一个年轻的数学天才，他相信自己是质数中的一个，而中学同学爱丽丝正是他的孪生质数。他们都有痛苦的过往，同样孤独，同样无法拉近和其他人之间的距离。从少年到成年，他们的生命不断交叉，努力消除存在于彼此间障碍，相互影响又彼此分离，就像孪生质数，彼此相近却永远无法靠近。', 2935
	, 'primeNumber.png', '9780670021482', 16.00, 15.00);

INSERT INTO book_library
VALUES ('追风筝的人', 'Khaled Hosseini', '外文小说', '「许多年过去了。人们说陈年旧事可以被埋葬，然而我终于明白这是错的，因为往事会自行爬上来。回首前尘，我意识到在过去二十六年里，自己始终在窥视着那荒芜的小径。」', 69655
	, 'theKiteRunner.png', '978720806164401', 39.00, 36.00);

INSERT INTO book_library
VALUES ('十三个理由', 'Jay Asher', '外文小说', '中学生汉娜自杀后，留下十三盒磁带。分别寄给十三个同学，在录音中，她一一追诉往事，发出质询……随着一盒又一盒磁带的揭示，在悬念丛生中，反思了中学阶段时人文教育的缺失，发人深思。', 26743
	, 'thirteenReasonsWhy.png', '9789877471908', 30.00, 29.00);

INSERT INTO book_library
VALUES ('房思琪的初戀樂園', '林奕含', '中文小说', '人喜欢的就是文饰过非。明明在做一件错事，却还是喜欢用漂亮的话来美化、掩盖丑恶。当真相被揭开，人性的劣根性彻底暴露，所有被谎言堆砌的美好轰然倒塌，那些在谎言里活着的人才会彻悟。', 50500
	, 'fangSiQi.png', '9787559614636', 19.00, 19.00);

INSERT INTO book_library
VALUES ('无人生还', 'Agatha Christie', '外文小说', '开创童谣杀人和孤岛模式…古典推理的不朽名作。', 3928
	, 'andThereWereNone.jpg', '9787513312929', 28.00, 28.00);

INSERT INTO book_library
VALUES ('我与地坛', '史铁生', '散文', '「母亲喜欢花，可自从我的腿瘫痪后，她侍弄的那些花都死了。」', 29421
	, 'woYuDiTan.png', '9787020083442', 33.00, 33.00);

INSERT INTO book_library
VALUES ('局外人', 'Albert Camus', '外文小说', '由于人和世界的分离，世界对于人来说是荒诞的、毫无意义的，而人对荒诞的世界无能为力，因此不抱任何希望，对一切事物都无动于衷。', 10049
	, 'juWaiRen.png', '9781508560449', 28.00, 28.00);

INSERT INTO book_library
VALUES ('欢愉', 'Albert Camus', '外文小说', '在对他人生活的审视中，我们发现了自己；性感而优雅，理性又激情，拷问文明和野蛮的界线，探寻知性与欲望的守衡。', 16199
	, 'Euphoria.png', '9787540479084', 38.00, 17.98);

-- ----------------------------  
-- Records of s_user  
-- ----------------------------  
INSERT INTO `s_user` VALUES (1, 'admin', 'admin');  
INSERT INTO `s_user` VALUES (2, 'veiking', 'veiking');  
INSERT INTO `s_user` VALUES (3, 'xiaoming', 'xiaoming');   
  
-- ----------------------------  
-- Records of s_role  
-- ----------------------------  
INSERT INTO `s_role` VALUES (1, 'R_ADMIN');  
INSERT INTO `s_role` VALUES (2, 'R_USER');  
  
-- ----------------------------  
-- Records of s_permission  
-- ----------------------------  
INSERT INTO `s_permission` VALUES (1, 'P_ME', '/me');  
INSERT INTO `s_permission` VALUES (2, 'P_CONSOLE', '/console');

-- ----------------------------  
-- Records of s_user_role  
-- ----------------------------  
INSERT INTO `s_user_role` VALUES (1, 1);  
INSERT INTO `s_user_role` VALUES (2, 2);  

-- ----------------------------  
-- Records of s_role_permission  
-- ----------------------------  
INSERT INTO `s_role_permission` VALUES (1, 1);
INSERT INTO `s_role_permission` VALUES (1, 2);
INSERT INTO `s_role_permission` VALUES (2, 1);
  
