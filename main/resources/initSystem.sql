/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : ch-scma

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-07-03 14:52:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_employee`
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee` (
  `EmpCode` varchar(20) NOT NULL COMMENT '员工代码',
  `EmpName` varchar(60) DEFAULT NULL COMMENT '员工名称',
  `EmpRank` varchar(10) DEFAULT NULL,
  `ClassName` varchar(10) DEFAULT NULL,
  `StationName` varchar(16) DEFAULT NULL COMMENT '群组',
  `QuitDate` datetime DEFAULT NULL COMMENT '离职时间',
  `EmpPass` varchar(32) DEFAULT NULL COMMENT '密码',
  `EmpBc` varchar(16) DEFAULT NULL,
  `SystemType` varchar(10) DEFAULT NULL COMMENT '系统类型 MES WMS 或者为空值，代表通用',
  `OrganizationCode` varchar(10) DEFAULT NULL COMMENT '机构代码',
  `DepartmentCode` varchar(25) DEFAULT NULL COMMENT '部门代码',
  `EmpEName` varchar(80) DEFAULT NULL COMMENT '员工英文名称',
  `EmpType` varchar(10) DEFAULT NULL COMMENT '员工分类(0：内部 1：外部),\n',
  `Email` varchar(20) DEFAULT NULL COMMENT 'EMAIL',
  `Phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `Photo` blob COMMENT '肖像图',
  `CustCode` varchar(50) DEFAULT NULL COMMENT '所属客户',
  `SupplierCode` varchar(50) DEFAULT NULL COMMENT '所属供应商',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`EmpCode`),
  UNIQUE KEY `EmpCode` (`EmpCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息';

-- ----------------------------
-- Records of sys_employee
-- ----------------------------
INSERT INTO `sys_employee` VALUES ('1', 'admin', '0', '0', '1', null, 'e10adc3949ba59abbe56e057f20f883e', '0', 'MES', '1', '1', 'admin', '0', 'admin@changhong.com', '13688888888', 0x31, '1', '1', null);

-- ----------------------------
-- Table structure for `sys_employeeorganization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_employeeorganization`;
CREATE TABLE `sys_employeeorganization` (
  `Id` varchar(36) NOT NULL COMMENT '主键',
  `EmpCode` varchar(20) DEFAULT NULL COMMENT '员工代码',
  `OrganizationCode` varchar(20) DEFAULT NULL COMMENT '机构代码',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  UNIQUE KEY `EmpCode` (`EmpCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门';

-- ----------------------------
-- Records of sys_employeeorganization
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_employeerole`
-- ----------------------------
DROP TABLE IF EXISTS `sys_employeerole`;
CREATE TABLE `sys_employeerole` (
  `Id` varchar(36) NOT NULL COMMENT '权限关系ID',
  `RoleId` varchar(36) DEFAULT NULL COMMENT '角色ID',
  `EmpCode` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_employeerole
-- ----------------------------
INSERT INTO `sys_employeerole` VALUES ('8236468C-A105-4661-98B2-82F4607D8E94', 'A0E3B952-FF9E-4563-997D-C52C61145459', '1', '1');

-- ----------------------------
-- Table structure for `sys_model`
-- ----------------------------
DROP TABLE IF EXISTS `sys_model`;
CREATE TABLE `sys_model` (
  `Id` varchar(36) NOT NULL COMMENT '主键',
  `ModelName` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `SystemId` tinyint(4) DEFAULT NULL COMMENT '系统编号',
  `ParentId` varchar(36) DEFAULT NULL COMMENT '父m模块ID',
  `OrderNo` int(11) DEFAULT NULL COMMENT '排序',
  `Grade` int(11) DEFAULT NULL COMMENT '菜单级别(0-模块 1-功能 2-按钮)',
  `DllFName` varchar(250) DEFAULT NULL COMMENT '模块对应的DLL文件名，不写扩展名',
  `ClassName` varchar(250) DEFAULT NULL COMMENT '模块类名，格式：命名空间.类名',
  `ModelType` varchar(2) DEFAULT NULL COMMENT '类型：0普通，1，报表，2，通用窗体',
  `ModelParameter` varchar(200) DEFAULT NULL COMMENT '参数值:当类型为1，或2时为必填项',
  `IsShow` bit(1) DEFAULT NULL COMMENT '是否显示在树目录',
  `SytemType` varchar(10) DEFAULT NULL COMMENT '系统类型(MES/WMS)',
  `TransFlag` varchar(10) DEFAULT NULL,
  `MenuCode` varchar(250) DEFAULT NULL COMMENT '菜单代码',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  UNIQUE KEY `ModelName` (`ModelName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能模块';

-- ----------------------------
-- Records of sys_model
-- ----------------------------
INSERT INTO `sys_model` VALUES ('79824BC6-48E9-4AC9-BF08-62C9B28CD7CF', '菜单管理', '1', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '1', '0', 'module', '/module', '0', '1', '', 'MES', '0', 'module', '1');
INSERT INTO `sys_model` VALUES ('D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '系统设置', '1', 'C0C50F39-DAF8-4696-B611-3084FAE1EFEC', '1', '0', 'system_settings', '', '0', '1', '', 'MES', '0', 'SystemSetting', '1');

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `OrganizationCode` varchar(20) NOT NULL COMMENT '机构代码',
  `RegionCode` varchar(20) DEFAULT NULL COMMENT '区域代码',
  `OrganizationName` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `Father` varchar(20) DEFAULT NULL COMMENT '隶属机构代码',
  `Address` varchar(100) DEFAULT NULL COMMENT '地址',
  `Phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `Remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `Path` varchar(500) DEFAULT NULL COMMENT '机构路径',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`OrganizationCode`),
  UNIQUE KEY `OrganizationCode` (`OrganizationCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织结构';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter` (
  `Id` varchar(36) NOT NULL,
  `BigClass` varchar(32) DEFAULT NULL COMMENT '大分类',
  `SmallClass` varchar(32) DEFAULT NULL COMMENT '小分类',
  `ParameterDesc` varchar(200) DEFAULT NULL COMMENT '描述',
  `ViewType` varchar(32) DEFAULT NULL COMMENT '名称/显示方式（0复选框；1输入框；2下拉列表）',
  `ParameterValue` varchar(128) DEFAULT NULL COMMENT '值',
  `ValueDesc` varchar(256) DEFAULT NULL COMMENT '排序字段/下拉列表选择项（用‘|’分隔）',
  `DefaultValue` varchar(128) DEFAULT NULL COMMENT '默认值',
  `Sequence` int(11) DEFAULT NULL COMMENT '排序号',
  `Type` varchar(32) DEFAULT NULL COMMENT '(0：系统级 1 ：辅料制具 2 ：smt 3：pth 4：成品仓  5 ：原料仓);\n',
  `VierFlag` char(1) DEFAULT NULL COMMENT '显示标志Y 显示在界面上 N 不显示在界面上',
  `ModifyUser` varchar(50) DEFAULT NULL COMMENT '修改人',
  `ModifyDate` date DEFAULT NULL COMMENT '修改时间',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数';

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------
INSERT INTO `sys_parameter` VALUES ('648C2A13-7D95-4B4C-8BDD-26BC3A691427', 'A', 'a', '是否显示', 'isshow', '1', '显示', '0', '2', '0', '1', 'admin', null, '1');
INSERT INTO `sys_parameter` VALUES ('DD8B80E0-C23A-4A70-8098-721867878D6E', 'A', 'a', '是否显示', 'isshow', '0', '不显示', '0', '1', '0', '1', 'admin', null, '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `Id` varchar(36) NOT NULL COMMENT '角色ID(主键)',
  `RoleName` varchar(50) DEFAULT NULL COMMENT '角色名',
  `RoleDesc` varchar(200) DEFAULT NULL COMMENT '描述',
  `Remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `IsManager` bit(1) DEFAULT NULL COMMENT '是否系统管理员0:假，1为真',
  `SystemType` varchar(10) DEFAULT NULL COMMENT '系统类型(MES/WMS)',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('A0E3B952-FF9E-4563-997D-C52C61145459', '管理员', '1', '管理员', '', 'MES', '1');

-- ----------------------------
-- Table structure for `sys_rolemodel`
-- ----------------------------
DROP TABLE IF EXISTS `sys_rolemodel`;
CREATE TABLE `sys_rolemodel` (
  `Id` varchar(36) NOT NULL COMMENT '主键',
  `RoleId` varchar(36) DEFAULT NULL COMMENT '角色ID',
  `ModelId` varchar(36) DEFAULT NULL COMMENT '模块ID',
  `CompanyCode` varchar(5) DEFAULT NULL COMMENT '公司别',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  KEY `RoleId` (`RoleId`),
  CONSTRAINT `sys_rolemodel_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `sys_role` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';

-- ----------------------------
-- Records of sys_rolemodel
-- ----------------------------
INSERT INTO `sys_rolemodel` VALUES ('6D1275D6-E21B-4C50-86CA-401289391613', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '1');
INSERT INTO `sys_rolemodel` VALUES ('DA9EB1C2-7B7F-4F15-9E94-32BDDE40EE63', 'A0E3B952-FF9E-4563-997D-C52C61145459', '79824BC6-48E9-4AC9-BF08-62C9B28CD7CF', '1');
