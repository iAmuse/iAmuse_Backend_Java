package com.iamuse.admin.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.BoothAdminLoginResponseVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.SubscriptionMaster;

public interface SuperadminDao {
	
	Adminboothevent getCurrentEvent(Integer eid);
	
	AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO, MultipartFile file, String rootPath);
	
	public List<BoothAdminLogin> getBoothAdminLoginList();
	
	public List<EventVO> getEventListForSuperAdmin();
	
	public boolean saveSubscriptionMaster(SubscriptionMaster subscriptionMaster,Integer userId);
	
	public boolean updateSubscriptionMaster(SubscriptionMaster subscriptionMaster,Integer userId);
	
	public boolean updateAdminSubscription(BoothAdminLogin boothAdminLogin,Integer userId);
	
	public boolean deleteSubscriptionMasterById(SubscriptionMaster masterValue, Integer userId);
	
	public boolean activeSubscriptionMaster(SubscriptionMaster subscriptionMaster,Integer userId);

	public boolean deactiveSubscriptionMaster(SubscriptionMaster subscriptionMaster,Integer userId);
	
	public Adminboothevent getEventValueByEventId(Integer eventId);
	
	public boolean deleteAdminBoothEventByEventId(Integer id);
	
	public EventVO saveCreateEventBySuperAdmin(EventVO eventVO, Integer userId);
	
	public 	List<SubscriptionMaster> getSubscriptionListSuperAdminPage();
	
	public Integer getSubscriptionCountForBarChart(String startDate,String endDate);
	
	public Integer getSubscriptionCountForBarChartDefaultSubscription(String janStartDate, String janEndDate, Integer subId);
	
	SubscriptionMaster getSubscriptionListById(Integer subId);
	
	public List<AdminPictureVO> getPicList(Integer eid, Integer userId);
	
	public AdminPictureVO getImageConfigure(Integer picId);
	
	public String saveCoordinatesOfImg(AdminPictureVO adminPictureVO,MultipartFile files, MultipartFile files1, String rootPath);
	
	public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO,MultipartFile[] files, String rootPath);

	public boolean updateAdminBoothEvent(Adminboothevent adminBoothEvent);

	public BoothAdminLogin getProfileDetails(Integer userId);

	public List<BoothAdminLogin> getBoothAdminLoginListWithPagination(int pageid,
			int total);

	public List<EventVO> getEventListForSuperAdminWithPagination(int pageid, int total);

	

}
