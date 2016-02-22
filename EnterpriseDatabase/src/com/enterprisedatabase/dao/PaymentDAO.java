package com.enterprisedatabase.dao;

import com.enterprisedatabase.model.Customer;
import com.enterprisedatabase.model.Order;
import com.enterprisedatabase.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PaymentDAO
{
    public List<Payment> listAllPayment()
    {
        List<Payment> allpayments = new ArrayList<Payment>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            PreparedStatement preparedStatement = con.prepareStatement("select * from payments order by checkNumber asc");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payments = new Payment();
                payments.setAmount(resultSet.getInt(4));
                payments.setPaymentDate(resultSet.getDate(3));
                payments.setCheckNumber(resultSet.getString(2));

                allpayments.add(payments);
            }
        }
        catch (Exception  e)
        {
            e.printStackTrace();
        }
        return allpayments;
    }

    public List<Payment> findOrderDetailsOfACustomer()
    {
        PreparedStatement preparedStatement;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the customer id whose payment information is to be seen");
        Integer customerId = scanner.nextInt();

        List<Payment> allPaymentInfo = new ArrayList<Payment>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();

            ResultSet rs= st.executeQuery("select * from payments where customerNumber="+customerId+" ");

            if(rs.next())
            {
            	 while(rs.next()){
                     preparedStatement = con.prepareStatement("select * from payments where customerNumber="+customerId+" ");
                     preparedStatement.execute();
                     Payment payments = new Payment();
                     payments.setAmount(rs.getInt(4));
                     payments.setPaymentDate(rs.getDate(3));
                     payments.setCheckNumber(rs.getString(2));

                     allPaymentInfo.add(payments);
                 }
            }  
            else
            {
                System.out.println("Customer Id you entered does not have payment history!");
            }

        }

        catch (Exception  e)
        {
            System.out.println("Invalid input");
        }
        return allPaymentInfo;
    }

}
