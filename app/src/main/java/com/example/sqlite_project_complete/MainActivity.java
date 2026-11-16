package com.example.sqlite_project_complete;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Models.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUser = findViewById(R.id.etUser);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        DatabaseHelper helper = new DatabaseHelper(this);


        // LOGIN:Desde la Base de datos

        btnLogin.setOnClickListener(v -> {

            String user = etUser.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor c = db.rawQuery(
                    "SELECT id FROM users WHERE username=? AND password=?",
                    new String[]{user, pass});

            if (c.moveToFirst()) {
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Home_act.class);
                startActivity(i);


            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
            c.close();
        });
    }
}