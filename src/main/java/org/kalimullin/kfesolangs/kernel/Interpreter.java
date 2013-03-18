package org.kalimullin.kfesolangs.kernel;

import java.io.InputStream;

public interface Interpreter {
    public void interpret(InputStream inputStream) throws SyntaxError;
}
