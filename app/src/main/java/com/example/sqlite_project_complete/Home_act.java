package com.example.sqlite_project_complete;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Models.Jugador;
import Models.JugadorDAO;
import Models.JugadorFormatter;

public class Home_act extends AppCompatActivity {

    EditText etNombreJugador, etPosicionJugador, etNumeroCamiseta;
    TextView tvList;
    JugadorDAO jugadorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etNombreJugador   = findViewById(R.id.etNombreJugador);
        etPosicionJugador = findViewById(R.id.etPosicionJugador);
        etNumeroCamiseta  = findViewById(R.id.etNumeroCamiseta);
        tvList            = findViewById(R.id.tvList);

        Button btnAdd    = findViewById(R.id.btnAdd);
        Button btnShow   = findViewById(R.id.btnShow);
        Button btnDelete = findViewById(R.id.btnDelete);

        jugadorDAO = new JugadorDAO(this);

        // ---------- AÑADIR JUGADOR ----------
        btnAdd.setOnClickListener(v -> {
            String nombre = etNombreJugador.getText().toString().trim();
            String posicion = etPosicionJugador.getText().toString().trim();
            String camisetaStr = etNumeroCamiseta.getText().toString().trim();

            if (nombre.isEmpty() || posicion.isEmpty() || camisetaStr.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int numeroCamiseta = Integer.parseInt(camisetaStr);
            Jugador nuevo = new Jugador(nombre, posicion, numeroCamiseta);
            long r = jugadorDAO.insertarJugador(nuevo);

            if (r != -1) {
                Toast.makeText(this, "Jugador añadido", Toast.LENGTH_SHORT).show();
                etNombreJugador.setText("");
                etPosicionJugador.setText("");
                etNumeroCamiseta.setText("");
            } else {
                Toast.makeText(this, "Ese número de camiseta ya existe", Toast.LENGTH_SHORT).show();
            }
        });

        // ---------- MOSTRAR JUGADORES ----------
        btnShow.setOnClickListener(v -> {
            List<Jugador> jugadores = jugadorDAO.listarJugadores();
            tvList.setText(JugadorFormatter.formatearLista(jugadores));
        });

        // ---------- ELIMINAR JUGADOR ----------
        btnDelete.setOnClickListener(v -> {
            String camisetaStr = etNumeroCamiseta.getText().toString().trim();
            if (camisetaStr.isEmpty()) {
                Toast.makeText(this, "Escribe el N° de camiseta a eliminar", Toast.LENGTH_SHORT).show();
                return;
            }

            int numeroCamiseta = Integer.parseInt(camisetaStr);
            int filas = jugadorDAO.eliminarPorCamiseta(numeroCamiseta);

            if (filas > 0) {
                Toast.makeText(this, "Jugador eliminado", Toast.LENGTH_SHORT).show();
                etNombreJugador.setText("");
                etPosicionJugador.setText("");
                etNumeroCamiseta.setText("");
            } else {
                Toast.makeText(this, "No existe un jugador con esa camiseta", Toast.LENGTH_SHORT).show();
            }
        });
    }
}