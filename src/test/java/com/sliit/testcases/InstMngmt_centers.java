package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_centers extends TestBase {
	/* Author Jayashani
	 * Add a new center
	 */
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority=1)
	public void add_new_centers(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		
		//Click institute management
		click("inst_management_XPATH");

		//Click centers
		click("centers_XPATH");

		//Click create new
		click("centers_create_new_XPATH");

		Thread.sleep(4000);

		//Click clear button
		click("centers_clear_changes_XPATH");

		//Enter center code
		type("centers_code_XPATH", data.get("code"));

		//Enter center name
		type("centers_name_XPATH", data.get("name"));

		//Select location status
		click("centers_status_XPATH");

		// Select checkbox based on Active or Inactive
		if (data.get("status").equals("Active")) {
			click("lcnts_active_XPATH");
		} else {
			click("lcnts_inactive_XPATH");
			click("lcnts_confirm_inactivation_XPATH");
		}

		//Click save button
		click("lcnts_save_XPATH");

		
		//Verify record is saved
		verifyRecordSave();
		
	}
	
	@Test(enabled=false)
	public void search_centers_by_id() throws InterruptedException {
		
		click("inst_management_XPATH");

		click("centers_XPATH");

		Thread.sleep(3000);
		
		
		List<String> code_values=getColumnValues(1);
		
	
		for(int i=0;i<code_values.size();i++) {
			System.out.println(code_values.get(i));
		}
		
	}

}
