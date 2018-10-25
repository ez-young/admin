<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>角色列表页</title>
	<#include "../layout/stylesandscripts.ftl">
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<#include "../layout/header.ftl">

	<#include "../layout/menu_navigation.ftl">

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
			<span class="layui-breadcrumb">
  				<a href="">用户管理</a>
  				<a><cite>用户列表</cite></a>
			</span>

			<blockquote class="layui-elem-quote" style="margin-top: 15px">
				<form class="layui-form layui-form-pane" action="" id="searchForm">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">角色名称</label>
							<div class="layui-input-inline">
								<input type="text" name="name" id="nameSearch" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<button class="layui-btn" lay-submit lay-filter="search"><i class="layui-icon">&#xe615;</i>查询</button>
						</div>
					</div>
				</form>
			</blockquote>

			<blockquote class="layui-elem-quote">
				<div class="layui-btn-group">
					<button class="layui-btn layui-btn-small" id="add"><i class="layui-icon">&#xe654;</i>增加</button>
					<button class="layui-btn layui-btn-small layui-btn-danger" id="delRole"><i class="layui-icon">&#xe640;</i>删除</button>
					<button class="layui-btn layui-btn-small layui-btn-normal" id="refresh"><i class="layui-icon">&#x1002;</i>刷新</button>
				</div>
			</blockquote>

			<table class="layui-table" lay-filter="roleTable" lay-data="{height:530,id:'systemrole',url:'/systemrole/data',page:true}">
				<thead>
				<tr>
					<th lay-data="{type:'checkbox'}"></th><!-- LAY_CHECKED:true 设置所有的复选框默认选中 -->
					<th lay-data="{type:'numbers'}">序号</th>
					<th lay-data="{field:'name', width:'150'}">角色名称</th>
					<th lay-data="{field:'field', width:'250'}">角色域</th>
					<th lay-data="{field:'comment', width:'200'}">备注</th>
					<th lay-data="{field:'weight', width:100}">排序号</th>
					<th lay-data="{fixed: 'right', width:180, align:'center', toolbar: '#bar'}"></th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	<#include "../layout/footer.ftl">
</div>




<script>
    //JavaScript代码区域
    layui.use(['element','table','form'], function(){
        var element = layui.element;
        var table=layui.table;
		var form=layui.form;

		form.on('submit(search)',function (data){
//		    console.log($("#searchForm").serializeObject(true));
            table.reload('systemrole', {
                where:$("#searchForm").serializeObject(false),
            });
            return false;
        });

        //监听工具条
        var addFormIndex;
        table.on('tool(roleTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('确定要删除?', {icon: 3, title:'提示'}, function(index){
                    $.get('/systemrole/del',{ids:data.id},function (data) {
						if(data.code==200){
                            layer.alert(data.msg,{icon: 1},function(index){
                                layer.close(index);
                                table.reload('systemrole', {
                                    where:$("#searchForm").serializeObject(false),
                                });
                            });
						}
						else{
                            layer.alert(data.msg,{icon: 2},function(index){
                                layer.close(index);
                                table.reload('systemrole', {
                                    where:$("#searchForm").serializeObject(false),
                                });
                            });
						}
                    },"json");
                    layer.close(index);
                });
            } else if(layEvent === 'edit'){
//                layer.msg('编辑操作');
                $.get('/systemrole/findOne',{id:data.id},function(res){
                    var systemrole=res.data.systemrole;
                    $('#id').val(systemrole.id);
                    $('#name').val(systemrole.name);
                    $('#field').val(systemrole.field);
                    $('#comment').val(systemrole.comment);
                    $('#weight').val(systemrole.weight);
                },"json");
                addFormIndex=layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '500px'], //宽高
                    content:$('#addForm'),
//                    title:'编辑页',
                    title:['编辑页','font-size:24px;'],
                    skin:'layui-layer-molv'
                });
            }
        });

        $('#add').click(function () {
//            $('#id').val("");
            $('#addForm input').each(function (i,n) {
                $(n).val("");
            });

            $('#comment').val("");

            addFormIndex=layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['400px', '500px'], //宽高
                content:$('#addForm'),
				title:['编辑页','font-size:24px;'],
				skin:'layui-layer-molv'
            });
        });

        $('#refresh').click(function () {
//            console.log($("#searchForm").serializeObject(true));
            table.reload('systemrole',{
                url: '/systemrole/data',
                where:$("#searchForm").serializeObject(false)
			});
        });

        form.on('submit(addSubmit)',function (data){
            $.ajax({
				type:'POST',
				url:'/systemrole/addOrUpdate',
				data:$('#addForm').serializeObject(true),
				dataType:'json',
				beforeSend:function(){
                    $('#addSubmit').text("提交中...");
                    $('#addSubmit').attr("disabled",true);
				},
				success:function (data) {
				    if(data.code<0){
						layer.alert(data.msg,{icon:7});
					}
                    else{
				        if(data.code==200){//成功
                            layer.alert(data.msg,{icon: 1},function(index){
                                layer.close(index);
                                layer.close(addFormIndex);
                                table.reload('systemrole', {
                                    where:$("#searchForm").serializeObject(false),
                                });
                            });
                        }
                        else if(data.code==500){//失败
                            layer.alert("系统错误,请重新尝试",{icon:2});
                        }
                    }
                },
				error:function (XMLHttpRequest, textStatus, errorThrown) {
//					console.log(XMLHttpRequest);
//					console.log(textStatus);
//					console.log(errorThrown);
					layer.alert('系统错误,请联系管理员',{icon:2});
                },
                complete:function(){
                    $('#addSubmit').text("立即提交");
                    $('#addSubmit').attr("disabled",false);
				}
			});

            return false;
        });

        $('#delRole').click(function () {
            var checkStatus = table.checkStatus('systemrole'); //user即为基础参数id对应的值

            console.log(checkStatus.data); //获取选中行的数据
            console.log(checkStatus.data.length); //获取选中行数量，可作为是否有选中行的条件
            console.log(checkStatus.isAll ); //表格是否全选

			if(checkStatus.data.length==0){
				layer.alert("请至少选中一行",{icon:7});
				return;
			}

			var ids="";
			$.each(checkStatus.data,function (i,n){
				if(i==(checkStatus.data.length-1)){
                    ids +=n.id;
				}
				else{
                    ids +=n.id+",";
				}
            });

            layer.confirm('确定要删除?', {icon: 3, title:'提示'}, function(index){
                $.get('/systemrole/del',{ids:ids},function (data) {
                    if(data.code==200){
                        layer.alert(data.msg,{icon: 1},function(index){
                            layer.close(index);
                            table.reload('systemrole', {
                                where:$("#searchForm").serializeObject(false),
                            });
                        });
                    }
                    else{
                        layer.alert(data.msg,{icon: 2},function(index){
                            layer.close(index);
                            table.reload('systemrole', {
                                where:$("#searchForm").serializeObject(false),
                            });
                        });
                    }
                },"json");
                layer.close(index);
            });
        });
    });
</script>

<script type="text/html" id="bar">
	<a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>

<!--<script type="text/html" id="birthdateTpl">
	{{# var fn= function(){ return Format(d.birthdate,"yyyy-MM-dd"); }; }}

	{{ fn() }}
</script>

<script type="text/html" id="iconTpl">
	{{# if(d.unicode) { }}
		<i class="layui-icon">{{ d.unicode }}</i>
	{{# }else{ }}

	{{# } }}
</script>

<script type="text/html" id="nameTpl">
	{{#  if(d.level == 2){ }}
		&nbsp;&nbsp;&nbsp;  ├&nbsp;{{d.name}}
	{{#  } else if(d.level==3) { }}
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ├&nbsp;{{d.name}}
	{{#  } else{ }}
		{{d.name}}
	{{# } }}
</script>-->
</body>


<form class="layui-form layui-form-pane" id="addForm" style="display: none">
	<input type="hidden" name="id" id="id">

	<div class="layui-form-item">
		<label class="layui-form-label">角色名称</label>
		<div class="layui-input-block">
			<input type="text" name="name" id="name" lay-verify="required" autocomplete="off" placeholder="请输入角色名称" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">角色域</label>
		<div class="layui-input-block">
			<input type="text" name="field" id="field" lay-verify="required" autocomplete="off" placeholder="请输入角色域" class="layui-input">
		</div>
		<!--<div class="layui-form-mid layui-word-aux">密码6-12位</div>-->
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">备注</label>
		<div class="layui-input-block">
			<textarea name="comment" id="comment" placeholder="请输入备注" class="layui-textarea"></textarea>
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">排序号</label>
		<div class="layui-input-block">
			<input type="text" name="weight" id="weight" autocomplete="off" placeholder="请输入排序号" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit="" lay-filter="addSubmit" id="addSubmit">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
</html>