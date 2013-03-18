package org.kalimullin.kfesolangs.kernel;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.kfesolangs.brainfuck.BrainfuckInterpreter;

import static org.hamcrest.CoreMatchers.instanceOf;

public class InterpreterFactoryTest extends Assert {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBrainfuckInterpreter() {
        Interpreter interpreter = InterpreterFactory.getInterpreter("brainfuck");
        assertThat(interpreter, instanceOf(BrainfuckInterpreter.class));
        interpreter = InterpreterFactory.getInterpreter("bRaiNfUck");
        assertThat(interpreter, instanceOf(BrainfuckInterpreter.class));
    }

    @Test
    public void testUnrecognizedInterpreter() {
        expectedException.expect(IllegalArgumentException.class);
        InterpreterFactory.getInterpreter("bla-bla-bla");
    }
}
