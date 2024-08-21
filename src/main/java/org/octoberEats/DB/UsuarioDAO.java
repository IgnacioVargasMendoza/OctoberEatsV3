package org.octoberEats.DB;

import org.octoberEats.Modelos.ItemMenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    ResultSet resultado = null;
    private ConexcionDB connectionDB;

    public UsuarioDAO(ConexcionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    public List<ItemMenu> getItemMenu(int idMenu) {
        List<ItemMenu> menus = new ArrayList<>();
        try {
            conexcionDB.setConexion();
            conexcionDB.setConsulta("SELECT * FROM itemsmenu WHERE idMenu = ?");
            conexcionDB.getConsulta().setInt(1, idMenu);
            resultado = conexcionDB.getResultado();

            while (resultado.next()) {
                int idItem = resultado.getInt("idItem");
                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio");
                String descripcion = resultado.getString("descripcion");
                menus.add(new ItemMenu(idItem,nombre, precio, descripcion, idMenu));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexcionDB.cerrarConexion();
        }
        return menus;
    }


}
