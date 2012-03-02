package chess.logic.game.piece;

public class Guard extends Piece {
    public void caculateTargets() {
        // clear target array
        clearTargets();

        // forward
        if (checkCell(iRow + 1, iCol - 1)) {
            addTarget(iRow + 1, iCol - 1);
        }
        // back
        if (checkCell(iRow + 1, iCol + 1)) {
            addTarget(iRow + 1, iCol + 1);
        }
        // side 1
        if (checkCell(iRow - 1, iCol + 1)) {
            addTarget(iRow - 1, iCol + 1);
        }
        // side 2
        if (checkCell(iRow - 1, iCol - 1)) {
            addTarget(iRow - 1, iCol - 1);
        }
    }
    
    public void initMoreData() {
        
    }
    
    public boolean checkTarget(int row, int col) {
        // forward
        if (checkCell(iRow + 1, iCol - 1)) {
            if (((iRow + 1) == row) && ((iCol - 1) == col))
                return true;
        }
        // back
        if (checkCell(iRow + 1, iCol + 1)) {
            if (((iRow + 1) == row) && ((iCol + 1) == col))
                return true;
        }
        // side 1
        if (checkCell(iRow - 1, iCol + 1)) {
            if (((iRow - 1) == row) && ((iCol + 1) == col))
                return true;
        }
        // side 2
        if (checkCell(iRow - 1, iCol - 1)) {
            if (((iRow - 1) == row) && ((iCol - 1) == col))
                return true;
        }

        return false;
    }
}

