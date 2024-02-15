package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.board.ChessBoard;
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
  public CaptureMove(final Board board, final Piece attackingPiece, final Piece capturedPiece) {
    super(board, attackingPiece, capturedPiece.getPosition());
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
    ChessBoard.Builder builder = ChessBoard.builder();
    // place all the non-attacking current player pieces
    for(final Piece piece: board.getCurrentPlayer().getAllActivePieces()){
      if(!movingPiece.equals(piece)){
        builder.setPiece(piece);
      }
    }
    // place all the pieces except for the captured one for opponent
    for(final Piece piece: board.getCurrentPlayer().getOpponent().getAllActivePieces()){
      if(!capturedPiece.equals(piece)){
        builder.setPiece(piece);
      }
    }
    // place the attacking piece into the location of captured piece
    builder.setPiece(movingPiece.move(this));
    // change the player turn
    builder.setNextMoveMaker(board.getCurrentPlayer().getOpponent().getSide());
    return builder.build();
  }
}

