package org.kalimullin.kfesolangs.interpreter;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.kfesolangs.interpreter.language.brainfuck.BrainfuckInterpreter;
import org.kalimullin.kfesolangs.interpreter.Interpreter;
import org.kalimullin.kfesolangs.interpreter.InterpreterFactory;
import org.kalimullin.kfesolangs.interpreter.language.Language;

import static org.hamcrest.CoreMatchers.instanceOf;

public class InterpreterFactoryTest extends Assert {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBrainfuckInterpreter() {
        Interpreter abstractInterpreter = InterpreterFactory.getInterpreter("brainfuck");
        assertThat(abstractInterpreter, instanceOf(BrainfuckInterpreter.class));
        abstractInterpreter = InterpreterFactory.getInterpreter(Language.BRAINFUCK);
        assertThat(abstractInterpreter, instanceOf(BrainfuckInterpreter.class));
    }

    @Test
    public void testUnrecognizedInterpreter() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Unrecognized language string");
        InterpreterFactory.getInterpreter("bla-bla-bla");
    }
}
