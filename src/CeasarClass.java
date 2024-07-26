import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CeasarClass {

    // Алфавит
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ";

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int index = ALPHABET.indexOf(ch);
            if (index != -1) {
                int newIndex = (index + shift) % ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, ALPHABET.length() - shift);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Выбор режима
        System.out.print("Выберите режим и введите его (encrypt/decrypt): ");
        String mode = scanner.nextLine().trim();

        // Ввод пути к входному файлу.
        System.out.print("Необходимо ввести путь к входному файлу: ");
        String inputFile = scanner.nextLine().trim();

        // Проверка существования файла.
        if (!Files.exists(Paths.get(inputFile))) {
            System.out.println("Входного файла нет.");
            return;
        }

        // Запрос пути к выходному файлу
        System.out.print("Необходимо ввести путь к выходному файлу: ");
        String outputFile = scanner.nextLine().trim();

        // Запрос значения для смещения
        System.out.print("Необходимо ввести значение сдвига (0-" + (ALPHABET.length() - 1) + "): ");
        int shift;
        try {
            shift = Integer.parseInt(scanner.nextLine().trim());
            if (shift < 0 || shift >= ALPHABET.length()) {
                System.out.println("Для указания значения сдвига должно быть целое число от 0 до " + (ALPHABET.length() - 1) + ".");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Для указания значения сдвига напишите целое число.");
            return;
        }

        // Чтение входного файла:
        StringBuilder inputText = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputText.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Во время чтения файла произошла ошибка: " + e.getMessage());
            return;
        }

        //Шифрование + дешифрование:
        String outputText;
        if (mode.equalsIgnoreCase("encrypt")) {
            outputText = encrypt(inputText.toString(), shift);
        } else if (mode.equalsIgnoreCase("decrypt")) {
            outputText = decrypt(inputText.toString(), shift);
        } else {
            System.out.println("Ошибка. Пожалуйста, напишите 'encrypt' или 'decrypt'.");
            return;
        }

        // Запись выходного файла:
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write(outputText);
        } catch (IOException e) {
            System.out.println("Во время записи файла произошла ошибка: " + e.getMessage());
        }

        System.out.println("Завершено успешно.");
    }
}