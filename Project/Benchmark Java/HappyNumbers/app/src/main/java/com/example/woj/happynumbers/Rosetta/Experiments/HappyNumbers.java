package com.example.woj.happynumbers.Rosetta.Experiments;

import java.util.HashSet;

/**
 * Created by woj on 03/08/15.
 */
public class HappyNumbers {

    public static long DEFAULT_NUMBER = 500000;

    public static void execute(long numberArgs) {

        for(long num = 1,count = 0;count<numberArgs;num++){
            if(happy(num)){
                //System.out.println(num);
                count++;
            }
        }
    }

    private static boolean happy(long number){
        long m = 0;
        int digit = 0;
        HashSet<Long> cycle = new HashSet<Long>();
        while(number != 1 && cycle.add(number)){
            m = 0;
            while(number > 0){
                digit = (int)(number % 10);
                m += digit*digit;
                number /= 10;
            }
            number = m;
        }
        return number == 1;
    }
}
