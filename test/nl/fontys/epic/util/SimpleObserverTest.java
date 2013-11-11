package nl.fontys.epic.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*; 

/**
 * Test case for {@see SimpleObserver}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleObserverTest {
    
    SimpleObserver<String> observer;
    
    String first = "Hello";
    String second = "World";
    String third = "!";
    
    @Before
    public void setUp() {
        observer = new SimpleObserver();
        observer.addListener(first);
        observer.addListener(second);
        observer.addListener(third);
    }
    
    
    @Test
    public void testAddListener() {
        int size = observer.getListeners().size();
        assertTrue("Observer size has to be 3 instead of " + size, size == 3);
    }
    
    @Test
    public void testRemoveListener() {
        observer.removeListener(first);
        int size = observer.getListeners().size();
        assertTrue("Observer size has to be 2 instead of " + size, size == 2);
        observer.removeListener(third);
        size = observer.getListeners().size();
        assertTrue("Observer size has to be 1 instead of " + size, size == 1);
         observer.removeListener(second);
        size = observer.getListeners().size();
        assertTrue("Observer size has to be 0 instead of " + size, size == 0);
    }
    
    @Test
    public void testHasListener() {
        assertTrue("Listener " + first + " has to be exist", observer.hasListener(first));
        assertTrue("Listener " + second + " has to be exist", observer.hasListener(second));
        assertTrue("Listener " + third + " has to be exist", observer.hasListener(third));
    }
   
}
