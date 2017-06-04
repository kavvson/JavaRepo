/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspl_2;

import java.net.InetAddress;
import static java.net.InetAddress.getByName;
import java.net.UnknownHostException;

/**
 *
 * @author u108-11
 */
public class PingAsync implements Runnable{
    
    private String address = null;
    
    public PingAsync(String address){
        this.address = address;
    }

    @Override
    public void run() {
        try{
            if(this.address != null){
                InetAddress inetAddress = InetAddress.getByName(this.address);
                boolean isReachable = inetAddress.isReachable(500);
                
                String isReachableMessage = isReachable ? "Dostępny" : "Niedostępny";
                System.out.println(String.format("Host: %s jest %s", this.address, isReachableMessage));
            }
        }catch(UnknownHostException e){
            e.printStackTrace();    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
    
    

