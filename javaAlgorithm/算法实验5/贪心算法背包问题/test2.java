package 练习1.算法实验5.贪心算法背包问题;

import java.util.Arrays;
/**
 * 背包问题（贪心算法）
 *教材P103，第四题算法设计题中第2题(0-1背包问题), 设计算法编程实现。
 * 问题：在0-1背包问题中，有n个物品（n=7），价值为 v={10,5,15,7,6,18,3};重量为 w={2,3,5,7,1,4,1};
 * 背包容量为15，请设计贪心算法，并讨论是否可获得最优解。
 */
public class test2 {
    public static void main(String[] args) {
        //个数
        int n=7;
        //背包容量
        double W=15;
        //价值
        double[] v={10,5,15,7,6,18,3};
        //重量
        double[] w={2,3,5,7,1,4,1};
//=====================================================
        //个数
//        int n=4;
//        //背包容量
//        double W=20;
//        //价值
//        double[] v={300,180,150,270};
//        //重量
//        double[] w={15,18,10,9};

        double[] x=new double[n];
        //物品对象
        Goods1[] gd=new Goods1[n];
        //存储单位价值
        //Goods1[] dwjz=new Goods1[n];
        int idex=0;
        for (int i=0;i<v.length;i++){
            //单个价值
            double v1 = v[i] / w[i];
            gd[idex++]=new Goods1(w[i],v[i],v1);
            //dwjz[i]=new Goods1(v1);
        }

        //查看gd数组
        System.out.println(Arrays.toString(gd));
        //对单位重量的价值排序：降序
        test2 t=new test2();
        t.descsort(gd,n);
        System.out.println(Arrays.toString(gd));
        //返回背包总价值
        double knap = t.knap(W, gd, n, x);
        System.out.println(knap);
        for (int i=0;i<n;i++){
            double v1 = x[i] * gd[i].weight;
            System.out.println("按照单位重量排序后数组中第"+(i+1)+"个物品个数:"+v1);
        }
//        System.out.println(Arrays.toString(x));


    }



    //排序
    public void descsort(Goods1[] wp,int n){
        int i,j;
        Goods1 t;
        for (i=0;i<n-1;i++){
            for(j=0;j<n-i-1;j++){
                if (wp[j].aprice<wp[j+1].aprice){
                    t=wp[j];
                    wp[j]=wp[j+1];
                    wp[j+1]=t;
                }
            }
        }
    }

    //返回背包总价值
    public  double knap(double W,Goods1[] wp,int n,double[] x){
        int i;
        double V=0; //总价值
        double weight=W;   //背包能装的有余下重量
        //初始化向量
        for (i=0;i<n;i++){
            x[i]=0;
        }

        i=0;
        //物品能全部装下才进入循环
        while (wp[i].weight<weight){
            //将i全部装入背包
            x[i]=1;
            //更新背包余下重量
            weight-=wp[i].weight;
            //累计总价值
            V+=wp[i].price;
            i++;
        }
        //余下重量大于0
        if (weight>0){
            //将物品i一部分装入
            x[i]=weight/wp[i].weight;
            //累计总价值
            V+=x[i]*wp[i].price;
        }
        return V;
    }

}


/*
物品类
 */
class Goods1 {
    double weight;   //物品重量
    double price;    //物品价值
    double aprice;   //物品价值/物品重量的比（即单位重量的价值）

    public Goods1(double weight, double price, double aprice) {

        this.weight = weight;
        this.price = price;
        this.aprice = aprice;
    }

    @Override
    public String toString() {
        return "Goods1{" +
                "weight=" + weight +
                ", price=" + price +
                ", aprice=" + aprice +
                '}';
    }
}