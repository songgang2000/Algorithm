package 练习1.算法实验01;

import java.util.Scanner;

/**
 * 从键盘输入一个不大于1000的正整数n(n>1)，输出所有小于等于n的素数。
 * 素数：只能被1和自己整除的数
 */
public class test1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int a,b;
        System.out.println("输入1000以内的素数");
        int i = scanner.nextInt();
        //1不是素数。所以从2开始
        for (a=2;a<=i;a++){
            b=2;
            //判断是否整除
            while (a%b!=0){
                b++;
                }
            //整除外判断是否相等
            if (a==b){
                System.out.println(a);
            }
        }
    }
}
