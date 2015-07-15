$(function(){
	init();
	function init() {
		var opts = {
			url : "../admin/getAllSalary.html",
			data : {}
		};
		ajaxRequest(opts);
	}
	
	//ajax request
	function ajaxRequest(opts){
		$.ajax({
			type:"POST",
			url:opts.url,
			dataType:"json",
			data:opts.data,
			success:function(jsonData){
				if(jsonData.success){
					var tab01 = '<table id="tab01"></tab>';
					$("#wrappedTab01").empty();
					$("#wrappedTab01").append(tab01);
					buildTab01(jsonData.data);
				}
			}
		});
	}
	//build tab01
	function buildTab01(data){
		var tab01Header = [{"sTitle": "选择","mData":"select"},{"sTitle": "编号","mData":"id"},{"sTitle": "身份证","mData":"teacherId"},{"sTitle": "姓名","mData":"teacherName"},
						   {"sTitle": "年份","mData":"year"},{"sTitle": "月份","mData":"Month"},{"sTitle": "工资","mData":"salary"}
						  ];
		$("#tab01").dataTable({
	        "aoColumns": tab01Header, 
	        "aaData":data.allSalaryList,
	        "aoColumnDefs": [{sDefaultContent: '',aTargets: [ '_all' ]}],
	        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
	        	$('td:eq(0)', nRow).html( '<input type="checkbox" name="salaryChk">' );
	          }
		});
	   oTable = $("#tab01").dataTable();
	}
	
	//点击刷新 Home
	$("#tab01Nav").on('click',function(){
		init();
	});
	
	//工资信息录入
	$("#salaryImport").on('click',function(){
		console.log("弹出工资导入的modal.....");
		$("#salaryInfoImport").modal('show');
	});
	//文件上传
	$("#uploadButton1").on('click',function(){
		console.log('开始文件上传.......');
		var file = $("#uploadForm1 :file").val();
		if(isEmpty(file)){
			alert("请选择文件");
			return false;
		}
		$("#uploadForm1").attr("action","../admin/upload1.html").submit();
	});
	
	//工资信息修改
	//var oTable = $("#tab01").dataTable();
	
	$("#salaryEdit").on('click',function(){
		console.log('进入修改modal....');
		var chks = $("#wrappedTab01 :checkbox[name='salaryChk']:checked");
		var chkSize = chks.size();
		if(chkSize == 0){
			alert('请选择一条工资信息进行编辑');
			return false;
		}
		if(chkSize>1){
			alert('只能选择一条工资信息进行编辑');
			return false;
		}	
		var pos = chks.parent().parent()[0];
		var rowData = oTable.fnGetData(pos);
		var teacherName = rowData.teacherName;
		var teacherId = rowData.teacherId;
		var year = rowData.year;
		var Month = rowData.Month;
		var salary = rowData.salary;
		console.log(teacherName+":::"+teacherId+":::"+year+":::"+Month+":::"+salary);
		$("#salaryEditModal").modal('show');
		
	});
	
	//工资信息删除
	$("#salaryDel").on('click',function(){
		console.log('工资信息删除....');
	});
	
	//判断是否为空
	function isEmpty(obj) {
	    if (typeof (obj) == "undefined" || obj == '' || obj== null) {
	    	return true;
	    }
	    return false;
	};
	

});