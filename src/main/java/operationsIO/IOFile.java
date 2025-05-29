package operationsIO;


import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOFile {

    public static boolean saveOnFile(String filename, JSONObject object) {
        try {
            Path dirPath = Paths.get("tables");
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Path filePath = dirPath.resolve(filename);
            try (FileWriter file = new FileWriter(filePath.toFile())) {
                file.write(object.toString(4)); // indentazione JSON leggibile
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject readFromFile(String filename) {
        try {
            Path filePath = Paths.get("tables", filename);
            String content = Files.readString(filePath);
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
