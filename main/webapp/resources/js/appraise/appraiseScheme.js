Ext.ns("Ext.changhong.appraiseScheme"); // 自定义一个命名空间
appraiseScheme = Ext.changhong.appraiseScheme; // 定义命名空间的别名
appraiseScheme = {
	all : ctx + '/appraiseScheme/all',// 加载所有
	save : ctx + "/appraiseScheme/save",//保存
	del : ctx + "/appraiseScheme/del/",//删除
	schemetype : schemeType,
	pageSize : 20 // 每页显示的记录数
};
/** 改变页的combo */
appraiseScheme.pageSizeCombo = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					appraiseScheme.pageSize = parseInt(comboBox.getValue());
					appraiseScheme.bbar.pageSize = parseInt(comboBox.getValue());
					appraiseScheme.store.baseParams.limit = appraiseScheme.pageSize;
					appraiseScheme.store.baseParams.start = 0;
					appraiseScheme.store.load();
				}
			}
		});
// 覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准
appraiseScheme.pageSize = parseInt(appraiseScheme.pageSizeCombo.getValue());
/** 基本信息-数据源 */
appraiseScheme.store = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
			baseParams : {
				start : 0,
				limit : appraiseScheme.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseScheme.all
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'schemecode', 'schemename', 'schemetype', 'remake',
				'companycode']),
			listeners : {
				'load' : function(store, records, options) {
					appraiseScheme.alwaysFun();
				}
			}
		});
/** 基本信息-选择模式 */
appraiseScheme.selModel = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					appraiseScheme.deleteAction.enable();
					appraiseScheme.editAction.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					appraiseScheme.alwaysFun();
				}
			}
		});
/** 基本信息-数据列 */
appraiseScheme.colModel = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true,
				width : 140
			},
			columns : [appraiseScheme.selModel, {
						hidden : true,
						header : 'ID',
						dataIndex : 'id'
					}, {
						header : '方案编号',
						dataIndex : 'schemecode'
					}, {
						header : '方案名称',
						dataIndex : 'schemename'
					}, {
						// (0:显示;1不显示)
						header : '方案类别',
						dataIndex : 'schemetype',
						renderer : function(v) {
							return Share.map(v,appraiseScheme.schemetype , '');
						}
					}, {
						header : '备注',
						dataIndex : 'remake'
					}]
		});
/** 新建 */
appraiseScheme.addAction = new Ext.Action({
			text : '新建',
			iconCls : 'user_add',
			handler : function() {
				appraiseScheme.addWindow.setIconClass('user_add'); // 设置窗口的样式
				appraiseScheme.addWindow.setTitle('新建方案'); // 设置窗口的名称
				appraiseScheme.addWindow.show().center(); // 显示窗口
				appraiseScheme.formPanel.getForm().reset(); // 清空表单里面的元素的值.
				appraiseScheme.typeCombo.clearValue();
			}
		});
/** 编辑 */
appraiseScheme.editAction = new Ext.Action({
			text : '编辑',
			iconCls : 'user_edit',
			disabled : true,
			handler : function() {
				var record = appraiseScheme.grid.getSelectionModel().getSelected();
				appraiseScheme.addWindow.setIconClass('user_edit'); // 设置窗口的样式
				appraiseScheme.addWindow.setTitle('编辑方案'); // 设置窗口的名称
				appraiseScheme.addWindow.show().center();
				appraiseScheme.formPanel.getForm().reset();
				appraiseScheme.formPanel.getForm().loadRecord(record);
			}
		});
/** 删除 */
appraiseScheme.deleteAction = new Ext.Action({
			text : '删除',
			iconCls : 'user_delete',
			disabled : true,
			handler : function() {
				appraiseScheme.delFun();
			}
		});
/** 查询 */
appraiseScheme.searchField = new Ext.ux.form.SearchField({
			store : appraiseScheme.store,
			paramName : 'fieldName',
			emptyText : '请输入名称',
			style : 'margin-left: 5px;'
		});


/** 顶部工具栏 */
appraiseScheme.tbar = [appraiseScheme.addAction, '-', appraiseScheme.editAction, '-',
		appraiseScheme.deleteAction, '-', appraiseScheme.searchField];
/** 底部工具条 */
appraiseScheme.bbar = new Ext.PagingToolbar({
			pageSize : appraiseScheme.pageSize,
			store : appraiseScheme.store,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', appraiseScheme.pageSizeCombo]
		});
/** 基本信息-表格 */
appraiseScheme.grid = new Ext.grid.GridPanel({
			store : appraiseScheme.store,
			colModel : appraiseScheme.colModel,
			selModel : appraiseScheme.selModel,
			tbar : appraiseScheme.tbar,
			bbar : appraiseScheme.bbar,
			autoScroll : 'auto',
			region : 'center',
			loadMask : true,
			// autoExpandColumn :'fieldDesc',
			stripeRows : true,
			listeners : {},
			viewConfig : {}
		});
appraiseScheme.typeCombo = new Ext.form.ComboBox({
	fieldLabel : '类型',
	hiddenName : 'schemetype',
	name : 'schemetype',
	triggerAction : 'all',
	mode : 'local',
	store : new Ext.data.ArrayStore({
				fields : ['v', 't'],
				data : Share.map2Ary(appraiseScheme.schemetype)
			}),
	valueField : 'v',
	displayField : 't',
	editable : false,
	anchor : '99%'
});
/** 基本信息-详细信息的form */
appraiseScheme.formPanel = new Ext.form.FormPanel({
			frame : false,
			title : '字段信息',
			bodyStyle : 'padding:10px;border:0px',
			labelWidth : 80,
			defaultType : 'textfield',
			items : [{
						xtype : 'hidden',
						fieldLabel : 'ID',
						name : 'id',
						anchor : '99%'
					},{
						fieldLabel : '方案编号',
						maxLength : 64,
						name : 'schemecode',
						anchor : '99%'
					},{
						fieldLabel : '方案名称',
						maxLength : 128,
						name : 'schemename',
						anchor : '99%'
					},appraiseScheme.typeCombo, {
						fieldLabel : '备注',
						//xtype : 'field',
						maxLength : 2,
						name : 'remake',
						anchor : '99%'
					}]
		});
/** 编辑新建窗口 */
appraiseScheme.addWindow = new Ext.Window({
			layout : 'fit',
			width : 400,
			height : 380,
			closeAction : 'hide',
			plain : true,
			modal : true,
			resizable : true,
			items : [appraiseScheme.formPanel],
			buttons : [{
						text : '保存',
						handler : function() {
							appraiseScheme.saveFun();
						}
					}, {
						text : '重置',
						handler : function() {
							var form = appraiseScheme.formPanel.getForm();
							var id = form.findField("id").getValue();
							form.reset();
							if (id != '')
								form.findField("id").setValue(id);
						}
					}]
		});
appraiseScheme.alwaysFun = function() {
	Share.resetGrid(appraiseScheme.grid);
	appraiseScheme.deleteAction.disable();
	appraiseScheme.editAction.disable();
};
appraiseScheme.saveFun = function() {
	var form = appraiseScheme.formPanel.getForm();
	if (!form.isValid()) {
		return;
	}
	// 发送请求
	Share.AjaxRequest({
				url : appraiseScheme.save,
				params : form.getValues(),
				callback : function(json) {
					appraiseScheme.addWindow.hide();
					appraiseScheme.alwaysFun();
					appraiseScheme.store.reload();
				}
			});
};
appraiseScheme.delFun = function() {
	var record = appraiseScheme.grid.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : appraiseScheme.del + record.data.id,
								callback : function(json) {
									appraiseScheme.alwaysFun();
									appraiseScheme.store.reload();
								}
							});
				}
			});
};
appraiseScheme.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'border',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [appraiseScheme.grid]
		});