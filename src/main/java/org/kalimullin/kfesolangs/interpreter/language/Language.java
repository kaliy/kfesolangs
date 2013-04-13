package org.kalimullin.kfesolangs.interpreter.language;

import org.apache.commons.lang.StringUtils;

public enum Language {
    BRAINFUCK, OOK;

    public static String getAllLanguagesString() {
        return StringUtils.join(values(), ", ");
    }

    public String toString() {
        return name().toLowerCase();
    }

}
