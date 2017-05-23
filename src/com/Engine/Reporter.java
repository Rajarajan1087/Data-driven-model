package com.Engine;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import com.Utils.Reusables;
import com.galenframework.reports.GalenTestInfo;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {

	public String filepath;
	public String OutputFilename;
	public boolean result = false;
	public String strOutputFilePath;
	public String strOutputScreenShotsFilePath;
	public String filename;
	public int rownumber;
	public int Offset=0;
	public List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
	public boolean testPassed = true;
	public String testtype;
	public String testname;
	public String methodname;
	public String[][] TestingResults;
	public static String absolutepath = System.getProperty("user.dir");
	public String CurrentRowOfExecution="";
	public String StartTime="";
	public String EndTime="";
	public String r="";
	public String TerminateReason="";
	public ExtentReports Extent;

	public ExtentTest test;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	Date d = cal.getTime();
	public String ExtentReportPath;
	public String Tempdata;

	public void fnReportPass(String strPassingItem,WebDriver...driver){
		ReporterLog(strPassingItem, LogStatus.PASS,driver);
		System.out.println(strPassingItem);
	} 

	// Function to report Fail
	public void fnReportWarning(String strPassingItem,WebDriver...driver) throws Exception {
		ReporterLog(strPassingItem, LogStatus.WARNING,driver);
	}
	public void fnReportFail(String strPassingItem,WebDriver...driver){
		result = false;
		testPassed = false;
		ReporterLog(Reusables.getdateFormat("dd/MM/yyyy hh:mm:ss :", 0) + strPassingItem, LogStatus.FAIL,driver);
		System.out.println(strPassingItem);
	}

	// Function to report Fail and Terminate Test

	public void fnReportFailAndTerminateTest(String strFailingItem, String strReportMessage,WebDriver...driver){
		result = false;
		testPassed = false;
		ReporterLog(Reusables.getdateFormat("dd/MM/yyyy hh:mm:ss :", 0) + strReportMessage, LogStatus.FAIL,driver);
		TerminateReason = strReportMessage;
		exception(methodname + "_" + rownumber, strFailingItem);
	}
	public void fnReportPageBreak(String Str_PageName,WebDriver driver){
		ReporterLog(Reusables.getdateFormat("dd/MM/yyyy hh:mm:ss :", 0) + "------ "+Str_PageName+" ------ ", LogStatus.INFO,driver);
	}
	public void exception(String testname1, String objname){
		//Stopping Seeker Proxy
		/*Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Synopsys\\Seeker\\SeekerStudio.exe\" "
				+ "/Server "+LoadEnvironment.Seeker_Server+" "
				+ "/User "+LoadEnvironment.Seeker_Username+" "
				+ "/Password "+LoadEnvironment.Seeker_Password+" "
				+ "/Command StopExternalCapture ");
		Runtime.getRuntime().exec("cls");*/
		EndTime = sdf1.format(d);
		System.out.println("-------[ Test hits a user defined exception ]-------");
		testPassed=false;
		Assert.fail(testname1 + " script failed, " + objname + " - " + testname1+ ".jpg for more details.");
	}



	// Takes a new screenshot

	public void takescreenshot(WebDriver driver, String Str_ReportMessage,LogStatus LS){



		//                            File folder = new File(strOutputScreenShotsFilePath+"/"+CurrentRowOfExecution);

		//                            if (!folder.exists()) {

		//                                            folder = new File(strOutputScreenShotsFilePath+"/"+CurrentRowOfExecution);

		//                                            folder.mkdirs();

		//                            }

		//                            String sPath = strOutputScreenShotsFilePath+"/"+CurrentRowOfExecution +"/"+ screenshotname + ".jpg";



		//System.out.println("OUT PATH "+ strOutputScreenShotsFilePath);


		String photographWithPath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		test.log(LS, Str_ReportMessage+test.addBase64ScreenShot("data:image/jpg;base64,"+photographWithPath));


	}
	public void capturescreenshot(String screenshotname) throws Exception {

		try {

			File folder = new File(strOutputScreenShotsFilePath);

			if (!folder.exists()) {

				folder = new File(strOutputScreenShotsFilePath);

				folder.mkdirs();

			}

			String sPath = strOutputScreenShotsFilePath+"/"+"Iteration_row_"+CurrentRowOfExecution +"__"+ screenshotname + ".jpg";



			System.out.println(sPath);

			File f = new File(sPath);

			if (f.exists()) {

				f.delete();

				System.out.println("old screenshot is deleted");

			}

			Robot robot = new Robot();

			BufferedImage bi = robot.createScreenCapture(new Rectangle(1280,

					1024));

			ImageIO.write(bi, "JPEG", new File(sPath));

			System.out.println("screenshot is created at " + sPath);

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("Screenshot could not be captured");



		}

	}
	// reports each step as pass pass or fail to console and to an array
	public void ReporterLog(String Reprot_Text, LogStatus LS,WebDriver... driver){
		if(LS.name().equalsIgnoreCase("Fail"))
		{
			testPassed=false;
		}
		Charset UTF8_CHARSET = Charset.forName("UTF-8");
		Charset USASCII_CHARSET = Charset.forName("ISO-8859-1");
		if(driver.length>0)
		{
			takescreenshot(driver[0], Reprot_Text, LS);
		}
		else{
			test.log(LS, Reprot_Text);
		}
		String Data="[ Reported ] "+ new String((LS.name() + "\t"+Reprot_Text).getBytes(UTF8_CHARSET), USASCII_CHARSET);
		System.out.println(Data);
		org.testng.Reporter.log(Data);

	}

	public void reportTest() throws Exception {

		EndTime = sdf1.format(d);
		//Stopping Seeker Proxy
		/*System.out.println("\"C:\\Program Files (x86)\\Synopsys\\Seeker\\SeekerStudio.exe\" "
				+ "/Server "+LoadEnvironment.Seeker_Server+" "
				+ "/User "+LoadEnvironment.Seeker_Username+" "
				+ "/Password "+LoadEnvironment.Seeker_Password+" "
				+ "/Command StopExternalCapture ");
		Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Synopsys\\Seeker\\SeekerStudio.exe\" "
				+ "/Server "+LoadEnvironment.Seeker_Server+" "
				+ "/User "+LoadEnvironment.Seeker_Username+" "
				+ "/Password "+LoadEnvironment.Seeker_Password+" "
				+ "/Command StopExternalCapture ");
		Runtime.getRuntime().exec("cls");
		ReporterLog("Capture Completed for : "+methodname, LogStatus.PASS);*/

	}

	public void reportTestSkip() throws SkipException {
			throw new SkipException("Skipping the test case");        
	}
}

