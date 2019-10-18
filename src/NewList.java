import java.util.Stack;

public class NewList<T> {
    Stack<T> stack1 = new Stack<>();
    Stack<T> stack2 = new Stack<>();

    public void push(T item) {
        stack1.push(item);
    }

    public T pop() {
        if (!stack2.isEmpty())
            return stack2.pop();
        else if (!stack1.isEmpty())
            for (T item : stack1) {
                stack2.push(stack1.pop());
            }
        else return null;
        return stack2.pop();
    }

    public static void main(String[] args) {
        int[] prices = {9, 10, 7, 11, 6, 9};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (max < profit) {
                    max = profit;
                }
            }
        }

        return max;
    }

}
