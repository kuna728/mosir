package pl.edu.wat.student.i9g1s1.mosir.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class StaticFilesService {

    @Value("${mosir.static-files-path}")
    private String staticFilesPath;

    @Value("${mosir.static-files-url}")
    private String staticFilesUrl;
    public byte[] getImage(String entityName, String fileName) throws IOException {
        String path = staticFilesPath + "/images/" + entityName + "/" + fileName + ".jpg";
        FileInputStream in = new FileInputStream(path);
        return IOUtils.toByteArray(in);
    }

    public String getImageUrl(String entityName, String fileName) {
        return staticFilesUrl + "/images/" + entityName + "/" + fileName;
    }

}
