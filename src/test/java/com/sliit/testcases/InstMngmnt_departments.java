package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmnt_departments extends TestBase{
	
	
	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_departments(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("departments_XPATH");

		Thread.sleep(3000);

		click("center_create_new_XPATH");

		type("dept_code_XPATH", data.get("code"));

		type("dept_name_XPATH", data.get("name"));

		click("dept_head_XPATH");

		Thread.sleep(4000);

		// Type the location name in the dropdown search
		type("dept_type_head_XPATH", data.get("head"));

		// Click on the first result
		click("dept_head_dd_value_XPATH");
		
		
		click("dept_faculty_XPATH");

		Thread.sleep(3000);

		// Type the location name in the dropdown search
		type("dept_type_faculty_XPATH", data.get("faculty"));
		
		// Click on the first result
		click("dept_faculty_dd_value_XPATH");

		setStatus("dept_status_XPATH", data.get("status"));

		click("center_save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_DEPT");


	}
	
	
	@Test(enabled =false,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_departments(Hashtable<String, String> data) throws InterruptedException, IOException {

		//Click institute managment
		click("inst_management_XPATH");

		click("departments_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(5, data.get("keyword"));

	}
	
	
	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_departments(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click departments
		click("departments_XPATH");

		int row_number = Integer.parseInt(data.get("row"));

		// Retrive given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "dept_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "dept_name_value_XPATH");

		// verify department
		verifyViewRowValues(row.get(2), "dept_dept_value_XPATH");

		// verify faculty
		verifyViewRowValues(row.get(3), "dept_faculty_value_XPATH");
		
		//verify status
		verifyViewRowValues(row.get(4), "dept_status_value_XPATH");


	}
	
	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_departments(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click centers
		click("departments_XPATH");

		// Retrive 5th row values before opening it to view

		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		// Update code value with new value
		type("dept_code_value_XPATH", data.get("new code"));

		type("dept_name_value_XPATH", data.get("new name"));

		// Click save butotn
		click("lcnts_save_button_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	
	@Test(enabled=false,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_departments(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		//Click institute managment
		click("inst_management_XPATH");

		//Click locations
		click("departments_XPATH");
		
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

		// Click Department
		click("departments_XPATH");

		//Create mode
		click("dept_create_new_XPATH");
		Thread.sleep(3000);
		verifyBreadrumbs("Add","Department");
		
		
		// Edit mode
		editRow(1);
		Thread.sleep(3000);
		verifyBreadrumbs("Edit","Department");
					
		
		//View mode
		viewRow(1);
		Thread.sleep(3000);
		verifyBreadrumbs("View","Department");
		
			
		//Home
		verifyBreadrumbs("Home","Dashboard");
	}


}
