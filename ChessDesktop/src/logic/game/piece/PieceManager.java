package logic.game.piece;

import logic.game.bean.LogicConstant;
import logic.game.bean.MapInfo;


public class PieceManager {
    private static final int MAX_SOLDIER = 10;
    private static final int MAX_OTHER = 4;
    private static final int MAX_GENNERAL = 2;
    
    private Soldier[] pSoldier = new Soldier[MAX_SOLDIER];
    private Catapult[] pCatapult = new Catapult[MAX_OTHER];
    private Rook[] pRook = new Rook[MAX_OTHER];
    private Cavarly[] pCavarly = new Cavarly[MAX_OTHER];
    private Elephant[] pElephant = new Elephant[MAX_OTHER];
    private Guard[] pGuard = new Guard[MAX_OTHER];
    private General[] pGenneral = new General[MAX_GENNERAL];

    public Piece[] Pieces = new Piece[LogicConstant.MAX_PIECE];
    public int iNumPiece;
    public int GenneralIndex;

    public PieceManager() {
        int i;

        for (i = 0; i < MAX_SOLDIER; i++) {
            pSoldier[i] = new Soldier();
        }

        for (i = 0; i < MAX_OTHER; i++) {
            pCatapult[i] = new Catapult();
            pRook[i] = new Rook();
            pCavarly[i] = new Cavarly();
            pElephant[i] = new Elephant();
            pGuard[i] = new Guard();
        }

        for (i = 0; i < MAX_GENNERAL; i++) {
            pGenneral[i] = new General();
        }
        iNumPiece = 0;
    }
    
    public void Reset() {
        int i;
        for (i = 0; i < iNumPiece; i++)
        {
            Pieces[i].reset();
        }
    }
    
    public void EraseAll() {
        int i;
        for (i = 0; i < MAX_SOLDIER; i++) {
            pSoldier[i].clear();
        }

        for (i = 0; i < MAX_OTHER; i++) {
            pCatapult[i].clear();
            pRook[i].clear();
            pCavarly[i].clear();
            pElephant[i].clear();
            pGuard[i].clear();
        }

        for (i = 0; i < MAX_GENNERAL; i++) {
            pGenneral[i].clear();
        }

        iNumPiece = 0;
    }
    
    public Soldier AddSoldier(int x, int y, int PlayerID, MapInfo pMap) {
        // tim slot hop ly
                int i;
                int vt = -1;
                for (i = 0; i < MAX_SOLDIER; i++)
                {
                        if (!pSoldier[i].isUsing)
                        {
                                vt = i;
                                pSoldier[i].isUsing = true;
                                break;
                        }
                }
                if (vt == -1) return null;

                Soldier NewPiece = pSoldier[vt];
                NewPiece.pMap = pMap;
                
                int r1, c1, r2, c2;
                if (PlayerID == 0)
                {
                        r1 = 4; c1 = 0;
                        r2 = LogicConstant.MAX_ROW-1; c2 = LogicConstant.MAX_COL-1;
                }
                else
                {
                        r1 = 0; c1 = 0;
                        r2 = 5; c2 = LogicConstant.MAX_COL-1;
                }
                NewPiece.pieceType = Piece.EPieceType.e_SOLDIER;
                NewPiece.setMoveAbleArea(r1, c1, r2, c2);
                NewPiece.ownerID = PlayerID;
                NewPiece.pieceID = iNumPiece;
                NewPiece.setPosition(x, y);
                NewPiece.setHomePosition(x, y);
                Pieces[iNumPiece] = NewPiece;
                iNumPiece++;
                return NewPiece;
    }
    
    public Cavarly AddCavalry(int x, int y, int PlayerID, MapInfo pMap) {
        // tim slot hop ly
        int i;
        int vt = -1;
        for (i = 0; i < MAX_OTHER; i++) {
            if (!pCavarly[i].isUsing) {
                vt = i;
                pCavarly[i].isUsing = true;
                break;
            }
        }
        if (vt == -1)
            return null;

        Cavarly NewPiece = pCavarly[vt];

        NewPiece.pMap = pMap;
        NewPiece.pieceType = Piece.EPieceType.e_CAVARLY;
        NewPiece.setMoveAbleArea(0, 0, LogicConstant.MAX_ROW - 1, LogicConstant.MAX_COL - 1);
        NewPiece.ownerID = PlayerID;
        NewPiece.pieceID = iNumPiece;
        NewPiece.setPosition(x, y);
        NewPiece.setHomePosition(x, y);
        Pieces[iNumPiece] = NewPiece;
        iNumPiece++;
        return NewPiece;
    }
    
    public Catapult AddCatapult(int x, int y, int PlayerID, MapInfo pMap) {
        // tim slot hop ly
        int i;
        int vt = -1;
        for (i = 0; i < MAX_OTHER; i++) {
            if (!pCatapult[i].isUsing) {
                vt = i;
                pCatapult[i].isUsing = true;
                break;
            }
        }
        if (vt == -1)
            return null;

        Catapult NewPiece = pCatapult[vt];

        NewPiece.pMap = pMap;
        NewPiece.pieceType = Piece.EPieceType.e_CATAPULT;
        NewPiece.setMoveAbleArea(0, 0, LogicConstant.MAX_ROW - 1, LogicConstant.MAX_COL - 1);
        NewPiece.ownerID = PlayerID;
        NewPiece.pieceID = iNumPiece;
        NewPiece.setPosition(x, y);
        NewPiece.setHomePosition(x, y);
        Pieces[iNumPiece] = NewPiece;
        iNumPiece++;
        return NewPiece;
    }
    
    public Rook AddRook(int x, int y, int PlayerID, MapInfo pMap) {
        // tim slot hop ly
        int i;
        int vt = -1;
        for (i = 0; i < MAX_OTHER; i++) {
            if (!pRook[i].isUsing) {
                vt = i;
                pRook[i].isUsing = true;
                break;
            }
        }
        if (vt == -1)
            return null;

        Rook NewPiece = pRook[vt];


        NewPiece.pMap = pMap;
        NewPiece.pieceType = Piece.EPieceType.e_ROOK;
        NewPiece.setMoveAbleArea(0, 0, LogicConstant.MAX_ROW - 1, LogicConstant.MAX_COL - 1);
        NewPiece.ownerID = PlayerID;
        NewPiece.pieceID = iNumPiece;
        NewPiece.setPosition(x, y);
        NewPiece.setHomePosition(x, y);
        Pieces[iNumPiece] = NewPiece;
        iNumPiece++;
        return NewPiece;
    }
    
    public Elephant AddElephant(int x, int y, int PlayerID, MapInfo pMap) {
        // tim slot hop ly
        int i;
        int vt = -1;
        for (i = 0; i < MAX_OTHER; i++) {
            if (!pElephant[i].isUsing) {
                vt = i;
                pElephant[i].isUsing = true;
                break;
            }
        }
        if (vt == -1)
            return null;

        Elephant NewPiece = pElephant[vt];


        NewPiece.pMap = pMap;
        int r1, c1, r2, c2;
        if (PlayerID == 0) {
            r1 = 0;
            c1 = 0;
            r2 = 4;
            c2 = LogicConstant.MAX_COL - 1;
        } else {
            r1 = 5;
            c1 = 0;
            r2 = 9;
            c2 = LogicConstant.MAX_COL - 1;
        }
        NewPiece.pieceType = Piece.EPieceType.e_ELEPHANT;
        NewPiece.setMoveAbleArea(r1, c1, r2, c2);
        NewPiece.ownerID = PlayerID;
        NewPiece.pieceID = iNumPiece;
        NewPiece.setPosition(x, y);
        NewPiece.setHomePosition(x, y);
        Pieces[iNumPiece] = NewPiece;
        iNumPiece++;
        return NewPiece;
    }
    
    public Guard AddGuard(int x, int y, int PlayerID, MapInfo pMap) {
        // tim slot hop ly
        int i;
        int vt = -1;
        for (i = 0; i < MAX_OTHER; i++) {
            if (!pGuard[i].isUsing) {
                vt = i;
                pGuard[i].isUsing = true;
                break;
            }
        }
        if (vt == -1)
            return null;

        Guard NewPiece = pGuard[vt];

        NewPiece.pMap = pMap;
        int r1, c1, r2, c2;
        if (PlayerID == 0) {
            r1 = 0;
            c1 = 3;
            r2 = 2;
            c2 = 5;
        } else {
            r1 = 7;
            c1 = 3;
            r2 = 9;
            c2 = 5;
        }
        NewPiece.pieceType = Piece.EPieceType.e_GUARD;
        NewPiece.setMoveAbleArea(r1, c1, r2, c2);
        NewPiece.ownerID = PlayerID;
        NewPiece.pieceID = iNumPiece;
        NewPiece.setPosition(x, y);
        NewPiece.setHomePosition(x, y);
        Pieces[iNumPiece] = NewPiece;
        iNumPiece++;
        return NewPiece;
    }
    
    public General AddGenneral(int x, int y, int PlayerID, MapInfo pMap) {
        // tim slot hop ly
        int i;
        int vt = -1;
        for (i = 0; i < MAX_GENNERAL; i++) {
            if (!pGenneral[i].isUsing) {
                vt = i;
                pGenneral[i].isUsing = true;
                break;
            }
        }
        if (vt == -1)
            return null;

        General NewPiece = pGenneral[vt];

        NewPiece.pMap = pMap;
        int r1, c1, r2, c2;
        if (PlayerID == 0) {
            r1 = 0;
            c1 = 3;
            r2 = 2;
            c2 = 5;
        } else {
            r1 = 7;
            c1 = 3;
            r2 = 9;
            c2 = 5;
        }
        NewPiece.pieceType = Piece.EPieceType.e_GENNERAL;
        NewPiece.setMoveAbleArea(r1, c1, r2, c2);
        NewPiece.ownerID = PlayerID;
        NewPiece.pieceID = iNumPiece;
        NewPiece.setPosition(x, y);
        NewPiece.setHomePosition(x, y);
        Pieces[iNumPiece] = NewPiece;
        GenneralIndex = iNumPiece;
        iNumPiece++;
        return NewPiece;
    }

    public int FindPiece(int row, int col) {
        int i;
        for (i = 0; i < iNumPiece; i++) {
            if (!Pieces[i].isDead) {
                if ((Pieces[i].iRow == row) && (Pieces[i].iCol == col)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
