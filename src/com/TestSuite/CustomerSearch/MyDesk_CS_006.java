/*Test Scenario 		: PEGA - NAD (MyDesk_CS_006)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Raja
 *Created on			: 07 Oct 2016
 */

package com.TestSuite.CustomerSearch;

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
import com.Engine.LoadEnvironment;
import com.Engine.SeleniumSetup;
import com.Enumerations.Generic;
import com.Enumerations.Generic.CustomerSearchDB;
import com.Enumerations.Generic.iframes;
import com.Enumerations.MyDeskEnumerations.CustomerInformationBar;
import com.Enumerations.MyDeskEnumerations.SearchBy;
import com.SharedModules.Constants;
import com.SharedModules.DBTestDataSetup;
import com.SharedModules.DatabaseTestData.searchByDataa;
import com.SharedModules.DbUtilities;
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.DataProviderExcelReader;
import com.Utils.ReadExcelSheet;

public class MyDesk_CS_006 extends SeleniumSetup implements Constants,Generic {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_CS_006")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_CustomerSearch,"MyDesk_CS_006"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_CS_006")
	public void MyDesk_CS_006_Method(String SCRIPT_ID, String ROW)
			throws Exception {
		/************************** Objects Declaration **********************************/

		Map<String, String> DATA_MAP = TestPreProcessing(SCRIPT_ID, ROW,LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_CustomerSearch);

		/*************************************** Objects Declaration ************************************/
		
		Account_Page AP=new Account_Page(driver, Report);
		Cases_Page CP=new Cases_Page(driver, Report);
		Common_Functions Com=new Common_Functions(driver, Report);
		Customer_Page CuP=new Customer_Page(driver, Report);
		Home_Page HP=new Home_Page(driver, Report);
		Summary_Page SP=new Summary_Page(driver, Report);
		VerifyCaller_Page VP=new VerifyCaller_Page(driver, Report);
		DbUtilities DBU = new DbUtilities(Report);
		
		/*************************************** Variables Declaration ************************************/
		
		String Username=DATA_MAP.get("OPERATOR_ID");
		String Password=DATA_MAP.get("PASSWORD");
		String CLI=DATA_MAP.get("CLI");
		String ACCOUNT=DATA_MAP.get("ACCOUNT");
		String Status=DATA_MAP.get("STATUS");
		String PC=DATA_MAP.get("POSTCODE");
		String LN=DATA_MAP.get("LASTNAME");
		String FN=DATA_MAP.get("FIRSTNAME");
		
		/*************************************** Data Preparation ******************************************/
		
//		Map<String,String> dbmap=DBU.RetrieveCustomerDetails(CLI);		
		
		/***************************** START OF MAIN TEST ****************************/
		
		driver.get(LoadEnvironment.PEGA_NAD_URL);
		
		Com.Pega_Login(LoadEnvironment.PEGA_USERNAME, LoadEnvironment.PEGA_PASSWORD);

		// Home Page
		HP.wait_for_Home_Page_To_Load();
		HP.Click_New_Interaction_Button();
		Com.switchToFrame(iframes.search);
		HP.wait_for_Identify_Customer_Page_To_Load();
		HP.Customer_Search_By_ButtonClick(SearchBy.Postcode);
		System.out.println("PostCode"+PC);
		HP.Customer_Search_By_InputValue(SearchBy.Postcode,PC);
		HP.Click_Search_Button();
		HP.Verify_SearchResult_Count("First Name", "Multiple");
		HP.Verify_Click_Unique_PostCode_SearchResults(LN,FN);
		
		//Verify Caller Page
		VP.wait_for_Verify_Caller_Page_to_load();
		Com.select_an_action_to_perform("Identify Customer");
		HP.wait_for_Identify_Customer_Page_To_Load();
		HP.wait_for_Search_Result_Pane_To_Load();
		HP.Verify_Click_Unique_SearchResults(CLI,Status);
		HP.Verify_SearchResult_Count("Full Name", "Single");
		
		//Exit interaction page
		Com.select_an_action_to_perform("Exit Interaction");
		Com.enter_Exit_interaction_Comments("Identfiy Customer page");
		Com.Pega_logout();
		
		/***************************** END OF MAIN TEST ****************************/
	}

}
