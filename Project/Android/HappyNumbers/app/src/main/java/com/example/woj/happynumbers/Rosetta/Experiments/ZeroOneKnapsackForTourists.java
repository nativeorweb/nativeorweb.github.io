package com.example.woj.happynumbers.Rosetta.Experiments;

import java.util.*;

/**
 * Created by woj on 10/08/15.
 */

public class ZeroOneKnapsackForTourists {

    public static int DEFAULT_SIZE = 180000;
    public static int DEFAULT_LOOP = 10;


    public static void execute(int loop,int sizeKnapsack){
        for (int i = 0; i < loop; i++) {
            new ZeroOneKnapsackForTourists(sizeKnapsack);
        }
    }

    public ZeroOneKnapsackForTourists(int sizeKnapsack) {
        ZeroOneKnapsack zok = new ZeroOneKnapsack(sizeKnapsack); // 400 dkg = 400 dag = 4 kg

        // making the list of items that you want to bring
        zok.add("map", 900, 150);
        zok.add("compass", 1300, 35);
        zok.add("water", 15300, 200);
        zok.add("sandwich", 5000, 160);
        zok.add("glucose", 1500, 60);
        zok.add("tin", 6800, 45);
        zok.add("banana", 2700, 60);
        zok.add("apple", 3900, 40);
        zok.add("cheese", 2300, 30);
        zok.add("beer", 5200, 10);
        zok.add("suntan cream", 1100, 70);
        zok.add("camera", 3200, 30);
        zok.add("t-shirt", 2400, 15);
        zok.add("trousers", 4800, 10);
        zok.add("umbrella", 7300, 40);
        zok.add("waterproof trousers", 4200, 70);
        zok.add("waterproof overclothes", 4300, 75);
        zok.add("note-case", 2200, 80);
        zok.add("sunglasses", 700, 20);
        zok.add("towel", 1800, 12);
        zok.add("socks", 400, 50);
        zok.add("book", 3000, 10);

        zok.add("suntan cream", 1600, 35);
        zok.add("camera", 5600, 70);
        zok.add("t-shirt", 1500, 89);
        zok.add("trousers", 25000, 78);
        zok.add("umbrella", 2500, 18);
        zok.add("waterproof trousers", 8900, 15);
        zok.add("waterproof overclothes", 20000, 70);
        zok.add("note-case", 1500, 13);
        zok.add("sunglasses", 900, 22);
        zok.add("towel", 8000, 42);
        zok.add("socks", 800, 12);
        zok.add("book", 3500, 200);

        zok.add("suntan cream", 860, 89);
        zok.add("camera", 760, 70);
        zok.add("t-shirt", 2500, 89);
        zok.add("trousers", 9800, 78);
        zok.add("umbrella", 500, 18);
        zok.add("waterproof trousers", 7600, 15);
        zok.add("waterproof overclothes", 14000, 70);
        zok.add("note-case", 6500, 13);
        zok.add("sunglasses", 1400, 22);
        zok.add("towel", 6500, 42);
        zok.add("socks", 3200, 12);
        zok.add("book", 5600, 200);

        // calculate the solution:
        List<ItemZeroOne> itemList = zok.calcSolution();

        // write out the solution in the standard output
/*        if (zok.isCalculated()) {
            NumberFormat nf = NumberFormat.getInstance();

            System.out.println(
                    "Maximal weight           = " +
                            nf.format(zok.getMaxWeight() / 100.0) + " kg"
            );
            System.out.println(
                    "Total weight of solution = " +
                            nf.format(zok.getSolutionWeight() / 100.0) + " kg"
            );
            System.out.println(
                    "Total value              = " +
                            zok.getProfit()
            );
            System.out.println();
            System.out.println(
                    "You can carry the following materials " +
                            "in the knapsack:"
            );
            for (ItemZeroOne item : itemList) {
                if (item.getInKnapsack() == 1) {
                    System.out.format(
                            "%1$-23s %2$-3s %3$-5s %4$-15s \n",
                            item.getName(),
                            item.getWeight(), "dag  ",
                            "(value = " + item.getValue() + ")"
                    );
                }
            }
        } else {
            System.out.println(
                    "The problem is not solved. " +
                            "Maybe you gave wrong data."
            );
        }*/

    }

} // class

class ZeroOneKnapsack {

    protected List<ItemZeroOne> itemList = new ArrayList<ItemZeroOne>();
    protected int maxWeight = 0;
    protected int solutionWeight = 0;
    protected int profit = 0;
    protected boolean calculated = false;

    public ZeroOneKnapsack(){}

    public ZeroOneKnapsack(int _maxWeight) {
        setMaxWeight(_maxWeight);
    }

    public ZeroOneKnapsack(List<ItemZeroOne> _itemList) {
        setItemList(_itemList);
    }

    public ZeroOneKnapsack(List<ItemZeroOne> _itemList, int _maxWeight) {
        setItemList(_itemList);
        setMaxWeight(_maxWeight);
    }

    // calculte the solution of 0-1 knapsack problem with dynamic method:
    public List<ItemZeroOne> calcSolution() {
        int n = itemList.size();

        setInitialStateForCalculation();
        if (n > 0 && maxWeight > 0) {
            List<List<Integer>> c = new ArrayList<List<Integer>>();
            List<Integer> curr = new ArrayList<Integer>();

            c.add(curr);
            for (int j = 0; j <= maxWeight; j++)
                curr.add(0);
            for (int i = 1; i <= n; i++) {
                List<Integer> prev = curr;
                c.add(curr = new ArrayList<Integer>());
                for (int j = 0; j <= maxWeight; j++) {
                    if (j > 0) {
                        int wH = itemList.get(i - 1).getWeight();
                        curr.add(
                                (wH > j)
                                        ?
                                        prev.get(j)
                                        :
                                        Math.max(
                                                prev.get(j),
                                                itemList.get(i - 1).getValue() + prev.get(j - wH)
                                        )
                        );
                    } else {
                        curr.add(0);
                    }
                } // for (j...)
            } // for (i...)
            profit = curr.get(maxWeight);

            for (int i = n, j = maxWeight; i > 0 && j >= 0; i--) {
                int tempI = c.get(i).get(j);
                int tempI_1 = c.get(i - 1).get(j);
                if (
                        (i == 0 && tempI > 0)
                                ||
                                (i > 0 && tempI != tempI_1)
                        ) {
                    ItemZeroOne iH = itemList.get(i - 1);
                    int wH = iH.getWeight();
                    iH.setInKnapsack(1);
                    j -= wH;
                    solutionWeight += wH;
                }
            } // for()
            calculated = true;
        } // if()
        return itemList;
    }

    // add an item to the item list
    public void add(String name, int weight, int value) {
        if (name.equals(""))
            name = "" + (itemList.size() + 1);
        itemList.add(new ItemZeroOne(name, weight, value));
        setInitialStateForCalculation();
    }

    // add an item to the item list
    public void add(int weight, int value) {
        add("", weight, value); // the name will be "itemList.size() + 1"!
    }

    // remove an item from the item list
    public void remove(String name) {
        for (Iterator<ItemZeroOne> it = itemList.iterator(); it.hasNext(); ) {
            if (name.equals(it.next().getName())) {
                it.remove();
            }
        }
        setInitialStateForCalculation();
    }

    // remove all items from the item list
    public void removeAllItems() {
        itemList.clear();
        setInitialStateForCalculation();
    }

    public int getProfit() {
        if (!calculated)
            calcSolution();
        return profit;
    }

    public int getSolutionWeight() {
        return solutionWeight;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int _maxWeight) {
        maxWeight = Math.max(_maxWeight, 0);
    }

    public void setItemList(List<ItemZeroOne> _itemList) {
        if (_itemList != null) {
            itemList = _itemList;
            for (ItemZeroOne item : _itemList) {
                item.checkMembers();
            }
        }
    }

    // set the member with name "inKnapsack" by all items:
    private void setInKnapsackByAll(int inKnapsack) {
        for (ItemZeroOne item : itemList)
            if (inKnapsack > 0)
                item.setInKnapsack(1);
            else
                item.setInKnapsack(0);
    }

    // set the data members of class in the state of starting the calculation:
    protected void setInitialStateForCalculation() {
        setInKnapsackByAll(0);
        calculated = false;
        profit = 0;
        solutionWeight = 0;
    }

} // class

class ItemZeroOne {

    protected String name = "";
    protected int weight = 0;
    protected int value = 0;
    protected int bounding = 1; // the maximal limit of item's pieces
    protected int inKnapsack = 0; // the pieces of item in solution

    public ItemZeroOne() {
    }

    public ItemZeroOne(ItemZeroOne item) {
        setName(item.name);
        setWeight(item.weight);
        setValue(item.value);
        setBounding(item.bounding);
    }

    public ItemZeroOne(int _weight, int _value) {
        setWeight(_weight);
        setValue(_value);
    }

    public ItemZeroOne(int _weight, int _value, int _bounding) {
        setWeight(_weight);
        setValue(_value);
        setBounding(_bounding);
    }

    public ItemZeroOne(String _name, int _weight, int _value) {
        setName(_name);
        setWeight(_weight);
        setValue(_value);
    }

    public ItemZeroOne(String _name, int _weight, int _value, int _bounding) {
        setName(_name);
        setWeight(_weight);
        setValue(_value);
        setBounding(_bounding);
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setWeight(int _weight) {
        weight = Math.max(_weight, 0);
    }

    public void setValue(int _value) {
        value = Math.max(_value, 0);
    }

    public void setInKnapsack(int _inKnapsack) {
        inKnapsack = Math.min(getBounding(), Math.max(_inKnapsack, 0));
    }

    public void setBounding(int _bounding) {
        bounding = Math.max(_bounding, 0);
        if (bounding == 0)
            inKnapsack = 0;
    }

    public void checkMembers() {
        setWeight(weight);
        setValue(value);
        setBounding(bounding);
        setInKnapsack(inKnapsack);
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public int getInKnapsack() {
        return inKnapsack;
    }

    public int getBounding() {
        return bounding;
    }

} // class