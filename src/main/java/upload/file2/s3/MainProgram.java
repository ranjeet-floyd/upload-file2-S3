/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload.file2.s3;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ranjeet
 */
public class MainProgram {

    private static final Logger log = LoggerFactory.getLogger(MainProgram.class);

    public static void main(String[] args) throws IOException {
        log.info(LogKey.MESSAGE, "going to  Upload");

        //Folder files to upload on S3.
        String upload_folder = new FileHelper().getProperties4Key("UPLOAD_FOLDER");
        if (args.length > 0) {
            upload_folder = args[0].trim();
        }
        log.info(LogKey.DIRECTORY, upload_folder);
        log.info("***********************************************************");

        log.info(LogKey.MESSAGE, "Start Uploading");
        new S3Uploader().start(upload_folder);
    }
}
