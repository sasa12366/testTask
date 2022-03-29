package Ovsyannikov;

/**
 * Класс для работы с колонкой
 */
public class AiroportsColumn implements Comparable<AiroportsColumn>{
    /**
     * строка колонки
     */
    String data;
    /**
     * поле для автоматической индексации строк
     */
    static int number=0;
    /**
     * номер строки в исходном файле
     */
    int numberString;

    public AiroportsColumn(String data) {
        this.data = data;
        this.numberString=number;
        number++;
    }
    public AiroportsColumn(String data,int number) {
        this.data = data;
        this.numberString=number;
    }

    public AiroportsColumn(AiroportsColumn temp) {
        this.data=temp.data;
        this.numberString=temp.numberString;
    }

    /**
     * метод для сравнения объектов этого класса для сортировки
     * @param o сравниваемый объект
     * @return результат сравнения
     */
    public int compareTo(AiroportsColumn o) {

//используем метод compareTo из класса String для сравнения имен
        int result = this.data.compareTo(o.data);
        return result;
    }
    @Override
    public String toString()
    {
        return super.toString();
    }

}
