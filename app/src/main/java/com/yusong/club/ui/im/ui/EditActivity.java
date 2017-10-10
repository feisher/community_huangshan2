package com.yusong.club.ui.im.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hyphenate.easeui.widget.EaseTitleBar;
import com.yusong.club.R;
import com.yusong.club.ui.im.IMBaseActivity;

public class EditActivity extends IMBaseActivity{
	private EditText editText;


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.em_activity_edit);
		EaseTitleBar mTitleBar = (EaseTitleBar) findViewById(R.id.title_bar);
		mTitleBar.setLeftImageResource(R.mipmap.back);
		mTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mTitleBar.setRightText("保存");
		mTitleBar.setRightLayoutClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save(v);
			}
		});
		editText = (EditText) findViewById(R.id.edittext);
		String title = getIntent().getStringExtra("title");
		String data = getIntent().getStringExtra("data");
		if(title != null)
			mTitleBar.setTitle(title);
		if(data != null)
			editText.setText(data);
		editText.setSelection(editText.length());
		
	}
	
	
	public void save(View view){
		setResult(RESULT_OK,new Intent().putExtra("data", editText.getText().toString()));
		finish();
	}

	public void back(View view) {
		finish();
	}
}
