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
    <link href="<%=contextPath%>/favicon.ico" type="image/x-icon" rel="icon">
    <link href="<%=contextPath%>/favicon.ico" type="image/x-icon" rel="shortcut icon">

    <!-- Bootstrap -->
    <link href="<%=contextPath%>/css/bootstrap.min.css" rel="stylesheet">

    <!--public css-->
    <link href="<%=contextPath%>/css/master.css" rel="stylesheet">
    
    <!-- jquery.dataTables.min.css -->
    <link href="<%=contextPath%>/css/jquery.dataTables.min.css" rel="stylesheet">

    <!--==引用JS--bootstrap等js==-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=contextPath%>/js/jquery.1.9.1.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=contextPath%>/js/bootstrap.min.js"></script>
    <!-- jquery.dataTables.min.js -->
    <script src="<%=contextPath%>/js/jquery.dataTables.min.js"></script>
    <!-- mine js -->
    <script src="<%=contextPath%>/js/min/salaryList.js"></script>
    
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

<div class="content-l">
    <h3 style="text-align:center;"><img src="<%=contextPath%>/images/logo-f.png" width="330" alt="日照一中校徽"/>教职工工资查询系统</h3>
</div>

<div class="content-con">
    <h5><i style="color:#f32;font-weight: bold;">${teacherInfo.teacherName}</i> &nbsp;&nbsp; 老师，你好！<span>这是您这半年的工资情况！</span></h5>
    <hr/>
    <table id="salaryTab" cellspacing="0" width="100%">
    
    </table>
    <!--  
    <ol class="list">
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="background-color: #dfdfdf;text-align: center;line-height: 2;border: 1px solid #aaa;">
                <tr>
                    <th width="11%">年月份</th>
                    <th width="11%">基本工资</th>
                    <th width="11%">绩效</th>
                    <th width="11%">医疗保险</th>
                    <th width="11%">失业保险</th>
                    <th width="11%">养老保险</th>
                    <th width="11%">生育保险</th>
                    <th width="11%">应扣款项</th>
                    <th width="11%">合计应发</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
        <li>
            <table cellspacing="0" cellpadding="0" width="100%" border="1"
                   style="text-align: center;line-height: 2;border: 1px solid #ddd;">
                <tr>
                    <th width="11%">1</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                    <th width="11%">0</th>
                </tr>
            </table>
        </li>
    </ol>
    -->
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
<input type="hidden" name="strSalaryList" id="strSalaryList" value='${salaryList}'/>
<!-- 隐藏域结束 -->
</body>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</html>