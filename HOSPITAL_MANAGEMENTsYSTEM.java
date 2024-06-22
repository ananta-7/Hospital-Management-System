package HOSPITAL_PROJECT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HOSPITAL_MANAGEMENTsYSTEM {
private static final String url="jdbc:mysql://localhost:3306/?user=root";
private static final String username="sys as sysdba";
private static final String password="17032005A";
public static void main(String[]args) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	Scanner scanner=new Scanner(System.in);
	try {
		Connection connection =DriverManager.getConnection(url,username,password);
		project patient=new project(connection,scanner);
		DOCTOR doctor=new DOCTOR(connection,scanner);
		while(true) {
			System.out.println("hospital management System");
			System.out.println("1.Add patient");
			System.out.println("2.view patient");
			System.out.println("3.view Doctor");
			System.out.println("4.Book Appointment");
			System.out.println("5.Exit");
			System.out.println("enter your choice");
			int choice=scanner.nextInt();
			
			switch(choice) {
			case 1:
				//add patient
				patient.addpatient();
				System.out.println();
			case 2:
				//view patient
				patient.viewPatient();
				System.out.println();
			case 3:
				//view doctors
				doctor.viewdoctor();
				System.out.println();
				
			case 4:
				//book appointment
				bookAppointment(patient,doctor,connection,scanner);
			case 5:
				return;
			default:
				System.out.println("enter your choice");
				}
			}
	}catch(SQLException e) {
		e.printStackTrace();
		
	}
}
public static void bookAppointment(project patient,DOCTOR doctor, Connection connection,Scanner scanner) {
	System.out.println("enter your patient id");
	int patientId=scanner.nextInt();
	System.out.println("enter doctor id:");
	int doctorId=scanner.nextInt();
	System.out.println("enter appointment date(YYYY-MM-DD):");
	String appointmentDate=scanner.next();
		if(project.getPatientById(patientId) && DOCTOR.getdoctorById(doctorId)) {
			if(checkDOCTORAvailability(doctorId,appointmentDate,connection)) {
				String appointmentQUERY="INSERT INTO appointment(patient_id,doctor_id,appointment_date)VALUES(?,?,?)";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(appointmentQUERY);
				preparedStatement.setInt(1,patientId);
				preparedStatement.setInt(2,doctorId);
				preparedStatement.setString(3,appointmentDate);
				int rowsAffected=preparedStatement.executeUpdate();
				if(rowsAffected>0) {
					System.out.println("appointment book:");
				}
				else {
					System.out.println("failed to appointment");
				}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		else {
			System.out.println("either doctor or patient doesn't exist!!!");
		}	

}
	else {
		System.out.println("doctor not available on this date!!");
	}
	}
	

public static boolean checkDOCTORAvailability(int doctorId,String appointmentDate,Connection connection) {
	String query="SELECT COUNT(*)FROM apppointment WHERE doctor_id=?AND appointment_date=?";
	try {
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1,doctorId);
		preparedStatement.setString(2,appointmentDate);
		ResultSet resultset=preparedStatement.executeQuery();
		if(resultset.next()) {
			int count=resultset.getInt(1);
			return true;
		}else {
			return false;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
}
}
