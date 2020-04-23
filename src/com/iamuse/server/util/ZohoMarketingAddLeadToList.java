package com.iamuse.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ZohoMarketingAddLeadToList {
	
	public static void zohoMarketingHumAddLeadUploadImage(String emailId) throws JsonParseException, JsonMappingException, IOException {
		try {

			Properties prop = new Properties();
			InputStream inputStream = ZohoMarketingAddLeadToList.class.getClassLoader()
					.getResourceAsStream("zohomarketinghub.properties");
			prop.load(inputStream);
			String grant_type = prop.getProperty("grant_type");
			String client_id = prop.getProperty("client_id");
			String client_secret = prop.getProperty("client_secret");
			String refresh_token = prop.getProperty("refresh_token");
			String resfmt = prop.getProperty("resfmt");

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headerAccessToken = new HttpHeaders();
			headerAccessToken.add("Accept", "application/json");
			headerAccessToken.add("Content-Type", "application/json");
			HttpEntity<String> requests = new HttpEntity<String>(headerAccessToken);

			String access_token_url = "https://accounts.zoho.com/oauth/v2/token";
			access_token_url += "?grant_type="+grant_type;
			access_token_url += "&client_id="+client_id;
			access_token_url += "&client_secret="+client_secret;
			access_token_url += "&refresh_token="+refresh_token;
			ResponseEntity<Object> responseForAccessToken = restTemplate.exchange(access_token_url, HttpMethod.POST,
					requests, Object.class);
			System.out.println("responseForAccessToken=" + responseForAccessToken.getBody());
			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writeValueAsString(responseForAccessToken.getBody());
			final ObjectNode node = new ObjectMapper().readValue(result, ObjectNode.class);
			String accessToken = null;
			if (node.has("access_token")) {
				accessToken = node.get("access_token").getTextValue();
				System.out.println("access_token: " + accessToken);
			}

			String oauthToken = "Zoho-oauthtoken " + accessToken;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", oauthToken);
			headers.add("Content-Type", "application/json");
			
			HttpEntity<String> req = new HttpEntity<String>(headers);
			String Leadurl = "https://marketinghub.zoho.com/api/v1/addleadsinbulk";
			Leadurl += "?listkey=fbfcda4ff4449ff32956524d90b0af16453c043008a6e1d4";
			Leadurl += "&resfmt="+resfmt;
			Leadurl += "&emailids=" + emailId;
			System.out.println("Inside restcall for lead=" + Leadurl);
			ResponseEntity<Object> responseForLeads = restTemplate.exchange(Leadurl, HttpMethod.POST, req,
					Object.class);
			System.out.println("Zoho Marketinghub leads added=" + responseForLeads.getStatusCode());
		} catch (Exception ex) {
			System.out.println("Exception=" + ex.getMessage());
		}

	}
	
	public static void zohoMarketingHumAddLeadSignUp(String emailId) throws JsonParseException, JsonMappingException, IOException {
		try {

			Properties prop = new Properties();
			InputStream inputStream = ZohoMarketingAddLeadToList.class.getClassLoader()
					.getResourceAsStream("zohomarketinghub.properties");
			prop.load(inputStream);
			String grant_type = prop.getProperty("grant_type");
			String client_id = prop.getProperty("client_id");
			String client_secret = prop.getProperty("client_secret");
			String refresh_token = prop.getProperty("refresh_token");
			String resfmt = prop.getProperty("resfmt");

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headerAccessToken = new HttpHeaders();
			headerAccessToken.add("Accept", "application/json");
			headerAccessToken.add("Content-Type", "application/json");
			HttpEntity<String> requests = new HttpEntity<String>(headerAccessToken);

			String access_token_url = "https://accounts.zoho.com/oauth/v2/token";
			access_token_url += "?grant_type="+grant_type;
			access_token_url += "&client_id="+client_id;
			access_token_url += "&client_secret="+client_secret;
			access_token_url += "&refresh_token="+refresh_token;
			ResponseEntity<Object> responseForAccessToken = restTemplate.exchange(access_token_url, HttpMethod.POST,
					requests, Object.class);
			System.out.println("responseForAccessToken=" + responseForAccessToken.getBody());
			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writeValueAsString(responseForAccessToken.getBody());
			final ObjectNode node = new ObjectMapper().readValue(result, ObjectNode.class);
			String accessToken = null;
			if (node.has("access_token")) {
				accessToken = node.get("access_token").getTextValue();
				System.out.println("access_token: " + accessToken);
			}

			String oauthToken = "Zoho-oauthtoken " + accessToken;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", oauthToken);
			headers.add("Content-Type", "application/json");

		
			HttpEntity<String> req = new HttpEntity<String>(headers);
			String Leadurl = "https://marketinghub.zoho.com/api/v1/addleadsinbulk";
			Leadurl += "?listkey=fbfcda4ff4449ff369abc96e1edef615130407cb69ff1411";
			Leadurl += "&resfmt="+resfmt;
			Leadurl += "&emailids=" + emailId;
			System.out.println("Inside restcall for lead=" + Leadurl);
			ResponseEntity<Object> responseForLeads = restTemplate.exchange(Leadurl, HttpMethod.POST, req,
					Object.class);
			System.out.println("Zoho Marketinghub leads added=" + responseForLeads.getStatusCode());
		} catch (Exception ex) {
			System.out.println("Exception=" + ex.getMessage());
		}

	}
}
