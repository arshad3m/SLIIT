package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_level_completion_outcomes extends TestBase {

	@Test(enabled =false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_learning_outcomes(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("level_completion_outcomes_XPATH");

		click("create_new_XPATH"); 
		type("lvl_cmpltn_otcms_code_XPATH", data.get("code"));
		type("lvl_cmpltn_otcms_name_XPATH", data.get("name"));
		
		setStatus("lvl_cmpltn_otcms_status_value_XPATH", data.get("status"));
		Thread.sleep(5000);
		click("save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_LCO_");


	}
	@Test(enabled =false,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_learning_outcomes(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("level_completion_outcomes_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_learning_outcomes(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("level_completion_outcomes_XPATH");
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);
		Thread.sleep(3000);
		// verify code
		verifyViewRowValues(row.get(0), "lvl_cmpltn_otcms_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "lvl_cmpltn_otcms_name_value_XPATH");
			
		//verify status
		verifyViewRowValues(row.get(2), "lvl_cmpltn_otcms_status_value_XPATH");	
	
		 
		
	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_learning_outcomes(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("level_completion_outcomes_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_learning_outcomes(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("level_completion_outcomes_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		//updating  code,name and description with new values
		type("lvl_cmpltn_otcms_code_value_XPATH", data.get("new code"));
		type("lvl_cmpltn_otcms_name_value_XPATH", data.get("new name"));

		
		// Click save button
		click("save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	@Test(enabled=false,priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("level_completion_outcomes_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Level Completion Outcome");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Level Completion Outcome");
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Level Completion Outcome");
		
		//Home
		verifyBreadrumbs("Home","Dashboard"); 
		
	}
}
