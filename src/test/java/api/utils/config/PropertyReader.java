package api.utils.config;

import api.utils.logging.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;

    public Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            InputStream configFile = null;
            try {
                System.setProperty("profile","zetta");
                String env = System.getProperty("profile");
                String configFilePath = "apiConfig/config/" + env + ".properties";
                System.out.println("Attempting to load properties file: " + configFilePath); // Логиране на пътя до конфигурационния файл
                configFile = getClass().getClassLoader().getResourceAsStream(configFilePath);
                if (configFile != null) {
                    properties.load(configFile);
                    System.out.println("Loaded properties file: " + configFilePath); // Логиране при успешно зареждане
                } else {
                    System.out.println("Property file '" + configFilePath + "' not found in the classpath"); // Логиране при неуспешно зареждане
                    throw new RuntimeException("Property file '" + configFilePath + "' not found in the classpath");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
