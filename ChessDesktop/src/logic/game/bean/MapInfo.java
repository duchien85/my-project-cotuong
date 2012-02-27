package logic.game.bean;

import logic.game.piece.Cell;


public class MapInfo {
    public Cell[][] cells = new Cell[LogicConstant.MAX_ROW][LogicConstant.MAX_COL];

    public MapInfo() {
        for (int i = 0; i < LogicConstant.MAX_ROW; i++){
            for (int j = 0; j < LogicConstant.MAX_COL; j++){
                  cells[i][j] = new Cell();
            }
        }
    }
    
    public void reset() {
        int i, j;
        for (i = 0; i < LogicConstant.MAX_ROW; i++)
        {
            for (j = 0; j < LogicConstant.MAX_COL; j++)
            {
                cells[i][j].Clear();
            }
        }
    }
    
    public static void flipXY(int x, int y) {
        x = (LogicConstant.MAX_COL - 1 - x);
        y = (LogicConstant.MAX_ROW - 1 - y);
    }
    
    public static int flipX(int x) {
        return (LogicConstant.MAX_COL - 1 - x);
    }
    
    public static int flipY(int y) {
        return (LogicConstant.MAX_ROW - 1 - y);
    }
}
