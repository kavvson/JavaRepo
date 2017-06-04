/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsp_6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u108-11
 */
public class Magazyn {

    //opróżnion jak pełen
    static final int MAXYMALNA_ILOSC_TOWARU = 10;
    int iloscTowaru = 0;

    //zamek magazynu - wszczymujemy do zapełnienia
    Lock blokadaOdbioru = new ReentrantLock();

    //warun da dostawy
    Condition warunekDlaDostawy = blokadaOdbioru.newCondition();
    //warun dla odbioru
    Condition warunekDlaOdbioru = blokadaOdbioru.newCondition();

    public void dostawaTowaru() {

        //użycie await/signal wymaga założenia zamka powiązanego z warunkiem
        blokadaOdbioru.lock();

        try {
            //sprawdzam czy ilość towaru osiągnęła max i blokuje wątek dostawcy
            while (iloscTowaru == MAXYMALNA_ILOSC_TOWARU) {

                System.out.println("Magazyn jest pełny. Czeka na odbiorcę");
                warunekDlaDostawy.await();

            }

            //dodaje towar do magazynu
            System.out.println(String.format("Następuje dostawa towaru. Wątek: %s", Thread.currentThread().getName()));
            System.out.println(String.format("Ilość dostępnego towaru: %d", iloscTowaru++));

            if (iloscTowaru == MAXYMALNA_ILOSC_TOWARU) {
                System.out.println("Odbiorca poinformowany");
                warunekDlaOdbioru.signalAll();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            blokadaOdbioru.unlock();
        }
    }

    public void odbiorTowaru() {

        blokadaOdbioru.lock();
        try {
            while (iloscTowaru != MAXYMALNA_ILOSC_TOWARU) {
                System.out.println("Oczekiwanie na zapełnienie magazynu");
                warunekDlaOdbioru.await();
            }

            System.out.println("Magazyn został zapełniony. Zwolnienie blokady");

            System.out.println("Odbieranie towaru przez odbiorcę");
            Thread.sleep(2000);

            System.out.println("Odebrano towar");
            iloscTowaru = 0;

            warunekDlaDostawy.signalAll();

        } catch (Exception e) {
        } finally {
            blokadaOdbioru.unlock();
        }
    }

}
