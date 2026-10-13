package org.example.entities;

import org.example.enums.Symbol;

public class Board {
    private Cell[][] gridBoard;
    private int size;

    public Board(int size) {
        this.size = size;
        this.gridBoard = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridBoard[i][j] = new Cell();
            }
        }
    }
    public void placeSymbol(int row, int col, Symbol symbol){
        validatePosition(row, col);
        gridBoard[row][col].setSymbol(symbol);
    }
    public boolean isCellEmpty(int row, int col){
        validatePosition(row, col);
        return gridBoard[row][col].isEmpty();
    }
    public boolean isFull(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(gridBoard[i][j].isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
    public void validatePosition(int row, int col){
        if(row < 0 || row >= size || col < 0 || col >= size){
            throw new IllegalArgumentException("Position [ " + row + " ] [ " + col +" ] out of bounds");
        }
    }

    public Cell getGridBoardCell(int row, int col) {
        validatePosition(row, col);
        return gridBoard[row][col];
    }

    public int getSize() {
        return size;
    }

    public void printBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" " + gridBoard[i][j].getSymbol() + " ");
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < size - 1) {
                System.out.println("---+---+---");
            }
        }
    }
}
