package codeTest.arr;

import java.util.LinkedList;
import java.util.Stack;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkNode node1 = new LinkNode(1);
        node1.setNext(new LinkNode(2));
        node1.getNext().setNext(new LinkNode(3));
        node1.getNext().getNext().setNext(new LinkNode(4));
        node1.getNext().getNext().getNext().setNext(new LinkNode(5));
        node1.getNext().getNext().getNext().getNext().setNext(new LinkNode(6));
        node1.getNext().getNext().getNext().getNext().getNext().setNext(new LinkNode(7));
        LinkNode node2 = new LinkNode(1);
        node2.setNext(new LinkNode(9));
//        System.out.println(node1.toString());
//        node1 = reverseLinked(node1);
        System.out.println(node1);
        System.out.println(node2);

//        Stack<Integer> stack2 = node2.toReverseString(node2, new Stack<Integer>());
//        Stack<Integer> stack1 = node1.toReverseString(node1, new Stack<Integer>());
//        Stack<Integer> stack3 = node1.toReverseString(addTwoList(node1, node2), new Stack<Integer>());
//        LinkNode node3 = addTwoList(node1, node2);
        System.out.println(mergeTwoLists(node1, node2));
//        while (!stack1.isEmpty()) {
//            System.out.print(stack1.pop() + " ");
//        }
//        System.out.println();
//        while (!stack2.isEmpty()) {
//            System.out.print(stack2.pop() + " ");
//        }
//        System.out.println();
//        while (!stack3.isEmpty()) {
//            System.out.print(stack3.pop() + " ");
//        }
    }

    //翻转链表
    public static LinkNode reverseLinked(LinkNode head) {
        //之前的头结点
        LinkNode pNode = head;
        //翻转之后的头结点
        LinkNode reverseHead = head;
        //之前的节点,翻转之后为Next节点。头结点之前节点为NULL
        LinkNode prev = null;
        while (pNode != null) {
            //后继节点
            LinkNode next = pNode.getNext();
            if (next == null)
                reverseHead = pNode;
            pNode.setNext(prev);
            prev = pNode;
            pNode = next;
        }
        return reverseHead;
    }

    //K位翻转链表
    LinkNode newTail = null;

    public void reverse(LinkNode head, LinkNode tail) {
        //head为头结点，不带有任何参数，并且能调用这个函数，说明至少有两个及以上的节点需要逆置，
        //所以第一次的cur代表第二个元素，不会空指针
        LinkNode cur = head.next.next;//准备前插的元素
        LinkNode curNext = cur.next;//前插的下一个元素，可能为空
        while (cur != null) {
            //不需要判断cur和curNext为空的情况，while里面可以直接写true
            //因为tail是本次逆置部分的最后一个元素，cur前插肯定会遇见它
            cur.next = head.next;//连接后面
            head.next = cur;//连接头结点
            if (cur == tail) {
                newTail.next = curNext;//将新链表的尾部与下一次k链的第一个元素进行连接
                break;
            }
            cur = curNext;//下一个需要头插的元素
            curNext = cur.next;
        }

    }

    public LinkNode reverseKGroup(LinkNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        LinkNode newHead = new LinkNode(-1);//每k个反转链表的前置头结点
        newHead.next = head;
        LinkNode tail = head;//每一组链表的尾结点
        head = newHead;
        int n = 1;
        while (tail != null) {
            if (n % k == 0) {
                newTail = newHead.next;//新的尾结点
                reverse(newHead, tail);
                newHead = newTail;//上一组的尾结点做下一组的头结点
                tail = newTail;
            }
            tail = tail.next;
            n++;
        }
        return head.next;
    }

    //两个链表模拟加法运算
    public static LinkNode addTwoList(LinkNode num1, LinkNode num2) {
        int size = Math.max(num1.size(), num2.size());
        LinkNode newNum = new LinkNode();
        LinkNode returnNum = newNum;
        Integer lev = 0;
        for (int i = 0; i < size; i++) {
            if (num1 == null || num1.getData() == null) {
                newNum.setData(num2.getData() + lev);
                lev = 0;
                newNum.setNext(new LinkNode());
                newNum = newNum.getNext();
            } else if (num2 == null || num2.getData() == null) {
                newNum.setData(num1.getData() + lev);
                lev = 0;
                newNum.setNext(new LinkNode());
                newNum = newNum.getNext();
            } else {
                newNum.setData((num1.getData() + num2.getData() + lev) % 10);
                lev = (num1.getData() + num2.getData() + lev) / 10;
                newNum.setNext(new LinkNode());
                newNum = newNum.getNext();
            }

            if (num1 != null)
                num1 = num1.getNext();
            if (num2 != null)
                num2 = num2.getNext();
        }
        return returnNum;
    }

    //合并链表
    //输入：1->2->4, 1->3->4
    //输出：1->1->2->3->4->4
    public static LinkNode mergeTwoLists(LinkNode list1, LinkNode list2) {
        LinkNode node1 = list1, node2 = list2;
        if (node1 == null)
            return node2;
        if (node2 == null)
            return node1;

        LinkNode newNode = new LinkNode();
        LinkNode head =newNode;
        while (node1 != null || node2 != null) {
            if (node1 != null && node2 == null) {
                newNode.setData(node1.getData());
                newNode.setNext(new LinkNode());
                newNode = newNode.getNext();
                node1 = node1.getNext();
            } else if (node1 == null && node2 != null) {
                newNode.setData(node2.getData());
                newNode.setNext(new LinkNode());
                newNode = newNode.getNext();
                node2 = node2.getNext();
            } else if (node1.getData() > node2.getData()) {
                newNode.setData(node2.getData());
                newNode.setNext(new LinkNode());
                newNode = newNode.getNext();
                node2 = node2.getNext();
            } else if (node1.getData() <= node2.getData()) {
                newNode.setData(node1.getData());
                newNode.setNext(new LinkNode());
                newNode = newNode.getNext();
                node1 = node1.getNext();
            }

        }

        newNode.setNext(null);
        return head;
    }
}

class LinkNode {
    public LinkNode next;
    private Integer data;
    private Integer size;

    public LinkNode(Integer data) {
        this.data = data;
        size = 1;
    }

    public LinkNode() {
        size = 1;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode node) {
        next = node;
        size++;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public String toString() {
        if (data != null) {
            String string = data.toString() + ",";
            if (next != null)
                return string + next.toString();
            else
                return string;
        }
        return "";
    }


    public Stack toReverseString(LinkNode node, Stack<Integer> stack) {
        if (node.getData() != null)
            stack.push(node.getData());
        if (node.getNext() != null) {
            toReverseString(node.getNext(), stack);
        }
        return stack;
    }

    public Integer size() {
        if (data != null && next != null)
            return next.size() + 1;
        if (data != null)
            return 1;
        return 0;
    }
}
