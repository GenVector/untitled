package collection.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BTree {

    private BTreeNode root;

    public static void main(String[] args) {
        BTree bTree = NewTree();
        //afterOrder(bTree.getRoot());
        System.out.println(level(bTree.getRoot(), 0));
        Stack<Integer> stack = new Stack<>();
        leftView(bTree.root);
        System.out.println(stack);
        posOrder(bTree.root);
        System.out.println();
        inOrder(bTree.root);
        //afterOrder(bTree.root);
        System.out.println();

        //int[][][][] array=new int[1][0][-1][];
        findPath(bTree.getRoot(), 65);
        System.out.println(ifAbsolute(bTree.getRoot()));


    }

    /*
                50
            5      15
          10     18  19
         2  21         25
                     80
     */
    public static BTree NewTree() {
        BTree bTree = new BTree(new BTreeNode(50));
        bTree.getRoot().setLeft(new BTreeNode(5));
        bTree.getRoot().setRight(new BTreeNode(15));
        bTree.getRoot().getLeft().setLeft(new BTreeNode(10));
        bTree.getRoot().getLeft().getLeft().setLeft(new BTreeNode(2));
        bTree.getRoot().getLeft().getLeft().setRight(new BTreeNode(21));
        bTree.getRoot().getRight().setLeft(new BTreeNode(18));
        bTree.getRoot().getRight().setRight(new BTreeNode(19));
        bTree.getRoot().getRight().getRight().setRight(new BTreeNode(25));
        bTree.getRoot().getRight().getRight().getRight().setLeft(new BTreeNode(80));
        return bTree;
    }

    //先序遍历
    public static void preOrder(BTreeNode node) {
        if (node != null)
            System.out.print(node.getData() + " ");
        else
            return;
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public static void preOrder2(BTreeNode node) {
        Stack<BTreeNode> nodeStack = new Stack<>();
        nodeStack.push(node);
        while (nodeStack.size() > 0) {
            BTreeNode curr = nodeStack.pop();
            if (curr != null)
                System.out.println(curr.getData() + " ");
            else
                return;
            if (curr.getRight() != null)
                nodeStack.push(curr.getRight());
            if (curr.getLeft() != null)
                nodeStack.push(curr.getLeft());
        }
    }

    public static void preOrder3(BTreeNode node) {
        Stack<BTreeNode> nodeStack = new Stack<>();

        while (nodeStack.size() > 0 || node != null) {

            if (node != null) {
                System.out.println(node.getData() + " ");
                nodeStack.push(node);
                node = node.getLeft();
            } else {
                node = nodeStack.pop();
                node = node.getRight();
            }
        }
    }

    //中序遍历
    public static void inOrder(BTreeNode node) {
        Stack<BTreeNode> nodeStack = new Stack<>();
        while (nodeStack.size() > 0 || node != null) {
            if (node != null) {
                nodeStack.push(node);
                node = node.getLeft();
            } else {
                node = nodeStack.pop();
                System.out.print(node.getData() + " ");
                node = node.getRight();
            }
        }
    }

    /*
                50
            5      15
          10     18  19
         2  21         25
                     80
     */
    //后序遍历
    public static void posOrder(BTreeNode node) {
        if (node == null)
            return;
        System.out.print("后序遍历---");
        Stack<BTreeNode> stack = new Stack<>();
        stack.push(node);
        BTreeNode cur;
        BTreeNode pre = node;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.getLeft() != null && pre != cur.getLeft() && pre != cur.getRight()) {
                stack.push(cur.getLeft());
            } else if (cur.getRight() != null && pre != cur.getRight()) {
                stack.push(cur.getRight());
            } else {
                System.out.print(cur.getData() + " ");
                stack.pop();
                pre = cur;
            }
        }
        System.out.println();
    }

    //层序遍历
    public static void levelOrder(BTreeNode node) {
        LinkedList<BTreeNode> nodeList = new LinkedList<>();
        //add first
        //nodeList.push(node);
        nodeList.offer(node);
        while (nodeList.size() > 0) {
            //peek do not rm
            BTreeNode curr = nodeList.poll();
            if (curr != null)
                System.out.println(curr.getData() + " ");
            else
                return;
            if (curr.getLeft() != null)
                nodeList.offer(curr.getLeft());
            if (curr.getRight() != null)
                nodeList.offer(curr.getRight());

        }

    }

    //二叉树镜像
    public static void mirrorRecursively(BTreeNode node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null && node.getRight() != null) {
            BTreeNode left = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(left);
        } else if (node.getLeft() != null) {
            node.setRight(node.getLeft());
            node.setLeft(null);
        } else if (node.getRight() != null) {
            node.setLeft(node.getRight());
            node.setRight(null);

        }
        mirrorRecursively(node.getLeft());
        mirrorRecursively(node.getRight());
    }

    //求二叉树的深度
    public static int level(BTreeNode node, int level) {
        if (node == null)
            return level;
        return Math.max(level(node.getLeft(), level++), level(node.getRight(), level++));
    }

    public static void leftView(BTreeNode node) {
        Stack<Integer> stack = new Stack<>();
        leftView(node, stack, 0);
        System.out.println("左视图");
        for (Integer num : stack) {
            System.out.print(num + " ");
        }
    }

    public static void rightView(BTreeNode node) {
        Stack<Integer> stack = new Stack<>();
        rightView(node, stack, 0);
        System.out.println("右视图");
        for (Integer num : stack) {
            System.out.print(num + " ");
        }
    }

    //打印左视图
    public static void leftView(BTreeNode root, Stack<Integer> stack, int level) {
        if (root == null)
            return;
        if (level == stack.size()) {//判断是不是每一层的第一个节点
            stack.push(root.getData());
        }
        leftView(root.getLeft(), stack, level + 1);
        leftView(root.getRight(), stack, level + 1);
    }

    //打印右视图
    public static void rightView(BTreeNode root, Stack<Integer> stack, int level) {
        if (root == null)
            return;
        if (level == stack.size()) {//判断是不是每一层的第一个节点
            stack.push(root.getData());
        }
        rightView(root.getRight(), stack, level + 1);
        rightView(root.getLeft(), stack, level + 1);
    }

    //打印从根节点开始和为K的所有节点
    public static void findPath(BTreeNode node, int k) {
        if (node == null) return;
        Stack<Integer> stack = new Stack<>();
        findPath(node, k, stack);
    }


    private static void findPath(BTreeNode node, int k, Stack<Integer> path) {
        if (node == null) return;
//		if(node.getLeftNode()==null&&node.getRightNode()==null) {
        if (node.getData() == k) {
            System.out.println("路径开始");
            for (int i : path)
                System.out.print(i + ",");
            System.out.println(node.getData());
//			}
        } else if (node.getData() < k) {
            path.push(node.getData());
            findPath(node.getLeft(), k - node.getData(), path);
            findPath(node.getRight(), k - node.getData(), path);
            path.pop();
        }

    }

    //判断是否是完全二叉树
    public static boolean ifAbsolute(BTreeNode node) {
        boolean rigth = false;
        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (queue.size() > 0) {
            node = queue.poll();
            if (node.getLeft() != null)
                queue.offer(node.getLeft());
            if (node.getRight() != null)
                queue.offer(node.getRight());
            if (node.getRight() != null && node.getLeft() == null)
                return false;
            if (node.getRight() == null) {
                rigth = true;
                continue;
            }
            if (rigth && (node.getLeft() != null || node.getRight() != null))
                return false;
        }
        return true;
    }


}

//树中的节点必须不相同
class Question6 {
    private static boolean flag = false;  //数据正确与错误的标志,true代表数据错误

    //定义一个内部节点类
    public static class Node<E> {
        public Node left;
        public Node right;
        public E e;

        public Node(E e) {
            this.e = e;
        }
    }

    /**
     * 根据先序遍历和中序遍历构建二叉树,并返回根节点
     *
     * @param preOrder 先序遍历数据存储的数组
     * @param preStart 先序遍历数组的起始位置
     * @param preEnd   先序遍历数组的结束位置
     * @param midOrder 中序遍历数据存储的数组
     * @param midStart 中序遍历数组的起始位置
     * @param midEnd   中序遍历数组的结束位置
     * @return
     */
    public static Node<Integer> reBuild_BinaryTree(int[] preOrder, int preStart, int preEnd, int[] midOrder, int midStart, int midEnd) {
        //增加鲁棒性
        if (preOrder == null || midOrder == null)
            throw new RuntimeException("Null point Exception");
        if (preOrder.length != midOrder.length || preOrder.length == 0 || midOrder.length == 0)
            throw new RuntimeException("date not correct");
        //递归结束标志,如果大于则说明数组越界了,空节点,直接返回
        if (midStart > midEnd && preStart > preEnd) return null;
        //等于说明遍历到了最后一个节点,构建节点返回
        if (midStart == midEnd && preStart == preEnd) {
            //此时midStart和preStart理应是同一个数字,但是如果不是呢?那就说明数据不正确
            if (preOrder[preStart] != midOrder[midStart]) {
                System.out.println("该数据不是一个正确的二叉树的先序遍历和中序遍历");
                flag = true; //
                return null;
            }
            return new Node(midOrder[midEnd]);
        }
        int mid;//指向中序遍历中每一次的跟节点.
        int pre;//指向先序遍历中左子树的结束位置
        //找出中序遍历中根节点的位置
        for (mid = midStart; mid < midEnd; mid++) {
            if (midOrder[mid] == preOrder[preStart]) break;
        }
        //如果输入的数据不正确,应该输出提示
        if (mid == midOrder.length) {
            System.out.println("该数据不是一个正确的二叉树的先序遍历和中序遍历");
            flag = true; //
            return null;
        }
        Node node = new Node(preOrder[preStart]);
        //找出先序遍历中左子树的结束位置
        pre = preStart + (mid - midStart);
        //递归构建二叉树,这里很容易出错,要注意
        //preOrder的左子树:preStart+1,pre  右子树:pre+1,preEnd;
        //midOrder的左子树:midStart,mid-1     右子树:mid+1,midEnd
        node.left = reBuild_BinaryTree(preOrder, preStart + 1, pre, midOrder, midStart, mid - 1);
        node.right = reBuild_BinaryTree(preOrder, pre + 1, preEnd, midOrder, mid + 1, midEnd);
        return node;
    }

    /**
     * 后序遍历输出二叉树
     *
     * @param head
     */
    public static void postOrder_print(Node head) {
        if (flag) return;//数据错误,直接返回
        if (head == null) return;
        postOrder_print(head.left);
        postOrder_print(head.right);
        System.out.println(head.e);
    }

    public static void main(String[] args) {
        int[] preOrder = {1, 3, 5, 7};
        int[] midOrder = {3, 1, 7, 5};
        Node<Integer> head = reBuild_BinaryTree(preOrder, 0, preOrder.length - 1, midOrder, 0, midOrder.length - 1);
        postOrder_print(head);
    }
}