/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomnumbergenerator;

import java.io.FileWriter;
import java.util.concurrent.Callable;

/**
 *
 * @author blaszczyk
 */
public class Writer implements Callable<Boolean> {

    private Buffer buffer;
    
    public Writer(Buffer buffer)
    {
        this.buffer = buffer;
    }


    @Override
    public Boolean call() throws Exception {
        FileWriter writer = new FileWriter("file.txt", true);
        
        for(int i = 0; i<10;i++)
        {
            System.out.println(String.format("Wpisanie danych do pliku %d", i));
            int[] buff = buffer.ReturnFromBuffer();
            
            for(int x = 0; x < buff.length; x++)
            {
                int data = buff[x];
                writer.write(String.valueOf(data));
            }
        }
        
        writer.flush();
        writer.close();

        return true;
    }
    
}
