package homework4.exercise1;

import java.util.*;

public class WorkWithUniqueWords {

    public static List<String> words =
            Arrays.asList("way", "road", "car", "machine", "road", "roadrunner",
                          "highway", "highway", "driver", "driver");


    public static void main(String[] args) {

        System.out.println(words + "\n" );
        System.out.println(new HashSet<>(words) + "\n" );

        HashSet<String> arrayWithOnlyUniqueWords = new HashSet<>(words);
        System.out.println("Set size=  " + arrayWithOnlyUniqueWords.size());

        HashMap<String,Integer> result = new HashMap<>();
        for(String str: arrayWithOnlyUniqueWords){
            result.put(str,count(str));
        }

        result.forEach((str, value) -> System.out.println(str + ": " + value));
    }


    public static Integer count(String str){
        Integer result = 0;
        for(String strThis : words){
            if(strThis.equals(str)) result++;
        }
        return result;
    }
}