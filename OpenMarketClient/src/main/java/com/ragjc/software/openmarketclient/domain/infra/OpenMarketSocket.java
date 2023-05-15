/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.domain.infra;

import com.ragjc.software.openmarketcommons.infra.Utilities;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RodAlejo
 */
public class OpenMarketSocket {
     private java.net.Socket socket = null;
    /**
     * Permite leer la recibir la respuesta del socket
     */
    private Scanner input;
    /**
     * Permite enviar una solicitud por el socket
     */
    private PrintStream output;
    /**
     * Ip del Server Socket
     */
    private final String IP_SERVER = Utilities.loadProperty("server.ip");
    /**
     * Puerto del server socket
     */
    private final int PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
    
    
    public String sendRequest(String requestJson) throws IOException {
        String response = "";
        input = new Scanner(socket.getInputStream());
        output = new PrintStream(socket.getOutputStream());
        output.flush();
        input.reset();

        Logger.getLogger(OpenMarketSocket.class.getName()).log(Level.INFO, "Lo que se le envia al: ("+requestJson+")");
           
        // Enviar la solicitud
        output.println(requestJson);

        // Procesa la respuesta
        if (input.hasNextLine()) {
            response = input.nextLine();
        }
        Logger.getLogger(OpenMarketSocket.class.getName()).log(Level.INFO, "Lo que se lee del servidor: ("+response+")");
                
        return response;
    }
    
     /**
     * Cierra los flujos input y output
     */
    public void closeStream() {
        output.close();
        input.close();
    }

    /**
     * Desconectar el socket
     */
    public void disconnect() {
        closeStream();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(OpenMarketSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Conectar o abrir un socket
     *
     * @throws IOException error de entrada y salida
     */
    public void connect(){
         try {
             socket = new java.net.Socket(IP_SERVER, PORT);
         } catch (IOException ex) {
             Logger.getLogger(OpenMarketSocket.class.getName()).log(Level.SEVERE, null, ex);
         }
        Logger.getLogger("SocketClient").log(Level.INFO, "Socket establecido");
    }
    
}
