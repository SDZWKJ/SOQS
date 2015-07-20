package com.zwkj.soqs.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwkj.soqs.base.BaseController;
import com.zwkj.soqs.base.ControllerReturns;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.AdminInfo;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;
import com.zwkj.soqs.service.AdminService;
import com.zwkj.soqs.service.SalaryService;
import com.zwkj.soqs.service.TeacherInfoService;
import com.zwkj.soqs.utils.Constants;
import com.zwkj.soqs.utils.Tools;

@Scope("request")
@Controller
public class AdminCotroller extends BaseController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private SalaryService salaryService;
	@Autowired
	private TeacherInfoService teacherInfoService;
	
	List<SalaryInfo> salaryLst = null;
	
	//后台登录界面
	@RequestMapping(value = "admin/login", produces = "text/html;charset=utf-8")
	public ModelAndView adminIndex(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("login");
		return view;
	}
	
	//进入管理页面
	@RequestMapping(value="admin/managePage",produces="text/html;charset=utf-8")
	public ModelAndView managePage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("manage");
		return view;
	}
	
	//登录验证
	@RequestMapping(value = "admin/validate", produces = "text/html;charset=utf-8")
	public ModelAndView validateLogin(HttpServletRequest request,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView();
		String loginId = request.getParameter("loginId");
		String loginPass = request.getParameter("loginPass");
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setLoginId(loginId);
		adminInfo.setLoginPassword(loginPass);
		AdminInfo info = null;
		HttpSession session = request.getSession();
		try {
			info = adminService.validateLogin(adminInfo);
			if(!Tools.isNull(info)){
				System.out.println("登录成功");
				session.setAttribute("user", info);		
				view.setViewName("redirect:managePage.html");
			}else{
				view.setViewName("redirect:login.html");
				attributes.addFlashAttribute("alertMessage",Constants.ALERT_MESSAGE_LOGIN_FAILURE);
			}
		} catch (SoqsException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	//查询所有工资信息
	@RequestMapping(value = "admin/getAllSalary", produces = "text/html;charset=utf-8")
	public @ResponseBody 
	String getAllSalaryInfo(HttpServletRequest request){
		int curYear = Tools.getCurrentYear();      //获取当年
		int curMonth = Tools.getCurrentMonth();    //获取当前月
		
		String currentYear = request.getParameter("selYear");
		String currentMonth = request.getParameter("selMonth");
		//System.out.println(currentYear);
		//System.out.println(currentMonth);
		if(Tools.isEmpty(currentYear)){
			currentYear = String.valueOf(curYear);
		}
		if(Tools.isEmpty(currentMonth)){
			currentMonth = String.valueOf(curMonth+1);
		}
		
		System.out.println(currentYear);
		System.out.println(currentMonth);
		
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setSelYear(currentYear);
		teacherInfo.setSelMonth(currentMonth);
		try {
			returns = salaryService.getSalaryInfo(teacherInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	
	//工资信息上传
//	@RequestMapping(value="admin/upload1",produces="text/html;charset=utf-8")
//	public ModelAndView uploadSalaryFile(@RequestParam(value = "file",required=false) MultipartFile file,HttpServletRequest request,RedirectAttributes attributes){
//		ModelAndView view = new ModelAndView();
//		System.out.println("进入uploadSalaryFile()");
//		String path = request.getSession().getServletContext().getRealPath("upload");
//		String fileName = file.getOriginalFilename();
//		System.out.println("path: "+path);
//		File targetFile = new File(path, fileName);
//		if(!targetFile.exists()){
//			targetFile.mkdirs();
//		}
//		try {
//			file.transferTo(targetFile);
//			System.out.println("上传完毕。。。。");
//			//读取excel的内容
//			String targetPath = path+"\\"+fileName;
//			System.out.println("targetPath "+targetPath);
//			adminService.insertSalaryInfo(targetPath);
//			attributes.addFlashAttribute("tabFlag", "tab1");
//			attributes.addFlashAttribute("importFlag", "sseccess");
//			System.out.println("导入数据库完毕..............");
//		}  catch (Exception e) {
//			e.printStackTrace();
//		}
//		view.setViewName("redirect:managePage.html");
//		return view;
//	}
	
	@RequestMapping(value="admin/upload1",produces="text/html;charset=utf-8")
	public @ResponseBody 
	String uploadSalaryFile(@RequestParam(value = "file",required=false) MultipartFile file,HttpServletRequest request,RedirectAttributes attributes){
		returns = new ControllerReturns();
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		System.out.println("path: "+path);
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			System.out.println("上传完毕。。。。");
			//读取excel的内容
			String targetPath = path+"\\"+fileName;
			System.out.println("targetPath "+targetPath);
			List<SalaryInfo> lst = salaryService.readSalaryExcel(targetPath); //把读取的内容放到List<Salary>中
			if(!CollectionUtils.isEmpty(lst)){
				SalaryInfo salaryInfo = lst.get(0);
				TeacherInfo teacherInfo = new TeacherInfo();
				teacherInfo.setSelYear(salaryInfo.getYearSalary());    //年度
				teacherInfo.setSelMonth(salaryInfo.getMonthSalary());  //月份
				
				List<SalaryInfo> lst2 = salaryService.getSalaryLst(teacherInfo); 
				if(!CollectionUtils.isEmpty(lst2)){                     //判断数据库中是否已有这个月的数据
					returns.putData("returnMessage", Constants.OVER_WRITE_OPTS);
				}else{
					returns.putData("returnMessage", Constants.CONTINUE_OPTS);
				}
				
			}else{
				returns.putData("returnMessage", Constants.FILE_NO_CONTENT);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		returns.putData("test", "file upload testing");
		return returns.generateJsonData();
	}
	
	//把List<Salary>写入数据库
	@RequestMapping(value="admin/insertSalaryInfo",produces="text/html;charset=utf-8")
	public @ResponseBody 
	String insertSalaryInfo(HttpServletRequest request){
		returns = new ControllerReturns();
		try {
			salaryService.inserSalaryInfo();
		} catch (SoqsException e) {
			returns.setSuccess(false);
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	
	
	//工资信息的删除
	//@RequestMapping(value="admin/deleteSalaryByIds",produces="text/html;charset=utf-8")
	public @ResponseBody
	String delteSalaryByIds(HttpServletRequest request){
		returns = new ControllerReturns();
		String ids = request.getParameter("ids");
		returns.putData("deleteNum", salaryService.deleteByIdStr(SalaryInfo.class, ids));
		return returns.generateJsonData();
	}
	
	//删除整月记录
	@RequestMapping(value="admin/delMonthRecord",produces="text/html;charset=utf-8")
	public @ResponseBody
	String delMonthRecord(HttpServletRequest request){
		int curYear = Tools.getCurrentYear();      //获取当年
		int curMonth = Tools.getCurrentMonth();    //获取当前月
		
		String currentYear = request.getParameter("selYear");
		String currentMonth = request.getParameter("selMonth");
		
		if(Tools.isEmpty(currentYear)){
			currentYear = String.valueOf(curYear);
		}
		if(Tools.isEmpty(currentMonth)){
			currentMonth = String.valueOf(curMonth+1);
		}
		
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setSelYear(currentYear);
		teacherInfo.setSelMonth(currentMonth);
		try {
			returns = salaryService.delMonthRecord(teacherInfo);
		} catch (SoqsException e) {
			returns.setSuccess(false);
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value="admin/editSalary",produces="text/html;charset=utf-8")
	public @ResponseBody 
	String edtiSalaryInfo(HttpServletRequest request){
		returns = new ControllerReturns();
		SalaryInfo salaryInfo = new SalaryInfo();
		
		String sid =  request.getParameter("sid");                                             //ID
		Integer id = null;
		if(!Tools.isEmpty(sid)){
			id = Integer.parseInt(sid);
		}
		String empId = request.getParameter("empId");                                    //职工编号
		String teacherName = request.getParameter("teacherName");                          //姓名
		String yfSalary = request.getParameter("yfSalary");                              //应发项
		String sfSalary = request.getParameter("sfSalary");                              //实发工资
		String jcSalary = request.getParameter("jcSalary");                              //基础工资
		String gwSalary = request.getParameter("gwSalary");                              //岗位工资
		String xjSalary = request.getParameter("xjSalary");                              //薪级工资
		String glSalary = request.getParameter("glSalary");                             //工龄工资
		String tgSalary = request.getParameter("tgSalary");                              //提高工资
		String jtSalary = request.getParameter("jtSalary");                              //津贴工资
		String qtjbSalary = request.getParameter("qtjbSalary");                          //其他基本工资
		String sydwjtbthjAllowance = request.getParameter("sydwjtbthjAllowance");        //事业单位津贴补贴合计
		String zwbtAllowance = request.getParameter("zwbtAllowance");                   //职务补贴
		String tgjtAllowance = request.getParameter("tgjtAllowance");                   //特钢津贴
		String jxjtAllowance = request.getParameter("jxjtAllowance");                    //警衔津贴
		String jhljtAllowance = request.getParameter("jhljtAllowance");                  //教护龄工资
		String bzrAllowance = request.getParameter("bzrAllowance");                      //班主任津贴
		String ggxbtWybtAllowance = request.getParameter("ggxbtWybtAllowance");          //改革性补贴二-物业补贴
		String qtbzAllowance = request.getParameter("qtbzAllowance");                    //其他补助工资
		String kpAward = request.getParameter("kpAward");                               //考评奖
		String qtSalary = request.getParameter("qtSalary");                              //其他工资
		String dsznAllowance = request.getParameter("dsznAllowance");                    //独生子女费
		String bfgzyfSalary = request.getParameter("bfgzyfSalary");                    //补发工资应发额
		String zfAllowance = request.getParameter("zfAllowance");                        //住房补贴
		String kfTotal = request.getParameter("kfTotal");                                //扣发小计
		String kgjjMoney = request.getParameter("kgjjMoney");						      //扣公积金
		String kyalbxMoney = request.getParameter("kyalbxMoney");                        //扣养老保险
		String kyilbxMoney = request.getParameter("kyilbxMoney");                        //扣医疗保险
		String dbbzjMoney = request.getParameter("dbbzjMoney");                          //大病补助金
		String iitMoney = request.getParameter("iitMoney");                              //应纳所得税额
		String kiitMoney = request.getParameter("kiitMoney");                            //扣所得税
		String monthSalary = request.getParameter("monthSalary");                        //月份
		String yearSalary = request.getParameter("yearSalary");                          //年度
		String sfjsTax = request.getParameter("sfjsTax");                                //是否计税
		
		
		salaryInfo.setId(id);
		salaryInfo.setEmpId(empId);
		salaryInfo.setTeacherName(teacherName);
		salaryInfo.setYfSalary(yfSalary);
		salaryInfo.setSfSalary(sfSalary);
		salaryInfo.setJcSalary(jcSalary);
		salaryInfo.setGwSalary(gwSalary);
		salaryInfo.setXjSalary(xjSalary);
		salaryInfo.setGlSalary(glSalary);
		salaryInfo.setTgSalary(tgSalary);
		salaryInfo.setJtSalary(jtSalary);
		salaryInfo.setQtjbSalary(qtjbSalary);
		salaryInfo.setSydwjtbthjAllowance(sydwjtbthjAllowance);
		salaryInfo.setZwbtAllowance(zwbtAllowance);
		salaryInfo.setTgjtAllowance(tgjtAllowance);
		salaryInfo.setJxjtAllowance(jxjtAllowance);
		salaryInfo.setJhljtAllowance(jhljtAllowance);
		salaryInfo.setBzrAllowance(bzrAllowance);
		salaryInfo.setGgxbtWybtAllowance(ggxbtWybtAllowance);
		salaryInfo.setQtbzAllowance(qtbzAllowance);
		salaryInfo.setKpAward(kpAward);
		salaryInfo.setQtSalary(qtSalary);
		salaryInfo.setDsznAllowance(dsznAllowance);
		salaryInfo.setBfgzyfSalary(bfgzyfSalary);
		salaryInfo.setZfAllowance(zfAllowance);
		salaryInfo.setKfTotal(kfTotal);
		salaryInfo.setKgjjMoney(kgjjMoney);
		salaryInfo.setKyalbxMoney(kyalbxMoney);
		salaryInfo.setKyilbxMoney(kyilbxMoney);
		salaryInfo.setDbbzjMoney(dbbzjMoney);
		salaryInfo.setIitMoney(iitMoney);
		salaryInfo.setKiitMoney(kiitMoney);
		salaryInfo.setMonthSalary(monthSalary);
		salaryInfo.setYearSalary(yearSalary);
		salaryInfo.setSfjsTax(sfjsTax);
		try {
			salaryService.updateSalaryInfo(salaryInfo);
		} catch (SoqsException e) {
			returns.setSuccess(false);
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	
	
	
	//查询所有用户信息
	@RequestMapping(value="admin/getAllUser",produces="text/html;charset=utf-8")
	public @ResponseBody
	String getAllUserInfo(HttpServletRequest request){
		try {
			returns  = teacherInfoService.getAllUserInfo();
		} catch (SoqsException e) {
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}

	//用户信息上传
	//@RequestMapping(value="admin/upload2",produces="text/html;charset=utf-8")
	public ModelAndView uploadUserFile(@RequestParam(value = "file",required=false) MultipartFile file,HttpServletRequest request,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView();
		System.out.println("进入uploadUserFile()");
		String path = request.getSession().getServletContext().getRealPath("upload2");
		String fileName = file.getOriginalFilename();
		System.out.println("path: "+path);
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			System.out.println("上传完毕。。。。");
			//读取excel的内容
			String targetPath = path+"\\"+fileName;
			System.out.println("targetPath "+targetPath);
			adminService.insertUserInfo(targetPath);
			attributes.addFlashAttribute("tabFlag", "tab2");
			attributes.addFlashAttribute("importFlag", "useccess");
			System.out.println("导入数据库完毕..............");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		view.setViewName("redirect:managePage.html");
		return view;
	}
	
	//用户信息添加
	@RequestMapping(value="admin/addUser",produces="text/html;charset=utf-8")
	public @ResponseBody 
	String addUserInfo(HttpServletRequest request){
		String teacherName = request.getParameter("userName");
		String teacherId = request.getParameter("teacherId");
		String empId = request.getParameter("empId");
		String queryPassword = "1234";
		if(!StringUtils.isEmpty(teacherId)){
			int len = teacherId.length();
			if(len>15){
				queryPassword = teacherId.substring(14);
			}else{
				queryPassword = teacherId.substring(11);
			}
		}
		
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherName(teacherName);
		teacherInfo.setTeacherId(teacherId);
		teacherInfo.setEmpId(empId);
		teacherInfo.setQueryPassword(queryPassword);
		try {
			returns = teacherInfoService.insertUser(teacherInfo);
		} catch (SoqsException e) {
			returns.setSuccess(false);
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	
	//用户信息的删除
	@RequestMapping(value="admin/deleteUseryByIds",produces="text/html;charset=utf-8")
	public @ResponseBody
	String delteUserByIds(HttpServletRequest request){
		returns = new ControllerReturns();
		String ids = request.getParameter("ids");
		try {
			returns.putData("deleteNum", teacherInfoService.deleteByIdStr(TeacherInfo.class, ids));
		} catch (SoqsException e) {
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	//用户信息修改
	@RequestMapping(value="admin/editUser",produces="text/html;charset=utf-8")
	public @ResponseBody
	String editUserInfo(HttpServletRequest request){
		returns = new ControllerReturns();
		TeacherInfo teacherInfo = new TeacherInfo();
	    int id = Integer.parseInt(request.getParameter("id"));
		String teacherName = request.getParameter("teacherName");
	    //String teacherId = request.getParameter("teacherId");
		String empId = request.getParameter("empId");
		String queryPassword = request.getParameter("queryPassword");
		teacherInfo.setId(id);
		teacherInfo.setTeacherName(teacherName);
		//teacherInfo.setTeacherId(teacherId);
		teacherInfo.setEmpId(empId);
		teacherInfo.setQueryPassword(queryPassword);
		try {
			teacherInfoService.updateUserById(teacherInfo);
		} catch (SoqsException e) {
			returns.setSuccess(false);
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	

}
