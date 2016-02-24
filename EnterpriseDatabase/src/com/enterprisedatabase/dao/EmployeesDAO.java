package com.enterprisedatabase.dao;

import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.enterprisedatabase.model.Customer;
import com.enterprisedatabase.model.Employee;
import com.enterprisedatabase.model.Office;


public class EmployeesDAO {

	//list all employees
	public List<Employee> findAllemployees()
	{
		List<Employee> allEmployees = new ArrayList<Employee>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "mahek", "mahek");

			PreparedStatement preparedStatement = con.prepareStatement("select * from employees order by EmployeeName asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee employees = new Employee();
				Office office = new Office();
				employees.setEmployeeNumber(resultSet.getInt(1));
				employees.setLastName(resultSet.getString(8));
                employees.setFirstName(resultSet.getString(2));
                employees.setExtension(resultSet.getString(5));
                employees.setEmail(resultSet.getString(6));
                employees.setOffice(office);
                employees.setJobTitle(resultSet.getString(10));
				allEmployees.add(employees);
			}
		} 
		catch (Exception  e) 
		{
			 e.printStackTrace();
		}
		return allEmployees;
	}


	//List employees by office code
	
	public List<Employee> findemployeesbyofficecode()
	{
		PreparedStatement preparedStatement;
	  Scanner scanner = new Scanner(System.in);
	  System.out.println("Enter the officecode");
	 Integer officeCode = scanner.nextInt();

		List<Employee> allEmployees = new ArrayList<Employee>();
		
	      try {
	          Class.forName("com.mysql.jdbc.Driver").newInstance();

	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "mahek", "mahek");

	          Statement st = con.createStatement();
	          ResultSet rs= st.executeQuery("select * from employees where officeCode ="+officeCode+" ");

	          if(rs.next()){
	          	while(rs.next())
	          	{
	          		preparedStatement = con.prepareStatement("select * from employees where officeCode="+officeCode+" ");
	          		preparedStatement.execute();
	          		Employee employees = new Employee();
	          		employees.setEmployeeNumber(rs.getInt(1));
	          		employees.setFirstName(rs.getString(10));
	          		employees.setLastName(rs.getString(10));
	          		allEmployees.add(employees);
	          	}
	          }
	          else
	          {
	          	System.out.println("Can't find any employee with given office code");
	          }
	      }
	      catch (Exception  e)
	      {
	          System.out.println("Invalid input");
	      }
		return allEmployees;
	}
	
	
//delete employee details

	public void deleteEmployee()
	{
		PreparedStatement preparedStatement;
		Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the employee id to be deleted");
    Integer employeeNumber = scanner.nextInt();
    
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "mahek", "mahek");

		 Statement st = con.createStatement();
		 
		ResultSet rs= st.executeQuery("select * from employees where employeeNumber ="+employeeNumber+"");
				
			if(rs.next()){
				preparedStatement = con.prepareStatement("delete from employees where employeeNumber="+employeeNumber+" ");
				preparedStatement.execute();
				preparedStatement.executeUpdate();
				System.out.println("Record is deleted.");
			} 
			else 
			{
		     System.out.println("Employee Id you entered does not exist.");   
		    }			
	}
		
		catch (Exception  e) 
		{
			System.out.println("Sorry you cannot delete this employee record");
		}
	}
}
	
