package com.iamuse.admin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PaypalAction {
	 

	 public static void main(String args[]) throws UnsupportedEncodingException{  
		 String s = "cmd=_notify-validate&mc_gross=10.00&protection_eligibility=Eligible&address_status=confirmed&payer_id=VWUWBKEMK8AM6&tax=0.00&address_street=1+Main+St&payment_date=05%3A40%3A14+Mar+22%2C+2017+PDT&payment_status=Completed&charset=utf-8&address_zip=95131&first_name=test&mc_fee=0.59&address_country_code=US&address_name=test+buyer&notify_version=3.8&custom=&payer_status=verified&business=samalpramod%40gmail.com&address_country=United+States&address_city=San+Jose&quantity=1&payer_email=java.manish.yadav-buyer%40gmail.com&verify_sign=AFcWxV21C7fd0v3bYYYRCpSSRl31AYjzvni3NHZ8BG25MCiHa48V6egd&txn_id=7FF998551N086622B&payment_type=instant&last_name=buyer&address_state=CA&receiver_email=samalpramod%40gmail.com&payment_fee=0.59&receiver_id=UMJVFFCBXQ59E&txn_type=web_accept&item_name=Basic&mc_currency=USD&item_number=2&residence_country=US&test_ipn=1&handling_amount=0.00&transaction_subject=&payment_gross=10.00&shipping=0.00&auth=ADLiYily1ZFPiMbidctOh-Sk1bM35jtkuabDEG0JqkK1fSWNS-7-961Mxv.DI.tLFzg6DZE4XoFOoGEeKJg2VXQ";
		 String[] words=s.split("&");//splits the string based on whitespace  
		 //using java foreach loop to print elements of string array 
		 for(String w:words){  
			 String[] word=w.split("=");
		if(word[0].equals("mc_gross")){
			System.out.println(word[1]);
		 }if(word[0].equals("protection_eligibility")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_status")){
			 System.out.println(word[1]);
		 }if(word[0].equals("payer_id")){
			 System.out.println(word[1]);
		 }if(word[0].equals("tax")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_street")){
			 System.out.println(word[1].replaceAll("\\+", " "));
		 }if(word[0].equals("payment_date")){
				String param2AfterDecoding = URLDecoder.decode(word[1], "UTF-8");
			 System.out.println(param2AfterDecoding);
		 }if(word[0].equals("payment_status")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_zip")){
			 System.out.println(word[1]);
		 }if(word[0].equals("first_name")){
			 System.out.println(word[1]);
		 }if(word[0].equals("mc_fee")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_country_code")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_name")){
			 System.out.println(word[1]);
		 }if(word[0].equals("notify_version")){
			 System.out.println(word[1]);
		 }if(word[0].equals("payer_status")){
			 System.out.println(word[1]);
		 }if(word[0].equals("business")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_country")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_city")){
			 System.out.println(word[1]);
		 }if(word[0].equals("quantity")){
			 System.out.println(word[1]);
		 }if(word[0].equals("payer_email")){
			 System.out.println(word[1]);
		 }if(word[0].equals("verify_sign")){
			 System.out.println(word[1]);
		 }if(word[0].equals("txn_id")){
			 System.out.println(word[1]);
		 }if(word[0].equals("payment_type")){
			 System.out.println(word[1]);
		 }if(word[0].equals("address_state")){
			 System.out.println(word[1]);
		 }if(word[0].equals("receiver_email")){
			 System.out.println(word[1]);
		 }if(word[0].equals("payment_fee")){
			 System.out.println(word[1]);
		 }if(word[0].equals("receiver_id")){
			 System.out.println(word[1]);
		 }if(word[0].equals("mc_currency")){
			 System.out.println(word[1]);
		 }if(word[0].equals("item_name")){
			 System.out.println(word[1]);
		 }if(word[0].equals("item_number")){
			 System.out.println(word[1]);
		 }if(word[0].equals("residence_country")){
			 System.out.println(word[1]);
		 }if(word[0].equals("handling_amount")){
			 System.out.println(word[1]);
		 }if(word[0].equals("payment_gross")){
			 System.out.println(word[1]);
		 }if(word[0].equals("auth")){
			 System.out.println(word[1]);
		 }if(word[0].equals("txn_type")){
			 System.out.println(word[1]);
		 }
		 }
		 }
 }
