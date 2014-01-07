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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import nl.fontys.epic.core.IDProvider;

/**
 * Stores entities to load them later on
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class DeferredStorage {

    public static enum StorageType {

        CREATURE,
        ITEM
    }
    
    public static class StorageData {
             
        public String id;        
        public StorageType type;
        public Position pos;
        
        public StorageData(String id, StorageType type, int x, int y) {
            this.id = id;
            this.type = type;
            this.pos = new Position(x, y);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + Objects.hashCode(this.id);
            hash = 37 * hash + Objects.hashCode(this.type);
            hash = 37 * hash + Objects.hashCode(this.pos);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final StorageData other = (StorageData) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            if (this.type != other.type) {
                return false;
            }
            return Objects.equals(this.pos, other.pos);
        }
        
        
    }

    private static final DeferredStorage instance;
    
    private final Map<String, List<StorageData> > data;

    static {
        instance = new DeferredStorage();
    }

    private DeferredStorage() {
        data = new HashMap< >();
    }

    public static DeferredStorage getInstance() {
        return instance;
    }

    public void add(String parent, String id, StorageType type, int x, int y) {
        StorageData element = new StorageData(id, type, x, y);        
        List<StorageData> dataList = data.get(parent);
        
        if (dataList == null) {
            dataList = new ArrayList< >();
            data.put(parent, dataList);
        }        
        
        if (!dataList.contains(element)) {
            dataList.add(element);
        }
    }

    public void add(String parent, String id, StorageType type) {
        add(parent, id, type, -1, -1);
    }
    
    public StorageData fetch(IDProvider parent) {
        List<StorageData> list = data.get(parent.getID());        
        StorageData element = list.remove(0);
        if (list.isEmpty()) {
            data.remove(parent.getID());
        }
        
        return element;
    }
    
    public boolean hasNext(IDProvider parent) {
        return data.get(parent.getID()) != null;
    }
}
