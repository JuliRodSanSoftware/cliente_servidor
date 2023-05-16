/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.serversocket.loadbalancerserver.logic;

/**
 *
 * @author RodAlejo
 */
public class MeasureTime {
    private static long start, end;
    
    public static void startTime(){
        start =  System.currentTimeMillis();
    }
    
    public static void endTime(){
        end =  System.currentTimeMillis();
    }
    
    public static void elapsedTime(){
        long elapsed = end - start;;
        System.out.println("Tiempo transcurrido: " + elapsed + " milisegundos");
    }
    
    
}
