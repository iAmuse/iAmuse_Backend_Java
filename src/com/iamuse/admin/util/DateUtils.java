package com.iamuse.admin.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	 	static DateFormat currentDate = DateFormat.getDateInstance();
		private static String add30Days(){ 
		 Calendar c = Calendar.getInstance();
	     c.add(Calendar.DATE, 30);
	     Date d = c.getTime();
	     return currentDate.format(d);
	}
	public static String timeStampConvertIntoStringDateFormat(Date date){
		 Calendar createdDateCalender = Calendar.getInstance();
		 createdDateCalender.setTimeInMillis(date.getTime());
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String resultStringDate = dateFormat.format(createdDateCalender.getTime());
		return resultStringDate;
	}
	
	
	public static String timeStampConvertIntoStringDateFormatYYYYMMDD(Date date){
		 Calendar createdDateCalender = Calendar.getInstance();
		 createdDateCalender.setTimeInMillis(date.getTime());
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String resultStringDate = dateFormat.format(createdDateCalender.getTime());
		return resultStringDate;
	}
	
	public static String checkNull(String stringValue){
		if(stringValue!=null){
			stringValue=stringValue.replaceAll(" ", "");
			return stringValue;
		}
		return "";
	}
	
	public static final long START = System.nanoTime();

	
	public static void main(String[] args) throws ParseException{
		add30Days();
		//when receiving packet:
		
		
	}
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
}
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    
    public static java.sql.Timestamp getTimeStamp()
	{
		DateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  TimeZone gmtTime = TimeZone.getTimeZone("UTC");
		  gmtFormat.setTimeZone(gmtTime);
		  Timestamp ts = Timestamp.valueOf(gmtFormat.format(new Date()));
		  return ts;
	}
    
    public static Date getUTC() throws ParseException
	{
    	String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
		DateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		ZonedDateTime now = ZonedDateTime.now();
		String format1=DateTimeFormatter.ofPattern(DATEFORMAT).format(now);
		System.out.println("format1="+format1);		
		Date date = sdf.parse(format1);
		System.out.println("Date="+date);
		return date;
		  
	}
}
