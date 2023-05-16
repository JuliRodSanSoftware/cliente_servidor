package co.edu.unicauca.openmarket.infra.tcpip;


import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import co.unicauca.strategyserver.infra.ServerHandler;
import com.ragjc.software.openmarketcommons.infra.Protocol;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ragjc.software.openmarketcommons.infra.JsonError;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rodalejo
 */
public class OpenMarketHandler  extends ServerHandler{
    private static ProductService service;
    
    public OpenMarketHandler(){
        
    }

    @Override
    public String processRequest(String requestJson) {
        Gson gson = new Gson();  
        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response="";
        switch (protocolRequest.getResource()) {
            case "product":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un customer
                    response = processGetProduct(protocolRequest);
                }

                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un customer    
                    response = Boolean.toString(processPostCustomer(protocolRequest));

                }
                
                if (protocolRequest.getAction().equals("put")) {
                    // Agregar un customer    
                    response = Boolean.toString(processPutCustomer(protocolRequest));

                }
                
                if (protocolRequest.getAction().equals("delete")) {
                    // Agregar un customer    
                    response = Boolean.toString(processDeleteCustomer(protocolRequest));

                }
                
                break;
                
            case "products":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un customer
                    response = processGetProducts();
                }
                break;
        }
        return response;

    }
    
    
    private String processGetProduct(Protocol protocolRequest) {
        // Extraer la cedula del primer parámetro
        String id = protocolRequest.getParameters().get(0).getValue();
        Product product = service.findProductById(Long.parseLong(id));
        if (product == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(product);
        }
    }
    
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Producto no encontrado. ID no existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }
    
    private boolean processPostCustomer(Protocol protocolRequest) {
        Product product = new Product();
        product.setName(protocolRequest.getParameters().get(0).getValue());
        product.setDescription(protocolRequest.getParameters().get(1).getValue());
        boolean bandera = getService().saveProduct(product.getName(), product.getDescription());
        return bandera;
    }
    
    private boolean processPutCustomer(Protocol protocolRequest) {
        Product product = new Product();
        Long id = 0L;
        id  = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());
        boolean bandera = getService().editProduct(id, product);
        return bandera;
    }
    
    
    private boolean processDeleteCustomer(Protocol protocolRequest) {
        Long id = 0L;
        id  = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        boolean bandera = getService().deleteProduct(id);
        return bandera;
    }
    
    
    public ProductService getService() {
        return service;
    }


    public void setService(ProductService service) {
        this.service = service;
    } 

    private String processGetProducts() {
    
        return new Gson().toJson(service.findAllProducts());
    }
    
}
