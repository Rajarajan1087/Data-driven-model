package com.BusinessModules.MyDesk;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.openqa.selenium.support.ui.WebDriverWait;









import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.Enumerations.Generic;
import com.Enumerations.MyDeskEnumerations.CustomerInformationBar;
import com.ObjectIdentifiers.XP_PEGA_MyDesk;
import com.WebActions.WebActions;

public class Common_Functions  extends WebActions implements Generic,XP_PEGA_MyDesk{
	public WebDriverWait myCustWait = new WebDriverWait(driver, LoadEnvironment.custTimeOut);

	public Common_Functions(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;
	}

	/************************************************************************************************
	 * Common_Functions  																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Switching Between Windows
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */


	public void switch_Window() {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); 
				driver.manage().window().maximize();
			}
			Report.fnReportPass("Switched to new window");
		} catch (Exception e) {
			Report.fnReportFailAndTerminateTest("Window switched","");
		}

	}

	
	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Desired label
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	16 Dec 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_Desired_label(String labelContent)throws Exception {

		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", "Cancel this work"), "Cancel this work label");
			
	}

	/************************************************************************************************
	 * Common_Functions  																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Switching Between Frames
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */



	public void switchToFrame(iframes framename){
		try{
			switch(framename){

			case defaultcontent:
				driver.switchTo().defaultContent();
				Report.fnReportPass("Switched to Default Content");
				break;
			case welcome:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Welcome_Frame)));
				Report.fnReportPass("Switched to Frame: Welcome");
				break;

			case search:
				System.out.println(driver.getPageSource().contains("iframe"));
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Search_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: Search");
				break;

			case create:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Create_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: Create");
				break;


			case Bulk:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Bulk_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: bulk");
				break;	


			case Pega4:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Pega4_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: pega4");
				break;

			case Pega5:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Pega5_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: pega4");
				break;

			case Mycases:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Mycases_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: My Case");
				break;

			case reviewEvent:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_reviewEvent_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: Review Event");
				break;

			case search_cm:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_search_cm_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: Search for Customer Management");
				break;

			case Tasklist:
				myCustWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElementFromLoc(Desktop_XPATH_Tasklist_Frame)));
				System.out.println(driver.getPageSource().contains("iframe"));
				Report.fnReportPass("Switched to Frame: Search for Customer Management");
				break;	


			}

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			Report.fnReportFailAndTerminateTest("switchToFrame() function", "switching to "+ framename + " failed",driver);
		}

	}


	/************************************************************************************************
	 * Common_Functions  																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Launching PEGA Instance for the First Time 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */



	public void LaunchPegaInstance(String URL) {

		try {
			driver.get(URL);
			Report.fnReportPass(driver.getCurrentUrl()+" has been navigated",driver );
		} catch (Exception e) {
			Report.fnReportFailAndTerminateTest("Launch PEGA URL",
					e.getLocalizedMessage(),driver);
		}

	}


	/************************************************************************************************
	 * Common_Functions  																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Login PEGA  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */


	public void Pega_Login(String Username,String Password) {

		try {

			VerifyElementPresentAndType(Desktop_XPATH_ID_Username, "UserIdentifier", Username);
			VerifyElementPresentAndType(Desktop_XPATH_ID_password,"Password",Password);
			VerifyElementPresentAndClick(Desktop_XPATH_ID_Submit, "Log In");

			Report.fnReportPass(driver.getCurrentUrl()+" has been navigated" );
		}  catch (Exception exMsg) {
			exMsg.printStackTrace();
		}

	}

	/************************************************************************************************
	 * Common_Functions  																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Logout PEGA  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */


	public void Pega_logout() {

		try {
			Thread.sleep(2000);
			refreshpage(driver);
			switchToFrame(iframes.defaultcontent);
			waitForElementToAppear(Desktop_XPATH_user, custTimeOut);
			VerifyElementPresentAndClick(Desktop_XPATH_user, "User");
			waitForElementToAppear(Desktop_XPATH_logout, custTimeOut);
			VerifyElementPresentAndClick(Desktop_XPATH_logout,"Logout");

			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {
				// TODO: handle exception
			}
			Report.fnReportPass("Logout button is found and clicked.",driver);
		}  catch (Exception exMsg) {
			exMsg.printStackTrace();
		}
	}


	/************************************************************************************************
	 * Common_Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select an action to perform
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	27 Oct 2016
	 * 	Modified By			:	Rajan
	 * 	Parameter			:	Action can be Bypass Verification,Exit Interaction,Identify Customer
	 */

	public void select_an_action_to_perform(String Action)throws Exception {

		VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Verify_Caller_Page_OtherActions_link, "Other Actions");
		boolean actionDisplayed = false;
		for (int i = 1; i <= c; i++) {

			
			actionDisplayed=waitForElementToAppear(Desktop_XPATH_OtherActions.replaceAll("M_Action", Action), t*3);
			if(actionDisplayed)
			{
				System.out.println(Action+" displayed is "+actionDisplayed);
				Thread.sleep(2000);
				VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_OtherActions.replaceAll("M_Action", Action), "Other Actions - "+Action);
		
				break;
			}
			else	
			{
				VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Verify_Caller_Page_OtherActions_link, "Other Actions");
			}


			if(i==c)
				Report.fnReportFailAndTerminateTest("OtherAction","Other Action / selected Action "+Action+" click failed", driver);
		}

		

	}

	
	/************************************************************************************************
	 * Common_Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To select an action to perform
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	Action can be Refresh , Cancel work,verify , Talksafe , payment navigation
	 */

	public void select_An_Action_To_Perform_ForCases(String Action)throws Exception {
		int counter=0;
		
			do{
			
			Report.fnReportPageBreak("Before clicking other action button", driver);	
			VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Verify_Caller_Page_OtherActions_link, "Other Actions");
			boolean actionDisplayed = false;
			
	
			actionDisplayed=waitForElementToAppear(Desktop_XPATH_OtherActions.replaceAll("M_Action", Action), t*3);
			if(actionDisplayed)
			{
				
				try {
					Thread.sleep(1000);
					VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_OtherActions_CancelTheWork_CasePage_Movement, Action+" in Other Actions");
					System.out.println(Action+" displayed is "+actionDisplayed);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(counter>2)
						break;
					else
						counter++;
////					System.out.println("There is exception and the handling transfer to robot class");
//
//					Robot RB = new Robot();
//					RB.keyPress(KeyEvent.VK_DOWN);
//					RB.keyPress(KeyEvent.VK_DOWN);
//					Thread.sleep(1000);
//					RB.keyPress(KeyEvent.VK_ENTER);				
//					Thread.sleep(2000);
				}
				
			}
			else	
			{
				VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_Verify_Caller_Page_OtherActions_link, "Other Actions");
			}
			}
			while(!waitForElementToAppear(Desktop_XPATH_Cases_OtherAction_PageLoad.replaceAll("title", Action), t));

			
	}
		

	/************************************************************************************************
	 * Exit_interaction																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To enter comments in exit interaction
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	07 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void enter_Exit_interaction_Comments(String comment)throws Exception {
		waitForElementToAppear(Desktop_XPATH_Exit_Interation_Comments, t*3);
		VerifyElementPresentAndClearType(Desktop_XPATH_Exit_Interation_Comments,"Exit_Interation_Comments", comment);
		click_Submit_Button();
	}

	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	Clicking submit button 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	06 Oct 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Rajan
	 */

	public void click_Submit_Button()throws Exception {

		Report.fnReportPageBreak("Before Submission", driver);
		Thread.sleep(1000);
		if(waitForElementToAppear(Desktop_XPATH_Submit_Button, timeOut))
			VerifyElementPresentAndClick(Desktop_XPATH_Submit_Button, "Submit Button");
	}



	

	/************************************************************************************************
	 * Common_Functions  																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Click Element Executor  
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	03 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public static void clikElementByExecutor(WebDriver driver,String elementLocator){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", elementLocator);
	}

	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the Elements in Customer Information Bar
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	06 Oct 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Raja
	 * 	Parameter			:	var arg CIB from enum CustomerInformationBar
	 */

	public void verify_the_Elements_in_Customer_Information_Bar(CustomerInformationBar... CIB)throws Exception {

		for(CustomerInformationBar DataToCheck:CIB)
		{
			String Element=DataToCheck.name().replaceAll("_", " ");
			if(Element.contains("and"))
			{
				Element=Element.split("and")[0]+"&"+Element.split("and")[1];
			}
			VerifyElementPresent(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", Element),"Customer Information Bar - "+Element+" label ", false);
		}

		Report.fnReportPass("Customer Information bar details Shown", driver);
	}


	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify the Elements not present in Customer Information Bar
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	14 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_the_Elements_NotPresent_in_Customer_Information_Bar(CustomerInformationBar... CIB)throws Exception {

		for(CustomerInformationBar DataToCheck:CIB)
		{
			String Element=DataToCheck.name().replaceAll("_", " ");
			if(Element.contains("and"))
			{
				Element=Element.split("and")[1];
			}

			if(!waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", Element),1))
			{
				Report.fnReportPass("Details in customer information bar has been hided", driver);
			}
			else
			{
				Report.fnReportFail("Details in customer information bar has not been hided", driver);
			}

		}
	}


	/************************************************************************************************
	 Common Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Exit Interaction page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	12 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void wait_for_Exit_Interaction_Page_To_Load()throws Exception {


		if(!waitForElementToAppear(Desktop_XPATH_Exit_Interation_Comments, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Exit Interaction page is not Loaded", "Exit Interaction page is not Loaded", driver);
		}
		else{

			Report.fnReportPageBreak("Exit Interaction page",driver);
		}
	}

	/************************************************************************************************
	 Common Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Exit Interaction page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void wait_for_Wrap_Page_To_Load(String title)throws Exception {


		if(!waitForElementToAppear(Desktop_XPATH_Generic_UI_TEXT_Searcher.replaceAll("M_ValidationText", title), custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Wrap Up  page is not Loaded", "Wrap Up  page is not Loaded", driver);
		}
		else
		{
			Report.fnReportPageBreak("WrapUp Page in Title : "+title,driver);
		}
	}


	/************************************************************************************************
	 Common Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Exit Interaction page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void check_Warning_Alert_While_Case_Opened(String Message)throws Exception {

		try {
			Alert alert = driver.switchTo().alert();
			System.out.println("Switched to alert box");
			String msg=alert.getText();
			System.out.println(msg+" is getting displayed in the alert");
			if(msg.equalsIgnoreCase(Message))
			{
				Report.fnReportPageBreak(Message, driver);
				alert.accept();
			}

			else
				Report.fnReportFail(Message + " Alert not getting passed", driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To click on Wrap up Link while cases are open
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	04 Jan 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_on_Wrap_up_button_CaseOpen_checkAlert()throws Exception {
		switchToFrame(iframes.defaultcontent);
		switchToFrame(iframes.create);
		Thread.sleep(1000);
		VerifyElementPresentAndClick(Desktop_XPATH_Cases_Frame_WrapUP_Button, "Wrap Up");
		Thread.sleep(1000);
		check_Warning_Alert_While_Case_Opened(OpenCases_WrapUp_Warning_Alert);
	}


	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To click on Wrap up Link
	 * 	Coded by 			:	Raja
	 * 	Created Data		:	04 Oct 2016
	 * 	Last Modified Date	:	04 Oct 2016
	 * 	Modified By			:	Raja
	 */

	public void click_on_Wrap_up_button() {
		try {
			switchToFrame(iframes.defaultcontent);
			switchToFrame(iframes.search);
			VerifyElementPresentAndClick(Desktop_XPATH_Cases_Frame_WrapUP_Button, "Wrap Up");
		} catch (StaleElementReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			click_on_Wrap_up_button();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	


	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To enter Wrap Up Comment 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	14 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void enter_Wrap_up_Comments()throws Exception {
		VerifyElementPresentAndClearType(Desktop_XPATH_Cases_Frame_WrapUP_Comment, "Wrap Up Comment Box","Wrapped up");
	}

	
	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	check string in entred Wrap Up Comment 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	14 Mar 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void check_entered_notIn_Wrap_up_Comments(String value)throws Exception {
		
		if(VerifyElementPresentAndGetText(Desktop_XPATH_Cases_Frame_WrapUP_Comment, "Wrap Up Comment Box").contains(value))
			Report.fnReportFail("Wrap up max char validation failed", driver);
		else
			Report.fnReportPass("Wrap up max char validation Pass");
		
	}

	
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To enter Wrap Up Comment upto chars entered
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	15 Mar 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void enter_Wrap_up_Comments_CharGivenwithCount(int count,String a)throws Exception {
		
		String comment=a;
		int counter=0;
		do
		{
			comment+=a;
			counter++;
		}while(counter<count);
		
		VerifyElementPresentAndType(Desktop_XPATH_Cases_Frame_WrapUP_Comment, "Wrap Up Comment Box",comment);
		String val=VerifyElementPresentAndGetText(Desktop_XPATH_Cases_Frame_WrapUP_Comment_RemChar, "Remaining char can be entred in wrap up comment");
		int value=2000-count;
		String val1=""+value;
		if(val==val1)
			Report.fnReportPass("Remaining chars updated as expected", driver);
		
		Report.fnReportPass("Wrap up box entered with commment having char count of "+count+" chars.");
	}
	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Clikc hide for alert messages
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	14 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_Hide_Alert_Message()throws Exception {

		while (elementExists(Desktop_XPATH_AlertMessage_Hide_link, 1)) {

			VerifyElementPresentAndClick(Desktop_XPATH_AlertMessage_Hide_link, "Alert Hide Link");
			Thread.sleep(1000);
		}


	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Click Show for alert messages
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	14 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void click_Show_Alert_Message()throws Exception {
		int i=1;
		while (elementExists(Desktop_XPATH_AlertMessage_Hide_link, t*2)) {
			i++;
			VerifyElementPresentAndClickNotAssertive(Desktop_XPATH_AlertMessage_Show_link, "Alert Show Link");
			if(i==10)
			{
				break;
			}
		}
	}

	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Check related alert  Message exist
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	14 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_Alert_Exists(String alert)throws Exception {


		if(waitForElementToAppear(Desktop_XPATH_AlertMessage_Content_checker.replaceAll("alert", alert), custTimeOut))
		{
				VerifyElementPresent(Desktop_XPATH_AlertMessage_Content_checker.replaceAll("alert", alert), alert+"Alert Message", false);
				Report.fnReportPass("Alert Message Screen", driver);
		}
		else
		{
			Report.fnReportFail("No Alert  Message found", driver);
		}


	}



	/************************************************************************************************
	 * Navigation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	Check related alert  Message not exist
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	14 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_Alert_NotExists(String alert)throws Exception {


		if(waitForElementToAppear(Desktop_XPATH_AlertMessage_Content_checker.replaceAll("alert", alert), 1))
			Report.fnReportFail(alert+" Message found", driver);
		else
			Report.fnReportPass("No Alert Message found", driver);
	}


	/************************************************************************************************
	 * Validation Functions 																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To verify Required outerframe Enabled
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	17 Oct 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 * 	Parameter			:	
	 */

	public void verify_Required_OuterFrame_Present(String header,boolean available)throws Exception 
	{

		if(available==waitForElementToAppear(Desktop_XPATH_OuterFrame_in_Page.replaceAll("M_Header", header),1))
			Report.fnReportPass("Outer frame "+header+" availability :"+available+"as expected", driver);
		else
			Report.fnReportFailAndTerminateTest("Outer frame "+header, "Outer frame "+header+" availability :"+available+"not as expected", driver);

	}




}
