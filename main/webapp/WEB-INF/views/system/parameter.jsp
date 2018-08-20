<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<div id="${param.id}"></div>

<script type="text/javascript">
var param='${param.id}',
isshow = eval('(${parameters.isshow==null?"{}":parameters.isshow})'),
partype = eval('(${parameters.type==null?"{}":parameters.type})');
yepnope.injectJs("${ctx}/resources/js/system/parameter.js");
</script>
