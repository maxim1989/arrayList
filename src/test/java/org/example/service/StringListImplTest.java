package org.example.service;

import org.example.exceptions.IndexException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NullException;
import org.example.exceptions.SizeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {
    StringListImpl out;

    @BeforeEach
    public void initEach() {
        out = new StringListImpl(3);
    }

    @AfterEach
    public void closeEach() {
        out = null;
    }

    @Test
    public void addStringInEmptyList() {
        assertEquals("test 1", out.add("test 1"));
        assertEquals("test 2", out.add("test 2"));
        assertEquals("test 3", out.add("test 3"));
        assertEquals("test 1", out.toArray()[0]);
        assertEquals("test 2", out.toArray()[1]);
        assertEquals("test 3", out.toArray()[2]);
        assertThrows(SizeException.class, () -> out.add("test 4"));
        assertThrows(NullException.class, () -> out.add(null));
    }

    @Test
    public void addStringByIndex() {
        assertThrows(IndexException.class, () -> out.add(2, "test 1"));
        assertThrows(IndexException.class, () -> out.add(-1, "test 2"));
        out.add("test 3");
        assertEquals("test 4", out.add(0, "test 4"));
        out.add("test 5");
        out.add("test 6");
        assertEquals("test 7", out.add(1, "test 7"));
        assertEquals("test 4", out.toArray()[0]);
        assertEquals("test 7", out.toArray()[1]);
        assertEquals("test 5", out.toArray()[2]);

        assertThrows(NullException.class, () -> out.add(2, null));
    }

    @Test
    public void setStringByIndex() {
        assertThrows(IndexException.class, () -> out.set(-1, "test 5"));
        assertThrows(IndexException.class, () -> out.set(3, "test 6"));
        assertThrows(NullException.class, () -> out.set(2, null));
        out.add("test 1");
        out.add("test 2");
        out.add("test 3");
        assertEquals("test 1", out.toArray()[0]);
        assertEquals("test 2", out.toArray()[1]);
        assertEquals("test 3", out.toArray()[2]);
        assertEquals("test 4", out.set(0, "test 4"));
        assertEquals("test 5", out.set(1, "test 5"));
        assertEquals("test 6", out.set(2, "test 6"));
        assertEquals("test 4", out.toArray()[0]);
        assertEquals("test 5", out.toArray()[1]);
        assertEquals("test 6", out.toArray()[2]);
    }

    @Test
    public void removeItem() {
        assertEquals("test 1", out.add("test 1"));
        assertEquals("test 2", out.add("test 2"));
        assertEquals("test 3", out.add("test 3"));
        assertThrows(NotFoundException.class, () -> out.remove("test 4"));
        assertEquals("test 2", out.remove("test 2"));
        assertThrows(NotFoundException.class, () -> out.remove("test 2"));
        assertEquals("test 5", out.add("test 5"));
        assertEquals("test 1", out.toArray()[0]);
        assertEquals("test 3", out.toArray()[1]);
        assertEquals("test 5", out.toArray()[2]);
        assertThrows(NullException.class, () -> out.remove(null));
    }

    @Test
    public void removeByIndex() {
        assertEquals("test 1", out.add("test 1"));
        assertEquals("test 2", out.add("test 2"));
        assertEquals("test 3", out.add("test 3"));
        assertThrows(IndexException.class, () -> out.remove(-1));
        assertThrows(IndexException.class, () -> out.remove(3));
        assertEquals("test 1", out.remove(0));
        assertEquals("test 4", out.add("test 4"));
        assertEquals("test 2", out.toArray()[0]);
        assertEquals("test 3", out.toArray()[1]);
        assertEquals("test 4", out.toArray()[2]);
    }

    @Test
    public void contains() {
        assertFalse(out.contains("test 1"));
        out.add("test 1");
        assertTrue(out.contains("test 1"));
        assertThrows(NullException.class, () -> out.contains(null));
    }

    @Test
    public void indexOf() {
        assertEquals(-1, out.indexOf("test 1"));
        out.add("test 1");
        assertEquals(0, out.indexOf("test 1"));
        assertThrows(NullException.class, () -> out.indexOf(null));
    }

    @Test
    public void lastIndexOf() {
        assertEquals(-1, out.lastIndexOf("test 1"));
        out.add("test 1");
        assertEquals(0, out.lastIndexOf("test 1"));
        assertThrows(NullException.class, () -> out.lastIndexOf(null));
    }

    @Test
    public void get() {
        out.add("test 1");
        assertEquals("test 1", out.get(0));
        assertThrows(IndexException.class, () -> out.get(1));
        assertThrows(IndexException.class, () -> out.get(4));
        assertThrows(IndexException.class, () -> out.get(-1));
    }

    @Test
    public void equals() {
        StringList l = new StringListImpl(3);
        out.add("test 1");
        out.add("test 2");
        l.add("test 1");
        l.add("test 2");
        assertTrue(out.equals(l));
        l.add("test 3");
        assertFalse(out.equals(l));
        out.add("test 4");
        assertFalse(out.equals(l));
        assertThrows(NullException.class, () -> out.equals(null));
    }

    @Test
    public void size() {
        assertEquals(0, out.size());
        out.add("test 1");
        assertEquals(1, out.size());
        out.add("test 2");
        assertEquals(2, out.size());
        out.add("test 3");
        assertEquals(3, out.size());
        out.remove("test 3");
        assertEquals(2, out.size());
        out.remove(0);
        assertEquals(1, out.size());
        assertEquals("test 2", out.get(0));
    }

    @Test
    public void isEmpty() {
        assertTrue(out.isEmpty());
        out.add("test 1");
        assertFalse(out.isEmpty());
    }

    @Test
    public void clear() {
        assertArrayEquals(new String[0], out.toArray());
        out.add("test 1");
        assertArrayEquals(new String[]{"test 1"}, out.toArray());
        out.clear();
        assertArrayEquals(new String[0], out.toArray());
    }

    @Test
    public void toArray() {
        String[] emptyArray = new String[]{
                "test 1", "test 2", "test 3"
        };
        out.add("test 1");
        out.add("test 2");
        out.add("test 3");
        assertArrayEquals(emptyArray, out.toArray());
    }
}
