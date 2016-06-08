package ua.greencampus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Arsenii on 15.05.2016.
 */
@Service
public class GlobalVar {
    @Value("${path.file}")
    private String pathToFile;

}
