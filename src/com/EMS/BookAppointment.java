package com.EMS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.Engine.LoadEnvironment;
import com.Utils.Reusables;

public class BookAppointment {

	public static String BookAppt(int dateTo,String postcode) throws Exception{
		String Date = Reusables.getdateFormat("yyyy-MM-dd", dateTo);
		String CurrDate = Reusables.getdateFormat("yyyy-MM-dd", 0);
		
		File file = new File(System.getProperty("user.dir")+"\\ProvisioningTemplates\\BookAppointment.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", Appointment = "";
		while((line = reader.readLine()) != null) 
			
			
			Appointment += line + "\r\n";
		reader.close();
		
		Appointment = Appointment.replaceAll("M_CURRDATE", CurrDate);
		Appointment = Appointment.replaceAll("M_DATE", Date);
		Appointment = Appointment.replaceAll("M_ENV", LoadEnvironment.ENV);
		Appointment = Appointment.replaceAll("M_env",LoadEnvironment.ENV );
		Appointment = Appointment.replaceAll("M_emm_hostname",LoadEnvironment.EMM_HOSTNAME);
		Appointment = Appointment.replaceAll("M_emm_port",LoadEnvironment.EMM_PORT );
		Appointment = Appointment.replaceAll("M_emm_username",LoadEnvironment.EMM_USERNAME );
		Appointment = Appointment.replaceAll("M_emm_password",LoadEnvironment.EMM_PASSWORD );
		Appointment = Appointment.replaceAll("M_Postcode",postcode );
		
		
		FileWriter writer = new FileWriter(System.getProperty("user.dir")+"\\ProvisioningUpdates\\Appointment.txt");
		writer.write(Appointment);writer.flush();writer.close();
		
		String response = MessageTester.MessageTester_test_Synchronous(System.getProperty("user.dir")+"\\ProvisioningUpdates\\Appointment.txt");
		System.out.println(" Book Appointment Request sent sucessfully");
		
		return response;
		
				
	}
}
