package 练习1.算法实验02;

import java.util.Arrays;

/**
 * 设计一个算法完成一维整型数组元素的逆置,编程实现。
 */
public class test2 {
    public static void main(String[] args) {
        int[] a={1,4,3,2,6};
        //数组长度
        int b = a.length;
        //存逆置元素的数组
        int[] c=new int[a.length];
        //定义下标
        int d=0;
        //循环条件
      while (b>0){
          //赋值
          c[d]=a[b-1];
          b--;
          d++;
      }
      //输出打印
        System.out.println("原数组："+Arrays.toString(a));
      System.out.println("逆置数组："+Arrays.toString(c));

    }
}
