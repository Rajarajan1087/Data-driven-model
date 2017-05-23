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
import java.util.NoSuchElementException;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;






















import net.sf.saxon.functions.CurrentDateTime;

import org.apache.commons.lang3.StringUtils;
import org.mozilla.javascript.ast.ThrowStatement;
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
import com.thoughtworks.selenium.webdriven.commands.Refresh;


public class Home_Page extends WebActions implements XP_PEGA_MyDesk,Generic,MyDeskEnumerations {

	Common_Functions Com1=new Common_Functions(driver, Report);
	
	
	public Home_Page(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;

	}

	/************************************************************************************************
	 Home Page Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Home Page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	30 Sep 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void wait_for_Home_Page_To_Load()throws Exception {
		
		
		Thread.sleep(1000);
		Com1.switch_Window();
		Thread.sleep(2000);
		if(!waitForElementToAppear(Desktop_XPATH_Home_Page_Validation, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Home Page is not Loaded", "Home Page is not Loaded", driver);
		}
		else{
			
			Report.fnReportPageBreak("Home Page",driver);
		}
	}

	/************************************************************************************************
	 Home Page Functions 																																				*	
	 ***********************************************************************************************/

	
	
	/************************************************************************************************
	 Home Page Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Welcome Page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	30 Sep 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Rajan
	 */

	public void wait_for_Welcome_Page_To_Load()throws Exception {
		
		Com1.switchToFrame(iframes.welcome);	
		if(!waitForElementToAppear(Desktop_XPATH_Home_Page_Welcome_Message, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Welcome Message is not Loaded", "Welcome Message is not Loaded", driver);
		}
		else{
			
			Report.fnReportPageBreak("Home page Welcome Message loaded",driver);
		}
	}
	/************************************************************************************************
	 Home Page Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Identify Customer Page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Oct 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void wait_for_Identify_Customer_Page_To_Load()throws Exception {
		
		
		if(!waitForElementToAppear(Desktop_XPATH_Identify_Customer_Page_Validation, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Identify Customer Page is not Loaded", "Identify Customer Page is not Loaded", driver);
		}
		else{
			
			Report.fnReportPageBreak("Identify Customer Page",driver);
		}
	}

	
	
	/************************************************************************************************
	 Home Page Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for search results panel to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	06 Oct 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Rajan
	 */

	public void wait_for_Search_Result_Pane_To_Load()throws Exception {
		
		if(!waitForElementToAppear(Desktop_XPATH_Search_Results_Pane, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Results Panel is not Loaded", "Results Panel is not Loaded", driver);
		}
		else{
			
			Report.fnReportPageBreak("Results Panel",driver);
		}
	}
	
	/************************************************************************************************
	 Home Page Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Post Code search results panel to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	06 Oct 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Rajan
	 */

	public void wait_for_PostCode_Search_Result_Pane_To_Load()throws Exception {
		
		if(!waitForElementToAppear(Desktop_XPATH_PostCode_Search_Results_Pane, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Post Code Results Panel is not Loaded", "Post Code Results Panel is not Loaded", driver);
		}
		else{
			
			Report.fnReportPageBreak("Post Code Results Panel",driver);
		}
	}
	
	
		

	/************************************************************************************************
	 Home Page Functions 																		*																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click New Interaction Button 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	30 Sep 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void Click_New_Interaction_Button()throws Exception {
		if(!waitForElementToAppear(Desktop_XPATH_Home_Page_InteractionButton, t))
		{
			Report.fnReportFailAndTerminateTest("New Interaction Button is not present", "New Interaction Button is not present", driver);
		}
		else{

			VerifyElementPresentAndClick(Desktop_XPATH_Home_Page_InteractionButton, "New Interaction Button" );
		}
	}


	
	/************************************************************************************************
	 Home Page Functions 																		*																		*	
	 ***********************************************************************************************/

	

	
	
	
	/************************************************************************************************
	 Home Page Functions 																		*																		*	
	 ***********************************************************************************************/

	
	
	
	
	/************************************************************************************************
	 Home Page Functions 																		*																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Clicking different type of customer search parameters button 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void Customer_Search_By_ButtonClick(SearchBy Button)throws Exception {

		switch (Button) {
		case CLI:
			VerifyElementPresentAndClick(Desktop_XPATH_SearchBy_Phone_Button, "PhoneNumber Search Button");
			break;

		case ACCOUNT:
			VerifyElementPresentAndClick(Desktop_XPATH_SearchBy_Account_Button, "Account Number Search Button");
			break;

		case MyAccountusername:
			VerifyElementPresentAndClick(Desktop_XPATH_SearchBy_MyAccount_Button, "My Account UserName Search Button");
			break;

		case Postcode:
			VerifyElementPresentAndClick(Desktop_XPATH_SearchBy_Postcode_Button, "PostCode Search Button");
			break;
			
		case MCLI:
			VerifyElementPresentAndClick(Desktop_XPATH_SearchBy_MobilePhone_Button, "TalkTalk Mobile Number Search Button");
			break;

		default:
			Common_Functions.refreshpage(driver);
			break;
		}
	}


	/************************************************************************************************
	 * Home Page Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Entering value for  different type of customer search parameters button 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void Customer_Search_By_InputValue(SearchBy Input,String...InputValue)throws Exception {

		switch (Input) {

		case CLI:
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_Phone_TextBox, "PhoneNumber TextBox", InputValue[0]);
			break;

		case ACCOUNT:
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_Account_TextBox, "AccountNumber TextBox", InputValue[0]);
			break;

		case MyAccountusername:
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_MyAccountName_TextBox, "MyAccount UserName TextBox", InputValue[0]);
			break;

		case Postcode:
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_PostCode_TextBox, "Postcode TextBox", InputValue[0]);
			break;

		case Postcodeandlastname:
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_PostCode_TextBox, "PhoneNumber TextBox", InputValue[0]);
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_LastName_TextBox, "LastName TextBox", InputValue[1]);
			break;

		case MyAccountusernameWithBrand:
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_MyAccountName_TextBox, "MyAccount UserName TextBox", InputValue[0]);
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_Brand_DropDown, "Brand DropDown", InputValue[1]);
			break;	
			
		case MCLI:
			VerifyElementPresentAndClearType(Desktop_XPATH_SearchBy_Mobile_TextBox, "TalkTalk Mobile Number Search Button",InputValue[0]);
			break;

		default:
			Common_Functions.refreshpage(driver);
			break;

		}
	}


	/************************************************************************************************
	 * Home Page Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Common Search button for searching customer using specific terms 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void Click_Search_Button() {

		VerifyElementPresentAndClick(Desktop_XPATH_Search_Button, "Search Button");

	}

	/************************************************************************************************
	 * Home Page Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Common Clear button for clearing search term for customer using specific terms 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void Click_Clear_Button() {

		VerifyElementPresentAndClick(Desktop_XPATH_Clear_Button, "Clear Button");

	}



	/************************************************************************************************
	Home Page Functions 																		 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Verifying list of search results with unique DB value 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */


	public void  Verify_Unique_SearchResults(String... term1) {

		if(term1.length>0)
		{
			String XPATH_ToUse=Desktop_XPATH_Search_Results.replaceAll("M_Pointer",term1[0]);
			for(int i=1;i<term1.length;i++){
				XPATH_ToUse+=Desktop_XPATH_Search_Results1.replaceAll("M_Pointer",term1[i]);
			}
			VerifyElementPresent(XPATH_ToUse,"Expected Data in Search Results", false);
		}
		else{
			Report.fnReportFailAndTerminateTest("Could not get any unique identifier to select", "Could not get any unique identifier to select", driver);
		}
	}

	/************************************************************************************************
	Home Page Functions 																		 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Verifying list of search results with unique DB value and clicking /selecting them
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * @throws InterruptedException 
	 */


	public void  Verify_Click_Unique_SearchResults(String... term1) throws InterruptedException {

		
		if(term1.length>0)
		{
			String XPATH_ToUse=Desktop_XPATH_Search_Results.replaceAll("M_Pointer",term1[0]);
			for(int i=1;i<term1.length;i++){
				XPATH_ToUse+=Desktop_XPATH_Search_Results1.replaceAll("M_Pointer",term1[i]);
			}
			Thread.sleep(1000);
			XPATH_ToUse+="/ancestor::td[1]";
		System.out.println(XPATH_ToUse);	
			VerifyElementPresentAndClick(XPATH_ToUse,"Expected Data in Search Results");
		}
		else{
			Report.fnReportFailAndTerminateTest("Could not get any unique identifier to select", "Could not get any unique identifier to select", driver);
		}
		

	}


	/************************************************************************************************
		Home Page Functions 																		 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Clicking expand button in search results
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	05 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */


	public void  Verify_ClickExpand_SearchResults(String... term1) {

		
		if(term1.length>0)
		{
			String XPATH_ToUse=Desktop_XPATH_Search_Results.replaceAll("M_Pointer",term1[0]);
			for(int i=1;i<term1.length;i++){
				XPATH_ToUse+=Desktop_XPATH_Search_Results1.replaceAll("M_Pointer",term1[i]);
			}
			XPATH_ToUse+="/ancestor::tr[1]/td[1]";
			VerifyElementPresentAndClick(XPATH_ToUse,"Expected Data in Search Results");
		}
		else{
			Report.fnReportFailAndTerminateTest("Could not get any unique identifier to select", "Could not get any unique identifier to select", driver);
		}

	}

		/************************************************************************************************
			Home Page Functions 																		 																		*	
		 ***********************************************************************************************/

		/** Description 		: 	Verifying list of PostCode search results with unique DB value 
		 * 	Coded by 			:	Rajan
		 * 	Created Data		:	11 Oct 2016
		 * 	Last Modified Date	:	
		 * 	Modified By			:	
		 */


		public void  Verify_Unique_PostCode_SearchResults(String... term1) {

			if(term1.length>0)
			{
				String XPATH_ToUse=Desktop_XPATH_PostCode_Search_Results.replaceAll("M_Pointer",term1[0]);
				for(int i=1;i<term1.length;i++){
					XPATH_ToUse+=Desktop_XPATH_Search_Results1.replaceAll("M_Pointer",term1[i]);
				}
				VerifyElementPresent(XPATH_ToUse,"Expected Data in Search Results", false);
			}
			else{
				Report.fnReportFailAndTerminateTest("Could not get any unique identifier to select", "Could not get any unique identifier to select", driver);
			}
		}

		/************************************************************************************************
			Home Page Functions 																		 																		*	
		 ***********************************************************************************************/

		/** Description 		: 	Verifying list of PostCode search results with unique DB value and clicking /selecting them
		 * 	Coded by 			:	Rajan
		 * 	Created Data		:	11 Oct 2016
		 * 	Last Modified Date	:	
		 * 	Modified By			:	
		 * @throws InterruptedException 
		 */


		public void  Verify_Click_Unique_PostCode_SearchResults(String... term1) throws InterruptedException {

			
			if(term1.length>0)
			{
				String XPATH_ToUse=Desktop_XPATH_PostCode_Search_Results.replaceAll("M_Pointer",term1[0]);
				for(int i=1;i<term1.length;i++){
					XPATH_ToUse+=Desktop_XPATH_Search_Results1.replaceAll("M_Pointer",term1[i]);
				}
				Thread.sleep(1000);
				VerifyElementPresentAndClick(XPATH_ToUse,"Expected Data in Search Results");
			}
			else{
				Report.fnReportFailAndTerminateTest("Could not get any unique identifier to select", "Could not get any unique identifier to select", driver);
			}
			

		}
	
		/************************************************************************************************
		Home Page Functions 																		 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Verifying fetched search results is single or multiple 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	11 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * @throws InterruptedException 
	 */


	public void  Verify_SearchResult_Count(String searchTerm,String checker) throws InterruptedException {

		int temp=1;
		String Compare="NA",temp1;
		String XPATH_ToUse=Desktop_XPATH_Search_Results_Count_Checker.replaceAll("M_Pointer1",searchTerm).replaceAll("M_Pointer2", String.valueOf(temp));
		
		
		try {
			
			if(waitForElementToAppear(XPATH_ToUse, 20))
			{
				while(!(VerifyElementPresentAndGetText(XPATH_ToUse, " element in table").equalsIgnoreCase("")))
				{
					System.out.println("Xpath To Use : "+XPATH_ToUse);
				if(temp==2)
				{	Compare="Multiple";
					break;
				}
				else
				{
					Compare="Single";
				}

				++temp;
				
				XPATH_ToUse=Desktop_XPATH_Search_Results_Count_Checker.replaceAll("M_Pointer1",searchTerm).replaceAll("M_Pointer2", String.valueOf(temp));
				System.out.println("Xpath To Use_after interchange : "+XPATH_ToUse);
				}
		
			}
			
			else
			{
				Report.fnReportPageBreak("PostCode Result not found", driver);
			}
			
			if(Compare.equalsIgnoreCase(checker))
			{
			System.out.println("Compare :"+Compare);
			Report.fnReportPass("Search results present as expected", driver);
			}
			else
			{
				System.out.println("Compare :"+Compare);
			Report.fnReportFailAndTerminateTest("SearchresultCount","Search results doesn't get the count as expected", driver);
			}
		
		} catch (Exception e) {
			Report.fnReportPageBreak("Issue in identifying search results element", driver);
			e.printStackTrace();
		}

	}
	
	

	/************************************************************************************************
	Home Page Functions 																		 																		*	
 ***********************************************************************************************/

/** Description 		: 	Clicking the specific search result accordong the row no given
 * 	Coded by 			:	Rajan
 * 	Created Data		:	17 Oct 2016
 * 	Last Modified Date	:	
 * 	Modified By			:	Parameters( Search Term = "Full Name"- CLI search / " First name" - PostCode Search , Row - 1st , 2nd or nth row to be clicked) 
 * @throws InterruptedException 
 */


public void  Click_Specific_PostCodeOrCLI_SearchResult_Row(String searchTerm,int rowNumber) throws InterruptedException {

	try {
		int temp=0;
		String Compare="NA",temp1;
		String XPATH_ToUse=Desktop_XPATH_Search_Results_Count_Checker.replaceAll("M_Pointer1",searchTerm).replaceAll("M_Pointer2", String.valueOf(rowNumber));
		VerifyElementPresentAndClick(XPATH_ToUse, "Search Result Row "+rowNumber);
		
		}
			catch (Exception e) {
				Report.fnReportPageBreak("Issue in identifying search results element", driver);
				e.printStackTrace();
			}



		}
	
/************************************************************************************************
Home Page Functions 																		 																		*	
***********************************************************************************************/

/** Description 		: 	Get Single Data From Search result
* 	Coded by 			:	Rajan
* 	Created Data		:	17 Oct 2016
* 	Last Modified Date	:	
* 	Modified By			:	Parameters( Search Term = "Full Name"- CLI search / " First name" - PostCode Search , Row - 1st , 2nd or nth row to be clicked,column value) 
* @throws InterruptedException 
*/


public String  getSingleData_ForSpecific_PostCodeOrCLI_SearchResult_Row(String searchTerm,int rowNumber,int colNumber) throws InterruptedException {

try {
	int temp=0;
	String Compare="NA",temp1;
	String XPATH_ToUse=Desktop_XPATH_Search_Results_Single_Data_Fetcher.replaceAll("M_Pointer1",searchTerm).replaceAll("M_Pointer2", String.valueOf(rowNumber)).replaceAll("M_Pointer3", String.valueOf(colNumber+1));
	return VerifyElementPresentAndGetText(XPATH_ToUse, "Search Result Row - "+rowNumber+" Column -"+colNumber);
	
	}
		catch (Exception e) {
			Report.fnReportPageBreak("Issue in identifying search results element", driver);
			e.printStackTrace();
			return null;
		}



	}
	
}






