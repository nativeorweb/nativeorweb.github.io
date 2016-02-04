package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by welli on 26/08/2015.
 */
import android.os.AsyncTask;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

import java.util.concurrent.CountDownLatch;

public class SleepSort {

    public static int DEFAULT_SIZE = 450; //PRECISA DEFINIR
    public static int DEFAULT_LOOP = 10; //PRECISA DEFINIR
    public static final int DEFAULT_TIME = 10; //PRECISA DEFINIR


    public static boolean itsOver;


    public static void sleepSortAndPrint(int[] nums) {
        final CountDownLatch doneSignal = new CountDownLatch(nums.length);
        for (final int num : nums) {
            new Thread(new Runnable() {
                public void run() {
                    doneSignal.countDown();
                    try {
                        doneSignal.await();

                        //using straight milliseconds produces unpredictable
                        //results with small numbers
                        //using 1000 here gives a nifty demonstration
                        Thread.sleep(num * DEFAULT_TIME);
                        //System.out.println(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        while(doneSignal.getCount() != 0){}
      //  itsOver = true;
      //  System.out.println("acabou");
    }
    public static void execute(int size,int loop) {
        for (int i = 0; i < loop; i++) {
            createAsync(size);
        }
     //   System.out.println("acabou Tudo");
    }

    private static void createAsync(int size) {
     //   itsOver = false;
        new AsyncExec().execute(size);

//        synchronized (SleepSort.class) {
//            while (!itsOver) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}  class AsyncExec extends AsyncTask<Integer, Void, Void> {

    @Override
    protected Void doInBackground(Integer... voids) {
        SleepSort.sleepSortAndPrint(Resources.criarArray(voids[0]));
        return null;
    }
}