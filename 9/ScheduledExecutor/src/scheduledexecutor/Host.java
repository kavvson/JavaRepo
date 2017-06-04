/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduledexecutor;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author blaszczyk
 */
public class Host {
    
    private InetAddress address;

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }
    private boolean reachable;
    
    public Host(String host) throws UnknownHostException
    {
        this.address = InetAddress.getByName(host);
    }
    
}
