/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package completionservice;
/**
 *
 * @author blaszczyk
 */
public class PingResult {

    private String address;
    private boolean reachable;
    
    public PingResult(String address, boolean reachable) {
        this.address = address;
        this.reachable = reachable;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }
    

}
