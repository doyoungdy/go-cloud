CREATE SCHEMA `going-aas` DEFAULT CHARACTER SET utf8 ;


-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('app', null, 'app', 'app', 'password,refresh_token', null, null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('webApp', null, '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', 'app', 'authorization_code,password,refresh_token,client_credentials', null, null, null, null, null, null);
