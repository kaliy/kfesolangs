package org.kalimullin.kfesolangs.brainfuck;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.kfesolangs.InterpreterTestBase;
import org.kalimullin.kfesolangs.kernel.Interpreter;
import org.kalimullin.kfesolangs.kernel.Language;
import org.kalimullin.kfesolangs.kernel.SyntaxError;


public class BrainFuckInterpreterTest extends InterpreterTestBase {

    private Interpreter brainfuckInterpreter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        brainfuckInterpreter = createInterpreter(Language.BRAINFUCK);
    }

    @Test
    public void testPlusOperator() {
        String program = getPlusesToChar('A')
                + "." // output A
                + "+"
                + "." // output B
                + "+"
                + "."; // output C
        brainfuckInterpreter.interpret(program);
        assertEquals("ABC", getOutput());
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
        brainfuckInterpreter.interpret(program);
        assertEquals("ABA", getOutput());
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
        brainfuckInterpreter.interpret(program);
        assertEquals("ABC", getOutput());
    }

    @Test
    public void testInputToken() {
        sendInput(brainfuckInterpreter, "A");
        brainfuckInterpreter.interpret(",...");
        assertEquals("AAA", getOutput());
    }

    @Test
    public void testInputMoreThanOneCharacter() {
        sendInput(brainfuckInterpreter, "ABC");
        brainfuckInterpreter.interpret(",.");
        assertEquals("A", getOutput());
    }

    @Test
    public void testLoopBeginOperator() {
        String program = ">"
                + getPlusesToChar('A')
                + "<"
                + "[->-]"
                + ">.";
        brainfuckInterpreter.interpret(program);
        assertEquals("A", getOutput());
    }

    @Test
    public void testLoop() {
        String program = "+++++++" // loop counter
                + ">"
                + getPlusesToChar('A')
                + "<"
                + "["
                + ">." // output A
                + "<-" // decrement loop counter
                + "]";
        brainfuckInterpreter.interpret(program);
        assertEquals("AAAAAAA", getOutput());
    }

    @Test
    public void testNestedLoop() {
        String program = "+++" // loop counter = 3
                + "> +++" // nested loop counter = 3
                + ">" + getPlusesToChar('A')
                + ">" + getPlusesToChar('B')
                + "<<<" + "[->>.>.<<" // print third and fourth pointers ('A' and 'B' initially)
                + "[->++.>++.<<]" // increment third and fourth them for two and print them
                + "+++<" // set nested loop counter back to 3
                + "]";
        brainfuckInterpreter.interpret(program);
        assertEquals("ABCDEFGHGHIJKLMNMNOPQRST", getOutput());
    }

    @Test
    public void testMinusOverflow() {
        StringBuilder program = new StringBuilder();
        for (int i = 0; i < 191; i++) {
            program.append("-");
        }
        program.append(".");
        brainfuckInterpreter.interpret(program.toString());
        assertEquals("A", getOutput());
    }

    @Test
    public void testPlusOverflow() {
        StringBuilder program = new StringBuilder();
        for (int i = 0; i < 321; i++) {
            program.append("+");
        }
        program.append(".");
        brainfuckInterpreter.interpret(program.toString());
        assertEquals("A", getOutput());
    }

    @Test
    public void testNegativePointerIndex() {
        String program = getPlusesToChar('B')
                + ">"
                + getPlusesToChar('A')
                + "<<"
                + getPlusesToChar('C')
                + ">>.<.<.";
        brainfuckInterpreter.interpret(program);
        assertEquals("ABC", getOutput());
    }

    @Test
    public void testIncorrectLoopSyntaxError() {
        String program = "+.][";
        expectedException.expect(SyntaxError.class);
        brainfuckInterpreter.interpret(program);
    }

    private String getPlusesToChar(char character) {
        StringBuilder plusesBuilder = new StringBuilder();
        for (int i = 0; i < (int)character; i++) {
            plusesBuilder.append("+");
        }
        return plusesBuilder.toString();
    }

}
