package org.octoberEats.Modelos;

import java.util.List;

public class Restaurante {

    private int id_restaurante;
    private String nombre;
    private String direccion;//aqui puedo gestionar el estimate arrival para los hilos de los pedidos
    private List<Menu> menu;

    public Restaurante(int id_restaurante, String nombre, String direccion, List<Menu> menu){
        this.id_restaurante = id_restaurante;
        this.nombre = nombre;
        this.direccion = direccion;
        this.menu = menu;
    }


    //Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }
}
