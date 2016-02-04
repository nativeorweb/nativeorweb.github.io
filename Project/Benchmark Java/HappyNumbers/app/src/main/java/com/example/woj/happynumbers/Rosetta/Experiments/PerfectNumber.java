package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 19/08/15.
 */
public class PerfectNumber {

    public static int LOOP = 1500000;
    public static int JAVA = 1;
    public static int JS = 2;

    public static boolean isPerfect(int n){
        double sum = 1;
        double sqrt=Math.floor(Math.sqrt(n));

        for (double i = sqrt-1; i > 1; i--) {
            if(n % i == 0){
                sum+= i + n/i;
            }
        }
        if(n % sqrt == 0){
            sum += sqrt + (sqrt*sqrt == n ? 0 : n/sqrt);
        }
        return sum == n;
    }


    public static boolean perf(int n){
        int sum= 0;
        for(int i= 1;i < n;i++){
            if(n % i == 0){
                sum+= i;
            }
        }
        return sum == n;
    }

    public static void execute(int TYPE, int loop){
        if(TYPE == JAVA) {
            for (int i = 0; i < loop; i++) {
                perf(i);
            }
        }
        else{
            for (int i = 0; i < loop; i++) {
                isPerfect(i);
            }
        }
    }

}
