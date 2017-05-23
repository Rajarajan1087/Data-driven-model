package com.EMS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.SharedModules.CustomerBean;
import com.SharedModules.DbUtilities;
import com.SharedModules.NameGenerator;
import com.SharedModules.OrderDetailsBean;
import com.Stubs.StubFilePlacing;
import com.Stubs.StubFilePlacing.StubType;
import com.Utils.Reusables;

public class SGOGenerator {

	public Reporter Report;
	
	
	

	
	/* Initial Sale Customer Creation for 291 with Ownbox */
	public String InitialSale_291_Ownbox(String CLI, String Fname, String Sname)
			throws Exception {
		// File file = new File(System.getProperty("user.dir")
		// + "\\ProvisioningTemplates\\Newline_LAF_SGO.xml");

		System.out.println("InitialSale_291_Ownbox");
		File file = new File(System.getProperty("user.dir")
				+ "\\ProvisioningTemplates\\InitialSale_291_Ownbox.xml");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", SGO = "";
		while ((line = reader.readLine()) != null)
			SGO += line + "\r\n";
		reader.close();

		// String NewCLI = NameGenerator.randomCLI(9);
		String AddressKey = "A" + CLI;

		String NLCLI = "NL" + CLI.substring(6);
		String OrderNO = "N" + CLI.substring(6);
		String NRR = "100" + NameGenerator.randomCLI(7);

		System.out.println("First Name is -> " + Fname + "  Last Name is -> "
				+ Sname);

		SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
		SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
		SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
		SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
		SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

		SGO = SGO.replaceAll("M_date1Z",
				Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
		SGO = SGO.replaceAll("M_date2",
				Reusables.getdateFormat("yyyy-MM-dd", 7));
		SGO = SGO.replaceAll("M_CLI", CLI);
		SGO = SGO.replaceAll("M_NLCLI", NLCLI);

		SGO = SGO.replaceAll("M_nrr", NRR);

		SGO = SGO.replaceAll("M_AddressKey", AddressKey);
		SGO = SGO.replaceAll("M_OrderNo", OrderNO);

		// SGO = SGO.replaceAll("M_apptid",
		// "1"+NameGenerator.randomCLI(9).substring(0, 4));
		// SGO = SGO.replaceAll("M_appt_date",
		// Reusables.getdateFormat("yyyy-MM-dd",23));

		SGO = SGO.replaceAll("M_first_name", Fname);
		SGO = SGO.replaceAll("M_last_name", Sname);
		SGO = SGO.replaceAll("M_email_id", Fname + Sname + "@gmail.com");

		// SGO = SGO.replaceAll("M_dob", "1984-09-09");

		// SGO = SGO.replaceAll("M_TVAPPID", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentId"));
		// SGO = SGO.replaceAll("M_TVAPPDATE", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentDate"));
		// SGO = SGO.replaceAll("M_SLOTSTART", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotStartTime"));
		// SGO = SGO.replaceAll("M_SLOTEND", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotEndTime"));

		FileWriter writer = new FileWriter(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291_Ownbox"
				+ ".xml");
		writer.write(SGO);
		writer.flush();
		writer.close();
		MessageTester.MessageTester_test(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291_Ownbox"
				+ ".xml");

		// return NLCLI + "," + Fname + "," + Sname;
		return OrderNO;

	}
	
	/* Initial Sale Customer Creation for 291 - UniCast */
	public String InitialSale_291_UC(String CLI, String Fname, String Sname)
			throws Exception {
		// File file = new File(System.getProperty("user.dir")
		// + "\\ProvisioningTemplates\\Newline_LAF_SGO.xml");

		File file = new File(System.getProperty("user.dir")
				+ "\\ProvisioningTemplates\\InitialSale_291_UC.xml");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", SGO = "";
		while ((line = reader.readLine()) != null)
			SGO += line + "\r\n";
		reader.close();

		// String NewCLI = NameGenerator.randomCLI(9);
		String AddressKey = "A" + CLI;

		String NLCLI = "NL" + CLI.substring(6);
		String OrderNO = "N" + CLI.substring(6);
		String NRR = "100" + NameGenerator.randomCLI(7);

		System.out.println("First Name is -> " + Fname + "  Last Name is -> "
				+ Sname);

		System.out.println("DGJKFGKJBG");
		SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
		SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
		SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
		SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
		SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

		SGO = SGO.replaceAll("M_date1Z",
				Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
		SGO = SGO.replaceAll("M_date2",
				Reusables.getdateFormat("yyyy-MM-dd", 7));
		SGO = SGO.replaceAll("M_CLI", CLI);
		SGO = SGO.replaceAll("M_NLCLI", NLCLI);

		SGO = SGO.replaceAll("M_nrr", NRR);
		System.out.println("DGJKFGKJBG");

		SGO = SGO.replaceAll("M_AddressKey", AddressKey);
		SGO = SGO.replaceAll("M_OrderNo", OrderNO);
		System.out.println("DGJKFGKJBG");


		// SGO = SGO.replaceAll("M_apptid",
		// "1"+NameGenerator.randomCLI(9).substring(0, 4));
		// SGO = SGO.replaceAll("M_appt_date",
		// Reusables.getdateFormat("yyyy-MM-dd",23));

		SGO = SGO.replaceAll("M_first_name", Fname);
		SGO = SGO.replaceAll("M_last_name", Sname);
		SGO = SGO.replaceAll("M_email_id", Fname + Sname + "@gmail.com");
		
		System.out.println("DGJKFGKJBG");

		// SGO = SGO.replaceAll("M_dob", "1984-09-09");

		// SGO = SGO.replaceAll("M_TVAPPID", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentId"));
		// SGO = SGO.replaceAll("M_TVAPPDATE", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentDate"));
		// SGO = SGO.replaceAll("M_SLOTSTART", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotStartTime"));
		// SGO = SGO.replaceAll("M_SLOTEND", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotEndTime"));

		FileWriter writer = new FileWriter(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291_UC"
				+ ".xml");
		writer.write(SGO);
		writer.flush();
		writer.close();
		MessageTester.MessageTester_test(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291_UC"
				+ ".xml");

		// return NLCLI + "," + Fname + "," + Sname;
		return OrderNO;

	}

	/*Customer Creation for 293 */
	public String Newline_293(String CLI, String Fname, String Sname)
			throws Exception {
		// File file = new File(System.getProperty("user.dir")
		// + "\\ProvisioningTemplates\\Newline_LAF_SGO.xml");

		File file = new File(System.getProperty("user.dir")
				+ "\\ProvisioningTemplates\\Newline_293.xml");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", SGO = "";
		while ((line = reader.readLine()) != null)
			SGO += line + "\r\n";
		reader.close();

		// String NewCLI = NameGenerator.randomCLI(9);
		String AddressKey = "A" + CLI;

		String NLCLI = "NL" + CLI.substring(6);
		String OrderNO = "N" + CLI.substring(6);
		String NRR = "100" + NameGenerator.randomCLI(7);

		System.out.println("First Name is -> " + Fname + "  Last Name is -> "
				+ Sname);

		System.out.println("DGJKFGKJBG");
		SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
		SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
		SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
		SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
		SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

		SGO = SGO.replaceAll("M_date1Z",
				Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
		SGO = SGO.replaceAll("M_date2",
				Reusables.getdateFormat("yyyy-MM-dd", 7));
		SGO = SGO.replaceAll("M_CLI", CLI);
		SGO = SGO.replaceAll("M_NLCLI", NLCLI);

		SGO = SGO.replaceAll("M_nrr", NRR);
		System.out.println("DGJKFGKJBG");

		SGO = SGO.replaceAll("M_AddressKey", AddressKey);
		SGO = SGO.replaceAll("M_OrderNo", OrderNO);
		System.out.println("DGJKFGKJBG");


		// SGO = SGO.replaceAll("M_apptid",
		// "1"+NameGenerator.randomCLI(9).substring(0, 4));
		// SGO = SGO.replaceAll("M_appt_date",
		// Reusables.getdateFormat("yyyy-MM-dd",23));

		SGO = SGO.replaceAll("M_first_name", Fname);
		SGO = SGO.replaceAll("M_last_name", Sname);
		SGO = SGO.replaceAll("M_email_id", Fname + Sname + "@gmail.com");
		
		System.out.println("DGJKFGKJBG");

		// SGO = SGO.replaceAll("M_dob", "1984-09-09");

		// SGO = SGO.replaceAll("M_TVAPPID", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentId"));
		// SGO = SGO.replaceAll("M_TVAPPDATE", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentDate"));
		// SGO = SGO.replaceAll("M_SLOTSTART", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotStartTime"));
		// SGO = SGO.replaceAll("M_SLOTEND", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotEndTime"));

		FileWriter writer = new FileWriter(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_Newline_293_"
				+ ".xml");
		writer.write(SGO);
		writer.flush();
		writer.close();
		MessageTester.MessageTester_test(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_Newline_293_"
				+ ".xml");

		// return NLCLI + "," + Fname + "," + Sname;
		return OrderNO;

	}
	
	/*Newline Customer Creation for 291 */
	public String Newline_291(String CLI, String Fname, String Sname)
			throws Exception {
		// File file = new File(System.getProperty("user.dir")
		// + "\\ProvisioningTemplates\\Newline_LAF_SGO.xml");

		File file = new File(System.getProperty("user.dir")
				+ "\\ProvisioningTemplates\\Newline_291.xml");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", SGO = "";
		while ((line = reader.readLine()) != null)
			SGO += line + "\r\n";
		reader.close();

		// String NewCLI = NameGenerator.randomCLI(9);
		String AddressKey = "A" + CLI;

		String NLCLI = "NL" + CLI.substring(6);
		String OrderNO = "N" + CLI.substring(6);
		String NRR = "100" + NameGenerator.randomCLI(7);

		System.out.println("First Name is -> " + Fname + "  Last Name is -> "
				+ Sname);

		System.out.println("DGJKFGKJBG");
		SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
		SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
		SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
		SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
		SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

		SGO = SGO.replaceAll("M_date1Z",
				Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
		SGO = SGO.replaceAll("M_date2",
				Reusables.getdateFormat("yyyy-MM-dd", 7));
		SGO = SGO.replaceAll("M_CLI", CLI);
		SGO = SGO.replaceAll("M_NLCLI", NLCLI);

		SGO = SGO.replaceAll("M_nrr", NRR);
		System.out.println("DGJKFGKJBG");

		SGO = SGO.replaceAll("M_AddressKey", AddressKey);
		SGO = SGO.replaceAll("M_OrderNo", OrderNO);
		System.out.println("DGJKFGKJBG");


		// SGO = SGO.replaceAll("M_apptid",
		// "1"+NameGenerator.randomCLI(9).substring(0, 4));
		// SGO = SGO.replaceAll("M_appt_date",
		// Reusables.getdateFormat("yyyy-MM-dd",23));

		SGO = SGO.replaceAll("M_first_name", Fname);
		SGO = SGO.replaceAll("M_last_name", Sname);
		SGO = SGO.replaceAll("M_email_id", Fname + Sname + "@gmail.com");
		
		System.out.println("DGJKFGKJBG");

		// SGO = SGO.replaceAll("M_dob", "1984-09-09");

		// SGO = SGO.replaceAll("M_TVAPPID", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentId"));
		// SGO = SGO.replaceAll("M_TVAPPDATE", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentDate"));
		// SGO = SGO.replaceAll("M_SLOTSTART", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotStartTime"));
		// SGO = SGO.replaceAll("M_SLOTEND", Reusables.getXMLdata(response,
		// "ns0:AppointmentDetails", "ns1:appointmentSlotEndTime"));

		FileWriter writer = new FileWriter(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_Newline_291_"
				+ ".xml");
		writer.write(SGO);
		writer.flush();
		writer.close();
		MessageTester.MessageTester_test(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_Newline_291_"
				+ ".xml");

		// return NLCLI + "," + Fname + "," + Sname;
		return OrderNO;

	}


	/* Initial Sale for TV Customer */
	public String generate263SGO_SwitcherSI(String CLI, String... Postcode)
			throws Exception {
		String Str_Postcode;
		System.out.println("Switcher 263 Self Install");
		if (Postcode.length < 1) {
			Str_Postcode = "W114AR";
		} else {
			Str_Postcode = Postcode[0];
		}
		File file = new File(System.getProperty("user.dir")
				+ "\\ProvisioningTemplates\\SGO_263SI.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", SGO = "";
		while ((line = reader.readLine()) != null)
			SGO += line + "\r\n";
		reader.close();

		String OrderNO = "N" + CLI.substring(4);
		String Fname = NameGenerator.randomName(6);
		String Sname = NameGenerator.randomName(6);
		System.out.println("First Name is -> " + Fname + "  Last Name is -> "
				+ Sname);
		String NRR = "140" + NameGenerator.randomCLI(5);

		SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
		SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
		SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
		SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
		SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

		SGO = SGO.replaceAll("M_CLI", CLI);
		SGO = SGO.replaceAll("M_PostCode", Str_Postcode);
		SGO = SGO.replaceAll("M_DATE",
				Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
		SGO = SGO.replaceAll("M_NRR", NRR);
		SGO = SGO.replaceAll("M_ORDERNO", OrderNO);
		SGO = SGO.replaceAll("M_FNAME", Fname);
		SGO = SGO.replaceAll("M_LNAME", Sname);

		FileWriter writer = new FileWriter(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_SGOTV263" + ".txt");
		writer.write(SGO);
		writer.flush();
		writer.close();
		MessageTester.MessageTester_test(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_SGOTV263" + ".txt");
		return SGO;

	}

	public void MobileUpdates_SGO(String CLI) throws Exception {
		File file = new File(System.getProperty("user.dir")
				+ "\\ProvisioningTemplates\\MobileUpdates.xml");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", SGO = "";
		while ((line = reader.readLine()) != null)
			SGO += line + "\r\n";
		reader.close();

		DbUtilities DBU = new DbUtilities(Report);
		OrderDetailsBean ODB = new OrderDetailsBean();
		CustomerBean CB = new CustomerBean();

		DBU.getCustomerDetails(CLI, CB);

		SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
		SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
		SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
		SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
		SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

		SGO = SGO.replaceAll("M_date1",
				Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));

		SGO = SGO.replaceAll("M_CLI", CLI);

		SGO = SGO.replaceAll("M_ACCOUNT", CB.getAccountNumber());
		SGO = SGO.replaceAll("M_CustomerId", CB.getCustomerNumber());

		FileWriter writer = new FileWriter(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_MobileUpdates" + ".xml");
		writer.write(SGO);
		writer.flush();
		writer.close();
		MessageTester.MessageTester_test(System.getProperty("user.dir")
				+ "\\ProvisioningUpdates\\" + CLI + "_MobileUpdates" + ".xml");

	}

	public String ManageMobileSubscription_SGO(String CLI,String MobAccountID) throws Exception {
		File file = new File(System.getProperty("user.dir")
		+ "\\ProvisioningTemplates\\ManageMobileSubscription.xml");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", SGO = "";
		while ((line = reader.readLine()) != null)
		SGO += line + "\r\n";
		reader.close();

		DbUtilities DBU = new DbUtilities(Report);
		OrderDetailsBean ODB = new OrderDetailsBean();
		CustomerBean CB = new CustomerBean();

		DBU.getCustomerDetails(CLI, CB);
		String CustNum = CB.getCustomerNumber();
		System.out.println(CustNum);
		System.out.println("CLI details");
		

		String MobileNumber = NameGenerator.randomMobileno(9);
//		String MobileNumber = NameGenerator.randomNumber("07", 9);
		System.out.println("MobileNumber is" + MobileNumber);

		String SubscriptionID = NameGenerator.randomNumber("10", 6);
		System.out.println("SubscriptionID is" + SubscriptionID);

		SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
		SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
		SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
		SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
		SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

		SGO = SGO.replaceAll("M_date1",
		Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
		System.out.println("TIME FORMAT"+ Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));

		System.out.println("Customer Number : "+CustNum);
		System.out.println("SGO Value : "+SGO);
		
		SGO = SGO.replaceAll("M_CustomerId", CustNum);
		
		SGO = SGO.replaceAll("M_MobAccountId", MobAccountID);
		SGO = SGO.replaceAll("M_MobileNumber", MobileNumber);
		SGO = SGO.replaceAll("M_SubscriptionId", SubscriptionID);
		
		System.out.println("SGO Value after replacement : "+SGO);
		
		FileWriter writer = new FileWriter(System.getProperty("user.dir")
		+ "\\ProvisioningUpdates\\" + CLI + "_ManageMobileSubscription"
		+ ".xml");
		
		System.out.println("Path Created");
		writer.write(SGO);
		writer.flush();
		writer.close();
		System.out.println("SGO written and closed");
		MessageTester.MessageTester_test(System.getProperty("user.dir")
		+ "\\ProvisioningUpdates\\" + CLI + "_ManageMobileSubscription"
		+ ".xml");
		
		System.out.println("Tester been done");

//		return SubscriptionID;
		return MobileNumber;

	}
	
	
//	/* Initial Sale Customer Creation for 291 with Manage Install - MultiCast */
//
//	public String InitialSale_291_MC(String CLI, String Fname, String Sname)throws Exception {
//
//	File file = new File(System.getProperty("user.dir")
//	+ "\\ProvisioningTemplates\\InitialSale_291.xml");
//
//	BufferedReader reader = new BufferedReader(new FileReader(file));
//	String line = "", SGO = "";
//	
//	while ((line = reader.readLine()) != null)
//	SGO += line + "\r\n";
//	reader.close();
//
//	String AddressKey = "A" + CLI;
//	String NLCLI = "NL" + CLI.substring(6);
//	String OrderNO = "N" + CLI.substring(6);
//	String NRR = "100" + NameGenerator.randomCLI(7);
//
//
//	System.out.println("First Name is -> " + Fname + " Last Name is -> "+ Sname);
//
//
//	SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
//	SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
//	SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
//	SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
//	SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);
//
//	SGO = SGO.replaceAll("M_date1Z",Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
//	SGO = SGO.replaceAll("M_date2",Reusables.getdateFormat("yyyy-MM-dd", 7));
//	SGO = SGO.replaceAll("M_CLI", CLI);
//	SGO = SGO.replaceAll("M_NLCLI", NLCLI);
//	SGO = SGO.replaceAll("M_nrr", NRR);
//	SGO = SGO.replaceAll("M_AddressKey", AddressKey);
//	SGO = SGO.replaceAll("M_OrderNo", OrderNO);
//
//	// SGO = SGO.replaceAll("M_apptid",
//
//	// "1"+NameGenerator.randomCLI(9).substring(0, 4));
//
//	// SGO = SGO.replaceAll("M_appt_date",
//
//	// Reusables.getdateFormat("yyyy-MM-dd",23));
//
//
//	SGO = SGO.replaceAll("M_first_name", Fname);
//
//	SGO = SGO.replaceAll("M_last_name", Sname);
//
//	SGO = SGO.replaceAll("M_email_id", Fname + Sname + "@gmail.com");
//
//
//	// SGO = SGO.replaceAll("M_dob", "1984-09-09");
//
//
//	// SGO = SGO.replaceAll("M_TVAPPID", Reusables.getXMLdata(response,
//
//	// "ns0:AppointmentDetails", "ns1:appointmentId"));
//
//	// SGO = SGO.replaceAll("M_TVAPPDATE", Reusables.getXMLdata(response,
//
//	// "ns0:AppointmentDetails", "ns1:appointmentDate"));
//
//	// SGO = SGO.replaceAll("M_SLOTSTART", Reusables.getXMLdata(response,
//
//	// "ns0:AppointmentDetails", "ns1:appointmentSlotStartTime"));
//
//	// SGO = SGO.replaceAll("M_SLOTEND", Reusables.getXMLdata(response,
//
//	// "ns0:AppointmentDetails", "ns1:appointmentSlotEndTime"));
//
//
//	FileWriter writer = new FileWriter(System.getProperty("user.dir")+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291"	+ ".xml");
//
//	writer.write(SGO);
//	writer.flush();
//	writer.close();
//
//	MessageTester.MessageTester_test(System.getProperty("user.dir")	+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291"+ ".xml");
//	return OrderNO;
//
//
//	}
//
//	
	/* Initial Sale Customer Creation for 291 with TV Store */

	public String InitialSale_291_TVStore(String CLI, String Fname, String Sname)throws Exception {

	File file = new File(System.getProperty("user.dir")
	+ "\\ProvisioningTemplates\\InitialSale_291_TVStore.xml");

	BufferedReader reader = new BufferedReader(new FileReader(file));
	String line = "", SGO = "";
	
	while ((line = reader.readLine()) != null)
	SGO += line + "\r\n";
	reader.close();

	String AddressKey = "A" + CLI;
	String NLCLI = "NL" + CLI.substring(6);
	String OrderNO = "N" + CLI.substring(6);
	String NRR = "100" + NameGenerator.randomCLI(7);

	System.out.println("Fast _CLI -> " + CLI );

	SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
	SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
	SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
	SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
	SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

	SGO = SGO.replaceAll("M_date1Z",Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
	SGO = SGO.replaceAll("M_date2",Reusables.getdateFormat("yyyy-MM-dd", 0));
	SGO = SGO.replaceAll("M_CLI", CLI);
	SGO = SGO.replaceAll("M_NLCLI", NLCLI);
	SGO = SGO.replaceAll("M_nrr", NRR);
	SGO = SGO.replaceAll("M_AddressKey", AddressKey);
	SGO = SGO.replaceAll("M_OrderNo", OrderNO);

	SGO = SGO.replaceAll("M_first_name", Fname);

	SGO = SGO.replaceAll("M_last_name", Sname);

	SGO = SGO.replaceAll("M_email_id", Fname + Sname + "@gmail.com");

	FileWriter writer = new FileWriter(System.getProperty("user.dir")+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291"	+ ".xml");

	writer.write(SGO);
	writer.flush();
	writer.close();

	MessageTester.MessageTester_test(System.getProperty("user.dir")	+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_291"+ ".xml");
	return OrderNO;



	}
	
	/* Initial Sale Customer Creation for 292 with TV Store*/

	public String InitialSale_292_TVStore(String CLI, String Fname, String Sname)throws Exception {

	File file = new File(System.getProperty("user.dir")
	+ "\\ProvisioningTemplates\\InitialSale_292_TVStore.xml");

	BufferedReader reader = new BufferedReader(new FileReader(file));
	String line = "", SGO = "";
	
	while ((line = reader.readLine()) != null)
	SGO += line + "\r\n";
	reader.close();

	String AddressKey = "A" + CLI;
	String NLCLI = "NL" + CLI.substring(6);
	String OrderNO = "N" + CLI.substring(6);
	String NRR = "100" + NameGenerator.randomCLI(7);

	System.out.println("Faster _CLI -> " + CLI );

	SGO = SGO.replaceAll("M_env", LoadEnvironment.ENV);
	SGO = SGO.replaceAll("M_emm_hostname", LoadEnvironment.EMM_HOSTNAME);
	SGO = SGO.replaceAll("M_emm_port", LoadEnvironment.EMM_PORT);
	SGO = SGO.replaceAll("M_emm_username", LoadEnvironment.EMM_USERNAME);
	SGO = SGO.replaceAll("M_emm_password", LoadEnvironment.EMM_PASSWORD);

	SGO = SGO.replaceAll("M_date1Z",Reusables.getdateFormat("yyyy-MM-dd'T'HH:mm:ss", 0));
	SGO = SGO.replaceAll("M_date2",Reusables.getdateFormat("yyyy-MM-dd", 0));
	SGO = SGO.replaceAll("M_CLI", CLI);
	SGO = SGO.replaceAll("M_NLCLI", NLCLI);
	SGO = SGO.replaceAll("M_nrr", NRR);
	SGO = SGO.replaceAll("M_AddressKey", AddressKey);
	SGO = SGO.replaceAll("M_OrderNo", OrderNO);
	String App_Id="7" + NameGenerator.randomCLI(7);
	SGO = SGO.replaceAll("M_app", App_Id);

	SGO = SGO.replaceAll("M_first_name", Fname);
	SGO = SGO.replaceAll("M_last_name", Sname);
	SGO = SGO.replaceAll("M_email_id", Fname + Sname + "@gmail.com");

	FileWriter writer = new FileWriter(System.getProperty("user.dir")+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_292"	+ ".xml");

	writer.write(SGO);
	writer.flush();
	writer.close();

	MessageTester.MessageTester_test(System.getProperty("user.dir")	+ "\\ProvisioningUpdates\\" + CLI + "_InitialSale_292"+ ".xml");
	return OrderNO;

	}

}
