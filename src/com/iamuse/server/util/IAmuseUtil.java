package com.iamuse.server.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.iamuse.server.requestVO.ImageVO;




public class IAmuseUtil {
	
	private static final String ALGO = "DES";
	private static final byte[] keyValue = new byte[] { 'K', 'e', 'Y', 'F', 'o', 'r', 'p', 'r', 'o', 'p','e','r','t', 'y','B', 'a', 'z', 'a', 'a','r','S','e','r','v','e','r' };
	
	private static final String serviceAccountId="307502776686-pid916p2l1o781003mebvduvra3dcnba@developer.gserviceaccount.com";
	
	
	public static String encrypt(String Data) {
		String encryptedValue=null;   
		if(Data != null)
		{
			try {
				Key key = generateKey();

				Cipher c = Cipher.getInstance(ALGO);
				c.init(Cipher.ENCRYPT_MODE, key);
				byte[] encVal = c.doFinal(Data.getBytes());
				encryptedValue =Base64.encodeBase64String(encVal);

				encryptedValue=encryptedValue.replace("=","");
			} catch (Exception e) {
				System.out.println("Error : "+e.getMessage());
			}
			return encryptedValue.trim();
		}
		else
			return null;
	}

	public static String decrypt(String encryptedData){
		String decryptedValue=null;
		if(encryptedData != null)
		{
			try {
				Key key = generateKey();
				Cipher c = Cipher.getInstance(ALGO);
				c.init(Cipher.DECRYPT_MODE, key);
				byte[] decordedValue =Base64.decodeBase64(encryptedData);
				byte[] decValue = c.doFinal(decordedValue);
				decryptedValue = new String(decValue);
			} catch (Exception e) {
				System.out.println("Error : "+e.getMessage());
			}
			return decryptedValue.trim();
		}
		else
			return null;
	}

	private static Key generateKey() throws Exception {

		DESKeySpec deskey = new DESKeySpec(keyValue);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey key = skf.generateSecret(deskey);    	
		return key;
	}
	
	public static List<String> responseSplitter(String response)
	{
		List<String> responseList=new ArrayList<>();
		if(response != null)
		{


			String[] responseArray=response.split("::");
			if(responseArray.length>=2)
			{
				responseList.add(responseArray[0]);
				responseList.add(responseArray[1]);
			}
		}
		return responseList;

	}
	
	public static Integer[] converStringToIntArray(String arr[])
	{
		Integer array[] = new Integer[arr.length];
		
		for (int i=0; i<arr.length;i++) {
			array[i]=Integer.parseInt(arr[i]);
		}
		return array;
	}	
	public static java.sql.Timestamp getTimeStamp()
	{
		DateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  TimeZone gmtTime = TimeZone.getTimeZone("UTC");
		  gmtFormat.setTimeZone(gmtTime);
		  Timestamp ts = Timestamp.valueOf(gmtFormat.format(new Date()));
		  return ts;
	}
	public static String writeMultipleFile(List<ImageVO> fileString,String folderPath,Integer imageId,String format)
	{
		String fileUrl=null;
		int i=0;
		try
		{
			for (ImageVO images1 : fileString) {
				
			i++;
		byte byteArray[] = Base64.decodeBase64(images1.getImage());
		File folder=new File(folderPath);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+imageId.toString()+"/"+i+"."+format));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	  
		}
			 fileUrl=folderPath+"/"+imageId.toString()+"."+format;
		}	
		catch (Exception e) {
			
		}
		return fileUrl;
	}
	
	public static String writeFile(String fileString,String folderPath,Integer imageId,String format)
	{
		String fileUrl=null;
		try
		{
		byte[] byteArray = Base64.decodeBase64(fileString);
		File folder=new File(folderPath);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+imageId.toString()+"."+format));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	   fileUrl=folderPath;
		}
		catch (Exception e) {
			
		}
		return fileUrl;
	}
	
	public static String writeFile(byte[] byteArray,String folderPath,String id, String extension)
	{
		String fileUrl=null;
		try
		{
		
		File folder=new File(folderPath);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+id+"."+extension));
	   //FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+id));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	  fileUrl=folderPath+"/"+id+"."+extension;
	   //fileUrl=folderPath+"/"+id;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return fileUrl;
	}
	public static boolean deleteFile(String fileString,String folderPath,Integer imageId,String format)
	{
		boolean status=false;
		try
		{
		String fileUrl=folderPath+"/"+imageId.toString()+"."+format;
		
		File file=new File(fileUrl);
		if (file.exists()) {
			
			file.delete();
			status=true;
		}
	  
		}
		catch (Exception e) {
			
		}
		return status;
	}
	
	
	public static byte[] readFile(String url)
	{
		 File file=new File(url);
		 byte[] byteArray=read(file);
		 return byteArray;
		  
	}
	
	 public static byte[] read(File file){

		    
		    ByteArrayOutputStream ous = null;
		    InputStream ios = null;
		    try {
		       
		        ous = new ByteArrayOutputStream();
		        ios = new FileInputStream(file);
		       // System.out.println("available bytes "+ios.available());
		        byte[] buffer = new byte[ios.available()];
		        int read = 0;
		        while ( (read = ios.read(buffer)) != -1 ) {
		            ous.write(buffer, 0, read);
		        }
		    }
		    catch ( IOException e) {
		    	e.printStackTrace();
	        }finally { 
		        try {
		             if ( ous != null ) 
		                 ous.close();
		        } catch ( IOException e) {
		        }

		        try {
		             if ( ios != null ) 
		                  ios.close();
		        } catch ( IOException e) {
		        }
		    }
		    return ous.toByteArray();
		}
	
	 public static java.sql.Timestamp getPlusTimeStamp(int month)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH,month);
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			return currentTimestamp;
		}
	 
	 
	 public static String cleanXSS(String value) {
		
			//value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
			//value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
			//value = value.replaceAll("'", "& #39;");
			//value = value.replaceAll("<script>", "");
			//value = value.replaceAll("</script>", "");
			
			/*value = value.replaceAll("eval\\((.*)\\)", "");
			value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
			value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
			value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
			value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
			value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
			logger.info("Out cleanXSS RequestWrapper ........ value ......." + value);*/
			 if (value != null) {
		            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
		            // avoid encoded attacks.
		            // value = ESAPI.encoder().canonicalize(value);

		            // Avoid null characters
		            value = value.replaceAll("", "");

		            // Avoid anything between script tags
		            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid anything in a src='...' type of expression
		            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Remove any lonesome </script> tag
		            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Remove any lonesome <script ...> tag
		            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid eval(...) expressions
		            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid expression(...) expressions
		            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid javascript:... expressions
		            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid vbscript:... expressions
		            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onload= expressions
		            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		        }
			
			return value;
		}
	 
	public static void main(String[] args) {
		//System.out.println(PropertyBazaarUtil.readFile("D:\\images.jpg"));
		//String fileName = "D:\\images.jpg".substring("D:\\images.jpg".lastIndexOf(".")+1);
		//System.out.println(fileName);
		
		//System.out.println(PropertyBazaarUtil.encrypt("23"));
		//System.out.println(PropertyBazaarUtil.decrypt("JWuZKvZGjZw"));
		//System.out.println("String is : "+PropertyBazaarUtil.cleanXSS("<Script>alert('');<Script>"));
		
		//List<String> s = new ArrayList<String>();
		//System.out.println(s.size());
		//PropertyBazaarUtil.processApplePayment("ew0KCSJzaWduYXR1cmUiID0gIkFnY2hkQVVIaTdWOXdoV3ZsZVF2RTdhcHlOdjBGWUIxTG1BVG9vZkJ5ZzJjUTlrejU4QkJWR3R2bzROdTR3dlQzN2poTm9NTHVhanFtWGVSeEJ6SUk2eWRoYndIVG9qdE9Wem54QzNUb3lUNlFMWE1Pa2tPcVdEaDdiaUNXVTRwMTZmNVI4bWxPQjk0RWtEQ2owUHpnSnNqckJjdnFBUDVRWVAweHkvLzkyUXhBQUFEVnpDQ0ExTXdnZ0k3b0FNQ0FRSUNDR1VVa1UzWldBUzFNQTBHQ1NxR1NJYjNEUUVCQlFVQU1IOHhDekFKQmdOVkJBWVRBbFZUTVJNd0VRWURWUVFLREFwQmNIQnNaU0JKYm1NdU1TWXdKQVlEVlFRTERCMUJjSEJzWlNCRFpYSjBhV1pwWTJGMGFXOXVJRUYxZEdodmNtbDBlVEV6TURFR0ExVUVBd3dxUVhCd2JHVWdhVlIxYm1WeklGTjBiM0psSUVObGNuUnBabWxqWVhScGIyNGdRWFYwYUc5eWFYUjVNQjRYRFRBNU1EWXhOVEl5TURVMU5sb1hEVEUwTURZeE5ESXlNRFUxTmxvd1pERWpNQ0VHQTFVRUF3d2FVSFZ5WTJoaGMyVlNaV05sYVhCMFEyVnlkR2xtYVdOaGRHVXhHekFaQmdOVkJBc01Fa0Z3Y0d4bElHbFVkVzVsY3lCVGRHOXlaVEVUTUJFR0ExVUVDZ3dLUVhCd2JHVWdTVzVqTGpFTE1Ba0dBMVVFQmhNQ1ZWTXdnWjh3RFFZSktvWklodmNOQVFFQkJRQURnWTBBTUlHSkFvR0JBTXJSakYyY3Q0SXJTZGlUQ2hhSTBnOHB3di9jbUhzOHAvUndWL3J0LzkxWEtWaE5sNFhJQmltS2pRUU5mZ0hzRHM2eWp1KytEcktKRTd1S3NwaE1kZEtZZkZFNXJHWHNBZEJFakJ3Ukl4ZXhUZXZ4M0hMRUZHQXQxbW9LeDUwOWRoeHRpSWREZ0p2MllhVnM0OUIwdUp2TmR5NlNNcU5OTEhzREx6RFM5b1pIQWdNQkFBR2pjakJ3TUF3R0ExVWRFd0VCL3dRQ01BQXdId1lEVlIwakJCZ3dGb0FVTmgzbzRwMkMwZ0VZdFRKckR0ZERDNUZZUXpvd0RnWURWUjBQQVFIL0JBUURBZ2VBTUIwR0ExVWREZ1FXQkJTcGc0UHlHVWpGUGhKWENCVE16YU4rbVY4azlUQVFCZ29xaGtpRzkyTmtCZ1VCQkFJRkFEQU5CZ2txaGtpRzl3MEJBUVVGQUFPQ0FRRUFFYVNiUGp0bU40Qy9JQjNRRXBLMzJSeGFjQ0RYZFZYQWVWUmVTNUZhWnhjK3Q4OHBRUDkzQmlBeHZkVy8zZVRTTUdZNUZiZUFZTDNldHFQNWdtOHdyRm9qWDBpa3lWUlN0USsvQVEwS0VqdHFCMDdrTHM5UVVlOGN6UjhVR2ZkTTFFdW1WL1VndkRkNE53Tll4TFFNZzRXVFFmZ2tRUVZ5OEdYWndWSGdiRS9VQzZZNzA1M3BHWEJrNTFOUE0zd294aGQzZ1NSTHZYaitsb0hzU3RjVEVxZTlwQkRwbUc1K3NrNHR3K0dLM0dNZUVONS8rZTFRVDlucC9LbDFuaithQnc3QzB4c3kwYkZuYUFkMWNTUzZ4ZG9yeS9DVXZNNmd0S3Ntbk9PZHFUZXNicDBiczhzbjZXcXMwQzlkZ2N4Ukh1T01aMnRtOG5wTFVtN2FyZ09TelE9PSI7DQoJInB1cmNoYXNlLWluZm8iID0gImV3b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGNITjBJaUE5SUNJeU1ERTBMVEEwTFRJMUlEQTNPakEzT2pVd0lFRnRaWEpwWTJFdlRHOXpYMEZ1WjJWc1pYTWlPd29KSW5WdWFYRjFaUzFwWkdWdWRHbG1hV1Z5SWlBOUlDSXdaakExTlRnMFpXUTFOamM1T1daaU9HTm1ZV1poTVRBMk5HSTBOalptWWpZMlpqUmpZbVV4SWpzS0NTSnZjbWxuYVc1aGJDMTBjbUZ1YzJGamRHbHZiaTFwWkNJZ1BTQWlNVEF3TURBd01ERXdPRGt5TlRRME9TSTdDZ2tpWW5aeWN5SWdQU0FpTWk0d0lqc0tDU0owY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREV3T0RreU5UUTBPU0k3Q2draWNYVmhiblJwZEhraUlEMGdJakVpT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakV6T1RnME16UTROekE0TnpraU93b0pJblZ1YVhGMVpTMTJaVzVrYjNJdGFXUmxiblJwWm1sbGNpSWdQU0FpT1RNNU16azVOa1l0TURWRVFTMDBPVVUwTFVFMlFVVXRSVFF3UmprMlEwUTVSVGcySWpzS0NTSndjbTlrZFdOMExXbGtJaUE5SUNKUVN6QXpNRTlVU0NJN0Nna2lhWFJsYlMxcFpDSWdQU0FpTkRFNU1qazVNemszSWpzS0NTSmlhV1FpSUQwZ0ltdGhjbUZ2YTJVdWJXVnlZV2RoYm1FdVkyOXRJanNLQ1NKd2RYSmphR0Z6WlMxa1lYUmxMVzF6SWlBOUlDSXhNems0TkRNME9EY3dPRGM1SWpzS0NTSndkWEpqYUdGelpTMWtZWFJsSWlBOUlDSXlNREUwTFRBMExUSTFJREUwT2pBM09qVXdJRVYwWXk5SFRWUWlPd29KSW5CMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBMExUSTFJREEzT2pBM09qVXdJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1VpSUQwZ0lqSXdNVFF0TURRdE1qVWdNVFE2TURjNk5UQWdSWFJqTDBkTlZDSTdDbjA9IjsNCgkiZW52aXJvbm1lbnQiID0gIlNhbmRib3giOw0KCSJwb2QiID0gIjEwMCI7DQoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsNCn0NCg==");
		//PropertyBazaarUtil.checkAndroidPayment("com.propertybazaar", "pb_upload_new_3", "obajdnoefilocdhkjoeehcmg.AO-J1OwtjjZd5LKiRbQXXdYYSkH5mKn62Y9ncjHo1a_iH7Et6hhIJdB0EDkWOs4a4tGqRxjsQQNLEKKwsjhnCluAUjFWUyxNlTYAVP6uwffcMXyxvRyhhsWCtyrzK5udFKrq0w3Umc2p");
		getTimeStamp();	
	}
	
    public static boolean processApplePayment(final String receipt,String mode)
    {
    	
    	boolean result=false;
    	
    	String Url="";
    	if(mode.equals("0"))
    		Url = "https://sandbox.itunes.apple.com/verifyReceipt";
    	else
    		Url = "https://buy.itunes.apple.com/verifyReceipt";
         StringBuffer response=new StringBuffer();
       // final BASE64Encoder encoder = new BASE64Encoder();
       // final String receiptData = encoder.encode(receipt.getBytes());


        final String jsonData = "{\"receipt-data\" : \"" + receipt + "\"}";

        System.out.println(receipt);
        System.out.println(jsonData);

        try
        {
        	final URL url = new URL(Url);
            final HttpURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            //conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            final OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(jsonData);
            wr.flush();
            
            // Get the response
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
            	response.append(line);
            }
            System.out.println(response);
            wr.close();
            rd.close();
            
            
            JSONParser parser = new JSONParser();
            
        	try {
         
        		Object obj = parser.parse(response.toString());
         
        		JSONObject jsonObject = (JSONObject) obj;
         
        		Long status=1L;
				try 
				{
					status = (Long) jsonObject.get("status");
				}
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
        		System.out.println(status);
        		if(status==0)
        		result=true;	
         
        		String original_purchase_date_pst;
				try 
				{
					original_purchase_date_pst = (String) jsonObject.get("original_purchase_date_pst");
					System.out.println(original_purchase_date_pst);
				}
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
        		
         
        		// loop array
        		/*JSONArray msg = (JSONArray) jsonObject.get("messages");
        		Iterator<String> iterator = msg.iterator();
        		while (iterator.hasNext()) {
        			System.out.println(iterator.next());
        		}*/
         
        	} catch (ParseException e) {
        		result=false;
        		e.printStackTrace();
        	}
            
            
        }
        catch (IOException e)
        {
        	result=false;
          e.printStackTrace();
        }
        return result;
    }
    public static String  generateImageName() {
        final String charset="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();                 
          Random rand = new Random();
             
             for (int i = 0; i<8; i++) {
                 int pos = rand.nextInt(charset.length());
                 //System.out.println("Postion ===>> "+pos);
                 sb.append(charset.charAt(pos));
             } 
        String OrderId =sb.toString();
        return OrderId;
    }
    
    public static String writeFileImageUpload(String fileString,String folderPath,String imageId,String format)
	{
		String fileUrl=null;
		try
		{
		byte byteArray[] = Base64.decodeBase64(fileString);
		File folder=new File(folderPath);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+imageId+"."+format));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	   fileUrl=folderPath;
		}
		catch (Exception e) {
			
		}
		return fileUrl;
	}
    
    
}
