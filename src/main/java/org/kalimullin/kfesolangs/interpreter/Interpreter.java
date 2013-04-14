package org.kalimullin.kfesolangs.interpreter;

import org.apache.commons.io.IOUtils;

import java.io.*;

public abstract class Interpreter {
    /**
     * Main method for interpreting programs.
     * @param source Source code
     * @throws SyntaxError
     */
    public abstract void interpret(InputStream source) throws SyntaxError;

    private PrintStream printStream;
    private BufferedReader bufferedReader;

    public void interpret(String source) {
        interpret(IOUtils.toInputStream(source));
    }

    public void interpret(File file) {
        try {
            interpret(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            printStream.println("File " + file + " was not found");
            System.exit(1);
        }
    }

    /**
     * Print to PrintStream (STDOUT by default)
     * @param object object you want to print
     */
    protected void print(Object object) {
        printStream.print(object);
    }

    /**
     * Print to PrintStream with new line at the and
     * @param object object you want to print
     */
    protected void println(Object object) {
        printStream.println(object);
    }

    /**
     * Read data from InputStream (STDIN by default)
     * @return String received from InputStream
     */
    protected String read() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * InputStream Setter. Also creates InputStreamReader for interpreter.
     * @param inputStream InputStream to set
     */
    public void setInputStream(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

}
