package lesson04.Task02;

public class MainClass {
    public static void main(String[] args) {

        PhoneBook phonebook = new PhoneBook();
        phonebook.add("9153645321","Zaycev");
        phonebook.add("3156896563","Baranov");
        phonebook.add("3164654665","Geek");
        phonebook.add("1316496532","Don");
        phonebook.add("1216598613","Tyron");
        phonebook.add("1569846132","Geek");

        System.out.println(phonebook.getPhoneByLastName("Geek"));
    }
}
