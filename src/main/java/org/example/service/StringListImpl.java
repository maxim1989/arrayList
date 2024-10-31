package org.example.service;

import org.example.exceptions.IndexException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NullException;
import org.example.exceptions.SizeException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final String[] store;
    private int size;

    public StringListImpl(int size) {
        this.store = new String[size];
    }

    @Override
    public String add(String item) {
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
    public String add(int index, String item) {
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
    public String set(int index, String item) {
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
    public String remove(String item) {
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
    public String remove(int index) {
        if (index < 0 || index >= size - 1) {
            throw new IndexException();
        }

        String result = store[index];

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
    public boolean contains(String item) {
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
    public int indexOf(String item) {
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
    public int lastIndexOf(String item) {
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
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexException();
        }

        return store[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        final String[] newArr = new String[size()];
        if (size >= 0) System.arraycopy(store, 0, newArr, 0, size);
        return newArr;
    }
}
