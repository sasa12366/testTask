package Ovsyannikov;

import java.util.ArrayList;

public class BinarySearch {
    public static int binarySearch(ArrayList<AiroportsColumn> a, AiroportsColumn x) {
        int low = 0;
        int high = a.size() - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (a.get(mid).data.indexOf(x.data) < 0) {
                low = mid + 1;
            } else if (a.get(mid).data.indexOf(x.data) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
    public static int find(ArrayList <AiroportsColumn> first, int start, int end, AiroportsColumn searchString){
        int mid = start + (end-start)/2;

        if(first.get(mid).data.startsWith(searchString.data)==true){
            return mid;
        }
        if(start==end)
            return -1;
        if(start<0||end<0)
            return -1;
        String substr2 = first.get(mid).data.substring(0,searchString.data.length());

        if(substr2.compareTo(searchString.data)> 0){
            return find(first, start, mid-1, searchString);
        }else if(substr2.compareTo(searchString.data)< 0){
            return find(first, mid+1, end, searchString);
        }
        if(Character.compare(substr2.charAt(0),searchString.data.charAt(0))!=0)
            return -1;
        return -1;
    }
}
