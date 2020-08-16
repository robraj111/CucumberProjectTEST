package com.selenium.reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	

	static ExtentReports reports;
	public static String screenshotFolderPath;
	
	public static ExtentReports getReports() {
		if(reports == null) {
			reports = new ExtentReports();
			// init the report folder
			Date d = new Date();
			String reportFolder=d.toString().replaceAll(":", "-").replaceAll(" ", "-");
			String reportFolderPath = System.getProperty("user.dir") +"//reports//"+reportFolder;
			
			String screenFolder=reportFolder +"//screenshots";
			screenshotFolderPath = System.getProperty("user.dir") +"//reports//"+screenFolder;
			
			File f = new File(screenshotFolderPath);
			f.mkdirs();// create dynamic report folder name + screenshots
			
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath);
			sparkReporter.config().setReportName("Auto Regression Test Results");
			sparkReporter.config().setDocumentTitle("Automation Reports");
			sparkReporter.config().setTheme(Theme.STANDARD);
			sparkReporter.config().setEncoding("utf-8");
			
			reports.attachReporter(sparkReporter);
		}
		
		return reports;
		
	}

}



