package com.iamuse.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 
 * This class is used as security interceptor for all incoming requests.
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter{
	private static final Logger log = Logger.getLogger(SecurityInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.info("Request Path:: "+req.getRequestURL().toString());
		try {
			
			System.out.println(req.getHeader("Authorization"));
			
			
			if(req.getRequestURL().toString().contains("req-resp") ||req.getRequestURL().toString().contains("resources") || req.getRequestURL().toString().contains("downloadmedia"))
				return true;
			
			 if(req.getHeader("Authorization")==null)
			 {
				 res.getWriter().write("{responseCode:\"0009\",responseDescription:\"Authorization key is required.\"}");
				 res.setContentType("application/json");
				 return false;
			 }
			 else if(!req.getHeader("Authorization").equals("DrEgBqmYbTXJqi2/a/H9O9YLYcRNjNTNn89BKpui1Y8"))
			 {
				 res.getWriter().write("{responseCode:\"0010\",responseDescription:\"Access Denied.\"}");
				 res.setContentType("application/json");
				 return false;
			 }
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return true;
		
		
	}
	
	
	
	
	
		
	
}
