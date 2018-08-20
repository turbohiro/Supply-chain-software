/*
Navicat MySQL Data Transfer

Source Server         : turbo
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : ch_scma

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-08-20 10:41:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `appraisescoreprinciple`
-- ----------------------------
DROP TABLE IF EXISTS `appraisescoreprinciple`;
CREATE TABLE `appraisescoreprinciple` (
  `id` char(36) NOT NULL DEFAULT '' COMMENT '主键',
  `scorePrinciple` varchar(255) DEFAULT NULL COMMENT '评分标准',
  `companyCode` varchar(36) DEFAULT NULL COMMENT '公司编号',
  `normCode` varchar(50) DEFAULT NULL COMMENT '指标编号',
  `normName` varchar(36) DEFAULT NULL COMMENT '指标名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appraisescoreprinciple
-- ----------------------------
INSERT INTO `appraisescoreprinciple` VALUES ('35b7db27-7865-487c-8dae-9f2e96db726b', '总分为8分。制冷、空调设备制造产值能耗不超过0.023吨标准煤/万元，产值水耗不超过1.1立方米/万元。', '1', '100311', '节能减排环保合规');

-- ----------------------------
-- Table structure for `appraise_norm`
-- ----------------------------
DROP TABLE IF EXISTS `appraise_norm`;
CREATE TABLE `appraise_norm` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键',
  `normName` varchar(36) DEFAULT NULL COMMENT '指标名称',
  `normCode` varchar(50) DEFAULT NULL COMMENT '指标编号',
  `schemeCode` varchar(36) DEFAULT NULL COMMENT '方案编号',
  `normCodeFather` varchar(36) DEFAULT NULL COMMENT '父指标编号',
  `normLevel` varchar(10) DEFAULT NULL COMMENT '指标级别',
  `remake` varchar(32) DEFAULT NULL COMMENT '备注',
  `companycode` varchar(10) DEFAULT NULL COMMENT '公司级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价指标';

-- ----------------------------
-- Records of appraise_norm
-- ----------------------------
INSERT INTO `appraise_norm` VALUES ('1000', '主列表菜单', '1000', '0', '1', '一级指标', null, null);
INSERT INTO `appraise_norm` VALUES ('1001', '绿色供应链管理战略及规划', '1001', '0', '1000', '一级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1003', '绿色供应商管理', '1002', '0', '1000', '一级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1004', '绿色生产', '1003', '0', '1000', '一级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1006', '绿色运输与回收', '1004', '0', '1000', '一级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1007', '绿色信息平台建设', '1005', '0', '1000', '一级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1008', '绿色信息披露', '1006', '0', '1000', '一级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1009', '纳入企业发展规划', '10011', '0', '1001', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1010', '制定绿色供应链管理目标', '10012', '0', '1001', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1011', '设置专门管理机构', '10013', '0', '1001', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1012', '建立绿色采购标准制度', '10021', '0', '1002', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1013', '供应商认证体系', '10022', '0', '1002', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1014', '供应商定期培训', '10023', '0', '1002', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1015', '低风险供应商占比', '10024', '0', '1002', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1016', '节能减排', '10031', '0', '1003', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1017', '有害物质管理', '10032', '0', '1003', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('10171', '绿色包装', '10033', '0', '1003', '二级指标', null, '1');
INSERT INTO `appraise_norm` VALUES ('1018', '回收率', '10041', '0', '1004', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1019', '运输与物流', '10042', '0', '1004', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1020', '建立回收体系', '10043', '0', '1004', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1021', '指导下游企业回收、拆解', '10044', '0', '1004', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1022', '建立绿色供应链管理信息平台', '10051', '0', '1005', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1023', '节能减排减碳信息', '10061', '0', '1006', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1024', '供应商信息', '10062', '0', '1006', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1025', '发布报告', '10063', '0', '1006', '二级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1026', '纳入企业发展规划与制定实施计划', '100111', '0', '10011', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('10261', '绿色设计体系', '100112', '0', '10011', '三级指标', null, '1');
INSERT INTO `appraise_norm` VALUES ('10262', '绿色设计工具软件', '100113', '0', '10011', '三级指标', null, '1');
INSERT INTO `appraise_norm` VALUES ('1027', '有害物质使用减少', '100121', '0', '10012', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1028', '签订绿色协议的供应商占供应商的比例', '100122', '0', '10012', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1029', '节能减排指标', '100123', '0', '10012', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1030', '产品/包装物回收及再利用率', '100124', '0', '10012', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1031', '成立企业绿色供应链管理组', '100131', '0', '10013', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1032', '明确职责，并提供相应的资源', '100132', '0', '10013', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1033', '建立企业（或产品）绿色采购物料清单及要求', '100211', '0', '10021', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1034', '企业绿色供应链管理流程', '100212', '0', '10021', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1035', '物料绿色性检测标准等', '100213', '0', '10021', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1036', '绿色供应商审核', '100221', '0', '10022', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1037', '供应商绿色绩效评估与定期审核', '100222', '0', '10022', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1038', '制定培训计划及教材，并根据产品升级并及时更新相关材料', '100231', '0', '10023', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1039', '对培训效果进行监督检查', '100232', '0', '10023', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1040', '对供应商分级管理，严格把控低风险供应商', '100241', '0', '10024', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1041', '节能减排环保合规', '100311', '0', '10031', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1042', '建立制造过程能耗数据采集和记录制度', '100312', '0', '10031', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1043', '建立制造过程排放数据采集和记录制度', '100313', '0', '10031', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1044', '管理规范', '100321', '0', '10032', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1045', '检测制度', '100322', '0', '10032', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('10451', '绿色包装率', '100331', '0', '10033', '三级指标', null, '1');
INSERT INTO `appraise_norm` VALUES ('10452', '绿色包装规范', '100332', '0', '10033', '三级指标', null, '1');
INSERT INTO `appraise_norm` VALUES ('1046', '产品回收率', '100411', '0', '10041', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1047', '包装物回收率', '100412', '0', '10041', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1048', '优化运输与物流方案', '100421', '0', '10042', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1049', '产品回收体系', '100431', '0', '10043', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1050', '包装物回收体系', '100432', '0', '10043', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1051', '对销售商、回收企业进行回收、拆解培训', '100441', '0', '10044', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1052', '建立独立完善绿色供应链管理信息平台', '100511', '0', '10051', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1053', '将绿色供应链管理要求与企业信息化系统融合', '100512', '0', '10051', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1054', '企业节能减排及产品回收再利用信息', '100611', '0', '10061', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1055', '绿色供应商供应产品的绿色性及节能减排信息', '100612', '0', '10061', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1056', '高、中风险供应商审核率', '100621', '0', '10062', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1057', '低风险供应商占比率', '100622', '0', '10062', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1058', '企业社会责任报告', '100631', '0', '10063', '三级指标', '', '1');
INSERT INTO `appraise_norm` VALUES ('1059', '企业绿色供应链管理报告（包括企业基本信息、产品有害物质使用等）', '100632', '0', '10063', '三级指标', '', '1');

-- ----------------------------
-- Table structure for `appraise_norm_data`
-- ----------------------------
DROP TABLE IF EXISTS `appraise_norm_data`;
CREATE TABLE `appraise_norm_data` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键',
  `dataType` varchar(32) DEFAULT NULL COMMENT '数据类别',
  `dataValue` varchar(32) DEFAULT NULL COMMENT '数据量',
  `unitSymbol` varchar(32) DEFAULT NULL COMMENT '单位符号',
  `remake` varchar(32) DEFAULT NULL COMMENT '备注',
  `companycode` varchar(10) DEFAULT NULL COMMENT '公司级别',
  `normCode` varchar(50) DEFAULT NULL,
  `normName` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据';

-- ----------------------------
-- Records of appraise_norm_data
-- ----------------------------
INSERT INTO `appraise_norm_data` VALUES ('0a35584f-6382-4ca0-a58b-ff39a4aba169', '供应商优秀率', '90', '百分比', '电视公司', '1', '100621', '高、中风险供应商审核率');
INSERT INTO `appraise_norm_data` VALUES ('13e5ab0c-324c-4783-b2a3-41f63f913a88', '供应商优秀率', '88', '百分比', '冰箱公司', '1', '100621', '高、中风险供应商审核率');
INSERT INTO `appraise_norm_data` VALUES ('35b7db27-7865-487c-8dae-9f2e96db726b', '万元增加值能耗', '0.020', '吨标准煤', '空调公司616处', '1', '100311', '节能减排环保合规');
INSERT INTO `appraise_norm_data` VALUES ('3a423666-363e-4b26-898f-a934e9ea0514', '万元增加值能耗', '0.018', '吨标准煤', '空调公司909处', '1', '100311', '节能减排环保合规');
INSERT INTO `appraise_norm_data` VALUES ('77c9fce8-6ce0-471f-a534-67b6c671a85e', '供应商优秀率', '80', '百分比', '空调公司', '1', '100621', '高、中风险供应商审核率');
INSERT INTO `appraise_norm_data` VALUES ('a66c92ba-c7b7-4e6c-9a60-a4c8c5c173b3', '万元增加值能耗', '0.017', '吨标准煤', '电视一厂', '1', '100311', '节能减排环保合规');
INSERT INTO `appraise_norm_data` VALUES ('b290dcee-3814-4adf-be6a-923341b32d0c', '万元增加值能耗', '0.019', '吨标准煤', '空调公司601处', '1', '100311', '节能减排环保合规');
INSERT INTO `appraise_norm_data` VALUES ('dd60fbbe-c55c-4e70-a363-4f93b8a99d18', '直套率', '95', '百分比', '冰箱公司', '1', '100331', '绿色包装率');
INSERT INTO `appraise_norm_data` VALUES ('deabfddb-02de-4597-bb8f-9d4085cf6a08', '直套率', '90', '百分比', '空调公司', '1', '100331', '绿色包装率');
INSERT INTO `appraise_norm_data` VALUES ('ef58dc8e-21ab-4eca-a551-d06f2696011b', '直套率', '92', '百分比', '电视公司', '1', '100331', '绿色包装率');

-- ----------------------------
-- Table structure for `appraise_norm_file`
-- ----------------------------
DROP TABLE IF EXISTS `appraise_norm_file`;
CREATE TABLE `appraise_norm_file` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键',
  `filename` varchar(200) DEFAULT NULL COMMENT '附件名称',
  `filepath` varchar(500) DEFAULT NULL COMMENT '附件路径',
  `filecontext` text COMMENT '附件内容',
  `remake` varchar(32) DEFAULT NULL COMMENT '备注',
  `companycode` varchar(10) DEFAULT NULL COMMENT '公司级别',
  `normCode` varchar(50) DEFAULT NULL,
  `normName` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件';

-- ----------------------------
-- Records of appraise_norm_file
-- ----------------------------
INSERT INTO `appraise_norm_file` VALUES ('af979830-929d-4bfb-89fb-790c9eb4d629', '上海市能效指南.pdf', '/upload/2018-08/17/上海市能效指南.pdf', null, null, null, '100311', null);

-- ----------------------------
-- Table structure for `appraise_norm_score`
-- ----------------------------
DROP TABLE IF EXISTS `appraise_norm_score`;
CREATE TABLE `appraise_norm_score` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键',
  `normCode` varchar(50) DEFAULT NULL COMMENT '指标编号',
  `empCode` varchar(32) DEFAULT NULL COMMENT '专家编号',
  `score` varchar(32) DEFAULT NULL COMMENT '得分',
  `remake` varchar(32) DEFAULT NULL COMMENT '备注',
  `companycode` varchar(10) DEFAULT NULL COMMENT '公司级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分值';

-- ----------------------------
-- Records of appraise_norm_score
-- ----------------------------
INSERT INTO `appraise_norm_score` VALUES ('1a15e633-35cc-43ad-9f73-c13139cd2e7f', '100311', '1', '8', null, '1');
INSERT INTO `appraise_norm_score` VALUES ('201c160b-e30f-4842-8a2e-4f6da8b0e548', '100312', '1', '2', null, '1');
INSERT INTO `appraise_norm_score` VALUES ('ae07a120-f1d1-4297-acc4-80c7abb3c929', '100313', '1', '2', null, '1');

-- ----------------------------
-- Table structure for `appraise_scheme`
-- ----------------------------
DROP TABLE IF EXISTS `appraise_scheme`;
CREATE TABLE `appraise_scheme` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键',
  `schemeName` varchar(32) DEFAULT NULL COMMENT '方案名称',
  `schemeCode` varchar(36) DEFAULT NULL COMMENT '方案编号',
  `schemeType` varchar(10) DEFAULT NULL COMMENT '方案类别',
  `remake` varchar(32) DEFAULT NULL COMMENT '备注',
  `companycode` varchar(10) DEFAULT NULL COMMENT '公司级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价方案';

-- ----------------------------
-- Records of appraise_scheme
-- ----------------------------
INSERT INTO `appraise_scheme` VALUES ('63395ef9-f196-41ed-bc51-ab5dd305a728', '方案一', '0', '0', '', null);
INSERT INTO `appraise_scheme` VALUES ('92731138-e85f-423c-a9a7-3c79027cfc35', '方案二', '1', '1', '', null);

-- ----------------------------
-- Table structure for `list_analyze`
-- ----------------------------
DROP TABLE IF EXISTS `list_analyze`;
CREATE TABLE `list_analyze` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '指标id',
  `normName` varchar(36) DEFAULT NULL COMMENT '指标名字',
  `normCode` varchar(50) DEFAULT NULL COMMENT '指标编号',
  `score` varchar(32) DEFAULT NULL COMMENT '分数',
  `empCode` varchar(32) DEFAULT NULL COMMENT '专家编号',
  `companycode` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of list_analyze
-- ----------------------------
INSERT INTO `list_analyze` VALUES ('1', '纳入企业发展规划与制定实施计划', '100111', null, null, null);
INSERT INTO `list_analyze` VALUES ('2', '绿色设计体系', '100112', null, null, null);
INSERT INTO `list_analyze` VALUES ('3', '绿色设计工具软件', '100113', null, null, null);
INSERT INTO `list_analyze` VALUES ('4', '有害物质使用减少', '100121', null, null, null);
INSERT INTO `list_analyze` VALUES ('5', '签订绿色协议的供应商占供应商的比例', '100122', null, null, null);
INSERT INTO `list_analyze` VALUES ('6', '节能减排指标', '100123', null, null, null);
INSERT INTO `list_analyze` VALUES ('7', '产品/包装物回收及再利用率', '100124', null, null, null);
INSERT INTO `list_analyze` VALUES ('8', '成立企业绿色供应链管理组', '100131', null, null, null);
INSERT INTO `list_analyze` VALUES ('9', '明确职责，并提供相应的资源', '100132', null, null, null);
INSERT INTO `list_analyze` VALUES ('10', '建立企业（或产品）绿色采购物料清单及要求', '100211', null, null, null);
INSERT INTO `list_analyze` VALUES ('11', '企业绿色供应链管理流程', '100212', null, null, null);
INSERT INTO `list_analyze` VALUES ('12', '物料绿色性检测标准等', '100213', null, null, null);
INSERT INTO `list_analyze` VALUES ('13', '绿色供应商审核', '100221', null, null, null);
INSERT INTO `list_analyze` VALUES ('14', '供应商绿色绩效评估与定期审核', '100222', null, null, null);
INSERT INTO `list_analyze` VALUES ('15', '制定培训计划及教材，并根据产品升级并及时更新相关材料', '100231', null, null, null);
INSERT INTO `list_analyze` VALUES ('16', '对培训效果进行监督检查', '100232', null, null, null);
INSERT INTO `list_analyze` VALUES ('17', '对供应商分级管理，严格把控低风险供应商', '100241', null, null, null);
INSERT INTO `list_analyze` VALUES ('18', '节能减排环保合规', '100311', null, null, null);
INSERT INTO `list_analyze` VALUES ('19', '建立制造过程能耗数据采集和记录制度', '100312', null, null, null);
INSERT INTO `list_analyze` VALUES ('20', '建立制造过程排放数据采集和记录制度', '100313', null, null, null);
INSERT INTO `list_analyze` VALUES ('21', '管理规范', '100321', null, null, null);
INSERT INTO `list_analyze` VALUES ('22', '检测制度', '100322', null, null, null);
INSERT INTO `list_analyze` VALUES ('23', '绿色包装率', '100331', null, null, null);
INSERT INTO `list_analyze` VALUES ('24', '绿色包装规范', '100332', null, null, null);
INSERT INTO `list_analyze` VALUES ('25', '产品回收率', '100411', null, null, null);
INSERT INTO `list_analyze` VALUES ('26', '包装物回收率', '100412', null, null, null);
INSERT INTO `list_analyze` VALUES ('27', '优化运输与物流方案', '100421', null, null, null);
INSERT INTO `list_analyze` VALUES ('28', '产品回收体系', '100431', null, null, null);
INSERT INTO `list_analyze` VALUES ('29', '包装物回收体系', '100432', null, null, null);
INSERT INTO `list_analyze` VALUES ('30', '对销售商、回收企业进行回收、拆解培训', '100441', null, null, null);
INSERT INTO `list_analyze` VALUES ('31', '建立独立完善绿色供应链管理信息平台', '100511', null, null, null);
INSERT INTO `list_analyze` VALUES ('32', '将绿色供应链管理要求与企业信息化系统融合', '100512', null, null, null);
INSERT INTO `list_analyze` VALUES ('33', '企业节能减排及产品回收再利用信息', '100611', null, null, null);
INSERT INTO `list_analyze` VALUES ('34', '绿色供应商供应产品的绿色性及节能减排信息', '100612', null, null, null);
INSERT INTO `list_analyze` VALUES ('35', '高、中风险供应商审核率', '100621', null, null, null);
INSERT INTO `list_analyze` VALUES ('36', '低风险供应商占比率', '100622', null, null, null);
INSERT INTO `list_analyze` VALUES ('37', '企业社会责任报告', '100631', null, null, null);
INSERT INTO `list_analyze` VALUES ('38', '企业绿色供应链管理报告（包括企业基本信息、产品有害物质使用等）', '100632', null, null, null);

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
INSERT INTO `sys_employeerole` VALUES ('6a14b334-bfe8-4f14-b8ff-de32720093f8', 'A0E3B952-FF9E-4563-997D-C52C61145459', '1', '1');

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
INSERT INTO `sys_model` VALUES ('01b5bb7d-97f6-4cd0-b32b-bbc3c9bf4d92', '组织机构', '1', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '12', '1', 'ww', '/organization', '0', '1', '', 'w', '1', 'organization', '1');
INSERT INTO `sys_model` VALUES ('1a388cb3-6443-4765-8b58-61168e48495d', '上传模块', '1', 'd7b39a37-66d2-47d8-884f-f066e8198cf8', '13', '1', 'qq', '/upload', '0', '1', '', '', '1', 'upload', '1');
INSERT INTO `sys_model` VALUES ('1e44b37d-678e-4332-b266-2ca02d057a2a', '数据字典', '1', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '3', '1', 'parameter', '/parameter', '0', '1', '', 'MES', '1', 'parameter', '1');
INSERT INTO `sys_model` VALUES ('63910000-35dc-4a79-a3f3-2bd502bb859e', '评价管理', '1', 'C0C50F39-DAF8-4696-B611-3084FAE1EFEC', '2', '1', 'appraise', '/appraise', '0', '1', '', 'mes', '0', 'appraise', '1');
INSERT INTO `sys_model` VALUES ('79824BC6-48E9-4AC9-BF08-62C9B28CD7CF', '菜单管理', '1', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '1', '1', 'module', '/module', '0', '1', '', 'MES', '1', 'module', '1');
INSERT INTO `sys_model` VALUES ('864fb872-eb92-4ffa-b061-5e013f397666', '列表分析', '1', 'd7b39a37-66d2-47d8-884f-f066e8198cf8', '12', '1', 'listAnalyze', '/listAnalyze', '0', '1', '', 'mes', '1', 'listAnalyze', '1');
INSERT INTO `sys_model` VALUES ('8dd418d6-3705-4460-93e9-d256bc0b71d4', '评价方案', '1', '63910000-35dc-4a79-a3f3-2bd502bb859e', '7', '1', 'appraiseScheme', '/appraiseScheme', '0', '1', '', 'mes', '1', 'appraiseScheme', '1');
INSERT INTO `sys_model` VALUES ('94c121a5-e2e2-486e-90e9-830190feefcd', '数据录入', '1', '63910000-35dc-4a79-a3f3-2bd502bb859e', '9', '1', 'appraiseData', '/appraiseData', '0', '1', '', 'mes', '1', 'appraiseData', '1');
INSERT INTO `sys_model` VALUES ('958cbd0e-7515-483d-a8c6-4f1a4f969f84', '评价指标', '1', '63910000-35dc-4a79-a3f3-2bd502bb859e', '8', '1', 'appraiseNorm', '/appraiseNorm', '0', '1', '', 'mes', '1', 'appraiseNorm', '1');
INSERT INTO `sys_model` VALUES ('bcedbdf2-733c-429b-9df2-9b8593affcba', '专家账号', '1', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '4', '1', 'emp', '/emp', '0', '1', '', 'mes', '1', 'emp', '1');
INSERT INTO `sys_model` VALUES ('D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '系统设置', '1', 'C0C50F39-DAF8-4696-B611-3084FAE1EFEC', '1', '1', 'system_settings', '/system', '0', '1', '', 'MES', '0', 'SystemSetting', '1');
INSERT INTO `sys_model` VALUES ('d2496fca-a427-49d9-8545-c619edaa6050', '角色管理', '1', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '5', '1', 'role', '/role', '0', '1', '', 'mes', '1', 'role', '1');
INSERT INTO `sys_model` VALUES ('d7b39a37-66d2-47d8-884f-f066e8198cf8', '评价分析', '1', 'C0C50F39-DAF8-4696-B611-3084FAE1EFEC', '11', '1', 'analyze', '/analyze', '0', '1', '', 'mes', '0', 'analyze', '1');
INSERT INTO `sys_model` VALUES ('ef18fcf5-7bbc-45a8-a33d-d68f0bd86e42', '专家评分', '1', '63910000-35dc-4a79-a3f3-2bd502bb859e', '10', '1', 'appraiseScore', '/appraiseScore', '0', '1', '', 'mes', '1', 'appraiseScore', '1');

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
INSERT INTO `sys_organization` VALUES ('1000', 'qw', 'qw', '1', 'qwe', 'qwe', null, '/org', '1');
INSERT INTO `sys_organization` VALUES ('1001', '12', '13', '1000', '13', '312', null, '/org', '1');
INSERT INTO `sys_organization` VALUES ('1002', '2', '43', '1000', '3', '3', null, '/org', '1');

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
INSERT INTO `sys_parameter` VALUES ('14aef0ff-9e9f-4fa2-b866-611ddd52c7b2', 'a', 'aa', '指标级别', 'normType', '0', '一级指标', '0', null, '', '1', 'admin', '2018-07-16', '1');
INSERT INTO `sys_parameter` VALUES ('54014E28-3167-4C9D-8666-B6614CB38C22', 'A', 'a', '是否展开', 'modelparameter', '1', '展开', '0', '1', '0', '1', 'admin', null, '1');
INSERT INTO `sys_parameter` VALUES ('5de5bfa7-f41a-4bd4-9894-1db5ed77ea7b', 'A', 'a', '指标级别', 'normType', '1', '二级指标', '0', null, '', '1', 'admin', '2018-07-16', '1');
INSERT INTO `sys_parameter` VALUES ('648C2A13-7D95-4B4C-8BDD-26BC3A691427', 'A', 'a', '是否显示', 'isshow', '1', '显示', '0', '2', '0', '1', 'admin', null, '1');
INSERT INTO `sys_parameter` VALUES ('8BD30199-62E0-4964-BFD5-C2D9978246E1', 'A', 'a', '节点类型', 'transflag', '0', '父节点', '0', '1', '0', '1', 'admin', '2018-07-06', '1');
INSERT INTO `sys_parameter` VALUES ('93e0c43f-843f-4e4c-bd70-793264c68910', '', '', '专家编号', 'empCode', '1', '1号专家', '0', null, '', '1', 'admin', '2018-07-18', '1');
INSERT INTO `sys_parameter` VALUES ('9d458f28-f8ec-4af2-8161-3e833eacdff1', '', '', '专家编号', 'empCode', '3', '3号专家', '0', null, '', '1', 'admin', '2018-07-18', '1');
INSERT INTO `sys_parameter` VALUES ('aa3b610b-4f99-40a2-a6a2-7004f78ca882', 'A', 'a', '指标级别', 'normType', '2', '三级指标', '0', null, '', '1', 'admin', '2018-07-16', '1');
INSERT INTO `sys_parameter` VALUES ('BAEBF26D-B9EB-47F9-9B61-1C7F34443DC0', 'A', 'a', '节点类型', 'transflag', '1', '子节点', '0', '1', '0', '1', 'admin', '2018-07-06', '1');
INSERT INTO `sys_parameter` VALUES ('bc666592-6b90-46ec-999f-6bf693177e39', 'a', 'ss', '方案类别', 'schemeType', '0', '方案一', '0', '1', '', '1', 'admin', '2018-07-16', '1');
INSERT INTO `sys_parameter` VALUES ('c1cfcfa9-5002-42a1-ade0-23e1d15aaaf7', 'qq', 'q', '方案类型', 'schemeType', '1', '方案二', '0', '2', '', '1', 'admin', '2018-07-16', '1');
INSERT INTO `sys_parameter` VALUES ('DD8B80E0-C23A-4A70-8098-721867878D6E', 'A', 'a', '是否显示', 'isshow', '0', '不显示', '0', '1', '0', '1', 'admin', null, '1');
INSERT INTO `sys_parameter` VALUES ('F44C0311-AABA-4991-B561-65299C3317B4', 'A', 'a', '是否展开', 'modelparameter', '0', '不展开', '0', '1', '0', '1', 'admin', null, '1');
INSERT INTO `sys_parameter` VALUES ('fdad9120-aab9-454e-adca-06364ebb3a5d', '', '', '专家编号', 'empCode', '2', '2号专家', '0', null, '', '1', 'admin', '2018-07-18', '1');

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
INSERT INTO `sys_rolemodel` VALUES ('02AA8D2B-51C9-45D7-920F-572A5982229C', 'A0E3B952-FF9E-4563-997D-C52C61145459', '1e44b37d-678e-4332-b266-2ca02d057a2a', '1');
INSERT INTO `sys_rolemodel` VALUES ('051742f2-9cc8-4ef4-8722-b707b13fa5da', 'A0E3B952-FF9E-4563-997D-C52C61145459', '94c121a5-e2e2-486e-90e9-830190feefcd', '1');
INSERT INTO `sys_rolemodel` VALUES ('0DA07172-2B2B-4A4D-84B4-BAF58F970586', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'bcedbdf2-733c-429b-9df2-9b8593affcba', '1');
INSERT INTO `sys_rolemodel` VALUES ('0e06c5f1-a0ea-4604-a0c1-0bef70e2c8c9', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'd2496fca-a427-49d9-8545-c619edaa6050', '1');
INSERT INTO `sys_rolemodel` VALUES ('0f33a3ef-3a80-4293-b77c-20401039fc54', 'A0E3B952-FF9E-4563-997D-C52C61145459', '8dd418d6-3705-4460-93e9-d256bc0b71d4', '1');
INSERT INTO `sys_rolemodel` VALUES ('12060c6d-72c9-4c27-ae3c-2da577a083c5', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '1');
INSERT INTO `sys_rolemodel` VALUES ('15dcb2ae-c7fb-44bf-bdc0-9502d22ae7ea', 'A0E3B952-FF9E-4563-997D-C52C61145459', '01b5bb7d-97f6-4cd0-b32b-bbc3c9bf4d92', '1');
INSERT INTO `sys_rolemodel` VALUES ('1a19695a-1d55-4ec4-a93b-2daa5175e068', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'ef18fcf5-7bbc-45a8-a33d-d68f0bd86e42', '1');
INSERT INTO `sys_rolemodel` VALUES ('1acb72fe-1b80-4386-ba35-afaa7ff32647', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '1');
INSERT INTO `sys_rolemodel` VALUES ('26c60928-a0af-43f9-8bdf-3a7bfd4dfbbf', 'A0E3B952-FF9E-4563-997D-C52C61145459', '958cbd0e-7515-483d-a8c6-4f1a4f969f84', '1');
INSERT INTO `sys_rolemodel` VALUES ('3b9dda39-8dd6-43e0-b059-f3853c0d46e1', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'd7b39a37-66d2-47d8-884f-f066e8198cf8', '1');
INSERT INTO `sys_rolemodel` VALUES ('437dbd46-3203-4d1d-83ae-397f37fd88a4', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'd7b39a37-66d2-47d8-884f-f066e8198cf8', '1');
INSERT INTO `sys_rolemodel` VALUES ('4c5985fa-3088-4188-b5a6-19415183f847', 'A0E3B952-FF9E-4563-997D-C52C61145459', '8dd418d6-3705-4460-93e9-d256bc0b71d4', '1');
INSERT INTO `sys_rolemodel` VALUES ('518ea527-0cc6-4ee7-b1bc-7783f2c1902c', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'bcedbdf2-733c-429b-9df2-9b8593affcba', '1');
INSERT INTO `sys_rolemodel` VALUES ('52de96a8-6a31-4490-bbe7-fd1cec749285', 'A0E3B952-FF9E-4563-997D-C52C61145459', '79824BC6-48E9-4AC9-BF08-62C9B28CD7CF', '1');
INSERT INTO `sys_rolemodel` VALUES ('5da15a4f-ef86-4a27-8f94-26b68fa2bfd2', 'A0E3B952-FF9E-4563-997D-C52C61145459', '79824BC6-48E9-4AC9-BF08-62C9B28CD7CF', '1');
INSERT INTO `sys_rolemodel` VALUES ('6a034294-70ee-4ecd-b087-f39c8eb04430', 'A0E3B952-FF9E-4563-997D-C52C61145459', '1e44b37d-678e-4332-b266-2ca02d057a2a', '1');
INSERT INTO `sys_rolemodel` VALUES ('6D1275D6-E21B-4C50-86CA-401289391613', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '1');
INSERT INTO `sys_rolemodel` VALUES ('6edfe605-772c-4bee-8f96-ed5f84c74314', 'A0E3B952-FF9E-4563-997D-C52C61145459', '864fb872-eb92-4ffa-b061-5e013f397666', '1');
INSERT INTO `sys_rolemodel` VALUES ('82c6c626-7a3b-42d2-9f3f-cc08154f4b39', 'A0E3B952-FF9E-4563-997D-C52C61145459', '63910000-35dc-4a79-a3f3-2bd502bb859e', '1');
INSERT INTO `sys_rolemodel` VALUES ('8966e300-c765-4956-91b4-7b85fbb46357', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'ef18fcf5-7bbc-45a8-a33d-d68f0bd86e42', '1');
INSERT INTO `sys_rolemodel` VALUES ('8c646887-9759-42f4-b0ca-9f650678fdc7', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'd2496fca-a427-49d9-8545-c619edaa6050', '1');
INSERT INTO `sys_rolemodel` VALUES ('95de0e4f-9ae3-4537-8e41-02294e8ff110', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'D1CEF3E4-DC6C-4051-BC0B-88180D38082B', '1');
INSERT INTO `sys_rolemodel` VALUES ('9cbdcf08-90da-484e-8fd0-27b2573021a1', 'A0E3B952-FF9E-4563-997D-C52C61145459', '864fb872-eb92-4ffa-b061-5e013f397666', '1');
INSERT INTO `sys_rolemodel` VALUES ('acf09a9c-4ea0-403c-a461-53852992a143', 'A0E3B952-FF9E-4563-997D-C52C61145459', '1e44b37d-678e-4332-b266-2ca02d057a2a', '1');
INSERT INTO `sys_rolemodel` VALUES ('ad10caad-9811-4d68-b0cc-72a161752d35', 'A0E3B952-FF9E-4563-997D-C52C61145459', '958cbd0e-7515-483d-a8c6-4f1a4f969f84', '1');
INSERT INTO `sys_rolemodel` VALUES ('ae1c7f4b-c8d2-4f2c-904b-5c7dfa89b1f6', 'A0E3B952-FF9E-4563-997D-C52C61145459', '79824BC6-48E9-4AC9-BF08-62C9B28CD7CF', '1');
INSERT INTO `sys_rolemodel` VALUES ('B63A37CC-7546-4184-A263-8BA2ECFB0309', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'd2496fca-a427-49d9-8545-c619edaa6050', '1');
INSERT INTO `sys_rolemodel` VALUES ('c1651f12-bbcc-4e5e-bcab-5a3bdc387977', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'bcedbdf2-733c-429b-9df2-9b8593affcba', '1');
INSERT INTO `sys_rolemodel` VALUES ('c5a7a2c1-e903-4f37-a647-7f8169f15a8b', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'd2496fca-a427-49d9-8545-c619edaa6050', '1');
INSERT INTO `sys_rolemodel` VALUES ('DA9EB1C2-7B7F-4F15-9E94-32BDDE40EE63', 'A0E3B952-FF9E-4563-997D-C52C61145459', '79824BC6-48E9-4AC9-BF08-62C9B28CD7CF', '1');
INSERT INTO `sys_rolemodel` VALUES ('dc06dc8b-cafa-4001-83a2-c3af75137e14', 'A0E3B952-FF9E-4563-997D-C52C61145459', '63910000-35dc-4a79-a3f3-2bd502bb859e', '1');
INSERT INTO `sys_rolemodel` VALUES ('de550c31-9e3b-41d9-9d94-60c7db456eb2', 'A0E3B952-FF9E-4563-997D-C52C61145459', 'bcedbdf2-733c-429b-9df2-9b8593affcba', '1');
INSERT INTO `sys_rolemodel` VALUES ('e3fbae1a-1c09-446d-951f-ae9d4347d587', 'A0E3B952-FF9E-4563-997D-C52C61145459', '94c121a5-e2e2-486e-90e9-830190feefcd', '1');
INSERT INTO `sys_rolemodel` VALUES ('f16aeaa9-a3f6-4971-90a4-9eebaa430508', 'A0E3B952-FF9E-4563-997D-C52C61145459', '63910000-35dc-4a79-a3f3-2bd502bb859e', '1');
INSERT INTO `sys_rolemodel` VALUES ('f974e039-4b59-41d7-9d69-2063ed6c90f4', 'A0E3B952-FF9E-4563-997D-C52C61145459', '1e44b37d-678e-4332-b266-2ca02d057a2a', '1');
