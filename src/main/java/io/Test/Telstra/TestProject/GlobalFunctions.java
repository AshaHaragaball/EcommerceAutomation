package io.Test.Telstra.TestProject;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.Keys;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class GlobalFunctions {
	public static WebDriver driver;
	public static ReadProperties rp;
	public static ExtentTest test;
	public static WebElement e;
	public static int Read_only_val;

	public void setTest(ExtentTest test) {
		GlobalFunctions.test = test;
	}

	/**************************************************************************************************************/
	/* Method to instantiate browser */
	public static void openBrowser() throws IOException {
		rp = new ReadProperties();
		String browser = rp.getData("browser");
		String url = rp.getData("url");		
		test.log(Status.INFO, "Opening Browser: " + browser);
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");	
			driver = new ChromeDriver();
			driver.manage().window().maximize();	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		default:
			break;
		}
		test.log(Status.INFO, "Opening URL: " + url);
		driver.get(url);
	}

	/**************************************************************************************************************/
	

	public static void waitForWebElementForXpath(String locValue) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locValue)));
			
		} catch (Exception ex) {
			test.log(Status.FAIL, "Element not found");
			ex.printStackTrace();
		}
	}


	/* Method waits for an element with an implement by passing locator values */

	public static void waitForWebElement(WebElement e) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			boolean flag = e.isEnabled();
			wait.until(ExpectedConditions.elementToBeClickable(e));
		} catch (Exception ex) {
			test.log(Status.FAIL, "Element not found");
			ex.printStackTrace();
		}
	}

	/**************************************************************************************************************/
	/* Method get webelement by passing locator values */

	public static WebElement getWebelement(String locType, String locValue, String testCaseName) {

		try {
			switch (locType.trim()) {
			case "id":
				e = driver.findElement(By.id(locValue));
				waitForWebElement(e);
				break;
			case "xpath":
				 e = driver.findElement(By.xpath(locValue));
				 waitForWebElement(e);
				break;
			case "name":
				e = driver.findElement(By.name(locValue));
				waitForWebElement(e);
				break;
			}
		} catch (Exception ex) {
			reportFailure(ex.getMessage(), testCaseName);
			ex.printStackTrace();
		}
		return e;
	}

	/**************************************************************************************************************/
	/* Method to click on an element */

	public static void click(String locType, String locValue, String testCaseName) {
		try {
			test.log(Status.INFO, "Clicking on webelment: " + locType + "=" + locValue);
			Thread.sleep(10000);
			getWebelement(locType, locValue, testCaseName).click();

			test.log(Status.PASS, "Click operation passed");
		} catch (Exception ex) {
			reportFailure("Unable to Click on the element", testCaseName);
			ex.printStackTrace();
		}

	}
	
	public static void click_specific(String locType, String locValue, String testCaseName) {
		try {
			Robot r = new Robot();
			test.log(Status.INFO, "Clicking on webelment: " + locType + "=" + locValue);
			Thread.sleep(10000);
			getWebelement(locType, locValue, testCaseName).sendKeys(Keys.ENTER);
			r.keyPress(KeyEvent.VK_ENTER);

			test.log(Status.PASS, "Click operation passed");
		} catch (Exception ex) {
			reportFailure("Unable to Click on the element", testCaseName);
			ex.printStackTrace();
		}

	}

	/**************************************************************************************************************/
	/* Method to enter text to a textbox */

	public static void enterText(String locType, String locValue, String data, String testCaseName) {
		try {
			test.log(Status.INFO, "Entering " + data + " into webelement: " + locType + "=" + locValue);
			getWebelement(locType, locValue, testCaseName).clear();
			Thread.sleep(500);
			getWebelement(locType, locValue, testCaseName).sendKeys(data.trim());
			Thread.sleep(500);

		} catch (Exception ex) {
			reportFailure("Unable to enter text  on the element", testCaseName);
			ex.printStackTrace();
		}

	}




	/**************************************************************************************************************/
	/* Method to close the Browser */
	public static void close() {

		if (driver != null)
			driver.quit();
	}

	/**************************************************************************************************************/
	/* Method for wait */
	public static void wait(String data) throws Exception {
		int time = Integer.parseInt(data);
		Thread.sleep(time);
	}

	/**************************************************************************************************************/
	/* Method to take a screenshot */
	public static void takeScreenshot(String testCaseName) {
		Date d = new Date();
		String screenshotFileName = testCaseName + ".png";
		String screenshotFolderPath = ExtentManager.screenshotFolderPath;
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File(screenshotFolderPath + screenshotFileName));
			test.log(Status.INFO,
					"screenshot ->" + test.addScreenCaptureFromPath(screenshotFolderPath + screenshotFileName));
		} catch (IOException ex) {
			test.log(Status.INFO, "Unable to take screenshot");
			ex.printStackTrace();
		}
	}

	/**************************************************************************************************************/
	/* Method to report failures to Event Reports */

	public static void reportFailure(String failureMessage, String testCaseName) {
		test.log(Status.FAIL, failureMessage);
		takeScreenshot(testCaseName);
		Assert.fail(failureMessage);
	}



	public static String GetCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}


}
		
	
	

	
	


	
	
	




