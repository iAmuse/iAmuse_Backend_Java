package com.iamuse.admin.util;

public class HighChartDataUtils {

	
	public static boolean isLeapYear(int year) {
		  if (year % 4 != 0) {
		    return false;
		  } else if (year % 400 == 0) {
		    return true;
		  } else if (year % 100 == 0) {
		    return false;
		  } else {
		    return true;
		  }
		}
	
	public static void main(String [] args){
		System.out.println(isLeapYear(2015));
		System.out.println(dateFormat("01-02-2016"));
	}
	
	public static String makingEndDate(int month,int year){
		String endDate="";
		switch (month) {
		case 1:
			endDate="31-"+month+"-"+year;
			break;
			
		case 2:
			 if(isLeapYear(year)){
				 endDate="29-"+month+"-"+year;
			 }else{
				 endDate="28-"+month+"-"+year;
			 }
			break;
		case 3:
			endDate="31-"+month+"-"+year;
			break;
		case 4:
			endDate="30-"+month+"-"+year;
			break;
			
		case 5:
			endDate="31-"+month+"-"+year;
			break;
			
		case 6:
			endDate="30-"+month+"-"+year;
			break;
		case 7:
			endDate="31-"+month+"-"+year;
			break;
		case 8:
			endDate="31-"+month+"-"+year;
			break;
		case 9:
			endDate="30-"+month+"-"+year;
			break;
		case 10:
			endDate="31-"+month+"-"+year;
			break;
		case 11:
			endDate="30-"+month+"-"+year;
			break;
		case 12:
			endDate="31-"+month+"-"+year;
			break;
		default:
			break;
		}
		return endDate;
	}
	
	
	public static String dateFormat(String stringValue){
	String stringArray[]=stringValue.split("-");
	stringValue=stringArray[2].toString()+"-"+stringArray[1].toString()+"-"+stringArray[0].toString();
		return stringValue;
	}
	
	
	public static String dispalyMonthName(int dateTextMonth){
		String monthName="";
		switch (dateTextMonth) {
		case 1:
			monthName="Jan";
			break;
			
		case 2:
				 monthName="Feb";
			break;
		case 3:
			monthName="Mar" ;
			break;
		case 4:
			monthName="Apr" ;
			break;
			
		case 5:
			monthName="May" ;
			break;
			
		case 6:
			monthName="Jun" ;
			break;
		case 7:
			monthName="July" ;
			break;
		case 8:
			monthName="Aug" ;
			break;
		case 9:
			monthName="Sep" ;
			break;
		case 10:
			monthName="Oct" ;
			break;
		case 11:
			monthName="Nov" ;
			break;
		case 12:
			monthName="Dec" ;
			break;
		default:
			break;
		}
		return monthName;
	}
	
	public static String makingStartDate(int month,int year){
		String endDate="";
		switch (month) {
		case 1:
			endDate="01-"+month+"-"+year;
			break;
			
		case 2:
			 if(isLeapYear(year)){
				 endDate="01-"+month+"-"+year;
			 }else{
				 endDate="01-"+month+"-"+year;
			 }
			break;
		case 3:
			endDate="01-"+month+"-"+year;
			break;
		case 4:
			endDate="01-"+month+"-"+year;
			break;
			
		case 5:
			endDate="01-"+month+"-"+year;
			break;
			
		case 6:
			endDate="01-"+month+"-"+year;
			break;
		case 7:
			endDate="01-"+month+"-"+year;
			break;
		case 8:
			endDate="01-"+month+"-"+year;
			break;
		case 9:
			endDate="01-"+month+"-"+year;
			break;
		case 10:
			endDate="01-"+month+"-"+year;
			break;
		case 11:
			endDate="01-"+month+"-"+year;
			break;
		case 12:
			endDate="01-"+month+"-"+year;
			break;
		default:
			break;
		}
		return endDate;
	}
}
