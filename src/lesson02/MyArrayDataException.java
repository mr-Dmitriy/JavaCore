package lesson02;

public class MyArrayDataException extends Exception {
    MyArrayDataException(int i, int j) {
        super(String.format("Wrong data in [%d,%d]", i, j));
    }
}
