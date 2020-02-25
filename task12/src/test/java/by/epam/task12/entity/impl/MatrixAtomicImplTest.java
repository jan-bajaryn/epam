package by.epam.task12.entity.impl;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.*;

public class MatrixAtomicImplTest {

    @Test
    public void atomicTest() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.compareAndSet(0, 20);
        assertEquals(atomicInteger.get(), 20);
    }

    @Test
    public void testCompareAndSet() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(0, 20);
        assertEquals(atomicInteger.get(), 1);
    }

    @Test
    public void testCompareAndSetReturnValueTrue() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        boolean b = atomicInteger.compareAndSet(0, 20);
        assertFalse(b);
    }

    @Test
    public void testCompareAndSetReturnValueFalse() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        boolean b = atomicInteger.compareAndSet(0, 20);
        assertTrue(b);
    }
}