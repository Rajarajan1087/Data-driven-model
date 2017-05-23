/*Test Scenario 		: PEGA - NAD (MyDesk_C1_001)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Rajan
 *Created on			: 18 Oct 2016
 */

package com.TestSuite.Customer180;

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
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.DataProviderExcelReader;
import com.Utils.ReadExcelSheet;

public class MyDesk_C1_001 extends SeleniumSetup implements Constants,Generic,PEGAConstants {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_C1_001")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Customer180,"MyDesk_C1_001"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_C1_001")
	public void MyDesk_C1_001_Method(String SCRIPT_ID, String ROW)
			throws Exception {
		/************************** Objects Declaration **********************************/

		Map<String, String> DATA_MAP = TestPreProcessing(SCRIPT_ID, ROW,LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Customer180);

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
		/*************************************** Variables Declaration ************************************/

		String Username=DATA_MAP.get("OPERATOR_ID");
		String Password=DATA_MAP.get("PASSWORD");
		String CLI=DATA_MAP.get("CLI");
		String ACCOUNT=DATA_MAP.get("ACCOUNT");
		String Status=DATA_MAP.get("STATUS");
		String Str_CLI= "";
		String Str_Account = "";
		/*************************************** Data Preparation ******************************************/
		/*String[] Data = DBTDST.Retrieve_SingleData_Valid("285", searchByDataa.blank, "", false, false).split(",");
		Str_CLI = Data[0];Str_Account=Data[1];
		Map<String,String> dbmap=DBU.RetrieveCustomerDetails(Str_CLI);

		System.out.println(dbmap.get("CLI"));
		System.out.println(dbmap.get("FIRSTNAME"));*/
		/***************************** START OF MAIN TEST ****************************/

				driver.get(LoadEnvironment.PEGA_NAD_URL);
		
				Com.Pega_Login(LoadEnvironment.PEGA_USERNAME, LoadEnvironment.PEGA_PASSWORD);
		
				// Home Page
				HP.wait_for_Home_Page_To_Load();
				HP.Click_New_Interaction_Button();
				Com.switchToFrame(iframes.search);
				HP.wait_for_Identify_Customer_Page_To_Load();
				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Call_Reason,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Monthly_Spend,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Total_Transfers,CustomerInformationBar.Hold_Time);
				HP.Customer_Search_By_ButtonClick(SearchBy.CLI);
				HP.Customer_Search_By_InputValue(SearchBy.CLI,CLI,"");
				HP.Click_Search_Button();
		
				//Verify Caller Page
				VP.wait_for_Verify_Caller_Page_to_load();
				VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
				VP.select_the_caller_Identification_type("Account Holder");
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.search);
				VP.select_the_call_Reason_GeneralEnquiry();
				Thread.sleep(2000);
				Com.click_Submit_Button();
				
				//Summary page
				SP.wait_for_Summary_Page_to_load();	
				Com.switchToFrame(iframes.defaultcontent);
				VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
				Com.switchToFrame(iframes.search);
				
				//Cases page
				
				//Entering Action
				CP.hover_on_Add_Case_button();
				Thread.sleep(3000);
				CP.select_Action_Category_for_Cases("Verify Caller");
				CP.click_on_Add_cases_button();
				
				
				
				//Verify Caller
				Thread.sleep(3000);
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.create);
				CP.waitFor_Specific_Task_PageLoad("Verify Caller");
				
				VP.select_the_caller_Identification_type("Account Holder");
				VP.verify_the_password_displayed_for_Telephone_Password("yesboss123");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("Can you confirm your house number & postcode?-11 HOLDEN STREET, LONDON, SW11 5UW");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("How do we send the bills to you each month?-PAPER");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("What is your mobile number on the account?-07474747474");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("What is the email address you have registered with us?-catherine27409@nowhere.com");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("What are the last 4 digits of your debit/credit card registered for recurring payment with TalkTalk?-0007");
				
				
				
				
				VP.select_the_caller_Identification_type("Nominated User/ Power of Attorney");
				VP.verify_the_caller_Name("RabuelA&DImran");
				VP.verify_the_password_displayed_for_Telephone_Password("NoBoss123");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("Can you confirm your house number & postcode?-Apartment 36, Barton Court, Central Way, WARRINGTON, UK, WA2 7TE");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("No-yes");
				
				Com.click_Submit_Button();
				//Case Open alert


				Com.click_on_Wrap_up_button_CaseOpen_checkAlert();
				
				
				//Back to Case Cancellation
				
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.create);
				Com.select_An_Action_To_Perform_ForCases("Cancel this work");
				Thread.sleep(3000);
				Com.enter_Exit_interaction_Comments("Cancelling task in Summary page");
				Com.click_Submit_Button();
				CP.click_Confirm_Button();
				
				
							
				
				//Wrap Up
				Com.click_on_Wrap_up_button();
				Com.enter_Wrap_up_Comments();
				Com.click_Submit_Button();
				Com.Pega_logout();

		/***************************** END OF MAIN TEST ****************************/
	}

}
