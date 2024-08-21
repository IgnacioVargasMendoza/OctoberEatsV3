package org.octoberEats.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private int idMenu;
    private String nombre;
    private int idRestaurante;


    public Menu(int idMenu, String nombre, int idRestaurante){
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.idRestaurante = idRestaurante;
    }

    //Getter and Setter
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
}
