/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.access.ProductRepository;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import java.util.Scanner;

/**
 *
 * @author RodAlejo
 */
public class OpenMarketServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        OpenMarketHandler myHandler = new OpenMarketHandler();
        myHandler.setService(new ProductService(new ProductRepository()));
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
    
}
