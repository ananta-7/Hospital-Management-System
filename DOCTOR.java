package HOSPITAL_PROJECT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DOCTOR {
	private static final PreparedStatement PreparedStaement = null;
	private static Connection connection;
	private Scanner scanner;
	 
	public DOCTOR(Connection connection,Scanner scanner ) {
		this.connection=connection;
		
	}
	
	public void viewdoctor() {
		String query="select*from doctor";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			System.out.println("patient: ");
			System.out.println("+------------+---------------+----------------+ ");
			System.out.println("| doctor ID |   NAME         |    specilization|");
			System.out.println("+------------+---------------+-----------------+");
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
			
				String apecilization=resultSet.getString("specilization");
				System.out.printf("|%-12s|%-17s|%-18s|\n id,name,specilization");
				System.out.println("+------------+------------+-----------------+");
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean getdoctorById(int id) {
	String query="SELECT* FROM doctors WHERE id=? ";
	
	try {
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		PreparedStaement.setInt(1,id);
		ResultSet resultSet=preparedStatement.executeQuery();
		if(resultSet.next()) {
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
