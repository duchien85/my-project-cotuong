package chess.logic.game.piece;

public class Cell {
    public int PieceID;
    public int PieceOwnerID;

    public Cell() {
        PieceID = -1;
        PieceOwnerID = -1;
    }
    
    public void Clear() {
        PieceID = -1;
        PieceOwnerID = -1;
    }
}
