package com.example.woj.happynumbers.Rosetta.Experiments;


import com.example.woj.happynumbers.Rosetta.Util.Resources;

/**
 * Created by welli on 26/08/2015.
 */
public class InsertSort {

    public static int DEFAULT_SIZE = 220000; //PRECISA DEFINIR

    public static void execute(int tamanhoArray){
        run(Resources.criarArray(tamanhoArray));
    }

    public static void run(int[] A){
        for(int i = 1; i < A.length; i++){
            int value = A[i];
            int j = i - 1;
            while(j >= 0 && A[j] > value){
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = value;
        }
    }

}
