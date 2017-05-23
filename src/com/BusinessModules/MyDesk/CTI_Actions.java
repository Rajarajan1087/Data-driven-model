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

public class CTI_Actions  extends WebActions implements Generic,XP_PEGA_MyDesk{
	public WebDriverWait myCustWait = new WebDriverWait(driver, LoadEnvironment.custTimeOut);

	public CTI_Actions(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;
	}

	




	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	Verifying Phone Icon Before call
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_PhoneIcon_BeforeCall()throws Exception {


		
		VerifyElementPresent(Desktop_XPATH_CTI_FullToolbar, "CTI ToolBar");
		VerifyElementPresent(Desktop_XPATH_CTI_PhoneIcon_WithoutCall, "Phone Icon Before Call");
		
		do{
			VerifyElementPresentAndClick(Desktop_XPATH_CTI_PhoneIcon_WithoutCall, "Phone Icon Before Call");
		}while(!waitForElementToAppear(Desktop_XPATH_CTI_Phone_Loginwindow_Title, timeOut));
		
		Thread.sleep(2000);
		Report.fnReportPageBreak("CTI login Window Before call ", driver);
	}

	
	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	Choosing CTI Link in CTI Login Window
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void choose_CTILink_In_CTIWindow()throws Exception {

		VerifyElementPresentAndSelect(Desktop_XPATH_CTI_Phone_Loginwindow_SelectLink, "Selecting CTI Link", "Talk Talk CTI Link");
	}

	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	enter_Extension_In_CTI Login Window
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void enter_Extension_In_CTIWindow(String Extension)throws Exception {

		VerifyElementPresentAndClearType(Desktop_XPATH_CTI_Phone_Loginwindow_Extension, "Entering Extension", Extension);
		Report.fnReportPageBreak("Extension entered", driver);
	}

	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	enter_AgentId_In_CTI Login Window
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void enter_AgentId_In_CTIWindow(String AgentId)throws Exception {

		VerifyElementPresentAndClearType(Desktop_XPATH_CTI_Phone_Loginwindow_Agent_ID, "Entering AgentId", AgentId);
	}
	
	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	enter_Agentpassword_In_CTI Login Window
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void enter_Agentpassword_In_CTIWindow(String Agentpassword)throws Exception {

		VerifyElementPresentAndClearType(Desktop_XPATH_CTI_Phone_Loginwindow_PasswordCTI, "Entering Agentpassword", Agentpassword);
	}
	
	
	
	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	Click On Login Button In_CTI Login Window
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void click_Login_In_CTIWindow()throws Exception {

		do{
		Thread.sleep(1000);
		VerifyElementPresentAndClick(Desktop_XPATH_CTI_Phone_Loginwindow_LoginButton, "Login Button");
		if(verify_ErrorMessage_Login_In_CTIWindow("Extension"))
		{
			enter_Extension_In_CTIWindow("1027002");
			VerifyElementPresentAndClick(Desktop_XPATH_CTI_Phone_Loginwindow_LoginButton, "Login Button");
		}
		
		if(verify_ErrorMessage_Login_In_CTIWindow("Agent"))
			VerifyElementPresentAndClick(Desktop_XPATH_CTI_Phone_Loginwindow_LoginButton, "Login Button");
		
		
		Thread.sleep(1000);
			
		}while(waitForElementToAppear(Desktop_XPATH_CTI_Phone_Loginwindow_Title, 1));
	}
	
	
	
	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	Verify Error Message In_CTI Login Window
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public boolean verify_ErrorMessage_Login_In_CTIWindow(String ErrorMessage)throws Exception {

		Thread.sleep(2000);
		if(waitForElementToAppear(Desktop_XPATH_CTI_Phone_Loginwindow_ErrorTag, 1))
			return waitForElementToAppear(Desktop_XPATH_CTI_Phone_Loginwindow_ErrorMessage.replaceAll("ErrorMessage", ErrorMessage), timeOut);
		else
			return false;
		
	}
	
	
	
	/************************************************************************************************
	 * Common_Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	Calling the required number
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	18 Nov 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void call_PhoneNumber_In_CTIToolBar(String PhoneNumber)throws Exception {


		
		do {
			
			if(waitForElementToAppear(Desktop_XPATH_CTI_PhoneIcon_OnCall_UnAvailable, custTimeOut))
			{
				VerifyElementPresentAndType(Desktop_XPATH_CTI_PhoneNumber_TextBox, "CallNumber textBox", PhoneNumber);
				Thread.sleep(1000);
			}
			VerifyElementPresentAndClick(Desktop_XPATH_CTI_PhoneNumber_CallButton, "call Button");
			Thread.sleep(3000);
			if(waitForElementToAppear(Desktop_XPATH_CTI_PhoneCallButton_Initiating_1, 1))
					break;
			if(waitForElementToAppear(Desktop_XPATH_CTI_PhoneCallButton_Active_1, 1))
				break;
		} while (true);
		
	}
		
		/************************************************************************************************
		 * Common_Functions 																																				*	
		 ***********************************************************************************************/

		/** Description 		: 	HangUp the Active Call
		 * 	Coded by 			:	Rajan
		 * 	Created Data		:	18 Nov 2016
		 * 	Last Modified Date	:	
		 * 	Modified By			:	
		 */

		public void hangUp_ActiveCall_In_CTIToolBar()throws Exception {

			if(waitForElementToAppear(Desktop_XPATH_CTI_PhoneCallButton_Active_1, timeOut))
			{
				do {
					VerifyElementPresentAndClick(Desktop_XPATH_CTI_PhoneNumber_Call_SideButton,"Call Side Button");
					Thread.sleep(1000);
					VerifyElementPresentAndClick(Desktop_XPATH_CTI_PhoneNumber_Call_Side_HangUpOption,"Side option_HangUp");
				} while (!waitForElementToAppear(Desktop_XPATH_CTI_PhoneCallButton_Idle_1, timeOut));
			}
			
					
		
		}
	
		
		/************************************************************************************************
		 * Common_Functions 																																				*	
		 ***********************************************************************************************/

		/** Description 		: 	Move User to Unavailable With Reason
		 * 	Coded by 			:	Rajan
		 * 	Created Data		:	21 Nov 2016
		 * 	Last Modified Date	:	
		 * 	Modified By			:	
		 */

		public void click_Unavailable_In_CTI_Menu_With_Reason(String reason)throws Exception {

			
			do {
			VerifyElementPresentAndClick(Desktop_XPATH_CTI_Status_DropDown_Button, "MenuOptions Side Button");
			if(waitForElementToAppear(Desktop_XPATH_CTI_Status_MenuOptions_enabled.replaceAll("MenuOptions", "Unavailable"), timeOut))
			{
				
					VerifyElementPresentAndHover(Desktop_XPATH_CTI_Status_MenuOptions_enabled.replaceAll("MenuOptions", "Unavailable"), "Unavailable Button");
					Thread.sleep(1000);
					VerifyElementPresentAndHover(Desktop_XPATH_CTI_Status_UnAvailable_enabled_innerOption.replaceAll("innerOptions", reason), reason+" been clicked for user been Logged Out.");
					Thread.sleep(2000);
					VerifyElementPresentAndClick(Desktop_XPATH_CTI_Status_UnAvailable_enabled_innerOption.replaceAll("innerOptions", reason), reason+" been clicked for user been unavailable");
			}} while (!waitForElementToAppear(Desktop_XPATH_CTI_PhoneIcon_OnCall_UnAvailable, timeOut));
			
			
					
		
		}
		
		
		
		
		/************************************************************************************************
		 * Common_Functions 																																				*	
		 ***********************************************************************************************/

		/** Description 		: 	LogOut User With Reason
		 * 	Coded by 			:	Rajan
		 * 	Created Data		:	21 Nov 2016
		 * 	Last Modified Date	:	
		 * 	Modified By			:	
		 */

		public void click_LogOut_In_CTI_Menu_With_Reason(String reason)throws Exception {

			do {
				VerifyElementPresentAndClick(Desktop_XPATH_CTI_Status_DropDown_Button, "MenuOptions Side Button");
				if(waitForElementToAppear(Desktop_XPATH_CTI_Status_MenuOptions_enabled.replaceAll("MenuOptions", "Logout"), timeOut))
				{
					
						VerifyElementPresentAndHover(Desktop_XPATH_CTI_Status_MenuOptions_enabled.replaceAll("MenuOptions", "Logout"), "Logout Button");
//						Robot RB = new Robot();
//						RB.keyPress(KeyEvent.VK_RIGHT);
////						RB.keyPress(KeyEvent.VK_DOWN);
						Thread.sleep(1000);
//						RB.keyPress(KeyEvent.VK_ENTER);
						VerifyElementPresentAndHover(Desktop_XPATH_CTI_Status_LogOut_enabled_innerOption.replaceAll("innerOptions", reason), reason+" been clicked for user been Logged Out.");
						Thread.sleep(2000);
						VerifyElementPresentAndClick(Desktop_XPATH_CTI_Status_LogOut_enabled_innerOption.replaceAll("innerOptions", reason), reason+" been clicked for user been Logged Out.");
				}} while (waitForElementToAppear(Desktop_XPATH_CTI_PhoneIcon_OnCall_UnAvailable, timeOut));
				
					
		
		}
	
		
		/************************************************************************************************
		 * Common_Functions 																																				*	
		 ***********************************************************************************************/

		/** Description 		: 	LogOut User from CTI 
		 * 	Coded by 			:	Rajan
		 * 	Created Data		:	21 Nov 2016
		 * 	Last Modified Date	:	
		 * 	Modified By			:	
		 */	
		 public void PhoneLogout() throws Exception{
        	 /*************************************************************************/
             
             String XPath_Agentdropdown="//*[@class='AgentStateButton']//*[@id='CT']//button";
            
             /*************************************************************************/
            
       try {
     
      VerifyElementPresentAndClick(XPath_Agentdropdown, "Clicking - Agent Status Dropdwon");
    
      waitForElementToAppear("//*[text()='Available']", 2000);
     
      VerifyElementPresentAndClick("//*[text()='Available']","Clicking Available");
     
      Thread.sleep(2000);
     
     
      VerifyElementPresentAndClick(XPath_Agentdropdown, "Clicking - Agent Status Dropdwon");
     
      waitForElementToAppear("//*[contains(@id,'pyNavigation')]//span[2]//*[text()='Unavailable']", 2000);
     
       VerifyElementPresentAndHover("//*[contains(@id,'pyNavigation')]//span[2]//*[text()='Unavailable']","Unavailable");
       Thread.sleep(2000);
                   
      VerifyElementPresentAndClick("//*[text()='Break']","Clicking Break");
                  
      Thread.sleep(2000);
    
     VerifyElementPresentAndClick(XPath_Agentdropdown, "Clicking - Agent Status Dropdwon");
    
  
     waitForElementToAppear("//*[contains(@id,'pyNavigation')]//span[2]//*[text()='Logout']", 2000);;
   

     VerifyElementPresentAndHover("//*[contains(@id,'pyNavigation')]//span[2]//*[text()='Logout']","Logout");
    
     Thread.sleep(2000);
     VerifyElementPresentAndClick("//*[contains(@id,'Elements$l4')]//*[text()='Break']","Clicking logout");
    
     Thread.sleep(2000);
     Report.fnReportPass("Phone logout was successfull", driver);
  

}  catch (Exception exMsg) {
     exMsg.printStackTrace();
}
}

	


	


}
