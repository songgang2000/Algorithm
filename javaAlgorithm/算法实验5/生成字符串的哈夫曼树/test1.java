package 练习1.算法实验5.生成字符串的哈夫曼树;

import java.util.*;

public class test1 {
    public static void main(String[] args) {
        //需要转换为哈夫曼编码的字符串
        String str = "i like you";

        /*
             //char字符
            char a='i';
            //将char字符转换为ASCII码
            int ascii=a;    //105
         */

        //将字符串转换为byte数组，里面存入的是每个字符的ASCII编码，
        byte[] bytes = str.getBytes();
//        for (byte b : bytes) {
//            System.out.println();
//        }
        List<Node> list = gitNode(bytes);
        hfmbm(list);


    }

    /*
    此方法将每个字符以Node对象的形式存入list集合中，形式：List{Node[valu,fr],
                                        Node[valu,fr],
                                         Node[valu,fr]}
        其中  Node以map键值对的形式存入到是：k=节点字符本身(asciim码)、v=权值(当前字符出现的次数)
     */
    public static List<Node> gitNode(byte[] bytes) {
        //创建list集合，用于存放权值
        List<Node> list = new ArrayList<>();
        //创建map集合，用于存储Node对象
        Map<Byte, Integer> mnode = new HashMap<>();
        for (byte by : bytes) {
            Integer nos = mnode.get(by);
            if (nos == null) {
                mnode.put(by, 1);
            } else {
                mnode.put(by, nos + 1);
            }
        }
        for (Map.Entry<Byte, Integer> mps : mnode.entrySet()) {
            list.add(new Node(mps.getKey(), mps.getValue()));
        }

        return list;
    }

    /*
    哈夫曼编码方法
     */
    public static void hfmbm(List<Node> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            Node nleft = list.get(0);
            Node nrigt = list.get(1);
            //创建Node对象
            Node nodenew = new Node(null, nleft.fr + nrigt.fr);
            nodenew.left = nleft;
            nodenew.right = nrigt;

            //删除使用过的节点权值
            list.remove(nleft);
            list.remove(nrigt);

            //将新的权值加入到list集合中
            list.add(nodenew);
            //重新排序集合：从小到大
            Collections.sort(list);
            System.out.println(list);
        }

    }

}
/**
 * 节点类
 * Node本身对象可看作一个简单的二叉树
 */
class Node implements Comparable<Node>{
    Byte value; //节点的字符本身，即原始值(ascii值)
    int fr; //权值：这里是指字符出现的次数
    Node left;  //二叉树左子节点
    Node right; //二叉树右子节点

    public Node(Byte value, int fr) {
        this.value = value;
        this.fr = fr;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", fr=" + fr +
                '}';
    }

    //支持从小到大遍历，遍历权值
    @Override
    public int compareTo(Node o) {
        return this.fr - o.fr;
    }

}