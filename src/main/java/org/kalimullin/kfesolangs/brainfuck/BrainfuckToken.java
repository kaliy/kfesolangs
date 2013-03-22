package org.kalimullin.kfesolangs.brainfuck;

/**
 * Brainfuck operator token.
 */
public enum BrainfuckToken {
    /**
     * ">": increment the data pointer (to point to the next cell to the right).
     */
    NEXT_POINTER(">"),
    /**
     * "<": decrement the data pointer (to point to the next cell to the left).
     */
    PREVIOUS_POINTER("<"),
    /**
     * "+": increment (increase by one) the byte at the data pointer.
     */
    INCREMENT("+"),
    /**
     * "-": decrement (decrease by one) the byte at the data pointer.
     */
    DECREMENT("-"),
    /**
     * ".": output the byte at the data pointer.
     */
    OUTPUT("."),
    /**
     * ",": - accept one byte of input, storing its value in the byte at the data pointer.
     */
    INPUT(","),
    /**
     * "[": if the byte at the data pointer is zero, then instead of moving the instruction pointer forward to the next command, jump it forward to the command after the matching ] command.
     */
    LOOP_BEGIN("["),
    /**
     * "]": if the byte at the data pointer is nonzero, then instead of moving the instruction pointer forward to the next command, jump it back to the command after the matching [ command.
     */
    LOOP_END("]");

    BrainfuckToken(String token) {
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    /**
     * Creates token from char. Token will be created only if character equals one of the language operator.
     * Otherwise method will return null.<br />
     * For example, from char '-' BrainfuckToken.DECREMENT will be created but from char 'a' or 'b' this method will return null.
     * @param c operator character
     * @return BrainfuckToken if character corresponds any token, null otherwise
     */
    public static BrainfuckToken fromChar(char c) {
        for (BrainfuckToken brainfuckToken: BrainfuckToken.values()) {
            if (brainfuckToken.getToken().equals(Character.toString(c))) {
                return brainfuckToken;
            }
        }
        return null;
    }
}
