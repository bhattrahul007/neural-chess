package main.com.chess.engine.moves;

import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

import java.util.Objects;

/**
 * Represents a move in a chess game.
 */
public abstract class Move {
  private final Position origin;
  private final Piece movingPiece;
  private final Position destination;

  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public Move(final Piece movingPiece, final Position destination) {
    this.origin = movingPiece.getPosition();
    this.movingPiece = movingPiece;
    this.destination = destination;
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
   * Indicates whether this move in chess game capture another piece ( of different side ).
   * @return {@code true} if it captures a piece while making move, {@code false} otherwise
   */
  public abstract boolean isCapturingMove();

  /**
   * Gets the chess piece that is being captured.
   * @return the captured piece.
   */
  public abstract Piece getCapturedPiece();

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
    return Objects.equals(origin, move.origin) && Objects.equals(movingPiece, move.movingPiece) && Objects.equals(destination, move.destination)
      && Objects.equals(isCapturingMove(), move.isCapturingMove()) && Objects.equals(getCapturedPiece(), move.getCapturedPiece());
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
