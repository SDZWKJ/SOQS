$(function() {
	init();
	alertMessage();
	$(".btn-success").on('click', function() {
		//console.log("进入查询");
		$("#verifyId").attr("action","../query/salary.html").submit();
	});

	$(".btn-default").on('click', function() {
		//console.log("进入修改");
		$("#verifyId").attr("action","../query/initModify.html").submit();
	});
	
	function init(){
		$("#verifyId :input[type='text']").val("");
		$("#verifyId :input[type='password']").val("");
	}
	
	function varifyId() {
		var id = $("#verifyId :input[type='text']").val().trim();
		if (id == "" || id == null) {
			alert("身份证号不能为空");
			return false;
		}
		var pass = $("#verifyId :input[type='password']").val();
	}
	
	function alertMessage(){
		var verifyFlag = $("#verifyFlag").val();
		//console.log(":::verifyFlag:::"+verifyFlag);
		if(verifyFlag == 'true'){
			$("#alertMessage").css("display","block");
			$("#alertMessage .dispalyText").text('').text("您输入的身份证号码或密码不正确，请重新输入.");
		}
		
		if(verifyFlag == "3"){
			$("#alertMessage").css("display","block");
			$("#alertMessage .dispalyText").text('').text("密码修改成功,您可以用新密码查询工资信息");
		}
		
	}
});
