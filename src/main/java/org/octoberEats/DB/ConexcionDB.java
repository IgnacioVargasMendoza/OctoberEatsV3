package org.octoberEats.DB;

import java.sql.*;

public class ConexcionDB {

    Connection connection = null;
    PreparedStatement consulta = null;
    ResultSet resultado = null;
    String url = "jdbc:mysql://localhost:3306/october_eats";
    String username = "root";
    String password = "Nhvm3197@";

    public void setConexion()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConsulta(String consulta)
    {
        try
        {
            this.consulta = connection.prepareStatement(consulta);
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }
    }

    public ResultSet getResultado()
    {
        try
        {
            return consulta.executeQuery();
        }
        catch(SQLException error)
        {
            error.printStackTrace();
            return null;
        }
    }

    public PreparedStatement getConsulta()
    {
        return consulta;
    }

    public void cerrarConexion()
    {
        //Cerramos y limpiamos los resultados
        if(resultado != null)
        {
            try
            {
                resultado.close();
            }
            catch(SQLException error)
            {
                error.printStackTrace();
            }
        }
        //Cerramos y limpiamos las consultas
        if(consulta != null)
        {
            try
            {
                consulta.close();
            }
            catch(SQLException error)
            {
                error.printStackTrace();
            }
        }
        //Cerramos y limpiamos la conexión
        if(connection != null)
        {
            try
            {
                connection.close();
            }
            catch(SQLException error)
            {
                error.printStackTrace();
            }
        }
    }
}
