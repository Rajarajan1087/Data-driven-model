/*Test Scenario 		: PEGA - NAD (MyDesk_CS_001)
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
import com.SharedModules.DBTestDataSetup;
import com.SharedModules.DbUtilities;
import com.SharedModules.NewDatabase;
import com.SharedModules.DatabaseTestData.searchByDataa;
import com.SharedModules.NewDatabase.searchByData;
import com.SharedModules.RandomGenerator;
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.DataProviderExcelReader;
import com.Utils.ReadExcelSheet;

public class MyDesk_MS_001 extends SeleniumSetup implements Constants,Generic {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_MS_001")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_BVTPACK,"MyDesk_MS_001"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_MS_001")
	public void MyDesk_MS_001_Method(String SCRIPT_ID, String ROW)
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
		/*************************************** Variables Declaration ************************************/

		/*************************************** Data Preparation ******************************************/
		
		String data;
		
		NewDatabase dbn = new NewDatabase(Report);
		data = dbn.getDataNEW_AIP("291",searchByData.blank,"",true,false,"TV Store"); // Str_PackageId ,Search by, search value [ NOT_PropID], Contract , Credit class check
		if(data==null){
			Report.fnReportFailAndTerminateTest("DataGetter", "No Data Found");
		}
		String Str_Account= data.substring(data.indexOf(',')+1);
		String Str_CLI=data.substring(0, data.indexOf(','));
		
		SGOGenerator SGO = new SGOGenerator();
		
		Map<String,String> dbmap=DBU.RetrieveMobileDetails(Str_CLI);
		
		String MCLI=dbmap.get("MOBILENUMBER");
		String MACCOUNT=dbmap.get("MOBILEACCOUNTID");
		
		if((MCLI.equalsIgnoreCase(""))){
			
			MCLI=DATA_MAP.get("MOB_CLI");
			MACCOUNT=DATA_MAP.get("MOB_ACCOUNT");
			Str_CLI=DATA_MAP.get("CLI");
			Str_Account=DATA_MAP.get("ACCOUNT");
//			MACCOUNT = RandomGenerator.randomNumber("20", 6);
//			 MCLI = SGO.ManageMobileSubscription_SGO(Str_CLI,MACCOUNT);
		}
			
			System.out.println("MobAccountID is" + MACCOUNT);
			System.out.println("MobileNumber------>" + MCLI);
		
		
		/***************************** START OF MAIN TEST ****************************/

				driver.get(LoadEnvironment.PEGA_NAD_URL);
		
				Com.Pega_Login(LoadEnvironment.PEGA_USERNAME, LoadEnvironment.PEGA_PASSWORD);
		
				// Home Page
				HP.wait_for_Home_Page_To_Load();
				HP.Click_New_Interaction_Button();
				Com.switchToFrame(iframes.search);
				HP.wait_for_Identify_Customer_Page_To_Load();
				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Call_Reason,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Monthly_Spend,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Total_Transfers,CustomerInformationBar.Hold_Time);

				// Mobile Search
				HP.Customer_Search_By_ButtonClick(SearchBy.MCLI);
				HP.Customer_Search_By_InputValue(SearchBy.MCLI,MCLI,"");		

				HP.Click_Search_Button();
		
				//Verify Caller Page
				VP.wait_for_Verify_Caller_Page_to_load();
				Com.verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar.Caller_Name,CustomerInformationBar.IDandV_Status,CustomerInformationBar.Call_Reason,CustomerInformationBar.Phone_Number,CustomerInformationBar.Package,CustomerInformationBar.Account_Number,CustomerInformationBar.Monthly_Spend,CustomerInformationBar.Total_Call_Length,CustomerInformationBar.My_Call_Length,CustomerInformationBar.Total_Transfers,CustomerInformationBar.Hold_Time);
				VP.verify_the_CIB_PhoneNumber(Str_CLI);
				VP.verify_the_CIB_Account_Number(Str_Account);
				VP.verify_the_CIB_IDV_Status_text("IDENTIFIED");
				VP.verify_the_CIB_Caller_name("-");
				Com.select_an_action_to_perform("Identify Customer");
				HP.wait_for_Identify_Customer_Page_To_Load();
				HP.wait_for_Search_Result_Pane_To_Load();
				HP.Verify_Click_Unique_SearchResults(Str_CLI,"ACTIVE");
		
				//Exit interaction
				Com.select_an_action_to_perform("Exit Interaction");
				Com.enter_Exit_interaction_Comments("Identfiy Customer page");
				Com.Pega_logout();

		/***************************** END OF MAIN TEST ****************************/
	}

}
