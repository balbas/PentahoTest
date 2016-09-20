package jmb.pentahotest.backend.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmbalbas
 */
public class PropertiesFileManager {
    private PropertiesFileManager() {        
        this.properties = new Properties();
        try {
            this.properties.load(new FileInputStream(url));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Implementando Singleton
     * @return
     */
    public static PropertiesFileManager getInstance() {
        return PropertiesFilesHolder.INSTANCE;
    }
 
    private static class PropertiesFilesHolder { 
        private static final PropertiesFileManager INSTANCE = new PropertiesFileManager();
    }
 
    /**
     * Retorna la propiedad de parametrización solicitada
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
    
    /**
     * Nuevo valor a la propiedad de parametrización solicitada.
     * @param key
     * @param value
     */    
    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);        
        try {
             this.properties.store(new FileOutputStream(new File(url)), null);
        } catch(FileNotFoundException ex) {
            Logger.getLogger(PropertiesFileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex) {
            Logger.getLogger(PropertiesFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Properties properties;
    public final static String PROPERTIES_FILE_NAME = "database.properties";
    public final static String BBDD_TYPE = "TypeDataBase";
    public final static String BBDD_URL = "UrlDataBase";
    public final static String BBDD_USER = "UserDataBase";
    public final static String BBDD_PASSWORD = "PassDataBase";
    // Ruta de acceso al fichero properties    
    private final static String url = System.getProperty("user.dir") + File.separator + PROPERTIES_FILE_NAME;
}
