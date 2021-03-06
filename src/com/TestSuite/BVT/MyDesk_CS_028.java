/*Test Scenario 		: PEGA - NAD (MyDesk_CS_018)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Raja
 *Created on			: 07 Oct 2016
 */

package com.TestSuite.BVT;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BusinessModules.MyDesk.Account_Page;
import com.BusinessModules.MyDesk.Cases_Page;
import com.BusinessModules.MyDesk.Common_Functions;
import com.BusinessModules.MyDesk.Customer_Page;
import com.BusinessModules.MyDesk.Home_Page;
import com.BusinessModules.MyDesk.Summary_Page;
import com.BusinessModules.MyDesk.VerifyCaller_Page;
import com.EMS.SGOGenerator;
import com.Engine.LoadEnvironment;
import com.Engine.SeleniumSetup;
import com.Enumerations.Generic;
import com.Enumerations.Generic.CustomerSearchDB;
import com.Enumerations.Generic.iframes;
import com.Enumerations.MyDeskEnumerations.CustomerInformationBar;
import com.Enumerations.MyDeskEnumerations.SearchBy;
import com.SharedModules.Constants;
import com.SharedModules.DbUtilities;
import com.SharedModules.RandomGenerator;
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.DataProviderExcelReader;
import com.Utils.ReadExcelSheet;

public class MyDesk_CS_028 extends SeleniumSetup implements Constants,Generic {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_CS_028")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_BVTPACK,"MyDesk_CS_028"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_CS_028")
	public void MyDesk_CS_028_Method(String SCRIPT_ID, String ROW)
			throws Exception {
		/************************** Objects Declaration **********************************/

		Map<String, String> DATA_MAP = TestPreProcessing(SCRIPT_ID, ROW,LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_BVTPACK);

		/*************************************** Objects Declaration ************************************/
		
		Account_Page AP=new Account_Page(driver, Report);
		Cases_Page CP=new Cases_Page(driver, Report);
		Common_Functions Com=new Common_Functions(driver, Report);
		Customer_Page CuP=new Customer_Page(driver, Report);
		Home_Page HP=new Home_Page(driver, Report);
		Summary_Page SP=new Summary_Page(driver, Report);
		VerifyCaller_Page VP=new VerifyCaller_Page(driver, Report);
		DbUtilities DBU = new DbUtilities(Report);

		/*************************************** Data Preparation ******************************************/
		
		Map<String,String> dbmap1=DBU.RetrieveNominatedUser();	
		
		String CLI=dbmap1.get("CLI");	
			
		/***************************** START OF MAIN TEST ****************************/
		
		driver.get(LoadEnvironment.PEGA_NAD_URL);
		
		Com.Pega_Login(LoadEnvironment.PEGA_USERNAME, LoadEnvironment.PEGA_PASSWORD);

		// Home Page
		HP.wait_for_Home_Page_To_Load();
		HP.Click_New_Interaction_Button();
		Com.switchToFrame(iframes.search);
		HP.wait_for_Identify_Customer_Page_To_Load();

		HP.Customer_Search_By_ButtonClick(SearchBy.CLI);
		HP.Customer_Search_By_InputValue(SearchBy.CLI,CLI,"");
	
		HP.Click_Search_Button();
		
		//Verify Caller Page
		VP.wait_for_Verify_Caller_Page_to_load();
		VP.select_the_caller_Identification_type("Nominated User/ Power of Attorney");
		Com.select_an_action_to_perform("Bypass Verification");
		
		VP.wait_for_Bypass_Verification_Page_To_Load();
		Com.select_an_action_to_perform("Identify Customer");
		HP.wait_for_Identify_Customer_Page_To_Load();	
		
		//Exit interaction
		Com.select_an_action_to_perform("Exit Interaction");
		Com.enter_Exit_interaction_Comments("Identfiy Customer page");
		Com.Pega_logout();
		
		/***************************** END OF MAIN TEST ****************************/
	}

}
