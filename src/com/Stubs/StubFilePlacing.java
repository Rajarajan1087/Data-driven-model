package com.Stubs;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.SharedModules.Constants;
import com.SharedModules.RandomGenerator;
import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.Engine.SeleniumSetup;
import com.Utils.Reusables;
import com.jcraft.jsch.*;


public class StubFilePlacing implements Constants {
	public Reporter Report;

	/**
	 * @param report
	 */
	public StubFilePlacing(Reporter report) {
		Report = report;
	}

	public StubFilePlacing() {
	}
	public enum LineType{
		BT,Opal
	}
	public enum ALKType{
		BTStop,BTLive,NotSet
	}
	public enum PortingRH{
		Sky,Virgin,BT,Others
	}
	//LCPTWS2178
	public enum PortingErrorCode{
		LCPTWS2163,LCPTWS2181,LCPTWS2166,Noerrorcode,LCPTWS2180,LCPTWSEC2178,LCPTWS2178
	}
	public enum NumberPortingInformation{
		NPORInvolved,NoPortingAgreement
	}
	public enum PortingStatus{
		PortingRequired,PortingRequiredNoAgreement
	}
	public boolean ALIwithC=true;
	public enum StubType{
		IPTVProvideNewFTTP,Porting_ALK,ExchangeStatus_Porting,NPAC_Porting_Sky,NPAC_Porting_Virgin,NPAC_Porting_BT,NPAC_Low_LLU_ALK,NPAC_Low_ExchangeStatus,NPAC_CP_MC_NoFibre,NPAC_LLU_ALK_Newline_NoFibre,NPAC_CP_MC_PortingVirgin,NPAC_CP_MC_PortingSky,NPAC_CP_MC_CeaseTrue,NPAC_Check_Nominated_CLI,NPAC_ACErrorResponse,LLU_ALK_LIVE,NPAC_LLU_ALK_LIVE,LLU_ALK_OPALLive,NGAv2CLI,LLUv5_CeasePending,NGAv2CLI_new,IPTVM,QS3,Check_Nominated_CLI,Check_CLI_Poratability,Working_Error_Response,IPTVProvide,IPTVModify,
		IPTVCease,IPTVSuspend,LLUv5,QS4,LLU_ALK_BTStop,ExchangeStatus,ExchangeStatusUnicast,PostCode_GoldAddress,NGA_ALK,NGAALK_new,NGA_ALK_TEST,NGAALK_new_SpeedLT5,NGAGEA,LLU_CPS_LowSpeed,
		LLU_ALK_LowSpeed,CreditCheck_TotalAccept,CreditCheck_ConditionalAccept,CreitCheck_TotalDecline,CreditCheck_Referral,NPAC_LLU_ALK_OPALLive_ALI13withCease,NPAC_LLU_ALK_OPALLive_ALI13withCeaseALIStartsWithA,
		LLU_CPS_PORT_RH_Virgin,ACErrorResponseVirgin,ACErrorResponseBT,LLU_ALK_BTLive,ACErrorResponseSky,MatchAddressResponse,
		GetCPEResponse,IPS,Low_ExchangeStatus,SMPF,LLU_ALK_BTStop_Unicast,RetainNumber,Check_Nominated_CLI1,Check_Nominated_CLI2,
		NGAGEA_SINO_MILT5,LLU_ALK_BTStop_LLU,NGAv2CLI_new_SI_LE5mbps,NGAALK_new_LE5Mbps,NGAALK_SINO_MIYES,LLU_ALK_BTLive_Unicast,
		Check_Nominated_CLI_Sales,IPTVU,BTPorting,Check_Nominated_CLI_BT,Check_Nominated_CLI_Sky,Check_CLI_Poratability_Sky,Lowspeed,
		LLU_ALK_Newline,LLU_ALK_Newline_Unicast,NGAv2CLI_ENG_new,UPRN_Lightning,IPTVProvideNew,IPTVModifyNew,UPRN_Porting,WorkingLinewithoutCeasePending,
		WorkingLinewithCeasePending,LLU_ALK_BTLive_WithCeasePending,MultipleStopandWorkinglines_WithCeasePending,LLUv5_GEA,
		IPTVM_CeaseOrder,LLU_ALK_BTLiveGEA,LLU_ALK_Newline_AMBER,LLUv5_PostCodeMatchNO,IPTVM_PostCodeMatchNo,NGAv2CLI_PostCodeMatchNO,NGAGEAError,
		NPAC_CP_UC,NPAC_CP_MC,NPAC_CP_MC_FibreMI,NPAC_CP_MC_TVStore,NPAC_CP_DoubleMigrate,NPAC_ExchangeStatus,NPAC_LLU_ALK_BTLive,NPAC_LLU_ALK_OPALLive_ALI13,NPAC_LLU_ALK_BTLive_ALI13,NPAC_LLU_ALK_BTLive_ALI14,NPAC_LLU_ALK_BTLive_ALI15,NPAC_LLU_ALK_BTStop,NPAC_NGA_GEA,NPAC_LLU_ALK_Newline,NPAC_LLU_ALK_BTLiveUni,
		NPAC_LLU_ALK_NewlineUni,NPAC_LLU_ALK_BTStopUni,NPAC_Lowspeed,NPAC_NGA_SI_LE5Mbps,NPAC_BTStop_NGA_LE5_SI,NPAC_NGAALK_SINO_MIYES,
		NPAC_NGAGEA_SINO_MILT5,NPAC_LLU_ALK_BTLive_Withcease,NPAC_LLU_ALK_OpalLive,NPAC_LLU_ALK_OpalLiveWithCease,NPAC_CP_MC_NoPCMatch,NPAC_LLU_ALK_BTLiveGEA,NPAC_MUL_1SLTO_1WLTO,NPAC_MUL_1SLTO_2WLTO_NEWLINE,NPAC_MUL_1SLTO_1WLTO_MIGRATEWITHALI,
		NPAC_MUL_1SLTO_1WLTO_withCease,NPAC_MUL_1SLTO_2WLTO_CEASEPENDING,NPAC_MUL_1SL_3WL_2PENDINGCEASE,NPAC_MUL_LINES,NPAC_UPRNLightning,NPAC_UPRNPorting,NPAC_LLU_ALK_Newline_Amber,NPAC_LLU_ALK_OPALLIVE_UNICAST,NPAC_LLU_ALK_OPALLIVE_GEA_821		
	}

	public enum SFTPStubType{
		DERBY,AUTOMATION
	}

	public enum AppoitnmentStubType{
		BT,EVG
	}
	public boolean IsUnicast=false;
	public boolean IsAmber=false;
	public boolean IPTVSelf=true;
	private File StubFileGenerate(String Str_CLI,String Str_TempFile,String Str_OutFileName,boolean...Past_Future){
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Report.fnReportFailAndTerminateTest("FileNotFoundException", "FileNotFoundException : "+Str_TempFile);
		}
		String line = "", oldtext = "";
		try {
			while((line = reader.readLine()) != null)
				oldtext += line + "\r\n";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("IOException", "IOException in reading template file");
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("IOException", "IOException in closing template file");
		}
		String newtext = oldtext.replaceAll("replaceMe",Str_CLI );
		String ALI = "C12"+RandomGenerator.randomCLI(7);
		newtext = newtext.replaceAll("xxxxx",ALI );

		newtext = newtext.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 10));
		if(Past_Future.length>0&&Past_Future[0])
		{
			newtext = newtext.replaceAll("M_CeaseDate",  Reusables.getdateFormat("yyyy-MM-dd", -1));
		}
		else{
			newtext = newtext.replaceAll("M_CeaseDate",  Reusables.getdateFormat("yyyy-MM-dd", 30));
		}
		if(IPTVSelf)
		{
			newtext = newtext.replaceAll("M_SI",  "true");
		}
		else{
			newtext = newtext.replaceAll("M_SI",  "false");
		}
		//for IPTV Stubs

		newtext = newtext.replaceAll("MYBOOKEDDATE", Reusables.getdateFormat("yyyy-MM-dd", 15));
		if(IsUnicast)
		{
			newtext = newtext.replaceAll("M_MCapacity", "R");
			newtext = newtext.replaceAll("M_MSpeed", "False");
		}
		else{
			newtext = newtext.replaceAll("M_MCapacity", "G");
			newtext = newtext.replaceAll("M_MSpeed", "True");
		}
		if(IsAmber)
		{
			newtext = newtext.replaceAll("M_ExchangeCapacity", "A");
		}
		else{
			newtext = newtext.replaceAll("M_ExchangeCapacity", "G");
		}
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		try {
			FileWriter writer = new FileWriter(newfile);
			writer.write(newtext);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("IOException", "IOException in Stub File writer");
		}
		System.out.println(newfile);
		return newfile;
	}
	private File StubFileGenerate_ALI(String Str_CLI,String Str_TempFile,String Str_OutFileName,int ALIDigits,boolean...Past_Future){
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			Report.fnReportFailAndTerminateTest("FileNotFoundException", "FileNotFoundException : "+Str_TempFile);
		}
		String line = "", oldtext = "";
		try {
			while((line = reader.readLine()) != null)
				oldtext += line + "\r\n";

			if(Past_Future.length>0&&Past_Future[0])
			{
				oldtext = oldtext.replaceAll("M_CeaseDate",  Reusables.getdateFormat("yyyy-MM-dd", 4));
			}
			else{
				oldtext = oldtext.replaceAll("M_CeaseDate",  Reusables.getdateFormat("yyyy-MM-dd", 30));
			}
			if(IPTVSelf)
			{
				oldtext = oldtext.replaceAll("M_SI",  "true");
			}
			else{
				oldtext = oldtext.replaceAll("M_SI",  "false");
			}
			//for IPTV Stubs

			oldtext = oldtext.replaceAll("MYBOOKEDDATE", Reusables.getdateFormat("yyyy-MM-dd", 15));
			if(IsUnicast)
			{
				oldtext = oldtext.replaceAll("M_MCapacity", "R");
				oldtext = oldtext.replaceAll("M_MSpeed", "False");
			}
			else{
				oldtext = oldtext.replaceAll("M_MCapacity", "G");
				oldtext = oldtext.replaceAll("M_MSpeed", "True");
			}
		} catch (IOException e) {
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("IOException", "IOException in reading template file");
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("IOException", "IOException in closing template file");
		}

		String newtext = oldtext.replaceAll("replaceMe",Str_CLI );
		String ALI = "12"+RandomGenerator.randomCLI(ALIDigits-5);
		if(ALIwithC)
		{
			ALI="C"+ALI;
		}
		else{
			ALI="A"+ALI;
		}
		newtext = newtext.replaceAll("xxxxx",ALI );

		newtext = newtext.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 10));
		if(Past_Future.length>0&&Past_Future[0])
		{
			newtext = newtext.replaceAll("M_CeaseDate",  Reusables.getdateFormat("yyyy-MM-dd", 4));
		}
		else{
			newtext = newtext.replaceAll("M_CeaseDate",  Reusables.getdateFormat("yyyy-MM-dd", 30));
		}

		//for IPTV Stubs

		newtext = newtext.replaceAll("MYBOOKEDDATE", Reusables.getdateFormat("yyyy-MM-dd", 15));
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		try {
			FileWriter writer = new FileWriter(newfile);
			writer.write(newtext);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("IOException", "IOException in Stub File writer");
		}
		System.out.println(newfile);
		return newfile;
	}
	public File StubFileGenerate_Porting(String Str_CLI,String Str_TempFile,String Str_OutFileName,String Postcode,PortingRH RH,ALKType ALK,PortingStatus PS,NumberPortingInformation NPI,PortingRH CP) throws Exception{
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();



		String newtext = oldtext.replaceAll("replaceMe",Str_CLI);

		newtext = newtext.replaceAll("repalceALKType",ALK.name());

		newtext = newtext.replaceAll("replaceRH",RH.name());
		newtext = newtext.replaceAll("replaceNPI",NPI.name());
		newtext = newtext.replaceAll("replacePS",PS.name());
		newtext = newtext.replaceAll("M_CP",CP.name());
		newtext = newtext.replaceAll("replacePC",Postcode);
		if(IsUnicast)
		{
			newtext = newtext.replaceAll("M_MCapacity", "R");
			newtext = newtext.replaceAll("M_MSpeed", "False");
		}
		else{
			newtext = newtext.replaceAll("M_MCapacity", "G");
			newtext = newtext.replaceAll("M_MSpeed", "True");
		}

		String ALI = "C12"+RandomGenerator.randomCLI(7);
		newtext = newtext.replaceAll("xxxxx",ALI );

		newtext = newtext.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 20));

		//for IPTV Stubs

		newtext = newtext.replaceAll("MYBOOKEDDATE", Reusables.getdateFormat("yyyy-MM-dd", 15));
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		System.out.println(newfile);
		return newfile;
	}
	public File StubFileGenerate_PortingCLI(String Str_CLI,String Str_PostCode,String Str_TempFile,String Str_OutFileName,PortingRH RH,PortingErrorCode PEC,NumberPortingInformation NPI,PortingStatus PS) throws Exception{
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();
		String ErrorValue="<ns2:Errors><ns2:Error><ns2:ErrorCategory>RequestError</ns2:ErrorCategory><ns2:ErrorCode>ChangeIT</ns2:ErrorCode></ns2:Error></ns2:Errors>";
		String newtext = oldtext.replaceAll("replaceMe",Str_CLI);
		newtext = newtext.replaceAll("M_ErrorStatus","true");
		newtext = newtext.replaceAll("M_ErrorMessage",ErrorValue.replaceAll("ChangeIT", PEC.name()));
		if(IsUnicast)
		{
			newtext = newtext.replaceAll("M_MCapacity", "R");
			newtext = newtext.replaceAll("M_MSpeed", "False");
		}
		else{
			newtext = newtext.replaceAll("M_MCapacity", "G");
			newtext = newtext.replaceAll("M_MSpeed", "True");
		}
		newtext = newtext.replaceAll("M_MRH",RH.name());
		newtext = newtext.replaceAll("M_CLI",Str_CLI);
		newtext = newtext.replaceAll("M_PS",PS.name());
		newtext = newtext.replaceAll("M_NPI",NPI.name());
		newtext = newtext.replaceAll("M_PostCode",Str_PostCode);
		//for IPTV Stubs

		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		System.out.println(newfile);
		return newfile;
	}
	public File StubFileGenerate_NoPortingCLI(String Str_CLI,String Str_PostCode,String Str_TempFile,String Str_OutFileName,PortingRH RH, String Str_CurrentCP) throws Exception{
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();
		String newtext = oldtext.replaceAll("replaceMe",Str_CLI);
		if(IsUnicast)
		{
			newtext = newtext.replaceAll("M_MCapacity", "R");
			newtext = newtext.replaceAll("M_MSpeed", "False");
		}
		else{
			newtext = newtext.replaceAll("M_MCapacity", "G");
			newtext = newtext.replaceAll("M_MSpeed", "True");
		}

		if(IsAmber)
		{
			newtext = newtext.replaceAll("M_ExchangeCapacity", "A");
		}
		else{
			newtext = newtext.replaceAll("M_ExchangeCapacity", "G");
		}
		newtext = newtext.replaceAll("M_MRH",RH.name());
		newtext = newtext.replaceAll("M_CLI",Str_CLI);
		newtext = newtext.replaceAll("M_CP",Str_CurrentCP);
		newtext = newtext.replaceAll("M_PostCode",Str_PostCode);
		//for IPTV Stubs

		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		System.out.println(newfile);
		return newfile;
	}
	private File StubFileGenerate_Multiple(String Str_TempFile,String Str_OutFileName,String...Data) throws Exception{
		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();
		String newtext = oldtext;
		if(Data.length>0)
		{
			for(int i=0;i<Data.length;i++)
			{
				newtext = oldtext.replaceAll("M_DATA"+(i+1),Data[i] );
			}
		}
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);
		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		return newfile;
	}
	private File StubFileGenerate_ALI(String Str_CLI,String ALI,String Str_TempFile,String Str_OutFileName) throws Exception{
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();

		String newtext = oldtext.replaceAll("replaceMe",Str_CLI );
		newtext = newtext.replaceAll("replaceAli", Str_CLI);

		newtext = newtext.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 10));

		//for IPTV Stubs

		newtext = newtext.replaceAll("MYBOOKEDDATE", Reusables.getdateFormat("yyyy-MM-dd", 15));
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		System.out.println(newfile);
		return newfile;
	}
	private File StubFileGenerate_Lightning(String UPRN,String Rface,String Str_Postcode,String Str_TempFile,String Str_OutFileName,String RHType,String InstallType) throws Exception{
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();

		String newtext = oldtext.replaceAll("Rfacereplace",Rface);
		newtext = newtext.replaceAll("postcodereplace", Str_Postcode);
		newtext = newtext.replaceAll("UPRNreplace", UPRN);

		newtext = newtext.replaceAll("RHreplace", RHType);
		if(RHType.equals("BT"))
		{
			newtext = newtext.replaceAll("PortStatus_Replace", "PortingRequiredDirect");
		}
		else
		{
			newtext = newtext.replaceAll("PortStatus_Replace", "PortingRequired");	
		}
		newtext = newtext.replaceAll("InstallTypeReplace", InstallType);

		//for IPTV Stubs
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		//System.out.println(newfile);
		return newfile;
	}
	private File StubFileGenerate_Lightning_Porting(String UPRN,String Rface,String Str_Postcode,String Str_PortingRH,String Str_TempFile,String Str_OutFileName) throws Exception{
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();

		String newtext = oldtext.replaceAll("Rfacereplace",Rface);
		newtext = newtext.replaceAll("postcodereplace", Str_Postcode);
		newtext = newtext.replaceAll("bname", "null");
		newtext = newtext.replaceAll("UPRNreplace", UPRN);
		newtext = newtext.replaceAll("numberRangeHolderReplace", Str_PortingRH);

		//for IPTV Stubs


		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		//System.out.println(newfile);
		return newfile;
	}
	private File StubFileGenerate_NGA(String Str_CLI,String PostCode,String Str_TempFile,String Str_OutFileName) throws Exception{
		//System.out.println(Str_CLI);

		File file = new File(Str_TempFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", oldtext = "";
		while((line = reader.readLine()) != null)
			oldtext += line + "\r\n";
		reader.close();

		String newtext = oldtext.replaceAll("replaceMe",Str_CLI);
		newtext = newtext.replaceAll("Postcodereplace", PostCode);
		newtext = newtext.replaceAll("xxxxx", Str_CLI);


		//for IPTV Stubs

		newtext = newtext.replaceAll("MYBOOKEDDATE", Reusables.getdateFormat("yyyy-MM-dd", 15));
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);

		FileWriter writer = new FileWriter(newfile);
		writer.write(newtext);
		writer.flush();
		writer.close();
		//System.out.println(newfile);
		return newfile;
	}
	public File StubPortingErrorFile(String Str_PortingErrorCode,String Str_NumberPortingInformation,String Str_PortingRH,String Str_CurrentCP,String Str_ALKType,String Str_PortStatus,String Str_CLI){
		String OUTPUTFILE_DIR 	= System.getProperty("user.dir")+"\\ProvisioningUpdates\\";
		//Str_PortingErrorCode, Str_NumberPortingInformation,Str_PortingRH,Str_CurrentCP,Str_ALKType,Str_PortingStatus, Str_CLI+".xml"
		File newfile = new File(OUTPUTFILE_DIR+Str_CLI+".xml");
		try{
			String Template		=	"";
			File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_PORTING.xml");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\r\n";
			}
			reader.close();
			if(Str_PortingErrorCode.equalsIgnoreCase("Noerrorcode")){
				PlaceFile(StubType.NPAC_CP_MC, Str_CLI);
			}else{
				Template = oldtext.replaceAll("M_ERROR_CODE",Str_PortingErrorCode );
				Template = Template.replaceAll("M_NumberPortingInformation", Str_NumberPortingInformation);
				Template = Template.replaceAll("M_PortingRH", Str_PortingRH);
				Template = Template.replaceAll("M_CLI", Str_CLI);
				Template = Template.replaceAll("M_CurrentCP", Str_CurrentCP);
				Template = Template.replaceAll("M_PortStatus", Str_PortStatus);
			}
			FileWriter writer = new FileWriter(newfile);
			writer.write(Template);
			writer.flush();
			writer.close();
		}catch(NullPointerException npe){
			System.out.println("File doesn't exists : "+npe.getMessage());
			npe.printStackTrace();
		}catch(Exception e){
			System.out.println("File doesn't exists");
			e.printStackTrace();
		}
		return newfile;
	}
	public static File StubFileGenerate_WLTOOpalLive(String Str_StubName,String Str_CLI,String Str_OutFileName,int Int_NumberOfDigits,boolean Bol_CeaseStatus) throws Exception{
		String OUTPUTFILE_DIR 	= System.getProperty("user.dir")+"\\ProvisioningUpdates\\";
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);
		try{			
			//NPAC_MWA_1WLTO_1PCO
			String[] Str_SplitStubName	=	Str_StubName.split("_");
			//read Templates
			String Str_FibreAvailabilityTemplate	=	"";
			String Str_WLTO_PCOFalseTemplate		=	"";
			String Str_WLTO_PCOTrueTemplate			=	"";
			String Str_SLTO_PCOFalseTemplate		=	"";
			String[] Str_WLTO						=	Str_SplitStubName[2].split("WLTO");
			System.out.println("Str_WLTO : "+Str_WLTO[0]);
			String[] Str_PCO						=	Str_SplitStubName[3].split("PCO");
			boolean WLTO_WithCeaseStatus			=	false;
			boolean WLTOWithoutCeaseStatus			=	false;
			boolean AccessLineStatus				=	false;
			String Str_NewWLTOFlaseTemplate			=	"";
			String Str_NewWLTOTrueTemplate			=	"";
			//Generate WLTO Sequence Number
			String[] ALIWLTO = new String[Integer.parseInt(Str_WLTO[0])];
			for(int WltoCount=0;WltoCount<Integer.parseInt(Str_WLTO[0]);WltoCount++){
				ALIWLTO[WltoCount] 	= "C"+RandomGenerator.randomCLI(Int_NumberOfDigits);
			}
			if(Int_NumberOfDigits == 12 || Int_NumberOfDigits == 13 || Int_NumberOfDigits == 14){
				AccessLineStatus	=	true;
			}
			if(Integer.parseInt(Str_WLTO[0]) == Integer.parseInt(Str_PCO[0])){
				//All WLTO are having cease order
				//Read with cease order template
				File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MWA_WLTO_OPALWITHCEASE.xml");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = "", oldtext = "";
				while((line = reader.readLine()) != null){
					oldtext += line + "\r\n";
				}
				reader.close();
				//replace with values in the template
				for(int TemplateCount=0;TemplateCount<Integer.parseInt(Str_WLTO[0]);TemplateCount++){
					Str_WLTO_PCOTrueTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
					if(!AccessLineStatus){
						Str_WLTO_PCOTrueTemplate = Str_WLTO_PCOTrueTemplate.replaceAll("ReplaceAccessLineStatus", "BTLive");
					}else{
						Str_WLTO_PCOTrueTemplate = Str_WLTO_PCOTrueTemplate.replaceAll("ReplaceAccessLineStatus", "OpalLive");
					}
					Str_WLTO_PCOTrueTemplate = Str_WLTO_PCOTrueTemplate.replaceAll("ReplaceWLTOCeaseALI", ALIWLTO[TemplateCount]);
					if(Bol_CeaseStatus){
						Str_WLTO_PCOTrueTemplate = Str_WLTO_PCOTrueTemplate.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 4));
					}else{
						Str_WLTO_PCOTrueTemplate = Str_WLTO_PCOTrueTemplate.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 30));
					}
					Str_NewWLTOTrueTemplate	=	Str_NewWLTOTrueTemplate+Str_WLTO_PCOTrueTemplate;
					WLTO_WithCeaseStatus	=	true;
				}
			}
			if(Integer.parseInt(Str_WLTO[0]) > Integer.parseInt(Str_PCO[0])){
				//Need to write method for WLTO first and then for Cease orders
				int WLTO_Count			=	Integer.parseInt(Str_WLTO[0]) - Integer.parseInt(Str_PCO[0]);
				System.out.println(WLTO_Count);
				//Generate WLTO Sequence number
				//Read without cease order template
				File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MWA_WLTO_OPALWITHOUTCEASE.xml");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = "", oldtext = "";
				while((line = reader.readLine()) != null){
					oldtext += line + "\r\n";
				}
				reader.close();
				//Replace with values in the template
				for(int TemplateCount=0;TemplateCount<WLTO_Count;TemplateCount++){
					Str_WLTO_PCOFalseTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
					if(!AccessLineStatus){
						Str_WLTO_PCOFalseTemplate = Str_WLTO_PCOFalseTemplate.replaceAll("ReplaceAccessLineStatus", "BTLive");
					}else{
						Str_WLTO_PCOFalseTemplate = Str_WLTO_PCOFalseTemplate.replaceAll("ReplaceAccessLineStatus", "OpalLive");
					}
					Str_WLTO_PCOFalseTemplate = Str_WLTO_PCOFalseTemplate.replaceAll("ReplaceWLTOALI", ALIWLTO[TemplateCount]);
					Str_NewWLTOFlaseTemplate	=	Str_NewWLTOFlaseTemplate+Str_WLTO_PCOFalseTemplate;
					WLTOWithoutCeaseStatus		=	true;
				}
			}
			//Read FTTC template
			File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MWA_WLTO_OPALFTTC.xml");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\r\n";
			}
			reader.close();
			//replace with values in the template
			String Str_NewFTTCTemplate	=	"";
			Str_FibreAvailabilityTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
			if(!AccessLineStatus){
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("ReplaceAccessLineStatus", "BTLive");
			}else{
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("ReplaceAccessLineStatus", "OpalLive");
			}
			Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("ReplaceFTTCALI", ALIWLTO[0]);
			if(WLTOWithoutCeaseStatus){
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_MWA_WLTO_WITHOUTCEASE",  Str_NewWLTOFlaseTemplate);
			}else{
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_MWA_WLTO_WITHOUTCEASE",  "");
			}
			if(WLTO_WithCeaseStatus){
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_MWA_WLTO_WITHCEASE",  Str_NewWLTOTrueTemplate);
			}else{
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_MWA_WLTO_WITHCEASE",  "");
			}
			Str_NewFTTCTemplate	=	Str_NewFTTCTemplate+Str_FibreAvailabilityTemplate;
			//	System.out.println(Str_NewFTTCTemplate);
			//Write to file and place in Provisioning updates path
			FileWriter writer = new FileWriter(newfile);
			writer.write(Str_FibreAvailabilityTemplate);
			writer.flush();
			writer.close();
		}catch(NullPointerException npe){
			System.out.println("Null Pointer Exception while generating Multiple Lines Stub File");
		}catch(Exception e){
			System.out.println("Exception while generating Multiple Lines Stub File");
			e.printStackTrace();
		}
		return newfile;
	}
	public File StubFileGenerate_MultipleLines(String Str_StubName,String Str_CLI,String Str_OutFileName) throws Exception{
		String OUTPUTFILE_DIR 	= System.getProperty("user.dir")+"\\ProvisioningUpdates\\";
		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);
		try{
			//NPAC_MUL_1SLTO_1WLTO_0PCO
			String[] Str_SplitStubName	=	Str_StubName.split("_");
			//read Templates
			String Str_FibreAvailabilityTemplate	=	"";
			String Str_WLTO_PCOFalseTemplate		=	"";
			String Str_WLTO_PCOTrueTemplate			=	"";
			String Str_SLTO_PCOFalseTemplate		=	"";
			String[] Str_WLTO						=	Str_SplitStubName[3].split("WLTO");
			System.out.println("Str_WLTO : "+Str_WLTO[0]);
			String[] Str_PCO						=	Str_SplitStubName[4].split("PCO");
			String[] Str_SLTO						=	Str_SplitStubName[2].split("SLTO");
			boolean WLTO_WithCeaseStatus			=	false;
			boolean WLTOWithoutCeaseStatus			=	false;
			boolean SLTOStatus						=	false;
			String Str_NewWLTOFlaseTemplate			=	"";
			String Str_NewWLTOTrueTemplate			=	"";
			String Str_NewSLTOFalseTemplate			=	"";

			if(Integer.parseInt(Str_WLTO[0]) == Integer.parseInt(Str_PCO[0])){
				//All WLTO are having cease orders
				//Generate WLTO Sequence Number
				String[] ALIWLTO = new String[Integer.parseInt(Str_WLTO[0])];
				for(int WltoCount=0;WltoCount<Integer.parseInt(Str_WLTO[0]);WltoCount++){
					ALIWLTO[WltoCount] 	= "OOZ"+RandomGenerator.randomCLI(7);
				}
				//Read with cease order template
				File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MUL_WLTO_WITHCEASE.xml");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = "", oldtext = "";
				while((line = reader.readLine()) != null){
					oldtext += line + "\r\n";
				}
				reader.close();
				//replace with values in the template
				for(int TemplateCount=0;TemplateCount<Integer.parseInt(Str_WLTO[0]);TemplateCount++){
					Str_WLTO_PCOTrueTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
					Str_WLTO_PCOTrueTemplate = Str_WLTO_PCOTrueTemplate.replaceAll("ReplaceWLTOCeaseALI", ALIWLTO[TemplateCount]);
					Str_WLTO_PCOTrueTemplate = Str_WLTO_PCOTrueTemplate.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 30));
					Str_NewWLTOTrueTemplate	=	Str_NewWLTOTrueTemplate+Str_WLTO_PCOTrueTemplate;
					WLTO_WithCeaseStatus	=	true;
				}
			}
			if(Integer.parseInt(Str_WLTO[0]) > Integer.parseInt(Str_PCO[0])){
				//Need to write method for WLTO first and then for Cease orders
				int WLTO_Count			=	Integer.parseInt(Str_WLTO[0]) - Integer.parseInt(Str_PCO[0]);
				System.out.println(WLTO_Count);
				//Generate WLTO Sequence number
				String[] ALIWLTO = new String[Integer.parseInt(Str_WLTO[0])];
				for(int WltoCount=0;WltoCount < WLTO_Count; WltoCount++){
					ALIWLTO[WltoCount] 	= "OOZ"+RandomGenerator.randomCLI(7);
				}
				//Read without cease order template
				File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MUL_WLTO_WITHOUTCEASE.xml");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = "", oldtext = "";
				while((line = reader.readLine()) != null){
					oldtext += line + "\r\n";
				}
				reader.close();
				//Replace with values in the template
				for(int TemplateCount=0;TemplateCount<WLTO_Count;TemplateCount++){
					Str_WLTO_PCOFalseTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
					Str_WLTO_PCOFalseTemplate = Str_WLTO_PCOFalseTemplate.replaceAll("ReplaceWLTOALI", ALIWLTO[TemplateCount]);
					Str_NewWLTOFlaseTemplate	=	Str_NewWLTOFlaseTemplate+Str_WLTO_PCOFalseTemplate;
					WLTOWithoutCeaseStatus		=	true;
				}
				//Generate WLTO Sequence number
				String[] PCOTRUE = new String[Integer.parseInt(Str_PCO[0])];
				for(int PCOCount=0;PCOCount < Integer.parseInt(Str_PCO[0]); PCOCount++){
					PCOTRUE[PCOCount] 	= "OOZ"+RandomGenerator.randomCLI(7);
				}
				//Read with cease order template
				file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MUL_WLTO_WITHCEASE.xml");
				reader = new BufferedReader(new FileReader(file));
				line = ""; oldtext = "";

				while((line = reader.readLine()) != null){
					oldtext += line + "\r\n";
				}
				reader.close();
				//Replace with values in the template
				for(int TemplateCount=0;TemplateCount<Integer.parseInt(Str_PCO[0]);TemplateCount++){
					Str_WLTO_PCOFalseTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
					Str_WLTO_PCOFalseTemplate = Str_WLTO_PCOFalseTemplate.replaceAll("ReplaceWLTOCeaseALI", PCOTRUE[TemplateCount]);
					Str_WLTO_PCOFalseTemplate = Str_WLTO_PCOFalseTemplate.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 35));
					Str_NewWLTOFlaseTemplate	=	Str_NewWLTOFlaseTemplate+Str_WLTO_PCOFalseTemplate;
				}
			}
			if(Integer.parseInt(Str_SLTO[0]) != 0){
				//Generate SLTO Sequence Number
				String[] ALISLTO = new String[Integer.parseInt(Str_SLTO[0])];
				for(int SltoCount=0;SltoCount<Integer.parseInt(Str_SLTO[0]);SltoCount++){
					ALISLTO[SltoCount] 	= "OOZ"+RandomGenerator.randomCLI(7);
				}
				//Read SLTO template
				File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MUL_STLO.xml");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = "", oldtext = "";
				while((line = reader.readLine()) != null){
					oldtext += line + "\r\n";
				}
				reader.close();
				//replace with values in the template
				for(int TemplateCount=0;TemplateCount<Integer.parseInt(Str_SLTO[0]);TemplateCount++){
					Str_SLTO_PCOFalseTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
					Str_SLTO_PCOFalseTemplate = Str_SLTO_PCOFalseTemplate.replaceAll("ReplaceSLTOALI", ALISLTO[TemplateCount]);
					Str_NewSLTOFalseTemplate	=	Str_NewSLTOFalseTemplate+Str_SLTO_PCOFalseTemplate;
					SLTOStatus					=	true;
				}
			}
			//Generate Sequence Number for FTTC Availability
			String[] ALIFTTC = new String[1];
			ALIFTTC[0] 	= "OOZ"+RandomGenerator.randomCLI(7);
			//Read FTTC template
			File file = new File(Template_NPAC_MUL_LINES_PATH+"NPAC_MUL_FTTCAVAILABILITY.xml");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\r\n";
			}
			reader.close();
			//replace with values in the template
			String Str_NewFTTCTemplate	=	"";
			Str_FibreAvailabilityTemplate = oldtext.replaceAll("replaceMe",Str_CLI );
			Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("ReplaceFTTCALI", ALIFTTC[0]);
			if(WLTOWithoutCeaseStatus){
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_WLTO_LINE_WITHOUTCEASE",  Str_NewWLTOFlaseTemplate);
			}else{
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_WLTO_LINE_WITHOUTCEASE",  "");
			}
			if(WLTO_WithCeaseStatus){
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_WLTO_LINE_WITHCEASE",  Str_NewWLTOTrueTemplate);
			}else{
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_WLTO_LINE_WITHCEASE",  "");
			}
			if(SLTOStatus){
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_STOPLINE",  Str_NewSLTOFalseTemplate);
			}else{
				Str_FibreAvailabilityTemplate = Str_FibreAvailabilityTemplate.replaceAll("M_STOPLINE",  "");
			}
			Str_NewFTTCTemplate	=	Str_NewFTTCTemplate+Str_FibreAvailabilityTemplate;
			//	System.out.println(Str_NewFTTCTemplate);
			//Write to file and place in Provisioning updates path
			FileWriter writer = new FileWriter(newfile);
			writer.write(Str_FibreAvailabilityTemplate);
			writer.flush();
			writer.close();
		}catch(NullPointerException npe){
			System.out.println("Null Pointer Exception while generating Multiple Lines Stub File");
		}catch(Exception e){
			System.out.println("Exception while generating Multiple Lines Stub File");
			e.printStackTrace();
		}
		return newfile;
	}
	private File StubFileGenerate_LLUMultipleLines(String Str_CLI,String[] ALI_WLTO,String[] ALI_SLTO,String Str_TempFile,String Str_OutFileName) throws Exception{

		File newfile = new File(OUTPUTFILE_DIR+Str_OutFileName);
		try {
			File file = new File(Str_TempFile);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";

			while((line = reader.readLine()) != null)
				oldtext += line + "\r\n";

			reader.close();


			String newtext = oldtext.replaceAll("replaceMe",Str_CLI );
			if(ALI_WLTO[0] != null && ALI_WLTO[0].length() > 0){
				newtext = newtext.replaceAll("replaceali0", ALI_WLTO[0]);
			}if(ALI_SLTO[0] != null && ALI_SLTO[0].length() > 0){
				newtext = newtext.replaceAll("replaceali1", ALI_SLTO[0]);
			}if(ALI_WLTO[1] != null && ALI_WLTO[1].length() > 0){
				newtext = newtext.replaceAll("replaceali2", ALI_WLTO[1]);
			}if(ALI_SLTO[1] != null && ALI_SLTO[1].length() > 0){
				newtext = newtext.replaceAll("replaceali3", ALI_SLTO[1]);
			}if(ALI_WLTO[2] != null && ALI_WLTO[2].length() > 0){
				newtext = newtext.replaceAll("replaceali4", ALI_WLTO[2]);
			}if(ALI_SLTO[2] != null && ALI_SLTO[2].length() > 0){
				newtext = newtext.replaceAll("replaceali5", ALI_SLTO[2]);
			}if(ALI_WLTO[3] != null && ALI_WLTO[3].length() > 0){
				newtext = newtext.replaceAll("replaceali6", ALI_WLTO[3]);
			}if(ALI_SLTO[3] != null && ALI_SLTO[3].length() > 0){
				newtext = newtext.replaceAll("replaceali7", ALI_SLTO[3]);
			}if(ALI_WLTO[4] != null && ALI_WLTO[4].length() > 0){
				newtext = newtext.replaceAll("replaceali8", ALI_WLTO[4]);
			}if(ALI_SLTO[4] != null && ALI_SLTO[4].length() > 0){
				newtext = newtext.replaceAll("replaceali9", ALI_SLTO[4]);
			}if(ALI_WLTO[5] != null && ALI_WLTO[5].length() > 0){
				newtext = newtext.replaceAll("replaceali10", ALI_WLTO[5]);
			}if(ALI_SLTO[5] != null && ALI_SLTO[5].length() > 0){
				newtext = newtext.replaceAll("replaceali11", ALI_SLTO[5]);
			}if(ALI_WLTO[6] != null && ALI_WLTO[6].length() > 0){
				newtext = newtext.replaceAll("replaceali12", ALI_WLTO[6]);
			}if(ALI_SLTO[6] != null && ALI_SLTO[6].length() > 0){
				newtext = newtext.replaceAll("replaceali13", ALI_SLTO[6]);
			}if(ALI_WLTO[7] != null && ALI_WLTO[7].length() > 0){
				newtext = newtext.replaceAll("replaceali14", ALI_WLTO[7]);
			}if(ALI_SLTO[7] != null && ALI_SLTO[7].length() > 0){
				newtext = newtext.replaceAll("replaceali15", ALI_SLTO[7]);
			}if(ALI_WLTO[8] != null && ALI_WLTO[8].length() > 0){
				newtext = newtext.replaceAll("replaceali16", ALI_WLTO[8]);
			}if(ALI_SLTO[8] != null && ALI_SLTO[8].length() > 0){
				newtext = newtext.replaceAll("replaceali17", ALI_SLTO[8]);
			}if(ALI_WLTO[9] != null && ALI_WLTO[9].length() > 0){
				newtext = newtext.replaceAll("replaceali18", ALI_WLTO[9]);
			}if(ALI_SLTO[9] != null && ALI_SLTO[9].length() > 0){
				newtext = newtext.replaceAll("replaceali19", ALI_SLTO[9]);
			}
			newtext = newtext.replaceAll("CeasePendingDate",  Reusables.getdateFormat("yyyy-MM-dd", 10));

			//for IPTV Stubs

			newtext = newtext.replaceAll("MYBOOKEDDATE", Reusables.getdateFormat("yyyy-MM-dd", 15));


			FileWriter writer = new FileWriter(newfile);
			writer.write(newtext);
			writer.flush();
			writer.close();
			System.out.println(newfile);

		}catch(NullPointerException npe){
			npe.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return newfile;
	}

	private void StubFileCopy(File File_Source,String Str_Destination){
		if(File_Source.exists()){
			System.out.println("source is there");
		}

		File File_Dest = new File(Str_Destination);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(File_Source);
			os = new FileOutputStream(File_Dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
				//System.out.println("copying file");
			}
		}catch(Exception e){
			Report.fnReportFailAndTerminateTest("IOException", "IOException in placing file");
		}finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("file Copy Complete to "+Str_Destination);
			if(Report!=null){
				Report.fnReportPass("file Copy Complete to "+Str_Destination);
			}
		}
	}

	public void StubFileSFTP(SFTPStubType SftpType,File File_Source,String Str_Destination){
		System.out.println("Transfering file to sftp");
		JSch jsch=new JSch();
		Session session=null;
		Channel channel=null;
		ChannelSftp channelSftp=null;
		try {
			switch(SftpType){
			case AUTOMATION:
				session = jsch.getSession(SFTPSTUB_auth, SFTPSTUB_host, 22);
				session.setPassword(SFTPSTUB_pass);
				break;
			case DERBY:
				session = jsch.getSession(DERBYSTUB_auth, DERBYSTUB_host, 22);
				session.setPassword(DERBYSTUB_pass);
				break;
			default:
				break;
			}

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(Str_Destination);
			try{
				channelSftp.rm(File_Source.getName());
			}catch(Exception e){
				// Do Nothing
			}
			channelSftp.put(new FileInputStream(File_Source), File_Source.getName());
			System.out.println("SFTP file transfer successfull");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("Exception", "Exception in Placing Stub File");
		}
		finally{
			channelSftp.exit();
			session.disconnect();
			System.out.println("file SFTP Complete to  "+Str_Destination);
			if(Report!=null){
				Report.fnReportPass("file SFTP Complete to "+Str_Destination);
			}
		}

	}
	public void PlaceFile(StubType Stub,String Str_CLI,String Str_Postcode) throws Exception{
		File File_Source = null;
		switch(Stub){
		case NGAALK_new:
			File_Source = StubFileGenerate_NGA(Str_CLI,Str_Postcode,Template_NGA_ALK_Live_TRIO14,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAALK_new);
			break;
		default:
			break;
		}

	}

	public void PlaceFile_Lightning(StubType Stub,String UPRN,String Rface,String Str_Postcode,String RHType,String InstallType) throws Exception{

		File File_Source = null;
		switch(Stub){
		case UPRN_Lightning:
			File_Source = StubFileGenerate_Lightning(UPRN, Rface, Str_Postcode,Template_UPRN,UPRN+".xml",RHType,InstallType);
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NPAC);
			break;
		case UPRN_Porting:
			File_Source = StubFileGenerate_Lightning(UPRN, Rface, Str_Postcode,Template_UPRN_Porting,UPRN+".xml",RHType,InstallType);
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_Lightning);
			break;
		case NPAC_UPRNLightning:
			File_Source = StubFileGenerate_Lightning(UPRN, Rface, Str_Postcode,Template_NPACUPRN,UPRN+".xml",RHType,InstallType);
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NPAC);
			break;
		case NPAC_UPRNPorting:
			File_Source = StubFileGenerate_Lightning(UPRN, Rface, Str_Postcode,Template_NPAC_UPRNPorting,UPRN+".xml",RHType,InstallType);
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NPAC);
			break;
		default:
			break;

		}


	}
	public void PlaceFile_Porting(StubType Stub,PortingRH RH,ALKType ALK,String Str_Postcode,String Str_CLI,PortingStatus PS,NumberPortingInformation NPI,PortingRH CP) throws Exception{

		File File_Source = null;
		switch(Stub){
		case Porting_ALK:
			File_Source = StubFileGenerate_Porting(Str_CLI,Template_Porting_Newline_ALK,Str_CLI+".xml",Str_Postcode,RH,ALK,PS,NPI,CP);
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NPAC);
			break;
		default:
			break;


		}


	}
	public void PlaceFile_PortingPostcode(PortingRH RH,ALKType ALK,String Str_Postcode,String Str_CLI,PortingStatus PS,NumberPortingInformation NPI,PortingRH CP) throws Exception{
		File File_Source = null;
		File_Source = StubFileGenerate_Porting(Str_CLI,Template_Porting_Newline_Postcode,Str_Postcode+".xml",Str_Postcode,RH,ALK,PS,NPI,CP);
		StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NPAC);
	}
	public void Stub_Porting(String Str_CLI,String ALK, String RH, String Str_PostCode, String PEC, String NPI, String PS, String Str_AddressKey,String CurrentCP)
	{
		try {
			PlaceFile_PortingPostcode(PortingRH.valueOf(RH), ALKType.valueOf(ALK), Str_PostCode, Str_AddressKey,PortingStatus.valueOf(PS),NumberPortingInformation.valueOf(NPI),PortingRH.valueOf(CurrentCP));
			PlaceFile_Porting(StubType.Porting_ALK, PortingRH.valueOf(RH), ALKType.valueOf(ALK), Str_PostCode, Str_AddressKey,PortingStatus.valueOf(PS),NumberPortingInformation.valueOf(NPI),PortingRH.valueOf(CurrentCP));
			if(!PEC.contains("Noerrorcode"))
			{
				PEC="LCPTWS"+PEC;
				PlaceFile_PortingCLI(PortingRH.valueOf(RH), Str_PostCode, Str_CLI, PortingErrorCode.valueOf(PEC), NumberPortingInformation.valueOf(NPI), PortingStatus.valueOf(PS));
			}
			else
			{
				PlaceFile(StubType.NPAC_CP_MC, Str_CLI);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("enum not found", e.getMessage()); 
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Report.fnReportFailAndTerminateTest("Porting Stub", "Stubbing for Porting is not done properly");
		}
	}

	public void PlaceFile_PortingCLI(PortingRH RH,String Str_Postcode,String Str_CLI, PortingErrorCode PEC, NumberPortingInformation NPI, PortingStatus PS) throws Exception{

		File File_Source = StubFileGenerate_PortingCLI(Str_CLI, Str_Postcode, Template_Porting_CLI, Str_CLI+".xml", RH, PEC, NPI, PS);
		StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NPAC);
	}
	public void PlaceFile_NoPortingCLI(PortingRH RH,String Str_Postcode,String Str_CLI,String CurrentCP) throws Exception{

		File File_Source = StubFileGenerate_NoPortingCLI(Str_CLI, Str_Postcode, Template_NoPorting_CLI, Str_CLI+".xml", RH,CurrentCP);
		StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NPAC);
	}
	public void PlaceGenericFile(StubType Stub,String...Data)
	{
		try {
			File File_Source = null;
			switch(Stub){
			case NPAC_LLU_ALK_LIVE:
				File_Source = StubFileGenerate_Multiple(Template_NPAC_LLU_ALK_Live,Data[0]+"_Resp.xml",Data);
				StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
				break;
			case LLU_ALK_LIVE:
				File_Source = StubFileGenerate_Multiple(Template_LLU_ALK_Live,Data[0]+"_Resp.xml",Data);
				StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void PlaceFile(StubType Stub,String Str_CLI,boolean... Past_Future){
		File File_Source = null;
		switch(Stub){
		case IPTVM:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVM,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case IPTVM_CeaseOrder:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVM_Ceaseorder,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;

		case BTPorting:
			File_Source = StubFileGenerate(Str_CLI,Template_BTPorting,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case IPTVU:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVU,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case IPTVM_PostCodeMatchNo:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVM_PostCodeMatchNo,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case NGAv2CLI:
			File_Source = StubFileGenerate(Str_CLI,Template_NGAv2CLI,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break;
		case NGAv2CLI_new:
			System.out.println("inside NGA");
			File_Source = StubFileGenerate(Str_CLI,Template_NGAv2CLI_new,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break;
		case NGAv2CLI_PostCodeMatchNO:
			System.out.println("inside NGA");
			File_Source = StubFileGenerate(Str_CLI,Template_NGAV2_new_PostcodeMatchNO,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break;
		case QS3:
			File_Source = StubFileGenerate(Str_CLI,Template_IPS,Str_CLI+".txt");
			StubFileCopy(File_Source, Stub_IPSCLI+Str_CLI+".txt");

			File_Source = StubFileGenerate(Str_CLI,Template_QS3_V5,Str_CLI+".txt");
			StubFileSFTP(SFTPStubType.DERBY,  File_Source, Stub_LLUCLI);
			break;
		case NPAC_CP_UC:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_UC, Str_CLI
					+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_NGA_GEA:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_NGA_GEA, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_Newline:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_Newline, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_BTLiveUni	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_BTLiveUni, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_NewlineUni	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_NewlineUni, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_BTStopUni	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_BTStopUni, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;	
		case NPAC_NGAGEA_SINO_MILT5	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_NGAGEA_SINO_MILT5, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_BTLive_Withcease	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_BTLive_Withcease, Str_CLI+".xml",Past_Future);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_OpalLive	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_OpalLive, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_OpalLiveWithCease	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_OpalLiveWithCease, Str_CLI+".xml",Past_Future);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_Newline_NoFibre	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_Newline_NoFibre, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_MC_NoFibre	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_NoFibre, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_LIVE:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_Live, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_MC_NoPCMatch	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_NoPCMatch, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_BTLiveGEA	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_BTLiveGEA, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;

		case NPAC_LLU_ALK_OPALLIVE_UNICAST	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_OPALLIVE_UNICAST, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_OPALLIVE_GEA_821	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_LLU_ALK_OPALLIVE_GEA_821, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_ACErrorResponse:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_ACErrorResponse,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_Check_Nominated_CLI:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_Check_Nominated_CLI,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_Nominated_CLI);
			break;
		case NPAC_Lowspeed:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_Lowspeed, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_NGAALK_SINO_MIYES:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_Lowspeed, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_NGA_SI_LE5Mbps	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_NGAALK_SINO_MIYES, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_BTStop_NGA_LE5_SI	:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_BTStop_NGA_LE5_SI, Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_MC_FibreMI:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_FibreMI, Str_CLI+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_MC:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC, Str_CLI+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_MC_TVStore:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_TVStore, Str_CLI+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_DoubleMigrate:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_GEA, Str_CLI
					+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case Check_CLI_Poratability:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_CLI_Poratability,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_CLI_Poratability);
			break;
		case Check_CLI_Poratability_Sky:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_CLI_Poratability_Sky,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_CLI_Poratability);
			break;
		case Check_Nominated_CLI:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_Nominated_CLI,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_Nominated_CLI);
			break;
		case Check_Nominated_CLI_BT:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_Nominated_CLI_BT,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_Nominated_CLI);
			break;
		case NPAC_CP_MC_PortingSky:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_PortingSky, Str_CLI
					+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_MC_PortingVirgin:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_PortingVirgin, Str_CLI
					+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_CP_MC_CeaseTrue:
			File_Source = StubFileGenerate(Str_CLI, Template_NPAC_CP_MC_CeaseTrue, Str_CLI
					+ ".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case Check_Nominated_CLI1:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_Nominated_CLI1,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_Nominated_CLI);
			break;
		case Check_Nominated_CLI2:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_Nominated_CLI2,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_Nominated_CLI);
			break;
		case Check_Nominated_CLI_Sales:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_Nominated_CLI_Sales,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_Nominated_CLI);
			break;
		case Check_Nominated_CLI_Sky:
			File_Source = StubFileGenerate(Str_CLI,Template_Check_Nominated_CLI_Sky,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Check_Nominated_CLI);
			break;
		case Working_Error_Response:
			File_Source = StubFileGenerate(Str_CLI,Template_Working_Error_Response,Str_CLI+".txt");
			StubFileSFTP(SFTPStubType.AUTOMATION, File_Source, Stub_Working_Error_Response);
			break;
		case IPTVCease:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVCeaseStubs,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.AUTOMATION, File_Source, Stub_IPTVCeaseStubsNew);
			break;
		case IPTVModify:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVModifyStubs,Str_CLI+".txt");
			StubFileSFTP(SFTPStubType.AUTOMATION, File_Source, Stub_IPTVModifyStubs);
			break;
		case IPTVProvide:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVProvideStubs,Str_CLI+".txt");
			StubFileSFTP(SFTPStubType.AUTOMATION, File_Source, Stub_IPTVProvideStubs);
			break;
		case IPTVProvideNew:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVProvideStubsNew,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_IPTVProvideStubsNew);
			break;
		case IPTVProvideNewFTTP:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVProvideStubsNewFTTP,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_IPTVProvideStubsNew);
			break;

		case IPTVModifyNew:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVModifyStubsNew,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_IPTVModifyStubsNew);
			break;
		case IPTVSuspend:
			File_Source = StubFileGenerate(Str_CLI,Template_IPTVSuspendStubs,Str_CLI+".txt");
			StubFileSFTP(SFTPStubType.AUTOMATION, File_Source, Stub_IPTVSuspendStubs);
			break;
		case LLUv5:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUv5,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY,  File_Source, Stub_LLUCLI);
			break;
		case LLUv5_GEA:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUv5GEA,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY,  File_Source, Stub_LLUCLI);
			break;
		case LLUv5_CeasePending:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUv5CeasePending,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY,  File_Source, Stub_LLUCLI);
			break;
		case LLUv5_PostCodeMatchNO:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUv5_PostCodeMatchNO,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY,  File_Source, Stub_LLUCLI);
			break;
		case QS4:
			File_Source = StubFileGenerate(Str_CLI,Template_IPS,Str_CLI+".txt");
			StubFileCopy(File_Source, Stub_IPSCLI+Str_CLI+".txt");

			File_Source = StubFileGenerate(Str_CLI,Template_QS3_V5,Str_CLI+".txt");
			StubFileSFTP(SFTPStubType.AUTOMATION,  File_Source, Stub_LLUCLI);
			break;
		case ExchangeStatus:
			Str_CLI=Str_CLI.replaceAll("\\s+", "");
			File_Source = StubFileGenerate(Str_CLI,Template_ExchangeStatus,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUPostcode);
			break;
		case NPAC_ExchangeStatus:
			//			Str_CLI=Str_CLI.replaceAll("\\s+", "");
			File_Source = StubFileGenerate(Str_CLI,Template_NPACExchangeStatus,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;

		case ExchangeStatusUnicast:
			Str_CLI=Str_CLI.replaceAll("\\s+", "");
			File_Source = StubFileGenerate(Str_CLI,Template_ExchangeStatus_Unicast,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUPostcode);
			break;


		case Lowspeed:
			File_Source = StubFileGenerate(Str_CLI,Template_Lowspeed,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY,  File_Source, Stub_LLUCLI);
			break;
		case LLU_ALK_OPALLive:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUALK_OPALLive,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_BTStop:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUALK_BTStop,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case NPAC_LLU_ALK_BTStop:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_LLUALK_BTStop,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case LLU_ALK_BTStop_LLU:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUALK_BTStop_LLU,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_BTStop_Unicast:
			File_Source = StubFileGenerate(Str_CLI,Template_LLU_ALK_BTStop_IPTVUnicast,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_Newline:
			System.out.println("inside newline");
			File_Source = StubFileGenerate(Str_CLI,Template_LLU_ALK_Newline,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_Newline_Unicast:
			File_Source = StubFileGenerate(Str_CLI,Template_LLU_ALK_Newline_Unicast,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_Newline_AMBER:
			System.out.println("inside newline");
			File_Source = StubFileGenerate(Str_CLI,Template_LLU_ALK_Newline_AMBER,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case PostCode_GoldAddress:
			break;
		case NGA_ALK:
			File_Source = StubFileGenerate(Str_CLI,Template_NGA_ALK_Live_TRIO14,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAALK_new);
			break;

		case NGAALK_new:
			System.out.println("inside NGA");
			File_Source = StubFileGenerate(Str_CLI,Template_NGA_ALK_Live_TRIO14,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAALK_new);
			break;

		case NGA_ALK_TEST:

			File_Source = StubFileGenerate(Str_CLI,Template_NGA_ALK_TEST,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAALK_new);
			break;
		case NGAALK_new_LE5Mbps:
			File_Source = StubFileGenerate(Str_CLI,Template_NGA_ALK_LE5Mbps,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAALK_new);
			break;
		case NGAALK_SINO_MIYES:
			File_Source = StubFileGenerate(Str_CLI,Template_NGAALK_SINO_MIYES,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAALK_new);
			break;		
		case NGAALK_new_SpeedLT5:
			File_Source = StubFileGenerate(Str_CLI,Template_NGAALK_new_SpeedLT5,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAALK_new);
			break;
		case NGAGEA:
			File_Source = StubFileGenerate(Str_CLI,Template_NGAGEA,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break;
		case NGAGEAError:
			File_Source = StubFileGenerate(Str_CLI,Template_NGAGEAError,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break;
		case NGAv2CLI_new_SI_LE5mbps:
			File_Source = StubFileGenerate(Str_CLI,Template_NGAV2_SI_LE5Mbps,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break;
		case NGAGEA_SINO_MILT5:
			File_Source = StubFileGenerate(Str_CLI,Template_NGAGEA_SINO_MILT5,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break;
		case NGAv2CLI_ENG_new:
			System.out.println("inside NGA");
			File_Source = StubFileGenerate(Str_CLI,Template_NGAv2CLI_ENG_new,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY,File_Source, Stub_NGAv2CLI_new);
			break; 
		case CreditCheck_ConditionalAccept:
			File_Source = StubFileGenerate(Str_CLI,Template_CreditCheck_ConditionalAccept,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_CreditCheck);
			break;
		case CreditCheck_Referral:
			File_Source = StubFileGenerate(Str_CLI,Template_CreditCheck_Referral,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_CreditCheck);
			break;
		case CreditCheck_TotalAccept:
			File_Source = StubFileGenerate(Str_CLI,Template_CreditCheck_TotalAccept,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_CreditCheck);
			break;
		case CreitCheck_TotalDecline:
			File_Source = StubFileGenerate(Str_CLI,Template_CreditCheck_TotalDecline,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_CreditCheck);
			break;
		case LLU_ALK_LowSpeed:
			File_Source = StubFileGenerate(Str_CLI,Template_LLU_ALK_LowSpeed,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_CPS_LowSpeed:
			File_Source = StubFileGenerate(Str_CLI,Template_LLU_CPS_LowSpeed,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY,  File_Source, Stub_LLUCLI);
			break;
		case LLU_CPS_PORT_RH_Virgin:
			File_Source = StubFileGenerate(Str_CLI,Template_LLU_CPS_PORT_RH_Virgin,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case ACErrorResponseVirgin:
			File_Source = StubFileGenerate(Str_CLI,Template_ACErrorResponseVirgin,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case ACErrorResponseSky:
			File_Source = StubFileGenerate(Str_CLI,Template_ACErrorResponseSky,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case LLU_ALK_BTLive:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUALK_BTLive,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case NPAC_LLU_ALK_BTLive:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_LLUALK_BTLive,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_BTLive_ALI13:
			File_Source = StubFileGenerate_ALI(Str_CLI,Template_NPAC_LLUALK_BTLive,Str_CLI+".xml",13);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_OPALLive_ALI13:
			File_Source = StubFileGenerate_ALI(Str_CLI, Template_NPAC_LLU_ALK_OpalLive, Str_CLI+".xml",13);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_OPALLive_ALI13withCease:
			File_Source = StubFileGenerate_ALI(Str_CLI, Template_NPAC_LLU_ALK_OpalLiveWithCease, Str_CLI+".xml",13,Past_Future);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_BTLive_ALI14:
			File_Source = StubFileGenerate_ALI(Str_CLI,Template_NPAC_LLUALK_BTLive,Str_CLI+".xml",14);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_BTLive_ALI15:
			File_Source = StubFileGenerate_ALI(Str_CLI,Template_NPAC_LLUALK_BTLive,Str_CLI+".xml",15);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_LLU_ALK_Newline_Amber:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_LLU_ALK_Newline_Amber,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_BTLiveGEA:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUALK_BTLiveGEA,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_BTLive_Unicast:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUALK_BTLive_Unicast,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case MatchAddressResponse:
			File_Source = StubFileGenerate(Str_CLI,Template_MatchAddressResponse,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_MatchAddressResponse);
			break;
		case LLU_ALK_BTLive_WithCeasePending:
			File_Source = StubFileGenerate(Str_CLI,Template_LLUALK_BTLive_WithCeasePending,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case ACErrorResponseBT:
			File_Source = StubFileGenerate(Str_CLI,Template_ACErrorResponseBT,Str_CLI+".txt");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUCLI);
			break;
		case GetCPEResponse:
			File_Source = StubFileGenerate(Str_CLI,Template_GetCPEResponse,Str_CLI+"_Resp.txt");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_GetCPEResponsse);
			break;
		case IPS:
			File_Source = StubFileGenerate(Str_CLI,Template_IPS,Str_CLI+".txt");
			StubFileCopy(File_Source, Stub_IPSCLI+Str_CLI+".txt");
			break;
		case Low_ExchangeStatus:
			Str_CLI=Str_CLI.replaceAll("\\s+", "");
			File_Source = StubFileGenerate(Str_CLI,Template_ExchangeStatus_Low,Str_CLI+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUPostcode);
			break;



		case ExchangeStatus_Porting:
			File_Source = StubFileGenerate(Str_CLI,Template_ExchangeStatus_Porting,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_Porting_Sky:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_Porting_Sky,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_Porting_Virgin:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_Porting_Virgin,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_Porting_BT:
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_Porting_BT,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;

		case NPAC_Low_ExchangeStatus:

			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_ExchangeStatus_lowspeed,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_Low_LLU_ALK:
			Str_CLI=Str_CLI.replaceAll("\\s+", "");
			File_Source = StubFileGenerate(Str_CLI,Template_NPAC_Low_LLU_ALK,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case SMPF:
			File_Source = StubFileGenerate(Str_CLI,Template_SMPF,Str_CLI+".txt");
			StubFileCopy(File_Source, Stub_SMPFCLI+Str_CLI+".txt");
			break;
		case RetainNumber:
			File_Source = StubFileGenerate(Str_CLI,Template_Retain_Number,Str_CLI+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_Retain_Number);
			break;	
		default:
			break;
		}
	}

	public void PlaceFile_MultipleLines(StubType Stub,String Str_AddressKey,String ALI_WLTO[],String ALI_SLTO[]) throws Exception{
		File File_Source = null;
		switch(Stub){
		case WorkingLinewithoutCeasePending:
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_WorkingLinewithoutCeasePending,Str_AddressKey+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_LLUALK_BTLivewithoutCeasePending,ALI_WLTO+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_LLUALK_BTStopwithALI,ALI_SLTO+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case WorkingLinewithCeasePending:
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_WorkingLinewithCeasePending,Str_AddressKey+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_LLUALK_BTLive_WithCeasePending,ALI_WLTO+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_LLUALK_BTStopwithALI,ALI_SLTO+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;

		case MultipleStopandWorkinglines_WithCeasePending:

			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_MultipleStopandWorkinglines_WithCeasePending,Str_AddressKey+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			String ALIWLTO1 = ALI_WLTO[0];
			String ALIWLTO2 = ALI_WLTO[1];
			String ALISLTO1 = ALI_SLTO[0];
			String ALISLTO2 = ALI_SLTO[1];
			File_Source = StubFileGenerate_ALI(Str_AddressKey, ALIWLTO1,Template_LLUALK_BTLivewithoutCeasePending,ALIWLTO1+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			File_Source = StubFileGenerate_ALI(Str_AddressKey, ALIWLTO2,Template_LLUALK_BTLive_WithCeasePending,ALIWLTO2+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			File_Source = StubFileGenerate_ALI(Str_AddressKey, ALISLTO1,Template_LLUALK_BTStopwithALI,ALISLTO1+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			File_Source = StubFileGenerate_ALI(Str_AddressKey, ALISLTO2,Template_LLUALK_BTStopwithALI,ALISLTO2+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case LLU_ALK_BTLiveGEA:
			File_Source = StubFileGenerate(Str_AddressKey,Template_LLUALK_BTLiveGEA,Str_AddressKey+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_LLUALK);
			break;
		case NPAC_MUL_1SLTO_1WLTO:
			System.out.println("Ented to Multiple lines stubbing");
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_1WLTO,Str_AddressKey+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_MUL_1SLTO_2WLTO_NEWLINE:
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_2WLTO_NewLine,Str_AddressKey+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_MUL_1SLTO_1WLTO_withCease:
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_1WLTO_withCease,Str_AddressKey+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_MUL_1SLTO_2WLTO_CEASEPENDING:
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_2WLTO_CeasePending,Str_AddressKey+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
		case NPAC_MUL_1SL_3WL_2PENDINGCEASE:
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_3WLTO_2CeasePending,Str_AddressKey+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;
			//StubFileGenerate_MultipleLines
		case NPAC_MUL_1SLTO_1WLTO_MIGRATEWITHALI:
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_1WLTO_MigrateWithAli,Str_AddressKey+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			File_Source = StubFileGenerate_LLUMultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_1WLTO_MigrateWithAliResp,Str_AddressKey+"_Resp.xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
			break;


		default:
			break;
		}
	}
	public void Stub_MultipleLines(String Str_StubName,String Str_AddressKey){
		try{
			File File_Source = null;
			File_Source = StubFileGenerate_MultipleLines(Str_StubName, Str_AddressKey, Str_AddressKey+".xml");
			//File_Source = StubFileGenerate_MultipleLines(Str_AddressKey,ALI_WLTO,ALI_SLTO,Template_NPAC_MUL_1SLTO_3WLTO_2CeasePending,Str_AddressKey+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void StubFileGenerate_OpalLive(String Str_StubName,String Str_AddressKey,int Int_NumberOfDigits,boolean Bol_CeaseDateStatus){
		try{
			File File_Source = null;
			File_Source = StubFileGenerate_WLTOOpalLive(Str_StubName, Str_AddressKey, Str_AddressKey+".xml",Int_NumberOfDigits,Bol_CeaseDateStatus);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void Stub_PortingWithOrWithoutErrorCode(String Str_CLI,String Str_ALKType,String Str_AddressKey,String Str_Postcode,String Str_PortingErrorCode,String Str_NumberPortingInformation,String Str_PortingRH,String Str_CurrentCP,String Str_PortingStatus){
		try{
			File File_Source = null;
			PlaceFile_PortingPostcode(PortingRH.valueOf(Str_PortingRH), ALKType.valueOf(Str_ALKType), Str_Postcode, Str_AddressKey,PortingStatus.valueOf(Str_PortingStatus),NumberPortingInformation.valueOf(Str_NumberPortingInformation),PortingRH.valueOf(Str_CurrentCP));
			PlaceFile_Porting(StubType.Porting_ALK, PortingRH.valueOf(Str_PortingRH), ALKType.valueOf(Str_ALKType), Str_Postcode, Str_AddressKey,PortingStatus.valueOf(Str_PortingStatus),NumberPortingInformation.valueOf(Str_NumberPortingInformation),PortingRH.valueOf(Str_CurrentCP));
			File_Source = StubPortingErrorFile( Str_PortingErrorCode, Str_NumberPortingInformation,Str_PortingRH,Str_CurrentCP,Str_ALKType,Str_PortingStatus, Str_CLI);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_NPAC);
		}catch(NullPointerException npe){

		}catch(Exception e){

		}
	}
	public void Stub_PostCodeAddress(String Str_Alk,String Str_PostCode,String Str_Qualifier) throws Exception{
		//		File Stub_Postcode = new File(Stub_AddressMatching+Str_PostCode+".txt");
		//		String Str_Target=System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+Str_PostCode+".txt";
		//		StubFileCopy(new File(Template_AddressMatching),Str_Target);
		//		PostcodeGenerate(Str_Target,Str_Alk,Str_PostCode,Str_Qualifier);
		//		StubFileCopy(new File(Str_Target),Stub_AddressMatching+Str_PostCode+".txt");
		Random rand = new Random();
		int Rface = 100 + rand.nextInt((1000 - 1) + 1) + 1;
		String Stub_Postcode =  Stub_AddressMatching+"/"+Str_PostCode+"_Response.xml";
		System.out.println("inside");
		String Str_Target=System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+Str_PostCode+"_Response.xml";
		System.out.println("target");
		GetFile(Stub_Postcode, Str_Target,DERBYSTUB_host,DERBYSTUB_auth,DERBYSTUB_pass);

		System.out.println("derby");

		PostcodeGenerate_withBuildingNumber(Str_Target,Str_Alk,Str_PostCode,Str_Qualifier,Rface);

		PutFile(Str_Target,Stub_Postcode, DERBYSTUB_host,DERBYSTUB_auth,DERBYSTUB_pass);
	}

	public void PostcodeGenerate(String Str_Target,String Str_Alk,String Str_PostCode,String Str_Qualifier) throws Exception{
		//String Str_Alk="A"+Str_CLI;
		String line = "", oldtext = "";
		int Int_LineCounter = 0;
		int Int_InsertCounter=0;
		int Int_InsertPosition = 0;
		String Str_Address="";
		File file = new File(Str_Target);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		while((line = reader.readLine()) != null)Int_LineCounter++;
		reader.close();

		Int_InsertPosition=Int_LineCounter - 5;
		if(Str_Qualifier.equalsIgnoreCase("GOLD")){
			Str_Address="<Address> \n\t\t"
					+ "<BuildingNumber>"+Int_InsertPosition+"</BuildingNumber>\n\t\t"
					+ "<Street>Worton Way</Street> \n\t\t"
					+ "<PostTown>ISLEWORTHy</PostTown> \n\t\t"
					+ "<County>Middlesex</County> \n\t\t"
					+ "<PostCode>"+Str_PostCode+"</PostCode> \n\t\t"
					+ "<ALK>"+Str_Alk+"</ALK> \n\t\t"
					+ "<CSSDistrictCode>TH</CSSDistrictCode> \n\t\t"
					+ "<Qualifier>Gold</Qualifier> \n"
					+ "</Address> \n";
		}else{
			Str_Address="<Address> \n\t\t"
					+ "<BuildingNumber>"+Int_InsertPosition+"</BuildingNumber>\n\t\t"
					+ "<Street>Worton Way</Street> \n\t\t"
					+ "<PostTown>ISLEWORTHy</PostTown> \n\t\t"
					+ "<County>Middlesex</County> \n\t\t"
					+ "<PostCode>"+Str_PostCode+"</PostCode> \n\t\t"
					+ "<CSSDistrictCode>TH</CSSDistrictCode> \n\t\t"
					+ "<Qualifier>Silver</Qualifier> \n"
					+ "</Address> \n";
		}
		line="";
		BufferedReader reader1 = new BufferedReader(new FileReader(file));
		while((line = reader1.readLine()) != null){
			oldtext += line + "\r\n";
			if(Int_InsertCounter==Int_InsertPosition){	
				//System.out.println("Matched");
				oldtext += Str_Address + "\n";
			}
			Int_InsertCounter++;
		}
		reader1.close();

		FileWriter writer = new FileWriter(Str_Target);
		writer.write(oldtext);writer.flush();writer.close();
	}

	//	public void Stub_PostCodeAddress_withBuildingNumber(String Str_Alk,String Str_PostCode,String Str_Qualifier,int BuildingNumber) throws Exception{
	//		File Stub_Postcode = new File(Stub_AddressMatching+Str_PostCode+".txt");
	//		String Str_Target=System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+Str_PostCode+".txt";
	//
	//		//StubFileCopy(new File(Template_AddressMatching),Str_Target);
	//		StubFileCopy(new File(Stub_AddressMatching+Str_PostCode+".txt"),Str_Target);
	//
	//		PostcodeGenerate_withBuildingNumber(Str_Target,Str_Alk,Str_PostCode,Str_Qualifier,BuildingNumber);
	//		//System.out.println(Stub_AddressMatching+Str_PostCode+".txt");
	//		StubFileCopy(new File(Str_Target),Stub_AddressMatching+Str_PostCode+".txt");
	//		//System.out.println(Stub_AddressMatching+Str_PostCode+".txt");
	//	}
	public void Stub_PostCodeAddress_withBuildingNumber(String Str_Alk,String Str_PostCode,String Str_Qualifier,int BuildingNumber) throws Exception{
		String Stub_Postcode =  Stub_AddressMatching+"/"+Str_PostCode+"_Response.xml";
		String Str_Target=System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+Str_PostCode+"_Response.xml";

		GetFile(Stub_Postcode, Str_Target,DERBYSTUB_host,DERBYSTUB_auth,DERBYSTUB_pass);

		PostcodeGenerate_withBuildingNumber(Str_Target,Str_Alk,Str_PostCode,Str_Qualifier,BuildingNumber);

		PutFile(Str_Target,Stub_Postcode, DERBYSTUB_host,DERBYSTUB_auth,DERBYSTUB_pass);
		System.out.println("building no completed");
	}
	public void Stub_IssueAddressKey(String Str_Alk,String Str_PostCode,String Str_Qualifier,int BuildingNumber) throws Exception{
		System.out.println("Issue Address Key Started");
		//File Stub_Postcode = new File(Stub_IssueAddressKey+Str_PostCode+".txt");
		String Str_Target=System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+Str_PostCode+".txt";
		StubFileCopy(new File(Template_IssueAddressKey),Str_Target);
		System.out.println("Targetcopy");
		IssueAddressKeyGenerate(Str_Target,Str_Alk,Str_PostCode,Str_Qualifier,BuildingNumber);
		System.out.println("KeyGenerator");
		StubFileCopy(new File(Str_Target),Stub_IssueAddressKey+Str_PostCode+".txt");
	} 

	public void PostcodeGenerate_withBuildingNumber(String Str_Target,String Str_Alk,String Str_PostCode,String Str_Qualifier,int BuildingNumber) throws Exception{

		String line = "", oldtext = "";
		int Int_LineCounter = 0;
		int Int_InsertCounter=0;
		int Int_InsertPosition = 0;
		String Str_Address="";
		File file = new File(Str_Target);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while((line = reader.readLine()) != null)Int_LineCounter++;
		reader.close();
		Int_InsertPosition=Int_LineCounter - 3;
		System.out.println(Int_InsertPosition);
		if(Str_Qualifier.equalsIgnoreCase("GOLD")){
			//			System.out.println("in Gold");
			//			Str_Address="\t<Address>"+ "\r\n"
			//					+ "\t<BuildingName>"+BuildingNumber+"</BuildingName>"+ "\r\n"
			//					+ "\t<Street>White City</Street>"+ "\r\n"
			//					+ "\t<PostTown>REDDY MARG</PostTown>"+ "\r\n"
			//					+ "\t<County>United Kingdoms</County>"+ "\r\n"
			//					+ "\t<PostCode>"+Str_PostCode+"</PostCode>"+ "\r\n"
			//					+"\t<ALK>"+Str_Alk+"</ALK>"+ "\r\n"
			//					+ "\t<CSSDistrictCode>ES</CSSDistrictCode>"+ "\r\n"
			//					+ "\t<ExchangeCode>LVB</ExchangeCode>"+ "\r\n"
			//					+ "\t<Qualifier>Gold</Qualifier>"+ "\r\n"
			//					+ "\t</Address>\r\n";
			//			
			//
			//
			//		}else{
			Str_Address="<ns0:PostCodeValidatedSite>"+ "\r\n"
					+ "<ns0:Address>"+ "\r\n"
					+"\t<ns0:ALK>"+Str_Alk+"</ns0:ALK>"+ "\r\n"
					+ "\t\t<ns0:BuildingName>White City</ns0:BuildingName>"+ "\r\n"
					+ "\t\t<ns0:BuildingNumber>"+BuildingNumber+"</ns0:BuildingNumber>"+ "\r\n"
					+ "\t<ns0:CSSDistrictCode>LC</ns0:CSSDistrictCode>"+ "\r\n"
					+ "\t<ns0:ExchangeCode>LCROC</ns0:ExchangeCode>"+ "\r\n"
					+"\t<ns0:IsPostCodeValid>true</ns0:IsPostCodeValid>"+ "\r\n"
					+ "\t\t<ns0:Locality>IRLAM</ns0:Locality>"+ "\r\n"
					+ "\t\t<ns0:OrganisationName>E TUPLING SON LTD</ns0:OrganisationName>"+ "\r\n"
					+ "\t\t<ns0:PostCode>"+Str_PostCode+"</ns0:PostCode>"+ "\r\n"
					+ "\t\t<ns0:PostTown>MANCHESTER</ns0:PostTown>"+ "\r\n"
					+ "\t\t<ns0:Qualifier>Gold</ns0:Qualifier>"+ "\r\n"
					+ "\t\t<ns0:Street>SIEMENS ROAD</ns0:Street>"+ "\r\n"
					+ "\t\t<ns0:SubBuilding>2</ns0:SubBuilding>"+ "\r\n"
					+ "\t<ns0:Technologies>"+ "\r\n"
					+ "\t\t<ns0:Technology>"+ "\r\n"
					+ "\t\t\t<ns0:IsAssociated>false</ns0:IsAssociated>"+ "\r\n"
					+ "\t\t\t<ns0:IsRestricted>false</ns0:IsRestricted>"+ "\r\n"
					+ "\t\t\t<ns0:Name>TTG</ns0:Name>"+ "\r\n"
					+ "\t\t</ns0:Technology>"+ "\r\n"
					+ "\t</ns0:Technologies>"+ "\r\n"
					+ "\t</ns0:Address>"+ "\r\n"
					+ "\t</ns0:PostCodeValidatedSite>";
		}
		System.out.println(Str_Address);

		line="";
		BufferedReader reader1 = new BufferedReader(new FileReader(file));

		while((line = reader1.readLine()) != null){

			if(Int_InsertCounter==Int_InsertPosition){	
				//System.out.println("Matched");
				oldtext += Str_Address + "\n";

			}
			oldtext += line + "\r\n";
			Int_InsertCounter++;
		}
		System.out.println(oldtext);
		reader1.close();

		FileWriter writer = new FileWriter(Str_Target);
		writer.write(oldtext);writer.flush();writer.close();
	}
	public void IssueAddressKeyGenerate(String Str_Target,String Str_Alk,String Str_PostCode,String Str_Qualifier,int BuildingNumber) throws Exception{
		//String Str_Alk="A"+Str_CLI;

		String line = "", oldtext = "";
		int Int_LineCounter = 0;
		int Int_InsertCounter=0;
		int Int_InsertPosition = 0;
		String Str_Address="";
		File file = new File(Str_Target);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		while((line = reader.readLine()) != null)Int_LineCounter++;
		reader.close();

		Int_InsertPosition=Int_LineCounter - 5;
		Str_Address="<BuildingNumber>"+BuildingNumber+"</BuildingNumber>\n\t\t"
				+ "<Street>Worton Way</Street>\n\t\t "
				+ "<Locality>Twickenham</Locality>"
				+ "<PostTown>ISLEWORTHy</PostTown>"
				+ "<PostCode>"+Str_PostCode+"</PostCode>"
				+ "<ALK>"+Str_Alk+"</ALK>"
				+ "<CSSDistrictCode>SS</CSSDistrictCode>"
				+ "<ExchangeCode>LCROC</ExchangeCode>"
				+ "<Qualifier>Silver</Qualifier>";

		line="";
		BufferedReader reader1 = new BufferedReader(new FileReader(file));
		while((line = reader1.readLine()) != null){
			oldtext += line + "\r\n";
			if(Int_InsertCounter==Int_InsertPosition){	
				//System.out.println("Matched");
				oldtext += Str_Address + "\n";
			}
			Int_InsertCounter++;
		}
		reader1.close();

		FileWriter writer = new FileWriter(Str_Target);
		writer.write(oldtext);writer.flush();writer.close();
	}


	public void AppointmentStub(AppoitnmentStubType AppStubType, boolean SlotAvailability ) throws Exception{
		String DATE = null;
		String SLOT = null;
		File File_Source = null;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		switch(AppStubType){
		case BT:

			String BT1 = 	"<?xml version=\"1.0\" encoding=\"utf-16\"?>"
					+ "\n<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
					+ "\n<soap:Body>"
					+ "\n<GetAvailableAppointmentsResponse xmlns=\"https://webservices.opalonline.co.uk/AppointingService\">"
					+ "\n<GetAvailableAppointmentsResult>"
					+ "\n<Status>"
					+ "\n<HasErrors>false</HasErrors>"
					+ "\n</Status>"
					+ "<ListOfAppointments>";



			String BT2 = "\n</ListOfAppointments>"
					+ "\n</GetAvailableAppointmentsResult>"
					+ "\n</GetAvailableAppointmentsResponse>"
					+ "\n</soap:Body>"
					+ "\n</soap:Envelope>";

			for(int i=0;i<90;i++){
				Calendar cs = Calendar.getInstance();
				cs.add(Calendar.DATE, i);
				int dayOfWeek = cs.get(Calendar.DAY_OF_WEEK);
				DATE = formatter.format(cs.getTime());
				if((dayOfWeek==7)||(dayOfWeek==1)){
				}else{
					BT1 = BT1 + 

							"\n<AppointmentSlotInfo><AppointmentDate>"+DATE+"T10:20:00-05:00</AppointmentDate><AppointmentTimeSlot>AM</AppointmentTimeSlot></AppointmentSlotInfo>";
					BT1 = BT1 + 

							"\n<AppointmentSlotInfo><AppointmentDate>"+DATE+"T10:20:00-05:00</AppointmentDate><AppointmentTimeSlot>PM</AppointmentTimeSlot></AppointmentSlotInfo>";
					BT1 = BT1 + 

							"\n<AppointmentSlotInfo><AppointmentDate>"+DATE+"T10:20:00-05:00</AppointmentDate><AppointmentTimeSlot>EV</AppointmentTimeSlot></AppointmentSlotInfo>";

				}
			}		
			BT1 = BT1 + BT2;

			FileWriter writer1 = new FileWriter(OUTPUTFILE_DIR+BTAPPOINTMENT_FILE_NAME+".txt");
			writer1.write(BT1);writer1.flush();writer1.close();
			File_Source = new File(OUTPUTFILE_DIR+BTAPPOINTMENT_FILE_NAME+".txt");
			StubFileCopy(File_Source, Stub_BT_APPOINTMENT+BTAPPOINTMENT_FILE_NAME+".txt");

			break;

		case EVG:

			String EVG1 = 	"<AvailabilityResponse>"
					+ "\n<Response> "
					+ "\n<Code>2147483647</Code> "
					+ "\n<Message>String content</Message> "
					+ "\n</Response> "
					+ "\n<Success>true</Success>";
			String EVG2 = "\n</AvailabilityResponse>";

			if(SlotAvailability){
				SLOT = "3000";
			}else{
				SLOT="0";
			}
			for(int i=0;i<90;i++){
				Calendar cs = Calendar.getInstance();
				cs.add(Calendar.DATE, i);
				int dayOfWeek = cs.get(Calendar.DAY_OF_WEEK);
				DATE = formatter.format(cs.getTime());
				if((dayOfWeek==7)||(dayOfWeek==1)){
				}else{
					EVG1 = EVG1 + "\n<Slot VisitDate=\""+DATE+"\" VisitWindowStart=\"09:00:00.000\" VisitWindowEnd=\"11:00:00.000\" Availability=\"High\" EngineerCount=\""+SLOT+"\"/>";
					EVG1 = EVG1 + "\n<Slot VisitDate=\""+DATE+"\" VisitWindowStart=\"15:00:00.000\" VisitWindowEnd=\"18:00:00.000\" Availability=\"High\" EngineerCount=\""+SLOT+"\"/>";
				}
			}		
			EVG1 = EVG1 +EVG2;
			FileWriter writer = new FileWriter(OUTPUTFILE_DIR+EVGAPPOINTMENT_FILE_NAME+".xml");
			writer.write(EVG1);writer.flush();writer.close();
			File_Source = new File(OUTPUTFILE_DIR+EVGAPPOINTMENT_FILE_NAME+".xml");
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_EVG_Query_Slot_Qube);
			StubFileSFTP(SFTPStubType.DERBY, File_Source, Stub_EVG_Query_Slot_Qube1);
			break;

		default:
			break;
		}
	}
	public void  Fetch_RC_DB_Details(){

		URL url;
		HttpURLConnection connection = null;  
		StringBuffer response = null;
		String responseString= null;
		String RCdetails = null;

		try{
			url = new URL(EVG_RC_URL);

			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches (false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//Send request
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
			wr.writeBytes ("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"
					+ "Accept-Encoding:gzip,deflate,sdch"
					+ "Accept-Language:en-US,en;q=0.8"
					+ "Cache-Control:max-age=0"
					+ "Connection:keep-alive"
					+ "Host:10.155.38.169");
			wr.flush ();
			wr.close ();
			//Get Response    
			InputStream is ;
			if(connection.getResponseCode()<=400){
				is=connection.getInputStream();

			}else{
				is = connection.getErrorStream();
			} 
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			response = new StringBuffer(); 
			while((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			responseString = response.toString();
		} catch (Exception e) {
			//	return null;
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.disconnect(); 
			}
		}	
		String html =responseString ;
		Document doc = Jsoup.parse(html);
		Elements tables = doc.select("table");
		Elements myTdsstudent = tables.select("tr");
		int j = 0;
		for (Element myTdsstudentIterator: myTdsstudent) {
			RCdetails = myTdsstudentIterator.select("td:eq(2)").text();
			if(myTdsstudentIterator.select("td:eq(3)").text().contains(LoadEnvironment.ENV)){
				//  System.out.println(myTdsstudentIterator.select("td:eq(2)").text());
				RCdetails = myTdsstudentIterator.select("td:eq(2)").text();
				if(j>0){
					if((RCdetails.contains("evg"))||(RCdetails.contains("EVG")))
						break;
				}else{
					j++;
				}
			}
		}
		String [] details = new String[3];
		int i=0;
		for(String RC : RCdetails.split("/")){
			details[i]=RC;
			i++;
		}
		//System.out.println("EVG Properties set to: EVG IP: PORT "+details[0]+" EVG DBNAME "+details[1]+" DBUSERNAME and DBPASSWORD "+details[2]);
		if((RCdetails.contains("evg"))||(RCdetails.contains("EVG"))){
			LoadEnvironment.EVG_DBPORT = details[0].substring(details[0].indexOf(':')+1);
			LoadEnvironment.EVG_DBIP =details[0].substring(0, details[0].indexOf(':'));
			LoadEnvironment.EVG_DBNAME = details[1];
			if(details[2]==null){
				LoadEnvironment.EVG_DBUSERNAME="evg";	
				LoadEnvironment.EVG_DBPASSWORD="evg";
			}else{
				LoadEnvironment.EVG_DBUSERNAME = details[2];
				LoadEnvironment.EVG_DBPASSWORD = details[2];
			}
			System.out.println("EVG Properties set to: EVG IP - > "+LoadEnvironment.EVG_DBIP+" PORT - > "+LoadEnvironment.EVG_DBPORT+" EVG DBNAME - > "+LoadEnvironment.EVG_DBNAME+" DBUSERNAME - > "+LoadEnvironment.EVG_DBUSERNAME+" DBPASSWORD - >"+LoadEnvironment.EVG_DBPASSWORD);
		}else{
			System.out.println("NO EVG Instances Pointed to "+LoadEnvironment.ENV);
		}
	}


	public void GetFile(String Str_Source,String File_Dest,String BW_ServerIP,String BW_UserName,String BW_Password) throws Exception{

		JSch jsch=new JSch();
		Session session=null;
		Channel channel=null;
		ChannelSftp channelSftp=null;
		try {

			session = jsch.getSession(BW_UserName, BW_ServerIP, 22);
			session.setPassword(BW_Password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			System.out.println("------------session Disconnected----------------");
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("------------session Disconnected----------------");
			channelSftp = (ChannelSftp) channel;
			channelSftp.get(Str_Source,File_Dest);

		}finally{
			channelSftp.exit();
			session.disconnect();
			System.out.println("file SFTP Complete to  "+File_Dest);

		}

	}
	public void PutFile(String Str_Source,String File_Dest,String BW_ServerIP,String BW_UserName,String BW_Password) throws Exception{

		JSch jsch=new JSch();
		Session session=null;
		Channel channel=null;
		ChannelSftp channelSftp=null;
		try {

			session = jsch.getSession(BW_UserName, BW_ServerIP, 22);
			session.setPassword(BW_Password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			System.out.println("------------session Disconnected----------------");
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("------------session Disconnected----------------");
			channelSftp = (ChannelSftp) channel;
			channelSftp.put(Str_Source,File_Dest);


		}finally{
			channelSftp.exit();
			session.disconnect();
			System.out.println("file SFTP Complete to  "+File_Dest);

		}

	}

	public  void PackageStubbing(String Package,String Str_CLI,String ALK,String  FibType,String ActivityType) throws Exception
	{
		StubType FT = null;

		switch(FibType)
		{	
		case "NGAv2CLI_ENG": 		
			FT=StubType.NGAv2CLI_ENG_new;
			break;
		case "NGAALK_SpeedLT5" 	  :	  	    
			FT=StubType.NGAALK_new_SpeedLT5;
			break;
		case "DoubleMigration": 	    
			FT=StubType.NGAGEA;
			break; 
		case "NGAGEA_SINO_MILT5"        :	    
			FT=StubType.NGAGEA_SINO_MILT5;
			break;
		case "NGAv2CLI_SI_LE5mbps"   : 	   
			FT=StubType.NGAv2CLI_new_SI_LE5mbps;
			break;
		case "NGAALK":					    
			FT=StubType.NGAALK_new;
			break;
		case "NGAALK_ENG":
			FT=StubType.NGAALK_SINO_MIYES;
			break;
		case "NGAv2CLI":
			FT=StubType.NGAv2CLI_new;
		case "None":
			break;
		}

		switch(ActivityType)
		{
		case "InitialSale" :	PlaceFile(StubType.IPTVM, Str_CLI);
		break;
		case "newline" :	    PlaceFile(StubType.LLU_ALK_Newline, ALK);
		break;
		case "workingline"	   :	PlaceFile(StubType.LLU_ALK_BTLive, ALK);												
		break;
		case "stopline"    :    PlaceFile(StubType.LLU_ALK_BTStop, ALK);	
		}

		if((Package.contains("TV"))&&(Package.contains("Fibre")))
		{
			switch(ActivityType)
			{
			case "InitialSale" :
				PlaceFile(StubType.IPTVM, Str_CLI);
				PlaceFile(FT, Str_CLI);
				System.out.println("Stubbed for Plus Tv and Fibre");
				break;
			case "newline" :	
				PlaceFile(StubType.LLU_ALK_Newline, ALK);
				PlaceFile(FT, ALK);
				break;
			case "stopline"	   :	
				PlaceFile(StubType.LLU_ALK_BTStop, ALK);
				PlaceFile(FT, ALK);
				break;		
			case "workingline" :
				PlaceFile(StubType.LLU_ALK_BTLive, ALK);
				PlaceFile(FT, ALK);
				break;

			}
		}

		if((Package.contains("TV")))
		{
			switch(ActivityType)
			{
			case "InitialSale" :	PlaceFile(StubType.IPTVM, Str_CLI);
			break;
			case "newline" :	PlaceFile(StubType.LLU_ALK_Newline, ALK);	
			break;
			case "workingline"	   :	PlaceFile(StubType.LLU_ALK_BTLive, ALK);												
			break;	
			case "stopline"	   :	PlaceFile(StubType.LLU_ALK_BTStop, ALK);												
			break;	
			}
			System.out.println("Stubbed for TV");
		}

		if(Package.contains("simply"))
		{
			switch(ActivityType)
			{
			case "InitialSale" :	PlaceFile(StubType.LLUv5, Str_CLI);
			break;
			case "newline" :	PlaceFile(StubType.LLU_ALK_Newline, ALK);	
			break;
			case "stopline"	   :	PlaceFile(StubType.LLU_ALK_BTStop, ALK);												
			break;	
			case "workingline"	   :	PlaceFile(StubType.LLU_ALK_BTLive, ALK);												
			break;	
			}
			System.out.println("Stubbed for LLU");

		}
		if((Package.contains("simply"))&& (Package.contains("fibre")))
		{	
			switch(ActivityType)
			{
			case "InitialSale" :	PlaceFile(StubType.LLUv5, Str_CLI);
			PlaceFile(FT, Str_CLI);
			break;
			case "newline" :	PlaceFile(StubType.LLU_ALK_Newline, ALK);
			PlaceFile(FT, ALK);
			break;
			case "stopline"	   :	PlaceFile(StubType.LLU_ALK_BTStop, ALK);
			PlaceFile(FT, ALK);
			break;	
			case "workingline"	   :	PlaceFile(StubType.LLU_ALK_BTLive, ALK);
			PlaceFile(FT, ALK);
			break;
			}

			System.out.println("Stubbed for LLU and fibre");
		}
		if(Package.contains("Unicast"))
		{
			switch(ActivityType)
			{
			case "InitialSale" :	PlaceFile(StubType.IPTVU, Str_CLI);
			break;
			case "newline" :	PlaceFile(StubType.LLU_ALK_Newline_Unicast, ALK);	
			break;
			case "stopline"	   :	PlaceFile(StubType.LLU_ALK_BTStop_Unicast, ALK);												
			break;
			case "workingline"	   :	PlaceFile(StubType.LLU_ALK_BTLive_Unicast, ALK);												
			break;	
			}
			System.out.println("Stubbed for TV Unicast");
		}


	} 
	public void Stub_UFO_Postcode(String Str_Address,String Str_Postcode,String Str_Uprn,String Str_StubName){
		String OUTPUTFILE_DIR 	= System.getProperty("user.dir")+"\\ProvisioningUpdates\\";
		File newfile = new File(OUTPUTFILE_DIR+Str_Postcode+".xml");
		try{

			String[] Str_SplitAdress	=	Str_Address.split(":");
			String	Str_BuildingNumber	=	Str_SplitAdress[3];
			String	Str_BuildingName	=	Str_SplitAdress[2];
			String	Str_ThroughFareName	=	Str_SplitAdress[4];
			String Str_BName			=	"";
			//ALK+":"+FilterDetails("subBuildingName")+":"+FilterDetails("buildingName")+":"+FilterDetails("buildingNumber")+":"+FilterDetails("thoroughfareName");
			if(Str_BuildingNumber.length() != 0 && Str_BuildingNumber != null && !Str_BuildingNumber.equalsIgnoreCase("null")){
				Str_BName				=	Str_BuildingNumber;
			}if(Str_BuildingName.length() != 0 && Str_BuildingName != null && !Str_BuildingName.equalsIgnoreCase("null")){
				Str_BName				=	Str_BuildingName;
			}
			File file = new File(Template_NPAC_MUL_LINES_PATH+Str_StubName+".xml");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "",newtext="";
			while((line = reader.readLine()) != null){
				oldtext += line + "\r\n";
			}
			reader.close();
			newtext = oldtext.replaceAll("M_BName",Str_BName );
			newtext = newtext.replaceAll("M_Postcode", Str_Postcode);
			newtext = newtext.replaceAll("M_UPRN",  Str_Uprn);
			newtext = newtext.replaceAll("M_ThroughFareName",  Str_ThroughFareName);
			FileWriter writer = new FileWriter(newfile);
			writer.write(newtext);
			writer.flush();
			writer.close();
			StubFileSFTP(SFTPStubType.DERBY, newfile, Stub_NPAC);
		}catch(NullPointerException npe){

		}catch(Exception e){

		}

	}
	public void Stub_UFO_UPRN(String Str_Address,String Str_Postcode,String Str_Uprn,String Str_StubName){
		String OUTPUTFILE_DIR 	= System.getProperty("user.dir")+"\\ProvisioningUpdates\\";
		File newfile = new File(OUTPUTFILE_DIR+Str_Uprn+".xml");
		try{

			String[] Str_SplitAdress	=	Str_Address.split(":");
			String	Str_BuildingNumber	=	Str_SplitAdress[3];
			String	Str_BuildingName	=	Str_SplitAdress[2];
			String	Str_ThroughFareName	=	Str_SplitAdress[4];
			String Str_BName			=	"";
			String Str_BNumber			=	"";
			//ALK+":"+FilterDetails("subBuildingName")+":"+FilterDetails("buildingName")+":"+FilterDetails("buildingNumber")+":"+FilterDetails("thoroughfareName");
			if(Str_BuildingNumber.length() != 0 && Str_BuildingNumber != null && !Str_BuildingNumber.equalsIgnoreCase("null")){
				Str_BNumber				=	Str_BuildingNumber;
			}else{
				Str_BNumber				=	"null";
			}
			if(Str_BuildingName.length() != 0 && Str_BuildingName != null && !Str_BuildingName.equalsIgnoreCase("null")){
				Str_BName				=	Str_BuildingName;
			}else{
				Str_BName				=	"null";
			}
			File file = new File(Template_NPAC_MUL_LINES_PATH+Str_StubName+".xml");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "",newtext="";
			while((line = reader.readLine()) != null){
				oldtext += line + "\r\n";
			}
			reader.close();
			newtext = oldtext.replaceAll("M_BName",Str_BName );
			newtext = newtext.replaceAll("M_BNumber",Str_BNumber );
			newtext = newtext.replaceAll("M_Postcode", Str_Postcode);
			newtext = newtext.replaceAll("M_UPRN",  Str_Uprn);
			FileWriter writer = new FileWriter(newfile);
			writer.write(newtext);
			writer.flush();
			writer.close();
			StubFileSFTP(SFTPStubType.DERBY, newfile, Stub_NPAC);
		}catch(NullPointerException npe){

		}catch(Exception e){

		}

	}
	public void Stub_UFO_PORTING(String Str_CLI,String Str_AddressKey,String ALI,String Str_CurrentCP,String Str_NumberRangeHolder,String Str_Postcode,String Str_StubName){
		String OUTPUTFILE_DIR 	= System.getProperty("user.dir")+"\\ProvisioningUpdates\\";
		File newfile = new File(OUTPUTFILE_DIR+Str_CLI+".xml");
		try{
			File file = new File(Template_NPAC_MUL_LINES_PATH+Str_StubName+".xml");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "",newtext="";
			while((line = reader.readLine()) != null){
				oldtext += line + "\r\n";
			}
			reader.close();
			newtext = oldtext.replaceAll("M_AddressKey",Str_AddressKey );  //Alk
			newtext = newtext.replaceAll("M_ALI",ALI );						//AK
			newtext = newtext.replaceAll("M_Postcode", Str_Postcode);
			newtext = newtext.replaceAll("M_CurrentCP",  Str_CurrentCP);
			newtext = newtext.replaceAll("M_NRH",  Str_NumberRangeHolder);
			newtext = newtext.replaceAll("M_CLI",  Str_CLI);
			FileWriter writer = new FileWriter(newfile);
			writer.write(newtext);
			writer.flush();
			writer.close();
			StubFileSFTP(SFTPStubType.DERBY, newfile, Stub_NPAC);
		}catch(NullPointerException npe){

		}catch(Exception e){

		}

	}
	public void Stub_PostCodeAddress_Lightning(String Str_UPRN,String Str_PostCode,String BuildingNumber,String StubType) throws Exception{
		String Stub_Postcode = "";
		if(StubType.equalsIgnoreCase("NPAC"))
			Stub_Postcode =  Stub_NPAC+"/"+Str_PostCode+".xml";
		else
			Stub_Postcode =  Stub_Lightning+"/"+Str_PostCode+".xml";
		String Str_Target=System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+Str_PostCode+".xml";

		GetFile(Stub_Postcode, Str_Target,DERBYSTUB_host,DERBYSTUB_auth,DERBYSTUB_pass);

		PostcodeGenerate_Lightning(Str_Target,Str_UPRN,Str_PostCode,BuildingNumber);

		PutFile(Str_Target,Stub_Postcode, DERBYSTUB_host,DERBYSTUB_auth,DERBYSTUB_pass);
		System.out.println("building no completed");
	}

	public void PostcodeGenerate_Lightning(String Str_Target,String Str_UPRN,String Str_PostCode,String BuildingNumber) throws Exception{
		//String Str_Alk="A"+Str_CLI;
		String line = "", oldtext = "";
		int Int_LineCounter = 0;
		int Int_InsertCounter=0;
		int Int_InsertPosition = 0;
		String Str_Address="";
		File file = new File(Str_Target);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while((line = reader.readLine()) != null)Int_LineCounter++;
		reader.close();
		Int_InsertPosition=Int_LineCounter - 6;
		System.out.println(Int_InsertPosition);
		Str_Address="\t<pfx:FTTPAddressDetails>"+ "\r\n"
				+ "\t<pfx:BuildingNumber>"+BuildingNumber+"</pfx:BuildingNumber>"+ "\r\n"
				+ "\t<pfx:CabinetID>YOP03C26</pfx:CabinetID>"+ "\r\n"
				+ "\t<pfx:PostTown>YORK</pfx:PostTown>"+ "\r\n"
				+ "\t<pfx:Postcode>"+Str_PostCode+"</pfx:Postcode>"+ "\r\n"
				+ "\t<pfx:Street>White City </pfx:Street>"+ "\r\n"
				+ "\t<pfx:UPRN>"+Str_UPRN+"</pfx:UPRN>"+ "\r\n"
				+ "\t</pfx:FTTPAddressDetails>"+ "\r\n";

		System.out.println(Str_Address);

		line="";
		BufferedReader reader1 = new BufferedReader(new FileReader(file));

		while((line = reader1.readLine()) != null){

			if(Int_InsertCounter==Int_InsertPosition){	
				//System.out.println("Matched");
				oldtext += Str_Address + "\n";

			}
			oldtext += line + "\r\n";
			Int_InsertCounter++;
		}
		System.out.println(oldtext);
		reader1.close();

		FileWriter writer = new FileWriter(Str_Target);
		writer.write(oldtext);writer.flush();writer.close();
	} 

	/*public String place_the_required_stub_files(String Str_Address,String Str_PostCode,String Str_StubType)
	{
		String Str_CLI="";
		Str_StubType=Str_StubType.replaceAll(" ", "").toLowerCase();
		boolean boolean_CeaseOrder=false;
		boolean Past_Future=true;
		String LineType="";
		if(Str_StubType.contains("pcotrue"))
		{
			if(Str_StubType.contains("csd<ccd"))
			{
				Past_Future=false;
			}
			boolean_CeaseOrder=true;
		}
		if(Str_StubType.contains("switcher"))
		{
			Str_CLI=RandomGenerator.randomCLI(9);
			if(boolean_CeaseOrder)
			{
				PlaceFile(StubType.NPAC_CP_MC, Str_CLI);
			}
			else{
				if(boolean_CeaseOrder)
				{
					PlaceFile(StubType.NPAC_CP_MC_CeaseTrue, Str_CLI);
				}
			}
		}
		if(Str_Address.equals("")){
//			FetchALK FA=new FetchALK(Report);
//			String Str_ALKAddress=FA.GETALK_SAD_MNAREQ(SeleniumSetup.ENV,Str_Data[8]);
//			String Str_AddressKey=Str_ALKAddress.split(":")[0];
//			Str_Address=Str_ALKAddress.substring(Str_ALKAddress.indexOf(Str_AddressKey)+Str_AddressKey.length()).replaceAll(":", "");
			String Str_AddressKey="A00024675908";
			Str_Address="60bVictoria Road West";
			System.out.println("Address is "+Str_Address);
			PlaceFile(StubType.NPAC_ExchangeStatus, Str_PostCode);
			if(boolean_CeaseOrder)
			{
				if(Str_StubType.contains("opal"))
				{
					System.out.println("OPAL Live with CEASE");
					PlaceFile(StubType.NPAC_LLU_ALK_OpalLiveWithCease, Str_AddressKey,Past_Future);
				}
				else
				{
					System.out.println("BT Live with CEASE");
					PlaceFile(StubType.NPAC_LLU_ALK_BTLive_Withcease, Str_AddressKey,Past_Future);
				}
			}
			else if(Str_StubType.contains("newline")||(Str_StubType.contains("switcher")&&Str_StubType.contains("nl"))){
				if(Str_StubType.contains("low")){
					System.out.println("NEWline Low Speed");
					PlaceFile(StubType.NPAC_Low_ExchangeStatus, Str_PostCode);
					PlaceFile(StubType.NPAC_Low_LLU_ALK, Str_AddressKey);
				}
				else{
					System.out.println("NEWline");
					PlaceFile(StubType.NPAC_LLU_ALK_Newline, Str_AddressKey);
				}
			}
			else if(Str_StubType.contains("stopline")){
				System.out.println("STOPline");
				PlaceFile(StubType.NPAC_LLU_ALK_BTStop, Str_AddressKey);
			}
			else if(Str_StubType.contains("workingline")||(Str_StubType.contains("switcher")&&Str_StubType.contains("wlto"))){
				if(Str_StubType.contains("opal"))
				{
					System.out.println("OPAL Live");
					PlaceFile(StubType.NPAC_LLU_ALK_OpalLive, Str_AddressKey);
				}
				else{
					System.out.println("BT Live");
					PlaceFile(StubType.NPAC_LLU_ALK_BTLive, Str_AddressKey);
				}
			}
			PlaceFile_PortingCLI()
		}
		return Str_CLI;
	}*/
}
