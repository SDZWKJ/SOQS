package com.zwkj.soqs.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
		TeacherInfo teacherInfo = new TeacherInfo();
		try {
			returns = salaryService.getSalaryInfo(teacherInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	
	//工资信息上传
	@RequestMapping(value="admin/upload1",produces="text/html;charset=utf-8")
	public ModelAndView uploadSalaryFile(@RequestParam(value = "file",required=false) MultipartFile file,HttpServletRequest request,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView();
		System.out.println("进入uploadSalaryFile()");
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
			adminService.insertSalaryInfo(targetPath);
			attributes.addFlashAttribute("tabFlag", "tab1");
			attributes.addFlashAttribute("importFlag", "sseccess");
			System.out.println("导入数据库完毕..............");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		view.setViewName("redirect:managePage.html");
		return view;
	}
	
	//工资信息的删除
	@RequestMapping(value="admin/deleteSalaryByIds",produces="text/html;charset=utf-8")
	public @ResponseBody
	String delteSalaryByIds(HttpServletRequest request){
		returns = new ControllerReturns();
		String ids = request.getParameter("ids");
		returns.putData("deleteNum", salaryService.deleteByIdStr(SalaryInfo.class, ids));
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
	@RequestMapping(value="admin/upload2",produces="text/html;charset=utf-8")
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
			returns = teacherInfoService.save(teacherInfo);
		} catch (SoqsException e) {
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
	    String teacherId = request.getParameter("teacherId");
		String empId = request.getParameter("empId");
		String queryPassword = request.getParameter("queryPassword");
		teacherInfo.setId(id);
		teacherInfo.setTeacherName(teacherName);
		teacherInfo.setTeacherId(teacherId);
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
