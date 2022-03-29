package Ovsyannikov;

import java.util.ArrayList;

/**
 * класс для поиска строки из файла по введённой строке
 */
public class BinarySearch {

    /**
     * Бинарный поиск по введенной строке
     * @param first коллекция строк выбранной колонки
     * @param start номер строки откуда начинать поиск
     * @param end номер строки по какую искать
     * @param searchString строка поиска
     * @return номер найденной строки или -1 если строка не найдена
     */
    public static int find(ArrayList <AiroportsColumn> first, int start, int end, AiroportsColumn searchString){
        int mid = start + (end-start)/2;

        if(first.get(mid).data.startsWith(searchString.data)==true){
            return mid;
        }
        if(start==end)
            return -1;
        if(start<0||end<0)
            return -1;
        String substr2;
        if(first.get(mid).data.length()<searchString.data.length()){
            substr2 = first.get(mid).data.substring(0,1);
            if(Character.compare(substr2.charAt(0),searchString.data.charAt(0))> 0){
                return find(first, start, mid-1, searchString);
            }else if(Character.compare(substr2.charAt(0),searchString.data.charAt(0))< 0){
                return find(first, mid+1, end, searchString);
            }
        }
        else {
            substr2 = first.get(mid).data.substring(0, searchString.data.length());
        }
        if(substr2.compareTo(searchString.data)> 0){
            return find(first, start, mid-1, searchString);
        }else if(substr2.compareTo(searchString.data)< 0){
            return find(first, mid+1, end, searchString);
        }
        return -1;
    }
}
