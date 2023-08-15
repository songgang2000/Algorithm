package 练习1.算法实验5.最小生成树克鲁斯卡尔算法;

import java.util.Arrays;

/**
 * 最小生成树：克鲁斯卡尔算法
 * 将无向连通图中的各个边从小到大排序，依次放入无边连通图中（不能出现环路）
 */
public class test1 {

    private int geshu;  //边的个数
    private char[] data; //存放节点
    private int[][] allquanzhi;   //存放带权边，邻接矩阵
    //标记不能相连通的两个节点，即不相邻的两个节点所形成的带权边。为Integer最大值
    private static final int maxlen = Integer.MAX_VALUE;

    public static void main(String[] args) {
      //  char[] data={'A','B','C','D','E'};
//        int[][] allquanzhi={
//                {0,20,maxlen,60,15},
//                {20,0,42,maxlen,maxlen},
//                {maxlen,42,0,30,maxlen},
//                {60,maxlen,30,0,23},
//                {15,maxlen,maxlen,23,0},
//                };
        char[] data={'A','B','C','D'};
        int[][] allquanzhi={
                {0,8,4,7},
                {8,0,maxlen,5},
                {4,maxlen,0,6},
                {7,5,6,0}
                    };
        test1 t=new test1(data,allquanzhi);
        //打印矩阵
        t.dayinjuzheng();
        Bian[] bians=t.andbian();
        System.out.println("排序前的边集合"+Arrays.toString(bians));
        t.biansort(bians);
        System.out.println("排序后的边集合"+Arrays.toString(bians));
        t.KrusKal();

    }

    //定义构造器
    public test1(char[] data, int[][] allquanzhi) {
        //初始化顶点数
        int spotgeshu = data.length;
        //初始化顶点（节点）
        this.data = data;
        //初始化 边
        this.allquanzhi = allquanzhi;
        //统计边的个数
        for (int i = 0; i < spotgeshu; i++) {
            for (int j = i+1; j < spotgeshu; j++) {
                if (this.allquanzhi[i][j] < maxlen) {
                    geshu++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void dayinjuzheng() {
        System.out.println(geshu);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                //格式化输出
                System.out.printf("%15d\t", +allquanzhi[i][j]);
            }
            System.out.println();
        }
    }


    /*
    对边排序：冒泡《从小到大》
     */
    public void biansort(Bian[] bian){
        for (int i=bian.length-1;i>0;i--){
            for (int j=0;j<i;j++){
                if (bian[j].bianquanzhi>bian[j+1].bianquanzhi){
                    Bian temp=bian[j];
                    bian[j]=bian[j+1];
                    bian[j+1]=temp;
                }
            }
        }
    }

    /*
    判断顶点是否存在
    返回顶点的索引
     */
public  int ismbian(char ch){
    for (int i=0;i<data.length;i++){
        if (data[i]==ch){
            return i;
        }
    }
    //如果ch不是顶点就返回-1
    return -1;
}

/*
将所以相邻边存入Bian[]中
 */
public Bian[] andbian(){
    int index=0;
    Bian[] bians=new Bian[geshu];
    for (int i=0;i<data.length;i++){
        for (int j=i+1;j<data.length;j++){
            if (allquanzhi[i][j]!=maxlen){
                bians[index++]=new Bian(data[i],data[j],allquanzhi[i][j]);
            }
        }
    }
    return bians;
}


/*
获取小标为i的顶点的终点，用于判断两个顶点的终点是否相同
star:存入的是顶点的终点的索引，初始化为{0，0，0，0，...},
i:顶点的索引
 */
public int getEnd(int[] star,int i){
    //动态的判断以此顶点的索引，
    //相连的前一个顶点的终点索引就是后一个顶点的索引
    //由于第一次的顶点的终点是自己的索引都
    //如果在star中找到了终点
    while (star[i]!=0){
        //就将此终点的值变为新的顶点的索引（找到此索引对应的顶点的终点，直到新的索引在star中没有找到终点，即为0，就将此索引返回为最初始的节点的终点索引）
        i=star[i];
    }
    //返回为终点
    return  i;
}


/*
最小生成树克鲁斯卡尔算法
 */
public  void KrusKal(){
    int index=0;    //最后结果数组的索引
    //存放最小生成树每个顶点的终点
    int[] star=new  int[geshu];
    //保存最终生成树
    Bian[] fin=new  Bian[geshu];
    //获取边的集合
    Bian[] andnewbian = andbian();
    //对所有边进行排序
    biansort(andnewbian);
    //遍历边集合。在判断的同时判断是否形成回路
    for (int i=0;i<geshu;i++){
        //上面andbian存放获得的边的类：bians[index++]=new Bian(data[i],data[j],allquanzhi[i][j]);
        //获取第一边的起点（顶点）,对应的是data[i]
        int h1=ismbian(andnewbian[i].da1);
        //获取第一条边的第二个顶点,对应的是data[j]
        int h2=ismbian(andnewbian[i].da2);
        //获取第一个顶点的终点
        int end = getEnd(star, h1);
        //获取第二个顶点的终点
        int end1 = getEnd(star, h2);
        //判断回路:如果没有构成回路
        if (end!=end1){
            //设置end1为已有生成数的终点
            star[end]=end1;
            //此时最终生成树有了一条边
            fin[index++]=andnewbian[i];
        }
    }
    System.out.println("最后生成树："+Arrays.toString(fin));
    System.out.println("最小生成树:");
    for (int i=0;i<index;i++){
        System.out.println(fin[i]);
    }
}

}


/*
边类
 */
class Bian{
    //组成一条边的两个节点
    char da1;
    char da2;
    //边权值
    int bianquanzhi;

    public Bian(char da1, char da2, int bianquanzhi) {
        this.da1 = da1;
        this.da2 = da2;
        this.bianquanzhi = bianquanzhi;
    }

    @Override
    public String toString() {
        return "bian{" +
                "da1=" + da1 +
                ", da2=" + da2 +
                ", bianquanzhi=" + bianquanzhi +
                '}';
    }
}
