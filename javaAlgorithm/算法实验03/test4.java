package 练习1.算法实验03;

import java.util.Arrays;
import java.util.Random;

/**
 * 设计算法并编程：随机生成20个三位正整数，再分别用冒泡法、直接选择法和直接插入法对其排序。
 */
public class test4 {
    public static void main(String[] args) {
        Random random=new Random();
        int[] b=new int[20];
        int i=0;
        //随机数范围100~999，并存入数组
        while (i<20) {
            int a = random.nextInt(900) + 100;
            b[i]=a;
            i++;
        }
        System.out.println("20个随机数为："+Arrays.toString(b));
        /*
        使用冒泡排序排序
         */
        for (int c=b.length-1;c>=0;c--){
            for (int d=0;d<c;d++){
                if (b[d]>b[d+1]){
                    int nuber=b[d];
                    b[d]=b[d+1];
                    b[d+1]=nuber;
                }
            }
        }
        System.out.println("排序后的数是："+Arrays.toString(b));
    }
}
