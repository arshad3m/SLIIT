package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_classrooms extends TestBase {

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_classrooms(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("classroom_XPATH");

		Thread.sleep(3000);

		click("clssrm_create_new_XPATH");
		
		Thread.sleep(3000);

		type("clssrm_code_XPATH", data.get("code"));

		type("clssrm_name_XPATH", data.get("name"));

		//Select class type from the dropdown
		click("clssrm_type_XPATH");
		type("clssrm_type_type_XPATH", data.get("type"));
		click("clssrm_type_dd_value_XPATH");

		//Add Study capacity and Exam capacity
		type("clssrm_study_capacity_XPATH", data.get("study capacity"));
		type("clssrm_exam_capacity_XPATH", data.get("exam capacity"));

		//Select center from dropdown
		Thread.sleep(3000);
		click("clssrm_center_XPATH");
		type("clssrm_type_center_XPATH", data.get("center"));
		Thread.sleep(3000);
		click("clssrm_center_dd_value_XPATH");
		
		//Select Faculty from drop-down
		Thread.sleep(3000);
		click("clssrm_faculty_XPATH");
		type("clssrm_type_faculty_XPATH", data.get("faculty"));
		Thread.sleep(3000);
		click("clssrm_faculty_dd_value_XPATH");
		
		setStatus("clssrm_status_XPATH", data.get("status"));

		click("clssrm_save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_CR_R-");


	}
	
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_classrooms(Hashtable<String, String> data) throws InterruptedException, IOException {

		//Click institute management
		click("inst_management_XPATH");

		click("classroom_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_classrooms(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click classrooms
		click("classroom_XPATH");

		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

	// verify code
		verifyViewRowValues(row.get(0), "clssrm_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "clssrm_name_value_XPATH");
		
		//verify type
		verifyViewRowValues(row.get(2), "clssrm_type_value_XPATH");
		
		//verify study capacity
		verifyViewRowValues(row.get(5), "clssrm_study_capacity_value_XPATH");
				
		//verify exam capacity
		verifyViewRowValues(row.get(6), "clssrm_exam_capacity_value_XPATH");	
				
		//verify center
		verifyViewRowValues(row.get(3), "clssrm_center_value_XPATH");
		
		//verify faculty
		//verifyViewRowValues(row.get(4), "clssrm_faculty_value_XPATH");	
		
		//verify status
		verifyViewRowValues(row.get(7), "clssrm_status_value_XPATH");
		/*	
			for(int i=0;i<8;i++)
			{
				System.out.println(row.get(i));
			}*/

	}
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_classrooms(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		// Click institute management
		click("inst_management_XPATH");

		// Click classrooms
		click("classroom_XPATH");

		// Retrieve 5th row values before opening it to view

		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		// Update code value with new value
		type("clssrm_code_value_XPATH", data.get("new code"));

		type("clssrm_name_value_XPATH", data.get("new name"));
		
		type("clssrm_study_capacity_value_XPATH", data.get("new study capacity"));

		type("clssrm_exam_capacity_value_XPATH", data.get("new exam capacity"));

		// Click save button
		click("clssrm_save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_classrooms(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		//Click institute managment
		click("inst_management_XPATH");

		//Click classrooms
		click("classroom_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	
}
