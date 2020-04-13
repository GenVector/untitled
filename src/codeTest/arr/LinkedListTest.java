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
