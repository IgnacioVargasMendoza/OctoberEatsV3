package org.octoberEats.UI;

import org.octoberEats.DB.ConexcionDB;
import org.octoberEats.DB.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OctoverEats extends JFrame{

    private JPanel mainPanel;
    private JLabel JLabelUserName;
    private JTextField userNameField;
    private JLabel JLabelPassword;
    private JPasswordField PasswordField;
    private JButton btnRegistrar;

    public OctoverEats() {

        setContentPane(mainPanel);
        setTitle("Octover Eats - Registro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 568);
        setLocationRelativeTo(null);
        setVisible(true);

        ConexcionDB conexcionDB = new ConexcionDB();


        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = userNameField.getText();
                char[] passwordChars = PasswordField.getPassword();
                String password = new String(passwordChars);

                UsuarioDAO usuarioDAO = new UsuarioDAO(conexcionDB);
                String mensaje = usuarioDAO.insertarUsuario(nombre, password);
                JOptionPane.showMessageDialog(null, mensaje);

                /*
                 * si el registro es exitoso, habilito la ventana para elegir restaurantes
                 * sino,
                 **/

                if(mensaje != null){
                    new ListaRestaurantes();
                } else {

                }
                setVisible(false);

            }
        });

    }



}
