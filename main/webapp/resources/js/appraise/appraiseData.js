Ext.ns("Ext.changhong.appraiseData"); // 自定义一个命名空间
appraiseData = Ext.changhong.appraiseData; // 定义命名空间的别名
appraiseData = {
	all_data : ctx + '/appraiseData/all_data',// 加载所有数据类文件
	all_file:  ctx + '/appraiseData/all_file', //加载所有文本类文件
	save_file : ctx + "/appraiseData/save_file",// 保存文本类文件
	save_data : ctx +  "/appraiseData/save_data",  // 保存数据类文件
	del_data : ctx + "/appraiseData/del_data/",// 删除
	del_file : ctx + "/appraiseData/del_file/",// 删除
	allModules : ctx + "/appraiseNorm.allModules",// 系统菜单
	schemetype : schemetype,
	fileNorm : '0',
	myModules : ctx + "/module/",
	childNodes : '',
	pageSize : 20
	// 每页显示的记录数
};
/** 数据类控件改变页的combo */
appraiseData.pageSizeCombo_data = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					appraiseData.pageSize = parseInt(comboBox.getValue());
					appraiseData.bbar_data.pageSize = parseInt(comboBox.getValue());
					appraiseData.store_data.baseParams.limit = appraiseData.pageSize;
					appraiseData.store_data.baseParams.start = 0;
					appraiseData.store_data.load();
				}
			}
		});

/** 文本类控件改变页的combo */
appraiseData.pageSizeCombo_file = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					appraiseData.pageSize = parseInt(comboBox.getValue());
					appraiseData.bbar_file.pageSize = parseInt(comboBox.getValue());
					appraiseData.store_file.baseParams.limit = appraiseData.pageSize;
					appraiseData.store_file.baseParams.start = 0;
					appraiseData.store_file.load();
				}
			}
		});

// 覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准 //数据类
appraiseData.pageSize_data = parseInt(appraiseData.pageSizeCombo_data.getValue());

//覆盖已经设置的。具体设置以当前页面的pageSizeCombo为准 //文本类类
appraiseData.pageSize_file = parseInt(appraiseData.pageSizeCombo_file.getValue());


/** 1.基本信息-数据类文件数据源 */
appraiseData.store_data = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
				baseParams : {
				start : 0,
				limit : 20
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseData.all_data                             //controller
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'datatype', 'datavalue', 'unitsymbol', 'normcode','remake','companycode','normname']),   //数据类文件字段
			listeners : {
				'load' : function(store, records, options) {
					appraiseData.alwaysFun_data();
				}
			}
		});
/** 原store2.基本信息-文本类文件数据源 */
appraiseData.store_file = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
				baseParams : {
				start : 0,
				limit : 20
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseData.all_file                  //controller
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'filename', 'filepath', 'filecontext', 'normcode','remake','companycode','normname']),  //文本类数据字段
			listeners : {
				'load' : function(store, records, options) {
					appraiseData.alwaysFun_file();
				}
			}
		});

/** 基本信息-选择模式 数据类 */
appraiseData.selModel_data = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					appraiseData.deleteAction_data.enable();
					appraiseData.editAction_data.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					appraiseData.alwaysFun_data();
				}
			}
		});
/** 基本信息-选择模式 文本类 */
appraiseData.selModel_file = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				'rowselect' : function(selectionModel, rowIndex, record) {
					appraiseData.deleteAction_file.enable();
				},
				'rowdeselect' : function(selectionModel, rowIndex, record) {
					appraiseData.alwaysFun_file();
				}
			}
		});
/** 基本信息-数据列 */
appraiseData.colModel_file = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true
			},
			columns : [appraiseData.selModel_file, {
						hidden : true,
						header : 'ID',
						dataIndex : 'id'
					}, {
						header : '文件名称',
						width : 300,
						dataIndex : 'filename'
					}, {
						header : "操作",
						align : 'center',
						width : 100,
						dataIndex : 'filepath',//这边gird对应的是传过来的文件名
						renderer : function(value, metaData, record) {
							return "<a href='"+ ctx+value +"'>下载预览</a>";
						}
					}]
		});
/** 基本信息-数据列 */
appraiseData.colModel_data = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true,
				width : 140
			},
			columns : [appraiseData.selModel_data, {
						hidden : true,
						header : 'ID',
						dataIndex : 'id'
					}, {
						header : '数据类型',
						dataIndex : 'datatype'
					}, {
						header : '数据量',
						dataIndex : 'datavalue'
					}, {
						header : '单位符号',
						dataIndex : 'unitsymbol'
					}, {
						header : '备注',
						dataIndex : 'remake'
					}]
		});

/** 新建 */
appraiseData.addAction_data = new Ext.Action({
			text : '新建',
			iconCls : 'role_add',
			handler : function() {
				appraiseData.addWindow_data.setIconClass('role_add'); // 设置窗口的样式
				appraiseData.addWindow_data.setTitle('新建角色'); // 设置窗口的名称
				appraiseData.addWindow_data.show().center(); // 显示窗口
				appraiseData.formPanel_data.getForm().reset(); // 清空表单里面的元素的值.
			}
		});
/** 编辑 */
appraiseData.editAction_data = new Ext.Action({
			text : '编辑',
			iconCls : 'role_edit',
			disabled : true,
			handler : function() {
				var record = appraiseData.grid_data.getSelectionModel().getSelected();
				appraiseData.addWindow_data.setIconClass('role_edit'); // 设置窗口的样式
				appraiseData.addWindow_data.setTitle('编辑角色'); // 设置窗口的名称
				appraiseData.addWindow_data.show().center();
				appraiseData.formPanel_data.getForm().reset();
				appraiseData.formPanel_data.getForm().loadRecord(record);
			}
		});
/** 删除 */
appraiseData.deleteAction_file = new Ext.Action({
			text : '删除',
			iconCls : 'role_delete',
			disabled : true,
			handler : function() {
				appraiseData.delFun_file();
			}
		});

/** 删除 */
appraiseData.deleteAction_data = new Ext.Action({
			text : '删除',
			iconCls : 'role_delete',
			disabled : true,
			handler : function() {
				appraiseData.delFun_data();
			}
		});

/** 上传文件 */
appraiseData.uploadAction_file = new Ext.Action({
			text : '上传',
			iconCls : 'synchro',
			handler : function() {
				new Ext.Window({
					width : 800,
					title : '上传文件',
					height : 300,
					layout : 'fit',
					items : [{
							xtype : 'SWFUploader',
							border : false,
							fileSize : 1024 * 550,// 限制文件大小550MB
							uploadUrl : ctx + '/fileupload',
							flashUrl : ctx + '/resources/swfupload/swfupload.swf',
							filePostName : 'file', // 后台接收参数
							fileTypes : '*.*',// 可上传文件类型
							postParams : {
								fileNormCode : appraiseData.fileNorm
						    }
					    }],
					 listeners : {'hide':{fn:appraiseData.closeWinFile}},   
				}).show();
			}
		});

/** 查询 数据类*/  
appraiseData.searchField_data = new Ext.ux.form.SearchField({
			store : appraiseData.store_data,
			paramName : 'datatype',
			emptyText : '请输入名称',
			style : 'margin-left: 5px;'
		});

/** 查询 文本类*/  
appraiseData.searchField_file = new Ext.ux.form.SearchField({
			store : appraiseData.store_file,
			paramName : 'filename',
			emptyText : '请输入名称',
			style : 'margin-left: 5px;'
		});

/** 顶部工具栏 */
appraiseData.tbar_file = [{ 
	xtype: "treecombo", 
	fieldLabel: "评价指标",
	valId:'cbvv',   
	name:'acad',
	width : 150,
	url: appraiseData.allModules, 
	anchor: "99%",
	listeners:{
		'select':function(c,r){
			appraiseData.fileNorm = r.id;
			appraiseData.uploadAction_file.enable();
		}
	}
},'-',appraiseData.uploadAction_file,'-',appraiseData.deleteAction_file,'-', appraiseData.searchField_file];
/** 顶部工具栏 */
appraiseData.tbar_data = [appraiseData.addAction_data, '-', appraiseData.editAction_data, '-', appraiseData.deleteAction_data, '-',
		appraiseData.searchField_data];

/** 底部工具条 */
appraiseData.bbar_file = new Ext.PagingToolbar({
			pageSize : appraiseData.pageSize,       //pagesize默认为20
			store : appraiseData.store_file,       
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', appraiseData.pageSizeCombo_file]
		});
/** 底部工具条 */
appraiseData.bbar_data = new Ext.PagingToolbar({
			pageSize : appraiseData.pageSize,           //pagesize默认为20
			store : appraiseData.store_data,       
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', appraiseData.pageSizeCombo_data]
		});

/** 基本信息-文件类表格 */
appraiseData.grid_file = new Ext.grid.GridPanel({
	title : '文件列表',
	store : appraiseData.store_file,
	colModel : appraiseData.colModel_file,
	selModel : appraiseData.selModel_file,
	tbar : appraiseData.tbar_file,
	bbar : appraiseData.bbar_file,
	collapsible : true,
	split:true,
	autoScroll : 'auto',
	region : 'center',
	loadMask : true,
	// autoExpandColumn :'roleDesc',
	stripeRows : true,
	listeners : {
		'cellclick' : function(obj, rowIndex, columnIndex, e) {
			var record = appraiseData.grid_file.getSelectionModel().getSelected();
		}
	},
	viewConfig : {}
});

/** 基本信息-表格 */
appraiseData.grid_data = new Ext.grid.GridPanel({
	title : '数据列表',
	region : 'east',
	width : '60%',
	store : appraiseData.store_data,
	colModel : appraiseData.colModel_data,
	selModel : appraiseData.selModel_data,
	tbar : appraiseData.tbar_data,
	bbar : appraiseData.bbar_data,
	collapsible : true,
	autoScroll : 'auto',
	loadMask : true,
	stripeRows : true,
	listeners : {
		'cellclick' : function(obj, rowIndex, columnIndex, e) {
			var record = appraiseData.grid_data.getSelectionModel().getSelected();
		}
	},
	viewConfig : {}
});
appraiseData.typeCombo = new Ext.form.ComboBox({
	fieldLabel : '评价方案',
	hiddenName : 'type',
	name : 'type',
	triggerAction : 'all',
	mode : 'local',
	store : new Ext.data.ArrayStore({
				fields : ['v', 't'],
				data : Share.map2Ary(appraiseData.schemetype)
			}),
	valueField : 'v',
	displayField : 't',
	editable : false,
	anchor : '40%'
});

/** 基本信息-详细信息的form 数据类*/
appraiseData.formPanel_data = new Ext.form.FormPanel({
			frame : false,
			title : '数据信息',
			bodyStyle : 'padding:10px;border:0px',
			labelWidth : 80,
			defaultType : 'textfield',
			items : [
					appraiseData.typeCombo,
					{
						xtype : 'hidden',
						fieldLabel : 'ID',
						name : 'id',
						anchor : '99%'
					},
					{ 
						xtype: "hidden", 
						fieldLabel: "指标编号隐藏值",  //没有这个字段
						id:'combHiddenValue1',
						name:'hiddenValue1',
						anchor: "99%"
					}, { 
						xtype: "treecombo", 
						fieldLabel: "评价指标2",
						valId:'combHiddenValue1',   
						name:'normname',
						url: appraiseData.allModules, 
						anchor: "99%"
					},
					{
						fieldLabel : '数据类型',
						maxLength : 64,
						allowBlank : false,
						name : 'datatype',
						anchor : '99%'
					}, 
					{
						fieldLabel : '数据量',
						maxLength : 64,
						allowBlank : false,
						name : 'datavalue',
						anchor : '99%'
					}, 
					{
						fieldLabel : '单位符号',
						maxLength : 64,
						allowBlank : false,
						name : 'unitsymbol',
						anchor : '99%'
					}, 
					{
						xtype : 'textarea',
						fieldLabel : '备注',
						maxLength : 128,
						height : 100,
						name : 'remake',
						anchor : '99%'
					}]
		});

/** 编辑数据类界面新建窗口 */
appraiseData.addWindow_data = new Ext.Window({
			layout : 'fit',
			width : 500,
			height : 400,
			closeAction : 'hide',
			plain : true,
			modal : true,
			resizable : true,
			items : [appraiseData.formPanel_data],
			buttons : [{
						text : '保存',
						handler : function() {
							appraiseData.saveFun_data();
						}
					}, {
						text : '重置',
						handler : function() {
							var form = appraiseData.formPanel_data.getForm();
							var id = form.findField("id").getValue();
							form.reset();
							if (id != '')
								form.findField("id").setValue(id);
						}
					}]
		});

appraiseData.alwaysFun_data = function() {
	Share.resetGrid(appraiseData.grid_data);
	appraiseData.deleteAction_data.disable();
	appraiseData.editAction_data.disable();
};
appraiseData.alwaysFun_file = function() {
	Share.resetGrid(appraiseData.grid_file);
	appraiseData.deleteAction_file.disable();

};

appraiseData.saveFun_data = function() {
	var form = appraiseData.formPanel_data.getForm();
	if (!form.isValid()) {
		return;
	}
	// 数字类发送请求
	Share.AjaxRequest({
				url : appraiseData.save_data,                       //controller
				params : form.getValues(),
				callback : function(json) {
					appraiseData.addWindow_data.hide();
					appraiseData.alwaysFun_data();
					appraiseData.store_data.reload();
				}
			});
};

appraiseData.delFun_file = function() {
	var record = appraiseData.grid_file.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({
								url : appraiseData.del_file + record.data.id,             //controller
								callback : function(json) {
									appraiseData.alwaysFun_file();
									appraiseData.store_file.reload();
								}
							});
				}
			});
};
appraiseData.closeWinFile = function() {
	appraiseData.store_file.reload();
};
appraiseData.delFun_data = function() {
	var record = appraiseData.grid_data.getSelectionModel().getSelected();
	Ext.Msg.confirm('提示', '确定要删除这条记录吗?', function(btn, text) {
				if (btn == 'yes') {
					// 发送请求
					Share.AjaxRequest({						
								url : appraiseData.del_data + record.data.id,
								callback : function(json) {
									appraiseData.alwaysFun_data();
									appraiseData.store_data.reload();
								}
							});
				}
			});
};
appraiseData.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'border',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [appraiseData.grid_data, appraiseData.grid_file]
		});