package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmt_institute_details extends TestBase {

	@Test(enabled=true,dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void add_institute_details(Hashtable<String, String> data) throws IOException, InterruptedException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("institute_details_XPATH");
		
		Thread.sleep(3000);
		
		click("inst_dtls_clear_XPATH");
		
		

		type("inst_dtls_name_XPATH", data.get("name"));

		type("inst_dtls_address_XPATH", data.get("address"));

		type("inst_dtls_email_XPATH", data.get("email"));

		type("inst_dtls_telephone_XPATH", data.get("telephone"));
		
		click("inst_dtls_plus_button_XPATH");
		
		//uploading a logo
		upload("inst_dtls_upload_XPATH",data.get("logo"));

		click("inst_dtls_save_XPATH");
		
		verifyRecordSave();
		
	}
	
	

	
	

}
