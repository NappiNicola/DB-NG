package operationsIO;


import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOFile {

    public static boolean saveOnFile(String filename, JSONObject object) {
        try (FileWriter file = new FileWriter(filename)) {
            file.write(object.toString(4)); // 4 = indentazione per formattazione leggibile
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject readFromFile(String filename) {
        try {
            String content = Files.readString(Paths.get(filename));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
