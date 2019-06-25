package com.sliit.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.google.inject.Key;
import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_locations extends TestBase {

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void add_new_locations(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("locations_XPATH");

		WebElement element = driver.findElement(By.xpath(OR.getProperty("lctns_create_new_XPATH")));

		click(element);

		Thread.sleep(4000);

		click("lcnts_clear_changes_XPATH");

		type("lctns_code_XPATH", data.get("code"));

		type("lctns_name_XPATH", data.get("name"));

		click("lcnts_status_XPATH");

		// Select checkbox based on Active or Inactive
		if (data.get("status").equals("Active")) {
			click("lcnts_active_XPATH");
		} else {
			click("lcnts_inactive_XPATH");
			click("lcnts_confirm_inactivation_XPATH");
		}

		click("lcnts_save_XPATH");

		verifyRecordSave();
		
	}

	@Test(enabled = true)
	public void search_locations_by_code() throws InterruptedException, IOException {

		click("inst_management_XPATH");

		click("locations_XPATH");

		Thread.sleep(3000);

		String keyword = "kandy";

		search("lcnts_searchbox_XPATH", keyword);

		verifySearchResults(2, keyword);

	}

	@Test(enabled = true)
	public void view_locations() throws InterruptedException, IOException {

		click("inst_management_XPATH");

		click("locations_XPATH");

		String row = getRowValues(5);

		viewRow(5);

		verifyViewRowValues(row);

	}

	
	@Test(enabled=true)
	public void edit_locations() throws InterruptedException, IOException {
		
		click("inst_management_XPATH");

		click("locations_XPATH");
		
		editRow(4);
		
		type("lcnts_code_value_XPATH","Matara branch");
		
		click("lcnts_save_button_XPATH");
		
		verifyRecordSave();
		

	}

}
