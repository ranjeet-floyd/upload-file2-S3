package upload.file2.s3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author ranjeet
 */
public class S3Uploader {

    private static final Logger log = LoggerFactory.getLogger(S3Uploader.class);
    final String access_key_id = "XXXXXXXXXXXXXX";
    final String secret_access_key = "XXXXXXXXXXX";
    private final String folderName = "XXXXXX";
    private final String bucketName = "XXXXXX";
    // get s3client
    private final AwsHelper awsHelper = new AwsHelper();

    public void start(String uploadDir) throws IOException {
        this.walkDirectory(uploadDir);
    }

    private void walkDirectory(String path) throws IOException {

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) {
            log.warn(LogKey.MESSAGE, "File Directory is Empty");
            return;
        }

        for (File f : list) {
            if (f.isDirectory()) {
                walkDirectory(f.getAbsolutePath());
                log.debug(LogKey.DIRECTORY, f.getAbsoluteFile());
            } else {
                String filePath = f.getAbsolutePath();
                String fileName = f.getName();
                log.debug(LogKey.FILE_NAME, fileName);
                log.debug(LogKey.DIRECTORY, filePath);

                String text = getFileText(filePath);
                log.trace(LogKey.TEXT, text);

                awsHelper.uploadToS3(fileName, text);
                log.info(LogKey.MESSAGE, "Uploaded!!");
            }
        }
    }

    private String getFileText(String file) throws IOException {
        BufferedReader br = null;
        String text = "";
        try {
            br = new BufferedReader(new FileReader(file));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
            log.trace(LogKey.TEXT, text);
        }
        catch (Exception ex) {
            log.error(LogKey.EXCEPTION, ex);
        }
        finally {
            br.close();
        }
        return text;
    }

}
