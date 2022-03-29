package Ovsyannikov;

/**
 * Класс для проверки является ли строка числом
 */
public class Numberis {
    /**
     * Функция проверки является ли строка числом
     * @param str проверяемая строка
     * @return результат проверки(true||false)
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
