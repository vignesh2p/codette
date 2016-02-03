package com.codette.apps.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codette.apps.util.CommonConstants;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	
	  
	  @Override
	  public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		  HttpSession session = request.getSession();
		  if(session.getAttribute(CommonConstants.SESSION_TOKEN) == null){
			  response.sendRedirect(request.getContextPath()+"/"+CommonConstants.LOGIN_PAGE);
			  return false;
		  }
		  return true;
	  }
}
