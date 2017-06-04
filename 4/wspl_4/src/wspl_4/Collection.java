/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspl_4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author u108-11
 */
public class Collection {
    
    List<String> list = new ArrayList();
    Object lock = new Object(); //blokowanko
    
    public void Show(){ //jak public synchronized void - jeden po drugim
        
        synchronized(lock){
        for(String s : list){
            try{
                System.out.println(s);
                Thread.sleep(1000);
            }catch(Exception e){ 
                System.out.println(";<");
            }
            
        }
    }
    }
    public void Add(String s){
        synchronized(lock){
        System.out.println(String.format("Dodano element do listy: %s",s));
        list.add(s);
        }
    }
    
}
