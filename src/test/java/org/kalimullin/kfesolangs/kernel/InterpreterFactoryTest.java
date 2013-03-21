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
        Interpreter abstractInterpreter = InterpreterFactory.getInterpreter("brainfuck");
        assertThat(abstractInterpreter, instanceOf(BrainfuckInterpreter.class));
        abstractInterpreter = InterpreterFactory.getInterpreter("bRaiNfUck");
        assertThat(abstractInterpreter, instanceOf(BrainfuckInterpreter.class));
    }

    @Test
    public void testUnrecognizedInterpreter() {
        expectedException.expect(IllegalArgumentException.class);
        InterpreterFactory.getInterpreter("bla-bla-bla");
    }
}
