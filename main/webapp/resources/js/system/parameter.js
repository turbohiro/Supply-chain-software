Ext.ns("Ext.changhong.parameter"); // 自定义一个命名空间
parameter = Ext.changhong.parameter; // 定义命名空间的别名
parameter = {
	all : ctx + '/parameter/all',// 加载所有
	save : ctx + "/parameter/save",//保存
	del : ctx + "/parameter/del/",//删除
	synchro : ctx + "/parameter/synchro",//内存同步
	isshow : isshow,
	partype : partype,
	pageSize : 20 // 每页显示的记录数
};
/** 改变页的combo */
parameter.pageSizeCombo = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					parameter.pageSize = parseInt(comboBox.getValue());
					parameter.bbar.pageSize = parseInt(comboBox.getValue());
					parameter.store.baseParams.limit = parameter.pageSize;
					parameter.store.baseParams.start = 0;
					parameter.store.load();
				}
			}
		});
// 覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准
parameter.pageSize = parseInt(parameter.pageSizeCombo.getValue());
/** 基本信息-数据源 */
parameter.store = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
			baseParams : {
				start : 0,
				limit : parameter.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : parameter.all
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'bigclass', 'smallclass', 'viewtype', 'parameterdesc',
				'parametervalue', 'valuedesc', 'type', 'vierflag', 'sequence',
					'modifyuser', 'modifydate']),
			listeners : {
				'load' : function(store, records, options) {
					parameter.alwaysFun();
				}
			}
		});
/** 基本信息-选择模式 */
parameter.selModel = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					parameter.deleteAction.enable();
					parameter.editAction.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					parameter.alwaysFun();
				}
			}
		});
/** 基本信息-数据列 */
parameter.colModel = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true,
				width : 140
			},
			columns : [parameter.selModel, {
						hidden : true,
						header : 'ID',
						dataIndex : 'id'
					}, {
						header : '大分类',
						dataIndex : 'bigclass'
					}, {
						header : '小分类',
						dataIndex : 'smallclass'
					}, {
						header : '名称',
						dataIndex : 'viewtype'
					}, {
						header : '名称描述',
						dataIndex : 'parameterdesc'
					}, {
						header : '值',
						dataIndex : 'parametervalue'
					}, {
						header : '值描述',
						dataIndex : 'valuedesc'
					}, {
						header : '类型',
						dataIndex : 'type'
					}, {
						// (0:显示;1不显示)
						header : '是否显示',
						dataIndex : 'vierflag',
						renderer : function(v) {
							return Share.map(v,parameter.isshow , '');
						}
					}, {
						header : '序号',
						dataIndex : 'sequence'
					}, {
						header : '修改人',
						dataIndex : 'modifyuser'
					}, {
						header : '修改时间',
						dataIndex : 'modifydate'
					}]
		});
/** 新建 */
parameter.addAction = new Ext.Action({
			text : '新建',
			iconCls : 'parameter_add',
			handler : function() {
				parameter.addWindow.setIconClass('parameter_add'); // 设置窗口的样式
				parameter.addWindow.setTitle('新建字段'); // 设置窗口的名称
				parameter.addWindow.show().center(); // 显示窗口
				parameter.formPanel.getForm().reset(); // 清空表单里面的元素的值.
				parameter.enabledCombo.clearValue();
				parameter.typeCombo.clearValue();
			}
		});
/** 编辑 */
parameter.editAction = new Ext.Action({
			text : '编辑',
			iconCls : 'parameter_edit',
			disabled : true,
			handler : function() {
				var record = parameter.grid.getSelectionModel().getSelected();
				parameter.addWindow.setIconClass('parameter_edit'); // 设置窗口的样式
				parameter.addWindow.setTitle('编辑字段'); // 设置窗口的名称
				parameter.addWindow.show().center();
				parameter.formPanel.getForm().reset();
				parameter.formPanel.getForm().loadRecord(record);
			}
		});
/** 删除 */
parameter.deleteAction = new Ext.Action({
			text : '删除',
			iconCls : 'parameter_delete',
			disabled : true,
			handler : function() {
				parameter.delFun();
			}
		});
/** 查询 */
parameter.searchField = new Ext.ux.form.SearchField({
			store : parameter.store,
			paramName : 'fieldName',
			emptyText : '请输入名称',
			style : 'margin-left: 5px;'
		});


/** 顶部工具栏 */
parameter.tbar = [parameter.addAction, '-', parameter.editAction, '-',
		parameter.deleteAction, '-', parameter.searchField];
/** 底部工具条 */
parameter.bbar = new Ext.PagingToolbar({
			pageSize : parameter.pageSize,
			store : parameter.store,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', parameter.pageSizeCombo]
		});
/** 基本信息-表格 */
parameter.grid = new Ext.grid.GridPanel({
			store : parameter.store,
			colModel : parameter.colModel,
			selModel : parameter.selModel,
			tbar : parameter.tbar,
			bbar : parameter.bbar,
			autoScroll : 'auto',
			region : 'center',
			loadMask : true,
			// autoExpandColumn :'fieldDesc',
			stripeRows : true,
			listeners : {},
			viewConfig : {}
		});
parameter.enabledCombo = new Ext.form.ComboBox({
			fieldLabel : '是否显示',
			hiddenName : 'vierflag',
			name : 'vierflag',
			triggerAction : 'all',
			mode : 'local',
			store : new Ext.data.ArrayStore({
						fields : ['v', 't'],
						data : Share.map2Ary(parameter.isshow)
					}),
			valueField : 'v',
			displayField : 't',
			allowBlank : false,
			editable : false,
			anchor : '99%'
		});
parameter.typeCombo = new Ext.form.ComboBox({
	fieldLabel : '类型',
	hiddenName : 'type',
	name : 'type',
	triggerAction : 'all',
	mode : 'local',
	store : new Ext.data.ArrayStore({
				fields : ['v', 't'],
				data : Share.map2Ary(parameter.partype)
			}),
	valueField : 'v',
	displayField : 't',
	editable : false,
	anchor : '99%'
});
/** 基本信息-详细信息的form */
parameter.formPanel = new Ext.form.FormPanel({
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
						fieldLabel : '大分类',
						maxLength : 64,
						name : 'bigclass',
						anchor : '99%'
					},{
						fieldLabel : '小分类',
						maxLength : 128,
						name : 'smallclass',
						anchor : '99%'
					},{
						fieldLabel : '名称',
						maxLength : 64,
						allowBlank : false,
						name : 'viewtype',
						anchor : '99%'
					},{
						fieldLabel : '名称描述',
						maxLength : 128,
						allowBlank : false,
						name : 'parameterdesc',
						anchor : '99%'
					},{
						fieldLabel : '值',
						maxLength : 128,
						allowBlank : false,
						name : 'parametervalue',
						anchor : '99%'
					},{
						fieldLabel : '值描述',
						maxLength : 128,
						allowBlank : false,
						name : 'valuedesc',
						anchor : '99%'
					}, parameter.enabledCombo,
					   parameter.typeCombo, {
						fieldLabel : '序号',
						xtype : 'numberfield',
						maxLength : 2,
						name : 'sequence',
						anchor : '99%'
					}]
		});
/** 编辑新建窗口 */
parameter.addWindow = new Ext.Window({
			layout : 'fit',
			width : 400,
			height : 380,
			closeAction : 'hide',
			plain : true,
			modal : true,
			resizable : true,
			items : [parameter.formPanel],
			buttons : [{
						text : '保存',
						handler : function() {
							parameter.saveFun();
						}
					}, {
						text : '重置',
						handler : function() {
							var form = parameter.formPanel.getForm();
							var id = form.findField("id").getValue();
							form.reset();
							if (id != '')
								form.findField("id").setValue(id);
						}
					}]
		});
parameter.alwaysFun = function() {
	Share.resetGrid(parameter.grid);
	parameter.deleteAction.disable();
	parameter.editAction.disable();
};
parameter.saveFun = function() {
	var form = parameter.formPanel.getForm();
	if (!form.isValid()) {
		return;
	}
	// 发送请求
	Share.AjaxRequest({
				url : parameter.save,
				params : form.getValues(),
				callback : function(json) {
					parameter.addWindow.hide();
					parameter.alwaysFun();
					parameter.store.reload();
					//内存同步
					Share.AjaxRequest({
						url : parameter.synchro,
						callback : function(json) {
						}
					});
				}
			});
};
parameter.delFun = function() {
	var record = parameter.grid.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : parameter.del + record.data.id,
								callback : function(json) {
									parameter.alwaysFun();
									parameter.store.reload();
								}
							});
				}
			});
};
parameter.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'border',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [parameter.grid]
		});