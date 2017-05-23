package com.SharedModules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.Enumerations.Generic.*;
import com.Utils.Reusables;

public class DbUtilities implements Constants {

	public Reporter Report;
	public Connection con = null;
	PreparedStatement stm = null;
	public DbUtilities(Reporter report) {
		Report = report;
	}

	public void getCustomerDetails(String CLI, CustomerBean CB) throws Exception{
		String query = null;
		ResultSet rs = null;

		con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":"+LoadEnvironment.CRM_DBNAME,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);

		try{
			query = "select * from CBLOWNER.v_customer_search where CLI = '"+CLI+"'";
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stm.executeQuery();

			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){

					CB.setAccountNumber(rs.getString("ACCOUNTNUMBER"));
					CB.setCustomerNumber(rs.getString("CUSTOMERNUMBER"));
					CB.setCLI(rs.getString("CLI"));
					rs.afterLast();
				}
			}else{
				throw new RuntimeException("No Records");
			}


		}catch(RuntimeException e){
			if(e.getMessage().equals("No Records")){

				Report.fnReportFail(" No  customer details Records found for " + CLI);
			}

		}finally{
			ConnectionFactory.closeConnection(con);
		}
	}
	/** Description   : To retrieve Data with multiple packages attributed to them
	 * @param package1
	 * @param package2
	 * @return
	 * @throws Exception
	 */
	public String getMultipleCLIAccountNumber(String package1, String package2)
			throws Exception {
		String query;
		String accountNumber = "";
		ResultSet rs = null;
		con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"
				+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT
				+ ":" + LoadEnvironment.CRM_DBNAME,
				LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);

		query = "SELECT ACCOUNTNUMBER FROM (SELECT DISTINCT ACCOUNTNUMBER FROM CBLOWNER.portfoliosalespackage psp JOIN ccsowner.account acc ON ( acc.id = psp.ACCOUNTID and acc.ACCOUNTNUMBER  IN (select s1.ACCOUNTNUMBER from  (SELECT acc.ACCOUNTNUMBER   FROM CBLOWNER.portfoliosalespackage psp   JOIN ccsowner.account acc ON ( acc.id = psp.ACCOUNTID)   WHERE psp.NAME IN ('"
				+ package1
				+ "')   ) s1   JOIN   (   SELECT acc.ACCOUNTNUMBER   FROM CBLOWNER.portfoliosalespackage psp   JOIN ccsowner.account acc   ON ( acc.id = psp.ACCOUNTID)   WHERE psp.NAME IN ('"
				+ package2
				+ "')   ) s2   on   (s1.ACCOUNTNUMBER = s2.ACCOUNTNUMBER) )) ORDER BY DBMS_RANDOM.RANDOM ) WHERE rownum = 1";

		System.out.println(query);
		try {
			PreparedStatement stm = con.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery();

			if (rs.next()) {
				rs.beforeFirst();

				while (rs.next()) {
					accountNumber = rs.getString("ACCOUNTNUMBER");
					rs.afterLast();
				}

			} else {
				throw new RuntimeException("NO COMMAND FOUND");
			}
		} catch (RuntimeException e) {
			if (e.getMessage().equals("NO COMMAND FOUND")) {

				Report.fnReportFailAndTerminateTest("getBillingMethod details",
						"NO COMMAND FOUND");
			}
		} finally {
			ConnectionFactory.closeConnection(con);
		}

		return accountNumber;
	}
	/**
	 * Description 		: To Execute the Provided query on the provided DB
	 * @param query
	 * @param dbname
	 * @param Str_ColumnName
	 * @return
	 * @throws Exception
	 */
	public String DBConnect(String query, DBName dbname,String Str_ColumnName)throws Exception {
		ResultSet rs=null;
		String values = "";
		try {
			String IP = "", PORT = "", UName = "", DBNAME = "", PASS = "";
			switch (dbname.name()) {
			case "CRM":
				con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);
				break;
			case "SV":
				con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.SV_DBIP + ":" + LoadEnvironment.SV_DBPORT + ":" + LoadEnvironment.SV_DBNAME, LoadEnvironment.SV_DBUSERNAME, LoadEnvironment.SV_DBPASSWORD);
				break;
			case "OMP":
				con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.OMP_DBIP + ":" + LoadEnvironment.OMP_DBPORT + ":" + LoadEnvironment.OMP_DBNAME, LoadEnvironment.OMP_DBUSERNAME, LoadEnvironment.OMP_DBPASSWORD);
				break;
			}

			stm = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery();

			if (rs.next()) {

				rs.beforeFirst();
				while(rs.next()) {
					values += ","+rs.getString(Str_ColumnName);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return values.replaceAll(",$", "").replaceAll("^,", "");
	}
	/**
	 * @param str_Account
	 * @return
	 */
	public String getclifromaccount(String str_Account) {
		String CLI="";
		String query="";
		ResultSet rs=null;
		DbUtilities DBU=new DbUtilities(Report);
		try {
			query="select cli from CBLOWNER.v_customer_search where accountnumber='"+str_Account+"' and not cli like '%HM' and not cli like '%NL'";
			CLI=DBU.DBConnect(query, DBName.CRM,"CLI");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CLI;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getDCACustomer(int id)throws Exception{
		String Account_number="";
		String query=null;
		ResultSet rs = null;
		DbUtilities DBU=new DbUtilities(Report);
		try {
			query="select ACCOUNT_NAME from "+LoadEnvironment.SV_DBSCHEMA+".account a, "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array nda where nda.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID "
					+" and nda.derived_attribute_id = '12000066' "
					+" AND SYSDATE BETWEEN nda.EFFECTIVE_START_DATE AND nda.EFFECTIVE_END_dATE and nda.index6_value="+id+" and ROWNUM<2 ";

			Account_number=DBU.DBConnect(query, DBName.SV, "ACCOUNT_NAME");
		} catch (Exception e) {
			// TODO: handle exception
		}
		//System.out.println(Account_number);
		return Account_number;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String getMulticlidata()throws Exception{
		String query=null,CLI=null;
		ResultSet rs=null;
		DbUtilities DBU=new DbUtilities(Report);
		try {
			query="Select p.cli from cblowner.portfoliosalespackage P,ccsowner.account a,"
					+ "cblowner.portfoliosalespackage P1 "
					+ "where p.accountid=a.id and p1.accountid=a.id and p.enddate is null "
					+ "and p1.enddate is null and p.cli<>p1.cli "
					+ "and not p.cli like '%HM%' and not p.cli like '%NL%' "
					+ "and not p1.cli like '%HM%' and not p1.cli like '%NL%' and ROWNUM<2";
			CLI= DBU.DBConnect(query, DBName.CRM,"CLI");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CLI;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String getMulticli_withHM()throws Exception{
		String query=null,CLI=null;
		ResultSet rs=null;
		DbUtilities DBU=new DbUtilities(Report);
		try {
			query="Select p.cli from cblowner.portfoliosalespackage P,ccsowner.account a,"
					+ "cblowner.portfoliosalespackage P1 "
					+ "where p.accountid=a.id and p1.accountid=a.id and p.enddate is null "
					+ "and p1.enddate is null and p.cli<>p1.cli "
					+ "and not p.cli like '%HM%' and not p.cli like '%NL%' "
					+ "and p1.cli like '%HM%' and not p1.cli like '%NL%' and ROWNUM<2";
			CLI=	DBU.DBConnect(query, DBName.CRM, "CLI");
		} catch (Exception e) {

			e.printStackTrace();
		}

		return CLI;
	}




	/**Description	: To Retrieve all Customer Details
	 * 
	 * 				--------------------------MAP Contains--------------------
	 * 
	 * 				TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE
	 * 				SECURITYQUESTNTEXT,SECURITYANSWERTEXT,ONLINEUSERNAME,ONLINEPASSWORD,MARKETINGPREFEMAILFLAG,MARKETINGPREFLETTERFLAG,
	 *    			MARKETINGPREFSMSFLAG,MARKETINGPREFVOICEFLAG,BIRTHDATE,GENDERCODE
	 * @param CLI
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Map<String,String> RetrieveCustomerDetails(String CLI,String Account)throws Exception{

		Map<String, String> map = new HashMap<String, String>();
		String Str_Query = "";	
		Connection CRMcon = null;
		Connection OMPcon = null;
		Connection SVcon  = null;
		PreparedStatement preparedStatement =null;
		ResultSet RS = null;
		ResultSet RS1 = null;
		ResultSet RS2 = null;
		int columnCount = 0;
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd1;
		ResultSetMetaData rsmd2;
		
		try{
			CRMcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);
			SVcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.SV_DBIP + ":" + LoadEnvironment.SV_DBPORT + ":" + LoadEnvironment.SV_DBNAME, LoadEnvironment.SV_DBUSERNAME, LoadEnvironment.SV_DBPASSWORD);
			OMPcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.OMP_DBIP + ":" + LoadEnvironment.OMP_DBPORT + ":" + LoadEnvironment.OMP_DBNAME, LoadEnvironment.OMP_DBUSERNAME, LoadEnvironment.OMP_DBPASSWORD);

			/*
			 * Get TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE,BRAND_NAME,BRAND_CODE,BRAND_ID
			 */

			Str_Query = "select TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE,BRAND_NAME,BRAND_CODE,BRAND_ID "
					+ " from cblowner.v_customer_search"
					+ " where CLI in ('"+CLI+"') ";
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=preparedStatement.executeQuery();

			if(RS.next()){
				
				RS.beforeFirst();
				rsmd = RS.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS.getString(rsmd.getColumnName(counter)));
						System.out.println(RS.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("TITLE", RS.getString("TITLE"));									
						map.put("FIRSTNAME", RS.getString("FIRSTNAME"));
						map.put("LASTNAME", RS.getString("LASTNAME"));
						map.put("CLI", RS.getString("CLI"));
						map.put("CUSTOMERNUMBER", RS.getString("CUSTOMERNUMBER"));
						map.put("ACCOUTNNUMBER", RS.getString("ACCOUNTNUMBER"));
						map.put("PSPSTATUSCODE", RS.getString("PSPSTATUSCODE"));
						map.put("BRAND_NAME", RS.getString("BRAND_NAME"));
						map.put("BRAND_CODE", RS.getString("BRAND_CODE"));
						map.put("BRAND_ID", RS.getString("BRAND_ID"));						
					}
									
			}
			
			/*
			 * Get NAME,BRANCHCODE,ACTIVATIONDATE,DISCONNECTIONDATE
			 */

			Str_Query = "select NAME,BRANCHCODE,ACTIVATIONDATE,DISCONNECTIONDATE from cblowner.PORTFOLIOSALESPACKAGE WHERE CLI in ('"+CLI+"')";
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=preparedStatement.executeQuery();

			if(RS.next()){
				
				RS.beforeFirst();
				rsmd = RS.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS.getString(rsmd.getColumnName(counter)));
						System.out.println(RS.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("PACKAGENAME", RS.getString("NAME"));									
						map.put("BRANCHCODE", RS.getString("BRANCHCODE"));
						map.put("ACTIVATIONDATE", RS.getString("ACTIVATIONDATE"));
						map.put("DISCONNECTIONDATE", RS.getString("DISCONNECTIONDATE"));
												
					}									
			}

			/*
			 * Get SECURITYQUESTNTEXT,SECURITYANSWERTEXT,ONLINEUSERNAME,ONLINEPASSWORD,MARKETINGPREFEMAILFLAG,MARKETINGPREFLETTERFLAG,MARKETINGPREFSMSFLAG,MARKETINGPREFVOICEFLAG,BIRTHDATE,GENDERCODE
			 */
			
			DbUtilities DbU = new DbUtilities(Report);
			CustomerBean CB = new CustomerBean();
			
			DbU.getCustomerDetails(CLI, CB);
			String Str_CustomerNumber = CB.getCustomerNumber();
			

			Str_Query = "select SECURITYQUESTNTEXT,SECURITYANSWERTEXT,ONLINEUSERNAME,ONLINEPASSWORD,MARKETINGPREFEMAILFLAG,MARKETINGPREFLETTERFLAG,MARKETINGPREFSMSFLAG,MARKETINGPREFVOICEFLAG,BIRTHDATE,GENDERCODE"
					+ " from cblowner.v_customer_full"
					+ " where customernumber in ('"+Str_CustomerNumber+"')";
			System.out.println(Str_Query);
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS1=preparedStatement.executeQuery();

			if(RS1.next()){
				
				RS1.beforeFirst();
				rsmd = RS1.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS1.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS1.getString(rsmd.getColumnName(counter)));
						System.out.println(RS1.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("SECURITYQUESTNTEXT", RS1.getString("SECURITYQUESTNTEXT"));
						map.put("SECURITYANSWERTEXT", RS1.getString("SECURITYANSWERTEXT"));
						map.put("ONLINEUSERNAME", RS1.getString("ONLINEUSERNAME"));
						map.put("ONLINEPASSWORD", RS1.getString("ONLINEPASSWORD"));
						map.put("MARKETINGPREFEMAILFLAG", RS1.getString("MARKETINGPREFEMAILFLAG"));
						map.put("MARKETINGPREFLETTERFLAG", RS1.getString("MARKETINGPREFLETTERFLAG"));
						map.put("MARKETINGPREFSMSFLAG", RS1.getString("MARKETINGPREFSMSFLAG"));
						map.put("MARKETINGPREFVOICEFLAG", RS1.getString("MARKETINGPREFVOICEFLAG"));
						map.put("PREFERREDCONTACTMETHOD", RS1.getString("PREFERCONTMTHDTEXT"));
						map.put("BIRTHDATE", RS1.getString("BIRTHDATE"));
						map.put("GENDERCODE", RS1.getString("GENDERCODE"));						
					}									
			}
			

			/*
			 * Billing preference method
			 * Get INV_MEDIA,INV_LAYOUT,INV_ITEMISATION
			 */

			Str_Query = "select index1_value,"
					+ "(SELECT ABBREVIATION FROM " +LoadEnvironment.SV_DBSCHEMA+ ".REFERENCE_CODE WHERE REFERENCE_TYPE_ID = 9000175 AND REFERENCE_CODE = RESULT1_VALUE) INV_MEDIA, "
					+ "(SELECT ABBREVIATION FROM " +LoadEnvironment.SV_DBSCHEMA+ ".REFERENCE_CODE WHERE REFERENCE_TYPE_ID = 9000176 AND REFERENCE_CODE = RESULT2_VALUE) INV_LAYOUT, "
					+ "(SELECT ABBREVIATION FROM " +LoadEnvironment.SV_DBSCHEMA+ ".REFERENCE_CODE WHERE REFERENCE_TYPE_ID = 9000177 AND REFERENCE_CODE = RESULT3_VALUE) INV_ITEMISATION, "
					+ "(SELECT ABBREVIATION FROM " +LoadEnvironment.SV_DBSCHEMA+ ".REFERENCE_CODE WHERE REFERENCE_TYPE_ID = 9000178 AND REFERENCE_CODE = RESULT4_VALUE) INV_MASKING "
					+ "from " +LoadEnvironment.SV_DBSCHEMA+ ".customer_node_da_array where derived_attribute_id = 9000098 "
					+ "AND SYSDATE BETWEEN EFFECTIVE_START_DATE AND EFFECTIVE_END_dATE "
					+ "AND CUSTOMER_NODE_ID in (select acc.CUSTOMER_NODE_ID from " +LoadEnvironment.SV_DBSCHEMA+ ".account acc where acc.account_name='"+Account+"')";
			
			System.out.println(Str_Query);
			preparedStatement=SVcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS2=preparedStatement.executeQuery();

			if(RS2.next()){
				
				RS2.beforeFirst();
				rsmd = RS2.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS2.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS2.getString(rsmd.getColumnName(counter)));
						System.out.println(RS2.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("BillMethod", RS2.getString("INV_MEDIA"));
						map.put("BillFormat", RS2.getString("INV_LAYOUT"));
						map.put("BillType", RS2.getString("INV_ITEMISATION"));
											
					}									
			}
			
			/*
			 * Billing Details
			 * Get INV_MEDIA,INV_LAYOUT,INV_ITEMISATION
			 */

			Str_Query = "select issue_date last_bill_date,invoice_amount,payment_due_date,current_balance outstanding_balance "
					+ "from " +LoadEnvironment.SV_DBSCHEMA+ ".invoice i,(select max(invoice_id) invoice_id from " +LoadEnvironment.SV_DBSCHEMA+ ".invoice "
					+ "where account_id = '"+Account+"' )a, account_history ah "
					+ "where i.invoice_id=a.invoice_id "
					+ "and i.account_id = ah.account_id "
					+ "and SYSDATE between ah.effective_start_date and ah.effective_end_date ";	
			
			System.out.println(Str_Query);
			preparedStatement=SVcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS2=preparedStatement.executeQuery();

			if(RS2.next()){
				
				RS2.beforeFirst();
				rsmd = RS2.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS2.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS2.getString(rsmd.getColumnName(counter)));
						System.out.println(RS2.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("last_bill_date", RS2.getString("LAST_BILL_DATE"));
						map.put("invoice_amount", RS2.getString("INVOICE_AMOUNT"));
						map.put("payment_due_date", RS2.getString("PAYMENT_DUE_DATE"));
						map.put("outstanding_balance", RS2.getString("OUTSTANDING_BALANCE"));
									
					}									
			}
						
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(CRMcon);
			ConnectionFactory.closeConnection(OMPcon);
			ConnectionFactory.closeConnection(SVcon);
		}

		return map;

	}
	
	@SuppressWarnings("unused")
	public Map<String,String> accountSearch(String Account,String PkgStatus)throws Exception{

		Map<String, String> map = new HashMap<String, String>();
		String Str_Query = "";	
		Connection CRMcon = null;
		Connection OMPcon = null;
		Connection SVcon  = null;
		PreparedStatement preparedStatement =null;
		ResultSet RS = null;
		ResultSet RS1 = null;
		ResultSet RS2 = null;
		int columnCount = 0;
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd1;
		ResultSetMetaData rsmd2;
		
		try{
			CRMcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);	

			/*
			 * Get TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE,BRAND_NAME,BRAND_CODE,BRAND_ID
			 */

			Str_Query = "select TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE,BRAND_NAME,BRAND_CODE,BRAND_ID "
					+ " from cblowner.v_customer_search"
					+ " where ACCOUNTNUMBER in ('"+Account+"') and PSPSTATUSCODE in ('"+PkgStatus+"')";
			
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=preparedStatement.executeQuery();

			if(RS.next()){
				
				RS.beforeFirst();
				rsmd = RS.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS.getString(rsmd.getColumnName(counter)));
						System.out.println(RS.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("TITLE", RS.getString("TITLE"));									
						map.put("FIRSTNAME", RS.getString("FIRSTNAME"));
						map.put("LASTNAME", RS.getString("LASTNAME"));
						map.put("CLI", RS.getString("CLI"));
						map.put("CUSTOMERNUMBER", RS.getString("CUSTOMERNUMBER"));
						map.put("ACCOUTNNUMBER", RS.getString("ACCOUNTNUMBER"));
						map.put("PSPSTATUSCODE", RS.getString("PSPSTATUSCODE"));
						map.put("BRAND_NAME", RS.getString("BRAND_NAME"));
						map.put("BRAND_CODE", RS.getString("BRAND_CODE"));
						map.put("BRAND_ID", RS.getString("BRAND_ID"));						
					}									
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(CRMcon);
		}

		return map;

	}
	

	
	/**Description	: To Retrieve all Customer Details
	 * 
	 * 				--------------------------MAP Contains--------------------
	 * 
	 * 				TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE
	 * 				SECURITYQUESTNTEXT,SECURITYANSWERTEXT,ONLINEUSERNAME,ONLINEPASSWORD,MARKETINGPREFEMAILFLAG,MARKETINGPREFLETTERFLAG,
	 *    			MARKETINGPREFSMSFLAG,MARKETINGPREFVOICEFLAG,BIRTHDATE,GENDERCODE
	 * @param CLI
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Map<String,String> RetrieveAddressDetails(String CLI,String Account)throws Exception{

		Map<String, String> map = new HashMap<String, String>();
		String Str_Query = "";
		String Str_CustomerNumber="";
		Connection CRMcon = null;
		Connection OMPcon = null;
		Connection SVcon  = null;
		PreparedStatement preparedStatement =null;
		ResultSet RS = null;
		ResultSet RS1 = null;
		ResultSet RS2 = null;
		int columnCount = 0;
		ResultSetMetaData rsmd;
		
		
		try{
			CRMcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);
			SVcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.SV_DBIP + ":" + LoadEnvironment.SV_DBPORT + ":" + LoadEnvironment.SV_DBNAME, LoadEnvironment.SV_DBUSERNAME, LoadEnvironment.SV_DBPASSWORD);
			OMPcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.OMP_DBIP + ":" + LoadEnvironment.OMP_DBPORT + ":" + LoadEnvironment.OMP_DBNAME, LoadEnvironment.OMP_DBUSERNAME, LoadEnvironment.OMP_DBPASSWORD);

			/*
			 * INSTALLATION ADDRESS
			 * Get ADDRESS1TEXT,ADDRESS2TEXT,CITYNAME,LOCALITYNAME,STATEPROVINCENAME,COUNTRYISOCODE,POSTALCODETEXT
			 */

			Str_Query = "select ADDRESS1TEXT,ADDRESS2TEXT,CITYNAME,LOCALITYNAME,STATEPROVINCENAME,COUNTRYISOCODE,POSTALCODETEXT from CCSOWNER.postaladdress where CONTACTINFOID in (select installationaddressid from CBLOWNER.portfoliosalespackage where cli=('"+CLI+"'))";
			System.out.println(Str_Query);
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=preparedStatement.executeQuery();
			
			if(RS.next()){
				
				RS.beforeFirst();
				rsmd = RS.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS.getString(rsmd.getColumnName(counter)));
						System.out.println(RS.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						map.put("Installation_ADDRESS1TEXT", RS.getString("ADDRESS1TEXT"));				
						map.put("Installation_ADDRESS2TEXT", RS.getString("ADDRESS2TEXT"));
						map.put("Installation_CITYNAME", RS.getString("CITYNAME"));
						map.put("Installation_LOCALITYNAME", RS.getString("LOCALITYNAME"));
						map.put("Installation_STATEPROVINCENAME", RS.getString("STATEPROVINCENAME"));
						map.put("Installation_COUNTRYISOCODE", RS.getString("COUNTRYISOCODE"));
						map.put("Installation_POSTALCODETEXT", RS.getString("POSTALCODETEXT"));	
						
					}									
			}


			/*
			 * BILLING ADDRESS
			 * Get LINE_1,DECODE(LINE_2, NULL,'-1',LINE_2) as LINE_2,SUBURB,CITY,POST_CODE
			 */

			Str_Query = "select LINE_1,DECODE(LINE_2, NULL,'-1',LINE_2) as LINE_2,SUBURB,CITY,POST_CODE from "+LoadEnvironment.SV_DBSCHEMA+".address_history ah "
					+ " where ah.address_id in "
					+ "	(select cnh.postal_address_id from "+LoadEnvironment.SV_DBSCHEMA+".customer_node_history cnh where cnh.customer_node_id in "
					+ " (select a.customer_node_id from "+LoadEnvironment.SV_DBSCHEMA+".account a where a.account_name='"+Account+"')) "
					+ " and SYSDATE between EFFECTIVE_START_DATE and EFFECTIVE_END_DATE" ;
			System.out.println(Str_Query);
			preparedStatement=SVcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS1=preparedStatement.executeQuery();
			
			if(RS1.next()){
				
				RS1.beforeFirst();
				rsmd = RS1.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS1.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS1.getString(rsmd.getColumnName(counter)));
						System.out.println(RS1.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						map.put("Billing_LINE_1", RS1.getString("LINE_1"));				
						map.put("Billing_LINE_2", RS1.getString("LINE_2"));
						map.put("Billing_SUBURB", RS1.getString("SUBURB"));
						map.put("Billing_CITY", RS1.getString("CITY"));
						map.put("Billing_POST_CODE", RS1.getString("POST_CODE"));												
					}								
			}
			/*
			 * CORRESPONDENCE ADDRESS
			 * Get ADDRESS1TEXT,ADDRESS2TEXT,CITYNAME,LOCALITYNAME,STATEPROVINCENAME,COUNTRYISOCODE,POSTALCODETEXT
			 */
			DbUtilities DbU = new DbUtilities(Report);
			CustomerBean CB = new CustomerBean();
			
			DbU.getCustomerDetails(CLI, CB);
			Str_CustomerNumber = CB.getCustomerNumber();

			Str_Query = "select ADDRESS1TEXT,ADDRESS2TEXT,CITYNAME,LOCALITYNAME,STATEPROVINCENAME,COUNTRYISOCODE,POSTALCODETEXT from CCSOWNER.postaladdress where CONTACTINFOID in (select contactinfoid from ccsowner.PARTYCONTACTINFO where PARTYID in "
					+ " ( select partyid from CBLOWNER.V_CUSTOMER_SEARCH where CUSTOMERNUMBER='"+Str_CustomerNumber+"'))";
			System.out.println(Str_Query);
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS2=preparedStatement.executeQuery();
			
			if(RS2.next()){
				
				RS2.beforeFirst();
				rsmd = RS2.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS2.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS2.getString(rsmd.getColumnName(counter)));
						System.out.println(RS2.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("Correspondence_ADDRESS1TEXT", RS2.getString("ADDRESS1TEXT"));
						map.put("Correspondence_ADDRESS2TEXT", RS2.getString("ADDRESS2TEXT"));
						map.put("Correspondence_CITYNAME", RS2.getString("CITYNAME"));
						map.put("Correspondence_LOCALITYNAME", RS2.getString("LOCALITYNAME"));
						map.put("Correspondence_STATEPROVINCENAME", RS2.getString("STATEPROVINCENAME"));
						map.put("Correspondence_COUNTRYISOCODE", RS2.getString("COUNTRYISOCODE"));
						map.put("Correspondence_POSTALCODETEXT", RS2.getString("POSTALCODETEXT"));
						
					}
						
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(CRMcon);
			ConnectionFactory.closeConnection(OMPcon);
			ConnectionFactory.closeConnection(SVcon);
			System.out.println(" sv connection closed");
		}

		return map;

	}
	
	


	public String Get_CLI_Based_On_Status(CustomerSearchDB customerSearchDB,String SearchString,Status status) throws Exception{

		String CLI = "";
		String Str_Query ="";
		try{

			switch(customerSearchDB){
			case ACCOUNTNUMBER:
				Str_Query = "select cli from cblowner.v_customer_search where ACCOUNTNUMBER in ('"+SearchString+"') and pspstatuscode in ('"+status.name().toString()+"')";
				break;
			case CLI:
				Str_Query = "select cli from cblowner.v_customer_search where CLI in ('"+SearchString+"') and pspstatuscode in ('"+status.name().toString()+"')";
				break;
			case CUSTOMERNUMBER:
				Str_Query = "select cli from cblowner.v_customer_search where CUSTOMERNUMBER in ('"+SearchString+"') and pspstatuscode in ('"+status.name().toString()+"')";
				break;
			case FIRSTNAME:
				Str_Query = "select cli from cblowner.v_customer_search where FIRSTNAME in ('"+SearchString+"') and pspstatuscode in ('"+status.name().toString()+"')";
				break;
			case LASTNAME:
				Str_Query = "select cli from cblowner.v_customer_search where LASTNAME in ('"+SearchString+"') and pspstatuscode in ('"+status.name().toString()+"')";
				break;
			case POSTCODE:
				Str_Query = "select cli from cblowner.v_customer_search where POSTCODE in ('"+SearchString+"') and pspstatuscode in ('"+status.name().toString()+"')";
				break;
			default:
				break;


			}

			Connection con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);
			PreparedStatement preparedStatement=con.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet RS=preparedStatement.executeQuery();
			if(RS.next()){
				RS.beforeFirst();

				while(RS.next()){
					CLI += ","+RS.getString("CLI");
				}

			}

		}catch(Exception e){

		}
		return CLI.replaceAll(",$", "").replaceAll("^,", "");
	}

	/**Description	: To Retrieve all mobile Details
	 * 
	 * 				--------------------------MAP Contains--------------------
	 * 
	 * 				TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE
	 * 				SECURITYQUESTNTEXT,SECURITYANSWERTEXT,ONLINEUSERNAME,ONLINEPASSWORD,MARKETINGPREFEMAILFLAG,MARKETINGPREFLETTERFLAG,
	 *    			MARKETINGPREFSMSFLAG,MARKETINGPREFVOICEFLAG,BIRTHDATE,GENDERCODE
	 * @param CLI
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Map<String,String> RetrieveMobileDetails(String CLI)throws Exception{

		Map<String, String> map = new HashMap<String, String>();
		String Str_Query = "";	
		Connection CRMcon = null;
		Connection OMPcon = null;
		Connection SVcon  = null;
		PreparedStatement preparedStatement =null;
		ResultSet RS = null;
		ResultSet RS1 = null;
		ResultSet RS2 = null;
		int columnCount = 0;
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd1;
		ResultSetMetaData rsmd2;
		
		try{
			CRMcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);

			/*
			 * Get Mobile number and mobile account
			 */

			Str_Query = "select o.mobileaccountid,o.mobilenumber from cblowner.v_mobile_acc_subscribe_details o inner join cblowner.v_customer_search c on o.CUSTOMERNUMBER=c.CUSTOMERNUMBER and c.CLI in ('"+CLI+"') "; 
			System.out.println("Query is" + Str_Query);
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=preparedStatement.executeQuery();
			System.out.println("Executed");
//			System.out.println("resultset"+RS.next());

			if(RS.next()){
				
				RS.beforeFirst();
				rsmd = RS.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				
				while(RS.next())
					
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS.getString(rsmd.getColumnName(counter)));
						System.out.println(RS.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						System.out.println("MOBILENUMBER"+RS.getString("MOBILENUMBER"));
						
						map.put("MOBILENUMBER", RS.getString("MOBILENUMBER"));									
						map.put("MOBILEACCOUNTID", RS.getString("MOBILEACCOUNTID"));					
					}									
			}else{
				
				System.out.println("DAta not fetched");
				map.put("MOBILENUMBER", "");									
				map.put("MOBILEACCOUNTID", "");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(CRMcon);
		}

		return map;
	}
	
	public void VerifyInteractionHistory(String Str_CLI, String OffereingName,
			String HistoryComments,String Summary) throws Exception {
		String query = "";
		ResultSet rs = null;
		int PassFlag = 0;
		String DateNow = Reusables.getdateFormat("dd-MMM-yy", 0);

		System.out.println("DateNow"+DateNow);

		try {
			con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"
					+ LoadEnvironment.CRM_DBIP + ":"
					+ LoadEnvironment.CRM_DBPORT + ":"
					+ LoadEnvironment.CRM_DBNAME,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
			query = "select e.CREATEDATE,e.interactiondesc, ec.COMMENTTEXT from CCSOWNER.ebcinteraction e inner join ccsowner.eicomment ec on "
					+ "ec.ebcinteractionid = e.id where e.offeringname like '%"+OffereingName+"%' and e.INTERACTIONDESC like '%"+Summary+"%' and "
					+ "e.partyroleid in "
					+ "(select pr.id from CCSOWNER.partyrole pr where pr.partyid in "
					+ "(select par.partyid from CCSOWNER.partyaccountrole par where par.accountid in "
					+ "(select psp.accountid from CBLOWNER.portfoliosalespackage psp where psp.cli='"
					+ Str_CLI + "'))) order by createdate desc";

			stm = con.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery();
			System.out.println(query);

			if (rs.next()) 
				rs.beforeFirst();
			else
				throw new RuntimeException("No Records");

			while (rs.next()) {
				System.out.println("IN INTERACTION HISTORYVERIFICATION ");

					System.out.println("HistoryComments"+rs.getString("COMMENTTEXT"));
					System.out.println("Summary"+rs.getString("interactiondesc"));

					if(!HistoryComments.equalsIgnoreCase("")){
						if (rs.getString("COMMENTTEXT").contains(
								HistoryComments)
								||rs.getString("COMMENTTEXT").matches(
										HistoryComments)) {
							PassFlag = 1;
							break;
						} else {
							PassFlag = 0;
						}
					}
					if(!Summary.equalsIgnoreCase("")){
						System.out.println(rs.getString("interactiondesc"));
						if (rs.getString("interactiondesc").contains(
								Summary)
								||rs.getString("interactiondesc").matches(
										Summary)) {
							PassFlag = 1;
							break;
						} else {
							PassFlag = 0;
						}
					}

			}
			if (PassFlag == 1) {
				Report.fnReportPass("Interaction history with "
						+ HistoryComments + " for CLI " + Str_CLI);
			} else {
				Report.fnReportFail("Interaction is not updated correctly: but  updated with "
						+ HistoryComments
						+ "but with "
						+ rs.getString("COMMENTTEXT")
						+ " for CLI "
						+ Str_CLI
						+ "Creation Date was "
						+ rs.getString("CREATEDATE"));

			}


		} catch (RuntimeException e) {
			if (e.getMessage().equals("No Records")) {

				Report.fnReportFail("No Records "
						+ Str_CLI);
			}

		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}
 
	/**Description	: To Retrieve NominatedUser
	 * 
	 * 				--------------------------MAP Contains--------------------
	 * 
	 * 				TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE
	 * 				SECURITYQUESTNTEXT,SECURITYANSWERTEXT,ONLINEUSERNAME,ONLINEPASSWORD,MARKETINGPREFEMAILFLAG,MARKETINGPREFLETTERFLAG,
	 *    			MARKETINGPREFSMSFLAG,MARKETINGPREFVOICEFLAG,BIRTHDATE,GENDERCODE
	 * @param CLI
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Map<String,String> RetrieveNominatedUser()throws Exception{

		Map<String, String> map = new HashMap<String, String>();
		String Str_Query = "";	
		Connection CRMcon = null;
		Connection OMPcon = null;
		Connection SVcon  = null;
		PreparedStatement preparedStatement =null;
		ResultSet RS = null;
		ResultSet RS1 = null;
		ResultSet RS2 = null;
		int columnCount = 0;
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd1;
		ResultSetMetaData rsmd2;
		String ranID = null;

		ArrayList<String>  PARTYROLEIDList = new ArrayList<>();


		try{
			CRMcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);

			/*
			 * Get Nominated user details
			 */

			Str_Query = "Select PARTYROLEID from cblowner.NOMINATEDUSER WHERE VBSTATUS is null "; 
			System.out.println("Query is" + Str_Query);
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=preparedStatement.executeQuery();
			System.out.println("Executed");

			int count=0;
			while(RS.next()) {
			if(count>996){
				break;
			}else{
				count++;
			}

			PARTYROLEIDList.add(RS.getString("PARTYROLEID"));

			}

				System.out.println("PARTYROLEIDList.size()"+PARTYROLEIDList.size());

				Random ran = new Random();
				int i = ran.nextInt(PARTYROLEIDList.size());
				if(i == PARTYROLEIDList.size())
				{
					i = i-1;
				}
				//System.out.println("random number - "+i);
				System.out.println("COUNT of Testdata Retrived - "+PARTYROLEIDList.size());
				ranID = PARTYROLEIDList.get(i).toString();

				System.out.println("Final Random CLI and Account Number -"+ranID);

				String PARTYROLEID=PARTYROLEIDList.get(i).toString();


			/*
			 * Get Nominated user details
			 * USERNAME,PASSWORD,SECURITYQUESTNTEXT,SECURITYANSWERTEXT,VBSTATUS as NotInterestedNow,NotInterestedEver
			 */

			Str_Query = "Select CUSTOMERNUMBER from CBLOWNER.V_CUSTOMER_FULL WHERE NOMINATEDUSERID ='"+PARTYROLEID+"' "; 
			System.out.println("Query is" + Str_Query);
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS1=preparedStatement.executeQuery();
			System.out.println("Executed");


				if(RS1.next()){

					map.put("CUSTOMERNUMBER", RS1.getString("CUSTOMERNUMBER"));	
                    String CUSTOMERNUMBER=RS1.getString("CUSTOMERNUMBER");
			        System.out.println("CUSTOMERNUMBER" +CUSTOMERNUMBER);
				}

			/*
			 * Get Nominated user Customer details
			 * CLI and account
			 */

			Str_Query = "select CLI,ACCOUNTNUMBER from CBLOWNER.v_customer_search where CUSTOMERNUMBER = '"+RS1.getString("CUSTOMERNUMBER")+"' "; 
			System.out.println("Query is" + Str_Query);
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS2=preparedStatement.executeQuery();
			System.out.println("Executed");

			if(RS2.next()){

				RS2.beforeFirst();
				rsmd = RS2.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);

				while(RS2.next())

					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS2.getString(rsmd.getColumnName(counter)));
						System.out.println(RS2.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));

						map.put("ACCOUNTNUMBER", RS2.getString("ACCOUNTNUMBER"));							
						map.put("CLI", RS2.getString("CLI"));					

					}									
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(CRMcon);
		}

		return map;
	}
	
	@SuppressWarnings("unused")
	public Map<String,String> MultiplePkg(String PkgType,String PkgStatus1,String Enddate1,String ActDate1,String PkgStatus2,String Enddate2,String ActDate2)throws Exception{

		Map<String, String> map = new HashMap<String, String>();
		String Str_Query = "";	
		Connection CRMcon = null;
		Connection OMPcon = null;
		Connection SVcon  = null;
		PreparedStatement preparedStatement =null;
		ResultSet RS = null;
		ResultSet RS1 = null;
		ResultSet RS2 = null;
		int columnCount = 0;
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd1;
		ResultSetMetaData rsmd2;
		
		try{
			CRMcon = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+ LoadEnvironment.CRM_DBIP + ":" + LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME, LoadEnvironment.CRM_DBUSERNAME, LoadEnvironment.CRM_DBPASSWORD);	

			if (PkgType.equalsIgnoreCase("Multiple")){
			/*
			 * Get ACCOUNTNUMBER oF Multiple pkg
			 */
			
			Str_Query = "select accountnumber,count(accountnumber) from cblowner.v_customer_search where accountnumber in "
					+ " (select accountnumber from cblowner.v_customer_search v where v.pspstatuscode='"+PkgStatus1+"' and cli in (select cli from cblowner.portfoliosalespackage where enddate is "+Enddate1+" and activationdate is "+ActDate1+"))"
					+ " and accountnumber in (select accountnumber from cblowner.v_customer_search v1 where v1.pspstatuscode='"+PkgStatus2+"' and cli in (select cli from cblowner.portfoliosalespackage where enddate is "+Enddate2+" and activationdate is "+ActDate2+"))group by accountnumber  having  count(accountnumber)=2";
			
			System.out.println("Query:" +Str_Query); 
			/*
			 * Get ACCOUNTNUMBER oF Single pkg
			 */
			}else{		
			Str_Query = "select accountnumber,count(accountnumber) from cblowner.v_customer_search where accountnumber in "
					+ " (select accountnumber from cblowner.v_customer_search v where v.pspstatuscode='"+PkgStatus1+"' and cli in (select cli from cblowner.portfoliosalespackage where enddate is "+Enddate1+" and activationdate is "+ActDate1+"))group by accountnumber  having  count(accountnumber)=1)";
					
			}
			
			preparedStatement=CRMcon.prepareStatement(Str_Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=preparedStatement.executeQuery();

			if(RS.next()){
				
				RS.beforeFirst();
				rsmd = RS.getMetaData();
				columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
				while(RS.next())
					for(int counter =1 ; counter <=columnCount; counter++){
						map.put(rsmd.getColumnName(counter), RS.getString(rsmd.getColumnName(counter)));
						System.out.println(RS.getString(rsmd.getColumnName(counter))+"||"+rsmd.getColumnName(counter));
						
						map.put("ACCOUTNNUMBER", RS.getString("ACCOUNTNUMBER"));
						
					}									
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(CRMcon);
		}

		return map;

	}
	
	
}

