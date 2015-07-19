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
	var sid = null;
	$("#salaryEdit").on('click',function(){
		console.log('进入修改modal....');
		//clear data
		clearSalaryEditModal(); 
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
		//------------------------------------------------
		sid =  rowData.id;                                            //ID
		var empId = rowData.empId;                                    //职工编号
		var teacherName = rowData.teacherName;	                      //姓名
		var yfSalary = rowData.yfSalary;                              //应发项
		var sfSalary = rowData.sfSalary;                              //实发工资
		var jcSalary = rowData.jcSalary;                              //基础工资
		var gwSalary = rowData.gwSalary;                              //岗位工资
		var xjSalary = rowData.xjSalary;                              //薪级工资
		var glSalary = rowData.glSalary;                              //工龄工资
		var tgSalary = rowData.tgSalary;                              //提高工资
		var jtSalary = rowData.jtSalary;                              //津贴工资
		var qtjbSalary = rowData.qtjbSalary;                          //其他基本工资
		var sydwjtbthjAllowance = rowData.sydwjtbthjAllowance;        //事业单位津贴补贴合计
		var zwbtAllowance = rowData.zwbtAllowance;                    //职务补贴
		var tgjtAllowance = rowData.tgjtAllowance;                    //特钢津贴
		var jxjtAllowance = rowData.jxjtAllowance;                    //警衔津贴
		var jhljtAllowance = rowData.jhljtAllowance;                  //教护龄工资
		var bzrAllowance = rowData.bzrAllowance;                      //班主任津贴
		var ggxbtWybtAllowance = rowData.ggxbtWybtAllowance;          //改革性补贴二-物业补贴
		var qtbzAllowance = rowData.qtbzAllowance;                    //其他补助工资
		var kpAward = rowData.kpAward;                                //考评奖
		var qtSalary = rowData.qtSalary;                              //其他工资
		var dsznAllowance = rowData.dsznAllowance;                    //独生子女费
		var bfgzyfSalary = rowData.bfgzyfSalary;                      //补发工资应发额
		var zfAllowance = rowData.zfAllowance;                        //住房补贴
		var kfTotal = rowData.kfTotal;                                //扣发小计
		var kgjjMoney = rowData.kgjjMoney;						      //扣公积金
		var kyalbxMoney = rowData.kyalbxMoney;                        //扣养老保险
		var kyilbxMoney = rowData.kyilbxMoney;                        //扣医疗保险
		var dbbzjMoney = rowData.dbbzjMoney;                          //大病补助金
		var iitMoney = rowData.iitMoney;                              //应纳所得税额
		var kiitMoney = rowData.kiitMoney;                            //扣所得税
		var monthSalary = rowData.monthSalary;                        //月份
		var yearSalary = rowData.yearSalary;                          //年度
		var sfjsTax = rowData.sfjsTax;                                //是否计税
		//---------------------------------------------------------------
		$("#salaryEditModal input[type='text']:eq(0)").val(empId);
		$("#salaryEditModal input[type='text']:eq(1)").val(teacherName);
		$("#salaryEditModal input[type='text']:eq(2)").val(yfSalary);
		$("#salaryEditModal input[type='text']:eq(3)").val(sfSalary);
		$("#salaryEditModal input[type='text']:eq(4)").val(jcSalary);
		$("#salaryEditModal input[type='text']:eq(5)").val(gwSalary);
		$("#salaryEditModal input[type='text']:eq(6)").val(xjSalary);
		$("#salaryEditModal input[type='text']:eq(7)").val(glSalary);
		$("#salaryEditModal input[type='text']:eq(8)").val(tgSalary);
		$("#salaryEditModal input[type='text']:eq(9)").val(jtSalary);
		$("#salaryEditModal input[type='text']:eq(10)").val(qtjbSalary);
		$("#salaryEditModal input[type='text']:eq(11)").val(sydwjtbthjAllowance);
		$("#salaryEditModal input[type='text']:eq(12)").val(zwbtAllowance);
		$("#salaryEditModal input[type='text']:eq(13)").val(tgjtAllowance);
		$("#salaryEditModal input[type='text']:eq(14)").val(jxjtAllowance);
		$("#salaryEditModal input[type='text']:eq(15)").val(jhljtAllowance);
		$("#salaryEditModal input[type='text']:eq(16)").val(bzrAllowance);
		$("#salaryEditModal input[type='text']:eq(17)").val(ggxbtWybtAllowance);
		$("#salaryEditModal input[type='text']:eq(18)").val(qtbzAllowance);
		$("#salaryEditModal input[type='text']:eq(19)").val(kpAward);
		$("#salaryEditModal input[type='text']:eq(20)").val(qtSalary);
		$("#salaryEditModal input[type='text']:eq(21)").val(dsznAllowance);
		$("#salaryEditModal input[type='text']:eq(22)").val(bfgzyfSalary);
		$("#salaryEditModal input[type='text']:eq(23)").val(zfAllowance);
		$("#salaryEditModal input[type='text']:eq(24)").val(kfTotal);
		$("#salaryEditModal input[type='text']:eq(25)").val(kgjjMoney);
		$("#salaryEditModal input[type='text']:eq(26)").val(kyalbxMoney);
		$("#salaryEditModal input[type='text']:eq(27)").val(kyilbxMoney);
		$("#salaryEditModal input[type='text']:eq(28)").val(dbbzjMoney);
		$("#salaryEditModal input[type='text']:eq(29)").val(iitMoney);
		$("#salaryEditModal input[type='text']:eq(30)").val(kiitMoney);
		$("#salaryEditModal input[type='text']:eq(31)").val(monthSalary);
		$("#salaryEditModal input[type='text']:eq(32)").val(yearSalary);
		$("#salaryEditModal input[type='text']:eq(33)").val(sfjsTax);
		$("#salaryEditModal").modal('show');
	});
	//Edit opt
	var editFlag = true;
	if(editFlag){
		editFlag = false;
		$("#salaryEditModal .btn-primary").on('click',function(){
			var empId = $("#salaryEditModal input[type='text']:eq(0)").val();
			var teacherName = $("#salaryEditModal input[type='text']:eq(1)").val();
			var yfSalary = $("#salaryEditModal input[type='text']:eq(2)").val();
			var sfSalary = $("#salaryEditModal input[type='text']:eq(3)").val();
			var jcSalary = $("#salaryEditModal input[type='text']:eq(4)").val();
			var gwSalary = $("#salaryEditModal input[type='text']:eq(5)").val();
			var xjSalary = $("#salaryEditModal input[type='text']:eq(6)").val();
			var glSalary = $("#salaryEditModal input[type='text']:eq(7)").val();
			var tgSalary = $("#salaryEditModal input[type='text']:eq(8)").val();
			var jtSalary = $("#salaryEditModal input[type='text']:eq(9)").val();
			var qtjbSalary = $("#salaryEditModal input[type='text']:eq(10)").val();
			var sydwjtbthjAllowance = $("#salaryEditModal input[type='text']:eq(11)").val();
			var zwbtAllowance = $("#salaryEditModal input[type='text']:eq(12)").val();
			var tgjtAllowance = $("#salaryEditModal input[type='text']:eq(13)").val();
			var jxjtAllowance = $("#salaryEditModal input[type='text']:eq(14)").val();
			var jhljtAllowance = $("#salaryEditModal input[type='text']:eq(15)").val();
			var bzrAllowance = $("#salaryEditModal input[type='text']:eq(16)").val();
			var ggxbtWybtAllowance = $("#salaryEditModal input[type='text']:eq(17)").val();
			var qtbzAllowance = $("#salaryEditModal input[type='text']:eq(18)").val();
			var kpAward = $("#salaryEditModal input[type='text']:eq(19)").val();
			var qtSalary = $("#salaryEditModal input[type='text']:eq(20)").val();
			var dsznAllowance = $("#salaryEditModal input[type='text']:eq(21)").val();
			var bfgzyfSalary = $("#salaryEditModal input[type='text']:eq(22)").val();
			var zfAllowance = $("#salaryEditModal input[type='text']:eq(23)").val();
			var kfTotal = $("#salaryEditModal input[type='text']:eq(24)").val();
			var kgjjMoney = $("#salaryEditModal input[type='text']:eq(25)").val();
			var kyalbxMoney = $("#salaryEditModal input[type='text']:eq(26)").val();
			var kyilbxMoney = $("#salaryEditModal input[type='text']:eq(27)").val();
			var dbbzjMoney = $("#salaryEditModal input[type='text']:eq(28)").val();
			var iitMoney = $("#salaryEditModal input[type='text']:eq(29)").val();
			var kiitMoney = $("#salaryEditModal input[type='text']:eq(30)").val();
			var monthSalary = $("#salaryEditModal input[type='text']:eq(31)").val();
			var yearSalary = $("#salaryEditModal input[type='text']:eq(32)").val();
			var sfjsTax = $("#salaryEditModal input[type='text']:eq(33)").val();
			
			var data = {
					sid:sid,
					empId:empId,
					teacherName:teacherName,
					yfSalary:yfSalary,
					sfSalary:sfSalary,
					jcSalary:jcSalary,
					gwSalary:gwSalary,
					xjSalary:xjSalary,
					glSalary:glSalary,
					tgSalary:tgSalary,
					jtSalary:jtSalary,
					qtjbSalary:qtjbSalary,
					sydwjtbthjAllowance:sydwjtbthjAllowance,
					zwbtAllowance:zwbtAllowance,
					tgjtAllowance:tgjtAllowance,
					jxjtAllowance:jxjtAllowance,
					jhljtAllowance:jhljtAllowance,
					bzrAllowance:bzrAllowance,
					ggxbtWybtAllowance:ggxbtWybtAllowance,
					qtbzAllowance:qtbzAllowance,
					kpAward:kpAward,
					qtSalary:qtSalary,
					dsznAllowance:dsznAllowance,
					bfgzyfSalary:bfgzyfSalary,
					zfAllowance:zfAllowance,
					kfTotal:kfTotal,
					kgjjMoney:kgjjMoney,
					kyalbxMoney:kyalbxMoney,
					kyilbxMoney:kyilbxMoney,
					dbbzjMoney:dbbzjMoney,
					iitMoney:iitMoney,
					kiitMoney:kiitMoney,
					monthSalary:monthSalary,
					yearSalary:yearSalary,
					sfjsTax:sfjsTax
			};
			$.ajax({
				type:"POST",
				url:"../admin/editSalary.html",
				dataType:"json",
				data:data,
				success:function(jsonData){
					if(jsonData.success){
						init();
						alert("修改成功!");
					}else{
						alert("修改失败");
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("服务器内部错误");
				},
				complete:function(XMLHttpRequest, textStatus){
					$("#salaryEditModal").modal('hide');
					editFlag = true;
				}
			});
		});
	}
	
	
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
	
	//清除数据
	function clearSalaryEditModal(){
		$("#salaryEditModal input[type='text']").val();
	}
	
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