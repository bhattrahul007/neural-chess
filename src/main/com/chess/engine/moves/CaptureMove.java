package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents a move in a chess game where a piece captures another piece.
 */
public class CaptureMove extends Move {
  private final Piece capturedPiece;

  /**
   * Constructs a capture move with the attacking piece and the piece being captured.
   *
   * @param attackingPiece The piece making the capture move.
   * @param capturedPiece  The piece being captured.
   */
  public CaptureMove(final Piece attackingPiece, final Piece capturedPiece) {
    super(attackingPiece, capturedPiece.getPosition());
    this.capturedPiece = capturedPiece;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isCapturingMove() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Piece getCapturedPiece() {
    return capturedPiece;
  }

  @Override
  public Board execute() {
    return null;
  }
}

