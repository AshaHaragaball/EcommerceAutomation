package io.Test.Telstra.TestProject;

import java.io.File;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;


//import io.Test.Telstra.TestProject.GlobalFunctions;




import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

//import io.boral.ta.maximo.keywords.GlobalFunctions;
//import io.boral.ta.maximo.util.ExtentManager;
//import io.boral.ta.maximo.util.HashMapValues;
//import io.boral.ta.maximo.util.ReadExcel;

public class Base {
	public ReadExcelData re;
	public GlobalFunctions gf;
	public ExtentManager em;
	public ExtentReports reports;
	public ExtentTest test;
	
	

	@BeforeTest
	public void initialize()  {
				
		re = new ReadExcelData("src/main/resource/datasource.xlsx");
		gf = new GlobalFunctions();
		reports = ExtentManager.getInstance();

	}

	

	@BeforeMethod
	public void method1() {
	}

	@AfterMethod
	public void close() throws MalformedURLException {

		if (gf != null)
			GlobalFunctions.close();

		if (reports != null)
			reports.flush();
	}

	@DataProvider
	public String[][] getExcel() {
		
		return re.getExcel();
	}
	
}
