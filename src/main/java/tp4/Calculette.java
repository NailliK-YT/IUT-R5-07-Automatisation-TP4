package tp4;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculette {
    private final Deque<Double> stack = new ArrayDeque<>();

    // pousse une valeur sur la pile
    public void push(double v) {
        stack.push(v);
    }

    private void require(int n) {
        if (stack.size() < n) {
            throw new IllegalStateException("Pile: pas assez d'opérandes (attendu " + n + ")");
        }
    }

    public double add() {
        require(2);
        double b = stack.pop();
        double a = stack.pop();
        double r = a + b;
        stack.push(r);
        return r;
    }

    public double sub() {
        require(2);
        double b = stack.pop();
        double a = stack.pop();
        double r = a - b;
        stack.push(r);
        return r;
    }

    public double mul() {
        require(2);
        double b = stack.pop();
        double a = stack.pop();
        double r = a * b;
        stack.push(r);
        return r;
    }

    public double div() {
        require(2);
        double b = stack.pop();
        double a = stack.pop();
        if (b == 0.0) throw new ArithmeticException("Division par zéro");
        double r = a / b;
        stack.push(r);
        return r;
    }

    // Évalue une expression RPN et renvoie le résultat final
    public double evaluateRPN(String expr) {
        stack.clear();
        String[] tokens = expr.trim().split("\\s+");
        for (String t : tokens) {
            if (t.matches("[+-]?\\d+(\\.\\d+)?")) { // nombre
                push(Double.parseDouble(t));
            } else {
                switch (t) {
                    case "+" -> add();
                    case "-" -> sub();
                    case "*" -> mul();
                    case "/" -> div();
                    default -> throw new IllegalArgumentException("Jeton inconnu: " + t);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalStateException("Expression invalide: pile finale = " + stack.size());
        }
        return stack.pop();
    }
}
