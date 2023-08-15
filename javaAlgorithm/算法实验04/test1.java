package 练习1.算法实验04;

/**
 * 教材P79页，第9题（电影售票排队问题）, 设计算法编程实现。
 * 每张电影票50元，现有m+n个人买票，m(手持50元)，n(手持100元)。
 * 问：以知售票处没有零钱，分配排队使其售票处不会出现找不清钱的情况方案数
 *
 */
public class test1 {
    public static void main(String[] args) {
        int dypd = dypd(5, 4);
        System.out.println("方案共有："+dypd+"种");
    }

    //排堆算法，共有m+n个人，需要合理分配m与n的人数，使其有多少种排队方案
    //m:50元钞票；n：100元钞票
    public static int dypd(int m,int n){
        //定义方案变量
       int sun;
       //如果没有100元的人买票，全部都是50元的人，就只有1种方案
       if (n==0){
           sun=1;
       }else if (m<n){//如果50元的人数少于100元的人，就绝对会出现补不了钱的情况，直接返回0
           sun=0;
       }else {
           //其它情况：
           sun=dypd(m-1,n)+dypd(m,n-1);
       }

           return sun;

        }

    }
