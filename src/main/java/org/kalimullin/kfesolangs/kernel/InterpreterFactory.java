package org.kalimullin.kfesolangs.kernel;

import org.kalimullin.kfesolangs.brainfuck.BrainfuckInterpreter;

import java.io.InputStream;
import java.io.PrintStream;

public class InterpreterFactory {
    /**
     * Creating interpreter by specific language. List of available languages:
     * <ul>
     *     <li>brainfuck</li>
     * </ul>
     * @param language String with specific language
     * @return Language-specific interpreter
     * @throws IllegalArgumentException if language string is not recognised
     */
    public static Interpreter getInterpreter(String language) {
        return getInterpreter(language, System.in, System.out);
    }

    public static Interpreter getInterpreter(String language, InputStream inputStream, PrintStream printStream) {
        String languageLowerCase = language.toLowerCase();
        Interpreter interpreter;
        if (languageLowerCase.equals("brainfuck")) {
            interpreter = new BrainfuckInterpreter();
        } else {
            throw new IllegalArgumentException("Unrecognized language string");
        }
        interpreter.setInputStream(inputStream);
        interpreter.setPrintStream(printStream);
        return interpreter;
    }
}
