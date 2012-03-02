package chess.logic.game.piece;

public class Cavarly extends Piece {
    public void caculateTargets() {
        clearTargets();

        // up
        if (isCellEmpty(iRow + 1, iCol)) // kiem tra bi cha.n
        {
            if (checkCell(iRow + 2, iCol - 1)) {
                addTarget(iRow + 2, iCol - 1);
            }
            if (checkCell(iRow + 2, iCol + 1)) {
                addTarget(iRow + 2, iCol + 1);
            }
        }

        // down
        if (isCellEmpty(iRow - 1, iCol)) // kiem tra bi cha.n
        {
            if (checkCell(iRow - 2, iCol - 1)) {
                addTarget(iRow - 2, iCol - 1);
            }
            if (checkCell(iRow - 2, iCol + 1)) {
                addTarget(iRow - 2, iCol + 1);
            }
        }

        // left
        if (isCellEmpty(iRow, iCol - 1)) // kiem tra bi cha.n
        {
            if (checkCell(iRow - 1, iCol - 2)) {
                addTarget(iRow - 1, iCol - 2);
            }
            if (checkCell(iRow + 1, iCol - 2)) {
                addTarget(iRow + 1, iCol - 2);
            }
        }

        // right
        if (isCellEmpty(iRow, iCol + 1)) // kiem tra bi cha.n
        {
            if (checkCell(iRow - 1, iCol + 2)) {
                addTarget(iRow - 1, iCol + 2);
            }
            if (checkCell(iRow + 1, iCol + 2)) {
                addTarget(iRow + 1, iCol + 2);
            }
        }
    }
    
    public void initMoreData() {
        
    }
    
    public boolean checkTarget(int row, int col) {
        // up
        if (isCellEmpty(iRow + 1, iCol)) // kiem tra bi cha.n
        {
            if (checkCell(iRow + 2, iCol - 1)) {
                if (((iRow + 2) == row) && ((iCol - 1) == col))
                    return true;
            }
            if (checkCell(iRow + 2, iCol + 1)) {
                if (((iRow + 2) == row) && ((iCol + 1) == col))
                    return true;
            }
        }

        // down
        if (isCellEmpty(iRow - 1, iCol)) // kiem tra bi cha.n
        {
            if (checkCell(iRow - 2, iCol - 1)) {
                if (((iRow - 2) == row) && ((iCol - 1) == col))
                    return true;
            }
            if (checkCell(iRow - 2, iCol + 1)) {
                if (((iRow - 2) == row) && ((iCol + 1) == col))
                    return true;
            }
        }

        // left
        if (isCellEmpty(iRow, iCol - 1)) // kiem tra bi cha.n
        {
            if (checkCell(iRow - 1, iCol - 2)) {
                if (((iRow - 1) == row) && ((iCol - 2) == col))
                    return true;
            }
            if (checkCell(iRow + 1, iCol - 2)) {
                if (((iRow + 1) == row) && ((iCol - 2) == col))
                    return true;
            }
        }

        // right
        if (isCellEmpty(iRow, iCol + 1)) // kiem tra bi cha.n
        {
            if (checkCell(iRow - 1, iCol + 2)) {
                if (((iRow - 1) == row) && ((iCol + 2) == col))
                    return true;
            }
            if (checkCell(iRow + 1, iCol + 2)) {
                if (((iRow + 1) == row) && ((iCol + 2) == col))
                    return true;
            }
        }

        return false;
    }
}

