package org.octoberEats.Modelos;

public class ItemMenu {

    private int idItem;
    private String nombre;
    private double precio;
    private String descripcion;
    private int idMenu;

    public ItemMenu(int idItem, String nombre, double precio, String descripcion, int idMenu){
        this.idItem = idItem;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idMenu = idMenu;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    //Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
