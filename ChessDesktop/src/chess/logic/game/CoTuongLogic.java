package chess.logic.game;

import java.util.ArrayList;
import java.util.List;

import chess.logic.game.bean.Bookmark;
import chess.logic.game.bean.History;
import chess.logic.game.bean.LogicConstant;
import chess.logic.game.bean.MapInfo;
import chess.logic.game.bean.MyPiece;
import chess.logic.game.bean.PiecesInfo;
import chess.logic.game.piece.Piece;
import chess.logic.game.piece.Piece.ETargetType;
import chess.logic.game.piece.PieceManager;

public class CoTuongLogic {
	public PieceManager[] playerPM = new PieceManager[2];
	protected int currentTurnID; // the playerID have turn
	protected int nextTurnID;
	protected int firstTurnID; // the player who turn first
	protected int winnerID; // ID the player who win the game
	protected boolean isGameEnd;
	protected boolean biChieuTuong;
	protected int pieceDiePieID;
	protected int pieceDieOwnerID;
	protected Bookmark myBook = new Bookmark();

	protected int[] undoCountDown = new int[2];
	protected int[] drawnCountDown = new int[2];

	protected int[] nTurn = new int[LogicConstant.MAX_PLAYER];

	protected int[] playerTimer = new int[2];
	protected int MaxTime;

	protected int timeStart;
	protected boolean isGamePlaying;

	protected int[][] pieKillList = new int[2][16];
	protected int[] nPieKill = new int[2];

	protected int nPieMapInfo;
	protected PiecesInfo[] pieMapInfo = new PiecesInfo[32];
	protected MapInfo map = new MapInfo();

	public CoTuongLogic() {
		for (int i = 0; i < playerPM.length; i++)
			playerPM[i] = new PieceManager();
		for (int i = 0; i < pieMapInfo.length; i++)
			pieMapInfo[i] = new PiecesInfo();
	}

	private void createPieceMap(List<MyPiece> list) {
		clearPieceMapList();
		for (MyPiece piece : list) {
			int playerId = 0;
			int pId = 0;
			if (piece.p.contains(MyPiece.SideOne))
				playerId = 0;
			else if (piece.p.contains(MyPiece.SideTwo))
				playerId = 1;

			if (piece.p.contains(MyPiece.TOT))
				pId = 0;
			else if (piece.p.contains(MyPiece.PHAO))
				pId = 1;
			else if (piece.p.contains(MyPiece.XE))
				pId = 2;
			else if (piece.p.contains(MyPiece.MA))
				pId = 3;
			else if (piece.p.contains(MyPiece.TG))
				pId = 4;
			else if (piece.p.contains(MyPiece.SY))
				pId = 5;
			else if (piece.p.contains(MyPiece.TUONG))
				pId = 6;

			addPieceList(piece.r, piece.c, playerId, pId, playerId);

		}
	}

	// public void initNewGame(int firstTurn, List<MyPiece> list) {
	// timeStart = -1;
	// MaxTime = 0;
	// createPieceMap(list);
	// int i;
	// for (i = 0; i < LogicConstant.MAX_PLAYER; i++) {
	// undoCountDown[i] = 3;
	// drawnCountDown[i] = 3;
	// playerTimer[i] = MaxTime;
	// nTurn[i] = 0;
	// nPieKill[i] = 0;
	// }
	//
	// firstTurnID = firstTurn;
	// currentTurnID = firstTurn;
	//
	// // clear book
	// myBook.reset();
	//
	// // clear map
	// map.reset();
	//
	// // init logic
	// playerPM[0].EraseAll();
	// playerPM[1].EraseAll();
	//
	// for (i = 0; i < 32; i++) {
	// if (pieMapInfo[i].PlayerID < 0)
	// break;
	// addPieceLogic(pieMapInfo[i]);
	// }
	//
	// updatePieceMap();
	// caculatePiecesTargets(currentTurnID);
	// caculateFinalPiecesTargets(currentTurnID);
	//
	// isGameEnd = false;
	// pieceDiePieID = pieceDieOwnerID = -1;
	// }

	public void initNewGame(int firstTurn) {
		int timePlay = 10;
		timeStart = -1;
		MaxTime = timePlay;
		createNormalPieceMap();
		int i;
		for (i = 0; i < LogicConstant.MAX_PLAYER; i++) {
			undoCountDown[i] = 3;
			drawnCountDown[i] = 3;
			playerTimer[i] = MaxTime;
			nTurn[i] = 0;
			nPieKill[i] = 0;
		}

		firstTurnID = firstTurn;
		currentTurnID = firstTurn;

		// clear book
		myBook.reset();

		// clear map
		map.reset();

		// init logic
		playerPM[0].EraseAll();
		playerPM[1].EraseAll();

		for (i = 0; i < 32; i++) {
			if (pieMapInfo[i].PlayerID < 0)
				break;
			addPieceLogic(pieMapInfo[i]);
		}

		updatePieceMap();
		caculatePiecesTargets(currentTurnID);
		caculateFinalPiecesTargets(currentTurnID);

		isGameEnd = false;
		pieceDiePieID = pieceDieOwnerID = -1;
	}

	// update all piece pos

	protected void updatePieceMap() {
		int i, j;
		map.reset();
		for (i = 0; i < LogicConstant.MAX_PLAYER; i++) {
			for (j = 0; j < playerPM[i].iNumPiece; j++) {
				Piece piece = playerPM[i].Pieces[j];
				if (!piece.isDead) {
					map.cells[piece.iRow][piece.iCol].PieceID = piece.pieceID;
					map.cells[piece.iRow][piece.iCol].PieceOwnerID = piece.ownerID;
				}
			}
		}
	}

	public boolean kiemTraXuHoa() {
		int count = 0;
		int i, j;
		for (i = 0; i < LogicConstant.MAX_PLAYER; i++) {
			for (j = 0; j < playerPM[i].iNumPiece; j++) {
				Piece piece = playerPM[i].Pieces[j];
				if (!piece.isDead) {
					if (piece.pieceType.compareTo(Piece.EPieceType.e_CAVARLY) <= 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// kiem tra xem tuo'ng cua playerID co bi chieu ko? ( true = bi chie'u,
	// false = ko)

	public boolean kiemTraChieuTuong(int PlayerID) {
		int i;

		// tinh ID cua doi thu
		int OtherID = tinhIDDoiPhuong(PlayerID);

		// vi tri cua quan tuo'ng
		int gR = playerPM[PlayerID].Pieces[playerPM[PlayerID].GenneralIndex].iRow;
		int gC = playerPM[PlayerID].Pieces[playerPM[PlayerID].GenneralIndex].iCol;

		// kiem tra chieu tuong
		for (i = 0; i < playerPM[OtherID].iNumPiece; i++) {
			Piece piece = playerPM[OtherID].Pieces[i];
			if (!piece.isDead) {
				if (piece.checkTarget(gR, gC)) {
					return true;
				}
			}
		}

		return false;
	}

	// kiem tra tuo'ng do'i ma.t nhau

	private boolean isKingSeeKing() {
		// tim vi tri cua 2 tuong
		int c0 = playerPM[0].Pieces[playerPM[0].GenneralIndex].iCol;
		int r0 = playerPM[0].Pieces[playerPM[0].GenneralIndex].iRow;
		int c1 = playerPM[1].Pieces[playerPM[1].GenneralIndex].iCol;
		int r1 = playerPM[1].Pieces[playerPM[1].GenneralIndex].iRow;

		// kiem tra thang ha`ng
		boolean KQ = true;
		if (c0 == c1) {
			int i;
			for (i = r0 + 1; i < r1; i++) {
				if (map.cells[i][c0].PieceID != -1) {
					KQ = false;
				}
			}
		} else {
			KQ = false;
		}

		return KQ;
	}

	// tinh toan nhung o ma cac quan co cua playerID co the di trong luo.t ke
	// tiep

	private void caculatePiecesTargets(int PlayerID) {
		int i, j;
		for (i = 0; i < playerPM[PlayerID].iNumPiece; i++) {

			if (!playerPM[PlayerID].Pieces[i].isDead) {
				Piece piece = playerPM[PlayerID].Pieces[i];
				piece.caculateTargets();
			}
		}
	}

	// caculate final target

	private void caculateFinalPiecesTargets(int PlayerID) {
		int i, j;
		Piece.ETargetType kq;
		for (i = 0; i < playerPM[PlayerID].iNumPiece; i++) {
			if (!playerPM[PlayerID].Pieces[i].isDead) {
				Piece piece = playerPM[PlayerID].Pieces[i];
				piece.camChieu3 = false;
				int maxTarget = piece.nTarget;
				if ((piece.pieceID == 5) && (maxTarget == 9)) {
				}
				List<Integer> list = new ArrayList();
				for (j = 0; j < maxTarget; j++) {					
					kq = checkPlayerMovePiece(PlayerID, piece.pieceID, piece.rowTarget[j], piece.colTarget[j]);					
					if (kq.compareTo(ETargetType.e_DI_DUOC) < 0) {
						list.add(j);
					}
					if (kq == ETargetType.e_CAM_CHIEU3){
						piece.camChieu3 = true;
						piece.camRow = piece.rowTarget[j];
						piece.camCol = piece.colTarget[j];
					}
				}
				for (int a : list) {
					piece.removeTarget(a);
				}
				piece.arrangeTarget();
			}
		}
	}
	
	public Piece.ETargetType canMovePiece(int PlayerID, int PieceID, int ToRow, int ToCol){
		Piece piece = playerPM[PlayerID].Pieces[PieceID];
		if (piece.camChieu3 && piece.camRow == ToRow && piece.camCol == ToCol)
			return ETargetType.e_CAM_CHIEU3;
		return piece.canMoveTo(ToRow, ToCol);
	}
	
	// check player di chuyen co`

	private Piece.ETargetType checkPlayerMovePiece(int PlayerID, int PieceID, int ToRow, int ToCol) {
		Piece.ETargetType KQ = Piece.ETargetType.e_DI_DUOC;		
		if (!checkPie(PlayerID, PieceID))
			return Piece.ETargetType.e_KO_DUOC;
		
		if (playerPM[PlayerID].Pieces[PieceID].canMoveTo(ToRow, ToCol) != Piece.ETargetType.e_DI_DUOC) {
			return Piece.ETargetType.e_KO_DUOC;
		}

		// luu tru lai vi tri truoc khi di chuyen
		int OldRow = playerPM[PlayerID].Pieces[PieceID].iRow;
		int OldCol = playerPM[PlayerID].Pieces[PieceID].iCol;

		// che't gia?
		int tPieID = map.cells[ToRow][ToCol].PieceID;
		int tPieOnwerID = map.cells[ToRow][ToCol].PieceOwnerID;
		if (tPieID >= 0) {
			playerPM[tPieOnwerID].Pieces[tPieID].kill();
		}

		// tien hanh di chuyen
		playerPM[PlayerID].Pieces[PieceID].setPosition(ToRow, ToCol);

		// kiem tra bi chieu tuong ko]
		// if (isKingSeeKing()) {
		// KQ = Piece.ETargetType.e_CAM_DI;
		// }

		if (kiemTraChieuTuong(PlayerID) || isKingSeeKing()) {
			KQ = Piece.ETargetType.e_CAM_DI;
		}

		// update code
		// kiem tra chieu tuong lap lai 3 lan

		int OtherID = tinhIDDoiPhuong(PlayerID);
		if (kiemTraChieuTuong(OtherID)) {
			int nRepeat = countRepeatMove(PlayerID, OldRow, OldCol, ToRow, ToCol);
			if (nRepeat >= 2) {
				KQ = Piece.ETargetType.e_CAM_CHIEU3;
			}
		}

		// update code

		// restore old positioon
		playerPM[PlayerID].Pieces[PieceID].setPosition(OldRow, OldCol);
		if (tPieID >= 0) {
			playerPM[tPieOnwerID].Pieces[tPieID].resurrect();
		}

		return KQ;
	}

	// thuc hien viec di chuyen quan co

	public int convert(int playerID, int row, int col) {
		PieceManager ma = playerPM[playerID];
		for (int i = 0; i < ma.Pieces.length; i++) {
			if (ma.Pieces[i].iRow == row && ma.Pieces[i].iCol == col)
				if (!ma.Pieces[i].isDead)
					return i;
		}
		return -1;
	}

	public void processMovePiece(int PlayerID, int PieceID, int ToRow, int ToCol, boolean IsMark) {
		int oldRow = playerPM[PlayerID].Pieces[PieceID].iRow;
		int oldCol = playerPM[PlayerID].Pieces[PieceID].iCol;
		// check new position for other piece
		if (map.cells[ToRow][ToCol].PieceID != -1) {
			int pieID = map.cells[ToRow][ToCol].PieceID;
			int ownerID = map.cells[ToRow][ToCol].PieceOwnerID;
			playerPM[ownerID].Pieces[pieID].kill();
			pieceDieOwnerID = ownerID;
			pieceDiePieID = pieID;

			int nKill = nPieKill[PlayerID];
			pieKillList[PlayerID][nKill] = pieceDiePieID;
			nPieKill[PlayerID] = nPieKill[PlayerID] + 1;
		} else {
			pieceDieOwnerID = -1;
			pieceDiePieID = -1;
		}

		// tien hanh di chuyen
		playerPM[PlayerID].Pieces[PieceID].setPosition(ToRow, ToCol);

		if (IsMark) {
			// update code
			int OtherID = tinhIDDoiPhuong(PlayerID);
			if (kiemTraChieuTuong(OtherID)) {
				myBook.addMark(oldRow, oldCol, ToRow, ToCol, pieceDiePieID, PlayerID, true);
			} else {
				myBook.addMark(oldRow, oldCol, ToRow, ToCol, pieceDiePieID, PlayerID, false);
			}
			// dem so turn
			nTurn[PlayerID]++;
			// update code
		}
	}

	// kiem tra end game

	public boolean checkGameEnd() {
		int i, j;

		for (i = 0; i < playerPM[currentTurnID].iNumPiece; i++) {
			if (playerPM[currentTurnID].Pieces[i].nTarget > 0) {
				return false;
			}
		}
		return true;
	}

	// start 1 turn moi

	public void startNewTurn() {
		// tim xem ai dc turn ke tiep
		if (currentTurnID == 0)
			currentTurnID = 1;
		else
			currentTurnID = 0;
		updatePieceMap();
		caculatePiecesTargets(currentTurnID);
		caculateFinalPiecesTargets(currentTurnID);
	}

	// Get next turn ID

	public int getNextTurnID() {
		// tim xem ai dc turn ke tiep
		if (currentTurnID == 0)
			return 1;
		else
			return 0;
	}

	// undo the last player move

	public void undoMove(int PlayerID) {
		int i;
		int nBack;
		History tg;
		if (PlayerID == currentTurnID) {
			nBack = 2;
		} else {
			nBack = 1;
		}
		for (i = 0; i < nBack; i++) {
			if (myBook.iNumMark > 0) {
				restoreByHistory(myBook.getLastMark());
			} else {
				break;
			}
		}
		currentTurnID = tinhIDDoiPhuong(PlayerID);
		startNewTurn();
		updatePieceMap();
	}

	// restore board data

	private void restoreByHistory(History data) {
		int i;
		int MovePieOwner = 0;
		int MovePieID = 0;
		// tim quan co di cuoi cung
		for (i = 0; i < LogicConstant.MAX_PLAYER; i++) {
			MovePieID = playerPM[i].FindPiece(data.seRow, data.seCol);
			if (MovePieID >= 0) {
				MovePieOwner = i;
				break;
			}
		}
		// thuc hien di chuyen lai
		if (MovePieID >= 0) {
			processMovePiece(MovePieOwner, MovePieID, data.fiRow, data.fiCol, false);
			nTurn[MovePieOwner]--;
			if (data.PieDieID >= 0) {
				int DiePieOwner = tinhIDDoiPhuong(MovePieOwner);
				playerPM[DiePieOwner].Pieces[data.PieDieID].resurrect();
				nPieKill[MovePieOwner] = nPieKill[MovePieOwner] - 1;
			}
		}
	}

	private void addPieceLogic(PiecesInfo PieInfo) {
		switch (PieInfo.PieID) {
		case 0:
			playerPM[PieInfo.PlayerID].AddSoldier(PieInfo.Row, PieInfo.Col, PieInfo.Side, map);
			break;
		case 1:
			playerPM[PieInfo.PlayerID].AddCatapult(PieInfo.Row, PieInfo.Col, PieInfo.Side, map);
			break;
		case 2:
			playerPM[PieInfo.PlayerID].AddRook(PieInfo.Row, PieInfo.Col, PieInfo.Side, map);
			break;
		case 3:
			playerPM[PieInfo.PlayerID].AddCavalry(PieInfo.Row, PieInfo.Col, PieInfo.Side, map);
			break;
		case 4:
			playerPM[PieInfo.PlayerID].AddElephant(PieInfo.Row, PieInfo.Col, PieInfo.Side, map);
			break;
		case 5:
			playerPM[PieInfo.PlayerID].AddGuard(PieInfo.Row, PieInfo.Col, PieInfo.Side, map);
			break;
		case 6:
			playerPM[PieInfo.PlayerID].AddGenneral(PieInfo.Row, PieInfo.Col, PieInfo.Side, map);
			break;
		}

		if (PieInfo.IsDead) {
			if (playerPM[PieInfo.PlayerID].iNumPiece > 0) {
				Piece pie = playerPM[PieInfo.PlayerID].Pieces[playerPM[PieInfo.PlayerID].iNumPiece - 1];
				pie.kill();
			}
		}
	}

	// create normal piece map

	public void changePieceMapSide(int sidePlayer0) { // side cua thang playerID
														// = 0
		int i;
		int OtherSide = 0;
		if (sidePlayer0 == 0) {
			OtherSide = 1;
		}

		for (i = 0; i < nPieMapInfo; i++) {
			if (pieMapInfo[i].PlayerID == 0) {
				pieMapInfo[i].Side = sidePlayer0;
			} else {
				pieMapInfo[i].Side = OtherSide;
			}
		}
	}

	private void clearPieceMapList() {
		nPieMapInfo = 0;
		int i;
		for (i = 0; i < 32; i++) {
			pieMapInfo[i].PlayerID = -1;
		}
	}

	private void addPieceList(int Row, int Col, int PlayerID, int PieID, int side) {
		addPieceList(Row, Col, PlayerID, PieID, side, false);
	}

	private void addPieceList(int Row, int Col, int PlayerID, int PieID, int side, boolean IsDead) {
		pieMapInfo[nPieMapInfo].PieID = PieID;
		pieMapInfo[nPieMapInfo].Row = Row;
		pieMapInfo[nPieMapInfo].Col = Col;
		pieMapInfo[nPieMapInfo].PlayerID = PlayerID;
		pieMapInfo[nPieMapInfo].Side = side;
		pieMapInfo[nPieMapInfo].IsDead = IsDead;
		nPieMapInfo++;
	}

	private void createNormalPieceMap() {
		clearPieceMapList();
		// player 0
		addPieceList(3, 0, 0, 0, 0);
		addPieceList(3, 2, 0, 0, 0);
		addPieceList(3, 4, 0, 0, 0);
		addPieceList(3, 6, 0, 0, 0);
		addPieceList(3, 8, 0, 0, 0);
		addPieceList(2, 1, 0, 1, 0);
		addPieceList(2, 7, 0, 1, 0);
		addPieceList(0, 0, 0, 2, 0);
		addPieceList(0, 8, 0, 2, 0);
		addPieceList(0, 1, 0, 3, 0);
		addPieceList(0, 7, 0, 3, 0);
		addPieceList(0, 2, 0, 4, 0);
		addPieceList(0, 6, 0, 4, 0);
		addPieceList(0, 3, 0, 5, 0);
		addPieceList(0, 5, 0, 5, 0);
		addPieceList(0, 4, 0, 6, 0);
		// player 1
		addPieceList(6, 0, 1, 0, 1);
		addPieceList(6, 2, 1, 0, 1);
		addPieceList(6, 4, 1, 0, 1);
		addPieceList(6, 6, 1, 0, 1);
		addPieceList(6, 8, 1, 0, 1);
		addPieceList(7, 1, 1, 1, 1);
		addPieceList(7, 7, 1, 1, 1);
		addPieceList(9, 0, 1, 2, 1);
		addPieceList(9, 8, 1, 2, 1);
		addPieceList(9, 1, 1, 3, 1);
		addPieceList(9, 7, 1, 3, 1);
		addPieceList(9, 2, 1, 4, 1);
		addPieceList(9, 6, 1, 4, 1);
		addPieceList(9, 3, 1, 5, 1);
		addPieceList(9, 5, 1, 5, 1);
		addPieceList(9, 4, 1, 6, 1);
	}

	public boolean checkPie(int PlayerID, int PieID) {
		if ((PlayerID < 0) || (PlayerID >= 2)) {
			return false;
		}
		int nPie = playerPM[PlayerID].iNumPiece;
		if ((PieID < 0) || (PieID >= nPie)) {
			return false;
		}

		return true;
	}

	private int tinhIDDoiPhuong(int PlayerID) {
		// ID cua do'i thu?

		return 1 - PlayerID;
	}

	public void startTimer() {
		timeStart = 0;
	}

	public void stopTimer() {
		timeStart = -1;
	}

	public int getPlayerTime() {
		playerTimer[currentTurnID]--;
		return playerTimer[currentTurnID];
	}

	// update code

	private int countRepeatMove(int PlayerID, int r1, int c1, int r2, int c2) {
		int i;
		int count = 0;
		for (i = myBook.iNumMark - 1; i >= 0; i--) {
			if (myBook.book[i].PlayerMoveID == PlayerID) {
				if (!myBook.book[i].IsKingCapture)
					return count;

				if ((myBook.book[i].fiRow == r1) && (myBook.book[i].fiCol == c1) && (myBook.book[i].seRow == r2) && (myBook.book[i].seCol == c2)) {
					count++;
					continue;
				}
				if ((myBook.book[i].fiRow == r2) && (myBook.book[i].fiCol == c2) && (myBook.book[i].seRow == r1) && (myBook.book[i].seCol == c1)) {
					count++;
					continue;
				}

				return count;
			}
		}

		return count;
	}

	// update code

	public boolean isGameEnd() {
		return isGameEnd;
	}

	public int getCurrentTurnID() {
		return currentTurnID;
	}

	public static class TargetChess {
		public int row;
		public int col;
		public ETargetType type;

		public TargetChess(int _row, int _col, ETargetType _type) {
			this.row = _row;
			this.col = _col;
			this.type = _type;
		}
	}

	public List<TargetChess> canMoveList(int playerID, int pieceID) {
		List<TargetChess> list = new ArrayList<CoTuongLogic.TargetChess>();
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 9; j++) {
				ETargetType tmp = canMovePiece(playerID, pieceID, i, j);
				switch (tmp) {
				case e_DI_DUOC:
					list.add(new TargetChess(i, j, tmp));
					break;
				case e_CAM_CHIEU3:
					System.out.println(" cam chieu 3 : " + i + " " +j);
					list.add(new TargetChess(i, j, tmp));
					break;
				}
			}
		return list;
	}

	public int existPiece(int row, int col) {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < playerPM[i].Pieces.length; j++) {
				Piece p = playerPM[i].Pieces[j];
				if (p.iRow == row && p.iCol == col)
					return p.pieceID;
			}
		return -1;
	}

	public static void main(String[] args) {
		CoTuongLogic logic = new CoTuongLogic();
		logic.initNewGame(0);

		System.out.println("can Move : " + logic.checkPlayerMovePiece(0, 0, 4, 0));

		for (int i = 0; i < 2; i++) {
			PieceManager mng = logic.playerPM[i];
			Piece[] pieces = mng.Pieces;
			for (int j = 0; j < pieces.length; j++) {
				Piece p = pieces[j];
				// System.out.println("owner : " + p.ownerID);
				System.out.println("piece ID : " + p.pieceID);
				System.out.println("type :" + p.pieceType);
				System.out.println("pos :" + p.iRow + " " + p.iCol);
				System.out.println("************\n");
			}
		}
	}
}
