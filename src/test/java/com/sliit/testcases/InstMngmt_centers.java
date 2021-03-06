package com.sliit.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_centers extends TestBase {

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 2)
	public void search_centers(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		click("inst_management_XPATH");

		click("centers_XPATH");

		Thread.sleep(3000);

		// Enter search keyword in the search box
		search("lcnts_searchbox_XPATH",data.get("keyword"));

		// Verify search results
		verifySearchResults(4, data.get("keyword"));

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_centers(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("centers_XPATH");

		Thread.sleep(3000);

		click("center_create_new_XPATH");

		type("center_code_XPATH", data.get("code"));

		type("center_name_XPATH", data.get("name"));

		click("center_location_XPATH");

		Thread.sleep(3000);

		// Type the location name in the dropdown search
		type("center_type_location_XPATH", data.get("location"));

		// Click on the first result
		click("center_location_dd_value_XPATH");

		setStatus("center_status_XPATH", data.get("status"));

		click("center_save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_CENTER");


	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_centers(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click centers
		click("centers_XPATH");

		int row_number = Integer.parseInt(data.get("row"));

		// Retrive given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "center_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "center_name_value_XPATH");

		// verify location
		verifyViewRowValues(row.get(2), "center_location_value_XPATH");

		// verify status
		verifyViewRowValues(row.get(3), "center_status_value_XPATH");

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_centers(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click centers
		click("centers_XPATH");

		// Retrive 5th row values before opening it to view

		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		// Update code value with new value
		type("center_code_value_XPATH", data.get("new code"));

		type("center_name_value_XPATH", data.get("new name"));

		// Click save butotn
		click("lcnts_save_button_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_centers(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		//Click institute managment
		click("inst_management_XPATH");

		//Click locations
		click("centers_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));
		}	
		
	}
	@Test(enabled=true, priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("inst_management_XPATH");
		click("centers_XPATH");
		
		//Create mode
		click("center_create_new_XPATH");
		verifyBreadrumbs("Add","Center");
		
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Center");
					
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Center");
		
			
		//Home
		verifyBreadrumbs("Home","Dashboard");
	}

}
