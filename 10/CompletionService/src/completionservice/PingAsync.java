/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blaszczyk
 */
public class PingAsync {
    
    public void pingWithCompletionService()
    {
        //Utworzenie instancji wykonawcy | Dowolna ilość zadań w puli
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Utworzenie instancji completionService
        CompletionService completionService = new ExecutorCompletionService<>(executorService);
        
        try
        {
            //Zadania wykonywane dla 254 hostów w sieci
            for(int i = 0; i< 255; i++)
            {
                //Określenie czasu oczekiwania dla hostów | 1-25: 10000; 26-100: 5000; 101-254: 2000
                int timeout = i < 26 ? 10000 : i < 101 ? 5000 : 2000;
                //Utworzenie adresu hosta
                String address = String.format("10.0.118.%d", i);
                //Rozpoczęcie wykonywania zadania
                completionService.submit(new PingCallable(address, timeout));
            }
            
            long startTime = System.currentTimeMillis();
            //Odebrania wszystkich 254 rozpoczętych zadań
            for(int i = 0; i< 255; i++)
            {
                //Oczekiwanie na rezultat z zadania które skończyło wykonywać operacje jako pierwsze
                Future<PingResult> result = completionService.take();
                //Pobranie wyniku wykonanego zadania
                PingResult pingResult = result.get();
                //Wyświetlrnie rezultatu
                System.out.println(String.format("HOST: %s, Dostępność: %b, Czas wykonania %d",
                        pingResult.getAddress(), 
                        pingResult.isReachable(),
                        System.currentTimeMillis() - startTime));
            }
        
        }
        catch(Exception e)
        {}
        finally
        {
            //Polecenie do zamknięcia wykonawcy
            executorService.shutdown();
            //Oczekiwanie na zamknięcie wykonawcy
            try {
                if(!executorService.awaitTermination(10000, TimeUnit.MILLISECONDS))
                {}
            } catch (InterruptedException ex) {
                Logger.getLogger(PingAsync.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void pingWithExecutorService()
    {
        List<Future<PingResult>> results = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        try
        {
            for(int i = 0; i< 255; i++)
            {
                int timeout = i < 26 ? 10000 : i < 101 ? 5000 : 2000;
                String address = String.format("10.0.118.%d", i);
                results.add(executorService.submit(new PingCallable(address, timeout)));
            }
            
            long startTime = System.currentTimeMillis();
            for(Future<PingResult> result : results)
            {
                PingResult pingResult = result.get();
                System.out.println(String.format("HOST: %s, Dostępność: %b, Czas wykonania %d",
                        pingResult.getAddress(), 
                        pingResult.isReachable(),
                        System.currentTimeMillis() - startTime));
            }
        }
        catch(Exception e)
        {}
        finally
        {
            //Polecenie do zamknięcia wykonawcy
            executorService.shutdown();
            //Oczekiwanie na zamknięcie wykonawcy
            try {
                if(!executorService.awaitTermination(10000, TimeUnit.MILLISECONDS))
                {}
            } catch (InterruptedException ex) {
                Logger.getLogger(PingAsync.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
