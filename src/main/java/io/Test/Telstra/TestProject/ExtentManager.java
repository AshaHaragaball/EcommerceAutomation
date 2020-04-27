package io.Test.Telstra.TestProject;



import java.io.File;
import java.util.Date;

//import org.apache.commons.mail.EmailAttachment;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.MultiPartEmail;
//import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;
	public static String screenshotFolderPath;
//
	public static ExtentReports getInstance()  {
		Date d = new Date();
		String folderName = d.toString().replace(":", "_").replace(" ", "_");

		new File("./target/Reports/" + folderName + "//Failed_TestCases_Screenshots").mkdirs();
		screenshotFolderPath = System.getProperty("user.dir") + "/target/Reports/" + folderName
				+ "/Failed_TestCases_Screenshots//";
		if (extent == null) {
			createInstance(System.getProperty("user.dir") + "/target/reports/" + folderName
					+ "/Telstra_TestSuite_Report.html");

		}
		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;

	}

	
}
