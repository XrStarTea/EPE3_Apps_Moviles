package com.example.sqlite_project_complete;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import Models.Jugador;
import Models.JugadorAdapter;
import Models.JugadorDAO;

public class Home_act extends AppCompatActivity {
    // Declaramos los componentes de la interfaz
    EditText etNombreJugador, etNumeroCamiseta;
    Spinner spPosicionJugador;
    ListView tvList;
    JugadorDAO jugadorDAO;
    List<Jugador> listaJugadores;
    JugadorAdapter adapter;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Llamamos a los componentes de la interfaz, buscandolas por el id
        etNombreJugador   = findViewById(R.id.etNombreJugador);
        spPosicionJugador = findViewById(R.id.spPosicionJugador);
        etNumeroCamiseta  = findViewById(R.id.etNumeroCamiseta);
        tvList            = findViewById(R.id.tvList);

        Button btnAdd    = findViewById(R.id.btnAdd);
        Button btnShow   = findViewById(R.id.btnShow);
        Button btnDelete = findViewById(R.id.btnDelete);

        // Declaramos los valores que estaran dentro del Spinner
        String[] posiciones = {
                "Selecciona una posición...",
                "Portero",
                "Defensa",
                "Mediocampista",
                "Delantero"
        };
        //Creamos adaptador con la lista de posiciones
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, posiciones);
        spPosicionJugador.setAdapter(spinnerAdapter); //conectamos al menu desplegable

        // Iniciamos DAO y ListView
        jugadorDAO = new JugadorDAO(this);
        listaJugadores = new ArrayList<>();
        adapter = new JugadorAdapter(this, listaJugadores);
        tvList.setAdapter(adapter);

        // añadimos jugadores
        btnAdd.setOnClickListener(v -> {
            String nombre = etNombreJugador.getText().toString().trim();
            String posicion = spPosicionJugador.getSelectedItem().toString();
            String camisetaStr = etNumeroCamiseta.getText().toString().trim();

            if (nombre.isEmpty() || camisetaStr.isEmpty() || spPosicionJugador.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Completa el nombre, camiseta y selecciona una posición", Toast.LENGTH_SHORT).show();
                return;
            } //Error donde faltan camposs para llenar

            int numeroCamiseta = Integer.parseInt(camisetaStr);
            Jugador nuevo = new Jugador(nombre, posicion, numeroCamiseta);
            long r = jugadorDAO.insertarJugador(nuevo);

            if (r != -1) {
                Toast.makeText(this, "Jugador añadido a la cancha", Toast.LENGTH_SHORT).show();
                etNombreJugador.setText("");
                spPosicionJugador.setSelection(0);
                etNumeroCamiseta.setText("");
            } else {
                Toast.makeText(this, "Ese número de camiseta ya existe", Toast.LENGTH_SHORT).show();
            }
        });

        // mostramos los jugadores en el tv list
        btnShow.setOnClickListener(v -> {
            listaJugadores.clear();
            listaJugadores.addAll(jugadorDAO.listarJugadores());
            adapter.notifyDataSetChanged();

            if (listaJugadores.isEmpty()) {
                Toast.makeText(this, "No hay jugadores registrados", Toast.LENGTH_SHORT).show();
            } //eror por si en el string de jugadores no hay ninguno, avisando con un texto
        });

        // eliminar jugadores
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
                spPosicionJugador.setSelection(0);
                etNumeroCamiseta.setText("");
                listaJugadores.clear();
                listaJugadores.addAll(jugadorDAO.listarJugadores());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "No existe un jugador con esa camiseta", Toast.LENGTH_SHORT).show();
            }
        });

        // Al tocar un jugador de la lista, carga sus datos arriba
        tvList.setOnItemClickListener((parent, view, position, id) -> {
            Jugador seleccionado = listaJugadores.get(position);
            etNombreJugador.setText(seleccionado.getNombre());
            etNumeroCamiseta.setText(String.valueOf(seleccionado.getNumeroCamiseta()));

            int spinnerPos = spinnerAdapter.getPosition(seleccionado.getPosicion());
            if (spinnerPos >= 0) {
                spPosicionJugador.setSelection(spinnerPos);
            }
        });
    }
}