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
import nl.fontys.epic.core.Door;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.util.ResourceManager;
import nl.fontys.epic.util.SharedResourceManager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * An implementation of {@see EntityFactory} in order to create rooms.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class RoomFactory implements EntityFactory<Room> {

    private final TextAdventure adventure;
    
    
    private final EntityFactory<Door> doorFactory;

    public RoomFactory(TextAdventure adventure, EntityFactory<Door> doorFactory) {
        this.adventure = adventure;
        this.doorFactory = doorFactory;
    }

    @Override
    public Room create(Node node) throws FactoryException {

        Room room = null;

        String id = getValue(node, "id");
        String name = getValue(node, "name");
        validateId(id);

        appendDoorList(room, node);

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

    private String getValue(Node node, String id) throws FactoryException {

        if (node.getNodeType() == Node.ELEMENT_NODE) {

            Element element = (Element) node;

            String value = element.getAttribute(id);

            if (value != null) {
                return value;
            } else {
                throw new FactoryException("Can't create room. Attribute `" + id + "` does not exist'");
            }
        } else {
            throw new FactoryException("Can't create room. No valid node");
        }
    }

    private void appendDoorList(Room room, Node node) throws FactoryException {

        NodeList children = node.getChildNodes();

        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);

            if (child.getNodeName().equals("doors")) {
                NodeList doors = child.getChildNodes();
                appendDoors(room, doors);
            }
        }
    }

    private void appendDoors(Room room, NodeList doors) throws FactoryException {
        for (int y = 0; y < doors.getLength(); ++y) {
            Door door = doorFactory.create(doors.item(y));
            room.addObject(door);
        }
    }
}
