package com.iamuse.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="background_images", catalog="iamuse_internal")
public class BackgroundImages
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="idpic", unique=true, nullable=false)
  int idpic;
  @Column(name="picinfo")
  String picinfo;
  @Column(name="mask_info")
  String mask_info;
  @Column(name="scale_x_offset")
  String scaleXOffset;
  @Column(name="scale_y_offset")
  String scaleYOffset;
  @Column(name="scale_z_offset")
  String scaleZOffset;
  @Column(name="scalingWidth")
  String scalingWidth;
  @Column(name="scalingHeight")
  String scalingHeight;
  
  public int getIdpic()
  {
    return this.idpic;
  }
  
  public void setIdpic(int idpic)
  {
    this.idpic = idpic;
  }
  
  public String getPicinfo()
  {
    return this.picinfo;
  }
  
  public void setPicinfo(String picinfo)
  {
    this.picinfo = picinfo;
  }
  
  public String getMask_info()
  {
    return this.mask_info;
  }
  
  public void setMask_info(String mask_info)
  {
    this.mask_info = mask_info;
  }
  
  public String getScaleXOffset()
  {
    return this.scaleXOffset;
  }
  
  public void setScaleXOffset(String scaleXOffset)
  {
    this.scaleXOffset = scaleXOffset;
  }
  
  public String getScaleYOffset()
  {
    return this.scaleYOffset;
  }
  
  public void setScaleYOffset(String scaleYOffset)
  {
    this.scaleYOffset = scaleYOffset;
  }
  
  public String getScaleZOffset()
  {
    return this.scaleZOffset;
  }
  
  public void setScaleZOffset(String scaleZOffset)
  {
    this.scaleZOffset = scaleZOffset;
  }
  
  public String getScalingWidth()
  {
    return this.scalingWidth;
  }
  
  public void setScalingWidth(String scalingWidth)
  {
    this.scalingWidth = scalingWidth;
  }
  
  public String getScalingHeight()
  {
    return this.scalingHeight;
  }
  
  public void setScalingHeight(String scalingHeight)
  {
    this.scalingHeight = scalingHeight;
  }
}
