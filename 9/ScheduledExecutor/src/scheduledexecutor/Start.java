/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduledexecutor;

import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author blaszczyk
 */

public class Start {

    private final static int CHECK_TIME_EXECUTION = 20;
    private final static int COUNT_TIME_EXECUTION = 30;
            
    public static void main(String[] args) 
    {
       
       ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
       HostsList list = new HostsList();
       
       try
       {  
           System.out.println("Uruchomienie wątku do sprawdzania dostępności hostów. Wykonanie co CHECK_TIME_EXECUTION.");
           scheduledExecutor.scheduleWithFixedDelay(new CheckReachable(list), 0, CHECK_TIME_EXECUTION, TimeUnit.SECONDS);
           System.out.println("Uruchomienie wątku do sprawdzania ilości dostępnych hostów. Wykonanie co COUNT_TIME_EXECUTION.");
           scheduledExecutor.scheduleAtFixedRate(new CountReachable(list), 0, COUNT_TIME_EXECUTION, TimeUnit.SECONDS);
           
           String key;
           Scanner scanner = new Scanner(System.in);
           
           System.out.println("Aby zamknąć program należy wcisnąć 'z'.");
           do
           {
               key = scanner.next();
           }while(!"z".equals(key));
           
           System.out.println("Wciśnięto klawisz 'z'. Zamykanie aplikacji. Proszę czekać..");
           
           scheduledExecutor.shutdown();
           
           System.out.println("Czekam na zamknięcie aplikacji");
           if(!scheduledExecutor.awaitTermination(60, TimeUnit.SECONDS))
           {
               scheduledExecutor.shutdownNow();
               System.exit(0);
           } 
       }
       catch(Exception e)
       {
           scheduledExecutor.shutdownNow();
           System.exit(0);
       }
       finally
       {
           System.out.println("Do widzenia");
       }
    }
    
}
