/*Test Scenario 		: PEGA - NAD (MyDesk_CS_023)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (non-account holder )(Till Second page)
 *Package				: CustomerSearch
 *Created By			: Rajan
 *Created on			: 14 Oct 2016
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
import com.EMS.SGOGenerator;
import com.Engine.LoadEnvironment;
import com.Engine.SeleniumSetup;
import com.Enumerations.Generic;
import com.Enumerations.Generic.CustomerSearchDB;
import com.Enumerations.Generic.iframes;
import com.Enumerations.MyDeskEnumerations.CustomerInformationBar;
import com.Enumerations.MyDeskEnumerations.SearchBy;
import com.ObjectIdentifiers.PEGAConstants;
import com.SharedModules.Constants;
import com.SharedModules.DbUtilities;
import com.SharedModules.RandomGenerator;
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.DataProviderExcelReader;
import com.Utils.ReadExcelSheet;

public class MyDesk_CS_023 extends SeleniumSetup implements Constants,Generic,PEGAConstants {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_CS_023")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_CustomerSearch,"MyDesk_CS_023"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_CS_023")
	public void MyDesk_CS_023_Method(String SCRIPT_ID, String ROW)
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
		String MCLI=DATA_MAP.get("MOB_CLI");
		String MACCOUNT=DATA_MAP.get("MOB_ACCOUNT");
		String SEARCHPARAM=DATA_MAP.get("SEARCH_TYPE");
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
		Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Call_Reason,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Monthly_Spend,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Total_Transfers,CustomerInformationBar.Hold_Time);
		
		/*Click Hide*/
		
		VP.click_on_Hide_Show_details(true);
		Com.verify_the_Elements_NotPresent_in_Customer_Information_Bar(CustomerInformationBar.Hold_Time);
		if(SEARCHPARAM.contains("Mobile"))
		{
			
			SGOGenerator SGO = new SGOGenerator();
			
			Map<String,String> dbmap=DBU.RetrieveMobileDetails(CLI);
			
			 MCLI=dbmap.get("MOBILENUMBER");
			 ACCOUNT=dbmap.get("MOBILEACCOUNTID");
			
			if((MCLI.equalsIgnoreCase(""))){
				
				ACCOUNT = RandomGenerator.randomNumber("20", 6);
				System.out.println("MobAccountID is" + ACCOUNT);

				 MCLI = SGO.ManageMobileSubscription_SGO(CLI,ACCOUNT);
				System.out.println("MobileNumber------>" + MCLI);
			}
		HP.Customer_Search_By_ButtonClick(SearchBy.MCLI);
		HP.Customer_Search_By_InputValue(SearchBy.MCLI,MCLI,"");		
		
		}
	else
		{
		HP.Customer_Search_By_ButtonClick(SearchBy.CLI);
		HP.Customer_Search_By_InputValue(SearchBy.CLI,CLI,"");
		}
	
		HP.Click_Search_Button();
		
		
		//Verify Caller Page
		VP.wait_for_Verify_Caller_Page_to_load();
		VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
		Com.verify_the_Elements_NotPresent_in_Customer_Information_Bar(CustomerInformationBar.Hold_Time);
		
		/*Click Show*/
		
		VP.click_on_Hide_Show_details(false);
		Com.verify_Alert_Exists(CustomerAlert_Fraud);
		Com.verify_Alert_Exists(CustomerAlert_Deceased);
		
		
		
//		VP.select_the_caller_Identification_type("Account Holder");
//		VP.select_the_caller_Identification_type("Nominated User/ Power of Attorney");
//		VP.select_the_caller_Verification_type("Manually Completed DPA in Trio?");
		VP.select_NumberOf_Required_Additional_Question_CheckBox(5);
		Com.click_Submit_Button();
		
		
		//Summary page
		SP.wait_for_Summary_Page_to_load();
		Com.switchToFrame(iframes.defaultcontent);
		Com.verify_Alert_Exists(CustomerAlert_Fraud);
		Com.verify_Alert_Exists(CustomerAlert_Deceased);
		
		/*Click Alert Hide*/
		
		Com.click_Hide_Alert_Message();
		Com.verify_Alert_NotExists(CustomerAlert_Fraud);
		Com.verify_Alert_NotExists(CustomerAlert_Deceased);
		
		
		
		//Wrap Up
		Com.click_on_Wrap_up_button();
		Com.enter_Wrap_up_Comments();
		Com.click_Submit_Button();
		Com.Pega_logout();
		
		
		/***************************** END OF MAIN TEST ****************************/
	}

}
