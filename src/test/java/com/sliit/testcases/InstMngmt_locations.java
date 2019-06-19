package com.sliit.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_locations extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void add_new_locations(Hashtable<String, String> data) throws InterruptedException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		click("inst_management_XPATH");

		click("locations_XPATH");
		
	//	click("lctns_create_new_XPATH");
		
		WebElement element=driver.findElement(By.xpath(OR.getProperty("lctns_create_new_XPATH")));
		
		click(element);
		
		Thread.sleep(1500);

		
		click("lcnts_clear_changes_XPATH");
		
		type("lctns_code_XPATH",data.get("code"));
		
		type("lctns_name_XPATh",data.get("name"));
		
		Thread.sleep(6500);
		
		click("lcnts_status_XPATH");
		
		Thread.sleep(11500);

		
		
		
		
		
	}
	
	
	

}
