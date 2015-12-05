package upload.file2.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ranjeet
 *
 */
public class AwsHelper {
    
    private final Logger logger = LoggerFactory.getLogger(AwsHelper.class);
//    private final String access_key_id;
//    private final String secret_access_key;
    private final Properties p;
    private AmazonS3 s3client;
    
    public AwsHelper() {
        FileHelper fileHelper = new FileHelper();
        this.p = new FileHelper().getProperties("config.properties");
        this.s3client = this.getS3Client();
    }
    
    private AmazonS3 getS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(
                this.p.getProperty("AWS_ACCESS_KEY_ID"), 
                this.p.getProperty("AWS_SECRET_ACCESS_KEY"));
        logger.debug(LogKey.MESSAGE, "Get S3 client !!");
        return new AmazonS3Client(awsCreds);
    }
    
    public boolean uploadToS3(String fileName, String text ) throws IOException {
        
        try {
            String folderName = this.p.getProperty("KEY_NAME");
            String bucketName = this.p.getProperty("AWS_BUCKET_NAME");
            logger.info(LogKey.MESSAGE, "Uploading a new object to S3 from a file");
            
            byte[] contentAsBytes = text.getBytes("UTF-8");
            ByteArrayInputStream contentsAsStream = new ByteArrayInputStream(contentAsBytes);
            ObjectMetadata md = new ObjectMetadata();
            md.setContentLength(contentAsBytes.length);
            
            if (this.s3client == null) {
                logger.debug(LogKey.MESSAGE, "reuse S3 client !!");
                this.s3client = this.getS3Client();
            }
            
            logger.info(LogKey.BUCKET_NAME, bucketName);
            logger.info(LogKey.KEY_NAME, folderName);
            logger.info(LogKey.FILE_NAME, fileName);
            
            this.s3client.putObject(new PutObjectRequest(
                    bucketName, folderName + File.separator + fileName, contentsAsStream, md));
            
            return true;
            
        }
        catch (AmazonServiceException ase) {
            logger.error(LogKey.EXCEPTION, ase);
            logger.error(LogKey.EXCEPTION, "Caught an AmazonServiceException, which "
                    + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response"
                    + " for some reason.");
            logger.error(LogKey.EXCEPTION, "Error Message:    " + ase.getMessage());
            logger.error(LogKey.EXCEPTION, "HTTP Status Code: " + ase.getStatusCode());
            logger.error(LogKey.EXCEPTION, "AWS Error Code:   " + ase.getErrorCode());
            logger.error(LogKey.EXCEPTION, "Error Type:       " + ase.getErrorType());
            logger.error(LogKey.EXCEPTION, "Request ID:       " + ase.getRequestId());
        }
        catch (AmazonClientException ace) {
            logger.error(LogKey.EXCEPTION, "Caught an AmazonClientException, which "
                    + "means the client encountered "
                    + "an internal error while trying to "
                    + "communicate with S3, "
                    + "such as not being able to access the network.");
            logger.error(LogKey.EXCEPTION, "Error Message: " + ace.getMessage());
        }
        return false;
    }
}
