package com.example.woj.happynumbers.Rosetta.Experiments;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

/**
 * Created by welli on 25/08/2015.
 */
public class CountingSort {

    public static int DEFAULT_SIZE = 45000000;


    public static void execute(int tamanhoArray){

        run(Resources.criarArray(tamanhoArray),0,(tamanhoArray));
    }

    private static void run(int[] array, int min, int max){
        int[] count= new int[max - min + 1];
        for(int number : array){
            count[number - min]++;
        }
        int z= 0;
        for(int i= min;i <= max;i++){
            while(count[i - min] > 0){
                array[z]= i;
                z++;
                count[i - min]--;
            }
        }
    }


}
