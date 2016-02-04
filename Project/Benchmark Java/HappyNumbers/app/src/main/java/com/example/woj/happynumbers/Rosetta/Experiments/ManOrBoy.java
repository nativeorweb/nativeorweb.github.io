package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 19/08/15.
 */
public class ManOrBoy {
    interface Arg {
        public int run();
    }
    public static int DEFAULT_NUMBER = 13;
    public static int LOOP = 35000;


    public static int A(final int k, final Arg x1, final Arg x2,
                        final Arg x3, final Arg x4, final Arg x5) {
        if (k <= 0)
            return x4.run() + x5.run();
        return new Arg() {
            int m = k;
            public int run() {
                m--;
                return A(m, this, x1, x2, x3, x4);
            }
        }.run();
    }
    public static Arg C(final int i) {
        return new Arg() {
            public int run() { return i; }
        };
    }

    public static void execute(int loop, int number) {

        for (int i = 0; i < loop; i++) {
            A(number, C(1), C(-1), C(-1), C(1), C(0));
        }

    }
}