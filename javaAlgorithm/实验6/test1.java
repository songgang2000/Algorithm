package 练习1.实验6;

/**
 * 凑硬币（动态规划法）
 *假设有2元，3元，5元的硬币若干(数量不限)，如何用最少的硬币凑够11元,编程实现。
 */
public class test1 {
    public static void main(String[] args) {
        int n = 11;
        int[] c = new int[n+1];
        int[] coin = {2, 3, 5};
        test1 t = new test1();
        int cyb = t.cyb(c, coin, n);
        System.out.println("最少需要"+cyb+"个硬币");


    }

    /**
     * @param c:c[i]---》能够凑齐i元的硬币数
     * @param coin：硬币面值种类
     * @param n：最终要凑齐的面额
     */
    public int cyb(int[] c, int[] coin, int n) {
        int i, j;
        //凑齐0元要0个硬币
        c[0] = 0;
        //遍历每一阶段要凑的面额，直到到n
        for (i = 1; i <= n; i++) {
            //先假设面值为i的面额凑不了，用i=9999表示，后面会更新的
            c[i] = 9999;
           /*
           循环条件：硬币面值必须要小于目前在凑的面值，
                   由于j要小于硬币面值的种类
            */
            for (j = 0;j <coin.length&& coin[j] <=i; j++) {
               /*
               i-coin[j]:当前要凑的面值减去面值种类中j对应的面值，还剩下的面值。假入还剩下k面值，得到先前k值时需要的最优个数。
               然后将其数目加上自身本身消耗的个数。如果个数小于9999
                */
                if (c[i - coin[j]] + 1 < c[i]) {
                    //就将i面值的9999个数换为当前j对应的面额个数。之后找j++的面额的个数，最终得到此面额最少个数
                    c[i] = c[i - coin[j]] + 1;
                }
              System.out.println(j+"和"+i);
            }

        }
        return c[n];
    }
}