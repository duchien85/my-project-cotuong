package com.gsn.chess.play;

import com.gsn.chess.asset.CommonTexture;
import com.gsn.chess.lobby.GsnDialogYesNoLayer;

public class DialogYesNoChessLayer extends GsnDialogYesNoLayer {
	public DialogYesNoChessLayer() {
		super(CommonTexture.greyBG, CommonTexture.dialogBg, CommonTexture.okBtn, CommonTexture.okBtnDown, CommonTexture.cancelBtn, CommonTexture.cancelBtnDown);
	}
}
