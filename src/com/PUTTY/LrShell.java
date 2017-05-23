package com.PUTTY;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.Engine.LoadEnvironment;
import com.Engine.Reporter;
import com.Engine.SeleniumSetup;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class LrShell extends SeleniumSetup {

	public LrShell(Reporter report) {
		Report=report;
	}

	public String LrShell_getLogfile(String TargetPattern,String LogFileName,String StartPattern_X,String EndPattern_Y,String Str_CLI,boolean XY) throws Exception{
		/*****************************************************************************************************/	
		String StartPattern=StartPattern_X;
		String EndPattern=EndPattern_Y;

		if(XY)
		{
			StartPattern = "<root xmlns:jms1="+StartPattern_X+" xmlns="+EndPattern_Y+">";
			EndPattern= "</root>";
		}

		String LogFile = "/opt/tibco/tra/domain/TTG" + LoadEnvironment.ENV + "/application/logs/"+LogFileName;
		String OutputFilename = "LogXML_"+Str_CLI+".xml";

		System.out.println("Log--------------"+LogFile);
		System.out.println("Start Pattern------------------"+StartPattern);

		LogXmlParser Logs = new LogXmlParser();

		Session session = Logs.CreateJschSession("baswarp", "year@2013", LoadEnvironment.BW_SERVERIP);
		Channel channel = Logs.CreateChannelforExecution(session, "shell");
		PrintStream commander = Logs.CreateCommander(channel);
		Logs.CommandSender(commander, "cd /home/baswarp");
		Logs.CommandSender(commander, "./Sample_xml_parser_param.csh "+LogFile+" '"+StartPattern+"' '"+EndPattern+"' '"+TargetPattern+"' '"+OutputFilename+"'");
		Logs.CommandSender(commander, "cp output.txt "+OutputFilename);
		Logs.CommandSender(commander, "sed 's/.*\\(<root>*\\)/\\1/' output.txt > "+OutputFilename);
		Logs.CommandSender(commander, "exit");
		Logs.CloseCommandSender(commander);
		Logs.DisplayShell(channel);

		Logs.CloseChannel(channel);
		Logs.CloseSession(session);

		System.out.println("/home/baswarp/" + OutputFilename);
		Logs.GetFile("/home/baswarp/" + OutputFilename,System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+OutputFilename);
		return OutputFilename;

		/*************************END of Main Test*****************************************//*
		Report.reportTest();*/


	}
	public String LrShell_getLogfileOtherPattern(String TargetPattern,String LogFileName,String StartPattern_X,String EndPattern_Y,String Str_Account,boolean XY,String command) throws Exception{
		/*****************************************************************************************************/	
		String StartPattern=StartPattern_X;
		String EndPattern=EndPattern_Y;
		String Command=command;		
		switch(command)
		{
		case "LLUCEASE":
			System.out.println("LLUCEASE");	
			StartPattern= "<ns0:CeaseLLURequest2 xmlns:jms1="+StartPattern_X+" xmlns:ns0="+EndPattern_Y+">";
			EndPattern= "</ns0:CeaseLLURequest2>";
			break;

		case "CancelIPS":
			System.out.println("CancelIPS");	
			StartPattern= "<ns0:CancelIPSRequest xmlns:jms1="+StartPattern_X+" xmlns:ns0="+EndPattern_Y+">";
			EndPattern= "</ns0:CancelIPSRequest>";
			break;		

		case "CancelLLU":
			System.out.println("CancelLLU");
			StartPattern= "<ns0:CancelLLURequest2 xmlns:jms1="+StartPattern_X+" xmlns:ns0="+EndPattern_Y+">";
			EndPattern= "</ns0:CancelLLURequest2>";
			break;	

		case "ProvideTakeOverLLU":
			System.out.println("ProvideTakeOverLLU");
			StartPattern= "<ns0:ProvideTakeOverLLURequest xmlns:jms1="+StartPattern_X+" xmlns:ns0="+EndPattern_Y+">";
			EndPattern= "</ns0:ProvideTakeOverLLURequest>";
			break;	

		case "ModifyLLU":
			System.out.println("ModifyLLU");
			StartPattern= "<ns0:ModifyLLURequest2 xmlns:jms1="+StartPattern_X+" xmlns:ns0="+EndPattern_Y+">";
			EndPattern= "</ns0:ModifyLLURequest2>";
			break;	

		case "NotifyChangePackage":
			System.out.println("NotifyChangePackage");
			StartPattern= "<ns0:NotifyChangePackageRequest xmlns:jms1="+StartPattern_X+" xmlns:ns0="+EndPattern_Y+">";
			EndPattern= "</ns0:NotifyChangePackageRequest>";
			break;
		case "SubmitOrder":
			System.out.println("SubmitOrder");
			StartPattern= "<ns1:CustomerOrder xmlns:ns1="+EndPattern_Y+">";
			EndPattern= "</ns1:CustomerOrder>";
			break;
		case "CTP":
			System.out.println("CTP");
			StartPattern= "<ns0:CheckTargetPremisesReply xmlns:ns0=\"http://xmlns.cpw.co.uk/CPW/Common_Functions/Provisioning/CheckTargetPremises\">";
			EndPattern= "</ns0:CheckTargetPremisesReply>";
			break;
		case "CSA":
			System.out.println("CSA");
			StartPattern= "<ns0:CheckTargetPremisesReply xmlns:ns0=\"http://xmlns.cpw.co.uk/CPW/Common_Functions/Provisioning/CheckTargetPremises\">";
			EndPattern= "</ns0:CheckTargetPremisesReply>";
			break;
		case "CTPHM":
			System.out.println("CTPHM");
			StartPattern= "<ns0:CheckTargetPremisesRequest xmlns:ns0=\"http://xmlns.cpw.co.uk/CPW/Common_Functions/Provisioning/CheckTargetPremises\">";
			EndPattern= "</ns0:CheckTargetPremisesRequest>";
			break;
		}

		String LogFile = "/opt/tibco/tra/domain/TTG" + LoadEnvironment.ENV + "/application/logs/"+LogFileName;
		String OutputFilename = "LogXML_"+Str_Account+".xml";
		System.out.println(OutputFilename);

		System.out.println("Log--------------"+LogFile);
		System.out.println("Start Pattern------------------"+StartPattern);

		LogXmlParser Logs = new LogXmlParser();

		Session session = Logs.CreateJschSession("baswarp", "year@2013", LoadEnvironment.BW_SERVERIP);
		Channel channel = Logs.CreateChannelforExecution(session, "shell");
		PrintStream commander = Logs.CreateCommander(channel);
		Logs.CommandSender(commander, "cd/home/baswarp");
		System.out.println("s"+StartPattern);
		System.out.println("e"+EndPattern);
		System.out.println("t"+TargetPattern);
		System.out.println("l"+LogFile);
		Logs.CommandSender(commander, "./Sample_xml_parser_param.csh "+LogFile+" '"+StartPattern+"' '"+EndPattern+"' '"+TargetPattern+"' '"+OutputFilename+"'");
		Logs.CommandSender(commander, "cp output.txt "+OutputFilename);

		switch(command)
		{
		case "LLUCEASE":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns0:CeaseLLURequest2>*\\)/\\1/' output.txt > "+OutputFilename);
			break;

		case "CancelIPS":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns0:CancelIPSRequest>*\\)/\\1/' output.txt > "+OutputFilename);
			break;	

		case "CancelLLU":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns0:CancelLLURequest2>*\\)/\\1/' output.txt > "+OutputFilename);
			break;

		case "ProvideTakeOverLLU":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns0:ProvideTakeOverLLURequest>*\\)/\\1/' output.txt > "+OutputFilename);
			break;

		case "ModifyLLU":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns0:ModifyLLURequest2>*\\)/\\1/' output.txt > "+OutputFilename);
			break;

		case "NotifyChangePackage":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns0:NotifyChangePackageRequest>*\\)/\\1/' output.txt > "+OutputFilename);
			break;
		case "SubmitOrder":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns1:CustomerOrder>*\\)/\\1/' output.txt > "+OutputFilename);
			break;	
		case "CTP":
			Logs.CommandSender(commander, "sed 's/.*\\(<ns0:CheckTargetPremisesReply>*\\)/\\1/' output.txt > "+OutputFilename);
			break;

		}

		Logs.CommandSender(commander, "exit");
		Logs.CloseCommandSender(commander);
		Logs.DisplayShell(channel);

		Logs.CloseChannel(channel);
		Logs.CloseSession(session);

		System.out.println("/home/baswarp/" + OutputFilename);
		System.out.println("good");
		Logs.GetFile("/home/baswarp/" + OutputFilename,System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+OutputFilename);
		System.out.println("wait");
		return OutputFilename;

	} 
	public static String XMLVerify_Modified2(String LogFilePath,String TargetNodes) throws Exception{
		File file = new File(LogFilePath);
		DocumentBuilderFactory dbFactory 
		= DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		String value="";
		dBuilder = dbFactory.newDocumentBuilder();
		try{
			Document doc = dBuilder.parse(file);   
			doc.getDocumentElement().normalize();
			XPath xPath =  XPathFactory.newInstance().newXPath();
			String ToCheck=TargetNodes;

			Node nList = (Node) xPath.compile(ToCheck).evaluate(doc, XPathConstants.NODE);
			value=nList.getTextContent();
		}
		catch(Exception e)
		{
			e.printStackTrace();    	
		}
		return value;

	}
	public String readFile(String FileName)
	{
		String line="";
		String Str_XmlLog="";
		try {
			File file = new File(System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+FileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null)
				Str_XmlLog += line + "\r\n";
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Str_XmlLog;
	}
	public void LrShell_XMLVerify(String LogFilePath,String TargetNodes,String TargetValues,boolean EmailbyTemplate_NameValue) throws Exception{
		/*****************************************************************************************************/	
		String Str_XmlLog="";
		String line = "";
		int Str_Index=0;
		int i=0;
		String []TargetNode=TargetNodes.split(",");
		String []TargetValue=TargetValues.split(",");
		/********************************** Reading XMLFile **************************************************/	
		File file = new File(System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+LogFilePath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while((line = reader.readLine()) != null)
			Str_XmlLog += line + "\r\n";
		reader.close();
		String Str_Temp=Str_XmlLog;
		/********************************** Getting the Index values of Target Nodes and Values ********************/	

		if(EmailbyTemplate_NameValue)
		{
			Str_Index=14;                    // 14 represents </name><value> exists between node and value
		}
		else
		{
			for(int j=0;j<TargetNode.length;j++)
			{
				TargetNode[j]="<ns2:name>"+TargetNode[j]+"</ns2:name>";
				TargetValue[j]="<ns2:value>"+TargetValue[j]+"</ns2:value>";
				//						TargetNode[j]="<"+TargetNode[j]+">";
				System.out.println("TargetNode[j]" + TargetNode[j]);
			}
		}
		for(int j=0;j<TargetNode.length;j++)
		{
			Str_Temp=Str_XmlLog;
			//					System.out.println("TEMP VALUE is" + Str_Temp);
			if(EmailbyTemplate_NameValue)
			{
				Str_Index=14;                    // 14 represents </name><value> exists between node and value
			}
			i=1;
			while(i==1)
			{
				i=1;
				if(Str_Temp.contains(TargetNode[j]))
				{
					if(Str_Temp.contains(TargetValue[j]))

					{
						System.out.println("TargetValue[j]" + TargetValue[j]);
						//								System.out.println(Str_Temp.indexOf(TargetNode[j])+TargetNode[j].length(),Str_Temp.indexOf(TargetValue[j],Str_Temp.indexOf(TargetNode[j])));
						//								System.out.println(Str_Temp.indexOf(TargetNode[j])+TargetNode[j].length(),Str_Temp.indexOf(TargetValue[j],Str_Temp.indexOf(TargetNode[j])));
						int index1 = Str_Temp.indexOf(TargetNode[j])+TargetNode[j].length();

						int index2 = Str_Temp.indexOf(TargetValue[j],Str_Temp.indexOf(TargetNode[j]))+TargetValue[j].length();

						System.out.println(index1);
						System.out.println(index2);
						String asd=Str_Temp.substring(Str_Temp.indexOf(TargetNode[j])+TargetNode[j].length()+1,Str_Temp.indexOf(TargetValue[j],Str_Temp.indexOf(TargetNode[j]))+TargetValue[j].length());
						System.out.println("TargetValue[j]" + TargetValue[j]);
						System.out.println("VALUE is" + asd);
						for(int k = 0; k < asd.length(); k++)
						{
							if(Character.isWhitespace(asd.charAt(k)))
							{
								Str_Index++;
								System.out.println("Str_Index" + Str_Index);
							}
						}
						//								int Index_Node=Str_Temp.indexOf(TargetNode[j])+TargetNode[j].length()+Str_Index;
						//								System.out.println("Index_Node"+Index_Node);
						//								int Index_Value=Str_Temp.indexOf(TargetValue[j],Str_Temp.indexOf(TargetNode[j]));
						//								System.out.println("Index_Value"+Index_Value);
						//								if(Index_Node==Index_Value)
						//								{
						Report.fnReportPass("XML Verification Passed : The "+TargetValue[j]+" is found as the Value for the Node "+TargetNode[j]);
						i=0;
						//								}
						//								else
						//								{
						//									System.out.println("inside else");
						//									Str_Temp=Str_Temp.substring(Str_Temp.indexOf(TargetValue[j],Str_Temp.indexOf(TargetNode[j]))+TargetValue[j].length(),Str_Temp.length()-1);
						//									System.out.println("Str_Temp" +Str_Temp);
						//								}
					}
					else
					{
						Report.fnReportFail("XML Verification Failed : The Target Value : "+TargetValue[j]+" is Not found");
						i=0;
					}
				}
				else
				{
					Report.fnReportFail("XML Verification Failed : The Target Node : "+TargetNode[j]+" is Not found");
					i=0;
				}
			}	
		}
	}



	//	public String LrShell_getLogfileOtherPattern(String TargetPattern,String LogFileName,String StartPattern_X,String EndPattern_Y,String Str_CLI,boolean XY,String command) throws Exception{
	//		/*****************************************************************************************************/	
	//		String StartPattern=StartPattern_X;
	//		String EndPattern=EndPattern_Y;
	//		String Command=command;		
	//		switch(command)
	//		{
	//		case "LLUCEASE":
	//			System.out.println("LLUCEASE");
	//			StartPattern= "<ns0:CeaseLLURequest2 xmlns:jms1="+StartPattern_X+" xmlns:ns0="+EndPattern_Y+">";
	//			EndPattern= "</ns0:CeaseLLURequest2>";
	//			break;
	//		}
	//
	//		String LogFile = "/opt/tibco/tra/domain/TTG" + LoadEnvironment.ENV + "/application/logs/"+LogFileName;
	//		String OutputFilename = "LogXML_"+Str_CLI+".xml";
	//		System.out.println(OutputFilename);
	//
	//		System.out.println("Log--------------"+LogFile);
	//		System.out.println("Start Pattern------------------"+StartPattern);
	//
	//		LogXmlParser Logs = new LogXmlParser();
	//
	//		Session session = Logs.CreateJschSession("baswarp", "year@2013", LoadEnvironment.BW_SERVERIP);
	//		Channel channel = Logs.CreateChannelforExecution(session, "shell");
	//		PrintStream commander = Logs.CreateCommander(channel);
	//		Logs.CommandSender(commander, "cd /home/baswarp");
	//		Logs.CommandSender(commander, "./Sample_xml_parser_param.csh "+LogFile+" '"+StartPattern+"' '"+EndPattern+"' '"+TargetPattern+"' '"+OutputFilename+"'");
	//		Logs.CommandSender(commander, "cp output.txt "+OutputFilename);
	//		Logs.CommandSender(commander, "sed 's/.*\\(<ns0:CeaseLLURequest2>*\\)/\\1/' output.txt > "+OutputFilename);
	//		Logs.CommandSender(commander, "exit");
	//		Logs.CloseCommandSender(commander);
	//		Logs.DisplayShell(channel);
	//
	//		Logs.CloseChannel(channel);
	//		Logs.CloseSession(session);
	//
	//		System.out.println("/home/baswarp/" + OutputFilename);
	//		System.out.println("bad");
	//		Logs.GetFile("/home/baswarp/" + OutputFilename,System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+OutputFilename);
	//		System.out.println("wait");
	//		return OutputFilename;
	//
	//		}

	public void LrShell_XMLVerify_Modified(String LogFilePath,String TargetNodes,String TargetValues) throws Exception{
		File file = new File(System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+LogFilePath);
		DocumentBuilderFactory dbFactory 
		= DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		XPath xPath =  XPathFactory.newInstance().newXPath();
		//     String ToCheck="//*[local-name()='LineDetail']";
		String ToCheck="//*[local-name()='"+TargetNodes+"']";
		Element element = (Element) xPath.compile(ToCheck).evaluate(doc, XPathConstants.NODE);
		if(element.getTextContent().equals(TargetValues))
		{
			Report.fnReportPass("The Node "+TargetNodes+" is having the value "+TargetValues);
		}
	}
	public String getXMLTextContent(String LogFilePath,String TargetNodes) throws Exception{
		File file = new File(LogFilePath);
		DocumentBuilderFactory dbFactory 
		= DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		String value="";
		dBuilder = dbFactory.newDocumentBuilder();
		try{
			Document doc = dBuilder.parse(file); 
			doc.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			String ToCheck=TargetNodes;

			Node nList = (Node) xPath.compile(ToCheck).evaluate(doc, XPathConstants.NODE);
			value=nList.getTextContent();
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		return value;

	}
	public File LrShell_FileWriter(String FileName,String Content,String Extension)
	{
		FileWriter writer;
		try {
			writer = new FileWriter(System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+FileName+"."+Extension);
			writer.write(Content);writer.flush();writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new File(System.getProperty("user.dir")+"\\ProvisioningUpdates\\"+FileName+"."+Extension);

	}
	public void LrShell_XMLVerify_CSA(String LogFile,String PackageName) throws Exception{
		try {
			File f= LrShell_FileWriter("ShellCheck",LogFile,"txt");
			DocumentBuilderFactory dbFactory 
			= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(f);
			doc.getDocumentElement().normalize();
			XPath xPath =  XPathFactory.newInstance().newXPath();
			String ToCheck="//*[local-name()='AvailablePackages']//*[local-name()='salesPackageId' and text()='"+PackageName+"']/..//*[local-name()='contractTerm'  and text()='12']";
			String ToCheck1="//*[local-name()='AvailablePackages']//*[local-name()='salesPackageId' and text()='"+PackageName+"']/..//*[local-name()='contractTerm'  and text()='18']";
			try {
				xPath.compile(ToCheck).evaluate(doc, XPathConstants.NODE);
				System.out.println("12 Month Contract is available for Package "+PackageName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				xPath.compile(ToCheck1).evaluate(doc, XPathConstants.NODE);
				System.out.println("18 Month Contract is available for Package "+PackageName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}