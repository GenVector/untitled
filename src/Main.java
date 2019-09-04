import java.net.URLEncoder;
import java.time.Instant;
import java.util.*;

public class Main {


    public static void main(String[] args) {

    }

    public static void uRLEncoderTest() throws Exception {
        String encode = URLEncoder.encode("宋磊", "UTF-8");
        encode += " : " + URLEncoder.encode("蔡凤娟", "UTF-8");
        for (int i = 0; i < 100; i++) {
            System.out.println((int) (1 + Math.random() * (10 - 1 + 1)));
        }
    }



    public int getMax(int[] a) {
        int len = a.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++)
            for (int j = i + 1; j < len; j++) {
                if (a[i] - a[j] > max)
                    max = a[i] - a[j];
            }
        return max;
    }

    public String compute(String input) {
        String str[];
        str = input.split(" ");
        Stack<Double> stack = new Stack<>();
        double m = Double.parseDouble(str[0]);
        stack.push(m);
        for (int i = 1; i < str.length; i++) {
            if (i % 2 == 1) {
                switch (str[i]) {
                    case "+": {
                        double help = Double.parseDouble(str[i + 1]);
                        stack.push(help);
                    }
                    case "-": {
                        double help = Double.parseDouble(str[i + 1]);
                        stack.push(-help);
                    }
                    case "*": {
                        double help = Double.parseDouble(str[i + 1]);
                        double ans = stack.peek();//取出栈顶元素
                        stack.pop();//消栈
                        ans *= help;
                        stack.push(ans);
                    }
                    case "/": {
                        double help = Double.parseDouble(str[i + 1]);
                        double ans = stack.peek();
                        stack.pop();
                        ans /= help;
                        stack.push(ans);
                    }
                }
            }
        }
        double ans = 0d;
        while (!stack.isEmpty()) {
            ans += stack.peek();
            stack.pop();
        }
        String result = String.valueOf(ans);
        return result;
    }

    public boolean isLoop(Node head) {
        Node fast = head;
        Node slow = head;
        if (fast == null) {
            return false;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }

        return !(fast == null || fast.next == null);
    }

    public Node FindLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow)
                break;
        }
        if (fast == null || fast.next == null)
            return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public int getMaxDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            int left = getMaxDepth(root.left);
            int right = getMaxDepth(root.right);
            return 1 + Math.max(left, right);
        }
    }

    public int getMaxWidth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new ArrayDeque<>();
        int maxWitdth = 1; // 最大宽度
        queue.add(root); // 入队

        while (true) {
            int len = queue.size(); // 当前层的节点个数
            if (len == 0)
                break;
            while (len > 0) {// 如果当前层，还有节点
                TreeNode t = queue.poll();
                len--;
                if (t.left != null)
                    queue.add(t.left); // 下一层节点入队
                if (t.right != null)
                    queue.add(t.right);// 下一层节点入队
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
        }
        return maxWitdth;
    }

    public void preOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                System.out.print(p.data.toString() + " ");
                stack.push(p);
                p = p.left;
            } else {
                System.out.print("^ ");
                p = stack.pop();
                p = p.right;
            }
        }
        System.out.println("^");
    }


    class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public String data;
    }

    class Node {
        public Node next;
        public String data;

    }
}