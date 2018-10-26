package com.iamuse.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestTemplateZoho {
	
	@Autowired MessageSource messageSource;
	private Locale locale = LocaleContextHolder.getLocale();
	
	
	public Object zohoRequestToServer(String urls) throws IOException {
		/***
		 * start before redirect to either event list or subscription page validate to
		 * the zoho server for auto subscription
		 */

	
		URL url;
		HttpURLConnection request1 = null;
		String authtoken = messageSource.getMessage("zoho.subscriptionId",null,locale);
		String method = messageSource.getMessage("zoho.methodType",null,locale);;
		String orgId = messageSource.getMessage("zoho.orgIdLive",null,locale);
		try {
			/*
			 * Set the URL and Parameters to create connection Set Request Method (GET, POST
			 * or DELETE)
			 */
			if ("GET".equals(method)) {
				// parameters = "Authorization=" + authtoken +
				// "&X-com-zoho-subscriptions-organizationid=660452433";
				url = new URL(urls);
				request1 = (HttpURLConnection) url.openConnection();
				request1.setRequestMethod("GET");
				request1.setRequestProperty("Authorization", authtoken);
				request1.setRequestProperty("X-com-zoho-subscriptions-organizationid", orgId);
				request1.setRequestProperty("Accept", "application/json");
				request1.setDoOutput(true);
				request1.setDoInput(true);
				request1.connect();
			}
			// Get Response
			BufferedReader bf = new BufferedReader(new InputStreamReader(request1.getInputStream()));

			String line;
			StringBuffer response1 = new StringBuffer();
			while ((line = bf.readLine()) != null) {
				response1.append(line);
				response1.append('\r');
			}
			bf.close();
			// Response HTTP Status Code
			System.out.println("Response HTTP Status Code : " + request1.getResponseCode());
			System.out.println("Response Body : " + response1.toString());
			return response1;
	} finally {
			request1.disconnect();
	}

}
}

