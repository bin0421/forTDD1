package com.back;

public class Calc {
    public static int run(String expr) {
        expr = expr.trim();

        while (isFullyEnclosed(expr)) {
            expr = expr.substring(1, expr.length() - 1).trim();
        }

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
    private static int findSplitIndex(String exp, String[] strings) {
        int parenCount = 0;

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (c == ')') {
                parenCount++;
            }
            else if (c == '(') {
                parenCount--;
            }

            else if (parenCount == 0) {
                for (String op : strings) {
                    if (c == op.charAt(0)) {
                        if (c == '-') {
                            if (i == 0) continue;
                            char prevChar = exp.substring(0, i).trim().charAt(exp.substring(0, i).trim().length() - 1);
                            if (prevChar == '+' || prevChar == '-' || prevChar == '*') continue;
                        }
                        return i;
                    }
                }
            }

        }
        return -1;
    }

    private static boolean isFullyEnclosed(String exp) {
        if (!exp.startsWith("(") || !exp.endsWith(")")) {
            return false;
        }

        int parenCount = 0;
        for (int i = 0; i < exp.length() - 1; i++) {
            char c = exp.charAt(i);
            if (c == '(') parenCount++;
            else if (c == ')') parenCount--;

            if (parenCount == 0) {
                return false;
            }
        }
        return true;
    }

}


