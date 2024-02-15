package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents the en-passant pawn capture move on board.
 */
public class PawnEnPassantCaptureMove extends PawnCaptureMove{
  /**
   * Constructs a capture move with the attacking piece and the piece being captured.
   *
   * @param board
   * @param attackingPiece The piece making the capture move.
   * @param capturedPiece  The piece being captured.
   */
  public PawnEnPassantCaptureMove(Board board, Piece attackingPiece, Piece capturedPiece) {
    super(board, attackingPiece, capturedPiece);
  }
}
