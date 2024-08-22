package org.octoberEats.UI;

import org.octoberEats.DB.ConexcionDB;
import org.octoberEats.DB.UsuarioDAO;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame{

    private JPanel mainPanel;
    private JLabel JLabelUserName;
    private JTextField userNameField;
    private JLabel JLabelPassword;
    private JPasswordField passwordField;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private JTextField direccionTxtField;
    private JLabel jlabelDireccion;
    private JTextField emailTxtField;
    private JLabel jlabelEmail;
    ConexcionDB conexcionDB = new ConexcionDB();
    UsuarioDAO usuarioDAO = new UsuarioDAO(conexcionDB);

    public VentanaRegistro() {

        setContentPane(mainPanel);
        setTitle("Octover Eats - Registro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 568);
        setLocationRelativeTo(null);
        setVisible(true);


        userNameField.setBorder(new MatteBorder(0, 0,1,0, Color.LIGHT_GRAY));
        passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        direccionTxtField.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        emailTxtField.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));


        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = userNameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                String dir = direccionTxtField.getText();
                String email = emailTxtField.getText();
                String mensaje = usuarioDAO.insertarUsuario(nombre,email,password,dir);
                JOptionPane.showMessageDialog(null, mensaje);

                /*
                 * si el registro es exitoso, habilito la ventana para elegir restaurantes
                 * sino,
                 **/

                if(mensaje != null){
                    new ListaRestaurantes();
                    setVisible(false);
                }


            }
        });

    }



}
