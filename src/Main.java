import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Выбор режима
        // Ввод пути к входному файлу
        // Проверка наличия файла
        // Ввести путь к выходному файлу:

        System.out.print("Пожалуйста, выберите и введите режим (encrypt/decrypt): ");
        String mode = scanner.nextLine().trim();


        System.out.print("Необходимо ввести путь к входному файлу: ");
        String inputFile = scanner.nextLine().trim();


        if (!FileHandler.exists(inputFile)) {
            System.out.println("Входной файл отсутствует.");
            return;
        }


        System.out.print("Необходимо ввести путь к выходному файлу: ");
        String outputFile = scanner.nextLine().trim();

        // Значение сдвига
        System.out.print("Необходимо ввести значение сдвига (0-" + (CaesarCipher.ALPHABET.length() - 1) + "): ");
        int shift;
        try {
            shift = Integer.parseInt(scanner.nextLine().trim());
            if (shift < 0 || shift >= CaesarCipher.ALPHABET.length()) {
                System.out.println("Для значения сдвига введите целое число от 0 до " + (CaesarCipher.ALPHABET.length() - 1) + ".");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Для указания сдвига нужно ввести целое число");
            return;
        }

        // Чтение файла
        String inputText;
        try {
            inputText = FileHandler.fileReader(inputFile);
        } catch (IOException e) {
            System.out.println("Ошибка во время чтения: " + e.getMessage());
            return;
        }

        // Шифровка и расшифровка
        String outputText;
        if (mode.equalsIgnoreCase ("encrypt")) {
            outputText = CaesarCipher.encrypt(inputText, shift);
        } else if (mode.equalsIgnoreCase("decrypt")) {
            outputText = CaesarCipher.decrypt(inputText, shift);
        } else {
            System.out.println("Просьба выбрать и написать 'encrypt' или 'decrypt'.");
            return;
        }

        // Запись выходного файла.
        try {
            FileHandler.fileWriter (outputFile, outputText);
        } catch (IOException e) {
            System.out.println("Ошибка во время записи: " + e.getMessage());
        }

        System.out.println("Завершено успешно.");
    }
}