package com.iamuse.admin.daoimpl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.iamuse.admin.VO.LoginVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.controller.FBConnection;
import com.iamuse.admin.dao.LoginDao;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.service.FBGraph;
import com.iamuse.admin.util.Crypto;
import com.paypal.constants.ServerConstants;


@Repository
public class LoginDaoImpl implements LoginDao{
	@Autowired SessionFactory sessionFactory;
	
	private static final Logger log = Logger.getLogger(LoginDao.class);
	/*****************************************************|| Start Previous IAmuse Code ||************************************/	
	
	@Override
	public BoothAdminLogin isValidUser(SignInVO signInVO)
	{
		BoothAdminLogin boothAdminLogin=null;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("emailId",signInVO.getEmailId()));
			//criteria.add(Restrictions.eq("password",signInVO.getPassword()));
			Criterion enc = Restrictions.eq("password",Crypto.encrypt(signInVO.getPassword()));
			Criterion nor = Restrictions.eq("password",signInVO.getPassword());
			LogicalExpression orExp = Restrictions.or(enc,nor);
			criteria.add(orExp);
			boothAdminLogin=(BoothAdminLogin)criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("LoginDaoImpl Method : isValidUser");
			log.error("Error isValidUser",e);
		}
		return boothAdminLogin;
	}
	/*****************************************************|| End Previous IAmuse Code ||************************************/

	
	@Override
	public BoothAdminLogin isValidUserSocial(SignInVO signInVO)
	{
		BoothAdminLogin boothAdminLogin=null;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			//criteria.add(Restrictions.eq("emailId",signInVO.getEmailId()));
			Criterion enc = Restrictions.eq("facebookId",signInVO.getFacebookId());
			Criterion nor = Restrictions.eq("googleId",signInVO.getGoogleId());
			LogicalExpression orExp = Restrictions.or(enc,nor);
			criteria.add(orExp);
			boothAdminLogin=(BoothAdminLogin)criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("LoginDaoImpl Method : isValidUser");
			log.error("Error isValidUser",e);
		}
		return boothAdminLogin;
	}
	
	
	@Override
	public void updateTour(Integer userId) {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId",userId));
			BoothAdminLogin boothAdminLogin=(BoothAdminLogin)criteria.uniqueResult();
			if(boothAdminLogin !=null){
				//boothAdminLogin.setLoginTour(1);
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("LoginDaoImpl Method : updateTour");
			log.error("Error updateTour",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
	}
	@Override
	public void setLastUpdateImages(Integer userId) {
		try {
			   sessionFactory.getCurrentSession().beginTransaction();
			   Criteria criteria= sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			   criteria.add(Restrictions.eq("userId", userId));
			   criteria.add(Restrictions.eq("isValidate", false));
			   criteria.addOrder(Order.desc("imageId"));
			   criteria.setFirstResult(0);
			   criteria.setMaxResults(1);
			   UploadImage eventList = (UploadImage)criteria.uniqueResult(); 
			   if(eventList !=null){
				   eventList.setIsValidate(true);
				   sessionFactory.getCurrentSession().update(eventList);
			   }
			   sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : setLastUpdateImages");
				log.error("Error setLastUpdateImages",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	}
	@Override
	public String forgotPassword(String username,String token) {
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			   Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			   criteria.add(Restrictions.eq("emailId", username));
			   criteria.add(Restrictions.eq("status", true));
			   BoothAdminLogin boothAdminLogin = (BoothAdminLogin)criteria.uniqueResult(); 
			   if(boothAdminLogin !=null){
				   boothAdminLogin.setToken(token);
				   sessionFactory.getCurrentSession().update(boothAdminLogin);
				  // result.setUsername(boothAdminLogin.getEmailId());
				   result="success";
			   }else{
				   result="invalidEmail";
			   }
			   sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : forgotPassword");
				log.error("Error forgotPassword",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
		return result;
	}
	@Override
	public String resetPassword(SignInVO signInVO) {
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("token", signInVO.getToken()));
			criteria.add(Restrictions.eq("status", true));
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin)criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setPassword(Crypto.encrypt(signInVO.getPassword()));
				boothAdminLogin.setToken(UUID.randomUUID().toString());
				sessionFactory.getCurrentSession().update(boothAdminLogin);
				result="success";
			}else if(boothAdminLogin ==null){
				result="fail";
			}
			  sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : resetPassword");
				log.error("Error resetPassword",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
		return result;
	}
	
	@Override
	public String changePassword(SignInVO signInVO) {
		
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId", signInVO.getUserId()));
			criteria.add(Restrictions.eq("password", Crypto.encrypt(signInVO.getPassword())));
			criteria.add(Restrictions.eq("status", true));
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin)criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setPassword(Crypto.encrypt(signInVO.getNewPassword()));
				//boothAdminLogin.setToken(UUID.randomUUID().toString());
				sessionFactory.getCurrentSession().update(boothAdminLogin);
				result="success";
			}else if(boothAdminLogin ==null){
				result="fail";
			}
			  sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : changePassword");
				log.error("Error changePassword",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
		return result;
	}
	
	@Override
	public BoothAdminLogin setFbDetails(String code) {
		BoothAdminLogin boothAdminLogin=null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			FBConnection fbConnection = new FBConnection();
			String accessToken = fbConnection.getAccessToken(code);
			FBGraph fbGraph = new FBGraph(accessToken);
			String graph = fbGraph.getFBGraph();

			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			Criterion c1 = Restrictions.eq("facebookId", fbProfileData.get("id"));
			Criterion c2 = Restrictions.eq("emailId", fbProfileData.get("email"));
			LogicalExpression orExp = Restrictions.or(c1, c2);
			criteria.add(orExp);
			boothAdminLogin=(BoothAdminLogin) criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setImageFileName(fbProfileData.get("picture"));
				boothAdminLogin.setFacebookId(fbProfileData.get("id"));
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}else{
				BoothAdminLogin userMaster2=new BoothAdminLogin();
				
				userMaster2.setCreatedDate(new java.sql.Timestamp(new Date().getTime()));
				userMaster2.setStatus(ServerConstants.MAKE_TRUE);
				userMaster2.setPlanCode(ServerConstants.SUBSCRIPTION_NORMAL);
				userMaster2.setIsDeleted(ServerConstants.MAKE_FALSE);
				userMaster2.setSubUpdatedDate(new java.sql.Timestamp(new Date().getTime()));
				userMaster2.setHexValueDefault("#341561");
				userMaster2.setRgbValueDefault("0,255,0");
				userMaster2.setRgbaValueDefault("0,255,0,255");
				userMaster2.setHexValueManual("#4EDB84");
				userMaster2.setRgbValueManual("0,255,0");
				userMaster2.setRgbaValueManual("255,255,255,255");
				userMaster2.setIsDefaultRgb(ServerConstants.MAKE_TRUE);
				userMaster2.setCurrentImageId(0);
				userMaster2.setUserType("Professional");
				userMaster2.setIsSubscriptionByAdmin(false);
				userMaster2.setPlanCode("1");
				userMaster2.setUsername(fbProfileData.get("name"));
				userMaster2.setFacebookId(fbProfileData.get("id"));
				userMaster2.setEmailId(fbProfileData.get("email"));
				userMaster2.setImageFileName(fbProfileData.get("picture"));
				userMaster2.setUserRole("boothadmin");
				Integer userId=(Integer) sessionFactory.getCurrentSession().save(userMaster2);
			if(userId !=0){
				Fovbyuser fovbyuser=new Fovbyuser();
				fovbyuser.setUserId(userId);
				fovbyuser.setZoomScale("1.00");
				fovbyuser.setFovTop("0");
				fovbyuser.setFovLeft("0");
				fovbyuser.setFovRight("0");
				fovbyuser.setFovBottom("0");
				sessionFactory.getCurrentSession().save(fovbyuser);
			}
			Criteria criteria1=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria1.add(Restrictions.eq("userId",userId));
			boothAdminLogin=(BoothAdminLogin) criteria1.uniqueResult();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}
	
	@Override
	public BoothAdminLogin setGoogleDetails(SignInVO signInVO) {
		BoothAdminLogin boothAdminLogin=null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			Criterion c1 = Restrictions.eq("emailId", signInVO.getEmailId());
			Criterion c2 = Restrictions.eq("googleId", signInVO.getGoogleId());
			LogicalExpression orExp = Restrictions.or(c1, c2);
			criteria.add(orExp);
			boothAdminLogin=(BoothAdminLogin) criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setImageFileName(signInVO.getImageFileName());
				boothAdminLogin.setGoogleId(signInVO.getGoogleId());
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}else{
				BoothAdminLogin userMaster2=new BoothAdminLogin();
				
				userMaster2.setCreatedDate(new java.sql.Timestamp(new Date().getTime()));
				userMaster2.setStatus(ServerConstants.MAKE_TRUE);
				userMaster2.setPlanCode(ServerConstants.SUBSCRIPTION_NORMAL);
				userMaster2.setIsDeleted(ServerConstants.MAKE_FALSE);
				userMaster2.setSubUpdatedDate(new java.sql.Timestamp(new Date().getTime()));
				userMaster2.setHexValueDefault("#341561");
				userMaster2.setRgbValueDefault("0,255,0");
				userMaster2.setRgbaValueDefault("0,255,0,255");
				userMaster2.setHexValueManual("#4EDB84");
				userMaster2.setRgbValueManual("0,255,0");
				userMaster2.setRgbaValueManual("255,255,255,255");
				userMaster2.setIsDefaultRgb(ServerConstants.MAKE_TRUE);
				userMaster2.setCurrentImageId(0);
				userMaster2.setUserType("Professional");
				userMaster2.setIsSubscriptionByAdmin(false);
				userMaster2.setPlanCode("1");
				userMaster2.setUsername(signInVO.getUsername());
				userMaster2.setGoogleId(signInVO.getGoogleId());
				userMaster2.setEmailId(signInVO.getEmailId());
				userMaster2.setImageFileName(signInVO.getImageFileName());
				userMaster2.setUserRole("boothadmin");
				Integer userId=(Integer) sessionFactory.getCurrentSession().save(userMaster2);
			if(userId !=0){
				Fovbyuser fovbyuser=new Fovbyuser();
				fovbyuser.setUserId(userId);
				fovbyuser.setZoomScale("1.00");
				fovbyuser.setFovTop("0");
				fovbyuser.setFovLeft("0");
				fovbyuser.setFovRight("0");
				fovbyuser.setFovBottom("0");
				sessionFactory.getCurrentSession().save(fovbyuser);
			}
			Criteria criteria1=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria1.add(Restrictions.eq("userId",userId));
			boothAdminLogin=(BoothAdminLogin) criteria1.uniqueResult();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}
	
	
}
