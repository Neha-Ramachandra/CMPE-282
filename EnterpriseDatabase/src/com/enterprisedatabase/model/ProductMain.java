package com.enterprisedatabase.jdbc.main;
import java.sql.SQLException;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import com.enterprisedatabase.jdbc.dao.ProductDAO;
import com.enterprisedatabase.jdbc.to.Product;
 
public class ProductMain {
    public static void main(String[] args) {
        //Get all employees
        getProducts();
        getProduct();
         
    }
 
    private static void getProducts() {
        ProductDAO prodDao = new ProductDAO();
        List<Product> products;
        try {
            products = prodDao.getProducts();
            for (Product product : products) {
                displayProducts(product);
                //System.out.println(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
 
    private static void displayProducts(Product product) {
        System.out.println("Product Code:" + product.getproductId());
        System.out.println("Product Name:" + product.getproductName());
        System.out.println("Product Line:" + product.getproductLine());
        System.out.println("Product Scale:" + product.getproductScale());
        System.out.println("Product Vendor:" + product.getproductVendor());
        System.out.println("Product MSRP:" + product.getproductMSRP());
        System.out.println("Product SP:" + product.getproductSP());
 


        System.out.println();
    }
    
    
 
	private static void getProduct() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Product Code:");
         
        try {
            String productId = (br.readLine());
            ProductDAO productDao = new ProductDAO();
            Product products = productDao.getProduct(productId);
            if(products != null)
                displayaProducts(products);
            else
                System.out.println("No Product with code: " + productId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    private static void displayaProducts(Product products) {
    	 System.out.println("Product Code:" + products.getproductId());
         System.out.println("Product Name:" + products.getproductName());
         System.out.println("Product Line:" + products.getproductLine());
         System.out.println("Product Scale:" + products.getproductScale());
         System.out.println("Product Vendor:" + products.getproductVendor());
         System.out.println("Product MSRP:" + products.getproductMSRP());
         System.out.println("Product SP:" + products.getproductSP());
  

        System.out.println();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
