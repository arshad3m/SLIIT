package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_document_type extends TestBase{

	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_document_type(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("document_type_XPATH");

		Thread.sleep(3000);

		click("dcmnt_typ_create_new_XPATH");
		
		Thread.sleep(3000);
		//Click clear button
		click("clear_changes_XPATH");

		type("dcmnt_typ_code_XPATH", data.get("code"));

		type("dcmnt_typ_name_XPATH", data.get("name"));
		
		//set status
		setStatus("dcmnt_typ_status_XPATH", data.get("status"));

		click("dcmnt_typ_save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_DT_");


	}
	
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_document_type(Hashtable<String, String> data) throws InterruptedException, IOException {

		//Click institute management
		click("inst_management_XPATH");

		click("document_type_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(3, data.get("keyword"));

	}
	
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_document_type(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click departments
		click("document_type_XPATH");

		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "dcmnt_typ_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "dcmnt_typ_name_value_XPATH");
		
		//verify status
		verifyViewRowValues(row.get(2), "dcmnt_typ_status_value_XPATH");


	}
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_document_type(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click centers
		click("document_type_XPATH");

		// Retrieve 5th row values before opening it to view

		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		// Update code value with new value
		type("dcmnt_typ_code_value_XPATH", data.get("new code"));

		type("dcmnt_typ_name_value_XPATH", data.get("new name"));

		// Click save button
		click("dcmnt_typ_save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_document_type(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		//Click institute management
		click("inst_management_XPATH");

		//Click locations
		click("document_type_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));
		}
		
	}
		@Test(enabled=true)
		public void verify_breadcrumbs() throws InterruptedException, IOException{
			// Click institute management
			click("inst_management_XPATH");

			// Click Document type
			click("document_type_XPATH");

			//Create mode
			click("dcmnt_typ_create_new_XPATH");
			Thread.sleep(3000);
			verifyBreadrumbs("Add","Document Type");
			
			
			// Edit mode
			editRow(1);
			Thread.sleep(3000);
			verifyBreadrumbs("Edit","Document Type");
						
			
			//View mode
			viewRow(1);
			Thread.sleep(3000);
			verifyBreadrumbs("View","Document Type");
			
				
			//Home
			verifyBreadrumbs("Home","Dashboard");
		}
		
}
	

