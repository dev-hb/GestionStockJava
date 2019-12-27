/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import java.util.*;

/**
 *
 * @author virus00x
 */
public class Program {
    public static void main(String[] args){
        ProductDAOIMPL dao = new ProductDAOIMPL();
        /*Product p = new Product(1,"mac book" , 12000);
        dao.create(p);*/
        
        List<Product> products = dao.findAll();
        for(Product pp : products){
            System.out.println(" id : " + pp.getId());
            System.out.println(" Designation : " + pp.getDesignation());
            System.out.println(" Prix : " + pp.getPrix());
            System.out.println("--------------------------------");
        }
    }
}
