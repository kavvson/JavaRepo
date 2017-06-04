/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspl_4;

/**
 *
 * @author u108-11
 */
public class ShowAsync implements Runnable {
    
    private Collection collection;
    
    public ShowAsync(Collection collection){
        this.collection = collection;
    }

    @Override
    public void run() {
        collection.Show();
        }
    
    
    
}
