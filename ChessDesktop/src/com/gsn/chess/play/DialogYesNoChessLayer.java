package com.gsn.chess.play;

import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.lobby.GsnDialogYesNoLayer;

public class DialogYesNoChessLayer extends GsnDialogYesNoLayer {
	public DialogYesNoChessLayer() {
		super(ChessTexture.greyBG, ChessTexture.dialogBg, ChessTexture.yesBtn, ChessTexture.yesBtnDown, ChessTexture.cancelBtn, ChessTexture.cancelBtnDown);
	}
}
