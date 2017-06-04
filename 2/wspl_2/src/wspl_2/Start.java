/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspl_2;

/**
 *
 * @author u108-11
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Ping ping = new Ping();
        //ping.start();
        
        ping.startAsync();
        
        
    }
    
}
