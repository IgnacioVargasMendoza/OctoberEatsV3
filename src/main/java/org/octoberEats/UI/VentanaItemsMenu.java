package org.octoberEats.UI;

import javax.swing.*;

public class VentanaItemsMenu extends JFrame{
    private JPanel mainPanel;
    private JList listaItemsMenu;
    private JButton agregarItemsbtn;
    private JButton verCarritobtn;
    private JComboBox comboBoxMenus;
    private JLabel labelNombreRestaurante;
    private JButton volverbtn;

    public VentanaItemsMenu() {
        setContentPane(mainPanel);
        setTitle("Items - Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 568);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
