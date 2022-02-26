package lesson03.part1;

import lesson02.MyArraySizeException;

public class ReplaceElementsClass <T>{

    T intermediate;

    public T[] swapElements(T[] array, int indexA, int indexB)  {
            intermediate = array[indexA];
            array[indexA] = array[indexB];
            array[indexB] = intermediate;
        return array;
    }
}
