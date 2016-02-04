package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 08/07/15.
 */
public class Tower {

    public static int DEFAULT_NUMBER = 32;
    public static short JAVA = 1;
    public static short JS = 2;


    public static void execute(short tipo, int numero){
        if(tipo == JS)
            new Tower().moveJS(numero, "1", "3", "2");
        else
            new Tower().moveJAVA(numero, 1, 3, 2);
    }

    Tower() {
       // this.moveJAVA(n, from, to, via);
       // this.moveJS(n,"A","B","C");
    }

    private void moveJAVA(int n, int from, int to, int via) {
        if (n == 1) {
          //  System.out.println("Move disk from pole " + from + " to pole " + to);
        } else {
            moveJAVA(n - 1, from, via, to);
            moveJAVA(1, from, to, via);
            moveJAVA(n - 1, via, to, from);
        }
    }

    private void moveJS(int n, String a, String b, String c){
        if (n > 0) {
            moveJS(n - 1, a, c, b);
          //  System.out.println("Move disk from " + a + " to " + c);
            moveJS(n - 1, b, a, c);
        }
    }
}
