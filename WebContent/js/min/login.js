$(function(){
	init();
	function init(){
		var messageFlag = $("#alertFlag").val();
		if(messageFlag == '5'){
			$("#alertMessage").css('display','block');
			$("#alertMessage .dispalyText").text('').text('用户名或密码不正确.');
			
		}
	};
	
	//登录提交
	$("#loginForm .btn-success").on('click',function(){
		$("#loginForm").attr('action','../admin/validate.html').submit();
	});

});