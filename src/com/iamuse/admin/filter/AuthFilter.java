//package com.iamuse.admin.filter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.iamuse.admin.entity.BoothAdminLogin;
//
//public class AuthFilter implements HandlerInterceptor {
//	
//	
//	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
//		
//		System.out.println("Pre-handle");
//		/*if(null == request.getSession(false)){
//	        //or response.sendError(..) etc.
//				 response.sendRedirect(request.getContextPath()+"/");
//				 return false;
//			}
//	       // return false;*/
//		return true;
//	}
//	
//	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
//		System.out.println("Post-handle");
//	}
//	
//	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
//		System.out.println("After completion handle");
//	}
//
//	
//
//
//}