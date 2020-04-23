package com.iamuse.server.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ZohoContactsSynUtil {
	
	public static void zohoContactSync(String emailId) throws ClientProtocolException, IOException {
	
	HttpClient client = new DefaultHttpClient();
	String productImaUse="https://campaigns.zoho.com/api/addlistsubscribersinbulk?"
			+"scope=CampaignsAPI"
			+ "&resfmt=JSON"
			+ "&authtoken=33e8d3683c3a13bcaeff859002297da8"
			+ "&listkey=d1ec5d9ab01c9c07011e644c02d82e148060bef678df20a0"
			+ "&emailids="+emailId;
	HttpGet getProductRequest=createHttpGet(productImaUse);
	HttpResponse getIamUseProductResponse =client.execute(getProductRequest);

}

private static HttpGet createHttpGet(String url) {
	
	HttpGet request = new HttpGet(url);
	request.setHeader("Content-Type", "application/json");
//	request.setHeader("Authorization", "Zoho-authtoken 3241cfd56afbc162bed1af6b7f47902c");
//	request.setHeader("X-com-zoho-subscriptions-organizationid", "690292259");
	return request;
}
}