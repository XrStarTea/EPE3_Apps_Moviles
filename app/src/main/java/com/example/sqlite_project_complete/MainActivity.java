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

        // Login Desde la Base de datos

        btnLogin.setOnClickListener(v -> {

            String user = etUser.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            // obtiene el texto escrito en los campos etUser y etPassword, convirtiendolos a string eliminando espacios con trim
            SQLiteDatabase db = helper.getReadableDatabase(); // abre sql en modo lectura
            Cursor c = db.rawQuery( //ejecuta consulta sql, buscando en tabla users datos que coincidan con usuario y contraseña ingresados
                    "SELECT id FROM users WHERE username=? AND password=?",
                    new String[]{user, pass}); // evita inyeccion sql, guarda resultado en cursor

            if (c.moveToFirst()) { //intenta mover cursor a primer registro encontrado, si da true, credenciales son validas
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show(); //muestra msj bienvenida
                Intent i = new Intent(this, Home_act.class); // crea intencion para arbrir la segunda pantalla, mmostrandonos el crud
                startActivity(i);


            } else { //si da false, es por que los datos son incorrectos
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
            c.close(); //cierra cursor para liberar memoria y recursos de base de datos
        });
    }
}