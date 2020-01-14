package com.sliit.pages;

import org.openqa.selenium.By;

import com.sliit.base.TestBase;

public class Program_definitions extends TestBase{
	
	
	public void selectEntryCriteria() throws InterruptedException {
		
		//get number of criterias in the entry criteria form group
		
		String criteria_XPATH="//div[text()='Module']/../parent::div/../div";
		int count=driver.findElements(By.xpath(criteria_XPATH)).size();
		
		//select the first value of the each criteria
		for(int i=2;i<count+1;i++) {
			String temp_XPATH=criteria_XPATH+"["+i+"]/div/div/*";
			OR.setProperty("temp_XPATH", temp_XPATH);
			selectFirstValue("temp_XPATH");
		}
		
		
	}

}
