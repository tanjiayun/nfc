package com.tjy.nfc;



import com.tjy.nfc.utils.AnalyzeUtil;
import com.tjy.nfc.utils.MD5Util;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class MyTest {

	public static void main(String[] args) throws Exception {

//		AnalyzeUtil.getDays("2020-11-16 14:59:27","2020-11-17 00:59:27" );
		System.out.println(AnalyzeUtil.getTotalTimes(13L,"2次/周"));



	}
}
