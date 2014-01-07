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
 * Converts two types of contents
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @param <OutputType> Target type
 * @param <ContentType> Type of the content
 * @since 1.0
 * @version 1.0
 */
public interface ContentConverter<OutputType, ContentType> {
    
    /**
     * Converts an input instance to an output type
     * 
     * @param source source of the class type
     * @return valid output object
     * @throws ConvertException is thrown when the source is corrupt
     */
    OutputType toOutput(ContentType source) throws ConvertException;
    
    /**
     * Converts an output instance to an input type
     * 
     * @param source source instance
     * @return valid input object
     * @throws ConvertException is thrown when the source is corrupt
     */
    ContentType toInput(OutputType source) throws ConvertException;    
}
