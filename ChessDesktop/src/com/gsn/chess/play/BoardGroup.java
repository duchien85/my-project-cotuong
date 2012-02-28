package com.gsn.chess.play;

import chess.logic.game.CoTuongLogic;
import chess.logic.game.piece.Piece;
import chess.logic.game.piece.Soldier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.engine.ActorUtility;

public class BoardGroup extends Group implements ClickListener {
	public static final String tag = "Board Group";

	Image board;
	float scale;
	float longCell;
	CoTuongLogic logic;
	Image select;
	public BoardGroup(float width, float height) {
		this.width = width;
		this.height = height;
		logic = new CoTuongLogic();
		logic.initNewGame(0, 10);
		
		
		float boardWidth = 0.95f * width;
		float boardHeight = 0.95f * height;

		Image boardBG = new Image(ChessTexture.boardBG);
		boardBG.width = width;
		boardBG.height = height;

		board = new Image(ChessTexture.board);
		scale = Math.min(boardWidth / board.width, boardHeight / board.height);
		board.setClickListener(this);

		scaleContent(board);
		longCell = board.width / 8;

		ActorUtility.setCenter(board, width / 2, height / 2);		
		
		addActor(boardBG);
		addActor(board);
		
		initBoard();
		Image img = new Image(ChessTexture.effectSelect);
		addActor(img);
		
		select = new Image(ChessTexture.effectSelect);
		scaleContent(select);
		addActor(select);
		select.visible = false;
	}

	public void initBoard() {
		for (int i = 0; i < 2; i++) {
			Piece[] pieceArr = logic.playerPM[i].Pieces;
			for (int j = 0; j < pieceArr.length; j++) {
				GsnPiece piece = GsnPiece.createPiece(pieceArr[j]);
				if (piece != null) {					
					addActor(piece);
					piece.setClickListener(this);
					scaleContent(piece);
					putCell(piece, piece.logic.iRow, piece.logic.iCol);
				}
			}
		}

	}

	public void scaleContent(Actor actor) {
		actor.width *= scale;
		actor.height *= scale;
	}

	public void putCell(Actor actor, int row, int column) {
		float x = board.x + column * longCell;
		float y = board.y + row * longCell;
		ActorUtility.setCenter(actor, x, y);
	}

	@Override
	public void click(Actor actor, float x, float y) {
		if (actor == board) {
			Gdx.app.log(tag, "click board : " + x + " " + y);
//			int column = Math.round(x / longCell);
//			int row = Math.round(y / longCell);
//			float kcX = Math.abs(x - column * longCell);
//			float kcY = Math.abs(y - row * longCell);
//			if ((kcX < longCell / 3 && kcY < longCell / 3)) {
//				Image piece = new Image(ChessTexture.blueCatapult);
//				putCell(piece, row, column);
//				addActor(piece);
//			}
		} else if (actor instanceof GsnPiece){
			GsnPiece piece = (GsnPiece)actor;
			Gdx.app.log(tag, "click piece : " + piece.logic.pieceType);
			effectSelect(piece.logic.iRow, piece.logic.iCol);
		}
	}

	
	public void effectSelect(int row, int col){
		select.visible = true;			
		putCell(select, row, col);
	}
}
