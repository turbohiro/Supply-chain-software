/**
 * 角色选择器
 */
var RoleSelector = {
	getView : function(callback,isSingle) {
		var gridPanel = this.initGridPanel(isSingle);
		var window = new Ext.Window({
			title : '角色选择',
			width : 630,
			height : 380,
			layout : 'fit',
			border : false,
			items : [gridPanel],
			modal : true,
			buttonAlign : 'center',
			buttons : [{
						iconCls : 'btn-ok',
						text : '确定',
						handler : function() {
							var grid = Ext.getCmp('RoleSelectorGrid');
							var rows = grid.getSelectionModel().getSelections();
							var roleIds = '';
							var roleNames = '';
							for (var i = 0; i < rows.length; i++) {

								if (i > 0) {
									roleIds += ',';
									roleNames += ',';
								}

								roleIds += rows[i].data.id;
								roleNames += rows[i].data.rolename;

							}

							if (callback != null) {
								callback.call(this, roleIds, roleNames);
							}
							window.close();
						}
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						handler : function() {
							window.close();
						}
					}]
		});
		return window;

	},

	initGridPanel : function(isSingle) {
		var sm=null;
		if(isSingle){
			var sm=new Ext.grid.CheckboxSelectionModel({singleSelect: true});
		}else{
			sm = new Ext.grid.CheckboxSelectionModel();
		}
		
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : "角色名称",
								dataIndex : 'rolename',
								width : 60
							}, {
								header : "角色描述",
								dataIndex : 'roledesc',
								width : 60
							}]
				});

		var store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : ctx + '/role/all'
							}),
					reader : new Ext.data.JsonReader({
								root : 'rows',
								totalProperty : 'results',
								id : 'id',
								fields : [{
											name : 'id',
											type : 'string'
										}, 'rolename', 'roledesc']
							})
				});
		store.load({
					params : {
						start : 0,
						limit : 10
					}
				});
		
		var toolbar = new Ext.Toolbar({
					id : 'AppRoleFootBar',
					height : 30,
					items : ['角色名称：', {
								name : 'roleName',
								xtype : 'textfield',
								id:'Q_roleName_S_LK',
								width : 200
							},' ',{
								xtype:'button',
								iconCls:'query',
								text:'查询',
								handler:function(){
									var roleName=Ext.getCmp('Q_roleName_S_LK').getValue();
									Ext.Ajax.request({
										url:ctx+'/role/all',
										params:{start:0,limit:10,roleName:roleName},
										method:'post',
										success:function(result,request){
											var data=Ext.util.JSON.decode(result.responseText);
											var grid=Ext.getCmp('RoleSelectorGrid');
											grid.getStore().loadData(data);
										},
										failure:function(result,request){
											//TODO
										}
									});
								}
							}

					]
				});
		
		/** 改变页的combo */
		var pageSizeCombo = new Share.pageSizeCombo({
					value : '10',
					listeners : {
						select : function(comboBox) {
							bbar.pageSize = parseInt(comboBox.getValue());
							role.store.baseParams.limit = parseInt(comboBox.getValue());
							role.store.baseParams.start = 0;
							role.store.load();
						}
					}
				});
		
		var bbar = new Ext.PagingToolbar({
			pageSize : 10,
			store : store,
			displayInfo : true,
			items : ['-', '&nbsp;', pageSizeCombo]
		});
		
		var grid = new Ext.grid.GridPanel({
					id : 'RoleSelectorGrid',
					tbar : toolbar,
					store : store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					height : 360,
					cm : cm,
					sm : sm,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					bbar : bbar
				});
		return grid;
	}
};