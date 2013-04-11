package org.kalimullin.kfesolangs.interpreter;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.kalimullin.kfesolangs.interpreter.language.Language;

import java.io.*;

public class InterpreterTestBase extends Assert {

    private PrintStream printStream;
    private InputStream inputStream;
    private OutputStream outputStream;

    @Before
    public void setUpOutputStream() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        inputStream = IOUtils.toInputStream("");
    }

    protected String getOutput() {
        return outputStream.toString();
    }

    protected void sendInput(Interpreter interpreter, String inputString) {
        interpreter.setInputStream(IOUtils.toInputStream(inputString));
    }

    protected Interpreter createInterpreter(Language language) {
        return InterpreterFactory.getInterpreter(language, inputStream, printStream);
    }

}
