package com.pedrofonseca.dsalgo.data_structures.interfaces;

import java.util.Collection;

public interface IExpressionTree<T> {

    /**
     * Reads a whitespace separated postfix expression of generic typed operands and supported operators,
     * and creates corresponding expression tree.
     * @param pExpression Whitespace separated operators and operands.
     * @return
     */
    boolean readPostfix(String pExpression);

    /**
     * Reads a collection of generic typed operands and supported operators and created corresponding type tree.
     * @param pExpression Collection of operators and operands.
     * @return
     */
    boolean readPostfix(Collection<String> pExpression);

    /**
     * Returns current expression tree in postfix notation.
     */
    String printPostfix();

    /**
     * Returns current expression tree in calculator notation.
     */
    String printNatural();

    /**
     * Returns the solved value of the current expression tree.
     * @return Value of the current expression tree.
     */
    T solve();

    /**
     * Sets existing variable's value. Returns true on success, returns false if variable is unknown.
     * @param pKey Variable to define.
     * @param pValue Value of the given variable.
     * @return True on successful assignment, false otherwise.
     */
    boolean defineVariable(String pKey, Double pValue);

}
