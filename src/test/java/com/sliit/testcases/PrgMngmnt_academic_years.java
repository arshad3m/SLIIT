package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_academic_years extends TestBase{
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_academic_year(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("academic_years_XPATH");

		click("create_new_XPATH"); 
		type("acdmc_years_year_XPATH", data.get("year"));
		type("acdmc_years_description_XPATH", data.get("description"));
		
		//Add From Date and End date
		
		//Select faculty from the dropdown
		click("acdmc_years_faculty_XPATH");
		Thread.sleep(3000);
		type("acdmc_years_type_faculty_XPATH", data.get("faculty"));
		click("acdmc_years_faculty_dd_value_XPATH");
		
		//Select Awarding institute from the dropdown
		click("acdmc_years_awdng_inst_XPATH");
		Thread.sleep(3000);
		type("acdmc_years_type_awdng_inst_XPATH", data.get("awarding institute"));
		click("acdmc_years_awdng_inst_dd_value_XPATH");
				
		setStatus("status_XPATH", data.get("status"));

		click("save_XPATH");

		verifyRecordSave();
		
		//verifyTableDescendingOrder("Auto_AY_");


	}
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_academic_years(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("academic_years_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_academic_years(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("academic_years_XPATH");
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify year
		verifyViewRowValues(row.get(0), "acdmc_years_year_value_XPATH");

		// verify description
		verifyViewRowValues(row.get(1), "acdmc_years_description_value_XPATH");
			
		//verify faculty
		verifyViewRowValues(row.get(2), "acdmc_years_faculty_value_XPATH");	
				
		//verify awarding institute
		verifyViewRowValues(row.get(3), "acdmc_years_awdng_inst_value_XPATH");	
		
		//verify From date
		//verifyViewRowValues(row.get(4), "acdmc_years_from_value_XPATH");
		verifyViewRowValues(row.get(4), "acdmc_years_status_value_XPATH");
		//verify To Date
		//verifyViewRowValues(row.get(5), "acdmc_years_to_value_XPATH");
		
		//verify status
		//verifyViewRowValues(row.get(6), "acdmc_years_status_value_XPATH");
		
	}
	
	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_academic_years(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("academic_years_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		// Update year value with new value
		type("acdmc_years_year_value_XPATH", data.get("new year"));
		
		// Update description value with new value
		type("acdmc_years_description_value_XPATH", data.get("new description"));

		// Click save button
		click("save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_academic_years(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("academic_years_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled=true,priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("academic_years_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Academic Year");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Academic Year");
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Academic Year");
		
		//Home
		verifyBreadrumbs("Home","Dashboard"); 
		
	}
	
}
