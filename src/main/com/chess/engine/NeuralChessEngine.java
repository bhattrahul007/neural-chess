package main.com.chess.engine;

import main.com.chess.engine.board.ChessBoard;

/**
 * Main chess engine class that is responsible for manipulating chess
 * board
 */
public class NeuralChessEngine {

  public static void main(String[] args) {
    ChessBoard board = ChessBoard.initStandardChessBoard();
    System.out.println(board);
  }
}
