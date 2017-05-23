package com.Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;



import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Utils.ReadExcelSheet;
import com.Utils.Reusables;
import com.galenframework.api.Galen;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.testng.GalenTestNgTestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;



@SuppressWarnings("deprecation")

public class SeleniumSetup extends LoadEnvironment {

	// Common_Functions variables

	public static enum ProcessType{
		Kill,Run,ClearApp,INSTALL,UNINSTALL
	}

	public static String WhereToRun = "local";// grid ; local

	public static String ENV;

	public static String RunType;

	public static String IEversion = "8";

	public static String platform;

	public WebDriver driver;

	public Reporter Report;

	public String strOutputFilePath;

	public String filename;

	public static final int empty_int = 999;

	public enum Browser {

		FIREFOX, GOOGLECHROME, IE, SAFARI, PHANTOM, FIREFOX_PROXY

	}


	/*---------------------------Main Components ------------------------------------------------------------*/



	// This method is called in every test class, where we can initialize the

	// necessary data.



	@BeforeSuite(alwaysRun = true)

	public void setupBeforeSuite(ITestContext context) throws Exception {

		System.out.println("Loading Env variables");

		ENV = context.getCurrentXmlTest().getParameter("test.env").toUpperCase();
		LoadEnvironment.ENV=ENV.toUpperCase();
		WhereToRun = context.getCurrentXmlTest().getParameter("whereToRun").toLowerCase();
		if(WhereToRun.equals("${where.torun}"))
		{
			WhereToRun="local";
		}
		RunType = context.getCurrentXmlTest().getParameter("RunType").toLowerCase();

		System.out.println("DownStreamENV:'"+ENV+"'");
		System.out.println("Mode of Execution:'"+WhereToRun+"'");

		LoadGenericSystemProperties();
		LoadDownStreamENV("env_"+ENV);
		LoadDownStreamPEGAENV();
		Assert.assertTrue(!ENV.isEmpty());
	}
	public void ExecuteGalen(String browser,String Url)

			throws Exception {
		GalenTestNgTestBase GTNG = null;
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/LocalJARs/chromedriver.exe");
		switch(browser.toUpperCase())
		{
		case "FIREFOX":
			GTNG=new GalenTestNgTestBase() {
				@Override
				public WebDriver createDriver(Object[] arg0) {
					WebDriver d=new FirefoxDriver();
					this.driver.set(d);
					return d;
				}
			};
			break;
		case "CHROME":
			GTNG=new GalenTestNgTestBase() {
				@Override
				public WebDriver createDriver(Object[] arg0) {
					WebDriver d=new ChromeDriver();
					this.driver.set(d);
					return d;
				}
			};
			break;
		}
		Galen GL=new Galen();
		driver=GTNG.createDriver(null);
		GTNG.load(Url);
		//		GTNG.load(Url, width, height);
	}
	public static String ExecuteCommand(String ProcessName,ProcessType Pro,String... Commands) throws Exception{
		String line=new String();
		if(Commands.length==0)
		{
			Commands=new String[1];
			switch (Pro.name().toUpperCase())
			{
			case "KILL":
				Commands[0]="taskkill /f /im "+ProcessName+".exe";
				break;
			case "RUN":
				Commands[0]="start "+ProcessName;
				break;
			case "CLEARAPP":
				Commands[0]="adb shell pm clear "+ProcessName;
				break;
			case "INSTALL":
				Commands[0]="adb install -r "+ProcessName;
				break;
			case "UNINSTALL":
				Commands[0]="adb uninstall "+ProcessName;
			}
		}
		for(String Command:Commands)
		{
			try
			{
				System.out.println(Command);
				Process process=Runtime.getRuntime().exec(Command);
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				line=br.readLine();
				String Temp;
				int i=0;
				while (i==0) {
					Temp=br.readLine();
					if(Temp!=null)
					{
						line=line+","+Temp;
					}
					else
					{
						i=1;
					}
				}
				//	    		line=line.substring(0,line.length()-1);
				System.out.println(Pro.name()+" : "+ProcessName);
				//	    		System.out.println(line);
			}
			catch(Exception N)
			{
				N.printStackTrace();
			}
		}
		return line;
	}

	@BeforeMethod

	// setup system properties
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",Reporter.absolutepath+"/LocalJARs/chromedriver.exe");
		System.setProperty("webdriver.ie.driver",Reporter.absolutepath+ "/LocalJARs/IEDriverServer.exe");
		System.setProperty("phantomjs.binary.path",Reporter.absolutepath+ "/LocalJARs/phantomjs.exe");

	}



	/**
	 * Get the method name for a depth in call stack.
	 * Utility function
	 * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
	 * @param Class_Method true for returning class name, false for returning method name
	 * @return method name
	 */
	public static String getCalledName(int depth,boolean Class_Method)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		if(Class_Method)
		{
			return ste[depth+1].getClassName();
		}
		else{
			return ste[depth+1].getMethodName();
		}
	}
	/**
	 * This function will start Extent Report html file with the name of current running class from test suite followed by random numbers (current date time)
	 * @throws Exception
	 */
	@BeforeMethod()
	public void InitializeExtentReports() throws Exception{
		Report = new Reporter();
		String ExtentReportPath=System.getProperty("user.dir") + "/Report/"+ this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1);
		DateFormat df = new SimpleDateFormat("yyyyMMMdd_HHmmssSSS");
		Date dateobj = new Date();
		String D=df.format(dateobj);
//		String ExtentReportPath=System.getProperty("user.dir") + "/Report/Run";
		Report.Extent = new ExtentReports(ExtentReportPath+"_"+D+".html", false);
		String Name=this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1);
		Report.test = Report.Extent.startTest(Name,Name);
	}

	
	
	/*
	 * This function gets the date value from DB and return the value as per PEGA application requires to verify front end validation
	 */
	public String dbDateFormatChanger(String date) throws ParseException
	{
		  DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		  DateFormat ddf = new SimpleDateFormat("dd-MMM-yyyy");
		  Date val = (Date)sdf.parse("10/13/2009 0:0:0");       
		 return ddf.format(val);
	}
	
	
	public static String sendGet(String Url) throws Exception {

		URL obj = new URL(Url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		System.setProperty("http.keepAlive", "false");
		con.setRequestProperty("Connection", "close");
		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + Url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//            		System.out.println(response.toString());
		return response.toString();
	}

	public String TestPreProcessing(String SCRIPTID,String ROW, String InputSheet, String SheetName,String...Str_RequiredData) throws Exception{
		if(SCRIPTID==null)
		{
			throw new SkipException("ROW number "+ROW+" is skipped");
		}
		ReadExcelSheet RX = new ReadExcelSheet(Report);
		Report.methodname=SCRIPTID;
		Report.StartTime=Reusables.getdateFormat("yyyy-MM-dd HH:mm:ss", 0);
		System.out.println("+++++++++++++++++START TIME=================="+Report.StartTime);
		Report.CurrentRowOfExecution=ROW;
		Report.ReporterLog(Report.methodname+ " from Row number "+ROW+" -> Test Started",LogStatus.PASS);
		System.out.println("Launching Driver");
		String AddModeData="";
		try{
			String Mode=RX.ReadFromExcelWithRows(InputSheet,SheetName, ROW, "MODE").toUpperCase();
			driver=WhereToExecute(RX.ReadFromExcelWithRows(InputSheet,SheetName, ROW, "BROWSER").toString().toUpperCase(), "http://localhost:4444/wd/hub",set_ModeResolution(Mode)); //"http://localhost:4444/wd/hub"
			AddModeData="|"+Mode.toUpperCase();
			Report.ReporterLog("Test Case Running on "+Mode+" Mode:", LogStatus.INFO);
		}
		catch(Exception e){
			Report.ReporterLog("Test Case Runing on Desktop Mode:", LogStatus.INFO);
			driver=WhereToExecute(RX.ReadFromExcelWithRows(InputSheet,SheetName, ROW, "BROWSER").toString().toUpperCase(), "http://localhost:4444/wd/hub"); //"http://localhost:4444/wd/hub"
		}
		System.out.println("Driver Launched");
		return RX.ReadFromExcelWithRows(InputSheet, SheetName, ROW, Str_RequiredData)+AddModeData;

	}


	/**+
	 * 
	 * @param Mode
	 * @return
	 */

	public Map<String,String> TestPreProcessing(String SCRIPTID,String ROW, String InputSheet, String SheetName) throws Exception{
		if(SCRIPTID==null)
		{
			throw new SkipException("ROW number "+ROW+" is skipped");
		}
		ReadExcelSheet RX = new ReadExcelSheet();
		Map<String,String> DATA_MAP = RX.CreateMapFromExcel(InputSheet,SheetName, ROW);
		
		
		String Desc=SCRIPTID.split("QWERTY")[1];
		System.out.println(Desc);
		SCRIPTID=SCRIPTID.split("QWERTY")[0];
		System.out.println(SCRIPTID);
		
		
		Report.methodname=SCRIPTID;
		Report.StartTime=Reusables.getdateFormat("yyyy-MM-dd HH:mm:ss", 0);
		System.out.println("+++++++++++++++++START TIME=================="+Report.StartTime);
		Report.CurrentRowOfExecution=ROW;
		Report.ReporterLog(Report.methodname+ " from Row number "+ROW+" -> Test Started",LogStatus.PASS);
		System.out.println("Launching Driver");
		System.out.println("Going To Print Description");
		Report.ReporterLog(Desc, LogStatus.INFO);
		try{
			String Mode=DATA_MAP.get("MODE").toUpperCase();
			driver=WhereToExecute(DATA_MAP.get("BROWSER").toString().toUpperCase(), "http://localhost:4444/wd/hub",set_ModeResolution(Mode)); //"http://localhost:4444/wd/hub"
			Report.ReporterLog("Test Case Running on "+Mode+" Mode:", LogStatus.INFO);
			

		}
		catch(Exception e){
			//Report.Offset=768/2;
			Report.ReporterLog("Test Case Running on Desktop Mode:", LogStatus.INFO);
			driver=WhereToExecute(DATA_MAP.get("BROWSER").toString().toUpperCase(), "http://localhost:4444/wd/hub"); //"http://localhost:4444/wd/hub"
		}
		System.out.println("Driver Launched");
		return DATA_MAP;


	}

	private Integer[] set_ModeResolution(String Mode)
	{
		Integer Width=0,Height=0;
		switch(Mode.toUpperCase())
		{
		case "TABLET_IPAD_L":
			Width=768;Height=1024;
			break;
		case "TABLET_IPAD_P":case "TABLET":
			Width=1024;Height=768;
			break;
		case "MOBILE_IPHONE6_L":
			Width=667;Height=375;
			break;
		case "MOBILE_IPHONE6_P":case "MOBILE":
			Width=375;Height=667;
			break;
		default:
			Width=1024;Height=768;
			break;
		}
		if(Width!=1024)
		{
			Report.Offset=Height/2;
		}
		Integer[] toReturn={Width,Height};
		return toReturn;
	}
	// -- This method is invoked at the end of each test case.

	public WebDriver WhereToExecute(String browser, String Url,Integer...Resolution)	throws Exception {
		WebDriver daemon = null;
		System.out.println("===================="+browser+"============================");
		System.out.println("Creating driver in " + WhereToRun + " mode with "+browser);
		String comparetext = browser;
		Browser value = Browser.valueOf(comparetext.toUpperCase());
		if (IEversion == "9") {
			platform = "Windows 2008";
		} else {
			platform = "Windows 2012";
		}
		if (WhereToRun.equals("local")) {
			switch (value) {
			case FIREFOX:
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("QAAutomation");
				daemon = new FirefoxDriver(myprofile);
				//					daemon = new FirefoxDriver();
				break;
			case FIREFOX_PROXY:
				Proxy proxy = new Proxy();   
				proxy.setHttpProxy("GB007778:10080");
				proxy.setSslProxy("GB007778:10078");
				DesiredCapabilities Firefox = DesiredCapabilities.firefox();
				Firefox.setCapability("proxy", proxy);
				daemon = new FirefoxDriver(Firefox);
				break;
			case GOOGLECHROME:
				daemon = new ChromeDriver();
				break;
			case IE:
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				ieCapabilities.setCapability("platform", "WINDOWS");
				ieCapabilities.setCapability("cssSelectorsEnabled", true);
				ieCapabilities.setCapability("unexpectedAlertBehaviour","accept");
				ieCapabilities.setCapability("browserName", "internet explorer");
				daemon = new InternetExplorerDriver(ieCapabilities);
				break;
			case SAFARI:
				daemon = new SafariDriver();
				DesiredCapabilities safaricaps = DesiredCapabilities.safari();
				safaricaps.setCapability("platform", "OS X 10.8");
				safaricaps.setCapability("version", "6");
				daemon = new SafariDriver(safaricaps);
				break;
			case PHANTOM:
				DesiredCapabilities         dCaps = new DesiredCapabilities();
				dCaps.setCapability("takesScreenshot", true);
				dCaps.setJavascriptEnabled(true);
				dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,Reporter.absolutepath+ "/LocalJARs/phantomjs.exe");
				daemon = new PhantomJSDriver(dCaps);
				break;
			default:
				break;
			}
			if(Resolution.length<2)
			{
				daemon.manage().window().maximize();
				Dimension windowDimension = daemon.manage().window().getSize();
				if (windowDimension.width < 1100) {
					daemon.manage().window().setSize(new Dimension(1296, 1000));
				}
			}else{
				if(Resolution[0]==9999)
				{
					daemon.manage().window().maximize();
					Dimension windowDimension = daemon.manage().window().getSize();
					if (windowDimension.width < 1100) {
						daemon.manage().window().setSize(new Dimension(1296, 1000));
					}
				}else{
					daemon.manage().window().setSize(new Dimension(Resolution[0], Resolution[1]));
				}
			}
		}
		if (WhereToRun.equals("grid")) {
			// Grid Parallel
			System.out.println("Configuring GRID Options");
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(			InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,					true);
			capability.setCapability("platform", "WINDOWS");
			capability.setCapability("cssSelectorsEnabled", true);
			switch (value) {
			case FIREFOX:
				ProfilesIni allProfiles = new ProfilesIni();
				FirefoxProfile profile = allProfiles.getProfile("QAAutomation");
				File ffox = new File("D:\\Mozilla Firefox\\firefox.exe");                                              
				FirefoxBinary dss=new FirefoxBinary(ffox);
				daemon = new FirefoxDriver(dss, profile);
				break; 
			case GOOGLECHROME:
				capability = DesiredCapabilities.chrome();
				capability.setCapability("browserName", "chrome");
				System.out.println("Creating remote driver instance");
				daemon = new RemoteWebDriver(new URL("http://10.10.2.47:4444/wd/hub"), capability);
				daemon.manage().window().maximize();
				break;
			case IE: 
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,						true);
				ieCapabilities.setCapability("cssSelectorsEnabled", true);
				ieCapabilities.setCapability("unexpectedAlertBehaviour","accept");
				ieCapabilities.setCapability("browserName", "internet explorer");
				daemon = new RemoteWebDriver(new URL("http://10.244.43.149:4444/wd/hub"), ieCapabilities);
				daemon.manage().window().maximize();
				break;
			case PHANTOM:
				break;
			case SAFARI:
				break;
			default:
				break;
			}
			daemon.manage().window().maximize();
			Dimension windowDimension = daemon.manage().window().getSize();
			if (windowDimension.width < 1100) {
				daemon.manage().window().setSize(new Dimension(1296, 1000));
			}
		}
		return daemon;
	}
	@AfterTest
	public void tearDown() {
		try {
			Report.Extent.flush();
		} catch (Exception e) {
		}
		finally{
			try {
				new HtmlReportBuilder().build(Report.tests, "target/galen-html-reports");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@AfterMethod
	public void clearRuntimeData(ITestResult result) throws Exception
	{
		if (result.getStatus()==ITestResult.SUCCESS) {
			Report.ReporterLog("Test Passed",LogStatus.PASS);
		}
		else if (result.getStatus()==ITestResult.FAILURE) {
			Report.ReporterLog("Test Failed",LogStatus.FAIL);
		}
		Report.Extent.endTest(Report.test);
		try {

			driver.close();
		} catch (Exception e) {
		}
//		ExecuteCommand("iedriverserver", ProcessType.Kill);
		//		ExecuteCommand(mPackageName, ProcessType.ClearApp);
		tearDown();
	}
}

