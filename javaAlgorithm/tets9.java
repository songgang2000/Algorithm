package 练习1;



import java.util.Scanner;

public class tets9 {
    public static void main(String[] args) {
        int used=0; //系统已经用的内存
        int available=10; //当前系统还剩的可分配内存
        process[] a=new process[11]; //把进程放入数组中储存
        int arry=0; //记录当前存在的进程的个数
        Scanner input = new Scanner(System.in);

        for(;true;){
            System.out.println("结束请输入0,创建进程请输入1，撤销进程请输入2：");
            int command = input.nextInt();

            if (command==0){break;}

            if (command==1){
                if(used<10){
                    System.out.println("内存还剩"+available+",可分配内存!");
                    System.out.println("请输入进程名");
                    int name = input.nextInt();
                    System.out.println("请输入进程优先级");
                    int priority = input.nextInt();
                    System.out.println("请输入进程大小");
                    int size = input.nextInt();
                    if(size>available){
                        System.out.println("内存不足,无法创建");
                    }else {
                        process p=new process(name,priority,size);
                        a[arry]=p;
                        arry++;
                        used=used+p.size;
                        available=10-used;
                        System.out.print("我第"+arry+"个进程,我的名字叫"+a[arry-1].name+"，我占用"+a[arry-1].size+"内存"+"，我的优先级为"+a[arry-1].priority+"    ");
                        for(int i=0;i<used;i++){System.out.print("★");}
                        for (int j=0;j<available;j++){System.out.print("✰");}
                        System.out.print("    "+"内存已用"+used+",内存还剩"+available);
                        System.out.println();
                    }
                }

            }

            if (command==2){
                //把优先级小的排在前面，优先级大的放后面
                for(int i=0;i<arry-1;i++){
                    for(int j=0;j<arry-i-1;j++){
                        if(a[j].priority>a[j+1].priority){
                            process b=a[j];
                            a[j]=a[j+1];
                            a[j+1]=b;
                        }
                    }
                }
                //释放内存
                for (int q=1;q<arry+1;q++){
                    System.out.print("开始释放第"+q+"个进程，名为"+a[q-1].name+"，优先级为"+a[q-1].priority+"的进程：");
                    for(int x=0;x<used-a[q-1].size;x++){System.out.print("★");}
                    used=used-a[q-1].size;
                    for(int y=0;y<available+a[q-1].size;y++){System.out.print("✰");}
                    available=available+a[q-1].size;
                    System.out.print("    "+"当前已用"+used+",还剩"+available+"内存");
                    System.out.println();
                }
            }
        }
    }
  static class process{
        int name;  //进程名
        int priority; //数字越大代表优先级越高
        int size;   //进程大小
        process(int name,int priority,int size){
            this.name=name;
            this.priority=priority;
            this.size=size;
        }
    }
}




