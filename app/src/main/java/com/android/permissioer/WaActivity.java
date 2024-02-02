package com.android.permissioer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WaActivity extends AppCompatActivity {
    ImageButton buttonWa;
    EditText etPhone;
    TextView tvCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wa);

        buttonWa = findViewById(R.id.button);
        etPhone = findViewById(R.id.et_phone);
        tvCopy = findViewById(R.id.tv_copy);

        tvCopy.setOnClickListener(view -> {
            handleNumber(tvCopy.getText().toString());
        });

        buttonWa.setOnClickListener(view -> {
            String phone = etPhone.getText().toString();
            handleNumber(phone);
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            String data = item.getText().toString();
            if(data.isEmpty()||!isValidMobile(data)){
                tvCopy.setVisibility(View.GONE);
            }else{
                tvCopy.setVisibility(View.VISIBLE);
                tvCopy.setText(data);
            }
        }
        super.onWindowFocusChanged(hasFocus);
    }

    void handleNumber(String phone){
        if (phone.isEmpty()) {
            Toast.makeText(this, "empty number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.contains("+")) {
            if (phone.indexOf("+") != 0) {
                Toast.makeText(this, "invalid number", Toast.LENGTH_SHORT).show();
            } else {
                phone = phone.substring(1);
            }
        }else{
            phone = "91"+phone;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://wa.me/"+phone));
        startActivity(intent);
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}