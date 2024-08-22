package org.octoberEats.DB;

import org.octoberEats.Modelos.Menu;
import org.octoberEats.Modelos.Restaurante;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

    ResultSet resultado = null;
    private ConexcionDB conexcionDB;

    //Constructor
    public RestauranteDAO(ConexcionDB connectionDB) {
        this.conexcionDB = connectionDB;
    }

    //Consulta los restaurante de la base Datos

    public List<Restaurante> consultarRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();
        try {
            conexcionDB.setConexion();
            conexcionDB.setConsulta("SELECT * FROM restaurantes");
            resultado = conexcionDB.getResultado();

            while (resultado.next()) {
                int idRestaurant = resultado.getInt("idRestaurant");
                String nombre = resultado.getString("nombre");
                String direccion = resultado.getString("direccion");
                restaurantes.add(new Restaurante(idRestaurant, nombre, direccion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexcionDB.cerrarConexion();
        }
        return restaurantes;
    }

    public Restaurante consultarRestaurantePorNombre(String nombreRestaurante) {
        Restaurante restaurante = null;
        try {
            conexcionDB.setConexion();
            conexcionDB.setConsulta("SELECT idRestaurante, nombre, direccion FROM restaurantes \n" +
                    "WHERE nombre = ?");
            conexcionDB.getConsulta().setString(1, nombreRestaurante);
            resultado = conexcionDB.getResultado();

            if (resultado.next()) {// en caso de que solo devuelva un resgistro, esta logica solo fuciona en caso de que la base de datos no tenga valores duplicados en la columna nombre
                int idRestaurante = resultado.getInt("idRestaurante");
                String nombre = resultado.getString("nombre");
                String direccion = resultado.getString("direccion");

                restaurante = new Restaurante(idRestaurante, nombre, direccion);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar el restaurante: " + e.getMessage());
        } finally {
            conexcionDB.cerrarConexion();
        }
        return restaurante;
    }


}

