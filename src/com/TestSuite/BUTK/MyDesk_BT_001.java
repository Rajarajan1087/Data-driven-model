/*Test Scenario 		: PEGA - NAD (MyDesk_BT_001)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Rajan
 *Created on			: 18 Oct 2016
 */

package com.TestSuite.BUTK;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BusinessModules.MyDesk.Account_Page;
import com.BusinessModules.MyDesk.BulkUploadToolKit;
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
import com.SharedModules.DbUtilities;
import com.SharedModules.NewDatabase;
import com.SharedModules.DatabaseTestData.searchByDataa;
import com.SharedModules.NewDatabase.searchByData;
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.DataProviderExcelReader;
import com.Utils.ReadExcelSheet;

public class MyDesk_BT_001 extends SeleniumSetup implements Constants,Generic {

	/**
	 * Data Provider returns SCRIPT_ID and ROW where the SCRIPT_ID exists in
	 * mentioned sheets
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "MyDesk_BT_001")
	// Data Provider name
	public Object[][] DATA() throws Exception {
		return DataProviderExcelReader.getExcelData(LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Customer180,"MyDesk_BT_001"); // Get data object
	}

	/**
	 * Test method name will be taken for Extent Report. Please mention a valid
	 * method name Test should contain TestPreProcessing for driver and report
	 * instantiation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Test(groups = { "SalesRegression" }, dataProvider = "MyDesk_BT_001")
	public void MyDesk_BT_001_Method(String SCRIPT_ID, String ROW)
			throws Exception {
		/************************** Objects Declaration **********************************/

		Map<String, String> DATA_MAP = TestPreProcessing(SCRIPT_ID, ROW,LoadEnvironment.WB_NAD,LoadEnvironment.Sheet_TestCases_Customer180);

		/*************************************** Objects Declaration ************************************/

		Account_Page AP=new Account_Page(driver, Report);
		Cases_Page CP=new Cases_Page(driver, Report);
		Common_Functions Com=new Common_Functions(driver, Report);
		Customer_Page CuP=new Customer_Page(driver, Report);
		Home_Page HP=new Home_Page(driver, Report);
		BulkUploadToolKit BT=new BulkUploadToolKit(driver, Report);
		Summary_Page SP=new Summary_Page(driver, Report);
		VerifyCaller_Page VP=new VerifyCaller_Page(driver, Report);
		DbUtilities DBU = new DbUtilities(Report);
		DBTestDataSetup DBTDST= new DBTestDataSetup(Report);
		/*************************************** Variables Declaration ************************************/

		/*************************************** Data Preparation ******************************************/
//		String data;
//		
//		NewDatabase dbn = new NewDatabase(Report);
//		data = dbn.getDataNEW_AIP("291",searchByData.blank,"",true,false,"TV Store"); // Str_PackageId ,Search by, search value [ NOT_PropID], Contract , Credit class check
//		if(data==null){
//			Report.fnReportFailAndTerminateTest("DataGetter", "No Data Found");
//		}
//		String Str_Account= data.substring(data.indexOf(',')+1);
//		String Str_CLI=data.substring(0, data.indexOf(','));
		
		/***************************** START OF MAIN TEST ****************************/

				driver.get(LoadEnvironment.PEGA_NAD_URL);
		
				Com.Pega_Login("v.rajan", "rules");
		
				// Home Page
				BT.wait_for_Home_Page_To_Load_AccessControl();
				BT.Click_Operator_Management_Button();
				BT.Click_Operator_Management_Options("Bulk Operation Creation");
				Com.switchToFrame(iframes.search);
				
//				Bulk Upload Page
				BT.wait_for_BulkCreate_Page_toLoad();
				BT.click_Close_BulkCreate_Page_toClose();
				BT.wait_for_AccessControl_Welcome_Page_To_Load();
				Com.Pega_logout();

		/***************************** END OF MAIN TEST ****************************/
	}

}
