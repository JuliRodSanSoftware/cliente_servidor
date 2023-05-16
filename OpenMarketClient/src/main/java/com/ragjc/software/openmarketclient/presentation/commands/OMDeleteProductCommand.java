/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.presentation.commands;

import com.ragjc.software.openmarketclient.domain.service.ProductService;
import com.ragjc.software.openmarketcommons.domain.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RodAlejo
 */
public class OMDeleteProductCommand extends OMCommand{
    
    private Product pP;
    private ProductService pS;
    private List<Product> removedProducts = new ArrayList<>();
    boolean result=false;
    public OMDeleteProductCommand(Product pP, ProductService pS){
        this.pP = pP;
        this.pS = pS;
    }

    @Override
    public void make() {
        Product removedProduct = pS.findProductById(pP.getProductId());
        result = pS.deleteProduct(pP.getProductId());
        removedProducts.add(removedProduct);
        
    }

    @Override
    public void unmake() {
        List<Product> products = removedProducts;
        for(Product each: products){
            if(each.getProductId().equals(pP.getProductId())){
                System.out.println("Recuperacion de borrado: "+removedProducts.size());
                result = pS.saveProduct(each.getName(), each.getDescription() );
                
            }
        }
        
    }
    
    public boolean result(){
        return result;
    }
    
}
