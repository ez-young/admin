<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>菜单列表页</title>
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

			<!--<blockquote class="layui-elem-quote">
				<form class="layui-form layui-form-pane" action="" id="searchForm">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">菜单名称</label>
							<div class="layui-input-inline">
								<input type="text" name="name" id="nameSearch" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<button class="layui-btn" lay-submit lay-filter="search"><i class="layui-icon">&#xe615;</i>查询</button>
						</div>
					</div>
				</form>
			</blockquote>-->

			<blockquote class="layui-elem-quote" style="margin-top: 15px">
				<div class="layui-btn-group">
					<button class="layui-btn layui-btn-small" id="add"><i class="layui-icon">&#xe654;</i>增加</button>
					<!--<button class="layui-btn layui-btn-small layui-btn-danger" id="delUser"><i class="layui-icon">&#xe640;</i>删除</button>-->
					<!--<button class="layui-btn layui-btn-small layui-btn-normal" id="refresh"><i class="layui-icon">&#x1002;</i>刷新</button>-->
				</div>
			</blockquote>

			<table class="layui-table" lay-filter="menuTable" lay-data="{height:530,id:'systemmenu',url:'/systemmenu/data'}">
				<thead>
				<tr>
					<!--<th lay-data="{type:'checkbox'}"></th>&lt;!&ndash; LAY_CHECKED:true 设置所有的复选框默认选中 &ndash;&gt;-->
					<th lay-data="{type:'numbers'}">序号</th>
					<th lay-data="{field:'name', width:'20%',templet:'#nameTpl'}">名称</th>
					<th lay-data="{field:'url', width:'10%'}">url</th>
					<th lay-data="{field:'unicode', width:150,templet:'#iconTpl'}">图标</th>
					<th lay-data="{field:'level', width:160}">级别</th>
					<th lay-data="{field:'module', width:250}">模块</th>
					<th lay-data="{field:'weight', width:160}">排序</th>
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
            table.reload('user', {
                where:$("#searchForm").serializeObject(false),
            });
            return false;
        });

        //监听工具条
        var addFormIndex;
        table.on('tool(menuTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('确定要删除此菜单及其子菜单?', {icon: 3, title:'提示'}, function(index){
                    $.get('/systemmenu/del',{id:data.id},function (data) {
						if(data.code==200){
                            layer.alert(data.msg,{icon: 1},function(index){
                                layer.close(index);
                                table.reload('systemmenu', {
                                    where:$("#searchForm").serializeObject(false),
                                });
                            });
						}
						else{
                            layer.alert(data.msg,{icon: 2},function(index){
                                layer.close(index);
                                table.reload('systemmenu', {
                                    where:$("#searchForm").serializeObject(false),
                                });
                            });
						}
                    },"json");
                    layer.close(index);
                });
            } else if(layEvent === 'edit'){
//                layer.msg('编辑操作');
                $.get('/systemmenu/findOne',{id:data.id},function(res){
                    var systemMenu=res.data.systemMenu;
                    $('#id').val(systemMenu.id);
                    $('#name').val(systemMenu.name);
                    $('#url').val(systemMenu.url);
                    $('#iconId').val(systemMenu.icon_id);
                    $('#weight').val(systemMenu.weight);
                    $('#module').val(systemMenu.module);
//					$('#pid').val(systemMenu.pid);
					if(systemMenu.parentName){
                        $('#pid').html('<option value="'+systemMenu.pid+'" data-module="'+systemMenu.module+'">'+systemMenu.parentName+'</option>');
					}
					else{
                        $('#pid').html('<option value="0" data-module="'+systemMenu.module+'">无</option>');
					}

                    form.render();
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
            $('#pid').val("");
            $('#iconId').val("");
            $('#addForm input').each(function (i,n) {
                $(n).val("");
            });


        	/*<option value="" data-module="">---请选择---</option>
                <option value="0" data-module="">无</option>
				#foreach($menu in $menus)
					#if($menu.level==2){
            		<option value="$menu.id" data-module="$!menu.module">&nbsp;&nbsp;&nbsp;  ├&nbsp;$menu.name</option>
					#else
            		<option value="$menu.id" data-module="$!menu.module">$menu.name</option>
					#end
				#end*/

        	$.ajax({
				type:'get',
				url:'/systemmenu/findSelectList',
				dataType:'json',
				success:function (res){
				    var html='';
				    html += '<option value="" data-module="">---请选择---</option>';
				    html += '<option value="0" data-module="">无</option>';
					$.each(res.data.menus,function (i,n){
//					    console.log(n);
//					    console.log(n.level);
						if(n.level==2){
						    html += '<option value="'+n.id+'" data-module="'+n.module+'">&nbsp;&nbsp;&nbsp;  ├&nbsp;'+n.name+'</option>';
						}
						else{
						    html += '<option value="'+n.id+'" data-module="'+n.module+'">'+n.name+'</option>';
						}
                    });

					$('#pid').html(html);

                    form.render();
                }
			});

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
            table.reload('user',{
                url: '/user/data',
                where:$("#searchForm").serializeObject(false)
			});
        });

        form.on('submit(addSubmit)',function (data){
            /*$.post('/user/addOrUpdate',$('#addForm').serializeObject(true),function (data) {
                if(data.code==200){
                    layer.alert(data.msg,{icon: 1},function(index){
                        layer.close(index);
                        layer.close(addFormIndex);
                        table.reload('user', {
                            where:$("#searchForm").serializeObject(true),
                        });
                    });
                }
            },"json");*/

            $.ajax({
				type:'POST',
				url:'/systemmenu/addOrUpdate',
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
                                table.reload('systemmenu', {
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

        $('#delUser').click(function () {
            var checkStatus = table.checkStatus('systemmenu'); //user即为基础参数id对应的值

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
                $.get('/user/del',{ids:ids},function (data) {
                    if(data.code==200){
                        layer.alert(data.msg,{icon: 1},function(index){
                            layer.close(index);
                            table.reload('user', {
                                where:$("#searchForm").serializeObject(false),
                            });
                        });
                    }
                },"json");
                layer.close(index);
            });
        });

        form.on('select(pidSelect)', function(data){
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            console.log(data.othis); //得到美化后的DOM对象

			var module="";
			$(data.elem).find('option').each(function (i,n){
				if($(n).val()==data.value){
                    module=$(n).data('module');
				    return false;
				}
            });

			$('#module').val(module);
        });

//        console.log("hello");
//        $('#icon').siblings().find('dd').each(function (i,n) {
//            console.log(n);
//			$(n).attr('class','layui-icon');
//        });
//
//        form.render();
    });
</script>

<script type="text/html" id="bar">
	<a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>

<!--<script type="text/html" id="birthdateTpl">
	{{# var fn= function(){ return Format(d.birthdate,"yyyy-MM-dd"); }; }}

	{{ fn() }}
</script>-->

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
</script>
</body>


<form class="layui-form layui-form-pane" id="addForm" style="display: none">
	<input type="hidden" name="id" id="id">

	<div class="layui-form-item">
		<label class="layui-form-label">上级菜单</label>
		<div class="layui-input-block">
			<select id="pid" name="pid" lay-verify="required" lay-filter="pidSelect" lay-search>

			</select>
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">菜单名称</label>
		<div class="layui-input-block">
			<input type="text" name="name" id="name" lay-verify="required" autocomplete="off" placeholder="请输入菜单名称" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">菜单链接</label>
		<div class="layui-input-inline">
			<input type="text" name="url" id="url" autocomplete="off" placeholder="请输入菜单链接" class="layui-input" value="javascript:;">
		</div>
		<!--<div class="layui-form-mid layui-word-aux">密码6-12位</div>-->
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">菜单图标</label>
		<div class="layui-input-block">
			<!--<i class="layui-icon">&#xe620;</i>-->
			<!--<input type="text" name="icon" id="icon" autocomplete="off" placeholder="请输入菜单图标" class="layui-input">-->
			<select id="iconId" name="iconId" lay-search>
				<option value="">---请选择---</option>
				<#list icons as icon>
                    <option value="${icon.id}">${icon.name}</option>
				</#list>
			</select>
			<!--#foreach($icon in $icons)
				<input type="radio" name="icon" value="$icon.id" title="<i class='layui-icon'>$icon.unicode</i>" #if($velocityCount==1) checked #end>
			#end-->
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">模块</label>
		<div class="layui-input-block">
			<input type="text" name="module" id="module" lay-verify="required" autocomplete="off" placeholder="请输入模块" class="layui-input">
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