package 练习1.算法实验5.生成哈夫曼树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造哈夫曼树
 */
public class test1 {
    public static void main(String[] args) {
        int[] arr={2,3,5,8};
        //调用自定义的哈夫曼树方法，生成哈夫曼树
        hafmantree(arr);
    }

    /**
     * ,构造哈夫曼数方法
     *
     * @param arry
     */
    public static void hafmantree(int[] arry) {
        //创建集合用于存放节点值
        List<Node> nlis = new ArrayList<Node>();
        //遍历集合
        for (int value : arry) {
            //将每个数组元素看作Node节点对象，并存入list集合内
            nlis.add(new Node(value));
        }
       /*
       由于集合中存入的是一个个的Node对象，而Node对象已经被我们声明成了按照从小到大的可排序对象。
       所以这里我们为可以通过Collections工具类中的sort方法进行排序
        */
        //循环条件：只有一个节点，即最终根节点
        while (nlis.size() > 1) {
            Collections.sort(nlis);
            //查看集合中还未被使用的节点
            System.out.println(nlis);
            //到了这里说明集合中的所有节点（权值）都是按照从小到大的顺序
            //获取最小的节点值（二叉树左节点）:子节点
            Node lileft = nlis.get(0);
            //获取第2小的节点值（二叉树右节点）：子节点
            Node lireght = nlis.get(1);
       /*创建新的Node节点对象（二叉树）:
            此节点对象是最小的两个节点之和所生成的最新的节点。三个节点可以看成一个二叉树，即：
             根节点(insternode对象)、左子节点(lileft.value)、右子节点(lireght.value)
        */
            Node insternode = new Node(lileft.value + lireght.value);
            //此二叉树的左节点
            insternode.left = lileft;
            //此二叉树的右节点
            insternode.right = lireght;
            //删除已经被处理过的节点
            nlis.remove(lileft);
            nlis.remove(lireght);
            //将最新的节点加入到list集合中
            nlis.add(insternode);
            //重新对最新的list数组进行排序
            Collections.sort(nlis);
        }
        //查看最终根节点
        System.out.println(nlis);
    }


}


/**
 * ,构造哈夫曼数节点类,
 * 此类也可以看成一个二叉树：根节点(Node对象)、左节子点(left)、右字节点(right)
 * 实现Comparable接口：说明此类是可通过Collections工具类排序的，
 */
class Node implements Comparable<Node> {
    int value;  //每个节点的值（权值）
    Node left;   //每个二叉树的左指向节点
    Node right;   //每个二叉树的右指向节点

    //构造方法，这里只需要初始化value实例变量，用于对象的赋值，而 left 与 right 本身就是此对象，可直接用于value赋值
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //支持从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}