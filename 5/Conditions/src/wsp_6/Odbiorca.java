/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsp_6;

/**
 *
 * @author blaszczyk
 */
public class Odbiorca implements Runnable{

    private Magazyn magazyn;
    
    public Odbiorca(Magazyn magazyn)
    {
        this.magazyn = magazyn;
    }
    
    @Override
    public void run() {
       while(true)
       {
           try
           {
               magazyn.odbiorTowaru();
               Thread.sleep(1000);
           }
           catch(Exception e)
           {
                e.printStackTrace();
           }
       }
    }
    
}
