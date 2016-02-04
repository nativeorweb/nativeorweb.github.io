package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 09/08/15.
 */
        import java.text.*;

public class KnapsackUnbounded {

    public static void execute() {
        new KnapsackUnbounded();
    } // main()


    protected ItemUnbounded []  items = {
            new ItemUnbounded("panacea", 3000,  0.3, 0.025),
            new ItemUnbounded("ichor"  , 1800,  0.2, 0.015),
            new ItemUnbounded("gold"   , 2500,  2.0, 0.002),
            new ItemUnbounded("silver"   , 3500,  0.7, 0.022),
            new ItemUnbounded("bronze"   , 500,  0.9, 0.014),
            new ItemUnbounded("lump"   , 5500,  1.5, 0.058),
            new ItemUnbounded("helio"   , 1500,  1.7, 0.032),
            new ItemUnbounded("tulip"   , 2500,  0.7, 0.062)
        //    new Item("clorium"   , 200,  3.0, 0.058),
    };
    protected final int    n = items.length; // the number of items
    protected ItemUnbounded      sack = new ItemUnbounded("sack"   ,    0, 27.0, 0.270);
    protected ItemUnbounded      best = new ItemUnbounded("best"   ,    0,  0.0, 0.000);
    protected int  []  maxIt = new int [n];  // maximum number of items
    protected int  []    iIt = new int [n];  // current indexes of items
    protected int  [] bestAm = new int [n];  // best amounts

    public KnapsackUnbounded() {
        // initializing:
        for (int i = 0; i < n; i++) {
            maxIt [i] = Math.min(
                    (int)(sack.getWeight() / items[i].getWeight()),
                    (int)(sack.getVolume() / items[i].getVolume())
            );
        } // for (i)

        // calc the solution:
        calcWithRecursion(0);

        // Print out the solution:
        NumberFormat nf = NumberFormat.getInstance();
      //  System.out.println("Maximum value achievable is: " + best.getValue());
     //   System.out.print("This is achieved by carrying (one solution): ");
     //   for (int i = 0; i < n; i++) {
      //      System.out.print(bestAm[i] + " " + items[i].getName() + ", ");
      //  }
     //   System.out.println();
      //  System.out.println("The weight to carry is: " + nf.format(best.getWeight()) +
      //                  "   and the volume used is: " + nf.format(best.getVolume())
    //    );

    }

    // calculation the solution with recursion method
    // item : the number of item in the "items" array
    public void calcWithRecursion(int item) {
        for (int i = 0; i <= maxIt[item]; i++) {
            iIt[item] = i;
            if (item < n-1) {
                calcWithRecursion(item+1);
            } else {
                int    currVal = 0;   // current value
                double currWei = 0.0; // current weight
                double currVol = 0.0; // current Volume
                for (int j = 0; j < n; j++) {
                    currVal += iIt[j] * items[j].getValue();
                    currWei += iIt[j] * items[j].getWeight();
                    currVol += iIt[j] * items[j].getVolume();
                }

                if (currVal > best.getValue()
                        &&
                        currWei <= sack.getWeight()
                        &&
                        currVol <= sack.getVolume()
                        )
                {
                    best.setValue (currVal);
                    best.setWeight(currWei);
                    best.setVolume(currVol);
                    for (int j = 0; j < n; j++) bestAm[j] = iIt[j];
                } // if (...)
            } // else
        } // for (i)
    } // calcWithRecursion()

    // the main() function:

} // class


class ItemUnbounded {
    protected String name = "";
    protected int value = 0;
    protected double weight = 0;
    protected double volume = 0;

    public ItemUnbounded() {
    }

    public ItemUnbounded(String name, int value, double weight, double volume) {
        setName(name);
        setValue(value);
        setWeight(weight);
        setVolume(volume);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = Math.max(value, 0);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = Math.max(weight, 0);
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = Math.max(volume, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

} // class