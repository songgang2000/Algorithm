package 练习1.实验6;

/**
 * 教材P127应用题第1小题(小林旅游问题，属于0-1背包问题)，采用动态规划法求解,编程实现。
 */
public class test2 {
    public static void main(String[] args) {
        //物品重量
        int[] w = {2, 1, 3};
        //物品价值
        int[] v = {60, 50, 100};
        int[] x = new int[w.length];
        //背包最大承载重量
        int W = 4;
        //物品个数
        int n = w.length-1 ;
        test2 t2 = new test2();
        int knap = t2.knap(w, v, W, n);
        System.out.println(knap);
        int traceback = Traceback(w, v, x, W, n);
        System.out.println(traceback);
       dispbag(x,traceback,knap,n);


    }

    //输出最佳背包方案
    public static void dispbag(int[] x, int traceback, int knap, int n) {
        int i;
        System.out.println("\n最佳背包方案是:\n");
        for (i=0;i<=n;i++)
            if (x[i]==1) {
                System.out.println("选取第种物品"+ i);
            }
        System.out.println("总重量,总价值"+traceback+""+knap);
    }

    //回推最优
    public static int Traceback(int w[], int v[], int[] x, int W, int n) {
        //最大价值
        int[][] f = new int[n + 1][W + 1];
        int i;
        int r = W;
        int maxw = 0;                //存放最优解的总总重量
        for (i = n; i >= 0; i--) {
            if (i == 0) {
                x[i] = 1;
                maxw += w[0];
                continue;
            }else {
                if (f[i][r] != f[i - 1][r]) {//循环判断每个物品
                    x[i] = 1;            //选取物品i
                    maxw += w[i];        //累计总重量
                    r = r - w[i];
                } else {
                    x[i] = 0;//不选取物品i
                }
            }
        }
        return maxw;//返回总重量
    }


    //0-1背包

    /**
     *
     * @param w:物品容量
     * @param v：物品价值
     * @param W：背包最大容量
     * @param n：物品个数
     * @return：最大价值
     */
    public int knap(int w[], int v[], int W, int n) {
        //最大价值
        int[][] f = new int[n + 1][W + 1];

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
                if (r < w[i]) {
                    //将i物品上一个物品的价值赋给当前物品在此容量处的价值
                    f[i][r] = f[i - 1][r];
                } else {
                    //此处表示：当前i的物品的重量小于等于当前的背包容量
                    //如果当前物品i上一个物品对应此处的容量的最大价值<当前物品价值+物品剩余容量对应的上一个物品的最大价值
                    if (f[i - 1][r] < f[i - 1][r - w[i]] + v[i]) {
                        //就得到当前i物品对应的背包容量的最大价值
                        f[i][r] = f[i - 1][r - w[i]] + v[i];
                    } else {
                        //如果大于，那此处最大价值就是上一个物品在此处背包容量的最大价值
                        f[i][r] = f[i - 1][r];
                    }
                }
            }
        }
        //返回所有商品在背包容量为W时的最大价值
        return f[n][W];
    }

}
