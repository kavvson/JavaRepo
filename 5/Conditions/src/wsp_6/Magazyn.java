/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsp_6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author blaszczyk
 */
public class Magazyn {
    
    static final int MAXYMALNA_ILOSC_TOWARU = 10;
    int iloscTowaru = 0;
    
    //Zamek dla magazynu
    Lock blokadaMagazynu = new ReentrantLock();
    
    //Warunek dla dostawy
    Condition blokadaDostawy = blokadaMagazynu.newCondition();
    
    //Warunek dla odbioru
    Condition blokadaOdbioru = blokadaMagazynu.newCondition();
    
    public void dostawaTowaru()
    {
        //Użycie await/signal wymaga założenia zamka powiązanego z warunkiem
        blokadaMagazynu.lock();
        try
        {
            //sprawdzam czy ilość towaru osiągneła max i blokuje wątek dostawcy
            while(iloscTowaru == MAXYMALNA_ILOSC_TOWARU)
            {
                System.out.println("Magazyn jest pełny. Oczekiwanie na odbiorcę");
                blokadaDostawy.await();
            }

            //Dodaję towar do magazynu
            System.out.println(String.format("Następuje dostawa towaru. Wątek %s",Thread.currentThread().getName()));
            System.out.println(String.format("Ilość dostępnego towaru: %d", ++iloscTowaru));
            
            //sprawdzam czy ilość towaru osiągnęła max i informuję o tym odbiorcę
            if(iloscTowaru == MAXYMALNA_ILOSC_TOWARU)
            {
                System.out.println("Odbiorca został poinformowany");
                blokadaOdbioru.signalAll();
            }
            
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            //Odblokowuję magazyn
            blokadaMagazynu.unlock();
        }
        
    }
    
    public void odbiorTowaru()
    {
        //Użycie await/signal wymaga założenia zamka powiązanego z warunkiem
        blokadaMagazynu.lock();
        try
        {
            //sprawdzam czy ilość towaru nie osiągneła max i blokuje wątek odbiorcy
            while(iloscTowaru != MAXYMALNA_ILOSC_TOWARU)
            {
                System.out.println("Oczekiwanie na zapełnienie magazyny");
                blokadaOdbioru.await();
            }
            
            System.out.println("Magazyn został zapełniony. Nastąpiło zwolnienie blokady.");
            System.out.println("Odbieranie towaru przez odbiorcę ...");
            Thread.sleep(5000);
            System.out.println("Odebrano towar");
            iloscTowaru = 0;
            
            // informuję dostawców że magazyn jest pusty
            blokadaDostawy.signalAll();
            
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            //Odblokowuję magazyn
            blokadaMagazynu.unlock();
        }
    }
    
    
}
