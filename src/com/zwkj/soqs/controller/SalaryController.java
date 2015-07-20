package com.zwkj.soqs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwkj.soqs.base.BaseController;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.TeacherInfo;
import com.zwkj.soqs.service.SalaryService;
import com.zwkj.soqs.utils.Constants;
import com.zwkj.soqs.utils.Tools;
@Scope("request")
@Controller
public class SalaryController extends BaseController {
	@Autowired
	private SalaryService salaryService;
	
	//前台进入工资查询页面
	@RequestMapping(value = "query/index", produces = "text/html;charset=utf-8")
	public ModelAndView queryIndex(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		//request.setAttribute("verifyFlag",verifyFlag);
		view.setViewName("index");
		return view;
	}
	
	//查询工资
	@RequestMapping(value = "query/salary", produces = "text/html;charset=utf-8")
	public ModelAndView querySalary(HttpServletRequest request, RedirectAttributes attributes){
		ModelAndView view = new ModelAndView();
		try {
			String teacherId = request.getParameter("teacherId");
			String queryPassword = request.getParameter("pass");
			//system.out.println("teacherId: "+teacherId);
			//system.out.println("queryPassword: "+queryPassword);
			TeacherInfo teacherInfo = new TeacherInfo();
			teacherInfo.setTeacherId(teacherId);
			teacherInfo.setQueryPassword(queryPassword);
			
			TeacherInfo info = salaryService.verifyId(teacherInfo);
			if(Tools.isNull(info)){
				/**
				 * 重定向传值RedirectAttributes
				 * 1:用addAttribute(),会在地址栏中显示参数，在页面用EL表达式接收参数的时候要加param.例如${param.verifyFlag}
				 * 2:用addFlashAttribute(),参数不会显示到地址栏,直接用EL表达式获取参数${verifyFlag}
				 */
				//attributes.addAttribute("verifyFlag", "true");
				attributes.addFlashAttribute("verifyFlag", "true");
				view.setViewName("redirect:index.html");
			}else{
				returns = salaryService.getSalaryInfo(info);
				//system.out.println(returns.generateJsonData());
				HttpSession session = request.getSession();
				session.setAttribute("teacherInfo", info);
				session.setAttribute("salaryList", returns.generateJsonData());
				view.setViewName("redirect:list.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	//验证通过后显示用户工资列表
	@RequestMapping(value = "query/list", produces = "text/html;charset=utf-8")
	public ModelAndView salaryList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		TeacherInfo teacherInfo = (TeacherInfo) session.getAttribute("teacherInfo");
		if(Tools.isNull(teacherInfo)){
			view.setViewName("redirect:index.html");
		}else{
			view.setViewName("list");
		}
		return view;
	}
	
	//显示密码修改页面
	@RequestMapping(value = "query/initModify", produces = "text/html;charset=utf-8")
	public ModelAndView initModify(){
		ModelAndView view = new ModelAndView();
		view.setViewName("xiugai");
		return view;
	}
	
	//修改密码
	@RequestMapping(value = "query/modify", produces = "text/html;charset=utf-8")
	public ModelAndView modifyPassword(HttpServletRequest request,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView();
		String teacherId = request.getParameter("teacherId");
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String confirmPass = request.getParameter("confirmPass");
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherId(teacherId);
		teacherInfo.setQueryPassword(oldPass);
		teacherInfo.setNewPassword(newPass);
		
		TeacherInfo info = null;
		try {
			if(!newPass.equals(confirmPass)){
				attributes.addFlashAttribute("alertFlg", Constants.ALERT_MESSAGE_NOT_SAME);
				view.setViewName("redirect:initModify.html");
			}else{
				info = salaryService.verifyId(teacherInfo);
				if(Tools.isNull(info)){
					attributes.addFlashAttribute("alertFlg", Constants.ALERT_MESSAGE_NO_USER);
					//request.setAttribute("alertFlg", Constants.ALERT_MESSAGE_NO_USER);
					view.setViewName("redirect:initModify.html");
				}else{
					salaryService.midifyPassword(teacherInfo);
					attributes.addFlashAttribute("verifyFlag",Constants.ALERT_MESSAGE_MODIFY_SUCCESS);
					view.setViewName("redirect:index.html");
				}
			}
		} catch (SoqsException e) {
			e.printStackTrace();
		}
		return view;
	}
}
