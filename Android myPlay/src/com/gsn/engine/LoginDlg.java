package com.gsn.engine;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vng.mycaro.R;

public class LoginDlg extends Dialog implements IChatInput{

	public String user, pass, session;
	Activity context;

	public LoginDlg(final Activity context, int layout) {
		super(context);
		this.context = context;
		this.setContentView(layout);
		this.setTitle("Login");				
	}

	@Override
	public void chatInput(final IChatListener listener) {		
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {														
				final EditText chatText = (EditText) LoginDlg.this.findViewById(R.id.editTextChat);
				chatText.setText("");
				show();							
				final Button loginBtn = (Button) LoginDlg.this.findViewById(R.id.okBtn);
				loginBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						listener.onFinish(chatText.getText().toString());
						hide();
					}
				});
			}
		});
	}
	
}
