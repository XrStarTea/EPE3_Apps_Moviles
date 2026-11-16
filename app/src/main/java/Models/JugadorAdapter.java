package Models;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class JugadorAdapter extends ArrayAdapter<Jugador> {

    public JugadorAdapter(@NonNull Context context, @NonNull List<Jugador> jugadores) {
        super(context, android.R.layout.simple_list_item_2, jugadores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        Jugador jugador = getItem(position);
        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        if (jugador != null) {
            text1.setText("#" + jugador.getNumeroCamiseta() + " - " + jugador.getNombre());
            text1.setTextColor(Color.parseColor("#1F1F1F"));
            text1.setTextSize(15f);

            text2.setText("Posición: " + jugador.getPosicion() + "  |  ID: " + jugador.getId());
            text2.setTextColor(Color.parseColor("#666666"));
        }

        return convertView;
    }
}