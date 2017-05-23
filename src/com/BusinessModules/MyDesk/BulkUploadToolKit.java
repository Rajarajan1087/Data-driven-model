package com.BusinessModules.MyDesk;
/**Class Name		: Bulk Upload Tool Kit	
 * Description		: This class contains functions for the Bulk Upload Tool Kit
 * @author 			: Rajan	
 * Function Names	: 
 * 
 * 
 * 
 * 
 * Creation Date	: 20 Mar 2017	
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


public class BulkUploadToolKit extends WebActions implements XP_PEGA_MyDesk,Generic,MyDeskEnumerations {

	Common_Functions Com1=new Common_Functions(driver, Report);
	
	
	public BulkUploadToolKit(WebDriver Driver, Reporter report) {
		super(Driver,report);
		driver = Driver;
		Report = report;

	}

	/************************************************************************************************
	 Bulk Upload Tool Kit Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Access Control Bulk Upload Tool Kit to load 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Mar 2017
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void wait_for_Home_Page_To_Load_AccessControl()throws Exception {
		
		
		Thread.sleep(1000);
		Com1.switch_Window();
		Thread.sleep(2000);
		if(!waitForElementToAppear(Desktop_XPATH_Home_Page_Validation_OperatorMgmt, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Bulk Upload Tool Kit is not Loaded", "Bulk Upload Tool Kit is not Loaded", driver);
		}
		else{
			
			Report.fnReportPageBreak("Bulk Upload Tool Kit",driver);
		}
	}
	
	/************************************************************************************************
	 Bulk Upload Tool Kit Functions 																																				*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Welcome Page to load
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	30 Sep 2016
	 * 	Last Modified Date	:	07 Oct 2016
	 * 	Modified By			:	Rajan
	 */

	public void wait_for_AccessControl_Welcome_Page_To_Load()throws Exception {
		
		Com1.switchToFrame(iframes.welcome);	
		if(!waitForElementToAppear(Desktop_XPATH_Home_Page_Welcome_Message_AccessControl, custTimeOut))
		{
			Report.fnReportFailAndTerminateTest("Bulk Upload Tool Kit Welcome Page is not Loaded", "Bulk Upload Tool Kit Welcome Page is not Loaded", driver);
		}
		else{
			
			Report.fnReportPageBreak("Bulk Upload Tool Kit Welcome Page loaded",driver);
		}
	}
	
	/************************************************************************************************
	 Bulk Upload Tool Kit Functions 																		*																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click Operator management option in access control Bulk Upload Tool Kit
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Mar 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void Click_Operator_Management_Button()throws Exception {
		if(!waitForElementToAppear(Desktop_XPATH_Home_Page_Validation_OperatorMgmt, t))
		{
			Report.fnReportFailAndTerminateTest("Operator Management Button is not present", "Operator Management Button is not present", driver);
		}
		else{

			do {
				VerifyElementPresentAndClick(
						Desktop_XPATH_Home_Page_Validation_OperatorMgmt,
						"Operator Management Button");
				Thread.sleep(1000);
			} while (!VerifyElementPresent(Desktop_XPATH_Options_OperatorMgmt.replaceAll("M_text", "Bulk Operation Creation"), "Bulk Operation Creation Option"));
		}
	}

	
	
	
	/************************************************************************************************
	 Bulk Upload Tool Kit Functions 																		*																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To Click required Operator management option in access control Bulk Upload Tool Kit
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	20 Mar 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void Click_Operator_Management_Options(String Options)throws Exception {
		if(waitForElementToAppear(Desktop_XPATH_Options_OperatorMgmt.replaceAll("M_text", Options), t))
		{
				
				VerifyElementPresentAndClick(Desktop_XPATH_Options_OperatorMgmt.replaceAll("M_text", Options),Options+" link");
		}
	}
	
	
	
	/************************************************************************************************
	 Bulk Upload Tool Kit Functions 																		*																		*	
	 ***********************************************************************************************/

	/** Description 		: 	To wait for Bulk load user creation page to load 
	 * 	Coded by 			:	Rajan
	 * 	Created Data		:	21 Mar 2016
	 * 	Last Modified Date	:	
	 * 	Modified By			:	
	 */

	public void wait_for_BulkCreate_Page_toLoad()throws Exception {
		if(waitForElementToAppear(Desktop_XPATH_BulkOpCreate_Page_Validation_OperatorMgmt, t))
		
			Report.fnReportPass("Bulk Load User Creation page", driver);
		else
			Report.fnReportFail("Bulk Load User Creation", driver);	
			}
	
	



/************************************************************************************************
Bulk Upload Tool Kit Functions 																		*																		*	
***********************************************************************************************/

/** Description 		: 	To click close button for clsoing Bulk load user creation page  
* 	Coded by 			:	Rajan
* 	Created Data		:	21 Mar 2016
* 	Last Modified Date	:	
* 	Modified By			:	
*/

public void click_Close_BulkCreate_Page_toClose()throws Exception {

	VerifyElementPresentAndClick(Desktop_XPATH_BulkOpCreate_Page_CloseButton, "Bulk upload creator page close button");
	}


}




