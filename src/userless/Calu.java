package userless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calu {
    private static List<String> sysboList = new ArrayList<String>() {{
        this.add("+");
        this.add("-");
        this.add("*");
        this.add("/");
        this.add("=");
    }};
    private static Pattern p1 = Pattern.compile("(^[0-9]{1,9}[.][0-9]{1,2}$)|(^[0-9]{1,11}$)");

    public static void main(String[] args) {
        String numstr = "(2+2*(1+2))*3/2";
        //String numstr = "C2(H3?2)2";
        String str = "12*2+(1*3+x*2)*2=50";

        System.out.println(result(str));
    }

    public int test(String str) {
        for (int i = 0; i < 200; i++) {

        }
        return 0;

    }

    public static BigDecimal result(String numstr) {
        //先把括号中的值给算出来然后替换，找的括号是最小范围的
        //最后一个'('出现的位置 到 第一个')'出现的位置
        StringBuffer sb = new StringBuffer(numstr);
        if (isStandard(sb.toString())) {
            String rs = bracketsString(sb);
            while (rs != null) {
                BigDecimal calBracketsValue = calBracketsValue(rs + "=");
                sb.replace(sb.indexOf("@"), sb.indexOf("@") + 1, calBracketsValue.toString());
                System.out.println(sb);
                rs = bracketsString(sb);
            }
            return calBracketsValue(sb.toString() + "=");
        }
        return new BigDecimal("0");
    }

    public static String bracketsString(StringBuffer str) {
        int leftBracketsPoint = str.lastIndexOf("(");
        int rightBracketsPoint = str.indexOf(")");
        if (leftBracketsPoint == -1 || rightBracketsPoint == -1) {
            return null;
        }
        String res = str.substring(leftBracketsPoint + 1, rightBracketsPoint);
        str.replace(leftBracketsPoint, rightBracketsPoint + 1, "@");
        return res;
    }

    //计算括号中的表达式的值
    public static BigDecimal calBracketsValue(String numStr) {
        Stack<BigDecimal> numStack = new Stack<BigDecimal>();//数字栈
        Stack<String> sysmboStack = new Stack<String>();//符号栈
        int lastSysboPoint = 0;//上一次非数字出现的位置
        for (int i = 0; i < numStr.length(); i++) {
            String ch = numStr.charAt(i) + "";
            if (sysboList.contains(ch)) {
                //开始计算入栈
                //先把前面的数字入栈 1+1*1=
                String num = numStr.substring(lastSysboPoint, i);
                numStack.push(new BigDecimal(num));

                while (!sysmboStack.isEmpty() && !comparePri(ch.charAt(0), sysmboStack.peek().charAt(0))) {//如果符号栈不为空，就把前一个符号拿出来和现在的符号做优先级对比
                    BigDecimal num2 = numStack.pop();
                    BigDecimal num1 = numStack.pop();
                    String sysboTemp = sysmboStack.pop();
                    if (sysboTemp.equals("+")) {
                        numStack.push(num1.add(num2));
                    } else if (sysboTemp.equals("-")) {
                        numStack.push(num1.subtract(num2));
                    } else if (sysboTemp.equals("*")) {
                        numStack.push(num1.multiply(num2));
                    } else if (sysboTemp.equals("/")) {
                        numStack.push(num1.divide(num2, 10, BigDecimal.ROUND_HALF_DOWN));
                    }
                }
                sysmboStack.push(ch);
                lastSysboPoint = i + 1;
            }
        }

        return numStack.pop();
    }


    private static boolean comparePri(char symbol, char top) {
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) {
            case '(': // 优先级最高
                return true;
            case '*': {
                if (top == '+' || top == '-') // 优先级比+和-高
                    return true;
                else
                    return false;
            }
            case '/': {
                if (top == '+' || top == '-') // 优先级比+和-高
                    return true;
                else
                    return false;
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')': // 优先级最低
                return false;
            case '=': // 结束符
                return false;
            default:
                break;
        }
        return true;
    }

    private static boolean isNumber(String num) {
        Matcher m1 = p1.matcher("123");
        return m1.matches();
    }

    private static boolean isStandard(String numStr) {
        if (numStr == null || numStr.isEmpty()) // 表达式不能为空
            return false;
        Stack<Character> stack = new Stack<Character>(); // 用来保存括号，检查左右括号是否匹配
        boolean b = false; // 用来标记'='符号是否存在多个
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            // 判断字符是否合法
            if (!(isNumber(n + "") || "(".equals(n + "") || ")".equals(n + "")
                    || "+".equals(n + "") || "-".equals(n + "")
                    || "*".equals(n + "") || "/".equals(n + "")
                    || "=".equals(n + ""))) {
                return false;
            }
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ("(".equals(n + "")) {
                stack.push(n);
            }
            if (")".equals(n + "")) { // 匹配括号
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // 括号是否匹配
                    return false;
            }
            // 检查是否有多个'='号
            if ("=".equals(n + "")) {
                if (b)
                    return false;
                b = true;
            }
        }
        // 可能会有缺少右括号的情况
        if (!stack.isEmpty())
            return false;
        return true;
    }

}