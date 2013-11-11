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
    
    SharedResourceManager managerA, managerB;
    
    @Before
    public void setUp() {
        managerA = SharedResourceManager.getInstance("A");
        managerB = SharedResourceManager.getInstance("B");
    }
    
    @Test
    public void testGetInstance() {
        assertTrue("ManagerA should not be null", managerA != null);
        assertTrue("ManagerB should not be null", managerB != null);
        assertTrue("ManagerA should be differently than ManagerB", !managerA.equals(managerB));
    }
    
    @Test
    public void testAdd() {
        
    }
    
    @Test
    public void testRemove() {
        
    }
    
    @Test
    public void testGet() {
        
    }
   
}
