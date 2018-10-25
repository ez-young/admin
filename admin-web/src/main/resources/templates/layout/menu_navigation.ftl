<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!--<a href="javascript:;">-->
            <!--<img src="" class="layui-circle" width="94px" height="94px">-->
        <!--</a>-->
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" id="nav_tree">
            <!--<li class="layui-nav-item">
                <a class="" href="javascript:;">所有商品</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">列表一</a></dd>
                    <dd><a href="javascript:;">列表二</a></dd>
                    <dd><a href="javascript:;">列表三</a></dd>
                    <dd><a href="javascript:;">超链接</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">解决方案</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">列表一</a></dd>
                    <dd><a href="javascript:;">列表二</a></dd>
                    <dd><a href="javascript:;">超链接</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;">云市场</a></li>-->
            ${systemmenus}
        </ul>
    </div>
</div>
<script>
    $(function (){
        var url='${requestUrl}';
        $('#nav_tree a').each(function(i,n){
//            console.log($(n).attr('href'));
            if($(n).attr('href')==url){
                $(n).parent().addClass('layui-this');
            }
        });
    });
</script>