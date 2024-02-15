package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;

public class MoveTransition {
  protected final Board transitionBoard;
  protected final Move move;
  protected final MoveStatus status;

  public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus status){
    this.transitionBoard = transitionBoard;
    this.move = move;
    this.status = status;
  }

  public Board getTransitionBoard() {
    return transitionBoard;
  }

  public Move getMove() {
    return move;
  }

  public MoveStatus getStatus() {
    return status;
  }
}
