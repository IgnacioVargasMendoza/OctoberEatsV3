package org.octoberEats.DB;

import org.octoberEats.Modelos.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    ConexcionDB conexcionDB;
    ResultSet resultado;

    /*
    * Metodo para obtener todos los datos de la tabla menu relacionada con la tabla restaurante
    * Establece la conexion con la base de datos, y el resultado lo devuelvo en una lista con
    * cada menu realacionado al id del restaurante
    * */

    public List<Menu> getMenuDeRestaurante(int idRestaurante) {

        List<Menu> menus = new ArrayList<>();//crea un arreglo para almacenar el resultado de la busqueda
        try {
            conexcionDB.setConexion();//establece conexion con october_eats
            conexcionDB.setConsulta("SELECT * FROM menus WHERE idRestaurante = ?");//Selecciona los datos que sean iguales al id del Restaurante
            conexcionDB.getConsulta().setInt(1, idRestaurante);
            resultado = conexcionDB.getResultado();//obtiene el resultado de la busqueda

            while (resultado.next()) {//mientras haya un resultado
                int idMenu = resultado.getInt("idMenu");
                String nombre = resultado.getString("nombre");
                menus.add(new Menu(idMenu,nombre, idRestaurante));//agrega un resultado a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexcionDB.cerrarConexion();
        }
        return menus;//devuelve las lista de menus
    }
}
