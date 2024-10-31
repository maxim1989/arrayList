package org.example.service;

import org.example.exceptions.IndexException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NullException;
import org.example.exceptions.SizeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {
    IntegerListImpl out;

    @BeforeEach
    public void initEach() {
        out = new IntegerListImpl(3);
    }

    @AfterEach
    public void closeEach() {
        out = null;
    }

    @Test
    public void addStringInEmptyList() {
        assertEquals(1, out.add(1));
        assertEquals(2, out.add(2));
        assertEquals(3, out.add(3));
        assertEquals(1, out.toArray()[0]);
        assertEquals(2, out.toArray()[1]);
        assertEquals(3, out.toArray()[2]);
        assertThrows(SizeException.class, () -> out.add(4));
        assertThrows(NullException.class, () -> out.add(null));
    }

    @Test
    public void addStringByIndex() {
        assertThrows(IndexException.class, () -> out.add(2, 1));
        assertThrows(IndexException.class, () -> out.add(-1, 2));
        out.add(3);
        assertEquals(4, out.add(0, 4));
        out.add(5);
        out.add(6);
        assertEquals(7, out.add(1, 7));
        assertEquals(4, out.toArray()[0]);
        assertEquals(7, out.toArray()[1]);
        assertEquals(5, out.toArray()[2]);

        assertThrows(NullException.class, () -> out.add(2, null));
    }

    @Test
    public void setStringByIndex() {
        assertThrows(IndexException.class, () -> out.set(-1, 5));
        assertThrows(IndexException.class, () -> out.set(3, 6));
        assertThrows(NullException.class, () -> out.set(2, null));
        out.add(1);
        out.add(2);
        out.add(3);
        assertEquals(1, out.toArray()[0]);
        assertEquals(2, out.toArray()[1]);
        assertEquals(3, out.toArray()[2]);
        assertEquals(4, out.set(0, 4));
        assertEquals(5, out.set(1, 5));
        assertEquals(6, out.set(2, 6));
        assertEquals(4, out.toArray()[0]);
        assertEquals(5, out.toArray()[1]);
        assertEquals(6, out.toArray()[2]);
    }

    @Test
    public void removeItem() {
        assertEquals(1, out.add(1));
        assertEquals(2, out.add(2));
        assertEquals(3, out.add(3));
        assertThrows(IndexException.class, () -> out.remove(4));
        assertEquals(3, out.remove(2));
        assertThrows(IndexException.class, () -> out.remove(2));
        assertEquals(5, out.add(5));
        assertEquals(1, out.toArray()[0]);
        assertEquals(2, out.toArray()[1]);
        assertEquals(5, out.toArray()[2]);
        assertThrows(NullException.class, () -> out.remove(null));
    }

    @Test
    public void removeByIndex() {
        assertEquals(1, out.add(1));
        assertEquals(2, out.add(2));
        assertEquals(3, out.add(3));
        assertThrows(IndexException.class, () -> out.remove(-1));
        assertThrows(IndexException.class, () -> out.remove(3));
        assertEquals(1, out.remove(0));
        assertEquals(4, out.add(4));
        assertEquals(2, out.toArray()[0]);
        assertEquals(3, out.toArray()[1]);
        assertEquals(4, out.toArray()[2]);
    }

    @Test
    public void contains() {
        assertFalse(out.contains(1));
        out.add(1);
        assertTrue(out.contains(1));
        assertThrows(NullException.class, () -> out.contains(null));
    }

    @Test
    public void indexOf() {
        assertEquals(-1, out.indexOf(1));
        out.add(1);
        assertEquals(0, out.indexOf(1));
        assertThrows(NullException.class, () -> out.indexOf(null));
    }

    @Test
    public void lastIndexOf() {
        assertEquals(-1, out.lastIndexOf(1));
        out.add(1);
        assertEquals(0, out.lastIndexOf(1));
        assertThrows(NullException.class, () -> out.lastIndexOf(null));
    }

    @Test
    public void get() {
        out.add(1);
        assertEquals(1, out.get(0));
        assertThrows(IndexException.class, () -> out.get(1));
        assertThrows(IndexException.class, () -> out.get(4));
        assertThrows(IndexException.class, () -> out.get(-1));
    }

    @Test
    public void equals() {
        IntegerList l = new IntegerListImpl(3);
        out.add(1);
        out.add(2);
        l.add(1);
        l.add(2);
        assertTrue(out.equals(l));
        l.add(3);
        assertFalse(out.equals(l));
        out.add(4);
        assertFalse(out.equals(l));
        assertThrows(NullException.class, () -> out.equals(null));
    }

    @Test
    public void size() {
        assertEquals(0, out.size());
        out.add(1);
        assertEquals(1, out.size());
        out.add(2);
        assertEquals(2, out.size());
        out.add(3);
        assertEquals(3, out.size());
        out.remove(2);
        assertEquals(2, out.size());
        out.remove(0);
        assertEquals(1, out.size());
        assertEquals(2, out.get(0));
    }

    @Test
    public void isEmpty() {
        assertTrue(out.isEmpty());
        out.add(1);
        assertFalse(out.isEmpty());
    }

    @Test
    public void clear() {
        assertArrayEquals(new Integer[0], out.toArray());
        out.add(1);
        assertArrayEquals(new Integer[]{1}, out.toArray());
        out.clear();
        assertArrayEquals(new Integer[0], out.toArray());
    }

    @Test
    public void toArray() {
        Integer[] emptyArray = new Integer[]{
                1, 2, 3
        };
        out.add(1);
        out.add(2);
        out.add(3);
        assertArrayEquals(emptyArray, out.toArray());
    }
}
