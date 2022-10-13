import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadData {
    private String path;
    public ReadData(String path) {
        this.path = path;
        readFileContentsOrNull(path);
    }

    private String readFileContentsOrNull(String path) {
        try {
            System.out.println(Files.readString(Path.of(path)));
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
