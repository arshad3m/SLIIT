package com.sliit.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sliit.base.TestBase;
import com.sliit.pages.Program_definitions;
import com.sliit.utilities.TestUtil;

public class PrgMngmnt_program_definitions extends TestBase {

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void createNewProgramDefinitions(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		Program_definitions pd = new Program_definitions();
		
		// Enter basic details
		click("prg_management_XPATH");
		click("prgm_program_definitions_XPATH");
		click("create_new_XPATH");
		type("prgm_code_XPATH", data.get("code"));
		type("prgm_name_XPATH", data.get("name"));
		selectFirstValue("prgm_faculties_XPATH");
		selectFirstValue("prgm_aw_institutes_XPATH");
		selectFirstValue("prgm_program_XPATH");
		selectFirstValue("prgm_cordinator_XPATH");
		click("prgm_next_XPATH");

		// Enter entry criteria
		selectFirstValue("prgm_entry_criteria_XPATH");
		pd.selectEntryCriteria();
		type("prgm_priority_XPATH", data.get("priority"));
		type("prgm_no_of_passes_XPATH", data.get("passes"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter years
		type("prgm_year_name_XPATH",data.get("year"));
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter Semesters
		type("prgm_semester_name_XPATH",data.get("semester"));
		selectFirstValue("prgm_semester_year_XPATH");
		click("prgm_add_XPATH");
		click("prgm_next_XPATH");
		Thread.sleep(3000);

		// Enter modules and submit
		selectFirstValue("prgm_semester_year_XPATH");
		selectFirstValue("prgm_module_semester_XPATH");
		selectFirstValue("prgm_module_XPATH");
		click("prgm_add_XPATH");
		click("prgm_submit_XPATH");
		Thread.sleep(3000);
		
		verifyRecordSave();

		

		
		
	}

}
