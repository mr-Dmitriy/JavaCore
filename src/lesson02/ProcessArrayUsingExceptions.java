package lesson02;

public class ProcessArrayUsingExceptions {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        String[][] arrwithwrongdate = new String[][]{
                {"4", "0", "11", "4"},
                {"54", "12", "23", "3"},
                {"1", "23", "2", "3"},
                {"54", "2", "f", "2"}};
        String[][] arrwithwrongsize = new String[][]{
                {"1", "5", "3", "4", "6"},
                {"4", "5", "53", "3", "34"},
                {"1", "2", "23", "2", "23"},
                {"2", "3", "4", "2", "523"}};
        String[][] correctarr = new String[][]{
                {"11", "52", "253", "235"},
                {"42", "25", "2", "24"},
                {"234", "2", "23", "234"},
                {"234", "2", "34", "244"}};

        System.out.println("Test with arrwithwrongdate");
        try {
            System.out.println("Sum all elements of array = " + MethodWithExceptionForArray.sumElemetsInCheckedArray(arrwithwrongdate) + ".\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Test with arrwithwrongsize");
        try {
            System.out.println("Sum all elements of array =  " + MethodWithExceptionForArray.sumElemetsInCheckedArray(arrwithwrongsize) + ".\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Test with correctarr");
        try {
            System.out.println("Sum all elements of array =  " + MethodWithExceptionForArray.sumElemetsInCheckedArray(correctarr) + ".\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
