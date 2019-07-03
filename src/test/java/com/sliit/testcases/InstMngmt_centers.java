package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_centers extends TestBase {
	
	
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
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority=1)
	public void add_new_centers(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("inst_management_XPATH");

		click("centers_XPATH");

		Thread.sleep(3000);
		
		click("center_create_new_XPATH");

		type("center_code_XPATH", data.get("code"));
		
		type("center_name_XPATH", data.get("name"));

		click("center_location_XPATH");
		
//		type("center_type_location_XPATH",data.get("location"));
		
		//select the first location in the dropdown
		click("center_location_dd_value_XPATH");
		
		setStatus("center_status_XPATH",data.get("status"));
		
		click("center_save_XPATH");
		
		verifyRecordSave();
		
		
		
	}
	

}
