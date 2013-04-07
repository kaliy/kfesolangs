package org.kalimullin.kfesolangs.kernel;

import org.apache.commons.io.IOUtils;

import java.io.*;

public abstract class Interpreter {
    public abstract void interpret(InputStream source) throws SyntaxError;

    private PrintStream printStream;
    private BufferedReader bufferedReader;

    public void interpret(String source) {
        interpret(IOUtils.toInputStream(source));
    }

    protected void print(Object object) {
        printStream.print(object);
    }

    protected void println(Object object) {
        printStream.println(object);
    }

    protected String read() throws IOException {
        return bufferedReader.readLine();
    }

    public void setInputStream(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

}
