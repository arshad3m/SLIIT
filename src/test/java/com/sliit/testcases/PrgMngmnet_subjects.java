package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class PrgMngmnet_subjects extends TestBase{
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_subjects(Hashtable<String, String> data) throws InterruptedException, IOException {
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("prg_management_XPATH");
		
		click("subjects_menu_XPATH");
		
		click("sbj_create_new_XPATH");
		
		type("sbj_code_XPATH",data.get("code"));
		type("sbj_name_XPATH",data.get("name"));
		type("sbj_credits_XPATH",data.get("credits"));
		
		click("sbj_departments_XPATH");
		Thread.sleep(5000);
		type("sbj_dept_search_XPATH","technology");
		click("sbj_dept_select_XPATH");

		
//		setStatus("sbj_status_XPATH", "Active");
		
		click("sbj_module_incharge_XPATH");
		click("sbj_module_select_XPATH");
		
		click("sbj_next_XPATH");
		
		type("sbj_session_code_XPATH",data.get("sessionname"));
		type("sbj_session_name_XPATH",data.get("sessioncode"));
		type("sbj_session_priority_XPATH",data.get("priority"));
		setStatus("sbj_session_status_XPATH", "Active");
		click("sbj_session_add_XPATH");
		
		
		
		click("sbj_submit_XPATH");
		
		
		
		
		verifyRecordSave();
		
		
		

		
		
	}
	
	@Test(enabled = true, priority = 2)
	public void searchSubjects() throws InterruptedException, IOException {
		


		

		//Click program management
		click("prg_management_XPATH");

		click("subjects_menu_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", "SUB009");

		//Verify search results
		verifySearchResults(4, "SUB009");
	}

	
	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=5)
	public void filterSubjects(Hashtable<String, String> data) throws NumberFormatException, InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		//Click institute management
		click("prg_management_XPATH");

		click("subjects_menu_XPATH");
		
		//Pass column number and search keyword
		filter(Integer.parseInt(data.get("column")),data.get("keyword"));
		
		Thread.sleep(6000);
		
		List<String> vals= getColumnValues(Integer.parseInt(data.get("column")));
		
		for(int i=0; i<vals.size();i++) {
			
			verifyContains(vals.get(i), data.get("keyword"));
		}
		
	}
	

}
