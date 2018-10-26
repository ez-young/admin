package com.hzhetun.example.interceptor;

import com.hzhetun.example.pojo.SystemMenu;
import com.hzhetun.example.pojo.SystemUser;
import com.hzhetun.example.service.SystemMenuService;
import com.hzhetun.example.service.SystemUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private SystemMenuService systemMenuService;

	@Autowired
	private SystemUserService systemUserService;

	private Logger logger= Logger.getLogger(LoginInterceptor.class);

	private static final String MODULE="first";

	/**
	 * controller之前调用的方法
	 */
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession httpSession=request.getSession();
		logger.info("拦截的URI:"+request.getRequestURI());
		SystemUser user=(SystemUser) httpSession.getAttribute("user");
		if(user==null){
			logger.info("session time out");
			response.sendRedirect("/login");
            //后面的interceptor和controller都不执行
			return false;
		}
		else{
            //放行
			return true;
		}
	}

	/**
	 * controller执行之后,modelandview返回之前调用这个方法
	 */
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {
		if(modelAndView==null){
			return;
		}

		HttpSession httpSession=request.getSession();
		SystemUser user=(SystemUser) httpSession.getAttribute("user");

		//获取url
		String requestUrl=request.getRequestURI();

		String module="first";
		SystemMenu systemMenu=systemMenuService.findByUrl(requestUrl);
		if(systemMenu!=null){
			module=systemMenu.getModule();
		}

		request.setAttribute("requestUrl",requestUrl);
		request.setAttribute("module",module);

		List<SystemMenu> modules=systemMenuService.findByLevel(1);
		request.setAttribute("modules",modules);

		if(MODULE.equals(module)){
			request.setAttribute("systemmenus",systemMenuService.loadMenu().get(modules.get(0).getModule()));
		}
		else{
			request.setAttribute("systemmenus",systemMenuService.loadMenu().get(module));
		}

		Integer uid=user.getId();
		SystemUser systemUser=systemUserService.findOne(uid);
		request.setAttribute("imgId",systemUser.getImg());
	}

	/**
	 * 拦截器执行完成之后调用这个方法
	 */
	@Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
}
