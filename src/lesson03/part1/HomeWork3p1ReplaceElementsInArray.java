package lesson03.part1;


import java.util.Arrays;

public class HomeWork3p1ReplaceElementsInArray {

    static String[] arrString = {"1", "2", "3", "4"};
    static Integer[] arrInteger = {1, 2, 3};
    static Double[] arrayDouble = {1d, 2d, 3d, 4d};

    public static void main(String[] args) {

        System.out.println("arrString: " + Arrays.toString(arrString));
        System.out.println("arrInteger: " + Arrays.toString(arrInteger));
        System.out.println("arrayDouble: " + Arrays.toString(arrayDouble) + "\n");

        ReplaceElementsClass<Object> swapArrayClass = new ReplaceElementsClass<>();

        try {
            System.out.println("Result of replacement in array arrString: " +
                    Arrays.toString(swapArrayClass.swapElements(arrString, 0, 3)));
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("The index of element is out of bounds in arrString");
        }

        try {
            System.out.println("Result of replacement in array arrInteger: " +
                    Arrays.toString(swapArrayClass.swapElements(arrInteger, 0, 3)));
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("The index of element is out of bounds in arrInteger");
        }

        try {
            System.out.println("Result of replacement in array arrayDouble: " +
                    Arrays.toString(swapArrayClass.swapElements(arrayDouble, 0, 3)));
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("The index of element is out of bounds in arrayDouble");
        }
    }
}
