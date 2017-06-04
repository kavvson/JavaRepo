/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author blaszczyk
 */
public class Start {

    public static void main(String[] args) {
        
        Collection collection = new Collection();
        
        collection.Add("Element nr 1");
        collection.Add("Element nr 2");
        collection.Add("Element nr 3");
        collection.Add("Element nr 4");
        collection.Add("Element nr 5");
        
        
        
            ExecutorService executor = Executors.newFixedThreadPool(3);
            executor.execute(new ShowAsync(collection));
            try
            {
                Thread.sleep(2000);
            }catch(Exception e){}
            collection.Add("Element 6");
            executor.execute(new ShowAsync(collection));
            executor.execute(new ShowAsync(collection));

            executor.shutdown();
    }
    
}
