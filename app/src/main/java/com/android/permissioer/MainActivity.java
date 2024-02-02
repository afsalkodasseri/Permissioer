package com.android.permissioer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button_ok,button_cancel;
    TextView tvAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_cancel = findViewById(R.id.button_cancel);
        button_ok = findViewById(R.id.button_ok);
        tvAction = findViewById(R.id.textView);

        Intent incoming = getIntent();
        if(incoming!=null){
            String actionName = incoming.getAction();
            tvAction.setText(actionName);
        }

        button_ok.setOnClickListener(view -> {
            setResult(RESULT_OK);
            finish();
        });

        button_cancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}