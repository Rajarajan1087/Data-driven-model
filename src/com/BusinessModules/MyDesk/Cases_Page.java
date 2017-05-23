package com.BusinessModules.MyDesk;
/**Class Name		: Cases Page	
 * Description		: This class contains functions for the Case_Details_Page which is part of customer summary page	
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
import com.ObjectIdentifiers.PEGAConstants;
import com.ObjectIdentifiers.XP_PEGA_MyDesk;
import com.Utils.Reusables;
import com.WebActions.WebActions;
import com.relevantcodes.extentreports.LogStatus;


public class Cases_Page extends WebActions implements XP_PEGA_MyDesk,Generic,MyDeskEnumerations,PEGAConstants {
	public WebDriver driver;
	public Reporter Report;

	public Cases_Page(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;
		
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Cases frame to load
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void wait_for_Verify_Cases_Frame_to_load()throws Exception {
		if(!waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Associated Service Case"), custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Cases Frame is not Loaded", "Cases Frame is not Loaded", driver);
		}
		else{
			Report.fnReportPageBreak("Cases Frame",driver);
		}
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To hover on Add Case button
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	20 Oct 2016
	 * 	Modified By			:	Rajan
	 */

	public void hover_on_Add_Case_button()throws Exception {
		
		boolean b=true;
		while(b)
		{
			VerifyElementPresentAndHover(Desktop_XPATH_Cases_Frame_AddCase_Button, "Add Case button");
			
			if(waitForElementToAppear(Desktop_XPATH_Cases_Frame_Add_cases_Button, 2))
				b=false;
	
		}
		
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Enter Action to Search
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	ActionName
	 */

	public void enter_the_action_to_search(String ActionName)throws Exception {
		boolean breaker=true;
		
		while(breaker)
		{
			driver.findElement(By.id("CPMTaskSearchInput")).clear();
			VerifyElementPresentAndClick(Desktop_XPATH_Cases_Frame_SearchAction_Text, ActionName);
			VerifyElementPresentAndType(Desktop_XPATH_Cases_Frame_SearchAction_Text, "Action to search", ActionName);
				if(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Cases_Frame_SearchAction_Text, "value").equalsIgnoreCase(ActionName))
				{
					if(select_theEntered_the_action_to_search(ActionName))
						break;
					else
						continue;
				}
		}
		
	}
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Choose the Entered Action to Search
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public boolean select_theEntered_the_action_to_search(String ActionName) {
		Report.fnReportPageBreak(ActionName+" Entered", driver);
		try {
			
			Thread.sleep(2000);
			
			if(elementExists(Desktop_XPATH_Cases_Frame_Choose_CaseCategory.replaceAll("Action", ActionName), t*2))
			{
				VerifyElementPresentAndClick(Desktop_XPATH_Cases_Frame_Choose_CaseCategory.replaceAll("Action", ActionName), "Action to search");
				return true;
			}
			else
				return false;
			
		} catch (Exception e) 
		{
			return false;
			
		}
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To click on Search button
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void click_on_Search_Button()throws Exception {
		VerifyElementPresentAndClick(Desktop_XPATH_Cases_Frame_Search_Button, "Search button for cases");
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To click on specific task button
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_on_Specific_Action_Button(String Action)throws Exception {
		
		VerifyElementPresentAndClick(Desktop_XPATH_Cases_Frame_Addtask_Button.replaceAll("Action", Action), Action+" button for cases");
		Report.fnReportPageBreak(Action+" Clicked", driver);
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify page size options in cases page after load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_PageSize_options()throws Exception {
		VerifyElementPresentAndSelectFromDropDown(Desktop_XPATH_Cases_Frame_PageSize_DropDown,"5");
		VerifyElementPresentAndSelectFromDropDown(Desktop_XPATH_Cases_Frame_PageSize_DropDown,"10");
		VerifyElementPresentAndSelectFromDropDown(Desktop_XPATH_Cases_Frame_PageSize_DropDown,"20");
		VerifyElementPresentAndSelectFromDropDown(Desktop_XPATH_Cases_Frame_PageSize_DropDown,"30");
		VerifyElementPresentAndSelectFromDropDown(Desktop_XPATH_Cases_Frame_PageSize_DropDown,"50");
		VerifyElementPresentAndSelectFromDropDown(Desktop_XPATH_Cases_Frame_PageSize_DropDown,"100");
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify on specific task page got loaded
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void waitFor_Specific_Task_PageLoad(String task)throws Exception {
		if(waitForElementToAppear(Desktop_XPATH_Cases_Frame_Specific_Task_PageLoad.replaceAll("task", task), custTimeOut))
			Report.fnReportPass(task+" page got loaded successfully", driver);
			else
			Report.fnReportFail(task+" page not getting loaded.", driver);
	}
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To  verify available action category for cases
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	26 Oct 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	CaseCategories can be TalkSafe,Verify Caller,Payments
	 */

	public void Verify_Action_Category_for_Cases(String CaseCategory)throws Exception {
		
		if(waitForElementToAppear(Desktop_XPATH_Cases_Frame_ActionCategory_Button.replace("M_Category", CaseCategory), 1))
			Report.fnReportPass(CaseCategory+" in Add case panel is avaiable");
		
	}
	
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select action category for cases
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	26 Oct 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	CaseCategories can be TalkSafe,Verify Caller
	 */

	public void select_Action_Category_for_Cases(String CaseCategory)throws Exception {
		
do		
		{
			try {
				VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Cases_Frame_ActionCategory_Button.replace("M_Category", CaseCategory), "Case Category - "+CaseCategory);
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception Thrown");
				if(e.getLocalizedMessage().contains("Element is no longer valid"))
					{
					
					refreshpage(driver);
					hover_on_Add_Case_button();
					select_Action_Category_for_Cases(CaseCategory);
					}
			}
		}while(!waitForElementToAppear(Desktop_XPATH_Cases_Frame_ActionCategory_Button_Selected.replace("M_Category", CaseCategory), 1));
		Report.fnReportPageBreak("Add Case Panel", driver);
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To click on Add cases button
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void click_on_Add_cases_button()throws Exception {
		Report.fnReportPageBreak("Add case ", driver);
		VerifyElementPresentAndClick(Desktop_XPATH_Cases_Frame_Add_cases_Button, "Add cases");
		
	}
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To click on Specific Button to navigate options ( Fixed / Mobile) 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	19 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_on_PayFor_In_Payment(String Option)throws Exception {
		
		if(Option.contains("Mobile"))
		{
			do{
				VerifyElementPresentAndClick(Desktop_XPATH_PayFor_Elements_Mobile_Not_Selected, "Mobile Tab");
				Thread.sleep(1000);
				}
			while(!waitForElementToAppear(Desktop_XPATH_PayFor_Elements_Mobile_Enabled_Selected, 1));
		}
		
		else
		{
			do{
				VerifyElementPresentAndClick(Desktop_XPATH_PayFor_Elements_FixedLine_Not_Selected, "Mobile Tab");
				Thread.sleep(1000);
				}
			while(!waitForElementToAppear(Desktop_XPATH_PayFor_Elements_FixedLine_Enabled_Selected, 1));
		}
		
		
	}
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify payFor tab Enabled Fixed Line
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Payment_payFor_Elements(boolean Fixed,boolean mobile)throws Exception {
				
		if(Fixed)
			VerifyElementPresent(Desktop_XPATH_PayFor_Elements_FixedLine_Enabled_Selected, "PayFor Fixed line Enabled is "+Fixed);
			else
			VerifyElementPresent(Desktop_XPATH_PayFor_Elements_FixedLine_Not_Selected, "PayFor Fixed line Disabled is "+Fixed);	
		
		Report.fnReportPageBreak("Payment page case ", driver);
		
		if(mobile)
			VerifyElementPresent(Desktop_XPATH_PayFor_Elements_Mobile_Enabled_Selected, "PayFor Mobile Enabled is "+Fixed);
			else
			VerifyElementPresent(Desktop_XPATH_PayFor_Elements_Mobile_Not_Selected, "PayFor Mobile Disabled is "+Fixed);
	}
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Payment Options tab Elements
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Payment_PaymentOptions_Elements(boolean DirectDebit,boolean OneOff,boolean Recurring)throws Exception {
		
		
		if(DirectDebit)
			VerifyElementPresent(Desktop_XPATH_PaymentOptions_Elements_DirectDebit_Enabled_Selected, "Payment Options DirectDebit Enabled is "+DirectDebit);
			else
			VerifyElementPresent(Desktop_XPATH_PaymentOptions_Elements_DirectDebit_Not_Selected, "Payment Options DirectDebit Disabled is "+DirectDebit);	
		
		if(OneOff)
			VerifyElementPresent(Desktop_XPATH_PaymentOptions_Elements_OneOffPay_Enabled_Selected, "Payment Options OneOff Enabled is "+OneOff);
			else
			VerifyElementPresent(Desktop_XPATH_PaymentOptions_Elements_OneOffPay_Not_Selected, "Payment Options OneOff Disabled is "+OneOff);
		
		if(Recurring)
			VerifyElementPresent(Desktop_XPATH_PaymentOptions_Elements_RecurringCard_Enabled_Selected, "Payment Options Recurring Enabled is "+Recurring);
			else
			VerifyElementPresent(Desktop_XPATH_PaymentOptions_Elements_RecurringCard_Not_Selected, "Payment Options Recurring Disabled is "+Recurring);
		
	}
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify PayWith tab Elements
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Payment_PayWith_Elements(boolean Registered,boolean UnRegistered)throws Exception {
		
		
		if(Registered)
			VerifyElementPresent(Desktop_XPATH_PaytWith_Elements_RegisteredCard_Enabled_Selected, "PayWith Registered Card Enabled is "+Registered);
			else
			VerifyElementPresent(Desktop_XPATH_PaytWith_Elements_RegisteredCard_Not_Selected, "PayWith Registered Card Disabled is "+Registered);	
		
		if(UnRegistered)
			VerifyElementPresent(Desktop_XPATH_PaytWith_Elements_UnRegisteredCard_Enabled_Selected, "PayWith UnRegistered Card Enabled is "+UnRegistered);
			else
			VerifyElementPresent(Desktop_XPATH_PaytWith_Elements_UnRegisteredCard_Not_Selected, "PayWith UnRegistered Card Disabled is "+UnRegistered);
		
		
	}
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Registered card panel
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Payment_RegisteredCardFor_Elements(boolean FutureOneOff,boolean RecurringCard)throws Exception {
		
		
		if(FutureOneOff)
			VerifyElementPresent(Desktop_XPATH_RegisterCardFor_Elements_FutureOneOffPay_Enabled_Selected, "Registered Card for Future one-off payments Enabled is "+FutureOneOff);
			else
			VerifyElementPresent(Desktop_XPATH_RegisterCardFor_Elements_FutureOneOffPay_Not_Selected, "Registered Card for Future one-off payments Disabled is "+FutureOneOff);	
		
		if(RecurringCard){}
//			VerifyElementPresent(Desktop_XPATH_RegisterCardFor_Elements_RecurringCard_Enabled_Selected, "Registered Card for Recurring card payments Enabled is "+RecurringCard);
			
//			VerifyElementPresent(Desktop_XPATH_RegisterCardFor_Elements_RecurringCard_Not_Selected, "Registered Card for Recurring card payments Disabled is "+RecurringCard);
		
		
		}
	
	
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Card Holder billing address retention button click
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_Payment_cardHolder_Billing_Address_Retention_ButtonClick_ToEnable()throws Exception 
	{
				do	{
					VerifyElementPresentAndClick(Desktop_XPATH_Payment_CardHolderAddress_BillingAddressButton_Not_Selected, "Same As Billing Address Button");
					Thread.sleep(2000);
					}while(!VerifyElementPresent(Desktop_XPATH_Payment_CardHolderAddress_BillingAddressButton_Enabled_Selected, "Same As Billing Address Button", false));
					
	}
	
	
	
	
	
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Payment account details values
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Payment_AccountDetails_Table(String Column,String Value)throws Exception 
	{
		
					VerifyElementPresent(Desktop_XPATH_Payments_AccountDetails.replaceAll("M_Category", Column).replaceAll("M_InnerText", Value), "Account details on column "+Column+" With value"+Value);
					Thread.sleep(500);
	}
	
	

	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Payment Mobile account details values
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Payment_Mobile_AccountDetails_Table(String Column,String Value)throws Exception 
	{
		
					VerifyElementPresent(Desktop_XPATH_Payments_Mobile_AccountDetails.replaceAll("M_Category", Column).replaceAll("M_InnerText", Value), "Account details on column "+Column+" With value"+Value);
					Thread.sleep(500);
	}
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Payment Mobile account details values
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	27 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_Payment_Mobile_AccountDetails_Table(String Column,String Value)throws Exception 
	{
		
					VerifyElementPresentAndClick(Desktop_XPATH_Payments_Mobile_AccountDetails.replaceAll("M_Category", Column).replaceAll("M_InnerText", Value), "Account details on column "+Column+" With value"+Value);
					Thread.sleep(500);
	}
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Registered credit card  details values
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	23 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Payment_RegistredCardDetails_Table(String Column,String Value)throws Exception 
	{
		
					VerifyElementPresent(Desktop_XPATH_Payments_RegisteredcardDetails_Row1.replaceAll("M_Category", Column).replaceAll("M_InnerText", Value), "Regsitred Card details on "+Column+" With value"+Value);
					Thread.sleep(500);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the customer  Name with card details in input box
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameters			:	
	 */

	public void verify_the_Customer_NameOnCard_Details(String CallerName)throws Exception {
		String cn= VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_NameOnCard,"value");
		if(CallerName.contains(cn))
		{
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_NameOnCard, "Desktop_XPATH_Payment_CardDetail_NameOnCard", false);
			Report.fnReportPass("Name On Card found as expected", driver);
		}
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the customer  Name with card details in label
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Jan 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameters			:	
	 */

	public void verify_the_Customer_NameOnCard_Details_label(String CallerName)throws Exception {
		{
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_NameOnCard_label.replaceAll("M_Validation_text", CallerName), "Caller Name in label format");
		}

	}
	
	
	
	
	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	Clicking Confirm  button in case cancellation 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	15 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_Confirm_Button()throws Exception {

		Report.fnReportPageBreak("Before Clicking confirm button", driver);
		Thread.sleep(1000);
		if(waitForElementToAppear(Desktop_XPATH_Confirm_Button, timeOut))
			VerifyElementPresentAndClick(Desktop_XPATH_Confirm_Button, "Confirm Button");
	}

	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 1 of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	AddressLine1
	 */

	public void validate_the_Address_Line_1_of_Billing_Address_in_CardHolder_Address(String AddressLine1)throws Exception {
		Report.fnReportPageBreak("Card Holder Billing Address bar ", driver);
		try {
			VerifyElementPresent(Desktop_XPATH_Payment_CardHolderAddress_Elements.replaceAll("Elements", AddressLine1), "CardHolder Address Line1",false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Address_Line_2_of_Billing_Address_in_CardHolder_Address(String AddressLine2)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Payment_CardHolderAddress_Elements.replaceAll("Elements", AddressLine2), "Address Line 2 of Billing Address - "+AddressLine2, false);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Town/City of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Town_City_of_Billing_Address_in_CardHolder_Address(String TownCity)throws Exception {
		
			VerifyElementPresent(Desktop_XPATH_Payment_CardHolderAddress_Elements.replaceAll("Elements", TownCity), "Town/City of Billing Address - "+TownCity);
		
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Country of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Country_of_Billing_Address_in_CardHolder_Address(String Country)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Payment_CardHolderAddress_Elements.replaceAll("Elements", Country), "Country of Billing Address - "+Country, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Postcode of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Postcode_of_Billing_Address_in_CardHolder_Address(String Postcode)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Payment_CardHolderAddress_Elements.replaceAll("Elements", Postcode), "Postcode of Billing Address - "+Postcode, false);
	}
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Registered card panel - Future One off payments button 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_Payment_RegisteredCardFor_FutureOneOff()throws Exception {
	do{
		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_RegisterCardFor_Elements_FutureOneOffPay_Not_Selected, "Registered Card for Future one-off payments clicked");
		Thread.sleep(2000);
	}while(!VerifyElementPresent(Desktop_XPATH_RegisterCardFor_Elements_FutureOneOffPay_Enabled_Selected, "Registered Card for Future one-off payments Eanbled", false));
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in Address Line 1 of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	AddressLine1
	 */

	public void validate_the_Error_Address_Line_1_of_Payment_Billing_Address_in_CardHolder_Address(String Error)throws Exception {
		Report.fnReportPageBreak("Card Holder Billing Address bar ", driver);
		String ErrorPath=Desktop_XPATH_Payment_CardHolderAddress_AddressLine1+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
		VerifyElementPresent(ErrorPath, "CardHolder Address Line1 input field Error:  "+Error,false);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in Town/City of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Error_Town_City_of_Payment_Billing_Address_in_CardHolder_Address(String Error)throws Exception {
		
		String ErrorPath=Desktop_XPATH_Payment_CardHolderAddress_TownCity+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
		VerifyElementPresent(ErrorPath, "CardHolder TownCity input field Error : "+Error,false);
		
		
	}
	

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in Country of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Error_Country_of_Payment_Billing_Address_in_CardHolder_Address(String Error)throws Exception {
		String ErrorPath=Desktop_XPATH_Payment_CardHolderAddress_Country+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
		VerifyElementPresent(ErrorPath, "CardHolder Country input field Error : "+Error,false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in Postcode of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Error_Postcode_of_Payment_Billing_Address_in_CardHolder_Address(String Error)throws Exception {
		String ErrorPath=Desktop_XPATH_Payment_CardHolderAddress_PostCode+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
		VerifyElementPresent(ErrorPath, "CardHolder PostCode input field Error : "+Error,false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 1 of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	AddressLine1
	 */

	public void enter_the_Address_Line_1_of_Payment_Billing_Address_in_CardHolder_Address(String AddressLine1)throws Exception {
		Report.fnReportPageBreak("Card Holder Billing Address bar ", driver);
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardHolderAddress_AddressLine1, "CardHolder Address Line1",AddressLine1);
	}
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_the_Address_Line_2_of_Payment_Billing_Address_in_CardHolder_Address(String AddressLine2)throws Exception {
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardHolderAddress_AddressLine2, "CardHolder Address Line2",AddressLine2);
		
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Town/City of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_the_Town_City_of_Payment_Billing_Address_in_CardHolder_Address(String TownCity)throws Exception {
		
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardHolderAddress_TownCity, "CardHolder TownCity",TownCity);	
		
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Country of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_the_County_of_Payment_Billing_Address_in_CardHolder_Address(String County)throws Exception {
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardHolderAddress_County, "CardHolder County",County);
		
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Country of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_the_Country_of_Payment_Billing_Address_in_CardHolder_Address(String Country)throws Exception {
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardHolderAddress_Country, "CardHolder Country",Country);
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Postcode of Billing Address in CardHolder_Address
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_the_Postcode_of_Payment_Billing_Address_in_CardHolder_Address(String Postcode)throws Exception {
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardHolderAddress_PostCode, "CardHolder Postcode",Postcode);
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the different type of card types available
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_All_CardTypes_in_Payment()throws Exception {
		
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_CardType_VISA, "Visa Icon");
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_CardType_AMEX, "Amex Icon");
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_CardType_MAESTRO, "Maestro Icon");
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_CardType_MASTERCARD, "Master Card Icon");
		
		
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Entering value into Payment Credit card start date
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	13 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_StartDate_Creditcard_Payment(String month,String year)throws Exception {
			VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardDetail_StartDate_Month, "Payment Credit card Startdate month",month);
			VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardDetail_StartDate_Year, "Payment Credit card Startdate year",year);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Validate value into Payment Credit card start date
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	22 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_StartDate_Creditcard_Payment(String month,String year)throws Exception {
			if(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_StartDate_Month, "value").equals(month))
				Report.fnReportPass("Credit card start date Month present as expected");
			if(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_StartDate_Year, "value").equals(year))
				Report.fnReportPass("Credit card start date year present as expected");
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Entering value into Name of the card in  Credit card in Payment
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	13 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_NameOfTheCard_Creditcard_Payment(String name)throws Exception {

			VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardDetail_NameOnCard, "Name of the credit card holder",name);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Entering value into Payment Credit card End date
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	13 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_EndDate_Creditcard_Payment(String month,String year)throws Exception {
			VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardDetail_EndDate_Month, "Payment Credit card Startdate month",month);
			VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardDetail_EndDate_Year, "Payment Credit card Startdate year",year);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Validate value into Payment Credit card End date
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	22 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_EndDate_Creditcard_Payment(String month,String year)throws Exception {
			if(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_EndDate_Month, "value").equals(month))
				Report.fnReportPass("Credit card End date Month present as expected");
			if(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_EndDate_Year, "value").equals(year))
				Report.fnReportPass("Credit card End date year present as expected");
	}
	
	
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Validate value into Payment Credit card End date in label format
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Jan 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameters			:	
	 */

	public void verify_EndDate_Creditcard_Payment_label(String month,String year)throws Exception {
		{
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_EndDate_Month_label.replaceAll("M_Validation_text", month), "Expiry date month in label format");
			VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_EndDate_Year_label.replaceAll("M_Validation_text", year), "Expiry date year in label format");
		}

	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in NameOfTheCard_Creditcard_Payment
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	13 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	Error
	 */

	public void validate_the_Error_NameOfTheCard_Creditcard_Payment(String Error)throws Exception {
		
		String ErrorPath=Desktop_XPATH_Payment_CardDetail_NameOnCard+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
//		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Payment_CardDetail_NameOnCard_label, "CardDetail_NameOnCard_label");
		VerifyElementPresent(ErrorPath, "Name of the card error :  "+Error,false);
		Report.fnReportPageBreak("Card Holder Credit card details Panel ", driver);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in Payment Credit card End date
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	13 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Error_EndDate_Creditcard_Payment(String Error)throws Exception {

		String ErrorPath=Desktop_XPATH_Payment_CardDetail_EndDate_Month+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
		VerifyElementPresent(ErrorPath, "Payment Credit card Enddate : "+Error,false);
//		ErrorPath=Desktop_XPATH_Payment_CardDetail_EndDate_Year+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", yearErr);
//		VerifyElementPresent(ErrorPath, "Payment Credit card Enddate year: "+yearErr,false);
			
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in Payment Credit card Start date
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	13 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Error_StartDate_Creditcard_Payment(String Error)throws Exception {

		String ErrorPath=Desktop_XPATH_Payment_CardDetail_StartDate_Month+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
		VerifyElementPresent(ErrorPath, "Payment Credit card Startdate : "+Error,false);
//		ErrorPath=Desktop_XPATH_Payment_CardDetail_EndDate_Year+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", yearErr);
//		VerifyElementPresent(ErrorPath, "Payment Credit card Enddate year: "+yearErr,false);
			
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the error code in Cancel the work comment
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	16 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Error_CancelThisWork_CommentBox(String Error)throws Exception {

		String ErrorPath=Desktop_XPATH_Exit_Interation_Comments+Desktop_XPATH_Payment_CardDetail_ErrorMessage.replaceAll("Error", Error);
		VerifyElementPresent(ErrorPath, "Payment Credit card Enddate month: "+Error,false);
		
			
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click card not recognised button in  Cancel the work comment
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	16 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_CardNotrecognised_In_CancelWork_And_VerifyComment()throws Exception {

	do
		{	
		VerifyElementPresentAndClick(Desktop_XPATH_Payments_CardNotrecognisedButton_Disabled, "Card Not Recognised Button");
		Thread.sleep(1000);
		}
	while(waitForElementToAppear(Desktop_XPATH_Payments_CardNotrecognisedButton_enabled, 1));
		String Msg="Payment case cancelled - due to card not recognised.";
		if(Msg.equals(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Exit_Interation_Comments, "value")))
			Report.fnReportPass(Msg+" Exist as expected", driver);
				
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Customer Changed Mind button in  Cancel the work comment
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	16 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_CustomerChangedMind_In_CancelWork_And_VerifyComment()throws Exception {

		do
			{
			VerifyElementPresentAndClick(Desktop_XPATH_Payments_CustomerChangedMindButton_Disabled, "Customer Changed Mind Button");
			Thread.sleep(1000);
			}
		while(waitForElementToAppear(Desktop_XPATH_Payments_CustomerChangedMindButton_enabled, 1));
		
		String Msg="Payment case cancelled - due to customer changed mind.";
		if(Msg.equals(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Exit_Interation_Comments, "value")))
			Report.fnReportPass(Msg+" Exist as expected", driver);
				
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Technical Issues button in  Cancel the work comment
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	19 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_Technicalissues_In_CancelWork_And_VerifyComment()throws Exception {

	do
		{	
		VerifyElementPresentAndClick(Desktop_XPATH_Payments_TechnicalIssueButton_Disabled,"Card Not Recognised Button");
		Thread.sleep(1000);
		}
	while(waitForElementToAppear(Desktop_XPATH_Payments_TechnicalIssueButton_enabled, 1));
		String Msg="Case cancelled due to technical failure and unable to proceed with payment. Customer advised to try to make payment using MyAccount or to try again later.";
		if(Msg.equals(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Exit_Interation_Comments, "value")))
			Report.fnReportPass(Msg+" Exist as expected", driver);
				
	}

	

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Entering Higher value in Amount to pay field than account balance
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	16 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void Enter_Higher_Value_AmountToPay_Payment()throws Exception {

	int val=Integer.parseInt(VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_AccountBalanceInput_Integer, "value"));
	if(val<1)
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardDetail_AccountBalanceInput_Integer, "Amount to pay Integer text box", "1");
	else
	{
		val=val*10;
		String intp=Integer.toString(val);
		VerifyElementPresentAndClearType(Desktop_XPATH_Payment_CardDetail_AccountBalanceInput_Integer, "Amount to pay Integer text box", intp);
	}
	
	VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Amount To Pay"), "Payments - Amount To pay label");
			
	}
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Account balance updated in text box
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	16 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_AccountBalance_Creditcard_Payment(String Balance)throws Exception {
		int indexStr=Balance.indexOf(".");
		String intp=Balance.substring(0, indexStr),decp=Balance.substring(indexStr+1);
		System.out.println(indexStr);
		String intpa=VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_AccountBalanceInput_Integer, "value");
		String decpa=VerifyElementPresentAndGetAttribute(Desktop_XPATH_Payment_CardDetail_AccountBalanceInput_Decimal, "value");
	
		if(intp.equalsIgnoreCase(intpa))
				Report.fnReportPass("Account balance in Amount to pay integer part : "+intp+" got updated as expected ", driver);
		else
				Report.fnReportFail("Incorrect value in Integer part "+intpa, driver);
				

		if(decp.equalsIgnoreCase(decpa))
				Report.fnReportPass("Account balance in Amount to pay decimal part : "+decp+" got updated as expected ", driver);
		else
				Report.fnReportFail("Incorrect value in Decimal part "+decpa, driver);
}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select the required card type present
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_required_CardType_in_Payment(CardType CT)throws Exception {
		
		switch (CT) {
		
		case VISA:
			VerifyElementPresentAndClick(Desktop_XPATH_Payment_CardDetail_CardType_VISA, "Visa Icon");
			break;
			
		case AMEX:
			VerifyElementPresentAndClick(Desktop_XPATH_Payment_CardDetail_CardType_AMEX, "Amex Icon");
			break;
		
		case MAESTRO:
			VerifyElementPresentAndClick(Desktop_XPATH_Payment_CardDetail_CardType_MAESTRO, "Maestro Icon");
			break;
			
		case MASTERCARD:
			VerifyElementPresentAndClick(Desktop_XPATH_Payment_CardDetail_CardType_MASTERCARD, "Master Card Icon");
			break;
		}
			
		
		
	}
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Payment account details values
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_IssueNumber_CardDetails_payments()throws Exception 
	{
		VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_IssueNum_MAESTRO, "Issue Number field");
		Report.fnReportPageBreak("Issue Number Validation After Maestro Card selection", driver);
		
	}
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Error Message When no card type is selected and user try to save card
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	22 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_error_Card_NotSelected_CardDetails_payments()throws Exception 
	{
		VerifyElementPresent(Desktop_XPATH_Payments_CardType_NotSelected_Error.replaceAll("M_ErrorMsg", Error_Card_NotSelected), "Error Message When no card type is selected");
		
	}
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Estabilish the secure connection button
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	22 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_Estabilish_Secure_Connection_Button()throws Exception 
	{
		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Establish Secure Connection"), "Establish Secure Connection Button");
		
	}
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify latest Case status in Associated service cases list 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void verify_Latest_Cases_Status_In_List(String expectedState)throws Exception 
	{
		
		if(waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Associated Service Case"), 3))
			VerifyElementPresent(Desktop_XPATH_CaseList_CaseStatus_FirstOnTable_ValueChecker.replaceAll("M_Case_Status", expectedState), "First record status in Case list table");
		
		Report.fnReportPageBreak("Case list page", driver);
		
	}	
	
	
	
	/************************************************************************************************
	 * Verification Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Associated service cases table list and get details of the first record 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Jan 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public String[] verify_Get_Latest_Cases_Details_From_List()throws Exception 
	{
		String[] firstCaseData=new String[4];
		if(waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Associated Service Case"), 3))
			{			
			firstCaseData[0]=VerifyElementPresentAndGetText(Desktop_XPATH_CaseList_CaseID_FirstOnTable, "First record Case ID in Case list table");
			firstCaseData[1]=VerifyElementPresentAndGetText(Desktop_XPATH_CaseList_CaseType_FirstOnTable, "First record Case Type in Case list table");
			firstCaseData[2]=VerifyElementPresentAndGetText(Desktop_XPATH_CaseList_CaseStatus_FirstOnTable, "First record Case Status in Case list table");
			firstCaseData[3]=VerifyElementPresentAndGetText(Desktop_XPATH_CaseList_CaseDateTime_FirstOnTable, "First record Case DateTime in Case list table");
			}
		
		Report.fnReportPageBreak("Case list page", driver);
		return firstCaseData;
		
	}


/************************************************************************************************
 * Verification Functions 																		*	
 ***********************************************************************************************/

/** Description 		: 	To click latest Case ID in Associated service cases list 
 * 	Coded by 			:	Rajan
 * 	Created Data		:	04 Jan 2017
 * 	Last Modified Date	:	
 * 	Modified By			:	
 */

public void click_Latest_CaseID_In_List()throws Exception 
	{
	if(waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Associated Service Case"), 3))
		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_CaseList_CaseID_FirstOnTable, "Case ID in First record in Case list table");
	
	Thread.sleep(1000);
	
	Report.fnReportPageBreak("Case details page", driver);
	}



/************************************************************************************************
 * Verification Functions 																		*	
 ***********************************************************************************************/

/** Description 		: 	To click close button after case ID been clicked and been opened
 * 	Coded by 			:	Rajan
 * 	Created Data		:	04 Jan 2017
 * 	Last Modified Date	:	
 * 	Modified By			:	
 */

public void click_CloseButton_Opened_CaseID_In_List()throws Exception 
	{
	
	Report.fnReportPageBreak("Before clicking close button", driver);
	if(waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Case interaction history"), 3))
		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Close_Button, "Close button");
	}




/************************************************************************************************
 * Verification Functions 																		*	
 ***********************************************************************************************/

/** Description 		: 	To verify Associated Card details in  table list and get details of the first record 
 * 	Coded by 			:	Rajan
 * 	Created Data		:	05 Jan 2017
 * 	Last Modified Date	:	
 * 	Modified By			:	
 */

public String[] verify_Get_Latest_RegisteredCard_Details_From_List()throws Exception 
	{
		String[] firstCardData=new String[4];
		if(waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Cards registered for One-off payment"), 3))
			{			
			try {
				firstCardData[0]=VerifyElementPresentAndGetText(Desktop_XPATH_Payment_CardList_CardNumber_FirstOnTable, "First record Card Number in Card list table");
				firstCardData[1]=VerifyElementPresentAndGetText(Desktop_XPATH_Payment_CardList_NameOncard_FirstOnTable, "First record Name On Card in Card list table");
				firstCardData[2]=VerifyElementPresentAndGetText(Desktop_XPATH_Payment_CardList_Expirydate_FirstOnTable, "First record Expiry Date in Card list table");
				firstCardData[3]=firstCardData[2].substring(3, 4);
				firstCardData[2]=firstCardData[2].substring(0, 1);
				
				System.out.println("Inside registeredcard details loop and value is "+firstCardData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	
	Report.fnReportPageBreak("Card list page", driver);
	return firstCardData;
	
	}


/************************************************************************************************
 * Verification Functions 																		*	
 ***********************************************************************************************/

/** Description 		: 	To click first Card details in  table list  
 * 	Coded by 			:	Rajan
 * 	Created Data		:	05 Jan 2017
 * 	Last Modified Date	:	09 Jan 2017
 * 	Modified By			:	Rajan
 */

public void Click_First_Record_In_CreditCardList(String CardNumber)throws Exception 
	{
		for (int i = 0; i < 2; i++) {
			VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Payment_CardList_CardNumber_FirstOnTable,"Fisrts record in card list");
			Thread.sleep(2000);
			if (verify_CardNumber_In_CreditCard_Panel(CardNumber))
				break;
		}	
	}


/************************************************************************************************
 * Verification Functions 																		*	
 ***********************************************************************************************/

/** Description 		: 	To verify card number pop up as expected 
 * 	Coded by 			:	Rajan
 * 	Created Data		:	06 Jan 2017
 * 	Last Modified Date	:	09 Jan 2017
 * 	Modified By			:	Rajan
 */

public boolean verify_CardNumber_In_CreditCard_Panel(String cardNumber)throws Exception 
	{
		return VerifyElementPresent(Desktop_XPATH_Payment_CardDetail_CreditCardNumber_label.replaceAll("M_Validation_text", cardNumber), "Creditcard number");
	}


/************************************************************************************************
* Talk Safe Functions *
***********************************************************************************************/

/** Description : To verify Talksafe case account types
* Coded by :
* Created Data :
* Last Modified Date :
* Modified By :
*/

public void Talksafe_checkFirstname(String FirstName)throws Exception
{

	
if(VerifyElementPresent(Talksafe_FirstName.replaceAll("M_AccType", FirstName), "FirstName")){
Report.fnReportPass("Enrollment Options Updated Firstname matches as  "+FirstName);
}else{
Report.fnReportFail("Enrollment Options Updated Firstname matches as "+FirstName);
}

}
/************************************************************************************************
* Talk Safe Functions *
***********************************************************************************************/

/** Description : To verify Talksafe case account types
* Coded by :
* Created Data :
* Last Modified Date :
* Modified By :
*/

public void Talksafe_checkLastname(String LastName)throws Exception
{

	
if(VerifyElementPresent(Talksafe_FirstName.replaceAll("M_AccType", LastName), "LastName")){
Report.fnReportPass("Enrollment Options Updated Lastname matches as  "+LastName);
}else{
Report.fnReportFail("Enrollment Options Updated Lastname matches as "+LastName);
}

}

/************************************************************************************************
* Talk Safe Functions *
***********************************************************************************************/

/** Description : To verify Talksafe case account types
* Coded by :
* Created Data :
* Last Modified Date :
* Modified By :
*/

public void Talksafe_checkAccountType(String AccType)throws Exception
{

	
if(VerifyElementPresent(Talksafe_AccType.replaceAll("M_AccType", AccType), "AccountType")){
Report.fnReportPass("Enrollment Options Updated for Account Type "+AccType);
}else{
Report.fnReportFail("Enrollment Options Updated for different Account Type "+AccType);
}

}
/************************************************************************************************
* Talk Safe Functions *
***********************************************************************************************/

/** Description : To verify Payment Mobile account details values
* Coded by :
* Created Data :
* Last Modified Date :
* Modified By :
*/

public void Talksafe_EnrollmentOption_Enroll(String EnrollType)throws Exception
{
VerifyElementPresent(Talksafe_EnrollmentOptions_Enabled.replaceAll("M_EnrollType", EnrollType), "Enrollment Option : Enroll");

}

public void Talksafe_EnrollmentOption_NotNow(String EnrollType,String Action)throws Exception
{
VerifyElementPresent(Talksafe_EnrollmentOptions_Disabled.replaceAll("M_EnrollType", EnrollType), "Enrollment Option : Not Now");

if (Action!=null) {
VerifyElementPresentAndClick(Talksafe_EnrollmentOptions_Disabled.replaceAll("M_EnrollType", EnrollType), "Enrollment Option : Not Now");
Thread.sleep(3000);
}

}
public void Talksafe_EnrollmentOption_Never(String EnrollType,String Action)throws Exception
{
VerifyElementPresent(Talksafe_EnrollmentOptions_Disabled.replaceAll("M_EnrollType", EnrollType), "Enrollment Option : Never");
if (Action!=null) {
VerifyElementPresentAndClick(Talksafe_EnrollmentOptions_Disabled.replaceAll("M_EnrollType", EnrollType), "Enrollment Option : Never");
Thread.sleep(3000);
}
}

public void Talksafe_EnrollmentOption_WarningMsg()throws Exception
{
String warningMsg="";
VerifyElementPresent(Talksafe_warningMsg_Never, "Warning Msg for Enrollment Option : Never");

}

public void Talksafe_EnrollmentOption_Submit()throws Exception
{
VerifyElementPresentAndClick(Talksafe_submit, "Submit Enrollment Option");

}
}

