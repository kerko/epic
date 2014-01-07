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

/**
 * Converts multiple types to an output type and vise versa.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @param <OutputType> Target type
 * @since 1.0
 * @version 1.0
 */
public interface IOConverter<OutputType> {
    
    /**
     * Converts an input instance to an output type
     * 
     * @param <T> class type of the input
     * @param source source of the class type
     * @return valid output object
     * @throws ConvertException is thrown when the source is corrupt
     */
    <T> OutputType toOutput(T source) throws ConvertException;
    
    /**
     * Converts an output instance to an input type
     * 
     * @param <T> class type of the output
     * @param source source instance
     * @param classType class type to make type checks
     * @return valid input object
     * @throws ConvertException is thrown when the source is corrupt
     */
    <T> T toInput(OutputType source, Class<T> classType) throws ConvertException;
    
    /**
     * Adds a new content converter
     * 
     * @param <T>
     * @param converter new converter instance
     * @param converterClass
     */
    <T> void addContentConverter(ContentConverter<OutputType, T> converter, Class<T> converterClass);
}
