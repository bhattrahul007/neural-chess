package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;

public interface MoveExecutor {
  public Board execute();
}
