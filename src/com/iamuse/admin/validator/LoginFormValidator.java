package com.iamuse.admin.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.iamuse.admin.VO.LoginVO;
import com.iamuse.admin.VO.SignInVO;

@Component
public class LoginFormValidator {

	@Autowired
	private MessageSource messageSource;
	private Locale locale = LocaleContextHolder.getLocale();
	public String validateLoginForm(LoginVO loginVO)
	{
		String result ;
		if(loginVO.getUsername()!=null && !("").equals(loginVO.getUsername()))
		{
			if(loginVO.getPassword()!=null && !("").equals(loginVO.getPassword()))
			{
				result = messageSource.getMessage("success", null, locale);
			}
			else
			{
				result = messageSource.getMessage("loginVO.password.NotEmpty", null, locale);
			}
		}
		else
		{
			result = messageSource.getMessage("loginVO.userName.NotEmpty", null, locale);
		}
		return result;
	}
	public String validateBoothLoginForm(SignInVO signInVO) {
		String result;
		if(signInVO.getEmailId()!=null && ! ("").equals(signInVO.getEmailId()))
		{
			if(signInVO.getPassword()!=null && !("").equals(signInVO.getPassword()))
			{
				result = messageSource.getMessage("success", null, locale);
			}
			else
			{
				result = messageSource.getMessage("loginVO.password.NotEmpty", null, locale);
			}
		}
		else
		{
			result = messageSource.getMessage("loginVO.userName.NotEmpty", null, locale);
		}
		return result;
	}
	
	public String validateGmailBoothLoginForm(SignInVO signInVO) {
		String result;
		if(signInVO.getEmailId()!=null && ! ("").equals(signInVO.getEmailId()))
		{
			result = messageSource.getMessage("success", null, locale);
		}		
		else
		{
			result = messageSource.getMessage("loginVO.EmailId.NotEmpty", null, locale);
		}
		return result;
	}
}
