package org.kalimullin.kfesolangs.kernel;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class KernelTest extends Assert {

    private ByteArrayOutputStream stdOut;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUpStream() {
        stdOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdOut));
    }

    @After
    public void tearDownStream() {
        System.setOut(null);
    }

    @Test
    public void testHelp() {
        Kernel.main(getArgumentsArrayFromString("--help"));
        assertTrue(stdOut.toString().startsWith("Usage:"));
        stdOut.reset();
        Kernel.main(getArgumentsArrayFromString("-h"));
        assertTrue(stdOut.toString().startsWith("Usage:"));
    }

    @Test
    public void testInterpretFromFile() {
        Kernel.main(getArgumentsArrayFromString("-l brainfuck src/test/resources/brainfuckSample.bf"));
        assertEquals("A", stdOut.toString());
    }

    @Test
    public void testInterpretPlainText() {
        Kernel.main(getArgumentsArrayFromString("-l brainfuck ++++++++++++++++ ++++++++ ++++++++ +++++++++++++++++++++++++++++++++."));
        assertEquals("A", stdOut.toString());
    }

    private String[] getArgumentsArrayFromString(String string) {
        return string.split("\\s+");
    }

}
