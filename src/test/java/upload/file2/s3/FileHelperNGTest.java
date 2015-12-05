/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload.file2.s3;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.assertNotEquals;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class FileHelperNGTest {

    private final Logger log = LoggerFactory.getLogger(FileHelperNGTest.class);

    public FileHelperNGTest() {
    }

    /**
     * Test of getProperties4Key method, of class FileHelper.
     */
    @Test
    public void testGetProperties() {
        log.info(LogKey.MESSAGE, "getProperties");
        String fileName = "config.properties";
        FileHelper instance = new FileHelper();
        Properties result = instance.getProperties(fileName);
        assertNotEquals(result, null);
    }

    /**
     * Test of getProperties4Key method, of class FileHelper.
     */
    @Test
    public void testGetProperties4Key() {
        System.out.println("getProperties4Key");
        String key = "AWS_BUCKET_NAME";
        FileHelper instance = new FileHelper();
        String result = instance.getProperties4Key(key);
        assertNotEquals(result, null);

    }

}
