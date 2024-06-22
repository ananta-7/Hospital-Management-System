package HOSPITAL_PROJECT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class project {
	private static final PreparedStatement PreparedStaement = null;
	private static Connection connection;
	private Scanner scanner;
	 
	public project(Connection connection,Scanner scanner ) {
		this.connection=connection;
		this.scanner=scanner;
	}
	public void addpatient(){
		System.out.print("ENTER YOUR PATIET NAME");
		String name=scanner.next();
		System.out.print("enter your age");
		int age=scanner.nextInt();
		System.out.print("Gender");
		String Gender=scanner.next();
		
		
		try {
			String query="INSERT INTO projects(name,age,gender)VALUES(?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			PreparedStaement.setString(1,name);
			PreparedStaement.setInt(2,age);
			PreparedStaement.setString(3,Gender);
			int affectedRows=preparedStatement.executeUpdate();
			if(affectedRows>0) {
				System.out.println("patient add sucessffully!!");			
			}
			else {
				System.out.println("faild to patient add!!!");
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewPatient() {
		String query="select*from patients";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			System.out.println("patient: ");
			System.out.println("+------------+----------------+--------------+------------+");
			System.out.println("| patient ID |NAME            |     AGE      | GENDER     |");
			System.out.println("+------------+----------------+--------------+------------+");
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				int age=resultSet.getInt("age");
				String gender=resultSet.getString("gender");
				System.out.printf("|%-13s|%-17s|%-15s|%-13s|\nid,name,age,gender");
				System.out.println("+------------+----------------+--------------+------------+");
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean getPatientById(int id) {
	String query="SELECT* FROM patients WHERE id=? ";
	
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
