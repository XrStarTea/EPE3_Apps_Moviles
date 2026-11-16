package Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {
    private final DatabaseHelper helper;

    public JugadorDAO(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    public long insertarJugador(Jugador jugador) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", jugador.getNombre());
        cv.put("posicion", jugador.getPosicion());
        cv.put("numero_camiseta", jugador.getNumeroCamiseta());
        long resultado = db.insert("jugadores", null, cv);
        db.close();
        return resultado;
    }

    public List<Jugador> listarJugadores() {
        List<Jugador> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, nombre, posicion, numero_camiseta FROM jugadores ORDER BY numero_camiseta ASC", null);
        while (c.moveToNext()) {
            lista.add(new Jugador(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3)));
        }
        c.close();
        db.close();
        return lista;
    }

    public int eliminarPorCamiseta(int numeroCamiseta) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int filas = db.delete("jugadores", "numero_camiseta=?", new String[]{String.valueOf(numeroCamiseta)});
        db.close();
        return filas;
    }
}