package com.sliit.testcases;

import java.util.List;

import org.testng.annotations.Test;

import com.sliit.base.TestBase;

public class InstMngmt_centers extends TestBase {
	
	
	@Test(enabled=true)
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
