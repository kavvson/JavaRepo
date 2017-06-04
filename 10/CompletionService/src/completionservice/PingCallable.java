/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package completionservice;

import java.net.InetAddress;
import java.util.concurrent.Callable;

/**
 *
 * @author blaszczyk
 */
public class PingCallable implements Callable<PingResult> {

    private String address;
    private int timeout; 
    
    public PingCallable(String address, int timeout) {
        this.address = address;
        this.timeout = timeout;
    }
    
    @Override
    public PingResult call() throws Exception {
        //Utworzenie adresu hosta 
        InetAddress inetAddress = InetAddress.getByName(address);
        //Sprawdzenie dostępności hosta w sieci
        boolean reachable = inetAddress.isReachable(timeout);
        //Zwrócenie obiektu reprezentującego rezultat pingowania
        return new PingResult(address, reachable);
        
    }
    
}
