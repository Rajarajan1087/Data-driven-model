/**
 * 
 */
package com.SharedModules;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.Engine.SeleniumSetup;

/**
 * @author 661317
 *
 */
public class RandomGenerator extends SeleniumSetup{

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

	public static String randomCLI( int len ) {
		int validate = 1;
		final String AB = "0123456789";
		Random rnd = new Random();
		StringBuilder sb  = new StringBuilder( len );
		sb.append( "01" );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

		String CLI = sb.toString();


		if(validate==1){
			return CLI;
		}else{
			randomCLI(9);
			return CLI;
		}
	}
	public static String randomCPWNRef( int len ) {
		int validate = 1;
		final String AB = "0123456789";
		Random rnd = new Random();
		StringBuilder sb  = new StringBuilder( len );
		sb.append( "1" );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

		String CPWN = sb.toString();
		try {

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

	public static String GenerateRandomDOB() throws ParseException{

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal=Calendar.getInstance();
		String Str_date1="01-01-1950 02:10:15";
		String Str_date2="01-01-1985 02:10:20";

		cal.setTime(formatter.parse(Str_date1));
		Long value1 = cal.getTimeInMillis();

		cal.setTime(formatter.parse(Str_date2));
		Long value2 = cal.getTimeInMillis();

		long value3 = (long)(value1 + Math.random()*(value2 - value1));
		cal.setTimeInMillis(value3);
		String[] DOB	=	formatter.format(cal.getTime()).split(" ");
		return DOB[0];
	}
	public static String GenarateRandomAlphabaets(){
		try{
			for(int i=0;i<10;i++){
				String Str_RandomString1 = RandomStringUtils.randomAlphabetic(10).substring(0,5);
				String Str_RandomString2 = RandomStringUtils.randomAlphabetic(10).substring(6, 10);
				return  Str_RandomString1.toUpperCase()+Str_RandomString2.toUpperCase();
			}
		}catch(NullPointerException npe){

		}catch(Exception e){

		}
		return null;
	}

}


