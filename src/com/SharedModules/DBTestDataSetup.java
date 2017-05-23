package com.SharedModules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.Enumerations.Generic.CustomerType;
import com.Enumerations.Generic.DBName;
import com.SharedModules.DatabaseTestData.searchByDataa;


public class DBTestDataSetup implements Constants {


	public Reporter Report ;
	private Connection con=null;

	public String CLI_OMP = null;
	public String CLI_CRM = null;
	public String CLI_SV=null;
	public String CLI_CLI = null;
	public String CLI_final = null;
	public String CLI_OMP2= null;
	public String Customer_numbers = "";
	public String Customer_numbers1 = "";
	public String Customer_numbers2 = "";
	public String Customer_numbers3 = "";
	public String Account_Array ="";
	public String CLI_Array = "";
	public String CLI_Array1 = "";
	public String Account_Array1 ="";
	public String Account_Array2 = "";

	public DBTestDataSetup(Reporter report) {
		Report = report;
	}




	/**
	 * Description		: To retrieve single valid data from CRM,CLI,OMP and SV Databases 
	 * @param packageID
	 * @param search
	 * @param searchValue
	 * @param inContract
	 * @param creditClassCheck
	 * @return
	 * @throws Exception
	 */

	public String Retrieve_SingleData_Valid(String packageID, searchByDataa search, String searchValue, boolean inContract, boolean creditClassCheck) throws Exception{

		System.out.println("Fetching Data from CRM");
		searchValue=searchValue.replace(",", "','");
		packageID=packageID.replace(",", "','");

		CLI_CRM = getCLI_CRM(packageID,search,searchValue,inContract);

		if(CLI_CRM!=null){
			System.out.println("Fetching Data from OMP");
			CLI_OMP  = getCLI_OMP(CLI_CRM);
		}else if(Report!=null){
			System.out.println("NO DATA FROM CRM");
			Report.fnReportFailAndTerminateTest("Data", "NO DATA FROM CRM");
		}else { 
			System.out.println("NO DATA FROM CRM");
		}
		if(CLI_OMP != null){
			System.out.println("Fetching Data from CLI DB");
			CLI_CLI = getCLI_CLIDB(CLI_OMP);
		}else if(Report!=null){
			System.out.println("NO DATA FROM OMP");
			Report.fnReportFailAndTerminateTest("Data", "NO DATA FROM OMP");
		}else{
			System.out.println("NO DATA FROM OMP");
		}
		if(CLI_CLI != null){
			System.out.println("Fetching Data from SV");
			CLI_final = getCLI_fineTune(CLI_CLI,creditClassCheck);
		}else if(Report!=null){
			System.out.println("NO DATA to fine tune");
			Report.fnReportFailAndTerminateTest("Data", "NO DATA to fine tune");
		}else{
			System.out.println("NO DATA to fine tune");
		}
		if(CLI_final.equals("")){
			if(Report!=null){
				System.out.println("NO DATA from SV");
				Report.fnReportFailAndTerminateTest("Data", "NO DATA from SV");
			}else{
				System.out.println("NO DATA from SV");
			}
		}
		return CLI_final;
	}
	/**
	 * Description		: ro retrieve Data From CRM based on Search Criteria and Search Value
	 * @param packageID
	 * @param search
	 * @param searchValue
	 * @param inContract
	 * @return
	 */
	private String getCLI_CRM(String packageID, searchByDataa search,String searchValue,boolean inContract) {
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

		return CLI;
	}	
	/**
	 * Description		: to retrieve data from OMP based on the CLI array from getCLI_CRM function
	 * @param cLI_CRM
	 * @return
	 */
	private String getCLI_OMP(String cLI_CRM) {
		String omp = null;
		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.OMP_DBIP+":"+LoadEnvironment.OMP_DBPORT+":"+LoadEnvironment.OMP_DBNAME;
			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.OMP_DBUSERNAME,LoadEnvironment.OMP_DBPASSWORD);
			String query = "select column_value CLI from table(sys.odcivarchar2list("+cLI_CRM+"))"
					+ " Minus"
					+ " select cli from "+LoadEnvironment.OMP_DBSCHEMA+".VW_CPWORDERSTATUS where ISORDERINFINALSTATE = 'N' and CLI in ("+cLI_CRM+")";

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
					break;
				}else{
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
	/***
	 * Description			: To Retrieve data from CLI DB based on the array from getCLI_OMP
	 * @param CLI_OMP
	 * @return
	 */
	private String getCLI_CLIDB(String CLI_OMP) {
		String cli = null;
		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CLI_DBIP+":"+LoadEnvironment.CLI_DBPORT+":"+LoadEnvironment.CLI_DBNAME;
			System.out.println(ConnectionURL+LoadEnvironment.CLI_DBUSERNAME+LoadEnvironment.CLI_DBPASSWORD);
			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CLI_DBUSERNAME,LoadEnvironment.CLI_DBPASSWORD);
			CLI_OMP = CLI_OMP.replaceAll(",", "','");
			String query = "select resource_id from t_access_method_instance where resource_id in('"+CLI_OMP+"')"; 
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
					break;
				}else{
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
	 * Description 			: to fine tune the CLI for single and valid data
	 * @param CLI_OMP2
	 * @param creditClassCheck
	 * @return
	 */
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
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();
			count=0;
			while(rs1.next()){
				if(count>996){
					break;
				}else{
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
			Customer_numbers1=Customer_numbers1.replace(",", "','");	
			query3 = "select column_value CUSTOMERNUMBER from table(sys.odcivarchar2list('"+Customer_numbers1+"'))"
					+ " Minus"
					+ " select CUSTOMERNUMBER from CBLOWNER.fraud f where f.CUSTOMERNUMBER in ('"+Customer_numbers1+"')";
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

			CLI_Array1=CLI_Array1.replace(",", "','");	
			query3 = "select ACCOUNTNUMBER from CBLOWNER.v_customer_search where CLI in ('"+CLI_Array1+"')";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();

			while(rs1.next()){
				Account_Array = Account_Array+rs1.getString("ACCOUNTNUMBER")+",";
			}
			if(creditClassCheck){
				System.out.println("-------------------------------in Credit Check--------------------------------------------");
				String Acc = ZeroBalCustomer(Account_Array);
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
			Account_Array1=Account_Array1.replace(",", "','");	
			query3 = "select CLI,ACCOUNTNUMBER from CBLOWNER.v_customer_search where ACCOUNTNUMBER in ('"+ Account_Array1+"')";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery(); 
			ArrayList<String> cliList = new ArrayList<>();
			ArrayList<String> accList = new ArrayList<>();
			while(rs1.next()){
				cliList.add(rs1.getString("CLI"));
				accList.add(rs1.getString("ACCOUNTNUMBER"));
			}
			Random ran = new Random();
			int i = ran.nextInt(cliList.size());
			if(i == cliList.size())
			{
				i = i-1;
			}
			System.out.println("Array List Size - "+cliList.size());
			ranCLIAccNumber = cliList.get(i).toString()+","+accList.get(i).toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return ranCLIAccNumber;
	}
	/**
	 * Description			: To update the credit class and tier for the given CLI
	 * @param Str_Account
	 * @param Str_CreditClass
	 * @param Str_CreditTier
	 * @throws Exception
	 */
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
	/**
	 * Description		: To Get Zero balance customer
	 * @param Account_Array
	 * @return
	 * @throws Exception
	 */
	public String ZeroBalCustomer(String Account_Array) throws Exception{
		System.out.println("-------------------------------in Zero Balance Check--------------------------------------------");
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
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				AccountNew= AccountNew+RS.getString("ACCOUNT_NAME")+",";
			}

		}catch(Exception e){

		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return AccountNew;
	}
	/**
	 *  Description 		: To get Collection Free Customer
	 * @param Account_Array
	 * @return
	 * @throws Exception
	 */
	public String CollectionFreeCustomer(String Account_Array) throws Exception{
		System.out.println("-------------------------------in Zero Balance Check--------------------------------------------");
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
				customer_node_id= RS.getString("CUSTOMER_NODE_ID").replace(",","");
				customer_node_id_new= customer_node_id_new+customer_node_id+",";
			}
			customer_node_id_new = customer_node_id_new.replace(",","','");
			query = "select * from "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array where derived_attribute_id = 12000066"
					+ " AND SYSDATE BETWEEN EFFECTIVE_START_DATE AND EFFECTIVE_END_dATE"
					+ " AND CUSTOMER_NODE_ID in" 
					+"('"+customer_node_id_new+"')";

			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				custnodeid_collfree= custnodeid_collfree+RS.getString("CUSTOMER_NODE_ID")+",";
			}
			custnodeid_collfree = custnodeid_collfree.replace(",","','");
			String query1= "SELECT * from "+LoadEnvironment.SV_DBSCHEMA+".account where  CUSTOMER_NODE_ID in('"+custnodeid_collfree+"')";
			stm=con.prepareStatement(query1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				AccountNew= AccountNew+RS.getString("ACCOUNT_NAME")+",";
			}
		}catch(Exception e){

		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return AccountNew;
	}
	/**
	 * Description : To retrieve customer cli based on the customer type 
	 */
	public String Retrieve_Data_Based_On_Customer_Type(CustomerType customerType, String Pacakage)throws Exception{
		String packageID=Pacakage.replace(",", "','");
		String Str_CLI="";
		String Str_Account ="";
		String Str_Query="";
		DbUtilities DBU = new DbUtilities(Report);
		DBTestDataSetup DBT = new DBTestDataSetup(Report);
		String ReturnData="";

		switch(customerType){
		case Cancelled:
			Str_Query="select distinct a.cli from CBLOWNER.v_customer_search a,"
					+ " cblowner.portfoliosalespackage P where P.cli=a.cli and p.packageid='"+packageID+"' "
					+ "and a.PSPSTATUSCODE='CANCELLED' and not a.cli like '%HM' "
					+ "and not a.cli like '%NL' AND p.activityid='26' and ROWNUM<2 ";
			ReturnData=DBU.DBConnect(Str_Query, DBName.CRM,"CLI");
			break;
		case DCA_Jefersson:
			Str_Account=DBU.getDCACustomer(31);
			ReturnData=DBU.getclifromaccount(Str_Account);
			break;
		case DCA_Lowell:
			Str_Account=DBU.getDCACustomer(32);
			ReturnData=DBU.getclifromaccount(Str_Account);
			break;
		case Disconnected:
			Str_Query="select distinct P.cli from CBLOWNER.v_customer_search a,"
					+ " cblowner.portfoliosalespackage P where P.cli=a.cli and p.packageid='"+packageID+"' "
					+ "and a.PSPSTATUSCODE='DISCONNECTED' and not a.cli like '%HM' "
					+ "and not a.cli like '%NL' and ROWNUM<2";
			ReturnData=DBU.DBConnect(Str_Query, DBName.CRM, "CLI");
			break;
		case HMInflight:
			ReturnData=DBU.getMulticli_withHM();
			break;
		case Incollection:
			Str_Query=" select ACCOUNT_NAME from "+LoadEnvironment.SV_DBSCHEMA+".account a, "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array nda where nda.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID" +
					" and nda.derived_attribute_id = '12000066'" +
					" AND SYSDATE BETWEEN nda.EFFECTIVE_START_DATE AND nda.EFFECTIVE_END_dATE and nda.INDEX2_VALUE = '1' and ROWNUM<2";
			Str_Account=DBU.DBConnect(Str_Query, DBName.SV, "ACCOUNT_NAME");
			ReturnData=DBU.getclifromaccount(Str_Account);
			break;
		case MultipleCli:
			ReturnData=DBU.getMulticlidata();
			break;
		case inflight:
			Str_Query="select distinct a.cli from CBLOWNER.v_customer_search a,"
					+ " cblowner.portfoliosalespackage P where P.cli=a.cli and p.packageid='"+packageID+"' "
					+ "and P.activationdate is null and P.disconnectiondate is null and P.enddate is null";
			ReturnData=DBU.DBConnect(Str_Query, DBName.CRM,"CLI");
			break;
		case Fraud:
			Str_Query = "select distinct  cli,accountnumber from cblowner.v_customer_search where pspstatuscode='ACTIVE'"
					+ " and customernumber in (select customernumber from cblowner.fraud where status like '%Confirmed%' and rownum <400)";
			ReturnData=DBU.DBConnect(Str_Query, DBName.CRM,"CLI");
		default:
			break;

		}
		return ReturnData;



	}


}

