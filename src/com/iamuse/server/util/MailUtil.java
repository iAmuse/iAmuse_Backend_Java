package com.iamuse.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sun.org.apache.bcel.internal.generic.RETURN;
@Component
public class MailUtil {
	@Autowired
	JavaMailSender mailSender;
	
	
	
	/*
	 * public JavaMailSender getJavaMailSender() { JavaMailSenderImpl sender = new
	 * JavaMailSenderImpl(); sender.setProtocol("smtp");
	 * sender.setHost("smtp.gmail.com"); sender.setPort(465);
	 * sender.setUsername("dev@iamuse.com"); sender.setPassword("M&n9yskP9x7e");
	 * 
	 * Properties mailProps = new Properties(); mailProps.put("mail.smtps.auth",
	 * "true"); mailProps.put("mail.smtp.starttls.enable", "true");
	 * mailProps.put("mail.smtp.debug", "true");
	 * 
	 * sender.setJavaMailProperties(mailProps);
	 * 
	 * return sender; }
	 */
	
	public  void sendEmail(String from,String to,String path,String subject,String attachmentName,String url,int i,boolean html){
		boolean result=false;
		
		 MimeMessage message = mailSender.createMimeMessage();
		 String htmlEmailpath=path+"//"+"email.html";
		   try{
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
				FileSystemResource file = new FileSystemResource(url+"/"+i+".jpg");
				helper.addAttachment(file.getFilename(), file);
		 
			}
			
		     }catch (MessagingException e) {
			throw new MailParseException(e);
		     }
		   mailSender.send(message);
	         }
	 
	

public  void sendEmailUploadMail(String from,String to,String path,String subject,String attachmentName,String url,String k,boolean html,String mailBody){
	boolean result=false;
	 MimeMessage message = mailSender.createMimeMessage();
	 String htmlEmailpath=path+"//"+"email.html";
	 String imgName=k;
	   try{
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		try{
			//StringWriter writer = new StringWriter();
			//IOUtils.copy(new FileInputStream(new File(htmlEmailpath)), writer);
			//helper.setText(writer.toString(), html);
			
		
		
		String[] img=imgName.split(",");//splits the string based on whitespace  
		for(String w:img){  
			System.out.println(w);
			FileSystemResource file = new FileSystemResource(url+"/"+w);
			helper.addAttachment(file.getFilename(), file);
		}
		helper.setText(mailBody, true);
		}catch (Exception e) {
			e.printStackTrace();	
		}
		}catch (MessagingException e) {
			throw new MailParseException(e);
	    }
	     	mailSender.send(message);
        }

}
