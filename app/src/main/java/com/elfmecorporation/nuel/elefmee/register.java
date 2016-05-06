package com.elfmecorporation.nuel.elefmee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText nama,username,password,email,ponsel,alamat,rePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama = (EditText)findViewById(R.id.tvNama);
        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);
        rePassword = (EditText)findViewById(R.id.etRePassword);
        email = (EditText)findViewById(R.id.etEmail);
        ponsel = (EditText)findViewById(R.id.etPonsel);
        alamat = (EditText)findViewById(R.id.etAlamat);
    }

    public void OpenReg(View v){
        String str_nama = nama.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        String str_rePassword =  rePassword.getText().toString();
        String str_email = email.getText().toString();
        String str_ponsel = ponsel.getText().toString();
        String str_alamat = alamat.getText().toString();
        String type = "register";
        if(str_nama.equals("") || str_username.equals("") || str_password.equals("") || str_email.equals("")
                || str_ponsel.equals("") || str_alamat.equals("")){
            Toast.makeText(register.this, "semua data harus di isi", Toast.LENGTH_SHORT).show();
        }else{
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_nama, str_username, str_password, str_email, str_ponsel, str_alamat);
        }


    }
}
