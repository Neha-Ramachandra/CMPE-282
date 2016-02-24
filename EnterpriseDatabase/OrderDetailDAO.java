package com.enterprisedatabase.jdbc.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.enterprisedatabase.jdbc.db.ConnectionDB;
import com.enterprisedatabases.jdbc.db.DbUtil;

import com.enterprisedatabase.jdbc.to.OrderDetail;



public class OrderDetailDAO {

	
	
	private Connection connection;
	private Statement statement;
	
	
	public OrderDetailDAO() { }
	
	// List all the OrderDetails
	 
    public List<OrderDetail> getOrderDetails() throws SQLException {
        String orderD = "SELECT * FROM orderdetails";
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        OrderDetail od = null;
        ResultSet result = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(orderD);
            while (result.next()) {
                od = new OrderDetail();
                
				
                od.setorderNumber(result.getInt("orderNumber"));
                od.setquantityOrdered(result.getInt("quantityOrdered"));
                od.setpriceEach(result.getDouble("priceEach"));
                od.setorderLineNumber(result.getInt("orderLineNumber"));
          
                list.add(od);
            }
        } finally {
            DbUtil.close(result);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }
	
	
// Search for order details using Order Number
    
    public OrderDetail getODetail(int orderNum) throws SQLException {
        String qry = "SELECT * FROM orderdetails WHERE orderNumber= " + orderNum;
        ResultSet reslt = null;
        OrderDetail orderD = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.createStatement();
            reslt = statement.executeQuery(qry);
            if (reslt.next()) {
            	orderD = new OrderDetail();
            	orderD.setorderNumber(reslt.getInt("orderNumber"));
            	orderD.setquantityOrdered(reslt.getInt("quantityOrdered"));
            	orderD.setpriceEach(reslt.getDouble("priceEach"));
            	orderD.setorderLineNumber(reslt.getInt("orderLineNumber"));
            
            }
        } finally {
            DbUtil.close(reslt);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return orderD;
    }
    
    
    
    
    
    
    
    
}
