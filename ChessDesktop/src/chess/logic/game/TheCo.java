package chess.logic.game;

import java.util.ArrayList;
import java.util.List;

import chess.logic.game.bean.MyPiece;



public class TheCo {
    public TheCo() {
        super();
    }
    
    public static List<MyPiece> theco1(){
        List<MyPiece> list = new ArrayList();
        list.add(new MyPiece("T1", 0, 4));
        list.add(new MyPiece("X1", 4, 6));
        list.add(new MyPiece("S1", 1, 4));        
        list.add(new MyPiece("S1", 0, 5));
        list.add(new MyPiece("tuong1", 0, 6));
        list.add(new MyPiece("X1", 0, 7));        
        list.add(new MyPiece("tot1", 3, 4));        
      list.add(new MyPiece("P1", 2, 1));
      list.add(new MyPiece("M1", 2, 2));
      list.add(new MyPiece("tuong1", 2, 0));
      list.add(new MyPiece("S1", 0, 5));
      list.add(new MyPiece("tot1", 3, 0));
      list.add(new MyPiece("tot1", 3, 8));
      list.add(new MyPiece("P1", 4, 4));
        
        list.add(new MyPiece("T2", 9, 4));
        list.add(new MyPiece("S2", 8, 4));
        list.add(new MyPiece("S2", 9, 5));
        list.add(new MyPiece("P2", 7, 4));
        list.add(new MyPiece("M2", 6, 6));
      list.add(new MyPiece("tuong2", 9, 6));
      list.add(new MyPiece("tuong2", 9, 2));
      list.add(new MyPiece("M2", 7, 2));
      list.add(new MyPiece("tot2", 6, 0));
      list.add(new MyPiece("tot2", 5, 8));
      list.add(new MyPiece("tot2", 4, 2));
      list.add(new MyPiece("P2", 3, 2));
      list.add(new MyPiece("X2", 9, 8));
        
        return list;
    }
}
