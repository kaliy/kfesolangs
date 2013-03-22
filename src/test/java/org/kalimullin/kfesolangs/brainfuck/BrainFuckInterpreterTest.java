package org.kalimullin.kfesolangs.brainfuck;

import org.junit.Before;
import org.junit.Test;
import org.kalimullin.kfesolangs.InterpreterTestBase;
import org.kalimullin.kfesolangs.kernel.Interpreter;

public class BrainFuckInterpreterTest extends InterpreterTestBase {

    private Interpreter brainfuckAbstractInterpreter;

    @Before
    public void setUp() {
        brainfuckAbstractInterpreter = new BrainfuckInterpreter();
    }

    @Test
    public void testPlusOperator() {
        String program = getPlusesToChar('A')
                + "." // output A
                + "+"
                + "." // output B
                + "+"
                + "."; // output C
        brainfuckAbstractInterpreter.interpret(program);
        assertEquals("ABC", getStdOut().toString());
    }

    @Test
    public void testMinusOperator() {
        //TODO: refactor
        String program = getPlusesToChar('A')
                + "." // output A
                + "+"
                + "." // output B
                + "-"
                + "."; // output A
        brainfuckAbstractInterpreter.interpret(program);
        assertEquals("ABA", getStdOut().toString());
    }

    @Test
    public void testPointerSwitching() {
        String program = getPlusesToChar('C')
                + ">"
                + getPlusesToChar('B')
                + ">"
                + getPlusesToChar('A')
                + "."
                + "<."
                + "<.";
        brainfuckAbstractInterpreter.interpret(program);
        assertEquals("ABC", getStdOut().toString());
    }

    @Test
    public void testCycle() {
        String program = "+++++++" // loop counter
                + ">"
                + getPlusesToChar('A')
                + "<"
                + "["
                + ">." // output A
                + "<-" // decrement loop counter
                + "]";
        brainfuckAbstractInterpreter.interpret(program);
        assertEquals("AAAAAAA", getStdOut().toString());
    }

    private String getPlusesToChar(char character) {
        StringBuilder plusesBuilder = new StringBuilder();
        for (int i = 0; i < (int)character; i++) {
            plusesBuilder.append("+");
        }
        return plusesBuilder.toString();
    }

}
