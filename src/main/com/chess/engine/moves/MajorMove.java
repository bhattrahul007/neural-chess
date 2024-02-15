package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.board.ChessBoard;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents a major move in a chess game, where a piece moves to a new position without capturing another piece.
 */
public class MajorMove extends Move{
  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public MajorMove(final Board board, final Piece movingPiece, final Position destination) {
    super(board, movingPiece, destination);
  }
}
