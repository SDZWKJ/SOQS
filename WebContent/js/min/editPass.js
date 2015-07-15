$(function(){
	init();
	alertMessage();
	//提交修改
	$("#editPass .btn-success").on('click',function(){
		//做一些验证   新密码和确认密码要相同
		$("#editPass").attr("action","../query/modify.html").submit();
	});
	
	
	
	
	//初始化
	function init(){
		$("#editPass input[type='text']").val("");
		$("#editPass input[type='password']:eq(0)").val("");
		$("#editPass input[type='password']:eq(1)").val("");
		$("#editPass input[type='password']:eq(2)").val("");
	}
	//提示信息
	function alertMessage(){
		var alertFlag = $("#alertFlg").val();
		if(alertFlag == '1'){
			$("#alertMessage").css('display','block');
			$(".displayText").text("新密码和确认密码不一致，请重新输入.");
		}
		if(alertFlag == '2'){
			$("#alertMessage").css('display','block');
			$(".displayText").text("您输入的身份证号码或密码不正确，无法完成修改.");
		}
	}
	
	
});