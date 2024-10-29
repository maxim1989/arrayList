package org.example.service;

import org.example.exceptions.IndexException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NullException;

public class StringListImpl implements StringList {
    private final String[] store;

    public StringListImpl(int size) {
        this.store = new String[size];
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullException();
        }

        for (int i = 0; i < store.length; i++) {
            if (store[i] == null) {
                store[i] = item;
                return item;
            }
        }

        return null;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new NullException();
        }

        if (index < 0 || index >= store.length) {
            throw new IndexException();
        }

        if (index == store.length - 1 | store[index] == null) {
            store[index] = item;
        } else {
            for (int i = store.length - 1; i >= index; i--) {
                if (i != index) {
                    store[i] = store[i - 1];
                } else {
                    store[i] = item;
                }
            }
        }

        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new NullException();
        }

        if (index < 0 || index >= store.length) {
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

        for (int i = 0; i < store.length; i++) {
            if (store[i] != null && store[i].equals(item)) {
                index = i;
            }

            if (index >= 0 & i + 1 < store.length) {
                store[i] = store[i + 1];
            }

            if (index >= 0 & i + 1 == store.length) {
                store[i] = null;
            }
        }

        if (index == -1) {
            throw new NotFoundException();
        }

        return item;
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= store.length) {
            throw new IndexException();
        }

        String result = store[index];

        if (result == null) {
            throw new NullException();
        }

        for (int i = index; i < store.length; i++) {
            if (i + 1 < store.length) {
                store[i] = store[i + 1];
            }

            if (i + 1 == store.length) {
                store[i] = null;
            }
        }

        return result;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new NullException();
        }

        for (final String element : store) {
            if (element == null) {
                continue;
            }

            if (element.equals(item)) {
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

        for (int i = 0; i < store.length; i++) {
            if (store[i] == null) {
                continue;
            }

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

        for (int i = store.length - 1; i >= 0; i--) {
            if (store[i] == null) {
                continue;
            }

            if (item.equals(store[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= store.length) {
            throw new IndexException();
        }

        return store[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullException();
        }

        if (this.size() == otherList.size()) {
            for (int i = 0; i < store.length; i++) {
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
        int counter = 0;

        for (int i = 0; i < store.length; i++) {
            if (store[i] != null) {
                counter++;
            }
        }
        return counter;
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
    }

    @Override
    public String[] toArray() {
        final String[] newArr = new String[store.length];
        System.arraycopy(store, 0, newArr, 0, store.length);
        return newArr;
    }
}
