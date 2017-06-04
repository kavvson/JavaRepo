/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsplb1;

/**
 *
 * @author u108-11
 */
public class Wsp_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Klasa1 k1 = new Klasa1();
        Klasa2 k2 = new Klasa2();
        
        try{
           Thread thread1 = new Thread(k1);
           Thread thread2 = new Thread(k2);
           
           thread1.setPriority(10);
           thread2.setPriority(1);
           
           thread1.start();
           thread2.start();
           
           Thread.sleep(5000); //zakończy się po 5 sec
           
           thread1.interrupt(); //oznaczon do przerwania, ale nie zabity
           thread2.interrupt();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    System.out.println("Koniec wątku main");
    }
}