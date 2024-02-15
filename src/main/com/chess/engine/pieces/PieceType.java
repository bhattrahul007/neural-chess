package main.com.chess.engine.pieces;

/**
 * Enumeration representing the types of chess pieces.
 */
public enum PieceType {

  /**
   * The King piece.
   */
  KING(1000, "K") {
    @Override
    public boolean isKing() {
      return true;
    }
  },

  /**
   * The Queen piece.
   */
  QUEEN(900, "Q") {
    @Override
    public boolean isQueen() {
      return true;
    }
  },

  /**
   * The Rook piece.
   */
  ROOK(500, "R") {
    @Override
    public boolean isRook() {
      return true;
    }
  },

  /**
   * The Knight piece.
   */
  KNIGHT(300, "N") {
    @Override
    public boolean isKnight() {
      return true;
    }
  },

  /**
   * The Bishop piece.
   */
  BISHOP(300, "B") {
    @Override
    public boolean isBishop() {
      return true;
    }
  },

  /**
   * The Pawn piece.
   */
  PAWN(100, "P") {
    @Override
    public boolean isPawn() {
      return true;
    }
  };

  private final int value;
  private final String code;

  /**
   * Constructs a new PieceType with the specified value and code.
   *
   * @param value The value of the piece.
   * @param code  The code representing the piece.
   */
  PieceType(final int value, final String code) {
    this.value = value;
    this.code = code;
  }

  /**
   * Gets the value of the piece.
   *
   * @return The value of the piece.
   */
  public int getValue() {
    return value;
  }

  /**
   * Gets the code representing the piece.
   *
   * @return The code representing the piece.
   */
  public String getCode() {
    return code;
  }

  /**
   * Checks if this piece type is a King.
   *
   * @return {@code true} if this piece type is a King, {@code false} otherwise.
   */
  public boolean isKing() {
    return false;
  }

  /**
   * Checks if this piece type is a Queen.
   *
   * @return {@code true} if this piece type is a Queen, {@code false} otherwise.
   */
  public boolean isQueen() {
    return false;
  }

  /**
   * Checks if this piece type is a Rook.
   *
   * @return {@code true} if this piece type is a Rook, {@code false} otherwise.
   */
  public boolean isRook() {
    return false;
  }

  /**
   * Checks if this piece type is a Knight.
   *
   * @return {@code true} if this piece type is a Knight, {@code false} otherwise.
   */
  public boolean isKnight() {
    return false;
  }

  /**
   * Checks if this piece type is a Bishop.
   *
   * @return {@code true} if this piece type is a Bishop, {@code false} otherwise.
   */
  public boolean isBishop() {
    return false;
  }

  /**
   * Checks if this piece type is a Pawn.
   *
   * @return {@code true} if this piece type is a Pawn, {@code false} otherwise.
   */
  public boolean isPawn() {
    return false;
  }
}
