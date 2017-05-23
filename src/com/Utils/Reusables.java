package com.Utils;

/* Script Name: I_RedboxInstant.Reusables
 * Script Author: Srinivas Sanduri
 * Date Created: 10-15-2012
 * Modified By:
 * Date Modified: 10-15-2012
 * Nature of Modification: NA
 * Areas covered in the script 
 * 		1. create New Invitation
 * 		2. get Unique Credit Card
 * 		3. ReadMyExcel
 * 		4. writeMyExcel
 * 		5. Reporter
 * 		6. ResultToExcel
 */


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("unused")
public class Reusables {

	public static String getdateFormatmonth(String hint,int months,String date) throws Exception {
		String nextday = null;

		try{
			String dt = date; 
			SimpleDateFormat ft =  new SimpleDateFormat (hint);
			Calendar c = Calendar.getInstance();
			c.setTime(ft.parse(dt));
			c.add(Calendar.MONTH, months);// number of months to add
			nextday = ft.format(c.getTime());
		}catch (Exception localException)
		{
			System.out.println(localException.getMessage());
			return nextday;
		}
		return nextday;
	}
	public static String getdateFormatdays(String hint,int days,String date) throws Exception {
		String nextday = null;

		try{
			String dt = date; 
			SimpleDateFormat ft =  new SimpleDateFormat (hint);
			Calendar c = Calendar.getInstance();
			c.setTime(ft.parse(dt));
			c.add(Calendar.DATE, days);// number of months to add
			nextday = ft.format(c.getTime());
		}catch (Exception localException)
		{
			System.out.println(localException.getMessage());
			return nextday;
		}
		return nextday;
	}
	public static String getdateFormat(String hint,int days){
		String nextday = null;

		try{
			Date dNow = new Date(); 
			SimpleDateFormat ft =  new SimpleDateFormat (hint);
			String today = ft.format(dNow);
			Date dtoday = ft.parse(today);
			Calendar c = Calendar.getInstance();
			c.setTime(dNow);
			c.add(Calendar.DATE, days);  // number of days to add
			nextday = ft.format(c.getTime());
		}catch (Exception localException)
		{
			System.out.println(localException.getMessage());
			return nextday;
		}
		return nextday;
	}
	
	public static String getdateFormat(String date,String in_format,String out_format) throws Exception {
		String out_date = null;

		try{
			SimpleDateFormat ft =  new SimpleDateFormat (in_format);
			SimpleDateFormat ft_out =  new SimpleDateFormat (out_format);
			Date dNow = ft.parse(date);
			out_date = ft_out.format(dNow);
		}catch (Exception localException)
		{
			System.out.println(localException.getMessage());
			return out_date;
		}
		return out_date;
	}
	
	public static String getDaysFrom(String date,String format,int days) throws Exception{
		String nextday=null;
		try{
 
			SimpleDateFormat ft =  new SimpleDateFormat (format);
			Date dNow = ft.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dNow);
			c.add(Calendar.DATE, days);  // number of days to add
			nextday = ft.format(c.getTime());
		}catch (Exception localException)
		{
			System.out.println(localException.getMessage());
			return nextday;
		}
		return nextday;
	}

	@SuppressWarnings("finally")
	public static String getXMLdata(String XMLContent, String NodeName, String TAG) throws Exception{
		String ReturnXMLvalue = null;
		
		try{
		java.io.FileWriter fw = new java.io.FileWriter(System.getProperty("user.dir")+"\\ProvisioningUpdates\\MYXML.xml");
		fw.write(XMLContent);
		fw.close();

		File fXmlFile = new File(System.getProperty("user.dir")+"\\ProvisioningUpdates\\MYXML.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();


		NodeList nList = doc.getElementsByTagName(NodeName);
		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				ReturnXMLvalue = eElement.getElementsByTagName(TAG).item(0).getTextContent();
				break;
			}
		}
		}catch(Exception e){
			
		}finally{
			return ReturnXMLvalue;
		}
		
	}
	public static int GetNumberOfDaysInAMonth(String Str_RequestedDate){
		try{
			String date1 = Str_RequestedDate;
		    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		    Date convertedDate = new Date();
		    try
		    {
		        convertedDate = df.parse(date1);
		    }
		    catch(Exception e)
		    {
		        System.out.print(e);
		    }
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(convertedDate);
		    int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH);
		    int day = cal.get(Calendar.DAY_OF_MONTH);
		    int numDays, startMonth;
		    numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		    return numDays;
		}catch(Exception e){
			
		}
		return 0;
	}

}

