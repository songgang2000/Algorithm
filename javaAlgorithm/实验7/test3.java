package 练习1.实验7;
/**
 * 教材P148第三大题算法设计题第4小题，回溯法求解0-1背包问题，编程实现。
 */
public class test3 {
    public static void main(String[] args) {
        double[] weight= {35,30,60,50};
        double[] price={10,40,30,50};
        double cc=100;

        test3 k=new test3();
        double best=k.knapsack(price,weight,cc);
        System.out.println("最优值："+best);
        System.out.println("选中的物品编号分别是：");
        for(int i=1;i<k.bestx.length;i++){
            if(k.bestx[i]==1){
                System.out.print(k.q[i].id+" ");
            }
        }
    }

    /**
     * 0-1背包问题--回溯法
     * @author Lican
     *
     */

    public static class Element implements Comparable{
        int id;//物品编号
        double d;//物品单位重量
        public Element(int id,double d){
            this.id=id;
            this.d=d;
        }
        @Override
        public int compareTo(Object x) {
            double xd=((Element)x).d;//递减顺序排列
            if(d<xd) return -1;
            if(d==xd) return 0;
            return 1;
        }
    }
    //=========================================================================
    double c;//背包容量
    int n;//物品数
    double[] w;//物品重量数组
    double[] p;//物品价值数组
    double cw;//当前重量
    double cp;//当前价值
    double bestp;//最优价值
    int[] x;//当前装入背包顺序
    int[] bestx;//最优装入背包顺序
    Element[] q;//q为单位重量价值数组

    /**
     *
     * @param pp:物品价值数组
     * @param ww：物品重量数组
     * @param cc：背包总重量
     * @return：
     */
    public double knapsack(double[] pp,double[] ww,double cc){
        //初始化
        c=cc;//背包容量
        n=pp.length-1;//物品数
        cw=0;//当前重量
        cp=0;//当前价值
        bestp=0;//最优价值
        x=new int[n+1];//当前装入背包顺序
        bestx=new int[n+1];//最优装入背包顺序
        q=new Element[n+1];//q为单位重量价值数组

        for(int i=0;i<=n;i++){
            q[i]=new Element(i,pp[i]/ww[i]);
        }
        //将个物品依单位重量价值从大到小排列
        java.util.Arrays.sort(q);

        p=new double[n+1];
        w=new double[n+1];
        for(int i=1;i<=n;i++){
            p[i]=pp[q[i].id];
            w[i]=ww[q[i].id];
        }
        backtrack(1);
        return bestp;
    }

    //==================================================================
    public void backtrack(int i){
        if(i>n){//到达叶子节点
            bestp=cp;//当前价值
            for(int j=1;j<=n;j++){//保存最优值对应的包的编号
                bestx[j]=x[j];
            }
            return;
        }
        if(cw+w[i]<=c){//左子树
            x[i]=1;
            cw+=w[i];
            cp+=p[i];
            backtrack(i+1);
            cw-=w[i];//恢复现场
            cp-=p[i];
        }
        if(bound(i+1)>bestp){
            x[i]=0;
            backtrack(i+1);
        }
    }

    //==============================================================
    public double bound(int i){//上界函数
        double cleft=c-cw;//背包容量-当前重量
        double bound=cp;//当前价值
        while(i<=n&&w[i]<=cleft){
            cleft-=w[i];
            bound+=p[i];
            i++;
        }
        if(i<=n){
            bound+=p[i]*cleft/w[i];
        }
        return bound;
    }
}
