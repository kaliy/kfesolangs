package org.kalimullin.kfesolangs.kernel;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

public abstract class Interpreter {
    public abstract void interpret(InputStream inputStream) throws SyntaxError;

    public void interpret(String string) {
        interpret(IOUtils.toInputStream(string));
    }
}
