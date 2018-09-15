package com.wjz.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.httpclient.util.DateUtil;

public class TimeDemo {
	
	private static final TimeZone ASIA_SHANGHAI = TimeZone.getTimeZone("Asia/Shanghai");

	public static void main(String[] args) {
		String time = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(time);
		time = formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(time);
		TimeZone timezone = TimeZone.getDefault();
		System.out.println(timezone.getID());
		
		System.out.println("===================================");
		TimeZone timeZone = TimeZone.getDefault();
		System.out.println(timeZone);
		System.out.println(timeZone.getDisplayName());
		System.out.println(timeZone.getID());
		System.out.println(timeZone.getOffset( System.currentTimeMillis()));
	}
	
	public static String formatDate(Date date, String pattern) {
        if (date == null) throw new IllegalArgumentException("date is null");
        if (pattern == null) throw new IllegalArgumentException("pattern is null");
        
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.CHINA);
        formatter.setTimeZone(ASIA_SHANGHAI);
        return formatter.format(date);
    }
}
