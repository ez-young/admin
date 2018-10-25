<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>用户列表页</title>
	<#include "../../layout/stylesandscripts.ftl">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <#include "../../layout/header.ftl">

    <#include "../../layout/menu_navigation.ftl">

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
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-inline">
								<input type="text" name="username" id="usernameSearch" placeholder="请输入用户名" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">电话</label>
							<div class="layui-input-inline">
								<input type="text" name="tel" id="telSearch" placeholder="请输入电话" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">真实姓名</label>
							<div class="layui-input-inline">
								<input type="text" name="realname" id="realnameSearch" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">出生日期</label>
							<div class="layui-input-inline">
								<input type="text" name="birthdate" id="birthdateSearch" placeholder="请输入出生日期" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">状态</label>
							<div class="layui-input-inline">
								<select name="status" id="statusSearch">
									<option value=""></option>
									<option value="0">启用</option>
									<option value="1">禁用</option>
								</select>
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
					<button class="layui-btn layui-btn-small layui-btn-danger" id="delUser"><i class="layui-icon">&#xe640;</i>删除</button>
					<button class="layui-btn layui-btn-small layui-btn-normal" id="refresh"><i class="layui-icon">&#x1002;</i>刷新</button>
				</div>
			</blockquote>

			<table class="layui-table" lay-filter="userTable" lay-data="{height:530,page:true,id:'user',url:'/user/data'}">
				<thead>
				<tr>
					<th lay-data="{type:'checkbox'}"></th><!-- LAY_CHECKED:true 设置所有的复选框默认选中 -->
					<th lay-data="{type:'numbers'}">序号</th>
					<th lay-data="{field:'username', width:160,sort:true}">用户名</th>
					<th lay-data="{field:'password', width:160}">密码</th>
					<th lay-data="{field:'rolenames', width:250}">角色</th>
					<th lay-data="{field:'realname', width:160}">真实姓名</th>
					<th lay-data="{field:'tel', width:160,align:'center'}">电话</th>
					<th lay-data="{field:'addr', width:250}">住址</th>
					<th lay-data="{field:'birthdate', width:160,templet: '#birthdateTpl',align:'center'}">出生日期</th>
					<th lay-data="{field:'status', width:80,templet: '#statusTpl'}">状态</th>
					<th lay-data="{field:'createTime', width:200,templet: '#createTimeTpl',align:'center'}">创建时间</th>
					<th lay-data="{field:'updateTime', width:200,templet: '#updateTimeTpl',align:'center'}">更新时间</th>
					<th lay-data="{title:'操作',fixed: 'right', width:180, align:'center', toolbar: '#bar'}"></th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
    <#include "../../layout/footer.ftl">
</div>




<script>
    //JavaScript代码区域
    layui.use(['element','table','form','laydate'], function(){
        var element = layui.element;
        var table=layui.table;
		var form=layui.form;
		var laydate=layui.laydate;
//		var upload=layui.upload;

//		var now=new Date();
//		console.log(util.toDateString(now));
        /*var uploadInst = upload.render({
            elem: '#upload',
			url: '/file/upload',
//			auto:false,
//            bindAction:'#upload_img',
            field:'file',
            before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#preview').attr('src', result); //图片链接（base64）
                });

                /!*var uploadText = $('#uploadText');
                uploadText.html('<a class="layui-btn layui-btn-mini upload_img">上传</a>');
                uploadText.find('.upload_img').on('click', function(){
                    uploadInst.upload();
                });*!/
            },
			done: function(res){
                if(res.code == 200){
                    return layer.msg('上传成功');
                }
            },
			error: function(){
                //演示失败状态，并实现重传
                var uploadText = $('#uploadText');
                uploadText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini reload">重试</a>');
                uploadText.find('.reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });*/

//		console.log($("#searchForm").serializeObject(true));

		form.on('submit(search)',function (data){
//		    console.log($("#searchForm").serializeObject(true));
            table.reload('user', {
                where:$("#searchForm").serializeObject(false),
            });
            return false;
        });

		laydate.render({
			elem:'#birthdateSearch'
		});

        laydate.render({
            elem: '#birthdate' //指定元素
        });

        //监听工具条
        var addFormIndex;
        table.on('tool(userTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('确定要删除?', {icon: 3, title:'提示'}, function(index){
                    $.get('/user/del',{ids:data.id},function (data) {
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
            } else if(layEvent === 'edit'){
//                layer.msg('编辑操作');
                $.get('/user/findOne',{id:data.id},function(res){
                    var user=res.data.user;
                    var userRoles=res.data.userRoles;
                    $('#id').val(user.id);
                    $('#username').val(user.username);
                    $('#password').val(user.password);
                    $('#realname').val(user.realname);
                    $('#tel').val(user.tel);
                    $('#addr').val(user.addr);
                    $('#birthdate').val(user.birthdate);
                    $(':radio').each(function (i,n) {
                        if($(n).val()==user.status){
                            $(n).attr("checked",true);
                        }
                        else{
                            $(n).removeAttr('checked');
						}
                    });

                    if(userRoles.length==0){
                        $('#addForm input:checkbox').each(function (i,n){
                            $(n).attr('checked',false);
                        });
                    }
                    $('input[name="roles"]').each(function (i,n) {
						var flag=false;
						$.each(userRoles,function (index,element){
							if($(n).val()==element.role_id){
								flag=true;
								return false;
							}
						});

						if(flag==true){
							$(n).attr('checked',true);
						}
						else{
						    $(n).attr('checked',false);
						}
                    });

                    form.render();
                },"json");
                addFormIndex=layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['600px', '700px'], //宽高
                    content:$('#addForm'),
//                    title:'编辑页',
                    title:['编辑页','font-size:24px;'],
                    skin:'layui-layer-molv'
                });
            }
        });

        $('#add').click(function () {
            $('#id').val("");
            $('#addForm input:text').each(function (i,n) {
                $(n).val("");
            });

            $('#addForm input:radio').each(function(i,n){
                if(i==0){
                    $(n).attr("checked",true);
                }
                else{
                    $(n).attr("checked",false);
                }
            });

            $('#addForm input:checkbox').each(function (i,n){
				$(n).attr('checked',false);
            });

            form.render();

            addFormIndex=layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['600px', '700px'], //宽高
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

        form.verify({
            password: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格']
		});

        form.on('switch(switch)', function(data){
            console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //开关是否开启，true或者false
            console.log(data.value); //开关value值，也可以通过data.elem.value得到
            console.log(data.othis); //得到美化后的DOM对象
			console.log($(data.elem).data('id'));


			$.post('/user/changeStatus',{id:$(data.elem).data('id'),checked:data.elem.checked},function (res) {
				layer.msg(res.msg);
            },"json");
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
            var obj=$('#addForm').serializeObject(true);
//            console.log(obj);

            var roles=[];
            $('input[name="roles"]:checked').each(function (i,n) {
				roles.push($(n).val());
            });
//            console.log(roles);

			if(roles.length==0){
			    layer.alert("至少选择一个角色",{icon:7});
                return false;
            }

            obj['roles']=roles;
            console.log(obj);

            $.ajax({
				type:'POST',
				url:'/user/addOrUpdate',
				data:obj,
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
                                table.reload('user', {
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
            var checkStatus = table.checkStatus('user'); //user即为基础参数id对应的值

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
    });
</script>

<script type="text/html" id="bar">
	<a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>

<script type="text/html" id="birthdateTpl">
	{{# var fn= function(){ return Format(d.birthdate,"yyyy-MM-dd"); }; }}

	{{ fn() }}
</script>

<script type="text/html" id="createTimeTpl">
	{{# var fn= function(){ return Format(d.createTime,"yyyy-MM-dd HH:mm:ss"); }; }}

	{{ fn() }}
</script>

<script type="text/html" id="updateTimeTpl">
	{{# var fn= function(){ return Format(d.updateTime,"yyyy-MM-dd HH:mm:ss"); }; }}

	{{ fn() }}
</script>

<script type="text/html" id="statusTpl">
	{{# if(d.status==0){ }}
		<input type="checkbox" name="switch" data-id="{{ d.id }}" lay-filter="switch" lay-skin="switch" lay-text="ON|OFF" checked>
	{{# }else{ }}
		<input type="checkbox" name="switch" data-id="{{ d.id }}" lay-filter="switch" lay-skin="switch" lay-text="ON|OFF">
	{{# } }}
</script>
</body>


<form class="layui-form layui-form-pane" id="addForm" style="display: none">
	<input type="hidden" name="id" id="id">
	<div class="layui-form-item">
		<label class="layui-form-label">用户名</label>
		<div class="layui-input-block">
			<input type="text" name="username" id="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">密码</label>
		<div class="layui-input-inline">
			<input type="text" name="password" id="password" lay-verify="password" autocomplete="off" placeholder="请输入密码" class="layui-input">
		</div>
		<div class="layui-form-mid layui-word-aux">密码6-12位</div>
	</div>
	<!--<div class="layui-form-item">
		<label class="layui-form-label">头像</label>
		<div class="layui-input-inline">
			<div class="layui-upload">
				<button type="button" class="layui-btn" id="upload">上传头像</button>
				<div class="layui-upload-list">
					<img class="layui-upload-img" id="preview" style="border: 1px solid #000000;width: 92px;height: 92px;">
					<p id="uploadText"></p>
				</div>
			</div>
		</div>
	</div>-->
	<div class="layui-form-item">
		<label class="layui-form-label">真实姓名</label>
		<div class="layui-input-block">
			<input type="text" name="realname" id="realname" lay-verify="realname" autocomplete="off" placeholder="请输入真实姓名" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">手机号</label>
		<div class="layui-input-block">
			<input type="text" name="tel" id="tel" lay-verify="phone" autocomplete="off" placeholder="请输入手机号" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">住址</label>
		<div class="layui-input-block">
			<input type="text" name="addr" id="addr" lay-verify="addr" autocomplete="off" placeholder="请输入住址" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">出生日期</label>
		<div class="layui-input-block">
			<input type="text" name="birthdate" id="birthdate" lay-verify="date" placeholder="请输入出生日期" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item" pane>
		<label class="layui-form-label">状态</label>
		<div class="layui-input-block">
			<input type="radio" name="status" value="0" title="启用" checked>
			<input type="radio" name="status" value="1" title="禁用">
		</div>
	</div>

	<div class="layui-form-item" pane>
		<label class="layui-form-label">角色</label>
		<div class="layui-input-block">
            <#list systemRoles as systemrole>
                <input type="checkbox" name="roles" title="${systemrole.name}" value="${systemrole.id}">
            </#list>
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