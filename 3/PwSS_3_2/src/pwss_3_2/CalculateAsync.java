/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwss_3_2;

import java.util.concurrent.Callable;

/**
 *
 * @author blaszczyk
 */
public class CalculateAsync implements Callable<Integer>{

    int value;

    public CalculateAsync(int value) {
        this.value = value;
    }
    
    @Override
    public Integer call() throws Exception {
       
        System.out.println("Rozpoczynam obiczenia w wątku");
        int x = 1;
        for(int i = 2; i <= this.value; i ++)
        {
            x = x*i;
        }
        System.out.println("Zakończono wykonywanie obliczeń w wątku");
        return x;
        
        
    }
    
}
