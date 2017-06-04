/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwss_3;

/**
 *
 * @author blaszczyk
 */
public class PingResult {
    
    private String address;
    private boolean reachable;
    
    public String getAddress()
    {
        return address;
    }
    
    public boolean isReachable()
    {
        return reachable;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public void setReachable(boolean reachable)
    {
        this.reachable = reachable;
    }
}
