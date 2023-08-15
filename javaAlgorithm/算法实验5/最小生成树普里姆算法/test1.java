package 练习1.算法实验5.最小生成树普里姆算法;

import java.util.Arrays;

/*
贪心策略：最小生成树-普里姆算法
       ：在包含n个顶点的无向连通图中，找出只有（n-1）条边且包含n个顶点的连通子图。使其形成最小连通子图。连通子图不能出现回路
分析：
    1.设置集合 W和集合 D 。其中W存入的是无向连通图中最小生成树的顶点集合；D存入的是最小生成树每相邻两个顶点之间的连接边的集合
    2.另集合W的初值为 W{w0}(即从w0顶点开始构建最小生成树)，另集合D初始值为 D{}
    3.设V为还未被选中的顶点。
    4.从w与v=V-W 中组成的所有带权边中选出最小的带权边(wn,vn).
    5.将顶点vn加入集合W中。此时集合W{wn,vn},集合D{(wn,vn)}
    6.重复上面步骤，直到V中所有顶点都加入到了W中，边有n-1条带权边。结束
代码：探讨修路问题
 */
public class test1 {
    public static void main(String[] args) {
        //所有节点
    char[] ndata=new char[]{'A','B','C','D','E','F','G'};
        //获取节点个数
        int nodes = ndata.length;
        //邻接矩阵.用较大的数10000表示两点不连通
        int[][] ndlenght=new int[][]{
                //A   ,B,C,  D  ,  E  ,  F  ,G
                {10000,5,7,10000,10000,10000,2},    //A
                {5,10000,10000,9,10000,10000,3},    //B
                {7,10000,10000,10000,8,10000,10000},//C
                {10000,9,10000,10000,10000,4,10000},//D
                {10000,10000,8,10000,10000,5,4},    //E
                {10000,10000,10000,4,5,10000,6},    //F
                {2,3,10000,10000,4,6,10000},        //G
                                                };
        //创建图对象
        Chart chart=new Chart(nodes);
        //创建生成数对象
        MinTree mt=new MinTree();
        //创建邻接矩阵
        mt.creathart(chart,nodes,ndata,ndlenght);
        //获取矩阵
       // mt.dischart(chart);
        mt.Prim(chart,0);


    }

}

/*
第二步：
创建生成树对象
 */
class MinTree{
    /**
     * 创建邻接矩阵
     * @param chart :图对象
     * @param nodes ：节点个数
     * @param ndata :存放节点数据
     * @param ndlenght  :带权边
     */
    public void creathart(Chart chart,int nodes,char[] ndata,int[] [] ndlenght){
        //i:已经被选中的节点，ndata[i0]就是为初值，ndata[0]节点开始生成树。一共有nodes个
        for (int i=0;i<nodes;i++){
            //将当前已被选的节点存入图对象的ndata中
            chart.ndata[i]=ndata[i];
            //j:还未被选中的节点
            for (int j=0;j<nodes;j++){
                //将所有可能两两连接的节点组合，存入图对象的邻接矩阵中
                chart.ndlenght[i][j]=ndlenght[i][j];
            }
        }
    }

    //显示图矩阵
    public void dischart(Chart chart){
        for (int[] link:chart.ndlenght){
            System.out.println(Arrays.toString(link));
        }
    }


    /**
     * 普里姆算法:
     *    最小生成树
     * @param chart:图
     * @param v :初值
     */
    public void Prim(Chart chart,int v){
        //存放已被选中的节点，初始都为0
        int[] ondata=new int[chart.nodes];
        //标记初值节点已被选中,1(表示被选中了的)
        ondata[v]=1;
        //设即将相连的两个节点下标为 index1、index2。由于还没有存入，所以初始为-1
        int index1=-1;
        int index2=-1;
        //由于还不知道第一个边长为多少，所以先虚拟设置一个最大带权边长。后面后被替换的
        int max=10000;
        //k:表示最多生成（n-1)条带权边
        for (int k=1;k<chart.nodes;k++){
            //i:表示以被选中的节点；j:还未被选中的节点
            for (int i=0;i<chart.nodes;i++){
                for (int j=0;j<chart.nodes;j++){
                    if (ondata[i]==1&&ondata[j]==0&&chart.ndlenght[i][j]<max){
                        max=chart.ndlenght[i][j];
                        index1=i;
                        index2=j;
                    }
                }
            }
            System.out.println("节点:<"+chart.ndata[index1]+","+chart.ndata[index2]+">,==>带权边长:"+max);
            //将当前节点标记为以访问使用的节点
            ondata[index2]=1;
            //重置max
            max=10000;
        }

    }

}

/*
第一步：
创建图对象
 */
class Chart{
    int nodes;  //图中节点个数
    char[] ndata;   //存放节点数据
    int[] [] ndlenght;  //存放带权边。也是邻接矩阵

    //构造器
    public Chart(int nodes) {
        this.nodes = nodes;
        //初始化，数组长度为nodes(节点个数)
        ndata=new char[nodes];
        //初始化，矩阵为nodes行nodes列
        ndlenght=new int[nodes][nodes];
    }
}