package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 06/08/15.
 */
import java.util.Collections;
import java.util.LinkedList;

public class Combinations{

    public static int DEFAULT_NUMBER_FST = 75;
    public static int DEFAULT_NUMBER_SND = 30;

    public static void execute(int first, int second){
        comb(first,second);
       /* Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                comb(DEFAULT_NUMBER_FIRST, DEFAULT_NUMBER_SECOND);
            }
        });
        Thread t2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                comb(DEFAULT_NUMBER_FIRST, DEFAULT_NUMBER_SECOND);
            }
        });
        t1.start();t2.start();

        try {
            t1.join();t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static String bitprint(int u){
        String s= "";
        for(int n= 0;u > 0;++n, u>>= 1)
            if((u & 1) > 0) s+= n + " ";
        return s;
    }

    public static int bitcount(int u){
        int n;
        for(n= 0;u > 0;++n, u&= (u - 1));//Turn the last set bit to a 0
        return n;
    }

    public static LinkedList<String> comb(int c, int n){
        LinkedList<String> s= new LinkedList<String>();
        for(int u= 0;u < 1 << n;u++)
            if(bitcount(u) == c) s.push(bitprint(u));
        Collections.sort(s);
        return s;
    }
}
