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

package nl.fontys.epic.io.xml;

import nl.fontys.epic.Attributes;
import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.Stats;
import nl.fontys.epic.io.ConvertException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Converts objects of {@see Creature} to XML nodes and vise versa
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CreatureConverter  extends AbstractContentConverter<Node, Creature> {

    public CreatureConverter(TextAdventure game) {
        super(game);
    }

    @Override
    public Node toOutput(Creature source) throws ConvertException {
        Document doc = documentFactory.create();
        Element creature = doc.createElement(Attributes.TAG_CREATURE);
        Stats stats = source.getStats();
        
        creature.setAttribute(Attributes.ATTR_ID, source.getID());
        creature.setAttribute(Attributes.ATTR_NAME, source.getName());
        creature.setAttribute(Attributes.ATTR_LIFE, String.valueOf(source.getLife()));
        creature.setAttribute(Attributes.ATTR_MAX_LIFE, String.valueOf(source.getMaxLife()));
        
        // TODO
        return creature;
    }

    @Override
    public Creature toInput(Node source) throws ConvertException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
