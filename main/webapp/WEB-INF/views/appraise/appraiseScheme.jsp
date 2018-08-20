<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<div id="${param.id}"></div>

<script type="text/javascript">
	var param='${param.id}';
	var schemeType = eval('(${parameters.schemeType==null?"{}":parameters.schemeType})');
	yepnope.injectJs("${ctx}/resources/js/appraise/appraiseScheme.js");
</script>
