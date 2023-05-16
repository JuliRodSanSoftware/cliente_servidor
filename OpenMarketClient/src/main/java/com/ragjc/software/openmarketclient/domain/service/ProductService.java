/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.domain.service;

import com.ragjc.software.openmarketclient.access.IProductRepository;
import com.ragjc.software.openmarketclient.presentation.GUIProductsFind;
import com.ragjc.software.openmarketcommons.domain.Product;
import java.util.ArrayList;
import java.util.List;
import reloj.frameworkobsobs.Observado;
import reloj.frameworkobsobs.Observador;

/**
 *
 * @author RodAlejo
 */
public class ProductService extends Observado{
    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private IProductRepository repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductRepository
     */
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }


    public boolean saveProduct(String name, String description) {
        
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }
        boolean respuesta = repository.save(newProduct);
        this.notificar();
        return respuesta ;
        

    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();

        return products;
    }
    
    public Product findProductById(Long id){
        return repository.findById(id);
    }
    
    public List<Product> findProductByName(String name){
        return repository.findByName(name);
    }
    
    public boolean deleteProduct(Long id){
        boolean result;
        result = repository.delete(id);
        this.notificar();
        return result;
    }

    public boolean editProduct(Long productId, Product prod) {
        
        //Validate product
        if (prod == null || prod.getName().isBlank() ) {
            return false;
        }
        this.notificar();
        return repository.edit(productId, prod);
    }

    public void addObservador(GUIProductsFind instance) {
        this.addObservador(new Observador() {
            @Override
            public void actualizar() {
                instance.actualizar();
            }
        });
    }
}
