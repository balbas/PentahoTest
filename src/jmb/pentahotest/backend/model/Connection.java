package jmb.pentahotest.backend.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.pentahotest.backend.controller.PropertiesFileManager;

/**
 *
 * @author jmbalbas
 */
public class Connection {

    public Connection() {
        url = PropertiesFileManager.getInstance().getProperty(PropertiesFileManager.BBDD_URL);
        user = PropertiesFileManager.getInstance().getProperty(PropertiesFileManager.BBDD_USER);
        password = PropertiesFileManager.getInstance().getProperty(PropertiesFileManager.BBDD_PASSWORD);
    }

    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:" + PropertiesFileManager.getInstance().getProperty(PropertiesFileManager.BBDD_TYPE).toLowerCase() + ":" + url, user, password);
            if (connect != null) {
                //connect.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public java.sql.Connection getConnection() {
        return connect;
    }
     
    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private final String url;
    private final String user;
    private final String password;
    private java.sql.Connection connect;
}