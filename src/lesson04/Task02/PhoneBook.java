package lesson04.Task02;

import java.util.HashMap;

public class PhoneBook {

    private HashMap<String,String> stringStringHashMap = new HashMap<>();

    public void add(String phone, String lastname){
        stringStringHashMap.put(phone, lastname);
    }

    public String getPhoneByLastName(String lastname){
        if(stringStringHashMap.containsValue(lastname)){
            String result = " phone numbers: ";
            for(String key: stringStringHashMap.keySet()){
                if(stringStringHashMap.get(key).equals(lastname)) result += key + "; ";
            }
            return lastname + result;
        } else return lastname + " not found ";
    }
}
