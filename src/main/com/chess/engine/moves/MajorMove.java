package main.com.chess.engine.moves;

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
  public MajorMove(Piece movingPiece, Position destination) {
    super(movingPiece, destination);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isCapturingMove() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Piece getCapturedPiece() {
    return null;
  }
}
