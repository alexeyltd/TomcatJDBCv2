package services.implementations.propertiesHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private final Properties properties = new Properties();
    private  InputStream inputStream = null;

    private static PropertiesHelper propertiesHelper;

    public static PropertiesHelper getInstance() {

        if (propertiesHelper == null) {
            propertiesHelper = new PropertiesHelper();
        }

        return propertiesHelper;
    }

    private PropertiesHelper() {

    }

    public String getPropertiesType() {

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            return properties.getProperty("type");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


}
