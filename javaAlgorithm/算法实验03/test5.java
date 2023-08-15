package 练习1.算法实验03;

import java.util.Arrays;
import java.util.Random;

/**
 * 设计算法并编程：随机生成20个三位正整数，再分别用冒泡法、直接选择法和直接插入法对其排序。
 */
public class test5 {
    public static void main(String[] args) {
            Random random = new Random();
            int[] b = new int[20];
            int i = 0;
            //随机数范围100~999，并存入数组
            while (i < 20) {
                int a = random.nextInt(900) + 100;
                b[i] = a;
                i++;
            }
            System.out.println("20个随机数为：" + Arrays.toString(b));
        /*
        使用插入排序排序
         */
        for (int c=1;c<b.length;c++) {
            for (int j=c;j>0&&b[j]<b[j-1];j--){
                if (b[j]<b[j-1]){
                    int nuber=b[j];
                    b[j]=b[j-1];
                    b[j-1]=nuber;
                }
            }
        }
        System.out.println("排序后的数是："+Arrays.toString(b));

    }
}
