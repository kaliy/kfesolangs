package org.kalimullin.kfesolangs.kernel;

import org.kalimullin.kfesolangs.brainfuck.BrainfuckInterpreter;

public class InterpreterFactory {
    /**
     * Creating interpreter by specific language. List of available languages:
     * <ul>
     *     <li>Nothing yet :(</li>
     * </ul>
     * @param language String with specific language
     * @return Language-specific interpreter
     * @throws IllegalArgumentException if language string is not recognised
     */
    public static Interpreter getInterpreter(String language) {
        String languageLowerCase = language.toLowerCase();
        if (languageLowerCase.equals("brainfuck"))
            return new BrainfuckInterpreter();
        throw new IllegalArgumentException("Unrecognized language string");
    }
}
