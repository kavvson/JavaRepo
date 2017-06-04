/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsp8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author u108-11
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        final DateFormat dateFormat = new SimpleDateFormat("mm:ss");

        try {
            System.out.println("Runnable");
            System.out.println(String.format("Wątek został wywołany o gdzinie: %s", dateFormat.format(new Date())));
            executor.schedule(new Runnable() {

                @Override
                public void run() {
                    System.out.println(String.format("Wątek zostal wykonaky o godzinie: %s"));
                }
            }, 10, TimeUnit.SECONDS);

            System.out.println("Callable");
            System.out.println(String.format("Wątek został wywołany o gdzinie: %s", dateFormat.format(new Date())));

            ScheduledFuture<String> future = executor.schedule(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    return String.format("Wątek został wykonany o godzinie: %s", dateFormat.format(new Date()));
                }

            }, 10, TimeUnit.SECONDS);

            System.out.println(String.format("scheduleAtFixedRate"));
            System.out.println(String.format("Wątek został wywołany o godzinie: %s", dateFormat.format(new Date())));
            executor.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    System.out.println(String.format("Wątek scheduledAtFixedRate został wykonany o godzinie: %s", dateFormat.format(new Date())));
                }

            }, 0, 5, TimeUnit.SECONDS);

            System.out.println(String.format("scheduledWithFixedDelay"));
            System.out.println(String.format("Wątek został wywołany o godzinie: %s", dateFormat.format(new Date())));
            executor.scheduleWithFixedDelay(new Runnable() {

                @Override
                public void run() {
                    System.out.println(String.format("Wątek scheduledWithFixedDelay został wykonany o godzinie: %s", dateFormat.format(new Date())));
                }

            }, 0, 5, TimeUnit.SECONDS);

            String callableTime = future.get();
            System.out.println(callableTime);

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);

        } catch (Exception e) {

        }
    }
}
