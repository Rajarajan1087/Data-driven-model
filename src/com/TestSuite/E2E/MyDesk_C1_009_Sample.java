/*Test Scenario 		: PEGA - NAD (MyDesk_C1_009_Sample)
 *Test Case Names 		: Verify the caller information bar is displayed with hide/show option (account holder,non-account holder & nominated user)
 *Package				: Customer180
 *Created By			: Raja
 *Created on			: 07 Oct 2016
 */

package com.TestSuite.E2E;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BusinessModules.MyDesk.Account_Page;
import com.BusinessModules.MyDesk.Cases_Page;
import com.BusinessModules.MyDesk.Common_Functions;
import com.BusinessModules.MyDesk.Customer_Page;
import com.BusinessModules.MyDesk.Home_Page;
import com.BusinessModules.MyDesk.Summary_Page;
import com.BusinessModules.MyDesk.VerifyCaller_Page;
import com.Engine.SeleniumSetup;
import com.Enumerations.Generic;
import com.SharedModules.Constants;
import com.SharedModules.DBTestDataSetup;
import com.SharedModules.DbUtilities;

import java.util.regex.Pattern.*;



public class MyDesk_C1_009_Sample extends SeleniumSetup implements Constants,Generic {


	
	public static void main(String args[])throws Exception {
		
		
		

		;
				
				String Balance="1.01";
		try {
			
			int indexStr=Balance.indexOf(".");
			System.out.println(indexStr);
			System.out.println("Splitting");
			System.out.println(Balance.substring(0, indexStr));
			System.out.println("First been stored successfully");
			System.out.println(Balance.substring(indexStr+1));
			System.out.println("Second been stored successfully");
		} catch (Exception e) {
		
			System.out.println("Exception Caught");
			e.printStackTrace();
		}
		
//		System.out.println(balInt);
//		System.out.println(balFloat);
		
		
		
//		String cn="MyDesk_C1_009QWERTYTest Description: \n10)Verify  customer account summary displayed account number,account status,phone number,package name,package status in customer tab\n11)Verify the correspondence address details  are displayed  in contact details section of customer tab\n12)Verify the Billing address details  are displayed  in contact details section of customer tab\n13)Verify the Installation address details  are displayed  in contact details section of customer tab\n14)Verify telephone number is displayed in contact details section of customer tab\n15)Verify marketing preferences and  preferred contact methods is displayed as read only in the customer tab\n16)Verify the telephone password of customer  is displayed in security section";
//		
//		System.out.println(cn.split("QWERTY")[1]);
		
		/*cn=cn.replaceAll("Account Holder", "").trim();
		
		  System.out.println(""+cn);
		
		
		  DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		  DateFormat ddf = new SimpleDateFormat("dd-MMM-yyyy");
		  Date val = (Date)sdf.parse("10/13/2009 0:0:0");       
		  System.out.println(ddf.format(val));*/

		
	}

}
