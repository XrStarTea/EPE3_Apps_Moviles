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
            // obtiene texto de etUser y etPass y los convierte a string, eliminando espacios conm trim
            SQLiteDatabase db = helper.getReadableDatabase(); // abre la base de datos en modo lectura
            Cursor c = db.rawQuery( //ejecuta consulta, buscando registro en tabla users que coincida con usuario y contraseña ingresados
                    "SELECT id FROM users WHERE username=? AND password=?",
                    new String[]{user, pass}); // evita uso de inyeccion sql y guarda el resultado en cursor

            if (c.moveToFirst()) { //intenta mover cursor al primer registro encontrado, si es true las credenciales son validas
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show(); //muestra mensaje emergente de bienvenida
                Intent i = new Intent(this, Home_act.class); // crea una instancia nueva y abre la otra pantalla, la pantalla del crud
                startActivity(i);


            } else { // si c.moveToFirst da false, notifica que datos son incorrectos
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show(); //msj emergente de datos incorrectos
            }
            c.close(); // cierra cursor para liberar memoria y recursos del sql
        });
    }
}