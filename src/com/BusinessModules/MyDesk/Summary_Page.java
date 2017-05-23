package com.BusinessModules.MyDesk;
/**Class Name		: Home Page	
 * Description		: This class contains functions for the Address_Details_Page	
 * @author 			: Rajan	
 * Function Names	: 
 * 
 * 
 * 
 * 
 * Creation Date	: 30 Sep 2016	
 */

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

















import net.sf.saxon.functions.CurrentDateTime;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


//import java.util.Set;









import bsh.ParseException;










import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.Enumerations.Generic;
import com.Enumerations.MyDeskEnumerations;
import com.Enumerations.MyDeskEnumerations.CustomerInformationBar;
import com.ObjectIdentifiers.XP_PEGA_MyDesk;
import com.Utils.Reusables;
import com.WebActions.WebActions;
import com.relevantcodes.extentreports.LogStatus;


public class Summary_Page extends WebActions implements XP_PEGA_MyDesk,Generic,MyDeskEnumerations {
	public WebDriver driver;
	public Reporter Report;

	public Summary_Page(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;
		
	}

	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Verify Caller to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	14 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void wait_for_Summary_Page_to_load()throws Exception {
		if(!waitForElementToAppear(Desktop_XPATH_Summary_Caller_Page_Validation, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Summary Page is not Loaded", "Summary Page is not Loaded", driver);
		}
		else{
			Report.fnReportPageBreak("Summary Page",driver);
			
			
			}
	}
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Switch Between Tabs
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	19 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void switch_Between_tabs(String tab)throws Exception {
		VerifyElementPresentAndClick(Desktop_XPATH_Summary_Page_Switchtabs.replaceAll("TABS", tab), tab+" Tab");
		Report.fnReportPageBreak(tab+" page", driver);
		}
	
	
	
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Contract End Date in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	ContractEndDate should be in format dd-Eee-yyyy
	 */

	public void validate_the_Contract_End_Date_in_Summary_Tab(String ContractEndDate)throws Exception {
		
		Report.fnReportPageBreak("Contract Summary", driver);
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_ContractEndDate.replaceAll("M_Header", "Contract Summary").replaceAll("M_Category", "Contract End Date").replaceAll("M_InnerText", ContractEndDate), "Contract End Date - "+ContractEndDate, false);
		
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Remaining Contract Term in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	RemainingContractTerm should be in format _ months _ days
	 */

	public void validate_the_Remaining_Contract_Term_in_Summary_Tab(String RemainingContractTerm)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_RemainingContractTerm.replaceAll("M_Header", "Contract Summary").replaceAll("M_Category", "Remaining Contract Term").replaceAll("M_InnerText", RemainingContractTerm), "Remaining Contract Term - "+RemainingContractTerm, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Last Bill Date in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	LastBillDate should be in format dd-Eee-yyyy
	 */

	public void validate_the_Last_Bill_Date_in_Summary_Tab(String LastBillDate)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_LastBillDate.replaceAll("M_Header", "Billing Summary").replaceAll("M_Category", "Last Bill Date").replaceAll("M_InnerText", LastBillDate), "Last Bill Date - "+LastBillDate, false);
		Report.fnReportPageBreak("Bill Summary ", driver);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Last Bill Amount in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	LastBillAmount should be in format dd-Eee-yyyy
	 */

	public void validate_the_Last_Bill_Amount_in_Summary_Tab(String LastBillAmount)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_LastBillAmount.replaceAll("M_Header", "Billing Summary").replaceAll("M_Category", "Last Bill Amount").replaceAll("M_InnerText", LastBillAmount), "Last Bill Amount - "+LastBillAmount, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Payment Due Date in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	PaymentDueDate should be in format dd-Eee-yyyy
	 */

	public void validate_the_Payment_Due_Date_in_Summary_Tab(String PaymentDueDate)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_PaymentDueDate.replaceAll("M_Header", "Billing Summary").replaceAll("M_Category", "Payment Due Date").replaceAll("M_InnerText", PaymentDueDate), "Payment Due Date - "+PaymentDueDate, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Outstanding Balance in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	OutstandingBalance should be in decimal format(0.00)
	 */

	public void validate_the_Outstanding_Balance_in_Summary_Tab(String OutstandingBalance)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_OutstandingBalance.replaceAll("M_Header", "Billing Summary").replaceAll("M_Category", "Outstanding Balance").replaceAll("M_InnerText", OutstandingBalance), "Outstanding Balance - "+OutstandingBalance, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Unbilled Usage in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	UnbilledUsage should be in decimal format(0.00)
	 */

	public void validate_the_Unbilled_Usage_in_Summary_Tab(String UnbilledUsage)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_UnbilledUsage.replaceAll("M_Header", "Billing Summary").replaceAll("M_Category", "Unbilled Usage").replaceAll("M_InnerText", UnbilledUsage), "Unbilled Usage - "+UnbilledUsage, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Next Bill Date in Summary Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	NextBillDate should be in format dd-Eee-yyyy
	 */

	public void validate_the_Next_Bill_Date_in_Summary_Tab(String NextBillDate)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Summary_Page_NextBillDate.replaceAll("M_Header", "Billing Summary").replaceAll("M_Category", "Next Bill Date").replaceAll("M_InnerText", NextBillDate), "Next Bill Date - "+NextBillDate, false);
	}
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate Switch package link  on checking value enabled or disabled - through Boolean value?
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_Switch_package_Link_Enabled(boolean enabled)throws Exception {
		
		
		if(enabled)
		{
			if(waitForElementToAppear(Desktop_XPATH_Verify_Summary_Page_SwitchPackage, t))
			{
				if(waitForElementToAppear(Desktop_XPATH_Verify_Summary_Page_SwitchPackage_disabled, t))
					Report.fnReportFail("Switch package is not in expected state", driver);
				else
					Report.fnReportPass("Switch package is in expected state", driver);
			}
			
		}
		
		else
		{
			if(waitForElementToAppear(Desktop_XPATH_Verify_Summary_Page_SwitchPackage_disabled, t))
					Report.fnReportPass("Switch package is not in expected state", driver);
			else 
				
			if(waitForElementToAppear(Desktop_XPATH_Verify_Summary_Page_SwitchPackage, t))
					Report.fnReportFail("Switch package is not in expected state", driver);	
		}
//		
//		if(waitForElementToAppear(Desktop_XPATH_Verify_Summary_Page_SwitchPackage, custTimeOut))
//		{
//			if(enabled==driver.findElement(getElementFromLoc(Desktop_XPATH_Verify_Summary_Page_SwitchPackage)).isEnabled())
//				Report.fnReportPass("Switch package is in expected state", driver);
//			else
//				Report.fnReportFail("Switch package is not in expected state", driver);
//		}
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Switch package link  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_Switch_package_Link()throws Exception {
		
		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Verify_Summary_Page_SwitchPackage, "SwitchPackage");
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the Elements in Phone Number Summary
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_the_Elements_in_Phone_Number_Summary(PhoneNumberSummary... PNS)throws Exception {
		
		int i=1;
		for(PhoneNumberSummary DataToCheck:PNS)
		{
			String Element=DataToCheck.name().replaceAll("_", " ");
			
			VerifyElementPresent(Desktop_XPATH_PhoneNumber_Summary_Page_Header.replaceAll("line", String.valueOf(i)).replaceAll("header", Element),"Phone Number Summary - "+Element, false);
		}
		
		Report.fnReportPass("Phone Number Bar Summary details Shown", driver);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Get CLI of Specifc line number inPh.No summary after clicking Switch package link  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public String getCLI_In_Phone_Summary_Swichpackage(int line)throws Exception {
		
		return VerifyElementPresentAndGetText(Desktop_XPATH_PhoneNumber_Summary_Page_PhoneNumber.replaceAll("line", String.valueOf(line+1)), "CLI_In_Phone_Summary_Swichpackage");
	}
	
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Get CLI of Specifc line number inPh.No summary after clicking Switch package link  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public String getAccount_In_Phone_Summary_Swichpackage(int line)throws Exception {
		
		return VerifyElementPresentAndGetText(Desktop_XPATH_PhoneNumber_Summary_Page_Account.replaceAll("line", String.valueOf(line+1)), "Account_In_Phone_Summary_Swichpackage");
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Get Package ofSpecifc line number inPh.No summary after clicking Switch package link  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public String getPackage_In_Phone_Summary_Swichpackage(int line)throws Exception {
		
		return VerifyElementPresentAndGetText(Desktop_XPATH_PhoneNumber_Summary_Page_Package.replaceAll("line", String.valueOf(line+1)), "Package_In_Phone_Summary_Swichpackage");
	}
	
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Get Caller Name from Customer info panel   
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public String getCustomerName_In_CIB()throws Exception {
		
		return VerifyElementPresentAndGetText(Desktop_XPATH_Payment_CIB_CallerName, "Caller Name ");
	}
	

	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Specific line number inPh.No summary after clicking Switch package link  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void select_SpecificRecord_Phone_Summary_Swichpackage(int line)throws Exception {
		
		Report.fnReportPageBreak("Package in line :"+line+" Selected", driver);
		VerifyElementPresentAndClick(Desktop_XPATH_PhoneNumber_Summary_Page_PhoneNumber.replaceAll("line", String.valueOf(line+1)), "CLI_In_Phone_Summary_Swichpackage");
	}
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click on Submit Button in Switch package pop up 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_on_Submit_In_SwitchPackage_PopUp()throws Exception {
		VerifyElementPresentAndClick(Desktop_XPATH_Switch_Pack_PopUp_Submit, "Switch Pack PopUp Submit");
	}
	
	
}