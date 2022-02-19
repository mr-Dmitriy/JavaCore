package lesson02;


public class MethodWithExceptionForArray {
    final static int SIZEOFARRAY = 4;
    static int sumElemetsInCheckedArray(String[][] myarray)
            throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (SIZEOFARRAY != myarray.length) throw new MyArraySizeException();
        for(int i = 0; i < myarray.length; i++){
            if (4 != myarray[i].length) throw new MyArraySizeException();
            for (int j = 0; j < myarray[i].length; j++) {
                try {
                    sum += Integer.parseInt(myarray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        return sum;
    }
}
