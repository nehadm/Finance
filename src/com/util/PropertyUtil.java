package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	static Properties prop = new Properties();
	static FileInputStream input = null;

    public static void initProperties() {
    	try {
    		input = new FileInputStream("C:/Users/Neha/workspace/Finance/resources/db_properties");
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in PropertyUtil.initProperties()");
			e.printStackTrace();
		} 
    }
    public static String getResource(String key){
    	initProperties();
    	return prop.getProperty(key);
    }

}