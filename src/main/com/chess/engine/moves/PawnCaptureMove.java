package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents the capture move by pawn on board.
 */
public class PawnCaptureMove extends CaptureMove{
  /**
   * Constructs a capture move with the attacking piece and the piece being captured.
   *
   * @param board
   * @param attackingPiece The piece making the capture move.
   * @param capturedPiece  The piece being captured.
   */
  public PawnCaptureMove(Board board, Piece attackingPiece, Piece capturedPiece) {
    super(board, attackingPiece, capturedPiece);
  }
}
