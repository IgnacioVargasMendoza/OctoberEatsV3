package org.octoberEats.DB;

import org.octoberEats.Modelos.ItemMenu;
import org.octoberEats.Modelos.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    ResultSet resultado = null;
    private ConexcionDB conexcionDB;

    public UsuarioDAO(ConexcionDB connectionDB) {
        this.conexcionDB = connectionDB;
    }

    public String insertarUsuario(String nom, String email, String cont, String dir)
    {
        try
        {
            conexcionDB.setConexion();
            conexcionDB.setConsulta("Insert into usuarios(nombre, email, password, direccion) values(?,?,?,?)");
            conexcionDB.getConsulta().setString(1, nom);
            conexcionDB.getConsulta().setString(2, email);
            conexcionDB.getConsulta().setString(3, cont);
            conexcionDB.getConsulta().setString(4, dir);

            return conexcionDB.getConsulta().executeUpdate() > 0 ? "Nuevo Usuario registrado":"Error en el rgistro!";
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }
        return null;
    }


    public Usuario consultarPorNombre(String nom)
    {
        try
        {
            conexcionDB.setConexion();
            conexcionDB.setConsulta(String.format("SELECT idUsuario, nombre, email, direccion, contraseña FROM usuarios WHERE nombre = '%s'",nom));
            resultado = conexcionDB.getResultado();
            Usuario usuario = new Usuario();
            while(resultado.next())
            {
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setDireccion(resultado.getString("direccion"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setContrasena(resultado.getString("contraseña"));
            }

            conexcionDB.cerrarConexion();

            return usuario;
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }

        return null;
    }


}
