package 练习1.实验6;
/**
 * 教材P127应用题第1小题(小林旅游问题，属于0-1背包问题)，采用动态规划法求解,编程实现。
 */
public class test3 {
    public static void main(String[] args) {
        //物品重量
        int[] w = {3, 1, 2,2,2};
        //物品价值
        int[] v = {10, 3, 9,5,6};
        //标记物品是否被选择，选择为1，否则为0
        int[] x = new int[w.length];
        //背包最大承载重量
        int W = 6;
        //物品个数
        int n = w.length;
        int knap = knap(w, v, W, n);
        System.out.println(knap);
    }

    /**
     *
     * @param w:物品容量
     * @param v：物品价值
     * @param W：背包最大容量
     * @param n：物品个数
     * @return：最大价值
     */
    public static int knap(int w[], int v[], int W, int n) {
        //最大价值
        int[][] f = new int[n + 1][W + 1];
        int[] x = new int[n+1];
        //置边界条件f(i,0)=0:第i个物品，背包容量为0的情况下价值为0
        for (int i = 0; i <= n; i++) {
            f[i][0] = 0;
        }

        //置边界条件f(0,r)=0：第0个物品的所以不管背包容量为多大都价值为0
        for (int r = 0; r <= W; r++) {
            f[0][r] = 0;
        }
        //遍历第i个物品
        for (int i = 1; i <= n; i++) {
            //背包容量++
            for (int r = 1; r <= W; r++) {
                //如果当前背包容量小于当前第i个物品的重量
                if (r < w[i-1]) {
                    //将i物品上一个物品的价值赋给当前物品在此容量处的价值
                    f[i][r] = f[i - 1][r];
                } else {
                    //此处表示：当前i的物品的重量小于等于当前的背包容量
                    //如果当前物品i上一个物品对应此处的容量的最大价值<当前物品价值+物品剩余容量对应的上一个物品的最大价值
                    if (f[i - 1][r] < f[i - 1][r - w[i-1]] + v[i-1]) {
                        //就得到当前i物品对应的背包容量的最大价值
                        f[i][r] = f[i - 1][r - w[i-1]] + v[i-1];
                    } else {
                        //如果大于，那此处最大价值就是上一个物品在此处背包容量的最大价值
                        f[i][r] = f[i - 1][r];
                    }
                }
            }
        }

        /*
        从后向前，那些些商品被选择了，选择[i]=1;否则[i]=0
         */
       int j=W;
        for (int i=n;i>0;i--){
            if (f[i][j]>f[i-1][j]){
                x[i]=1;
                j=j-w[i-1];
            }else {
                x[i]=0;
            }
        }

        //返回所有商品在背包容量为W时的最大价值
        for (int i=0;i<x.length;i++){
            if (x[i]==1){
                System.out.println("选择第"+i+"个商品");
            }
        }
        System.out.println("背包总价值："+f[n][W]);
        return f[n][W];
    }
}
