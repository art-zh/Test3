public class CoderDecoder {
    //Класс, реализующий функциональность шифра Цезаря и дешифровки
    String outputText;


    public static void main(String[] args) {
    int sheft = 11;
        char base = 'A';
        char ch = 'v';
        ch = (char) ((ch - base + sheft) % 26 + base);
        System.out.println(ch);
    }

}
