package com.talentscreen.base;

import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import com.talentscreen.helper.ConfigUtils;

public class BaseApiTest {
	protected String endPoint;
	@BeforeSuite
	public void beforeSuit(){
		Properties prop= ConfigUtils.loadConfigFile("config.properties");
		endPoint= prop.getProperty("TSurl");
	}

}
