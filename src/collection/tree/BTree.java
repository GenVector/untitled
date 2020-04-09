package collection.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        //leftview(bTree.root, stack, 0);
        leftView2(bTree.root, 0, stack);
        System.out.println(stack);
        posOrder(bTree.root);
        System.out.println();
        inOrder(bTree.root);
        //afterOrder(bTree.root);
        System.out.println();

        //int[][][]array=new int[1][0][-1];
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

    public static void leftView2(BTreeNode node, int level, Stack<Integer> stack) {
        if (node == null)
            return;
        if (stack.size() == level) {
            stack.push(node.getData());
        }
        leftView2(node.getLeft(), level + 1, stack);
        leftView2(node.getRight(), level + 1, stack);
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
        Stack<BTreeNode> stack = new Stack<>();
        stack.push(node);
        BTreeNode c;
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.getLeft() != null && node != c.getLeft() && node != c.getRight()) {
                stack.push(c.getLeft());
            } else if (c.getRight() != null && node != c.getRight()) {
                stack.push(c.getRight());
            } else {
                System.out.print(c.getData() + " ");
                stack.pop();
                node = c;
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
        if (node == null)
            return;
        if (node.getLeft() != null && node.getRight() != null) {
            BTreeNode left = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(left);
        } else if (node.getLeft() != null) {
            node.setRight(node.getLeft());
        } else if (node.getRight() != null) {
            node.setLeft(node.getRight());
        }
        mirrorRecursively(node.getLeft());
        mirrorRecursively(node.getRight());
    }

    public static int level(BTreeNode node, int level) {
        if (node == null)
            return level;
        return Math.max(level(node.getLeft(), level++), level(node.getRight(), level++));
    }

    //打印左视图
    public static void leftview(BTreeNode root, Stack<Integer> stack, int level) {
        if (root == null)
            return;
        if (level == stack.size()) {//判断是不是每一层的第一个节点
            stack.push(root.getData());
        }
        leftview(root.getLeft(), stack, level + 1);
        leftview(root.getRight(), stack, level + 1);
    }

    //打印右视图
    public static void rightview(BTreeNode root, Stack<Integer> stack, int level) {
        if (root == null)
            return;
        if (level == stack.size()) {//判断是不是每一层的第一个节点
            stack.push(root.getData());
        }
        rightview(root.getRight(), stack, level + 1);
        rightview(root.getLeft(), stack, level + 1);
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
            System.out.println(node.getData() + " ");
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
