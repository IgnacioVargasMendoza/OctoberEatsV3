package org.octoberEats.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

        ResultSet resultado = null;
        private ConexcionDB connectionDB;

        //Constructor
        public RestauranteDAO(ConexcionDB connectionDB) {
            this.connectionDB = connectionDB;
        }

        //Consulta los restaurante de la base Datos
        public void consultarRestaurantes() {

            try {
                connectionDB.setConexion();
                connectionDB.setConsulta("SELECT idRestaurant, nombre, direccion FROM restaurant");
                resultado = connectionDB.getResultado();

                while (resultado.next()) {
                    int idRestaurant = resultado.getInt("idRestaurant");
                    String nombre = resultado.getString("nombre");
                    String direccion = resultado.getString("direccion");

                    System.out.println("ID: " + idRestaurant + " - Nombre: " + nombre + " - Dirección: " + direccion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionDB.cerrarConexion();
            }
        }


}

