package algorithm;

import java.util.Stack;

/**
 * @author Eric
 * Date 2017/4/28 17:38
 * Desc
 */
public class AlgorithmDemo {

    static class Evalute {
        static final char addition = '+';
        static final char subtraction = '-';
        static final char multiplication = '*';
        static final char division = '/';
        static final char rightbracket = ')';
        static final char leftbracket = '(';

        public static void main(String[] args) {
            String expression = "(((((1+1)+6)*2)*(1+1))/((3*4)-4))";
            expressionCalculation(expression);

            // args = null;
            // System.out.println(checkNotNull(args, "参数不能为空"));

            while (true) {
                System.out.println("fitst");
                return;
            }
        }

        /**
         * 表达式求值: (((((1+1)+6)*2)*(1+1))/((3*4)-4))
         */
        private static void expressionCalculation(String expression) {
            Stack<Character> ops = new Stack<Character>();
            Stack<Double> vals = new Stack<Double>();

            // String expression = "(((((1+1)+6)*2)*(1+1))/((3*4)-4))";
            for (char c : expression.toCharArray()) {
                switch (c) {
                    case addition:
                    case subtraction:
                    case multiplication:
                    case division:
                        ops.push(c);
                        break;
                    case leftbracket:
                        break;
                    case rightbracket:
                        char op = ops.pop();
                        double v = vals.pop(); // 弹出栈顶数:即运算符后的数
                        switch (op) {
                            case addition:
                                // System.out.println(vals.pop() + v);
                                // v = Double.valueOf(String.valueOf(vals.pop())) + v;
                                v = vals.pop() + v;
                                break;
                            case subtraction:
                                v = vals.pop() - v;
                                break;
                            case multiplication:
                                v = vals.pop() * v;
                                break;
                            case division:
                                v = vals.pop() / v;
                                break;
                            default:
                                break;
                        }
                        vals.push(v);
                        break;
                    default:
                        vals.add(Double.valueOf(String.valueOf(c)));
                        break;
                }
            }
            if (!vals.empty()) {
                System.out.println(vals.pop());
            }
        }
    }
}
