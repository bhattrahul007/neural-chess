package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.common.Position;

import java.util.Objects;

public class MoveFactory {
  private static final Move NULL_MOVE = new NullMove();
  public static Move createMove(final Board board, final Position initialPos, final Position destinationPos){
    for(final Move move: board.getAllLegalMoves()){
      if(Objects.equals(move.getOrigin(), initialPos) && Objects.equals(move.getDestination(), destinationPos)){
        return move;
      }
    }
    return NULL_MOVE;
  }
}
