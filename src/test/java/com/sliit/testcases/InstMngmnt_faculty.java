package com.sliit.testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sliit.base.TestBase;
import com.sliit.utilities.TestUtil;

public class InstMngmnt_faculty extends TestBase{
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_new_faculty(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("inst_management_XPATH");

		click("faculty_XPATH");

		Thread.sleep(3000);

		click("fclt_create_new_XPATH");

		type("fclt_code_XPATH", data.get("code"));

		type("fclt_name_XPATH", data.get("name"));

		click("fctl_center_XPATH");
		
		click("fctl_center_select_all_XPATH");
		
		click("fctl_center_centers_label_XPATH");

		Thread.sleep(3000);


		setStatus("dept_status_XPATH", data.get("status"));

		click("fctl_save_XPATH");

		verifyRecordSave();
		
		verifyTableDescendingOrder("Auto_FCT");


	}
	
	@Test(enabled=true)
	public void verify_values_in_centers_dropdown() throws InterruptedException {
		
		click("inst_management_XPATH");

		click("centers_XPATH");

		Thread.sleep(3000);

		List<String> center_names=getActiveColumnValues(2, 4);
		
		click("faculty_XPATH");

		Thread.sleep(3000);

		click("fclt_create_new_XPATH");

		click("fctl_center_XPATH");
		
		//Retrieve all the values in the dropdown
		List<WebElement> elements=driver.findElements(By.xpath(OR.getProperty("fctl_center_list_XPATH")));

		//Retrive the text value of each element
		List<String> text_values=new ArrayList<String> ();
		
		for(int i=0;i<elements.size();i++) {
			text_values.add(elements.get(i).getAttribute("innerText"));
		}
		
		center_names.sort(null);
		text_values.sort(null);

		
		if(center_names.equals(text_values)) {
			test.log(LogStatus.INFO, "Values in the centers dropdown match with the centers available in the system");
			assertTrue(true);
			
		}
		
		else {

			
			test.log(LogStatus.INFO, "Miss match in dropdown list values");
			test.log(LogStatus.INFO, "Available centers in the system : "+center_names);
			test.log(LogStatus.INFO, "Values in Centers dropdown: "+text_values);
			assertTrue(false);
			
		}
		
	}
	@Test(enabled =false,dataProviderClass = TestUtil.class, dataProvider = "dp", priority=3)
	public void search_faculty(Hashtable<String, String> data) throws InterruptedException, IOException {

		//Click institute management
		click("inst_management_XPATH");

		click("document_type_XPATH");

		Thread.sleep(3000);

		//Enter search keyword in the search box
		search("search_box_XPATH", data.get("keyword"));

		//Verify search results
		verifySearchResults(4, data.get("keyword"));

	}
}
