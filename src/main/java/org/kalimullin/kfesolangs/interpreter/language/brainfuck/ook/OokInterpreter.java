package org.kalimullin.kfesolangs.interpreter.language.brainfuck.ook;

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
        List<BrainfuckToken> ookTokenList = new ArrayList<BrainfuckToken>();
        //TODO: throw exception if operator number is odd
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

}
