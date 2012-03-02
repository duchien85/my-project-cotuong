package chess.logic.game.piece;

public class General extends Piece {
    public void caculateTargets() {
        // clear target array
        clearTargets();

        // forward
        if (checkCell(iRow + 1, iCol)) {
            addTarget(iRow + 1, iCol);
        }
        // back
        if (checkCell(iRow - 1, iCol)) {
            addTarget(iRow - 1, iCol);
        }
        // side 1
        if (checkCell(iRow, iCol + 1)) {
            addTarget(iRow, iCol + 1);
        }
        // side 2
        if (checkCell(iRow, iCol - 1)) {
            addTarget(iRow, iCol - 1);
        }
    }
    
    public void initMoreData() {
        
    }
    
    public boolean checkTarget(int row, int col) {
        // forward
        if (checkCell(iRow + 1, iCol)) {
            if (((iRow + 1) == row) && (iCol == col))
                return true;
        }
        // back
        if (checkCell(iRow - 1, iCol)) {
            if (((iRow - 1) == row) && (iCol == col))
                return true;
        }
        // side 1
        if (checkCell(iRow, iCol + 1)) {
            if (((iRow) == row) && ((iCol + 1) == col))
                return true;
        }
        // side 2
        if (checkCell(iRow, iCol - 1)) {
            if (((iRow) == row) && ((iCol - 1) == col))
                return true;
        }
        
        return false;
    }
}

