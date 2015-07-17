$(function(){
	init();
	function init() {
		var opts = {
			url : "../admin/getAllSalary.html",
			data : {}
		};
		console.log("getAllSalary");
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
		var tab01Header = [{"sTitle": "选择","mData":"select"},{"sTitle": "职工编号","mData":"empId"},{"sTitle": "姓名","mData":"teacherName"},
		                   {"sTitle": "应发项","mData":"yfSalary"},{"sTitle": "实发工资","mData":"sfSalary"},{"sTitle": "基础工资","mData":"jcSalary"},{"sTitle": "岗位工资","mData":"gwSalary"},
		                   {"sTitle": "薪级工资","mData":"xjSalary"},{"sTitle": "工龄工资","mData":"glSalary"},{"sTitle": "提高工资","mData":"tgSalary"},{"sTitle": "津贴奖金","mData":"jtSalary"},
		                   {"sTitle": "其他基本工资","mData":"qtjbSalary"},{"sTitle": "事业单位津贴补贴合计","mData":"sydwjtbthjAllowance"},{"sTitle": "职务工资","mData":"zwbtAllowance"},{"sTitle": "特岗津贴","mData":"tgjtAllowance"},
		                   {"sTitle": "警衔津贴","mData":"jxjtAllowance"},{"sTitle": "教护龄津贴","mData":"jhljtAllowance"},{"sTitle": "班主任津贴","mData":"bzrAllowance"},{"sTitle": "改革性补贴二-物业补贴","mData":"ggxbtWybtAllowance"},
		                   {"sTitle": "其他补助工资","mData":"qtbzAllowance"},{"sTitle": "考评奖","mData":"kpAward"},{"sTitle": "其他工资","mData":"qtSalary"},{"sTitle": "独生子女费","mData":"dsznAllowance"},
		                   {"sTitle": "补发工资应发额","mData":"bfgzyfSalary"},{"sTitle": "住房补贴","mData":"zfAllowance"},{"sTitle": "扣发小计","mData":"kfTotal"},{"sTitle": "扣公积金","mData":"kgjjMoney"},
		                   {"sTitle": "扣养老保险","mData":"kyalbxMoney"},{"sTitle": "扣医疗保险","mData":"kyilbxMoney"},{"sTitle": "大病救助金","mData":"dbbzjMoney"},{"sTitle": "应纳所得税额","mData":"iitMoney"},
		                   {"sTitle": "扣所得税","mData":"kiitMoney"},{"sTitle": "月份","mData":"monthSalary"},{"sTitle": "年度","mData":"yearSalary"},{"sTitle": "是否计税","mData":"sfjsTax"}
						  ];
		$("#tab01").dataTable({
			"sScrollX": "100%",
	        "sScrollXInner": "110%",
	        "bScrollCollapse": true,
	        "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	        "sZeroRecords": "没有检索到数据",
	        "aoColumns": tab01Header, 
	        "aaData":data.allSalaryList,
	        "aoColumnDefs": [{sDefaultContent: '',aTargets: [ '_all' ]}],
	        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
	        	$('td:eq(0)', nRow).html( '<input type="checkbox" name="salaryChk" value="'+aData.id+'">' );
	          }
		});
	   oTable = $("#tab01").dataTable();
	}
	
	//点击刷新 Home
	$("#tab01Nav").on('click',function(){
		init();
	});
	
	//-----------------工资信息录入-start--------------------------------------
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
	//-----------------工资信息录入-start--------------------------------------
	
	
	//-----------------工资信息修改-start--------------------------------------
	//init the data which need to be edited
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
	//-----------------工资信息修改-end--------------------------------------
	
	
	// ----------------工资信息删除-start-------------------------------
	//工资信息删除
	$("#salaryDel").on('click',function(){
		console.log('工资信息删除....');
		var chks = $("#wrappedTab01 :checkbox[name='salaryChk']:checked");
		var chkSize = chks.size();
		if(chkSize == 0){
			alert('请至少选择一条信息进行删除');
			return false;
		} 
		var ids = "";
		$.each(chks,function(index,item){
			ids += item.value+",";
		});
		var flag = confirm("确定要删除这些工资信息吗?");
		if(!flag){
			return false;
		}
		$.ajax({
			type:"POST",
			url:"../admin/deleteSalaryByIds.html",
			dataType:"json",
			data:{
				ids:ids
			},
			success:function(JsonData){
				if(JsonData.success){
					console.log(JsonData.data.deleteNum);
					init();
					alert('删除成功!');
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("服务器内部错误");
			}
		});
	});
	// ----------------工资信息删除-end-------------------------------
	
	//判断是否为空
	function isEmpty(obj) {
	    if (typeof (obj) == "undefined" || obj == '' || obj== null) {
	    	return true;
	    }
	    return false;
	};
	
	/**
       "oLanguage": {
    	"sLengthMenu": "每页显示 _MENU_ 条记录",
    	"sZeroRecords": "抱歉， 没有找到",
    	"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
    	"sInfoEmpty": "没有数据",
    	"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
    	"oPaginate": {
        	"sFirst": "首页",
        	"sPrevious": "前一页",
        	"sNext": "后一页",
        	"sLast": "尾页"
    	},
    	"sZeroRecords": "没有检索到数据",
    	"sProcessing": "<img src='./loading.gif' />"
       },
	 */
	

});