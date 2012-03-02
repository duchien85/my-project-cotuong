package chess.logic.game.piece;

import chess.logic.game.bean.LogicConstant;
import chess.logic.game.bean.MapInfo;


public abstract class Piece {
    public MapInfo pMap;
    public int pieceID;
    public int ownerID;
    public EPieceType pieceType;
    public boolean canGo;
    public int r1, r2, c1, c2; // piece can move in this area
    public boolean isDead;
    public int iRow, iCol; // position on the board
    public int[] rowTarget = new int[LogicConstant.MAX_TARGET];
    public int[] colTarget = new int[LogicConstant.MAX_TARGET];
    public int nTarget;
    public int homeRow;
    public int homeCol;
    public boolean isUsing;

    public abstract void caculateTargets();
    public abstract boolean checkTarget(int row, int col);
    public abstract void initMoreData();
    
    public Piece() {
        clear();
    }
    
    public boolean camChieu3 = false;
    public int camRow;
    public int camCol;
    
    public void setPosition(int row, int col) {
        if ((iRow >= 0) && (iRow < LogicConstant.MAX_ROW) && (iCol >= 0) && (iCol <LogicConstant.MAX_COL))
        {
            pMap.cells[iRow][iCol].Clear();            
        }
        iRow = row;
        iCol = col;
        if ((iRow >= 0) && (iRow < LogicConstant.MAX_ROW) && (iCol >= 0) && (iCol <LogicConstant.MAX_COL))
        {
            pMap.cells[iRow][iCol].PieceID = this.pieceID;
            pMap.cells[iRow][iCol].PieceOwnerID = this.ownerID;            
        }
    }
    
    public void setHomePosition(int row, int col) {
        homeRow = row;
        homeCol = col;
    }
    
    public void reset() {
        setPosition(homeRow, homeCol);
        isDead = false;
        clearTargets();
    }
    
    public ETargetType canMoveTo(int row, int col) {
       if ((row < 0) || (row >= LogicConstant.MAX_ROW) || (col < 0) || (col >= LogicConstant.MAX_COL))
        {
            return ETargetType.e_KO_DUOC;
        }
        else
        {
            int i;
            for (i = 0; i < nTarget; i++)
            {
                if ((rowTarget[i] == row) && (colTarget[i] == col))
                {
                        return ETargetType.e_DI_DUOC;
                }
            }
        }                
        return ETargetType.e_KO_DUOC;
    }
    
    public void setMoveAbleArea(int row1, int col1, int row2, int col2) {
        r1 = row1;
        c1 = col1;
        r2 = row2;
        c2 = col2;
    }
    
    public void clear() {
        int i, j;

        pieceID = -1;
        ownerID = -1;
        isDead = false;
        r1 = c1 = r2 = c2 = 0;

        // init more data
        initMoreData();

        isUsing = false;
    }
    
    public void kill() {
        isDead = true;
        canGo = false;
        pMap.cells[iRow][iCol].Clear();
        clearTargets();
    }
    
    public void resurrect() {
        isDead = false;
        setPosition(iRow, iCol);
    }
    
    public boolean checkCell(int row, int col) {
        if ((row < r1) || (row > r2) || (col < c1) || (col > c2))
        {
            return false;
        }
        if (pMap.cells[row][col].PieceOwnerID != this.ownerID)
        {
            return true;
        }
        return false;
    }
    
    public boolean isCellEmpty(int row, int col) {
        if ((row < r1) || (row > r2) || (col < c1) || (col > c2))
        {
            return false;
        }
        if (pMap.cells[row][col].PieceOwnerID == -1)
        {
            return true;
        }
        return false;
    }
    
    public void clearTargets() {
        int i, j;
        canGo = false;
        nTarget = 0;
    }
    
    public void addTarget(int row, int col) {
        rowTarget[nTarget] = row;
        colTarget[nTarget] = col;
        nTarget++;
        canGo = true;
    }
    
    public void removeTarget(int TargetID) {
        rowTarget[TargetID] = -1;
        colTarget[TargetID] = -1;
        nTarget--;
        if (nTarget <= 0)
        {
            canGo = false;
        }
    }
    
    public void arrangeTarget() {
        int i;
        int count = 0;
        for (i = 0; i < LogicConstant.MAX_TARGET; i++)
        {
            if ((rowTarget[i] >= 0) && (colTarget[i] >= 0))
            {
                rowTarget[count] = rowTarget[i];
                colTarget[count] = colTarget[i];
                count++;
            }
        }
    }

    public enum EPieceType
    {
        e_SOLDIER,
        e_CATAPULT,
        e_ROOK,
        e_CAVARLY,
        e_ELEPHANT,
        e_GUARD,
        e_GENNERAL
    }

    public enum ETargetType
    {
        e_KO_DUOC,
        e_CAM_DI,
        e_CAM_CHIEU3,
        e_DI_DUOC       
    }
}
