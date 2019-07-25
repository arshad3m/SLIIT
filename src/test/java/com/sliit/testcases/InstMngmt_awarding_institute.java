package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_awarding_institute extends TestBase {

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_awarding_institute(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("awrd_institute_XPATH");

		Thread.sleep(3000);

		click("awrd_inst_create_new_XPATH");

		type("awrd_inst_code_XPATH", data.get("code"));

		type("awrd_inst_name_XPATH", data.get("name"));
		
		setStatus("awrd_inst_status_XPATH", data.get("status"));

		click("awrd_inst_save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_AI");


	}
	
	@Test(enabled =false,priority=2)
	public void search_awarding_institute() throws InterruptedException, IOException {

		//Click institute managment
		click("inst_management_XPATH");

		click("awrd_institute_XPATH");

		Thread.sleep(3000);

		String keyword = "b"; 

		//Enter serach keyword in the searchbox
		search("search_box_XPATH", keyword);

		//Verify search results
		verifySearchResults(3, keyword);

	}
	
	@Test(enabled =false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_awarding_institutes(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click departments
		click("awrd_institute_XPATH");

		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "awrd_inst_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "awrd_inst_name_value_XPATH");
		
		//verify status
		verifyViewRowValues(row.get(2), "awrd_inst_status_value_XPATH");


	}
	
}
