package com.spark.base.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * <p>
 * 日期工具类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author yl
 * @version 2011-6-29
 */
public class DateUtil {

	private final static String EMPTY = "-";
	private final static String DEFAULT_PATTERN = "yyyy-MM-dd";

	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd hh:mm";

	public final static String DATE_TIME_PATTERN2 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 一天的毫秒数
	 */
	public final static long ONE_DATE_TIME = 24 * 60 * 60 * 1000l;

	/**
	 * 把long类型的日期转成指定格式pattern的字符串返回
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String dateFromat(long time, String pattern) {
		if (time > 0) {
			Date thisDate = new Date(time);
			String p = DEFAULT_PATTERN;
			if (CheckIsNull.isNotEmpty(pattern)) {
				p = pattern;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(p);
			return sdf.format(thisDate);
		}
		return EMPTY;
	}

	/**
	 * 把long类型的日期转成"yyyy-MM-dd"的字符串返回
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String dateFromat(long time) {
		if (time > 0) {
			Date thisDate = new Date(time);
			String p = DEFAULT_PATTERN;
			SimpleDateFormat sdf = new SimpleDateFormat(p);
			return sdf.format(thisDate);
		}
		return EMPTY;
	}

	/**
	 * 把long类型的日期转成"yyyy-MM-dd"的字符串返回
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String dateFromat(Date thisDate) {
		if (null != thisDate) {
			String p = DEFAULT_PATTERN;
			SimpleDateFormat sdf = new SimpleDateFormat(p);
			return sdf.format(thisDate);
		}
		return EMPTY;
	}

	/**
	 * 把long类型的日期转成"yyyy-MM-dd"的字符串返回
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String dateFromat(Date thisDate, String pattern) {
		if (thisDate != null) {
			String p = DEFAULT_PATTERN;
			if (CheckIsNull.isNotEmpty(pattern)) {
				p = pattern;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(p);
			return sdf.format(thisDate);
		}
		return EMPTY;
	}

	/**
	 * 新历转成农历日期
	 * 
	 * @return
	 */
	public static long new2OldDate(long newDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(newDate);
		Date time = ChineseCalendar.sCalendarSolarToLundar(cal
				.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal
				.get(Calendar.DAY_OF_MONTH));
		return time.getTime();
	}

	/**
	 * 农历转新历
	 * 
	 * @param oldDate
	 * @return
	 */
	public static long old2NewDate(long oldDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(oldDate);
		Date time = ChineseCalendar.sCalendarLundarToSolar(cal
				.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal
				.get(Calendar.DAY_OF_MONTH));
		return time.getTime();
	}

	/**
	 * 将形如2003-2-2的时间字符串转换为 Date对象
	 * 
	 * @param dateStr
	 * @return long
	 */
	public static Date convertStringToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将形如2003-2-2的时间字符串转换为 Date对象，如果字符串格式不正确，返回当前时间对象
	 * 
	 * @param dateStr
	 * @return long
	 */
	public static Date convertStringToDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期取整函数<Br>
	 * 将指定日期从指定的时间单位处开始取整，如将2011-11-1 15:30 抹去时间 获得 2011-11-1 00:00
	 * 
	 * 
	 * @param time
	 * @param field
	 *            接受值为java.util.Calendar的常量 Calendar.DATE 从天数处开始取整
	 *            Calendar.MONTH 从月份处开始取整 2011-5-3 返回 2011-5-1 Calendar.YEAR
	 *            从年份处开始取整 Calendar.HOUR 从小时取整
	 * @return long
	 */
	public static long trunc(long time, int field) {
		String format = "yyyyMMdd 00:00";
		switch (field) {
		case Calendar.DATE:
			break;
		case Calendar.MONTH:
			format = "yyyyMM";
			break;
		case Calendar.YEAR:
			format = "yyyy";
			break;
		case Calendar.HOUR:
			format = "yyyyMMdd hh:00";
			break;
		default:
			throw new IllegalArgumentException("日期取整时参数错误：没有" + field
					+ "这种类型。常量值请参考java.util.Calendar");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(sdf.format(time)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return time;
		}
	}

	/**
	 * 将指定时间对象的小时分钟信息归零
	 * 
	 * @param time
	 * @return long
	 */
	public static long truncDay(long time) {
		return DateUtil.trunc(time, Calendar.DATE);
	}

	/**
	 * 得到指定日的起始时间（0时0分0秒）
	 * 
	 * @param date
	 * @return
	 */
	public static long getDayStartTime(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTimeInMillis();
	}

	/**
	 * 得到指定日的结束时间（23时59分59秒...）
	 * 
	 * @param date
	 * @return
	 */
	public static long getDayEndTime(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTimeInMillis() + 24 * 60 * 60 * 1000 - 1;
	}

	/**
	 * 获取本月第一天日期
	 * 
	 * @return
	 */
	public static Date getThisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}
	
	/**
	 * 获取本月最后一天日期
	 * 
	 * @return
	 */
	public static Date getLastDayOfThisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE,  - 1 );
		return cal.getTime();
	}

	/**
	 * 获取今天开始的时间
	 * 
	 * @return
	 */
	public static long getToday() {
		return truncDay(System.currentTimeMillis());
	}

	/**
	 * 判断两个日期是否跨年
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkIsNewYear(long beginDate, long endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(beginDate);
		int beginYear = cal.get(Calendar.YEAR);
		cal.setTimeInMillis(endDate);
		int endYear = cal.get(Calendar.YEAR);
		return beginYear != endYear;
	}

	/**
	 * 传入当前日期，格式是yyyy-mm-dd
	 * 
	 * @param date
	 * @return 返回传入日期是当前的今年某个月的某个星期 格式为:yyyymmww
	 */
	public static String getDayofWeekofMonth(String time) {
		// String time = "2011-12-02";
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
				/*
				 * System.out.println("本周期末时间： <=" + new
				 * SimpleDateFormat("yyyyMMdd").format(calendar .getTime()));
				 */
				/*
				 * calendar.set(Calendar.DAY_OF_YEAR, calendar
				 * .get(Calendar.DAY_OF_YEAR) - 7); System.out.println("本周期初时间：<=" +
				 * new SimpleDateFormat("yyyyMMdd").format(calendar
				 * .getTime()));
				 */
				return new SimpleDateFormat("yyyyMMdd").format(calendar
						.getTime());
			} else {
				while (calendar.get(Calendar.DAY_OF_WEEK) < 7) {
					calendar.set(Calendar.DAY_OF_WEEK, calendar
							.get(Calendar.DAY_OF_WEEK) + 1);
				}
				calendar.set(Calendar.DAY_OF_YEAR, calendar
						.get(Calendar.DAY_OF_YEAR) + 1);
				/*
				 * System.out.println("本周期末时间： <=" + new
				 * SimpleDateFormat("yyyyMMdd").format(calendar .getTime()));
				 * calendar.set(Calendar.DAY_OF_YEAR, calendar
				 * .get(Calendar.DAY_OF_YEAR) - 7); System.out.println("本周期初时间：<=" +
				 * new SimpleDateFormat("yyyyMMdd").format(calendar
				 * .getTime()));
				 */
				return new SimpleDateFormat("yyyyMMdd").format(calendar
						.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;

		/*
		 * Calendar c_now = new GregorianCalendar(); Calendar c_begin = new
		 * GregorianCalendar(); Calendar c_end = new GregorianCalendar();
		 * DateFormatSymbols dfs = new DateFormatSymbols(); SimpleDateFormat df =
		 * new SimpleDateFormat("yyyy-M-dd"); java.util.Date cDate = null; try {
		 * cDate = df.parse(date); } catch (ParseException e) {
		 * e.printStackTrace(); } c_now.setTime(cDate); int year =
		 * c_now.get(Calendar.YEAR); int month = c_now.get(Calendar.MONTH) + 1;
		 * int today = c_now.get(Calendar.DAY_OF_MONTH);
		 * System.out.println(today); int[] days = { 0, 31, 28, 31, 30, 31, 30,
		 * 31, 31, 30, 31, 30, 31 }; if (year % 4 == 0) { days[2] = 29;// 大年 }
		 * c_begin.set(2010, month - 1, 1); // 月 0-11 天 0- c_end.set(2010, month -
		 * 1, days[month]);
		 * 
		 * int count = 1; c_end.add(Calendar.DAY_OF_YEAR, 1); //
		 * 结束日期下滚一天是为了包含最后一天 int day = c_now.get(Calendar.DAY_OF_WEEK) - 1;
		 * while (c_begin.before(c_end)) { if (day == 8) { count++; day = 1; }
		 * 
		 * if (today == c_begin.get(Calendar.DAY_OF_MONTH)) {
		 * //System.out.println(df.format(c_begin.getTime()) + "是" + month //+
		 * "月第" + count + "周的第" + day + "天"); String returnmonth =
		 * (c_begin.get(Calendar.YEAR)+1) + "" +
		 * (c_begin.get(Calendar.MONTH)+1); Date date2; String returnvalue = "";
		 * try { date2 = new SimpleDateFormat("yyyyMM").parse(returnmonth);
		 * returnvalue = new SimpleDateFormat("yyyyMM").format(date2)+ "0" +
		 * count; } catch (ParseException e) { e.printStackTrace(); } return
		 * returnvalue ; } day++; // System.out.println("第"+count+"周 日期："+new //
		 * java.sql.Date(c_begin. getTime().getTime())+",
		 * "+weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
		 * 
		 * c_begin.add(Calendar.DAY_OF_YEAR, 1); }
		 * //System.out.println("共计（跨越）：" + (count) + "周"); return null;
		 */
	}

	/**
	 * 
	 * @param date
	 *            当前日期
	 * @return 得到当前月日期所属季度
	 */
	public static int getCurrQuarter(String str) {
		String s = "";
		if (null != str) {
			String da = str.substring(4, 6);
			String yy = str.substring(0, 4);
			int m = Integer.valueOf(da);
			int cs = (m - 1) / 3 + 1;
			s = yy + String.valueOf(cs);

		}
		return Integer.parseInt(s);
	}

	/**
	 * 得到当前月份
	 * 
	 * @param str
	 * @return
	 */
	public static int getMonth(String str) {
		int reValue = 0;
		if (null != str) {
			String s = str.substring(0, 6);
			reValue = Integer.parseInt(s);
		}
		return reValue;
	}

	/**
	 * 得到上一个星期的日期
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Calendar getBeforeWeekDay(String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.get(Calendar.DAY_OF_YEAR) - 7);
		return calendar;
	}

	/**
	 * 得到两个日期的间隔天数
	 */
	public static long getDaysBetween(Date startDate, Date endDate) {
		long days = 0;
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);
		days = ((toCalendar.getTime().getTime() - fromCalendar.getTime()
				.getTime()) / (1000 * 60 * 60 * 24));
		return days;
	}

	/**
	 * 判断指定日期是否为当天
	 */
	public static boolean isCurrentDay(long date) {
		return getDayStartTime(new Date().getTime()) <= date
				&& getDayEndTime(new Date().getTime()) >= date;
	}

	/**
	 * 获取时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static String getHHMMSS(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String hh = c.get(Calendar.HOUR_OF_DAY)<10?("0"+c.get(Calendar.HOUR_OF_DAY)):(c.get(Calendar.HOUR_OF_DAY)+"");
		String mm = c.get(Calendar.MINUTE)<10?("0"+c.get(Calendar.MINUTE)):(c.get(Calendar.MINUTE)+"");
		String ss = c.get(Calendar.SECOND)<10?("0"+c.get(Calendar.SECOND)):(c.get(Calendar.SECOND)+"");
		return hh+":"+mm+":"+ss;

	}

	/**
	 * 获取上月的今天
	 */
	public static Date getTodayOfLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取明天
	 */
	public static String getTomorrow(Date date) {
		if (CheckIsNull.isEmpty(date))
			date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, +24);
		return dateFromat(calendar.getTime());
	}
	
	/**
	 * 获取后天
	 */
	public static String getTheDayAfterTomorrow(Date date) {
		if (CheckIsNull.isEmpty(date))
			date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, +48);
		return dateFromat(calendar.getTime());
	}
	
	/**
	 * 获取半小时前时间
	 */
	public static long getHalfHourAgo(Date date)
	{
		if (CheckIsNull.isEmpty(date))
			date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, -(60*30));
		return calendar.getTimeInMillis();
	}
}
