import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    //чтение

    public static String fileReader(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    //запись

    public static void fileWriter (String filePath, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        }
    }
    //exist.
    public static boolean exists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
}
