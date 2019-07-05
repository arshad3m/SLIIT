package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmnt_departments extends TestBase{
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
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

		Thread.sleep(3000);

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
		
		verifyTableDescendingOrder("DEPT");


	}

}
