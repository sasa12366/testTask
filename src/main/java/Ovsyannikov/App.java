package Ovsyannikov;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import  java.util.Scanner;
import java.util.Properties;

public class App {
    /**
     * поле хранит в себе путь к файлу конфигурации
     */
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    /**
     *
     * @param args необязательный парамметр номер колонки для сортировки
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // беру из файла конфигурации номер колонки по умолчанию
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        int number_column;
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            String column = prop.getProperty("column");
             number_column= Integer.parseInt(column);
        }
        catch (Exception e){
            System.out.println("Error read config.properties");
            number_column=1;
        }
        // если передали необязательный парамметр беру номер колонки из него
        if(args.length>0){
            if(Numberis.isNumeric(args[0]))
            number_column = Integer.parseInt(args[0]);
        }

        String stringSearching = "";

        //считываю колонку из файла
        ArrayList<AiroportsColumn> airoportsColumns = new ArrayList<AiroportsColumn>();
        try (BufferedReader br = new BufferedReader(new FileReader("airports.dat"))) {

            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                //здесь будет браться номер колонки из файла
                String columns1 = columns[number_column];
                columns1 = columns1.replaceAll("^\"|\"$", "");
                AiroportsColumn lineArrays = new AiroportsColumn(columns1);
                airoportsColumns.add(lineArrays);
                line = br.readLine();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        //сортирую
        Collections.sort(airoportsColumns);

        //считываю строку для поиска
        System.out.println("Введите строку:");
        Scanner in = new Scanner(System.in);
        stringSearching = in.nextLine();
        //засекаю время сначала поиска
        long start = System.currentTimeMillis();
        AiroportsColumn searching = new AiroportsColumn(stringSearching);
        if (stringSearching.isEmpty()){
            throw new Exception("Вы не ввели строку!");
        }

        //ищу совпадения по введенной строке, использую бинарный поиск
        boolean isTrue = true;
        ArrayList<AiroportsColumn> results=new ArrayList<AiroportsColumn>();
        while (isTrue) {
            int number = BinarySearch.find(airoportsColumns,0,airoportsColumns.size(), searching);
            if (number > -1) {
                AiroportsColumn temp = new AiroportsColumn(airoportsColumns.get(number).data,airoportsColumns.get(number).numberString);
                results.add(temp);
                airoportsColumns.remove(number);
            }
            else {
                isTrue = false;
            }
        }

        //сортирую полученные строки
        Collections.sort(results);
        //беру полную информацию из файла и вывожу
        for(int i=0;i<results.size();i++){
            String resultsShow="";
            try (BufferedReader br = new BufferedReader(new FileReader("airports.dat"))) {
                int countString=0;
                String line = br.readLine();
                while (countString<results.get(i).numberString) {

                    line = br.readLine();

                    countString++;
                }
                String[] columns = line.split(",");
                columns[number_column] = columns[number_column].replaceAll("^\"|\"$", "");
                resultsShow+=columns[number_column];
                for(int j=1;j<number_column;j++){
                    columns[j] = columns[j].replaceAll("^\"|\"$", "");
                    resultsShow+=" "+columns[j];
                }
                for(int j=number_column+1;j<columns.length;j++){
                    columns[j] = columns[j].replaceAll("^\"|\"$", "");
                    resultsShow+=" "+columns[j];
                }
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
            System.out.println(resultsShow);
        }
        //затраченное время
        long finish = System.currentTimeMillis()-start;
        System.out.println("Количество найденных строк: "+results.size());
        System.out.print("Время, затраченное на поиск: ");
        System.out.print(finish+" мс");
    }
}
