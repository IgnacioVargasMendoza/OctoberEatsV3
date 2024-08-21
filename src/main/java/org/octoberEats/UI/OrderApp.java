package org.octoberEats.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderApp extends JFrame {

    private JList<String> itemList;
    private DefaultTableModel tableModel;
    private JTable orderTable;
    private JLabel totalLabel;

    public OrderApp() {
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
            OrderApp app = new OrderApp();
            app.setVisible(true);
        });
    }
}

