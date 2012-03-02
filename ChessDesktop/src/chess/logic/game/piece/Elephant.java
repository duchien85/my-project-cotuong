package chess.logic.game.piece;

public class Elephant extends Piece {
    public void caculateTargets() {
        // clear target array
        clearTargets();

        // forward
        if (isCellEmpty(iRow + 1, iCol - 1)) {
            if (checkCell(iRow + 2, iCol - 2)) {
                addTarget(iRow + 2, iCol - 2);
            }
        }
        // back
        if (isCellEmpty(iRow + 1, iCol + 1)) {
            if (checkCell(iRow + 2, iCol + 2)) {
                addTarget(iRow + 2, iCol + 2);
            }
        }
        // side 1
        if (isCellEmpty(iRow - 1, iCol + 1)) {
            if (checkCell(iRow - 2, iCol + 2)) {
                addTarget(iRow - 2, iCol + 2);
            }
        }
        // side 2
        if (isCellEmpty(iRow - 1, iCol - 1)) {
            if (checkCell(iRow - 2, iCol - 2)) {
                addTarget(iRow - 2, iCol - 2);
            }
        }
    }
    
    public void initMoreData() {
        
    }
    
    public boolean checkTarget(int row, int col) {
        // forward
        if (isCellEmpty(iRow + 1, iCol - 1)) {
            if (checkCell(iRow + 2, iCol - 2)) {
                if (((iRow + 2) == row) && ((iCol - 2) == col))
                    return true;
            }
        }
        // back
        if (isCellEmpty(iRow + 1, iCol + 1)) {
            if (checkCell(iRow + 2, iCol + 2)) {
                if (((iRow + 2) == row) && ((iCol + 2) == col))
                    return true;
            }
        }
        // side 1
        if (isCellEmpty(iRow - 1, iCol + 1)) {
            if (checkCell(iRow - 2, iCol + 2)) {
                if (((iRow - 2) == row) && ((iCol + 2) == col))
                    return true;
            }
        }
        // side 2
        if (isCellEmpty(iRow - 1, iCol - 1)) {
            if (checkCell(iRow - 2, iCol - 2)) {
                if (((iRow - 2) == row) && ((iCol - 2) == col))
                    return true;
            }
        }

        return false;
    }
}

