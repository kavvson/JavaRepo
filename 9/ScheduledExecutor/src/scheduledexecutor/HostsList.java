/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduledexecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author blaszczyk
 */
public class HostsList {
    
    List<Host> list = new ArrayList();
    
    private Date lastCheck;

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }
    
    public void add(Host host)
    {
        list.add(host);
    }
    
    public void clear()
    {
        list.clear();
    }
    
    public int reachableHostsCount()
    {
       int i = 0; 
       for(Host host : list)
       {
           if(host.isReachable())
           {
               i++;
           }
       }
       return i;
    }
    
}
