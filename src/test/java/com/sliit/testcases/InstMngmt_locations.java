package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_locations extends TestBase {

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority=1)
	public void add_new_locations(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		
		//Click institute managment
		click("inst_management_XPATH");

		//Click locations
		click("locations_XPATH");

		//Click create new
		click("lctns_create_new_XPATH");

		Thread.sleep(4000);

		//Click clear button
		click("lcnts_clear_changes_XPATH");

		//Enter location code
		type("lctns_code_XPATH", data.get("code"));

		//Enter location name
		type("lctns_name_XPATH", data.get("name"));

		//Set status
		setStatus("lcnts_status_XPATH", data.get("status"));

		//Click save button
		click("lcnts_save_XPATH");

		
		//Verify record is saved
		verifyRecordSave();
		
		//Verify table records are in descending order
		verifyTableDescendingOrder("Auto_LOC");

		
	}

	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_locations(Hashtable<String, String> data) throws InterruptedException, IOException {

		//Click institute management
		click("inst_management_XPATH");

		click("locations_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(3, data.get("keyword"));

	}

	@Test(enabled = true,priority=3)
	public void view_locations() throws InterruptedException, IOException {

		//Click institute management
		click("inst_management_XPATH");

		//Click locations
		click("locations_XPATH");

		//Retrive 5th row values before opening it to view
		List<String> row = getRowValues(5);

		//Click and view 5th row
		viewRow(5);

		//Verify row values are correct for 1st, 2nd and 3rd fields (code,name,status)
		verifyViewRowValues(row.get(0),"lcnts_code_value_XPATH");
		
		verifyViewRowValues(row.get(1),"lcnts_name_value_XPATH");
		
		verifyViewRowValues(row.get(2),"lcnts_status_value_XPATH");



	}

	
	@Test(enabled=true,priority=4)
	public void edit_locations() throws InterruptedException, IOException {
		
		//Click institute managment
		click("inst_management_XPATH");

		//Click locations
		click("locations_XPATH");
		
		//Edit fourth row
		editRow(4);
		
		//Update code value with new value
		type("lcnts_code_value_XPATH","Auto_Matara branch");
		
		//Click save butotn
		click("lcnts_save_button_XPATH");
		
		//Verify record is updated
		verifyRecordSave();
		

	}

}
