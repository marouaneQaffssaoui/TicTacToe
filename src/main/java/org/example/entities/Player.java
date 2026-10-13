package org.example.entities;

import org.example.enums.Symbol;

public class Player {
    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        if(symbol == Symbol.EMPTY){
            throw new IllegalArgumentException("Player cannot play without a symbol");
        }
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name + " { " + symbol.getDisplayChar() + " }";
    }
}
