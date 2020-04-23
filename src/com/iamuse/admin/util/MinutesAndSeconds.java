package com.iamuse.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MinutesAndSeconds {
	public static String minutesSeconds(String min, String sec,Integer total) {
		String minutes = null;
		String seconds = null;
		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(min);
			d2 = format.parse(sec);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60+30;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			minutes = String.valueOf(diffMinutes);
			long seconds1 = diffSeconds/total;
			seconds = String.valueOf(seconds1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return minutes + " " + seconds;
	}

}
