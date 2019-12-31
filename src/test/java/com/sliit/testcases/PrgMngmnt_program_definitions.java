package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.pages.Program_definitions;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_program_definitions extends TestBase {

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void createNewProgramDefinitions(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		Program_definitions pd = new Program_definitions();
		
		// Enter basic details
		click("prg_management_XPATH");
		click("prgm_program_definitions_XPATH");
		click("create_new_XPATH");
		type("prgm_code_XPATH", data.get("code"));
		type("prgm_name_XPATH", data.get("name"));
		selectFirstValue("prgm_faculties_XPATH");
		selectFirstValue("prgm_aw_institutes_XPATH");
		selectFirstValue("prgm_program_XPATH");
		selectFirstValue("prgm_cordinator_XPATH");
		click("prgm_next_XPATH");

		// Enter entry criteria
		selectFirstValue("prgm_entry_criteria_XPATH");
		pd.selectEntryCriteria();
		type("prgm_priority_XPATH", data.get("priority"));
		type("prgm_no_of_passes_XPATH", data.get("passes"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter years
		type("prgm_year_name_XPATH",data.get("year"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter Semesters
		type("prgm_semester_name_XPATH",data.get("semester"));
		selectFirstValue("prgm_semester_year_XPATH");
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter modules and submit
		selectFirstValue("prgm_semester_year_XPATH");
		selectFirstValue("prgm_module_semester_XPATH");
		selectFirstValue("prgm_module_XPATH");
		click("prgm_add_XPATH");
		click("prgm_submit_XPATH");
		Thread.sleep(3000);
		
		verifyRecordSave();
	
		
	}
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void searchProgramDefinitions(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("prgm_program_definitions_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_ProgramDefinitions(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");
		click("prgm_program_definitions_XPATH");
		Thread.sleep(3000);
		
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "prgrm_def_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "prgrm_def_name_value_XPATH");

		// verify program
		verifyViewRowValues(row.get(2), "prgrm_def_program_value_XPATH");

		//verify progress
		verifyEquals(row.get(6), data.get("Progress"));
		
		verifyViewRowValues(row.get(7), "prgm_status_value_XPATH");


		 
		
	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_ProgramDefinitions(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("prgm_program_definitions_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_ProgramDefinitions(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("prgm_program_definitions_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);
		Program_definitions pd = new Program_definitions();
		//updating  code,name and description with new values
		type("prgrm_def_code_value_XPATH", data.get("new code"));
		type("prgrm_def_name_value_XPATH", data.get("new name"));
		//Does not allow editing program, faculty and awarding institute
		
		//selectFirstValue("prgm_cordinator_XPATH");
		selectFromDropdown("prgm_cordinator_XPATH",2);
		click("prgm_next_XPATH");
		
		// Enter entry criteria
		selectFromDropdown("prgm_entry_criteria_XPATH",2);
		pd.selectEntryCriteria();
		type("prgm_priority_XPATH", data.get("new priority"));
		type("prgm_no_of_passes_XPATH", data.get("new passes"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter years
		type("prgm_year_name_XPATH",data.get("new year"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter Semesters
		type("prgm_semester_name_XPATH",data.get("new semester"));
		selectFromDropdown("prgm_semester_year_XPATH",2);
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter modules and submit
		selectFromDropdown("prgm_semester_year_XPATH",2);
		selectFromDropdown("prgm_module_semester_XPATH",1);
		selectFromDropdown("prgm_module_XPATH",2);
		selectFromDropdown("prgm_module_XPATH",3);
		click("prgm_module_nongpa_XPATH");
		click("prgm_module_nongpa_XPATH");
		click("prgm_add_XPATH");
		click("prgm_submit_XPATH");
		Thread.sleep(3000);
	

		// Verify record is updated
		verifyRecordSave();
	
	}
	
	@Test(enabled=true,priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("prgm_program_definitions_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Program Definition");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Program Definition");
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Program Definition");
		
		//Home
		verifyBreadrumbs("Home","Dashboard"); 
		
	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 7)
	public void saveDraft_ProgramDefinitions(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		Program_definitions pd = new Program_definitions();
		
		// Enter basic details
		click("prg_management_XPATH");
		click("prgm_program_definitions_XPATH");
		click("create_new_XPATH");
		type("prgm_code_XPATH", data.get("code"));
		type("prgm_name_XPATH", data.get("name"));
		selectFirstValue("prgm_faculties_XPATH");
		selectFirstValue("prgm_aw_institutes_XPATH");
		selectFirstValue("prgm_program_XPATH");
		selectFirstValue("prgm_cordinator_XPATH");
		click("prgm_next_XPATH");

		// Enter entry criteria
		selectFirstValue("prgm_entry_criteria_XPATH");
		pd.selectEntryCriteria();
		type("prgm_priority_XPATH", data.get("priority"));
		type("prgm_no_of_passes_XPATH", data.get("passes"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter years
		type("prgm_year_name_XPATH",data.get("year"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter Semesters
		type("prgm_semester_name_XPATH",data.get("semester"));
		selectFirstValue("prgm_semester_year_XPATH");
		click("prgm_add_XPATH");

		Thread.sleep(3000);

		click("prgm_save_draft_XPATH");
		Thread.sleep(3000);

		// Retrieve first row values before opening it to view
		List<String> row = getRowValues(1);
		Thread.sleep(3000);
		
		//Verify that the draft is in in-progress state		
		verifyEquals(row.get(6), "In-progress");
		
		// Click and view first row
		viewRow(1);
		//go to semester tab
		for (int i=0;i<3;i++)
		click("prgm_next_XPATH");
		
		
		// verify semester
		verifyViewRowValues(data.get("semester"), "prgm_semester_value_XPATH");
		
	}
	
}
