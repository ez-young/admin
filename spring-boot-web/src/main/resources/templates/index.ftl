<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>主页</title>
	<#include "layout/stylesandscripts.ftl">
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<#include "layout/header.ftl">

    <#include "layout/menu_navigation.ftl">

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
			<div class="layui-row"><!-- 定义一行 -->
			</div>
		</div>
	</div>
    <#include "layout/footer.ftl">
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>