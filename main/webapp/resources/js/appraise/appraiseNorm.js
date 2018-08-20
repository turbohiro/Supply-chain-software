Ext.ns("Ext.changhong.appraiseNorm"); // 自定义一个命名空间
appraiseNorm = Ext.changhong.appraiseNorm; // 定义命名空间的别名
appraiseNorm = {
	all : ctx + '/appraiseNorm/all',// 加载所有
	save : ctx + "/appraiseNorm/save",// 保存
	del : ctx + "/appraiseNorm/del/",// 删除
	allModules : ctx + "/appraiseNorm",// 组织机构
	childNodes : '',
	pageSize : 20
	// 每页显示的记录数
};
/** 改变页的combo */
appraiseNorm.pageSizeCombo = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					appraiseNorm.pageSize = parseInt(comboBox.getValue());
					appraiseNorm.bbar.pageSize = parseInt(comboBox.getValue());
					appraiseNorm.store.baseParams.limit = appraiseNorm.pageSize;
					appraiseNorm.store.baseParams.start = 0;
					appraiseNorm.store.load();
				}
			}
		});
// 覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准
appraiseNorm.pageSize = parseInt(appraiseNorm.pageSizeCombo.getValue());
/** 基本信息-数据源 */
appraiseNorm.store = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
				baseParams : {
				start : 0,
				limit : appraiseNorm.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseNorm.all
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'normname', 'normcode', 'schemecode', 'normcodefather', 'normlevel','remake','companycode']),
			listeners : {
				'load' : function(store, records, options) {
					appraiseNorm.alwaysFun();
				}
			}
		});
/** 基本信息-选择模式 */
appraiseNorm.selModel = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					appraiseNorm.deleteAction.enable();
					appraiseNorm.editAction.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					appraiseNorm.alwaysFun();
				}
			}
		});

/** 基本信息-数据列 */
appraiseNorm.colModel = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true,
				width : 140
			},
			columns : [appraiseNorm.selModel, {
						header : '指标编号',
						dataIndex : 'normcode'
					}, {
						header : '指标名称',
						dataIndex : 'normname'
					}, {
						header : '方案编号',
						dataIndex : 'schemecode'
					}, {
						header : '父指标编号',
						dataIndex : 'normcodefather',
						renderer : function(v) {
							if(v==1){
								return '已是最高机构';
							}else{
								var record = appraiseNorm.store.query('normcode',v,true,true);  ///不懂？？
								return record.items[0].data.normname;
							}
						}
					}, {
						header : '指标级别',
						dataIndex : 'normlevel'
					}, {
						header : '备注',
						dataIndex : 'remake'
					}]
		});
/** 新建 */
appraiseNorm.addAction = new Ext.Action({
			text : '新建',
			iconCls : 'role_add',
			handler : function() {
				appraiseNorm.addWindow.setIconClass('role_add'); // 设置窗口的样式
				appraiseNorm.addWindow.setTitle('新建指标'); // 设置窗口的名称
				appraiseNorm.addWindow.show().center(); // 显示窗口
				appraiseNorm.formPanel.getForm().reset(); // 清空表单里面的元素的值.
			}
		});
/** 编辑 */
appraiseNorm.editAction = new Ext.Action({
			text : '编辑',
			iconCls : 'role_edit',
			disabled : true,
			handler : function() {
				var record = appraiseNorm.grid.getSelectionModel().getSelected();
				appraiseNorm.addWindow.setIconClass('role_edit'); // 设置窗口的样式
				appraiseNorm.addWindow.setTitle('编辑指标'); // 设置窗口的名称
				appraiseNorm.addWindow.show().center();
				appraiseNorm.formPanel.getForm().reset();
				appraiseNorm.formPanel.getForm().loadRecord(record);
			}
		});
/** 删除 */
appraiseNorm.deleteAction = new Ext.Action({
			text : '删除',
			iconCls : 'role_delete',
			disabled : true,
			handler : function() {
				appraiseNorm.delFun();
			}
		});
/** 查询 */
appraiseNorm.searchField = new Ext.ux.form.SearchField({
			store : appraiseNorm.store,
			paramName : 'roleName',
			emptyText : '请输入名称',
			style : 'margin-left: 5px;'
		});
/** 顶部工具栏 */
appraiseNorm.tbar = [appraiseNorm.addAction, '-', appraiseNorm.editAction, '-', appraiseNorm.deleteAction, '-',
		appraiseNorm.searchField];
/** 底部工具条 */
appraiseNorm.bbar = new Ext.PagingToolbar({
			pageSize : appraiseNorm.pageSize,
			store : appraiseNorm.store,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', appraiseNorm.pageSizeCombo]
		});
/** 基本信息-表格 */
appraiseNorm.grid = new Ext.grid.GridPanel({
	title : '评价指标列表',
	store : appraiseNorm.store,
	colModel : appraiseNorm.colModel,
	selModel : appraiseNorm.selModel,
	tbar : appraiseNorm.tbar,
	bbar : appraiseNorm.bbar,
	autoScroll : 'auto',
	region : 'center',
	loadMask : true,
	stripeRows : true,
	viewConfig : {}
});
/** 基本信息-详细信息的form */
appraiseNorm.formPanel = new Ext.form.FormPanel({
			frame : false,
			title : '指标信息',
			bodyStyle : 'padding:10px;border:0px',
			labelWidth : 80,
			defaultType : 'textfield',
			items : [{
						fieldLabel : '指标编号',
						name : 'normcode',
						anchor : '99%'
					}, {
						fieldLabel: "指标名称",
						maxLength : 64,
						allowBlank : false,
						name : 'normname',
						anchor : '99%'
					}, {
						fieldLabel: "方案编号",
						maxLength : 64,
						allowBlank : false,
						name : 'schemecode',
						anchor : '99%'
		            }, { 
						xtype: "hidden", 
						fieldLabel: "父指标编号隐藏值",  //没有这个字段
						id:'combHiddenValue',
						name:'hiddenValue',
						anchor: "99%"
					}, { 
						xtype: "treecombo", 
						fieldLabel: "父指标编号",
						valId:'combHiddenValue',   
						name:'normcodefather',
						url: appraiseNorm.allModules, 
						anchor: "99%"
					}, {
						fieldLabel : '指标级别',
						maxLength : 64,
						allowBlank : false,
						name : 'normlevel',
						anchor : '99%'
					}, {
						fieldLabel : '备注',
						maxLength : 64,
						name : 'remake',
						anchor : '99%'
					}]
		});
/** 编辑新建窗口 */
appraiseNorm.addWindow = new Ext.Window({
			layout : 'fit',
			width : 500,
			height : 280,
			closeAction : 'hide',
			plain : true,
			modal : true,
			resizable : true,
			items : [appraiseNorm.formPanel],
			buttons : [{
						text : '保存',
						handler : function() {
							debugger;
							appraiseNorm.saveFun();
						}
					}, {
						text : '重置',
						handler : function() {
							var form = appraiseNorm.formPanel.getForm();
							var id = form.findField("id").getValue();
							form.reset();
							if (id != '')
								form.findField("id").setValue(id);   
						}
					}]
		});
appraiseNorm.alwaysFun = function() {
	Share.resetGrid(appraiseNorm.grid);
	appraiseNorm.deleteAction.disable();
	appraiseNorm.editAction.disable();
};
appraiseNorm.saveFun = function() {
	var form = appraiseNorm.formPanel.getForm();
	if (!form.isValid()) {
		return;
	}
	// 发送请求
	Share.AjaxRequest({
				url : appraiseNorm.save,
				params : form.getValues(),
				callback : function(json) {
					appraiseNorm.addWindow.hide();
					appraiseNorm.alwaysFun();
					appraiseNorm.store.reload();
				}
			});
};
appraiseNorm.delFun = function() {
	var record = appraiseNorm.grid.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : appraiseNorm.del + record.data.id,
								callback : function(json) {
									appraiseNorm.alwaysFun();
									appraiseNorm.store.reload();
								}
							});
				}
			});
};
/**组织机构树*/
appraiseNorm.treePanel = new Ext.tree.TreePanel({
			title : '评价指标',
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
			dataUrl : appraiseNorm.allModules,
			listeners : {
				'click': function (node, e) { // 点击事件
					debugger;
					// 去除前缀
					var normId="";
					normId += node.id.replace("_chmesnorm_", "");
					appraiseNorm.store.load({params:{normId:normId}});
		        }
			}
		});
appraiseNorm.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'border',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [appraiseNorm.grid, appraiseNorm.treePanel]
		});

