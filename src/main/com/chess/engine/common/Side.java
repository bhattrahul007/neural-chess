package main.com.chess.engine.common;

/**
 * An enumeration representing the two sides in chess: White and Black.
 * Each side has methods to determine whether it represents White or Black.
 */
public enum Side {

  /**
   * The White side in chess.
   */
  WHITE {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWhite() {
      return true;
    }

    @Override
    public String toString(){
      return "W";
    }
  },

  /**
   * The Black side in chess.
   */
  BLACK {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBlack() {
      return true;
    }

    @Override
    public String toString(){
      return "B";
    }
  };

  /**
   * Checks if the side is White.
   *
   * @return {@code true} if the side is White, {@code false} otherwise.
   */
  public boolean isWhite() {
    return false;
  }

  /**
   * Checks if the side is Black.
   *
   * @return {@code true} if the side is Black, {@code false} otherwise.
   */
  public boolean isBlack() {
    return false;
  }
}

