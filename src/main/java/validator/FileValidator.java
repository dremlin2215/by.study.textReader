package validator;

import java.io.File;

public class FileValidator {
    private static FileValidator ourInstance = new FileValidator();

    private FileValidator() {
    }

    public static FileValidator getInstance() {
        return ourInstance;
    }
    
    public boolean isExist(String str) {
        File f = new File(str);
        return (f.exists() && !f.isDirectory());
    }
}
