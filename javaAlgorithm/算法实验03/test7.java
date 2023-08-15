package 练习1.算法实验03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ,用蛮力法求大于n的最小Smith数,编程实现。
 * 史密斯数原理：若一个正整数的质因数分解式逐位相加之和等于其本身逐位相加之和，
 * 则称这个数为 Smith 数。如 4937775=3*5*5*65837，而 3+5+5+6+5+8+3+7=42，4+9+3+7+7+7+5=42，
 * 所以 4937775 是 Smith 数。
 */
public class test7 {
    public static void main(String[] args) {
        Scanner n=new Scanner(System.in);
        System.out.println("请输入一个数，打印大于它的最小Smith数，n=");
        int i1 = n.nextInt();
        List l=new ArrayList();
        while (l.size()==0) {
            for (int i = i1; ; ++i) {
                if (isSmith(i)) {
                    l.add(i);
                    if (l.size()!=0){
                        break;
                    }
                }
            }

        }
        if (l.size()==1){

            for (Object a:l) {
                System.out.println(a);
            }

        }



    }

    /*
    拆分数字求其拆分后的数字和，例如：123==>1=2+3
     */
    public static int sum(int n){
        //定义用于接收求和的变量
        int re=0;
        //循环条件：被拆分的数字不能为0
        while (n!=0){
            //开始拆分，从个位开始，每次拆分后与re值相加
            //例如：123%10=3，即re=0+3=3
            re+=n%10;
//            int re1=(n-(n%10))/10;
//            n=re1;
            //这里n=12.3,由于是int类型，所以是n=12,之后下一次循环
            n/=10;
        }
        //返回和值
        return re;
    }

    /*
    判断是否为素数
     */
   public static boolean isSushu(int n){
       //如果是2，直接返回true
       if(n==2) return true;
       //n%（2~n），如果期间被整除。说明不是素数
       for (int i=2;i<n;i++){
           if (n%i==0){
               return false;
           }
       }
        return true;
   }

   /*
   判断是否为史密斯数
    */
    public static boolean isSmith(int n){
        //求数的和
        int a=sum(n);
        //求所有分解质因数的和
        int b=0;
        //分解，由于1既不是质数（素数）也不是合数，所以从最小的质因数开始分解
        for (int i=2;i<=n;i++){
            //如果n能被整除，且整除他的那个数是素数（意思是这个数不能在被除1和自身整除）。则这就是我们要找的其中一个质因数
            if (n%i==0&&isSushu(i)){
                //将此质因数分解求和加入到b中存入
                b+=sum(i);
                //更新n：例如n=4 ==》4/2=2，则n=2
                n/=i;
                //更新i
                i=1;
            }
        }
        //如果各个质因数的和等于数本身各个之和，则返回真
        if (a==b){
            return true;
        }
    //不满足返回假
        return false;
    }
}
