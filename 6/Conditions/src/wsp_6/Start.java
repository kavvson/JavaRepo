/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsp_6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author blaszczyk
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutorService executorOdbiorcy = Executors.newFixedThreadPool(1);
        ExecutorService executordostawcy = Executors.newFixedThreadPool(3);
        
        Magazyn magazyn = new Magazyn();
        
        try
        {
            executorOdbiorcy.execute(new Odbiorca(magazyn));
            executordostawcy.execute(new Dostawca(magazyn));
            executordostawcy.execute(new Dostawca(magazyn));
            executordostawcy.execute(new Dostawca(magazyn));
        }
        catch(Exception e)
        {}
        finally
        {
            executorOdbiorcy.shutdown();
            executordostawcy.shutdown();
        }
       
    }
    
}
