package 练习1.算法实验04;

import java.util.Arrays;
import java.util.Random;

/**
 * . 设计算法并编程：随机生成15个两位正整数，再分别用快速排序法、二路归并排序法实现。
 */
public class test3 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] n = new int[15];
        //随机生成15个二位正整数
        for (int i = 0; i < n.length; i++) {
            int nu = random.nextInt(89) + 10;
            n[i] = nu;
        }
        System.out.println(Arrays.toString(n));
        elbg(n,0,n.length-1);
        System.out.println(Arrays.toString(n));
    }


    //二路归并排序法实现。

    /**
     * arry[]:要排序的数组，
     * let：下限
     * ret：上限
     */
    public static void elbg(int arry[],int let,int ret){
        //拆分的数的长度最少为2，且下限必须小于上限
        if(let>=ret){
            return;
        }
        //tmp：中间位
        int tmp=(let+ret)/2;
        //递归[let~tmp]
        elbg(arry,let,tmp);
        //递归[tmp+1~ret]
        elbg(arry,tmp+1,ret);
        //合并为【let~ret】
        eldghb(arry,let,tmp,ret);
    }



    /**
     *排序:
     * @param ary:要排序的数组
     * @param let：下限
     * @param tmp：左边上限
     * @param ret：上限
     */
    public static void eldghb(int ary[],int let,int tmp,int ret){
        //左下限
        int i=let;
        //右边下限
      int j=tmp+1;
      //临时储存数组
        int[] aya=new int[(ret-let)+1];
        //数组下标
        int k=0;
        //左下限要小于左边上限，右下限要小于右边上限，
        while (i<=tmp&&j<=ret){
            //合并拆分的数组，两两开始合并，判断比较左边下限开始和右边下限开始
            //如果左边的小于右边的，就将左边小的存入aya数组中，佛则将右边小的存入aya数组
            if (ary[i]<=ary[j]){
                aya[k++]=ary[i++];
            }else {
                aya[k++]=ary[j++];
            }
        }
        //如果出现左边还有元素，就将此元素继续存入aya数组
        while (i<=tmp){
            aya[k++]=ary[i++];
        }
        //如果出现右边还有元素，就将此元素继续存入aya数组
        while (j<=ret){
            aya[k++]=ary[j++];
        }
        //合并两边已排序好的数
        for (int l=0;l<aya.length;l++){
            ary[l+let]=aya[l];
        }
    }
}
