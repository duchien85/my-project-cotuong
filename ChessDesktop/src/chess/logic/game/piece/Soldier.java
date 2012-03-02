package chess.logic.game.piece;

public class Soldier extends Piece {
    public void caculateTargets() {
        // clear target array
        clearTargets();
        // carculate new target
        // forward
        if (ownerID == 0)
        {
            if (checkCell(iRow+1, iCol))
            {
                addTarget(iRow+1, iCol);
            }
        }
        else
        {
            if (checkCell(iRow-1, iCol))
            {
                addTarget(iRow-1, iCol);
            }
        }

        // check if soldier has cross river
        boolean HasCrossRiver = false;
        if (((ownerID == 0) && (iRow >= 5)) || ((ownerID == 1) && (iRow <= 4)))
        {
            HasCrossRiver = true;
        }

        if (HasCrossRiver)
        {
            // side1
            if (checkCell(iRow, iCol+1))
            {
                addTarget(iRow, iCol+1);
            }
            // side2
            if (checkCell(iRow, iCol-1))
            {
                addTarget(iRow, iCol-1);
            }
        }
    }
    
    public void initMoreData() {
        
    }
    
    public boolean checkTarget(int row, int col) {
        // forward
        if (ownerID == 0)
        {
            if (checkCell(iRow+1, iCol))
            {
                if (((iRow+1) == row) && ((iCol) == col)) return true;
            }
        }
        else
        {
            if (checkCell(iRow-1, iCol))
            {
                if (((iRow-1) == row) && ((iCol) == col)) return true;
            }
        }

        // check if soldier has cross river
        boolean HasCrossRiver = false;
        if (((ownerID == 0) && (iRow >= 5)) || ((ownerID == 1) && (iRow <= 4)))
        {
            HasCrossRiver = true;
        }

        if (HasCrossRiver)
        {
            // side1
            if (checkCell(iRow, iCol+1))
            {
                if (((iRow) == row) && ((iCol+1) == col)) return true;
            }
            // side2
            if (checkCell(iRow, iCol-1))
            {
                if (((iRow) == row) && ((iCol-1) == col)) return true;
            }
        }

        return false;
    }
}
