package com.enterprisedatabase.jdbc.main;
import java.sql.SQLException;
import java.util.List;



import com.enterprisedatabase.jdbc.dao.ProductLineDAO;

import com.enterprisedatabase.jdbc.to.ProductLine;

public class ProductLineMain {

	public static void main(String[] args) {
    
        getProductLine();
    
         
    }
 
    private static void getProductLine() {
        ProductLineDAO prodlineDao = new ProductLineDAO();
        List<ProductLine> productlines;
        try {
            productlines = prodlineDao.getProductLine();
            for (ProductLine prod : productlines) {
                displayProductLine(prod);
                //System.out.println(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
 
    private static void displayProductLine(ProductLine prod) {
        System.out.println("Product Line:" + prod.getproductLine());
        System.out.println("Text Description:" + prod.gettextDescription());
        System.out.println("HTML Description:" + prod.gethtmlDescription());
     
        System.out.println();
    }
    
	
	
	
	
	
	
}
