/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ragjc.software.openmarketclient;

import com.ragjc.software.openmarketclient.access.Factory;
import com.ragjc.software.openmarketclient.access.IProductRepository;
import com.ragjc.software.openmarketclient.domain.service.ProductService;
import com.ragjc.software.openmarketclient.presentation.*;

/**
 *
 * @author RodAlejo
 */
public class OpenMarketClient {

    public static void main(String[] args) {
        IProductRepository repository = Factory.getInstance().getRepository("remote");
        ProductService productService = new ProductService(repository);
        
        GUIProducts instance = new GUIProducts(productService);
        instance.setVisible(true);
        GUIProductsFind instance2 = new GUIProductsFind(null,false,productService);
        instance2.setVisible(true);
        productService.addObservador(instance2);
    }
}
