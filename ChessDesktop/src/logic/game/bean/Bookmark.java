package logic.game.bean;

public class Bookmark {
    private static final int MAX_BOOK_SAVE = 300;
    private static final int MAX_BOOK_REMOVE = 200;

    public int iNumMark;
    public History[] book = new History[MAX_BOOK_SAVE+1];

    public Bookmark() {
        for (int i = 0; i < book.length; i++)
            book[i] = new History();
        iNumMark = 0;
    }
    
    public void addMark(int fiRow, int fiCol, int seRow, int seCol, int PieDieID, int PlayerMoveID, boolean IsKingCapture) {
        book[iNumMark].fiRow = fiRow;
        book[iNumMark].fiCol = fiCol;
        book[iNumMark].seRow = seRow;
        book[iNumMark].seCol = seCol;
        book[iNumMark].PieDieID = PieDieID;
        book[iNumMark].PlayerMoveID = PlayerMoveID;
        book[iNumMark].IsKingCapture = IsKingCapture;
        iNumMark++;

        if (iNumMark >= MAX_BOOK_SAVE) {
            iNumMark = MAX_BOOK_SAVE - MAX_BOOK_REMOVE;
            // Dịch các phần tử còn lại về đầu mảng
            for(int i = 0; i < iNumMark; i++) {
                book[i].fiRow = book[i+iNumMark].fiRow;
                book[i].fiCol = book[i+iNumMark].fiCol;
                book[i].seRow = book[i+iNumMark].seRow;
                book[i].seCol = book[i+iNumMark].seCol;
                book[i].PieDieID = book[i+iNumMark].PieDieID;
                book[i].PlayerMoveID = book[i+iNumMark].PlayerMoveID;
                book[i].IsKingCapture = book[i+iNumMark].IsKingCapture;
            }
        }
    }
    public void removeMark(int iNum) {
        iNumMark -= iNum;
        if (iNumMark < 0)
        {
            iNumMark = 0;
        }
    }
    public void reset() {
        iNumMark = 0;
    }
    public History getMark(int index) {
        if ((index >= 0) && (index < iNumMark))
        {
            return book[index];
        }
        else
        {
            return book[0];
        }
    }
    public History getLastMark() {
        if (iNumMark > 0)
        {
            iNumMark--;
        }
        else
        {
            iNumMark = 0;
        }
        return book[iNumMark];
    }
}
