<!--<style type="text/css">
    .avatar{
        width: 45px;
        height: 45px;
        margin-right: 10px;
        border-radius: 50%;
    }
</style>-->
<script>
    $(function (){
        var module='${module}';

        if(module=='first'){
            $('.layui-layout-left li:eq(0)').addClass('layui-this');
        }
        else{
            $('.layui-layout-left li').each(function(i,n){
//            console.log($(n).attr('href'));
                if($(n).data('module')==module){
                    $(n).addClass('layui-this');
                }
            });
        }

        var imgId='${imgId!}';
        if(imgId){
            $.ajax({
                url:'/file/getImgUrl',
                type:'GET',
                dataType:'json',
                data:{
                    imgId:imgId
                },
                success:function (res){
                    if(res.code==200){
                        $('#imgMini').attr('src',res.data.url);
                        $('#img').attr('src',res.data.url);
                    }else{
                        $('#imgMini').attr('src','/img/defaultavatar.jpeg');
                        $('#img').attr('src','/img/defaultavatar.jpeg');
                    }
                }
            });
        }else{
            $('#imgMini').attr('src','/img/defaultavatar.jpeg');
            $('#img').attr('src','/img/defaultavatar.jpeg');
        }

    });
</script>
<div class="layui-header">
    <div class="layui-logo">layui后台模板</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
        <!--<li class="layui-nav-item"><a href="">控制台</a></li>
        <li class="layui-nav-item"><a href="">商品管理</a></li>
        <li class="layui-nav-item"><a href="">用户</a></li>
        <li class="layui-nav-item">
            <a href="javascript:;">其它系统</a>
            <dl class="layui-nav-child">
                <dd><a href="">邮件管理</a></dd>
                <dd><a href="">消息管理</a></dd>
                <dd><a href="">授权管理</a></dd>
            </dl>
        </li>-->
        <#if modules ??>
            <#list modules as module>
                <li class="layui-nav-item" data-module="${module.module!}"><a href="javascript:;" onclick="changeMenu('${module.module!}')">${module.name}</a></li>
            </#list>
        </#if>
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img id="imgMini" src="/img/defaultavatar.jpeg" class="layui-nav-img">
                ${user.username!}:${user.realname!}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" onclick="changeAvatar()">修改头像</a></dd>
                <dd><a href="">安全设置</a></dd>
                <dd><a href="/logout">退出系统</a></dd>
            </dl>
        </li>
        <!--<li class="layui-nav-item"><a href="">退了</a></li>-->
    </ul>
</div>

<div id="avatarhtml" style="display: none" class="layui-row">
    <div class="layui-col-md-offset3">
        <input type="hidden" id="file_id">
        <!--<input type="hidden" id="url" value="$!{url}">-->
        <button type="button" class="layui-btn" id="selectImg">选择图片</button>
        <button type="button" class="layui-btn layui-btn-danger" id="upload">开始上传</button>
        <div class="layui-upload-list">
            <img class="layui-upload-img" src="/img/defaultavatar.jpeg" id="img" style="border: 1px solid #f2f2f2;width: 256px;height: 256px;">
            <p id="imgText"></p>
        </div>
    </div>
</div>