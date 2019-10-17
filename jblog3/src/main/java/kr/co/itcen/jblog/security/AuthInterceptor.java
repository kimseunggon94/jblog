package kr.co.itcen.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.itcen.jblog.vo.UserVo;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler)
					throws Exception {

	
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}

		
		UserVo authUser =(UserVo) session.getAttribute("authUser");
		System.out.println(authUser.getId()+"11111111111");
		if("aaa@aaa".equals(authUser.getId())==false){ 
			response.sendRedirect(request.getContextPath());
			return false;
		}
		

		return true;
	}

}