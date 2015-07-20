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
    
    <!-- jquery.dataTables.min.css -->
    <link href="<%=contextPath%>/css/jquery.dataTables.min.css" rel="stylesheet"> 

    <link href="<%=contextPath%>/css/custom.css" rel="stylesheet">
    
    <!--==引用JS--bootstrap等js==-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=contextPath%>/js/jquery.1.9.1.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=contextPath%>/js/bootstrap.min.js"></script>
    <!-- jquery.dataTables.min.js -->
    <script src="<%=contextPath%>/js/jquery.dataTables.min.js"></script>
    <!-- jquery.form.js -->
    <script src="<%=contextPath%>/js/jquery.form.js"></script>
    <!-- mine js -->
    <script src="<%=contextPath%>/js/min/ajaxReqLoading.js"></script>
    <script src="<%=contextPath%>/js/min/admin.js"></script>
    <script src="<%=contextPath%>/js/min/admin-tab2.js"></script>

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
<div id="alertMessage" class="alert alert-success alert-dismissible" role="alert" style="display: none;">
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

<div class="content-l">
    <h3 style="text-align:center;"><img src="<%=contextPath%>/images/logo-f.png" width="330" alt="日照一中校徽"/>教职工工资查询系统</h3>
</div>

<div class="content-con">

    <ul class="nav nav-tabs" id="myTab">
        <li class="active"><a id="tab01Nav" href="#home" style="padding: 10px 30px;">工资管理</a></li>
        <li><a id="tab02Nav" href="#profile" style="padding: 10px 30px;">用户管理</a></li>
    </ul>

    <div class="tab-content">
        <div class="tab-pane active" id="home"><br/>
        <div class="clearfix">
        	<div style="width:180px;" class="pull-left">
	            <button type="button" id="salaryEdit" class="btn btn btn-primary btn-ms">修改</button>
	            <!--  
	            <button type="button" id="salaryDel" class="btn btn btn-primary btn-ms">删除</button>
	            -->
	            <button type="button" id="salaryImport" class="btn btn btn-primary btn-ms">导入</button>
        	</div>
        	<div style="width:250px; margin-left:50px;" class="pull-left">
        		<form class="form-horizontal">
	        		<div class="form-group">
					    <label class="col-sm-2 control-label">年度:</label>
					    <div class="col-sm-5">
					      <select class="form-control" id="selYear">
							  <option value="" selected = "selected">默认当前年</option>
							  <option value="2015">15</option>
							  <option value="2016">16</option>
							  <option value="2017">17</option>
							  <option value="2018">18</option>
						  </select>
					    </div>
					  </div>
				</form>
        	</div>
        	<div style="width:250px; margin-left:0px;" class="pull-left">
        		<form class="form-horizontal">
	        		<div class="form-group">
					    <label class="col-sm-2 control-label">月份:</label>
					    <div class="col-sm-5">
					      <select class="form-control" id="selMonth">
							  <option value="" selected = "selected">默认当前月</option>
							  <option value="1">1</option>
							  <option value="2">2</option>
							  <option value="3">3</option>
							  <option value="4">4</option>
							  <option value="5">5</option>
							  <option value="6">6</option>
							  <option value="7">7</option>
							  <option value="8">8</option>
							  <option value="9">9</option>
							  <option value="10">10</option>
							  <option value="11">11</option>
							  <option value="12">12</option>
						  </select>
					    </div>
					  </div>
				</form>
        	</div>
        	<button type="button" id="salarySearch" class="btn btn btn-primary btn-ms">查询</button>
        	<button type="button" id="delMonthRecord" class="btn btn btn-primary btn-ms">删除整月记录</button>
        </div>
            <hr/>
        	<div id="wrappedTab01">
        		<table id="tab01">
        		
        		</table>
        	</div>
        </div>
        <div class="tab-pane" id="profile">
            <br/>
            <button type="button" id="userAdd" class="btn btn-primary btn-ms">新增</button>
            <button type="button" id="userEdit" class="btn btn-primary btn-ms">修改</button>
            <button type="button" id="userDel" class="btn btn-primary btn-ms">删除</button>
            <!--  
            <button type="button" id="userImport" class="btn btn-primary btn-ms">导入</button>
            -->
            <hr/>
            <div id="wrappedTab02">
        		<table id="tab02">
        		
        		</table>
        	</div>
        </div>
    </div>

    <script>
        $(function () {
        	var tabFlag = $("#tabFlag").val();
        	if(tabFlag=='tab2'){
        		$('#myTab a:last').tab('show');//初始化显示哪个tab
        		$('#alertMessage').css('display','block');
        		$('.dispalyText').text('').text('用户信息导入成功');
        	}else{
        		if(tabFlag=='tab1'){
        			$('#alertMessage').css('display','block');
            		$('.dispalyText').text('').text('工资信息导入成功');
        		}
            	$('#myTab a:first').tab('show');//初始化显示哪个tab
        	}
            $('#myTab a').click(function (e) {
                e.preventDefault();//阻止a链接的跳转行为
                $(this).tab('show');//显示当前选中的链接及关联的content
            })
        })
    </script>
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

<!-- 工资管理数据导入modal-START -->
<div class="modal fade" id="salaryInfoImport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">工资信息数据导入</h4>
				<h5 style="color: red">提示:为确保数据录入成功，请去掉Excel的只读模式</h5>
			</div>
			<div class="modal-body">
				<form id="uploadForm1" method="post" enctype="multipart/form-data">  
					<input type="file" name="file" accept="application/msexcel" /> 
					<input id="uploadButton1" type="button" value="开始上传" />
				</form> 
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 工资管理数据导入modal-END -->

<!-- 工资导入提示覆盖modal-START -->
<div class="modal fade" id="salaryInfoWrite" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">数据覆盖提示窗口</h4>
			</div>
			<div class="modal-body">
				<p style="color:red">数据库中包含此年度月份的记录,继续操作可能会覆盖这些数据。</p>
				<p style="color:red">继续操作请点击继续按钮</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-success">继续</button>
			</div>
		</div>
	</div>
</div>
<!-- 工资导入提示覆盖modal-END -->

<!-- 工资导入继续执行modal-START -->
<div class="modal fade" id="salaryInfoContinue" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">输入导入提示窗口</h4>
			</div>
			<div class="modal-body">
				<p style="color:red">文件成功上传，请点击继续完成数据录入工作。</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-success">继续</button>
			</div>
		</div>
	</div>
</div>
<!-- 工资导入继续执行modal-END -->

<!-- 工资信息修改modal-START -->
<div class="modal fade" id="salaryEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">工资信息修改</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal clearfix">
					<!-- 左边 -->
					<div class="pull-left" style="width:400px;">
						<div class="form-group">
							<label class="col-sm-2 control-label">职工编号:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="职工编号" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="姓名" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">应发项:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="应发项" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">实发工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="实发工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">基础工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="基础工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">岗位工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="岗位工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">薪级工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="薪级工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">工龄工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="工龄工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">提高工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="提高工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">津贴工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="津贴工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">其他基本工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="其他基本工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">事业单位津贴补贴合计:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="事业单位津贴补贴合计" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">职务补贴:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="职务补贴" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">特岗津贴:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="特岗津贴" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">警衔津贴:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="警衔津贴" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">教护龄津贴:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="教护龄津贴" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">班主任津贴:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="班主任津贴" value="">
							</div>
						</div>
					</div>
					
					<!-- 左边 -->
					<div class="pull-right" style="width:400px;">
						<div class="form-group">
							<label class="col-sm-2 control-label">改革性补贴二-物业补贴:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  placeholder="改革性补贴二-物业补贴" value="">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">其他补助工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="其他补助工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">考评奖:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="考评奖" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">其他工资:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="其他工资" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">独生子女费:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="独生子女费" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">补发工资应发额:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="补发工资应发额" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">住房补贴:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="住房补贴" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">扣发小计:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="扣发小计" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">扣公积金:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="扣公积金" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">扣养老保险:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="扣养老保险" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">扣医疗保险:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="扣医疗保险" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">大病补救金:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="大病补救金" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">应纳所得税额:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="应纳所得税额" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">扣所得税:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="扣所得税" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">月份:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="月份" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">年度:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="年度" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否计税:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" placeholder="是否计税" value="">
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
<!-- 工资信息修改modal-END -->

<!-- 用户数据导入modal START -->
<div class="modal fade" id="userInfoImport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">用户数据导入</h4>
				<h5 style="color: red">提示:为确保数据录入成功，请去掉Excel的只读模式</h5>
			</div>
			<div class="modal-body">
				<form id="uploadForm2" method="post" enctype="multipart/form-data">  
					<input type="file" name="file" accept="application/msexcel" /> 
					<input id="uploadButton2" type="button" value="开始导入" />
				</form> 
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 用户数据导入modal END -->

<!-- 用户信息添加modal-START -->
<div class="modal fade" id="userAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">添加用户信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
				    	<label class="col-sm-2 control-label">姓名:</label>
				     	<div class="col-sm-6">
				        	<input type="text" class="form-control"  placeholder="姓名" value="">
				     	</div>
				     </div>
				 	 <div class="form-group">
				     	<label class="col-sm-2 control-label">身份证:</label>
				    	<div class="col-sm-6">
				        	<input type="text" class="form-control" placeholder="身份证号码" value="">
				    	</div>
				    </div>
				  	<div class="form-group">
				    	<label class="col-sm-2 control-label">职工编号:</label>
				    	<div class="col-sm-6">
				        	<input type="text" class="form-control"  placeholder="职工编号" value="">
				    	</div>
				 	</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
<!-- 用户信息添加modal-END -->

<!-- 用户信息修改modal-START -->
<div class="modal fade" id="userEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">用户信息修改</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
				    	<label class="col-sm-2 control-label">姓名:</label>
				     	<div class="col-sm-6">
				        	<input type="text" class="form-control" placeholder="姓名" value="">
				     	</div>
				     </div>
				 	 <div class="form-group">
				     	<label class="col-sm-2 control-label">身份证:</label>
				    	<div class="col-sm-6">
				        	<input type="text" class="form-control" placeholder="身份证" value="" disabled="disabled">
				    	</div>
				    </div>
				  	<div class="form-group">
				    	<label class="col-sm-2 control-label">职工编号:</label>
				    	<div class="col-sm-6">
				        	<input type="text" class="form-control" placeholder="职工编号" value="">
				    	</div>
				 	</div>
				 	<div class="form-group">
				    	<label class="col-sm-2 control-label">查询密码:</label>
				    	<div class="col-sm-6">
				        	<input type="password" class="form-control" placeholder="不修改请留空" value="">
				    	</div>
				 	</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
<!-- 用户信息修改modal-END -->


<!-- 隐藏域开始 -->
<input type="hidden" name="tabFlag" id="tabFlag" value='${tabFlag}'/>
<!-- 隐藏域结束 -->
</body>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</html>