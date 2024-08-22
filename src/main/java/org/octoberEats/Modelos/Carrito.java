package org.octoberEats.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private List<ItemMenu> itemMenus;

    public Carrito() {
        itemMenus = new ArrayList<>();
    }

    public void agregarPlato(ItemMenu itemMenu) {
        itemMenus.add(itemMenu);
    }

    public List<ItemMenu> getPlatos() {
        return itemMenus;
    }

    public void vaciarCarrito() {
        itemMenus.clear();
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemMenu item : itemMenus) {
            total += item.getPrecio();
        }
        return total;
    }
}
