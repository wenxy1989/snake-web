package com.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTools {
	public final static long SECOND_TIME = 1000;
	public final static long MINUTE_TIME = 60*SECOND_TIME;
	public final static long HOUR_TIME = 60*MINUTE_TIME;
	public final static long DAY_TIME = 24*HOUR_TIME;
	public final static long WEEK_TIME = 7*DAY_TIME;
	
	private static SimpleDateFormat getDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	private static SimpleDateFormat getTimeFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public static String now(){
		return getTimeFormat().format(new Date());
	}
	
	public static String yestodayTime(){
		Date lastTime = new Date((new Date()).getTime() - DAY_TIME);
		return getTimeFormat().format(lastTime);
	}
	
	public static String tomorromTime(){
		Date nextTime = new Date((new Date()).getTime() + DAY_TIME);
		return getTimeFormat().format(nextTime);
	}
	
	public static String today(){
		return getDateFormat().format(new Date());
	}
	
	public static String tomorrow(){
		SimpleDateFormat sdf = getDateFormat();
		Date tomorrow = null;
		try {
			tomorrow = new Date(sdf.parse(today()).getTime() + DAY_TIME);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(tomorrow);
	}
	
	public static String yestoday(){
		SimpleDateFormat sdf = getDateFormat();
		Date yestoday = null;
		try {
			yestoday = new Date(sdf.parse(today()).getTime() - DAY_TIME);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(yestoday);
	}
	
	public static String getTime(Date date){
		return getTimeFormat().format(date);
	}
	
	public static String getDate(Date date){
		return getDateFormat().format(date);
	}
	
	public static Date parse(String str) throws ParseException{
		Date date = null;
		if(null!=str){
			if(str.length() == 10){
				date = getDateFormat().parse(str);
			}else if(str.length() == 19){
				date = getTimeFormat().parse(str);
			}
		}
		return date;
	}
	

}
