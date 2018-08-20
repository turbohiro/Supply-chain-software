<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<div id="editUserDiv" style="width: 100%; height: 100%;">
	<div id="editUserToolBarDiv"></div>
	<div id="editUserFormDiv"></div>
</div>

<script type="text/javascript">
	Ext.ns("Ext.changhong.myinfo"); // 自定义一个命名空间
	myinfo = Ext.changhong.myinfo; // 定义命名空间的别名
	myinfo = {
		save : ctx + "/emp/myinfo"
		/* ,
		THEME: eval('(${parameters.theme==null?"{}":parameters.theme})') */
	};
	/* 
	myinfo.themeComboBox = new Ext.form.ComboBox({
		hiddenName : 'theme',
		fieldLabel : '风格',
		store : new Ext.data.ArrayStore({
			fields : ['v', 't'],
			data : Share.map2Ary(myinfo.THEME)
		}),
		mode : 'local',
		triggerAction : "all",
		valueField : 'v',
		displayField : 't',
		editable : false,
		anchor : '99%',
		listeners : {
			collapse : function() {
				Share.swapStyle(this.value);
			}
		}
	});
	myinfo.setTheme = function() {
		var theme = Ext.state.Manager.get('Cxjava_theme');
		if (theme && theme != '') {
			myinfo.themeComboBox.setValue(theme);
		} else {
			myinfo.themeComboBox.setValue('xtheme-gray.css');
		}
	};
	myinfo.setTheme();
	 */
	//编辑用户Form
	myinfo.formPanel = new Ext.form.FormPanel({
		renderTo : 'editUserFormDiv',
		frame : false,
		height : 330,
		bodyStyle : 'padding:10px;border:0px',
		labelWidth: 70,
		defaultType : 'textfield',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '员工编号',
			value : '${user.empcode}',
			name : 'empcode'
		}, {
			fieldLabel : '员工账号',
			maxLength : 64,
			allowBlank : false,
			value : '${user.empname}',
			name : 'empname',
			anchor : '99%'
		}, {
			fieldLabel : '员工姓名',
			maxLength : 64,
			allowBlank : false,
			value : '${user.classname}',
			name : 'classname',
			anchor : '99%'
		}, {
			fieldLabel : '英文姓名',
			maxLength : 64,
			allowBlank : false,
			value : '${user.empename}',
			name : 'empename',
			anchor : '99%'
		}, {
			fieldLabel : '所属机构',
			maxLength : 64,
			allowBlank : false,
			value : '${user.organizationcode}',
			name : 'organizationcode',
			anchor : '99%'
		}, {
			fieldLabel : '所属部门',
			maxLength : 64,
			allowBlank : false,
			value : '${user.departmentcode}',
			name : 'departmentcode',
			anchor : '99%'
		}, /* {
			fieldLabel : '所属客户',
			maxLength : 64,
			allowBlank : false,
			value : '${user.custcode}',
			name : 'custcode',
			anchor : '99%'
		}, {
			fieldLabel : '员工类别',
			maxLength : 64,
			allowBlank : false,
			value : '${user.emptype}',
			name : 'emptype',
			anchor : '99%'
		},  */{
			fieldLabel : '电子邮件',
			maxLength : 64,
			allowBlank : false,
			regex : /^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/,
			regexText : '请输入有效的邮箱地址',
			value : '${user.email}',
			name : 'email',
			anchor : '99%'
		}, {
			fieldLabel : '手机号码',
			maxLength : 32,
			allowBlank : false,
			value : '${user.phone}',
			name : 'phone',
			anchor : '99%'
		} ]
	});
	// 工具栏
	myinfo.toolbar = new Ext.Toolbar({
		renderTo : 'editUserToolBarDiv',
		items : [ new Ext.Button({
			text : '保存',
			iconCls : 'save',
			handler : function() {
				var form = myinfo.formPanel.getForm();
				if (form.isValid()) {
					var values = form.getValues();
					if (values.theme) {
						delete values.theme;
					}
					Share.AjaxRequest({
						url : myinfo.save,
						params : values,
						callback : function(json) {
							// 重新登录
							Share.getWin().location = ctx;
						}
					});
				}
			}
		}), new Ext.Button({
			text : '取消',
			iconCls : 'cancel',
			handler : function() {
				header.myInfoWindow.close();
			}
		}) ]
	});
</script>
