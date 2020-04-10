package collection.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Stack;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BTreeNode {
    private BTreeNode left;
    private BTreeNode right;
    private Integer data;

    public BTreeNode(Integer data) {
        this.data = data;
    }

    public void add(Integer v) {
        // 如果当前节点没有值，就把数据放在当前节点上
        if (null == data)
            data = v;

            // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else {
            // 新增的值，比当前值小或者相同

            if (v - data <= 0) {
                if (null == left)
                    left = new BTreeNode();
                left.add(v);
            }
            // 新增的值，比当前值大
            else {
                if (null == right)
                    right = new BTreeNode();
                right.add(v);
            }

        }

    }

    public static void add(BTreeNode node, Integer v) {
        // 如果当前节点没有值，就把数据放在当前节点上
        if (null == node.getData())
            node.setData(v);
            // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else {
            // 新增的值，比当前值小或者相同
            if (v - node.getData() <= 0) {
                if (null == node.getLeft())
                    node.setLeft(new BTreeNode());
                add(node.getLeft(), v);
            }
            // 新增的值，比当前值大
            else {
                if (null == node.getRight())
                    node.setRight(new BTreeNode());
                add(node.getRight(), v);
            }

        }

    }

    public static void main(String[] args) {
        BTreeNode node = new BTreeNode(15);
        add(node, 12);
        add(node, 17);
        add(node, 20);
        add(node, 5);
        add(node, 14);
        BTree.levelOrder(node);
        BTree.leftView(node);
        BTree.rightView(node);
    }

}
