package com.ObjectIdentifiers;

import java.util.HashMap;

import com.sun.jna.StringArray;

public interface PEGAConstants {
	
	
	//Customer Alert Message ( Fraud, Bereavement, Deceased, Collections )
	
	
	
	//Warning while cases in opened state
	public static final String OpenCases_WrapUp_Warning_Alert									=	"Please cancel or submit any open work before wrapping up the interaction";
	public static final String OpenCases_SwitchPackage_Warning_Alert							=	"Please close or submit any open case windows before switching to a different package/account";
	
	
	//Different customer alert message
	
	public static final String CustomerAlert_Fraud												=	"Warning: The Fraud Department have taken actions on to this account. We have left instructions for you to read in the TRIO Care Notes.";
	public static final String CustomerAlert_Collection											=	"Account in Collections Treatment";
	public static final String CustomerAlert_Accessibility										=	"Customer has accessibility requirements, please check Trio for further information.";
	public static final String CustomerAlert_Deceased											=	"A bereavement has been confirmed on this account. Please transfer to Bereavement team";
	public static final String CustomerAlert_Deceased_Suspected									=	"We have received notification there may be a bereavement on this account. Please transfer to Bereavement team.";
	public static final String CustomerAlert_Bereavement										=	"A bereavement has been reported. The Bereavement Team are awaiting updates on this account. Please transfer to Bereavement team.";
	
	
	
	//Cases_Payment's
	
	
	public static final String UnRegistered_card_Warning										=	"There are no cards registered against the selected account for one-off payment. Please capture new card details to take the payment.";
	public static final String Recurring_card_Message											=	"Paying by Direct Debit is the easiest way to pay your bill, and it saves you money too, because there are no additional charges for this kind of payment.";
	public static final String CardHolder_BillingAddress_Warning								=	"Any changes to the fields below will not affect the billing address.";
	public static final String Future_OneOff_Payments_Message									=	"Please advise the customer that the same card can be re-used for future one-off payments against the same TalkTalk Fixed Line account.";
	public static final String AmountToPay_AccountHigherpay_Warning								=	"The payment amount entered is greater than the total outstanding balance for the selected account. Does the customer wish to continue with the payment?";
	public static final String Error_Card_NotSelected											=	"Please Select Card Type";
	public static final String Warning_OtherPaymentMethod										=	"The account selected does not have a recurring payment method set up. Please suggest the customer to set up a direct debit or recurring card payment method to avoid Non-recurring payment method fees";
	
}

