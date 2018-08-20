Ext.QuickTips.init();
Ext.ns("Ext.changhong.user"); // 自定义一个命名空间
user = Ext.changhong.user; // 定义命名空间的别名
user = {
	all : ctx + '/emp', // 所有用户
	save : ctx + "/emp/save",// 保存用户
	reset : ctx + "/emp/reset/",// 重置用户密码
	del : ctx + "/emp/del/",// 删除用户
	myRole : ctx + "/emp/",// 加载某个用户的所有角色
	loadRole : ctx + '/role/all',// 加载所有角色
	pageSize : 20,// 每页显示的记录数
	/** 当前正在编辑用户的角色ID数组 */
	userRoleIds : new Ext.util.MixedCollection(),
	hasActive : true
};
/** 改变页的combo */
user.pageSizeCombo = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					user.pageSize = parseInt(comboBox.getValue());
					user.bbar.pageSize = parseInt(comboBox.getValue());
					user.store.baseParams.limit = user.pageSize;
					user.store.baseParams.start = 0;
					user.store.load();
				}
			}
		});
// 覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准
user.pageSize = parseInt(user.pageSizeCombo.getValue());
/** 基本信息-数据源 */
user.store = new Ext.data.Store({
			remoteSort : true,
			autoLoad : true,
			baseParams : {
				start : 0,
				limit : user.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : user.all
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['empcode', 'empname', 'classname', 'email', 'phone', 'emppass',
					'stationname', 'organizationcode', 'departmentcode',
					'custcode', 'suppliercode', 'empename', 'systemtype','emptype']),
			listeners : {
				'load' : function(store, records, options) {
					user.alwaysFun();
				}
			}
		});
/** 基本信息-选择模式 */
user.selModel = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					user.deleteAction.enable();
					user.editAction.enable();
					user.resetPwdAction.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					user.deleteAction.disable();
					user.editAction.disable();
					user.resetPwdAction.disable();
				}
			}
		});
/** 基本信息-数据列 */
user.colModel = new Ext.grid.ColumnModel({
	defaults : {
		sortable : true,
		width : 110
	},
	columns : [user.selModel, {
				header : '用户编号',
				dataIndex : 'empcode'
			}, {
				header : '账号',
				dataIndex : 'empname'
			}, {
				header : '密码',
				hidden : true,
				dataIndex : 'emppass'
			}, {
				header : '员工姓名',
				dataIndex : 'classname'
			}, {
				header : '英文姓名',
				dataIndex : 'empename'
			}, {
				header : '电子邮件',
				dataIndex : 'email',
				renderer : function(value) {
					return '<span title="点击给 ' + value
							+ ' 发邮件"><a href="mailto:' + value + '">' + value
							+ '</a></span>';
				}
			}, {
				header : '手机号码',
				dataIndex : 'phone'
			}, {
				header : '所属群组',
				dataIndex : 'stationname'
			}, {
				header : '所属机构',
				dataIndex : 'organizationcode'
			}, {
				header : '所属部门',
				dataIndex : 'departmentcode'
			}, {
				header : '所属客户',
				dataIndex : 'custcode'
			}, {
				header : '所属供应商',
				dataIndex : 'suppliercode'
			}, {
				header : '系统类型',
				dataIndex : 'systemtype'
			}, {
				header : '员工分类',
				dataIndex : 'emptype',
				renderer : function(v) {
					if(v=='0'){
						return '内部员工'
					}else{
						return '外部员工'
					}
				}
			}]
});
/** 用户密码 */
user.password = new Ext.form.TextField({
			fieldLabel : '密码',
			inputType : 'password',
			maxLength : 32,
			allowBlank : false,
			name : 'emppass',
			anchor : '99%'
		});
/** 新建 */
user.addAction = new Ext.Action({
			text : '新建',
			// text : '<fmt:message key="common.cancel"/>',
			iconCls : 'user_add',
			handler : function() {
				user.addWindow.setIconClass('user_add'); // 设置窗口的样式
				user.addWindow.setTitle('新建用户'); // 设置窗口的名称
				user.addWindow.show().center(); // 显示窗口
				user.formPanel.getForm().reset(); // 清空表单里面的元素的值.
				user.userRoleIds.clear();// 清空原来用户的角色
				user.password.setVisible(true);
				user.password.setDisabled(false);
				user.hasActive = true;
				user.tabPanel.activate(user.formPanel);
			}
		});
/** 编辑 */
user.editAction = new Ext.Action({
			text : '编辑',
			iconCls : 'user_edit',
			disabled : true,
			handler : function() {
				var record = user.grid.getSelectionModel().getSelected();
				user.userRoleIds.clear();
				// 发送请求
				// 查找当前用户的角色
				Share.AjaxRequest({
							url : user.myRole + record.data.empcode,
							callback : function(json) {
								Ext.each(json, function(role) {
											// 当前用户的角色
											user.userRoleIds.add(role.roleid,
													role.empcode);
										});
							}
						});
				user.addWindow.setIconClass('user_edit'); // 设置窗口的样式
				user.addWindow.setTitle('编辑用户'); // 设置窗口的名称
				user.addWindow.show().center();
				user.formPanel.getForm().reset();
				user.formPanel.getForm().loadRecord(record);
				user.password.setVisible(true);
				user.password.setDisabled(true);
				user.hasActive = true;
				user.tabPanel.activate(user.formPanel);
			}
		});
/** 删除 */
user.deleteAction = new Ext.Action({
			text : '删除',
			iconCls : 'user_delete',
			disabled : true,
			handler : function() {
				user.delFun();
			}
		});
/** 重置密码 */
user.resetPwdAction = new Ext.Action({
			text : '重置密码',
			iconCls : 'reset_pwd',
			disabled : true,
			handler : function() {
				user.resetPwdFun();
			}
		});
/** 查询 */
user.searchField = new Ext.ux.form.SearchField({
			store : user.store,
			paramName : 'realName',
			emptyText : '请输入员工姓名',
			style : 'margin-left: 5px;'
		});
/** 顶部工具栏 */
user.tbar = [user.addAction, '-', user.editAction, '-', user.deleteAction, '-',
		user.resetPwdAction, '-', user.searchField];
/** 底部工具条 */
user.bbar = new Ext.PagingToolbar({
			pageSize : user.pageSize,
			store : user.store,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', user.pageSizeCombo]
		});
/** 基本信息-表格 */
user.grid = new Ext.grid.GridPanel({
			store : user.store,
			colModel : user.colModel,
			selModel : user.selModel,
			tbar : user.tbar,
			bbar : user.bbar,
			autoScroll : 'auto',
			region : 'center',
			loadMask : true,
			viewConfig : {},
			stripeRows : true
		});
/** 基本信息-详细信息的form */
user.formPanel = new Ext.form.FormPanel({
	autoScroll : true,
	frame : false,
	title : '员工信息',
	bodyStyle : 'padding:10px;border:0px',
	labelWidth : 80,
	defaultType : 'textfield',
	items : [{
				xtype : 'hidden',
				fieldLabel : '员工编号',
				name : 'empcode'
			}, {
				fieldLabel : '员工账号',
				maxLength : 64,
				allowBlank : false,
				name : 'empname',
				anchor : '99%'
			}, user.password, {
				fieldLabel : '员工姓名',
				maxLength : 64,
				allowBlank : false,
				name : 'classname',
				anchor : '99%'
			}, {
				fieldLabel : '英文姓名',
				maxLength : 64,
				allowBlank : false,
				name : 'empename',
				anchor : '99%'
			}, {
				fieldLabel : '电子邮件',
				maxLength : 64,
				allowBlank : false,
				regex : /^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/,
				regexText : '请输入有效的邮箱地址',
				name : 'email',
				anchor : '99%'
			}, {
				fieldLabel : '手机号码',
				maxLength : 32,
				allowBlank : false,
				name : 'phone',
				anchor : '99%'
			}]
});
/** 角色 */
user.roleSelModel = new Ext.grid.CheckboxSelectionModel();
user.roleStore = new Ext.data.JsonStore({
			root : 'rows',
			totalProperty : 'results',
			autoLoad : true,
			baseParams : {
				start : 0,
				limit : user.pageSize
			},
			proxy : new Ext.data.HttpProxy({
						method : 'POST',
						url : user.loadRole
					}),
			fields : ['id', 'rolename', 'roledesc'],
			listeners : {
				'load' : function(store, records, options) {
					// user.roleSelModel.clearSelections();
				}
			}
		});
user.roleGrid = new Ext.grid.GridPanel({
			title : '用户角色',
			store : user.roleStore,
			sm : user.roleSelModel,
			autoScroll : 'auto',
			loadMask : true,
			deferRowRender : false,
			stripeRows : true,
			// autoExpandColumn :'roleDesc',
			bodyStyle : 'padding:0px;border:0px',
			columns : [user.roleSelModel, {
						hidden : true,
						header : '角色ID',
						dataIndex : 'id'
					}, {
						header : "角色名称",
						width : 200,
						dataIndex : 'rolename'
					}, {
						header : "角色描述",
						width : 300,
						dataIndex : 'roledesc'
					}],
			listeners : {
				activate : function() {
					if (user.hasActive) {
						var grid = user.roleGrid;
						var store = user.roleStore;// store
						Share.resetGrid(grid);
						grid.getSelectionModel();// 选择所有行
						var total = store.getCount();// 数据行数
						for (var i = 0; i < total; i++) {
							var row = store.data.items[i];
							if (user.userRoleIds.containsKey(row.data.id)) {
								user.roleSelModel.selectRow(i, true);
							}
						}
						user.hasActive = false;
					}
				}
			}
		});

user.tabPanel = new Ext.TabPanel({
			activeTab : 0,
			defaults : {
				autoHeight : true
			},
			items : [user.formPanel, user.roleGrid]
		});
/** 编辑新建窗口 */
user.addWindow = new Ext.Window({
			layout : 'fit',
			width : 500,
			height : 400,
			closeAction : 'hide',
			plain : true,
			modal : true,
			resizable : true,
			items : [user.tabPanel],
			buttons : [{
						text : '保存',
						handler : function() {
							user.saveFun();
						}
					}, {
						text : '重置',
						handler : function() {
							Share.resetGrid(user.roleGrid);
							var form = user.formPanel.getForm();
							var id = form.findField("empcode").getValue();
							var account = form.findField("empname").getValue();
							form.reset();
							if (id != '')
								form.findField("empcode").setValue(id);
							if (account != '')
								form.findField("empname").setValue(account);
						}
					}]
		});
user.alwaysFun = function() {
	Share.resetGrid(user.grid);
	user.deleteAction.disable();
	user.resetPwdAction.disable();
	user.editAction.disable();
};
user.saveFun = function() {
	// 保存角色
	var selections = user.roleGrid.getSelectionModel().getSelections();
	var ids = [];
	if (user.hasActive) {// 如果没有点击角色tab
		ids = user.userRoleIds.keys;
	} else {// 点击了角色tab
		for (var i = 0; i < selections.length; i++) {
			ids.push(selections[i].data.id);
		}
	}
	if (ids.length == 0) {
		user.tabPanel.activate(user.roleGrid);
		Ext.Msg.alert("提示", "请至少为此用户分配一个角色!");
		return;
	}
	var form = user.formPanel.getForm();
	if (!form.isValid()) {
		user.tabPanel.activate(user.formPanel);
		return;
	}
	var params = {
		roleIds : ids
	};
	// 合并 params 和 form.getValues()，修改并返回 params。
	$.extend(params, form.getValues());
	if (params.emppass) {
		params.emppass = Ext.MD5(params.emppass);
	}
	// 发送请求
	Share.AjaxRequest({
				url : user.save,
				params : params,
				callback : function(json) {
					user.addWindow.hide();
					user.alwaysFun();
					user.store.reload();
					// fix bug 打开页面，编辑，不点击角色的tab。直接点击保存，再点击新建，在保存，会直接提交。
					// user.tabPanel.activate(user.roleGrid);
					// Share.resetGrid(user.roleGrid);
				}
			});
};
user.resetPwdFun = function() {
	var record = user.grid.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要重置此用户的密码吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : user.reset + record.data.empcode,
								callback : function(json) {
									user.alwaysFun();
									user.store.reload();
								}
							});
				}
			});
};
user.delFun = function() {
	var record = user.grid.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : user.del + record.data.empcode,
								callback : function(json) {
									user.alwaysFun();
									user.store.reload();
								}
							});
				}
			});
};
user.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'border',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [user.grid]
		});