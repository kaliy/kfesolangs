package org.kalimullin.kfesolangs.brainfuck;

import org.apache.commons.io.IOUtils;
import org.kalimullin.kfesolangs.kernel.Interpreter;
import org.kalimullin.kfesolangs.kernel.SyntaxError;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BrainfuckInterpreter extends Interpreter {

    private List<BrainfuckToken> tokenList;
    private int currentTokenIndex = -1; //TODO refactor looping
    private int currentPointerIndex = 0;
    private List<Integer> pointerList = new ArrayList<Integer>();

    public BrainfuckInterpreter() {
        //XXX
        pointerList.add(0);
    }

    @Override
    public void interpret(InputStream inputStream) throws SyntaxError {
        try {
            tokenList = getTokenList(IOUtils.toCharArray(inputStream));
        } catch (IOException e) {
            // TODO: better error handling
            e.printStackTrace();
        }
        while (currentTokenIndex < tokenList.size() - 1) {
            currentTokenIndex++;
            switch (tokenList.get(currentTokenIndex)) {
                case INCREMENT:
                    runIncrementToken();
                    break;
                case DECREMENT:
                    runDecrementToken();
                    break;
                case OUTPUT:
                    runOutputToken();
                    break;
            }
        }
    }

    /**
     * Creates token list from character array.
     * @param chars character array from brainfuck program source code
     * @return List with BrainfuckToken
     */
    private List<BrainfuckToken> getTokenList(char[] chars) {
        List<BrainfuckToken> tokens = new ArrayList<BrainfuckToken>();
        for (char c: chars) {
            BrainfuckToken token = BrainfuckToken.fromChar(c);
            if (null != token) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    private void runIncrementToken() {
        pointerList.set(currentPointerIndex, pointerList.get(currentPointerIndex) + 1);
    }

    private void runDecrementToken() {
        pointerList.set(currentPointerIndex, pointerList.get(currentPointerIndex) - 1);
    }

    private void runOutputToken() {
        //TODO make output more abstract with OutputStream
        System.out.print((char)pointerList.get(currentPointerIndex).intValue());
    }

}
