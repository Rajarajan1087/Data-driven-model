package com.Enumerations;

public interface MyDeskEnumerations {

	public static enum SearchBy {
		CLI,ACCOUNT,MyAccountusername,Postcode,Postcodeandlastname,MyAccountusernameWithBrand,MCLI
	}
	public static enum CustomerInformationBar {
		Caller_Name,IDandV_Status,Call_Reason,Phone_Number,Package,Account_Number,Monthly_Spend,Total_Call_Length,My_Call_Length,Total_Transfers,Hold_Time
	}
	
	public static enum PhoneNumberSummary {
		Phone_Number,Package,Account_Number,Package_Name,Status,Installation_Address
	}
	
	
	
	public static enum PaymentsAccountDetails {
		Account_Number,Phone_Number,Account_Balance,Latest_Bill_Amount,Latest_Bill_Due_Date,Payment_Method,Approx_Bill_Due_Date
	}
	
	
	
	public static enum CardType {
		VISA,AMEX,MASTERCARD,MAESTRO
	}
	
}
