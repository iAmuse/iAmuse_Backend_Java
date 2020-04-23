package com.iamuse.server.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.amuse.server.dao.UserDao;
import com.iamuse.server.requestVO.CrashLogsRequestVO;
import com.iamuse.server.requestVO.FileVO;
import com.iamuse.server.requestVO.UploadImageRequestVO;
import com.iamuse.server.requestVO.UploadImageWithEmailRequestVO;




@Lazy(true)
@Component
public class IAmuseValidator {

	private Locale locale = LocaleContextHolder.getLocale();
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserDao userDao;
 
	public String validateImageUploadRequest(UploadImageRequestVO uploadImageRequestVO)
	{
		String result = null;
		
			if(uploadImageRequestVO.getImages()!=null )
			{
				
				result = messageSource.getMessage("response.success", null, locale);
				
			}else {
				result = messageSource.getMessage("image.upload.empty", null, locale);
			}
		return result;
	}
	public String validateImageUploadWithEmail(UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO)
	{
		String result = null;
		if(uploadImageWithEmailRequestVO.getImages().size()<4)
		{
			if(uploadImageWithEmailRequestVO.getImages()!=null )
			{
				if(uploadImageWithEmailRequestVO.getEmailId()!=null && uploadImageWithEmailRequestVO.getEmailId()!="") {
					result = messageSource.getMessage("response.success", null, locale);
			}
				else{
				result = messageSource.getMessage("image.email.empty", null, locale);
				}
			}else{
				result = messageSource.getMessage("image.upload.empty", null, locale);
			}
		}
		else{
			result = messageSource.getMessage("image.upload.size", null, locale);
		}
		return result;
	}
	public String validateCrashlogsupload(CrashLogsRequestVO crashLogsRequestVO) {
		String result = null;
		if(crashLogsRequestVO.getUserId()!=null){
		if (crashLogsRequestVO.getFiles().size() != 0) {
			for(FileVO fileVO : crashLogsRequestVO.getFiles())
			{
				if(null != fileVO.getFile()){
					result = messageSource.getMessage("response.success", null,
							locale);
				}
				else{
					result = messageSource.getMessage("crashlog.file.empty", null,
							locale);
				}
			}
				result = messageSource.getMessage("response.success", null,
						locale);
		}
		else {
			result = messageSource.getMessage("crashlog.file.empty", null,
					locale);
		}
		}
		else {
			result = messageSource.getMessage("iamuse.userId.empty", null,
					locale);
		}
		return result;
	}
	
	  
	  
	  
	  
	
	  
}