package com.example.woj.happynumbers.Rosetta.Experiments;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

/**
 * Created by welli on 26/08/2015.
 */
public class GnomeSort {


    public static int DEFAULT_SIZE = 180000;

    public static void execute(int tamanhoArray){
            run(Resources.criarArray(tamanhoArray));
    }

    public static void run(int[] a)
    {
        int i=1;
        int j=2;

        while(i < a.length) {
            if ( a[i-1] <= a[i] ) {
                i = j; j++;
            } else {
                int tmp = a[i-1];
                a[i-1] = a[i];
                a[i--] = tmp;
                i = (i==0) ? j++ : i;
            }
        }
    }

}
