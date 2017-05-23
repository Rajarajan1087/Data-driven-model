/*Test Scenario 		: PEGA - NAD (MyDesk_C1_009_DB)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Raja
 *Created on			: 07 Oct 2016
 */

package com.TestSuite.Customer180;

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

public class MyDesk_C1_010 extends SeleniumSetup implements Constants,Generic,PEGAConstants {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_C1_010")
// 	Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Customer180,"MyDesk_C1_010"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_C1_010")
	public void MyDesk_C1_010_Method(String SCRIPT_ID, String ROW)
			throws Exception {
		/************************** Objects Declaration **********************************/
		
		System.out.println("Inside test Method");
		Map<String, String> DATA_MAP = TestPreProcessing(SCRIPT_ID, ROW,LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Customer180);

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

		/*************************************** Data Preparation ******************************************/
		
		Map<String,String> dbmap2=DBU.MultiplePkg("Multiple","ACTIVE","null","not null","CANCELLED","not null","not null");
		
		ACCOUNT=dbmap2.get("ACCOUTNNUMBER");
		System.out.println("ACCOUNT"+ACCOUNT);
		
		Map<String,String> dbmap3=DBU.accountSearch(ACCOUNT,"ACTIVE");
		CLI=dbmap3.get("CLI");
		
		Map<String,String> dbmap=DBU.RetrieveCustomerDetails(CLI,ACCOUNT);
		Map<String,String> dbmap1=DBU.RetrieveAddressDetails(CLI,ACCOUNT);
	
		/***************************** START OF MAIN TEST ****************************/

//				driver.get(LoadEnvironment.PEGA_NAD_URL);
//				Thread.sleep(3000);
//		
//				Com.Pega_Login(LoadEnvironment.PEGA_USERNAME, LoadEnvironment.PEGA_PASSWORD);
//		
//// 				Home Page
				HP.wait_for_Home_Page_To_Load();		
//				HP.Click_New_Interaction_Button();
//				Com.switchToFrame(iframes.search);
//				HP.wait_for_Identify_Customer_Page_To_Load();
//				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Hold_Time);
//				HP.Customer_Search_By_ButtonClick(SearchBy.CLI);
//				HP.Customer_Search_By_InputValue(SearchBy.CLI,CLI,"");
//				HP.Click_Search_Button();
//
////				Verify Caller Page
//				VP.wait_for_Verify_Caller_Page_to_load();
//				VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
//				
//				
//				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Hold_Time);
//			
//				VP.verify_the_caller_Identification_type("Account Holder,Non-Account Holder,Nominated User/ Power of Attorney");
//				VP.verify_the_call_Reason("General Enquiry?,Fault?,Outbound call to landline?,Account Status?,Make a Payment/Check account balance?");
//	
//				
//				Com.click_Submit_Button();
//				
////				Summary page
//				SP.wait_for_Summary_Page_to_load();	
//				Com.switchToFrame(iframes.defaultcontent);
//				VP.verify_the_CIB_Caller_name("(Account Holder)");
//				
//				String CallerName=dbmap.get("TITLE")+" "+(dbmap.get("FIRSTNAME"))+" "+(dbmap.get("LASTNAME"));
//				
//				VP.verify_the_CIB_IDV_Status_text("VERIFIED");
//				VP.verify_the_CIB_PhoneNumber(CLI);
//				VP.verify_the_CIB_Account_Number(ACCOUNT);
//				VP.verify_the_CIB_Package_name(dbmap.get("PACKAGENAME"));
//				Com.switchToFrame(iframes.search);
//									
////				Customer page
//				
//				SP.switch_Between_tabs("Customer");
//				
//				//Customer Account Summary
//				
//				CuP.validate_the_Account_Number_in_Customer_Account_Summary_of_Customer_Tab(ACCOUNT);
//				CuP.validate_the_Account_Status_in_Customer_Account_Summary_of_Customer_Tab("Active");
//				CuP.validate_the_Phone_Number_in_Customer_Account_Summary_of_Customer_Tab(CLI);
//				CuP.validate_the_Package_Name_in_Customer_Account_Summary_of_Customer_Tab(dbmap.get("PACKAGENAME"));
//				CuP.validate_the_Package_Status_in_Customer_Account_Summary_of_Customer_Tab(dbmap.get("PSPSTATUSCODE"));
//				
//	
//				//Correspondence Address
//				
//				CuP.validate_the_Address_Line_1_of_Correspondence_Address_in_Customer_Tab(dbmap1.get("Correspondence_ADDRESS1TEXT"));
//				CuP.validate_the_Address_Line_2_of_Correspondence_Address_in_Customer_Tab(dbmap1.get("Correspondence_ADDRESS2TEXT"));
//				CuP.validate_the_Town_City_of_Correspondence_Address_in_Customer_Tab("CHICHESTER");
//				CuP.validate_the_Country_of_Correspondence_Address_in_Customer_Tab("UK");
//				CuP.validate_the_Postcode_of_Correspondence_Address_in_Customer_Tab("PO19 5DR");
//			
//				//Billing Address
//				
//				CuP.validate_the_Address_Line_1_of_Billing_Address_in_Customer_Tab("1 DURHAM GARDENS");
////				CuP.validate_the_Address_Line_2_of_Billing_Address_in_Customer_Tab(dbmap1.get("Billing_LINE_2"));
//				CuP.validate_the_Town_City_of_Billing_Address_in_Customer_Tab("CHICHESTER");
//				CuP.validate_the_Country_of_Billing_Address_in_Customer_Tab("UK");
//				CuP.validate_the_Postcode_of_Billing_Address_in_Customer_Tab("PO19 5DR");
//
//				
//				//Installation Address
//				CuP.validate_the_Address_Line_1_of_Installation_Address_in_Customer_Tab("1, Durham Gardens");
////				CuP.validate_the_Address_Line_2_of_Installation_Address_in_Customer_Tab(dbmap1.get("Installation_ADDRESS2TEXT"));
//				CuP.validate_the_Town_City_of_Installation_Address_in_Customer_Tab("CHICHESTER");
//				CuP.validate_the_Country_of_Installation_Address_in_Customer_Tab("UK");
//				CuP.validate_the_Postcode_of_Installation_Address_in_Customer_Tab("PO19 5DR");
//	
//				
//				//Customer page -- Account tab
//				
//				SP.switch_Between_tabs("Account");
//				
//		
//				
//				Com.switchToFrame(iframes.defaultcontent);
//				Com.switchToFrame(iframes.search);
//				SP.validate_Switch_package_Link_Enabled(true);
//				SP.click_Switch_package_Link();
//				Com.switch_Window();
//				Com.switchToFrame(iframes.defaultcontent);
//				Com.switchToFrame(iframes.search);
//				Thread.sleep(3000);
//				
//				CLI=SP.getCLI_In_Phone_Summary_Swichpackage(1);
//				
				Map<String,String> dbmap4= DBU.accountSearch(ACCOUNT,"CANCELLED");
				CLI=dbmap4.get("CLI");
				
				Map<String,String> dbmap5=DBU.RetrieveCustomerDetails(CLI,ACCOUNT);
				Map<String,String> dbmap6=DBU.RetrieveAddressDetails(CLI,ACCOUNT);
				
//				SP.select_SpecificRecord_Phone_Summary_Swichpackage(1);
//				Thread.sleep(3000);
//				SP.click_on_Submit_In_SwitchPackage_PopUp();
//				Com.switchToFrame(iframes.defaultcontent);
//				VP.verify_the_CIB_PhoneNumber(CLI);
//				VP.verify_the_CIB_Package_name(dbmap5.get("PACKAGENAME"));
//				
//		
//				//Wrap Up
//				//Wrap Up
//				Com.click_on_Wrap_up_button();
//				Com.enter_Wrap_up_Comments();
//				Com.click_Submit_Button();
//				
//				Thread.sleep(2000);
//				Com.switchToFrame(iframes.defaultcontent);
//
//				Thread.sleep(1000);
//
//				Com.Pega_logout();

		/***************************** END OF MAIN TEST ****************************/
	}

}
