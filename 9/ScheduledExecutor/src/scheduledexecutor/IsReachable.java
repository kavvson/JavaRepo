/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduledexecutor;

import java.util.concurrent.Callable;

/**
 * @author blaszczyk
 */
public class IsReachable implements Callable<Host> 
{
    private final Host host;
    
    public IsReachable(Host host)
    {
        this.host = host;
    }

    @Override
    public Host call() {
        try
        {
            host.setReachable(host.getAddress().isReachable(1000));
            return host;
        }catch(Exception e)
        {
            System.out.println(String.format("%s, Wystąpił błąd aplikacji", IsReachable.class));
            return host;
        }
    }
    
    
}
