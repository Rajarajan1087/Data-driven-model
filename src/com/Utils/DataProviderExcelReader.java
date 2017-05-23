package com.Utils;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.Engine.Reporter;
import com.Engine.SeleniumSetup;
import com.relevantcodes.extentreports.LogStatus;

public class DataProviderExcelReader {

	static String[][] tabArray = null;
	Workbook workbk;
	Sheet sheet;
	static int rowCount;
	//	String sheetPath = LoadEnvironment.workingDir+LoadEnvironment.INPUTSHEET;
	static int colCount;
	public static Reporter report1=new Reporter();
	

	public static  String[][] getExcelData(String WorkBookPath,String shtName,String ScriptName) throws Exception
	{
		try {
			Workbook workbk = Workbook.getWorkbook(new File(WorkBookPath));
			Sheet sht = workbk.getSheet(shtName);
			rowCount = sht.getRows();
			colCount = sht.getColumns();
			int counter=0;
			int counter2=0;
			ReadExcelSheet RX = new ReadExcelSheet(null);
			Sheet sheet = RX.Excel(WorkBookPath, shtName);
			int row = sheet.getRows();
			int column = 0;
			String TestDesc="QWERTYTest Description:   \n";
			int Executecolumn = sheet.findCell("TO_BE_EXECUTED", 0, 0, sheet.getColumns(),sheet.getRows(), false).getColumn();
			column = sheet.findCell("SCRIPT_ID", 0, 0, sheet.getColumns(),sheet.getRows(), false).getColumn();
			int testDescColumn = sheet.findCell("TESTCASE_NAME", 0, 0, sheet.getColumns(),sheet.getRows(), false).getColumn();

			for(int i =0 ; i<row;i++){
				if(sht.getCell(column, i).getContents().equalsIgnoreCase(ScriptName)){
					counter+=1;
				}
				
				if(sht.getCell(column, i).getContents().contains(ScriptName)){
				
				String temp=sht.getCell(testDescColumn, i).getContents();
				if(!temp.isEmpty())
					{
					TestDesc+="\n"+i+")"+temp;
					}
				}
			}
			
			System.out.println(TestDesc);
		
			
			
			tabArray = new String[counter][2];
			for(int i =0 ; i<row;i++){
				if((sht.getCell(column, i).getContents().equalsIgnoreCase(ScriptName))){
					if((sht.getCell(Executecolumn, i).getContents().equalsIgnoreCase("YES"))){
						tabArray[counter2][0]=ScriptName+TestDesc;
						tabArray[counter2][1]=Integer.toString(i);
						System.out.println("ROW--------- "+Integer.toString(i));
						counter2++;
					}
					else{
						tabArray[counter2][1]=Integer.toString(i);
						System.out.println("ROW--------- "+Integer.toString(i));
						counter2++;
					}
				}
				
			}
		} catch (Exception e) {
			System.out.println("Problem with Dataprovider");
			e.printStackTrace();
		}
		if(tabArray==null)
		{
			return new String[0][0];
		}
		return (tabArray);
	}
}

