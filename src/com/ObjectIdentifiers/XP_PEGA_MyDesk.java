package com.ObjectIdentifiers;
/**Class Name		:	XP_PEGA_MyDesk
 * Description		:	Interface for storing Element Locators in PEGA Pages - MyDesk
 * @author			:	RajaRajan
 * Function Names	:	no functions
 * Creation Date	:	07 Sep 2016
 */

public interface XP_PEGA_MyDesk {

	/**
	 * Home Page 
	 */
	

//	  Welcome Section
	
//	Replaced the Xpath for Desktop_XPATH_Home_Page_Validation with //img[contains(@src,'mydesk')] from //*[contains(text(),'Welcome to MyDesk')]
	public static final String Desktop_XPATH_Home_Page_Validation															=	"//*[contains(@src,'mydesk')]";
	public static final String Desktop_XPATH_Home_Page_Validation_OperatorMgmt												=	"//a[contains(text(),'Operator Management')]";
	public static final String Desktop_XPATH_Options_OperatorMgmt															=	"//span[contains(text(),'M_Text')]/ancestor::a";
	public static final String Desktop_XPATH_Identify_Customer_Page_Validation												=	"//*[text()='Identify Customer']";
	public static final String Desktop_XPATH_Home_Page_InteractionButton													=	"//a[@title='Create a New Interaction']";
	public static final String Desktop_XPATH_Home_Page_Welcome_Message														=	"//*[contains(text(),'Welcome')]";
	public static final String Desktop_XPATH_Home_Page_Welcome_Message_AccessControl										=	"//img[contains(@src,'newbrandingtalktalk')]";
	

	
	
//	  Bulk Operator creator section
	
	
	public static final String Desktop_XPATH_BulkOpCreate_Page_Validation_OperatorMgmt										=	"//label[contains(text(),'Upload Excel Sheet with Operator Details')]";
	public static final String Desktop_XPATH_BulkOpCreate_Page_CloseButton													=	"//button[contains(@class,'AlternateButton')";
	
	
	
//	  Customer Search By Methods

	
	
	
//	  Search term Button
	
	public static final String Desktop_XPATH_SearchBy_Account_Button														=	"//*[@class='Customer_Search pzhc']//*[text()='Account Number']";
	public static final String Desktop_XPATH_SearchBy_Phone_Button															=	"//*[@class='Customer_Search pzhc']//*[text()='Phone Number']";
	public static final String Desktop_XPATH_SearchBy_Postcode_Button														=	"//*[text()='Postcode']/ancestor::button[@class='Strong pzhc']";
	public static final String Desktop_XPATH_SearchBy_MyAccount_Button														=	"//*[text()='MyAccount Username']";
	public static final String Desktop_XPATH_SearchBy_MobilePhone_Button													=	"//*[text()='TalkTalk Mobile Number']/ancestor::button[contains(@class,'Customer_Search')]";
	
	
//	 Search term TextBox
	
	public static final String Desktop_XPATH_SearchBy_Account_TextBox														=	"id=SearchStringAcctNum";
	public static final String Desktop_XPATH_SearchBy_Phone_TextBox															=	"id=SearchStringPhone";
	public static final String Desktop_XPATH_SearchBy_MyAccountName_TextBox													=	"id=SearchStringOnlineUserName";
	public static final String Desktop_XPATH_SearchBy_PostCode_TextBox														=	"id=searchStringPostCode";
	public static final String Desktop_XPATH_SearchBy_LastName_TextBox														=	"id=SearchStringLastName";
	public static final String Desktop_XPATH_SearchBy_Brand_DropDown														=	"id=SearchStringBrand";
	public static final String Desktop_XPATH_SearchBy_Mobile_TextBox														=	"id=SearchStringMobile";
	
	
//	  Buttons for Search, Clear, Hide, Show , Other Actions
	
	public static final String Desktop_XPATH_Search_Button																	=	"//*[text()='Search']";
	public static final String Desktop_XPATH_Clear_Button																	=	"//*[text()='Clear']";
	public static final String Desktop_XPATH_Show_Button																	=	"//*[text()='Show Details ']";
	public static final String Desktop_XPATH_Hide_Button																	=	"//*[text()='Hide Details ']";
	public static final String Desktop_XPATH_Other_Actions_Button															=	"//*[text()='Other Actions ']";
	
	
	
//	 Verification labels and values
	
	
	public static final String Desktop_XPATH_Identify_Customer_label														=	"//*[text()='Identify Customer']";															
	public static final String Desktop_XPATH_Caller_Name_label																=	"//*[text()='Caller Name']";
	public static final String Desktop_XPATH_IDV_Status_label																=	"//*[text()='ID&V Status']";
	public static final String Desktop_XPATH_Phone_Number_label																=	"//label[text()='Phone Number' and @for='CLINumber']";
	public static final String Desktop_XPATH_Account_Number_label															=	"//label[text()='Account Number' and @for='AccountNumber']";
	public static final String Desktop_XPATH_Total_Call_Length_label														=	"//*[text()='Total Call Length']";
	public static final String Desktop_XPATH_My_Call_Length_label															=	"//*[text()='My Call Length']";
	public static final String Desktop_XPATH_Call_Reason_label																=	"//*[text()='Call Reason']";
	public static final String Desktop_XPATH_Package_label																	=	"//*[text()='Package']";
	public static final String Desktop_XPATH_Avg_Monthly_Spend_label														=	"//*[text()='Avg. Monthly Spend']";
	public static final String Desktop_XPATH_Total_Transfers_label															=	"//*[text()='Total Transfers']";
	public static final String Desktop_XPATH_Hold_Time_label																=	"//*[text()='Hold Time']";
	
	
	
	
//	 Failures
	 
	
	
	
	
	
//	  Search Results Verification and next page navigation
	  
	public static final String Desktop_XPATH_Search_Results																	=	"//*[contains(text(),'Full Name')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Pointer')]";
	public static final String Desktop_XPATH_PostCode_Search_Results														=	"//*[contains(text(),'First Name')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Pointer')]";
	public static final String Desktop_XPATH_Search_Results1																=	"/ancestor::tr[1]//*[contains(text(),'M_Pointer')]";
	public static final String Desktop_XPATH_Search_Results_Pane															=	"//*[@node_name='CPMContactList']//*[text()='Results']";
	public static final String Desktop_XPATH_PostCode_Search_Results_Pane													=	"//*[@node_name='CPMContactList']//*[text()='Postcode Search Results']";
	public static final String Desktop_XPATH_Search_Results_Count_Checker													=	"//*[contains(text(),'M_Pointer1')]/following::tr[M_Pointer2]/td[2]";
	public static final String Desktop_XPATH_Search_Results_Single_Data_Fetcher												=	"//*[contains(text(),'M_Pointer1')]/following::tr[M_Pointer2]/td[M_Pointer3]";
	
	
	
	

	/**
	 * Verify Caller Page
	 * */
	
	

//	  Verification labels and values
	 
	
	public static final String Desktop_XPATH_Verify_Caller_Page_Validation													=	"//*[contains(text(),'M_ValidationText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_Hide_Show													=	"//*[contains(text(),'M_ValidationText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_Submit														=	"//*[@id='M_ValidationText']";
	public static final String Desktop_XPATH_Verify_Caller_Page_OtherActions_link											=	"//*[contains(text(),'Other Actions')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_CLIVerification												=	"//*[contains(text(),'M_Data')]/ancestor::*[contains(@class,'inner')][1]//*[contains(@class,'Value')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_CallLength													=	"//*[@id='elapsedTime']";
	public static final String Desktop_XPATH_Verify_Caller_Page_CallerSelection												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_CallerName													=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_CallerVerification											=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_CallReason													=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_CallReason_GeneralEnquiry									=	"//input[@id='IsGeneralEnquiry']";
	public static final String Desktop_XPATH_Verify_Caller_Page_TelephonePassword											=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_TelephonePassword_Answer									=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]/ancestor::tr[1]//*[contains(text(),'M_Answer')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_AdditionalQuestions											=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_AdditionalQuestions_Answer									=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_InnerText')]/ancestor::tr[1]//*[contains(text(),'M_Answer')]";
	public static final String Desktop_XPATH_Verify_Caller_Page_FirstName_Text												=	"id=FirstName";
	public static final String Desktop_XPATH_Verify_Caller_Page_LastName_Text												=	"id=LastName";
	public static final String Desktop_XPATH_Verify_Caller_Telephone_Password_CheckBox										=	"id=ValidAccountPassword";
	public static final String Desktop_XPATH_Verify_Caller_Additional_Questions_CheckBox									=	"//*[contains(text(),'Additional')]/ancestor::*[@id='EXPAND-OUTERFRAME']/descendant::input[@type='checkbox']";
	
	
	/**
	 * Bypass Verification Page
	 * */
	
	public static final String Desktop_XPATH_Bypass_Verification_Page_ByPassReason_Select									=	"id=ByPassReason";
	public static final String Desktop_XPATH_Bypass_Verification_Page_ByPassSubReason_Select								=	"id=ByPassSubReason";
	public static final String Desktop_XPATH_Bypass_Reason																	=	"//*[contains(text(),'Reason')]";

	
	/**
	 * Cases Page
	 * */
	
	public static final String Desktop_XPATH_Generic_UI_TEXT_Searcher														=	"//*[contains(text(),'M_ValidationText')]";
	public static final String Desktop_XPATH_Cases_Frame_AddCase_Button														=	"//*[contains(text(),'Add Case')]/ancestor::button";
	public static final String Desktop_XPATH_Cases_Frame_Addtask_Button														=	"//*[@title='Action' and @class='Add_task']";
	public static final String Desktop_XPATH_Cases_Frame_Specific_Task_PageLoad												=	"//label[contains(text(),'task')]";
	public static final String Desktop_XPATH_Cases_Frame_SearchAction_Text													=	"id=CPMTaskSearchInput";
	public static final String Desktop_XPATH_Cases_Frame_Search_Button														=	"//*[contains(@src,'searchicon')]";
	public static final String Desktop_XPATH_Cases_Frame_ActionCategory_Button												=	"//*[@class='Add_task' and contains(text(),'M_Category')]";
	public static final String Desktop_XPATH_Cases_Frame_ActionCategory_Button_Selected										=	"//*[@class='Add_task_selected' and contains(text(),'M_Category')]";
	public static final String Desktop_XPATH_Cases_Frame_Add_cases_Button													=	"//*[contains(text(),'Add case(s)')]/ancestor::button";
	public static final String Desktop_XPATH_Cases_Frame_PageSize_DropDown													=	"//*[@id='PageSize']";
	public static final String Desktop_XPATH_Cases_Frame_Choose_CaseCategory												=	"//*[@pl_prop_class='Rule-PegaCA-Intent-Task']/descendant::*/*[contains(text(),'Action')]";
	public static final String Desktop_XPATH_Confirm_Button																	=	"//*[text()='Confirm']";
	public static final String Desktop_XPATH_Close_Button																	=	"//*[contains(text(),'Close')]/ancestor::button";

	
/*
 * payments
 * */
	
	
	//Button and options
	
	public static final String Desktop_XPATH_PayFor_Elements_FixedLine_Enabled_Selected										=	"//*[text()='Pay For']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Fixed Line']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_PayFor_Elements_FixedLine_Not_Selected											=	"//*[text()='Pay For']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Fixed Line']//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_PayFor_Elements_Mobile_Enabled_Selected										=	"//*[text()='Pay For']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Mobile']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_PayFor_Elements_Mobile_Not_Selected											=	"//*[text()='Pay For']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Mobile']//ancestor::button[contains(@class,'Customer_Search')]";
	
	public static final String Desktop_XPATH_PaymentOptions_Elements_DirectDebit_Enabled_Selected							=	"//*[text()='Payment Options']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Direct Debit']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_PaymentOptions_Elements_DirectDebit_Not_Selected								=	"//*[text()='Payment Options']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Direct Debit']//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_PaymentOptions_Elements_OneOffPay_Enabled_Selected								=	"//*[text()='Payment Options']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='One off card payment']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_PaymentOptions_Elements_OneOffPay_Not_Selected									=	"//*[text()='Payment Options']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='One off card payment']//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_PaymentOptions_Elements_RecurringCard_Enabled_Selected							=	"//*[text()='Payment Options']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Recurring card setup']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_PaymentOptions_Elements_RecurringCard_Not_Selected								=	"//*[text()='Payment Options']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Recurring card setup']//ancestor::button[contains(@class,'Customer_Search')]";
	
	
	public static final String Desktop_XPATH_PaytWith_Elements_RegisteredCard_Enabled_Selected								=	"//*[text()='Pay With']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Registered Card']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_PaytWith_Elements_RegisteredCard_Not_Selected									=	"//*[text()='Pay With']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Registered Card']//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_PaytWith_Elements_UnRegisteredCard_Enabled_Selected							=	"//*[text()='Pay With']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Unregistered Card']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_PaytWith_Elements_UnRegisteredCard_Not_Selected								=	"//*[text()='Pay With']//ancestor::div[contains(@class,'layout-content-inline')]//*[text()='Unregistered Card']//ancestor::button[contains(@class,'Customer_Search')]";
	
	
	public static final String Desktop_XPATH_RegisterCardFor_Elements_FutureOneOffPay_Enabled_Selected						=	"//*[text()='Register Card for']//ancestor::div[contains(@class,'layout-content-default')]//*[text()='Future one-off payments']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_RegisterCardFor_Elements_FutureOneOffPay_Not_Selected							=	"//*[text()='Register Card for']//ancestor::div[contains(@class,'layout-content-default')]//*[text()='Future one-off payments']//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_RegisterCardFor_Elements_RecurringCard_Enabled_Selected						=	"//*[text()='Register Card for']//ancestor::div[contains(@class,'layout-content-default')]//*[text()='Recurring card payment']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_RegisterCardFor_Elements_RecurringCard_Not_Selected							=	"//*[text()='Register Card for']//ancestor::div[contains(@class,'layout-content-default')]//*[text()='Recurring card payment']//ancestor::button[contains(@class,'Customer_Search')]";
	
	public static final String Desktop_XPATH_Payment_CardHolderAddress_BillingAddressButton_Not_Selected					=	"//*[text()='Same as Billing Address']//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_Payment_CardHolderAddress_BillingAddressButton_Enabled_Selected				=	"//*[text()='Same as Billing Address']//ancestor::button[contains(@class,'Strong')]";
	
	
	//Credit card panel
	public static final String Desktop_XPATH_Payment_CardHolderAddress_Elements												=	"//*[contains(@value,'Elements')]";
	
	
	public static final String Desktop_XPATH_Payment_CardDetail_ErrorMessage												=	"//following::div/span[contains(text(),'Error')]";
	public static final String Desktop_XPATH_Payment_CardDetail_NameOnCard													=	"//input[contains(@id,'CardHoldersName')]";
	public static final String Desktop_XPATH_Payment_CardDetail_NameOnCard_label											=	"//label[contains(@for,'CardHoldersName')]/following::span[1][contains(text(),'M_Validation_text')]";
	public static final String Desktop_XPATH_Payment_CardDetail_StartDate_Month												=	"//input[contains(@id,'StartDateMonth')]";
	public static final String Desktop_XPATH_Payment_CardDetail_StartDate_Year												=	"//input[contains(@id,'StartDateYear')]";
	public static final String Desktop_XPATH_Payment_CardDetail_EndDate_Month												=	"//input[contains(@id,'ExpiryDateMonth')]";
	public static final String Desktop_XPATH_Payment_CardDetail_EndDate_Year												=	"//input[contains(@id,'ExpiryDateYear')]";
	public static final String Desktop_XPATH_Payment_CardDetail_EndDate_Month_label											=	"//label[contains(@for,'ExpiryDateMonth')]/following::span[1][contains(text(),'M_Validation_text')]";
	public static final String Desktop_XPATH_Payment_CardDetail_EndDate_Year_label											=	"//label[contains(@for,'ExpiryDateYear')]/following::span[1][contains(text(),'M_Validation_text')]";
	public static final String Desktop_XPATH_Payment_CardDetail_CreditCardNumber_label										=	"//label[contains(@for,'CardNumber')]/following::span[1][contains(text(),'M_Validation_text')]";
	
	
	public static final String Desktop_XPATH_Payment_CardDetail_CardType_VISA												=	"//img[contains(@src,'Visa')]";
	public static final String Desktop_XPATH_Payment_CardDetail_CardType_AMEX												=	"//img[contains(@src,'Amex')]";
	public static final String Desktop_XPATH_Payment_CardDetail_CardType_MAESTRO											=	"//img[contains(@src,'Masetro')]";
	public static final String Desktop_XPATH_Payment_CardDetail_CardType_MASTERCARD											=	"//img[contains(@src,'Mastercard')]";
	
	public static final String Desktop_XPATH_Payment_CardDetail_IssueNum_MAESTRO											=	"//input[contains(@id,'IssueNum')]";
	
	public static final String Desktop_XPATH_Payment_CardDetail_AccountBalanceInput_Integer									=	"//input[contains(@id,'PaymentAmtInteger')]";
	public static final String Desktop_XPATH_Payment_CardDetail_AccountBalanceInput_Decimal									=	"//input[contains(@id,'PaymentAmtDecimal')]";
	
	public static final String Desktop_XPATH_Payment_CardDetail_EstablishConnection_Button									=	"//*[text()='Establish Secure Connection']//ancestor::button[contains(@class,'Strong')]";
	public static final String Desktop_XPATH_Payments_RegisteredcardDetails_Row1											=	"//*[contains(text(),'Cards registered for')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::tbody[1]/tr[2]/td//span[contains(text(),'M_InnerText')]";
	
	
	public static final String Desktop_XPATH_Payment_CardHolderAddress_AddressLine1											=	"//input[contains(@id,'AddressLine1')]";
	public static final String Desktop_XPATH_Payment_CardHolderAddress_AddressLine2											=	"//input[contains(@id,'AddressLine2')]";
	public static final String Desktop_XPATH_Payment_CardHolderAddress_TownCity												=	"//input[contains(@id,'TownOrCity')]";
	public static final String Desktop_XPATH_Payment_CardHolderAddress_County												=	"//input[contains(@id,'County')]";
	public static final String Desktop_XPATH_Payment_CardHolderAddress_Country												=	"//input[contains(@id,'Country')]";
	public static final String Desktop_XPATH_Payment_CardHolderAddress_PostCode												=	"//input[contains(@id,'Postcode')]";
	
	
	
	public static final String Desktop_XPATH_Payment_CIB_CallerName															=	"//*[contains(@for,'CallerName')]//following::div[1]";
	public static final String Desktop_XPATH_Payments_AccountDetails														=	"//*[contains(text(),'Account Details')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[text()='M_InnerText']";
	public static final String Desktop_XPATH_Payments_Mobile_AccountDetails													=	"//*[contains(text(),'Account Details')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::tbody[1]/tr[2]/td//span[contains(text(),'M_InnerText')]";
	
	
	public static final String Desktop_XPATH_Payments_CardType_NotSelected_Error											=	"//*[@id='ERRORMESSAGES_ALL']/ul/li[contains(text(),'M_ErrorMsg')]";
	
	public static final String Desktop_XPATH_Payment_CardList_RecordSelectionTick_FirstOnTable								=	"//table[@pl_prop='RegisteredCardList.pxResults']//tr[2]/td[1]/div/img";
	public static final String Desktop_XPATH_Payment_CardList_CardNumber_FirstOnTable										=	"//table[@pl_prop='RegisteredCardList.pxResults']//tr[2]/td[2]//span";
	public static final String Desktop_XPATH_Payment_CardList_NameOncard_FirstOnTable										=	"//table[@pl_prop='RegisteredCardList.pxResults']//tr[2]/td[3]//span";
	public static final String Desktop_XPATH_Payment_CardList_Expirydate_FirstOnTable										=	"//table[@pl_prop='RegisteredCardList.pxResults']//tr[2]/td[5]//span";
	
	
//	Associated case services
	
	
	public static final String Desktop_XPATH_CaseList_CaseID_FirstOnTable												=	"//td[contains(@data-attribute-name,'Case')]/ancestor::tbody[1]/tr[2]/td[2]//span/a";
	public static final String Desktop_XPATH_CaseList_CaseType_FirstOnTable												=	"//td[contains(@data-attribute-name,'Case')]/ancestor::tbody[1]/tr[2]/td[3]//span";
	public static final String Desktop_XPATH_CaseList_CaseStatus_FirstOnTable											=	"//td[contains(@data-attribute-name,'Case')]/ancestor::tbody[1]/tr[2]/td[4]//span";
	public static final String Desktop_XPATH_CaseList_CaseDateTime_FirstOnTable											=	"//td[contains(@data-attribute-name,'Case')]/ancestor::tbody[1]/tr[2]/td[5]//span";
	
	public static final String Desktop_XPATH_CaseList_CaseStatus_FirstOnTable_ValueChecker								=	"//td[contains(@data-attribute-name,'Case')]/ancestor::tbody[1]/tr[2]/td[4]//span[contains(text(),'M_Case_Status')]";
	
	
//	Cancel Work on cases
	
	
	public static final String Desktop_XPATH_Payments_CardNotrecognisedButton_Disabled										=	"//*[contains(text(),'Card not recognised')]//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_Payments_CustomerChangedMindButton_Disabled									=	"//*[contains(text(),'Customer changed mind')]//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_Payments_TechnicalIssueButton_Disabled											=	"//*[contains(text(),'Technical Issue')]//ancestor::button[contains(@class,'Customer_Search')]";
	public static final String Desktop_XPATH_Payments_CardNotrecognisedButton_enabled										=	"//*[contains(text(),'Card not recognised')]//ancestor::button[contains(@class,'Customer_Search') and contains(@style,'display:none')]";
	public static final String Desktop_XPATH_Payments_CustomerChangedMindButton_enabled										=	"//*[contains(text(),'Customer changed mind')]//ancestor::button[contains(@class,'Customer_Search') and contains(@style,'display:none')]";
	public static final String Desktop_XPATH_Payments_TechnicalIssueButton_enabled											=	"//*[contains(text(),'Technical Issue')]//ancestor::button[contains(@class,'Customer_Search') and contains(@style,'display:none')]";
	/**
	 * Summary Page
	 * */
	
	public static final String Desktop_XPATH_Summary_Caller_Page_Validation													=	"//h3[contains(text(),'Summary')]";
	public static final String Desktop_XPATH_Verify_CallerName																=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_IDVStatus																=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_AccountNumber															=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_PhoneNumber																=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Package																	=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_AvgMonthlySpend															=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_ContractEndDate											=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_RemainingContractTerm										=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_LastBillDate												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_LastBillAmount												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_PaymentDueDate												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_OutstandingBalance											=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_UnbilledUsage												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_NextBillDate												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_SwitchPackage												=	"//*[contains(text(),'Switch Packages/Accounts')]";
	public static final String Desktop_XPATH_Verify_Summary_Page_SwitchPackage_disabled										=	"//*[contains(text(),'Switch Packages/Accounts') and @disabled='']";
	public static final String Desktop_XPATH_Summary_Page_Switchtabs														=	"//h3[contains(text(),'TABS')]";
	public static final String Desktop_XPATH_Switch_Pack_PopUp_Submit														=	"//*[@id='ModalButtonSubmit']";
	
	
//	  Phone Number Summary
	
	
	public static final String Desktop_XPATH_PhoneNumber_Summary_Page_Header												=	"//*//*[contains(text(),'Phone Number Summary')]/following::table[3]/tbody/tr[1]/th[line]/div/div/div[contains(text(),'header')]";
	public static final String Desktop_XPATH_PhoneNumber_Summary_Page_PhoneNumber											=	"//*[contains(text(),'Phone Number Summary')]/following::table[3]/tbody/tr[line]/td[1]";
	public static final String Desktop_XPATH_PhoneNumber_Summary_Page_Account												=	"//*[contains(text(),'Phone Number Summary')]/following::table[3]/tbody/tr[line]/td[2]";
	public static final String Desktop_XPATH_PhoneNumber_Summary_Page_Package												=	"//*[contains(text(),'Phone Number Summary')]/following::table[3]/tbody/tr[line]/td[3]";
	
	/**
	 * Customer Page
	 * */
	public static final String Desktop_XPATH_Verify_Customer_Page_CorrespondenceAddress										=	"//*[contains(text(),'M_Header')]/ancestor::*[@bsimplelayout='true'][1]//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_BillingAddress											=	"//*[contains(text(),'M_Header')]/ancestor::*[@bsimplelayout='true'][1]//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_InstallationAddress										=	"//*[contains(text(),'M_Header')]/ancestor::*[@bsimplelayout='true'][1]//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_TelephoneHome												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::tbody[1]//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_TelephoneWork												=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::tbody[1]//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_TelephoneMobile											=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::tbody[1]//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_Security													=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_CustomerAccountSummary									=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::tbody[1]//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Customer_Page_FrameAvailibility											=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']";
	
	// XPATHs for Desktop_XPATH_Verify_Customer_Page_MarketingPreferences and Desktop_XPATH_Verify_Customer_Page_PreferredContactMethod needs to be modified for verifying checkboxes to be checked
	public static final String Desktop_XPATH_Verify_Customer_Page_MarketingPreferences										=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::*[@id='RULE_KEY'][1]//*[@for='M_InnerText']/ancestor::span/input[@type='checkbox']";
	public static final String Desktop_XPATH_Verify_Customer_Page_PreferredContactMethod									=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']//*[contains(text(),'M_Category')]/ancestor::*[@id='RULE_KEY'][1]//*[@for='M_InnerText']/ancestor::span/input[@type='checkbox']";
	
	
	/**
	 * Account Page
	 * */
	public static final String Desktop_XPATH_Verify_Account_Page_AccountSummary												=	"//*[contains(text(),'M_Header')]/ancestor::*[@bsimplelayout='true'][1]//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Account_Page_Collection													=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	public static final String Desktop_XPATH_Verify_Account_Page_DCA														=	"//*[contains(text(),'M_Category')]/parent::*//*[contains(text(),'M_InnerText')]";
	
	/**
	 * Common_Functions  
	 */
	
	

//	PEGA MyDesk Login

	
	public static final String Desktop_XPATH_ID_Username																   	=	"//*[@id='txtUserID']";
	public static final String Desktop_XPATH_ID_password																   	=	"//*[@id='txtPassword']";
	public static final String Desktop_XPATH_ID_Submit																   		=	"//*[@id='sub']";
	
	

//	 PEGA MyDesk Logout
	 
	
	public static final String Desktop_XPATH_user																			=	"//*[@id='RULE_KEY']/div[1]/div/div/div[2]/div/div/div/div/div/div/div/span/a";
	public static final String Desktop_XPATH_logout																			=	"//*[contains(@class,'menu-item-title')]//*[contains(text(),'Logout')]";
	
	
	
//	Frames
	
	
	public static final String Desktop_XPATH_Welcome_Frame																	=	"id=PegaGadget0Ifr";
	public static final String Desktop_XPATH_Search_Frame																	=	"id=PegaGadget1Ifr";
	public static final String Desktop_XPATH_Create_Frame																	=	"id=PegaGadget2Ifr";
	public static final String Desktop_XPATH_Bulk_Frame																		=	"//iframe[@id='PegaGadget3Ifr']";
	public static final String Desktop_XPATH_Pega4_Frame																	=	"//iframe[@id='PegaGadget4Ifr']";
	public static final String Desktop_XPATH_Pega5_Frame																	=	"id=PegaGadget5Ifr";
	public static final String Desktop_XPATH_Mycases_Frame																	=	"xpath=(//iframe[contains(@src,'harnessName=pyCMCases')])[last()]";
	public static final String Desktop_XPATH_reviewEvent_Frame																=	"xpath=(//iframe[contains(@src,'action=openWorkByHandle')])[last()]";
	public static final String Desktop_XPATH_search_cm_Frame																=	"xpath=(//iframe[contains(@src,'action=pyUpdatePortalContext&label')])[last()]";
	public static final String Desktop_XPATH_Tasklist_Frame																	=	"xpath=(//iframe[contains(@src,'openAssignment&key')])[last()]";
	
	
//	 Other Actions
	
	public static final String Desktop_XPATH_OtherActions																	=	"//*[contains(text(),'M_Action')]/ancestor::*[contains(@id,'pyNavigation') and contains(@style,'display: block')][1]//*[contains(text(),'M_Action')]/ancestor::li";
	public static final String Desktop_XPATH_OtherActions_CancelTheWork_CasePage_Movement									=	"//li[contains(@title,'Refresh')]/following::li/a";
	public static final String Desktop_XPATH_Exit_Interation_Comments														=	"//*[@id='CancelNotes']";
	public static final String Desktop_XPATH_Submit_Button																	=	"//*[text()='Submit']";
	public static final String Desktop_XPATH_Bypass_Verification_pageUniquelabel											=	"//*[text()='Bypass Details']";
	public static final String Desktop_XPATH_Cases_OtherAction_PageLoad														=	"//label[@class='actionTitleBarLabelStyle' and contains(text(),'title')]";
	
	
//	 Wrap up	 
	
//	
	public static final String Desktop_XPATH_Cases_Frame_WrapUP_Button														=	"//button[contains(@class,'Wrap_up_button')]";
	public static final String Desktop_XPATH_Cases_Frame_WrapUP_Comment														=	"id=WrapUpComments";
	public static final String Desktop_XPATH_Cases_Frame_WrapUP_Comment_RemChar												=	"//span[contains(@id,'WrapUpComments_counter')]";
	public static final String Desktop_XPATH_Cases_Frame_WrapUP_Page														=	"//*[contains(text(),'Wrap Up')]";

	
//	 Alert message
	 
	 
	public static final String Desktop_XPATH_AlertMessage_Hide_link															=	"//*[contains(text(),'Alert Message')]/following::a[text()='Hide']";
	public static final String Desktop_XPATH_AlertMessage_Show_link															=	"//*[contains(text(),'Alert Message')]/following::a[text()='Show']";
	public static final String Desktop_XPATH_AlertMessage_Content_checker 													=	"//*[contains(text(),'alert')]";
	public static final String Desktop_XPATH_AlertMessage_Collections 														=	"//*[contains(text(),'Account in Collections Treatment')]";
	
	
//	  OuterFrame Availability
	
	
	public static final String Desktop_XPATH_OuterFrame_in_Page 															=	"//*[contains(text(),'M_Header')]/ancestor::*[@id='EXPAND-OUTERFRAME']";
	
	/**
	 * CTI_Actions  
	 */ 
	
	// Talk safe Enrollment options

	public static final String Talksafe_FirstName 																			= "//tr[contains(@id,'TalkSafeUsers')][1]/td[@headers='a2']//*[contains(text(),'M_AccType')]";
	public static final String Talksafe_LastName 																			= "//tr[contains(@id,'TalkSafeUsers')][1]/td[@headers='a3']//*[contains(text(),'M_AccType')]";
	public static final String Talksafe_AccType 																			= "//tr[contains(@id,'TalkSafeUsers')][1]/td[@headers='a4']//*[contains(text(),'M_AccType')]";
	public static final String Talksafe_EnrollmentOptions_Enabled															= "//tr[contains(@id,'TalkSafeUsers')][1]/td[7]//button[contains(@class,'Strong')]//div[text()='M_EnrollType']";
	public static final String Talksafe_EnrollmentOptions_Disabled															= "//tr[contains(@id,'TalkSafeUsers')][1]/td[7]//button[contains(@class,'Customer')]//div[text()='M_EnrollType']";
	public static final String Talksafe_submit 																				= "//*[@id='RULE_KEY']/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div/span/button/div/div/div/div/[@img='Submit']";
	public static final String Talksafe_warningMsg_Never 																	= ".//*[@id='RULE_KEY']/div/div/div/div/div/div/span";
	
//	CTI ToolBar
	
	public static final String Desktop_XPATH_CTI_FullToolbar																=	"//*[@id='PegaCallContainerDIV']";
	public static final String Desktop_XPATH_CTI_PhoneIcon_WithoutCall														=	"//*[@id='CT']/nobr/table/tbody/tr/td[2]/button";
	public static final String Desktop_XPATH_CTI_PhoneIcon_OnCall_Available													=	"//*[@id='pegaCallLoginOption' and @class='cpmpegacall_available']";
	public static final String Desktop_XPATH_CTI_PhoneIcon_OnCall_UnAvailable												=	"//*[@id='pegaCallLoginOption' and @class='cpmpegacall_notavailable']";
	public static final String Desktop_XPATH_CTI_PhoneIcon_OnCall_AfterCall													=	"//*[@id='pegaCallLoginOption' and @class='cpmpegacall_aftercall']";
	public static final String Desktop_XPATH_CTI_Status_DropDown_Button														=	"//*[@class='AgentStateButton']//button";
	public static final String Desktop_XPATH_CTI_PhoneCallButton_Idle_1														=	"//*[@id='CALLPhoneButton' and @line_id='0' and @class='cpmpegacall_idle']";
	public static final String Desktop_XPATH_CTI_PhoneCallButton_Idle_2														=	"//*[@id='CALLPhoneButton' and @line_id='1' and @class='cpmpegacall_idle']";
	public static final String Desktop_XPATH_CTI_PhoneCallButton_Active_1													=	"//*[@id='CALLPhoneButton' and @line_id='0' and @class='cpmpegacall_connected']";
	public static final String Desktop_XPATH_CTI_PhoneCallButton_Active_2													=	"//*[@id='CALLPhoneButton' and @line_id='1' and @class='cpmpegacall_connected']";
	public static final String Desktop_XPATH_CTI_PhoneCallButton_Initiating_1												=	"//*[@id='CALLPhoneButton' and @line_id='0' and @class='cpmpegacall_initiating']";
	public static final String Desktop_XPATH_CTI_PhoneNumber_TextBox														=	"//*[@id='pyCpmPhoneNumberInputBox']";
	public static final String Desktop_XPATH_CTI_PhoneNumber_CallButton														=	"//*[@id='CallActionButton' and @title='Call']";
	public static final String Desktop_XPATH_CTI_PhoneNumber_WarmTransferButton												=	"//*[@id='CallActionButton' and @title='Warm Transfer']";
	public static final String Desktop_XPATH_CTI_PhoneNumber_HangUpButton													=	"//*[@id='CallActionButton' and @title='Hang up']";
	public static final String Desktop_XPATH_CTI_PhoneNumber_Call_SideButton												=	"//*[@class='callActionsRight']";
	public static final String Desktop_XPATH_CTI_PhoneNumber_Call_Side_WarmTransferOption									=	"//*[@id='/contextMenu/WarmTransfer']";
	public static final String Desktop_XPATH_CTI_PhoneNumber_Call_Side_HangUpOption											=	"//*[@id='/contextMenu/HangUp']";
	
	
//	CTI Call Status MenuBar
	
	public static final String Desktop_XPATH_CTI_Status_MenuOptions_enabled													=	"//*[@class='menu-panel-wrapper']//li[contains(@class,'enabled')]//span[text()='MenuOptions']";
	public static final String Desktop_XPATH_CTI_Status_MenuOptions_disabled												=	"//*[@class='menu-panel-wrapper']//li[contains(@class,'disabled')]//span[text()='MenuOptions']";
	public static final String Desktop_XPATH_CTI_Status_UnAvailable_enabled_innerOption										=	"//*[@class='menu-panel-wrapper']//li[contains(@class,'enabled')]//span[text()='Unavailable']/ancestor::li//li[@title='innerOptions']";
	public static final String Desktop_XPATH_CTI_Status_LogOut_enabled_innerOption											=	"//*[@class='menu-panel-wrapper']//li[contains(@class,'enabled')]//span[text()='Logout']/ancestor::li//li[@title='innerOptions']";
	
//	CTI Login Window
	
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_Title													=	"//*[contains(text(),'Phone Login')]";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow															=	"//*[@id='modaldialog_hd']/following::div[1]";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_SelectLink												=	"//*[@id='pySelectedLinkName']";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_Extension												=	"//*[@id='pyExtension']";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_Agent_ID													=	"//*[@id='pyAgentId']";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_PasswordCTI												=	"//*[@id='pyAgentPwd']";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_LoginButton												=	"//*[text()='Login']";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_ErrorTag													=	"//*[@for='pyPegaCTIError']";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_ErrorMessage												=	"//*[@for='pyPegaCTIError']/ancestor::tr[1]/td[contains(text(),'ErrorMessage')]";
	public static final String Desktop_XPATH_CTI_Phone_Loginwindow_Dialoguebox_Close										=	"//*[contains(@title,'close modal')]";
	
	
}
