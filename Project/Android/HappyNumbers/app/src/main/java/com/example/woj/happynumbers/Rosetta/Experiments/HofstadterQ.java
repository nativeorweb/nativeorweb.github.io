package com.example.woj.happynumbers.Rosetta.Experiments;


public class HofstadterQ {

    public static int DEFAULT_NUMBER = 2500000;
    public static int DEFAULT_LOOP = 250;
    public static int JAVA = 1;

    //  public static int JS = 2;
    //private static ArrayList memo = new ArrayList<Integer>(DEFAULT_NUMBER);
    //private static int[] array = new int[DEFAULT_NUMBER];

    public static int[] q = null;

    public static int Q(int n){

        int result = q[n];

        if(result == -1){
            result = Q(n - Q(n - 1)) + Q(n - Q(n - 2));
            q[n] = result;
        }
        return result;
    }

    private static void init(int number) {
        q = new int[number+3];
        q[0] = 1;
        q[1] = 1;
        q[2] = 1;
        for (int x = 3; x < number+3; x++) {
            q[x] = -1;
        }
    }

    public static void execute(int TYPE, int number,int loop) {


        for (int m = 0; m < loop; m++) {
            init(number);
            for (int i = 1; i <= number; i++) {
                    Q(i);
            }
        }
            // if(TYPE == JAVA) {

//        for (int w = 0; w < loop ; w++) {
//
//
//                for (int i = 1; i <= number; i++) {
//                    if (i == number - 1) {
//                        System.out.println("JAVA " + Q(number));
//                    } else {
//                        Q(i);
//                    }
//                }
//
//                q =  new HashMap<Integer, Integer>(){{
//                    put(1, 1);
//                    put(2, 1);
//                }};

            //  }
       /* else{
            for (int i = 1; i < number; i += 1) {
                if(i == number-1){
                    System.out.println("JS " + hofstadterQ(i)) ;
                }else{
                    hofstadterQ(i);
                }
            }*/

            //  }
            //  }
        }


}

    /** Solução de JAVA no Rosetta */



//    private static Map<Integer, Integer> q = new HashMap<Integer, Integer>(){{
//        put(1, 1);
//        put(2, 1);
//    }};
//
//    public static int Q(int n){
//        if(q.containsKey(n)){
//            return q.get(n);
//        }
//        int ans = Q(n - Q(n - 1)) + Q(n - Q(n - 2));
//        q.put(n, ans);
//        return ans;
//    }

/*
    */
/** Solução de JS no Rosetta *//*


    public int hofstadterQ(int i) {
        array = new int[i+10];
        memo.clear();
        memo.add(1);
        memo.add(1);
        memo.add(1);
        return QJS(i);

    }

    private int QJS(int i) {
        int result = 0;
        */
/*try{
                result = (int) memo.get(i);
            } catch (Exception e){
                result = QJS(i - QJS(i - 1)) + QJS(i - QJS(i - 2));
                memo.add(i,result);
                return result;
            }*//*



                result = array[i];
            if(result != (int)result){
                result = QJS(i - QJS(i - 1)) + QJS(i - QJS(i - 2));
                array[i] = result;
                return result;
            }

        return result;
    }

*/




//}