package org.octoberEats.Main;

import org.octoberEats.UI.VentanaItemsMenu;
import org.octoberEats.UI.VentanaRegistro;


public class Main {
    public static void main(String[] args) {


        //new VentanaRegistro();
//        ConexcionDB conexcionDB = new ConexcionDB();
//        UsuarioDAO prueba1 = new UsuarioDAO(conexcionDB);
//        System.out.println(prueba1.insertarUsuario("prueba3", "789456"));

        //new ListaRestaurantes();

        //new InterfazOctoberEats();
        // EL Usuario para ingresar a la app es admin y la contraseña es password
        new VentanaItemsMenu();
    }
}