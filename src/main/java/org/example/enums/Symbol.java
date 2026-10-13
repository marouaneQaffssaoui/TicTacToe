package org.example.enums;

public enum Symbol {
    X('X'),
    O('O'),
    EMPTY('_');
    private char displayChar;

    Symbol(char displayChar) {
        this.displayChar = displayChar;
    }

    public char getDisplayChar() {
        return displayChar;
    }
}
