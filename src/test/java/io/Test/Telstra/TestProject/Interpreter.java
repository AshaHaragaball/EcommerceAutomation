package io.Test.Telstra.TestProject;


import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.Test.Telstra.TestProject.Base;
import io.Test.Telstra.TestProject.GlobalFunctions;
import io.Test.Telstra.TestProject.Constants;



public class Interpreter extends Base {
	

	@Test(dataProvider = "getExcel")
		
	public void executeKeywords(String testCaseName, String summary, String mode) throws Exception {
		test = reports.createTest(testCaseName);
		gf.setTest(test);
		test.log(Status.INFO, "Execution Started");
		if (mode.equalsIgnoreCase("n")) {
			test.log(Status.SKIP, "Testcase mode is set to no.");
			throw new SkipException("Testcase mode is set to no.");
		}

		int rows = re.getRowCount(testCaseName);

		for (int i = 2; i <= rows; i++) {
			String iterator = re.getCellData(testCaseName, Constants.keywords_col, i);
			String locType = re.getCellData(testCaseName, Constants.locType_col, i);			
			String locValue = re.getCellData(testCaseName, Constants.locValue_col, i);
			String data = re.getCellData(testCaseName, "data", i);
			if(iterator!=null)
			{

			switch (iterator) {
			case "openBrowser":
				GlobalFunctions.openBrowser();
				break;
			case "click":	
				Thread.sleep(3000);
				GlobalFunctions.click(locType, locValue, testCaseName);
				break;
			case "click_specific":	
				Thread.sleep(3000);
				GlobalFunctions.click(locType, locValue, testCaseName);
				break;
			case "enterText":
				GlobalFunctions.enterText(locType, locValue, data, testCaseName);
				break;
			case "wait":
				GlobalFunctions.wait(data);
				break;
			
			case "close":
				GlobalFunctions.close();
				break;
		
			 default:
			
				break;
			}
			}
		}

	}


}


