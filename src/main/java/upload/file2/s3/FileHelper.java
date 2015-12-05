/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload.file2.s3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ranjeet
 */
public class FileHelper {

    private final Logger log = LoggerFactory.getLogger(FileHelper.class);

    public String getProperties4Key(String key) {

        Properties p = new Properties();
        String propFile = "config.properties";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFile);
        try {
            p.load(inputStream);
            return p.getProperty(key);
        }
        catch (IOException ex) {
            log.error(LogKey.EXCEPTION, ex);
        }
        return "";
    }

    public Properties getProperties(String propFile) {

        Properties p = new Properties();
        //String propFile = "config.properties";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFile);
        try {
            p.load(inputStream);
            return p;
        }
        catch (IOException ex) {
            log.error(LogKey.EXCEPTION, ex);
        }
        return null;
    }
}
