package com.example.woj.happynumbers.Rosetta.Experiments;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

/**
 * Created by welli on 26/08/2015.
 */
public class ShellSort {

    public static int DEFAULT_SIZE = 40000000;

    public static void execute(int tamanhoArray){

            shell(Resources.criarArray(tamanhoArray));
    }

    public static void shell(int[] a) {
        int increment = a.length / 2;
        while (increment > 0) {
            for (int i = increment; i < a.length; i++) {
                int j = i;
                int temp = a[i];
                while (j >= increment && a[j - increment] > temp) {
                    a[j] = a[j - increment];
                    j = j - increment;
                }
                a[j] = temp;
            }
            if (increment == 2) {
                increment = 1;
            } else {
                increment *= (5.0 / 11);
            }
        }
    }

}
