package org.octoberEats.Modelos;

import java.util.List;

public class Restaurante {

    private int idRestaurante;
    private String nombre;
    private String direccion;//aqui puedo gestionar el estimate arrival para los hilos de los pedidos


    public Restaurante(int idRestaurante, String nombre, String direccion){
        this.idRestaurante = idRestaurante;
        this.nombre = nombre;
        this.direccion = direccion;
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

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
}
