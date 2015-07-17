$(function(){
	init2();
	function init2(){
		var tabFlag = $("#tabFlag").val();
		if(tabFlag == 'tab2'){
			ajaxRquest2();
		}
	}
	//点击刷新 User Management
	$("#tab02Nav").on('click',function(){
		ajaxRquest2();
	});
	function ajaxRquest2(){
		$.ajax({
			type:"POST",
			url:"../admin/getAllUser.html;",
			dataType:"json",
			success:function(jsonData){
				if(jsonData.success){
					var tab01 = '<table id="tab02"></tab>';
					$("#wrappedTab02").empty();
					$("#wrappedTab02").append(tab01);
					buildTab02(jsonData.data);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("服务器内部错误");
			}
		});//ajax-END
	}
	//buildTab02
	function buildTab02(data){
		var tab02Header = [{"sTitle": "选择","mData":"select"},{"sTitle": "职工编号","mData":"empId"},{"sTitle": "姓名","mData":"teacherName"},
		                   {"sTitle": "身份证","mData":"teacherId"}
						  ];
		$("#tab02").dataTable({
	        "aoColumns": tab02Header, 
	        "aaData":data.allUser,
	        "aoColumnDefs": [{sDefaultContent: '',aTargets: [ '_all' ]}],
	        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
	        	$('td:eq(0)', nRow).html( '<input type="checkbox" name="userChk" value="'+aData.id+'" />' );
	          }
		});
		oTable2 = $("#tab02").dataTable();
	};
	
	//-----------------用户数据修改-start--------------------------------
	//init the data which need to be edited
	var id = null;
	$("#userEdit").on('click',function(){
		console.log('进入修改 user modal....');
		clearUserEditModal();
		var chks = $("#wrappedTab02 :checkbox[name='userChk']:checked");
		var chkSize = chks.size();
		if(chkSize == 0){
			alert('请选择一条用户信息进行编辑');
			return false;
		}
		if(chkSize>1){
			alert('只能选择一条用户信息进行编辑');
			return false;
		}	
		var pos = chks.parent().parent()[0];
		var rowData = oTable2.fnGetData(pos);
		var teacherName = rowData.teacherName;
		var teacherId = rowData.teacherId;
		var empId = rowData.empId;
		id = rowData.id;
		console.log(teacherName+":::"+teacherId+":::"+empId+":::"+id);
		
		$("#userEditModal input[type='text']:eq(0)").val(teacherName);
		$("#userEditModal input[type='text']:eq(1)").val(teacherId);
		$("#userEditModal input[type='text']:eq(2)").val(empId);
		$("#userEditModal").modal('show');
	});
	//save userInfo after edit
	var editFlag2 = true;
	$("#userEditModal .btn-primary").on('click',function(){
		console.log('保存修改后的信息........');
		var teacherName = $("#userEditModal input[type='text']:eq(0)").val().trim();
		var teacherId = $("#userEditModal input[type='text']:eq(1)").val().trim();
		var empId = $("#userEditModal input[type='text']:eq(2)").val().trim();
		var queryPassword = $("#userEditModal input[type='password']").val();
		if(isEmpty(teacherName)){
			alert("请输入姓名");
			return false;
		}
		
		if(isEmpty(teacherId)){
			alert("请输入身份证号");
			return false;
		}
		var idReg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		var idRegFlag = idReg.test(teacherId);
		if(!idRegFlag){
			alert("身份证号码格式不正确");
			return false;
		}
		
		if(isEmpty(empId)){
			alert("请输入员工号");
			return false;
		}
		if(editFlag2){
			editFlag2 = false;
			$.ajax({
				type:"POST",
				url:"../admin/editUser.html",
				dataType:"json",
				data:{
					id:id,
					teacherName:teacherName,
					teacherId:teacherId,
					empId:empId,
					queryPassword:queryPassword
				},
				complete:function(){
					editFlag2 = true;
				},
				success:function(jsonData){
					if(jsonData.success){
						ajaxRquest2();
						$("#userEditModal").modal('hide');
						alert('修改成功!');
					}else{
						$("#userEditModal").modal('hide');
						alert('没有修改成功!');
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("服务器内部错误");
				}
			});
		}else{
			console.log("重复保存操作......");
		}
	});
	//-----------------用户数据修改-end--------------------------------
	
	// ----------------用户数据增加-start-------------------------------
	//show addModal
	$("#userAdd").on('click',function(){
		console.log("用户数据信息添加.......");
		clearUserAddModal();
		$("#userAddModal").modal('show');
	});
	//add opt
	var submitFlag2 = true; //提交表示位，防止重复提交
	$("#userAddModal .btn-primary").on('click',function(){
		console.log("开始保存信息.....");
		var userName = $("#userAddModal input[type='text']:eq(0)").val().trim();  //姓名
		var teacherId = $("#userAddModal input[type='text']:eq(1)").val().trim(); //身份证号
		var empId = $("#userAddModal input[type='text']:eq(2)").val().trim();     //职工编号
		if(isEmpty(userName)){
			alert("请输入姓名");
			return false;
		}
		
		if(isEmpty(teacherId)){
			alert("请输入身份证号");
			return false;
		}
		var idReg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		var idRegFlag = idReg.test(teacherId);
		if(!idRegFlag){
			alert("身份证号码格式不正确");
			return false;
		}
		
		if(isEmpty(empId)){
			alert("请输入员工号");
			return false;
		}
		if(submitFlag2){
			submitFlag2 = false;
			$.ajax({
				type:"POST",
				url:"../admin/addUser.html",
				dataType:"json",
				data:{
					userName:userName,
					teacherId:teacherId,
					empId:empId
				},
				complete :function(XMLHttpRequest){
					submitFlag2 = true;
				},
				success:function(jsonData){
					if(jsonData.success){
						ajaxRquest2();
						$("#userAddModal").modal('hide');
						alert('添加成功!');
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("服务器内部错误");
				}
			});
		}else{
			console.log("重复提交......");
		}
	});
	// ----------------用户数据增加-end-------------------------------
	
	// --------------用户数删除-start----------------------------
	//用户信息删除
	$("#userDel").on('click',function(){
		console.log('用户信息删除....');
		var chks = $("#wrappedTab02 :checkbox[name='userChk']:checked");
		var chkSize = chks.size();
		if(chkSize == 0){
			alert('请至少选择一条用户信息进行删除');
			return false;
		} 
		var ids = "";
		$.each(chks,function(index,item){
			ids += item.value+",";
		});
		var flag = confirm("确定要删除这些用户信息吗?");
		if(!flag){
			return false;
		}
		$.ajax({
			type:"POST",
			url:"../admin/deleteUseryByIds.html",
			dataType:"json",
			data:{
				ids:ids
			},
			success:function(jsonData){
				if(jsonData.success){
					console.log(jsonData.data.deleteNum);
					ajaxRquest2();
					alert('删除成功!');
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("服务器内部错误");
			}
		});
	});
	
	// --------------用户数删除-end----------------------------

	

	// --------------用户数据导入-start----------------------------
	//显示用户数据导入的modal
	$("#userImport").on('click',function(){
		console.log("弹出用户导入的modal.....");
		$("#userInfoImport").modal('show');
	});
	
	//导入用户数据
	$("#uploadButton2").on('click',function(){
		console.log('开始文件上传.......');
		var file = $("#uploadForm2 :file").val();
		if(isEmpty(file)){
			alert("请选择用户信息Excel文件");
			return false;
		}
		$("#uploadForm2").attr("action","../admin/upload2.html").submit();
	});
	//--------------用户数据导入-end----------------------------
	
	//清除数据方法
	function clearUserAddModal(){
		$("#userAddModal input[type='text']").val("");
	}
	function clearUserEditModal(){
		$("#userEditModal input[type='text']").val("");
		$("#userEditModal input[type='password']").val("");
	}
	
	//判断是否为空
	function isEmpty(obj) {
	    if (typeof (obj) == "undefined" || obj == '' || obj== null) {
	    	return true;
	    }
	    return false;
	};
});