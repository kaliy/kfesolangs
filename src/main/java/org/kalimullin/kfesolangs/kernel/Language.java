package org.kalimullin.kfesolangs.kernel;

import org.apache.commons.lang.StringUtils;

public enum Language {
    BRAINFUCK;

    public static String getAllLanguagesString() {
        return StringUtils.join(values(), ", ");
    }

    public String toString() {
        return name().toLowerCase();
    }

}
