public class Compra {
    private String nombreCliente;
    private String pelicula;
    private int cantidadEntradas;
    private double precioTotal;

    public Compra(String nombreCliente, String pelicula, int cantidadEntradas, double precioTotal) {
        this.nombreCliente = nombreCliente;
        this.pelicula = pelicula;
        this.cantidadEntradas = cantidadEntradas;
        this.precioTotal = precioTotal;
    }

    public String getPelicula() { return pelicula; }
    public int getCantidadEntradas() { return cantidadEntradas; }
    public double getPrecioTotal() { return precioTotal; }

    @Override
    public String toString() {
        return String.format("Película: %s | Cliente: %s | Entradas: %d | Total: $%.2f",
                pelicula, nombreCliente, cantidadEntradas, precioTotal);
    }
}
