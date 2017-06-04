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
 * @author u108-11
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // dawej lock miast synchronized
        //await wszczymuje, notify informuje, że już można dalej robić - na kodzie zsynchronizwanem
        
        ExecutorService executorOdbiorcy = Executors.newFixedThreadPool(1);
        ExecutorService executorDostawcy = Executors.newFixedThreadPool(3);
        
        Magazyn magazyn = new Magazyn();
        
        try{
            executorOdbiorcy.execute(new Odbiorca(magazyn));
            executorDostawcy.execute(new Dostawca(magazyn));
            executorDostawcy.execute(new Dostawca(magazyn));
            executorDostawcy.execute(new Dostawca(magazyn));
        }catch(Exception e){
        }finally{
            executorOdbiorcy.shutdown();
            executorDostawcy.shutdown();
    }
    }
    
}
