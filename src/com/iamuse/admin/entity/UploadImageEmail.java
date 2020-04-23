package com.iamuse.admin.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="upload_image_email", catalog="iamuse")
public class UploadImageEmail
  implements Serializable
{
  private Integer id;
  private String mailImageUrl;
  private Timestamp uploadTime;
  private String mailImageName;
  private String emailId;
  private Integer userId;
  private Timestamp mailSentTime;
  private Boolean status;
  private String photoSessionId;
  private String publicUseAck;
  private String newsletterOptIn;
  private String fileName;
  private String renderVersion;
  private String share;
  private Integer downloadStatus;
  
  public UploadImageEmail() {}
  
  public UploadImageEmail(String mailImageUrl, Timestamp uploadTime, String mailImageName, String emailId, Integer userId, Timestamp mailSentTime, Boolean status, String photoSessionId, String publicUseAck, String newsletterOptIn, String fileName, String renderVersion, String share, Integer downloadStatus)
  {
    this.mailImageUrl = mailImageUrl;
    this.uploadTime = uploadTime;
    this.mailImageName = mailImageName;
    this.emailId = emailId;
    this.userId = userId;
    this.mailSentTime = mailSentTime;
    this.status = status;
    this.photoSessionId = photoSessionId;
    this.publicUseAck = publicUseAck;
    this.newsletterOptIn = newsletterOptIn;
    this.fileName = fileName;
    this.renderVersion = renderVersion;
    this.share = share;
    this.downloadStatus = downloadStatus;
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", unique=true, nullable=false)
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  @Column(name="mail_image_url", length=1000)
  public String getMailImageUrl()
  {
    return this.mailImageUrl;
  }
  
  public void setMailImageUrl(String mailImageUrl)
  {
    this.mailImageUrl = mailImageUrl;
  }
  
  @Column(name="upload_time", length=19)
  public Timestamp getUploadTime()
  {
    return this.uploadTime;
  }
  
  public void setUploadTime(Timestamp uploadTime)
  {
    this.uploadTime = uploadTime;
  }
  
  @Column(name="mail_image_name", length=45)
  public String getMailImageName()
  {
    return this.mailImageName;
  }
  
  public void setMailImageName(String mailImageName)
  {
    this.mailImageName = mailImageName;
  }
  
  @Column(name="email_id", length=45)
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  @Column(name="user_id")
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  @Column(name="mail_sent_time", length=19)
  public Timestamp getMailSentTime()
  {
    return this.mailSentTime;
  }
  
  public void setMailSentTime(Timestamp mailSentTime)
  {
    this.mailSentTime = mailSentTime;
  }
  
  @Column(name="status")
  public Boolean getStatus()
  {
    return this.status;
  }
  
  public void setStatus(Boolean status)
  {
    this.status = status;
  }
  
  @Column(name="photoSessionId", length=45)
  public String getPhotoSessionId()
  {
    return this.photoSessionId;
  }
  
  public void setPhotoSessionId(String photoSessionId)
  {
    this.photoSessionId = photoSessionId;
  }
  
  @Column(name="publicUseAck", length=45)
  public String getPublicUseAck()
  {
    return this.publicUseAck;
  }
  
  public void setPublicUseAck(String publicUseAck)
  {
    this.publicUseAck = publicUseAck;
  }
  
  @Column(name="newsletterOptIn", length=45)
  public String getNewsletterOptIn()
  {
    return this.newsletterOptIn;
  }
  
  public void setNewsletterOptIn(String newsletterOptIn)
  {
    this.newsletterOptIn = newsletterOptIn;
  }
  
  @Column(name="fileName", length=45)
  public String getFileName()
  {
    return this.fileName;
  }
  
  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }
  
  @Column(name="renderVersion", length=45)
  public String getRenderVersion()
  {
    return this.renderVersion;
  }
  
  public void setRenderVersion(String renderVersion)
  {
    this.renderVersion = renderVersion;
  }
  
  @Column(name="share", length=45)
  public String getShare()
  {
    return this.share;
  }
  
  public void setShare(String share)
  {
    this.share = share;
  }
  
  @Column(name="downloadStatus")
  public Integer getDownloadStatus()
  {
    return this.downloadStatus;
  }
  
  public void setDownloadStatus(Integer downloadStatus)
  {
    this.downloadStatus = downloadStatus;
  }
}
