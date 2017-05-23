package com.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.Engine.Reporter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class ReadExcelSheet{
	public Reporter Report;
	public ReadExcelSheet(Reporter report) {
		Report=report;
	}
	public ReadExcelSheet() {
		
	}
	//String sheetPath = LoadEnvironment.workingDir+LoadEnvironment.INPUTSHEET;

	public Sheet Excel(String filename, String sheetname) throws Exception {
		Sheet sheet = null;
		try {
			//System.out.println("input file name  "+filename);System.out.println("InputSheet name  "+sheetname);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setLocale(new Locale("en", "EN"));

			settings.setNamesDisabled(true);
			settings.setFormulaAdjust(true);
			settings.setMergedCellChecking(true);
			settings.setCellValidationDisabled(true);
			settings.setSuppressWarnings(true);

			Workbook workbook = Workbook.getWorkbook(new File(filename),
					settings);
			sheet = workbook.getSheet(sheetname);
		} catch (Exception e) {
			Report.fnReportFailAndTerminateTest("Failed to read from Excel file", "ReadExcel");
			e.printStackTrace();
		}

		if (sheet == null) {
			System.out.println("NULL SHEET");
		}
		return sheet;
	}
	public String ReadFromExcel(String WorkbookLocation,String SheetName, String TestScript,
			String PARAMETER) throws Exception {

		Sheet sheet = Excel(WorkbookLocation, SheetName);

		int row = 0;
		int column = 0;
		row = sheet.findCell(TestScript, 0, 0, sheet.getColumns(),
				sheet.getRows(), false).getRow();
		column = sheet.findCell(PARAMETER, 0, 0, sheet.getColumns(),
				sheet.getRows(), false).getColumn();
		String iterations = sheet.getCell(column, row).getContents();
		System.out.println("the Column is ->"+PARAMETER+" the parameter value is ->"+iterations);
		return iterations;

	}
	/**
	 * This function will retrieve data from excel sheet
	 * @param WorkbookLocation
	 * @param SheetName
	 * @param Row
	 * @param PARAMETERS multiple parameteres can be sent
	 * @return ReturnValue it contains data from sheet. Multiple data separated by '|' character
	 * DateModified 10 Aug 2016
	 * @throws Exception
	 */
	public String ReadFromExcelWithRows(String WorkbookLocation,String SheetName, String Row,String...PARAMETERS) throws Exception {
		try{
			String ReturnValue="";
			Sheet sheet = Excel(WorkbookLocation, SheetName);
			int column = 0;
			int row = Integer.parseInt(Row);
			int i=0,j=0;
			for(String PARAMETER:PARAMETERS)
			{
				i++;
				try {
					column = sheet.findCell(PARAMETER, 0, 0, sheet.getColumns(),
							sheet.getRows(), false).getColumn();
					j++;
				} catch (NullPointerException e) {
					throw new NullPointerException(PARAMETER+" is not found in the sheet "+SheetName);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				String Actual=ReturnValue;
				String Data=sheet.getCell(column,row).getContents();
				ReturnValue = ReturnValue+"|"+Data;
			}
			return ReturnValue.replaceAll("^\\|", "");
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Map<String,String> CreateMapFromExcel(String WorkbookLocation,String SheetName, String Row) throws Exception {

		Sheet sheet = Excel(WorkbookLocation, SheetName);
		int column = sheet.getColumns();
		Map<String, String> map = new HashMap<String, String>();
		int row = Integer.parseInt(Row);
		for(int i = 0 ;i < column; i++){
			String KEY 	 = sheet.getCell(i,0).getContents();
			String VALUE = sheet.getCell(i, row).getContents();
			map.put(KEY, VALUE);
					}
		return map;

		
	}
	
	
//	Reading the related definition from excel using script Id matcher and passing the value in report 
	
//	public String 
	
	
	
	
}
