package Models;

import java.util.List;

public class JugadorFormatter {

    // Construye la tabla visual formateada para mostrar en el TextView monoespaciado
    public static String formatearLista(List<Jugador> jugadores) {
        if (jugadores == null || jugadores.isEmpty()) {
            return "No hay jugadores registrados";
        }

        StringBuilder sb = new StringBuilder();
        for (Jugador j : jugadores) {
            sb.append("ID: ").append(j.getId())
                    .append(" | #").append(j.getNumeroCamiseta())
                    .append(" | ").append(j.getNombre())
                    .append(" (").append(j.getPosicion()).append(")\n");
        }
        return sb.toString();
    }
}