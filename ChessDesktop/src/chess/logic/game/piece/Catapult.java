package chess.logic.game.piece;

import chess.logic.game.bean.LogicConstant;


public class Catapult extends Piece {
    public void caculateTargets() {
        int i;
        int count;
        clearTargets();

        // up
        count = 0;
        for (i = iRow + 1; i < LogicConstant.MAX_ROW; i++) {
            if (isCellEmpty(i, iCol)) {
                if (count == 0) {
                    addTarget(i, iCol);
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(i, iCol)) {
                        addTarget(i, iCol);
                    }
                    break;
                }
            }
        }

        // down
        count = 0;
        for (i = iRow - 1; i >= 0; i--) {
            if (isCellEmpty(i, iCol)) {
                if (count == 0) {
                    addTarget(i, iCol);
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(i, iCol)) {
                        addTarget(i, iCol);
                    }
                    break;
                }
            }
        }

        // left
        count = 0;
        for (i = iCol - 1; i >= 0; i--) {
            if (isCellEmpty(iRow, i)) {
                if (count == 0) {
                    addTarget(iRow, i);
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(iRow, i)) {
                        addTarget(iRow, i);
                    }
                    break;
                }
            }
        }

        // right
        count = 0;
        for (i = iCol + 1; i < LogicConstant.MAX_COL; i++) {
            if (isCellEmpty(iRow, i)) {
                if (count == 0) {
                    addTarget(iRow, i);
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(iRow, i)) {
                        addTarget(iRow, i);
                    }
                    break;
                }
            }
        }
    }
    
    public void initMoreData() {
        
    }
    
    public boolean checkTarget(int row, int col) {
        int i;
        int count;

        // up
        count = 0;
        for (i = iRow + 1; i < LogicConstant.MAX_ROW; i++) {
            if (isCellEmpty(i, iCol)) {
                if (count == 0) {
                    if ((i == row) && (iCol == col))
                        return true;
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(i, iCol)) {
                        if ((i == row) && (iCol == col))
                            return true;
                    }
                    break;
                }
            }
        }

        // down
        count = 0;
        for (i = iRow - 1; i >= 0; i--) {
            if (isCellEmpty(i, iCol)) {
                if (count == 0) {
                    if ((i == row) && (iCol == col))
                        return true;
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(i, iCol)) {
                        if ((i == row) && (iCol == col))
                            return true;
                    }
                    break;
                }
            }
        }

        // left
        count = 0;
        for (i = iCol - 1; i >= 0; i--) {
            if (isCellEmpty(iRow, i)) {
                if (count == 0) {
                    if ((iRow == row) && (i == col))
                        return true;
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(iRow, i)) {
                        if ((iRow == row) && (i == col))
                            return true;
                    }
                    break;
                }
            }
        }

        // right
        count = 0;
        for (i = iCol + 1; i < LogicConstant.MAX_COL; i++) {
            if (isCellEmpty(iRow, i)) {
                if (count == 0) {
                    if ((iRow == row) && (i == col))
                        return true;
                }
            } else {
                count++;
                if (count == 2) {
                    if (checkCell(iRow, i)) {
                        if ((iRow == row) && (i == col))
                            return true;
                    }
                    break;
                }
            }
        }

        return false;
    }

}
