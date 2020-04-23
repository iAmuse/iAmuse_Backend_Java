package com.iamuse.admin.dao;

import com.iamuse.admin.VO.BoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.LoginBaseResponseVO;
import com.iamuse.admin.VO.LoginBoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.LoginVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.entity.BoothAdminLogin;

public interface LoginDao {
	
	BoothAdminLogin isValidUser(SignInVO signInVO);
	
	BoothAdminLogin gMailIsValidUser(SignInVO signInVO);
	
	void updateTour(Integer userId);
	
	void setLastUpdateImages(Integer userId);

	String forgotPassword(String username, String token);

	String resetPassword(SignInVO signInVO);

	String changePassword(SignInVO signInVO);

	BoothAdminLogin setFbDetails(String code);

	BoothAdminLogin isValidUserSocial(SignInVO signInVO);
	
	public LoginBaseResponseVO fetchGmailLoginBaseResponseVO(LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO);
	
	public LoginBaseResponseVO saveGmailBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO);
	
}
