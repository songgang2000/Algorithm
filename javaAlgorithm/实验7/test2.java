package 练习1.实验7;

import java.util.Arrays;

public class test2 {


    public static void main(String[] args) {
        int[] w= {35,30,60,50};
        int[] v={10,40,30,50};
        int W=100;
        int n=w.length-1;
        int[] p=new int[n+1];
        int huisubag = huisubag(w, v, W, n, 0, 0, 0, p);
        System.out.println(Arrays.toString(p));
        System.out.println(huisubag);
    }


    public static int huisubag(int[] w,int[] v,int W,int n,int index,int lw,int lv,int[] p){
        int maxw=0;
        int maxv=0;
        //int[] x=new int[n];

        if (index>n){
            if (lw<=W && lv>=maxv){
                maxw=lw;
                maxv=lv;
//                for (int j=0;j<=n;j++){
//                    x[j]=p[j];
//                }
            }
        }
        //else {
            if (lw+w[index]<W){
                p[index]=1;
                huisubag(w,v,W,n,index+1,lw+w[index],lv+v[index],p);
            }else {
                p[index]=0;
                huisubag(w,v,W,n,index+1,lw,lv,p);
            }
        //}
        return maxv;
    }
}
