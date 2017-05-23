/*Test Scenario 		: PEGA - NAD (MyDesk_C1_009_DB)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Raja
 *Created on			: 07 Oct 2016
 */

package com.TestSuite.Payments;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BusinessModules.MyDesk.Account_Page;
import com.BusinessModules.MyDesk.CTI_Actions;
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
import com.Enumerations.MyDeskEnumerations.CardType;
import com.Enumerations.MyDeskEnumerations.CustomerInformationBar;
import com.Enumerations.MyDeskEnumerations.PhoneNumberSummary;
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

public class MyDesk_PayFix_002 extends SeleniumSetup implements Constants,Generic,PEGAConstants {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_PayFix_002")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Payments,"MyDesk_PayFix_002"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_PayFix_002")
	public void MyDesk_PayFix_002_Method(String SCRIPT_ID, String ROW)
			throws Exception {
		/************************** Objects Declaration **********************************/
		
		System.out.println("Inside test Method");
		Map<String, String> DATA_MAP = TestPreProcessing(SCRIPT_ID, ROW,LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Payments);

		/*************************************** Objects Declaration ************************************/

		Account_Page AP=new Account_Page(driver, Report);
		Cases_Page CP=new Cases_Page(driver, Report);
		Common_Functions Com=new Common_Functions(driver, Report);
		Customer_Page CuP=new Customer_Page(driver, Report);
		Home_Page HP=new Home_Page(driver, Report);
		Summary_Page SP=new Summary_Page(driver, Report);
		CTI_Actions CA=new CTI_Actions(driver, Report);
		VerifyCaller_Page VP=new VerifyCaller_Page(driver, Report);
		DbUtilities DBU = new DbUtilities(Report);
		DBTestDataSetup DBTDST= new DBTestDataSetup(Report);
		/*************************************** Variables Declaration ************************************/

		String Username=DATA_MAP.get("OPERATOR_ID");
		String Password=DATA_MAP.get("PASSWORD");
		String CLI=DATA_MAP.get("CLI");
		String ACCOUNT=DATA_MAP.get("ACCOUNT");
		String PACKAGE=DATA_MAP.get("PACKAGE");
		String MOB_CLI=DATA_MAP.get("MOB_CLI");
		String MOB_ACC=DATA_MAP.get("MOB_ACC");


		/*************************************** Data Preparation ******************************************/
		
//		Map<String,String> dbmap=DBU.RetrieveCustomerDetails(CLI,ACCOUNT);
//		Map<String,String> dbmap_Mob=DBU.RetrieveCustomerDetails(MOB_CLI,MOB_ACC);
//		Map<String,String> dbmap1=DBU.RetrieveAddressDetails(CLI,ACCOUNT);
//		String accountbalance=dbmap.get("outstanding_balance");
		
//		String accountbalance="0.01";
//		if(accountbalance.equalsIgnoreCase("0"))
//			accountbalance="0.00";
		

		
		/***************************** START OF MAIN TEST ****************************/

				driver.get(LoadEnvironment.PEGA_NAD_URL);
				Thread.sleep(3000);
		
				Com.Pega_Login(LoadEnvironment.PEGA_USERNAME, LoadEnvironment.PEGA_PASSWORD);
		
//				Home Page
				HP.wait_for_Home_Page_To_Load();
				
				

				
				HP.Click_New_Interaction_Button();
				Com.switchToFrame(iframes.search);
				HP.wait_for_Identify_Customer_Page_To_Load();
				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Hold_Time);
				
				
				HP.Customer_Search_By_ButtonClick(SearchBy.CLI);
				HP.Customer_Search_By_InputValue(SearchBy.CLI,CLI,"");
				HP.Click_Search_Button();
				
//				HP.Click_Specific_PostCodeOrCLI_SearchResult_Row("Full Name", 1);
//				Thread.sleep(1000);
//				Com.click_Submit_Button();
			
		
//				Verify Caller Page
				VP.wait_for_Verify_Caller_Page_to_load();
				VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Hold_Time);
				VP.select_NumberOf_Required_Additional_Question_CheckBox(5);
				Com.click_Submit_Button();
				
//				Summary page
				SP.wait_for_Summary_Page_to_load();	
				Com.switchToFrame(iframes.defaultcontent);
				VP.verify_the_CIB_Caller_name("(Account Holder)");
				
//				String CallerName=dbmap.get("TITLE")+" "+(dbmap.get("FIRSTNAME"))+" "+(dbmap.get("LASTNAME"));
				
				VP.verify_the_CIB_IDV_Status_text("VERIFIED");
				VP.verify_the_CIB_PhoneNumber(CLI);
				VP.verify_the_CIB_Account_Number(ACCOUNT);
				VP.verify_the_CIB_Package_name(PACKAGE);
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.search);
				
				
//				Customer page
				
				
				SP.switch_Between_tabs("Summary");
				
				
//				Choosing payment case 
				
				
				CP.hover_on_Add_Case_button();
				CP.select_Action_Category_for_Cases("Payment");
				CP.click_on_Add_cases_button();
				
//				Payment Page
				Thread.sleep(1000);
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.create);
				CP.waitFor_Specific_Task_PageLoad("Payment");
				
				
//				PayFix001
				CP.verify_Payment_payFor_Elements(true, false);	
				
				CP.verify_Payment_PaymentOptions_Elements(false, true, false);
				
				
//				PayFix002				
				
				String[] cardvalues=CP.verify_Get_Latest_RegisteredCard_Details_From_List();
				System.out.println(cardvalues);
				
				CP.Click_First_Record_In_CreditCardList(cardvalues[0]);
				Thread.sleep(1000);
				CP.verify_the_Customer_NameOnCard_Details_label(cardvalues[1]);
				CP.verify_EndDate_Creditcard_Payment_label(cardvalues[2], cardvalues[3]);

				

//				Canceling the payment task
				Com.select_An_Action_To_Perform_ForCases("Cancel this work");
				Thread.sleep(3000);				
				Com.enter_Exit_interaction_Comments("Cancelling task in Summary page");
				Com.click_Submit_Button();
				CP.click_Confirm_Button();
				
				
//				Wrap Up
				Com.click_on_Wrap_up_button();
				Com.enter_Wrap_up_Comments();
				Com.click_Submit_Button();
				
				Thread.sleep(2000);
				Com.switchToFrame(iframes.defaultcontent);
				Thread.sleep(1000);			
				Com.Pega_logout();

		/***************************** END OF MAIN TEST ****************************/
	}

}
