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

import java.util.Iterator;
import java.util.Map;

/**
 * Response of a single command chain
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class MatrixIterator<Type> implements Iterator<Type> {
        
        private final Iterator<? extends Map<Integer, Type> > iteratorX;
        
        private Iterator<Type> iteratorY;

        
        public MatrixIterator(Iterator<? extends Map<Integer, Type> > chunks) {
                this.iteratorX = chunks;
        }


        @Override
        public boolean hasNext() {
                return iteratorY != null ? iteratorY.hasNext() || iteratorX.hasNext() : iteratorX.hasNext();
        }

        @Override
        public Type next() {
                if (iteratorY == null || !iteratorY.hasNext())
                        iteratorY = iteratorX.next().values().iterator();                
                return iteratorY.hasNext() ? iteratorY.next() : null;                
        }

        @Override
        public void remove() {
                // TODO: Not implemented yet
        }
}