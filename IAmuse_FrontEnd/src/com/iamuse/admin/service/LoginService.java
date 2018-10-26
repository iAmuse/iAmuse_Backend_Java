package com.iamuse.admin.service;

import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.entity.BoothAdminLogin;

public interface LoginService {
	BoothAdminLogin isValidUser(SignInVO signInVO);
	
	void updateTour(Integer userId);
	void setLastUpdateImages(Integer userId);

	String forgotPassword(String username, String token);

	String resetPassword(SignInVO signInVO);

	String changePassword(SignInVO signInVO);

	BoothAdminLogin setFbDetails(String code);

	BoothAdminLogin isValidUserSocial(SignInVO signInVO);

	BoothAdminLogin setGoogleDetails(SignInVO signInVO);

}
