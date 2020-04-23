package com.iamuse.admin.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamuse.admin.VO.BoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.LoginBaseResponseVO;
import com.iamuse.admin.VO.LoginBoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.LoginVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.dao.LoginDao;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired LoginDao loginDao;

	private static final Logger log = Logger.getLogger(LoginService.class);
	
	@Override
	public BoothAdminLogin isValidUser(SignInVO signInVO) {
		return loginDao.isValidUser(signInVO);
	}
	
	@Override
	public BoothAdminLogin gMailIsValidUser(SignInVO signInVO) {
		return loginDao.gMailIsValidUser(signInVO);
	}
	
	@Override
	public void updateTour(Integer userId) {
		loginDao.updateTour(userId);
	}
	
	@Override
	public void setLastUpdateImages(Integer userId) {
		loginDao.setLastUpdateImages(userId);
		
	}

	@Override
	public String forgotPassword(String username,String token) {
		return loginDao.forgotPassword(username,token);
	}

	@Override
	public String resetPassword(SignInVO signInVO) {
		return loginDao.resetPassword(signInVO);
	}
	
	@Override
	public String changePassword(SignInVO signInVO) {
		return loginDao.changePassword(signInVO);
	}

	@Override
	public BoothAdminLogin setFbDetails(String code) {
		return loginDao.setFbDetails(code);
	}

	@Override
	public BoothAdminLogin isValidUserSocial(SignInVO signInVO) {
		return loginDao.isValidUserSocial(signInVO);
	}
	
	@Override
	public LoginBaseResponseVO fetchGmailLoginBaseResponseVO(
			LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO) {
		return loginDao.fetchGmailLoginBaseResponseVO(loginRegistrationRequestVO);
	}
	
	
	@Override
	public LoginBaseResponseVO saveGmailBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO) {
		return loginDao.saveGmailBoothRegistration(adminBoothRegistrationRequestVO);
	}
		
}
