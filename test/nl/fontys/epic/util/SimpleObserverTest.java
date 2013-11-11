/* The MIT License (MIT)
 * 
 * Copyright (c) 2013 Jan Kerkenhoff, Miguel Gonzalez
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
