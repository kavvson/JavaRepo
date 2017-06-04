/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomnumbergenerator;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author blaszczyk
 */
public class Generator implements Runnable {

    
    private Buffer buffer;
    
    public Generator(Buffer buffer)
    {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        
       Random rand = new Random();
       do
       {
           int i = rand.nextInt(2000) - 1000; 
           buffer.AddToBuffer(i);
       }while(!Thread.currentThread().isInterrupted());
        
    }
    
}
