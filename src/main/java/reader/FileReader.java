package reader;

import exception.EmptyFileException;
import exception.NoSuchFileException;
import validator.FileValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    private static final String lineSeparator = "\n";
    private static FileReader ourInstance = new FileReader();

    private FileReader() {
    }

    public static FileReader getInstance() {
        return ourInstance;
    }

    public String readFromFile(String path) throws NoSuchFileException, EmptyFileException, IOException {
        FileValidator validator = FileValidator.getInstance();
        if (!validator.isExist(path)) {
            throw new NoSuchFileException("There is no such file");
        }
        if (isEmpty(path)) {
            throw new EmptyFileException("The file is empty");
        }

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            stream.forEach(s -> stringBuilder.append(s + lineSeparator));
            return stringBuilder.toString();
        }
    }

    private boolean isEmpty(String path) {

        File file = new File(path);

        if (file.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
