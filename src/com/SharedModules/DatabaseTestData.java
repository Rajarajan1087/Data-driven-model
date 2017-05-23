package com.SharedModules;

import java.sql.Connection;

import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.Enumerations.Generic.DBName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.SharedModules.ConnectionFactory;


public class DatabaseTestData {
	private Reporter Report;
	private Connection con=null;
	public enum searchByDataa{
		Proposition,Bundle,Discount,blank
	}
	public DatabaseTestData(Reporter report){
		this.Report=report;
	}
	public DatabaseTestData(){

	}
	String CLI_OMP = null;
	String CLI_CRM = null;
	String CLI_SV=null;
	String CLI_CLI = null;
	String CLI_final = null;
	String CLI_OMP2= null;
	String Customer_numbers = "";
	String Customer_numbers1 = "";
	String Customer_numbers2 = "";
	String Customer_numbers3 = "";
	String Account_Array ="";
	String CLI_Array = "";
	String CLI_Array1 = "";
	String Account_Array1 ="";
	String Account_Array2 = "";

	String Data[][] = new String[1000][2];

	public String getDataNEW(String packageID, searchByDataa search, String searchValue, boolean inContract, boolean creditClassCheck) {
		System.out.println("Fetching Data from CRM");
		searchValue=searchValue.replace(",", "','");
		packageID=packageID.replace(",", "','");
		CLI_CRM = getCLI_CRM(packageID,search,searchValue,inContract);

		if(CLI_CRM!=null){
			System.out.println("Fetching Data from OMP");
			CLI_OMP  = getCLI_OMP(CLI_CRM);

		}else{
			if(Report!=null)
			{
				System.out.println("NO DATA FROM CRM");
				Report.fnReportFailAndTerminateTest("Data", "NO DATA FROM CRM");
			}
			else{
				System.out.println("NO DATA FROM CRM");
			}
		}
		if(CLI_OMP != null){
			System.out.println("Fetching Data from CLI DB");
			CLI_CLI = getCLI_CLIDB(CLI_OMP);
		}else{
			if(Report!=null)
			{
				System.out.println("NO DATA FROM OMP");
				Report.fnReportFailAndTerminateTest("Data", "NO DATA FROM OMP");
			}
			else{
				System.out.println("NO DATA FROM OMP");
			}
		}
		if(CLI_CLI != null){
			System.out.println("Fetching Data from SV");
			CLI_final = getCLI_fineTune(CLI_CLI,creditClassCheck);
		}else{
			if(Report!=null)
			{
				System.out.println("NO DATA to fine tune");
				Report.fnReportFailAndTerminateTest("Data", "NO DATA to fine tune");
			}
			else{
				System.out.println("NO DATA to fine tune");
			}
		}
		if(CLI_final.equals(""))
		{
			if(Report!=null)
			{
				System.out.println("NO DATA from SV");
				Report.fnReportFailAndTerminateTest("Data", "NO DATA from SV");
			}
			else{
				System.out.println("NO DATA from SV");
			}
		}
		return CLI_final;
	}


	public String getDataNEW1(String Customer,String packageID) throws Exception {

		packageID=packageID.replace(",", "','");
		String query="";
		DatabaseTestData dbu=new DatabaseTestData();
		DbUtilities DBU=new DbUtilities(Report);
		String Account_num;

		switch(Customer){
		case "Cancelled":
			query="select distinct a.cli from CBLOWNER.v_customer_search a,"
					+ " cblowner.portfoliosalespackage P where P.cli=a.cli and p.packageid='"+packageID+"' "
					+ "and a.PSPSTATUSCODE='CANCELLED' and not a.cli like '%HM' "
					+ "and not a.cli like '%NL' AND p.activityid='26' and ROWNUM<2 ";

			CLI_CRM=DBU.DBConnect(query, DBName.CRM,"CLI");

			break;
		case "Disconnected":
			query="select distinct P.cli from CBLOWNER.v_customer_search a,"
					+ " cblowner.portfoliosalespackage P where P.cli=a.cli and p.packageid='"+packageID+"' "
					+ "and a.PSPSTATUSCODE='DISCONNECTED' and not a.cli like '%HM' "
					+ "and not a.cli like '%NL' and ROWNUM<2";

			CLI_CRM=DBU.DBConnect(query, DBName.CRM, "CLI");
			break;
		case "Incollection":
			query=" select ACCOUNT_NAME from "+LoadEnvironment.SV_DBSCHEMA+".account a, "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array nda where nda.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID" +
					" and nda.derived_attribute_id = '12000066'" +
					" AND SYSDATE BETWEEN nda.EFFECTIVE_START_DATE AND nda.EFFECTIVE_END_dATE and nda.INDEX2_VALUE = '1' and ROWNUM<2";
			Account_num=DBU.DBConnect(query, DBName.SV, "ACCOUNT_NAME");
			CLI_CRM=dbu.getclifromaccount1(Account_num);
			break;
		case "DCA_Jefersson":
			Account_num=dbu.getDCACustomer(31);
			CLI_CRM=dbu.getclifromaccount1(Account_num);
			break;
		case "DCA_Lowell":
			Account_num=dbu.getDCACustomer(32);
			CLI_CRM=dbu.getclifromaccount1(Account_num);
			break;
		case "multipleCli":
			CLI_CRM=dbu.getMulticlidata();
			break;
		case "Home_move_inflight":
			CLI_CRM=dbu.getMulticli_withoneHM();
			break;
		case "inflight":
			query="select distinct a.cli from CBLOWNER.v_customer_search a,"
					+ " cblowner.portfoliosalespackage P where P.cli=a.cli and p.packageid='"+packageID+"' "
					+ "and P.activationdate is null and P.disconnectiondate is null and P.enddate is null";
			CLI_CRM=DBU.DBConnect(query, DBName.CRM,"CLI");
			break;
		}
		return CLI_CRM;
	}
	public String getMulticli_withoneHM()throws Exception{
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
			CLI=
					DBU.DBConnect(query, DBName.CRM, "cli");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return CLI;
	}
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
			CLI=
					DBU.DBConnect(query, DBName.CRM,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return CLI;
	}
	private String getDCACustomer(int id)throws Exception{
		String Account_number="";
		String query=null;
		ResultSet rs = null;
		DbUtilities DBU=new DbUtilities(Report);
		try {
			query="select ACCOUNT_NAME from "+LoadEnvironment.SV_DBSCHEMA+".account a, "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array nda where nda.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID "
					+" and nda.derived_attribute_id = '12000066' "
					+" AND SYSDATE BETWEEN nda.EFFECTIVE_START_DATE AND nda.EFFECTIVE_END_dATE and nda.index6_value="+id+" and ROWNUM<2 ";
			//System.out.println(query);
			Account_number=DBU.DBConnect(query, DBName.SV, "ACCOUNT_NAME");
		} catch (Exception e) {
			// TODO: handle exception
		}
		//System.out.println(Account_number);
		return Account_number;
	}
	private String getincollectionaccount_num() throws Exception{
		String Acc_num=null,query;
		ResultSet rs = null;
		//System.out.println("out");
		try {
			//System.out.println("inside");
			query=" select ACCOUNT_NAME from "+LoadEnvironment.SV_DBSCHEMA+".account a, "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array nda where nda.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID" +
					" and nda.derived_attribute_id = '12000066'" +
					" AND SYSDATE BETWEEN nda.EFFECTIVE_START_DATE AND nda.EFFECTIVE_END_dATE and nda.INDEX2_VALUE = '1' and ROWNUM<40;";
			//System.out.println(query);
			DbUtilities DBU=new DbUtilities(Report);
			Acc_num=DBU.DBConnect(query, DBName.SV, "ACCOUNT_NAME");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Acc_num;
	}
	private String getclifromaccount1(String Account_num)throws Exception{
		String CLI="";
		String query="";
		ResultSet rs=null;
		DbUtilities DBU=new DbUtilities(Report);
		try {
			query="select cli from CBLOWNER.v_customer_search where accountnumber='"+Account_num+"' and not cli like '%HM' and not cli like '%NL'";
			//System.out.println(query);
			CLI=DBU.DBConnect(query, DBName.CRM,"cli");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return CLI;
	}
	private String getCLI_fineTune(String CLI_OMP2,boolean creditClassCheck) {

		String query3 = null; 
		ResultSet rs1 = null;
		PreparedStatement stm =null;
		String ranCLIAccNumber="";

		try{

			int count=0;

			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":" +LoadEnvironment.CRM_DBNAME;
			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
			CLI_OMP2=CLI_OMP2.replace(",", "','");
			query3 = "select CUSTOMERNUMBER from CBLOWNER.v_customer_search where CLI in ('"+CLI_OMP2+"')";
			//System.out.println(query3);
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();
			count=0;
			while(rs1.next()){
				if(count>996){
					//System.out.println("--------------------"+count+"--------------------");
					break;
				}else{
					//System.out.println("--------------------"+count+"--------------------");
					count++;
				}
				Customer_numbers = Customer_numbers+rs1.getString("CUSTOMERNUMBER")+",";
			}





			Customer_numbers=Customer_numbers.replace(",", "','");	
			query3 = "select column_value CUSTOMERNUMBER from table(sys.odcivarchar2list('"+Customer_numbers+"'))"
					+ " Minus"
					+ " select CUSTOMERNUMBER from CBLOWNER.bar where CUSTOMERNUMBER in ('"+Customer_numbers+"')";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();
			count=0;
			while(rs1.next()){
				if(count>996){
					break;
				}else{
					count++;
				}
				Customer_numbers1 = Customer_numbers1+rs1.getString("CUSTOMERNUMBER")+",";
				count++;
			}

			//	System.out.println("CIUSTOMER_NUMBERS1"+Customer_numbers1);

			Customer_numbers1=Customer_numbers1.replace(",", "','");	
			query3 = "select column_value CUSTOMERNUMBER from table(sys.odcivarchar2list('"+Customer_numbers1+"'))"
					+ " Minus"
					+ " select CUSTOMERNUMBER from CBLOWNER.fraud f where f.CUSTOMERNUMBER in ('"+Customer_numbers1+"')";
			//	System.out.println(query3);
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();
			count=0;
			while(rs1.next()){
				if(count>996){
					break;
				}else{
					count++;
				}
				Customer_numbers2 = Customer_numbers2+rs1.getString("CUSTOMERNUMBER")+",";
			}

			//	System.out.println("Customer_numbers2"+Customer_numbers2);


			Customer_numbers2=Customer_numbers2.replace(",", "','");	
			query3 = "select customernumber from CBLOWNER.v_customer_search"
					+ " where customernumber in ('"
					+ Customer_numbers2+"')"
					+ "group by customernumber having count(*) = 1";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();
			count=0;
			while(rs1.next()){
				if(count>996){
					break;
				}else{
					count++;
				}
				Customer_numbers3 = Customer_numbers3+rs1.getString("customernumber")+",";
			}
			//	System.out.println("Customer_numbers3"+Customer_numbers3);

			Customer_numbers3=Customer_numbers3.replace(",", "','");
			query3 = "select CLI from  CBLOWNER.v_customer_search where CUSTOMERNUMBER in ('"+Customer_numbers3+"')";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();
			count=0;
			while(rs1.next()){
				if(count>996){
					break;
				}else{
					count++;
				}
				CLI_Array = CLI_Array+rs1.getString("CLI")+",";
			}

			CLI_Array=CLI_Array.replace(",", "','");	
			query3 = "select CLI from CBLOWNER.portfoliosalespackage"
					+ " where CLI in ('"
					+ CLI_Array+"')"
					+ "group by CLI having count(*) = 1";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();

			while(rs1.next()){
				CLI_Array1 = CLI_Array1+rs1.getString("CLI")+",";
			}



			//	System.out.println("CLI_Array1"+CLI_Array1);


			CLI_Array1=CLI_Array1.replace(",", "','");	
			query3 = "select ACCOUNTNUMBER from CBLOWNER.v_customer_search where CLI in ('"+CLI_Array1+"')";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();

			while(rs1.next()){
				Account_Array = Account_Array+rs1.getString("ACCOUNTNUMBER")+",";
			}

			//	System.out.println("Account_Array  "+Account_Array);


			if(creditClassCheck){
				System.out.println("-------------------------------in Credit Check--------------------------------------------");
				String Acc = ZeroBalCustomet(Account_Array);
				Acc = CollectionFreeCustomer(Acc);

				Account_Array=Acc;				
			}






			Account_Array=Account_Array.replace(",", "','");	
			query3 = "select  ACCOUNTNUMBER from CBLOWNER.v_customer_search"
					+ " where ACCOUNTNUMBER in ('"
					+ Account_Array+"')"
					+ "group by ACCOUNTNUMBER having count(*) = 1";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();

			while(rs1.next()){
				if(!rs1.getString("ACCOUNTNUMBER").equalsIgnoreCase("")||rs1.getString("ACCOUNTNUMBER")!=null){
					Account_Array1 = Account_Array1+rs1.getString("ACCOUNTNUMBER")+",";
				}
			}
			//	System.out.println("Account_Array1"+Account_Array1);

			Account_Array1=Account_Array1.replace(",", "','");	
			query3 = "select CLI,ACCOUNTNUMBER from CBLOWNER.v_customer_search where ACCOUNTNUMBER in ('"+ Account_Array1+"')";
			//	System.out.println("query3 - "+query3);
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery(); 
			ArrayList<String> cliList = new ArrayList<>();
			ArrayList<String> accList = new ArrayList<>();
			while(rs1.next()){
				//				System.out.println("CLI \t "+rs1.getString("CLI")+"\t Account\t"+rs1.getString("ACCOUNTNUMBER"));
				//System.out.println(rs1.getString("CLI"));
				cliList.add(rs1.getString("CLI"));
				accList.add(rs1.getString("ACCOUNTNUMBER"));
			}

			//Getting random CLI

			Random ran = new Random();
			int i = ran.nextInt(cliList.size());
			if(i == cliList.size())
			{
				i = i-1;
			}

			//			System.out.println("random number - "+i);
			System.out.println("Array List Size - "+cliList.size());
			ranCLIAccNumber = cliList.get(i).toString()+","+accList.get(i).toString();
			//			System.out.println("Final Random CLI and Account Number -"+ranCLIAccNumber);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}


		return ranCLIAccNumber;
	}


	public void Update_CreditClassCreditTier(String Str_Account,String Str_CreditClass,String Str_CreditTier) throws Exception{

		String query = null;
		PreparedStatement stm = null;
		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT+":"+LoadEnvironment.SV_DBNAME;
		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
		try{
			query = "UPDATE "+LoadEnvironment.SV_DBSCHEMA+".customer_node_history CNH"
					+ " SET CNH.CREDIT_RATING_CODE = (SELECT REFERENCE_CODE FROM "+LoadEnvironment.SV_DBSCHEMA+".REFERENCE_CODE"
					+ " WHERE REFERENCE_TYPE_ID = 9000173  AND ABBREVIATION='"+Str_CreditClass+"')"
					+ " WHERE CNH.EFFECTIVE_END_DATE > SYSDATE and CNH.CUSTOMER_NODE_ID in"
					+ " (select acc.CUSTOMER_NODE_ID from "+LoadEnvironment.SV_DBSCHEMA+".account acc where acc.account_name ='"+Str_Account+"')";
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stm.executeUpdate();
			con.commit();

			query = "UPDATE "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array"
					+ " SET INDEX1_VALUE = "+Str_CreditTier +" "
					+ " WHERE derived_attribute_id = 23000140"
					+ " AND SYSDATE BETWEEN EFFECTIVE_START_DATE AND EFFECTIVE_END_dATE"
					+ "  --and INDEX2_VALUE = 1"
					+ " AND CUSTOMER_NODE_ID  in"
					+ " (Select acc.CUSTOMER_NODE_ID from "+LoadEnvironment.SV_DBSCHEMA+".ACCOUNT acc where acc.account_name = '"+Str_Account+"')";
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stm.executeUpdate();
			con.commit();
		}finally{
			ConnectionFactory.closeConnection(con);
		}
	}


	public String ZeroBalCustomet(String Account_Array) throws Exception{
		System.out.println("-------------------------------in Zero Balance Check--------------------------------------------");
		//		System.out.println("IN ZERO BAL ---- > "+Account_Array);
		Account_Array=Account_Array.replace(",", "','");	
		String query = null;
		PreparedStatement stm = null;
		ResultSet RS = null;
		String AccountNew= "";
		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT+":"+LoadEnvironment.SV_DBNAME;
		Connection con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
		try{
			query =  " select ACCOUNT_NAME from "+LoadEnvironment.SV_DBSCHEMA+".account where ACCOUNT_BALANCE <= 0 and"
					+ "  ACCOUNT_NAME in ('"+Account_Array+"')";
			//System.out.println(query);
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				//	System.out.println("Account--> "+RS.getString("ACCOUNT_NAME")+",");
				AccountNew= AccountNew+RS.getString("ACCOUNT_NAME")+",";
			}

		}catch(Exception e){

		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return AccountNew;
	}

	public String CollectionFreeCustomer(String Account_Array) throws Exception{
		System.out.println("-------------------------------in Zero Balance Check--------------------------------------------");
		//		System.out.println("IN ZERO BAL ---- > "+Account_Array);
		Account_Array=Account_Array.replace(",", "','");	
		String query = null;
		PreparedStatement stm = null;
		ResultSet RS = null;
		String AccountNew= "";
		String customer_node_id_new="";
		String custnodeid_collfree=""; 
		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT+":"+LoadEnvironment.SV_DBNAME;
		Connection con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
		try{
			String customer_node_id="Select acc.CUSTOMER_NODE_ID from "+LoadEnvironment.SV_DBSCHEMA+".ACCOUNT acc where acc.account_name in ('"+Account_Array+"')";

			stm=con.prepareStatement(customer_node_id,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				//	System.out.println("CUSTOMER_NODE_ID--> "+RS.getString("CUSTOMER_NODE_ID")+",");
				customer_node_id= RS.getString("CUSTOMER_NODE_ID").replace(",","");
				customer_node_id_new= customer_node_id_new+customer_node_id+",";
			}
			customer_node_id_new = customer_node_id_new.replace(",","','");
			query = "select * from "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array where derived_attribute_id = 12000066"
					+ " AND SYSDATE BETWEEN EFFECTIVE_START_DATE AND EFFECTIVE_END_dATE"
					+ " AND CUSTOMER_NODE_ID in" 
					+"('"+customer_node_id_new+"')";
			//			System.out.println(query);
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				//System.out.println("CUSTOMER_NODE_ID--> "+RS.getString("CUSTOMER_NODE_ID")+",");
				custnodeid_collfree= custnodeid_collfree+RS.getString("CUSTOMER_NODE_ID")+",";
			}
			custnodeid_collfree = custnodeid_collfree.replace(",","','");
			String query1= "SELECT * from "+LoadEnvironment.SV_DBSCHEMA+".account where  CUSTOMER_NODE_ID in('"+custnodeid_collfree+"')";
			//			System.out.println(query1);
			stm=con.prepareStatement(query1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				//System.out.println("Account--> "+RS.getString("ACCOUNT_NAME")+",");
				AccountNew= AccountNew+RS.getString("ACCOUNT_NAME")+",";
			}
		}catch(Exception e){

		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return AccountNew;
	}


	private String getCLI_OMP(String cLI_CRM) {
		String omp = null;
		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.OMP_DBIP+":"+LoadEnvironment.OMP_DBPORT+":"+LoadEnvironment.OMP_DBNAME;
			//	System.out.println(ConnectionURL);

			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.OMP_DBUSERNAME,LoadEnvironment.OMP_DBPASSWORD);

			String query = "select column_value CLI from table(sys.odcivarchar2list("+cLI_CRM+"))"
					+ " Minus"
					+ " select cli from "+LoadEnvironment.OMP_DBSCHEMA+".VW_CPWORDERSTATUS where ISORDERINFINALSTATE = 'N' and CLI in ("+cLI_CRM+")";
			//	System.out.println(query);
			PreparedStatement stm=con.prepareStatement(query);
			ResultSet rs=stm.executeQuery();
			int count=0;
			while(rs.next()){
				if(omp == null){
					omp=rs.getString(1);
					omp=omp+",";
				}else{

					omp=omp+rs.getString(1);
					omp=omp+",";
				}
				if(count>996){
					//					System.out.println("--------------------"+count+"--------------------");
					break;
				}else{
					//System.out.println("--------------------"+count+"--------------------");
					count++;
				}
			}
			if(omp!=null){
				omp = omp.substring(0, omp.length() - 1);
			}else{
				System.out.println("NO ACTIVE DATA FOUND in OMP");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return omp;
	}
	private String getCLI_C(String cLI_CRM) {
		String omp = null;
		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.OMP_DBIP+":"+LoadEnvironment.OMP_DBPORT+":"+LoadEnvironment.OMP_DBNAME;
			//	System.out.println(ConnectionURL);

			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.OMP_DBUSERNAME,LoadEnvironment.OMP_DBPASSWORD);

			String query = "select column_value CLI from table(sys.odcivarchar2list("+cLI_CRM+"))"
					+ " Minus"
					+ " select cli from "+LoadEnvironment.OMP_DBSCHEMA+".VW_CPWORDERSTATUS where ISORDERINFINALSTATE = 'N' and CLI in ("+cLI_CRM+")";
			//	System.out.println(query);
			PreparedStatement stm=con.prepareStatement(query);
			ResultSet rs=stm.executeQuery();
			int count=0;
			while(rs.next()){
				if(omp == null){
					omp=rs.getString(1);
					omp=omp+",";
				}else{

					omp=omp+rs.getString(1);
					omp=omp+",";
				}
				if(count>996){
					//					System.out.println("--------------------"+count+"--------------------");
					break;
				}else{
					//System.out.println("--------------------"+count+"--------------------");
					count++;
				}
			}
			if(omp!=null){
				omp = omp.substring(0, omp.length() - 1);
			}else{
				System.out.println("NO ACTIVE DATA FOUND in OMP");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return omp;
	}
	/**
	 * @param packageID
	 * @param search
	 * @param searchValue
	 * @return
	 */
	private String getCLI_CRM(String packageID, searchByDataa search,
			String searchValue,boolean inContract) {

		String CLI = null ;	
		ResultSet rs = null;
		String query1 = null; 

		switch (search){
		case Proposition:
			if(inContract){
				if(searchValue.contains("NOT")){
					searchValue = searchValue.substring(4);
					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
							+ " and psp.id = ct.portfoliosalespackageid "
							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
							+ " and psp.isvalid = 1 "
							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
							+ "	and ct.terminationdate is null "
							+ " and psp.activationdate <= sysdate-40"
							+ " MINUS "
							+ " Select P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"')"
							+ " and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
							+ " sysdate < p1.activationdate)) "
							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";

				}else {
					//					System.out.println("123");
					query1 ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P.id = ct.portfoliosalespackageid "
							+ " and P1.propositionid in ('"+searchValue+"') "
							+ "	and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
							+ " sysdate >= p1.activationdate and"
							+ " sysdate < p1.disconnectiondate)"
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and"
							+ "	p1.enddate is not null and"
							+ "	sysdate < p1.activationdate))"
							+ "	and p.activationdate is not null "
							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
							+ " and ct.terminationdate is null"
							+ " and p.activationdate BETWEEN sysdate and sysdate-25";
				}
			}else{
				if(searchValue.contains("NOT")){

					searchValue = searchValue.substring(4);
					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
							+ " and psp.isvalid = 1 "
							+ " and psp.activationdate <= sysdate-40"
							+ " MINUS "
							+ " Select P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"')"
							+ " and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
							+ " sysdate < p1.activationdate)) "
							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";

				}else {

					query1 ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"') "
							+ "	and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
							+ " sysdate >= p1.activationdate and"
							+ " sysdate < p1.disconnectiondate)"
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and"
							+ "	p1.enddate is not null and"
							+ "	sysdate < p1.activationdate))"
							+ "	and p.activationdate is not null "
							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";
				}
			}
			break;
		case Discount:
			if(inContract){
				if(searchValue.contains("NOT")){
					searchValue = searchValue.substring(4);
					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
							+ " and psp.id = ct.portfoliosalespackageid "
							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
							+ " and psp.isvalid = 1 "
							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
							+ "	and ct.terminationdate is null "
							+ " and psp.activationdate <= sysdate-40"
							+ " MINUS "
							+ " Select P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"')"
							+ " and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
							+ " sysdate < p1.activationdate)) "
							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";

				}else {

					query1 ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P.id = ct.portfoliosalespackageid "
							+ " and P1.propositionid in ('"+searchValue+"') "
							+ "	and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
							+ " sysdate >= p1.activationdate and"
							+ " sysdate < p1.disconnectiondate)"
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and"
							+ "	p1.enddate is not null and"
							+ "	sysdate < p1.activationdate))"
							+ "	and p.activationdate is not null "
							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
							+ " and ct.terminationdate is null "
							+ " and p.activationdate <= sysdate-40";
				}
			}else{
				if(searchValue.contains("NOT")){
					searchValue = searchValue.substring(4);
					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null and  psp.isvalid = 1 "
							+ " and psp.activationdate <= sysdate-40"
							+ " MINUS "
							+ " Select P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"')"
							+ " and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
							+ " sysdate < p1.activationdate)) "
							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";
				}else{

					query1 ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"') "
							+ "	and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
							+ " sysdate >= p1.activationdate and"
							+ " sysdate < p1.disconnectiondate)"
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and"
							+ "	p1.enddate is not null and"
							+ "	sysdate < p1.activationdate))"
							+ "	and p.activationdate is not null "
							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";
				}
			}
			break;
		case Bundle:
			if(inContract){
				if(searchValue.contains("NOT")){
					searchValue = searchValue.substring(4);
					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
							+ " and psp.id = ct.portfoliosalespackageid "
							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
							+ " and psp.isvalid = 1 "
							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
							+ "	and ct.terminationdate is null "
							+ " and psp.activationdate <= sysdate-40"
							+ " MINUS "
							+ " Select P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"')"
							+ " and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
							+ " sysdate < p1.activationdate)) "
							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";

				}else {

					query1 ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P.id = ct.portfoliosalespackageid "
							+ " and P1.propositionid in ('"+searchValue+"') "
							+ "	and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
							+ " sysdate >= p1.activationdate and"
							+ " sysdate < p1.disconnectiondate)"
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and"
							+ "	p1.enddate is not null and"
							+ "	sysdate < p1.activationdate))"
							+ "	and p.activationdate is not null "
							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
							+ " and ct.terminationdate is null "
							+ " and p.activationdate <= sysdate-40";
				}
			}else{
				if(searchValue.contains("NOT")){
					searchValue = searchValue.substring(4);
					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null and  psp.isvalid = 1 "
							+ " and psp.activationdate <= sysdate-40"
							+ " MINUS "
							+ " Select P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"')"
							+ " and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
							+ " sysdate < p1.activationdate)) "
							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";
				}else {

					query1 ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
							+ " where p1.portfoliosalespackageid = P.id "
							+ " and P1.propositionid in ('"+searchValue+"') "
							+ "	and p.packageid in('"+packageID+"') "
							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
							+ " sysdate >= p1.activationdate and"
							+ " sysdate < p1.disconnectiondate)"
							+ " or (p1.activationdate is not null and"
							+ " p1.disconnectiondate is not null and"
							+ "	p1.enddate is not null and"
							+ "	sysdate < p1.activationdate))"
							+ "	and p.activationdate is not null "
							+ " and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1"
							+ " and p.activationdate <= sysdate-40";
				}
			}
			break;
		case blank:
			/*query1 = "select CLI from CBLOWNER.portfoliosalespackage p where packageid in ('"+packageID+"') and"
					    + " ((p.activationdate is not null and p.disconnectiondate is null and p.enddate is null)"
						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
						+ "	sysdate >= p.activationdate and sysdate < p.disconnectiondate)"
						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
						+ "	sysdate < p.activationdate)) and rownum <=1000";*/
			if(inContract){
				query1 = "select  cli from cblowner.portfoliosalespackage psp LEFT OUTER Join CBLOWNER.contract ct on "+
						"	psp.id = ct.portfoliosalespackageid where "+
						"	packageid in('"+packageID+"') "+
						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
						"	and psp.isvalid = 1 "+
						"	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "+
						"	and ct.terminationdate is null "
						+ " and psp.activationdate <= sysdate-40"+
						"	MINUS "+
						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
						"	where p1.portfoliosalespackageid = P.id "+
						"	and p1.tariffid in ('317') "+
						"	and p.packageid in ('"+packageID+"') "+
						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "
						+ " and p.activationdate <= sysdate-40";
			}else{
				query1 = "select  cli from cblowner.portfoliosalespackage psp"+
						"	where "+
						"	packageid in('"+packageID+"') "+
						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
						"	and psp.isvalid = 1 "
						+ " and psp.activationdate <= sysdate-40"+
						"	MINUS "+
						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
						"	where p1.portfoliosalespackageid = P.id "+
						"	and p1.tariffid in ('317') "+
						"	and p.packageid in ('"+packageID+"') "+
						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "
						+ " and p.activationdate <= sysdate-40";
			}
			break;	
		}


		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
			//	System.out.println(ConnectionURL);
			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
			PreparedStatement stm=con.prepareStatement(query1);
			rs=stm.executeQuery();
			CLI = "'";
			int count = 0;
			while(rs.next()){
				CLI=CLI+rs.getString(1);
				CLI=CLI+"','";
				if(count==900)
					break;
				count++;

			}
			if(CLI.equals("'")){
				System.out.println("NO DATA IN CRM");
				CLI=null;
			}else{
				CLI = CLI.substring(0, CLI.length() - 2);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		//	System.out.println("CLI FROM CRM " + CLI);
		return CLI;
	}

	private String getCLI_CLIDB(String CLI_OMP) {
		String cli = null;
		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CLI_DBIP+":"+LoadEnvironment.CLI_DBPORT+":"+LoadEnvironment.CLI_DBNAME;
			//	System.out.println(ConnectionURL);

			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CLI_DBUSERNAME,LoadEnvironment.CLI_DBPASSWORD);
			CLI_OMP = CLI_OMP.replaceAll(",", "','");
			String query = "select resource_id from t_access_method_instance where resource_id in('"+CLI_OMP+"')"; 
			//			System.out.println(query);
			PreparedStatement stm=con.prepareStatement(query);
			ResultSet rs=stm.executeQuery();
			int count=0;
			while(rs.next()){
				if(cli == null){
					cli=rs.getString(1);
					cli=cli+",";
				}else{

					cli=cli+rs.getString(1);
					cli=cli+",";
				}
				if(count>900){
					//					System.out.println("--------------------"+count+"--------------------");
					break;
				}else{
					//System.out.println("--------------------"+count+"--------------------");
					count++;
				}
			}
			if(cli!=null){
				cli = cli.substring(0, cli.length() - 1);
			}else{
				System.out.println("NO ACTIVE DATA FOUND in CLIDB");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return cli;
	}
	/**
	 * @param cLI
	 * @return
	 */
	@SuppressWarnings("finally")
	public int getCLIValidate(String cLI) {

		ResultSet rs = null;
		int returnval = 1;
		String query1 = null; 

		query1 = "select CLI from cblowner.portfoliosalespackage where CLI in ('"+cLI+"')";

		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
			PreparedStatement stm=con.prepareStatement(query1);
			rs=stm.executeQuery();

			if(rs.next()){
				returnval = 0;
			}else{
				returnval = 1;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
			return returnval;
		}

	}

	@SuppressWarnings("finally")
	public int Get_CPWNRef(String cPWN) throws Exception {
		String query = null;
		ResultSet rs = null;
		PreparedStatement stm;
		int returnval = 1;


		con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+LoadEnvironment.SKID_DBIP+":"+LoadEnvironment.SKID_DBPORT+":"+LoadEnvironment.SKID_DBNAME,LoadEnvironment.SKID_DBUSERNAME,LoadEnvironment.SKID_DBPASSWORD);
		query ="select NK_VALUE from T_NETWORK_KEYS where NK_VALUE = '"+cPWN+"'";
		try{
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stm.executeQuery();
			if(rs.next()){
				returnval = 0;
			}else{
				returnval = 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
			//			System.out.println("CPWN REF is -->"+ cPWN);
			return returnval;
		}
	}

	@SuppressWarnings("finally")
	public String GetFrauddata() throws Exception {
		String query = null;
		ResultSet rs = null;
		PreparedStatement stm;
		String ranCLIAccNumber = "";

		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":" +LoadEnvironment.CRM_DBNAME;
		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
		query ="select distinct  cli,accountnumber from cblowner.v_customer_search where pspstatuscode='ACTIVE' and customernumber in (select customernumber from cblowner.fraud where status like '%Confirmed%' and rownum <400)";
		try{
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stm.executeQuery();
			ArrayList<String> cliList = new ArrayList<>();
			ArrayList<String> accList = new ArrayList<>();

			while(rs.next()){
				//				System.out.println("CLI \t "+rs.getString("CLI")+"\t Account\t"+rs.getString("ACCOUNTNUMBER"));
				//System.out.println(rs1.getString("CLI"));
				cliList.add(rs.getString("CLI"));
				accList.add(rs.getString("ACCOUNTNUMBER"));
			}

			//Getting random CLI
			Random ran = new Random();
			int i = ran.nextInt(cliList.size());
			if(i == cliList.size())
			{
				i = i-1;
			}
			//			System.out.println("random number - "+i);
			//			System.out.println("Array List Size - "+cliList.size());
			ranCLIAccNumber = cliList.get(i).toString()+","+accList.get(i).toString();
			//			System.out.println("Final Random CLI and Account Number -"+ranCLIAccNumber);


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
			return ranCLIAccNumber;

		}

	}

	@SuppressWarnings("finally")
	public String GetEmailMobiledata() throws Exception {
		String query = null;
		ResultSet rs = null;
		PreparedStatement stm;
		String ranCLIAccNumber = "";

		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":" +LoadEnvironment.CRM_DBNAME;
		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
		//		query ="select vcs.cli,vcs.accountnumber from CBLOWNER.v_contactinfo_full vcf,cblowner.v_customer_search_email vcs where (vcf.telecomnumber is not null and length(vcf.telecomnumber)>0) and (vcs.email is not null and length(vcs.email)>0) and vcf.partyid=vcs.partyid and vcf.contmthdtypecode='TELECOMNUM' and vcf.telecomnumtypecode='MOBILE' and rownum<40 and vcs.cli not like 'HM%' and vcs.cli not like '<null>'";
		query ="select vcs.cli,vcs.accountnumber from CBLOWNER.v_contactinfo_full vcf,cblowner.v_customer_search_email vcs where (vcf.telecomnumber is not null and length(vcf.telecomnumber)>0) and (vcs.email is not null and length(vcs.email)>0) and vcf.partyid=vcs.partyid and vcf.contmthdtypecode='TELECOMNUM' and vcf.telecomnumtypecode='MOBILE' and rownum<40 and vcs.cli not like 'HM%' and vcs.cli not like '<null>' and PSPSTATUSCODE='ACTIVE'";
		try{
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stm.executeQuery();
			ArrayList<String> cliList = new ArrayList<>();
			ArrayList<String> accList = new ArrayList<>();

			while(rs.next()){
				//				System.out.println("CLI \t "+rs.getString("CLI")+"\t Account\t"+rs.getString("ACCOUNTNUMBER"));
				//System.out.println(rs1.getString("CLI"));
				cliList.add(rs.getString("CLI"));
				accList.add(rs.getString("ACCOUNTNUMBER"));
			}

			//Getting random CLI
			Random ran = new Random();
			int i = ran.nextInt(cliList.size());
			if(i == cliList.size())
			{
				i = i-1;
			}
			//			System.out.println("random number - "+i);
			//			System.out.println("Array List Size - "+cliList.size());
			ranCLIAccNumber = cliList.get(i).toString()+","+accList.get(i).toString();
			//			System.out.println("Final Random CLI and Account Number -"+ranCLIAccNumber);


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
			return ranCLIAccNumber;

		}

	}

	@SuppressWarnings("finally")
	public String GetEmaildata() throws Exception {
		String query = null;
		ResultSet rs = null;
		PreparedStatement stm;
		String ranCLIAccNumber = "";

		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":" +LoadEnvironment.CRM_DBNAME;
		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
		query ="select distinct vcs.cli,vcs.accountnumber from CBLOWNER.v_contactinfo_full vcf,cblowner.v_customer_search_email vcs where (vcf.telecomnumber is null) and (vcs.email is not null and length(vcs.email)>0) and vcf.partyid=vcs.partyid and rownum<40 and vcs.cli not like 'HM%' and vcs.cli not like '<null>' and PSPSTATUSCODE='ACTIVE'";
		try{
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stm.executeQuery();
			ArrayList<String> cliList = new ArrayList<>();
			ArrayList<String> accList = new ArrayList<>();

			while(rs.next()){
				//				System.out.println("CLI \t "+rs.getString("CLI")+"\t Account\t"+rs.getString("ACCOUNTNUMBER"));
				//System.out.println(rs1.getString("CLI"));
				cliList.add(rs.getString("CLI"));
				accList.add(rs.getString("ACCOUNTNUMBER"));
			}

			//Getting random CLI
			Random ran = new Random();
			int i = ran.nextInt(cliList.size());
			if(i == cliList.size())
			{
				i = i-1;
			}
			//			System.out.println("random number - "+i);
			//			System.out.println("Array List Size - "+cliList.size());
			ranCLIAccNumber = cliList.get(i).toString()+","+accList.get(i).toString();
			//			System.out.println("Final Random CLI and Account Number -"+ranCLIAccNumber);


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
			return ranCLIAccNumber;

		}

	}


	public String OtherPaymentMethod() {
		String query = null;
		ResultSet rs = null;
		PreparedStatement stm;
		String ranAccNumber = "";
		String Str_Account = "";
		String data = "";

		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT+":"+LoadEnvironment.SV_DBNAME;
		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
		query ="select account_name from "+LoadEnvironment.SV_DBSCHEMA+".account a where a.customer_node_id in (select customer_node_id from "+LoadEnvironment.SV_DBSCHEMA+".CUSTOMER_NODE_HISTORY where payment_method_code = 2 ) and rownum<40";
		//		System.out.println(query);


		try{

			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stm.executeQuery();
			//			ArrayList<String> cliList = new ArrayList<>();
			ArrayList<String> accList = new ArrayList<>();

			while(rs.next()){
				//				System.out.println(rs.getString("account_name"));
				//				System.out.println(rs1.getString("CLI"));
				//				cliList.add(rs.getString("CLI"));

				//				System.out.println(rs.getString("account_name"));
				accList.add(rs.getString("account_name"));
			}

			//Getting random CLI
			Random ran = new Random();
			int i = ran.nextInt(accList.size());
			if(i == accList.size())
			{
				i = i-1;
			}
			//			System.out.println("random number - "+i);
			//			System.out.println("Array List Size - "+accList.size());
			ranAccNumber = accList.get(i).toString();
			//			System.out.println("Final Random Account Number -"+ranAccNumber);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
			return ranAccNumber;

		}

	}
	public String getVLRData(String packageID,String proposition,String type,String vlrtype){
		String CLI="";
		ResultSet rs=null;
		int count=0;
		String query="";
		if(type.equalsIgnoreCase("expired")){
			if(vlrtype.equalsIgnoreCase("vlr18")){
				query="Select CLI from cblowner.PORTFOLIOSALESPACKAGE WHERE packageid='"+packageID+"' AND ID in "+
						"(Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE WHERE PORTFOLIOSALESPACKAGEID NOT IN "+
						"(Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE WHERE propositionid = '20888' and sysdate < enddate) "+
						"and propositionid = '20888')";
			}
			else{
				query="Select CLI from cblowner.PORTFOLIOSALESPACKAGE WHERE packageid='"+packageID+"' AND ID in "+
						"(Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE WHERE PORTFOLIOSALESPACKAGEID NOT IN "+
						"(Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE WHERE propositionid = '18208' and sysdate < enddate) "+
						"and propositionid = '18208')";
			}
		}
		else if(type.equalsIgnoreCase("active")){
			if(vlrtype.equalsIgnoreCase("vlr12")){
				query="Select cli from cblowner.PORTFOLIOSALESPACKAGE WHERE packageid='"+packageID+"' and ID "
						+ "in (Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIOITEMDISCOUNT WHERE propositionid = '19410' "
						+"and (enddate is null or enddate>sysdate) and activationdate > add_months(sysdate,-12) )";
			}
			else{
				query="Select cli from cblowner.PORTFOLIOSALESPACKAGE WHERE packageid='"+packageID+"' and "
						+"ID  in (Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIOITEMDISCOUNT WHERE propositionid = '20889'  "
						+"and (enddate is null or enddate>sysdate) and activationdate > add_months(sysdate,-12))";
			}
		}
		else if(type.equalsIgnoreCase("RENEW"))
		{
			if(vlrtype.equalsIgnoreCase("vlr18")){
				query="Select * from cblowner.PORTFOLIOSALESPACKAGE WHERE packageid='"+packageID+"' and "
						+" ID  in (Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIOITEMDISCOUNT WHERE propositionid = 20889 "  
						+" and (enddate is null or (enddate>sysdate and enddate<add_months(sysdate,2))) and activationdate > add_months(sysdate,-12))";
			}
			else{
				query="Select * from cblowner.PORTFOLIOSALESPACKAGE WHERE packageid='"+packageID+"' and "
						+" ID  in (Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIOITEMDISCOUNT WHERE propositionid = 19410 "  
						+" and (enddate is null or (enddate>sysdate and enddate<add_months(sysdate,2))) and activationdate > add_months(sysdate,-12))";
			}
		}
		else{
			query="Select cli from cblowner.PORTFOLIOSALESPACKAGE WHERE packageid='"+packageID+"' and ID "
					+ "in (Select PORTFOLIOSALESPACKAGEID from cblowner.PORTFOLIOITEMDISCOUNT WHERE propositionid = '"+proposition+"' "
					+"and (enddate is null or enddate>sysdate) and activationdate > add_months(sysdate,-12))"
					+ " and activationdate > sysdate";
		}
		//		System.out.println(query);


		try {
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
			PreparedStatement stm = con.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery();
			CLI = "'";
			//			System.out.println("cli");
			while(rs.next()){
				CLI=CLI+rs.getString(1);
				CLI=CLI+"','";
				if(count==900)
					break;
				count++;

			}
			if(CLI.equals("'")){
				System.out.println("NO DATA IN CRM");
				CLI=null;
			}else{
				CLI = CLI.substring(0, CLI.length() - 2);
			}			

		} catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}

		return CLI;
	}
	public String getDataNEW(String packageID,String propositionid,String Customertype,String VlrType,boolean creditClassCheck) {
		//		System.out.println(packageID);

		DatabaseTestData dbd=new DatabaseTestData();
		CLI_CRM = getVLRData(packageID, propositionid, Customertype, VlrType);

		if(CLI_CRM!=null){
			CLI_OMP  = dbd.getCLI_OMP(CLI_CRM);
			//			System.out.println(CLI_OMP);
		}else{
			Report.fnReportFailAndTerminateTest("NO DATA FROM OMP", "NO DATA FROM OMP");
		}
		if(CLI_OMP != null){
			CLI_CLI = dbd.getCLI_CLIDB(CLI_OMP);
		}else{
			Report.fnReportFailAndTerminateTest("NO DATA FROM CLIDB","NO DATA FROM CLIDB");
		}
		if(CLI_CLI != null){
			CLI_final = dbd.getCLI_fineTune(CLI_CLI,creditClassCheck);
		}else{
			Report.fnReportFailAndTerminateTest("NO DATA to fine tune","NO DATA to fine tune");
		}
		return CLI_final;
	}
}

