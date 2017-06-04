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
public class Start {

    public static void main(String[] args) {
        PingAsync pingAsync = new PingAsync();
        
        System.out.println("----------pingWithExecutorService--------------");
        pingAsync.pingWithExecutorService();
        
        System.out.println("----------pingWithCompletionService--------------");
        pingAsync.pingWithCompletionService();
    }
    
}
