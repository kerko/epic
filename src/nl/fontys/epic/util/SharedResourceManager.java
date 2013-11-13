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

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton implementation of {@see ResourceManager}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SharedResourceManager implements ResourceManager {
    
    private static final Map<String, SharedResourceManager> managers;
    
    private final Map<String, Object> resources;
    
    static {
        managers = new HashMap<>();
    }
    
    private SharedResourceManager() { 
        resources = new HashMap<>();
    }
    
    public static SharedResourceManager getInstance(final String ID) {
        
        SharedResourceManager manager = managers.get(ID);
        
        if (manager == null) {
            manager = new SharedResourceManager();
            managers.put(ID, manager);
        }
        
        return manager;
    }

    @Override
    public <Type> void add(String ID, Type element) {
        resources.put(ID, element);
    }

    @Override
    public <Type> void remove(String ID) {
        resources.remove(ID);
    }

    @Override
    public <Type> Type get(String ID, Class<Type> typeClass) {
        return (Type) resources.get(ID);
    }

    @Override
    public boolean isEmpty() {
        return resources.isEmpty();
    }

    @Override
    public int size() {
        return resources.size();
    }
    
}
