package Ovsyannikov;

public class AiroportsColumn implements Comparable<AiroportsColumn>{
    String data;
    static int number=0;
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
