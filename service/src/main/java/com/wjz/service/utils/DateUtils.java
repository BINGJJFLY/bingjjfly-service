package com.wjz.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <b>日期工具类</b>
 * 
 * @author iss002
 *
 */
public abstract class DateUtils {

	private static final TimeZone ASIA_SHANGHAI = TimeZone.getTimeZone("Asia/Shanghai"); // 上海时区
	private static final Locale LOCALE_CHINA = Locale.CHINA; // 中国地区

	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
	private static final String DEFAULT_COMPLETE_TIME_PATTERN = "HH:mm:ss.SSS";
	private static final String DEFAULT_DATE_TIME_PATTERN = DEFAULT_DATE_PATTERN + " " + DEFAULT_TIME_PATTERN;
	private static final String DEFAULT_COMPLETE_DATE_TIME_PATTERN = DEFAULT_DATE_PATTERN + " "
			+ DEFAULT_COMPLETE_TIME_PATTERN;

	private static final String DEFAULT_DATE_VALUE_PATTERN = "yyyyMMdd";
	private static final String DEFAULT_TIME_VALUE_PATTERN = "HHmmss";
	private static final String DEFAULT_COMPLETE_TIME_VALUE_PATTERN = "HHmmssSSS";
	private static final String DEFAULT_DATE_TIME_VALUE_PATTERN = DEFAULT_DATE_VALUE_PATTERN
			+ DEFAULT_TIME_VALUE_PATTERN;
	private static final String DEFAULT_COMPLETE_DATE_TIME_VALUE_PATTERN = DEFAULT_DATE_VALUE_PATTERN
			+ DEFAULT_COMPLETE_TIME_VALUE_PATTERN;
	private static final Collection<String> DEFAULT_PATTERNS = Arrays
			.asList(new String[] { DEFAULT_DATE_PATTERN, DEFAULT_TIME_PATTERN, DEFAULT_COMPLETE_TIME_PATTERN,
					DEFAULT_DATE_TIME_PATTERN, DEFAULT_COMPLETE_DATE_TIME_PATTERN, DEFAULT_DATE_VALUE_PATTERN,
					DEFAULT_TIME_VALUE_PATTERN, DEFAULT_COMPLETE_TIME_VALUE_PATTERN, DEFAULT_DATE_TIME_VALUE_PATTERN,
					DEFAULT_COMPLETE_DATE_TIME_VALUE_PATTERN });

	public static final int SECONDS_PER_MINUTE = 60;
	public static final int MINUTES_PER_HOUR = 60;
	public static final int HOURS_PER_DAY = 24;
	public static final int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
	public static final long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;

	public static String format(Date date) {
		return format(date, DEFAULT_DATE_TIME_PATTERN);
	}

	public static String format(Date date, String pattern) {
		final SimpleDateFormat formatter = new SimpleDateFormat(pattern, LOCALE_CHINA);
		formatter.setTimeZone(ASIA_SHANGHAI);
		return formatter.format(date);
	}

	public static Date parse(String dateValue) throws DateParseException {
		Iterator<String> iterator = DEFAULT_PATTERNS.iterator();
		while (iterator.hasNext()) {
			String pattern = (String) iterator.next();
			try {
				return parse(dateValue, pattern);
			} catch (DateParseException e) {
				// ignore this exception, try the next format
			}
		}

		throw new DateParseException("Unable to parse the date " + dateValue);
	}

	public static Date parse(String dateValue, String pattern) throws DateParseException {
		final SimpleDateFormat dateParser = new SimpleDateFormat(pattern, LOCALE_CHINA);
		try {
			return dateParser.parse(dateValue);
		} catch (ParseException e) {
			// ignore
		}

		throw new DateParseException("Unable to parse the date " + dateValue);
	}

	/**
	 * 设置 {@code HH:MM:SS.SSS } 为 {@code 00:00:00.000 }
	 * 
	 * @param date
	 * @return
	 */
	public static Date dayStart(Date date) {
		final Calendar calendar = Calendar.getInstance(ASIA_SHANGHAI, Locale.CHINA);
		calendar.setTime(date);
		return dayStart(calendar).getTime();
	}

	private static Calendar dayStart(final Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 设置 {@code HH:MM:SS.SSS} 为 {@code 23:59:59.999 }
	 * 
	 * @param date
	 * @return
	 */
	public static Date dayEnd(Date date) {
		final Calendar calendar = Calendar.getInstance(ASIA_SHANGHAI, Locale.CHINA);
		calendar.setTime(date);
		return dayEnd(calendar).getTime();
	}

	private static Calendar dayEnd(final Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar;
	}

	/**
	 * 根据日历规则添加或减去指定的天数
	 * 
	 * @param date
	 * @param amount
	 *            指定的天数
	 * @return
	 */
	public static Date addOrSubtractDay(Date date, int amount) {
		final Calendar calendar = Calendar.getInstance(ASIA_SHANGHAI, Locale.CHINA);
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		return calendar.getTime();
	}

	/**
	 * 根据日历规则添加1天
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextOneDay(Date date) {
		return addOrSubtractDay(date, 1);
	}

	/**
	 * 根据日历规则减去1天
	 * 
	 * @param date
	 * @return
	 */
	public static Date lastOneDay(Date date) {
		return addOrSubtractDay(date, -1);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int cycle(Date before, Date after) {
		long beforeTime = dayStart(before).getTime();
		long afterTime = dayStart(after).getTime();
		long newer = beforeTime >= afterTime ? beforeTime : afterTime;
		long older = beforeTime >= afterTime ? afterTime : beforeTime;

		int days = (int) ((newer - older) / (DAY_MILLISECONDS));
		return days;
	}

	@SuppressWarnings("serial")
	private static final class DateParseException extends ParseException {

		public DateParseException(String message) {
			super(message, 0);
		}
	}

}
