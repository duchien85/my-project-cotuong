package com.gsn.chess.play;

import chess.logic.game.piece.Piece;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;

public class GsnPiece extends Image {
	public Piece logic;
	
	public static GsnPiece createPiece(Piece piece) {
		switch (piece.ownerID){
		case 0:
			switch (piece.pieceType){
			case e_SOLDIER:
				return new GsnPiece(piece, ChessTexture.blueSoldier);
			case e_CATAPULT:
				return new GsnPiece(piece, ChessTexture.blueCatapult);
			case e_CAVARLY:
				return new GsnPiece(piece, ChessTexture.blueCavarly);
			case e_ELEPHANT:
				return new GsnPiece(piece, ChessTexture.blueElephant);
			case e_GENNERAL:
				return new GsnPiece(piece, ChessTexture.blueGeneral);
			case e_GUARD:
				return new GsnPiece(piece, ChessTexture.blueGuard);
			case e_ROOK:
				return new GsnPiece(piece, ChessTexture.blueRook);				
			}
			break;
		case 1:
			switch (piece.pieceType){
			case e_SOLDIER:
				return new GsnPiece(piece, ChessTexture.redSoldier);
			case e_CATAPULT:
				return new GsnPiece(piece, ChessTexture.redCatapult);
			case e_CAVARLY:
				return new GsnPiece(piece, ChessTexture.redCavarly);
			case e_ELEPHANT:
				return new GsnPiece(piece, ChessTexture.redElephant);
			case e_GENNERAL:
				return new GsnPiece(piece, ChessTexture.redGeneral);
			case e_GUARD:
				return new GsnPiece(piece, ChessTexture.redGuard);
			case e_ROOK:
				return new GsnPiece(piece, ChessTexture.redRook);
			}
			break;
		}
		return null;
	}
	
	public GsnPiece(Piece piece, TextureRegion region) {
		super(region);
		this.logic = piece;
	}
}
