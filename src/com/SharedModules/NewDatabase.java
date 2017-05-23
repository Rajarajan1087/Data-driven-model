//package com.SharedModules;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.Engine.LoadEnvironment;
//import com.Engine.Reporter;
//
//public class NewDatabase{
//	public Reporter Report;
//	public NewDatabase(Reporter report) {
//		Report=report;
//	}
//	private String Str_ErrorCLI="01225287751,01227374909,01205359651,01159501111,01254877552,01245450951,01245450952,01245450953,01245450955,01245450956,01245450957,01245450958,"
//			+ "01245450959,01245450960,01245450964,01245450969,01245450970,01245450972,01245450973,01245450974"
//			+ ",01245450975,01245450976,01245450977,01245450978,01245450979,01245450980";
//	private static Connection con=null;
//	
//	public enum DBNames{
//		CRM(LoadEnvironment.CRM_DBIP,LoadEnvironment.CRM_DBPORT,LoadEnvironment.CRM_DBNAME,"",LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD),
//		SV(LoadEnvironment.SV_DBIP,LoadEnvironment.SV_DBPORT,LoadEnvironment.SV_DBNAME,LoadEnvironment.SV_DBSCHEMA,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD),
//		OMP(LoadEnvironment.OMP_DBIP,LoadEnvironment.OMP_DBPORT,LoadEnvironment.OMP_DBNAME,LoadEnvironment.OMP_DBSCHEMA,LoadEnvironment.OMP_DBUSERNAME,LoadEnvironment.OMP_DBPASSWORD),
//		EVG(LoadEnvironment.EVG_DBIP,LoadEnvironment.EVG_DBPORT,LoadEnvironment.EVG_DBNAME,"",LoadEnvironment.EVG_DBUSERNAME,LoadEnvironment.EVG_DBPASSWORD);
//		
//		private final String DB_IP;
//	    private final String DB_PORT;
//	    private final String DB_Name;
//	    private final String DB_SCHEMA;
//	    private final String DB_UName;
//	    private final String DB_Password;
//	    
//	    private DBNames(String DB_IP,String DB_PORT,String DB_Name,String DB_SCHEMA,String DB_UName,String DB_Password) {
//		        this.DB_IP = DB_IP;
//		        this.DB_PORT = DB_PORT;
//		        this.DB_Name = DB_Name; 
//		        this.DB_SCHEMA = DB_SCHEMA;
//		        this.DB_UName = DB_UName;
//		        this.DB_Password = DB_Password;
//		    }
//		    
//		    public String[] getDBDetails() {
//		    	String[] DB_Details={DB_IP,DB_PORT,DB_Name,DB_SCHEMA,DB_UName,DB_Password};
//		        return DB_Details;
//		    }
//	
//	}
//	public enum searchByData{
//		Proposition,Bundle,Discount,blank
//	}
///*	public String getDataNEW(String packageID, searchByData search, String searchValue, boolean inContract, boolean creditClassCheck,String... Str_CLI) throws Exception {
//		String CLI_OMP = null;
//		String CLI_CRM = null;
//		String CLI_SV = null;
//
//		searchValue=searchValue.replace(",", "','");
//		packageID=packageID.replace(",", "','");
//		//CLI_CRM=Data_GetCLI_CRM(packageID,search,searchValue,inContract);
//		CLI_CRM=getCLI_CRM(packageID,search,searchValue,inContract);
//		if(CLI_CRM!="")
//		{
//			for(int i=0;i<Str_CLI.length;i++)
//			{
//				CLI_CRM=removeCLI(CLI_CRM,Str_CLI[i]);
//			}
//			CLI_OMP = getCLI_OMP(CLI_CRM);
//			System.out.println(CLI_OMP);
//		}else{
//			Report.fnReportFail("NO DATA FROM CRM");
//		}
//		if(CLI_OMP != null){
//			CLI_SV = getCLI_SV(CLI_OMP,inContract,creditClassCheck);
//		}
//		else{
//			Report.fnReportFail("NO DATA FINE TUNED");
//		}
//		if(CLI_SV==",")
//		{
//			Report.fnReportFail("NO fine tuned DATA");
//		}
//		return CLI_SV;
//	}*/
//	
///*	public String getDataNEW(String packageID, searchByData search, String searchValue, boolean inContract, boolean creditClassCheck,String... Str_CLI) throws Exception {
//		String CLI_OMP = null;
//		String CLI_CRM = "";
//		String CLI_SV = null;
//
//		searchValue=searchValue.replace(",", "','");
//		packageID=packageID.replace(",", "','");
//		//CLI_CRM=Data_GetCLI_CRM(packageID,search,searchValue,inContract);
//		int j=0;
//		while(CLI_CRM.equalsIgnoreCase("")||j<3)
//		{
//			CLI_CRM=getCLI_CRM(packageID,search,searchValue,inContract);
//			if(!CLI_CRM.equalsIgnoreCase(""))
//				j=3;
//			j++;
//		}
//		if(!CLI_CRM.equalsIgnoreCase(""))
//		{
//			for(int i=0;i<Str_CLI.length;i++)
//			{
//				CLI_CRM=removeCLI(CLI_CRM,Str_CLI[i]);
//				System.out.println(CLI_CRM);
//			}
//			CLI_CRM=shuffleCLI(CLI_CRM);
//			System.out.println("Shuffling cli's");
//			System.out.println(CLI_CRM);
//			j=0;
//			while(CLI_OMP==null||j<3)
//			{
//				System.out.println("OMP CLi");
//				CLI_OMP = getCLI_OMP(CLI_CRM);
//				System.out.println("Getting OMP CLI");
//				System.out.println(CLI_OMP);
//				if(CLI_OMP!=null)
//				{
//					j=3;
//					System.out.println("Got cli");
//				}
//				j++;
//			}
//			System.out.println("Shuffling OMP cli");
//			CLI_OMP=shuffleCLI(CLI_OMP);
//			System.out.println(CLI_OMP);
//			
//			j=0;
//			while(CLI_SV==null||j<3)
//			{
//				System.out.println("Getting sv clis");
//				CLI_SV = getCLI_SV(CLI_OMP,inContract,creditClassCheck);
//				System.out.println(CLI_SV);
//				if(CLI_SV!=",")
//				{
//					j=3;
//				}
//				
//				 if(!CLI_SV.equalsIgnoreCase(","))
//						
//				 {
//				 j=3;
//				 System.out.println("CLI_SV is there");
//				 }
//				
//				else
//				{
//					System.out.println("No data from OMP");
//					Report.fnReportFail("NO DATA FROM OMP");
//				}
//				j++;
//			}
//			if(!CLI_SV.equalsIgnoreCase(","))
//			{
//				for(int i=0;i<Str_CLI.length;i++)
//				{
//					CLI_SV=removeCLI(CLI_SV,Str_CLI[i]);
//					System.out.println(CLI_SV);
//				}
//				CLI_SV=shuffleCLI(CLI_SV);
//				System.out.println("Shuffling  Sv cli's");
//				System.out.println(CLI_SV);
//					
//			
//		}else{
//			System.out.println("No data from CRM");
//			Report.fnReportFailAndTerminateTest("Fail", "NO DATA FROM CRM");
//		}
//		}
//		return CLI_SV;
//	}	
//*/
//		/*if(CLI_SV==",")
//		{
//			Report.fnReportFail("NO fine tuned DATA");
//		}
//		return CLI_SV;*/
//	
//	
////	/* OLD FUNCTION*/
////	public String getDataNEW(String packageID, searchByData search, String searchValue, boolean inContract, boolean creditClassCheck,String... Str_CLI) throws Exception {
////		String CLI_OMP = null;
////		String CLI_CRM = "";
////		String CLI_SV = ",";
////
////		searchValue=searchValue.replace(",", "','");
////		packageID=packageID.replace(",", "','");
////		//CLI_CRM=Data_GetCLI_CRM(packageID,search,searchValue,inContract);
////		int j=0;
////		while(CLI_CRM.equalsIgnoreCase("")||j<3)
////		{
////			CLI_CRM=getCLI_CRM(packageID,search,searchValue,inContract);
////			if(!CLI_CRM.equalsIgnoreCase(""))
////				j=3;
////			j++;
////		}
////		if(!CLI_CRM.equalsIgnoreCase(""))
////		{
////			for(int i=0;i<Str_CLI.length;i++)
////			{
////				CLI_CRM=removeCLI(CLI_CRM,Str_CLI[i]);
////				System.out.println(CLI_CRM);
////			}
////			CLI_CRM=shuffleCLI(CLI_CRM);
////			System.out.println("Shuffling cli's");
////			System.out.println(CLI_CRM);
////		}
////		else{
////			System.out.println("No data from CRM");
////			Report.fnReportFailAndTerminateTest("Fail", "NO DATA FROM CRM");
////		}
////
////		j=0;
////		while(CLI_OMP==null||j<3)
////		{
////			System.out.println("OMP CLi");
////			CLI_OMP = getCLI_OMP(CLI_CRM);
////			System.out.println("Getting OMP CLI");
////			System.out.println(CLI_OMP);
////			if(CLI_OMP!=null)
////			{
////				j=3;
////				System.out.println("Got cli");
////			}
////			j++;
////		}
////		if(CLI_OMP!=null)
////		{
////			System.out.println("Shuffling OMP cli");
////			CLI_OMP=shuffleCLI(CLI_OMP);
////			System.out.println(CLI_OMP);
////		}
////		
////		j=0;
////		while(CLI_SV.equalsIgnoreCase(",")||j<3)
////		{
////			System.out.println("Getting sv clis");
////			CLI_SV = getCLI_SV(CLI_OMP,inContract,creditClassCheck);
////			System.out.println(CLI_SV);
////			if(!CLI_SV.equalsIgnoreCase(","))
////			{
////				j=3;
////				System.out.println("CLI_SV is there");
////			}
////			j++;
////		}
////		if(!CLI_SV.equalsIgnoreCase(","))
////		{
////			System.out.println(CLI_SV);
////		}
////		else{
////			System.out.println("No data from SV");
////			Report.fnReportFailAndTerminateTest("Fail", "NO DATA FROM SV");
////		}
////
////		return CLI_SV;
////	} 
//	
//	
////	/* NEW FUNCTION*/
//	public String getDataNEW(String packageID, searchByData search, String searchValue, boolean inContract, boolean creditClassCheck,String... Str_CLI) throws Exception {
//		String CLI_OMP = null;
//		String CLI_CRM = "";
//		String CLI_SV = ",";
//
//		searchValue=searchValue.replace(",", "','");
//		packageID=packageID.replace(",", "','");
//		//CLI_CRM=Data_GetCLI_CRM(packageID,search,searchValue,inContract);
//		int j=0;
//		while(j<3)
//		{
//			System.out.println("Retreiving Data from CRM - - TRIAL "+(j+1));
//			CLI_CRM=getCLI_CRM(packageID,search,searchValue,inContract);
//			if(!CLI_CRM.equalsIgnoreCase(""))
//			{
//				System.out.println("Got Data from CRM in TRIAL "+(j+1));
//				j=3;
//			}
//			j++;
//		}
//		if(!CLI_CRM.equalsIgnoreCase(""))
//		{
//			for(int i=0;i<Str_CLI.length;i++)
//			{
//				CLI_CRM=removeCLI(CLI_CRM,Str_CLI[i]);
//				System.out.println(CLI_CRM);
//			}
//			CLI_CRM=shuffleCLI(CLI_CRM);
//			System.out.println(CLI_CRM);
//		}
//		else{
//			Report.fnReportFailAndTerminateTest("Fail", "NO DATA FROM CRM");
//		}
//
//		j=0;
//		while(j<3)
//		{
//			System.out.println("Retreiving Data from OMP - - TRIAL "+(j+1));
//			CLI_OMP = getCLI_OMP(CLI_CRM);
//			if(CLI_OMP!=null)
//			{
//				System.out.println("Got Data from OMP in TRIAL "+(j+1));
//				j=3;
//			}
//			else
//			{
//				CLI_CRM=shuffleCLI(CLI_CRM);
//				j++;
//			}
//		}
//		if(CLI_OMP!=null)
//		{
//			CLI_OMP=shuffleCLI(CLI_OMP);
//			System.out.println(CLI_OMP);
//		}
//
//		j=0;
//		while(j<3)
//		{
//			System.out.println("Retreiving Data from SV - - TRIAL "+(j+1));
//			CLI_SV = getCLI_SV(CLI_OMP,inContract,creditClassCheck);
//			if(!CLI_SV.equalsIgnoreCase(","))
//			{
//				System.out.println("Got Data from SV in TRIAL "+(j+1));
//				j=3;
//			}
//			else
//			{
//				CLI_OMP=shuffleCLI(CLI_OMP);
//				j++;
//			}
//		}
//		if(!CLI_SV.equalsIgnoreCase(","))
//		{
//			System.out.println(CLI_SV);
//		}
//		else{
//			System.out.println("No data from SV");
//			Report.fnReportFailAndTerminateTest("Fail", "NO DATA FROM SV");
//		}
//		return CLI_SV;
//	}  
//	
///*	public String getDataNEW_Modified(String packageID, searchByData search, String searchValue, boolean inContract, boolean creditClassCheck,String Str_NOT_CLI) {
//
//		String CLI_OMP = null;
//		String CLI_CRM = null;
//		String CLI_final = null;
//		
//		CLI_CRM=Data_GetCLI_CRM(packageID,search,searchValue,inContract);
//		
//		if(CLI_CRM!=null){
//			CLI_OMP  = getCLI_OMP(CLI_CRM);
//		}else{
//			System.out.println("NO DATA FROM OMP");
//		}
//		if(CLI_OMP != null){
//			System.out.println("CLIs From OMP_DB for "+packageID+" are "+CLI_OMP);
//			CLI_final = getCLI_fineTune(CLI_OMP,inContract,creditClassCheck);
//		}else{
//			System.out.println("NO DATA to fine tune");
//		}
//		return CLI_final;
//		
//	}*/
//	
//	@SuppressWarnings("finally")
//	private String[] getCLI_fineTune(String cLI_OMP, boolean inContract,boolean creditClassCheck) {
//
//		String query3 = ""; 
//		ResultSet rs1 = null;
//		PreparedStatement stm =null;
//		String customerNumber = "";
//		String AccountNumber = "";
//		String data = "";
//		//int flag=0;
//		try{
//			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":" +LoadEnvironment.CRM_DBNAME;
//			//	System.out.println(ConnectionURL);
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
//			System.out.println("Connection established");
//			String [] cli=cLI_OMP.split(",");
//			for (int i=0;i<cli.length;i++){
//				int flag=0;
//
//				query3 ="select CUSTOMERNUMBER from CBLOWNER.v_customer_search where CLI in ('"+cli[i]+"')";
//				//		System.out.println(query3);
//				stm=con.prepareStatement(query3);
//				rs1=stm.executeQuery();
//				while(rs1.next()){
//					customerNumber = rs1.getString(1);
//				}
//				query3="select CUSTOMERNUMBER,CATEGORY from CBLOWNER.bar where CUSTOMERNUMBER='"+customerNumber+"'";
//				//	System.out.println(query3);
//				stm=con.prepareStatement(query3);
//				rs1=stm.executeQuery();
//
//				while(rs1.next()){
//					flag=1;
//				}
//				if(flag==0){
//					query3="select * from CBLOWNER.fraud f where f.CUSTOMERNUMBER='"+customerNumber+"'";
//					//		System.out.println(query3);
//					stm=con.prepareStatement(query3);
//					rs1=stm.executeQuery();
//
//					while(rs1.next()){
//						flag=1;
//					}
//					if(flag==0){
//						query3="select count(*) WithSameCustomerNumber from CBLOWNER.v_customer_search cs where cs.customernumber = '"+customerNumber+"'";
//						//		System.out.println(query3);
//						stm=con.prepareStatement(query3);
//						rs1=stm.executeQuery();
//						while(rs1.next()){
//							if(rs1.getString(1).equals("1")){
//								flag=0;
//							}else{
//								flag=1;
//							}
//						}
//						if(flag==0){
//							query3="select count(*) WithSameCLI from CBLOWNER.portfoliosalespackage psp where psp.cli ='"+cli[i]+"'";
//							//					System.out.println(query3);
//							stm=con.prepareStatement(query3);
//							rs1=stm.executeQuery();
//							while(rs1.next()){
//								if(rs1.getString(1).equals("1")){
//									flag=0;
//								}else{
//									flag=1;
//								}
//							}
//							if(flag==0){
//								query3="select ACCOUNTNUMBER from CBLOWNER.v_customer_search where CLI ='"+cli[i]+"'";
//								//			System.out.println(query3);
//								stm=con.prepareStatement(query3);
//								rs1=stm.executeQuery();
//								while(rs1.next()){
//									AccountNumber = rs1.getString(1);
//								}
//								query3="select count (*) WithSameAccount from CBLOWNER.v_customer_search where accountnumber ='"+AccountNumber+"'";
//								//			System.out.println(query3);
//								stm=con.prepareStatement(query3);
//								rs1=stm.executeQuery();
//
//								while(rs1.next()){
//									if(rs1.getString(1).equals("1")){
//										flag=0;
//									}else{
//										flag=1;
//									}
//								}
//								if((flag==0))
//								/*if((flag==0)&&(inContract) )*/{
//									/*query3="select CONTRACTTERM  from CBLOWNER.contract where portfoliosalespackageid in (select id  from "
//											+ "cblowner.portfoliosalespackage where CLI in ('"+cli+"')) and sysdate > STARTDATE"
//											+ " and SYSDATE < STARTDATE+(CONTRACTTERM*30)-1 order by STARTDATE desc";
//									//			System.out.println(query3);
//									stm=con.prepareStatement(query3);
//									rs1=stm.executeQuery();
//									flag=1;
//									while(rs1.next()){
//										//				System.out.println(rs1.getString(1));
//										if((rs1.getString(1).equals("12"))||(rs1.getString(1).equals("18"))||(rs1.getString(1).equals("24"))){
//											flag=0;
//										}else{
//											flag=1;
//										}
//									}*/
//									if((flag==0)&&(creditClassCheck) ){
//										/*String ConnectionURL1 = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT+":"+ LoadEnvironment.SV_DBNAME;
//									//	System.out.println(ConnectionURL1);
//										con1 = ConnectionFactory.createConnection(ConnectionURL1,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
//										query3="select cnh.customer_node_id,(SELECT ABBREVIATION FROM "+LoadEnvironment.SV_DBSCHEMA+".REFERENCE_CODE"
//												+ " WHERE REFERENCE_TYPE_ID = 9000167 AND REFERENCE_CODE=CNH.PAYMENT_METHOD_CODE)"
//												+ " PAYMENT_METHOD,(SELECT ABBREVIATION FROM "+LoadEnvironment.SV_DBSCHEMA+".REFERENCE_CODE"
//												+ " WHERE REFERENCE_TYPE_ID = 9000173"
//												+ " AND REFERENCE_CODE=CNH.CREDIT_RATING_CODE) CREDIT_CLASS FROM "+LoadEnvironment.SV_DBSCHEMA+".customer_node_history"
//												+ " CNH where CNH.EFFECTIVE_END_DATE > SYSDATE and CNH.CUSTOMER_NODE_ID in (select acc.CUSTOMER_NODE_ID"
//												+ " from "+LoadEnvironment.SV_DBSCHEMA+".account acc where acc.account_name ='"+AccountNumber+"')";
//										//			System.out.println(query3);
//										stm=con1.prepareStatement(query3);
//										rs1=stm.executeQuery();
//										flag=1;
//										while(rs1.next()){
//											//				System.out.println(rs1.getString(3));
//											if((rs1.getString(3).equals("E1"))||(rs1.getString(1).equals("E2"))){
//												flag=0;
//											}else{
//												flag=1;
//											}
//										}*/
//										try{
//											Update_CreditClassCreditTier(AccountNumber,"E1","1");
//											flag=0;
//										}catch(Exception e){
//											flag=1;
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//				if (flag==0){
//					if(cli[i].length()==11){
//						data = cli[i]+","+AccountNumber;
//						break;
//					}
//				}
//				if(flag==1)
//				{
//					cLI_OMP=removeCLI(cLI_OMP,cli[i]);
//				}
//			}
//		}catch(Exception e){
//		}finally{
//			ConnectionFactory.closeConnection(con);
//			//	ConnectionFactory.closeConnection(con1);
//			String[] returnData={data,cLI_OMP};
//			return returnData;
//		}
//	}
//
//	private String Data_GetCLI_CRM(String packageID, searchByData search,String searchValue,boolean inContract) throws Exception
//	{
//		String CLI_CRM0 = null;
//		String CLI_CRM1 = null;
//		String[] SearchValue;
//		String Result="";
//		boolean Boolean_Search=true;
//		boolean Boolean_Search_NOT=false;
//		if(searchValue!=""&&search.name()!="blank")
//		{
//			SearchValue=Data_SearchValue(searchValue);
//			SearchValue[0]=SearchValue[0].replace(",", "','");
//			SearchValue[1]=SearchValue[1].replace(",", "','");
//			packageID=packageID.replace(",", "','");
//			if(SearchValue[0].equalsIgnoreCase(""))
//			{
//				Boolean_Search=false;
//			}
//			else
//			{
//				CLI_CRM0=getCLI_CRM(packageID,search,SearchValue[0],inContract);
//				CLI_CRM0=CLI_CRM0.replace("'", "");
//			}
//			if(SearchValue[1].equalsIgnoreCase(""))
//			{
//				Boolean_Search_NOT=false;
//			}
//			else
//			{
//				CLI_CRM1= getCLI_CRM(packageID,search,"NOT_"+SearchValue[1],inContract);
//				CLI_CRM1=CLI_CRM1.replace("'", "");
//			}
//			if(Boolean_Search&&Boolean_Search_NOT)
//			{
//				Result=filterCLI(CLI_CRM0,CLI_CRM1,false);
//			}
//			else if(Boolean_Search)
//			{
//				Result=CLI_CRM0;
//			}
//			else
//			{
//				Result=CLI_CRM1;
//			}
//			return Result;
//		}
//		else
//		{
//			return getCLI_CRM(packageID,search,searchValue,inContract);
//		}
//	}
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private String filterCLI(String Data1,String Data2,Boolean Minus_DIFF) throws Exception
//	{
//		String[] CLI0=Data1.split(",");
//		String[] CLI1=Data2.split(",");
//		List<String> baseList=Arrays.asList(CLI1);
//		List<String> compareList=new ArrayList(Arrays.asList(CLI0));
//		if(CLI0.length>CLI1.length)
//		{
//			compareList = new ArrayList(Arrays.asList(CLI0));
//			baseList = Arrays.asList(CLI1);
//		}
//		else
//		{
//			if(Minus_DIFF)
//			{
//				Report.fnReportFailAndTerminateTest("DB Comparison", "Invalid Data for Comparison");
//			}else
//			{
//				compareList = new ArrayList(Arrays.asList(CLI1));
//				baseList = Arrays.asList(CLI0);
//			}
//		}
//		try
//		{
//			compareList.retainAll(baseList);
//		}
//		catch(Exception e)
//		{
//			Report.fnReportFailAndTerminateTest("Retain Function", "Retaining the String is not done properly");
//		}
//		return StringUtils.join(compareList,",");
//	}
//	private String[] Data_SearchValue(String SearchValue)
//	{
//		StringBuffer SEARCHVALUE = new StringBuffer(110);
//		StringBuffer NOT_SEARCHVALUE = new StringBuffer(110);
//		String [] Search=SearchValue.split("','");
//		
//		for(int i=0;i<Search.length;i++)
//		{
//			if(Search[i].contains("NOT"))
//			{
//				NOT_SEARCHVALUE.append(Search[i].substring(4)+",");
//			}
//			else
//			{
//				SEARCHVALUE.append(Search[i]+",");
//			}
//		}
//		
//		String [] a={StringUtils.removeEnd(SEARCHVALUE.toString(), ","),StringUtils.removeEnd(NOT_SEARCHVALUE.toString().toString(), ",")};
//		return a;
//	}
//	
//	public void Update_CreditClassCreditTier(String Str_Account,String Str_CreditClass,String Str_CreditTier) throws Exception{
//
//		String query = null;
//		PreparedStatement stm = null;
//		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT+":"+LoadEnvironment.SV_DBNAME;
//		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
//		try{
//			query = "UPDATE "+LoadEnvironment.SV_DBSCHEMA+".customer_node_history CNH"
//					+ " SET CNH.CREDIT_RATING_CODE = (SELECT REFERENCE_CODE FROM "+LoadEnvironment.SV_DBSCHEMA+".REFERENCE_CODE"
//					+ " WHERE REFERENCE_TYPE_ID = 9000173  AND ABBREVIATION='"+Str_CreditClass+"')"
//					+ " WHERE CNH.EFFECTIVE_END_DATE > SYSDATE and CNH.CUSTOMER_NODE_ID in"
//					+ " (select acc.CUSTOMER_NODE_ID from "+LoadEnvironment.SV_DBSCHEMA+".account acc where acc.account_name ='"+Str_Account+"')";
//			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			stm.executeUpdate();
//			con.commit();
//
//			query = "UPDATE "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array"
//					+ " SET INDEX1_VALUE = "+Str_CreditTier +" "
//					+ " WHERE derived_attribute_id = 23000140"
//					+ " AND SYSDATE BETWEEN EFFECTIVE_START_DATE AND EFFECTIVE_END_dATE"
//					+ "  --and INDEX2_VALUE = 1"
//					+ " AND CUSTOMER_NODE_ID  in"
//					+ " (Select acc.CUSTOMER_NODE_ID from "+LoadEnvironment.SV_DBSCHEMA+".ACCOUNT acc where acc.account_name = '"+Str_Account+"')";
//			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			stm.executeUpdate();
//			con.commit();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//	}
//
//	private String getCLI_OMP(String cLI_CRM) {
//		String omp = null;
//		try{
//			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.OMP_DBIP+":"+LoadEnvironment.OMP_DBPORT+":"+LoadEnvironment.OMP_DBNAME;
//			//	System.out.println(ConnectionURL);
//			
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.OMP_DBUSERNAME,LoadEnvironment.OMP_DBPASSWORD);
//			String query = "select * from(select column_value CLI from table(sys.odcivarchar2list("+cLI_CRM+"))"
//				+ " Minus"
//				+ " select cli from "+LoadEnvironment.OMP_DBSCHEMA+".VW_CPWORDERSTATUS where ISORDERINFINALSTATE = 'N' and CLI in ("+cLI_CRM+"))order by dbms_random.value";
//			//System.out.println(query);
//			PreparedStatement stm=con.prepareStatement(query);
//			ResultSet rs=stm.executeQuery();
//			while(rs.next()){
//				if(omp == null){
//					omp=rs.getString(1);
//					omp=omp+",";
//				}else{
//
//					omp=omp+rs.getString(1);
//					omp=omp+",";
//				}
//			}
//			if(omp!=null){
//				omp = omp.substring(0, omp.length() - 1);
//			}else{
//				System.out.println("NO ACTIVE DATA FOUND in OMP");
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		//System.out.println("CLIs From OMP_DB are "+omp);
//		return omp;
//	}
//
//	@SuppressWarnings("unused")
//	private String getAcc_CLI(String CLI_ACC,boolean Boolean_CLI_ACC) {
//		String ACC_CLI = null;
//		String query=null;
//		try{
//			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":"+LoadEnvironment.CRM_DBNAME;
//			//	System.out.println(ConnectionURL);
//			
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
//			String[] CLI=CLI_ACC.split(",");
//			for(int i=0;i<198;i++)
//			{
//			if(Boolean_CLI_ACC)
//			{
//				query = "select CUSTOMERNUMBER from CBLOWNER.v_customer_search where CLI in ('"+CLI[i]+"')";
//			}
//			else
//			{
//				query = "select CLI from CBLOWNER.v_customer_search where CUSTOMERNUMBER in ('"+CLI[i]+"')";
//			}
//			PreparedStatement stm=con.prepareStatement(query);
//			ResultSet rs=stm.executeQuery();
//			if(rs.next()){
//				if(ACC_CLI == null){
//					ACC_CLI=rs.getString(1);
//					ACC_CLI=ACC_CLI+",";
//				}else{
//
//					ACC_CLI=ACC_CLI+rs.getString(1);
//					ACC_CLI=ACC_CLI+",";
//				}
//			}
//			}
//			ACC_CLI = ACC_CLI.substring(0, ACC_CLI.length() - 1);
//			}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		return ACC_CLI;
//	}
//	
//	private String[] executeQuery(String query,DBNames DBName,String[] Data) throws Exception {
//		
//		try{
//			DBNames g = DBName;
//			String[] DB_Details=g.getDBDetails();
//			String[] Datam=new String[50];
//			String ConnectionURL = "jdbc:oracle:thin:@"+DB_Details[0]+":"+DB_Details[1]+":"+DB_Details[2];
//			con = ConnectionFactory.createConnection(ConnectionURL,DB_Details[4],DB_Details[5]);
//			PreparedStatement stm=con.prepareStatement(query);
//			ResultSet rs=stm.executeQuery();
//			if(query.startsWith("select"))
//			{
//				if(rs.next())
//				{
//					if(Data.length>0)
//					{
//						for(int i=0;i<Data.length;i++)
//						{
//							Datam[i]=rs.getString(Data[i]);
//							//System.out.println("Initial Data --> "+Datam[i]);
//						}
//							int count = 0;
//							while(rs.next()){
//								for(int i=0;i<Data.length;i++)
//								{
//									Datam[i]=Datam[i]+","+rs.getString(Data[i]);
//									//System.out.println(count+" Data --> "+Datam[i]);
//								}
//								if(count==998)
//									break;
//								count++;
//							}
//					}
//				}else{
//					Data=null;
//					throw new RuntimeException("NO Data FOUND");
//				}
//			}
//		}catch(RuntimeException e){
//			if(e.getMessage().equals("NO Data FOUND")){
//
//				Report.fnReportFailAndTerminateTest("Data Not Available","NO Data FOUND" );
//			}
//		}
//			finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		return Data;
//	}
//	private String getCLI_SV(String CLI_OMP,boolean inContract,boolean creditClassCheck) {
//		String Data[]={"ACCOUNT_NAME"};
//		int i=0;
//		String[] Dat={"",""};
//		String[] CLI_ACC={"",CLI_OMP};
//		try{
//			for(i=0;i<198;i++)
//			{
//			CLI_ACC = getCLI_fineTune(CLI_ACC[1],inContract,creditClassCheck);
//			Dat=CLI_ACC[0].split(",");
//			String query = "select ACCOUNT_NAME from "+LoadEnvironment.SV_DBSCHEMA+".account a, "+LoadEnvironment.SV_DBSCHEMA+".customer_node_da_array nda where nda.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID "
//					+ "and nda.derived_attribute_id = '12000066' "
//					+ "AND SYSDATE BETWEEN nda.EFFECTIVE_START_DATE AND nda.EFFECTIVE_END_dATE "
//					+ "and nda.INDEX2_VALUE = '0' and ACCOUNT_NAME in '"+Dat[1]+"'";
//			Data=executeQuery(query,DBNames.SV,Data);
//			if(Data==null)
//			{
//				CLI_ACC[1]=removeCLI(CLI_ACC[1],Dat[0]);
//			}
//			else
//			{
//				i=200;
//			}
//			if(CLI_ACC[1]=="")
//			{
//				i=198;
//			}
//			}
//			if(i==198)
//			{
//				Report.fnReportFailAndTerminateTest("Data ISSUE","No Data Found");
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		return CLI_ACC[0];
//	}
//	
//	/**
//	 * @param packageID
//	 * @param search
//	 * @param searchValue
//	 * @return
//	 */
//	private String getCLI_CRM(String packageID, searchByData search,
//			String searchValue,boolean inContract) {
//
//		String CLI = null ;	
//		ResultSet rs = null;
//		String query1 = null; 
//
//		switch (search){
//		case Proposition:
//			if(inContract){
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
//							+ " and psp.id = ct.portfoliosalespackageid "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ "	and ct.terminationdate is null "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null";
//				}
//			}else{
//				if(searchValue.contains("NOT")){
//
//					searchValue = searchValue.substring(4);
//					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"; 
//
//				}else {
//					query1 ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//				}
//			}
//			break;
//		case Discount:
//			if(inContract){
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
//							+ " and psp.id = ct.portfoliosalespackageid "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ "	and ct.terminationdate is null "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";; 
//
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null ";
//				}
//			}else{
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null and  psp.isvalid = 1 "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1"; 
//				}else{
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//				}
//			}
//			break;
//		case Bundle:
//			if(inContract){
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
//							+ " and psp.id = ct.portfoliosalespackageid "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ "	and ct.terminationdate is null "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";; 
//
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null ";
//				}
//			}else{
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null and  psp.isvalid = 1 "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1"; 
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1";
//				}
//			}
//			break;
//		case blank:
//			/*query1 = "select CLI from CBLOWNER.portfoliosalespackage p where packageid in ('"+packageID+"') and"
//					    + " ((p.activationdate is not null and p.disconnectiondate is null and p.enddate is null)"
//						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
//						+ "	sysdate >= p.activationdate and sysdate < p.disconnectiondate)"
//						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
//						+ "	sysdate < p.activationdate)) and rownum <=1000";*/
//			if(inContract){
//				query1 = "select  cli from cblowner.portfoliosalespackage psp LEFT OUTER Join CBLOWNER.contract ct on "+
//						"	psp.id = ct.portfoliosalespackageid where "+
//						"	packageid in('"+packageID+"') "+
//						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
//						"	and psp.isvalid = 1 "+
//						"	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "+
//						"	and ct.terminationdate is null "+
//						"	MINUS "+
//						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
//						"	where p1.portfoliosalespackageid = P.id "+
//						"	and p1.tariffid in ('317') "+
//						"	and p.packageid in ('"+packageID+"') "+
//						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
//						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "+
//						"   ";	
//			}else{
//				query1 = "select  cli from cblowner.portfoliosalespackage psp"+
//						"	where "+
//						"	packageid in('"+packageID+"') "+
//						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
//						"	and psp.isvalid = 1 "+
//						"	MINUS "+
//						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
//						"	where p1.portfoliosalespackageid = P.id "+
//						"	and p1.tariffid in ('317') "+
//						"	and p.packageid in ('"+packageID+"') "+
//						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
//						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "+
//						"   ";	
//			}
//			break;	
//		}
//
//		try{
//			//System.out.println(query1);
//			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
//			//	System.out.println(ConnectionURL);
//			
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
//			PreparedStatement stm=con.prepareStatement(query1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			rs=stm.executeQuery();
//			if(rs.next())
//			{
//				rs.beforeFirst();
//				CLI = "'";
//				int count = 0;
//				while(rs.next()){
//					CLI=CLI+rs.getString(1);
//					CLI=CLI+"','";
//					if(count==998)
//						break;
//					count++;
//				}
//			}
//			else
//			{
//				CLI="";
//			}
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		//System.out.println("CLI FROM CRM " + CLI);
//		CLI=removeCLI(CLI,Str_ErrorCLI);
//		return CLI;
//	}
//	
//	/*private String getCLI_CRM_Modified(String packageID, searchByData search,
//			String searchValue,boolean inContract) {
//
//		String CLI = null ;	
//		ResultSet rs = null;
//			String queryPI ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null";
//
//			String 	queryPO ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//
//			String queryDI ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null ";
//
//			String queryDO ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//			String queryBI ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null ";
//			String queryBO ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1";
//			query1 = "select CLI from CBLOWNER.portfoliosalespackage p where packageid in ('"+packageID+"') and"
//					    + " ((p.activationdate is not null and p.disconnectiondate is null and p.enddate is null)"
//						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
//						+ "	sysdate >= p.activationdate and sysdate < p.disconnectiondate)"
//						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
//						+ "	sysdate < p.activationdate)) and rownum <=1000";
//			String queryI = "select  cli from cblowner.portfoliosalespackage psp LEFT OUTER Join CBLOWNER.contract ct on "+
//						"	psp.id = ct.portfoliosalespackageid where "+
//						"	packageid in('"+packageID+"') "+
//						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
//						"	and psp.isvalid = 1 "+
//						"	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "+
//						"	and ct.terminationdate is null "+
//						"	MINUS "+
//						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
//						"	where p1.portfoliosalespackageid = P.id "+
//						"	and p1.tariffid in ('317') "+
//						"	and p.packageid in ('"+packageID+"') "+
//						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
//						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "+
//						"   ";
//			String queryO = "select  cli from cblowner.portfoliosalespackage psp"+
//						"	where "+
//						"	packageid in('"+packageID+"') "+
//						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
//						"	and psp.isvalid = 1 "+
//						"	MINUS "+
//						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
//						"	where p1.portfoliosalespackageid = P.id "+
//						"	and p1.tariffid in ('317') "+
//						"	and p.packageid in ('"+packageID+"') "+
//						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
//						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "+
//						"   ";	
//		try{
//			System.out.println(queryPI);
//			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
//			//	System.out.println(ConnectionURL);
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
//			PreparedStatement stm=con.prepareStatement(queryPI);
//			rs=stm.executeQuery();
//			if(rs.next())
//			{
//				CLI = "'";
//				int count = 0;
//				while(rs.next()){
//					CLI=CLI+rs.getString(1);
//					CLI=CLI+"','";
//					if(count==998)
//						break;
//					count++;
//
//				}
//			}
//			else
//			{
//				CLI="";
//			}
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		//System.out.println("CLI FROM CRM " + CLI);
//		CLI=removeCLI(CLI,Str_ErrorCLI);
//		return CLI;
//	}*/
//	
//	
//	private String removeCLI(String CLI_CRM,String Str_ErrorCLI)
//	{
//		String[] Str_CLI=Str_ErrorCLI.split(",");
//		for(int i=0;i<Str_CLI.length;i++)
//		{
//			CLI_CRM = CLI_CRM.replace(Str_CLI[i]+",","");
//			CLI_CRM = CLI_CRM.replace(Str_CLI[i]+"','","");
//			CLI_CRM = CLI_CRM.replace(Str_CLI[i],"");
//			CLI_CRM = CLI_CRM.replaceAll(",'$", "");
//			CLI_CRM = CLI_CRM.replaceAll(",$", "");
//		}
//		return CLI_CRM;
//	}
//	
//	@SuppressWarnings("unused")
//	private String getCLI_CRM_Combined(String packageID, searchByData search,
//			String searchValue,boolean inContract) {
//
//		String CLI = null ;	
//		ResultSet rs = null;
//		String query1 = null; 
//
//		switch (search){
//		case Proposition:
//			if(inContract){
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
//							+ " and psp.id = ct.portfoliosalespackageid "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ "	and ct.terminationdate is null "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null";
//				}
//			}else{
//				if(searchValue.contains("NOT")){
//
//					searchValue = searchValue.substring(4);
//					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"; 
//
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.PORTFOLIO_ITEM_SUPP_SERVICE P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//				}
//			}
//			break;
//		case Discount:
//			if(inContract){
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
//							+ " and psp.id = ct.portfoliosalespackageid "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ "	and ct.terminationdate is null "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";; 
//
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null ";
//				}
//			}else{
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null and  psp.isvalid = 1 "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1"; 
//				}else{
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitemdiscount P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";
//				}
//			}
//			break;
//		case Bundle:
//			if(inContract){
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 =  "select  cli from cblowner.portfoliosalespackage psp,CBLOWNER.contract ct where packageid in('"+packageID+"') "
//							+ " and psp.id = ct.portfoliosalespackageid "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null"
//							+ " and psp.isvalid = 1 "
//							+ "	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ "	and ct.terminationdate is null "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1";; 
//
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P.id = ct.portfoliosalespackageid "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1"
//							+ " and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "
//							+ " and ct.terminationdate is null ";
//				}
//			}else{
//				if(searchValue.contains("NOT")){
//					searchValue = searchValue.substring(4);
//					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
//							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null and  psp.isvalid = 1 "
//							+ " MINUS "
//							+ " Select P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"')"
//							+ " and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null) "
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and "
//							+ " sysdate >= p1.activationdate and sysdate < p1.disconnectiondate) "
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and  p1.enddate is not null and"
//							+ " sysdate < p1.activationdate)) "
//							+ " and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1"; 
//				}else {
//
//					query1 ="Select Distinct P.CLI from cblowner.portfolioitembundle P1,cblowner.portfoliosalespackage P "
//							+ " where p1.portfoliosalespackageid = P.id "
//							+ " and P1.propositionid in ('"+searchValue+"') "
//							+ "	and p.packageid in('"+packageID+"') "
//							+ " and ((p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null)"
//							+ " or (p1.activationdate is not null and p1.disconnectiondate is not null and p1.enddate is not null and"
//							+ " sysdate >= p1.activationdate and"
//							+ " sysdate < p1.disconnectiondate)"
//							+ " or (p1.activationdate is not null and"
//							+ " p1.disconnectiondate is not null and"
//							+ "	p1.enddate is not null and"
//							+ "	sysdate < p1.activationdate))"
//							+ "	and p.activationdate is not null "
//							+ " and p.disconnectiondate is null and p.enddate is null and  p.isvalid = 1";
//				}
//			}
//			break;
//		case blank:
//			/*query1 = "select CLI from CBLOWNER.portfoliosalespackage p where packageid in ('"+packageID+"') and"
//					    + " ((p.activationdate is not null and p.disconnectiondate is null and p.enddate is null)"
//						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
//						+ "	sysdate >= p.activationdate and sysdate < p.disconnectiondate)"
//						+ "	or (p.activationdate is not null and p.disconnectiondate is not null and p.enddate is not null and"
//						+ "	sysdate < p.activationdate)) and rownum <=1000";*/
//			if(inContract){
//				query1 = "select  cli from cblowner.portfoliosalespackage psp LEFT OUTER Join CBLOWNER.contract ct on "+
//						"	psp.id = ct.portfoliosalespackageid where "+
//						"	packageid in('"+packageID+"') "+
//						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
//						"	and psp.isvalid = 1 "+
//						"	and SYSDATE between ct.STARTDATE+15 and ct.STARTDATE+(ct.CONTRACTTERM*30)-1 "+
//						"	and ct.terminationdate is null "+
//						"	MINUS "+
//						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
//						"	where p1.portfoliosalespackageid = P.id "+
//						"	and p1.tariffid in ('317') "+
//						"	and p.packageid in ('"+packageID+"') "+
//						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
//						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "+
//						"   ";	
//			}else{
//				query1 = "select  cli from cblowner.portfoliosalespackage psp"+
//						"	where "+
//						"	packageid in('"+packageID+"') "+
//						"	and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null "+
//						"	and psp.isvalid = 1 "+
//						"	MINUS "+
//						"	Select P.CLI from CBLOWNER.portfolioitemproduct P1,cblowner.portfoliosalespackage P "+ 
//						"	where p1.portfoliosalespackageid = P.id "+
//						"	and p1.tariffid in ('317') "+
//						"	and p.packageid in ('"+packageID+"') "+
//						"	and p1.activationdate is not null and p1.disconnectiondate is null and p1.enddate is null "+ 
//						"	and p.activationdate is not null and p.disconnectiondate is null and p.enddate is null and p.isvalid = 1 "+
//						"   ";	
//			}
//			break;	
//		}
//
//
//		try{
//			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
//			//	System.out.println(ConnectionURL);
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
//			PreparedStatement stm=con.prepareStatement(query1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			rs=stm.executeQuery();
//			CLI = "'";
//			int count = 0;
//			if(rs.next())
//			{
//				rs.beforeFirst();
//			while(rs.next()){
//				CLI=CLI+rs.getString(1);
//				CLI=CLI+"','";
//				if(count==998)
//					break;
//				count++;
//
//			}
//			}
//			else
//			{
//				CLI="";
//			}
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		//System.out.println("CLI FROM CRM " + CLI);
//		return CLI;
//	}
//	
//	@SuppressWarnings("unused")
//	private String shuffleCLI(String Str_CLI)
//	{
//		List<String> CLIs = Arrays.asList(Str_CLI.split(","));
//		Collections.shuffle(CLIs);
//		StringBuilder sb = new StringBuilder();
//		
//		for (String w : CLIs) 
//		{
//			sb.append(w);
//		    sb.append(",");
//		}
//		Str_CLI = sb.toString().trim().replaceAll(",$", "");
//		return Str_CLI;
//	}
//	
//	/**
//	 * @param cLI
//	 * @return
//	 */
//	@SuppressWarnings("finally")
//	public static int getCLIValidate(String cLI) {
//
//		ResultSet rs = null;
//		int returnval = 1;
//		String query1 = null; 
//
//		query1 = "select CLI from cblowner.portfoliosalespackage where CLI in ('"+cLI+"')";
//
//		try{
//			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
//			PreparedStatement stm=con.prepareStatement(query1);
//			rs=stm.executeQuery();
//
//			if(rs.next()){
//				returnval = 0;
//			}else{
//				returnval = 1;
//			}
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//			return returnval;
//		}
//
//	}
//	
//	@SuppressWarnings("finally")
//	public int Get_CPWNRef(String cPWN) throws Exception {
//		String query = null;
//		ResultSet rs = null;
//		PreparedStatement stm;
//		int returnval = 1;
//		
//		
//		con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+LoadEnvironment.SKID_DBIP+":"+LoadEnvironment.SKID_DBPORT+":"+LoadEnvironment.SKID_DBNAME,LoadEnvironment.SKID_DBUSERNAME,LoadEnvironment.SKID_DBPASSWORD);
//		query ="select NK_VALUE from T_NETWORK_KEYS where NK_VALUE = '"+cPWN+"'";
//		try{
//			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			rs=stm.executeQuery();
//			if(rs.next()){
//				returnval = 0;
//			}else{
//				returnval = 1;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//			System.out.println("CPWN REF is -->"+ cPWN);
//			return returnval;
//		}
//	}
//	

//	@SuppressWarnings("finally")
//	public String getAccountWithInvoice(){
//		String query = null;
//		ResultSet rs = null;
//		PreparedStatement stm;
//		String strCustInfo = "";
//		
//		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT + ":" + LoadEnvironment.SV_DBNAME;
//		//	System.out.println(ConnectionURL);
//		try{
//			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
//			query = "select distinct inv.account_id,acc.account_name from ops$svwpor1b.invoice inv,ops$svwpor1b.account acc where "
//						+ " acc.account_id=inv.account_id and "
//						+ " acc.account_name like '4%'";
//			stm = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			rs=stm.executeQuery();
//			if(rs.next()){
//				strCustInfo=rs.getString("account_name");
//			}else{
//				Report.fnReportFail("NO Account with Invoice present in SV DB");
//				strCustInfo = null;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			ConnectionFactory.closeConnection(con);
//			return strCustInfo;
//		}
//	}
//}
//


package com.SharedModules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import com.Engine.LoadEnvironment;
import com.Engine.Reporter;



public class NewDatabase{


	public Reporter Report;
	public NewDatabase(Reporter report) {
		Report=report;
	}
	public enum searchByData{
		Proposition,Bundle,Discount,blank,TVProfile
	}

	private  Connection con=null;
	private  String CLI_OMP = null;
	private  String CLI_CRM = null;
	private  String CLI_CLI = null;
	private  String CLI_final = null;
	private  String CLI_OMP2= null;
	private  String Customer_numbers = "";
	private  String Customer_numbers1 = "";
	private  String Customer_numbers2 = "";
	private  String Customer_numbers3 = "";
	private  String Account_Array ="";
	private  String CLI_Array = "";
	private  String CLI_Array1 = "";
	private  String Account_Array1 ="";
	private  String Account_Array2 = "";

	String Data[][] = new String[350][2];

	public  String getDataNEW(String packageID, searchByData search, String searchValue, boolean inContract, boolean creditClassCheck) {


		//System.out.println(packageID);
		searchValue=searchValue.replace(",", "','");
		packageID=packageID.replace(",", "','");
		CLI_CRM = getCLI_CRM(packageID,search,searchValue,inContract);

		if(CLI_CRM!=null){
			System.out.println(" DATA FROM CRM");
			CLI_OMP  = getCLI_OMP(CLI_CRM);
		}else{
			System.out.println("NO DATA FROM OMP");
		}
		if(CLI_OMP != null){
			System.out.println("DATA FROM CLI DB");
			//CLI_CLI = getCLI_CLIDB(CLI_OMP);
			CLI_CLI = CLI_OMP;
			System.out.println(CLI_OMP);
		}else{
			System.out.println("NO DATA FROM CLIDB");
		}
		if(CLI_CLI != null){
			System.out.println("DATA FROM FineTune DB");
			CLI_final = getCLI_fineTune(CLI_CLI,creditClassCheck);
		}else{
			System.out.println("NO DATA to fine tune");
		}
		return CLI_final;
	}
	
	public String getDataNEW_AIP(String packageID, searchByData search, String searchValue, boolean inContract, boolean creditClassCheck, String TVAccountType) {

		//System.out.println(packageID);
		searchValue=searchValue.replace(",", "','");
		packageID=packageID.replace(",", "','");
		CLI_CRM = getCLI_CRM_AIP(packageID,TVAccountType);
		
//		CLI_CRM = getCLI_CRM_AIP(packageID,TVAccountType,searchValue);

		if(CLI_CRM!=null){
			System.out.println(" DATA FROM CRM");
			CLI_OMP  = getCLI_OMP(CLI_CRM);
		}else{
			System.out.println("NO DATA FROM OMP");
		}
		if(CLI_OMP != null){
			System.out.println("DATA FROM CLI DB");
			//CLI_CLI = getCLI_CLIDB(CLI_OMP);
			CLI_CLI = CLI_OMP;
			System.out.println(CLI_OMP);
		}else{
			System.out.println("NO DATA FROM CLIDB");
		}
		if(CLI_CLI != null){
			System.out.println("DATA FROM FineTune DB");
			CLI_final = getCLI_fineTune(CLI_CLI,creditClassCheck);
		}else{
			System.out.println("NO DATA to fine tune");
		}
		return CLI_final;
	}
	
	
	@SuppressWarnings("finally")
	public String Get_BlacklistCustomer(){
		String query = null;
		ResultSet rs = null;
		PreparedStatement stm;
		String strCustInfo = "";
		
		
		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
		//	System.out.println(ConnectionURL);
		try{
			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
			query = "select CUSTOMERNUMBER,FIRSTNAME,FAMILYNAME from cblowner.v_blacklist_search where rownum=1";
			stm = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stm.executeQuery();
			if(rs.next()){
				strCustInfo=rs.getString("FIRSTNAME") + "," + rs.getString("FAMILYNAME");
			}else{
				Report.fnReportFail("NO BLACKLIST DATA IN CRM");
				strCustInfo = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
			return strCustInfo;
		}
	}

	public  String BC_98() throws Exception{
		System.out.println("-------------------------------BC_98--------------------------------------------");
		System.out.println("BC_98 ");
		//Account_Array=Account_Array.replace(",", "','");	
		String query = null;
		PreparedStatement stm = null;
		ResultSet RS = null;
		String AccountNew= "";
		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.SV_DBIP+":"+LoadEnvironment.SV_DBPORT+":"+LoadEnvironment.SV_DBNAME;
		Connection con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.SV_DBUSERNAME,LoadEnvironment.SV_DBPASSWORD);
		try{
			//query="select distinct a.account_name from "+LoadEnvironment.SV_DBSCHEMA+".account a,"+LoadEnvironment.SV_DBSCHEMA+".customer_node_history his,"+LoadEnvironment.SV_DBSCHEMA+".schedule s,"+LoadEnvironment.SV_DBSCHEMA+
			//		".invoice i where his.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID and i.ACCOUNT_ID= a.ACCOUNT_ID and s.SCHEDULE_ID = his.SCHEDULE_ID and s.schedule_name like 'BC_Pending' and his.PAYMENT_METHOD_CODE='0' and i.GENERAL_5 like'%TTK%' ";
				
//			actual query  
			query="select * from "+LoadEnvironment.SV_DBSCHEMA+".account where account_id in" +
			"(select prime_account_id from "+LoadEnvironment.SV_DBSCHEMA+". customer_node_history where schedule_id =(select schedule_id  from schedule where schedule_name like 'BC_Pending'))"+
			"and invoice_id is NULL"; 
			
//			query="select distinct a.account_name from "+LoadEnvironment.SV_DBSCHEMA+".account a,"+LoadEnvironment.SV_DBSCHEMA+".customer_node_history his,"+LoadEnvironment.SV_DBSCHEMA+".schedule s,"+LoadEnvironment.SV_DBSCHEMA+
//							".invoice i where his.CUSTOMER_NODE_ID=a.CUSTOMER_NODE_ID and i.ACCOUNT_ID= a.ACCOUNT_ID and s.SCHEDULE_ID = his.SCHEDULE_ID and s.schedule_name like 'BC_Pending'";
//			
			//System.out.println(query);
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
					System.out.println("Account--> "+RS.getString("ACCOUNT_NAME")+",");
				AccountNew= AccountNew+RS.getString("ACCOUNT_NAME")+",";
					//AccountNew= RS.getString("ACCOUNT_NAME");
				System.out.println("Account--> "+RS.getString("ACCOUNT_NAME")+",");
					break;		
			}

		}catch(Exception e){

		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return AccountNew;
	}


	private  String getCLI_fineTune(String CLI_OMP2,boolean creditClassCheck) {

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

			System.out.println("Customer_numbers2"+Customer_numbers2);


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
			System.out.println("Customer_numbers3"+Customer_numbers3);

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



			System.out.println("CLI_Array1"+CLI_Array1);


			CLI_Array1=CLI_Array1.replace(",", "','");	
			query3 = "select ACCOUNTNUMBER from CBLOWNER.v_customer_search where CLI in ('"+CLI_Array1+"')";
			stm=con.prepareStatement(query3);
			rs1=stm.executeQuery();

			while(rs1.next()){
				Account_Array = Account_Array+rs1.getString("ACCOUNTNUMBER")+",";
			}

			System.out.println("Account_Array  "+Account_Array);


			if(creditClassCheck){
				System.out.println("-------------------------------in Credit Check--------------------------------------------");
				String Acc = ZeroBalCustomet(Account_Array);
				Acc = CollectionFreeCustomer(Acc);

				Account_Array=Acc;				
			}
			System.out.println(Account_Array);
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
			//System.out.println("random number - "+i);
			System.out.println("COUNT of CLI Retrived - "+cliList.size());
			ranCLIAccNumber = cliList.get(i).toString()+","+accList.get(i).toString();
			System.out.println("Final Random CLI and Account Number -"+ranCLIAccNumber);

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


	public  String ZeroBalCustomet(String Account_Array) throws Exception{
		System.out.println("-------------------------------in Zero Balance Check--------------------------------------------");
		System.out.println("IN ZERO BAL ---- > "+Account_Array);
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

	public  String CollectionFreeCustomer(String Account_Array) throws Exception{
		//System.out.println("-------------------------------in Zero Balance Check--------------------------------------------");
		//System.out.println("IN ZERO BAL ---- > "+Account_Array);
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
			//System.out.println(query);
			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			RS=	stm.executeQuery();
			while(RS.next()){
				//System.out.println("CUSTOMER_NODE_ID--> "+RS.getString("CUSTOMER_NODE_ID")+",");
				custnodeid_collfree= custnodeid_collfree+RS.getString("CUSTOMER_NODE_ID")+",";
			}
			custnodeid_collfree = custnodeid_collfree.replace(",","','");
			String query1= "SELECT * from "+LoadEnvironment.SV_DBSCHEMA+".account where  CUSTOMER_NODE_ID in('"+custnodeid_collfree+"')";
			//System.out.println(query1);
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


	private  String getCLI_OMP(String cLI_CRM) {
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
					System.out.println("--------------------"+count+"--------------------");
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
					System.out.println("--------------------"+count+"--------------------");
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
	
//	public int ManageCLIDB_ResourceProductDetailsVerification(String Account, String AccessMethod) throws Exception{
//		String query = null;
//		ResultSet rs = null;
//		String ResourceId = null;
//		int ResourceTypeId = 0;
//		con = ConnectionFactory.createConnection("jdbc:oracle:thin:@"+LoadEnvironment.CLI_DBIP+":"+LoadEnvironment.CLI_DBPORT+":"+LoadEnvironment.CLI_DBNAME,LoadEnvironment.CLI_DBUSERNAME,LoadEnvironment.CLI_DBPASSWORD);
//		try{			
//			
//			query="select * from T_ACCESS_METHOD_INSTANCE where ACCOUNT_ID = '"+Account+"'and ACCESS_METHOD_PRODUCT_ID='"+AccessMethod+"' ";
//			System.out.println(query);
//			stm=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			rs=stm.executeQuery();
//			System.out.println("Query Executed");
//
//			if(rs.next()){
//				rs.beforeFirst();
//				while(rs.next()){
//					ResourceId=rs.getString("RESOURCE_ID");
//					System.out.println("Resource ID for the data is----->"+ResourceId);
//					Report.fnReportPass("Resource ID for the data is----->"+ResourceId);
//				
//					ResourceTypeId=rs.getInt("RESOURCE_TYPE_ID");
//					System.out.println("Resource Type ID for the data is----->"+ResourceTypeId);
//					Report.fnReportPass("Resource Type ID for the data is----->"+ResourceTypeId);
//					rs.afterLast();
//				}
//			}else{
//				throw new RuntimeException("NO ROWS");
//			}
//		}catch(RuntimeException e){
//			if(e.getMessage().equals("NO ROWS")){
//				Report.fnReportFail("Resource ID and Resorce Type are not updaetd for this CLI");
//			}
//		}finally{
//			ConnectionFactory.closeConnection(con);
//		}
//		return ResourceTypeId;
//	}
	
	private String getCLI_CRM_AIP(String packageID, String TVAccountType) {
		
		String CLI = null ;	
		ResultSet rs = null;
		String query1 = null; 
		
		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
		
		try{
			
		query1=	"select ps.cli from cblowner.portfolioitemproduct pp,cblowner.portfoliosalespackage ps where ps.id=pp.portfoliosalespackageid and pp.TVACCOUNTTYPE = ('"+TVAccountType+"') and ps.PACKAGEID= ('"+packageID+"')";   
		System.out.println("query1 :" + query1);
		
//		query1=	"select ps.cli from cblowner.portfolioitemproduct pp,cblowner.portfoliosalespackage ps where ps.id=pp.portfoliosalespackageid and pp.TVACCOUNTTYPE = ('"+TVAccountType+"') and ps.PACKAGEID= ('"+packageID+"') and pp.PROPOSITIONID= ('"+PropositionId+"')";   
		
		PreparedStatement stm=con.prepareStatement(query1);
		rs=stm.executeQuery();
		CLI = "'";
		int count = 0;
		while(rs.next()){
			CLI=CLI+rs.getString(1);
			CLI=CLI+"','";
			if(count==700)
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
	}
		return CLI;
	}
	
private String getCLI_CRM_AIP(String packageID, String TVAccountType, String PropositionId) {
		
		String CLI = null ;	
		ResultSet rs = null;
		String query1 = null; 
		
		String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT + ":" + LoadEnvironment.CRM_DBNAME;
		con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
		
		try{
			
//		query1=	"select ps.cli from cblowner.portfolioitemproduct pp,cblowner.portfoliosalespackage ps where ps.id=pp.portfoliosalespackageid and pp.TVACCOUNTTYPE = ('"+TVAccountType+"') and ps.PACKAGEID= ('"+packageID+"')";   
//		System.out.println("query1 :" + query1);
		
		query1=	"select ps.cli from cblowner.portfolioitemproduct pp,cblowner.portfoliosalespackage ps where ps.id=pp.portfoliosalespackageid and pp.TVACCOUNTTYPE = ('"+TVAccountType+"') and ps.PACKAGEID= ('"+packageID+"') and pp.PROPOSITIONID= ('"+PropositionId+"')";   
		System.out.println("query1 :" + query1);
		
		PreparedStatement stm=con.prepareStatement(query1);
		rs=stm.executeQuery();
		CLI = "'";
		int count = 0;
		while(rs.next()){
			CLI=CLI+rs.getString(1);
			CLI=CLI+"','";
			if(count==700)
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
	}
		return CLI;
	}

	private  String getCLI_CRM(String packageID, searchByData search,
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
							+ " and p.activationdate <= sysdate-40";
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
		case TVProfile:
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
					
//					select ps.cli,pp.TVACCOUNTTYPE  from cblowner.portfolioitemproduct pp,cblowner.portfoliosalespackage ps where ps.id=pp.portfoliosalespackageid
//							and pp.TVACCOUNTTYPE =    

					query1 ="Select Distinct P.CLI from cblowner.portfolioitemproduct P1,cblowner.portfoliosalespackage P,CBLOWNER.contract ct "
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
					System.out.println(query1);
				}
			}else{
				if(searchValue.contains("NOT")){
					searchValue = searchValue.substring(4);
					query1 = "select  cli from cblowner.portfoliosalespackage psp where packageid in('"+packageID+"') "
							+ " and psp.activationdate is not null and psp.disconnectiondate is null and psp.enddate is null and  psp.isvalid = 1 "
							+ " and psp.activationdate <= sysdate-40"
							+ " MINUS "
							+ " Select P.CLI from cblowner.portfolioitemproduct P1,cblowner.portfoliosalespackage P "
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

					query1 ="Select Distinct P.CLI from cblowner.portfolioitemproduct P1,cblowner.portfoliosalespackage P "
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
						+ " and p.activationdate <= sysdate-100";
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
						+ " and p.activationdate <= sysdate-100";
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
				if(count==700)
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
	
	/**
	 * @param cLI
	 * @return
	 */
	@SuppressWarnings("finally")
	public int getMobilenoValidate(String Mobileno) {

		ResultSet rs = null;
		int returnval = 1;
		String query1 = null; 

		query1 = "select MOBILENUMBER from cblowner.v_mobile_acc_subscribe_details where MOBILENUMBER in ('"+Mobileno+"')";
		System.out.println("MobileNumber is" + query1);

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



	public String getAcc_CLI(String CLI_ACC,boolean Boolean_CLI_ACC) {
		String ACC_CLI = null;
		String query=null;
		try{
			String ConnectionURL = "jdbc:oracle:thin:@"+LoadEnvironment.CRM_DBIP+":"+LoadEnvironment.CRM_DBPORT+":"+LoadEnvironment.CRM_DBNAME;
			//				System.out.println(ConnectionURL);

			con = ConnectionFactory.createConnection(ConnectionURL,LoadEnvironment.CRM_DBUSERNAME,LoadEnvironment.CRM_DBPASSWORD);
			CLI_ACC=CLI_ACC.replaceAll(",", "','");
			if(Boolean_CLI_ACC)
			{
				query = "select ACCOUNTNUMBER from CBLOWNER.v_customer_search where CLI in ('"+CLI_ACC+"')";
			}
			else
			{
				query = "select CLI from CBLOWNER.v_customer_search where ACCOUNTNUMBER in ('"+CLI_ACC+"')";
			}
			//			System.out.println(query);
			PreparedStatement stm=con.prepareStatement(query);
			ResultSet rs=stm.executeQuery();
			int count=0;
			while(rs.next()){
				if(ACC_CLI == null){
					ACC_CLI=rs.getString(1);
					ACC_CLI=ACC_CLI+",";
				}else{

					ACC_CLI=ACC_CLI+rs.getString(1);
					ACC_CLI=ACC_CLI+",";
				}
				if(count==998)
					break;
			}
			ACC_CLI = ACC_CLI.substring(0, ACC_CLI.length() - 1);
			//			System.out.println(ACC_CLI);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return ACC_CLI;
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
			System.out.println("CPWN REF is -->"+ cPWN);
			return returnval;
		}
	}
}
