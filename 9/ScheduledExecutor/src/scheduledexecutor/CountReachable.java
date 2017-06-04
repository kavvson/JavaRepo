/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduledexecutor;

import java.util.Date;

/**
 *
 * @author blaszczyk
 */
public class CountReachable implements Runnable{

    private final HostsList list;
    
    public CountReachable(HostsList list)
    {
        this.list = list;
    }
    
    @Override
    public void run() {
        
        try
        {
            System.out.println("Sprawdzam ilość dostępnych hostów");
            Date date = new Date();
            System.out.println(
                    String.format("%s, Ilość dostępnych hostów: %d, Ostatnie sprawdzenie: %s", 
                            date.toString(), 
                            list.reachableHostsCount(),
                            list.getLastCheck().toString()));
            System.out.println("Zakończono sprawdzanie");
        }
        catch(Exception e)
        {
            System.out.println(String.format("%s, Wystąpił błąd aplikacji", CountReachable.class));
            System.err.println(e.getLocalizedMessage());
        }
        
    }
    
}
