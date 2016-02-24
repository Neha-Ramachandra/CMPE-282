package com.enterprisedatabase.jdbc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;

import com.enterprisedatabase.jdbc.db.ConnectionDB;
import com.enterprisedatabases.jdbc.db.DbUtil;

import com.enterprisedatabase.jdbc.to.Product;



public class ProductDAO {

	private Connection connection;
	private Statement statement;
	
	
	public ProductDAO() { }
	
	// List all the products
	 
    public List<Product> getProducts() throws SQLException {
        String query = "SELECT * FROM products";
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        ResultSet rs = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                product = new Product();
                
				
                product.setproductId(rs.getString("productCode"));
                product.setproductName(rs.getString("productName"));
                product.setproductLine(rs.getString("productLine"));
                product.setproductScale(rs.getString("productScale"));
                product.setproductVendor(rs.getString("productVendor"));
                product.setproductDescription(rs.getString("productDescription"));
                product.setquantityInStock(rs.getInt("quantityInStock"));
                product.setproductSP(rs.getDouble("buyPrice"));
                product.setproductMSRP(rs.getDouble("MSRP"));
                
                //add each employee to the list
                list.add(product);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }
    
    
   // Search for a product using Product ID
    
    public Product getProduct(String productId) throws SQLException {
        String query = "SELECT * FROM products WHERE productCode= " + productId;
        ResultSet rss = null;
        Product products = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.createStatement();
            rss = statement.executeQuery(query);
            if (rss.next()) {
                products = new Product();
                products.setproductId(rss.getString("productCode"));
                products.setproductName(rss.getString("productName"));
                products.setproductLine(rss.getString("productLine"));
                products.setproductScale(rss.getString("productScale"));
                products.setproductVendor(rss.getString("productVendor"));
                products.setproductDescription(rss.getString("productDescription"));
                products.setquantityInStock(rss.getInt("quantityInStock"));
                products.setproductSP(rss.getDouble("buyPrice"));
                products.setproductMSRP(rss.getDouble("MSRP"));
            }
        } finally {
            DbUtil.close(rss);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return products;
    }
   
   
    // Update a product's SP
	
    public void updateProductSP()
    {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter Product ID: ");
        Double prodUpdate = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Enter SP: ");
        Double prodSP = scanner.nextDouble();
        scanner.nextLine();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/classicmodels", "root", "1234");

            Statement st = con.createStatement();
   		 
    		ResultSet set= st.executeQuery("select * from products where productCode ="+prodUpdate+"");
    		
    		
    		 String updateQuery = "UPDATE products SET buyPrice = ?"
                     + " WHERE productCode= ? ";
    			if(set.next()){
    				PreparedStatement ps = con.prepareStatement(updateQuery);
    				ps.setDouble(1, prodSP);
    	            ps.setDouble(2, prodUpdate);
    	           
    	            ps.executeUpdate();
    				
    				System.out.println("Record is updated!");
    			}
    			else 
    			{
    		     System.out.println("Product ID does not exist");   
    		    }           
        }

        catch (Exception  e)
        {
           System.out.println("Cannot update record");
        }
    }
	
	
    // Update a product's MSRP
  
    public void updateProductMSRP()
    {
       
		Scanner scan= new Scanner(System.in);
        
        System.out.println("Enter Product ID: ");
        Double prodUp = scan.nextDouble();
        scan.nextLine();
        
        System.out.println("Enter SP: ");
        Double prodMSRP = scan.nextDouble();
        scan.nextLine();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3305/classicmodels", "root", "1234");

            Statement stat = c.createStatement();
   		 
    		ResultSet set= stat.executeQuery("select * from products where productCode ="+prodUp+"");
    		
    		
    		 String prodQuery = "UPDATE products SET MSRP = ?"
                     + " WHERE productCode= ? ";
    			if(set.next()){
    				PreparedStatement ps = c.prepareStatement(prodQuery);
    				ps.setDouble(1, prodMSRP);
    	            ps.setDouble(2, prodUp);
    	           
    	            ps.executeUpdate();
    				
    				System.out.println("Record is updated!");
    			}
    			else 
    			{
    		     System.out.println("Product ID does not exist");   
    		    }           
        }

        catch (Exception  e)
        {
           System.out.println("Cannot update record");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
	




