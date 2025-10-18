import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class CineApp extends JFrame {
    private JTextPane PELÍCULATextPane;
    private JTextPane NOMBRECLIENTETextPane;
    private JTextPane CANTIDADENTRADASTextPane;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton COMPRARButton;
    private JTextArea textArea1;
    private JPanel principal;

    private Queue<String> colaCompras = new LinkedList<>();

    private int capXmen = 23, capMario = 23, capBatman = 23;
    private int vendXmen = 0, vendMario = 0, vendBatman = 0;

    private final double precioXmen = 2.25, precioMario = 3.25, precioBatman = 3.75;
    private double totalRecaudado = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CineApp");
        frame.setContentPane(new CineApp().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public CineApp() {

        comboBox1.addItem("XMEN");
        comboBox1.addItem("MARIO");
        comboBox1.addItem("BATMAN");

        COMPRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pelicula = comboBox1.getSelectedItem().toString();
                String nombre = textField1.getText().trim();
                String cantidadStr = textField2.getText().trim();

                if (nombre.isEmpty() || cantidadStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos.");
                    return;
                }

                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                    return;
                }

                if (cantidad <= 0 || cantidad > 4) {
                    JOptionPane.showMessageDialog(null, "Cantidad debe ser entre 1 y 4.");
                    return;
                }

                double precioEntrada = 0;
                switch (pelicula) {
                    case "XMEN":
                        if (vendXmen + cantidad > capXmen) {
                            JOptionPane.showMessageDialog(null, "Capacidad de XMEN superada.");
                            return;
                        }
                        vendXmen += cantidad;
                        precioEntrada = precioXmen;
                        break;
                    case "MARIO":
                        if (vendMario + cantidad > capMario) {
                            JOptionPane.showMessageDialog(null, "Capacidad de MARIO superada.");
                            return;
                        }
                        vendMario += cantidad;
                        precioEntrada = precioMario;
                        break;
                    case "BATMAN":
                        if (vendBatman + cantidad > capBatman) {
                            JOptionPane.showMessageDialog(null, "Capacidad de BATMAN superada.");
                            return;
                        }
                        vendBatman += cantidad;
                        precioEntrada = precioBatman;
                        break;
                }

                double total = cantidad * precioEntrada;
                totalRecaudado += total;

                String compra = "Película: " + pelicula + " | Cliente: " + nombre +
                        " | Entradas: " + cantidad + " | Total: $" + String.format("%.2f", total);

                colaCompras.add(compra);
                textArea1.append(compra + "\n");

                textArea1.append("\n--- ESTADO ---\n");
                textArea1.append("XMEN: Vendidas " + vendXmen + " / " + capXmen + "\n");
                textArea1.append("MARIO: Vendidas " + vendMario + " / " + capMario + "\n");
                textArea1.append("BATMAN: Vendidas " + vendBatman + " / " + capBatman + "\n");
                textArea1.append("Total Recaudado: $" + String.format("%.2f", totalRecaudado) + "\n");
                textArea1.append("-------------------------\n");

                textField1.setText("");
                textField2.setText("");
            }
        });
    }
}
