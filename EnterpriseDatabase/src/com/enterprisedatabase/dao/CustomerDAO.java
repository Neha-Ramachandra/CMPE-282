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


public class CustomerDAO {

	//list all customers
	public List<Customer> findAllCustomers()
	{
		List<Customer> allCustomers = new ArrayList<Customer>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con.prepareStatement("select * from customers order by customerName asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Customer customers = new Customer();
				customers.setCustomerNumber(resultSet.getInt(1));
				customers.setCity(resultSet.getString(8));
                customers.setCustomerName(resultSet.getString(2));
                customers.setPhone(resultSet.getString(5));
                customers.setAddressLine1(resultSet.getString(6));
                customers.setCity(resultSet.getString(8));
                customers.setCountry(resultSet.getString(11));
                customers.setPostalCode(resultSet.getString(10));
                customers.setPhone(resultSet.getString(5));
                customers.setCreditLimit(resultSet.getDouble(13));
				allCustomers.add(customers);
			}
		} 
		catch (Exception  e) 
		{
			 e.printStackTrace();
		}
		return allCustomers;
	}

	//find customers served by an employee
	public List<Customer> findEmployeesOfCustomers()
	{
		PreparedStatement preparedStatement;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the employee id");
       Integer employeeId = scanner.nextInt();

		List<Customer> allCustomers = new ArrayList<Customer>();
		
	        try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();

	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

	            Statement st = con.createStatement();

				//checks whether the employee id given by the is valid
	            ResultSet rs= st.executeQuery("select * from customers where salesRepEmployeeNumber="+employeeId+" ");

	            if(rs.next()){
	            	while(rs.next())
	            	{
	            		preparedStatement = con.prepareStatement("select * from customers where salesRepEmployeeNumber="+employeeId+" ");
	            		preparedStatement.execute();
	            		Customer customers = new Customer();
	            		customers.setCustomerNumber(rs.getInt(1));
	            		customers.setCustomerName(rs.getString(2));
	            		customers.setPhone(rs.getString(5));
	            		allCustomers.add(customers);
	            	}
	            }
	            else
	            {
	            	System.out.println("Employee Id you entered has not served to any of our existing customer!");
	            }
	        }
	        catch (Exception  e)
	        {
	            System.out.println("Invalid input");
	        }
		return allCustomers;
	}

	//delete customer details
	public void deleteACustomer()
	{
		PreparedStatement preparedStatement;
		Scanner scanner = new Scanner(System.in);
        System.out.println("enter the customer id to be deleted");
        Integer customerNumberToBeDeleted = scanner.nextInt();
        
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

		 Statement st = con.createStatement();
		 
		ResultSet rs= st.executeQuery("select * from customers where customerNumber ="+customerNumberToBeDeleted+"");
				
			if(rs.next()){
				preparedStatement = con.prepareStatement("delete from customers where customerNumber="+customerNumberToBeDeleted+" ");
				preparedStatement.execute();
				preparedStatement.executeUpdate();
				System.out.println("Record is deleted!");
			} 
			else 
			{
		     System.out.println("Customer Id you entered does not match the records!");   
		    }			
	}
		
		catch (Exception  e) 
		{
			System.out.println("Foreign key constraints;you cannot delete this customer record");
		}
	}

	//updates the customer's city
	public void updateCustomerCity()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("enter the customer Id whose city is to be updated");
        Integer customerNumberToBeUpdated = scanner.nextInt();
        
        scanner.nextLine();
        
        System.out.println("enter the city to be updated");
        String cityToBeDeleted = scanner.nextLine();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();
   		 
    		ResultSet rs= st.executeQuery("select * from customers where customerNumber ="+customerNumberToBeUpdated+"");
    		//checking for existance of user entered pin
    		
    		 String updateQuery = "UPDATE customers SET city = ?"
                     + " WHERE customerNumber = ? ";
    			if(rs.next()){
    				PreparedStatement ps = con.prepareStatement(updateQuery);
    				ps.setString(1, cityToBeDeleted);
    	            ps.setInt(2, customerNumberToBeUpdated);
    	           
    	            ps.executeUpdate();
    				
    				System.out.println("Record is updated!");
    			}
    			else 
    			{
    		     System.out.println("Customer Id you entered does not match the records!");   
    		    }           
        }

        catch (Exception  e)
        {
           System.out.println("Foreign key constraints;you cannot update this customer record");
        }
    }
}