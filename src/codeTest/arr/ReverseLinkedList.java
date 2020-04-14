package codeTest.arr;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ReverseLinkedList {
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
                //翻转头尾之间
                reverse(newHead, tail);
                newHead = newTail;//上一组的尾结点做下一组的头结点
                tail = newTail;
            }
            //找到尾结点
            tail = tail.next;
            n++;
        }
        return head.next;
    }
}

class Solution {
    public static LinkNode reverseKGroup(LinkNode head, int k) {
        Stack<LinkNode> stack = new Stack<>();
        LinkNode dummy = new LinkNode(0);
        //p指向翻转后的头结点
        LinkNode p = dummy;
        while (true) {
            int count = 0;
            //tem指向当前原链表的头结点
            LinkNode tmp = head;
            while (tmp != null && count < k) {
                stack.add(tmp);
                tmp = tmp.next;
                count++;
            }
            if (count != k) {
                p.next = head;
                break;
            }
            while (!stack.isEmpty()) {
                p.next = stack.pop();
                p = p.next;
            }
            p.next = tmp;
            head = tmp;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        LinkNode node = new LinkNode(1);
        LinkNode head = node;
        LinkNode head2 = head;
        for (int i = 2; i <= 6; i++) {
            node.next = new LinkNode(i);
            node = node.next;
        }
        while (head != null) {
            System.out.print(head.getData() + " ");
            head = head.next;
        }
        System.out.println();

        head = reverseKGroup(head2, 2);
        while (head != null) {
            System.out.print(head.getData() + " ");
            head = head.next;
        }
    }
}