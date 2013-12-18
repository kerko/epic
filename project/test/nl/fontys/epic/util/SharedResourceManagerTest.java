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
 * Test case for {@see SharedResourceManager}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SharedResourceManagerTest {
    
    SharedGameObjectManager managerA, managerB;
    
    @Before
    public void setUp() {
        managerA = SharedGameObjectManager.getInstance("A");
        managerB = SharedGameObjectManager.getInstance("B");
    }
    
    @Test
    public void testGetInstance() {
        assertTrue("ManagerA should not be null", managerA != null);
        assertTrue("ManagerB should not be null", managerB != null);
        assertTrue("ManagerA should be differently than ManagerB", !managerA.equals(managerB));
    }
    
    @Test
    public void testAdd() {
        assertTrue("Size of ManagerA should be empty", managerA.isEmpty());
        managerA.add("Hallo", "Hallo");
        managerA.add("Welt", "Welt");
        assertFalse("Size of ManagerA shouldn't be empty", managerA.isEmpty());
        assertTrue("Size of ManagerA should be 2", managerA.size() == 2);
    }
    
    @Test
    public void testRemove() {
        managerA.add("Hallo", "Hallo");
        managerA.add("Welt", "Welt");
        assertFalse("Size of ManagerA shouldn't be empty", managerA.isEmpty());
        assertTrue("Size of ManagerA should be 2", managerA.size() == 2);
        managerA.remove("Welt");
        assertTrue("Size of ManagerA should be 1", managerA.size() == 1);
        managerA.remove("Hallo");
        assertTrue("Size of ManagerA shouldn't be empty", managerA.isEmpty());
        assertTrue("Size of ManagerA should smaller than 1", managerA.size() < 1);
    }
    
    @Test
    public void testGet() {
        managerA.add("Hallo", "Hallo");
        managerA.add("Welt", "Welt");
        
        String hello = managerA.get("Hallo", String.class);
        String world = managerA.get("Welt", String.class);
        
        assertFalse("First element should not be null", hello == null);
        assertFalse("Second element should not be null", world == null);
        
        assertTrue("First element should be 'Hallo'", hello.equals("Hallo"));
        assertTrue("Second element should be 'Welt'", world.equals("Welt"));
    }
   
}
