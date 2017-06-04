/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomnumbergenerator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author blaszczyk
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ExecutorService generators = Executors.newFixedThreadPool(10);
        ExecutorService writer = Executors.newSingleThreadExecutor();
        
        Buffer buff = new Buffer();
        
        for(int i = 0; i <10; i++)
        {
            generators.execute(new Generator(buff));
        }
        
        Future<Boolean> done = writer.submit(new Writer(buff));
        done.get();
        
        generators.shutdownNow();
        writer.shutdownNow();
        
        if(!generators.awaitTermination(10, TimeUnit.SECONDS))
        {
            System.out.println("Czekam na zakończenie wątków");
            System.out.println("Przerwanie działania systemu");
            System.exit(0);
        }
        
        System.out.println("Do widzenia");
        
    }
    
}
