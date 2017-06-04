/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspl_4;

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
        // TODO code application logic here
        
        Collection collection = new Collection();
        
        collection.Add("Element nr 1");
        collection.Add("Element nr 2");
        collection.Add("Element nr 3");
        collection.Add("Element nr 4");
        collection.Add("Element nr 5");
        
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new ShowAsync(collection));
        try{
        Thread.sleep(1000);
        }catch(Exception e){
            System.out.println("nastąpi samozniszczenie");
        }
        collection.Add("Element zaskoczenia");
        executor.execute(new ShowAsync(collection));
        executor.execute(new ShowAsync(collection));
        
        executor.shutdown();
        
    }
    
}
