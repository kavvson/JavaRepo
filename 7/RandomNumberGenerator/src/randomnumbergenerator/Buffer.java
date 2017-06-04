/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomnumbergenerator;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author blaszczyk
 */
public class Buffer {
    
    private int[] buffer = new int[5000]; 
    private int position = 0;
    
    Lock lock = new ReentrantLock();
    Condition writeCondition = lock.newCondition();
    Condition readCondition = lock.newCondition();
    
    
    public void AddToBuffer(int number)
    {
        lock.lock();
        try
        {
            while(position == 5000)
            {
                System.out.println("Oczekuje na zwolnienie bufora");
                writeCondition.await();
            }
            
            buffer[position++] = number;
            
            if(position == 5000)
            {
                readCondition.signal();
            }
        }
        catch(Exception e)
        {
           System.out.println(e.getLocalizedMessage()); 
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public int[] ReturnFromBuffer()
    {
        lock.lock();
        try
        {
            while(position < 5000)
            {
                System.out.println("Oczekuje na zapełnienie bufora");
                readCondition.await();
            }
            
            int[] b = buffer;
            buffer = new int[5000];
            position = 0;
            
            System.out.println("Bufor wyczyszczony");
            
            writeCondition.signalAll();

            
            return b;
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        finally
        {
            lock.unlock();
        }
        
    }
}
