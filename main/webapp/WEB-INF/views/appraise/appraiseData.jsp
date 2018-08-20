<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<div id="${param.id}"></div>

<script type="text/javascript">
	var param = '${param.id}';
	var schemetype = eval('(${parameters.schemeType==null?"{}":parameters.schemeType})');
	yepnope({
		load : ["${ctx}/resources/swfupload/css/icons.css",
	        "${ctx}/resources/swfupload/swfupload.js",
	        "${ctx}/resources/swfupload/uploaderPanel.js"]
	});
	yepnope.injectJs("${ctx}/resources/js/appraise/appraiseData.js");
</script>