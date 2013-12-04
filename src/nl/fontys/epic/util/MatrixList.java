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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Response of a single command chain
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class MatrixList<Type extends Indexable> implements Collection<Type> {        
        
        private int elementSize;

        private final HashMap<Integer, HashMap<Integer, Type>> chunks;
        
        
        
        public MatrixList() {
                elementSize = 0;
                chunks = new HashMap< >();
        }
        
        @Override
        public boolean add(Type element) {
                if (chunks.containsKey(element.getX())) {
                        HashMap<Integer, Type> yMap = chunks.get(element
                                        .getX());
                        if (!yMap.containsKey(element.getY())) {
                                yMap.put(element.getY(), element);
                                elementSize++;
                                return true;
                        } else {
                                return false;
                        }
                } else {
                        HashMap<Integer, Type> yChunkMap = new HashMap<>();
                        yChunkMap.put(element.getY(), element);
                        chunks.put(element.getX(), yChunkMap);
                        elementSize++;
                        return true;
                }
        }

        @Override
        public boolean addAll(Collection<? extends Type> objects) {
                
                boolean changed = false;
                
                for (Type object : objects) {
                        if (!changed) {
                                changed = add(object);
                        }
                }
                
                return changed;
        }

        @Override
        public void clear() {
                chunks.clear();
        }

        @Override
        public boolean contains(Object object) {
                if (object instanceof Indexable) {
                        Indexable indexable = (Indexable)object;
                        return contains(indexable.getX(), indexable.getY());
                } else {
                        return false;
                }
        }

        @Override
        public boolean containsAll(Collection<?> objects) {
                for (Type elem : this) {
                        if (!objects.contains(elem)) {
                                return false;
                        }
                }
                
                return true;
        }

        @Override
        public boolean isEmpty() {
                return chunks.isEmpty();
        }

        @Override
        public Iterator<Type> iterator() {
                return new MatrixIterator<>(chunks.values().iterator());
        }

        @Override
        public boolean remove(Object object) {
                if (object instanceof Indexable) {
                        Indexable indexable = (Indexable)object;
                        return remove(indexable.getX(), indexable.getY());
                } else {
                        return false;
                }
        }

        @Override
        public boolean removeAll(Collection<?> objects) {
                boolean changed = false;
                
                for (Object object : objects) {
                        if (!changed) {
                                changed = remove((Type)object);
                        }
                }
                
                return changed;
        }

        @Override
        public boolean retainAll(Collection<?> objects) {
                
                boolean changed = false;
                
                for (Object o : this) {
                        if (!objects.contains(o)) {
                                remove((Type)o);
                                changed = true;
                        }
                }
                
                return changed;
        }

        @Override
        public int size() {
                return elementSize;
        }

        @Override
        public Object[] toArray() {
                return toArray(new Object[size()]);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T[] toArray(T[] objects) {
                if (objects.length != size()) {
                        objects = (T[]) new Object[size()];
                }
                
                int index = 0;
                
                for (Type type : this) {
                        objects[index++] = (T) type;
                }
                
                return objects;
        }

        public boolean remove(int indexX, int indexY) {
                HashMap<Integer, Type> yChunkMap = chunks.get(indexX);

                if (yChunkMap != null) {
                        yChunkMap.remove(indexY);
                        // X axis
                        if (yChunkMap.isEmpty()) {
                                chunks.remove(indexX);
                        } else {
                                return false;
                        }
                        elementSize--;
                        return true;
                } else {
                        return false;
                }
        }

        public MatrixList<Type> copy() {
                MatrixList<Type> copyList = new MatrixList<>();
                for (Type element : this) {
                        copyList.add(element);
                }
                return copyList;
        }

        public boolean contains(int indexX, int indexY) {
                return get(indexX, indexY) != null;
        }

        public Type get(int indexX, int indexY) {
                HashMap<Integer, Type> yChunkMap = chunks.get(indexX);

                if (yChunkMap != null) {
                        Type element = yChunkMap.get(indexY);

                        if (element != null) {
                                return element;
                        } else {
                                return null;
                        }
                } else {
                        return null;
                }
        }

        public void set(MatrixList<Type> matrixList) {
                this.elementSize = matrixList.size();
                clear();
                for (Type elem : matrixList) {
                        add(elem);
                }
        }

        // ===========================================================
        // Methods
        // ===========================================================

        // ===========================================================
        // Inner classes
        // ===========================================================
}
