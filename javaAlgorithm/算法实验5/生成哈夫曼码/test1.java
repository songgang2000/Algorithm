package 练习1.算法实验5.生成哈夫曼码;

import java.util.*;

/**
 * 构造哈夫曼数+生成哈夫曼编码,编程实现。
 */
public class test1 {
    public static void main(String[] args) {
        //需要转换为哈夫曼编码的字符串
        String valus="asdsgddbhj ,sjsh";
        //将字符串存以node对象存入list集合中
        List<Node> list = ListAndNode(valus);
        //生成哈夫曼树
        Node node = HFMTree(list);
        //得到哈夫曼编码
        HFMTable(node,"",andindex);
        System.out.println(yezijied);   //{32=1010, 97=1011, 98=1100, 115=01, 100=111, 103=1101, 104=001, 106=100, 44=000}

    }

/*
第四步：
创建哈夫曼编码表：将叶子节点以0、1表示。0===》向左子节点走。1===》向右子节点走
    yezijied:存放叶子节点对应的哈夫曼编码。此集合作业与全局
    andindex：拼接编码。(拼接对应的0或1，形参最终的哈夫曼编码)
 */

    static Map<Byte,String> yezijied=new HashMap<>();
    static  StringBuilder andindex=new StringBuilder();

    /**
     *
      * @param node：节点
     * @param index：路径表示：左路径为0.右路劲为1
     * @param sub：拼接路径，使其成为对应叶子节点的哈夫曼码
     */
public static void HFMTable(Node node,String index,StringBuilder sub){
    //
    StringBuilder gitindex=new StringBuilder(sub);
    //拼接路径
    gitindex.append(index);
    //如果节点为空就不需要处理
    if (node!=null) {
        //如果当前节点不是叶子节点
        if (node.value == null) {
            //如果节点不为空就递归其左边节点。并设置向左为0
            HFMTable(node.left, "0", gitindex);
            //如果节点不为空就递归其右边节点。并设置向右为1
            HFMTable(node.right, "1", gitindex);
        } else {
            //为叶子节点就将其存入map集合中
            yezijied.put(node.value, gitindex.toString());
        }

    }
}

    /*
    第三步：
    @param nodes:已经存入list集合中的Node节点
    创建字符串的哈夫曼树结构
     */
    public static Node HFMTree(List<Node> nodes){
        //循环条件：节点数必须大于1.如果等于1就是一个节点(根节点),没有分支
        while (nodes.size()>1){
            //排序list集合，根据权值(节点个数)从小到大排序
            Collections.sort(nodes);

            /*
            创建一个二叉树：
            feiyezijied:根节点
            nodeleft：左子节点
            noderight：右子节点
             */
            //得到权值最小的两个节点.这两个节点分别可看作左右两个子节点
            Node nodeleft = nodes.get(0);
            Node noderight = nodes.get(1);
            //创建新的Node对象：这可以想象为两个叶子节点生成的根节点，
            // 由于哈夫曼数的原理，需要编码的值是叶子节点，而叶子节点上的父节点只是通过叶子节点虚拟创建的节点，
            // 是为了形成一整颗完整的树。所以它是没有字符串原始值，，其可用两个字节的权值之和标记
            Node feiyezijied=new Node(null,nodeleft.quanzhi+noderight.quanzhi);
            //Node对象的左字节点
            feiyezijied.left=nodeleft;
            //Node对象的右字节点
            feiyezijied.right=noderight;

            //删除原集合中的以使用的节点对象.即上面已经每次获得的集合中两个最小的节点
            nodes.remove(nodeleft);
            nodes.remove(noderight);

            //将新创建的Node节点加入list集合中
            nodes.add(feiyezijied);
            //重新对list集合排序
            Collections.sort(nodes);
        }
        //返回最终根节点
        return nodes.get(0);
    }



    /*
    第二步：
    @param valus:传入需要编码的字符串，将其变成节点
    将需要编码的字符串，每个原始值(ASCIIM码)以节点(Node)对象形式传入list集合中。
    而节点对象Node初始化了value与quanzhi，所以节点对象是包括这两个值，所以将每个节点对象当作一个map.
    设k=value、v=quanzhi
     */
    public static List<Node> ListAndNode(String valus){
        //将字符对象存入byte数组。
        byte[] bytes = valus.getBytes();
        //创建List集合
        List<Node> nodes=new ArrayList<>();
        //创建Map集合
        Map<Byte,Integer> node=new HashMap<>();
        //遍历bytes数组，得到每个字符串的原始值
        for (byte by:bytes){
            //先试着通过传入的第一个k获取v
            Integer index = node.get(by);
            //如果map集合中此原始值对应的个数还没有
            if (index==null){
                node.put(by,1);
            }else {
                node.put(by,index+1);
            }
        }
        //遍历map集合，并将每次遍历的元素，以Node对象的形式存入list集合
        for (Map.Entry<Byte,Integer> n:node.entrySet()){
            nodes.add(new Node(n.getKey(),n.getValue()));
        }
        //最后返回此list集合
        return nodes;
    }

}

  /*
  第一步：
    节点类:其本身可可看作一个概念性的二叉树
    Node对象本身可看作是一个二叉树的根节点
    实现Comparable接口：泛型规定此接口作用与此Node节点，说明此类是可排序的，通过' Collections.sort()'
     */

class Node implements Comparable<Node>{
    Byte value;     //原始值：字符本身的ASCIIM码。因为一段字符串中有许多相同的字符，但相同字符却对应这一个ASCIIM码
    int quanzhi;    //此字符value在一段字符串中出现的次数
    Node left;      //Node对象看作是二叉树的根节点，那么这就是此二叉树的左子节点
    Node right;     //Node对象看作是二叉树的根节点，那么这就是此二叉树的右边子节点

    //构造器初始化 value 、quanzhi。
    public Node(Byte value, int quanzhi) {
        this.value = value;
        this.quanzhi = quanzhi;
    }

    //重写toString：因为我们需要拿到这两个值
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", quanzhi=" + quanzhi +
                '}';
    }

    //实现Comparable接口中的方法：手动设置排序规则
    @Override
    public int compareTo(Node o) {
        //设置为通过权值从小到大排序
        return    this.quanzhi-o.quanzhi;
    }

    //前序遍历
    public void qxbl(){
        //先输出当前节点，也就是根节点
        System.out.println(this);
        //如果左子节点不是null节点，就递归遍历输出左子节点.null表示不是叶子节点
        if (this.left!=null){
            this.left.qxbl();
        }
        //同样递归右子节点
        if (this.right!=null){
            this.right.qxbl();
        }
    }
}