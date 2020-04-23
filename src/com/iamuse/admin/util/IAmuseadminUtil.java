package com.iamuse.admin.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.iamuse.admin.VO.TicketVO;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.paypal.constants.ServerConstants;
import com.paypal.sdk.NVPCallerServices;
import com.paypal.sdk.NVPDecoder;
import com.paypal.sdk.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class IAmuseadminUtil {
	private IAmuseadminUtil(){}
	private static final String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
	
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
	
	public static java.sql.Timestamp getTimeStamp()
	{
		DateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  TimeZone gmtTime = TimeZone.getTimeZone("IST");
		  gmtFormat.setTimeZone(gmtTime);
		  Timestamp ts = Timestamp.valueOf(gmtFormat.format(new Date()));
		  return ts;
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
	   fileUrl=folderPath+"/"+imageId.toString()+"."+format;
		}
		catch (Exception e) {
			
		}
		return fileUrl;
	}
	
	public static String writeFile1(byte[] bytes,String folderPath,String format)
	{
		String fileUrl=null;
		try
		{
		byte[] byteArray = Base64.decodeBase64(bytes);
		File folder=new File(folderPath);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+format));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	   fileUrl=folderPath;
		}
		catch (Exception e) {
			
		}
		return fileUrl;
	}
	
	public static String writeFile2(byte[] bytes,String folderPath,Integer pictureId,String format)
	{
		String fileUrl=null;
		try
		{
		byte[] byteArray = Base64.decodeBase64(bytes);
		File folder=new File(folderPath);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+pictureId.toString()+"."+format));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	   fileUrl=folderPath;
		}
		catch (Exception e) {
			
		}
		return fileUrl;
	}

	 public static String convertDateDDMMYYYToYYYMMDD(String date) {
		   String result="";
		   try {
			   result=new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd-mm-yyyy").parse(date));

		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		   return result;
	 }
	 public static String dateToString(Date date)
		{
			String dateString = "";
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				dateString = sdf.format(date);
			    return dateString;
			} catch (Exception e) {
				return dateString;
			}
		}
	public static java.sql.Timestamp getPlusTimeStamp(int month)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,month);
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		return currentTimestamp;
	}
	public static String getDatetimeInWord(Timestamp timestamp)
	{
		  String dateString="";
		try {
			if(timestamp==null)
			{
				return dateString;
			}
			else {
			 DateFormat format2 = new SimpleDateFormat("MMMMM dd, yyyy hh:mm");
			     dateString = format2.format(timestamp);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}
	public static String getRandomNumber() {
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i<8; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}
	
	
	public static Integer[] converStringToIntArray(String arr[])
	{
		Integer[] array = new Integer[arr.length];
		for (int i=0; i<arr.length;i++) {
			array[i]=Integer.parseInt(arr[i]);
		}
		return array;
	}
	
	
	 public static String cleanXSS(String value) {
			
			 if (value != null) {
		          
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
	 public static String checkNull(String stringValue) {
			if(stringValue == null)
				return "";
			else
				return stringValue;
		}
	public static void main(String[] args) throws ParseException {
	
	}
	
	public static String writeFile(byte[] byteArray,String folderPath,int applicantId,int appid, String extension)
	{
		return writeFile(byteArray, folderPath, applicantId, appid, extension, 0);
	}
	
	public static String writeFile(byte[] byteArray,String folderPath,int applicantId,int appid, String extension, int subscriptionId)
	{
		String fileUrl=null;
		try
		{
			File folder=new File(folderPath+"/"+applicantId+"/"+appid);
			if (!folder.exists()) {
				if (folder.mkdirs()){
					folder.setExecutable(true);
					folder.setReadable(true);
					folder.setWritable(true);
				} 
			}
			FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+applicantId+"/"+appid+"/"+extension));
			if(subscriptionId == 1) {
				String dataDirectory = folderPath+"/../..";
				String fileName = "/IAmuse/resources/images/images/logo.png";
		        Path path = Paths.get(dataDirectory, fileName);
				File watermarkImageFile = path.toFile();
				addWatermarkToImage(watermarkImageFile, byteArray, fop);
			} else {
			   fop.write(byteArray);
			}
		   fop.flush();
		   fop.close();
		   fileUrl=extension;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return "/iAmuse_images/Admin_Picture/"+applicantId+"/"+appid+"/"+extension;
	}
	public static String writeFile1(byte[] byteArray,String folderPath,int applicantId,int appid, String extension)
	{
		String fileUrl=null;
		try
		{
		
		File folder=new File(folderPath+"/"+applicantId+"/"+appid);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+applicantId+"/"+appid+"/"+extension));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	   fileUrl=extension;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return "/iAmuse_images/Admin_Picture/Image_mask/"+applicantId+"/"+appid+"/"+extension;
	}
	
	private static final String keyValue = "fd<[;.7e@OC0W!d|";
    private static final String ALG = "Blowfish";
    
    
    private static NVPCallerServices caller = null;

    public static NVPDecoder DoDirectPaymentCode(TicketVO ticketVO)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();

        try
        {

            caller = new NVPCallerServices();
        APIProfile profile = ProfileFactory.createSignatureAPIProfile();
            /*
             WARNING: Do not embed plaintext credentials in your application code.
             Doing so is insecure and against best practices.
             Your API credentials must be handled securely. Please consider
             encrypting them for use in any production environment, and ensure
             that only authorized individuals may view or modify them.
             */
        // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(ServerConstants.SELLER_API_USERNAME);
            profile.setAPIPassword(ServerConstants.SELLER_API_PASSWORD);
            profile.setSignature(ServerConstants.SELLER_API_SIGNATURE);
           
            /*profile.setAPIUsername("sdk-three_api1.sdk.com");
        profile.setAPIPassword("QFZCWN5HZM8VBG7Q");
        profile.setSignature("AVGidzoSQiGWu.lGj3z15HLczXaaAcK6imHawrjefqgclVwBe8imgCHZ"); */

            profile.setEnvironment("sandbox");
            profile.setSubject("");
            caller.setAPIProfile(profile);

            //encoder.add("VERSION", "86");
            encoder.add("VERSION", "51.0");
            encoder.add("METHOD","DoDirectPayment");

        // Add request-specific fields to the request string.
            encoder.add("PAYMENTACTION","Sale");
            encoder.add("AMT",""+ticketVO.getTotalTicketPrice());
            encoder.add("CREDITCARDTYPE",ticketVO.getCardType());     
            encoder.add("ACCT",ticketVO.getAcct());                       
            encoder.add("EXPDATE",ticketVO.getMonth()+ticketVO.getYear());
            encoder.add("CVV2",ticketVO.getCvv2());
            encoder.add("FIRSTNAME",ticketVO.getFirstName());

        // Execute the API operation and obtain the response.
            String NVPRequest = encoder.encode();
            String NVPResponse =(String) caller.call(NVPRequest);
            decoder.decode(NVPResponse);
            String strAck = decoder.get("ACK");
            String trasID = decoder.get("TRANSACTIONID");
            if(strAck !=null && !(strAck.equals("Success") || strAck.equals("SuccessWithWarning"))) {
            } else {
            }

        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return decoder;
    }
    
 
    public static String encrypt(String text) throws Exception
    {
    	 SecretKeySpec key = new SecretKeySpec(keyValue.getBytes(), ALG);
            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes());
            String encrypted = new BASE64Encoder ().encodeBuffer ( encryptedBytes );            
            encrypted= encrypted.replace('/', '@');
            encrypted= encrypted.replace('+', '*');
           return encrypted;    
    }
    
    public static String decrypt(String text) throws Exception
    {  
    	text=text.replace('@', '/');
    	text=text.replace('*', '+');
    	 SecretKeySpec key = new SecretKeySpec(keyValue.getBytes(), ALG);
    	Cipher cipher = Cipher.getInstance(ALG);
    	cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] recoveredBytes = cipher.doFinal( new BASE64Decoder ().decodeBuffer (  text ) );
        String recovered = new String(recoveredBytes);
        return recovered;
                
    }
    public static String writeFileUploadingImage(byte[] byteArray,String folderPath,int applicantId,int appid, String extension)
	{
		String fileUrl=null;
		try
		{
		
		File folder=new File(folderPath+"/"+applicantId+"/"+appid);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+applicantId+"/"+appid+"/"+extension));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	   fileUrl=extension;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return "/iAmuse_images/Admin_Picture/"+applicantId+"/"+appid+"/"+extension;
	}
   
    public static String writeFile1BYSuperAdmin(byte[] byteArray,String folderPath,int applicantId,int appid, String extension)
	{
		String fileUrl=null;
		try
		{
		File folder=new File(folderPath+"/"+applicantId+"/"+appid);
		if (!folder.exists()) {
			if (folder.mkdirs()){
				folder.setExecutable(true);
				folder.setReadable(true);
				folder.setWritable(true);
			} 
		}
	   FileOutputStream fop = new FileOutputStream(new File(folderPath+"/"+applicantId+"/"+appid+"/"+extension));
	   fop.write(byteArray);
	   fop.flush();
	   fop.close();
	   fileUrl=extension;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return "/iAmuse_images/Admin_Picture/Image_mask/"+applicantId+"/"+appid+"/"+extension;
	}
    public static String changeUploadFileName(MultipartFile file){
        String fileName=file.getOriginalFilename();
        String fileNameFirst=fileName.substring(0, fileName.indexOf('.'));
     
     String fileNames=fileNameFirst+'_'+getRandomNumber()+'.'+fileName.substring(fileName.indexOf('.')+1, fileName.length());
      return fileNames;
       }
    
    public static boolean stringToBool(String s) {
        s = s.toLowerCase();
        Set<String> trueSet = new HashSet<>(Arrays.asList("1", "true"));
        Set<String> falseSet = new HashSet<>(Arrays.asList("0", "false"));

        if (trueSet.contains(s))
            return true;
        if (falseSet.contains(s))
            return false;

        throw new IllegalArgumentException(s + " is not a boolean.");
    }
    
	private static void addWatermarkToImage(File watermarkImageFile, byte[] byteSourceImage, OutputStream destImageOutputStream) throws IOException{
		InputStream inStream = new ByteArrayInputStream(byteSourceImage);
		BufferedImage sourceImage = ImageIO.read(inStream);
		BufferedImage watermarkImage = ImageIO.read(watermarkImageFile);
		Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
		AlphaComposite alphaComposition = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
		g2d.setComposite(alphaComposition);
		int topLeftX = sourceImage.getWidth() - watermarkImage.getWidth();
		int	topLeftY = sourceImage.getHeight() - watermarkImage.getHeight();
		g2d.drawImage(watermarkImage, topLeftX,	 topLeftY, null);
		ImageIO.write(sourceImage, "png", destImageOutputStream);
		g2d.dispose();
	}
    
	/**
	 * Manage image compress quality
	 */
	private static final float IMAGE_COMPRESS_QUALITY = 0.85f;
	public static final String COMPRESSED_IMAGE_PREFIX = "compressed";

	/**
	 * Java Image compression API.
	 * 
	 * @param path
	 * @param imagePath
	 * @return
	 */
	public static String compressImageUsingJava(String path, String imagePath) {

		String compressedImagePath = null;
		try {

			File input = new File(imagePath);
			BufferedImage image = ImageIO.read(input);

			String fileName = input.getName();
			compressedImagePath = checkAndCreateDir(path, COMPRESSED_IMAGE_PREFIX) + "/" + fileName;
			File compressedImageFile = new File(compressedImagePath);
			OutputStream os = new FileOutputStream(compressedImageFile);

			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = (ImageWriter) writers.next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);

			ImageWriteParam param = writer.getDefaultWriteParam();

			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(IMAGE_COMPRESS_QUALITY);
			writer.write(null, new IIOImage(image, null, null), param);

			os.close();
			ios.close();
			writer.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return compressedImagePath;
	}

	/**
	 * Create compressed directory if not available in EmailImage Directory
	 * 
	 * @param path
	 * @return
	 */
	private static String checkAndCreateDir(String path, String dir) {
		String finalPath = path + File.separator + dir;

		if (path != null && path.length() > 0) {

			// Check if practicedata dir available
			File destDir = null;
			try {
				destDir = new File(finalPath);
				if (!destDir.exists()) {
					destDir.mkdir(); // Create practicedata if not available
				}
			} catch (Exception e) {
				e.printStackTrace();
				finalPath = null;
			} finally {
				if (destDir != null) {
					destDir = null;
				}
			}

		}
		return finalPath;
	}
	
	public static void getImageHeightWidth(AdminBoothEventPicture adminPicture, String fileUrl) {
			
			BufferedImage bimg = null;
			try {
				bimg = ImageIO.read(new File(fileUrl));
				int width          = bimg.getWidth();
				int height         = bimg.getHeight();
				
				adminPicture.setImageHeight(height+"");
				adminPicture.setImageWidth(width+"");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(bimg != null) {
					bimg.flush();
				}
			}
		}
}
