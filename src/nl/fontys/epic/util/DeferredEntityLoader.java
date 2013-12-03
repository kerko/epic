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

/**
 * Loads entities into a target later on
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class DeferredEntityLoader {

    public static enum EntityType {

        CREATURE,
        ITEM
    }
    
    public static class DeferredData {
             
        public String id;        
        public EntityType type;
        public Position pos;
        
        public DeferredData(String id, EntityType type, int x, int y) {
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
            final DeferredData other = (DeferredData) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            if (this.type != other.type) {
                return false;
            }
            if (!Objects.equals(this.pos, other.pos)) {
                return false;
            }
            return true;
        }
        
        
    }

    private static final DeferredEntityLoader instance;
    
    private final Map<String, List<DeferredData> > data;

    static {
        instance = new DeferredEntityLoader();
    }

    private DeferredEntityLoader() {
        data = new HashMap< >();
    }

    public static DeferredEntityLoader getInstance() {
        return instance;
    }

    public void add(String parent, String id, EntityType type, int x, int y) {
        DeferredData element = new DeferredData(id, type, x, y);        
        List<DeferredData> dataList = data.get(parent);
        
        if (dataList == null) {
            dataList = new ArrayList< >();
            data.put(parent, dataList);
        }        
        
        if (!dataList.contains(element)) {
            dataList.add(element);
        }
    }

    public void add(String parent, String id, EntityType type) {
        add(parent, id, type, -1, -1);
    }
    
    public DeferredData fetch(String parent) {
        List<DeferredData> list = data.get(parent);        
        DeferredData element = list.remove(0);
        if (list.isEmpty()) {
            data.remove(parent);
        }
        
        return element;
    }
    
    public boolean hasNext(String parent) {
        return data.get(parent) != null;
    }
}
