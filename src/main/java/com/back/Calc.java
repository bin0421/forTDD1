package com.back;

public class Calc {
    public static int run(String expr) {
        expr = expr.trim();

        int splitIdx = findSplitIndex(expr, new String[]{"+", "-"});

        if (splitIdx != -1) {
            char op = expr.charAt(splitIdx);
            String left = expr.substring(0,splitIdx-1).trim();
            String right = expr.substring(splitIdx+1).trim();

            if (op == '+') {
                return add(run(left), run(right));
            } else if (op == '-') {
                return sub(run(left), run(right));
            }
        }

        splitIdx = findSplitIndex(expr, new String[]{"*"});

        if (splitIdx != -1) {
            String left = expr.substring(0, splitIdx).trim();
            String right = expr.substring(splitIdx + 1).trim();

            return mul(run(left), run(right));
        }

        return Integer.parseInt(expr);
    }

    private static int add(int left, int right) {
        return left + right;
    }

    private static int sub(int left, int right) {
        return left - right;
    }

    private static int mul(int left, int right) {
        return left * right;
    }
    private static int findSplitIndex(String expr, String[] strings) {

    }


}


