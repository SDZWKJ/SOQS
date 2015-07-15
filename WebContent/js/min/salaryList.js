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
		var header = [{"sTitle": "编号","mData":"id"},{"sTitle": "身份证","mData":"teacherId"},{"sTitle": "姓名","mData":"teacherName"},
					  {"sTitle": "年份","mData":"year"},{"sTitle": "月份","mData":"Month"},{"sTitle": "工资","mData":"salary"}
					];
		$("#salaryTab").dataTable({
			"bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": true,
	        "bSort": true,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": header, 
	        "aaData": jsonSalaryList.data.salaryList
		});
	}
});