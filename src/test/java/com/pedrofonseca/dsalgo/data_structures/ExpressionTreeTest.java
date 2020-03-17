package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTreeTest {

    private ExpressionTree mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new ExpressionTree();
    }

    @Test
    public void testReadString() {
        try {
            mTestedClass.readPostfix("");
            fail();
        } catch (Exception e) {
            assertEquals("Expression cannot be empty.", e.getMessage());
        }

        try {
            mTestedClass.readPostfix("1 +");
        } catch (Exception e) {
            assertEquals("Invalid expression.", e.getMessage());
        }

        try {
            mTestedClass.readPostfix("1 2 %");
        } catch (Exception e) {
            assertEquals("% is not an operand of parameterized type or a valid variable.", e.getMessage());
        }

        try {
            mTestedClass.readPostfix("1 2 + 3 4 +");
            fail();
        } catch (Exception e) {
            assertEquals("More than one valid expression created.", e.getMessage());
        }

        assertTrue(mTestedClass.readPostfix("1 2 +"));

        assertTrue(mTestedClass.readPostfix("1.2 a +"));
        System.out.println(mTestedClass);
    }

    @Test
    public void testToPostfix() {
        assertEquals("", mTestedClass.printPostfix());

        mTestedClass.readPostfix("1 2 + 3 -");
        assertEquals("1 2 + 3 - ", mTestedClass.printPostfix());
    }

    @Test
    public void testToNatural() {
        assertEquals("", mTestedClass.printNatural());

        mTestedClass.readPostfix("1 2 + 3 -");
        assertEquals("((1+2)-3)", mTestedClass.printNatural());
    }

    @Test
    public void testSolve() {
        assertNull(mTestedClass.solve());

        mTestedClass.readPostfix("1 1 + 2 2 * * 2 2 ^ / 2 -");
        assert(0 == mTestedClass.solve());
    }

    @Test
    public void testSolveVariables() {
        mTestedClass.readPostfix("a b +");
        try {
            mTestedClass.solve();
            fail();
        } catch (Exception e) {
            assertEquals("Undefined value for variable a", e.getMessage());
        }

        mTestedClass.defineVariable("a", 1D);

        try {
            mTestedClass.solve();
            fail();
        } catch (Exception e) {
            assertEquals("Undefined value for variable b", e.getMessage());
        }

        mTestedClass.defineVariable("b", 2D);

        assert(3 == mTestedClass.solve());

    }
}
