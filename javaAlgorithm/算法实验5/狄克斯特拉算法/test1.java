package 练习1.算法实验5.狄克斯特拉算法;

import java.util.Arrays;

/**
 * 狄克斯特拉算法:最短路径问题（单源最短路径问题）
 */
public class test1 {
    public static void main(String[] args) {
        //顶点
        char[]  data={'A','B','C','D','E'};
        //char[]  data={'A','B','C','D','E','F','G'};
        //定义最大值，也就是不能相连接
        final int N=65535;
        //邻接矩阵
        int[][] ljjz={
                {N,20,N,60,15},
                {20,N,42,N,N},
                {N,42,N,30,N},
                {60,N,30,N,23},
                {15,N,N,23,N}
        };
//        int[][] ljjz=new int[data.length][data.length];
//        ljjz[0]=new int[]{N,5,7,N,N,N,2};
//        ljjz[1]=new int[]{5,N,N,9,N,N,3};
//        ljjz[2]=new int[]{7,N,N,N,8,N,N};
//        ljjz[3]=new int[]{N,9,N,N,N,4,N};
//        ljjz[4]=new int[]{N,N,8,N,N,5,4};
//        ljjz[5]=new int[]{N,N,N,4,5,N,6};
//        ljjz[6]=new int[]{2,3,N,N,4,6,N};

        //创建图对象
        Chart chart=new Chart(data,ljjz);
        //打印图
        chart.displaych();
//        chart.DijKstra(6);
        chart.DijKstra(0);
        chart.showDijKstra();

    }
}

/**
 * 构造一个图类
 */
class Chart{
    private char[] data;  //存放各个节点的数组
    private int[][] ljjz; //邻接矩阵，也就是每相邻两顶点{前驱节点(源)与其它能访问的节点}的边长(路长)
    private VisitedData visn;//顶点集合
    //构造器
    public Chart(char[] data, int[][] ljjz) {
        this.data = data;
        this.ljjz = ljjz;
    }
    //显示结果
    public void  showDijKstra(){
        visn.show();
    }
    @Override
    public String toString() {
        return "Chart{" +
                "data=" + Arrays.toString(data) +
                ", ljjz=" + Arrays.toString(ljjz) +
                '}';
    }

    //显示图
    public void displaych(){
        for (int[] tu:ljjz){
            System.out.println(Arrays.toString(tu));
        }
    }

    /**
     * 狄克斯特拉算法
     * @param index:出发顶点索引
     */
    public void DijKstra(int index){
        //获得顶点类各个集合
        visn=new VisitedData(data.length,index);
        //更新index到周围顶点的距离和前驱节点
            updata(index);
            //由于第一个是已被访问过的，从1开始
            for (int i=1;i<data.length;i++){
                //更新新的顶点
                index = visn.upindex();
                //再次更新index到周围顶点的距离和前驱节点
                updata(index);
            }

    }

    /*
      更新index顶点到周围顶点的距离和周围顶点的前驱顶点
     */
    public void updata(int index){
        //设置初始长度为0
        int len=0;
        //遍历邻接矩阵的第ljjz[index]行，即先遍历出发顶点到各个顶点的距离，找出最短边
        for (int i=0;i<ljjz[index].length;i++){
            //len:出发顶点到index顶点的距离+从index节点到i节点的距离
            len= visn.upix(index)+ljjz[index][i];
            //如果当前节点没被访问过,并且len小于另一个出发顶点到i顶点的距离
            if (!visn.in(i)&&len<visn.upix(i)){
                //更新i为前驱节点
                visn.upctdataqujd(i,index);
                //更新出发顶点到i节点的距离
                visn.upzuiduanjuli(i,len);
            }
        }
    }


}

/**
 * 顶点集合类
 */
class VisitedData{
    //记录顶点是否被访问，初始为0，被访问就动态更新为1
    private int[] isdatavis;
    //记录后一个顶点下标对应的索引为前一个顶点的下标索引，即前一个顶点(新头节点)为后一个顶点的前驱节点
    private int[] ctdataqujd;
    //存储记录出发顶点(源)到其它顶点的距离，将最短的放入到 qianqudata集合中
    private int[] zuiduanjuli;

    /**
     * 构造器
     * @param lenth:顶点的个数
     * @param index:出发顶点(源)对应的下标
     */
    public VisitedData(int lenth,int index) {
        //初始化顶点个数
        this.isdatavis=new  int[lenth];
        //初始化ctdataqujd数，与顶点数相同，因为是要相互对应的
        this.ctdataqujd=new int[lenth];
        //初始化设置边数,应为自己也被算作一条边所以是以顶点个数相同
        this.zuiduanjuli=new int[lenth];
        /*
        初始化设置边的值,初始都为最大值65535，表示还没相连。注意：出发点与自己本身需要设置为 0，
        因为：出发点与自己可以看作是一条长度为0的边
        Arrays.fill(int[] a,int value):将指定的int值分配给int型数组的每个元素（元素替换）
         */
        Arrays.fill(zuiduanjuli,65535);
        //初始化出发顶点是已被访问过的
        this.isdatavis[index]=1;
        //设置自己本身长度为0；
        this.zuiduanjuli[index]=0;
    }

    /*
    判断当前顶点是否是已经被访问过的
     */
    public boolean in(int index){
        //如果此顶点下标(index)在isdatavis中对应的值为1，就返回true，否则返回false
        return isdatavis[index]==1;
    }

    /*
    出发顶点到ind顶点的距离，长度为len
     */
    public void upzuiduanjuli(int ind,int len){
        zuiduanjuli[ind]=len;
    }

    /*
    更新前驱顶点：
    index：出发节点
    ind:后一个节点
     */
    public void upctdataqujd(int ind,int index){
        //更新前一个的下标为后一个的前驱节点下标
        ctdataqujd[ind]=index;
    }

    /*
    返回出发节点到此ind索引节点的距离
     */
    public int upix(int ind){
        return zuiduanjuli[ind];
    }

    /*
    继续访问新的节点（不是出发节点）
     */
    public  int upindex(){
        int max=65535,index=0;
        for (int i=0;i<isdatavis.length;i++){
            //如果i对应的节点没被访问过，且它的最短距离小于max
            if (isdatavis[i]==0&&zuiduanjuli[i]<max){
                //更新距离
                max=zuiduanjuli[i];
                //更新新的出发点索引
                index=i;
            }
        }
        //更新index顶点被访问过
        isdatavis[index]=1;
        return index;
    }

    //输出最后结果
    public void show(){
        System.out.println("==========================");
        //输出被访问的节点
        for (int i:isdatavis){
            System.out.print(i+"");
        }
        System.out.println("");
        //输出前驱节点
        for (int i:ctdataqujd){
            System.out.print(i+"");
        }
        System.out.println("");
        //输出最短路径
        for (int i:zuiduanjuli){
            System.out.print(i+"");
        }
        System.out.println();
        char[]  data={'A','B','C','D','E'};
        //char[]  data={'A','B','C','D','E','F','G'};
        int comt=0;
        for (int i:zuiduanjuli){
            if (i!=65535){
                System.out.print(data[comt]+"("+i+")");
            }else {
                System.out.print("N");
            }
            comt++;
        }
    System.out.println("");
    }
    }

