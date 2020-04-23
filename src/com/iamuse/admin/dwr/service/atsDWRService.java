package com.iamuse.admin.dwr.service;

import javax.servlet.http.HttpServletRequest;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

@Service
@RemoteProxy(name="dwrService")
public class atsDWRService {
	HttpServletRequest request;
	@RemoteMethod
	public String getStatus(int i)
	{
		WebContext ctx = WebContextFactory.get();
		request = ctx.getHttpServletRequest();
		String status=(String) request.getSession().getAttribute("status");
		
		return status;
	}
}
