import java.util.*;

public class GestorCine {
    private static final int CAPACIDAD_SALA = 23;
    private Map<String, Queue<Compra>> colasPeliculas;
    private Map<String, Integer> entradasVendidas;
    private Map<String, Double> precios;

    public GestorCine() {
        colasPeliculas = new HashMap<>();
        entradasVendidas = new HashMap<>();
        precios = new HashMap<>();

        colasPeliculas.put("XMEN", new LinkedList<>());
        colasPeliculas.put("MARIO", new LinkedList<>());
        colasPeliculas.put("BATMAN", new LinkedList<>());

        entradasVendidas.put("XMEN", 0);
        entradasVendidas.put("MARIO", 0);
        entradasVendidas.put("BATMAN", 0);

        precios.put("XMEN", 2.25);
        precios.put("MARIO", 3.25);
        precios.put("BATMAN", 3.75);
    }

    public boolean agregarCompra(String pelicula, String nombre, int cantidad) {
        int actuales = entradasVendidas.get(pelicula);
        if (cantidad < 1 || cantidad > 4) return false;
        if (actuales + cantidad > CAPACIDAD_SALA) return false;

        double total = precios.get(pelicula) * cantidad;
        Compra compra = new Compra(nombre, pelicula, cantidad, total);
        colasPeliculas.get(pelicula).offer(compra);
        entradasVendidas.put(pelicula, actuales + cantidad);
        return true;
    }

    public String mostrarCompras() {
        StringBuilder sb = new StringBuilder();
        for (String p : colasPeliculas.keySet()) {
            for (Compra c : colasPeliculas.get(p)) {
                sb.append(c.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public int getVendidas(String pelicula) {
        return entradasVendidas.get(pelicula);
    }

    public int getDisponibles(String pelicula) {
        return CAPACIDAD_SALA - entradasVendidas.get(pelicula);
    }

    public double getTotalRecaudado() {
        double total = 0;
        for (String p : colasPeliculas.keySet()) {
            for (Compra c : colasPeliculas.get(p)) {
                total += c.getPrecioTotal();
            }
        }
        return total;
    }
}

