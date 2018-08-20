Ext.ns("Ext.changhong.listAnalyze"); // 自定义一个命名空间
listAnalyze = Ext.changhong.listAnalyze; // 定义命名空间的别名
listAnalyze = {
	all : ctx + '/listAnalyze/all',// 加载所有
	save : ctx + "/listAnalyze/save",//保存
	del : ctx + "/listAnalyze/del/",//删除
	exportExcel : ctx + "/listAnalyze/exportExcel",//导出excel文件
	empcode : empCode,  //专家编号
	pageSize : 50// 每页显示的记录数
};
/** 改变页的combo */
listAnalyze.pageSizeCombo = new Share.pageSizeCombo({
			value : '50',
			listeners : {
				select : function(comboBox) {
					listAnalyze.pageSize = parseInt(comboBox.getValue());
					listAnalyze.bbar.pageSize = parseInt(comboBox.getValue());
					listAnalyze.store.baseParams.limit = listAnalyze.pageSize;
					listAnalyze.store.baseParams.start = 0;
					listAnalyze.store.load();
				}
			}
		});
// 覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准
listAnalyze.pageSize = parseInt(listAnalyze.pageSizeCombo.getValue());
/** 基本信息-数据源 */
listAnalyze.store = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
			baseParams : {
				start : 0,
				limit : listAnalyze.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url :listAnalyze.all
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'normcode', 'empcode','normname', 'score','companycode']),
			listeners : {
				'load' : function(store, records, options) {
					listAnalyze.alwaysFun();
				}
			}
		});
/** 基本信息-选择模式 */
listAnalyze.selModel = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					listAnalyze.deleteAction.enable();
					listAnalyze.editAction.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					listAnalyze.alwaysFun();
				}
			}
		});
/** 基本信息-数据列 */
listAnalyze.colModel = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true,
				width : 140
			},
			columns : [listAnalyze.selModel, {
						header : '指标ID',
						dataIndex : 'id',
						width:200
					},  {
						header : '指标名称（共38项）',
						dataIndex : 'normname',
						width:500		
					},  {
						header : '得分',
						dataIndex : 'score',
					}]
		});
/** 新建 */
listAnalyze.addAction = new Ext.Action({
			text : '新建',
			iconCls : 'user_add',
			handler : function() {
				listAnalyze.addWindow.setIconClass('user_add'); // 设置窗口的样式
				listAnalyze.addWindow.setTitle('新建方案'); // 设置窗口的名称
				listAnalyze.addWindow.show().center(); // 显示窗口
				listAnalyze.formPanel.getForm().reset(); // 清空表单里面的元素的值.
			}
		});
/** 编辑 */
listAnalyze.editAction = new Ext.Action({
			text : '编辑',
			iconCls : 'user_edit',
			disabled : true,
			handler : function() {
				var record = listAnalyze.grid.getSelectionModel().getSelected();
				listAnalyze.addWindow.setIconClass('user_edit'); // 设置窗口的样式
				listAnalyze.addWindow.setTitle('编辑方案'); // 设置窗口的名称
				listAnalyze.addWindow.show().center();
				listAnalyze.formPanel.getForm().reset();
				listAnalyze.formPanel.getForm().loadRecord(record);
			}
		});
/** 删除 */
listAnalyze.deleteAction = new Ext.Action({
			text : '删除',
			iconCls : 'user_delete',
			disabled : true,
			handler : function() {
				listAnalyze.delFun();
			}
		});

/**打印文件**/
listAnalyze.printer = new Ext.Action({
	text:'导出excel文件',
	iconCls:'printer',
	//disabled:true,
	handler: function(){
		listAnalyze.printFun();      
	}
})

listAnalyze.empCodeCombox = new Ext.form.ComboBox({
	fieldLabel : '专家编号',
	iconCls : 'search',
	hiddenName : 'empcode',
	name : 'empcode',
	triggerAction : 'all',
	mode : 'local',
	store : new Ext.data.ArrayStore({
				fields : ['v', 't'],
				data : Share.map2Ary(listAnalyze.empcode)
			}),
	valueField : 'v',
	value: 1,
	displayField : 't',
	editable : false,
	anchor : '99%'
});
/**
//计算总得分
listAnalyze.summary = function(){
	var sum=0;
	for(var i=0,len=listAnalyze.store.data.length;i<len;i++){
		var data =listAnalyze.store.getAt(i).data;//data就是对应record的一个一个的对象
		score=data.score;//获取的就是该对象dataIndex属性对应的值
		sum=sum+score;
	}
};
**/

/** 顶部工具栏 */
listAnalyze.tbar = [listAnalyze.addAction, '-', listAnalyze.editAction, '-',listAnalyze.deleteAction, '-', listAnalyze.empCodeCombox,'-',listAnalyze.printer,'-'
	];

/** 底部工具条 **/
listAnalyze.bbar = new Ext.PagingToolbar({
			pageSize :listAnalyze.pageSize,
			store : listAnalyze.store,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', listAnalyze.pageSizeCombo]
		});
/** 基本信息-表格 */
listAnalyze.grid = new Ext.grid.GridPanel({
			store : listAnalyze.store,
			bodystyle:"background:#00FF00",
			colModel : listAnalyze.colModel,
			selModel : listAnalyze.selModel,
			title:'综合得分表',
			tbar : listAnalyze.tbar,
			bbar : listAnalyze.bbar,
			enableColumnMove:true,               
			enableColumnResize:true,   
			autoScroll : 'auto',
			region : 'center',
			loadMask : true,
			iconCls:'icon-grid',
			// autoExpandColumn :'fieldDesc',
			stripeRows : true,
			listeners : {},
			viewConfig : {}
		});

/** 基本信息-详细信息的form */
listAnalyze.formPanel = new Ext.form.FormPanel({
			frame : false,
			title : '字段信息',
			bodyStyle : 'padding:10px;border:0px',
			labelWidth : 80,
			defaultType : 'textfield',
			items : [{
						fieldLabel : '指标ID',
						name : 'id',
						anchor : '99%'
					},
					{
						fieldLabel : '指标名称',
						maxLength : 300,
						name : 'normname',
						anchor : '99%'
					},{
						fieldLabel : '指标编号',
						//xtype : 'field',
						maxLength : 20,
						name : 'normcode',
						anchor : '99%'
					}]
		});
/** 编辑新建窗口 */
listAnalyze.addWindow = new Ext.Window({
			layout : 'fit',
			width : 400,
			height : 380,
			closeAction : 'hide',
			plain : true,
			modal : true,
			resizable : true,
			items : [listAnalyze.formPanel],
			buttons : [{
						text : '保存',
						handler : function() {
							listAnalyze.saveFun();
						}
					}, {
						text : '重置',
						handler : function() {
							var form = listAnalyze.formPanel.getForm();
							var id = form.findField("id").getValue();
							form.reset();
							if (id != '')
								form.findField("id").setValue(id);
						}
					}]
		});
listAnalyze.alwaysFun = function() {
	Share.resetGrid(listAnalyze.grid);
	listAnalyze.deleteAction.disable();
	listAnalyze.editAction.disable();
};

/**创建打印函数**/
listAnalyze.printFun = function(){
	Ext.Ajax.request({
		url : listAnalyze.exportExcel,
		method : 'get',
		success : function(response, options) {
			Ext.Msg.alert('提示', '<span style="color:red"><b>已成功导出到E盘根目录中的score.xls！</b></span>', function() {});
		},
		failure : function() {
		}
	});
};


listAnalyze.saveFun = function() {
	var form = listAnalyze.formPanel.getForm();
	if (!form.isValid()) {
		return;
	}
	// 发送请求
	Share.AjaxRequest({
				url : listAnalyze.save,
				params : form.getValues(),
				callback : function(json) {
					listAnalyze.addWindow.hide();
					listAnalyze.alwaysFun();
					listAnalyze.store.reload();
				}
			});
};
listAnalyze.delFun = function() {
	var record = listAnalyze.grid.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : listAnalyze.del + record.data.id,
								callback : function(json) {
									listAnalyze.alwaysFun();
									listAnalyze.store.reload();
								}
							});
				}
			});
};
listAnalyze.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'fit',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [listAnalyze.grid]
		});