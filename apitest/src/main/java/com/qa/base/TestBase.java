package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.qa.test.a;
import com.qa.test.phone;

public class TestBase {
	
	public static Properties prop;
	public static File file;
	public static FileReader reader;
	public static FileInputStream ip;
	public static int Response_status_Code_200=200;
	public int Response_status_Code_201=201;
	public int Response_status_Code_400=400;
	public int Response_status_Code_401=401;
	public int Response_status_Code_500=500;
	public int Response_status_Code_422=422;
	public int Response_status_Code_404=404;
	
	
	
		
	public TestBase() throws IOException {
		
			prop=new Properties();
			ip= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);	
		
	}
	
	
}


