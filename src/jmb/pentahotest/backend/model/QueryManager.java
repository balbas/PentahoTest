package jmb.pentahotest.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class QueryManager {
    
    public QueryManager() {}
    
    public ResultSet execute(String query) {
        try {
            // Preparamos la conexión
            connection = new Connection();
            connection.connect();
            statement = connection.getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean insertOrUpdate(String query) {
        try {
            // Preparamos la conexión
            connection = new Connection();
            connection.connect();
            statement = connection.getConnection().createStatement();
            statement.executeUpdate(query);
            statementClose();
            //commit();
            connectionClose();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void statementClose() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void connectionClose() {
        if (connection != null) {
            connection.close();
            connection = null;
        } 
    }
    
    public void commit() {
        if (connection.getConnection() != null) {
            try {                
                connection.getConnection().commit();
            } catch (SQLException ex) {
                Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);                
            } 
        }
    }
    
    private Connection connection = null;
    private Statement statement = null;
}

