package main.com.chess.engine.moves;

public enum MoveStatus {
  DONE {
    @Override
    public boolean isDone(){
      return true;
    }
  },
  ILLEGAL_MOVE, LEAVE_PLAYER_IN_CHECK;

  public boolean isDone(){
    return false;
  }
}
