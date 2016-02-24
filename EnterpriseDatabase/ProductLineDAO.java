package com.enterprisedatabase.jdbc.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.enterprisedatabase.jdbc.db.ConnectionDB;
import com.enterprisedatabases.jdbc.db.DbUtil;
import com.enterprisedatabase.jdbc.to.ProductLine;


public class ProductLineDAO {

	
	private Connection connection;
	private Statement statement;
	
	  public List<ProductLine> getProductLine() throws SQLException {
	        String str = "SELECT * FROM productlines";
	        List<ProductLine> pl = new ArrayList<ProductLine>();
	        ProductLine productline = null;
	        ResultSet resSet = null;
	        try {
	            connection = ConnectionDB.getConnection();
	            statement = connection.createStatement();
	            resSet = statement.executeQuery(str);
	            while (resSet.next()) {
	                productline = new ProductLine();
	                
					
	                productline.setproductLine(resSet.getString("productLine"));
	                productline.settextDescription(resSet.getString("textDescription"));
	                productline.sethtmlDescription(resSet.getString("htmlDescription"));
	                pl.add(productline);
	            }
	        } finally {
	            DbUtil.close(resSet);
	            DbUtil.close(statement);
	            DbUtil.close(connection);
	        }
	        return pl;
	    }
	    
	
	
	
	
	
	
	
	
	
	
	
	
}
