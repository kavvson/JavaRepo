/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.JavaxSecurityAuthKerberosAccess;

/**
 *
 * @author blaszczyk
 */
public class CollectionLock {
    
    List<String> list = new ArrayList();
    
    Lock lock = new ReentrantLock();
    
    public void Show()
    {
        lock.lock();
        try
        {
            for(String s : list)
            {
                try
                {
                    System.out.println(s);
                    Thread.sleep(1000);
                }
                catch(Exception e)
                {}
            }
        }catch(Exception e)
        {}
        finally
        {
         lock.unlock();
        }
    }
    
    public void Add(String s)
    {
        lock.lock();
        try
        {
            System.out.println(String.format("Dodano element do listy: %s", s));
            list.add(s);
        }
        catch(Exception e)
        {
        }
        finally
        {
            lock.unlock();
        }
    }
    
}
