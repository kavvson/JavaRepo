/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduledexecutor;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author blaszczyk
 */
public class CheckReachable implements Runnable{
    
    
    private final HostsList list;
    
    public  CheckReachable(HostsList list)
    {
        this.list = list;
    }

    @Override
    public void run() {
        
        String hostPattern = "10.0.118.%d";
        
        System.out.println("Tworzę listę obiektór Future");
        List<Future<Host>> futureList = new ArrayList();
        
        System.out.println("Tworzę obiekt wykonawcy z pulą max 254 wątków");
        ExecutorService executor = Executors.newFixedThreadPool(254);
        
        System.out.println("Czyszczę listę");
        list.clear();
        list.setLastCheck(new Date());
        
        try
        {
            System.out.println("Sprawdzam hosty");
            for(int i = 0; i <255; i++)
            {
                String hostAddress = String.format(hostPattern, i);
                Host host = new Host(hostAddress);
                
                futureList.add(executor.submit(new IsReachable(host)));
            }
            
            for(Future<Host> future : futureList)
            {
                Host host = future.get();
                list.add(host);
            }
            
            System.out.println("Zakończono sprawdzanie");
        }
        catch(UnknownHostException | InterruptedException | ExecutionException e)
        {
            System.out.println(String.format("%s, Wystąpił błąd aplikacji", CheckReachable.class));
            System.err.println(e.getLocalizedMessage());
        }
        finally
        {
            executor.shutdown();
        }
    }
    
}
