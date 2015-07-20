<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="山东省日照第一中学" name="keywords"/>
    <meta content="山东省日照第一中学" name="description"/>
    <link rel="shortcut icon" type="images/favicon.icon">

    <!--==规定双核浏览器优先级==-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">

    <title>山东省日照第一中学工资查询系统</title>
    <link href="<%=contextPath%>/favicon/favicon.ico" type="image/x-icon" rel="icon">
    <link href="<%=contextPath%>/favicon/favicon.ico" type="image/x-icon" rel="shortcut icon">

    <!-- Bootstrap -->
    <link href="<%=contextPath%>/css/bootstrap.min.css" rel="stylesheet">

    <!--public css-->
    <link href="<%=contextPath%>/css/master.css" rel="stylesheet">

    <!--==引用JS--bootstrap等js==-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=contextPath%>/js/jquery.1.9.1.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=contextPath%>/js/bootstrap.min.js"></script>
    <!-- Include My JS -->
    <script src="<%=contextPath%>/js/min/login.js"></script>

    <!--==排除IE6\7==-->
    <!--[if IE 6]>
    <script type="text/javascript">
        window.location.href = 'ie6update.html';
    </script>
    <![endif]-->
    <!--[if IE 7]>
    <script type="text/javascript">
        window.location.href = 'ie6update.html';
    </script>
    <![endif]-->

</head>
<body>
<!-- 信息提示开始 -->
<div id="alertMessage" class="alert alert-danger alert-dismissible" role="alert" style="display: none;">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>提示!</strong> <span class="dispalyText"></span>
</div>
<!-- 信息提示结束 -->

<!--返回顶部 代码 开始 -->
<div class="go-top dn" id="go-top">
    <a href="javascript:void(0);" class="go"></a>
</div>

<script>
    $(function () {
        $(window).on('scroll', function () {
            var st = $(document).scrollTop();
            if (st > 0) {
                if ($('#main-container').length != 0) {
                    var w = $(window).width(), mw = $('#main-container').width();
                    if ((w - mw) / 2 > 70)
                        $('#go-top').css({'left': (w - mw) / 2 + mw + 20});
                    else {
                        $('#go-top').css({'left': 'left'});
                    }
                }
                $('#go-top').fadeIn(function () {
                    $(this).removeClass('dn');
                });
            } else {
                $('#go-top').fadeOut(function () {
                    $(this).addClass('dn');
                });
            }
        });
        $('#go-top .go').on('click', function () {
            $('html,body').animate({'scrollTop': 0}, 500);
        });

        $('#go-top .uc-2vm').hover(function () {
            $('#go-top .uc-2vm-pop').removeClass('dn');
        }, function () {
            $('#go-top .uc-2vm-pop').addClass('dn');
        });
    });
</script>
<!--返回顶部 代码 结束 -->


<!--==content==-->

<div class="content">
    <h3 style="text-align: center;"><img src="<%=contextPath%>/images/logo-f.png" width="330" alt="日照一中校徽"/>工资查询系统-后台登录</h3>

    <br/>

    <!--==登录框==-->
    <div class="login-y">


        <form class="form-horizontal" id="loginForm" method="post">
            <div class="input-group">
                <span class="input-group-addon" id="sizing-addon3">用户名：</span>
                <input type="text" class="form-control" id="loginId" name="loginId" placeholder="请输入用户名" aria-describedby="sizing-addon2" value="">
            </div>
            <br/>

            <div class="input-group">
                <span class="input-group-addon" id="sizing-addon2">密　码：</span>
                <input type="password" class="form-control" id="loginPass" name="loginPass" placeholder="请输入密码" aria-describedby="sizing-addon3" value="">
            </div>
            </br>

            <div class="btn-group" style="margin: 0 auto;">
                <button type="button" class="btn btn-success btn-lg">确认登陆</button>
                <button type="reset" class="btn btn-default btn-lg">重置</button>
            </div>
        </form>


    </div>
    <!--==登录框==-->

</div>

<!--==content==-->


<!--==footer==-->
<hr style="height:1px;background-color: #ddd;"/>
<div class="footer clearfix">
    <p style="width: 100%;">日照一中 版权所有(C) &nbsp; 2015-2020 鲁ICP备05043509号</p>

    <p style="width: 100%;">学校邮箱： <a href="mailto:rzdyzx@163.com">RZDYZX@163.com</a>
        技术支持：<a href="javascript:void(0)" style="color:#333;">山东展望信息科技有限公司</a></p>
</div>
<!--==footer==-->

<!-- 隐藏域开始 -->
<input type="hidden" name="alertFlag" id="alertFlag" value="${alertMessage}"/>
<!-- 隐藏域结束 -->
</body>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</html>