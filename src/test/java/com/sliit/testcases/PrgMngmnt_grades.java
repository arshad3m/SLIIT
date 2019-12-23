package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_grades extends TestBase {

	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_grades(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("grades_XPATH");
		
		click("create_new_XPATH"); 
		type("grds_code_XPATH", data.get("code"));
		type("grds_name_XPATH", data.get("name"));
		
		//Add Start date and end date
		//click on start date calendar icon
		click("grds_start_date_XPATH");
		// go to next month
		click("grds_start_date_next_XPATH"); 
		//select 11th 
		click("grds_select_start_date_XPATH");
		//click on end date calendar icon
		click("grds_end_date_XPATH"); 
		//click on year
		click("grds_end_year_XPATH");
		//click on next icon
		click("grds_end_year_next_XPATH");
		//select year 2023
		click("grds_select_end_year_XPATH");
		//click on end date
		click("grds_select_end_date_XPATH");
		
		
		//Add Grades
		
		type("grds_grade_XPATH", data.get("grade1"));
		type("grds_marks_from_XPATH", data.get("marksfrom1"));
		type("grds_marks_to_XPATH", data.get("marksto1"));
		type("grds_grade_point_XPATH", data.get("gradepoint1"));
		click("grds_grades_plus_btn_XPATH");
		
		//setStatus("status_XPATH", data.get("status"));
		
		click("save_XPATH");

		verifyRecordSave();
		
		//verifyTableDescendingOrder("Auto_GR_");


	}
	@Test(enabled =true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=2)
	public void search_grades(Hashtable<String, String> data) throws InterruptedException, IOException {

		click("prg_management_XPATH");

		click("grades_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(7, data.get("keyword"));

	}
	@Test(enabled =true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void view_grades(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("grades_XPATH");
		int row_number = Integer.parseInt(data.get("row"));

		// Retrieve given row values before opening it to view
		List<String> row = getRowValues(row_number);

		// Click and view (row_number)
		viewRow(row_number);

		// verify code
		verifyViewRowValues(row.get(0), "grds_code_value_XPATH");

		// verify name
		verifyViewRowValues(row.get(1), "grds_name_value_XPATH");
			
		//verify status
		verifyViewRowValues(row.get(5), "grds_status_value_XPATH");	
	
		 
		
	}
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filter_grades(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("prg_management_XPATH");

		click("grades_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));

		}
	}
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void edit_grades(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");

		click("grades_XPATH");


		int row_number = Integer.parseInt(data.get("row"));

		List<String> row = getRowValues(row_number);

		// Edit row
		editRow(row_number);

		//updating  code,name and description with new values
		type("grds_code_XPATH", data.get("new code"));
		type("grds_name_XPATH", data.get("new name"));
						
		//Add Grade
		type("grds_grade_XPATH", data.get("new grade1"));
		type("grds_marks_from_XPATH", data.get("new marksfrom1"));
		type("grds_marks_to_XPATH", data.get("new marksto1"));
		type("grds_grade_point_XPATH", data.get("new gradepoint1"));
		click("grds_grades_plus_btn_XPATH");
		
		// Click save button
		click("save_XPATH");

		// Verify record is updated
		verifyRecordSave();

	}
	
	@Test(enabled=true,priority=6)
	public void verify_breadcrumbs() throws InterruptedException, IOException{

		click("prg_management_XPATH");

		click("grades_XPATH");
		
		//Create mode
		click("create_new_XPATH");
		verifyBreadrumbs("Add","Grade");		
		Thread.sleep(3000);
		
		// Edit mode
		editRow(1);
		verifyBreadrumbs("Edit","Grade");
		Thread.sleep(3000);
		
		//View mode
		viewRow(1);
		verifyBreadrumbs("View","Grade");
		
		//Home
		verifyBreadrumbs("Home","Dashboard"); 
		
	}
}
