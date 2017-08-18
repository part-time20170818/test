package com.wg.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	
	private static Properties prop;
	
	// 读取配置文件
	static {
		prop = new Properties();
		try {
			InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("rock.properties");
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getString(String propt) {
		return prop.getProperty(propt).trim();
	}
	
	public static void main(String[] args) {
		PropertiesUtils.getString("");
	}
}
