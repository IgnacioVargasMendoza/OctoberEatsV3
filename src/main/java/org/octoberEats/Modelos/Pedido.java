package org.octoberEats.Modelos;

public class Pedido extends Thread {
    private int idPedido;
    private Usuario usuario;
    private Restaurante restaurante;
    private Carrito carrito;
    private String estado;  // Ejemplo: "Pendiente", "En preparación", "En camino", "Entregado"

    public Pedido(int idPedido, Usuario usuario, Restaurante restaurante, Carrito carrito) {
        this.idPedido = idPedido;
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.carrito = carrito;
        this.estado = "Pendiente";  // Estado inicial
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        try {
            // Simulación del proceso del pedido
            System.out.println("Pedido " + idPedido + " para el usuario " + usuario.getNombre() + " está en proceso.");

            // Simulamos tiempo de preparación
            Thread.sleep(5000);
            setEstado("En preparación");
            System.out.println("Pedido " + idPedido + " está en preparación.");

            // Simulamos tiempo de entrega
            Thread.sleep(5000);
            setEstado("En camino");
            System.out.println("Pedido " + idPedido + " está en camino.");

            // Simulamos entrega
            Thread.sleep(5000);
            setEstado("Entregado");
            System.out.println("Pedido " + idPedido + " ha sido entregado.");
        } catch (InterruptedException e) {
            System.out.println("El pedido " + idPedido + " fue interrumpido.");
        }
    }
}


