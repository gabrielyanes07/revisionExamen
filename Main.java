import javax.swing.*;
import java.awt.*;

public class Main {
    // Control del simulador
    private static Cocina cocina = new Cocina();
    private static Cocinero chef = new Cocinero("Gabriel Yanes");
    private static int numOrden = 21940;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulador de Pizzería");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel de formulario con GridLayout (estilo Ejemplo 3)
        JPanel campos = new JPanel(new GridLayout(6, 2, 5, 5));
        campos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Ingreso de texto: Nombre del cliente
        JTextField txtCliente = new JTextField();

        // 2. Selección: Masa y Salsa
        JComboBox<String> cbMasa = new JComboBox<>(new String[]{"Tradicional", "Delgada", "Gruesa", "Borde de Queso"});
        JComboBox<String> cbSalsa = new JComboBox<>(new String[]{"Normal", "Picante", "Barbacoa", "Salsa Blanca"});

        // 3. Cajitas (JCheckBox): Queso e Ingredientes
        JCheckBox chkQueso = new JCheckBox("Lleva Queso Mozzarella", true);

        JCheckBox chkPepperoni = new JCheckBox("Pepperoni");
        JCheckBox chkJamon = new JCheckBox("Jamón");
        JCheckBox chkPina = new JCheckBox("Piña");
        JPanel panelIngredientes = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelIngredientes.add(chkPepperoni);
        panelIngredientes.add(chkJamon);
        panelIngredientes.add(chkPina);

        // Ingreso de texto: Ingrediente extra
        JTextField txtExtra = new JTextField();

        // Agregar etiquetas y componentes al formulario
        campos.add(new JLabel("Cliente (Texto):"));
        campos.add(txtCliente);
        campos.add(new JLabel("Masa (Selección):"));
        campos.add(cbMasa);
        campos.add(new JLabel("Salsa (Selección):"));
        campos.add(cbSalsa);
        campos.add(new JLabel("Queso (Cajita):"));
        campos.add(chkQueso);
        campos.add(new JLabel("Ingredientes (Cajitas):"));
        campos.add(panelIngredientes);
        campos.add(new JLabel("Ingrediente Extra (Texto):"));
        campos.add(txtExtra);

        // Panel de botones (acciones)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnCrear = new JButton("Crear y Enviar Orden");
        JButton btnVer = new JButton("Ver Pendientes");
        JButton btnDespachar = new JButton("Despachar Orden");
        panelBotones.add(btnCrear);
        panelBotones.add(btnVer);
        panelBotones.add(btnDespachar);

        // Área de salida para mostrar resultados
        JTextArea salida = new JTextArea(6, 30);
        salida.setEditable(false);

        // --- ACCIONES DE LOS BOTONES ---

        // Botón 1: Crear la orden y mandarla a cocina
        btnCrear.addActionListener(e -> {
            numOrden++;
            String cliente = txtCliente.getText().trim();
            if (cliente.isEmpty()) {
                cliente = "Cliente general";
            }

            // Armar la pizza con masa, salsa y queso
            Pizza pizza = new Pizza(
                (String) cbMasa.getSelectedItem(),
                (String) cbSalsa.getSelectedItem(),
                chkQueso.isSelected()
            );

            // Agregar ingredientes de las cajitas marcadas
            if (chkPepperoni.isSelected()) pizza.agregarIngrediente("Pepperoni");
            if (chkJamon.isSelected()) pizza.agregarIngrediente("Jamón");
            if (chkPina.isSelected()) pizza.agregarIngrediente("Piña");

            // Agregar ingrediente extra si se escribió texto
            String extra = txtExtra.getText().trim();
            if (!extra.isEmpty()) {
                pizza.agregarIngrediente(extra);
            }

            // Crear orden y agregar pizza
            Orden orden = new Orden(numOrden, cliente);
            orden.agregarPizza(pizza);

            // Enviar a la cocina
            boolean agregada = cocina.agregarOrden(orden);
            if (agregada) {
                salida.setText("Orden #" + numOrden + " enviada a cocina con éxito.\n"
                    + "Cliente: " + cliente + "\n"
                    + pizza.toString() + "\n"
                    + "Órdenes en cocina: " + cocina.getCantidadOrdenes() + "/5");
            } else {
                salida.setText("ADVERTENCIA: La cocina está llena (máximo 5 órdenes).\n"
                    + "Despache una orden antes de enviar una nueva.");
            }
        });

        // Botón 2: Ver órdenes pendientes
        btnVer.addActionListener(e -> {
            salida.setText("Órdenes pendientes en cocina: " + cocina.getCantidadOrdenes() + "/5\n");
            cocina.mostrarOrdenesPendientes();
            salida.append("(Detalle completo listado en consola con mostrarOrdenesPendientes())");
        });

        // Botón 3: Despachar orden con el cocinero
        btnDespachar.addActionListener(e -> {
            Orden despachada = cocina.despacharSiguienteOrden();
            if (despachada != null) {
                chef.preparar(despachada);
                salida.setText("Orden #" + despachada.getNumOrden() + " despachada y preparada por " + chef.getNombre() + ".\n"
                    + "Estado: " + despachada.getEstado() + "\n"
                    + "Órdenes restantes en cocina: " + cocina.getCantidadOrdenes() + "/5");
            } else {
                salida.setText("No hay órdenes pendientes en la cocina para despachar.");
            }
        });

        // Armar el marco y mostrarlo
        frame.add(campos, BorderLayout.NORTH);
        frame.add(panelBotones, BorderLayout.CENTER);
        frame.add(new JScrollPane(salida), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
