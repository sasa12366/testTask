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
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream;
        //инициализируем специальный объект Properties
        //типа Hashtable для удобной работы с данными
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
        if(args.length>0){
            if(Numberis.isNumeric(args[0]))
            number_column = Integer.parseInt(args[0]);
        }
        String stringSearching = "";
        ArrayList<AiroportsColumn> airoportsColumns = new ArrayList<AiroportsColumn>();
        try (BufferedReader br = new BufferedReader(new FileReader("airports.dat"))) {

            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                //здесь будет браться номер колонки из файла
                String columns1 = columns[number_column]; // 004
                columns1 = columns1.replaceAll("^\"|\"$", "");
                AiroportsColumn lineArrays = new AiroportsColumn(columns1);
                airoportsColumns.add(lineArrays);
                line = br.readLine();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        Collections.sort(airoportsColumns);
        System.out.println("Введите строку:");
        Scanner in = new Scanner(System.in);
        stringSearching = in.nextLine();
        long start = System.currentTimeMillis();
        AiroportsColumn searching = new AiroportsColumn(stringSearching);
        if (stringSearching.isEmpty()){
            throw new Exception("Вы не ввели строку!");
        }

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
        Collections.sort(results);
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
                columns[1] = columns[1].replaceAll("^\"|\"$", "");
                resultsShow+=columns[1];
                for(int j=2;j<columns.length;j++){
                    columns[j] = columns[j].replaceAll("^\"|\"$", "");
                    resultsShow+=" "+columns[j];
                }
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
            System.out.println(resultsShow);
        }
        long finish = System.currentTimeMillis()-start;
        System.out.println("Количество найденных строк: "+results.size());
        System.out.print("Время, затраченное на поиск: ");
        System.out.print(finish+" мс");
    }
}
