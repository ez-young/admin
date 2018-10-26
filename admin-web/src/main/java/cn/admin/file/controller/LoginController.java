package cn.admin.file.controller;

import cn.admin.file.enums.UserStatusEnum;
import cn.admin.file.pojo.SystemUser;
import cn.admin.file.utils.RestfulResult;
import cn.admin.file.service.SystemUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author ChenBingKun
 * @date
 */
@Controller
public class LoginController {
	
	@Autowired
	private SystemUserService systemUserService;

	private Logger logger= Logger.getLogger(LoginController.class);
	
	/**
	 * 跳转到登录页面(多个请求对应的一个方法)
	 * @return
	 */
	@RequestMapping(value = {"/login","/"})
	public String login(){
		return "login";
	}
	
	/**
	 * 登录方法
	 */
	@ResponseBody
	@RequestMapping(value = "doLogin",method = RequestMethod.POST)
	public RestfulResult doLogin(SystemUser systemUser, HttpSession httpSession){
		SystemUser user= systemUserService.login(systemUser);
		if(user==null){
			return RestfulResult.warning(-10,"用户名或者密码错误");
		}
		else{
			if(user.getStatus()== UserStatusEnum.FORBIDDEN.getStatus()){
				return RestfulResult.warning(-20,"账户被禁用");
			}

			httpSession.setAttribute("user", user);
			//以秒为单位 session有效期一个小时
			httpSession.setMaxInactiveInterval(60*60);
			logger.info("登录成功");
			return RestfulResult.success();
		}
	}

	@RequestMapping(value = "logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(Model model, HttpSession session){
		return "index";
	}
}
