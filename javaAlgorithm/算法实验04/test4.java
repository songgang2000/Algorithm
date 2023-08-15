package 练习1.算法实验04;

import b站javaSE.Java作业.线程交替输出.nuber;

/**
 * 递归法实现对10个整数构成的有序序列二分查找。
 */
public class test4 {
    public static void main(String[] args) {
        int[] arry={1,2,3,4,5,6};
        int digeffcz = digeffcz(arry, 3, 0, arry.length - 1);
        System.out.println(digeffcz);
    }

    //递归的二分法
    public static int digeffcz(int[] arry,int nuber,int let,int ret) {
int i=let;
int j=ret;
    int tmp=(let+ret)/2;
    while (i<j){
        if (arry[tmp]==nuber){
            return tmp;
        }else if (arry[tmp]>nuber){
            digeffcz(arry,nuber,let,tmp);
        } else if (arry[tmp] <nuber) {
            digeffcz(arry, nuber, let, tmp);
        }
        }
        return -1;
    }
}
