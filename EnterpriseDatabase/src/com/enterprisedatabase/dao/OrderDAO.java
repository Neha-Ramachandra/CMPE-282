package com.enterprisedatabase.dao;


import com.enterprisedatabase.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDAO
{
    //list all orders
    public List<Order> findAllOrders()
    {
        List<Order> allOrders = new ArrayList<Order>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            PreparedStatement preparedStatement = con.prepareStatement("select * from orders order by orderNumber asc");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order orders = new Order();
                orders.setOrderNumber(resultSet.getInt(1));
                orders.setOrderDate(resultSet.getDate(2));
                orders.setRequiredDate(resultSet.getDate(3));
                orders.setShippedDate(resultSet.getDate(4));
                orders.setStatus(resultSet.getString(5));
                orders.setComments(resultSet.getString(6));
                allOrders.add(orders);
            }
        }
        catch (Exception  e)
        {
            e.printStackTrace();
        }
        return allOrders;
    }

    //list the details of order given the customer id
    public List<Order> findOrderDetailsOfACustomer()
    {
        PreparedStatement preparedStatement;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the customer id whose order details are to be seen");
        Integer customerId = scanner.nextInt();

        List<Order> allOrders = new ArrayList<Order>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();

            ResultSet rs= st.executeQuery("select * from orders where customerNumber="+customerId+" ");

            if(rs.next())
            {
            	while(rs.next())
            	{
            		 preparedStatement = con.prepareStatement("select * from orders where customerNumber="+customerId+" ");
                     preparedStatement.execute();
                     Order orders = new Order();
                     orders.setOrderNumber(rs.getInt(1));
                     orders.setOrderDate(rs.getDate(2));
                     orders.setRequiredDate(rs.getDate(3));
                     orders.setShippedDate(rs.getDate(4));
                     orders.setStatus(rs.getString(5));

                     allOrders.add(orders);
            	}
            }
            else
            {
                System.out.println("Customer you entered has not placed any order!");
            }

        }

        catch (Exception  e)
        {
            System.out.println("Invalid input");
        }
        return allOrders;
    }

    //update the status of the order
    public void updateOrderDetails()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the order Id which is to be updated");
        Integer orderNumberToBeUpdated = scanner.nextInt();

        scanner.nextLine();

        System.out.println("enter the status to be changed");
        String orderStatusToBeChanged = scanner.nextLine();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();

            ResultSet rs= st.executeQuery("select * from orders where orderNumber =" + orderNumberToBeUpdated + "");
            //checking for existance of user entered pin

            String updateQuery = "UPDATE orders SET status = ?"
                    + " WHERE orderNumber = ? ";
            if(rs.next()){
                PreparedStatement ps = con.prepareStatement(updateQuery);
                ps.setString(1, orderStatusToBeChanged);
                ps.setInt(2, orderNumberToBeUpdated);

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
