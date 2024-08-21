package org.octoberEats.UI;

import javax.swing.*;
import java.awt.*;

public class ListaRestaurantes extends JFrame{

    private JPanel panel1;
    private JButton button1;
    private JList list1;

    public ListaRestaurantes() {
        setTitle("Octover Eats - Lista de Restaurantes");
        setSize(320, 568);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ejemplo de datos para la lista
        String[] restaurantes = {"Restaurante A", "Restaurante B", "Restaurante C", "Restaurante D"};

        // Crear el JList con los nombres de los restaurantes
        JList<String> listaRestaurantes = new JList<>(restaurantes);
        listaRestaurantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        // Hacer visible el JFrame
        setVisible(true);
    }
}

