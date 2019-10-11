package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_assessment_criteria extends TestBase{

	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_assessment_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("assessment_criteria_XPATH");

		click("create_new_XPATH"); 
		type("assmnt_crtr_code_XPATH", data.get("code"));
		type("assmnt_crtr_name_XPATH", data.get("name"));
		
		setStatus("status_XPATH", data.get("status"));
        type("assmnt_crtr_sub_assess_XPATH", data.get("sub assessment1"));
		click("assmnt_crtr_plus_btn_XPATH");
		type("assmnt_crtr_sub_assess_XPATH", data.get("sub assessment2"));
		click("assmnt_crtr_plus_btn_XPATH");

		click("save_XPATH");
		
		Thread.sleep(3000);

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_AC_");


	}
	@Test(enabled =false,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_assessment_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("assessment_criteria_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_assessment_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("assessment_criteria_XPATH");
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);
		Thread.sleep(3000);
		// verify code
		verifyViewRowValues(row.get(0), "assmnt_crtr_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "assmnt_crtr_name_value_XPATH");
			
		//verify status
		verifyViewRowValues(row.get(3), "assmnt_crtr_status_value_XPATH");	
	
		 
		
	}
	@Test(enabled=false,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_assessment_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("assessment_criteria_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_assessment_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("assessment_criteria_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		//updating  code,name and description with new values
		type("assmnt_crtr_code_value_XPATH", data.get("new code"));
		type("assmnt_crtr_name_value_XPATH", data.get("new name"));
		type("assmnt_crtr_sub_assess_edit_value_XPATH", data.get("new sub assessment"));	
		click("assmnt_crtr_plus_btn_value_XPATH");

		click("save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	@Test(enabled=true,priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("assessment_criteria_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Assessment Criteri");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Assessment Criteri");
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Assessment Criteri");
		
		//Home
		verifyBreadrumbs("Home","Dashboard"); 
		
	}

}
