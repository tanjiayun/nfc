package com.tjy.nfc.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalyzeUtil {
	public static Long getDays(String beginTime,String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(("yyyy-MM-dd"));
		Date beginDate = sdf.parse(beginTime);
		Date endDate = sdf.parse(endTime);
		long diff = endDate.getTime() - beginDate.getTime();//这样得到的差值是微秒级别
		long days = diff / (1000 * 60 * 60 * 24);
		System.out.println(days);
		return days;
	}

	public static  Long getTotalTimes(Long days, String times) {
		if(StringUtils.equals("1次/日",times)){
			return days*1;
		}else  if(StringUtils.equals("2次/日",times)){
			return days*3;
		}else if(StringUtils.equals("3次/日",times)){
			return days*3;
		}else if(StringUtils.equals("4次/日",times)){
			return days*3;
		}else if(StringUtils.equals("5次/日",times)){
			return days*3;
		}else  if(StringUtils.equals("1次/周",times)){
			return days/7;
		}else  if(StringUtils.equals("2次/周",times)){
			return days/7*2;
		}else  if(StringUtils.equals("3次/周",times)){
			return days/7*3;
		}else  if(StringUtils.equals("4次/周",times)){
			return days/7*4;
		}else  if(StringUtils.equals("5次/周",times)){
			return days/7*5;
		}
		return days*0;
	}
}
