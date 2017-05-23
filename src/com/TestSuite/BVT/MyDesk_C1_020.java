
/*Test Scenario 		: PEGA - NAD (MyDesk_C1_018)
 *Test Case Names 		: interaction updates_Marketing preference
 *Package				: Customer180
 *Created By			: Padma
 *Created on			: 25 jan 2017
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
import com.Engine.LoadEnvironment;
import com.Engine.SeleniumSetup;
import com.Enumerations.Generic;
import com.Enumerations.Generic.CustomerSearchDB;
import com.Enumerations.Generic.iframes;
import com.Enumerations.MyDeskEnumerations.CustomerInformationBar;
import com.Enumerations.MyDeskEnumerations.SearchBy;
import com.ObjectIdentifiers.PEGAConstants;
import com.SharedModules.Constants;
import com.SharedModules.DBTestDataSetup;
import com.SharedModules.DbUtilities;
import com.SharedModules.DatabaseTestData.searchByDataa;
import com.SharedModules.NewDatabase;
import com.SharedModules.NewDatabase.searchByData;
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.DataProviderExcelReader;
import com.Utils.ReadExcelSheet;

public class MyDesk_C1_020 extends SeleniumSetup implements Constants,Generic,PEGAConstants {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_C1_020")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_BVTPACK,"MyDesk_C1_020"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_C1_020")
	public void MyDesk_C1_020_Method(String SCRIPT_ID, String ROW)
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
		DBTestDataSetup DBTDST= new DBTestDataSetup(Report);
		DbUtilities db=new DbUtilities(Report);
		/*************************************** Variables Declaration ************************************/
		String ACCOUNTTYPE=DATA_MAP.get("ACCOUNTTYPE");
		/*************************************** Data Preparation ******************************************/
		
		String data;

		NewDatabase dbn = new NewDatabase(Report);
		data = dbn.getDataNEW_AIP("291",searchByData.blank,"",true,false,"TV Store"); // Str_PackageId ,Search by, search value [ NOT_PropID], Contract , Credit class check
		if(data==null){
			Report.fnReportFailAndTerminateTest("DataGetter", "No Data Found");
		}
		String Str_Account= data.substring(data.indexOf(',')+1);
		String Str_CLI=data.substring(0, data.indexOf(','));
		
		
		/***************************** START OF MAIN TEST ****************************/

				driver.get(LoadEnvironment.PEGA_NAD_URL);
		
				Com.Pega_Login(LoadEnvironment.PEGA_USERNAME, LoadEnvironment.PEGA_PASSWORD);
		
//				Home Page
				HP.wait_for_Home_Page_To_Load();
				HP.Click_New_Interaction_Button();
				Com.switchToFrame(iframes.search);
				HP.wait_for_Identify_Customer_Page_To_Load();
				HP.Customer_Search_By_ButtonClick(SearchBy.CLI);
				HP.Customer_Search_By_InputValue(SearchBy.CLI,Str_CLI,"");
				HP.Click_Search_Button();
		
//				Verify Caller Page
				VP.wait_for_Verify_Caller_Page_to_load();
				VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
				VP.select_NumberOf_Required_Additional_Question_CheckBox(3);
				Com.click_Submit_Button();
				
//				Summary page
				SP.wait_for_Summary_Page_to_load();	
				Com.switchToFrame(iframes.defaultcontent);
				VP.verify_the_CIB_Caller_name("(Account Holder)");
				VP.verify_the_CIB_IDV_Status_text("VERIFIED");
				Com.switchToFrame(iframes.search);
			
//				Entering Action
				CP.hover_on_Add_Case_button();
				Thread.sleep(3000);
				CP.select_Action_Category_for_Cases("TalkSafe");
				CP.click_on_Add_cases_button();				
//							
//				TalkSafe
				Thread.sleep(3000);
						
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.create);
				CP.waitFor_Specific_Task_PageLoad("TalkSafe");				

//				//TalkSafe Enroll
				
				Thread.sleep(2000);
				CP.Talksafe_checkAccountType("Account Holder");
				CP.Talksafe_EnrollmentOption_NotNow("Not Now","Select");
				Com.click_Submit_Button();					
		
				//Wrap Up
				Thread.sleep(4500);

				Com.click_on_Wrap_up_button();
				Com.enter_Wrap_up_Comments();
				Com.click_Submit_Button();
				
				db.VerifyInteractionHistory(Str_CLI,"MYDESK","Not Now","TalkSafe Update");
				
				Com.Pega_logout();

		/***************************** END OF MAIN TEST ****************************/
	}

}