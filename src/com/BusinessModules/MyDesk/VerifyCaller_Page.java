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

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Engine.Reporter;
import com.Enumerations.Generic;
import com.Enumerations.MyDeskEnumerations;
import com.ObjectIdentifiers.XP_PEGA_MyDesk;
import com.WebActions.WebActions;
import com.relevantcodes.extentreports.LogStatus;


public class VerifyCaller_Page extends WebActions implements XP_PEGA_MyDesk,Generic,MyDeskEnumerations {
	public WebDriver driver;
	public Reporter Report;

	public VerifyCaller_Page(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;
		
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Verify Caller to load
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	03 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void wait_for_Verify_Caller_Page_to_load()throws Exception {
		if(!waitForElementToAppear(Desktop_XPATH_Verify_Caller_Page_Validation.replaceAll("M_ValidationText", "Verify Caller"), 4))
		{
			Report.fnReportFailAndTerminateTest("Verify Caller Page is not Loaded", "Verify Caller Page is not Loaded", driver);
		}
		else{
			Report.fnReportPageBreak("Verify Caller Page",driver);
		}
	}

	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the customer Caller Name
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_the_CIB_Caller_name(String CallerName)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_CallerName.replaceAll("M_Category", "Caller Name").replaceAll("M_InnerText", CallerName),"Caller Name - "+CallerName, false);
	}
	
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the customer IDV Status
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	ExpectedStatus can be IDENTIFIED,VERIFIED
	 */

	public void verify_the_CIB_IDV_Status_text(String ExpectedStatus)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_IDVStatus.replaceAll("M_Category", "V Status").replaceAll("M_InnerText", ExpectedStatus),"ID&V Status - "+ExpectedStatus, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the customer Account Number
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 *  Parameters			:	AccountNumber
	 */

	public void verify_the_CIB_Account_Number(String AccountNumber)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_AccountNumber.replaceAll("M_Category", "Account Number").replaceAll("M_InnerText", AccountNumber),"Account Number - "+AccountNumber, false);
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the customer Phone Number
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 *  Parameters			:	CLI
	 */

	public void verify_the_CIB_PhoneNumber(String CLI)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_PhoneNumber.replaceAll("M_Category", "Phone Number").replaceAll("M_InnerText", CLI),"Phone Number - "+CLI, false);
		Report.fnReportPageBreak(CLI+" Phone Number in Sumamry page verified ", driver);
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the customer Package Name
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameters			:	PackageName
	 */

	public void verify_the_CIB_Package_name(String PackageName)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Package.replaceAll("M_Category", "Package").replaceAll("M_InnerText", PackageName),"Package - "+PackageName, false);
	}
	
	
	

	

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To print the My call Length Screen value
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameters			:	
	 */

	public void print_the_CIB_My_Call_Length()throws Exception {
		
		String time=VerifyElementPresentAndGetText(Desktop_XPATH_Verify_Caller_Page_CallLength,"Elpased Timer");
		Report.fnReportPass("My Call length Timer"+time, driver);
		Report.fnReportPageBreak("Elapsed time shown", driver);
	}

	/************************************************************************************************
	 * Data Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To get the customer Account Number
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	03 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public String get_the_customer_Account_Number()throws Exception {
		return VerifyElementPresentAndGetText(Desktop_XPATH_Verify_Caller_Page_CLIVerification.replaceAll("M_Data", "Account Number"), "Account Number");
	}

	/************************************************************************************************
	 * Data Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To get the customer Phone Number
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	03 Oct 2016
	 * 	Modified By			:	Raja
	 * @return 
	 */

	public String get_the_customer_PhoneNumber()throws Exception {
		return VerifyElementPresentAndGetText(Desktop_XPATH_Verify_Caller_Page_CLIVerification.replaceAll("M_Data", "Phone Number"), "CLI");
	}

	/************************************************************************************************
	 * Data Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To get the customer Package Name
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	03 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public String get_the_customer_Package_name()throws Exception {
		return VerifyElementPresentAndGetText(Desktop_XPATH_Verify_Caller_Page_CLIVerification.replaceAll("M_Data", "Package"), "Package Name");
	}

	/************************************************************************************************
	 * Data Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To get the customer Average Monthly Spend
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public String get_the_customer_Average_monthly_spend()throws Exception {
		return VerifyElementPresentAndGetText(Desktop_XPATH_Verify_Caller_Page_CLIVerification.replaceAll("M_Data", "Avg. Monthly Spend"), "Average Monthly Spend");
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the Call Length is getting changed
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void verify_the_Call_Length_to_be_elapsed()throws Exception {
		String Past=VerifyElementPresentAndGetText(Desktop_XPATH_Verify_Caller_Page_CallLength, "Call Length");
		Thread.sleep(1500);
		if(VerifyElementPresentAndGetText(Desktop_XPATH_Verify_Caller_Page_CallLength, "Call Length").equals(Past))
		{
			Report.fnReportFailAndTerminateTest("Call Length is not getiting elapsed", "Call Length is not getiting elapsed", driver);
		}
		else{
			Report.fnReportPass("Call Length is not getting elapsed");
		}
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select the caller Identification type
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	03 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	CallerType can be Account Holder,Non-Account Holder,Nominated User/ Power of Attorney
	 */

	public void select_the_caller_Identification_type(String CallerType)throws Exception {
		VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Page_CallerSelection.replaceAll("M_Header", "Caller Identification").replaceAll("M_InnerText", CallerType), "Caller Identification - "+CallerType);
	}
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify  the caller Name in nominated user selection
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	19 Jan 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	CallerType can be Account Holder,Non-Account Holder,Nominated User/ Power of Attorney
	 */

	public void verify_the_caller_Name(String CallerName)throws Exception {
		
		String FirstName=CallerName.split("A&D")[0];
		String LastName=CallerName.split("A&D")[1];
		VerifyElementPresent(Desktop_XPATH_Verify_Caller_Page_CallerSelection.replaceAll("M_Header", "Caller Name").replaceAll("M_InnerText", "What is the Caller Name?"), "Caller Name Question");
		VerifyElementPresent(Desktop_XPATH_Verify_Caller_Page_CallerSelection.replaceAll("M_Header", "Caller Name").replaceAll("M_InnerText", FirstName), "Caller First Name - "+FirstName);
		VerifyElementPresent(Desktop_XPATH_Verify_Caller_Page_CallerSelection.replaceAll("M_Header", "Caller Name").replaceAll("M_InnerText", LastName), "Caller First Name - "+LastName);
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Verify the caller Identification type
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_the_caller_Identification_type(String CallerType)throws Exception {
		Report.fnReportPageBreak("Caller identification", driver);
		for(String Caller:CallerType.split(","))
		{
		VerifyElementPresent(Desktop_XPATH_Verify_Caller_Page_CallerSelection.replaceAll("M_Header", "Caller Identification").replaceAll("M_InnerText", Caller), "Caller Identification - "+Caller+" Radio button and label  ");
		}
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select the call Reason
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	CallReasons can be General Enquiry,Fault,Outbound Call,Account Status,Make a Payment,Check account balance
	 */

	public void select_the_call_Reason(String CallReasons)throws Exception {
		
	
		for(String Reasons:CallReasons.split(","))
		{
			VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Page_CallReason.replaceAll("M_Header", "Call Reason").replaceAll("M_InnerText", Reasons), "Call Reason - "+Reasons);
		}
		Thread.sleep(1000);
		
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select the General enquiry call Reason
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	09 Mar 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	CallReasons can be General Enquiry
	 */

	public void select_the_call_Reason_GeneralEnquiry() {
		
		
			try {
				VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Page_CallReason_GeneralEnquiry, "Call Reason - GeneralEnquiry");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				select_the_call_Reason_GeneralEnquiry();
			}
			
		
		
		
	}
	
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select the call Reason
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	CallReasons can be General Enquiry,Fault,Outbound Call,Account Status,Make a Payment,Check account balance
	 */

	public void verify_the_call_Reason(String CallReasons)throws Exception {
		
		Report.fnReportPageBreak("Caller Reason", driver);

		for(String Reasons:CallReasons.split(","))
		{
			VerifyElementPresent(Desktop_XPATH_Verify_Caller_Page_CallReason.replaceAll("M_Header", "Call Reason").replaceAll("M_InnerText", Reasons), "Call Reason - "+Reasons+" Check Box and label");
		}
		Thread.sleep(1000);
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To enter the first name
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	FirstName
	 */

	public void Enter_the_First_name(String FirstName)throws Exception {
		VerifyElementPresentAndClearType(Desktop_XPATH_Verify_Caller_Page_FirstName_Text, "First Name of the Customer", FirstName);
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To enter the last name
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	LastName
	 */

	public void Enter_the_Last_name(String LastName)throws Exception {
		VerifyElementPresentAndClearType(Desktop_XPATH_Verify_Caller_Page_LastName_Text, "Last Name of the Customer", LastName);
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select the caller Verification type
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	CallerVerification can be Manually Completed DPA in Trio
	 */

	public void select_the_caller_Verification_type(String CallerVerification)throws Exception {
		for(String CallerVerifications:CallerVerification.split(","))
		{
			if(waitForElementToAppear(Desktop_XPATH_Verify_Caller_Page_CallerVerification.replaceAll("M_Header", "Caller Verification").replaceAll("M_InnerText", CallerVerifications), custTimeOut))
			VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Page_CallerVerification.replaceAll("M_Header", "Caller Verification").replaceAll("M_InnerText", CallerVerifications), "Caller Verification - "+CallerVerifications);
		}
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select the Additional Questions answered correctly
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	17 Oct 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	AdditionalQuestions and answer combination will be passed as parameters  -  can be Can you confirm your house number & postcode,How do we send the bills to you each month,What is your mobile number on the account,What is the email address you have registered with us,What are the last 4 digits of your bank account registered for direct debit with TalkTalk
	 */

	public void select_the_Additional_Questions_answered_correctly(String AdditionalQuestion_Answer)throws Exception {
		
		System.out.println("Enter select_the_Additional_Questions_answered_correctly");
			
				String AdditionalQuestion=AdditionalQuestion_Answer.split("-*-")[0];
				System.out.println(""+AdditionalQuestion);
				String Answer=AdditionalQuestion_Answer.split("-*-")[1];
				System.out.println(""+Answer);
				String XPathToUse=Desktop_XPATH_Verify_Caller_Page_AdditionalQuestions_Answer.replaceAll("M_Header", "Additional Questions").replaceAll("M_InnerText", AdditionalQuestion).replaceAll("M_Answer", Answer)+"/ancestor::tr[1]//*[@type='checkbox']";
				VerifyElementPresentAndClick(XPathToUse, "Additional Questions - "+AdditionalQuestion+" Answer - "+Answer);
			
		
		}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the answers displayed for Additional Questions
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	AdditionalQuestions can be Can you confirm your house number & postcode,How do we send the bills to you each month,What is your mobile number on the account,What is the email address you have registered with us,What are the last 4 digits of your bank account registered for direct debit with TalkTalk
	 */

	public void verify_the_answers_displayed_for_Additional_Questions_Answers(String AdditionalQuestions_Answer)throws Exception {
		Report.fnReportPageBreak("Additional Questions and answers", driver);
		
			String AdditionalQuestion=AdditionalQuestions_Answer.split("-")[0];
			System.out.println(""+AdditionalQuestion);
			String Answer=AdditionalQuestions_Answer.split("-")[1];
			System.out.println(""+Answer);
			VerifyElementPresent(Desktop_XPATH_Verify_Caller_Page_AdditionalQuestions_Answer.replaceAll("M_Header", "Additional Questions").replaceAll("M_InnerText", AdditionalQuestion).replaceAll("M_Answer", Answer), "Additional Questions - "+AdditionalQuestion+" Answer - "+Answer);
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the password displayed for Telephone Password
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void verify_the_password_displayed_for_Telephone_Password(String Password) {
		String TelephonePasswordQuestion="What is the Telephone Password on the Account";
		
		
		try {
			VerifyElementPresent(Desktop_XPATH_Verify_Caller_Page_TelephonePassword_Answer.replaceAll("M_Header", "Telephone Password").replaceAll("M_InnerText", TelephonePasswordQuestion).replaceAll("M_Answer", Password), "Additional Questions - "+TelephonePasswordQuestion+" Answer - "+Password);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			verify_the_password_displayed_for_Telephone_Password("N/A");
		}
	}

	
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click on Submit Button
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void click_on_Submit()throws Exception {
		VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Page_Submit.replaceAll("M_ValidationText", "Submit"), "Submit");
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click on Hide/Show details
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	boolean vararg Hide_Show can be TRUE for Hide details,FALSE for Show details, left alone for toggle details
	 */

	public void click_on_Hide_Show_details(boolean...Hide_Show)throws Exception {
		String toDo="Show";
		if(Hide_Show.length>0)
		{
			if(Hide_Show[0])
			{
				toDo="Hide";
			}
			if(waitForElementToAppear(Desktop_XPATH_Verify_Caller_Page_Hide_Show.replaceAll("M_ValidationText", toDo+" Details"), t))
			{
				VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Page_Hide_Show.replaceAll("M_ValidationText", toDo+" Details"), toDo+" Details");
				Thread.sleep(2000);
			}
			else{
				Report.ReporterLog("Customer details are already in "+toDo+" state", LogStatus.FAIL);
			}
		}
		else{
			toDo="";
			VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Page_Hide_Show.replaceAll("M_ValidationText", toDo+" Details"), toDo+" Details");
		}
		
		Thread.sleep(2000);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Check the telephone password CheckBox
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void select_the_Telephone_Password_answered_correctly()throws Exception {
		
	
	boolean enabled= VerifyElementPresentAndGetCheckBoxEnableDisableStatus(Desktop_XPATH_Verify_Caller_Telephone_Password_CheckBox, "Telephone password CheckBox");	
	boolean checked= VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Caller_Telephone_Password_CheckBox, "Telephone password CheckBox");	
	System.out.println("Telephone password checkbox checked ? "+checked);
	
	
	if(enabled)
	{
		if(!checked)
		{
		VerifyElementPresentAndClick(Desktop_XPATH_Verify_Caller_Telephone_Password_CheckBox, "Telephone password CheckBox");
		Report.fnReportPass("TelephonePasswordChecked", driver);
		}
	}

	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the telephone password CheckBox is checked ? 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_Telephone_Password_CheckBox_EnableCheckStatus(boolean enabledStatus,boolean checkStatus)throws Exception {
		
		boolean enabled= VerifyElementPresentAndGetCheckBoxEnableDisableStatus(Desktop_XPATH_Verify_Caller_Telephone_Password_CheckBox, "Telephone password CheckBox");	
		boolean checked= VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Caller_Telephone_Password_CheckBox, "Telephone password CheckBox");	
		System.out.println("Telephone password checkbox checked ? "+checked);
		
		if(enabled==enabledStatus)
		{
			Report.fnReportPass("Telephone Password Enabled status = "+enabledStatus, driver);
			
			if(checked==checkStatus)
			Report.fnReportPass("Telephone Password  Checked status = "+checkStatus, driver);
			else
			Report.fnReportFail("Telephone Password  Checked status = "+checkStatus, driver);
		}
		
	
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Check required no of CheckBox's as per the input in additional questions from number one
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void select_NumberOf_Required_Additional_Question_CheckBox(int count)throws Exception {
		
		List<WebElement> checkBox=VerifyElementPresentAndGetElementList(Desktop_XPATH_Verify_Caller_Additional_Questions_CheckBox);
		int checks=checkBox.size();
		
		if(checkBox.size()>count)
			checks=count;
		
		for (int i = 1; i <= checks; i++) 
		{
			
			String xpathToVerify=Desktop_XPATH_Verify_Caller_Additional_Questions_CheckBox+"["+i+"]";
			boolean enabled=VerifyElementPresentAndGetCheckBoxEnableDisableStatus(xpathToVerify, "Additional Question:"+i);
			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(xpathToVerify, "Additional Question:"+i);
			
			if(enabled)
			{
				if(!checked)
				{
			VerifyElementPresentAndClick(xpathToVerify, "Additional Question "+i+" been Checked");
				}
			}
			
		}	
		
		Report.fnReportPageBreak("After Additional Question been checked", driver);

	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Check all the CheckBox's in additional questions got UnChecked as Expected
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_Required_Additional_Question_CheckBox_enableCheckStatus(boolean enabledStatus,boolean checkStatus)throws Exception {
		
		List<WebElement> checkBox=VerifyElementPresentAndGetElementList(Desktop_XPATH_Verify_Caller_Additional_Questions_CheckBox);
		
		
		for (int i = 1; i <= checkBox.size(); i++) {
			
			String xpathToVerify=Desktop_XPATH_Verify_Caller_Additional_Questions_CheckBox+"["+i+"]";
			boolean enabled=VerifyElementPresentAndGetCheckBoxEnableDisableStatus(xpathToVerify, "Additional Question:"+i);
			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(xpathToVerify, "Additional Question:"+i);
			
			if(enabled==enabledStatus)
			{
				Report.fnReportPass("Additional Question "+i+"  :  Enabled status = "+enabledStatus, driver);
				
				if(checked==checkStatus)
				Report.fnReportPass("Additional Question "+i+"  :  Checked status = "+checkStatus, driver);
				else
				Report.fnReportFail("Additional Question "+i+"  :  Checked status = "+checkStatus, driver);
			}
			
		}	
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Check all the CheckBox's in additional questions got UnChecked as Expected
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_Additional_Question_CheckBox_enableCheckStatus_ByQuestionOrAnswerText(String AdditionalQuestion,String Answer,boolean enabledStatus,boolean checkStatus)throws Exception {
		
	
			
		
				String XPathToUse=Desktop_XPATH_Verify_Caller_Page_AdditionalQuestions_Answer.replaceAll("M_Header", "Additional Questions").replaceAll("M_InnerText", AdditionalQuestion).replaceAll("M_Answer", Answer)+"/ancestor::tr[1]//*[@type='checkbox']";
				boolean enabled=VerifyElementPresentAndGetCheckBoxEnableDisableStatus(XPathToUse, "Additional Questions - "+AdditionalQuestion+" Answer - "+Answer);
				boolean checked=VerifyElementPresentAndGetCheckBoxStatus(XPathToUse, "Additional Questions - "+AdditionalQuestion+" Answer - "+Answer);
						
			if(enabled==enabledStatus)
			{
				Report.fnReportPass("Additional Question "+AdditionalQuestion+"  :  Enabled status = "+enabledStatus, driver);
				
				if(checked==checkStatus)
				Report.fnReportPass("Additional Question "+AdditionalQuestion+"  :  Checked status = "+checkStatus, driver);
				else
				Report.fnReportFail("Additional Question "+AdditionalQuestion+"  :  Checked status = "+checkStatus, driver);
			}
			else
				Report.fnReportFail("Additional Question "+AdditionalQuestion+"  :  Enabled status = "+enabledStatus, driver);
				
		
		}
	
	
	
	/************************************************************************************************
	 ByPass Verification page 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Exit Interaction page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void wait_for_Bypass_Verification_Page_To_Load()throws Exception {
		
			
		if(!waitForElementToAppear(Desktop_XPATH_Bypass_Verification_pageUniquelabel, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Bypass Verification page is not Loaded", "Bypass Verification page is not Loaded", driver);
		}
		else
		{
			
			Report.fnReportPageBreak("Bypass Verification page",driver);
		}
	}
	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select Bypass reason
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	BypassReason can be Correspondence,Internal Call,Non-Account Holder
	 */

	public void select_Bypass_Reason(String BypassReason)throws Exception {
		VerifyElementPresentAndSelect(Desktop_XPATH_Bypass_Verification_Page_ByPassReason_Select, "Bypass Reason", BypassReason);
	}
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select Bypass subreason
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	BypassSubreason can be Not Sufficient DPA info,Escalations,HLC,HomeMovers,Operations,Other Support/ Back Office Activity,Team Manager
	 */

	public void select_Bypass_Subreason(String BypassSubreason)throws Exception {
		VerifyElementPresentAndSelect(Desktop_XPATH_Bypass_Verification_Page_ByPassSubReason_Select, "Bypass Subreason", BypassSubreason);
	}
	
	
	
	
}

