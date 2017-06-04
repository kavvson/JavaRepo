/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspl_2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author u108-11
 */
public class Ping {
    
    public void start(){
        
        for(int i = 1; i<255;i++){
            try{
                //utworzenie adresu hosta
                String address = String.format("10.0.118.%d",i);
                InetAddress inetAddress = InetAddress.getByName(address);
                
                //sprawdzenie dostępności hosta
                boolean isReachable = inetAddress.isReachable(500);
                String isReachableMessage = isReachable ? "Dostępny" : "Niedostępny";
                
                //wyświetlenie dostępności hosta
                System.out.println(String.format("Host zdalny: %s jest %s", address, isReachableMessage));
            }catch(UnknownHostException e){
                
            }catch(Exception e){
                
            }finally{
            }
        }
        }
    
    public void startAsync(){
        
        ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(254);
        
        for(int i =0; i<255; i++){
            try{
                //utworzenie adresu hosta
                String address = String.format("10.0.118.%d", i);
                
                executor.execute(new PingAsync(address));
                
                //Thread thread = new Thread(new PingAsync(address));
                //thread.start();
            }catch(Exception e){
                
            }finally{
                
            }
        }
        
    }
    
    
    }
    
    
