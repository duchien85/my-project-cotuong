package com.gsn.chess.play;

import java.util.ArrayList;
import java.util.List;

import chess.logic.game.CoTuongLogic;
import chess.logic.game.CoTuongLogic.TargetChess;
import chess.logic.game.piece.Piece;
import chess.logic.game.piece.Piece.ETargetType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.asset.DataProvider;
import com.gsn.chess.game.MyChess;
import com.gsn.chess.packet.PacketFactory;
import com.gsn.engine.ActorUtility;

public class BoardGroup extends Group implements ClickListener {
	enum State {
		CHUA_BAT_DAU, CHUA_CHON, DA_CHON
	}

	public static final String tag = "Board Group";
	public static int GENERAL_ID = 15;
	Image board;
	Image boardBG;
	Image justMoveEff;
	Image selectEff;
	Image chieuEff;
	Image camChieuEff;
	CoTuongLogic logic;

	float longCell;
	float scale;

	GsnPiece selectPiece;

	State state;
	List<Image> suggestList = new ArrayList<Image>();
	List<GsnPiece> pieceList = new ArrayList<GsnPiece>();

	public BoardGroup(float width, float height) {
		this.width = width;
		this.height = height;
		initBoardBg();
		
		logic = new CoTuongLogic();
		logic.initNewGame(0);
	}

	private void addSuggest(int row, int col) {
		Image tmp = new Image(ChessTexture.effectSuggest);
		scaleContent(tmp);
		putCell(tmp, row, col);
		addActor(tmp);
		suggestList.add(tmp);
	}

	private void addSuggestAnQuan(int row, int col) {
		Image tmp = new Image(ChessTexture.effectAnQuan);
		scaleContent(tmp);
		putCell(tmp, row, col);
		addActor(tmp);
		suggestList.add(tmp);
	}

	@Override
	public void click(Actor actor, float x, float y) {
		if (actor == boardBG) {
			Gdx.app.log(tag, "click board : " + x + " " + y);
			x = x - board.x;
			y = y - board.y;
			int column = Math.round(x / longCell);
			int row = Math.round(y / longCell);
			float kcX = Math.abs(x - column * longCell);
			float kcY = Math.abs(y - row * longCell);
			if ((kcX < longCell * 0.45 && kcY < longCell * 0.45)) {
				selectCell(row, column);
			}
		} else if (actor instanceof GsnPiece) {
			GsnPiece piece = (GsnPiece) actor;
			Gdx.app.log(tag, "click piece : " + piece.logic.pieceType + " owner : " + piece.logic.ownerID + " id : " + piece.logic.pieceID);
			switch (state) {
			case CHUA_BAT_DAU:				
				break;
			case CHUA_CHON:
				if (logic.getCurrentTurnID() == piece.logic.ownerID)
					selectMyPiece(piece);
				break;
			case DA_CHON:
				if (logic.getCurrentTurnID() == piece.logic.ownerID)
					selectMyPiece(piece);
				else
					selectCell(piece.logic.iRow, piece.logic.iCol);
				break;
			}
		}
	}

	private void effectSelect(int row, int col) {
		selectEff.visible = true;
		putCell(selectEff, row, col);
	}

	private void effectCamChieu(int row, int col) {
		camChieuEff.visible = true;
		putCell(camChieuEff, row, col);
	}
	
	public void initBoardBg(){
		state = State.CHUA_CHON;
		float boardWidth = 0.9f * width;
		float boardHeight = 0.9f * height;

		boardBG = new Image(ChessTexture.boardBG);
		boardBG.width = width;
		boardBG.height = height;
		boardBG.setClickListener(this);

		board = new Image(ChessTexture.board);
		scale = Math.min(boardWidth / board.width, boardHeight / board.height);

		scaleContent(board);
		longCell = board.width / 8;

		ActorUtility.setCenter(board, width / 2, height / 2);

		addActor(boardBG);
		addActor(board);
	}

	public void initBoardPiece() {
		pieceList.clear();
		for (int i = 0; i < 2; i++) {
			Piece[] pieceArr = logic.playerPM[i].Pieces;
			for (int j = 0; j < pieceArr.length; j++) {
				GsnPiece piece = GsnPiece.createPiece(pieceArr[j]);
				if (piece != null) {
					pieceList.add(piece);
					addActor(piece);
					piece.setClickListener(this);
					scaleContent(piece);
					putCell(piece, piece.logic.iRow, piece.logic.iCol);
				}
			}
		}
	}

	public void startGame(int firstTurn) {
		logic = new CoTuongLogic();
		logic.initNewGame(firstTurn);
		
		clear();
		initBoardBg();
		initBoardPiece();		

		selectEff = new Image(ChessTexture.effectSelect);
		scaleContent(selectEff);
		addActor(selectEff);
		selectEff.visible = false;

		justMoveEff = new Image(ChessTexture.effectJustMove);
		scaleContent(justMoveEff);
		addActor(justMoveEff);
		justMoveEff.visible = false;

		chieuEff = new Image(ChessTexture.effectChieuTuong);
		scaleContent(chieuEff);
		addActor(chieuEff);
		chieuEff.visible = false;

		camChieuEff = new Image(ChessTexture.signX);
		scaleContent(camChieuEff);
		addActor(camChieuEff);
		camChieuEff.visible = false;
	}

	private void putCell(Actor actor, int row, int column) {
		float x = board.x + column * longCell;
		float y = board.y + row * longCell;
		ActorUtility.setCenter(actor, x, y);
	}

	private void removeSuggest() {
		for (Image tmp : suggestList)
			tmp.remove();
		suggestList.clear();
	}

	private void scaleContent(Actor actor) {
		actor.width *= scale;
		actor.height *= scale;
	}

	private void selectCell(int row, int col) {
		Gdx.app.log(tag, "select cell : " + row + " " + col);
		switch (state) {
		case CHUA_CHON:
			break;
		case DA_CHON:
			if (logic.canMovePiece(logic.getCurrentTurnID(), selectPiece.logic.pieceID, row, col) == ETargetType.e_DI_DUOC) {
				int fromRow = selectPiece.logic.iRow;
				int fromCol = selectPiece.logic.iCol;
				int toRow = row;
				int toCol = col;
				if (MyChess.game.reserve){
					fromRow = 9 - fromRow;
					fromCol = 8 - fromCol;
					toRow = 9 - toRow;
					toCol = 8 - toCol;
				}
				
				MyChess.client.send(PacketFactory.createMove(fromRow, fromCol, toRow, toCol));
				moveChess(logic.getCurrentTurnID(), selectPiece.logic.pieceID, row, col);
				
			}
			break;
		}
	}

	private GsnPiece findPieceByID(int playerId, int pieceId) {
		for (GsnPiece tmp : pieceList) {
			if (tmp.visible && playerId == tmp.logic.ownerID && pieceId == tmp.logic.pieceID)
				return tmp;
		}
		return null;
	}

	private GsnPiece findPieceByPos(int row, int col) {
		for (GsnPiece tmp : pieceList) {
			if (tmp.visible && row == tmp.logic.iRow && col == tmp.logic.iCol)
				return tmp;
		}
		return null;
	}

	public void moveChess(int playerId, int pieceId, int row, int col) {
		removeSuggest();
		selectEff.visible = false;
		camChieuEff.visible = false;
		GsnPiece piece = findPieceByID(playerId, pieceId);
		putCell(selectPiece, row, col);
		GsnPiece other = findPieceByPos(row, col);
		if (other != null)
			other.visible = false;
		logic.processMovePiece(playerId, piece.logic.pieceID, row, col, true);
		state = State.CHUA_CHON;
		logic.startNewTurn();
		effectJustMove(row, col);
		effectChieuTuong();
	}

	private int checkChieuTuong() {
		if (logic.kiemTraChieuTuong(0))
			return 0;
		if (logic.kiemTraChieuTuong(1))
			return 1;
		return -1;
	}

	private void effectChieuTuong() {		
		int chieu = checkChieuTuong();
		if (chieu < 0)
			chieuEff.visible = false;
		else {
			GsnPiece general = findPieceByID(chieu, GENERAL_ID);
			chieuEff.visible = true;
			putCell(chieuEff, general.logic.iRow, general.logic.iCol);
			
			Image chieuImg = new Image(ChessTexture.redGeneral);;
			ActorUtility.setCenter(chieuImg, width / 2, height / 2);
			addActor(chieuImg);
			chieuImg.action(FadeOut.$(1.5f));
		}
	}

	private void effectJustMove(int row, int col) {
		justMoveEff.visible = true;
		putCell(justMoveEff, row, col);
	}

	private void selectMyPiece(GsnPiece piece) {
		state = State.DA_CHON;
		selectPiece = piece;
		effectSelect(piece.logic.iRow, piece.logic.iCol);
		List<TargetChess> targetList = logic.canMoveList(logic.getCurrentTurnID(), piece.logic.pieceID);
		removeSuggest();
		for (TargetChess target : targetList) {
			int row = target.row;
			int col = target.col;
			// System.out.println("Target : " + row + " " + col);
			switch (target.type) {
			case e_DI_DUOC:
				if (findPieceByPos(row, col) != null)
					addSuggestAnQuan(row, col);
				else
					addSuggest(row, col);
				break;
			case e_CAM_CHIEU3:
				Gdx.app.log(tag, "Effect Cam Chieu 3 ----------");
				effectCamChieu(row, col);
				break;
			}			
		}
	}

	public void moveChess(int turn, int fromRow, int fromCol, int toRow, int toCol) {
		GsnPiece p = findPieceByPos(fromRow, fromCol);
		selectPiece = p;
		moveChess(turn, p.logic.pieceID, toRow, toCol);		
	}
}
