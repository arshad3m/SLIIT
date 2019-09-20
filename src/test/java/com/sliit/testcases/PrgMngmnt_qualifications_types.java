package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_qualifications_types extends TestBase{

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_program_types(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("program_types_XPATH");

		click("create_new_XPATH"); 
		type("prgrm_typ_code_XPATH", data.get("code"));
		type("prgrm_typ_name_XPATH", data.get("name"));
		type("prgrm_typ_description_XPATH", data.get("description"));
		setStatus("status_XPATH", data.get("status"));
		
		Thread.sleep(3000);
		
		if (data.get("aptitude").equals("Y")) 

			click("prgrm_typ_apptitude_test_XPATH");		
			
		if (!data.get("interview").equals("Y")) 

			click("prgrm_typ_interview_XPATH");		
			
		if (data.get("research proposal").equals("Y"))

			click("prgrm_typ_research_proposal_XPATH");		
			

		click("save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_PT_");


	}
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_program_types(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("program_types_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_program_types(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("program_types_XPATH");
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "prgrm_typ_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "prgrm_typ_name_value_XPATH");
			
		//verify description
		verifyViewRowValues(row.get(2), "prgrm_typ_description_value_XPATH");	
				
		//verify aptitude test
		verifyViewRowValues(row.get(3), "prgrm_typ_aptitude_value_XPATH");	
		
		//verify interview
		verifyViewRowValues(row.get(3), "prgrm_typ_interview_value_XPATH");	
		
		//verify interview
		verifyViewRowValues(row.get(3), "prgrm_typ_research_proposal_value_XPATH");	
		
	}
	
	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_program_types(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("program_types_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		//updating  code,name and description with new values
		type("prgrm_typ_code_value_XPATH", data.get("new code"));
		type("prgrm_typ_name_value_XPATH", data.get("new name"));
		type("prgrm_typ_description_value_XPATH", data.get("new description"));
		
		if (data.get("aptitude").equals("Y")) 

			click("prgrm_typ_aptitude_value_XPATH");		
			
		if (!data.get("interview").equals("Y")) 

			click("prgrm_typ_interview_value_XPATH");		
			
		if (data.get("research proposal").equals("Y"))

			click("prgrm_typ_research_proposal_value_XPATH");		
			
		
		// Click save button
		click("save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_program_types(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("program_types_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled=true,priority=5)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("program_types_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Program Type");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Program Type");
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Program Type");
		
		//Home
		verifyBreadrumbs("Home","Dashboard"); 
		
	}
}
