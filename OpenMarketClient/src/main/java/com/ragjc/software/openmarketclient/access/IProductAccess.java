/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.ragjc.software.openmarketcommons.domain.Product;
import java.util.List;

/**
 *
 * @author RodAlejo
 */
public interface IProductAccess {
    boolean save(Product newProduct) throws Exception;
    
    boolean edit(Long id, Product product) throws Exception;
    
    boolean delete(Long id) throws Exception;
    
    List<Product> findByName(String name) throws Exception;

    Product findById(Long id) throws Exception;
    
    List<Product> findAll() throws Exception;
}
