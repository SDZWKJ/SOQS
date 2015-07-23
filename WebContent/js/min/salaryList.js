$(function(){
	buildTab01();
	//获取数据
	function buildTab01(){
		var strSalaryList = $("#strSalaryList").val();
		if(strSalaryList == "" || strSalaryList == null){
			return false;
		}
		var jsonSalaryList = JSON.parse(strSalaryList);
		//console.log(strSalaryList);
		//console.log(jsonSalaryList);
		var header = [{"sTitle": "职工编号","mData":"empId"},{"sTitle": "姓名","mData":"teacherName"},
	                  {"sTitle": "应发项","mData":"yfSalary"},{"sTitle": "实发工资","mData":"sfSalary"},{"sTitle": "基础工资","mData":"jcSalary"},{"sTitle": "岗位工资","mData":"gwSalary"},
	                  {"sTitle": "薪级工资","mData":"xjSalary"},{"sTitle": "工龄工资","mData":"glSalary"},{"sTitle": "提高工资","mData":"tgSalary"},{"sTitle": "津贴奖金","mData":"jtSalary"},
	                  {"sTitle": "其他基本工资","mData":"qtjbSalary"},{"sTitle": "事业单位津贴补贴合计","mData":"sydwjtbthjAllowance"},{"sTitle": "职务工资","mData":"zwbtAllowance"},{"sTitle": "特岗津贴","mData":"tgjtAllowance"},
	                  {"sTitle": "警衔津贴","mData":"jxjtAllowance"},{"sTitle": "教护龄津贴","mData":"jhljtAllowance"},{"sTitle": "班主任津贴","mData":"bzrAllowance"},{"sTitle": "改革性补贴二-物业补贴","mData":"ggxbtWybtAllowance"},
	                  {"sTitle": "其他补助工资","mData":"qtbzAllowance"},{"sTitle": "考评奖","mData":"kpAward"},{"sTitle": "其他工资","mData":"qtSalary"},{"sTitle": "独生子女费","mData":"dsznAllowance"},
	                  {"sTitle": "补发工资应发额","mData":"bfgzyfSalary"},{"sTitle": "住房补贴","mData":"zfAllowance"},{"sTitle": "扣发小计","mData":"kfTotal"},{"sTitle": "扣公积金","mData":"kgjjMoney"},
	                  {"sTitle": "扣养老保险","mData":"kyalbxMoney"},{"sTitle": "扣医疗保险","mData":"kyilbxMoney"},{"sTitle": "职业年金","mData":"dbbzjMoney"},{"sTitle": "应纳所得税额","mData":"iitMoney"},
	                  {"sTitle": "扣所得税","mData":"kiitMoney"},{"sTitle": "月份","mData":"monthSalary"},{"sTitle": "年度","mData":"yearSalary"},{"sTitle": "是否计税","mData":"sfjsTax"}
					  ];
		$("#salaryTab").dataTable({
			"bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bSort": true,
	        "bInfo": false,
	        "bAutoWidth": false,
			"sScrollX": "100%",
	        "sScrollXInner": "110%",
	        "bScrollCollapse": true,
	        "aoColumns": header, 
	        "aaData": jsonSalaryList.data.allSalaryList
		});
	}
});