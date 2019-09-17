package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

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
	
	
}
