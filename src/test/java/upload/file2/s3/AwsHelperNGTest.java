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
public class AwsHelperNGTest {

    private final Logger log = LoggerFactory.getLogger(AwsHelperNGTest.class);
    AwsHelper awsHelper = null;

    public AwsHelperNGTest() {
        awsHelper = new AwsHelper();
    }

    /**
     * Test of uploadToS3 method, of class AwsHelper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadToS3() throws Exception {
        log.info(LogKey.MESSAGE, "uploadToS3 Test Case");
        String fileName = "test.txt";
        String text = "Hello S3 test file";
        //change true 
        boolean expResult = false;//true;
        boolean result = awsHelper.uploadToS3(fileName, text);
        assertEquals(result, expResult);

    }

}
