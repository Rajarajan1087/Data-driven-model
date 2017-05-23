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
import com.ObjectIdentifiers.XP_PEGA_MyDesk;
import com.Utils.Reusables;
import com.WebActions.WebActions;
import com.relevantcodes.extentreports.LogStatus;


public class Account_Page extends WebActions implements XP_PEGA_MyDesk,Generic,MyDeskEnumerations {
	public WebDriver driver;
	public Reporter Report;

	public Account_Page(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Usage Limit of Balance in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	UsageLimit
	 */

	public void validate_the_Usage_Limit_of_Balance_in_Account_Tab(String UsageLimit)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Balance").replaceAll("M_Category", "Usage Limit").replaceAll("M_InnerText", UsageLimit), "Usage Limit of Balance - "+UsageLimit, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Credit Limit of Balance in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	CreditLimit
	 */

	public void validate_the_Credit_Limit_of_Balance_in_Account_Tab(String CreditLimit)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Balance").replaceAll("M_Category", "Credit Limit").replaceAll("M_InnerText", CreditLimit), "Credit Limit of Balance - "+CreditLimit, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Current Overdue of Balance in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	CurrentOverdue
	 */

	public void validate_the_Current_Overdue_of_Balance_in_Account_Tab(String CurrentOverdue)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Balance").replaceAll("M_Category", "Current Overdue").replaceAll("M_InnerText", CurrentOverdue), "Current Overdue of Balance - "+CurrentOverdue, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Aged 31- 60 days balance of Balance in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Aged31to60daysbalance
	 */

	public void validate_the_Aged_31_to_60_days_balance_of_Balance_in_Account_Tab(String Aged31to60daysbalance)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Balance").replaceAll("M_Category", "Aged 31- 60 days balance").replaceAll("M_InnerText", Aged31to60daysbalance), "Aged 31- 60 days balance of Balance - "+Aged31to60daysbalance, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Aged 61 - 90 days balance of Balance in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Aged61to90daysbalance
	 */

	public void validate_the_Aged_61_to_90_days_balance_of_Balance_in_Account_Tab(String Aged61to90daysbalance)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Balance").replaceAll("M_Category", "Aged 61 - 90 days balance").replaceAll("M_InnerText", Aged61to90daysbalance), "Aged 61 - 90 days balance of Balance - "+Aged61to90daysbalance, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Aged 90+ days balance of Balance in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Aged90plusdaysbalance
	 */

	public void validate_the_Aged_90_plus_days_balance_of_Balance_in_Account_Tab(String Aged90plusdaysbalance)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Balance").replaceAll("M_Category", "Aged 90+ days balance").replaceAll("M_InnerText", Aged90plusdaysbalance), "Aged 90+ days balance of Balance - "+Aged90plusdaysbalance, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Invoice Format of Billing Preferences in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	InvoiceFormat
	 */

	public void validate_the_Invoice_Format_of_Billing_Preferences_in_Account_Tab(String InvoiceFormat)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Billing Preferences").replaceAll("M_Category", "Invoice Format").replaceAll("M_InnerText", InvoiceFormat), "Invoice Format of Billing Preferences - "+InvoiceFormat, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Payment Method of Billing Preferences in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	PaymentMethod
	 */

	public void validate_the_Payment_Method_of_Billing_Preferences_in_Account_Tab(String PaymentMethod)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Billing Preferences").replaceAll("M_Category", "Payment Method").replaceAll("M_InnerText", PaymentMethod), "Payment Method of Billing Preferences - "+PaymentMethod, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Bill Method of Billing Preferences in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	BillMethod
	 */

	public void validate_the_Bill_Method_of_Billing_Preferences_in_Account_Tab(String BillMethod)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Billing Preferences").replaceAll("M_Category", "Bill Method").replaceAll("M_InnerText", BillMethod), "Bill Method of Billing Preferences - "+BillMethod, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Total Recurring Charges of Charges  in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	TotalRecurringCharges
	 */

	public void validate_the_Total_Recurring_Charges_of_Charges_in_Account_Tab(String TotalRecurringCharges)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Charges ").replaceAll("M_Category", "Total Recurring Charges").replaceAll("M_InnerText", TotalRecurringCharges), "Total Recurring Charges of Charges  - "+TotalRecurringCharges, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Total Discounts of Charges  in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	TotalDiscounts
	 */

	public void validate_the_Total_Discounts_of_Charges_in_Account_Tab(String TotalDiscounts)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Charges ").replaceAll("M_Category", "Total Discounts").replaceAll("M_InnerText", TotalDiscounts), "Total Discounts of Charges  - "+TotalDiscounts, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Account Status of Account Summary in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	AccountStatus
	 */

	public void validate_the_Account_Status_of_Account_Summary_in_Account_Tab(String AccountStatus)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Account Summary").replaceAll("M_Category", "Account Status").replaceAll("M_InnerText", AccountStatus), "Account Status of Account Summary - "+AccountStatus, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Entered Cooling off of Account Summary in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	EnteredCoolingoff
	 */

	public void validate_the_Entered_Cooling_off_of_Account_Summary_in_Account_Tab(String EnteredCoolingoff)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Account Summary").replaceAll("M_Category", "Entered Cooling off").replaceAll("M_InnerText", EnteredCoolingoff), "Entered Cooling off of Account Summary - "+EnteredCoolingoff, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Brand of Account Summary in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Brand
	 */

	public void validate_the_Brand_of_Account_Summary_in_Account_Tab(String Brand)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Account Summary").replaceAll("M_Category", "Brand").replaceAll("M_InnerText", Brand), "Brand of Account Summary - "+Brand, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Channel of Account Summary in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Channel
	 */

	public void validate_the_Channel_of_Account_Summary_in_Account_Tab(String Channel)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Account Summary").replaceAll("M_Category", "Channel").replaceAll("M_InnerText", Channel), "Channel of Account Summary - "+Channel, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Branch of Account Summary in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Branch
	 */

	public void validate_the_Branch_of_Account_Summary_in_Account_Tab(String Branch)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_AccountSummary.replaceAll("M_Header", "Account Summary").replaceAll("M_Category", "Branch").replaceAll("M_InnerText", Branch), "Branch of Account Summary - "+Branch, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate for In Collections in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	InCollections
	 */

	public void validate_for_In_Collections_in_Account_Tab(String InCollections)throws Exception {
		Report.fnReportPageBreak("Account Summary tab", driver);
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_Collection.replaceAll("M_Category", "In Collections").replaceAll("M_InnerText", InCollections), "In Collections - "+InCollections, false);
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate for With DCA in Account Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	WithDCA
	 */

	public void validate_for_With_DCA_in_Account_Tab(String WithDCA)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Account_Page_DCA.replaceAll("M_Category", "With DCA").replaceAll("M_InnerText", WithDCA), "With DCA - "+WithDCA, false);
	}
}