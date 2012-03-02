package chess.logic.game.piece;

import chess.logic.game.bean.LogicConstant;


public class Rook extends Piece {
    public void caculateTargets() {
        int i;

        clearTargets();

        // up
        for (i = iRow + 1; i < LogicConstant.MAX_ROW; i++) {
            if (isCellEmpty(i, iCol)) {
                addTarget(i, iCol);
            } else {
                if (checkCell(i, iCol)) {
                    addTarget(i, iCol);
                }
                break;
            }
        }
        // down
        for (i = iRow - 1; i >= 0; i--) {
            if (isCellEmpty(i, iCol)) {
                addTarget(i, iCol);
            } else {
                if (checkCell(i, iCol)) {
                    addTarget(i, iCol);
                }
                break;
            }
        }

        // left
        for (i = iCol - 1; i >= 0; i--) {
            if (isCellEmpty(iRow, i)) {
                addTarget(iRow, i);
            } else {
                if (checkCell(iRow, i)) {
                    addTarget(iRow, i);
                }
                break;
            }
        }

        // right
        for (i = iCol + 1; i < LogicConstant.MAX_COL; i++) {
            if (isCellEmpty(iRow, i)) {
                addTarget(iRow, i);
            } else {
                if (checkCell(iRow, i)) {
                    addTarget(iRow, i);
                }
                break;
            }
        }
    }
    
    public void initMoreData() {
        
    }
    
    public boolean checkTarget(int row, int col) {
        int i;

        // up
        for (i = iRow + 1; i < LogicConstant.MAX_ROW; i++) {
            if (isCellEmpty(i, iCol)) {
                if ((i == row) && (iCol == col))
                    return true;
            } else {
                if (checkCell(i, iCol)) {
                    if ((i == row) && (iCol == col))
                        return true;
                }
                break;
            }
        }
        // down
        for (i = iRow - 1; i >= 0; i--) {
            if (isCellEmpty(i, iCol)) {
                if ((i == row) && (iCol == col))
                    return true;
            } else {
                if (checkCell(i, iCol)) {
                    if ((i == row) && (iCol == col))
                        return true;
                }
                break;
            }
        }

        // left
        for (i = iCol - 1; i >= 0; i--) {
            if (isCellEmpty(iRow, i)) {
                if ((iRow == row) && (i == col))
                    return true;
            } else {
                if (checkCell(iRow, i)) {
                    if ((iRow == row) && (i == col))
                        return true;
                }
                break;
            }
        }

        // right
        for (i = iCol + 1; i < LogicConstant.MAX_COL; i++) {
            if (isCellEmpty(iRow, i)) {
                if ((iRow == row) && (i == col))
                    return true;
            } else {
                if (checkCell(iRow, i)) {
                    if ((iRow == row) && (i == col))
                        return true;
                }
                break;
            }
        }

        return false;
    }
}
