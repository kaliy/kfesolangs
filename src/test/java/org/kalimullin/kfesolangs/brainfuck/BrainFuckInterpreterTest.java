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
        StringBuilder plusBuilder = new StringBuilder();
        for (int builderCounter = 0; builderCounter < 64; builderCounter++) {
            plusBuilder.append("+");
        }
        plusBuilder.append(".") // output A
                .append("+")
                .append(".") // output B
                .append("+")
                .append("."); // output C
        brainfuckAbstractInterpreter.interpret(plusBuilder.toString());
        assertEquals("ABC", getStdOut().toString());
    }

    @Test
    public void testMinusOperator() {
        //TODO: refactor
        StringBuilder plusBuilder = new StringBuilder();
        for (int builderCounter = 0; builderCounter < 64; builderCounter++) {
            plusBuilder.append("+");
        }
        plusBuilder.append(".") // output A
                .append("+")
                .append(".") // output B
                .append("-")
                .append("."); // output C
        brainfuckAbstractInterpreter.interpret(plusBuilder.toString());
        assertEquals("ABA", getStdOut().toString());
    }

}
