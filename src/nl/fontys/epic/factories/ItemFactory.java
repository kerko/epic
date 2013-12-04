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

package nl.fontys.epic.factories;

import nl.fontys.epic.Attributes;
import nl.fontys.epic.core.Item;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * An implementation of {@see EntityFactory} in order to create rooms.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ItemFactory implements EntityFactory<Item> {

    @Override
    public Item create(Node node) throws FactoryException {        
        
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new FactoryException("Can't create item! Item node needs to be an element node.");
        }
        
        if (node.getNodeName().equals(Attributes.TAG_ITEM)) {
            throw new FactoryException("Can't create item! Node needs to be an item.");
        }
        
        return createItem((Element)node);
    }
    
    private Item createItem(Element element) {
        
        
        
        
        return null;
    }
    
}
