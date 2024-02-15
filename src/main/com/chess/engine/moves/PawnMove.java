package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents pawn move on board.
 */
public class PawnMove extends Move{
  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param board
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public PawnMove(Board board, Piece movingPiece, Position destination) {
    super(board, movingPiece, destination);
  }
}
