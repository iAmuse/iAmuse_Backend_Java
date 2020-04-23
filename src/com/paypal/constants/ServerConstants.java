package com.paypal.constants;
public class ServerConstants {
	/**
	 * HttpSession and HttpSerletRequest Attributes
	 */
	
	/**
	 * User Roles
	 */
	public static final Integer ADMIN = 1;
	public static final Integer USER = 2;
	public static final Integer OWNER = 3;
	public static final Integer SUBSCRIPTION_FREE=4;
	public static final Integer SUBSCRIPTION_NORMAL=1;
	
	
	/**
	 *Tickets Type
	 */
	public static final String EARLY_BIRD = "eb";
	public static final String ADVANCE = "ad";
	
	/**
	 *Event Status
	 */
	public static final String APPROVED = "Approved";
	public static final String REJECTED = "Rejected";
	public static final String PENDING = "Pending";
	public static final String DRAFT = "Draft";
	public static final boolean SAVEASDRAFT = true;
	public static final boolean NOT_SAVEASDRAFT =false;
	
	
	
	/**
	 * User Status
	 */
	public static final boolean ACTIVE = true;
	public static final boolean NOT_ACTIVE = false;
	public static final String USERBLOCKED = "Blocked";
	public static final String USERAPPROVED = "Approved";
	public static final String USERAUNBLOCKED = "Active";
	public static final Integer EXPIRED = 6;
	/**
	 *User View Constants
	 */
	public static final String LOGIN_VIEW = "loginPage";
	public static final String WELCOME_VIEW = "userWelcome";
	public static final String FORGOT_PASSWORD_VIEW = "forgotPassword";
	/**
	 * Error Message Key Constants
	 */
	public static final String ERROR_MESSAGE_KEY = "errorMessageKey";
	public static final String SUCCESS_MESSAGE_KEY = "successMessageKey";
	public static final String CONFIRMATION_MESSAGE_HEADER = "confirmationHeader";
	public static final String CONFIRMATION_MESSAGE_BODY = "confirmationBody";
	
	/**
	 * Current URL
	 */
	public static final String CURRENT_URL ="http://192.168.1.53:8080";
	/**
	 * Facebook key
	 */
	public static final String FACEBOOK_ID ="1655809694705473";
	
	/**
	 *Pay Pal
	 */
	public static final String SELLER_API_USERNAME ="sb-y3kp4352520@personal.example.com";
	public static final String SELLER_API_PASSWORD ="!3hkN5n7vnW%";
    public static final String SELLER_API_SIGNATURE ="Ag6jZU5MR7.MzlPyjFO6SFo9cOXwAFbxBCH.4Wbu76-DFvbSYYEB9KmK";
	
	
	//PayPal account
	/*public static final String CLIENT_ID="AcX0I93sJQJMpDFlW5V3m7fyTaV18NtQ8TIV2rVD7EPVnkV-MtBczmzmaQNWOYk7AHIFvgGghy2fVY6H";
	public static final String SECRET_ID="EOEBwXda1YN2zRRdg6FtWMtDHISSMAjf4Uf-tqJ3sMqV1dcn_jZuPi6MGPQOCpnQJvjexULLEVwwrfqW";
	public static final String SELLER_ID ="billing@iamuse.com";
	public static final String PASSWORD ="!3hkN5n7vnW%";*/
	
	
	
	//sandbox account
	public static final String CLIENT_ID="AfbUTT99-lKACK_IzQ31zuYfx0EEJy6a60Ez-TiHHmWmfQ6r80aaCv4rFc--dnGszVaWd99WHm5UPO2h";
	public static final String SECRET_ID="EFNEiD2vfLf5MDgyg0ATlbgLBFPuYglbGoPnlQEOZB8oRMur_uluXWdv2HjaOBy4Lmrt6YpgBlYcBZhz";
	public static final String SELLER_ID ="sb-gfcxy204490@business.example.com";
	public static final String PASSWORD ="Qi_K+MoQ";
	
	
	public static final int Max_IMAGE_SIZE = 15000;
		
	
	/**
	 * Ticket MAster
	 */
	public static final String EVENT_TICKET="MTEVN";
	public static final String PACKAGE_TICKET="MTPKG";
	
	
	/*IAMUSE BOOTHADMINCONTROLLER & AUTOCONFIGCONTROLLER*/
	public static final String REDIRECT_LOGIN_PAGE="redirect:/";
	public static final String GET_SUBS="redirect:getSubscription";
	public static final String SUCCESS_MESSAGE="successMessage";
	public static final String ERROR_MESSAGE="errorMessage";
	public static final String SUCCESS="success";
	public static final String SETUP_BACKGROUND_IMAGE="redirect:setUpBackgroundImage?picId=";
	public static final String SETUP_BACKGROUND_IMAGE_CONFIG="redirect:setUpBackgroundImageConfig?picId=";
	public static final String VALID_FROM="validFrom";
	public static final String BOOTH_SETUP="redirect:boothSetUp";
	public static final String EVENT_GALLERY="redirect:eventGallery?eventId=";
	public static final String ADD_IMAGES_OF_EVENT="redirect:addImagesOfEvent?eventId=";
	public static final String ADD_IMAGES_OF_EVENT_CONFIG="redirect:addImagesOfEventConfig?eventId=";
	public static final String IMAGE_DETAILS="imageDetails";
	public static final String BOOTH_ADMIN_LOGIN2="boothAdminLogin2";
	public static final String FOVBYUSER="fovbyuser";
	public static final String DEVICE_REGISTRATION="deviceRegistration";
	public static final String EMAIL_IMAGE_LIST="emailImagesList";
	public static final String OPTIONS_REPORTS="optionsReports";
	public static final String SUBSCRIPTION_PLAN="subscriptionPlan";
	public static final String UPLOAD_IMAGE="uploadImage";
	public static final String EVENT_NAME="eventName";
	public static final String SUBSCRIPTION="subscription";
	public static final String PAGE_ID="pageid";
	public static final String TOTAL="total";
	public static final String CONTENT_DISPOSITION="Content-Disposition";
	public static final String USER_MASTER="userMaster";
	public static final String BOOTH_ADMIN_LOGIN="boothAdminLogin";
	public static final String ADMIN_PICTURE_VOS2="adminPictureVOs2";
	public static final String GET_UPLOAD_IMAGES_CINFIG="redirect:getUploadedImagesConfig?eventId=";
	public static final String GET_UPLOAD_IMAGES="redirect:getUploadedImages?eventId=";
	public static final String IMAGES="/iAmuse_images/Admin_Picture";
	public static final String LIB_IMAGES="/library_images";
	public static final String UPLOAD_SUCCESS="Successfully uploaded background image";
	public static final String UPLOAD_FAILED="failed for upload background image";
	public static final String EVENT_LIST="eventList";
	public static final String DEFAULTS="default";
	public static final String DEFAULT_ID="defaultId";
	public static final boolean MAKE_TRUE=true;
	public static final boolean MAKE_FALSE=false;
	public static final String BOOTH_SETUP_BY_EVENT_CONFIG_FIRST="redirect:boothSetUpByEventConfigFirst";
	public static final String RGB_SETUP="redirect:rgbSetup";
	public static final String STATUS="status";
	public static final String OPEN_IMAGE_PAGE="redirect:openimagepage?id=";
	public static final String USER_ID="userId";
	public static final String EMAIL_ID="emailId";
	public static final String IS_DELETED="isDeleted";
	public static final String CREATED_BY="createdBy";
	public static final String EVENT_ID="eventId";
	public static final String MAIL_SENT_TIME="mailSentTime";
	public static final String DATE_FORMAT="yyyy-MM-dd";
	public static final String PIC_ID="picId";
	public static final String ADMIN_BOOTH_EVENT_PICTURE_ID="adminBoothEventPictureId";
	public static final String SUB_ID="subId";
	public static final String DEVICE_TYPE="deviceType";
	public static final String IMAGE_ID="imageId";
}
