package com.example.woj.happynumbers.Rosetta.Experiments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by welli on 26/08/2015.
 */
public class QuickSort {

    public static int DEFAULT_LOOP = 1; //PRECISA DEFINIR
    public static int DEFAULT_SIZE = 1800000; //PRECISA DEFINIR


    public static void execute(int tamanhoArray){

        ArrayList list = new ArrayList();

        for (int i = 0; i < tamanhoArray; i++) {
            list.add((int) Math.floor(Math.random() * (int) (tamanhoArray *1.5)));
        }

        quickSort(list);

    }

    public static <E extends Comparable<? super E>> List<E> quickSort(List<E> arr) {
        if (!arr.isEmpty()) {
            E pivot = arr.get(0); //This pivot can change to get faster results


            List<E> less = new LinkedList<>();
            List<E> pivotList = new LinkedList<E>();
            List<E> more = new LinkedList<E>();

            // Partition
            for (E i: arr) {
                if (i.compareTo(pivot) < 0)
                    less.add(i);
                else if (i.compareTo(pivot) > 0)
                    more.add(i);
                else
                    pivotList.add(i);
            }

            // Recursively sort sublists
            less = quickSort(less);
            more = quickSort(more);

            // Concatenate results
            less.addAll(pivotList);
            less.addAll(more);
            return less;
        }
        return arr;

    }

}
