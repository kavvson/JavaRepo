/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsp_6;

/**
 *
 * @author u108-11
 */
public class Dostawca implements Runnable{
    
    private Magazyn magazyn;
    
    public Dostawca(Magazyn magazyn){
        this.magazyn = magazyn;
    }

    @Override
    public void run() {
        while(true){
            try{
                magazyn.dostawaTowaru();
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
