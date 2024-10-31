package org.example.service;

import org.example.exceptions.IndexException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NullException;
import org.example.exceptions.SizeException;

import java.util.Arrays;
import java.util.Collections;

public class IntegerListImpl implements IntegerList {
    private final Integer[] store;
    private int size;

    public IntegerListImpl(int size) {
        this.store = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new NullException();
        }

        if (size == store.length) {
            throw new SizeException();
        }

        if (size < store.length) {
            size++;
            store[size - 1] = item;
            return item;
        }

        return null;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null) {
            throw new NullException();
        }

        if (index < 0 || index > size - 1) {
            throw new IndexException();
        }

        for (int i = size - 1; i > index; i--) {
            store[i] = store[i - 1];
        }
        store[index] = item;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) {
            throw new NullException();
        }

        if (index < 0 || index > size - 1) {
            throw new IndexException();
        }

        store[index] = item;

        return item;
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) {
            throw new NullException();
        }

        int index = -1;

        for (int i = 0; i <= size - 1; i++) {
            if (store[i] != null && store[i].equals(item)) {
                index = i;
            }

            if (index >= 0 & i < size - 1) {
                store[i] = store[i + 1];
            }

            if (index >= 0 & i == size - 1) {
                store[i] = null;
            }
        }

        if (index == -1) {
            throw new NotFoundException();
        }

        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexException();
        }

        Integer result = store[index];

        if (result == null) {
            throw new NullException();
        }

        for (int i = index; i <= size - 1; i++) {
            if (i < size - 1) {
                store[i] = store[i + 1];
            }

            if (i == size - 1) {
                store[i] = null;
            }
        }

        size--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        if (item == null) {
            throw new NullException();
        }

        for (int i = 0; i < size; i++) {
            if (store[i].equals(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new NullException();
        }

        for (int i = 0; i < size; i++) {
            if (item.equals(store[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new NullException();
        }

        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(store[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexException();
        }

        return store[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullException();
        }

        if (size == otherList.size()) {
            for (int i = 0; i < size; i++) {
                if (store[i] == null & otherList.toArray()[i] == null) {
                    continue;
                }

                if (
                        store[i] != null & otherList.toArray()[i] == null
                                | store[i] == null & otherList.toArray()[i] != null
                ) {
                    return false;
                }

                if (!store[i].equals(otherList.toArray()[i])) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < store.length; i++) {
            if (store[i] != null) {
                store[i] = null;
            }
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        final Integer[] newArr = new Integer[size()];
        if (size >= 0) System.arraycopy(store, 0, newArr, 0, size);
        return newArr;
    }

    public static int[] generateArray(int size) {
        int[] array = new int[size];
        int range = 1000000;

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * range);
        }

        return array;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
