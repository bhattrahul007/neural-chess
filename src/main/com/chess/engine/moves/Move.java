package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.board.ChessBoard;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

import java.util.Objects;

/**
 * Represents a move in a chess game.
 */
public abstract class Move implements MoveExecutor{
  protected final Board board;
  protected final Position origin;
  protected final Piece movingPiece;
  protected final Position destination;

  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public Move(final Board board, final Piece movingPiece, final Position destination) {
    assert movingPiece != null;
    assert board != null;
    this.board = board;
    this.origin = movingPiece.getPosition();
    this.movingPiece = movingPiece;
    this.destination = destination;
  }
  /**
   * Gets the board to which this move belongs.
   *
   * @return The board to which this move belongs.
   */
  public Board getBoard(){
    return board;
  }

  /**
   * Gets the origin position of the move.
   *
   * @return The origin position.
   */
  public Position getOrigin() {
    return origin;
  }

  /**
   * Gets the piece making the move.
   *
   * @return The moving piece.
   */
  public Piece getMovingPiece() {
    return movingPiece;
  }

  /**
   * Gets the destination position of the move.
   *
   * @return The destination position.
   */
  public Position getDestination() {
    return destination;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Board execute() {
    final ChessBoard.Builder builder = ChessBoard.builder();
    // set all pieces on board except for the piece current player is moving
    for(final Piece piece: board.getCurrentPlayer().getAllActivePieces()){
      if(!movingPiece.equals(piece)){
        builder.setPiece(piece);
      }
    }
    // place all the opponent pieces as it is
    for(final Piece piece: board.getCurrentPlayer().getOpponent().getAllActivePieces()){
      builder.setPiece(piece);
    }
    // place the moving piece into its destination
    builder.setPiece(movingPiece.move(this));
    builder.setNextMoveMaker(board.getCurrentPlayer().getOpponent().getSide());
    return builder.build();
  }

  /**
   * Indicates whether this move in chess game capture another piece ( of different side ).
   * @return {@code true} if it captures a piece while making move, {@code false} otherwise
   */
  public boolean isCapturingMove(){
    return false;
  }

  /**
   * Gets the chess piece that is being captured.
   * @return the captured piece.
   */
  public Piece getCapturedPiece(){
    return null;
  }

  /**
   * Checks if the move is castle move.
   * @return {@code true} if move played is castle move, {@code false} otherwise.
   */
  public boolean isCastlingMove(){
    return false;
  }

  /**
   * Checks if this move is equal to another object.
   *
   * @param o The object to compare with this move.
   * @return {@code true} if the moves are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Move move)) return false;
    return Objects.equals(movingPiece, move.movingPiece) && Objects.equals(destination, move.destination) &&
      Objects.equals(getCapturedPiece(), move.getCapturedPiece());
  }

  /**
   * Computes the hash code of this move.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(origin, movingPiece, destination, getCapturedPiece(), isCapturingMove());
  }
}
