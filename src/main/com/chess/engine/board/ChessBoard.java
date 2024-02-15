package main.com.chess.engine.board;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;
import main.com.chess.engine.moves.Move;
import main.com.chess.engine.pieces.*;
import main.com.chess.engine.player.*;

import java.util.*;

/**
 * Represents the chess board.
 */
public class ChessBoard implements Board {
  private final List<BoardSquare> squares;
  private final Collection<Piece> wpieces;
  private final Collection<Piece> bpieces;

  private final Player wplayer;
  private final Player bplayer;

  private final Player currentPlayer;

  private ChessBoard(Builder builder) {
    this.squares = initChessBoardSquares(builder);
    this.wpieces = collectAllPiecesOnBoardForSide(squares, Side.WHITE);
    this.bpieces = collectAllPiecesOnBoardForSide(squares, Side.BLACK);

    final Collection<Move> wLegalMoves = collectAllMovesByPieces(wpieces);
    final Collection<Move> bLegalMoves = collectAllMovesByPieces(bpieces);
    this.wplayer = new WhitePlayer(this, wLegalMoves, bLegalMoves);
    this.bplayer = new BlackPlayer(this, bLegalMoves, wLegalMoves);
    this.currentPlayer = builder.nextMoveMaker.choosePlayer(wplayer, bplayer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BoardSquare getSquare(Position pos) {
    try{
      return this.squares.get(pos.getX());
    }catch(IndexOutOfBoundsException e){
      return null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Piece> getAllActiveWhitePieces(){
    return this.wpieces;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Piece> getAllActiveBlackPieces(){
    return this.bpieces;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getWhitePlayer(){
    return wplayer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getBlackPlayer(){
    return bplayer;
  }

  @Override
  public Player getCurrentPlayer(){
    return currentPlayer;
  }

  /**
   * Initializes the chess board squares based on the provided builder.
   *
   * @param builder The builder containing information about pieces and their positions.
   * @return The list of initialized board squares.
   */
  public static List<BoardSquare> initChessBoardSquares(Builder builder) {
    final int TOTAL_SIZE = BoardUtils.BOARD_ROWS * BoardUtils.BOARD_COLS;
    final BoardSquare[] squares = new BoardSquare[TOTAL_SIZE];
    for (int i = 0; i < TOTAL_SIZE; i++) {
      squares[i] = AbstractBoardSquare.createSquare(i, builder.pieces.get(i));
    }
    return ImmutableList.copyOf(squares);
  }


  /**
   * Initializes a standard chess board with all pieces in their starting positions.
   *
   * @return The initialized standard chess board.
   */
  public static ChessBoard initStandardChessBoard(){
    Builder builder = new Builder();
    // initialize black pieces
    builder
      .setPiece(new RookChessPiece(new Position(0), Side.BLACK))
      .setPiece(new KnightChessPiece(new Position(1), Side.BLACK))
      .setPiece(new BishopChessPiece(new Position(2), Side.BLACK))
      .setPiece(new QueenChessPiece(new Position(3), Side.BLACK))
      .setPiece(new KingChessPiece(new Position(4), Side.BLACK))
      .setPiece(new BishopChessPiece(new Position(5), Side.BLACK))
      .setPiece(new KnightChessPiece(new Position(6), Side.BLACK))
      .setPiece(new RookChessPiece(new Position(7), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(8), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(9), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(10), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(11), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(12), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(13), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(14), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(15), Side.BLACK));

    // white pieces
    builder
      .setPiece(new RookChessPiece(new Position(56), Side.WHITE))
      .setPiece(new KnightChessPiece(new Position(57), Side.WHITE))
      .setPiece(new BishopChessPiece(new Position(58), Side.WHITE))
      .setPiece(new QueenChessPiece(new Position(59), Side.WHITE))
      .setPiece(new KingChessPiece(new Position(60), Side.WHITE))
      .setPiece(new BishopChessPiece(new Position(61), Side.WHITE))
      .setPiece(new KnightChessPiece(new Position(62), Side.WHITE))
      .setPiece(new RookChessPiece(new Position(63), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(48), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(49), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(50), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(51), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(52), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(53), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(54), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(55), Side.WHITE));

    // side who is allowed to make move
    builder.setNextMoveMaker(Side.WHITE);

    return builder.build();
  }

  /**
   * Collects all pieces on the board for a specific side.
   *
   * @param squares The list of board squares representing the chessboard.
   * @param side    The side for which to collect pieces.
   * @return A collection of pieces belonging to the specified side.
   */
  public Collection<Piece> collectAllPiecesOnBoardForSide(final List<BoardSquare> squares, final Side side){
    final List<Piece> pieces = new ArrayList<>();
    for(final BoardSquare square: squares){
      Piece occupiedBy = square.getOccupiedBy();
      if(!square.isEmpty() && occupiedBy.getSide() == side){
        pieces.add(occupiedBy);
      }
    }
    return ImmutableList.copyOf(pieces);
  }

  /**
   * Collects all moves made by the given collection of pieces.
   *
   * @param pieces The collection of pieces for which to collect moves.
   * @return A collection of all moves made by the given pieces.
   */
  public Collection<Move> collectAllMovesByPieces(final Collection<Piece> pieces){
    List<Move> moves = new ArrayList<>();
    for(final Piece piece: pieces){
      moves.addAll(piece.generateAllMoves(this));
    }
    return ImmutableList.copyOf(moves);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Move> getAllLegalMoves(){
    return Iterables.unmodifiableIterable(Iterables.concat(wplayer.getAllLegalMoves(), bplayer.getAllLegalMoves()));
  }

  /**
   * Creates a new instance of the ChessBoard.Builder.
   *
   * @return A new ChessBoard builder.
   */
  public static Builder builder(){
    return new Builder();
  }

  /**
   * Generates a string representation of the chess board.
   *
   * @return A string representation of the chess board.
   */
  @Override
  public String toString(){
    final int TOTAL_SIZE = BoardUtils.BOARD_ROWS * BoardUtils.BOARD_COLS;
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<TOTAL_SIZE; i++){
      sb.append(this.squares.get(i).toString());
      if(((i+1)%BoardUtils.BOARD_ROWS) == 0){
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Builder class for creating instances of ChessBoard.
   */
  public static class Builder {
    private final Map<Integer, Piece> pieces;
    private Side nextMoveMaker;
    private Piece enPassant;

    /**
     * Constructs a new ChessBoard builder.
     */
    private Builder() {
      this.pieces = new HashMap<>();
    }

    /**
     * Sets the side of the player to make the next move.
     *
     * @param side The side of the player to make the next move.
     * @return The builder instance.
     */
    public Builder setNextMoveMaker(final Side side) {
      this.nextMoveMaker = side;
      return this;
    }

    /**
     * Sets a piece on the board.
     *
     * @param piece The piece to set on the board.
     * @return The builder instance.
     */
    public Builder setPiece(final Piece piece) {
      this.pieces.put(piece.getPosition().getX(), piece);
      return this;
    }

    public Builder setEnPassant(final Piece piece){
      this.enPassant = piece;
      return this;
    }

    /**
     * Builds and returns an instance of ChessBoard based on the builder's configuration.
     *
     * @return An instance of ChessBoard.
     */
    public ChessBoard build() {
      return new ChessBoard(this);
    }
  }
}
