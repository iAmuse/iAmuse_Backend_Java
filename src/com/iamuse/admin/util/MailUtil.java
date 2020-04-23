package com.iamuse.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class MailUtil {
	@Autowired
	JavaMailSender mailSender;
	
	public  void sendEmail(String from,String to,String path,String subject,String url,int i,boolean html, String extensionType) throws javax.mail.MessagingException{
		boolean result;
		MimeMessage message = mailSender.createMimeMessage();
		String htmlEmailpath=path+"//"+"email.html";
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		try{
		StringWriter writer = new StringWriter();
		IOUtils.copy(new FileInputStream(new File(htmlEmailpath)), writer);
		helper.setText(writer.toString(),html);
		
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		for(;i>0;i--){
			FileSystemResource file = new FileSystemResource(url+"/"+i+extensionType);
			helper.addAttachment(i+".jpg", file);
		}
		     mailSender.send(message);
	         }
	

public  void sendUpgradeSubscriptionEmail(String from,String to,String testText, String subject) throws javax.mail.MessagingException{
	
	try{
		MimeMessage message = mailSender.createMimeMessage();
	 	MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(testText, true);
	    mailSender.send(message);
	}
	catch (Exception e) {
		e.printStackTrace();	
	}
}

public  String sendEmailUploadMailBYWebPortal(String from,String to,String path,String subject,String attachmentName,String url,String k,boolean html,String testText) throws javax.mail.MessagingException
	{
	 	String response="";
	 	MimeMessage message = mailSender.createMimeMessage();
	 	//String htmlEmailpath=path+"//"+"email.html";
	 	MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		
		try{
			//StringWriter writer = new StringWriter();
			//IOUtils.copy(new FileInputStream(new File(htmlEmailpath)), writer);
			//helper.setText(writer.toString(),html);
			
		}
		catch (Exception e) {
			response=e.getLocalizedMessage();	
		}
	
		FileSystemResource file = new FileSystemResource(IAmuseadminUtil.compressImageUsingJava(path, url));
		helper.addAttachment(file.getFilename(), file);
		helper.setText(testText, true);
	    mailSender.send(message);
	     
	    return response;
	}

public  boolean sendTestEmail(String from,String to,String subject) throws javax.mail.MessagingException{
	boolean result=false;
	try {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
	 
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText("Test Mail by IAmuse");
		
//		 Properties p = new Properties();
//	        p.setProperty("mail.smtp.host", "smtp.gmail.com");
//	        p.put("mail.smtp.port", 587);
//	        p.put("mail.smtp.auth", "true");
	        
		
			/*
			 * Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
			 * props.put("mail.transport.protocol", "smtp"); props.put("mail.smtp.auth",
			 * "true"); props.put("mail.smtp.starttls.enable", "true");
			 * props.put("mail.debug", "true");
			 */
		
		
		
		
		mailSender.send(message);
		     result=true;
	} catch (Exception e) {
		e.printStackTrace();
		return result;
	}
	return result;
         }

	public void sendEmailByInfo(String from,String to,String messageText,String subject){
		   MimeMessage message = mailSender.createMimeMessage();
		    boolean result=false;
		    try{
		    
		     MimeMessageHelper helper = new MimeMessageHelper(message, true);
		     
		     helper.setFrom(from);
		     helper.setTo(to);
		     helper.setSubject(subject);
		     helper.setText(messageText,true);
		   
		     result=true;
		    } 
		    catch (MessagingException e) {
		    throw new MailParseException(e);
		        }
		    mailSender.send(message);
		    }
}

