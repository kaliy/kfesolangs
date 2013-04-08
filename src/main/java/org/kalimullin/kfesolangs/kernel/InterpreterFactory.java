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
    public static Interpreter getInterpreter(String language) throws IllegalArgumentException {
        return getInterpreter(getLanguageFromString(language));
    }

    public static Interpreter getInterpreter(Language language) {
        return getInterpreter(language, System.in, System.out);
    }

    public static Interpreter getInterpreter(String language, InputStream inputStream, PrintStream printStream) throws IllegalArgumentException {
        return getInterpreter(getLanguageFromString(language), inputStream, printStream);
    }

    public static Interpreter getInterpreter(Language language, InputStream inputStream, PrintStream printStream) {
        Interpreter interpreter = null;
        switch (language) {
            case BRAINFUCK:
                interpreter = new BrainfuckInterpreter();
                break;
        }
        interpreter.setInputStream(inputStream);
        interpreter.setPrintStream(printStream);
        return interpreter;
    }

    private static Language getLanguageFromString(String language) {
        try {
            return Language.valueOf(language.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unrecognized language string");
        }
    }
}
