package 练习1.算法实验02;

/**
 * 编程实现，在10个整数构成的有序序列中，二分法(折半法)查找一个数。
 */
public class test3 {
    public static void main(String[] args) {
        int[] a={1,2,3,4,5,6};
        //调用自定义的二分法方法
        int lookup = lookup(a, 3);

        System.out.println(lookup);

    }

    //二分法方法
    public static int lookup(int[] ar,int e){
        //初始下标
        int b=0;
        //末尾下标
        int c=ar.length-1;
        //循环条件
        while (b<c){
            //第一次二分法下标
            int d=(c+b)/2;
            //如果下标数大于要查找的数，则将末尾下标更新为此下标，重新折半查找
            if (ar[d]>e){
                c=d;
            }
            //如果下标数小于要查找的数，则将初始下标更新为此下标，重新折半查找
            if (ar[d]<e) {
                b=d;
            }
            //如果相等就返回下标
            if (ar[d]==e){
                return d;
            }
        }
        return -1;
    }
}
