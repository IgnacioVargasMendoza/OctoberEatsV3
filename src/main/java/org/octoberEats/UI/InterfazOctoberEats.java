package org.octoberEats.UI;

import org.octoberEats.DB.ConexcionDB;
import org.octoberEats.DB.ItemMenuDAO;
import org.octoberEats.DB.MenuDAO;
import org.octoberEats.DB.RestauranteDAO;
import org.octoberEats.Modelos.Carrito;
import org.octoberEats.Modelos.ItemMenu;
import org.octoberEats.Modelos.Menu;
import org.octoberEats.Modelos.Restaurante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfazOctoberEats extends JFrame {
    private JComboBox<Restaurante> restauranteComboBox;
    private JList<ItemMenu> menuList;
    private DefaultListModel<ItemMenu> menuModel;
    private JButton addButton;
    private JButton removeButton;
    private JButton viewCartButton;
    private JButton proceedButton;
    private JTextArea pedidoArea;
    private JTextField cantidadField;
    private Carrito carrito;

    //Instacias hacia la base de datos
    ConexcionDB conexcionDB = new ConexcionDB();
    MenuDAO menuDAO = new MenuDAO(conexcionDB);
    ItemMenuDAO itemMenuDAO = new ItemMenuDAO(conexcionDB);

    public InterfazOctoberEats() {
        setTitle("Pedido");
        setSize(600, 568); // Tamaño de la ventana principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Crear el diálogo de inicio de sesión
        JDialog loginDialog = new JDialog(this, "Iniciar Sesión", true);
        loginDialog.setSize(600, 232); // Tamaño del diálogo de inicio de sesión
        loginDialog.setLayout(new GridLayout(3, 2));

        // Campos de texto para usuario y contraseña
        JTextField userField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        // Establecer tamaño preferido más pequeño para los campos de texto
        Dimension fieldSize = new Dimension(100, 25); // Ancho de 100 y altura de 25
        userField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);

        // Botones de iniciar y salir
        JButton loginButton = new JButton("Iniciar");
        JButton exitButton = new JButton("Salir");

        // Añadir componentes al diálogo
        loginDialog.add(new JLabel("Usuario:"));
        loginDialog.add(userField);
        loginDialog.add(new JLabel("Contraseña:"));
        loginDialog.add(passwordField);
        loginDialog.add(loginButton);
        loginDialog.add(exitButton);

        // Acción para el botón de iniciar sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                if (verifyCredentials(username, password)) {
                    JOptionPane.showMessageDialog(loginDialog, "Inicio de sesión exitoso");
                    loginDialog.dispose(); // Cerrar el diálogo al iniciar sesión
                } else {
                    JOptionPane.showMessageDialog(loginDialog, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para el botón de salir
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });

        // Mostrar el diálogo de inicio de sesión
        loginDialog.setLocationRelativeTo(this);
        loginDialog.setVisible(true);

        // Configuración de la interfaz de pedidos
        carrito = new Carrito();
        restauranteComboBox = new JComboBox<>();
        menuModel = new DefaultListModel<>();
        menuList = new JList<>(menuModel);
        addButton = new JButton("Agregar");
        removeButton = new JButton("Eliminar");
        viewCartButton = new JButton("Ver Carrito");
        proceedButton = new JButton("Proceder con el Pago");
        pedidoArea = new JTextArea();
        cantidadField = new JTextField("1", 5);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Seleccione Restaurante:"));
        topPanel.add(restauranteComboBox);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Cantidad:"));
        bottomPanel.add(cantidadField);
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(viewCartButton);
        bottomPanel.add(proceedButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(new JScrollPane(pedidoArea), BorderLayout.EAST);

        // Cargar restaurantes
        ConexcionDB conexcionDB = new ConexcionDB();
        RestauranteDAO restauranteDAO = new RestauranteDAO(conexcionDB);
        List<Restaurante> restaurantes = restauranteDAO.consultarRestaurantes();
        for (Restaurante restaurante : restaurantes) {
            restauranteComboBox.addItem(restaurante);
        }

        // Cargar menú cuando se selecciona un restaurante
        restauranteComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Restaurante selectedRestaurante = (Restaurante) restauranteComboBox.getSelectedItem();
                if (selectedRestaurante != null) {
                    loadMenu(selectedRestaurante.getIdRestaurante());
                }
            }
        });

        // Acción para agregar ítems al carrito
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemMenu selectedItem = menuList.getSelectedValue();
                if (selectedItem != null) {
                    try {
                        int cantidad = Integer.parseInt(cantidadField.getText());
                        if (cantidad >= 1) {
                            for (int i = 0; i < cantidad; i++) {
                                carrito.agregarPlato(selectedItem);
                            }
                            actualizarPedido();
                        } else {
                            JOptionPane.showMessageDialog(InterfazOctoberEats.this, "La cantidad mínima es 1", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(InterfazOctoberEats.this, "Ingrese una cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Acción para eliminar ítems del carrito
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carrito.vaciarCarrito();
                actualizarPedido();
            }
        });

        // Acción para ver el carrito
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarrito();
            }
        });

        // Acción para proceder con el pago
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procederConPago();
            }
        });
    }

    private void loadMenu(int restauranteId) {
        menuModel.clear();
        List<Menu> menus = menuDAO.getMenuDeRestaurante(restauranteId);
        for (Menu menu : menus) {
            List<ItemMenu> items = itemMenuDAO.getItemMenu(menu.getIdMenu());
            for (ItemMenu item : items) {
                menuModel.addElement(item);
            }
        }
    }

    private void actualizarPedido() {
        pedidoArea.setText("");
        for (ItemMenu item : carrito.getPlatos()) {
            pedidoArea.append(item.getNombre() + " - " + item.getPrecio() + "\n");
        }
        pedidoArea.append("\nTotal: " + carrito.calcularTotal());
    }

    private void mostrarCarrito() {
        StringBuilder carritoInfo = new StringBuilder("Carrito de Compras:\n");
        for (ItemMenu item : carrito.getPlatos()) {
            carritoInfo.append(item.getNombre()).append(" - ").append(item.getPrecio()).append("\n");
        }
        carritoInfo.append("\nTotal: ").append(carrito.calcularTotal());
        JOptionPane.showMessageDialog(this, carritoInfo.toString(), "Carrito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void procederConPago() {
        String[] opciones = {"Tarjeta", "Efectivo"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione el método de pago", "Método de Pago", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        if (seleccion != -1) {
            String metodoPago = opciones[seleccion];
            double total = carrito.calcularTotal();

            // Generar un tiempo de llegada al azar entre 10 y 30 minutos
            int tiempoLlegada = 10 + (int)(Math.random() * 21);

            // Generar el mensaje de confirmación usando el método centralizado
            String mensajeConfirmacion = generarMensajeConfirmacion(metodoPago, total, tiempoLlegada);

            JOptionPane.showMessageDialog(this, mensajeConfirmacion, "Confirmación de Pago", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String generarMensajeConfirmacion(String metodoPago, double total, int tiempoLlegada) {
        StringBuilder mensaje = new StringBuilder("Detalle de la Compra:\n");
        for (ItemMenu item : carrito.getPlatos()) {
            mensaje.append(item.getNombre()).append(" - ").append(item.getPrecio()).append("\n");
        }
        mensaje.append("\nTotal: ").append(total);
        mensaje.append("\nMétodo de Pago: ").append(metodoPago);
        mensaje.append("\nSu entrega será en: Universidad Fidélitas");
        mensaje.append("\nTiempo de llegada estimado: ").append(tiempoLlegada).append(" minutos");
        return mensaje.toString();
    }

    // Método para verificar las credenciales (ejemplo simple)
    private boolean verifyCredentials(String username, String password) {
        // Aquí puedes añadir la lógica para verificar el usuario y contraseña con la base de datos
        return "admin".equals(username) && "password".equals(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazOctoberEats frame = new InterfazOctoberEats();
            frame.setVisible(true);
        });
    }

    public static class Test extends JFrame {

        private JList<String> itemList;
        private DefaultTableModel tableModel;
        private JTable orderTable;
        private JLabel totalLabel;

        public Test() {
            setTitle("October Eats Order System");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 400);
            setLayout(new BorderLayout());

            // Panel izquierdo con JList
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("Item 1 - $10.00");
            listModel.addElement("Item 2 - $15.00");
            listModel.addElement("Item 3 - $7.50");
            itemList = new JList<>(listModel);
            add(new JScrollPane(itemList), BorderLayout.WEST);

            // Panel derecho con JTable
            String[] columnNames = {"Item", "Cantidad", "Precio Unitario", "Total"};
            tableModel = new DefaultTableModel(columnNames, 0);
            orderTable = new JTable(tableModel);
            add(new JScrollPane(orderTable), BorderLayout.CENTER);

            // Panel inferior con total y botones
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(2, 1));

            totalLabel = new JLabel("Total: $0.00");
            bottomPanel.add(totalLabel);

            JPanel buttonPanel = new JPanel();
            JButton addButton = new JButton("Agregar");
            JButton removeButton = new JButton("Eliminar");
            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            bottomPanel.add(buttonPanel);

            add(bottomPanel, BorderLayout.SOUTH);

            // Listeners para agregar y eliminar items
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agregarItem();
                }
            });

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminarItem();
                }
            });
        }

        private void agregarItem() {
            String selectedItem = itemList.getSelectedValue();
            if (selectedItem != null) {
                // Separar el nombre del item y el precio
                String[] itemParts = selectedItem.split(" - \\$");
                String itemName = itemParts[0];
                double itemPrice = Double.parseDouble(itemParts[1]);

                // Verificar si el item ya está en la tabla
                boolean itemFound = false;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 0).equals(itemName)) {
                        int cantidad = (int) tableModel.getValueAt(i, 1);
                        tableModel.setValueAt(cantidad + 1, i, 1);  // Incrementar cantidad
                        tableModel.setValueAt((cantidad + 1) * itemPrice, i, 3);  // Actualizar total
                        itemFound = true;
                        break;
                    }
                }

                // Si el item no estaba en la tabla, agregarlo
                if (!itemFound) {
                    tableModel.addRow(new Object[]{itemName, 1, itemPrice, itemPrice});
                }

                actualizarTotal();
            }
        }

        private void eliminarItem() {
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                actualizarTotal();
            }
        }

        private void actualizarTotal() {
            double total = 0;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                total += (double) tableModel.getValueAt(i, 3);
            }
            totalLabel.setText("Total: $" + String.format("%.2f", total));
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Test app = new Test();
                app.setVisible(true);
            });
        }
    }
}