package com.enterprisedatabase.jdbc.main;

import java.sql.SQLException;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.enterprisedatabase.jdbc.dao.OrderDetailDAO;

import com.enterprisedatabase.jdbc.to.OrderDetail;



public class OrderLineMain {

	public static void main(String[] args) {
  
		getOrderDetails();
		getODetail();
    }
	
	
	private static void getOrderDetails() {
        OrderDetailDAO odd = new OrderDetailDAO();
        List<OrderDetail> order;
        try {
            order = odd.getOrderDetails();
            for (OrderDetail orders : order) {
                displayOrderDetails(orders);
                //System.out.println(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
 
    private static void displayOrderDetails(OrderDetail order) {
        System.out.println("Order Number:" + order.getorderNumber());
        System.out.println("Quantity Ordered:" + order.getquantityOrdered());
        System.out.println("Price Each:" + order.getpriceEach());
        System.out.println("Order Line Number:" + order.getorderLineNumber());
        System.out.println();
    }
    
    private static void getODetail() {
        BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Order Number:");
         
        try {
            Integer ordernumber = Integer.parseInt(breader.readLine());
            OrderDetailDAO ord = new OrderDetailDAO();
            OrderDetail odl = ord.getODetail(ordernumber);
            if(odl!= null)
                displayaOrderDetail(odl);
            else
                System.out.println("No order details available for Order Number: " + ord);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    private static void displayaOrderDetail(OrderDetail ord) {
    	System.out.println("Order Number:" + ord.getorderNumber());
        System.out.println("Quantity Ordered:" + ord.getquantityOrdered());
        System.out.println("Price Each:" + ord.getpriceEach());
        System.out.println("Order Line Number:" + ord.getorderLineNumber());
        System.out.println();
  

        System.out.println();
    }

    
  
	
	
	
	
}
