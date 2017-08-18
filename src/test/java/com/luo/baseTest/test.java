package com.luo.baseTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	
	
	public static void main(String[] args) throws ParseException {
		String dd= "20161211063322";
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		Date parse = s.parse(dd);
		SimpleDateFormat p = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String format = p.format(parse);
		System.out.println(parse);
		System.out.println(format);
	}

}
