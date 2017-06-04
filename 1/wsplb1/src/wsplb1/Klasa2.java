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
public class Klasa2 implements Runnable{
    
    public void DoWork() throws InterruptedException{
            
        int i = 0;
        for(;;){
                System.out.println(String.format("Wątek %s, Klasa 2: %d", Thread.currentThread().getName(),i++));
                //Thread.sleep(100); //uśpienie wątu - spowalnia operacje lub interwał
                if(Thread.interrupted()){ //teraz kill'em wąt 1
                    return;
                }
            }
        }

    @Override
    public void run() {
         try{
            DoWork();
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
