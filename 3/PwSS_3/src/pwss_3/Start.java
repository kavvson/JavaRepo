/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwss_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author blaszczyk
 */
public class Start {

    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newFixedThreadPool(254);
        List<Future<PingResult>> pingResultsList = new ArrayList<Future<PingResult>>();
        
        for(int i = 1; i < 255; i++)
        {
            try
            {
                String address = String.format("10.0.118.%d", i);
                PingAsync pingAsync = new PingAsync(address);
                
                Future<PingResult> future = executor.submit(pingAsync);
                pingResultsList.add(future);
                
            }
            catch(Exception e)
            {
                
            } 
        }
        
        for(Future<PingResult> future: pingResultsList)
        {
            try
            {
                PingResult pingResult = future.get();
                System.out.println(String.format("Adres: %s - %b", pingResult.getAddress(), pingResult.isReachable()));
            }
            catch(InterruptedException | ExecutionException e)
            {}
            catch(Exception e)
            {}
        }
        
        executor.shutdown();
        
    }
    
}
