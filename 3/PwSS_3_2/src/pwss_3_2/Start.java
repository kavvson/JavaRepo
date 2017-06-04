/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwss_3_2;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author blaszczyk
 */
public class Start {

    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Scanner scanner = new Scanner(System.in);
        
        try
        {
        
            System.out.println("Podaj liczbę 1:");
            int x1 = Integer.parseInt(scanner.nextLine());
        
            System.out.println("Podaj liczbę 2:");
            int x2 = Integer.parseInt(scanner.nextLine());
        
            System.out.println("Podaj liczbę 3:");
            int x3 = Integer.parseInt(scanner.nextLine());
            
            System.out.println("Uruchomienie wątków");
            Future<Integer> cx1 = executor.submit(new CalculateAsync(x1));
            Future<Integer> cx2 = executor.submit(new CalculateAsync(x2));
            Future<Integer> cx3 = executor.submit(new CalculateAsync(x3));
            
            System.out.println("Oczekiwanie na zakończenie działania wątków");
            int sum = cx1.get() + cx2.get() + cx3.get();
            
            System.out.println(String.format("Wynik sumowania: %d", sum));
            
        }catch(NumberFormatException | InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }catch(Exception e)
        {
            
        }
        
        executor.shutdown();
    }
}
