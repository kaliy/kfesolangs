package org.kalimullin.kfesolangs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InterpreterTestBase extends Assert {

    private ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
    private ByteArrayOutputStream stdErr = new ByteArrayOutputStream();

    @Before
    public void setUpOutputStream() {
        System.setErr(new PrintStream(stdErr));
        System.setOut(new PrintStream(stdOut));
    }

    @After
    public void tearDownOutputStream() {
        System.setOut(null);
        System.setErr(null);
    }

    protected ByteArrayOutputStream getStdOut() {
        return stdOut;
    }

    protected ByteArrayOutputStream getStdErr() {
        return stdErr;
    }

}
