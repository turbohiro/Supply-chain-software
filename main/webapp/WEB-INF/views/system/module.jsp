<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<div id="${param.id}"></div>

<script type="text/javascript">
var param='${param.id}',
moduleMap =eval('(${moduleMap})'),
transflag = eval('(${parameters.transflag==null?"{}":parameters.transflag})'),
expanded = eval('(${parameters.modelparameter==null?"{}":parameters.modelparameter})'),
isshow=eval('(${parameters.isshow==null?"{}":parameters.isshow})');
yepnope.injectJs("${ctx}/resources/js/system/module.js");
</script>
