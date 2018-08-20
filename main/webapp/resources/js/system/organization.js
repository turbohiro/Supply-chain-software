Ext.ns("Ext.changhong.organization"); // 自定义一个命名空间
organization = Ext.changhong.organization; // 定义命名空间的别名
organization = {
	all : ctx + '/organization/all',// 加载所有
	save : ctx + "/organization/save",// 保存
	del : ctx + "/organization/del/",// 删除
	allModules : ctx + "/organization",// 组织机构
	childNodes : '',
	pageSize : 20
	// 每页显示的记录数
};
/** 改变页的combo */
organization.pageSizeCombo = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					organization.pageSize = parseInt(comboBox.getValue());
					organization.bbar.pageSize = parseInt(comboBox.getValue());
					organization.store.baseParams.limit = organization.pageSize;
					organization.store.baseParams.start = 0;
					organization.store.load();
				}
			}
		});
// 覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准
organization.pageSize = parseInt(organization.pageSizeCombo.getValue());
/** 基本信息-数据源 */
organization.store = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
				baseParams : {
				start : 0,
				limit : organization.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : organization.all
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['organizationcode', 'regioncode', 'organizationname', 'father', 'address', 'phone']),
			listeners : {
				'load' : function(store, records, options) {
					organization.alwaysFun();
				}
			}
		});
/** 基本信息-选择模式 */
organization.selModel = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					organization.deleteAction.enable();
					organization.editAction.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					organization.alwaysFun();
				}
			}
		});

/** 基本信息-数据列 */
organization.colModel = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true,
				width : 140
			},
			columns : [organization.selModel, {
						header : '机构代码',
						dataIndex : 'organizationcode'
					}, {
						header : '区域代码',
						dataIndex : 'regioncode'
					}, {
						header : '机构名称',
						dataIndex : 'organizationname'
					}, {
						header : '隶属机构代码',
						dataIndex : 'father',
						renderer : function(v) {
							if(v==1){
								return '已是最高机构';
							}else{
								var record = organization.store.query('organizationcode',v,true,true);
								return record.items[0].data.organizationname;
							}
						}
					}, {
						header : '地址',
						dataIndex : 'address'
					}, {
						header : '电话',
						dataIndex : 'phone'
					}]
		});
/** 新建 */
organization.addAction = new Ext.Action({
			text : '新建',
			iconCls : 'role_add',
			handler : function() {
				organization.addWindow.setIconClass('role_add'); // 设置窗口的样式
				organization.addWindow.setTitle('新建组织机构'); // 设置窗口的名称
				organization.addWindow.show().center(); // 显示窗口
				organization.formPanel.getForm().reset(); // 清空表单里面的元素的值.
			}
		});
/** 编辑 */
organization.editAction = new Ext.Action({
			text : '编辑',
			iconCls : 'role_edit',
			disabled : true,
			handler : function() {
				var record = organization.grid.getSelectionModel().getSelected();
				organization.addWindow.setIconClass('role_edit'); // 设置窗口的样式
				organization.addWindow.setTitle('编辑组织机构'); // 设置窗口的名称
				organization.addWindow.show().center();
				organization.formPanel.getForm().reset();
				organization.formPanel.getForm().loadRecord(record);
			}
		});
/** 删除 */
organization.deleteAction = new Ext.Action({
			text : '删除',
			iconCls : 'role_delete',
			disabled : true,
			handler : function() {
				organization.delFun();
			}
		});
/** 查询 */
organization.searchField = new Ext.ux.form.SearchField({
			store : organization.store,
			paramName : 'roleName',
			emptyText : '请输入机构名称',
			style : 'margin-left: 5px;'
		});
/** 顶部工具栏 */
organization.tbar = [organization.addAction, '-', organization.editAction, '-', organization.deleteAction, '-',
		organization.searchField];
/** 底部工具条 */
organization.bbar = new Ext.PagingToolbar({
			pageSize : organization.pageSize,
			store : organization.store,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', organization.pageSizeCombo]
		});
/** 基本信息-表格 */
organization.grid = new Ext.grid.GridPanel({
	title : '机构列表',
	store : organization.store,
	colModel : organization.colModel,
	selModel : organization.selModel,
	tbar : organization.tbar,
	bbar : organization.bbar,
	autoScroll : 'auto',
	region : 'center',
	loadMask : true,
	stripeRows : true,
	viewConfig : {}
});
/** 基本信息-详细信息的form */
organization.formPanel = new Ext.form.FormPanel({
			frame : false,
			title : '机构信息',
			bodyStyle : 'padding:10px;border:0px',
			labelWidth : 80,
			defaultType : 'textfield',
			items : [{
						fieldLabel : '机构代码',
						name : 'organizationcode',
						anchor : '99%'
					}, {
						fieldLabel: "区域代码",
						maxLength : 64,
						allowBlank : false,
						name : 'regioncode',
						anchor : '99%'
					}, {
						fieldLabel: "机构名称",
						maxLength : 64,
						allowBlank : false,
						name : 'organizationname',
						anchor : '99%'
		            }, { 
						xtype: "hidden", 
						fieldLabel: "隶属机构隐藏值", 
						id:'combHiddenValue',
						name:'hiddenValue',
						anchor: "99%"
					}, { 
						xtype: "treecombo", 
						fieldLabel: "隶属机构",
						valId:'combHiddenValue',
						name:'father',
						url: organization.allModules, 
						anchor: "99%"
					}, {
						fieldLabel : '地址',
						maxLength : 64,
						allowBlank : false,
						name : 'address',
						anchor : '99%'
					}, {
						fieldLabel : '电话',
						maxLength : 64,
						allowBlank : false,
						name : 'phone',
						anchor : '99%'
					}]
		});
/** 编辑新建窗口 */
organization.addWindow = new Ext.Window({
			layout : 'fit',
			width : 500,
			height : 280,
			closeAction : 'hide',
			plain : true,
			modal : true,
			resizable : true,
			items : [organization.formPanel],
			buttons : [{
						text : '保存',
						handler : function() {
							organization.saveFun();
						}
					}, {
						text : '重置',
						handler : function() {
							var form = organization.formPanel.getForm();
							var id = form.findField("organizationcode").getValue();
							form.reset();
							if (id != '')
								form.findField("organizationcode").setValue(id);
						}
					}]
		});
organization.alwaysFun = function() {
	Share.resetGrid(organization.grid);
	organization.deleteAction.disable();
	organization.editAction.disable();
};
organization.saveFun = function() {
	var form = organization.formPanel.getForm();
	if (!form.isValid()) {
		return;
	}
	// 发送请求
	Share.AjaxRequest({
				url : organization.save,              //controller "save"
				params : form.getValues(),
				callback : function(json) {
					organization.addWindow.hide();
					organization.alwaysFun();
					organization.store.reload();
				}
			});
};
organization.delFun = function() {
	var record = organization.grid.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : organization.del + record.data.id,
								callback : function(json) {
									organization.alwaysFun();
									organization.store.reload();
								}
							});
				}
			});
};
/**组织机构树*/
organization.treePanel = new Ext.tree.TreePanel({
			title : '组织机构',
			region : 'west',
			split : true,
			minSize : 200,
			maxSize : 900,
			useArrows : true,
			autoScroll : true,
			width : '20%',
			animate : true,
			enableDD : true,
			containerScroll : true,
			rootVisible : false,
			buttonAlign : 'left',
			frame : false,
			disabled : false,
			root : {
				nodeType : 'async'
			},
			dataUrl : organization.allModules,
			listeners : {
				'click': function (node, e) { // 点击事件
					// 去除前缀
					var orgId="";
					orgId += node.id.replace("_chmesorg_", "");
					organization.store.load({params:{orgId:orgId}});
		        }
			}
		});
organization.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'border',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [organization.grid, organization.treePanel]
		});

