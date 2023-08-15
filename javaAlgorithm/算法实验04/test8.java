package 练习1.算法实验04;

/**
 * 算法实验04-01：用递归法输出n层三角形状数字,编程实现。
 */

public class test8{
    public static void main(String[] args) {
    //sanjiao(4);
       // diguisj1(4);
        diguisj2(4);
    }


    /**
     * 不用递归的方法
     * @param nu
     */
    public static void sanjiao(int nu){
        //如果数字小于1，就直接返回
        if (nu<1){
            return;
        }
        //定义一个每排固定数
        int a=1;
        while (a<=nu) {
            for (int i = 0; i <a; i++) {
            System.out.print(a+"\t");
            }
            System.out.println();
            a++;
        }
    }

    /**
     * 用递归方法1顺
     */

    public static void diguisj1(int k){

        if (k<=0){
            return;
        }
            for (int i = 0; i < k; i++) {
                System.out.print(k);
            }
            System.out.println();
             diguisj1(k-1);
    }


    /**
     * 用递归方法2逆
     * @param k
     */
    public static void diguisj2(int k) {
        //先执行递归，每次递归都会产生一个方法栈，
    if (k>0){
        diguisj2(k-1);
    }
    //当上面递归结束后就会开始返回方法栈的内容，从栈顶(0)开始，栈底(k)结束，之后其它没用的方法就会自动销毁
    for (int i=k;i>0;i--){
        System.out.print(k);
    }
    System.out.println();

    }
}