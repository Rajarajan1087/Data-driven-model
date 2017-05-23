/*Test Scenario 		: PEGA - NAD (MyDesk_C1_009)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Raja
 *Created on			: 07 Oct 2016
 */

package com.TestSuite.E2E;

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

public class MyDesk_C1_009 extends SeleniumSetup implements Constants,Generic,PEGAConstants {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_C1_009")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Customer180,"MyDesk_C1_009"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_C1_009")
	public void MyDesk_C1_009_Method(String SCRIPT_ID, String ROW)
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
		String pack=DATA_MAP.get("PACKAGE");
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
		
				Com.Pega_Login(Username, Password);
		
				// Home Page
				HP.wait_for_Home_Page_To_Load();
				HP.Click_New_Interaction_Button();
				Com.switchToFrame(iframes.search);
				HP.wait_for_Identify_Customer_Page_To_Load();
				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Call_Reason,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Monthly_Spend,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Total_Transfers,CustomerInformationBar.Hold_Time);
				
				/*Click Hide*/
				
				VP.click_on_Hide_Show_details(true);
				Com.verify_the_Elements_NotPresent_in_Customer_Information_Bar(CustomerInformationBar.Hold_Time);
				HP.Customer_Search_By_ButtonClick(SearchBy.CLI);
				HP.Customer_Search_By_InputValue(SearchBy.CLI,CLI,"");
				HP.Click_Search_Button();
		
				//Verify Caller Page
				VP.wait_for_Verify_Caller_Page_to_load();
				VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
				Com.verify_the_Elements_NotPresent_in_Customer_Information_Bar(CustomerInformationBar.Hold_Time);
				
				/*Click Show*/
				
				VP.click_on_Hide_Show_details(false);
				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Call_Reason,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Monthly_Spend,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Total_Transfers,CustomerInformationBar.Hold_Time);
				
				
				//Fraud message check
				
				Com.verify_Alert_Exists(CustomerAlert_Fraud);
				Com.verify_Alert_Exists(CustomerAlert_Deceased);
				Com.verify_Alert_Exists(CustomerAlert_Accessibility);
				
				
				VP.verify_the_caller_Identification_type("Account Holder,Non-Account Holder,Nominated User/ Power of Attorney");
				VP.verify_the_call_Reason("General Enquiry?,Fault?,Outbound call to landline?,Account Status?,Make a Payment/Check account balance?");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("Can you confirm your house number & postcode?-19 HAWKE CLOSE, RAWMARSH, ROTHERHAM, S62 7NL");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("How do we send the bills to you each month?-PAPER");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("What is your mobile number on the account?-N/A");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("What is the email address you have registered with us?-N/A");
				VP.verify_the_answers_displayed_for_Additional_Questions_Answers("What are the last 4 digits of your debit/credit card registered for recurring payment with TalkTalk?-0007");
				VP.select_the_Additional_Questions_answered_correctly("Can you confirm your house number & postcode?-19 HAWKE CLOSE, RAWMARSH, ROTHERHAM, S62 7NL");
				VP.select_the_Additional_Questions_answered_correctly("How do we send the bills to you each month?-PAPER");
				Com.click_Submit_Button();
				
				//Summary page
				SP.wait_for_Summary_Page_to_load();	
				Com.switchToFrame(iframes.defaultcontent);
				VP.verify_the_CIB_Caller_name("(Account Holder)");
				String CallerName=SP.getCustomerName_In_CIB();
				VP.verify_the_CIB_IDV_Status_text("VERIFIED");
				VP.verify_the_CIB_PhoneNumber(CLI);
				VP.verify_the_CIB_Account_Number("1000004951");
				VP.verify_the_CIB_Package_name("TalkTalk Essentials");
				Com.verify_Alert_Exists(CustomerAlert_Fraud);
				Com.verify_Alert_Exists(CustomerAlert_Deceased);
				Com.verify_Alert_Exists(CustomerAlert_Accessibility);
				Com.switchToFrame(iframes.search);
				
				
				/*SP.validate_the_Contract_End_Date_in_Summary_Tab("12-Apr-2011");
				SP.validate_the_Remaining_Contract_Term_in_Summary_Tab("0");
				
				
				SP.validate_the_Last_Bill_Date_in_Summary_Tab("04-Mar-2016");
				SP.validate_the_Last_Bill_Amount_in_Summary_Tab("£83.61");
				SP.validate_the_Payment_Due_Date_in_Summary_Tab("02-Mar-2016");
				SP.validate_the_Outstanding_Balance_in_Summary_Tab("£0.00");
				SP.validate_the_Unbilled_Usage_in_Summary_Tab("£0.00");
				SP.validate_the_Next_Bill_Date_in_Summary_Tab("04-Dec-2016");
				
				
					
				//Customer page
				
				SP.switch_Between_tabs("Customer");
				
				//Customer Account Summary
				
				CuP.validate_the_Account_Number_in_Customer_Account_Summary_of_Customer_Tab("1000004951");
				CuP.validate_the_Account_Status_in_Customer_Account_Summary_of_Customer_Tab("Active");
				CuP.validate_the_Phone_Number_in_Customer_Account_Summary_of_Customer_Tab("01709710391");
				CuP.validate_the_Package_Name_in_Customer_Account_Summary_of_Customer_Tab("TalkTalk Essentials");
				CuP.validate_the_Package_Status_in_Customer_Account_Summary_of_Customer_Tab("DISCONNECTED");
				
				
				
				CuP.validate_the_Phone_Number_in_Customer_Account_Summary_of_Customer_Tab("HM01709710391");
				CuP.validate_the_Package_Name_in_Customer_Account_Summary_of_Customer_Tab("TalkTalk Plus");
				CuP.validate_the_Package_Status_in_Customer_Account_Summary_of_Customer_Tab("INACTIVE");

				
				
				//Correspondence Address
				
				CuP.validate_the_Address_Line_1_of_Correspondence_Address_in_Customer_Tab("19 HAWKE CLOSE");
				CuP.validate_the_Address_Line_2_of_Correspondence_Address_in_Customer_Tab("RAWMARSH");
				CuP.validate_the_Town_City_of_Correspondence_Address_in_Customer_Tab("ROTHERHAM");
				CuP.validate_the_Country_of_Correspondence_Address_in_Customer_Tab("UK");
				CuP.validate_the_Postcode_of_Correspondence_Address_in_Customer_Tab("S62 7NL");
			
				//Billing Address
				
				CuP.validate_the_Address_Line_1_of_Billing_Address_in_Customer_Tab("19 HAWKE CLOSE");
				CuP.validate_the_Address_Line_2_of_Billing_Address_in_Customer_Tab("RAWMARSH");
				CuP.validate_the_Town_City_of_Billing_Address_in_Customer_Tab("ROTHERHAM");
				CuP.validate_the_Country_of_Billing_Address_in_Customer_Tab("UK");
				CuP.validate_the_Postcode_of_Billing_Address_in_Customer_Tab("S62 7NL");

				
				//Installation Address
				CuP.validate_the_Address_Line_1_of_Installation_Address_in_Customer_Tab("19 HAWKE CLOSE");
				CuP.validate_the_Address_Line_2_of_Installation_Address_in_Customer_Tab("RAWMARSH");
				CuP.validate_the_Town_City_of_Installation_Address_in_Customer_Tab("ROTHERHAM");
				CuP.validate_the_Country_of_Installation_Address_in_Customer_Tab("UK");
				CuP.validate_the_Postcode_of_Installation_Address_in_Customer_Tab("S62 7NL");

			
				//Telephone
				
				//CuP.validate_the_Telephone_Home_in_Contact_Details_of_Customer_Tab("01709710391");
				CuP.validate_the_Telephone_Work_in_Contact_Details_of_Customer_Tab("N/A");
				CuP.validate_the_Telephone_Mobile_in_Contact_Details_of_Customer_Tab("N/A");
				
				
				//Marketing preferences and preferred contact method
				
				CuP.validate_the_Marketing_preferences_in_Contact_Details_of_Customer_Tab("Phone",false);
				CuP.validate_the_Marketing_preferences_in_Contact_Details_of_Customer_Tab("Email",false);
				CuP.validate_the_Marketing_preferences_in_Contact_Details_of_Customer_Tab("Mail",true);
				CuP.validate_the_Preferred_Contact_Method_in_Contact_Details_of_Customer_Tab_Phone(true);
				CuP.validate_the_Preferred_Contact_Method_in_Contact_Details_of_Customer_Tab_Email(false);
				CuP.validate_the_Preferred_Contact_Method_in_Contact_Details_of_Customer_Tab_Mail(false);
				
				//Security Section
				CuP.validate_the_Password_of_Security_in_Customer_Tab("N/A");
				CuP.validate_the_Online_Username_of_Security_in_Customer_Tab("megan47290@nowhere.com");
				
				
				//Customer page -- Account tab
				
				SP.switch_Between_tabs("Account");
				
				//Collection
				
				AP.validate_for_In_Collections_in_Account_Tab("NO");
				AP.validate_for_With_DCA_in_Account_Tab("NO");


				
				
				//Account Summary
				
				AP.validate_the_Usage_Limit_of_Balance_in_Account_Tab("£19.00");
				AP.validate_the_Credit_Limit_of_Balance_in_Account_Tab("£38.00");
				AP.validate_the_Current_Overdue_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Aged_31_to_60_days_balance_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Aged_61_to_90_days_balance_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Aged_90_plus_days_balance_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Invoice_Format_of_Billing_Preferences_in_Account_Tab("REGULAR PRINT");
				AP.validate_the_Payment_Method_of_Billing_Preferences_in_Account_Tab("CARD");
				AP.validate_the_Bill_Method_of_Billing_Preferences_in_Account_Tab("PAPER");
				AP.validate_the_Total_Recurring_Charges_of_Charges_in_Account_Tab("£0.00");
				AP.validate_the_Total_Discounts_of_Charges_in_Account_Tab("£0.00");
				AP.validate_the_Account_Status_of_Account_Summary_in_Account_Tab("Active");
				AP.validate_the_Entered_Cooling_off_of_Account_Summary_in_Account_Tab("07-Apr-2008");
				AP.validate_the_Brand_of_Account_Summary_in_Account_Tab("TALKTALK");
				AP.validate_the_Branch_of_Account_Summary_in_Account_Tab("5322");
				
				
				
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.search);
				SP.validate_Switch_package_Link_Enabled(true);
				SP.click_Switch_package_Link();
				Com.switch_Window();
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.search);
				Thread.sleep(3000);
				CLI=SP.getCLI_In_Phone_Summary_Swichpackage(1);
				pack=SP.getPackage_In_Phone_Summary_Swichpackage(1);
				SP.select_SpecificRecord_Phone_Summary_Swichpackage(1);
				Thread.sleep(3000);
				SP.click_on_Submit_In_SwitchPackage_PopUp();
				Com.switchToFrame(iframes.defaultcontent);
				VP.verify_the_CIB_PhoneNumber(CLI);
				VP.verify_the_CIB_Package_name(pack);
				
				
				
				
				//Customer page
				
				Com.switchToFrame(iframes.search);
				SP.switch_Between_tabs("Customer");
				
				
				
				//Correspondence Address
				
				CuP.validate_the_Address_Line_1_of_Correspondence_Address_in_Customer_Tab("19 HAWKE CLOSE");
				CuP.validate_the_Address_Line_2_of_Correspondence_Address_in_Customer_Tab("RAWMARSH");
				CuP.validate_the_Town_City_of_Correspondence_Address_in_Customer_Tab("ROTHERHAM");
				CuP.validate_the_Country_of_Correspondence_Address_in_Customer_Tab("UK");
				CuP.validate_the_Postcode_of_Correspondence_Address_in_Customer_Tab("S62 7NL");
			
				//Billing Address
				
				CuP.validate_the_Address_Line_1_of_Billing_Address_in_Customer_Tab("19 HAWKE CLOSE");
				CuP.validate_the_Address_Line_2_of_Billing_Address_in_Customer_Tab("RAWMARSH");
				CuP.validate_the_Town_City_of_Billing_Address_in_Customer_Tab("ROTHERHAM");
				CuP.validate_the_Country_of_Billing_Address_in_Customer_Tab("UK");
				CuP.validate_the_Postcode_of_Billing_Address_in_Customer_Tab("S62 7NL");
				
				//Installation Address
				CuP.validate_the_Address_Line_1_of_Installation_Address_in_Customer_Tab("16,1,White City");
				CuP.validate_the_Address_Line_2_of_Installation_Address_in_Customer_Tab("SIEMENS ROAD");
				CuP.validate_the_Town_City_of_Installation_Address_in_Customer_Tab("MANCHESTER");
				CuP.validate_the_Country_of_Installation_Address_in_Customer_Tab("UK");
				CuP.validate_the_Postcode_of_Installation_Address_in_Customer_Tab("W11 4AR");
				
				
				//Customer page -- Account tab
				
				SP.switch_Between_tabs("Account");
				
				//Collection
				
				AP.validate_for_In_Collections_in_Account_Tab("NO");
				AP.validate_for_With_DCA_in_Account_Tab("NO");


				
				
				//Account Summary
				
				AP.validate_the_Usage_Limit_of_Balance_in_Account_Tab("£19.00");
				AP.validate_the_Credit_Limit_of_Balance_in_Account_Tab("£38.00");
				AP.validate_the_Current_Overdue_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Aged_31_to_60_days_balance_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Aged_61_to_90_days_balance_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Aged_90_plus_days_balance_of_Balance_in_Account_Tab("£0.00");
				AP.validate_the_Invoice_Format_of_Billing_Preferences_in_Account_Tab("REGULAR PRINT");
				AP.validate_the_Payment_Method_of_Billing_Preferences_in_Account_Tab("CARD");
				AP.validate_the_Bill_Method_of_Billing_Preferences_in_Account_Tab("PAPER");
				AP.validate_the_Total_Recurring_Charges_of_Charges_in_Account_Tab("£0.00");
				AP.validate_the_Total_Discounts_of_Charges_in_Account_Tab("£0.00");
				AP.validate_the_Account_Status_of_Account_Summary_in_Account_Tab("Active");
				AP.validate_the_Entered_Cooling_off_of_Account_Summary_in_Account_Tab("07-Apr-2008");
				AP.validate_the_Brand_of_Account_Summary_in_Account_Tab("TALKTALK");
				AP.validate_the_Branch_of_Account_Summary_in_Account_Tab("5322");*/
				
				
				//Choosing payment case 
				
				
				CP.hover_on_Add_Case_button();
				CP.select_Action_Category_for_Cases("Payment");
				CP.click_on_Add_cases_button();
				
				//Payment Page
				Thread.sleep(1000);
				Com.switchToFrame(iframes.defaultcontent);
				Com.switchToFrame(iframes.create);
				CP.waitFor_Specific_Task_PageLoad("Payment");
				
				CP.verify_Payment_payFor_Elements(true, false);
				CP.verify_Payment_PaymentOptions_Elements(false, true, false);
				
				
				CP.verify_Payment_AccountDetails_Table("Account Number", "1000004951");
				CP.verify_Payment_AccountDetails_Table("Phone Number", "01709710391");
				CP.verify_Payment_AccountDetails_Table("Account Balance", "£0.00");
				CP.verify_Payment_AccountDetails_Table("Latest Bill Amount", "£83.61");
				CP.verify_Payment_AccountDetails_Table("Latest Bill Due Date", "04-Mar-2016");
				CP.verify_Payment_AccountDetails_Table("Payment Method", "Recurring Card");
				CP.verify_Payment_AccountDetails_Table("Approx Bill Due Date", "16th of each month");
				CP.verify_Payment_AccountDetails_Table("Approx Re-presentation Date", "-");
				
//				CP.verify_Payment_PayWith_Elements(false, true);
				CP.verify_Payment_RegisteredCardFor_Elements(false, false);
				
				CP.click_Payment_cardHolder_Billing_Address_Retention_ButtonClick_ToEnable();
				CP.click_Payment_RegisteredCardFor_FutureOneOff();
				
				Com.verify_Alert_Exists(UnRegistered_card_Warning);
				Com.verify_Alert_Exists(Future_OneOff_Payments_Message);
				
				
				CP.validate_the_Address_Line_1_of_Billing_Address_in_CardHolder_Address("19 HAWKE CLOSE");
				CP.validate_the_Address_Line_2_of_Billing_Address_in_CardHolder_Address("RAWMARSH");
				CP.validate_the_Town_City_of_Billing_Address_in_CardHolder_Address("ROTHERHAM");
				CP.validate_the_Country_of_Billing_Address_in_CardHolder_Address("UK");
				CP.validate_the_Postcode_of_Billing_Address_in_CardHolder_Address("S62 7NL");
				
				
				CP.verify_the_Customer_NameOnCard_Details(CallerName);
				
				
				
				
				//Canceling the payment task
				Com.select_An_Action_To_Perform_ForCases("Cancel this work");
				Thread.sleep(3000);
				Com.enter_Exit_interaction_Comments("Cancelling task in Summary page");
				Com.click_Submit_Button();
				
				
				
				//Wrap Up
				Com.click_on_Wrap_up_button();
				Com.enter_Wrap_up_Comments();
				Com.click_Submit_Button();
				Com.Pega_logout();

		/***************************** END OF MAIN TEST ****************************/
	}

}
