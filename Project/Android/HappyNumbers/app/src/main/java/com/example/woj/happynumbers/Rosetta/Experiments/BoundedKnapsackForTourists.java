package com.example.woj.happynumbers.Rosetta.Experiments;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by woj on 14/08/15.
 */
public class BoundedKnapsackForTourists {


    public static int DEFAULT_SIZE = 70000;
    public static int DEFAULT_LOOP = 13;

    public static void execute(int loop,int sizeKnapsackParam) {

        for (int i = 0; i < loop; i++) {
            new BoundedKnapsackForTourists(sizeKnapsackParam);
        }
    }

    public BoundedKnapsackForTourists(int sizeKnapsackParam) {
        BoundedKnapsack bok = new BoundedKnapsack(sizeKnapsackParam); // 400 dkg = 400 dag = 4 kg

        // making the list of items that you want to bring
        bok.add("map", 900, 150, 1);
        bok.add("compass", 1300, 35, 1);
        bok.add("water", 15300, 200, 3);
        bok.add("sandwich", 5000, 160, 2);
        bok.add("glucose", 1500, 60, 2);
        bok.add("tin", 6800, 45, 3);
        bok.add("banana", 2700, 60, 3);
        bok.add("apple", 3900, 40, 3);
        bok.add("cheese", 2300, 30, 1);
        bok.add("beer", 5200, 10, 3);
        bok.add("suntan cream", 1100, 70, 1);
        bok.add("camera", 3200, 30, 1);
        bok.add("t-shirt", 2400, 15, 2);
        bok.add("trousers", 4800, 10, 2);
        bok.add("umbrella", 7300, 40, 1);
        bok.add("waterproof trousers", 4200, 70, 1);
        bok.add("waterproof overclothes", 4300, 75, 1);
        bok.add("note-case", 2200, 80, 1);
        bok.add("sunglasses", 700, 20, 1);
        bok.add("towel", 1800, 12, 2);
        bok.add("socks", 400, 50, 1);
        bok.add("book", 3000, 10, 2);

        bok.add("suntan cream", 1600, 35, 3);
        bok.add("camera", 5600, 70, 3);
        bok.add("t-shirt", 1500, 89, 3);
        bok.add("trousers", 25000, 78, 3);
        bok.add("umbrella", 2500, 18, 3);
        bok.add("waterproof trousers", 8900, 15, 3);
        bok.add("waterproof overclothes", 20000, 70, 3);
        bok.add("note-case", 1500, 13, 3);
        bok.add("sunglasses", 900, 22, 3);
        bok.add("towel", 8000, 42, 3);
        bok.add("socks", 800, 12, 3);
        bok.add("book", 3500, 200, 3);

        bok.add("suntan cream", 860, 89, 3);
        bok.add("camera", 760, 70, 3);
        bok.add("t-shirt", 2500, 89, 3);
        bok.add("trousers", 9800, 78, 3);
        bok.add("umbrella", 500, 18, 3);
        bok.add("waterproof trousers", 7600, 15, 3);
        bok.add("waterproof overclothes", 14000, 70, 3);
        bok.add("note-case", 6500, 13, 3);
        bok.add("sunglasses", 1400, 22, 3);
        bok.add("towel", 6500, 42, 3);
        bok.add("socks", 3200, 12, 3);
        bok.add("book", 5600, 200, 3);

        // calculate the solution:
        List<ItemZeroOne> itemList = bok.calcSolution();

        // write out the solution in the standard output
        if (bok.isCalculated()) {
            NumberFormat nf = NumberFormat.getInstance();

            System.out.println(
                    "Maximal weight           = " +
                            nf.format(bok.getMaxWeight() / 100.0) + " kg"
            );
            System.out.println(
                    "Total weight of solution = " +
                            nf.format(bok.getSolutionWeight() / 100.0) + " kg"
            );
            System.out.println(
                    "Total value              = " +
                            bok.getProfit()
            );
            System.out.println();
            System.out.println(
                    "You can carry te following materials " +
                            "in the knapsack:"
            );
            for (ItemZeroOne item : itemList) {
                if (item.getInKnapsack() > 0) {
                    System.out.format(
                            "%1$-10s %2$-23s %3$-3s %4$-5s %5$-15s \n",
                            item.getInKnapsack() + " unit(s) ",
                            item.getName(),
                            item.getInKnapsack() * item.getWeight(), "dag  ",
                            "(value = " + item.getInKnapsack() * item.getValue() + ")"
                    );
                }
            }
        } else {
            System.out.println(
                    "The problem is not solved. " +
                            "Maybe you gave wrong data."
            );
        }

    }

} // class


class BoundedKnapsack extends ZeroOneKnapsack {
    public BoundedKnapsack() {
    }

    public BoundedKnapsack(int _maxWeight) {
        setMaxWeight(_maxWeight);
    }

    public BoundedKnapsack(List<ItemZeroOne> _itemList) {
        setItemList(_itemList);
    }

    public BoundedKnapsack(List<ItemZeroOne> _itemList, int _maxWeight) {
        setItemList(_itemList);
        setMaxWeight(_maxWeight);
    }

    @Override
    public List<ItemZeroOne> calcSolution() {
        int n = itemList.size();

        // add items to the list, if bounding > 1
        for (int i = 0; i < n; i++) {
            ItemZeroOne item = itemList.get(i);
            if (item.getBounding() > 1) {
                for (int j = 1; j < item.getBounding(); j++) {
                    add(item.getName(), item.getWeight(), item.getValue());
                }
            }
        }

        super.calcSolution();

        // delete the added items, and increase the original items
        while (itemList.size() > n) {
            ItemZeroOne lastItem = itemList.get(itemList.size() - 1);
            if (lastItem.getInKnapsack() == 1) {
                for (int i = 0; i < n; i++) {
                    ItemZeroOne iH = itemList.get(i);
                    if (lastItem.getName().equals(iH.getName())) {
                        iH.setInKnapsack(1 + iH.getInKnapsack());
                        break;
                    }
                }
            }
            itemList.remove(itemList.size() - 1);
        }

        return itemList;
    }

    // add an item to the item list
    public void add(String name, int weight, int value, int bounding) {
        if (name.equals(""))
            name = "" + (itemList.size() + 1);
        itemList.add(new ItemZeroOne(name, weight, value, bounding));
        setInitialStateForCalculation();
    }
} // class