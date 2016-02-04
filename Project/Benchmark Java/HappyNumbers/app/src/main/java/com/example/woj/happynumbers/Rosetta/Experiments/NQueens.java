package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 19/08/15.
 */
public class NQueens {

    public static int DEFAULT_LOOP = 4;
    public static int DEFAULT_SIZE = 13;
    private static int[] b;

    static boolean unsafe(int y) {
        int x = b[y];
        for (int i = 1; i <= y; i++) {
            int t = b[y - i];
            if (t == x ||
                    t == x - i ||
                    t == x + i) {
                return true;
            }
        }
        return false;
    }
    public static void putboard(int number) {
        //System.out.println("\n\nSolution " + (++s));
        for (int y = 0; y < number; y++) {
            for (int x = 0; x < number; x++) {
              //  System.out.print((b[y] == x) ? "|Q" : "|_");
            }
        }
    }
    public static void execute(int loop, int size){
        for (int i = 0; i < loop ; i++) {
            run(size);
        }
    }
    public static void run(int number) {
        b = new int[number];
        int y = 0;
        b[0] = -1;
        while (y >= 0) {
            do {
                b[y]++;
            } while ((b[y] < number) && unsafe(y));
            if (b[y] < number) {
                if (y < number-1) {
                    b[++y] = -1;
                } else {
                    putboard(number);
                }
            } else {
                y--;
            }
        }
    }
}