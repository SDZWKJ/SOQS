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
			}
		});//ajax-END
	}
	//buildTab02
	function buildTab02(data){
		var tab02Header = [{"sTitle": "选择","mData":"select"},{"sTitle": "编号","mData":"id"},{"sTitle": "身份证","mData":"teacherId"},{"sTitle": "密码","mData":"queryPassword"},
						   {"sTitle": "姓名","mData":"teacherName"}
						  ];
		$("#tab02").dataTable({
	        "aoColumns": tab02Header, 
	        "aaData":data.allUser,
	        "aoColumnDefs": [{sDefaultContent: '',aTargets: [ '_all' ]}],
	        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
	        	$('td:eq(0)', nRow).html( '<input type="checkbox">' );
	          }
		});
	};
	
//	var oTable = $("#wrappedTab02 table").dataTable();
//	$().on('click',function(){
//		
//	});
	
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
	
	//判断是否为空
	function isEmpty(obj) {
	    if (typeof (obj) == "undefined" || obj == '' || obj== null) {
	    	return true;
	    }
	    return false;
	};
});