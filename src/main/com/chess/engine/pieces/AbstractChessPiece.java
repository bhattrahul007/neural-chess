package main.com.chess.engine.pieces;

import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;

import java.util.Objects;

/**
 * An abstract base class representing a chess piece.
 */
public abstract class AbstractChessPiece implements Piece {

  /**
   * The position of the piece on the chessboard.
   */
  protected final Position position;

  /**
   * The side (color) of the piece.
   */
  protected final Side side;

  /**
   * The type of the piece.
   */
  protected final PieceType type;

  /**
   * Indicates whether the piece has moved during the game.
   */
  protected final boolean moved;

  /**
   * Constructs a new AbstractChessPiece with the specified position, side, and type.
   *
   * @param position The position of the piece on the chessboard.
   * @param side     The side (color) of the piece.
   * @param type     The type of the piece.
   */
  protected AbstractChessPiece(final Position position, final Side side, final PieceType type) {
    this.position = position;
    this.side = side;
    this.type = type;
    this.moved = false;
  }

  /**
   * Constructs a new AbstractChessPiece with the specified position, side, type, and moved status.
   *
   * @param position The position of the piece on the chessboard.
   * @param side     The side (color) of the piece.
   * @param type     The type of the piece.
   * @param moved    Indicates whether the piece has moved during the game.
   */
  protected AbstractChessPiece(final Position position, final Side side, final PieceType type, final boolean moved) {
    this.position = position;
    this.side = side;
    this.type = type;
    this.moved = moved;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Position getPosition() {
    return position;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Side getSide() {
    return side;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PieceType getType() {
    return type;
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasMoved() {
    return moved;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractChessPiece that)) return false;
    return moved == that.moved && Objects.equals(position, that.position) && side == that.side && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, side, type, moved);
  }

  @Override
  public String toString() {
    return this.side + "_" + this.type;
  }
}
