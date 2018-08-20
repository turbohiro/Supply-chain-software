Ext.ns("Ext.changhong.appraiseScore"); // 自定义一个命名空间
appraiseScore = Ext.changhong.appraiseScore; // 定义命名空间的别名
appraiseScore = {
	all : ctx + '/appraiseScore/all',// 加载所有
	all_score : ctx + '/appraiseScore/all_score',// 加载所有
	querry_data : ctx + '/appraiseScore/querry_data',// 加载所有数据类文件
	querry_file:  ctx + '/appraiseScore/querry_file', //加载所有文本类文件
	querry_scorePrinciple : ctx + '/appraiseScore/querry_scorePrinciple',//加载所有评价标准文件
	save : ctx + "/appraiseScore/save",// 保存
	del : ctx + "/appraiseScore/del/",// 删除
	allModules : ctx + "/appraiseNorm.allModules",// 组织机构
	formNormId : '',
	pageSize : 20
	// 每页显示的记录数
};
/** 改变页的combo (数据类文件) */
appraiseScore.pageSizeCombo_data = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					appraiseScore.pageSize = parseInt(comboBox.getValue());
					appraiseScore.bbar_data.pageSize = parseInt(comboBox.getValue());
					appraiseScore.store_data.baseParams.limit = appraiseScore.pageSize;
					appraiseScore.store_data.baseParams.start = 0;
					appraiseScore.store_data.load();
				}
			}
		});

/** 改变页的combo (文本类文件) */
appraiseScore.pageSizeCombo_file = new Share.pageSizeCombo({
			value : '20',
			listeners : {
				select : function(comboBox) {
					appraiseScore.pageSize = parseInt(comboBox.getValue());
					appraiseScore.bbar_file.pageSize = parseInt(comboBox.getValue());
					appraiseScore.store_file.baseParams.limit = appraiseScore.pageSize;
					appraiseScore.store_file.baseParams.start = 0;
					appraiseScore.store_file.load();
				}
			}
		});

/** 基本信息-数据源(专家得分） */
appraiseScore.store = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
				baseParams : {
				start : 0,
				limit : appraiseScore.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseScore.all
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'normcode', 'empcode', 'score', 'remake', 'companycode'])
		});

/** 基本信息-数据源（数据类文件） */
appraiseScore.store_data = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
			baseParams : {
			start : 0,
			limit : appraiseScore.pageSize
		},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseScore.querry_data
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'datatype', 'datavalue', 'unitsymbol', 'normcode', 'remake','companycode','normname']),
			listeners : {
				'load' : function(store, records, options) {
					appraiseScore.alwaysFun_data();
				}
			}
		});

/** 基本信息-评价标准（评价标准文件） */
appraiseScore.store_principle = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseScore.querry_scorePrinciple
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'scoreprinciple', 'normcode','companycode','normname'])		
		});

/** 基本信息-数据源（文本类文件） */
appraiseScore.store_file = new Ext.data.Store({
			autoLoad : true,
			remoteSort : true,
				baseParams : {
				start : 0,
				limit : appraiseScore.pageSize
			},
			proxy : new Ext.data.HttpProxy({// 获取数据的方式
				method : 'POST',
				url : appraiseScore.querry_file
			}),
			reader : new Ext.data.JsonReader({// 数据读取器
				totalProperty : 'results', // 记录总数
				root : 'rows' // Json中的列表数据根节点
			}, ['id', 'filename', 'filepath', 'filecontext', 'normcode', 'remake','companycode','normname']),
			listeners : {
				'load' : function(store, records, options) {
					appraiseScore.alwaysFun_file();
				}
			}
		});

/** 基本信息-选择模式 (只有单选框）*/
appraiseScore.selModel = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true
		});

/** 基本信息-数据列 （数据类文件）*/
appraiseScore.colModel_data = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true,
				width : 140
			},
			columns : [appraiseScore.selModel, {
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

/** 基本信息-数据列 （文本类文件）*/
appraiseScore.colModel_file = new Ext.grid.ColumnModel({
			defaults : {
				sortable : true
			},
			columns : [appraiseScore.selModel, {
				hidden : true,
				header : 'ID',
				dataIndex : 'id'
			}, {
				header : '文件名称',
				width : 500,
				dataIndex : 'filename'
			}, {
				header : "操作",
				align : 'center',
				width : 100,
				dataIndex : 'filepath',//这边gird对应的是传过来的文件名
				renderer : function(value, metaData, record) {
					return "<a href='"+ ctx+value +"' target='_blank'>下载预览</a>";
				}
			}]
		});
/** 底部工具条 （数据类文件） *//*
appraiseScore.bbar_data = new Ext.PagingToolbar({
			pageSize : appraiseScore.pageSize,
			store : appraiseScore.store_data,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', appraiseScore.pageSizeCombo_data]
		});

*//** 底部工具条 （文本类文件） *//*
appraiseScore.bbar_file = new Ext.PagingToolbar({
			pageSize : appraiseScore.pageSize,
			store : appraiseScore.store_file,
			displayInfo : true,
			// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			items : ['-', '&nbsp;', appraiseScore.pageSizeCombo_file]
		});*/

/** 基本信息-表格 */
appraiseScore.grid_file = new Ext.grid.GridPanel({
	title : '文件列表',
	flex : 3.5,
	store : appraiseScore.store_file,
	colModel : appraiseScore.colModel_file,
	selModel : appraiseScore.selModel,
	//bbar : appraiseScore.bbar_file,
	autoScroll : 'auto',
	region : 'center',
	loadMask : true,
	stripeRows : true,
	viewConfig : {}
});
appraiseScore.grid_data = new Ext.grid.GridPanel({
	title : '数据列表',
	flex : 3.5,
	store : appraiseScore.store_data,
	colModel : appraiseScore.colModel_data,
	selModel : appraiseScore.selModel,
	//bbar : appraiseScore.bbar_data,
	autoScroll : 'auto',
	region : 'south',
	loadMask : true,
	stripeRows : true,
	viewConfig : {}
});

appraiseScore.alwaysFun_data = function() {
	Share.resetGrid(appraiseScore.grid_data);
};

appraiseScore.alwaysFun_file = function() {
	Share.resetGrid(appraiseScore.grid_file);
};

appraiseScore.formPanel2 = new Ext.form.FormPanel({
	title : '专家评分',
	width : '100%',
	flex : 2,
	layout : 'column',
	bodyStyle : 'padding:5px;border:0px,margin-top:10px',
	defaultType : 'textfield',
	items : [ 
		{
			xtype : 'label',
			text : '评价打分:'
		},{
			maxLength : 64,
			allowBlank : false,
			name : 'score',
			anchor : '99%'
		},{
			xtype:'button',
			style:'background-color:green',
			text:'保存',
			handler:function(){
				appraiseScore.saveFun();				
			}
		},
		{
			xtype : 'label',
			text : '评价标准:'
		},
		{
			xtype:'textarea',
			fieldLabel:'评分标准',
			name:'scoreprinciple',
			width:600,
			height:80
		}
		]
});

/**评价指标树*/
appraiseScore.treePanel = new Ext.tree.TreePanel({
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
			dataUrl : appraiseScore.allModules,
			listeners : {
				'click': function (node, e) { // 点击事件
					// 去除前缀
					var normId="";
					normId += node.id.replace("_chmesnorm_", "");
					appraiseScore.store.load({params:{normId:normId}});
					appraiseScore.store_principle.load({params:{normId:normId}});
					appraiseScore.store_data.load({params:{normId:normId}});
					appraiseScore.store_file.load({params:{normId:normId}});
					//var re = appraiseScore.store;
					//appraiseScore.formPanel2.getForm().loadRecord(re);
					appraiseScore.formNormId = normId;
					appraiseScore.loadScore(normId);
					
					appraiseScore.loadPrinciple(normId);
		        }
			}
		});

appraiseScore.loadScore = function(normId) {
	// 发送请求
	Ext.Ajax.request({
				url : appraiseScore.all_score,
				params : {normId:normId},
				timeout : 60*1000,
				success : function(response, options) {	
					var json = Ext.decode(response.responseText);
					var form = appraiseScore.formPanel2.getForm();
					form.reset();
					form.findField("score").setValue(json.msg.score);	
				}	
			});
};

appraiseScore.loadPrinciple = function(normId) {
	// 发送请求
	Ext.Ajax.request({
				url : appraiseScore.querry_scorePrinciple,
				params : {normId:normId},
				timeout : 60*1000,
				success : function(response, options) {	
					var json = Ext.decode(response.responseText);
					var form = appraiseScore.formPanel2.getForm();
					//form.reset();
					form.findField("scoreprinciple").setValue(json.msg.scoreprinciple);	
				}	
			});
};
appraiseScore.saveFun = function() {
	
	form = appraiseScore.formPanel2.getForm();
	var Score= form.findField("score").getValue();
	if (!form.isValid()) {
		return;
	}
	// 发送请求
	Share.AjaxRequest({
				url : appraiseScore.save,
				params : {score:Score,normId:appraiseScore.formNormId},
				//params : parArr,
				callback : function(json) {
					appraiseScore.store.reload();
				}
			});
};

appraiseScore.myPanel1 = new Ext.Panel({
	region : 'center',
	layout : 'vbox',
	boder : false,
	height : index.tabPanel.getInnerHeight() - 1,
	items : [appraiseScore.formPanel2, appraiseScore.grid_data, appraiseScore.grid_file]
});
appraiseScore.myPanel = new Ext.Panel({
			id : param + '_panel',
			renderTo : param,
			layout : 'border',
			boder : false,
			height : index.tabPanel.getInnerHeight() - 1,
			items : [appraiseScore.myPanel1, appraiseScore.treePanel]
		});

