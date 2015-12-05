/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload.file2.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class S3UploaderNGTest {

    private final Logger log = LoggerFactory.getLogger(S3UploaderNGTest.class);

    public S3UploaderNGTest() {
    }

    /**
     * Test of start method, of class S3Uploader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStart() throws Exception {
        log.info(LogKey.MESSAGE, "Start");
        //test upload with config file
        String fileName = "config.properties";
        String uploadDir = S3Uploader.class.getClassLoader().getResource(fileName).getPath();
        S3Uploader instance = new S3Uploader();
        instance.start(uploadDir);
        assertEquals(true, true);
    }

}
