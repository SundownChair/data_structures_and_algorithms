package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IExpressionTree;

import java.util.*;

/**
 * <p>Tree structure capable of reading and storing expressions in postfix notation (operations are tree nodes while
 * operands are leafs). Can print in either natural or postfix notation, or solve the stored expression.</p>
 */
public class ExpressionTree implements IExpressionTree<Double> {

    private Node mTree;

    private HashMap<String, Double> mVariables;

    enum OPERATOR {
        SUM, SUBTRACTION, MULTIPLICATION, DIVISION, POWER
    }

    public ExpressionTree() {
        mTree = null;
        mVariables = new HashMap<>();
    }

    @Override
    public boolean readPostfix(String pExpression) {
        if(pExpression == null || pExpression.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be empty.");
        }

        List<String> lList = Arrays.asList(pExpression.split(" "));
        return readPostfix(lList);
    }

    @Override
    public boolean readPostfix(Collection<String> pExpression) {
        if(pExpression == null || pExpression.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be empty.");
        }

        Stack<Node> lStack = new Stack<>();

        for (String ele : pExpression) {
            if (ele == null || ele.isEmpty()) {
                throw new IllegalArgumentException("Expression elements cannot be empty.");
            }

            OPERATOR op = isOperator(ele);
            if(op != null) {
                Node subTree = new Node(ele);
                try {
                    subTree.right = lStack.pop();
                    subTree.left = lStack.pop();
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("Invalid expression.");
                }
                lStack.add(subTree);
            } else {
                if (ele.matches("[a-zA-Z]+")) {
                    mVariables.put(ele, null);
                    lStack.add(new Node(ele));
                }else if(ele.matches("[0-9]+([.,]?[0-9]+)?")) {
                    lStack.add(new Node(ele));
                } else {
                    throw new IllegalArgumentException(ele.concat(" is not an operand of parameterized type or a valid variable."));
                }
            }
        }

        if(lStack.size() > 1) {
            throw new IllegalArgumentException("More than one valid expression created.");
        }

        mTree = lStack.pop();

        return true;
    }

    /**
     * Returns OPERATOR enum element on correctly identifying supported operator. Returns null otherwise.
     * @param pElement Element to classify.
     * @return Enum element, null if operator not supported
     */
    private OPERATOR isOperator(String pElement) {
        switch(pElement) {
            case "+": return OPERATOR.SUM;
            case "-": return OPERATOR.SUBTRACTION;
            case "*": return OPERATOR.MULTIPLICATION;
            case "/": return OPERATOR.DIVISION;
            case "^": return OPERATOR.POWER;
            default: return null;
        }
    }

    @Override
    public String printPostfix() {
        return printPostFix(mTree);
    }

    /**
     * Inner recursive postfix notation creation function. A post-order traversal of the tree.
     * @param pNode Current node.
     * @return Current resukt.
     */
    private String printPostFix(Node pNode) {
        if (pNode == null) {
            return "";
        }
        StringBuilder exp = new StringBuilder();
        exp.append(printPostFix(pNode.left));
        exp.append(printPostFix(pNode.right));
        exp.append(pNode.value);
        exp.append(" ");
        return exp.toString();
    }

    @Override
    public String printNatural() {
        return printNatural(mTree);
    }

    /**
     * Inner recursive postfix notation creation function. An in-order traversal of the tree.
     * @param pNode Current node.
     * @return Current result.
     */
    private String printNatural(Node pNode) {
        if (pNode == null) {
            return "";
        }

        String[] brackets = {"", ""};
        if (isOperator(pNode.value.toString()) != null) {
            brackets[0] = "(";
            brackets[1] = ")";
        }

        StringBuilder exp = new StringBuilder();
        exp.append(brackets[0]);
        exp.append(printNatural(pNode.left));
        exp.append(pNode.value);
        exp.append(printNatural(pNode.right));
        exp.append(brackets[1]);

        return exp.toString();
    }

    @Override
    public Double solve() {
        if (mTree == null) {
            return null;
        }

        return solve(mTree);
    }

    /**
     * Inner recursive function to calculate the value of the current expression tree.
     * Will throw an error anytime it finds an undefined variable in the expression tree.
     * @param pNode Current node.
     * @return Current result.
     */
    private Double solve(Node pNode) {
        OPERATOR op = isOperator(pNode.value.toString());
        if (op != null) {
            Double left = solve(pNode.left);
            Double right = solve(pNode.right);
            if (op.equals(OPERATOR.SUM)) {
                return left + right;
            } else if (op.equals(OPERATOR.SUBTRACTION)) {
                return left - right;
            } else if (op.equals(OPERATOR.MULTIPLICATION)) {
                return left * right;
            } else if (op.equals(OPERATOR.DIVISION)) {
                return left / right;
            } else if (op.equals(OPERATOR.POWER)) {
                return Math.pow(left, right);
            }
        } else {
            String value = pNode.value.toString();

            if(value.matches("[a-zA-Z]+")) {
                if(mVariables.get(value) != null) {
                    return mVariables.get(value);
                } else {
                    throw new IllegalArgumentException("Undefined value for variable ".concat(value));
                }
            }

            return Double.valueOf(value);
        }

        return null;
    }

    public boolean defineVariable(String pKey, Double pValue) {
        if(mVariables.containsKey(pKey)) {
            mVariables.put(pKey, pValue);
            return true;
        }
        return false;
    }

    private class Node {
        Object value;
        Node left;
        Node right;

        Node(Object pValue) {
            value = pValue;
            left = null;
            right = null;
        }
    }
}
