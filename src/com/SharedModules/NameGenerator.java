/**
 * 
 */
package com.SharedModules;

import java.util.Random;

import com.Engine.Reporter;

/**
 * @author 661317
 *
 */
public class NameGenerator {
	public static Reporter Report;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i;
		for ( i=0;i<=0;i++){
			String fname = randomName(6);
			String lname = randomName(6);
			String CLI = randomCLI(9);
			
			System.out.println("Firstname-->"+fname+"  Lastname-->"+lname+"   CLI-->"+CLI);
		}

	}
	public static String randomName( int len ) {

		final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );

		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

		return sb.toString();
	}

	@SuppressWarnings("static-access")
	public static String randomCLI( int len ) {
		int validate = 1;
		final String AB = "0123456789";
		NewDatabase db = new NewDatabase(Report);
		Random rnd = new Random();
		//String returnCLI = null;
		StringBuilder sb  = new StringBuilder( len );
		sb.append( "01" );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

		String CLI = sb.toString();
		validate = db.getCLIValidate(CLI);
		System.out.println("NewCLI --> "+CLI);
		if(validate==1){
			System.out.println("NewCLI --> "+CLI);
			return CLI;
		}else{
			randomCLI(9);
			System.out.println("NewCLI --> "+CLI);
			return CLI;
		}
	}
	@SuppressWarnings("static-access")
	public static String randomMobileno( int len ) {
		int validate = 1;
		final String AB = "0723456789";
		NewDatabase db = new NewDatabase(Report);
		Random rnd = new Random();
		//String returnCLI = null;
		StringBuilder sb  = new StringBuilder( len );
		sb.append( "07" );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

		String Mobileno = sb.toString();
		validate = db.getMobilenoValidate(Mobileno);
		System.out.println("Mobile No --> "+Mobileno);
		if(validate==1){
			System.out.println("Mobile No --> "+Mobileno);
			return Mobileno;
		}else{
			randomMobileno(9);
			System.out.println("Mobile No --> "+Mobileno);
			return Mobileno;
		}
	}
	public static String randomCPWNRef( int len ) {
		int validate = 1;
		final String AB = "0123456789";
		NewDatabase db = new NewDatabase(Report);
		Random rnd = new Random();
		//String returnCLI = null;
		StringBuilder sb  = new StringBuilder( len );
		sb.append( "1" );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

		String CPWN = sb.toString();
		try {
			//validate = db.Get_CPWNRef(CPWN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(validate==1){
			return CPWN;
		}else{
			randomCLI(len);
			return CPWN;
		}
	}

	public static String randomNumber( String StartingNumber, int Balancelength ) {

		final String AB = "0123456789";
		Random rnd = new Random();

		StringBuilder sb  = new StringBuilder( Balancelength );
		sb.append( StartingNumber );
		for( int i = 0; i < Balancelength; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

		String CPWN = sb.toString();

		return CPWN;
	}

}




