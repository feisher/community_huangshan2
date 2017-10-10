package com.yusong.club.ui.school.school.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yusong.club.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        TextView con = (TextView) findViewById(R.id.content);
        con.setText(getIntent().getStringExtra("content"));
    }
}
