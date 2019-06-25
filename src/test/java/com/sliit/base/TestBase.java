package com.sliit.base;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sliit.utilities.ExcelReader;
import com.sliit.utilities.ExtentManager;
import com.sliit.utilities.TestUtil;

/**
 * @author ArshadM
 *
 */
public class TestBase {

	/*
	 * WebDriver - done Properties - done Logs - log4j jar, .log, log4j.properties,
	 * Logger ExtentReports DB Excel Mail ReportNG, ExtentReports Jenkins
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static Actions action;

	/**
	 * @author ArshadM initiating file input streams initiating browser driver
	 *         inititating configurations initiating reports intitiating logs
	 */

	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 25);
			action = new Actions(driver);
		}

	}

	/**
	 * @author ArshadM Wrapper mehtod to click on an element
	 */

	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(OR.getProperty(locator)))).perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator)))).click();
			// driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on : " + locator.toString().replace("_XPATH", ""));
	}

	public static void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		test.log(LogStatus.INFO, "Clicking on : " + element.toString().replace("_XPATH", ""));
	}

	/**
	 * @author ArshadM Wrapper method to enter a value in textbox field
	 */
	public static void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {

			// wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			// action.moveToElement(driver.findElement(By.xpath(OR.getProperty(locator)))).sendKeys(value).build().perform();;;
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator)))).clear();

			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(locator))))).clear();
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO,
				"Typing in : " + locator.toString().replace("_XPATH", "") + " entered value as " + value);

	}

	/**
	 * @author ArshadM Method to enter a key
	 */

	public void type(String locator, Keys key) {

		if (locator.endsWith("_CSS")) {

			// wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(key);
		} else if (locator.endsWith("_XPATH")) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(locator)))));
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(key);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(key);
		}

		test.log(LogStatus.INFO, "Pressed the key: " + key + " in : " + locator.toString().replace("_XPATH", ""));

	}

	static WebElement dropdown;

	/**
	 * @author ArshadM Wrapper method for selecting a value from a drop down
	 */
	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO,
				"Selecting from dropdown : " + locator.toString().replace("_XPATH", "") + " value as " + value);

	}

	/**
	 * @author ArshadM Check if an element is present
	 */
	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	public static void verifyEqualsIgnoreCase(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase());
			test.log(LogStatus.INFO, "Verifying the expected text: " + expected);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}

	}

	/**
	 * @author ArshadM Verify two strings
	 */
	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);
			test.log(LogStatus.INFO, "Verifying the expected text: " + expected);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}

	}

	public static void verifyGreaterThan(int value, int shouldBeGreaterThanThis) throws IOException {

		try {

			boolean state = false;
			if (value > shouldBeGreaterThanThis) {
				state = true;
			}

			assertTrue(state);
			test.log(LogStatus.INFO, "Verified " + value + " is greater than " + shouldBeGreaterThanThis);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}
	}
	
	
	public static void verifyLessThan(int value, int shouldBeLessThanThis) throws IOException {

		try {

			boolean state = false;
			if (value < shouldBeLessThanThis) {
				state = true;
			}

			assertTrue(state);
			test.log(LogStatus.INFO, "Verified " + value + " is less than " + shouldBeLessThanThis);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}
	}
	
	

	public static void verifyContains(String text, String word) throws IOException {
		try {

			assertTrue(text.contains(word));
			test.log(LogStatus.INFO, "Asserting " + text + "contains: " + word);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Assertion failed for " + text + " with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}

	}

	public void verifyElementExists(String xpath) throws IOException {

		try {
			List<WebElement> element = driver.findElements(By.xpath(OR.getProperty(xpath)));
			int val = element.size();
			if (val > 0) {
				assertTrue(true);
				test.log(LogStatus.INFO, "Asserting element " + xpath + " exists");
			}

			else {
				assertTrue(false);
				test.log(LogStatus.INFO, "Asserting element " + xpath + " does not exist");
			}

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Assertion failed for " + xpath + " with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}
	}

	
	/**
	 * @author ArshadM Navigate to newly openened tab
	 * Verify the element is not available
	 */
	public void verifyElementExistNot(String xpath) throws IOException {

		try {
			List<WebElement> element = driver.findElements(By.xpath(OR.getProperty(xpath)));
			int val = element.size();
			if (val == 0) {
				assertTrue(true);
				test.log(LogStatus.INFO, "Asserting element " + xpath + " does not exist");
			}

			else {
				assertTrue(false);
				test.log(LogStatus.INFO, "Asserting element " + xpath + " exists");
			}

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Assertion failed for " + xpath + " with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}
	}
	
	
	
	/**
	 * @author ArshadM 
	 * Search
	 * @throws InterruptedException 
	 */
	public static void search(String search_box_xpath, String searchword) throws InterruptedException {
		
		type(search_box_xpath, searchword);

		driver.findElement(By.xpath(OR.getProperty(search_box_xpath))).sendKeys(Keys.ENTER);
		test.log(LogStatus.INFO, "Hitting enter key");
		
		Thread.sleep(3000);

	}

	
	
	/**
	 * @author ArshadM Navigate to newly openened tab
	 * Retrive the all the values in the given column into a list
	 * If there are more than 1 page, it would go to each page and read the values
	 */
	public static List<String> getColumnValues(int column_number) throws InterruptedException {

		String column=OR.getProperty("list_column_XPATH")+"["+column_number+"]";

		// Read listed elements in the first page into a list
		List<WebElement> list = driver.findElements(By.xpath(column));

		// Derive the codes in the elements and store in a seperate list
		List<String> codes = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			codes.add(list.get(i).getAttribute("innerText"));
		}

		if (list.size() > 10 | list.size()==9) {

		int	number_of_pages = Integer.parseInt(
					driver.findElement(By.xpath(OR.getProperty("page_count_XPATH"))).getAttribute("innerText"));

			// Do the above the remaining pages
			for (int j = 0; j < number_of_pages - 1; j++) {
				click("next_page_XPATH");

				Thread.sleep(3000);

				list = driver.findElements(By.xpath(column));

				for (int i = 0; i < list.size(); i++) {
					codes.add(list.get(i).getAttribute("innerText"));
				}

			}
			
			Thread.sleep(3000);
			click("first_page_XPATH");
			Thread.sleep(3000);

		}

		return codes;

	}
	
	
	/**
	 * @author ArshadM 
	 * Retrive the values for a given row.
	 * The return value will be an string consisting the column values sepearted by space
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	
	public static String getRowValues(int row_number) throws IOException, InterruptedException {
		
		//verifyGreaterThan(getRecordCountForTable(), 0);
		
		//First make sure there are rows in the table
		try {
			if (getRecordCountForTable() > 0)
				Assert.assertTrue(true);

			else
				assertTrue(false);
		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "No records found : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " No records found in the table : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
		
		
		String row=OR.getProperty("list_row_XPATH")+"["+row_number+"]";
		List<String> values = new ArrayList<String>();

		if(row_number <10 | row_number == 10) {
			
			List<WebElement> list= driver.findElements(By.xpath(row));
			
			for(int i=0;i<list.size();i++) {
				values.add(list.get(i).getAttribute("innerText"));
			}
		}
		
		return values.get(0);
		
	}
	
	
	
	
	

	
	
	/**
	 * @author ArshadM 
	 * View any row of a grid
	 * Provide the row number as a parameter
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void viewRow(int row_number) throws InterruptedException, IOException {
		
		//First make sure there are rows in the table

		try {
			if (getRecordCountForTable() > 0)
				Assert.assertTrue(true);

			else
				assertTrue(false);
		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "No records found : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " No records found in the table : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
		
		
		
		String xpath=OR.getProperty("row_ellipsis_XPATH")+"["+row_number+"]";
		//click(xpath);
		
		
		WebElement element = driver.findElement(By.xpath(xpath));
		click(element);
		click("view_row_XPATH");
		
		Thread.sleep(4000);

		
	}
	
	
	public static void verifyViewRowValues(String row_values) throws IOException, InterruptedException {
		
		
		String row=row_values;
		
	//	viewRow(5);
		
		Thread.sleep(3000);
		
		verifyEquals(row.split("\n")[0], getTextAttribute("lcnts_code_value_XPATH"));
	}
	
	
	
	/**
	 * @author ArshadM 
	 * Edit any row of a grid
	 * Provide the row number as a parameter
	 * @throws InterruptedException 
	 */
	public static void editRow(int row_number) throws InterruptedException {
		String xpath=OR.getProperty("row_ellipsis_XPATH")+"["+row_number+"]";
		
		//click(xpath);
		WebElement element = driver.findElement(By.xpath(xpath));
		click(element);
		click("edit_row_XPATH");
		
		Thread.sleep(4000);
	}
	
	
	/**
	 * @author ArshadM 
	 * Retrive the number of results returned from the pagination text
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static int getRecordCountForTable() throws InterruptedException, IOException {
		
		Thread.sleep(4000);
		
	
		 String result = driver.findElement(By.xpath(OR.getProperty("results_count_XPATH")))
				.getAttribute("innerText");
		 

		String array[] = result.split(" ");
		
		int count = Integer.parseInt(array[4]);
		
		return count;
	}
	
	
	/**
	 * @author ArshadM 
	 * This method will verify following:
	 * - Verify search results are more than 0
	 * - Read all the values in the given number of columns
	 * - Go through each row and make sure the search keyword is availale in any of the column
	 */
	public static void verifySearchResults(int how_many_columns, String search_keyword) throws IOException, InterruptedException {
		
	//	verifyGreaterThan(getRecordCountForTable(), 0);
		
		test.log(LogStatus.INFO, "Verifying Search keyword: " + search_keyword + " in the listed records"); 
			
		try {
			if (getRecordCountForTable() > 0)
				Assert.assertTrue(true);

			else
				assertTrue(false);
		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "No records found : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " No records found in the table : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
		
		List<String> first_column=getColumnValues(1);
		
		List<String> second_column=getColumnValues(2);
		
				
		try {
		
		for(int i=0;i<first_column.size();i++) {
			
	
			if(first_column.get(i).toLowerCase().contains(search_keyword.toLowerCase())| second_column.get(i).toLowerCase().contains(search_keyword.toLowerCase())) {
				
				Assert.assertTrue(true);
				
			} //test.log(LogStatus.INFO, "Search keyword: " + search_keyword + " found in all the records listed");
		
			else {
				test.log(LogStatus.INFO,  search_keyword + " does'nt exists in row: "+first_column.get(i));
				assertTrue(false);
				//test.log(LogStatus.INFO, "Asserting element " + search_keyword + " does'nt exists");
			}
		}
		
		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Assertion failed for " + search_keyword + " with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}

	}
	
	
	public static void verifyRecordSave() throws InterruptedException, IOException {
		
		String message_after_save = getTextOfElement("lcnts_success_message_XPATH");

		Thread.sleep(2500);

		verifyContains(message_after_save, "successfuly!");
	}
	
	

	/**
	 * @author ArshadM Close newly opened tab
	 */

	public void closeNewTab() {
		String originalHandle = driver.getWindowHandle();

		// Do something to open new tabs

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);
	}

	/**
	 * @author ArshadM Navigate to newly openened tab
	 * 
	 */
	public void swithToNewTab() {
		String originalHandle = driver.getWindowHandle();

		// Do something to open new tabs

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				// driver.close();
			}
		}

		test.log(LogStatus.INFO, "Switching to new tab ");

		// driver.switchTo().window(originalHandle);
	}

	
	
	/**
	 * @author ArshadM 
	 * Get text attribute of the element
	 * 
	 */
	public static String getTextOfElement(String xpath) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(xpath)))));
		String text = driver.findElement(By.xpath(OR.getProperty(xpath))).getText();
		test.log(LogStatus.INFO, "Reading value of " + xpath.toString().replace("_XPATH", " "));
		return text;
	}
	
	
	
	/**
	 * @author ArshadM 
	 * Get text attribute of the element
	 * 
	 */
	public static String getTextAttribute(String xpath) {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(xpath)))));
		String text = driver.findElement(By.xpath(OR.getProperty(xpath))).getAttribute("value");
		test.log(LogStatus.INFO, "Reading value of " + xpath.toString().replace("_XPATH", " "));
		return text;	
		
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

		log.debug("test execution completed !!!");
	}

	@BeforeMethod
	public void beforeTest() throws InterruptedException {
		closeNewTab();

		driver.get(config.getProperty("testsiteurl"));
	}

	@AfterTest
	public void afterTest() {
		wait = new WebDriverWait(driver, 30);
	}
}
