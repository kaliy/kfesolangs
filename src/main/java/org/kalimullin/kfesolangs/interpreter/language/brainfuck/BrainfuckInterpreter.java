package org.kalimullin.kfesolangs.interpreter.language.brainfuck;

import org.apache.commons.io.IOUtils;
import org.kalimullin.kfesolangs.interpreter.Interpreter;
import org.kalimullin.kfesolangs.interpreter.SyntaxError;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Brainfuck language interpreter.
 * @see <a href="http://www.muppetlabs.com/~breadbox/bf/">Information about brainfuck language</a>
 */
public class BrainfuckInterpreter extends Interpreter {

    private List<BrainfuckToken> tokenList;
    private int currentTokenIndex = 0;
    private int currentPointerIndex = 0;
    private List<Byte> pointerList;

    public BrainfuckInterpreter() {
        pointerList = new ArrayList<Byte>();
        pointerList.add((byte)0); // First default pointer
    }

    /**
     * This method will be invoked before interpreting source code
     */
    protected void beforeInterpret(String sourceCode) {
    }

    protected void runToken(BrainfuckToken token) {
        switch (token) {
            case INCREMENT:
                runIncrementToken();
                break;
            case DECREMENT:
                runDecrementToken();
                break;
            case OUTPUT:
                runOutputToken();
                break;
            case INPUT:
                runInputToken();
                break;
            case NEXT_POINTER:
                runNextPointerToken();
                break;
            case PREVIOUS_POINTER:
                runPreviousPointerToken();
                break;
            case LOOP_BEGIN:
                runLoopBeginToken();
                break;
            case LOOP_END:
                runLoopEndToken();
                break;
        }
    }

    @Override
    public void interpret(InputStream source) throws SyntaxError {
        String sourceCode = "";
        try {
            sourceCode = IOUtils.toString(source);
        } catch (IOException e) {
            // TODO: better error handling
            e.printStackTrace();
        }
        beforeInterpret(sourceCode);
        tokenList = getTokenList(sourceCode);
        while (currentTokenIndex < tokenList.size()) {
            runToken(tokenList.get(currentTokenIndex));
            currentTokenIndex++;
        }
    }

    /**
     * Creates token list from character array.
     * @param source brainfuck program source code string
     * @return List with BrainfuckToken
     */
    protected List<BrainfuckToken> getTokenList(String source) {
        List<BrainfuckToken> tokens = new ArrayList<BrainfuckToken>();
        for (char c: source.toCharArray()) {
            BrainfuckToken token = BrainfuckToken.fromChar(c);
            if (null != token) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    private void runIncrementToken() {
        pointerList.set(currentPointerIndex, (byte)(pointerList.get(currentPointerIndex) + 1));
    }

    private void runDecrementToken() {
        pointerList.set(currentPointerIndex, (byte)(pointerList.get(currentPointerIndex) - 1));
    }

    private void runNextPointerToken() {
        currentPointerIndex++;
        if (pointerList.size() <= currentPointerIndex || null == pointerList.get(currentPointerIndex)) {
            pointerList.add(currentPointerIndex, (byte)0);
        }
    }

    private void runPreviousPointerToken() {
        currentPointerIndex--;
        if (currentPointerIndex < 0 || null == pointerList.get(currentPointerIndex)) {
            currentPointerIndex = 0;
            pointerList.add(currentPointerIndex, (byte) 0);
        }
    }

    private void runInputToken() {
        try {
            pointerList.set(currentPointerIndex, (byte)read().charAt(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runOutputToken() {
        print((char) pointerList.get(currentPointerIndex).byteValue());
    }

    private void runLoopEndToken() {
        if (!Byte.valueOf((byte)0).equals(pointerList.get(currentPointerIndex))) {
            int loopNestingLevelCounter = 1;
            while (loopNestingLevelCounter > 0) {
                currentTokenIndex--;
                if (currentTokenIndex < 0) {
                    throw new SyntaxError("There is no loop opening bracket ([) for loop closing bracket (])");
                }
                if (BrainfuckToken.LOOP_END.equals(tokenList.get(currentTokenIndex))) {
                    loopNestingLevelCounter++;
                } else if (BrainfuckToken.LOOP_BEGIN.equals(tokenList.get(currentTokenIndex))) {
                    loopNestingLevelCounter--;
                }
            }
        }
    }

    private void runLoopBeginToken() {
        if (Byte.valueOf((byte)0).equals(pointerList.get(currentPointerIndex))) {
            int loopNestingLevelCounter = 1;
            while (loopNestingLevelCounter > 0) {
                currentTokenIndex++;
                if (BrainfuckToken.LOOP_BEGIN.equals(tokenList.get(currentTokenIndex))) {
                    loopNestingLevelCounter++;
                } else if (BrainfuckToken.LOOP_END.equals(tokenList.get(currentTokenIndex))) {
                    loopNestingLevelCounter--;
                }
            }
        }
    }

}
