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


package nl.fontys.epic.io;

import java.util.HashMap;
import java.util.Map;

/**
 * XML implementation of {@see GameManager}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleIOConverter<Type> implements IOConverter<Type> {
    
    private final Map<Class<?>, ContentConverter<Type, ?> > converters;
    
    public SimpleIOConverter() {
        converters = new HashMap< >();
    }

    @Override
    public <T> Type toOutput(T source) throws ConvertException {
        
        ContentConverter<Type, T> converter = (ContentConverter<Type, T>) converters.get(source.getClass());
        
        if (converter != null) {
            return converter.toOutput(source);
        } else {
            throw new ConvertException("No converter was found for type " + source.getClass());
        }
    }

    @Override
    public <T> T toInput(Type source, Class<T> classType) throws ConvertException {
        
        ContentConverter<Type, T> converter = (ContentConverter<Type, T>) converters.get(source.getClass());
        
        if (converter != null) {
            return converter.toInput(source);
        } else {
            throw new ConvertException("No converter was found for type " + source.getClass());
        }
    }

    @Override
    public <T> void addContentConverter(ContentConverter<Type, T> converter, Class<T> converterClass) {
       converters.put(converterClass, converter);
    }

    
}
