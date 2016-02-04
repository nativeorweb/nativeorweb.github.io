package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 19/08/15.
 */
public class SeqNonSquares {


    public static long DEFAULT_NUMBER = 3500000000L;


//    public static void nonsqr(long n) {
 //       long args = n + (long)Math.round(Math.sqrt(n));
 //   }

    public static void nonsqr(long n) {

    }

    public static void execute(long number) {
        for (long i = 1; i < number; i++) {
            long args = i + (long) Math.floor(1 / 2 + Math.sqrt(i));
        }
    }
}