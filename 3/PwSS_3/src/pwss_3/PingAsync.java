/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwss_3;

import java.net.InetAddress;
import java.util.concurrent.Callable;

public class PingAsync implements Callable<PingResult>{

    private String address;
    
    public PingAsync(String address)
    {
        this.address = address;
    }
    
    @Override
    public PingResult call() throws Exception {      
        PingResult pingResult = new PingResult();
        try
        {
            InetAddress inetAddress = InetAddress.getByName(address);

            boolean reachable = inetAddress.isReachable(1000);

            pingResult.setReachable(reachable);
            pingResult.setAddress(address);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pingResult;
    }
}
