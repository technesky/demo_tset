package com.sky.demo;

/**算法
 * demo
 * Created by sunzhg on 2016/8/12.
 */
public class Demo1 {

    public static void main(String[] args) {
        /*long a=46561205;
        System.out.println(getId(a));*/
        System.out.println(test(4));
    }

    /**
     *
     * @param i
     * @return
     */
    public static int test(int i){
        if (i==1){
            return 1;
        }else if (i==2){
            return 2;
        }else {
            System.out.println(i);
            return test(i-1)+test(i-2);
        }
    }



    public static String getId(long id){
        final long suffix=id%10000;
        long p=(suffix % 100)*100+suffix / 100;

        if (p<1000&&p>0){
            return "0"+Long.toString(p);
        }else if (p==0) {
            return "0000";
        }
        return Long.toString(p);
    }
}
