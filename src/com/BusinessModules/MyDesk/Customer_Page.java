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














import javax.swing.text.Highlighter.Highlight;

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


public class Customer_Page extends WebActions implements XP_PEGA_MyDesk,Generic,MyDeskEnumerations {
	public WebDriver driver;
	public Reporter Report;

	public Customer_Page(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the required frame available or not  in Customer Account Summary of Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_required_FrameVisibility_Customer_Tab(String Frame,boolean status)throws Exception 
	{
		if(status)
		{
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_FrameAvailibility.replaceAll("M_Header", Frame), "Required frame availiable is "+Frame);
		Report.fnReportPageBreak(Frame+ " Frame is Visible", driver);
		}
		else
		{
		VerifyElementNotPresent(Desktop_XPATH_Verify_Customer_Page_FrameAvailibility.replaceAll("M_Header", Frame), "Required frame not availiable and "+Frame);
		Report.fnReportPageBreak(Frame+ " Frame is not Visible", driver);
		}
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Account Number in Customer Account Summary of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	25 Oct 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	AccountNumber
	 */

	public void validate_the_Account_Number_in_Customer_Account_Summary_of_Customer_Tab(String AccountNumber)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CustomerAccountSummary.replaceAll("M_Header", "Landline Account Summary").replaceAll("M_Category", "Account Number").replaceAll("M_InnerText", AccountNumber), "Account Number - "+AccountNumber, false);
		Report.fnReportPageBreak("Customer Account Summary", driver);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Account Status in Customer Account Summary of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	25 Oct 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	AccountStatus
	 */

	public void validate_the_Account_Status_in_Customer_Account_Summary_of_Customer_Tab(String AccountStatus)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CustomerAccountSummary.replaceAll("M_Header", "Landline Account Summary").replaceAll("M_Category", "Account Status").replaceAll("M_InnerText", AccountStatus), "Account Status - "+AccountStatus, false);
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Phone Number in Customer Account Summary of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	PhoneNumber
	 */

	public void validate_the_Phone_Number_in_Customer_Account_Summary_of_Customer_Tab(String PhoneNumber)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CustomerAccountSummary.replaceAll("M_Header", "Landline Account Summary").replaceAll("M_Category", "Phone Number").replaceAll("M_InnerText", PhoneNumber), "Phone Number - "+PhoneNumber, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Package Name in Customer Account Summary of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	PackageName
	 */

	public void validate_the_Package_Name_in_Customer_Account_Summary_of_Customer_Tab(String PackageName)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CustomerAccountSummary.replaceAll("M_Header", "Landline Account Summary").replaceAll("M_Category", "Package Name").replaceAll("M_InnerText", PackageName), "Package Name - "+PackageName, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Package Status in Customer Account Summary of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	PackageStatus
	 */

	public void validate_the_Package_Status_in_Customer_Account_Summary_of_Customer_Tab(String PackageStatus)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CustomerAccountSummary.replaceAll("M_Header", "Landline Account Summary").replaceAll("M_Category", "Package Status").replaceAll("M_InnerText", PackageStatus), "Package Status - "+PackageStatus, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 1 of Correspondence Address in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	AddressLine1
	 */

	public void validate_the_Address_Line_1_of_Correspondence_Address_in_Customer_Tab(String AddressLine1)throws Exception {
		Report.fnReportPageBreak("Correspondence Address", driver);
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Correspondence Address").replaceAll("M_Category", "Address Line 1").replaceAll("M_InnerText", AddressLine1), "Address Line 1 of Correspondence Address - "+AddressLine1, false);
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Correspondence Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Address_Line_2_of_Correspondence_Address_in_Customer_Tab(String AddressLine2)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Correspondence Address").replaceAll("M_Category", "Address Line 2").replaceAll("M_InnerText", AddressLine2), "Address Line 2 of Correspondence Address - "+AddressLine2, false);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Town/City of Correspondence Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Town_City_of_Correspondence_Address_in_Customer_Tab(String TownCity)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Correspondence Address").replaceAll("M_Category", "Town/City").replaceAll("M_InnerText", TownCity), "Town/City of Correspondence Address - "+TownCity, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Correspondence Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Country_of_Correspondence_Address_in_Customer_Tab(String Country)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Correspondence Address").replaceAll("M_Category", "Country").replaceAll("M_InnerText", Country), "Country of Correspondence Address - "+Country, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Postcode of Correspondence Address in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	02 Dec 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	Postcode
	 */

	public void validate_the_Postcode_of_Correspondence_Address_in_Customer_Tab(String Postcode)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Correspondence Address").replaceAll("M_Category", "Postcode").replaceAll("M_InnerText", Postcode), "Postcode of Correspondence Address - "+Postcode, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 1 of Billing Address in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	02 Dec 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	AddressLine1
	 */

	public void validate_the_Address_Line_1_of_Billing_Address_in_Customer_Tab(String AddressLine1)throws Exception {
		Report.fnReportPageBreak("Billing Address", driver);
		System.out.println(AddressLine1);
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_BillingAddress.replaceAll("M_Header", "Billing Address").replaceAll("M_Category", "Address Line 1").replaceAll("M_InnerText", AddressLine1), "Address Line 1 of Billing Address - "+AddressLine1, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Billing Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Address_Line_2_of_Billing_Address_in_Customer_Tab(String AddressLine2)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Billing Address").replaceAll("M_Category", "Address Line 2").replaceAll("M_InnerText", AddressLine2), "Address Line 2 of Billing Address - "+AddressLine2, false);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Town/City of Billing Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Town_City_of_Billing_Address_in_Customer_Tab(String TownCity)throws Exception {
		if(VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Billing Address").replaceAll("M_Category", "Town/City").replaceAll("M_InnerText", TownCity), "Town/City of Billing Address - "+TownCity, false))
		{
			VerifyElementPresentAndClick(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Billing Address").replaceAll("M_Category", "Town/City").replaceAll("M_InnerText", TownCity), "Town/City of Billing Address - "+TownCity);
		}
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Correspondence Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Country_of_Billing_Address_in_Customer_Tab(String Country)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Billing Address").replaceAll("M_Category", "Country").replaceAll("M_InnerText", Country), "Country of Billing Address - "+Country, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Postcode of Billing Address in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Postcode
	 */

	public void validate_the_Postcode_of_Billing_Address_in_Customer_Tab(String Postcode)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_BillingAddress.replaceAll("M_Header", "Billing Address").replaceAll("M_Category", "Postcode").replaceAll("M_InnerText", Postcode), "Postcode of Billing Address - "+Postcode, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 1 of Installation Address in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	AddressLine1
	 */

	public void validate_the_Address_Line_1_of_Installation_Address_in_Customer_Tab(String AddressLine1)throws Exception {
		Report.fnReportPageBreak("Instalation Address", driver);
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_InstallationAddress.replaceAll("M_Header", "Installation Address").replaceAll("M_Category", "Address Line 1").replaceAll("M_InnerText", AddressLine1), "Address Line 1 of Installation Address - "+AddressLine1, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Installation Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Address_Line_2_of_Installation_Address_in_Customer_Tab(String AddressLine2)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Installation Address").replaceAll("M_Category", "Address Line 2").replaceAll("M_InnerText", AddressLine2), "Address Line 2 of Installation Address - "+AddressLine2, false);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Town/City of Installation Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Town_City_of_Installation_Address_in_Customer_Tab(String TownCity)throws Exception {
		VerifyElementPresentAndClick(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Installation Address").replaceAll("M_Category", "Town/City").replaceAll("M_InnerText", TownCity), "Town/City of Installation Address - "+TownCity);
			
			
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Address Line 2 of Installation Address in Customer Tab
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Country_of_Installation_Address_in_Customer_Tab(String Country)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress.replaceAll("M_Header", "Installation Address").replaceAll("M_Category", "Country").replaceAll("M_InnerText", Country), "Country of Installation Address - "+Country, false);
	}
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Postcode of Installation Address in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Postcode
	 */

	public void validate_the_Postcode_of_Installation_Address_in_Customer_Tab(String Postcode)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_InstallationAddress.replaceAll("M_Header", "Installation Address").replaceAll("M_Category", "Postcode").replaceAll("M_InnerText", Postcode), "Postcode of Installation Address - "+Postcode, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Telephone - Home in Contact Details of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	TelephoneHome
	 */

	public void validate_the_Telephone_Home_in_Contact_Details_of_Customer_Tab(String TelephoneHome)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_TelephoneHome.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Telephone - Home").replaceAll("M_InnerText", TelephoneHome), "Telephone - Home - "+TelephoneHome, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Telephone - Work in Contact Details of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	TelephoneWork
	 */

	public void validate_the_Telephone_Work_in_Contact_Details_of_Customer_Tab(String TelephoneWork)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_TelephoneWork.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Telephone - Work").replaceAll("M_InnerText", TelephoneWork), "Telephone - Work - "+TelephoneWork, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Telephone - Mobile in Contact Details of Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	TelephoneMobile
	 */

	public void validate_the_Telephone_Mobile_in_Contact_Details_of_Customer_Tab(String TelephoneMobile)throws Exception {
		Report.fnReportPageBreak("Telephone Details", driver);
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_TelephoneMobile.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Telephone - Mobile").replaceAll("M_InnerText", TelephoneMobile), "Telephone - Mobile - "+TelephoneMobile, false);
	}
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Marketing preferences in Contact Details of Customer Tab for Phone
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	23 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Marketing_preferences_in_Contact_Details_of_Customer_Tab(String type,String i)throws Exception {
			
			boolean checked= false;
			boolean status= false;
			try {
				checked = VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Marketing preferences").replaceAll("M_InnerText", type), "Marketing preferences on "+type);
				
				if(i.equalsIgnoreCase("1"))
					status=true;
				else
					status=false;
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally
			{
			if(checked==status)
				Report.fnReportPass("Marketing preferences  - "+type+" :  Checked status = "+status+"	Display Status"+checked, driver);
				else
				Report.fnReportFail("Marketing preferences - "+type+"  :  Checked status = "+status+"	Display Status"+checked, driver);
			
			}
	}
	
//	/************************************************************************************************
//	 * Validation Functions 																		*	
//	 ***********************************************************************************************/
//
//	/** Description 		: 	To validate the Marketing preferences in Contact Details of Customer Tab for Email
//	 * 	Coded by 			:	Rajan
//	 * 	Created Data		:	23 Oct 2016
//	 * 	Last Modified Date	:	
//	 * 	Modified By			:	
//	 * 	Parameter			:	
//	 */
//
//	public void validate_the_Marketing_preferences_in_Contact_Details_of_Customer_Tab("Email",String i)throws Exception {
//			
//			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Marketing preferences").replaceAll("M_InnerText", "Email"), "Marketing preferences on Email  ");
//			boolean status=false;
//			
//			if(i.equalsIgnoreCase("1"))
//				status=true;
//			else
//				status=false;
//			
//			
//			if(checked==status)
//				Report.fnReportPass("Marketing preferences  - Email :  Checked status = "+status+"Display Status"+checked, driver);
//				else
//				Report.fnReportFail("Marketing preferences - Email  :  Checked status = "+status+"Display Status"+checked, driver);
//			
//		
//	}
//	
	
	
//	/************************************************************************************************
//	 * Validation Functions 																		*	
//	 ***********************************************************************************************/
//
//	/** Description 		: 	To validate the Marketing preferences in Contact Details of Customer Tab for Mail
//	 * 	Coded by 			:	Rajan
//	 * 	Created Data		:	23 Oct 2016
//	 * 	Last Modified Date	:	
//	 * 	Modified By			:	
//	 * 	Parameter			:	
//	 */
//
//	public void validate_the_Marketing_preferences_in_Contact_Details_of_Customer_Tab("Mail",String i)throws Exception {
//			
//			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Marketing preferences").replaceAll("M_InnerText", "Mail"), "Marketing preferences on Mail  ");
//			boolean status=false;
//			
//			if(i.equalsIgnoreCase("1"))
//				status=true;
//			else
//				status=false;
//			
//			Report.ReporterLog("Mail flag checked status"+checked+" And DB Value"+status, LogStatus.INFO);
//			
//			if(checked==status)
//				Report.fnReportPass("Marketing preferences  - Mail :  Checked status = "+status+"Display Status"+checked, driver);
//				else
//				Report.fnReportFail("Marketing preferences - Mail :  Checked status = "+status+"Display Status"+checked, driver);
//			
//		
//	}
//	
	
	
	
//	/************************************************************************************************
//	 * Validation Functions 																		*	
//	 ***********************************************************************************************/
//
//	/** Description 		: 	To validate the Marketing preferences in Contact Details of Customer Tab for SMS
//	 * 	Coded by 			:	Padma
//	 * 	Created Data		:	24 jan 2017
//	 * 	Last Modified Date	:	
//	 * 	Modified By			:	
//	 * 	Parameter			:	
//	 */
//
//	public void validate_the_Marketing_preferences_in_Contact_Details_of_Customer_Tab("SMSPreference",String i)throws Exception {
//			
//			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Marketing preferences").replaceAll("M_InnerText", "SMSPreference"), "Marketing preferences on SMS  ");
//			boolean status=false;
//			
//			if(i.equalsIgnoreCase("1"))
//				status=true;
//			else
//				status=false;
//			
//			if(checked==status)
//				Report.fnReportPass("Marketing preferences  - SMS :  Checked status = "+status+"Display Status"+checked, driver);
//				else
//				Report.fnReportFail("Marketing preferences - SMS :  Checked status = "+status+"Display Status"+checked, driver);
//			
//		
//	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Preferred Contact Method in Contact Details of Customer Tab for Phone
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	23 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Preferred_Contact_Method_in_Contact_Details_of_Customer_Tab_Phone(boolean status)throws Exception {
			
			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Preferred Contact Method").replaceAll("M_InnerText", "Phone"), "Preferred Contact Method on Phone  ");
			
			if(checked==status)
				Report.fnReportPass("Preferred Contact Method  - Phone :  Checked status = "+status+"Display Status"+checked, driver);
				else
				Report.fnReportFail("Preferred Contact Method - Phone  :  Checked status = "+status+"Display Status"+checked, driver);
			
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Preferred Contact Method in Contact Details of Customer Tab for Email
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	23 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Preferred_Contact_Method_in_Contact_Details_of_Customer_Tab_Email(boolean status)throws Exception {
			
			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Preferred Contact Method").replaceAll("M_InnerText", "Email"), "Preferred Contact Method on Email  ");
			
			if(checked==status)
				Report.fnReportPass("Preferred Contact Method  - Email :  Checked status = "+status+"Display Status"+checked, driver);
				else
				Report.fnReportFail("Preferred Contact Method - Email  :  Checked status = "+status+"Display Status"+checked, driver);
			
		
	}
	
	
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Preferred Contact Method in Contact Details of Customer Tab for Mail
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	23 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Preferred_Contact_Method_in_Contact_Details_of_Customer_Tab_Mail(boolean status)throws Exception {
			
			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Preferred Contact Method").replaceAll("M_InnerText", "Mail"), "Preferred Contact Method on Mail  ");
			
			if(checked==status)
				Report.fnReportPass("Preferred Contact Method  - Mail :  Checked status = "+status+"Display Status"+checked, driver);
				else
				Report.fnReportFail("Preferred Contact Method - Mail :  Checked status = "+status+"Display Status"+checked, driver);
			
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Preferred Contact Method in Contact Details of Customer Tab for SMS
	 * 	Coded by 			:	Padma
	 * 	Created Data		:	25 Jan 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void validate_the_Preferred_Contact_Method_in_Contact_Details_of_Customer_Tab_SMS(boolean status)throws Exception {
			
			boolean checked=VerifyElementPresentAndGetCheckBoxStatus(Desktop_XPATH_Verify_Customer_Page_MarketingPreferences.replaceAll("M_Header", "Contact Details").replaceAll("M_Category", "Preferred Contact Method").replaceAll("M_InnerText", "SMSPreference"), "Preferred Contact Method on SMS ");
			
			if(checked==status)
				Report.fnReportPass("Preferred Contact Method  - SMS :  Checked status = "+status+"Display Status"+checked, driver);
				else
				Report.fnReportFail("Preferred Contact Method - SMS :  Checked status = "+status+"Display Status"+checked, driver);
			
		
	}
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Password of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Password
	 */

	public void validate_the_Password_of_Security_in_Customer_Tab(String Password)throws Exception {
		Report.fnReportPageBreak("Security", driver);
		if(Password==null)
			Password="N/A";
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Password").replaceAll("M_InnerText", Password), "Password - "+Password, false);
		
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Security Question of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	SecurityQuestion
	 */

	public void validate_the_Security_Question_of_Security_in_Customer_Tab(String SecurityQuestion)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Security Question").replaceAll("M_InnerText", SecurityQuestion), "Security Question - "+SecurityQuestion, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Security Answer of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	SecurityAnswer
	 */

	public void validate_the_Security_Answer_of_Security_in_Customer_Tab(String SecurityAnswer)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Security Answer").replaceAll("M_InnerText", SecurityAnswer), "Security Answer - "+SecurityAnswer, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Date of Birth of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	DateofBirth
	 */

	public void validate_the_Date_of_Birth_of_Security_in_Customer_Tab(String DateofBirth)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Date of Birth").replaceAll("M_InnerText", DateofBirth), "Date of Birth - "+DateofBirth, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Nominated user of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	Nominateduser
	 */

	public void validate_the_Nominated_user_of_Security_in_Customer_Tab(String Nominateduser)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Nominated user").replaceAll("M_InnerText", Nominateduser), "Nominated user - "+Nominateduser, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Accessibility Need of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	AccessibilityNeed
	 */

	public void validate_the_Accessibility_Need_of_Security_in_Customer_Tab(String AccessibilityNeed)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Accessibility Need").replaceAll("M_InnerText", AccessibilityNeed), "Accessibility Need - "+AccessibilityNeed, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Email Address 1 of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	EmailAddress1
	 */

	public void validate_the_Email_Address_1_of_Security_in_Customer_Tab(String EmailAddress1)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Email Address 1").replaceAll("M_InnerText", EmailAddress1), "Email Address 1 - "+EmailAddress1, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Email Address 2 of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	EmailAddress2
	 */

	public void validate_the_Email_Address_2_of_Security_in_Customer_Tab(String EmailAddress2)throws Exception {
		VerifyElementPresent(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Email Address 2").replaceAll("M_InnerText", EmailAddress2), "Email Address 2 - "+EmailAddress2, false);
	}
	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To validate the Online Username of Security in Customer Tab
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	05 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	OnlineUsername
	 */

	public void validate_the_Online_Username_of_Security_in_Customer_Tab(String OnlineUsername)throws Exception {
		VerifyElementPresentAndClick(Desktop_XPATH_Verify_Customer_Page_Security.replaceAll("M_Header", "Security").replaceAll("M_Category", "Online Username").replaceAll("M_InnerText", OnlineUsername), "Online Username - "+OnlineUsername);
	}
}