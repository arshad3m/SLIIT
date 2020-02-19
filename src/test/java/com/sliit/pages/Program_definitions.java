package com.sliit.pages;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;
import com.sliit.base.TestBase;

public class Program_definitions extends TestBase{
	
	
	public void selectEntryCriteria() throws InterruptedException {
		
		//get number of criteria in the entry criteria form group
		
		String criteria_XPATH="//table//tbody//tr";
		int count=driver.findElements(By.xpath(criteria_XPATH)).size();
		
		//select the first value of the each criteria
		for(int i=1;i<count+1;i++) {
			String temp_XPATH=criteria_XPATH+"["+i+"]/td[2]/div/div";
			OR.setProperty("temp_XPATH", temp_XPATH);
		
			selectFromDropdown("temp_XPATH",3);
			
		}
		
		
	}

}
