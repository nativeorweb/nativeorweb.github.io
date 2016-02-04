package com.example.woj.happynumbers.Rosetta.Experiments;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

/**
 * Created by welli on 25/08/2015.
 */
public class BubbleSort {

    public static int DEFAULT_LOOP = 35000;
    private static String[] ARRAY;

    public static void execute(int loop){
//        String[] baseList = {"Q","W","E","R","T","Y"};
//        int baseListSize = baseList.length;
//
//        ARRAY = new String[loop*baseListSize];
//        for (int i = 0; i < loop*baseListSize; i++) {
//            ARRAY[i] = baseList[i%baseListSize];
//        }
        bubbleSort(Resources.criarArrayString(loop));
    }


    public static <E extends Comparable<? super E>> void bubbleSort(E[] comparable) {
        boolean changed = false;
        do {
            changed = false;
            for (int a = 0; a < comparable.length - 1; a++) {
                if (comparable[a].compareTo(comparable[a + 1]) > 0) {
                    E tmp = comparable[a];
                    comparable[a] = comparable[a + 1];
                    comparable[a + 1] = tmp;
                    changed = true;
                }
            }
        } while (changed);
    }
}
