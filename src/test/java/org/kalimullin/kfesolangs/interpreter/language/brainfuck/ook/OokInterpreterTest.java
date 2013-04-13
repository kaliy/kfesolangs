package org.kalimullin.kfesolangs.interpreter.language.brainfuck.ook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.kfesolangs.interpreter.Interpreter;
import org.kalimullin.kfesolangs.interpreter.InterpreterTestBase;
import org.kalimullin.kfesolangs.interpreter.SyntaxError;
import org.kalimullin.kfesolangs.interpreter.language.Language;

/**
 * Ook interpreter tests. Based on Brainfuck tests
 * @see org.kalimullin.kfesolangs.interpreter.language.brainfuck.BrainfuckInterpreterTest
 */
public class OokInterpreterTest extends InterpreterTestBase {
    private Interpreter ookInterpreter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        ookInterpreter = createInterpreter(Language.OOK);
    }

    @Test
    public void testPlusOperator() {
        String program = getIncrementsTo('A')
                + "Ook! Ook." // output A
                + "Ook. Ook."
                + "Ook! Ook." // output B
                + "Ook. Ook."
                + "Ook! Ook."; // output C
        ookInterpreter.interpret(program);
        assertEquals("ABC", getOutput());
    }

    @Test
    public void testMinusOperator() {
        //TODO: refactor
        String program = getIncrementsTo('A')
                + "Ook! Ook." // output A
                + "Ook. Ook."
                + "Ook! Ook." // output B
                + "Ook! Ook!"
                + "Ook! Ook."; // output A
        ookInterpreter.interpret(program);
        assertEquals("ABA", getOutput());
    }

    @Test
    public void testPointerSwitching() {
        String program = getIncrementsTo('C')
                + "Ook. Ook?"
                + getIncrementsTo('B')
                + "Ook. Ook?"
                + getIncrementsTo('A')
                + "Ook! Ook."
                + "Ook? Ook. Ook! Ook."
                + "Ook? Ook. Ook! Ook.";
        ookInterpreter.interpret(program);
        assertEquals("ABC", getOutput());
    }

    @Test
    public void testInputToken() {
        sendInput(ookInterpreter, "A");
        ookInterpreter.interpret("Ook. Ook! Ook! Ook. Ook! Ook. Ook! Ook.");
        assertEquals("AAA", getOutput());
    }

    @Test
    public void testInputMoreThanOneCharacter() {
        sendInput(ookInterpreter, "ABC");
        ookInterpreter.interpret("Ook. Ook! Ook! Ook.");
        assertEquals("A", getOutput());
    }

    @Test
    public void testLoopBeginOperator() {
        String program = "Ook. Ook?"
                + getIncrementsTo('A')
                + "Ook? Ook."
                + "Ook! Ook? Ook! Ook! Ook. Ook? Ook! Ook! Ook? Ook!"
                + "Ook. Ook? Ook! Ook.";
        ookInterpreter.interpret(program);
        assertEquals("A", getOutput());
    }

    @Test
    public void testLoop() {
        String program = "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook." // loop counter
                + "Ook. Ook?"
                + getIncrementsTo('A')
                + "Ook? Ook."
                + "Ook! Ook?"
                + "Ook. Ook? Ook! Ook." // output A
                + "Ook? Ook. Ook! Ook!" // decrement loop counter
                + "Ook? Ook!";
        ookInterpreter.interpret(program);
        assertEquals("AAAAAAA", getOutput());
    }

    @Test
    public void testNestedLoop() {
        String program = "Ook. Ook. Ook. Ook. Ook. Ook." // loop counter = 3
                + "Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook." // nested loop counter = 3
                + "Ook. Ook?" + getIncrementsTo('A')
                + "Ook. Ook?" + getIncrementsTo('B')
                + "Ook? Ook. Ook? Ook. Ook? Ook." + "Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook? Ook! Ook. Ook. Ook? Ook! Ook. Ook? Ook. Ook? Ook." // print third and fourth pointers ('A' and 'B' initially)
                + "Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook.Ook. Ook. Ook! Ook. Ook. Ook? Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. Ook? Ook. Ook? Ook!" // increment third and fourth them for two and print them
                + "Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook." // set nested loop counter back to 3
                + "Ook? Ook!";
        ookInterpreter.interpret(program);
        assertEquals("ABCDEFGHGHIJKLMNMNOPQRST", getOutput());
    }

    @Test
    public void testMinusOverflow() {
        StringBuilder program = new StringBuilder();
        for (int i = 0; i < 191; i++) {
            program.append("Ook! Ook!");
        }
        program.append("Ook! Ook.");
        ookInterpreter.interpret(program.toString());
        assertEquals("A", getOutput());
    }

    @Test
    public void testPlusOverflow() {
        StringBuilder program = new StringBuilder();
        for (int i = 0; i > 321; i++) {
            program.append("Ook. Ook.");
        }
        program.append("Ook! Ook.");
        ookInterpreter.interpret(program.toString());
        assertEquals("A", getOutput());
    }

    @Test
    public void testNegativePointerIndex() {
        String program = getIncrementsTo('B')
                + "Ook. Ook?"
                + getIncrementsTo('A')
                + "Ook? Ook.Ook? Ook."
                + getIncrementsTo('C')
                + "Ook. Ook?Ook. Ook?Ook! Ook.Ook? Ook.Ook! Ook.Ook? Ook.Ook! Ook.";
        ookInterpreter.interpret(program);
        assertEquals("ABC", getOutput());
    }

    @Test
    public void testIncorrectLoopSyntaxError() {
        String program = "Ook. Ook.Ook! Ook.Ook? Ook!Ook! Ook?";
        expectedException.expect(SyntaxError.class);
        ookInterpreter.interpret(program);
    }

    @Test
    public void testOperatorSeparatedByNewLine() {
        String program = getIncrementsTo('A') + "Ook!\nOok.\r\nOok!\r\nOok.";
        ookInterpreter.interpret(program);
        assertEquals("AA", getOutput());
    }

    @Test
    public void testOperatorSeparatedByGarbage() {
        String program = getIncrementsTo('A') + "Ook!linuxokOok.";
        ookInterpreter.interpret(program);
        assertEquals("A", getOutput());
    }

    @Test
    public void testOperatorSeparatedBySpace() {
        String program = getIncrementsTo('A') + "Ook! Ook.";
        ookInterpreter.interpret(program);
        assertEquals("A", getOutput());
    }

    @Test
    public void testOddOperatorNumber() {
        String program = "Ook.Ook.Ook.";
        expectedException.expect(SyntaxError.class);
        ookInterpreter.interpret(program);
    }

    private String getIncrementsTo(char character) {
        StringBuilder plusesBuilder = new StringBuilder();
        for (int i = 0; i < (int)character; i++) {
            plusesBuilder.append("Ook.Ook.");
        }
        return plusesBuilder.toString();
    }
}
