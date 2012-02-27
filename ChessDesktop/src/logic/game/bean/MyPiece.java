package logic.game.bean;

public class MyPiece {
    public MyPiece(String piece, int row, int col) {
        super();
        p = piece;
        r = row;
        c = col;
    }
    public static final String XE = "X";
    public static final String PHAO = "P";
    public static final String MA = "M";
    public static final String TUONG = "T";
    public static final String SY = "S";
    public static final String TG = "tuong";
    public static final String TOT = "tot";
    public static final String SideOne = "1";
    public static final String SideTwo = "2";
    
    public String p;
    public int r;
    public int c;
}
