package com.Engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class LoadEnvironment {

	// Generic System Properties

	public static int timeOut;
	public static int custTimeOut; 
	public static int justtimeOut;
	public static int counter;
	public static String Sheet_TestCases;
	public static String WB_NAD;
	public static String Sheet_TestCases_Customer180;
	public static String Sheet_TestCases_CTI;
	public static String Sheet_TestCases_NewPack;
	public static String Sheet_TestCases_Errorvalidations;
	public static String Sheet_TestCases_Payments;
	public static String Sheet_TestCases_VerifyCaller;
	public static String Sheet_TestCases_OCR;
	public static String Sheet_TestCases_CustomerSearch;
	public static String Sheet_TestCases_BVTPACK;

	// Down Stream Environment Properties	
	public static String INSTANCE;
	public static String PEGA_NAD_URL;
	public static String PEGA_USERNAME;
	public static String PEGA_PASSWORD;

	// PEGANBT DB details
	public static String PEGANBT_DBNAME;
	public static String PEGANBT_DBUSERNAME;
	public static String PEGANBT_DBPASSWORD;
	public static String PEGANBT_DBIP;
	public static String PEGANBT_DBPORT;

	// UP Stream Environment Properties	
	public static String MA_ONLINE_INSTANCE;
	public static String MA_URL;
	public static String ONLINE_URL;
	public static String AGENT_URL;
	public static String MA_CSA_URL;
	public static String ONLINE_CSA_URL;


	public static  String SSO_DBNAME;
	public static  String SSO_DBUSERNAME;
	public static  String SSO_DBPASSWORD;
	public static  String SSO_DBIP;
	public static  String SSO_DBPORT;

	public static  String NRM_DBNAME;
	public static  String NRM_DBUSERNAME;
	public static  String NRM_DBPASSWORD;
	public static  String NRM_DBIP;
	public static  String NRM_DBPORT;

	public static String CPEG_INSTANCE;

	public static  String ANOVO_CPEGPlaceOrder_URL;
	public static  String ANOVO_orderSender_URL;
	public static  String ANOVO_statusUpdate_URL;
	public static  String ANOVO_updateProcessor_URL;
	public static  String ANOVO_ReturnStatusupdate_URL;
	public static  String ANOVO_Unsolicitedorderprocessor_URL;

	public static  String NetLynk_CPEGPlaceOrder_URL;
	public static  String NetLynk_orderSender_URL;
	public static  String NetLynk_statusUpdate_URL;
	public static  String NetLynk_updateProcessor_URL;

	public static  String CPEG_DBNAME;
	public static  String CPEG_DBUSERNAME;
	public static  String CPEG_DBPASSWORD;
	public static  String CPEG_DBIP;
	public static  String CPEG_DBPORT;

	// Down Stream Environment Properties	
	public static String ENV;
	public static String CRM_URL;
	public static String CRM_USERNAME;
	public static String CRM_PASSWORD;
	public static String BW_SERVERIP;
	public static String BW_SERVERPORT;
	// eMM variables
	public static String EMM_USERNAME;
	public static String EMM_PASSWORD;
	public static String EMM_HOSTNAME;
	public static String EMM_PORT;
	// CRM DB variables
	public static String CRM_DBNAME;
	public static String CRM_DBUSERNAME;
	public static String CRM_DBPASSWORD;
	public static String CRM_DBIP;
	public static String CRM_DBPORT;
	// CLI DB variables
	public static String CLI_DBNAME;
	public static String CLI_DBUSERNAME;
	public static String CLI_DBPASSWORD;
	public static String CLI_DBIP;
	public static String CLI_DBPORT;
	// SV DB variables
	public static String SV_DBNAME;
	public static String SV_DBUSERNAME;
	public static String SV_DBPASSWORD;
	public static String SV_DBSCHEMA;
	public static String SV_DBIP;
	public static String SV_DBPORT;
	// OMP DB variables
	public static String OMP_DBNAME;
	public static String OMP_DBUSERNAME;
	public static String OMP_DBPASSWORD;
	public static String OMP_DBSCHEMA;
	public static String OMP_DBIP;
	public static String OMP_DBPORT;
	// SKID DB variables
	public static String SKID_DBNAME;
	public static String SKID_DBUSERNAME;
	public static String SKID_DBPASSWORD;
	public static String SKID_DBIP;
	public static String SKID_DBPORT;
	// DDGS DB variables
	public static String DDGS_DBNAME;
	public static String DDGS_DBUSERNAME;
	public static String DDGS_DBPASSWORD;
	public static String DDGS_DBIP;
	public static String DDGS_DBPORT;
	//EV BD Variables
	public static  String EVG_DBNAME;
	public static  String EVG_DBUSERNAME;
	public static  String EVG_DBPASSWORD;
	public static  String EVG_DBIP;
	public static  String EVG_DBPORT;

	//Loading Objects
	public static Properties prop = new Properties();
	public static InputStream input = null;

	public static String workingDir = System.getProperty("user.dir");
	public static void LoadGenericSystemProperties() throws Exception{
		try {

			input = new FileInputStream(workingDir+"/PropertyFiles/GenericProperties/TestConfigGeneric.properties");
			prop.load(input);
			//get the property value and print it out
			timeOut =  (int) Double.parseDouble(prop.getProperty("timeOut"));
			WB_NAD 	=	LoadEnvironment.workingDir+prop.getProperty("WB_NAD");
			justtimeOut =  (int) Double.parseDouble(prop.getProperty("justtimeOut"));
			custTimeOut =   (int) Double.parseDouble(prop.getProperty("custTimeOut"));
			counter =  (int) Double.parseDouble(prop.getProperty("counter"));
			Sheet_TestCases=prop.getProperty("Sheet_TestCases");
			Sheet_TestCases_Customer180=prop.getProperty("Sheet_TestCases_Customer180");
			Sheet_TestCases_CustomerSearch=prop.getProperty("Sheet_TestCases_CustomerSearch");
			Sheet_TestCases_BVTPACK=prop.getProperty("Sheet_TestCases_BVTPACK");
			Sheet_TestCases_CTI=prop.getProperty("Sheet_TestCases_CTI");
			Sheet_TestCases_NewPack=prop.getProperty("Sheet_TestCases_NewPack");
			Sheet_TestCases_Errorvalidations=prop.getProperty("Sheet_TestCases_Errorvalidations");
			Sheet_TestCases_Payments=prop.getProperty("Sheet_TestCases_Payments");
			Sheet_TestCases_VerifyCaller=prop.getProperty("Sheet_TestCases_VerifyCaller");
			Sheet_TestCases_OCR=prop.getProperty("Sheet_TestCases_OCR");
			System.out.println("*-------Generic Test Properties Loaded-------*");
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public static void LoadDownStreamPEGAENV() throws Exception{

		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream(workingDir+"\\PropertyFiles\\EnvironmentProperties\\PEGA.properties");
			//load a properties file
			prop.load(input);
			INSTANCE=prop.getProperty("INSTANCE");
			PEGA_NAD_URL=prop.getProperty("PEGA_NAD_URL");
			PEGA_USERNAME=prop.getProperty("PEGA_USERNAME");
			PEGA_PASSWORD=prop.getProperty("PEGA_PASSWORD");
			// PEGANBT DB variables
			PEGANBT_DBNAME=prop.getProperty("PEGANBT_DBNAME");
			PEGANBT_DBUSERNAME=prop.getProperty("PEGANBT_DBUSERNAME");
			PEGANBT_DBPASSWORD=prop.getProperty("PEGANBT_DBPASSWORD");
			PEGANBT_DBIP=prop.getProperty("PEGANBT_DBIP");
			PEGANBT_DBPORT=prop.getProperty("PEGANBT_DBPORT");
			System.out.println("Pega props loaded");

		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	public static void LoadFile() throws Exception{

		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream(workingDir+"\\LogFiles\\");

			//load a properties file
			prop.load(input);
			INSTANCE=prop.getProperty("INSTANCE");
			PEGA_NAD_URL=prop.getProperty("PEGA_NAD_URL");
			System.out.println("Pega props loaded");

		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	public static void LoadDownStreamENV(String env) throws Exception{

		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(workingDir+"\\PropertyFiles\\EnvironmentProperties\\"+env+".properties");
			//load a properties file
			prop.load(input);

			CPEG_INSTANCE=prop.getProperty("CPEG_INSTANCE");

			ANOVO_CPEGPlaceOrder_URL=prop.getProperty("ANOVO_CPEGPlaceOrder_URL");
			ANOVO_orderSender_URL=prop.getProperty("ANOVO_orderSender_URL");
			ANOVO_statusUpdate_URL=prop.getProperty("ANOVO_statusUpdate_URL");
			ANOVO_updateProcessor_URL=prop.getProperty("ANOVO_updateProcessor_URL");
			ANOVO_ReturnStatusupdate_URL=prop.getProperty("ANOVO_ReturnStatusupdate_URL");
			ANOVO_Unsolicitedorderprocessor_URL=prop.getProperty("ANOVO_Unsolicitedorderprocessor_URL");

			NetLynk_CPEGPlaceOrder_URL=prop.getProperty("NetLynk_CPEGPlaceOrder_URL");
			NetLynk_orderSender_URL=prop.getProperty("NetLynk_orderSender_URL");
			NetLynk_statusUpdate_URL=prop.getProperty("NetLynk_statusUpdate_URL");
			NetLynk_updateProcessor_URL=prop.getProperty("NetLynk_updateProcessor_URL");

			CPEG_DBNAME=prop.getProperty("CPEG_DBNAME");
			CPEG_DBUSERNAME=prop.getProperty("CPEG_DBUSERNAME");
			CPEG_DBPASSWORD=prop.getProperty("CPEG_DBPASSWORD");
			CPEG_DBIP=prop.getProperty("CPEG_DBIP");
			CPEG_DBPORT=prop.getProperty("CPEG_DBPORT");

			//get the property value and print it out
			ENV = prop.getProperty("ENV");
			CRM_URL = prop.getProperty("CRM_URL");
			CRM_USERNAME = prop.getProperty("CRM_USERNAME");
			CRM_PASSWORD = prop.getProperty("CRM_PASSWORD");
			BW_SERVERIP=prop.getProperty("BW_SERVERIP");
			BW_SERVERPORT=prop.getProperty("BW_SERVERPORT");
			// eMM variables
			EMM_USERNAME=prop.getProperty("EMM_USERNAME");
			EMM_PASSWORD=prop.getProperty("EMM_PASSWORD");
			EMM_HOSTNAME=prop.getProperty("EMM_HOSTNAME");
			EMM_PORT=prop.getProperty("EMM_PORT");
			// CRM DB variables
			CRM_DBNAME=prop.getProperty("CRM_DBNAME");
			CRM_DBUSERNAME=prop.getProperty("CRM_DBUSERNAME");
			CRM_DBPASSWORD=prop.getProperty("CRM_DBPASSWORD");
			CRM_DBIP=prop.getProperty("CRM_DBIP");
			CRM_DBPORT=prop.getProperty("CRM_DBPORT");
			// CLI DB variables
			CLI_DBNAME=prop.getProperty("CLI_DBNAME");
			CLI_DBUSERNAME=prop.getProperty("CLI_DBUSERNAME");
			CLI_DBPASSWORD=prop.getProperty("CLI_DBPASSWORD");
			CLI_DBIP=prop.getProperty("CLI_DBIP");
			CLI_DBPORT=prop.getProperty("CLI_DBPORT");
			// SV DB variables
			SV_DBNAME=prop.getProperty("SV_DBNAME");
			SV_DBUSERNAME=prop.getProperty("SV_DBUSERNAME");
			SV_DBPASSWORD=prop.getProperty("SV_DBPASSWORD");
			SV_DBSCHEMA=prop.getProperty("SV_DBSCHEMA");
			SV_DBIP=prop.getProperty("SV_DBIP");
			SV_DBPORT=prop.getProperty("SV_DBPORT");
			// OMP DB variables
			OMP_DBNAME=prop.getProperty("OMP_DBNAME");
			OMP_DBUSERNAME=prop.getProperty("OMP_DBUSERNAME");
			OMP_DBPASSWORD=prop.getProperty("OMP_DBPASSWORD");
			OMP_DBSCHEMA=prop.getProperty("OMP_DBSCHEMA");
			OMP_DBIP=prop.getProperty("OMP_DBIP");
			OMP_DBPORT=prop.getProperty("OMP_DBPORT");
			// SKID DB variables
			SKID_DBNAME=prop.getProperty("SKID_DBNAME");
			SKID_DBUSERNAME=prop.getProperty("SKID_DBUSERNAME");
			SKID_DBPASSWORD=prop.getProperty("SKID_DBPASSWORD");
			SKID_DBIP=prop.getProperty("SKID_DBIP");
			SKID_DBPORT=prop.getProperty("SKID_DBPORT");
			// DDGS DB variables
			DDGS_DBNAME=prop.getProperty("DDGS_DBNAME");
			DDGS_DBUSERNAME=prop.getProperty("DDGS_DBUSERNAME");
			DDGS_DBPASSWORD=prop.getProperty("DDGS_DBPASSWORD");
			DDGS_DBIP=prop.getProperty("DDGS_DBIP");
			DDGS_DBPORT=prop.getProperty("DDGS_DBPORT");
			//EVG DB Variables
			EVG_DBNAME=prop.getProperty("EVG_DBNAME");
			EVG_DBUSERNAME=prop.getProperty("EVG_DBUSERNAME");
			EVG_DBPASSWORD=prop.getProperty("EVG_DBPASSWORD");
			EVG_DBIP=prop.getProperty("EVG_DBIP");
			EVG_DBPORT=prop.getProperty("EVG_DBPORT");
			System.out.println("*-------DownStream Env Properties Loaded-------*");
			//NRM DB Variables
			NRM_DBNAME=prop.getProperty("NRM_DBNAME");
			NRM_DBUSERNAME=prop.getProperty("NRM_DBUSERNAME");
			NRM_DBPASSWORD=prop.getProperty("NRM_DBPASSWORD");
			NRM_DBIP=prop.getProperty("NRM_DBIP");
			NRM_DBPORT=prop.getProperty("NRM_DBPORT");

		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		}

	}
}