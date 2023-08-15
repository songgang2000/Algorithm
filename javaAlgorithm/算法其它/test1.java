package 练习1.算法其它;

import java.util.Random;
import java.util.Scanner;

/**
 * 币种统计问题
 * 假如由n个工人，每个工人的工资不一样，现需要到银行取现金。要求：面值有（100、50、10、5、1）元的面值。而且取得的张数最少，正好发完。
 * 问：取得各个面值张数的最优解
 */
public class test1 {
    public static void main(String[] args) {
        //假设有两个员工
        int n=2;
        QqProgramme(n);
    }

    //n:需要结发工资的人数
    public static void QqProgramme(int n) {
        /*
        以下两个数组是面值下标索引与面值对应的张数索引对应相同
        用0占住索引为0的位置，因为人没有第0个人
         */
        //用于存储面值，默认面值从大到小面值
        int[] mianzhi = {0,100, 50, 10, 5, 1};
        //用于存储与之下标索引对应的张数，初始化全部为0
        int[] zhangshu = {0,0, 0, 0, 0, 0};
        Scanner scanner = new Scanner(System.in);
        //遍历人数，每次循环都会先遍历当前这个人金额所需要各个面值的张数。从第一个人开始
        for (int i = 1; i <= n; i++) {
            System.out.println("请输入第" + i + "个员工的工资：");
            //输入当前员工的应发放总金额
            int gz = scanner.nextInt();
            //从索引为1即面值为100向面值小的遍历，每次先会得出当前面值的张数，直到到最小金额的张数，就会执行下一个人
            //这里所以为1是100，也对应它张数的索引；索引为5就是最小面额，所以到索引5后就没有面额了
            for (int j = 1; j <=5 ; j++) {
                //总金额先除当前索引对应的面额，得出当前面额需要的张数
               int s1= gz /mianzhi[j];
               //将张数存入张数对应的数组中
               zhangshu[j]+=s1;
               //更新总金额，算出下一阶面额张数
               gz=gz-s1*mianzhi[j];
            }
        }

        //遍历最新张数数组，得出各个面额需要的张数
        for (int i = 1; i <=5 ; i++) {
            System.out.println(mianzhi[i]+"面值的需要"+zhangshu[i]+"张");
        }
    }
}
