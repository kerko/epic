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

import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.util.ResourceManager;
import nl.fontys.epic.util.SharedResourceManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * An implementation of {@see EntityFactory} in order to create rooms.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class RoomFactory implements EntityFactory<Room> {
    
    private final TextAdventure adventure;
    
    public RoomFactory(TextAdventure adventure) {
        this.adventure = adventure;
    }

    @Override
    public Room create(Node node) throws FactoryException {
        
        Room room = null;
        NamedNodeMap attrs = node.getAttributes();
        
        String id = getValue(attrs, "id");
        String name = getValue(attrs, "name");
        validateId(id);       
        
        return room;
    }
    
    private void validateId(String id) throws FactoryException {   
        ResourceManager mgr = SharedResourceManager.getInstance(adventure.getName());
        
        if (mgr.get(id, Room.class) != null) {
            throw new FactoryException("Room with id `" + id + "` does already exist");
        }
    }
    
    private String validateString(String str) throws FactoryException {
        if (str == null || str.isEmpty()) {
            throw new FactoryException("ID for this room should not be empty");
        }
        
        return str;
    }
    
    private String getValue(NamedNodeMap attrs, String id) throws FactoryException {
        Node node = attrs.getNamedItem(id);
        
        if (node != null) {
            String value = node.getNodeValue();
            
            return validateString(value);
        } else {
            throw new FactoryException("Can't create room. Attribute `" + id + "` does not exist'");
        }
    }
    
    
}
