package main.com.chess.engine.pieces;

import main.com.chess.engine.common.Position;

public interface Jumpable {

  /** Logic to determine if the piece can jump to the specified square
   * implementation based on the current position and the offset
   * @return  true if the jump is possible, false otherwise
   */
  public boolean canJumpToSquareFromCurrentPosition(final Position currPos, final int offset);
}
