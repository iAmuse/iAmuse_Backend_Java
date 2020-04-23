package com.iamuse.admin.daoimpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.DeviceVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.OptionsReports;
import com.iamuse.admin.VO.PaginationVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.VO.TransactionHistoryVO;
import com.iamuse.admin.VO.TransactionReceiptVO;
import com.iamuse.admin.dao.BoothAdminDao;
import com.iamuse.admin.entity.AccessToken;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.AdminEventPictureMapping;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BackgroundImages;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.BoothPictureCropper;
import com.iamuse.admin.entity.BoothUploadImageEmail;
import com.iamuse.admin.entity.DefaultAdminBoothEventPicture;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.StatusCount;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.entity.TokenClsss;
import com.iamuse.admin.entity.TransactionMappingAdmin;
import com.iamuse.admin.entity.TransactionMaster;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.util.FileMoveUtility;
import com.iamuse.admin.util.IAmuseadminUtil;
import com.iamuse.admin.util.MailUtil;
import com.iamuse.admin.util.Crypto;
import com.paypal.constants.ServerConstants;

@Repository
public class BoothAdminDaoImpl implements BoothAdminDao {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	MailUtil mailUtil;
	@Autowired
	MessageSource messageSource;
	long loopConstant;

	private static final Logger log = Logger.getLogger(BoothAdminDao.class);

	public int getUserId(SignInVO signInVO) {

		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);

		Criterion c1 = Restrictions.eq(ServerConstants.EMAIL_ID, signInVO.getEmailId());
		criteria.add(c1);
		BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
//	 sessionFactory.getCurrentSession().getTransaction().commit();

		return boothAdminLogin.getUserId();
	}

	@Override
	public SignInVO createBoothAdmin(SignInVO signInVO) {
		SignInVO signInVO2 = new SignInVO();
		Boolean result = ServerConstants.MAKE_TRUE;
		Integer userId = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			Criterion c1 = Restrictions.eq(ServerConstants.USER_ID, signInVO.getUserId());
			Criterion c2 = Restrictions.eq(ServerConstants.EMAIL_ID, signInVO.getEmailId());
			LogicalExpression orExp = Restrictions.or(c1, c2);
			criteria.add(orExp);
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			if (boothAdminLogin != null) {
				if (("update").equals(signInVO.getResult())) {
					boothAdminLogin.setUsername(signInVO.getUsername());
					boothAdminLogin.setUsername(Crypto.encrypt(signInVO.getPassword()));
					sessionFactory.getCurrentSession().update(boothAdminLogin);
					signInVO2.setResult("update");
					signInVO2.setIsDeleted(false);
										
				} else {
					userId = getUserId(signInVO);
					signInVO2.setIsDeleted(false);
					if (boothAdminLogin.getIsDeleted()) {
						boothAdminLogin.setIsDeleted(signInVO.getIsDeleted());
						// boothAdminLogin.setIsDeleted(false);
						signInVO2.setIsDeleted(boothAdminLogin.getIsDeleted());
						boothAdminLogin.setIsDeleted(ServerConstants.MAKE_FALSE);
						sessionFactory.getCurrentSession().saveOrUpdate(boothAdminLogin);
						signInVO2.setIsDeleted(true);						
					}
					
					signInVO2.setResult("exist");
				}
			} else {
				if (signInVO.getEmailId() == null && signInVO.getEmailId() == "") {
					return new SignInVO();
				} else {
					BoothAdminLogin boothAdminLogin1 = new BoothAdminLogin();
					boothAdminLogin1.setEmailId(signInVO.getEmailId());
					boothAdminLogin1.setPassword(Crypto.encrypt(signInVO.getPassword()));
					// boothAdminLogin1.setPassword(signInVO.getPassword());
					boothAdminLogin1.setCreatedDate(com.iamuse.admin.util.DateUtils.getUTC());
					System.out.println("boothAdminLogin1.setCreatedDate========"+boothAdminLogin1.getCreatedDate());
					boothAdminLogin1.setStatus(ServerConstants.MAKE_TRUE);
					boothAdminLogin1.setUserRole("boothadmin");
					boothAdminLogin1.setUsername(signInVO.getUsername());
					boothAdminLogin1.setLastname(signInVO.getLastname());
					boothAdminLogin1.setSubId(ServerConstants.SUBSCRIPTION_NORMAL);
					boothAdminLogin1.setIsDeleted(ServerConstants.MAKE_FALSE);
					boothAdminLogin1.setSubUpdatedDate(new Date());
					boothAdminLogin1.setHexValueDefault("#341561");
					boothAdminLogin1.setRgbValueDefault("0,255,0");
					boothAdminLogin1.setRgbaValueDefault("0,255,0,255");
					boothAdminLogin1.setHexValueManual("#4EDB84");
					boothAdminLogin1.setRgbValueManual("0,255,0");
					boothAdminLogin1.setRgbaValueManual("255,255,255,255");
					boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_TRUE);
					boothAdminLogin1.setCurrentImageId(0);
					boothAdminLogin1.setUserType(signInVO.getUserType());
					boothAdminLogin1.setContactNumber(signInVO.getContactNumber());
					boothAdminLogin1.setLoginTour(0);
					// Added Newly for resolving error
					boothAdminLogin1.setFacebookUrl("Facebook");
					boothAdminLogin1.setTwitterUrl("Twitter");
					boothAdminLogin1.setIs_annual(false);
					// boothAdminLogin1.set("Facebook");
					if(signInVO.getPin().equals(""))
					{
						boothAdminLogin1.setPin("0000");
					}
					else {
                    boothAdminLogin1.setPin(signInVO.getPin());
					}
					/*if (boothAdminLogin1.getSubId() == 1) {
						Date newDate = DateUtils.addDays(new Date(), 14);
						boothAdminLogin1.setSubEndDate(newDate);
					}*/
					if (boothAdminLogin1.getSubId() == 4) {
						Date newDate = DateUtils.addHours(new Date(), 720);
						boothAdminLogin1.setSubEndDate(newDate);
					}
					userId = (Integer) sessionFactory.getCurrentSession().save(boothAdminLogin1);
					if (userId != 0) {
						Fovbyuser fovbyuser = new Fovbyuser();
						fovbyuser.setUserId(userId);
						fovbyuser.setZoomScale("0.75");
						fovbyuser.setFovTop("0");
						fovbyuser.setFovLeft("0");
						fovbyuser.setFovRight("0");
						fovbyuser.setFovBottom("0");
						sessionFactory.getCurrentSession().save(fovbyuser);
					}
					signInVO2.setResult(ServerConstants.SUCCESS);
				}
			}
			signInVO2.setUserId(userId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : createBoothAdmin");
			log.error("Error createBoothAdmin", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return signInVO2;
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionList() {
		List<SubscriptionMaster> subscriptionMasters = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			subscriptionMasters = criteria.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscriptionList");
			log.error("Error getSubscriptionList", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionMasters;
	}

	@Override
	public List<DeviceRegistration> getRegisteredDevice(Integer userId) {
		List<DeviceRegistration> deviceRegistration = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			// criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,
			// ServerConstants.MAKE_FALSE));
			deviceRegistration = criteria.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getRegisteredDevice");
			log.error("Error getRegisteredDevice", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return deviceRegistration;
	}

	@Override
	public List<EventVO> getEventList(Integer userId, int pageid, int total, Integer subId) {
		List<EventVO> eventVOs = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			//if (subId != 1) {
			Criterion c1=(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			//} else {
			Criterion c2=(Restrictions.eq("eventType", "default"));
			//}
				LogicalExpression orExp = Restrictions.or(c1, c2);
				criteria.add(orExp);
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.setFirstResult((pageid - 1) * total);
			criteria.setMaxResults(total);
			criteria.addOrder(Order.desc("EId"));
			List<Adminboothevent> eventList = (List<Adminboothevent>) criteria.list();
			if (eventList != null) {
				for (Adminboothevent event : eventList) {
					EventVO eventVO = new EventVO();
					Criteria criteriaAdmin = sessionFactory.getCurrentSession()
							.createCriteria(BoothUploadImageEmail.class);
					criteriaAdmin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
					criteriaAdmin.add(Restrictions.eq(ServerConstants.EVENT_ID, event.getEId()));
					criteriaAdmin.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
					criteriaAdmin.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
					ProjectionList projList = Projections.projectionList();
					projList.add(Projections.countDistinct("guestSessions"));
					criteriaAdmin.setProjection(projList);
					List adminBoothEventPicture = criteriaAdmin.list();

					eventVO.setTotalGuestSession(Integer.parseInt(adminBoothEventPicture.get(0).toString()));
					eventVO.setEventHostEmail(event.getEventHostMailerId());
					eventVO.setEId(event.getEId());
					eventVO.setEventStart("" + event.getEventStart());
					eventVO.setEventName(event.getEventName());
					eventVO.setSponsorName(event.getSponsorName());
					eventVO.setZoomScale(event.getZoomScale());
					eventVOs.add(eventVO);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventList");
			log.error("Error getEventList", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVOs;
	}

	@Override
	public EventVO saveCreateEvent(EventVO eventVO, Integer userId) {
		EventVO eventVO2 = new EventVO();
		SimpleDateFormat formatter = new SimpleDateFormat(ServerConstants.DATE_FORMAT);
		String dateInString = eventVO.getEventStart();
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.getMessage();
		}
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Adminboothevent event1 = new Adminboothevent();
			event1.setCreatedBy(userId);
			event1.setEventStart(date);
			event1.setCreatedDate(new Date());
			event1.setEventName(eventVO.getEventName());
			event1.setSponsorName(eventVO.getSponsorName());
			event1.setIsDeleted(ServerConstants.MAKE_FALSE);
			event1.setStatus(ServerConstants.MAKE_TRUE);
			Integer eId = (Integer) sessionFactory.getCurrentSession().save(event1);
			eventVO2.setResult(ServerConstants.SUCCESS);
			eventVO2.setEId(eId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : saveCreateEvent");
			log.error("Error saveCreateEvent", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVO2;
	}

	@Override
	public Adminboothevent getCurrentEvent(Integer userId, EventVO eventVO) {
		SimpleDateFormat formatter = new SimpleDateFormat(ServerConstants.DATE_FORMAT);
		Adminboothevent event = null;
		Date date = null;
		try {
			String dateInString = eventVO.getEventStart();
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.getMessage();
		}
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Adminboothevent event1 = new Adminboothevent();
			event1.setCreatedBy(userId);
			event1.setEventStart(date);
			event1.setCreatedDate(new Date());
			event1.setEventName(eventVO.getEventName());
			event1.setSponsorName(eventVO.getSponsorName());
			event1.setIsDeleted(ServerConstants.MAKE_FALSE);
			event1.setStatus(ServerConstants.MAKE_TRUE);
			Integer eid = (Integer) sessionFactory.getCurrentSession().save(event1);
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eid));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			event = (Adminboothevent) criteria.uniqueResult();
			if (event != null) {
				return event;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getCurrentEvent");
			log.error("Error getCurrentEvent", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return event;
	}

	@Override
	public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO, MultipartFile[] files, String rootPath,
			MultipartFile thankyoufiles, MultipartFile lookAtTouchScreen, MultipartFile cameraTVScreenSaver,
			MultipartFile waterMarkImage, String default4Images) {
		String destinationFilePath;
		AdminPictureVO adminPictureVO2 = new AdminPictureVO();
		SimpleDateFormat formatter = new SimpleDateFormat(ServerConstants.DATE_FORMAT);
		String dateInString = adminPictureVO.getEventStart();
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.getMessage();
		}
		Integer eid = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, adminPictureVO.getCreatedBy()));
			criteria.addOrder(Order.desc("fovId"));
			criteria.setFirstResult(0);
			criteria.setMaxResults(1);
			Fovbyuser fovbyuser = (Fovbyuser) criteria.uniqueResult();
			Adminboothevent event1 = new Adminboothevent();
			System.out.println("Event 1"+event1);
			String[] preBackground;
			List<AdminBoothEventPicture> copyPicture = new ArrayList<>();
			if (adminPictureVO.getSelectedPreImage().trim() != null && adminPictureVO.getSelectedPreImage().trim() != ""
					&& !adminPictureVO.getSelectedPreImage().isEmpty()
					&& !adminPictureVO.getSelectedPreImage().equalsIgnoreCase("0")) {
				preBackground = adminPictureVO.getSelectedPreImage().split(",");
				for (String picId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
					crt.add(Restrictions.eq(ServerConstants.PIC_ID, Integer.parseInt(picId)));
					AdminBoothEventPicture picture = (AdminBoothEventPicture) crt.uniqueResult();
					copyPicture.add(picture);
				}
			}
			if (fovbyuser != null) {
				event1.setFovBottom(fovbyuser.getFovBottom());
				event1.setFovLeft(fovbyuser.getFovLeft());
				event1.setFovRight(fovbyuser.getFovRight());
				event1.setFovTop(fovbyuser.getFovTop());
				event1.setGreenScreenCountdownDelay(fovbyuser.getGreenScreenCountDownDelay());
				event1.setGreenScreenDistance(fovbyuser.getGreenScreenDistance());
				event1.setGreenScreenHeight(fovbyuser.getGreenScreenHeight());
				event1.setGreenScreenWidth(fovbyuser.getGreenScreenWidth());
				event1.setOtherCountdownDelay(fovbyuser.getOthrtCountDelay());
				event1.setOtherIntractionTimout(fovbyuser.getOtherInstructionTimeout());
				event1.setZoomScale(fovbyuser.getZoomScale());
			}
			event1.setCreatedBy(adminPictureVO.getCreatedBy());
			event1.setEventStart(date);
			event1.setCreatedDate(new Date());
			event1.setEventName(adminPictureVO.getEventName());
			event1.setSponsorName(adminPictureVO.getSponsorName());
			event1.setIsDeleted(ServerConstants.MAKE_FALSE);
			event1.setEventTimezone(adminPictureVO.getEventTimezone());
			event1.setStatus(ServerConstants.MAKE_TRUE);
			if (!adminPictureVO.getEventType().equals("default")) {
				if (adminPictureVO.getIsSubscribed().equals("yes")) {
					event1.setIsSubscribed(true);
				} else if (adminPictureVO.getIsSubscribed().equals("no")) {
					event1.setIsSubscribed(false);
				}
			} else {
				event1.setIsSubscribed(true);
				event1.setEventTimezone("(GMT 0:00)");
			}
			if (!adminPictureVO.getEventType().equals("default")) {
				if (adminPictureVO.getIsName().equals("yes")) {
					event1.setIsName(true);
				} else if (adminPictureVO.getIsName().equals("no")) {
					event1.setIsName(false);
				}
			} else {
				event1.setIsName(true);
				event1.setEventTimezone("(GMT 0:00)");
			}
			if (!adminPictureVO.getEventType().equals("default")) {
				if (adminPictureVO.getIsPhone().equals("yes")) {
					event1.setIsPhone(true);
				} else if (adminPictureVO.getIsPhone().equals("no")) {
					event1.setIsPhone(false);
				}
			} else {
				event1.setIsPhone(true);
				event1.setEventTimezone("(GMT 0:00)");
			}
			if (adminPictureVO.getEventType() != null) {
				event1.setEventType(adminPictureVO.getEventType());
			}
			if (adminPictureVO.getEventHostMailerId() != null) {
				event1.setEventHostMailerId(adminPictureVO.getEventHostMailerId());
			}
			if (adminPictureVO.getFacebook() != null) {
				event1.setFacebook(adminPictureVO.getFacebook());
			}
			if (adminPictureVO.getTwitter() != null) {
				event1.setTwitter(adminPictureVO.getTwitter());
			}
			eid = (Integer) sessionFactory.getCurrentSession().save(event1);
			Adminboothevent eventLoad = (Adminboothevent) sessionFactory.getCurrentSession().load(Adminboothevent.class,
					eid);
			if (adminPictureVO.getEmailBody() == " ") {
				String emailBody = "Thank you for coming to our Event! We hope you had fun, attached is your picture.";
				eventLoad.setEmailBody(emailBody);
			} else {
				eventLoad.setEmailBody(adminPictureVO.getEmailBody());
			}

			if (thankyoufiles != null && thankyoufiles.getSize() > 0) {
				String thankyoufilesURL = IAmuseadminUtil.writeFile(thankyoufiles.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(thankyoufiles));
				eventLoad.setThankYouScreen(thankyoufilesURL);
				eventLoad.setPreSetThankYouScreen(ServerConstants.MAKE_FALSE);
			} else {
				Path path = Paths.get(default4Images + "/resources/img/4.png");
				byte[] data = Files.readAllBytes(path);
				String thankyoufilesURL = IAmuseadminUtil.writeFile(data, rootPath, adminPictureVO.getCreatedBy(), eid,
						"thank_you.png");
				eventLoad.setThankYouScreen(thankyoufilesURL);
				eventLoad.setPreSetThankYouScreen(ServerConstants.MAKE_TRUE);
			}
			if (lookAtTouchScreen != null && lookAtTouchScreen.getSize() > 0) {
				String lookAtTouchScreenURL = IAmuseadminUtil.writeFile(lookAtTouchScreen.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(lookAtTouchScreen));
				eventLoad.setLookAtTouchScreen(lookAtTouchScreenURL);
				eventLoad.setPreLookAtTouchScreen(ServerConstants.MAKE_FALSE);
			} else {
				Path path = Paths.get(default4Images + "/resources/img/3.png");
				byte[] data = Files.readAllBytes(path);
				String lookAtTouchScreenURL = IAmuseadminUtil.writeFile(data, rootPath, adminPictureVO.getCreatedBy(),
						eid, "photo_complete.png");
				eventLoad.setLookAtTouchScreen(lookAtTouchScreenURL);
				eventLoad.setPreLookAtTouchScreen(ServerConstants.MAKE_FALSE);
			}
			if (cameraTVScreenSaver != null && cameraTVScreenSaver.getSize() > 0) {
				String cameraTVScreenSaverURL = IAmuseadminUtil.writeFile(cameraTVScreenSaver.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(cameraTVScreenSaver));
				eventLoad.setCameraTVScreenSaver(cameraTVScreenSaverURL);
				eventLoad.setPreCameraTVScreenSaver(ServerConstants.MAKE_FALSE);
			} else {
				Path path = Paths.get(default4Images + "/resources/img/2.png");
				byte[] data = Files.readAllBytes(path);
				String thankyoufilesURL = IAmuseadminUtil.writeFile(data, rootPath, adminPictureVO.getCreatedBy(), eid,
						"screen_saver.png");
				eventLoad.setCameraTVScreenSaver(thankyoufilesURL);
				eventLoad.setPreCameraTVScreenSaver(ServerConstants.MAKE_FALSE);
			}
			if (waterMarkImage != null && waterMarkImage.getSize() > 0) {
				String waterMarkImageURL = IAmuseadminUtil.writeFile(waterMarkImage.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(waterMarkImage));
				eventLoad.setWaterMarkImage(waterMarkImageURL);
				eventLoad.setPreWaterMarkImage(ServerConstants.MAKE_FALSE);
			} else {
				Path path = Paths.get(default4Images + "/resources/img/1.png");
				byte[] data = Files.readAllBytes(path);
				String waterMarkImageURL = IAmuseadminUtil.writeFile(data, rootPath, adminPictureVO.getCreatedBy(), eid,
						"water_mark.png");
				eventLoad.setWaterMarkImage(waterMarkImageURL);
				eventLoad.setPreWaterMarkImage(ServerConstants.MAKE_FALSE);
			}
			List<String> waterMarkPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreWaterMarkImage())) {
				preBackground = adminPictureVO.getSelectedPreWaterMarkImage().split(",");
				for (String eventId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					crt.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent waterMarkImageURL = (Adminboothevent) crt.uniqueResult();
					waterMarkPicture.add(waterMarkImageURL.getWaterMarkImage());
				}
				if (!waterMarkPicture.isEmpty()) {
					for (String b : waterMarkPicture) {
						if (b != null && b != "") {
							destinationFilePath = b;
							eventLoad.setWaterMarkImage(destinationFilePath);
							eventLoad.setPreWaterMarkImage(ServerConstants.MAKE_TRUE);
						}
					}
				}
			}
			List<String> cameraTVScreenSaverPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreCameraTVScreenSaver())) {
				preBackground = adminPictureVO.getSelectedPreCameraTVScreenSaver().split(",");
				for (String eventId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					crt.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent cameraTVScreenSaverURL = (Adminboothevent) crt.uniqueResult();
					cameraTVScreenSaverPicture.add(cameraTVScreenSaverURL.getCameraTVScreenSaver());
				}
				if (!cameraTVScreenSaverPicture.isEmpty()) {
					for (String b : cameraTVScreenSaverPicture) {
						if (b != null && b != "") {
							destinationFilePath = b;
							eventLoad.setCameraTVScreenSaver(destinationFilePath);
							eventLoad.setPreCameraTVScreenSaver(ServerConstants.MAKE_TRUE);

						}
					}
				}
			}

			List<String> lookAtTouchScreenPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreLookAtTouchScreen())) {
				preBackground = adminPictureVO.getSelectedPreLookAtTouchScreen().split(",");
				for (String eventId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					crt.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent lookTouchScrrenURL = (Adminboothevent) crt.uniqueResult();
					lookAtTouchScreenPicture.add(lookTouchScrrenURL.getLookAtTouchScreen());
				}
				if (!lookAtTouchScreenPicture.isEmpty()) {
					for (String b : lookAtTouchScreenPicture) {
						if (b != null && b != "") {
							destinationFilePath = b;
							eventLoad.setLookAtTouchScreen(destinationFilePath);
							eventLoad.setPreLookAtTouchScreen(ServerConstants.MAKE_TRUE);
						}
					}

				}
			}
			List<String> thankYouScreenPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreThankYouScreen())) {
				preBackground = adminPictureVO.getSelectedPreThankYouScreen().split(",");
				for (String eventId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					crt.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent thankYouScrenURL = (Adminboothevent) crt.uniqueResult();
					thankYouScreenPicture.add(thankYouScrenURL.getThankYouScreen());
				}
				if (!thankYouScreenPicture.isEmpty()) {
					for (String b : thankYouScreenPicture) {
						if (b != null && b != "") {
							destinationFilePath = b;
							eventLoad.setThankYouScreen(destinationFilePath);
							eventLoad.setPreSetThankYouScreen(ServerConstants.MAKE_TRUE);
						}
					}
				}
			}
			sessionFactory.getCurrentSession().update(eventLoad);
			if (files != null) {
				for (MultipartFile file : files) {
					byte[] bytes = file.getBytes();
					String fileName = file.getOriginalFilename();
					if (fileName != "") {
						fileName = IAmuseadminUtil.changeUploadFileName(file);
						String fileUrl1 = IAmuseadminUtil.writeFile(bytes, rootPath, adminPictureVO.getCreatedBy(), eid,
								fileName);
						AdminBoothEventPicture adminPicture = new AdminBoothEventPicture();
						adminPicture.setCreatedBy(adminPictureVO.getCreatedBy());
						adminPicture.setCreatedDate(new Date());
						adminPicture.setPicName(fileUrl1);
						adminPicture.setEId(eid);
						adminPicture.setIsDeleted(ServerConstants.MAKE_FALSE);
						adminPicture.setStatus(ServerConstants.MAKE_TRUE);
						if (fovbyuser != null) {
							adminPicture.setScaleZOffset(fovbyuser.getZoomScale());
						}
						adminPicture.setPreSetBackground(ServerConstants.MAKE_FALSE);
						Integer adminPicId = (Integer) sessionFactory.getCurrentSession().save(adminPicture);
						if (adminPicId != null) {
							AdminEventPictureMapping adminEventPictureMapping = new AdminEventPictureMapping();
							adminEventPictureMapping.setPicId(adminPicId);
							adminEventPictureMapping.setEId(eid);
							adminEventPictureMapping.setUserId(adminPictureVO.getCreatedBy());
							adminEventPictureMapping.setCreatedDate(new Date());
							adminEventPictureMapping.setIsDeleted(ServerConstants.MAKE_FALSE);
							adminEventPictureMapping.setStatus(ServerConstants.MAKE_TRUE);
							if ((ServerConstants.DEFAULTS).equalsIgnoreCase(adminPictureVO.getEventType())) {
								adminEventPictureMapping.setEventType(adminPictureVO.getEventType());
							}
							sessionFactory.getCurrentSession().save(adminEventPictureMapping);
						}
					}
				}
			}
			if (!copyPicture.isEmpty()) {
				for (AdminBoothEventPicture b : copyPicture) {
					AdminBoothEventPicture adminPicture = new AdminBoothEventPicture();
					adminPicture.setCreatedBy(adminPictureVO.getCreatedBy());
					adminPicture.setCreatedDate(new Date());
					if (b.getPicName() != "" && b.getPicName() != null) {
						destinationFilePath = b.getPicName();
						adminPicture.setPicName(destinationFilePath);
					}
					adminPicture.setEId(eid);
					adminPicture.setIsDeleted(ServerConstants.MAKE_FALSE);
					adminPicture.setStatus(ServerConstants.MAKE_TRUE);
					adminPicture.setPreSetBackground(ServerConstants.MAKE_TRUE);
					if (fovbyuser != null) {
						adminPicture.setScaleZOffset(fovbyuser.getZoomScale());
					}
					adminPicture.setImageMask(b.getImageMask());
					adminPicture.setImageHeight(b.getImageHeight());
					adminPicture.setImageWidth(b.getImageWidth());
					adminPicture.setPicTitle(b.getPicTitle());
					adminPicture.setRgbValues(b.getRgbValues());
					adminPicture.setScaleXOffset(b.getScaleXOffset());
					adminPicture.setScaleYOffset(b.getScaleYOffset());
					adminPicture.setScaleZOffset(b.getScaleZOffset());
					adminPicture.setScalingHeight(b.getScalingHeight());
					adminPicture.setScalingWidth(b.getScalingWidth());
					Integer adminPicIds = (Integer) sessionFactory.getCurrentSession().save(adminPicture);
					if (adminPicIds != null) {
						AdminEventPictureMapping adminEventPictureMappings = new AdminEventPictureMapping();
						adminEventPictureMappings.setPicId(adminPicIds);
						adminEventPictureMappings.setEId(eid);
						adminEventPictureMappings.setUserId(adminPictureVO.getCreatedBy());
						adminEventPictureMappings.setCreatedDate(new Date());
						adminEventPictureMappings.setIsDeleted(ServerConstants.MAKE_FALSE);
						adminEventPictureMappings.setStatus(ServerConstants.MAKE_TRUE);
						if ((ServerConstants.DEFAULTS).equalsIgnoreCase(adminPictureVO.getEventType())) {
							adminEventPictureMappings.setEventType(adminPictureVO.getEventType());
						}
						sessionFactory.getCurrentSession().save(adminEventPictureMappings);
					}
				}
			}
			adminPictureVO2.setResult(ServerConstants.SUCCESS);
			adminPictureVO2.setEId(eid);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : uploadBackgroundImage");
			log.error("Error uploadBackgroundImage", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVO2;
	}

	@Override
	public List<AdminPictureVO> getPicList(Integer eId, Integer userId) {
		List<AdminPictureVO> adminPictureVOs = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			if (boothAdminLogin.getSubId() == 1) {
				Criteria criteriaAdminEventPictureMapping = sessionFactory.getCurrentSession()
						.createCriteria(AdminEventPictureMapping.class);
				criteriaAdminEventPictureMapping.add(Restrictions.eq("EId", eId));
				criteriaAdminEventPictureMapping
						.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaAdminEventPictureMapping
						.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				List<AdminEventPictureMapping> adminEventPictureMapping = (List<AdminEventPictureMapping>) criteriaAdminEventPictureMapping
						.list();
				if (!adminEventPictureMapping.isEmpty()) {
					for (AdminEventPictureMapping adminEventPictureMappings : adminEventPictureMapping) {
						Criteria criteriaAdminPicture = sessionFactory.getCurrentSession()
								.createCriteria(AdminBoothEventPicture.class);
						criteriaAdminPicture
								.add(Restrictions.eq(ServerConstants.PIC_ID, adminEventPictureMappings.getPicId()));
						criteriaAdminPicture
								.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
						AdminBoothEventPicture adminPicture = (AdminBoothEventPicture) criteriaAdminPicture
								.uniqueResult();
						if (adminPicture != null) {
							AdminPictureVO adminPictureVO = new AdminPictureVO();
							adminPictureVO.setPicId(adminPicture.getPicId());
							adminPictureVO.setPicName(adminPicture.getPicName());
							adminPictureVO.setImageHeight(adminPicture.getImageHeight());
							adminPictureVO.setPicTitle(adminPicture.getPicTitle());
							adminPictureVO.setUpdatedBy(adminPicture.getUpdatedBy());
							adminPictureVOs.add(adminPictureVO);
						}
					}
				}
			} else {
				Criteria criteriaAdminEventPictureMapping = sessionFactory.getCurrentSession()
						.createCriteria(AdminEventPictureMapping.class);
				criteriaAdminEventPictureMapping.add(Restrictions.eq("EId", eId));
				criteriaAdminEventPictureMapping.add(Restrictions.eq(ServerConstants.USER_ID, userId));
				criteriaAdminEventPictureMapping
						.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaAdminEventPictureMapping
						.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				List<AdminEventPictureMapping> adminEventPictureMapping = (List<AdminEventPictureMapping>) criteriaAdminEventPictureMapping
						.list();
				if (!adminEventPictureMapping.isEmpty()) {
					for (AdminEventPictureMapping adminEventPictureMappings : adminEventPictureMapping) {
						Criteria criteriaAdminPicture = sessionFactory.getCurrentSession()
								.createCriteria(AdminBoothEventPicture.class);
						criteriaAdminPicture
								.add(Restrictions.eq(ServerConstants.PIC_ID, adminEventPictureMappings.getPicId()));
						criteriaAdminPicture
								.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
						AdminBoothEventPicture adminPicture = (AdminBoothEventPicture) criteriaAdminPicture
								.uniqueResult();
						if (adminPicture != null) {
							AdminPictureVO adminPictureVO = new AdminPictureVO();
							adminPictureVO.setPicId(adminPicture.getPicId());
							adminPictureVO.setPicName(adminPicture.getPicName());
							adminPictureVO.setImageHeight(adminPicture.getImageHeight());
							adminPictureVO.setPicTitle(adminPicture.getPicTitle());
							adminPictureVO.setUpdatedBy(adminPicture.getUpdatedBy());
							adminPictureVOs.add(adminPictureVO);
						}
					}
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPicList");
			log.error("Error getPicList", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVOs;
	}

	@Override
	public AdminPictureVO getImageConfigure(Integer picId, Integer userId, Integer subId) {
		AdminPictureVO adminPictureVO = new AdminPictureVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			/* if(subId==1){ */
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DefaultAdminBoothEventPicture.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteria.add(Restrictions.eq(ServerConstants.PIC_ID, picId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			DefaultAdminBoothEventPicture adminPicture = (DefaultAdminBoothEventPicture) criteria.uniqueResult();
			if (adminPicture != null) {
				adminPictureVO.setPicId(adminPicture.getPicId());
				adminPictureVO.setPicName(adminPicture.getPicName());
				adminPictureVO.setPicTitle(adminPicture.getPicTitle());
				adminPictureVO.setScaleXOffset(adminPicture.getScaleXOffset());
				adminPictureVO.setScaleYOffset(adminPicture.getScaleYOffset());
				adminPictureVO.setScaleZOffset(adminPicture.getScaleZOffset());
				adminPictureVO.setScalingWidth(adminPicture.getScalingWidth());
				adminPictureVO.setScalingHeight(adminPicture.getScalingHeight());
				adminPictureVO.setEId(adminPicture.getEId());
				Criteria criteriaBoothPictureCropper = sessionFactory.getCurrentSession()
						.createCriteria(BoothPictureCropper.class);
				criteriaBoothPictureCropper
						.add(Restrictions.eq("defaultAdminBoothEventPictureId", adminPicture.getDefaultId()));
				BoothPictureCropper boothPictureCropper = (BoothPictureCropper) criteriaBoothPictureCropper
						.uniqueResult();
				if (boothPictureCropper != null) {
					adminPictureVO.setCropImgX(boothPictureCropper.getImgX());
					adminPictureVO.setCropImgY(boothPictureCropper.getImgY());
					adminPictureVO.setCropImgWidth(boothPictureCropper.getImgWidth());
					adminPictureVO.setCropImgHeight(boothPictureCropper.getImgHeight());
					adminPictureVO.setGridShift(boothPictureCropper.getGridShift());
				} else {
					adminPictureVO.setCropImgX("0");
					adminPictureVO.setCropImgY("0");
					adminPictureVO.setCropImgWidth(adminPicture.getImageWidth());
					adminPictureVO.setCropImgHeight(adminPicture.getImageHeight());
				}
			} else {
				Criteria criteriaAdminBoothEventPicture = sessionFactory.getCurrentSession()
						.createCriteria(AdminBoothEventPicture.class);
				criteriaAdminBoothEventPicture.add(Restrictions.eq(ServerConstants.PIC_ID, picId));
				criteriaAdminBoothEventPicture.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaAdminBoothEventPicture
						.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				AdminBoothEventPicture adminPicture1 = (AdminBoothEventPicture) criteriaAdminBoothEventPicture
						.uniqueResult();
				if (adminPicture1 != null) {
					adminPictureVO.setPicId(adminPicture1.getPicId());
					adminPictureVO.setPicName(adminPicture1.getPicName());
					adminPictureVO.setPicTitle(adminPicture1.getPicTitle());
					adminPictureVO.setScaleXOffset(adminPicture1.getScaleXOffset());
					adminPictureVO.setScaleYOffset(adminPicture1.getScaleYOffset());
					adminPictureVO.setScaleZOffset(adminPicture1.getScaleZOffset());
					adminPictureVO.setScalingWidth(adminPicture1.getScalingWidth());
					adminPictureVO.setScalingHeight(adminPicture1.getScalingHeight());
					adminPictureVO.setEId(adminPicture1.getEId());
					adminPictureVO.setImageMask(adminPicture1.getImageMask());
					adminPictureVO.setImageWaterMark(adminPicture1.getWaterMarkImage());
					Criteria criteriaBoothPictureCropper = sessionFactory.getCurrentSession()
							.createCriteria(BoothPictureCropper.class);
					criteriaBoothPictureCropper.add(
							Restrictions.eq(ServerConstants.ADMIN_BOOTH_EVENT_PICTURE_ID, adminPicture1.getPicId()));
					BoothPictureCropper boothPictureCropper = (BoothPictureCropper) criteriaBoothPictureCropper
							.uniqueResult();
					if (boothPictureCropper != null) {
						adminPictureVO.setCropImgX(boothPictureCropper.getImgX());
						adminPictureVO.setCropImgY(boothPictureCropper.getImgY());
						adminPictureVO.setCropImgWidth(boothPictureCropper.getImgWidth());
						adminPictureVO.setCropImgHeight(boothPictureCropper.getImgHeight());
						adminPictureVO.setGridShift(boothPictureCropper.getGridShift());
					}
					 else if (adminPicture1.getScaleXOffset() != null)
			          {
			            adminPictureVO.setCropImgX(adminPicture1.getScaleXOffset());
			            adminPictureVO.setCropImgY(adminPicture1.getScaleYOffset());
			            adminPictureVO.setGridShift("-1");
			            adminPictureVO.setCropImgWidth(boothPictureCropper.getImgWidth());
			            adminPictureVO.setCropImgHeight(boothPictureCropper.getImgHeight());
			            
			          }
					 else {
						adminPictureVO.setCropImgX("0");
						adminPictureVO.setCropImgY("0");
						adminPictureVO.setCropImgWidth(adminPicture1.getImageWidth());
						adminPictureVO.setCropImgHeight(adminPicture1.getImageHeight());
					}
				}
			}
			/*
			 * }else{ Criteria criteria=
			 * sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.
			 * class); criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			 * criteria.add(Restrictions.eq(ServerConstants.PIC_ID, picId));
			 * criteria.add(Restrictions.eq(ServerConstants.STATUS,
			 * ServerConstants.MAKE_TRUE));
			 * criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,
			 * ServerConstants.MAKE_FALSE)); AdminBoothEventPicture adminPicture
			 * =(AdminBoothEventPicture) criteria.uniqueResult(); if(adminPicture !=null){
			 * adminPictureVO.setPicId(adminPicture.getPicId());
			 * adminPictureVO.setPicName(adminPicture.getPicName());
			 * adminPictureVO.setPicTitle(adminPicture.getPicTitle());
			 * adminPictureVO.setScaleXOffset(adminPicture.getScaleXOffset());
			 * adminPictureVO.setScaleYOffset(adminPicture.getScaleYOffset());
			 * adminPictureVO.setScaleZOffset(adminPicture.getScaleZOffset());
			 * adminPictureVO.setScalingWidth(adminPicture.getScalingWidth());
			 * adminPictureVO.setScalingHeight(adminPicture.getScalingHeight());
			 * adminPictureVO.setEId(adminPicture.getEId());
			 * adminPictureVO.setImageMask(adminPicture.getImageMask());
			 * adminPictureVO.setImageWaterMark(adminPicture.getWaterMarkImage()); Criteria
			 * criteriaBoothPictureCropper=
			 * sessionFactory.getCurrentSession().createCriteria(BoothPictureCropper.class);
			 * criteriaBoothPictureCropper.add(Restrictions.eq(ServerConstants.
			 * ADMIN_BOOTH_EVENT_PICTURE_ID, adminPicture.getPicId())); BoothPictureCropper
			 * boothPictureCropper =(BoothPictureCropper)
			 * criteriaBoothPictureCropper.uniqueResult(); if(boothPictureCropper !=null){
			 * adminPictureVO.setCropImgX(boothPictureCropper.getImgX());
			 * adminPictureVO.setCropImgY(boothPictureCropper.getImgY());
			 * adminPictureVO.setCropImgWidth(boothPictureCropper.getImgWidth());
			 * adminPictureVO.setCropImgHeight(boothPictureCropper.getImgHeight()); } } }
			 */
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getImageConfigure");
			log.error("Error getImageConfigure", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVO;
	}
	
	
	@Override
	public String saveCoordinatesOfImg(AdminPictureVO adminPictureVO,MultipartFile files, String rootPath) {
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
		
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteria.add(Restrictions.eq(ServerConstants.PIC_ID, adminPictureVO.getPicId()));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			AdminBoothEventPicture adminPicture =(AdminBoothEventPicture) criteria.uniqueResult();
			if(adminPicture !=null){
				adminPicture.setScaleXOffset(adminPictureVO.getScaleXOffset());
				adminPicture.setScaleYOffset(adminPictureVO.getScaleYOffset());
				adminPicture.setScaleZOffset(adminPictureVO.getScaleZOffset());
				adminPicture.setPicTitle(adminPictureVO.getPicTitle());
				//adminPicture.setImageWidth(adminPictureVO.getImageWidth());
				adminPicture.setScalingWidth(adminPictureVO.getScalingWidth());
				//adminPicture.setImageHeight(adminPictureVO.getImageHeight());
				adminPicture.setScalingHeight(adminPictureVO.getScalingHeight());
				adminPicture.setUpdatedBy(adminPictureVO.getUpdatedBy());
				adminPicture.setUpdatedDate(new Date());
				
				Criteria criteriaAdminboothevent= sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
				criteriaAdminboothevent.add(Restrictions.eq("EId", adminPicture.getEId()));
				criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				Adminboothevent adminboothevent =(Adminboothevent) criteriaAdminboothevent.uniqueResult();
				if(adminboothevent!=null){
					adminboothevent.setUpdatedDate(new Date());
					sessionFactory.getCurrentSession().update(adminboothevent);
					
					Criteria criteriaBoothPictureCropper= sessionFactory.getCurrentSession().createCriteria(BoothPictureCropper.class);
					criteriaBoothPictureCropper.add(Restrictions.eq(ServerConstants.ADMIN_BOOTH_EVENT_PICTURE_ID, adminPicture.getPicId()));
					BoothPictureCropper boothPictureCroppers =(BoothPictureCropper) criteriaBoothPictureCropper.uniqueResult();
					if(boothPictureCroppers !=null){
						boothPictureCroppers.setImgX(adminPictureVO.getCropImgX());
						boothPictureCroppers.setImgY(adminPictureVO.getCropImgY());
						boothPictureCroppers.setImgWidth(adminPictureVO.getCropImgWidth());
						boothPictureCroppers.setImgHeight(adminPictureVO.getCropImgHeight());
						boothPictureCroppers.setAdminBoothEventPictureId(adminPictureVO.getPicId());
						boothPictureCroppers.setGridShift(adminPictureVO.getGridShift());
						sessionFactory.getCurrentSession().update(boothPictureCroppers);
					}else{
						BoothPictureCropper boothPictureCropper=new BoothPictureCropper();
						boothPictureCropper.setImgX(adminPictureVO.getCropImgX());
						boothPictureCropper.setImgY(adminPictureVO.getCropImgY());
						boothPictureCropper.setImgWidth(adminPictureVO.getCropImgWidth());
						boothPictureCropper.setImgHeight(adminPictureVO.getCropImgHeight());
						boothPictureCropper.setAdminBoothEventPictureId(adminPictureVO.getPicId());
						boothPictureCropper.setGridShift(adminPictureVO.getGridShift());
						sessionFactory.getCurrentSession().save(boothPictureCropper);
						System.out.println("c"+boothPictureCropper);
					}
				}
				
				byte[] bytes  = files.getBytes();
        		String fileName = files.getOriginalFilename();
        		if(fileName!=""){
        			String fileUrl1=IAmuseadminUtil.writeFile1(bytes, rootPath, adminPictureVO.getUpdatedBy(), adminPictureVO.getPicId(), fileName);
				adminPicture.setImageMask(fileUrl1);
        		}
				sessionFactory.getCurrentSession().update(adminPicture);
				result=ServerConstants.SUCCESS;
			}
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : saveCoordinatesOfImg");
			log.error("Error saveCoordinatesOfImg",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	
	
	
	
/*
	@Override
	public String saveCoordinatesOfImg(AdminPictureVO adminPictureVO, MultipartFile files, String rootPath) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();

			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteria.add(Restrictions.eq(ServerConstants.PIC_ID, adminPictureVO.getPicId()));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			AdminBoothEventPicture adminPicture = (AdminBoothEventPicture) criteria.uniqueResult();
			String fileName;
			byte[] bytes;
			Integer eid = 0;
			if (adminPicture != null) {
				adminPicture.setScaleXOffset(adminPictureVO.getScaleXOffset());
				adminPicture.setScaleYOffset(adminPictureVO.getScaleYOffset());
				adminPicture.setScaleZOffset(adminPictureVO.getScaleZOffset());
				adminPicture.setPicTitle(adminPictureVO.getPicTitle());
				adminPicture.setImageWidth(adminPictureVO.getImageWidth());
				adminPicture.setScalingWidth(adminPictureVO.getScalingWidth());

				//adminPicture.getImageHeight();

				eid = adminPicture.getEId();
				BoothAdminLogin loggedInUser = (BoothAdminLogin) sessionFactory.getCurrentSession()
						.get(AdminBoothEventPicture.class, adminPicture.getCreatedBy());

				bytes = files.getBytes();
				fileName = files.getOriginalFilename();
				if (fileName != "") {
					String fileNameFirst = fileName.substring(0, fileName.indexOf('.'));
					String fileNames = fileNameFirst + "_" + IAmuseadminUtil.getRandomNumber() + "."
							+ fileName.substring(fileName.indexOf('.') + 1, fileName.length());
					String fileUrl1 = IAmuseadminUtil.writeFile(bytes, rootPath, adminPicture.getCreatedBy(), eid,
							fileNames, loggedInUser.getSubId());
					String actualImgPath = rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + fileNames;
					IAmuseadminUtil.getImageHeightWidth(adminPicture, actualImgPath);
				}
				//adminPicture.setImageHeight(adminPictureVO.getImageHeight());
				adminPicture.setScalingHeight(adminPictureVO.getScalingHeight());
				adminPicture.setUpdatedBy(adminPictureVO.getUpdatedBy());
				adminPicture.setUpdatedDate(new Date());

				Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession()
						.createCriteria(Adminboothevent.class);
				criteriaAdminboothevent.add(Restrictions.eq("EId", adminPicture.getEId()));
				criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				Adminboothevent adminboothevent = (Adminboothevent) criteriaAdminboothevent.uniqueResult();
				if (adminboothevent != null) {
					adminboothevent.setUpdatedDate(new Date());
					sessionFactory.getCurrentSession().update(adminboothevent);

					Criteria criteriaBoothPictureCropper = sessionFactory.getCurrentSession()
							.createCriteria(BoothPictureCropper.class);
					criteriaBoothPictureCropper.add(
							Restrictions.eq(ServerConstants.ADMIN_BOOTH_EVENT_PICTURE_ID, adminPicture.getPicId()));
					BoothPictureCropper boothPictureCroppers = (BoothPictureCropper) criteriaBoothPictureCropper
							.uniqueResult();
					if (boothPictureCroppers != null) {
						boothPictureCroppers.setImgX(adminPictureVO.getCropImgX());
						boothPictureCroppers.setImgY(adminPictureVO.getCropImgY());
						boothPictureCroppers.setImgWidth(adminPictureVO.getCropImgWidth());
						boothPictureCroppers.setImgHeight(adminPictureVO.getCropImgHeight());
						boothPictureCroppers.setAdminBoothEventPictureId(adminPictureVO.getPicId());
						sessionFactory.getCurrentSession().update(boothPictureCroppers);
					} else {
						BoothPictureCropper boothPictureCropper = new BoothPictureCropper();
						boothPictureCropper.setImgX(adminPictureVO.getCropImgX());
						boothPictureCropper.setImgY(adminPictureVO.getCropImgY());
						boothPictureCropper.setImgWidth(adminPictureVO.getCropImgWidth());
						boothPictureCropper.setImgHeight(adminPictureVO.getCropImgHeight());
						boothPictureCropper.setAdminBoothEventPictureId(adminPictureVO.getPicId());
						sessionFactory.getCurrentSession().save(boothPictureCropper);
						System.out.println("c" + boothPictureCropper);
					}
				}

				// byte[] bytes = files.getBytes();
				// String fileName = files.getOriginalFilename();
				if (fileName != "") {
					String fileUrl1 = IAmuseadminUtil.writeFile1(bytes, rootPath, adminPictureVO.getUpdatedBy(),
							adminPictureVO.getPicId(), fileName);
					adminPicture.setImageMask(fileUrl1);
				}
				sessionFactory.getCurrentSession().update(adminPicture);
				result = ServerConstants.SUCCESS;
			}

			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : saveCoordinatesOfImg");
			log.error("Error saveCoordinatesOfImg", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}
*/
	@Override
	public BoothAdminLogin getProfileDetails(Integer userId) {
		BoothAdminLogin boothAdminLogin = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			// criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,
			// ServerConstants.MAKE_FALSE));
			boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getProfileDetails");
			log.error("Error getProfileDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}

	@Override
	public String updateProfileDetails(Integer userId, SignInVO signInVO) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			if (boothAdminLogin != null) {
				boothAdminLogin.setUsername(signInVO.getUsername());
				boothAdminLogin.setContactNumber(signInVO.getContactNumber());
				boothAdminLogin.setUpdatedDate(new Date());
				boothAdminLogin.setPin(signInVO.getPin());
				boothAdminLogin.setLastname(signInVO.getLastname());
				boothAdminLogin.setUserType(signInVO.getUserType());
				if (signInVO.getImage() != null) {
					boothAdminLogin.setImage(signInVO.getImage());
					boothAdminLogin.setImageFileName(signInVO.getImageFileName());
				}
				sessionFactory.getCurrentSession().update(boothAdminLogin);
				result = ServerConstants.SUCCESS;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : updateProfileDetails");
			log.error("Error updateProfileDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public SubscriptionMaster getSubscriptionListById(Integer subId) {
		SubscriptionMaster subscriptionPlan = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq(ServerConstants.SUB_ID, subId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			subscriptionPlan = (SubscriptionMaster) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscriptionListById");
			log.error("Error getSubscriptionListById", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionPlan;
	}

	@Override
	public TransactionReceiptVO setTransactionHistoryOfSubscription(Integer userId, HttpServletRequest request,
			String res, String cmd) {
		TransactionReceiptVO transactionReceiptVO = new TransactionReceiptVO();
		TransactionMaster transactionMaster = new TransactionMaster();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			if (("VERIFIED").equals(res)) {
				String itemName = request.getParameter("item_name");
				String itemNumber = request.getParameter("item_number");
				String paymentStatus = request.getParameter("payment_status");
				String paymentAmount = request.getParameter("mc_gross");
				String paymentCurrency = request.getParameter("mc_currency");
				String txnId = request.getParameter("txn_id");
				String receiverEmail = request.getParameter("receiver_email");
				String payerEmail = request.getParameter("payer_email");
				String protectionEligibility = request.getParameter("protection_eligibility");
				String addressStatus = request.getParameter("address_status");
				String payerId = request.getParameter("payer_id");
				String tax = request.getParameter("tax");
				String addressStreet = request.getParameter("address_street");
				String paymentDate = request.getParameter("payment_date");
				String addressZip = request.getParameter("address_zip");
				String firstName = request.getParameter("first_name");
				String mcFee = request.getParameter("mc_fee");
				String addressCountryCode = request.getParameter("address_country_code");
				String addressName = request.getParameter("address_name");
				String payerStatus = request.getParameter("payer_status");
				String business = request.getParameter("business");
				String addressCountry = request.getParameter("address_country");
				String addressCity = request.getParameter("address_city");
				String quantity = request.getParameter("quantity");
				String paymentType = request.getParameter("payment_type");
				String addressState = request.getParameter("address_state");
				String paymentFee = request.getParameter("payment_fee");
				String receiverId = request.getParameter("receiver_id");
				String txnType = request.getParameter("txn_type");
				String residenceCountry = request.getParameter("residence_country");
				String handlingAmount = request.getParameter("handling_amount");
				String paymentGross = request.getParameter("payment_gross");
				String auth = request.getParameter("auth");

				transactionMaster.setAddressCity(addressCity);
				transactionMaster.setAddressCountry(addressCountry);
				transactionMaster.setAddressCountryCode(addressCountryCode);
				transactionMaster.setAddressName(addressName);
				transactionMaster.setAddressState(addressState);
				transactionMaster.setAddressStatus(addressStatus);
				transactionMaster.setAddressStreet(addressStreet);
				transactionMaster.setAddressZip(addressZip);
				transactionMaster.setAuth(auth);
				transactionMaster.setBusiness(business);
				transactionMaster.setFirstName(firstName);
				transactionMaster.setHandlingAmount(handlingAmount);
				transactionMaster.setItemName(itemName);
				transactionMaster.setItemNumber(itemNumber);
				transactionMaster.setMcCurrency(paymentCurrency);
				transactionMaster.setMcFee(mcFee);
				transactionMaster.setPayerEmail(payerEmail);
				transactionMaster.setPayerId(payerId);
				transactionMaster.setPayerStatus(payerStatus);
				transactionMaster.setPaymentAmount(paymentAmount);
				transactionMaster.setPaymentDate(paymentDate);
				transactionMaster.setPaymentFee(paymentFee);
				transactionMaster.setPaymentGross(paymentGross);
				transactionMaster.setPaymentStatus(paymentStatus);
				transactionMaster.setPaymentType(paymentType);
				transactionMaster.setProtectionEligibility(protectionEligibility);
				transactionMaster.setQuantity(quantity);
				transactionMaster.setReceiverEmail(receiverEmail);
				transactionMaster.setReceiverId(receiverId);
				transactionMaster.setResidenceCountry(residenceCountry);
				transactionMaster.setStatusResponse(res);
				transactionMaster.setTax(tax);
				transactionMaster.setTxnId(txnId);
				transactionMaster.setTxnType(txnType);
				transactionMaster.setUserId(userId);
				transactionMaster.setStatus(ServerConstants.MAKE_TRUE);
				transactionMaster.setIsDeleted(ServerConstants.MAKE_FALSE);
				Integer transactionMasterId = (Integer) sessionFactory.getCurrentSession().save(transactionMaster);
				if (transactionMasterId != null) {
					TransactionMappingAdmin transactionMappingAdmin = new TransactionMappingAdmin();
					transactionMappingAdmin.setUserId(userId);
					transactionMappingAdmin.setTransactionMasterId(transactionMasterId);
					transactionMappingAdmin.setDate(new Date());
					sessionFactory.getCurrentSession().save(transactionMappingAdmin);
					Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
					criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
					BoothAdminLogin subscriptionPlan = (BoothAdminLogin) criteria.uniqueResult();
					//int newPayment=Integer.parseInt(paymentAmount);
					if (subscriptionPlan != null) {
						subscriptionPlan.setSubId(Integer.parseInt(itemNumber));
						Date today = Calendar.getInstance().getTime();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String folderName = formatter.format(today);
						Date startDate = formatter.parse(folderName);
						if (subscriptionPlan.getSubEndDate() != null) {
							if (subscriptionPlan.getSubUpdatedDate() != null
									&& subscriptionPlan.getSubEndDate().compareTo(startDate) > 0) {
								if (Integer.parseInt(itemNumber) == 2) {
									Date newDate = DateUtils.addHours(subscriptionPlan.getSubEndDate(), 24);
									subscriptionPlan.setSubEndDate(newDate);
								} else {
									
									if(transactionMaster.getPaymentAmount().equals("150.00"))
									{
									System.out.println("Price 1st"+transactionMaster.getPaymentAmount());
									Date newDate = DateUtils.addHours(subscriptionPlan.getSubEndDate(), 720);
									subscriptionPlan.setSubEndDate(newDate);
									}
									else {
										System.out.println("Payment 1st"+transactionMaster.getPaymentAmount());
										Date newDate = DateUtils.addDays(subscriptionPlan.getSubEndDate(), 365);
										subscriptionPlan.setSubEndDate(newDate);
										subscriptionPlan.setIs_annual(ServerConstants.MAKE_TRUE);
									}
								}
							} else {
								if (Integer.parseInt(itemNumber) == 2 )  {
									Date newDate = DateUtils.addHours(startDate, 24);
									subscriptionPlan.setSubEndDate(newDate);
								} else {
									if(transactionMaster.getPaymentAmount().equals("150.00"))
									{
									System.out.println("Price 2nd"+transactionMaster.getPaymentAmount());
									Date newDate = DateUtils.addHours(startDate, 720);
									subscriptionPlan.setSubEndDate(newDate);
									}
									else {
										System.out.println("Payment 2nd"+transactionMaster.getPaymentAmount());
										Date newDate = DateUtils.addDays(startDate, 365);
										subscriptionPlan.setSubEndDate(newDate);
										subscriptionPlan.setIs_annual(ServerConstants.MAKE_TRUE);
									}
								}
							}
						} else {
							if (Integer.parseInt(itemNumber) == 2) {
								Date newDate = DateUtils.addHours(startDate, 24);
								subscriptionPlan.setSubEndDate(newDate);
							} else {
								if(transactionMaster.getPaymentAmount().equals("150.00"))
								{
								System.out.println("Price 3rd"+transactionMaster.getPaymentAmount());
								Date newDate = DateUtils.addHours(startDate, 720);
								subscriptionPlan.setSubEndDate(newDate);
								}
								else {
									System.out.println("Payment 3rd"+transactionMaster.getPaymentAmount());
									Date newDate = DateUtils.addDays(startDate, 365);
									subscriptionPlan.setSubEndDate(newDate);
									subscriptionPlan.setIs_annual(ServerConstants.MAKE_TRUE);
								}
							}
						}
						subscriptionPlan.setSubUpdatedDate(startDate);
						sessionFactory.getCurrentSession().update(subscriptionPlan);
						transactionReceiptVO.setTrsId("" + transactionMasterId);
					     transactionReceiptVO.setSubId(itemNumber);
						
						transactionReceiptVO.setResult(ServerConstants.SUCCESS);
					}
				}
			} else if (("INVALID").equals(res)) {

				String itemName = request.getParameter("item_name");
				String itemNumber = request.getParameter("item_number");
				String paymentStatus = request.getParameter("payment_status");
				String paymentAmount = request.getParameter("mc_gross");
				String paymentCurrency = request.getParameter("mc_currency");
				String txnId = request.getParameter("tx");
				String txnIds = request.getParameter("txn_id");
				String amt = request.getParameter("amt");
				String receiverEmail = request.getParameter("receiver_email");
				String payerEmail = request.getParameter("payer_email");
				String protectionEligibility = request.getParameter("protection_eligibility");
				String addressStatus = request.getParameter("address_status");
				String payerId = request.getParameter("payer_id");
				String tax = request.getParameter("tax");
				String addressStreet = request.getParameter("address_street");
				String paymentDate = request.getParameter("payment_date");
				String addressZip = request.getParameter("address_zip");
				String firstName = request.getParameter("first_name");
				String mcFee = request.getParameter("mc_fee");
				String addressCountryCode = request.getParameter("address_country_code");
				String cc = request.getParameter("cc");
				String addressName = request.getParameter("address_name");
				String payerStatus = request.getParameter("payer_status");
				String st = request.getParameter("st");
				String business = request.getParameter("business");
				String addressCountry = request.getParameter("address_country");
				String addressCity = request.getParameter("address_city");
				String quantity = request.getParameter("quantity");
				String paymentType = request.getParameter("payment_type");
				String addressState = request.getParameter("address_state");
				String paymentFee = request.getParameter("payment_fee");
				String receiverId = request.getParameter("receiver_id");
				String txnType = request.getParameter("txn_type");
				String residenceCountry = request.getParameter("residence_country");
				String handlingAmount = request.getParameter("handling_amount");
				String paymentGross = request.getParameter("payment_gross");
				String auth = request.getParameter("auth");
				if (txnId != null) {
					transactionMaster.setAddressCity(addressCity);
					transactionMaster.setAddressCountry(addressCountry);
					transactionMaster.setAddressCountryCode(cc);
					transactionMaster.setAddressName(addressName);
					transactionMaster.setAddressState(addressState);
					transactionMaster.setAddressStatus(addressStatus);
					transactionMaster.setAddressStreet(addressStreet);
					transactionMaster.setAddressZip(addressZip);
					transactionMaster.setAuth(auth);
					transactionMaster.setBusiness(business);
					transactionMaster.setFirstName(firstName);
					transactionMaster.setHandlingAmount(handlingAmount);
					transactionMaster.setItemName(itemName);
					transactionMaster.setItemNumber(itemNumber);
					transactionMaster.setMcCurrency(paymentCurrency);
					transactionMaster.setMcFee(mcFee);
					transactionMaster.setPayerEmail(payerEmail);
					transactionMaster.setPayerId(payerId);
					transactionMaster.setPayerStatus(st);
					transactionMaster.setPaymentAmount(amt);
					transactionMaster.setPaymentDate(paymentDate);
					transactionMaster.setPaymentFee(paymentFee);
					transactionMaster.setPaymentGross(paymentGross);
					transactionMaster.setPaymentStatus(paymentStatus);
					transactionMaster.setPaymentType(paymentType);
					transactionMaster.setProtectionEligibility(protectionEligibility);
					transactionMaster.setQuantity(quantity);
					transactionMaster.setReceiverEmail(receiverEmail);
					transactionMaster.setReceiverId(receiverId);
					transactionMaster.setResidenceCountry(residenceCountry);
					transactionMaster.setStatusResponse(res);
					transactionMaster.setTax(tax);
					transactionMaster.setTxnId(txnId);
					transactionMaster.setTxnType(txnType);
					transactionMaster.setUserId(userId);
					transactionMaster.setStatus(ServerConstants.MAKE_TRUE);
					transactionMaster.setIsDeleted(ServerConstants.MAKE_FALSE);
					Integer transactionMasterId = (Integer) sessionFactory.getCurrentSession().save(transactionMaster);
					if (transactionMasterId != null) {
						TransactionMappingAdmin transactionMappingAdmin = new TransactionMappingAdmin();
						transactionMappingAdmin.setUserId(userId);
						transactionMappingAdmin.setTransactionMasterId(transactionMasterId);
						transactionMappingAdmin.setDate(new Date());
						sessionFactory.getCurrentSession().save(transactionMappingAdmin);

						transactionReceiptVO.setTrsId("" + transactionMaster.getTxnId());
						transactionReceiptVO.setSubId(itemNumber);
						transactionReceiptVO.setResult(res);
					}
				}

			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : setTransactionHistoryOfSubscription");
			log.error("Error setTransactionHistoryOfSubscription", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return transactionReceiptVO;
	}

	@Override
	public TransactionReceiptVO getTransactionDetails(String trsId, String subId) {
		TransactionReceiptVO transactionReceiptVO = new TransactionReceiptVO();
		Transaction tx = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteriaTransactionMaster = sessionFactory.getCurrentSession()
					.createCriteria(TransactionMaster.class);
			criteriaTransactionMaster.add(Restrictions.eq("transactionMasterId", Integer.parseInt(trsId)));
			TransactionMaster transactionMasters = (TransactionMaster) criteriaTransactionMaster.uniqueResult();
			if (transactionMasters != null) {
				Criteria criteriaSubscriptionMaster = sessionFactory.getCurrentSession()
						.createCriteria(SubscriptionMaster.class);
				criteriaSubscriptionMaster.add(Restrictions.eq(ServerConstants.SUB_ID, Integer.parseInt(subId)));
				SubscriptionMaster subscriptionMasters = (SubscriptionMaster) criteriaSubscriptionMaster.uniqueResult();
				if(subId!="3")
				{
					transactionReceiptVO.setPlanValidity(subscriptionMasters.getSubValidaityDayPeriod() + "Day");
					transactionReceiptVO.setUnitPrice("$" + subscriptionMasters.getSubPrice());
				}
				else if(subId == "3" && transactionMasters.getPaymentAmount().equals("150.00"))
				{
					transactionReceiptVO.setPlanValidity(subscriptionMasters.getSubValidaityDayPeriod() + "Day");
					transactionReceiptVO.setUnitPrice("$" + subscriptionMasters.getSubPrice());
				}
				else
				{
					transactionReceiptVO.setPlanValidity("365 Days");
					transactionReceiptVO.setUnitPrice("$ 100");
				}
				//transactionReceiptVO.setPlanValidity(subscriptionMasters.getSubValidaityDayPeriod() + "Day");
				transactionReceiptVO.setSellerEmail(transactionMasters.getBusiness());
				transactionReceiptVO.setSellerName(transactionMasters.getFirstName());
				transactionReceiptVO.setSupscriptionPlan(transactionMasters.getItemName());
				transactionReceiptVO.setTotalAmount("$" + transactionMasters.getPaymentAmount());
				transactionReceiptVO.setTransactionDate(transactionMasters.getPaymentDate());
				transactionReceiptVO.setTransactionId(transactionMasters.getTxnId());
				//transactionReceiptVO.setUnitPrice("$" + subscriptionMasters.getSubPrice());
				transactionReceiptVO.setResult(ServerConstants.SUCCESS);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (HibernateException he) {
			    if (sessionFactory != null) {
			    	sessionFactory.getCurrentSession().getTransaction().rollback();
			   }
			    log.info("Transaction rollback exception");
			   log.error("Error getTransactionDetails", he);
			 }catch (Exception ex) {
			log.info("BoothAdminDaoImpl Method : getTransactionDetails");
			log.error("Error getTransactionDetails", ex);
			sessionFactory.getCurrentSession().getTransaction().rollback();
	}			    
	return transactionReceiptVO;
}
	@Override
	public String updatePreviousSubscription(Integer userId) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			BoothAdminLogin subscriptionPlan = (BoothAdminLogin) criteria.uniqueResult();
			if (subscriptionPlan != null) {
				subscriptionPlan.setSubId(1);
				Date today = Calendar.getInstance().getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String folderName = formatter.format(today);
				Date startDate = formatter.parse(folderName);
				subscriptionPlan.setSubUpdatedDate(startDate);
				sessionFactory.getCurrentSession().update(subscriptionPlan);
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : updatePreviousSubscription");
			log.error("Error updatePreviousSubscription", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public String advanceBoothSetUp(SignInVO signInVO) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", signInVO.getEId()));
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, signInVO.getUserId()));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Adminboothevent boothAdminLogin = (Adminboothevent) criteria.uniqueResult();
			if (boothAdminLogin != null) {
				boothAdminLogin.setFovTop(signInVO.getFovTop());
				boothAdminLogin.setFovBottom(signInVO.getFovBottom());
				boothAdminLogin.setFovLeft(signInVO.getFovLeft());
				boothAdminLogin.setFovRight(signInVO.getFovRight());
				boothAdminLogin.setGreenScreenCountdownDelay(signInVO.getGreenScreenCountdownDelay());
				boothAdminLogin.setGreenScreenDistance(signInVO.getGreenScreenDistance());
				boothAdminLogin.setGreenScreenHeight(signInVO.getGreenScreenHeight());
				boothAdminLogin.setGreenScreenWidth(signInVO.getGreenScreenWidth());
				boothAdminLogin.setOtherCountdownDelay(signInVO.getOtherCountdownDelay());
				boothAdminLogin.setOtherIntractionTimout(signInVO.getOtherIntractionTimout());
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : advanceBoothSetUp");
			log.error("Error advanceBoothSetUp", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public String deleteEvent(Integer eid, Integer userId) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eid));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Adminboothevent adminboothevent = (Adminboothevent) criteria.uniqueResult();
			if (adminboothevent != null) {
				adminboothevent.setIsDeleted(ServerConstants.MAKE_TRUE);
				sessionFactory.getCurrentSession().update(adminboothevent);
				Criteria criteriaAdminBoothEventPicture = sessionFactory.getCurrentSession()
						.createCriteria(AdminBoothEventPicture.class);
				criteriaAdminBoothEventPicture.add(Restrictions.eq("EId", adminboothevent.getEId()));
				criteriaAdminBoothEventPicture.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaAdminBoothEventPicture
						.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				List<AdminBoothEventPicture> adminBoothEventPicture = criteriaAdminBoothEventPicture.list();
				if (adminBoothEventPicture.size() >= 0) {
					for (AdminBoothEventPicture adminBoothEventPictures : adminBoothEventPicture) {
						adminBoothEventPictures.setIsDeleted(ServerConstants.MAKE_TRUE);
						sessionFactory.getCurrentSession().update(adminBoothEventPictures);
					}
				}
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : deleteEvent");
			log.error("Error deleteEvent", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public int getCount(Integer userId) {
		int rowCount = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			List<BoothUploadImageEmail> uploadImageEmail = criteria.list();
			if (uploadImageEmail.size() >= 0) {
				rowCount = uploadImageEmail.size();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getCount");
			log.error("Error getCount", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return rowCount;
	}

	@Override
	public int getEventCount(Integer userId) {
		int rowCount = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria1 = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria1.add(Restrictions.eq("userId", userId));
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteria1.uniqueResult();

			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			if (!boothAdminLogin.getUserRole().equals("superadmin")) {
				criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			}
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<Adminboothevent> rc = criteria.list();
			if (rc.size() >= 0) {
				rowCount = rc.size();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventCount");
			log.error("Error getEventCount", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return rowCount;
	}

	@Override
	public OptionsReports getEventReportDetailsDefault(Integer userId, Integer eventId, Integer defaultId) {
		OptionsReports optionsReports = new OptionsReports();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			// Total Guest sessions
			Criteria criteriaAdmin = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteriaAdmin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.DEFAULT_ID, defaultId));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaAdmin.add(Restrictions.isNotNull(ServerConstants.MAIL_SENT_TIME));
			List<BoothUploadImageEmail> adminBoothEventPicture = criteriaAdmin.list();
			optionsReports.setTotalGuestSessions(adminBoothEventPicture.size());

			// Total Guests
			Criteria criteriDista = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class)
					.setProjection(Projections.countDistinct(ServerConstants.EMAIL_ID));
			criteriDista.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriDista.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriDista.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriDista.add(Restrictions.eq(ServerConstants.DEFAULT_ID, defaultId));
			criteriDista.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Integer distCounbt = (Integer) criteriDista.uniqueResult();
			optionsReports.setTotalGuests(distCounbt);

			// Repeat Guests
			/*
			 * Criteria criteriaRepeatGuests=
			 * sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class
			 * ); criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.USER_ID,
			 * userId)); criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.EVENT_ID,
			 * eventId)); criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.STATUS,
			 * ServerConstants.MAKE_TRUE));
			 * criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.DEFAULT_ID,
			 * defaultId));
			 * criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.IS_DELETED,
			 * ServerConstants.MAKE_FALSE)); List<BoothUploadImageEmail> guestsList=
			 * criteriaRepeatGuests.list();
			 * 
			 * Map<String, Integer> map = new HashMap<>(); for (BoothUploadImageEmail temp :
			 * guestsList) { Integer count = map.get(temp.getEmailId());
			 * map.put(temp.getEmailId(), (count == null) ? 1 : count + 1); } int count =0;
			 * for (Map.Entry<String,Integer> entry : map.entrySet()) { int value =
			 * entry.getValue(); if(value>1) count++; }
			 * optionsReports.setRepeatGuests(count);
			 */

			Criteria criteriaRepeatGuests = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
			// criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			// criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.STATUS,
			// ServerConstants.MAKE_TRUE));
			// criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.DEFAULT_ID,
			// defaultId));
			// criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.IS_DELETED,
			// ServerConstants.MAKE_FALSE));
			List<StatusCount> guestsList = criteriaRepeatGuests.list();
			int repeatGuestCount = 0;
			if (guestsList != null) {
				for (StatusCount obj : guestsList) {
					repeatGuestCount += obj.getRepetedGuestCount();
				}
			}
			optionsReports.setRepeatGuests(repeatGuestCount);

			// Average Session Time
			Criteria criteriaAverageSessionTime = sessionFactory.getCurrentSession()
					.createCriteria(BoothUploadImageEmail.class).setProjection(Projections.sum("sessionTime"));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.DEFAULT_ID, defaultId));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Integer total = (Integer) criteriaAverageSessionTime.uniqueResult();
			String min = "0";
			String sec = "0";
			if (total != null) {
				total = total * 60;
				double totalSeconds = total / guestsList.size();
				double minutes = Math.floor(totalSeconds / 60);
				double seconds = Math.round(totalSeconds - minutes * 60);
				min = String.valueOf(minutes).split("\\.")[0];
				sec = String.valueOf(seconds).split("\\.")[0];
			}
			optionsReports.setAvgVisitorSession(min + " min " + sec + " sec");

			// Photo Sent
			Criteria criteriaPhotoSent = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.DEFAULT_ID, defaultId));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaPhotoSent.add(Restrictions.isNotNull(ServerConstants.MAIL_SENT_TIME));
			List<BoothUploadImageEmail> photoSentList = criteriaPhotoSent.list();
			optionsReports.setPhotosSent(photoSentList.size());

			// Email Sent
			Criteria criteriaEmailSent = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
			// criteriaEmailSent.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaEmailSent.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			// criteriaEmailSent.add(Restrictions.eq(ServerConstants.STATUS,
			// ServerConstants.MAKE_TRUE));
			// criteriaEmailSent.add(Restrictions.eq(ServerConstants.DEFAULT_ID,
			// defaultId));
			// criteriaEmailSent.add(Restrictions.eq(ServerConstants.IS_DELETED,
			// ServerConstants.MAKE_FALSE));
			// criteriaEmailSent.add(Restrictions.isNotNull(ServerConstants.MAIL_SENT_TIME));
			List<StatusCount> emailSentList = criteriaEmailSent.list();
			int emailSentCount = 0;
			if (emailSentList != null) {
				for (StatusCount obj : emailSentList) {
					emailSentCount += obj.getMailSentCount();
				}
			}
			optionsReports.setEmailsSent(emailSentCount);

			// Email Bounce
			Criteria criteriaEmailBounce = sessionFactory.getCurrentSession()
					.createCriteria(BoothUploadImageEmail.class);
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.DEFAULT_ID, defaultId));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaEmailBounce.add(Restrictions.isNull(ServerConstants.MAIL_SENT_TIME));
			List<BoothUploadImageEmail> emailBounceList = criteriaEmailBounce.list();
			optionsReports.setEmailBounces(emailBounceList.size());

			// Facebook
			Criteria criteriaFacebook = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteriaFacebook.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.DEFAULT_ID, defaultId));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaFacebook.add(Restrictions.eq("facebookShare", "1"));
			List<BoothUploadImageEmail> facebookList = criteriaFacebook.list();
			optionsReports.setFacebook(facebookList.size());

			// Twitter
			Criteria criteriaTwitter = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteriaTwitter.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.DEFAULT_ID, defaultId));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaTwitter.add(Restrictions.eq("twitterShare", "1"));
			List<BoothUploadImageEmail> twitterList = criteriaTwitter.list();
			optionsReports.setTwitter(twitterList.size());
			optionsReports.setEventId(eventId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventReportDetailsDefault");
			log.error("Error getEventReportDetailsDefault", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return optionsReports;
	}

	//Created by Mahesh
	@SuppressWarnings("deprecation")
	@Override
	public OptionsReports getEventReportDetails(Integer userId, Integer eventId,String emailId) {
		OptionsReports optionsReports = new OptionsReports();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteriaBoothAdminLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaBoothAdminLogin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			BoothAdminLogin booothAdminLogin = (BoothAdminLogin) criteriaBoothAdminLogin.uniqueResult();
			
			Criteria criteriaAdmin = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaAdmin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaAdmin.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.countDistinct("guestSessions"));
			criteriaAdmin.setProjection(projList);
			List adminBoothEventPicture = criteriaAdmin.list();
			optionsReports.setTotalGuestSessions(Integer.parseInt(adminBoothEventPicture.get(0).toString()));

			// Total Guests
			Criteria criteriDista = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class)
					.setProjection(Projections.countDistinct(ServerConstants.EMAIL_ID));
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriDista.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriDista.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriDista.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriDista.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Integer distCounbt = (Integer) criteriDista.uniqueResult();
			optionsReports.setTotalGuests(distCounbt);
			
            //Repeat Guests
			Criteria criteriaRepeatGuests = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
			criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			List<StatusCount> guestsList = criteriaRepeatGuests.list();
			int repeatGuestCount = 0;
			if (guestsList != null) {
				for (StatusCount obj : guestsList) {
					repeatGuestCount += obj.getRepetedGuestCount();
				}
			}
			optionsReports.setRepeatGuests(repeatGuestCount);

			// Average Session Time
			Criteria criteriaAverageSessionTime = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class)
					.setProjection(Projections.sum("sessionTime"));
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			
			Criteria criteriaBoothUploadImage = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			//criteriaBoothUploadImage.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaBoothUploadImage.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));		
			List<BoothUploadImageEmail> boothUploadImageEmailDetails = criteriaBoothUploadImage.list();
			Integer total = (Integer) boothUploadImageEmailDetails.size();
			System.out.println("Total ="+total);
			
			/*criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Integer total = (Integer) criteriaAverageSessionTime.uniqueResult();
			System.out.println("Total ="+total);*/				
			
			
			long firstConstantdiff=0;
			if(boothUploadImageEmailDetails.size() ==1) {
				optionsReports.setAvgVisitorSession(0 + " min " + 30 + " sec");
			}
			else if(boothUploadImageEmailDetails.size() ==2)
			{
				Instant  timestampStart = boothUploadImageEmailDetails.listIterator(total-2).next().getUploadTime().toInstant();
				Date myStartDate = Date.from(timestampStart);
				
				Instant  timestampEnd = boothUploadImageEmailDetails.listIterator(total-1).next().getUploadTime().toInstant();
				Date myEndDate = Date.from(timestampEnd);
				
			int constantSecond = 30000;
			long diffMinus = myEndDate.getTime() - myStartDate.getTime();
			firstConstantdiff = diffMinus + constantSecond;
			long averagediff = firstConstantdiff/total;
			long diffSeconds = averagediff / 1000 % 60;
		    long diffMinutes = averagediff / (60 * 1000) % 60;
			optionsReports.setAvgVisitorSession(diffMinutes + " min " + diffSeconds + " sec");			
			}
			
			else if(boothUploadImageEmailDetails.size()>2) {
				int index1=1;
				int index2=2;
				
				for (int i = 2; i < total; i++) {
										
				if(i==2) {
				int constantSecond = 30000;
				Instant  timeStart = boothUploadImageEmailDetails.listIterator(0).next().getUploadTime().toInstant();
				Date startDate = Date.from(timeStart);
								
				Instant  timeEnd = boothUploadImageEmailDetails.listIterator(1).next().getUploadTime().toInstant();
				Date endDate = Date.from(timeEnd);
				long diffMinus = endDate.getTime() - startDate.getTime();
				loopConstant = diffMinus + constantSecond;
				
					}				
							
				Instant  timestampStart = boothUploadImageEmailDetails.listIterator(index1).next().getUploadTime().toInstant();
				Date myStartDate = Date.from(timestampStart);
				
				Instant  timestampEnd = boothUploadImageEmailDetails.listIterator(index2).next().getUploadTime().toInstant();
				Date myEndDate = Date.from(timestampEnd);
				
					long diff = myEndDate.getTime() - myStartDate.getTime();
					loopConstant +=diff;
					index1++;
					index2++;
				}
				
					long averagediff1 = loopConstant/total;
					long diffSeconds1 = averagediff1 / 1000 % 60;
				    long diffMinutes1 = averagediff1 / (60 * 1000) % 60;
					optionsReports.setAvgVisitorSession(diffMinutes1 + " min " + diffSeconds1 + " sec");
					
				}
		
							   			
			//total = total * 60;
			/*double totalSeconds = total / adminBoothEventPicture.size();
			double minutes = Math.floor(totalSeconds / 60);
			double seconds = Math.round(totalSeconds - minutes * 60);
			min = String.valueOf(minutes).split("\\.")[0];
			sec = String.valueOf(seconds).split("\\.")[0];
			optionsReports.setAvgVisitorSession(min + " min " + sec + " sec");*/
			
			
			else{
				optionsReports.setAvgVisitorSession(0 + " min " + 0 + " sec");
			}
						
			// Photo Sent
			Criteria criteriaPhotoSent = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaPhotoSent.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaPhotoSent.add(Restrictions.isNotNull(ServerConstants.MAIL_SENT_TIME));
			//criteriaPhotoSent.add(Restrictions.eq("userId", userId));
			List<BoothUploadImageEmail> photoSentList = criteriaPhotoSent.list();
			optionsReports.setPhotosSent(photoSentList.size());

			// Email Sent
			Criteria criteriaEmailSent = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
			criteriaEmailSent.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaEmailSent.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			List<StatusCount> emailSentList = criteriaEmailSent.list();
			//optionsReports.setEmailsSent(emailSentList.size());
			int emailSentCount = 0;
			if (emailSentList != null) {
				for (StatusCount obj : emailSentList) {
					emailSentCount += obj.getMailSentCount();
				}
			}
			optionsReports.setEmailsSent(emailSentCount);
			
			// Email Bounce
			Criteria criteriaEmailBounce = sessionFactory.getCurrentSession()
					.createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaEmailBounce.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaEmailBounce.add(Restrictions.isNull(ServerConstants.MAIL_SENT_TIME));
			List<BoothUploadImageEmail> emailBounceList = criteriaEmailBounce.list();
			optionsReports.setEmailBounces(emailBounceList.size());

			// Facebook
			Criteria criteriaFacebook = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaFacebook.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaFacebook.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaFacebook.add(Restrictions.eq("facebookShare", "1"));
			List<BoothUploadImageEmail> facebookList = criteriaFacebook.list();
			optionsReports.setFacebook(facebookList.size());

			// Twitter
			Criteria criteriaTwitter = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaTwitter.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaTwitter.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaTwitter.add(Restrictions.eq("twitterShare", "1"));
			List<BoothUploadImageEmail> twitterList = criteriaTwitter.list();
			optionsReports.setTwitter(twitterList.size());
			optionsReports.setEventId(eventId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventReportDetails");
			log.error("Error getEventReportDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return optionsReports;
	}
	
	@Override
	public OptionsReports getEventReportDetails(Integer userId, Integer eventId) {
		OptionsReports optionsReports = new OptionsReports();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteriaBoothAdminLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaBoothAdminLogin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			BoothAdminLogin booothAdminLogin = (BoothAdminLogin) criteriaBoothAdminLogin.uniqueResult();

			// Total Guest sessions
			/*
			 * Criteria criteriaAdmin=
			 * sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class
			 * ); if(!booothAdminLogin.getUserRole().equals("superadmin")){
			 * criteriaAdmin.add(Restrictions.eq(ServerConstants.USER_ID, userId)); }
			 * criteriaAdmin.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			 * criteriaAdmin.add(Restrictions.eq(ServerConstants.STATUS,
			 * ServerConstants.MAKE_TRUE));
			 * criteriaAdmin.add(Restrictions.eq(ServerConstants.IS_DELETED,
			 * ServerConstants.MAKE_FALSE));
			 * criteriaAdmin.add(Restrictions.isNotNull(ServerConstants.MAIL_SENT_TIME));
			 * List<BoothUploadImageEmail> adminBoothEventPicture= criteriaAdmin.list();
			 */
			Criteria criteriaAdmin = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaAdmin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaAdmin.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaAdmin.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.countDistinct("guestSessions"));
			criteriaAdmin.setProjection(projList);
			List adminBoothEventPicture = criteriaAdmin.list();
			optionsReports.setTotalGuestSessions(Integer.parseInt(adminBoothEventPicture.get(0).toString()));

			// Total Guests
			Criteria criteriDista = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class)
					.setProjection(Projections.countDistinct(ServerConstants.EMAIL_ID));
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriDista.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriDista.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriDista.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriDista.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Integer distCounbt = (Integer) criteriDista.uniqueResult();
			optionsReports.setTotalGuests(distCounbt);

			// Repeat Guests
			/*
			 * Criteria criteriaRepeatGuests=
			 * sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class
			 * ); if(!booothAdminLogin.getUserRole().equals("superadmin")){
			 * criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.USER_ID, userId)); }
			 * criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			 * criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.STATUS,
			 * ServerConstants.MAKE_TRUE));
			 * criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.IS_DELETED,
			 * ServerConstants.MAKE_FALSE)); List<BoothUploadImageEmail> guestsList=
			 * criteriaRepeatGuests.list();
			 * 
			 * Map<String, Integer> map = new HashMap<>(); for (BoothUploadImageEmail temp :
			 * guestsList) { Integer count = map.get(temp.getEmailId());
			 * map.put(temp.getEmailId(), (count == null) ? 1 : count + 1); } int count =0;
			 * for (Map.Entry<String,Integer> entry : map.entrySet()) { int value =
			 * entry.getValue(); if(value>1) count++; }
			 * optionsReports.setRepeatGuests(count);
			 */

			Criteria criteriaRepeatGuests = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
			/*
			 * if(!booothAdminLogin.getUserRole().equals("superadmin")){
			 * criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.USER_ID, userId)); }
			 */
			criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			// criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.STATUS,
			// ServerConstants.MAKE_TRUE));
			// criteriaRepeatGuests.add(Restrictions.eq(ServerConstants.IS_DELETED,
			// ServerConstants.MAKE_FALSE));
			List<StatusCount> guestsList = criteriaRepeatGuests.list();
			int repeatGuestCount = 0;
			if (guestsList != null) {
				for (StatusCount obj : guestsList) {
					repeatGuestCount += obj.getRepetedGuestCount();
				}
			}
			optionsReports.setRepeatGuests(repeatGuestCount);

			// Average Session Time
			Criteria criteriaAverageSessionTime = sessionFactory.getCurrentSession()
					.createCriteria(BoothUploadImageEmail.class).setProjection(Projections.sum("sessionTime"));
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaAverageSessionTime.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Integer total = (Integer) criteriaAverageSessionTime.uniqueResult();

			String min;
			String sec;
			if (total != null) {
				total = total * 60;
				double totalSeconds = total / adminBoothEventPicture.size();
				double minutes = Math.floor(totalSeconds / 60);
				double seconds = Math.round(totalSeconds - minutes * 60);
				min = String.valueOf(minutes).split("\\.")[0];
				sec = String.valueOf(seconds).split("\\.")[0];
				optionsReports.setAvgVisitorSession(min + " min " + sec + " sec");
			}

			// Photo Sent
			Criteria criteriaPhotoSent = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaPhotoSent.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaPhotoSent.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaPhotoSent.add(Restrictions.isNotNull(ServerConstants.MAIL_SENT_TIME));
			List<BoothUploadImageEmail> photoSentList = criteriaPhotoSent.list();
			optionsReports.setPhotosSent(photoSentList.size());

			// Email Sent
			Criteria criteriaEmailSent = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
			/*
			 * if(!booothAdminLogin.getUserRole().equals("superadmin")){
			 * criteriaEmailSent.add(Restrictions.eq(ServerConstants.USER_ID, userId)); }
			 */
			criteriaEmailSent.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			// criteriaEmailSent.add(Restrictions.eq(ServerConstants.STATUS,
			// ServerConstants.MAKE_TRUE));
			// criteriaEmailSent.add(Restrictions.eq(ServerConstants.IS_DELETED,
			// ServerConstants.MAKE_FALSE));
			List<StatusCount> emailSentList = criteriaEmailSent.list();
			int emailSentCount = 0;
			if (emailSentList != null) {
				for (StatusCount obj : emailSentList) {
					emailSentCount += obj.getMailSentCount();
				}
			}
			optionsReports.setEmailsSent(emailSentCount);

			// Email Bounce
			Criteria criteriaEmailBounce = sessionFactory.getCurrentSession()
					.createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaEmailBounce.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaEmailBounce.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaEmailBounce.add(Restrictions.isNull(ServerConstants.MAIL_SENT_TIME));
			List<BoothUploadImageEmail> emailBounceList = criteriaEmailBounce.list();
			optionsReports.setEmailBounces(emailBounceList.size());

			// Facebook
			Criteria criteriaFacebook = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaFacebook.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaFacebook.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaFacebook.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaFacebook.add(Restrictions.eq("facebookShare", "1"));
			List<BoothUploadImageEmail> facebookList = criteriaFacebook.list();
			optionsReports.setFacebook(facebookList.size());

			// Twitter
			Criteria criteriaTwitter = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if (!booothAdminLogin.getUserRole().equals("superadmin")) {
				criteriaTwitter.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			criteriaTwitter.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaTwitter.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaTwitter.add(Restrictions.eq("twitterShare", "1"));
			List<BoothUploadImageEmail> twitterList = criteriaTwitter.list();
			optionsReports.setTwitter(twitterList.size());
			optionsReports.setEventId(eventId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventReportDetails");
			log.error("Error getEventReportDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return optionsReports;
	}

	@Override
	public List<BoothUploadImageEmail> dbToCsv(Integer userId, int p, int total) {
		List<BoothUploadImageEmail> eventList = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class)
					.setProjection(Projections.countDistinct(ServerConstants.EMAIL_ID));
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.setFirstResult(p - 1);
			criteria.setMaxResults(total);
			eventList = (List<BoothUploadImageEmail>) criteria.list();
			if (eventList.size() >= 0) {
				return eventList;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : dbToCsv");
			log.error("Error dbToCsv", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventList;
	}

	@Override
	public String resendEmailImages(String userId, String[] total, HttpServletRequest request) {
		String result = "";
		String emailBody = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			String[] elements = total;
			for (String s : elements) {
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
				criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
				criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				criteria.add(Restrictions.eq("id", Integer.parseInt(s)));
				BoothUploadImageEmail uploadImageEmail = (BoothUploadImageEmail) criteria.uniqueResult();
				if (uploadImageEmail != null) {
					String emailId = uploadImageEmail.getEmailId();
					String imageName = uploadImageEmail.getMailImageName();
					String rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("") + "/..")
							.getCanonicalPath();
					String path = rootPath + "/IAmuseimages/EmailImages";
					Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession()
							.createCriteria(Adminboothevent.class);
					criteriaAdminboothevent.add(Restrictions.eq("EId", uploadImageEmail.getEventId()));
					Adminboothevent adminboothevent = (Adminboothevent) criteriaAdminboothevent.uniqueResult();

					if (("null").equals(adminboothevent.getEmailBody())
							|| ("").equals(adminboothevent.getEmailBody())) {
						emailBody = "Thank you for Picture(s) with us! Here are the picture(s) to keep as a memory of the event. We hope you had fun!";
					} else {
						emailBody = adminboothevent.getEmailBody();
					}

					criteria = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
					criteria.add(Restrictions.eq("emailId", emailId));
					criteria.add(Restrictions.eq("eventId", uploadImageEmail.getEventId()));
					StatusCount statusCount = (StatusCount) criteria.uniqueResult();
					if (statusCount != null) {
						Integer mailSent = statusCount.getMailSentCount();
						statusCount.setMailSentCount(mailSent + 1);
						sessionFactory.getCurrentSession().update(statusCount);
					}
					
					String testText = "<html><body>"+
							"<table id=\"m_-5368927744985068358backgroundTable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
							"<tbody><tr><td><table id=\"m_-5368927744985068358innerTable\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
							"<tbody><tr><td class=\"m_-5368927744985068358payloadCell\" valign=\"top\"><table style=\"border:1px solid rgb(207,207,207);border-radius:8px;background:rgb(255,255,255)\" border=\"0\" cellspacing=\"0\" width=\"100%\">"+
							"<tbody><tr><td style=\"color:rgb(85,85,85);font-size:14px;font-family:'helvetica neue',arial,serif;padding:30px 10px\" align=\"center\">"+
							"<h3 style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\">Your photo booth picture from</h3>"+
							"<h1 style=\"font-weight:600;text-align:center;\">"+adminboothevent.getEventName()+"</h1>"+
							//"<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT100_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNExSsY9fpbaIXKUYmJaDURNeFlELw\"><img alt=\"iAmuse\" longdesc=\"https://ci3.googleusercontent.com/proxy/tSnkDkFiofgBYd5c5rsqAFQE_sTYbRIdlGOTJCekl9GkbR2Yz4vb0tMUMQ=s0-d-e1-ft#http://www.iamuse.com\" height=\"50\" width=\"150\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/iamuse-email-logo.png\" class=\"CToWUd\"></a></span></p>"+
						    "<p style=\"font-weight:600;font-size:16px;text-align:center;\">"+emailBody+"</p>"+
						    "<p style=\"font-size:15px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px;\">Facebook :</span><a href=\""+adminboothevent.getFacebook()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getFacebook();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNGumqOxdBAoecShS9vqcuckJavPCA\">"+adminboothevent.getFacebook()+"</a>"+
						   "</p><p style=\"font-size:15px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px\">Twitter :</span><a href=\""+adminboothevent.getTwitter()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getTwitter();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNFDLaMsnpw0j20w_N4xRU_loYQW4Q\">"+adminboothevent.getTwitter()+"</a></p>"+
						    //"<p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Event Date :</span> "+adminboothevent.getEventStart()+"</p><p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Event Host's email address :</span> "+adminboothevent.getEventHostMailerId()+"</p>"+
						   // "<p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Facebook :</span><a href=\""+adminboothevent.getFacebook()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getFacebook();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNGumqOxdBAoecShS9vqcuckJavPCA\">"+adminboothevent.getFacebook()+"</a>"+
						    //"</p><p style=\"font-size:15px;margin-bottom:35px\"><span style=\"font-weight:bold;margin-right:3px\">Twitter :</span><a href=\""+adminboothevent.getTwitter()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getTwitter();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNFDLaMsnpw0j20w_N4xRU_loYQW4Q\">"+adminboothevent.getTwitter()+"</a></p>"+
						    "<p style=\"font-size:15px;margin-top:0px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px\">Event Host:</span> "+adminboothevent.getSponsorName()+"&nbsp|&nbsp;"+adminboothevent.getEventHostMailerId()+"</p>"+
						    "<hr>"+
						    "<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px;background-color:#e2dfcc;\">Do you want this photo booth experience at your own event?<br>iAmuse DIY photo booth - <a alt=\"Download now!\" href=\"http://download.iAmuse.com\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Download now!\">Download now!</a>&nbsp;| <a alt=\"Get a Booth\" href=\"https://www.iamuse.com/buy-green-screen-photo-booth\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Get a Booth\"><font color=\"#000000\" style=\"color:#000000;\">Get a Booth</font></a></p>"+
						    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\">"+
			                "<tbody><tr><td align=\"center\"> <p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT101_com_zimbra_url\"><a href=\"https://www.facebook.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Facebook\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_1_zcsclwgtfb4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none;\" vspace=\"5\"></span></p></a></td>"+
			                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT102_com_zimbra_url\"><a href=\"https://twitter.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Twitter\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_2_zcsclwgttwt4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a></td>"+
			                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"https://www.linkedin.com/company/18273958/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"LinkedIn\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_3_zcsclwgtlin4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a></td>"+
			                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"https://www.instagram.com/iamusepics/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Instagram\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_4_zcsclwgtinsta4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a>"+
			                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"mailto:support@iAmuse.com\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"support@iAmuse.com\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_5_zcsclwgtmail4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a>"+
			                 "</tr></tbody></table>"+
			              // "<p style=\"text-align:center;>"+
			              // "<a href=\"https://www.facebook.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Facebook\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_1_zcsclwgtfb4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none;\" vspace=\"10\"></a>"+
			              // "<a href=\"https://twitter.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Twitter\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_2_zcsclwgttwt4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a>"+
			              // "<a href=\"https://www.linkedin.com/company/18273958/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"LinkedIn\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_3_zcsclwgtlin4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a>"+
			              // "<a href=\"https://www.instagram.com/iamusepics/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Instagram\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_4_zcsclwgtinsta4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a>"+
			               //"<a href=\"mailto:support@iAmuse.com\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"support@iAmuse.com\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_5_zcsclwgtmail4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a></p>"+
			               //"<p style=\"text-align:center;\">"+
			              // "Unsubscribe | <a alt=\"Privacy Policy\" href=\"https://www.iamuse.com/privacy-policy\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Privacy Policy\">Privacy Policy</a></p>"+
			               "<p style=\"font-size:11px;font-family:Arial;\">"+
			               "This email was sent by <a style='color:#0000FF;font-size:11px;font-family:Arial;'target=\"_blank\" href=\"mailto:info@iamuse.com\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>info@iamuse.com</span></a> to <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=3D\"_blank\" href=\"mailto:mike@tanyi.ca\"><span style=3D'color:#0000FF;font-size:11px;font-family:Arial;'>mike@tanyi.ca</span></a></p>"+
			               "<p style=\"font-size:11px;font-family:Arial;\">"+
			               "Not interested? <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=\"_blank\" href=\"https://iamu.maillist-manage.com/ua/testemail?od=2d5a885a69b60a972b74097ddbe736c751185630859ca1fd0&cmpDgs=14a75a50b8770a86&tm=unsub\"rel=\"nofollow\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Unsubscribe</span></a> | <a style='color:#0000FF;font-size:11px;font-family:Arial;'href=\"https://iamu.maillist-manage.com/ua/testemail?od=2d5a885a69b60a972b74097ddbe736c751185630859ca1fd0&cmpDgs=14a75a50b8770a86&tm=upd\" target=\"_blank\" rel=\"nofollow\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Update profile</span></a></p>"+
			              // "<p style=\"text-align:center;\">List Description comes here.</p>"+
			               //"<p>Our&nbsp<a href=\"\" target=\"_blank\" rel=\"nofollow\">Privacy Policy</a>&nbspand&nbsp<a href=\"#\" target=\"_blank\" rel=\"nofollow\">Terms of use.</a></p>"+
			               "<p style=\"font-size:11px;font-family:Arial;\">Our <a style='color:#0000FF;font-size:11px;font-family:Arial;'target=\"_blank\" href=\"https://www.iamuse.com/privacy-policy\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Privacy Policy</span></a> and <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=\"_blank\" href=\"https://www.iamuse.com/terms-conditions\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Terms of Use</span></a></p>"+
			               //"<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"+
			                "</td></tr></tbody></table></td></tr><tr>"+
			               // "<td class=\"m_-5368927744985068358payloadCell\" style=\"height:40px;font-size:9px;font-family:'helvetica neue',arial,serif;color:rgb(136,136,136)\" align=\"right\" valign=\"top\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT105_com_zimbra_url\"><a style=\"color:rgb(136,136,136)\" href=\"http://iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNHuUfOsnIEfdwOnnQQ9sl7Ljgn9ZA\">powered by iAmuse.com</a></span></td>"+
			                "</tr></tbody></table></td></tr></tbody></table></body></html>";

					// String testText="\n\n\t"+emailBody+
					// "\n\n\t Event Host Name : " +adminboothevent.getSponsorName()+
					// "\n\n\t Event Date : " + adminboothevent.getEventStart()+
					// "\n\n\t Event Host's email address :
					// "+adminboothevent.getEventHostMailerId()+
					// "\n\t Others" +"\n"+
					// "\n\n\t Facebook : " +adminboothevent.getFacebook() +
					// "\n\n\t Twitter : " +adminboothevent.getTwitter() ;

					String emailHosting = adminboothevent.getEventHostMailerId();
					mailUtil.sendEmailUploadMailBYWebPortal(
							adminboothevent.getSponsorName() + "(" + emailHosting + ")" + " <dev@iamuse.com>", emailId,
							path, "Your Picture Is Ready", imageName,
							rootPath + uploadImageEmail.getMailImageUrl() + "/" + uploadImageEmail.getEventId() + "/"
									+ uploadImageEmail.getMailImageName(),
							uploadImageEmail.getMailImageName(), ServerConstants.MAKE_TRUE, testText);
				}
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDashboardImpl Method : resendEmailImages ");
			log.error("Error resendEmailImages", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public String syncDevice(Integer userId, Integer deviceId) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq("deviceId", deviceId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			DeviceRegistration deviceRegistration = (DeviceRegistration) criteria.uniqueResult();
			if (deviceRegistration != null) {
				deviceRegistration.setLastSyncTime(new java.sql.Timestamp(new Date().getTime()));
				sessionFactory.getCurrentSession().update(deviceRegistration);
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : syncDevice");
			log.error("Error syncDevice", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public EventVO getEventDetails(Integer userId, Integer eventId) {
		EventVO eventVO = new EventVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eventId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Adminboothevent eventVOs = (Adminboothevent) criteria.uniqueResult();
			if (eventVOs != null) {
				eventVO.setEId(eventVOs.getEId());
				eventVO.setSponsorName(eventVOs.getSponsorName());
				eventVO.setEventStart("" + eventVOs.getEventStart());
				eventVO.setEventName(eventVOs.getEventName());
				eventVO.setCreatedDate(eventVOs.getCreatedDate());
				eventVO.setEventHostMailerId(eventVOs.getEventHostMailerId());
				eventVO.setEmailBody(eventVOs.getEmailBody());
				eventVO.setFacebook(eventVOs.getFacebook());
				eventVO.setTwitter(eventVOs.getTwitter());
				eventVO.setEventType(eventVOs.getEventType());
				if (eventVOs.getIsSubscribed().equals(true)) {
					eventVO.setIsSubscribed("Yes");
				} else if (eventVOs.getIsSubscribed().equals(false)) {
					eventVO.setIsSubscribed("No");
				}
				if (eventVOs.getIsName().equals(true)) {
					eventVO.setIsName("Yes");
				} else if (eventVOs.getIsName().equals(false)) {
					eventVO.setIsName("No");
				}
				if (eventVOs.getIsPhone().equals(true)) {
					eventVO.setIsPhone("Yes");
				} else if (eventVOs.getIsPhone().equals(false)) {
					eventVO.setIsPhone("No");
				}
				eventVO.setEventTimezone(eventVOs.getEventTimezone());
			} /*
				 * else{ Criteria criteriaDefaultAdminBoothEventPicture=
				 * sessionFactory.getCurrentSession().createCriteria(
				 * DefaultAdminBoothEventPicture.class);
				 * criteriaDefaultAdminBoothEventPicture.add(Restrictions.eq(ServerConstants.
				 * CREATED_BY, userId));
				 * criteriaDefaultAdminBoothEventPicture.add(Restrictions.eq("EId", eventId));
				 * criteriaDefaultAdminBoothEventPicture.add(Restrictions.eq(ServerConstants.
				 * STATUS, ServerConstants.MAKE_TRUE));
				 * criteriaDefaultAdminBoothEventPicture.add(Restrictions.eq(ServerConstants.
				 * IS_DELETED, ServerConstants.MAKE_FALSE)); DefaultAdminBoothEventPicture
				 * defaultAdminBoothEventPicture = (DefaultAdminBoothEventPicture)
				 * criteriaDefaultAdminBoothEventPicture.uniqueResult();
				 * if(defaultAdminBoothEventPicture !=null){
				 * eventVO.setDefaultId(defaultAdminBoothEventPicture.getDefaultId()); } }
				 */
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventDetails");
			log.error("Error getEventDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVO;
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionListSA() {
		List<SubscriptionMaster> subscriptionMasters = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			subscriptionMasters = criteria.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscriptionListSA");
			log.error("Error getSubscriptionListSA", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionMasters;
	}

	@Override
	public SubscriptionMaster getSubscriptionListByIdForDeactive(Integer id) {
		SubscriptionMaster subscriptionPlan = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq(ServerConstants.SUB_ID, id));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			subscriptionPlan = (SubscriptionMaster) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscriptionListByIdForDeactive");
			log.error("Error getSubscriptionListByIdForDeactive", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionPlan;
	}

	@Override
	public SubscriptionMaster getSubscriptionListByIdForActive(Integer id) {
		SubscriptionMaster subscriptionPlan = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq(ServerConstants.SUB_ID, id));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			subscriptionPlan = (SubscriptionMaster) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscriptionListByIdForDeactive");
			log.error("Error getSubscriptionListByIdForDeactive", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionPlan;
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionListSAWithPagination(int pageid, int total) {
		List<SubscriptionMaster> subscriptionMasters = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.setFirstResult((pageid - 1) * total);
			criteria.setMaxResults(total);
			subscriptionMasters = criteria.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscriptionListSAWithPagination");
			log.error("Error getSubscriptionListSAWithPagination", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionMasters;
	}

	@Override
	public List<EventVO> getEventListDefault() {
		List<EventVO> eventVOs = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaAdminboothevent.add(Restrictions.eq("eventType", ServerConstants.DEFAULTS));
			List<Adminboothevent> eventLists = criteriaAdminboothevent.list();
			if (!eventLists.isEmpty()) {
				for (Adminboothevent eventList : eventLists) {
					EventVO eventVO = new EventVO();
					eventVO.setEventStart("" + eventList.getEventStart());
					eventVO.setEventName(eventList.getEventName());
					eventVO.setSponsorName(eventList.getSponsorName());
					eventVO.setEId(eventList.getEId());
					eventVO.setEventType(eventList.getEventType());
					eventVOs.add(eventVO);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventListDefault");
			log.error("Error getEventListDefault", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVOs;
	}

	@Override
	public boolean checkDefaultAlreadyExits(EventVO eventVO) {
		boolean flag = ServerConstants.MAKE_FALSE;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq("eventType", eventVO.getEventType()));
			List<Adminboothevent> mapping = criteria.list();
			if (!mapping.isEmpty()) {
				flag = ServerConstants.MAKE_TRUE;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : checkDefaultAlreadyExits");
			log.error("Error checkDefaultAlreadyExits", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}

		return flag;
	}

	@Override
	public SubscriptionMaster getSubscription(Integer subId) {
		SubscriptionMaster mapping = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq(ServerConstants.SUB_ID, subId));
			mapping = (SubscriptionMaster) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscription");
			log.error("Error getSubscription", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return mapping;
	}

	@Override
	public List<EventVO> getEvents(Integer userId) {
		List<EventVO> eventVOs = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.addOrder(Order.desc("EId"));
			List<Adminboothevent> eventList = (List<Adminboothevent>) criteria.list();
			if (!eventList.isEmpty()) {
				for (Adminboothevent event : eventList) {
					EventVO eventVO = new EventVO();
					eventVO.setEId(event.getEId());
					eventVO.setEventStart("" + event.getEventStart());
					eventVO.setEventName(event.getEventName());
					eventVO.setSponsorName(event.getSponsorName());
					eventVOs.add(eventVO);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEvents");
			log.error("Error getEvents", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVOs;
	}

	@Override
	public boolean setAdminDetails(EventVO eventVO) {
		boolean flag = ServerConstants.MAKE_FALSE;
		SimpleDateFormat formatter = new SimpleDateFormat(ServerConstants.DATE_FORMAT);
		String dateInString = eventVO.getEventStart();
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.getMessage();
		}
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eventVO.getEId()));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Adminboothevent boothAdminEvent = (Adminboothevent) criteria.uniqueResult();
			if (boothAdminEvent != null) {
				boothAdminEvent.setEventName(eventVO.getEventName());
				boothAdminEvent.setEventStart(date);
				boothAdminEvent.setSponsorName(eventVO.getSponsorName());
				boothAdminEvent.setEventHostMailerId(eventVO.getEventHostMailerId());
				boothAdminEvent.setFacebook(eventVO.getFacebook());
				boothAdminEvent.setTwitter(eventVO.getTwitter());
				boothAdminEvent.setEmailBody(eventVO.getEmailBody());
				boothAdminEvent.setUpdatedDate(new Date());
				boothAdminEvent.setEventTimezone(eventVO.getEventTimezone());
				if (eventVO.getIsSubscribed().equals("yes")) {
					boothAdminEvent.setIsSubscribed(true);
				} else if (eventVO.getIsSubscribed().equals("no")) {
					boothAdminEvent.setIsSubscribed(false);
				}
				sessionFactory.getCurrentSession().update(boothAdminEvent);
			}
			flag = ServerConstants.MAKE_TRUE;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : setAdminDetails");
			log.error("Error setAdminDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return flag;
		}
		return flag;
	}

	@Override
	public Adminboothevent getFOVValueBasedOnEvent(int eventId) {
		Adminboothevent event = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eventId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			event = (Adminboothevent) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getFOVValueBasedOnEvent");
			log.error("Error getFOVValueBasedOnEvent", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return event;
	}

	@Override
	public List<TransactionHistoryVO> getTransactionHistory(Integer userId) {
		List<TransactionHistoryVO> transactionHistoryVOs = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransactionMappingAdmin.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			List<TransactionMappingAdmin> traMappingAdminList = criteria.list();
			if (!traMappingAdminList.isEmpty()) {
				for (TransactionMappingAdmin transactionMappingAdmin : traMappingAdminList) {
					Criteria criteriaTransactionMaster = sessionFactory.getCurrentSession()
							.createCriteria(TransactionMaster.class);
					criteriaTransactionMaster.add(
							Restrictions.eq("transactionMasterId", transactionMappingAdmin.getTransactionMasterId()));
					TransactionMaster transactionMaster = (TransactionMaster) criteriaTransactionMaster.uniqueResult();
					if (transactionMaster != null) {
						TransactionHistoryVO transactionHistoryVO = new TransactionHistoryVO();
						transactionHistoryVO.setTxnId(transactionMaster.getTxnId());
						transactionHistoryVO.setPaymentType(transactionMaster.getPaymentType());
						transactionHistoryVO.setPaymentDate(transactionMaster.getPaymentDate());
						transactionHistoryVO.setPaymentAmount(transactionMaster.getPaymentAmount());
						transactionHistoryVO.setItemName(transactionMaster.getItemName());
						transactionHistoryVO.setStatusResponse(transactionMaster.getStatusResponse());
						transactionHistoryVOs.add(transactionHistoryVO);
					}
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getTransactionHistory");
			log.error("Error getTransactionHistory", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return transactionHistoryVOs;
	}

	@Override
	public List<EventVO> getEventsWithDelete(Integer userId) {
		List<EventVO> eventVOs = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteria.addOrder(Order.desc("EId"));
			List<Adminboothevent> eventList = (List<Adminboothevent>) criteria.list();
			if (!eventList.isEmpty()) {
				for (Adminboothevent event : eventList) {
					EventVO eventVO = new EventVO();
					eventVO.setEId(event.getEId());
					eventVO.setEventStart("" + event.getEventStart());
					eventVO.setEventName(event.getEventName());
					eventVO.setSponsorName(event.getSponsorName());
					eventVOs.add(eventVO);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventsWithDelete");
			log.error("Error getEventsWithDelete", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVOs;
	}

	@Override
	public Adminboothevent getEventDetails(Integer eId) {
		Adminboothevent eventList = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eId));
			eventList = (Adminboothevent) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getEventDetails");
			log.error("Error getEventDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventList;
	}

	@Override
	public UploadImage getCurrentImages(Integer userId) {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.addOrder(Order.desc("imageId"));
			criteria.setFirstResult(0);
			criteria.setMaxResults(1);
			UploadImage eventList = (UploadImage) criteria.uniqueResult();
			if (eventList != null && eventList.getIsValidate() == ServerConstants.MAKE_FALSE) {
				return eventList;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getCurrentImages");
			log.error("Error getCurrentImages", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return new UploadImage();
	}

	@Override
	public boolean updateMaskingImageStatus(Integer pictureId) {
		boolean flag = ServerConstants.MAKE_FALSE;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteria.add(Restrictions.eq(ServerConstants.PIC_ID, pictureId));
			criteria.add(Restrictions.isNotNull("imageMask"));
			AdminBoothEventPicture picture = (AdminBoothEventPicture) criteria.uniqueResult();
			if (picture != null) {
				picture.setImageMask("");
				sessionFactory.getCurrentSession().update(picture);
				flag = ServerConstants.MAKE_TRUE;
				sessionFactory.getCurrentSession().getTransaction().commit();
			}
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : updateMaskingImageStatus");
			log.error("Error updateMaskingImageStatus", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return flag;
	}

	@Override
	public boolean updateWaterMarkStatus(Integer pictureId) {
		boolean flag = ServerConstants.MAKE_FALSE;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteria.add(Restrictions.eq(ServerConstants.PIC_ID, pictureId));
			criteria.add(Restrictions.isNotNull("waterMarkImage"));
			AdminBoothEventPicture picture = (AdminBoothEventPicture) criteria.uniqueResult();
			if (picture != null) {
				picture.setWaterMarkImage("");
				sessionFactory.getCurrentSession().update(picture);
				flag = ServerConstants.MAKE_TRUE;
				sessionFactory.getCurrentSession().getTransaction().commit();
			}
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : updateWaterMarkStatus");
			log.error("Error updateWaterMarkStatus", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return flag;
	}

	@Override
	public UploadImage getCurrentImagesClicked(Integer userId, int imageId) {
		UploadImage eventList = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq("imageId", imageId));
			eventList = (UploadImage) criteria.uniqueResult();
			if (eventList != null) {
				return eventList;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getCurrentImagesClicked");
			log.error("Error getCurrentImagesClicked", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventList;
	}

	@Override
	public String sendIndividualMailImage(String emailId, Integer imgId, HttpServletRequest request) {
		String result = "";
		String emailBody = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteria.add(Restrictions.eq("id", imgId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			BoothUploadImageEmail uploadImageEmail = (BoothUploadImageEmail) criteria.uniqueResult();
			if (uploadImageEmail != null) {
				uploadImageEmail.setMailSentTime(IAmuseadminUtil.getTimeStamp());
				String rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("") + "/..")
						.getCanonicalPath();
				String path = rootPath + "/IAmuseimages/EmailImages";
				Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession()
						.createCriteria(Adminboothevent.class);
				criteriaAdminboothevent.add(Restrictions.eq("EId", uploadImageEmail.getEventId()));
				Adminboothevent adminboothevent = (Adminboothevent) criteriaAdminboothevent.uniqueResult();

				if (("null").equals(adminboothevent.getEmailBody()) || ("").equals(adminboothevent.getEmailBody())) {
					emailBody = "Thank you for Picture(s) with us! Here are the picture(s) to keep as a memory of the event. We hope you had fun!";
				} else {
					emailBody = adminboothevent.getEmailBody();
				}
				criteria = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
				criteria.add(Restrictions.eq("emailId", emailId));
				criteria.add(Restrictions.eq("eventId", uploadImageEmail.getEventId()));
				StatusCount statusCount = (StatusCount) criteria.uniqueResult();
				if (statusCount != null) {
					//Integer repetedGuest = statusCount.getRepetedGuestCount();
					Integer mailSent = statusCount.getMailSentCount();
					statusCount.setMailSentCount(mailSent + 1);
					//statusCount.setRepetedGuestCount(repetedGuest + 1);
					sessionFactory.getCurrentSession().update(statusCount);
				}
				String testText = "<html><body>"+
						"<table id=\"m_-5368927744985068358backgroundTable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
						"<tbody><tr><td><table id=\"m_-5368927744985068358innerTable\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
						"<tbody><tr><td class=\"m_-5368927744985068358payloadCell\" valign=\"top\"><table style=\"border:1px solid rgb(207,207,207);border-radius:8px;background:rgb(255,255,255)\" border=\"0\" cellspacing=\"0\" width=\"100%\">"+
						"<tbody><tr><td style=\"color:rgb(85,85,85);font-size:14px;font-family:'helvetica neue',arial,serif;padding:30px 10px\" align=\"center\">"+
						"<h3 style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\">Your photo booth picture from</h3>"+
						"<h1 style=\"font-weight:600;text-align:center;\">"+adminboothevent.getEventName()+"</h1>"+
						//"<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT100_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNExSsY9fpbaIXKUYmJaDURNeFlELw\"><img alt=\"iAmuse\" longdesc=\"https://ci3.googleusercontent.com/proxy/tSnkDkFiofgBYd5c5rsqAFQE_sTYbRIdlGOTJCekl9GkbR2Yz4vb0tMUMQ=s0-d-e1-ft#http://www.iamuse.com\" height=\"50\" width=\"150\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/iamuse-email-logo.png\" class=\"CToWUd\"></a></span></p>"+
					    "<p style=\"font-weight:600;font-size:16px;text-align:center;\">"+emailBody+"</p>"+
					    "<p style=\"font-size:15px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px;\">Facebook :</span><a href=\""+adminboothevent.getFacebook()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getFacebook();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNGumqOxdBAoecShS9vqcuckJavPCA\">"+adminboothevent.getFacebook()+"</a>"+
					   "</p><p style=\"font-size:15px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px\">Twitter :</span><a href=\""+adminboothevent.getTwitter()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getTwitter();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNFDLaMsnpw0j20w_N4xRU_loYQW4Q\">"+adminboothevent.getTwitter()+"</a></p>"+
					    //"<p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Event Date :</span> "+adminboothevent.getEventStart()+"</p><p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Event Host's email address :</span> "+adminboothevent.getEventHostMailerId()+"</p>"+
					   // "<p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Facebook :</span><a href=\""+adminboothevent.getFacebook()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getFacebook();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNGumqOxdBAoecShS9vqcuckJavPCA\">"+adminboothevent.getFacebook()+"</a>"+
					    //"</p><p style=\"font-size:15px;margin-bottom:35px\"><span style=\"font-weight:bold;margin-right:3px\">Twitter :</span><a href=\""+adminboothevent.getTwitter()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getTwitter();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNFDLaMsnpw0j20w_N4xRU_loYQW4Q\">"+adminboothevent.getTwitter()+"</a></p>"+
					    "<p style=\"font-size:15px;margin-top:0px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px\">Event Host:</span> "+adminboothevent.getSponsorName()+"&nbsp|&nbsp;"+adminboothevent.getEventHostMailerId()+"</p>"+
					    "<hr>"+
					    "<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px;background-color:#e2dfcc;\">Do you want this photo booth experience at your own event?<br>iAmuse DIY photo booth - <a alt=\"Download now!\" href=\"http://download.iAmuse.com\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Download now!\">Download now!</a>&nbsp;| <a alt=\"Get a Booth\" href=\"https://www.iamuse.com/buy-green-screen-photo-booth\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Get a Booth\"><font color=\"#000000\" style=\"color:#000000;\">Get a Booth</font></a></p>"+
					    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\">"+
		                "<tbody><tr><td align=\"center\"> <p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT101_com_zimbra_url\"><a href=\"https://www.facebook.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Facebook\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_1_zcsclwgtfb4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none;\" vspace=\"5\"></span></p></a></td>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT102_com_zimbra_url\"><a href=\"https://twitter.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Twitter\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_2_zcsclwgttwt4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a></td>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"https://www.linkedin.com/company/18273958/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"LinkedIn\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_3_zcsclwgtlin4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a></td>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"https://www.instagram.com/iamusepics/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Instagram\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_4_zcsclwgtinsta4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"mailto:support@iAmuse.com\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"support@iAmuse.com\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_5_zcsclwgtmail4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a>"+
		                 "</tr></tbody></table>"+
		              // "<p style=\"text-align:center;>"+
		              // "<a href=\"https://www.facebook.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Facebook\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_1_zcsclwgtfb4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none;\" vspace=\"10\"></a>"+
		              // "<a href=\"https://twitter.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Twitter\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_2_zcsclwgttwt4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a>"+
		              // "<a href=\"https://www.linkedin.com/company/18273958/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"LinkedIn\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_3_zcsclwgtlin4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a>"+
		              // "<a href=\"https://www.instagram.com/iamusepics/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Instagram\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_4_zcsclwgtinsta4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a>"+
		               //"<a href=\"mailto:support@iAmuse.com\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"support@iAmuse.com\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_5_zcsclwgtmail4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"10\"></a></p>"+
		              // "<p style=\"text-align:center;\">"+
		              // "Unsubscribe | <a alt=\"Privacy Policy\" href=\"https://www.iamuse.com/privacy-policy\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Privacy Policy\">Privacy Policy</a></p>"+
		               "<p  style=\"font-size:11px;font-family:Arial;\">"+
		               "This email was sent by <a style='color:#0000FF;font-size:11px;font-family:Arial;'target=\"_blank\" href=\"mailto:info@iamuse.com\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>info@iamuse.com</span></a> to <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=3D\"_blank\" href=\"mailto:mike@tanyi.ca\"><span style=3D'color:#0000FF;font-size:11px;font-family:Arial;'>mike@tanyi.ca</span></a></p>"+
		               "<p  style=\"font-size:11px;font-family:Arial;\">"+
		               "Not interested? <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=\"_blank\" href=\"https://iamu.maillist-manage.com/ua/testemail?od=2d5a885a69b60a972b74097ddbe736c751185630859ca1fd0&cmpDgs=14a75a50b8770a86&tm=unsub\"rel=\"nofollow\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Unsubscribe</span></a> | <a style='color:#0000FF;font-size:11px;font-family:Arial;'href=\"https://iamu.maillist-manage.com/ua/testemail?od=2d5a885a69b60a972b74097ddbe736c751185630859ca1fd0&cmpDgs=14a75a50b8770a86&tm=upd\" target=\"_blank\" rel=\"nofollow\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Update profile</span></a></p>"+
		               //"<p style=\"text-align:center;\">List Description comes here.</p>"+
		               //"<p>Our&nbsp<a href=\"\" target=\"_blank\" rel=\"nofollow\">Privacy Policy</a>&nbspand&nbsp<a href=\"#\" target=\"_blank\" rel=\"nofollow\">Terms of use.</a></p>"+
		               "<p  style=\"font-size:11px;font-family:Arial;\">Our <a style='color:#0000FF;font-size:11px;font-family:Arial;'target=\"_blank\" href=\"\r\n" + 
		               "\r\n" + 
		               "https://www.iamuse.com/privacy-policy\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Privacy Policy</span></a> and <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=\"_blank\" href=\"https://www.iamuse.com/terms-conditions\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Terms of Use</span></a></p>"+
		               //"<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"+
		                "</td></tr></tbody></table></td></tr><tr>"+
		               // "<td class=\"m_-5368927744985068358payloadCell\" style=\"height:40px;font-size:9px;font-family:'helvetica neue',arial,serif;color:rgb(136,136,136)\" align=\"right\" valign=\"top\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT105_com_zimbra_url\"><a style=\"color:rgb(136,136,136)\" href=\"http://iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNHuUfOsnIEfdwOnnQQ9sl7Ljgn9ZA\">powered by iAmuse.com</a></span></td>"+
		                "</tr></tbody></table></td></tr></tbody></table></body></html>";
				/*
				 * String testText="\n\n\t"+emailBody+ "\n\t Event Host Name : "
				 * +adminboothevent.getSponsorName()+ "\n\t Event Date : " +
				 * adminboothevent.getEventStart()+
				 * "\n\t Event Host's email address : "+adminboothevent.getEventHostMailerId()+
				 * "\n\n\t Others" +"\n"+ "\n\t Facebook : " +adminboothevent.getFacebook() +
				 * "\n\t Twitter : " +adminboothevent.getTwitter() ;
				 */

				String emailHosting = adminboothevent.getEventHostMailerId();
				mailUtil.sendEmailUploadMailBYWebPortal(
						adminboothevent.getSponsorName() + "(" + emailHosting + ")" + " <dev@iamuse.com>", emailId,
						path, "Your Picture Is Ready", uploadImageEmail.getMailImageName(),
						rootPath + uploadImageEmail.getMailImageUrl() + "/" + uploadImageEmail.getEventId() + "/"
								+ uploadImageEmail.getMailImageName(),
						uploadImageEmail.getMailImageName(), ServerConstants.MAKE_TRUE, testText);
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			log.info("BoothAdminDashboardImpl Method : resendEmailImages ");
			log.error("Error resendEmailImages", e);
			result = "faild";
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public int getCountByEvent(Integer userId, Integer eventId) {
		int rowCount = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<BoothUploadImageEmail> uploadImageEmail = criteria.list();
			if (!uploadImageEmail.isEmpty()) {
				rowCount = uploadImageEmail.size();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getCountByEvent");
			log.error("Error getCountByEvent", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return rowCount;
	}

	@Override
	public String deletEventSinglePicture(String picId, Integer eventId, HttpServletRequest request) {
		String result = "failed";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteria.add(Restrictions.eq("EId", eventId));
			criteria.add(Restrictions.eq(ServerConstants.PIC_ID, Integer.parseInt(picId)));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			AdminBoothEventPicture adminBoothEventPicture = (AdminBoothEventPicture) criteria.uniqueResult();
			if (adminBoothEventPicture != null) {
				adminBoothEventPicture.setIsDeleted(ServerConstants.MAKE_TRUE);
				sessionFactory.getCurrentSession().update(adminBoothEventPicture);
				Criteria criteriaMapping = sessionFactory.getCurrentSession()
						.createCriteria(AdminEventPictureMapping.class);
				criteriaMapping.add(Restrictions.eq("EId", eventId));
				criteriaMapping.add(Restrictions.eq(ServerConstants.PIC_ID, Integer.parseInt(picId)));
				criteriaMapping.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaMapping.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				AdminEventPictureMapping mapping = (AdminEventPictureMapping) criteriaMapping.uniqueResult();
				if (mapping != null) {
					mapping.setIsDeleted(ServerConstants.MAKE_TRUE);
					sessionFactory.getCurrentSession().update(mapping);
				}
				Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession()
						.createCriteria(Adminboothevent.class);
				criteriaAdminboothevent.add(Restrictions.eq("EId", eventId));
				Adminboothevent adminboothevent = (Adminboothevent) criteriaAdminboothevent.uniqueResult();
				if (adminboothevent != null) {
					adminboothevent.setUpdatedDate(new Date());
					sessionFactory.getCurrentSession().update(adminboothevent);
				}
				result = ServerConstants.SUCCESS;
			}
			sessionFactory.getCurrentSession().beginTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : deletEventSinglePicture");
			log.error("Error deletEventSinglePicture", e);
			sessionFactory.getCurrentSession().beginTransaction().rollback();
			return result;
		}
		return result;
	}

	@Override
	public String advanceBoothSetUpConfig(SignInVO signInVO) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteria.add(Restrictions.eq("userId", signInVO.getUserId()));
			Fovbyuser boothAdminLogin = (Fovbyuser) criteria.uniqueResult();
			if (boothAdminLogin != null) {
				boothAdminLogin.setFovTop(signInVO.getFovTop());
				boothAdminLogin.setFovBottom(signInVO.getFovBottom());
				boothAdminLogin.setFovLeft(signInVO.getFovLeft());
				boothAdminLogin.setFovRight(signInVO.getFovRight());
				boothAdminLogin.setGreenScreenCountDownDelay(signInVO.getGreenScreenCountdownDelay());
				boothAdminLogin.setGreenScreenDistance(signInVO.getGreenScreenDistance());
				boothAdminLogin.setGreenScreenHeight(signInVO.getGreenScreenHeight());
				boothAdminLogin.setGreenScreenWidth(signInVO.getGreenScreenWidth());
				boothAdminLogin.setOthrtCountDelay(signInVO.getOtherCountdownDelay());
				boothAdminLogin.setOtherInstructionTimeout(signInVO.getOtherIntractionTimout());
				boothAdminLogin.setImageX(signInVO.getImageX());
				boothAdminLogin.setImageY(signInVO.getImageY());
				boothAdminLogin.setImageHeight(signInVO.getImageHeight());
				boothAdminLogin.setImageWidth(signInVO.getImageWidth());
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}
			if (boothAdminLogin != null) {
				Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession()
						.createCriteria(Adminboothevent.class);
				criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.CREATED_BY, signInVO.getUserId()));
				criteriaAdminboothevent.add(Restrictions.eq("zoomScale", boothAdminLogin.getZoomScale()));
				criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaAdminboothevent.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				List<Adminboothevent> adminboothevent = (List<Adminboothevent>) criteriaAdminboothevent.list();
				if (adminboothevent.size() > 0) {
					for (Adminboothevent adminboothevents : adminboothevent) {
						adminboothevents.setFovBottom(signInVO.getFovBottom());
						adminboothevents.setFovLeft(signInVO.getFovLeft());
						adminboothevents.setFovRight(signInVO.getFovRight());
						adminboothevents.setFovTop(signInVO.getFovTop());
						sessionFactory.getCurrentSession().update(adminboothevents);
					}
				}
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : advanceBoothSetUpConfig");
			log.error("Error advanceBoothSetUpConfig", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionDetails() {
		List<SubscriptionMaster> subscriptionMaster = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			subscriptionMaster = criteria.list();
			if (!subscriptionMaster.isEmpty()) {
				return subscriptionMaster;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getSubscriptionDetails");
			log.error("Error getSubscriptionDetails", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionMaster;
	}

	@Override
	public String setShareValue(int userId, String[] imagesIdList) {
		try {
			for (String img : imagesIdList) {
				sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
				criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
				criteria.add(Restrictions.eq("id", Integer.parseInt(img)));
				BoothUploadImageEmail eventList = (BoothUploadImageEmail) criteria.uniqueResult();
				if (eventList != null) {
					eventList.setFacebookShare("1");
					sessionFactory.getCurrentSession().update(eventList);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : setShareValue");
			log.error("Error setShareValue", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public String setTwitterShareValue(int userId, String[] imagesIdList) {
		try {
			for (String img : imagesIdList) {
				sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
				criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
				criteria.add(Restrictions.eq("id", Integer.parseInt(img)));
				BoothUploadImageEmail eventList = (BoothUploadImageEmail) criteria.uniqueResult();
				if (eventList != null) {
					eventList.setTwitterShare("1");
					sessionFactory.getCurrentSession().update(eventList);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : setTwitterShareValue");
			log.error("Error setTwitterShareValue", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return null;
	}

	public AdminPictureVO editUploadBackgroundImage(AdminPictureVO adminPictureVO, MultipartFile[] files,
			String rootPath) {
		AdminPictureVO adminPictureVO2 = new AdminPictureVO();
		Integer eid = 0;
		
		String[] rootPath2=rootPath.split("iAmuse");
	    try {
	    	sessionFactory.getCurrentSession().beginTransaction();
	    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
	    	criteria.add(Restrictions.eq(ServerConstants.USER_ID, adminPictureVO.getCreatedBy()));
	    	criteria.addOrder(Order.desc("fovId"));
	    	criteria.setFirstResult(0);
	    	criteria.setMaxResults(1);
	    	Fovbyuser fovbyuser = (Fovbyuser) criteria.uniqueResult();
	    	eid = adminPictureVO.getEId();
	    	String[] preBackground;
	    	BoothAdminLogin loggedInUser = (BoothAdminLogin) sessionFactory.getCurrentSession().get(BoothAdminLogin.class, adminPictureVO.getCreatedBy());
	    	List<BackgroundImages> copyPicture = new ArrayList<>();
 
	    	if (adminPictureVO.getSelectedPreImage().trim() != null && adminPictureVO.getSelectedPreImage().trim() != "" && !adminPictureVO.getSelectedPreImage().isEmpty() && !adminPictureVO.getSelectedPreImage().equalsIgnoreCase("0")) 
	    	{ 
	    		preBackground = adminPictureVO.getSelectedPreImage().split(",");
	    		int i=0;
	    		for (String picId : preBackground) 
	    		{ 
	    			Criteria crt = sessionFactory.getCurrentSession().createCriteria(BackgroundImages.class); 
	    			crt.add(Restrictions.eq("idpic",Integer.parseInt(picId))); 
	    			BackgroundImages picture =(BackgroundImages) crt.uniqueResult(); 
	    			
	    			copyPicture.add(picture);
	    			String fileURL2="";
	    			File file1=new File(rootPath2[0] + picture.getPicinfo().toString());
	    			String maskImage=picture.getMask_info();
	    			Path path = Paths.get(file1.getAbsolutePath());
	    			String name = file1.getName();
	    			byte[] bytes = Files.readAllBytes(path);
	    			String originalFileName = file1.getName();
	    			if(originalFileName != "") {
	    				String fileNameFirst = originalFileName.substring(0, originalFileName.indexOf('.'));
						String fileNames = fileNameFirst + "_" + IAmuseadminUtil.getRandomNumber() + "."
								+ originalFileName.substring(originalFileName.indexOf('.') + 1, originalFileName.length());
						String fileUrl1 = IAmuseadminUtil.writeFile(bytes, rootPath, adminPictureVO.getCreatedBy(), eid,
								fileNames, loggedInUser.getSubId());

						String actualImgPath = rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + fileNames;
						//mask_image
						if(picture.getMask_info()!=null)
						{
							File maskURL=new File(rootPath2[0] + picture.getMask_info().toString());
			    			Path path1= Paths.get(maskURL.getAbsolutePath());
			    			String maskname=maskURL.getName();
			    			byte[] maskbytes=Files.readAllBytes(path1);
			    			String maskFileName= maskURL.getName();
						String fileNameFirst1 = maskFileName.substring(0, maskFileName.indexOf('.'));
						String fileNames1 = fileNameFirst1 + "_" + IAmuseadminUtil.getRandomNumber() + "."
								+ maskFileName.substring(maskFileName.indexOf('.') + 1, maskFileName.length());
						String fileUrl2 = IAmuseadminUtil.writeFile(maskbytes, rootPath, adminPictureVO.getCreatedBy(), eid,
								fileNames1, loggedInUser.getSubId());
						fileURL2=fileUrl2;
						String actualImgPath1 = rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + fileNames1;
						}
						AdminBoothEventPicture adminPicture = new AdminBoothEventPicture();
						adminPicture.setCreatedBy(adminPictureVO.getCreatedBy());
						adminPicture.setCreatedDate(new Date());
						adminPicture.setPicName(fileUrl1);
						adminPicture.setEId(eid);
						adminPicture.setScaleXOffset(picture.getScaleXOffset());
			            adminPicture.setScaleYOffset(picture.getScaleYOffset());
			            adminPicture.setScaleZOffset(picture.getScaleZOffset());
			            adminPicture.setScalingHeight(picture.getScalingHeight());
			            adminPicture.setScalingWidth(picture.getScalingWidth());
						if(fileURL2!="")
						{
						adminPicture.setImageMask(fileURL2);
						}
						adminPicture.setIsDeleted(ServerConstants.MAKE_FALSE);
						adminPicture.setStatus(ServerConstants.MAKE_TRUE);
						IAmuseadminUtil.getImageHeightWidth(adminPicture, actualImgPath);
						if (fovbyuser != null) {
							adminPicture.setScaleZOffset(fovbyuser.getZoomScale());
						}
						adminPicture.setPreSetBackground(ServerConstants.MAKE_FALSE);
						Integer adminPicId = (Integer) sessionFactory.getCurrentSession().save(adminPicture);
						if (adminPicId != null) {
							AdminEventPictureMapping adminEventPictureMapping = new AdminEventPictureMapping();
							adminEventPictureMapping.setPicId(adminPicId);
							adminEventPictureMapping.setEId(eid);
							adminEventPictureMapping.setUserId(adminPictureVO.getCreatedBy());
							adminEventPictureMapping.setCreatedDate(new Date());
							adminEventPictureMapping.setIsDeleted(ServerConstants.MAKE_FALSE);
							adminEventPictureMapping.setStatus(ServerConstants.MAKE_TRUE);
							if ((ServerConstants.DEFAULTS).equalsIgnoreCase(adminPictureVO.getEventType())) {
								adminEventPictureMapping.setEventType(adminPictureVO.getEventType());
							}
							sessionFactory.getCurrentSession().save(adminEventPictureMapping);
						}
						
	    			
	    			
	    			}
	    			
	    		} 
	    	}

	    	

				
			for (MultipartFile file : files) {
				byte[] bytes = file.getBytes();
				String fileName = file.getOriginalFilename();
				
				if (fileName != "") {
					String fileNameFirst = fileName.substring(0, fileName.indexOf('.'));
					String fileNames = fileNameFirst + "_" + IAmuseadminUtil.getRandomNumber() + "."
							+ fileName.substring(fileName.indexOf('.') + 1, fileName.length());
					String fileUrl1 = IAmuseadminUtil.writeFile(bytes, rootPath, adminPictureVO.getCreatedBy(), eid,
							fileNames, loggedInUser.getSubId());

					String actualImgPath = rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + fileNames;

					AdminBoothEventPicture adminPicture = new AdminBoothEventPicture();
					adminPicture.setCreatedBy(adminPictureVO.getCreatedBy());
					adminPicture.setCreatedDate(new Date());
					adminPicture.setPicName(fileUrl1);
					adminPicture.setEId(eid);
					adminPicture.setIsDeleted(ServerConstants.MAKE_FALSE);
					adminPicture.setStatus(ServerConstants.MAKE_TRUE);
					IAmuseadminUtil.getImageHeightWidth(adminPicture, actualImgPath);
					if (fovbyuser != null) {
						adminPicture.setScaleZOffset(fovbyuser.getZoomScale());
					}
					adminPicture.setPreSetBackground(ServerConstants.MAKE_FALSE);
					Integer adminPicId = (Integer) sessionFactory.getCurrentSession().save(adminPicture);
					if (adminPicId != null) {
						AdminEventPictureMapping adminEventPictureMapping = new AdminEventPictureMapping();
						adminEventPictureMapping.setPicId(adminPicId);
						adminEventPictureMapping.setEId(eid);
						adminEventPictureMapping.setUserId(adminPictureVO.getCreatedBy());
						adminEventPictureMapping.setCreatedDate(new Date());
						adminEventPictureMapping.setIsDeleted(ServerConstants.MAKE_FALSE);
						adminEventPictureMapping.setStatus(ServerConstants.MAKE_TRUE);
						if ((ServerConstants.DEFAULTS).equalsIgnoreCase(adminPictureVO.getEventType())) {
							adminEventPictureMapping.setEventType(adminPictureVO.getEventType());
						}
						sessionFactory.getCurrentSession().save(adminEventPictureMapping);
					}
					//adminPictureVO2.setResult(ServerConstants.SUCCESS);
				}
			}

			/*
			 * String destinationFilePath; if (!copyPicture.isEmpty()) { for
			 * (AdminBoothEventPicture b : copyPicture) { AdminBoothEventPicture
			 * adminPicture = new AdminBoothEventPicture();
			 * adminPicture.setCreatedBy(adminPictureVO.getCreatedBy());
			 * adminPicture.setCreatedDate(new Date()); if (b.getPicName() != null &&
			 * b.getPicName() != "") { destinationFilePath = b.getPicName();
			 * adminPicture.setPicName(destinationFilePath); } adminPicture.setEId(eid);
			 * adminPicture.setIsDeleted(ServerConstants.MAKE_FALSE);
			 * adminPicture.setStatus(ServerConstants.MAKE_TRUE);
			 * adminPicture.setImageMask(b.getImageMask());
			 * adminPicture.setPreSetBackground(ServerConstants.MAKE_TRUE);
			 * adminPicture.setImageHeight(b.getImageHeight());
			 * adminPicture.setImageWidth(b.getImageWidth());
			 * adminPicture.setPicTitle(b.getPicTitle());
			 * adminPicture.setRgbValues(b.getRgbValues());
			 * adminPicture.setScaleXOffset(b.getScaleXOffset());
			 * adminPicture.setScaleYOffset(b.getScaleYOffset());
			 * adminPicture.setScaleZOffset(b.getScaleZOffset());
			 * adminPicture.setScalingHeight(b.getScalingHeight());
			 * adminPicture.setScalingWidth(b.getScalingWidth()); Integer adminPicIds =
			 * (Integer) sessionFactory.getCurrentSession().save(adminPicture); if
			 * (adminPicIds != null) { Criteria criteriaBoothPictureCropper =
			 * sessionFactory.getCurrentSession()
			 * .createCriteria(BoothPictureCropper.class); criteriaBoothPictureCropper
			 * .add(Restrictions.eq(ServerConstants.ADMIN_BOOTH_EVENT_PICTURE_ID,
			 * b.getPicId())); BoothPictureCropper boothPictureCropper =
			 * (BoothPictureCropper) criteriaBoothPictureCropper .uniqueResult(); if
			 * (boothPictureCropper != null) { BoothPictureCropper boothPictureCropper1 =
			 * new BoothPictureCropper();
			 * boothPictureCropper1.setAdminBoothEventPictureId(adminPicIds);
			 * boothPictureCropper1.setImgHeight(boothPictureCropper.getImgHeight());
			 * boothPictureCropper1.setImgWidth(boothPictureCropper.getImgWidth());
			 * boothPictureCropper1.setImgX(boothPictureCropper.getImgX());
			 * boothPictureCropper1.setImgY(boothPictureCropper.getImgY());
			 * sessionFactory.getCurrentSession().save(boothPictureCropper1); }
			 * AdminEventPictureMapping adminEventPictureMappings = new
			 * AdminEventPictureMapping(); adminEventPictureMappings.setPicId(adminPicIds);
			 * adminEventPictureMappings.setEId(eid);
			 * adminEventPictureMappings.setUserId(adminPictureVO.getCreatedBy());
			 * adminEventPictureMappings.setCreatedDate(new Date());
			 * adminEventPictureMappings.setIsDeleted(ServerConstants.MAKE_FALSE);
			 * adminEventPictureMappings.setStatus(ServerConstants.MAKE_TRUE); if
			 * ((ServerConstants.DEFAULTS).equalsIgnoreCase(adminPictureVO.getEventType()))
			 * { adminEventPictureMappings.setEventType(adminPictureVO.getEventType()); }
			 * sessionFactory.getCurrentSession().save(adminEventPictureMappings); } } }
			 */
			adminPictureVO2.setResult(ServerConstants.SUCCESS);
			adminPictureVO2.setEId(eid);
			Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteriaAdminboothevent.add(Restrictions.eq("EId", eid));
			Adminboothevent adminboothevent = (Adminboothevent) criteriaAdminboothevent.uniqueResult();
			if (adminboothevent != null) {
				adminboothevent.setUpdatedDate(new Date());
				sessionFactory.getCurrentSession().update(adminboothevent);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : editUploadBackgroundImage");
			log.error("Error editUploadBackgroundImage", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVO2;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetBackGrounds(Integer userId) {
	List<ImageEmailFormVO> imageFormList = new ArrayList<>();
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteriaFOv = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteriaFOv.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			Fovbyuser fovByUser = (Fovbyuser) criteriaFOv.uniqueResult();
			if (fovByUser != null) {
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BackgroundImages.class);
				
				/*
				 * criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
				 * criteria.add(Restrictions.eq(ServerConstants.STATUS,
				 * ServerConstants.MAKE_TRUE));
				 * criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,
				 * ServerConstants.MAKE_FALSE));
				 * criteria.add(Restrictions.eq("preSetBackground",
				 * ServerConstants.MAKE_FALSE)); criteria.add(Restrictions.eq("scaleZOffset",
				 * fovByUser.getZoomScale()));
				 */
				 
				 
				 
				List<BackgroundImages> pictureList = criteria.list();
				if (!pictureList.isEmpty()) {
					ImageEmailFormVO image;
					for (BackgroundImages picture : pictureList) {
						image = new ImageEmailFormVO();
						image.setMailImageUrl(picture.getPicinfo());
						//log.info("URL:"+picture.getPicinfo());
						image.setId(picture.getIdpic());
						imageFormList.add(image);
						log.info("LIST1:"+imageFormList);
					}
			}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetBackGrounds");
			log.error("Error getPreSetBackGrounds", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}

		return imageFormList;
	}

	@Override
	public PaginationVO getFirstLast(Integer eId, Integer picId) {
		PaginationVO paginationVO = new PaginationVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteriaFirst = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteriaFirst.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaFirst.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaFirst.add(Restrictions.eq("EId", eId));
			criteriaFirst.addOrder(Order.desc(ServerConstants.PIC_ID));
			criteriaFirst.setFirstResult(0);
			criteriaFirst.setMaxResults(1);
			AdminBoothEventPicture boothAdminLogin = (AdminBoothEventPicture) criteriaFirst.uniqueResult();
			if (boothAdminLogin != null) {
				Criteria criteriaLast = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
				criteriaLast.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteriaLast.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				criteriaLast.add(Restrictions.eq("EId", eId));
				criteriaLast.addOrder(Order.asc(ServerConstants.PIC_ID));
				criteriaLast.setFirstResult(0);
				criteriaLast.setMaxResults(1);
				AdminBoothEventPicture adminBoothEventPicture = (AdminBoothEventPicture) criteriaLast.uniqueResult();
				if (adminBoothEventPicture != null) {
					Criteria criteriaNext = sessionFactory.getCurrentSession()
							.createCriteria(AdminBoothEventPicture.class);
					criteriaNext.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
					criteriaNext.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
					criteriaNext.add(Restrictions.eq("EId", eId));
					criteriaNext.add(Restrictions.gt(ServerConstants.PIC_ID, picId));
					criteriaNext.setFirstResult(0);
					criteriaNext.setMaxResults(1);
					AdminBoothEventPicture adminBoothEventPictureNext = (AdminBoothEventPicture) criteriaNext
							.uniqueResult();
					if (adminBoothEventPictureNext != null) {
						paginationVO.setNext(adminBoothEventPictureNext.getPicId());
					} else {
						paginationVO.setNext(boothAdminLogin.getPicId());
					}
					Criteria criteriaPrevious = sessionFactory.getCurrentSession()
							.createCriteria(AdminBoothEventPicture.class);
					criteriaPrevious.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
					criteriaPrevious.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
					criteriaPrevious.add(Restrictions.eq("EId", eId));
					criteriaPrevious.add(Restrictions.lt(ServerConstants.PIC_ID, picId));
					List<AdminBoothEventPicture> adminBoothEventPicturePrevious = criteriaPrevious.list();
					if (!adminBoothEventPicturePrevious.isEmpty()) {
						paginationVO.setPrevious(adminBoothEventPicturePrevious
								.get(adminBoothEventPicturePrevious.size() - 1).getPicId());
					} else {
						paginationVO.setPrevious(adminBoothEventPicture.getPicId());
					}
					paginationVO.setFirst(adminBoothEventPicture.getPicId());
					paginationVO.setLast(boothAdminLogin.getPicId());
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getFirstLast");
			log.error("Error getFirstLast", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return paginationVO;
	}

	@Override
	public Fovbyuser getFovTableData(Integer userId) {
		Fovbyuser data = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteria.add(Restrictions.eq("userId", userId));
			data = (Fovbyuser) criteria.uniqueResult();
			/*
			 * if(data!=null){ return data; }else{ Fovbyuser fovbyuser=new Fovbyuser();
			 * fovbyuser.setZoomScale("1.00"); fovbyuser.setFovBottom("0");
			 * fovbyuser.setFovLeft("0"); fovbyuser.setFovRight("0");
			 * fovbyuser.setFovTop("0"); fovbyuser.setUserId(userId); Integer
			 * fovId=(Integer)sessionFactory.getCurrentSession().save(fovbyuser); if(fovId
			 * !=0){ Criteria criteriaFovbyuser=
			 * sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			 * criteriaFovbyuser.add(Restrictions.eq("fovId", fovId)); data =(Fovbyuser)
			 * criteriaFovbyuser.uniqueResult(); }
			 */
			// }
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getFovTableData");
			log.error("Error getFovTableData", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return data;
	}

	@Override
	public SignInVO getImageData(Integer userId) {
		SignInVO signInVO = new SignInVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			Fovbyuser data = (Fovbyuser) criteria.uniqueResult();
			if (data != null) {
				signInVO.setImageX(data.getImageX());
				signInVO.setImageY(data.getImageY());
				signInVO.setImageHeight(data.getImageHeight());
				signInVO.setImageWidth(data.getImageWidth());
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getImageData");
			log.error("Error getImageData", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return signInVO;
	}

	@Override
	public String saveZoomScale(Integer userId, SignInVO signInVO) {
		String result = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			Fovbyuser data = (Fovbyuser) criteria.uniqueResult();
			if (data != null) {
				data.setZoomScale(signInVO.getZoomScale());
				sessionFactory.getCurrentSession().update(data);
			}
			result = ServerConstants.SUCCESS;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : saveZoomScale");
			log.error("Error saveZoomScale", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public AdminPictureVO updateWaterMarkLookAtTouchThankYouCameraScreen(AdminPictureVO adminPictureVO,
			MultipartFile[] files, String rootPath, MultipartFile thankyoufiles, MultipartFile lookAtTouchScreen,
			MultipartFile cameraTVScreenSaver, MultipartFile waterMarkImage) {
		String destinationFilePath = null;
		String[] preBackground = null;
		AdminPictureVO adminPictureVO2 = new AdminPictureVO();
		Integer eid = adminPictureVO.getEId();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", adminPictureVO.getEId()));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			Adminboothevent eventLoad = (Adminboothevent) criteria.uniqueResult();
			if (thankyoufiles != null && thankyoufiles.getSize() > 0) {
				String thankyoufilesURL = IAmuseadminUtil.writeFile(thankyoufiles.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(thankyoufiles));
				eventLoad.setThankYouScreen(thankyoufilesURL);
				eventLoad.setPreSetThankYouScreen(ServerConstants.MAKE_FALSE);
			}
			if (lookAtTouchScreen != null && lookAtTouchScreen.getSize() > 0) {
				String lookAtTouchScreenURL = IAmuseadminUtil.writeFile(lookAtTouchScreen.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(lookAtTouchScreen));
				eventLoad.setLookAtTouchScreen(lookAtTouchScreenURL);
				eventLoad.setPreLookAtTouchScreen(ServerConstants.MAKE_FALSE);
			}

			if (cameraTVScreenSaver != null && cameraTVScreenSaver.getSize() > 0) {
				String cameraTVScreenSaverURL = IAmuseadminUtil.writeFile(cameraTVScreenSaver.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(cameraTVScreenSaver));
				eventLoad.setCameraTVScreenSaver(cameraTVScreenSaverURL);
				eventLoad.setPreCameraTVScreenSaver(ServerConstants.MAKE_FALSE);
			}
			if (waterMarkImage != null && waterMarkImage.getSize() > 0) {
				String waterMarkImageURL = IAmuseadminUtil.writeFile(waterMarkImage.getBytes(), rootPath,
						adminPictureVO.getCreatedBy(), eid, IAmuseadminUtil.changeUploadFileName(waterMarkImage));
				eventLoad.setWaterMarkImage(waterMarkImageURL);
				eventLoad.setPreWaterMarkImage(ServerConstants.MAKE_FALSE);
			}
			List<String> waterMarkPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreWaterMarkImage())) {
				preBackground = adminPictureVO.getSelectedPreWaterMarkImage().split(",");
				for (String eventId : preBackground) {
					Criteria criteriaWaterMarkImage = sessionFactory.getCurrentSession()
							.createCriteria(Adminboothevent.class);
					criteriaWaterMarkImage.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent waterMarkImageURL = (Adminboothevent) criteriaWaterMarkImage.uniqueResult();
					waterMarkPicture.add(waterMarkImageURL.getWaterMarkImage());
				}
				if (!waterMarkPicture.isEmpty()) {
					for (String b : waterMarkPicture) {
						if (b != null && b != "") {
							String[] fileNames = b.split("/");
							destinationFilePath = ServerConstants.IMAGES + adminPictureVO.getCreatedBy() + "/" + eid
									+ "/" + eid + "/" + fileNames[5];
							File folder = new File(
									rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid);
							if (!folder.exists()) {
								if (folder.mkdirs()) {
									folder.setExecutable(ServerConstants.MAKE_TRUE);
									folder.setReadable(ServerConstants.MAKE_TRUE);
									folder.setWritable(ServerConstants.MAKE_TRUE);
								}
							}
							FileMoveUtility.copyFileUsingStream(
									new File(rootPath + "/" + fileNames[3] + "/" + fileNames[4] + "/" + fileNames[5]),
									new File(rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid
											+ "/" + fileNames[5]));
							eventLoad.setWaterMarkImage(destinationFilePath);
						}
					}
				}
			}
			List<String> cameraTVScreenSaverPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreCameraTVScreenSaver())) {
				preBackground = adminPictureVO.getSelectedPreCameraTVScreenSaver().split(",");
				for (String eventId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					crt.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent cameraTVScreenSaverURL = (Adminboothevent) crt.uniqueResult();
					cameraTVScreenSaverPicture.add(cameraTVScreenSaverURL.getCameraTVScreenSaver());
				}
				if (!cameraTVScreenSaverPicture.isEmpty()) {
					for (String b : cameraTVScreenSaverPicture) {
						if (b != null && b != "") {
							String[] fileNames = b.split("/");
							destinationFilePath = ServerConstants.IMAGES + adminPictureVO.getCreatedBy() + "/" + eid
									+ "/" + eid + "/" + fileNames[5];
							File folder = new File(
									rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid);
							if (!folder.exists()) {
								if (folder.mkdirs()) {
									folder.setExecutable(ServerConstants.MAKE_TRUE);
									folder.setReadable(ServerConstants.MAKE_TRUE);
									folder.setWritable(ServerConstants.MAKE_TRUE);
								}
							}
							FileMoveUtility.copyFileUsingStream(
									new File(rootPath + "/" + fileNames[3] + "/" + fileNames[4] + "/" + fileNames[5]),
									new File(rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid
											+ "/" + fileNames[5]));
							eventLoad.setCameraTVScreenSaver(destinationFilePath);
						}
					}
				}
			}
			List<String> lookAtTouchScreenPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreLookAtTouchScreen())) {

				preBackground = adminPictureVO.getSelectedPreLookAtTouchScreen().split(",");
				for (String eventId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					crt.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent lookTouchScrrenURL = (Adminboothevent) crt.uniqueResult();
					lookAtTouchScreenPicture.add(lookTouchScrrenURL.getLookAtTouchScreen());
				}
				if (!lookAtTouchScreenPicture.isEmpty()) {
					for (String b : lookAtTouchScreenPicture) {
						if (b != null && b != "") {
							String[] fileNames = b.split("/");
							destinationFilePath = ServerConstants.IMAGES + adminPictureVO.getCreatedBy() + "/" + eid
									+ "/" + eid + "/" + fileNames[5];
							File folder = new File(
									rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid);
							if (!folder.exists()) {
								if (folder.mkdirs()) {
									folder.setExecutable(ServerConstants.MAKE_TRUE);
									folder.setReadable(ServerConstants.MAKE_TRUE);
									folder.setWritable(ServerConstants.MAKE_TRUE);
								}
							}
							FileMoveUtility.copyFileUsingStream(
									new File(rootPath + "/" + fileNames[3] + "/" + fileNames[4] + "/" + fileNames[5]),
									new File(rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid
											+ "/" + fileNames[5]));
							eventLoad.setLookAtTouchScreen(destinationFilePath);
						}
					}
				}
			}
			List<String> thankYouScreenPicture = new ArrayList<>();
			if (!("0").equalsIgnoreCase(adminPictureVO.getSelectedPreThankYouScreen())) {
				preBackground = adminPictureVO.getSelectedPreThankYouScreen().split(",");
				for (String eventId : preBackground) {
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					crt.add(Restrictions.eq("EId", Integer.parseInt(eventId)));
					Adminboothevent thankYouScrenURL = (Adminboothevent) crt.uniqueResult();
					thankYouScreenPicture.add(thankYouScrenURL.getThankYouScreen());
				}
				if (!thankYouScreenPicture.isEmpty()) {
					for (String b : thankYouScreenPicture) {
						if (b != null && b != "") {
							String[] fileNames = b.split("/");
							destinationFilePath = ServerConstants.IMAGES + adminPictureVO.getCreatedBy() + "/" + eid
									+ "/" + eid + "/" + fileNames[5];
							File folder = new File(
									rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid);
							if (!folder.exists()) {
								if (folder.mkdirs()) {
									folder.setExecutable(ServerConstants.MAKE_TRUE);
									folder.setReadable(ServerConstants.MAKE_TRUE);
									folder.setWritable(ServerConstants.MAKE_TRUE);
								}
							}
							FileMoveUtility.copyFileUsingStream(
									new File(rootPath + "/" + fileNames[3] + "/" + fileNames[4] + "/" + fileNames[5]),
									new File(rootPath + "/" + adminPictureVO.getCreatedBy() + "/" + eid + "/" + eid
											+ "/" + fileNames[5]));
							eventLoad.setThankYouScreen(destinationFilePath);
						}
					}

				}
			}
			eventLoad.setUpdatedDate(new Date());
			sessionFactory.getCurrentSession().update(eventLoad);
			adminPictureVO2.setResult(ServerConstants.SUCCESS);
			adminPictureVO2.setEId(eid);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : updateWaterMarkLookAtTouchThankYouCameraScreen");
			log.error("Error updateWaterMarkLookAtTouchThankYouCameraScreen", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVO2;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetThankYouScreenBasedOnEventId(Integer userId, Integer eid, Integer subId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			if (subId != 1) {
				criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			}
			criteria.add(Restrictions.eq("EId", eid));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setId(event.getEId());
					image.setThankYouScreen(event.getThankYouScreen());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetWaterMarkImageBasedOnEventId");
			log.error("Error getPreSetWaterMarkImageBasedOnEventId", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetWaterMarkImageBasedOnEventId(Integer userId, Integer eid, Integer subId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			if (subId != 1) {
				criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			}
			criteria.add(Restrictions.eq("EId", eid));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setWaterMarkImage(event.getWaterMarkImage());
					image.setId(event.getEId());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetWaterMarkImageBasedOnEventId");
			log.error("Error getPreSetWaterMarkImageBasedOnEventId", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetLookAtTouchScreenBasedOnEventId(Integer userId, Integer eid, Integer subId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			if (subId != 1) {
				criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			}
			criteria.add(Restrictions.eq("EId", eid));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setId(event.getEId());
					image.setLookAtTouchScreen(event.getLookAtTouchScreen());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetLookAtTouchScreenBasedOnEventId");
			log.error("Error getPreSetLookAtTouchScreenBasedOnEventId", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetCameraTVScreenSaverBasedOnEventId(Integer userId, Integer eid,
			Integer subId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			if (subId != 1) {
				criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			}
			criteria.add(Restrictions.eq("EId", eid));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setId(event.getEId());
					image.setCameraTVScreenSaver(event.getCameraTVScreenSaver());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetCameraTVScreenSaverBasedOnEventId");
			log.error("Error getPreSetCameraTVScreenSaverBasedOnEventId", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetThankYouScreen(Integer userId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setId(event.getEId());
					image.setThankYouScreen(event.getThankYouScreen());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetThankYouScreen");
			log.error("Error getPreSetThankYouScreen", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetWaterMarkImageScreen(Integer userId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq("preWaterMarkImage", ServerConstants.MAKE_FALSE));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setWaterMarkImage(event.getWaterMarkImage());
					image.setId(event.getEId());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetWaterMarkImageScreen");
			log.error("Error getPreSetWaterMarkImageScreen", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetLookAtTouchScreen(Integer userId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq("preLookAtTouchScreen", ServerConstants.MAKE_FALSE));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setId(event.getEId());
					image.setLookAtTouchScreen(event.getLookAtTouchScreen());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetLookAtTouchScreen");
			log.error("Error getPreSetLookAtTouchScreen", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetCameraTVScreen(Integer userId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq("preCameraTVScreenSaver", ServerConstants.MAKE_FALSE));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setId(event.getEId());
					image.setCameraTVScreenSaver(event.getCameraTVScreenSaver());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetCameraTVScreen");
			log.error("Error getPreSetCameraTVScreen", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<ImageEmailFormVO> getPreSetWaterMarkImageTouchScreenCameraTVThankYouScreen(Integer userId) {
		List<ImageEmailFormVO> imageFormList = new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<Adminboothevent> eventList = criteria.list();
			if (!eventList.isEmpty()) {
				ImageEmailFormVO image;
				for (Adminboothevent event : eventList) {
					image = new ImageEmailFormVO();
					image.setWaterMarkImage(event.getWaterMarkImage());
					image.setId(event.getEId());
					image.setThankYouScreen(event.getThankYouScreen());
					image.setCameraTVScreenSaver(event.getCameraTVScreenSaver());
					image.setLookAtTouchScreen(event.getLookAtTouchScreen());
					imageFormList.add(image);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getPreSetWaterMarkImageTouchScreenCameraTVThankYouScreen");
			log.error("Error getPreSetWaterMarkImageTouchScreenCameraTVThankYouScreen", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormList;
	}

	@Override
	public List<DeviceRegistration> getRegisteredDevicePush(Integer userId) {
		List<DeviceRegistration> deviceRegistration = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq(ServerConstants.DEVICE_TYPE, "Camera device"));
			deviceRegistration = criteria.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : getRegisteredDevicePush");
			log.error("Error getRegisteredDevicePush", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return deviceRegistration;
	}

	@Override
	public List<AdminBoothEventPicture> notConfiguredImage(Integer eid, Integer userId) {
		List<AdminBoothEventPicture> adminPicture = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteriaAdminPicture = sessionFactory.getCurrentSession()
					.createCriteria(AdminBoothEventPicture.class);
			criteriaAdminPicture.add(Restrictions.eq("EId", eid));
			criteriaAdminPicture.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaAdminPicture.add(Restrictions.isNull("imageHeight"));
			adminPicture = criteriaAdminPicture.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : notConfiguredImage");
			log.error("Error notConfiguredImage", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPicture;
	}

	@Override
	public DeviceVO grtDeviceTockenAndIP(Integer userId) {
		DeviceVO deviceVO = new DeviceVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteria.add(Restrictions.eq(ServerConstants.DEVICE_TYPE, "Camera device"));
			DeviceRegistration deviceRegistration = (DeviceRegistration) criteria.uniqueResult();
			if (deviceRegistration != null) {
				deviceVO.setCameraDeviceToken(deviceRegistration.getDeviceToken());
				deviceVO.setCameraDeviceIP(deviceRegistration.getIpAddress());
			}
			Criteria criteriaTouch = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteriaTouch.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaTouch.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteriaTouch.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			criteriaTouch.add(Restrictions.eq(ServerConstants.DEVICE_TYPE, "Guest Touchscreen"));
			DeviceRegistration deviceRegistrationTouch = (DeviceRegistration) criteriaTouch.uniqueResult();
			if (deviceRegistrationTouch != null) {
				deviceVO.setTouchDeviceIP(deviceRegistrationTouch.getIpAddress());
				deviceVO.setTouchDeviceToken(deviceRegistrationTouch.getDeviceToken());
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : grtDeviceTockenAndIP");
			log.error("Error grtDeviceTockenAndIP", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return deviceVO;
	}

	@Override
	public String updateEventDate(Integer eventId) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eventId));
			Adminboothevent adminboothevent = (Adminboothevent) criteria.uniqueResult();
			if (adminboothevent != null) {
				adminboothevent.setUpdatedDate(new Date());
				sessionFactory.getCurrentSession().update(adminboothevent);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
			result = "success";
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : updateEventDate");
			log.error("Error grtDeviceTockenAndIP", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public Boolean deleteUserProfile(Integer userId, String rootPath) {
		Boolean result = ServerConstants.MAKE_FALSE;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();

			/*Criteria criteriaAdminBooth = session.createCriteria(Adminboothevent.class);
			criteriaAdminBooth.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteriaAdminBooth.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));

			List<Adminboothevent> adminBoothEvents = criteriaAdminBooth.list();
			if (adminBoothEvents != null) {
				for (Adminboothevent adminBoothEvent : adminBoothEvents) {
					Criteria criteriaStatusCount = session.createCriteria(StatusCount.class);
					criteriaStatusCount.add(Restrictions.eq(ServerConstants.EVENT_ID, adminBoothEvent.getEId()));
					List<StatusCount> statusCounts = criteriaStatusCount.list();
					if (statusCounts != null) {
						for (StatusCount statusCount : statusCounts) {
							session.delete(statusCount);
						}
					}
					adminBoothEvent.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(adminBoothEvent);
					
				}
			}*/

			/* ---------------------------------------- */

			try {
				sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
				criteria.add(Restrictions.eq("userId", userId));
				criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
				criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
				BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
				if (boothAdminLogin != null) {
					//boothAdminLogin.setIsDeleted(ServerConstants.MAKE_TRUE);
					sessionFactory.getCurrentSession().delete(boothAdminLogin);
				}
				result = ServerConstants.MAKE_TRUE;
				sessionFactory.getCurrentSession().getTransaction().commit();
				session.clear();
				session.close();
			} catch (Exception e) {
				log.info("BoothAdminDaoImpl Method : deleteUserProfile");
				log.error("Error deleteUserProfile", e);
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}

			/*--------------------------------------------- */

			/*Criteria criteriaAdminBoothEventPic = session.createCriteria(AdminBoothEventPicture.class);
			criteriaAdminBoothEventPic.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteriaAdminBoothEventPic.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<AdminBoothEventPicture> adminBoothEventPictures = criteriaAdminBoothEventPic.list();
			if (adminBoothEventPictures != null) {
				for (AdminBoothEventPicture adminBoothEventPicture : adminBoothEventPictures) {
					adminBoothEventPicture.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(adminBoothEventPicture);
				}
			}

			Criteria criteriaAdminEventPicMap = session.createCriteria(AdminEventPictureMapping.class);
			criteriaAdminEventPicMap.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaAdminEventPicMap.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<AdminEventPictureMapping> adminEventPicMaps = criteriaAdminEventPicMap.list();
			if (adminEventPicMaps != null) {
				for (AdminEventPictureMapping adminEventPicMap : adminEventPicMaps) {
					adminEventPicMap.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(adminEventPicMap);
				}
			}

			Criteria criteriaBoothUploadImageEmail = session.createCriteria(BoothUploadImageEmail.class);
			criteriaBoothUploadImageEmail.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaBoothUploadImageEmail.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<BoothUploadImageEmail> boothUploadImageEmails = criteriaBoothUploadImageEmail.list();
			if (boothUploadImageEmails != null) {
				for (BoothUploadImageEmail boothUploadImageEmail : boothUploadImageEmails) {
					boothUploadImageEmail.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(boothUploadImageEmail);
				}
			}

			Criteria criteriaDeviceRegistration = session.createCriteria(DeviceRegistration.class);
			criteriaDeviceRegistration.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaDeviceRegistration.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<DeviceRegistration> deviceRegistrations = criteriaDeviceRegistration.list();
			if (deviceRegistrations != null) {
				for (DeviceRegistration deviceRegistration : deviceRegistrations) {
					deviceRegistration.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(deviceRegistration);
				}
			}

			Criteria criteriaDeviceRegistrationAll = session.createCriteria(DeviceRegistrationAll.class);
			criteriaDeviceRegistrationAll.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaDeviceRegistrationAll.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<DeviceRegistrationAll> deviceRegistrationAlls = criteriaDeviceRegistrationAll.list();
			if (deviceRegistrationAlls != null) {
				for (DeviceRegistrationAll deviceRegistrationAll : deviceRegistrationAlls) {
					deviceRegistrationAll.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(deviceRegistrationAll);
				}
			}

			Criteria criteriaDefAdminBoothEventPic = session.createCriteria(DefaultAdminBoothEventPicture.class);
			criteriaDefAdminBoothEventPic.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
			criteriaDefAdminBoothEventPic.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<DefaultAdminBoothEventPicture> defAdminBoothEventPics = criteriaDefAdminBoothEventPic.list();
			if (defAdminBoothEventPics != null) {
				for (DefaultAdminBoothEventPicture defAdminBoothEventPic : defAdminBoothEventPics) {
					defAdminBoothEventPic.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(defAdminBoothEventPic);
				}
			}

			Criteria criteriaUploadImage = session.createCriteria(UploadImage.class);
			criteriaUploadImage.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteriaUploadImage.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
			List<UploadImage> uploadImages = criteriaUploadImage.list();
			if (uploadImages != null) {
				for (UploadImage uploadImage : uploadImages) {
					uploadImage.setIsDeleted(ServerConstants.MAKE_TRUE);
					session.update(uploadImage);
				}
			}

			Criteria criteriaCrashLogs = session.createCriteria(CrashLogs.class);
			criteriaCrashLogs.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			List<CrashLogs> crashLogs = criteriaCrashLogs.list();
			if (crashLogs != null) {
				for (CrashLogs crashLog : crashLogs) {
					session.delete(crashLog);
				}
			}

//			 	Criteria criteriaFovbyuser= session.createCriteria(Fovbyuser.class);
//			 	criteriaFovbyuser.add(Restrictions.eq(ServerConstants.USER_ID, userId));
//			 	List<Fovbyuser> fovbyusers = criteriaFovbyuser.list();
//			 	if(fovbyusers != null){
//			 		for (Fovbyuser fovbyuser : fovbyusers) {
//			 			session.delete(fovbyuser);
//			 		}
//			 	}

			Criteria criteriaTransactionMappingAdmin = session.createCriteria(TransactionMappingAdmin.class);
			criteriaTransactionMappingAdmin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			List<TransactionMappingAdmin> transactionMappingAdmins = criteriaTransactionMappingAdmin.list();
			if (transactionMappingAdmins != null) {
				for (TransactionMappingAdmin transactionMappingAdmin : transactionMappingAdmins) {
					session.delete(transactionMappingAdmin);
				}
			}

			deleteBackgroundImageFolder(userId, rootPath);
			result = ServerConstants.MAKE_TRUE;
			sessionFactory.getCurrentSession().getTransaction().commit();*/
		} catch (Exception e) {
			log.info("BoothAdminDaoImpl Method : deleteUserProfile");
			log.error("Error deleteUserProfile", e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	private static void deleteBackgroundImageFolder(Integer userId, String rootPath) {

		File imageFolder = new File(rootPath + "/" + userId);
		if (imageFolder.exists()) {
			String[] entries = imageFolder.list();
			for (String s : entries) {
				File currentFile = new File(imageFolder.getPath(), s);

				String[] subFolderEntries = currentFile.list();
				for (String sub : subFolderEntries) {
					File subFolder = new File(imageFolder.getPath() + "/" + s, sub);
					subFolder.delete();
				}

				currentFile.delete();
			}
			imageFolder.delete();
		}
	}

	@Override
	public com.iamuse.admin.entity.AccessToken GetAccessToken(Integer userId) {
		com.iamuse.admin.entity.AccessToken accessToken = null;
		try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(com.iamuse.admin.entity.AccessToken.class);
		criteria.add(Restrictions.eq("userid", userId));
		// criteria.add(Restrictions.eq(ServerConstants.CREATED_BY, userId));
		accessToken = (com.iamuse.admin.entity.AccessToken) criteria.uniqueResult();
		System.out.println("Access Token*************="+accessToken);
		if (accessToken != null) {
			sessionFactory.getCurrentSession().update(accessToken);
			
		} else {
			sessionFactory.getCurrentSession().save(accessToken);
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e)
		{
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return accessToken;
	}

	public BoothAdminLogin updateSubsctiptionForAdmin(Integer userId,Integer subId) {
		BoothAdminLogin boothAdminLogin = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId", userId));
			boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			boothAdminLogin.setSubId(subId);
			boothAdminLogin.setIs_annual(false);
			Date today = Calendar.getInstance().getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String folderName = formatter.format(today);
		    Date startDate = formatter.parse(folderName);
		    /* if(boothAdminLogin.getIs_annual()==true)
			{
				boothAdminLogin.setIs_annual(ServerConstants.MAKE_FALSE);
			}*/
		    
		    if(subId==1){
				boothAdminLogin.setSubUpdatedDate(startDate);
			    boothAdminLogin.setSubEndDate(null);
			    }
		    else if(subId==2){
			Date newDate = com.iamuse.admin.util.DateUtils.addDays(startDate, 1);
			boothAdminLogin.setSubUpdatedDate(startDate);
		    boothAdminLogin.setSubEndDate(newDate);
		    }
		else if(subId==3)
		{
			   	Date newDate = com.iamuse.admin.util.DateUtils.addDays(startDate, 30);
			   	boothAdminLogin.setSubUpdatedDate(startDate);
		    	boothAdminLogin.setSubEndDate(newDate);
		    }
			sessionFactory.getCurrentSession().saveOrUpdate(boothAdminLogin);
			sessionFactory.getCurrentSession().getTransaction().commit();
	
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}

	@Override
	public boolean addDataAccessToken(TokenClsss token) {
		
		boolean result = false;
		try {
			 	sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccessToken.class);
				criteria.add(Restrictions.eq("userid", Integer.parseInt(token.getUserId())));
				AccessToken accessToken = (AccessToken) criteria.uniqueResult();
				AccessToken access = new AccessToken();
				if(accessToken == null) {
					access.setUserid((Integer.parseInt(token.getUserId())));
					access.setRefresh_token(token.getRefresh_token());
					access.setAccess_token(token.getAccess_token());
					access.setFlag(token.getFlag());
					access.setCreate_by(com.iamuse.admin.util.DateUtils.getTimeStamp());
					access.setUpdated_by(com.iamuse.admin.util.DateUtils.getTimeStamp());
					sessionFactory.getCurrentSession().save(access);					
				}				
				else if(accessToken != null) {
					accessToken.setUserid((Integer.parseInt(token.getUserId())));
					accessToken.setRefresh_token(token.getRefresh_token());
					accessToken.setAccess_token(token.getAccess_token());
					accessToken.setFlag(token.getFlag());
					accessToken.setCreate_by(com.iamuse.admin.util.DateUtils.getTimeStamp());
					accessToken.setUpdated_by(com.iamuse.admin.util.DateUtils.getTimeStamp());
					sessionFactory.getCurrentSession().update(accessToken);			
					}	        
				result = true;
				sessionFactory.getCurrentSession().getTransaction().commit();
			}catch(Exception e)
				{
					e.printStackTrace();
					sessionFactory.getCurrentSession().getTransaction().rollback();
					result = false;
				}
				return result;
	}

	
	@Override
	public void removeAccessToken(TokenClsss token)
	{
		try {
			
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = this.sessionFactory.getCurrentSession();
			Query q = session.createQuery("delete AccessToken where userid = "+ token.getUserId());
			q.executeUpdate();
			sessionFactory.getCurrentSession().getTransaction().commit();
			
			/*
			 * sessionFactory.getCurrentSession().beginTransaction(); Criteria criteria=
			 * sessionFactory.getCurrentSession().createCriteria(AccessToken.class);
			 * criteria.add(Restrictions.eq("userid", token.getUserid())); AccessToken
			 * accessToken = (AccessToken) criteria.uniqueResult() ; if(accessToken!=null){
			 * sessionFactory.getCurrentSession().delete(accessToken);
			 * sessionFactory.getCurrentSession().getTransaction().commit(); } else {
			 * sessionFactory.getCurrentSession().getTransaction().commit(); }
			 */
			
			/*
			 * sessionFactory.getCurrentSession().beginTransaction(); Session session =
			 * sessionFactory.getCurrentSession();
			 * 
			 * Criteria criteriaTransactionMappingAdmin=
			 * session.createCriteria(AccessToken.class);
			 * criteriaTransactionMappingAdmin.add(Restrictions.eq("userid",
			 * token.getUserid())); List<AccessToken> transactionMappingAdmins =
			 * criteriaTransactionMappingAdmin.list(); if(transactionMappingAdmins != null){
			 * for (AccessToken transactionMappingAdmin : transactionMappingAdmins) {
			 * session.delete(transactionMappingAdmin); } }
			 */
		}
		catch(Exception e)
		{
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
	}
	

	/*
	 * @Override public Boolean deleteUserProfile(Integer userId) { // TODO
	 * Auto-generated method stub return null; }
	 */

	/*
	 * @Override public Boolean deleteUserProfile(Integer userId) { Boolean
	 * result=ServerConstants.MAKE_FALSE; try {
	 * sessionFactory.getCurrentSession().beginTransaction(); Criteria criteria=
	 * sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
	 * criteria.add(Restrictions.eq("userId", userId));
	 * criteria.add(Restrictions.eq(ServerConstants.STATUS,
	 * ServerConstants.MAKE_TRUE));
	 * criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,
	 * ServerConstants.MAKE_FALSE)); BoothAdminLogin boothAdminLogin =
	 * (BoothAdminLogin)criteria.uniqueResult(); if(boothAdminLogin !=null){
	 * boothAdminLogin.setIsDeleted(ServerConstants.MAKE_TRUE);
	 * sessionFactory.getCurrentSession().update(boothAdminLogin); }
	 * result=ServerConstants.MAKE_TRUE;
	 * sessionFactory.getCurrentSession().getTransaction().commit(); } catch
	 * (Exception e) { log.info("BoothAdminDaoImpl Method : deleteUserProfile");
	 * log.error("Error deleteUserProfile",e);
	 * sessionFactory.getCurrentSession().getTransaction().rollback(); } return
	 * result; }
	 */
}
