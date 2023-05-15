/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.ragjc.software.openmarketcommons.domain.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RodAlejo
 */
public class RemoteRepository implements IProductRepository{
    private ProductAccessImplSockets productAccess;
    
    public RemoteRepository(){
        productAccess = new ProductAccessImplSockets();
    }
    
    @Override
    public boolean save(Product newProduct) {
        try {
            return productAccess.save(newProduct);
            
        } catch (Exception ex) {
            Logger.getLogger(RemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public boolean edit(Long id, Product product) {
        try {
            return productAccess.edit(id, product);
            
        } catch (Exception ex) {
            Logger.getLogger(RemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            return productAccess.delete(id);
            
        } catch (Exception ex) {
            Logger.getLogger(RemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Product findById(Long id) {
        try {
            return productAccess.findById(id);
            
        } catch (Exception ex) {
            Logger.getLogger(RemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            return productAccess.findAll();
            
        } catch (Exception ex) {
            Logger.getLogger(RemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
