package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_entry_criteria extends TestBase{

	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_entry_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("entry_criteria_XPATH");
		
		click("create_new_XPATH"); 
		type("entry_crtr_code_XPATH", data.get("code"));
		type("entry_crtr_name_XPATH", data.get("name"));
		
		
		//select qualification type
		click("entry_crtr_qualification_type_XPATH");
		Thread.sleep(3000);
		type("entry_crtr_type_qualification_type_XPATH", data.get("type"));
		Thread.sleep(3000);
		click("entry_crtr_qualification_type_dd_value_XPATH");
		
		//Select Qualification outcome
		Thread.sleep(3000); // waiting for Outcomes dropdown to load
		click("entry_crtr_qualification_outcome_XPATH");
		type("entry_crtr_type_qualification_outcome_XPATH", data.get("outcome"));
		Thread.sleep(3000);
		click("entry_crtr_qualification_outcome_dd_value_XPATH");
		
		//Add Subjects
		type("entry_crtr_subjects_XPATH", data.get("subject1"));
		click("entry_crtr_subjects_plus_btn_XPATH");
		//Add Results
		
		type("entry_crtr_grade_XPATH", data.get("grade"));
		type("entry_crtr_value_XPATH", data.get("value"));
		click("entry_crtr_results_plus_btn_XPATH");

		setStatus("status_XPATH", data.get("status"));
		
		click("save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_ET_");


	}
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_entry_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("entry_criteria_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_entry_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("entry_criteria_XPATH");
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "qlfctn_typ_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "qlfctn_typ_name_value_XPATH");
			
		//verify status
		verifyViewRowValues(row.get(4), "qlfctn_typ_status_value_XPATH");	
	
		 
		
	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_entry_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("entry_criteria_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_entry_criteria(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("entry_criteria_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		//updating  code,name and description with new values
		type("entry_crtr_code_value_XPATH", data.get("new code"));
		type("entry_crtr_name_value_XPATH", data.get("new name"));
		
		//select qualification type
		Thread.sleep(3000);
		click("entry_crtr_qualification_type_XPATH");
		Thread.sleep(3000);
		type("entry_crtr_type_qualification_type_XPATH", data.get("new type"));
		Thread.sleep(3000);
		click("entry_crtr_qualification_type_dd_value_XPATH");
				
		//Select Qualification outcome
		Thread.sleep(3000); // waiting for Outcomes dropdown to load
		click("entry_crtr_qualification_outcome_XPATH");
		type("entry_crtr_type_qualification_outcome_XPATH", data.get("new outcome"));
		Thread.sleep(3000);
		click("entry_crtr_qualification_outcome_dd_value_XPATH");
				
		//Add Subjects
		type("entry_crtr_subjects_XPATH", data.get("new subject"));
		click("entry_crtr_subjects_plus_btn_XPATH");
		//Add Results
				
		type("entry_crtr_grade_XPATH", data.get("new grade"));
		type("entry_crtr_value_XPATH", data.get("new value"));
		click("entry_crtr_results_plus_btn_XPATH");
		
		// Click save button
		click("save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	@Test(enabled=true,priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("entry_criteria_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Entry Criterion");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Entry Criterion");
		Thread.sleep(3000);
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Entry Criterion");
		
		//Home
		verifyBreadrumbs("Home","Dashboard"); 
		
	}

}
