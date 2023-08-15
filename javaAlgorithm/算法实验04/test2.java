package 练习1.算法实验04;

import java.util.Arrays;
import java.util.Random;

/**
 * 设计算法并编程：随机生成15个两位正整数，再分别用快速排序法、二路归并排序法实现。
 */
public class test2 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] n = new int[15];
        //随机生成15个二位正整数
        for (int i = 0; i < n.length; i++) {
            int nu = random.nextInt(89) + 10;
            n[i] = nu;
        }
        System.out.println(Arrays.toString(n));
        kspx(n,0,n.length-1);
        System.out.println(Arrays.toString(n));

    }

    //使用快速排序将15个数排序(快速排序)
    public static void kspx(int[] n, int a, int b) {
        if (a > b) {
            return;
        }
        //最左边索引
        int lt = a;
        //最右边索引
        int rt = b;
        //初始基准数
        int m = n[lt];
        while (lt!=rt){
            //先从右边比较，如果右边的数大于基准数就向下减，如果小于基准数就先停顿等待，去检索左边
            while (n[rt]>=m&&rt>lt){
                rt--;
            }
            //左边检索，如果满足就做加加，如果大于基准数就等待执行下面代码
            while (n[lt]<=m&&lt<rt){
                lt++;
            }
            //将右检索小于基准数的数与左检索大于基准数的数进行交换
            int nu=n[lt];
            n[lt]=n[rt];
            n[rt]=nu;
        }

        //执行到这里就表示：左检索与右检索的索引相遇了，没有需要检索的数，就将相遇的数认定为最新的基准数
        n[a]=n[lt];
        n[lt]=m;

        //由于到这里以新的基准数将数组分隔，及新基准数的下标是：lt=rt
        //递归新基准数左边
        kspx(n, a,lt-1);
        //递归新基准数右边
        kspx(n,lt+1,b);
    }
}
