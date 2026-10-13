package org.example.entities;

import org.example.enums.GameStatus;
import org.example.enums.Symbol;
import org.example.exceptions.InvalidMoveException;

public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private GameStatus status;

    public Game(Player player1, Player player2, int boardSize) {
        if (player1.getSymbol() == player2.getSymbol()) {
            throw new IllegalArgumentException("Players must have different symbols");
        }
        this.board = new Board(boardSize);
        this.players = new Player[]{player1, player2};
        this.currentPlayerIndex = 0;
        this.status = GameStatus.IN_PROGRESS;
    }
    public synchronized void makeMove(int row, int col) {
        if(status != GameStatus.IN_PROGRESS){
            throw new InvalidMoveException("Game is over!");
        }
        if(!board.isCellEmpty(row, col)){
            throw new InvalidMoveException("Cell [ " + row + " ][ " + col + " ] is occupied");
        }
        Player currentTurnPlayer = players[currentPlayerIndex];
        board.placeSymbol(row, col, currentTurnPlayer.getSymbol());
        if(checkTheWinner(row, col, currentTurnPlayer.getSymbol())){
            status = (currentTurnPlayer.getSymbol() == Symbol.X) ? GameStatus.WINNER_X : GameStatus.WINNER_O;
            return;
        }
        if(board.isFull()){
            status = GameStatus.DRAW;
            return;
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    private boolean checkTheWinner(int row, int col, Symbol symbol) {
        int size = board.getSize();
        boolean rowWon = true;
        for (int i = 0; i < size; i++) {
            if (board.getGridBoardCell(row, i).getSymbol() != symbol) {
                rowWon = false;
                break;
            }
        }
        if(rowWon) return true;
        boolean colWon = true;
        for (int j = 0; j < size; j++) {
            if(board.getGridBoardCell(j, col).getSymbol() != symbol){
                colWon = false;
                break;
            }
        }
        if (colWon) return true;
        if(row == col){
            boolean diagonal = true;
            for (int d = 0; d < size; d++) {
                if (board.getGridBoardCell(d, d).getSymbol() != symbol) {
                    diagonal = false;
                    break;
                }
            }
            if (diagonal) return true;
        }
        if(row + col == size - 1){
            boolean antiDiagonalWon = true;
            for(int i = 0; i < size; i++){
                if(board.getGridBoardCell(i, size - 1 -i ).getSymbol() != symbol){
                    antiDiagonalWon = false;
                    break;
                }
            }
            if (antiDiagonalWon) return true;
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getTheWinnerPlayer() {
        if(status == GameStatus.WINNER_X){
            return players[0].getSymbol() == Symbol.X ? players[0] : players[1];
        }else if (status == GameStatus.WINNER_O) {
            return players[0].getSymbol() == Symbol.O ? players[0] : players[1];
        }
        return null;
    }
    public void printBoard() {
        board.printBoard();
    }
}
