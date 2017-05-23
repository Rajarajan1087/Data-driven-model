package com.Enumerations;

public interface Generic {

	public static enum iframes{
		defaultcontent,welcome,search,create,Mycases,reviewEvent,search_cm,Tasklist,Bulk,Pega4,Pega5
	}

	public static enum CustomerType{
		Cancelled,Disconnected,Incollection,DCA_Jefersson,DCA_Lowell,MultipleCli,HMInflight,inflight,Fraud
	}
	public enum DBName{
		CRM,SV,OMP
	}

	public enum Status{
		CANCELLED,ACTIVE,INACTIVE,DISCONNECTED
	}
	
	public enum CustomerSearchDB{
		CLI,ACCOUNTNUMBER,CUSTOMERNUMBER,POSTCODE,FIRSTNAME,LASTNAME
	}
	
	public enum CustomerDetails {
		TITLE,FIRSTNAME,LASTNAME,CLI,CUSTOMERNUMBER,ACCOUNTNUMBER,PSPSTATUSCODE,SECURITYQUESTNTEXT,SECURITYANSWERTEXT,ONLINEUSERNAME,ONLINEPASSWORD,MARKETINGPREFEMAILFLAG,MARKETINGPREFLETTERFLAG,MARKETINGPREFSMSFLAG,MARKETINGPREFVOICEFLAG,BIRTHDATE,GENDERCODE
	}
}
