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
		
		//Select country from dropdown
		Thread.sleep(3000);
		click("awrd_inst_country_XPATH");
		type("awrd_inst_type_country_XPATH", data.get("country"));
		Thread.sleep(3000);
		click("awrd_inst_country_dd_value_XPATH");
		
		setStatus("awrd_inst_status_XPATH", data.get("status"));

		click("awrd_inst_save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_AI");


	}
	
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_awarding_institute(Hashtable<String, String> data) throws InterruptedException, IOException {

		//Click institute managment
		click("inst_management_XPATH");

		click("awrd_institute_XPATH");

		Thread.sleep(3000);


		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(4, data.get("keyword"));

	}
	
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
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
		verifyViewRowValues(row.get(3), "awrd_inst_status_value_XPATH");


	}
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_awarding_institute(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click centers
		click("awrd_institute_XPATH");

		// Retrieve 5th row values before opening it to view

		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		// Update code value with new value
		type("awrd_inst_code_value_XPATH", data.get("new code"));

		type("awrd_inst_name_value_XPATH", data.get("new name"));

		// Click save button
		click("awrd_inst_save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_awarding_institute(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		//Click institute managment
		click("inst_management_XPATH");

		//Click locations
		click("awrd_institute_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));
		}
		
		
		
		
	}

}
