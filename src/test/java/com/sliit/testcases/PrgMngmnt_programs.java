package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_programs extends TestBase {

	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_programs(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("programs_XPATH");
		
		click("create_new_XPATH"); 
		type("prgrms_code_XPATH", data.get("code"));
		type("prgrms_name_XPATH", data.get("name"));
		
		//select program type
		Thread.sleep(3000);
		click("prgrms_program_type_XPATH");
		type("prgrms_program_type_type_XPATH", data.get("program type"));
		Thread.sleep(3000);
		click("prgrms_program_type_dd_value_XPATH");
		
		//select awarding institute
		Thread.sleep(3000);
		click("prgrms_awarding_institute_XPATH");
		type("prgrms_awarding_institute_type_XPATH", data.get("awarding institute"));
		Thread.sleep(3000);
		click("prgrms_awarding_institute_dd_value_XPATH");
		
		//Select Documents
		Thread.sleep(3000); // waiting  dropdown to load
		click("prgrms_documents_XPATH");
		type("prgrms_documents_type_XPATH", data.get("documents"));
		Thread.sleep(3000);
		click("prgrms_document_dd_value_XPATH");
		
		//Add specializations
		type("prgrms_specialization_XPATH", data.get("specialization"));

		Thread.sleep(3000); // waiting for dropdown to load
		click("prgrms_department_XPATH");
		type("prgrms_type_department_XPATH", data.get("department"));
		Thread.sleep(3000);
		click("prgrms_department_dd_value_XPATH");
		
		Thread.sleep(3000); // waiting for dropdown to load
		click("prgrms_centers_XPATH");
		type("prgrms_type_centers_XPATH", data.get("centers"));
		Thread.sleep(3000);
		click("prgrms_centers_dd_value_XPATH");
		
		click("prgrms_plus_button_XPATH");

		//setStatus("status_XPATH", data.get("status"));
		
		click("save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_PRG_");


	}
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_programs(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("programs_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_programs(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("programs_XPATH");
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "prgrms_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "prgrms_name_value_XPATH");
		
		//verify program type
		verifyViewRowValues(row.get(2), "prgrms_program_type_value_XPATH");

		//verify awarding institute
		verifyViewRowValues(row.get(4), "prgrms_awarding_institute_value_XPATH");
		
		//verify status
		verifyViewRowValues(row.get(7), "prgrms_status_value_XPATH");	
	
		 
		
	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_programs(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("programs_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_programs(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("programs_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		//updating  code,name and description with new values
		type("prgrms_code_value_XPATH", data.get("new code"));
		type("prgrms_name_value_XPATH", data.get("new name"));
		
		//update awarding institute
		Thread.sleep(3000);
		click("prgrms_awarding_institute_XPATH");
		type("prgrms_awarding_institute_type_XPATH", data.get("new awarding institute"));
		Thread.sleep(3000);
		click("prgrms_awarding_institute_dd_value_XPATH");
		
		//Select Documents
		Thread.sleep(3000); // waiting  dropdown to load
		click("prgrms_documents_XPATH");
		type("prgrms_documents_type_XPATH", data.get("new documents"));
		Thread.sleep(3000);
		click("prgrms_document_dd_value_XPATH");
		
		//Add specializations
		type("prgrms_specialization_XPATH", data.get("new specialization"));

		Thread.sleep(3000); // waiting for dropdown to load
		click("prgrms_department_XPATH");
		type("prgrms_type_department_XPATH", data.get("new department"));
		Thread.sleep(3000);
		click("prgrms_department_dd_value_XPATH");
		
		Thread.sleep(3000); // waiting for dropdown to load
		click("prgrms_centers_XPATH");
		type("prgrms_type_centers_XPATH", data.get("new centers"));
		Thread.sleep(3000);
		click("prgrms_centers_dd_value_XPATH");
		// Click save button
		click("save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	@Test(enabled=true,priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("programs_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Program");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Program");
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Program");
		
		//Home
		verifyBreadrumbs("Home","Program"); 
		
	}
}
