package com.SharedModules;

public class CustomerBean {
	private  String AccountNumber;
	private  String CustomerNumber;
	private  String BranchCode;
	private  String ExchangeCode;
	private String CLI;
	private String FirstName;
	private String LastName;
	private String BrandName;
	
	public String getCLI() {
		return CLI;
	}
	public void setCLI(String cLI) {
		CLI = cLI;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public String getCustomerNumber() {
		return CustomerNumber;
	}
	public String getBranchCode() {
		return BranchCode;
	}
	public String getExchangeCode() {
		return ExchangeCode;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		CustomerNumber = customerNumber;
	}
	public void setBranchCode(String branchCode) {
		BranchCode = branchCode;
	}
	public void setExchangeCode(String exchangeCode) {
		ExchangeCode = exchangeCode;
	}
	
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getFirstName() {
		return FirstName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getLastName() {
		return LastName;
	}
	
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	public String getBrandName() {
		return BrandName;
	}

}
