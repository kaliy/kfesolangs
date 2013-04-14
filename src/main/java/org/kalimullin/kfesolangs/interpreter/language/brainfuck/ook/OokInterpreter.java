package org.kalimullin.kfesolangs.interpreter.language.brainfuck.ook;

import org.kalimullin.kfesolangs.interpreter.SyntaxError;
import org.kalimullin.kfesolangs.interpreter.language.brainfuck.BrainfuckInterpreter;
import org.kalimullin.kfesolangs.interpreter.language.brainfuck.BrainfuckToken;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @see <a href="http://www.dangermouse.net/esoteric/ook.html">Information about Ook language</a>
 */
public class OokInterpreter extends BrainfuckInterpreter {

    @Override
    protected List<BrainfuckToken> getTokenList(String source) {
        checkOperatorNumber(source);
        List<BrainfuckToken> ookTokenList = new ArrayList<BrainfuckToken>();
        Pattern ookTokenSearchPattern = Pattern.compile("Ook(\\p{Punct}).*?Ook(\\p{Punct})",
                Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher ookTokenSearchMatcher = ookTokenSearchPattern.matcher(source);
        while (ookTokenSearchMatcher.find()) {
            String tokenString = ookTokenSearchMatcher.group(1) + ookTokenSearchMatcher.group(2);
            if (tokenString.equals("..")) {
                ookTokenList.add(BrainfuckToken.INCREMENT);
            } else if (tokenString.equals("!!")) {
                ookTokenList.add(BrainfuckToken.DECREMENT);
            } else if (tokenString.equals(".?")) {
                ookTokenList.add(BrainfuckToken.NEXT_POINTER);
            } else if (tokenString.equals("?.")) {
                ookTokenList.add(BrainfuckToken.PREVIOUS_POINTER);
            } else if (tokenString.equals("!?")) {
                ookTokenList.add(BrainfuckToken.LOOP_BEGIN);
            } else if (tokenString.equals("?!")) {
                ookTokenList.add(BrainfuckToken.LOOP_END);
            } else if (tokenString.equals("!.")) {
                ookTokenList.add(BrainfuckToken.OUTPUT);
            } else if (tokenString.equals(".!")) {
                ookTokenList.add(BrainfuckToken.INPUT);
            }
        }
        return ookTokenList;
    }

    private void checkOperatorNumber(String source) {
        Pattern ookTokenSearchPattern = Pattern.compile("Ook\\p{Punct}");
        Matcher ookTokenSearchMatcher = ookTokenSearchPattern.matcher(source);
        int matchCounter = 0;
        while (ookTokenSearchMatcher.find()) {
            matchCounter++;
        }
        if (matchCounter % 2 != 0) {
            throw new SyntaxError("Source code should have even \"Ook\" operators number");
        }
    }

}
