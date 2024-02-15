package main.com.chess.engine.common;

import main.com.chess.engine.player.Player;

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
    public Player choosePlayer(final Player white, final Player black){
      return black;
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
    public Player choosePlayer(final Player white, final Player black){
      return white;
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

  public abstract Player choosePlayer(final Player white, final Player black);
}

