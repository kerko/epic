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

import nl.fontys.epic.io.SimpleIOConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nl.fontys.epic.Attributes;
import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.Door;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.io.ConvertException;
import nl.fontys.epic.io.GameManager;
import nl.fontys.epic.io.IOConverter;
import nl.fontys.epic.util.DeferredEntityLoader;
import nl.fontys.epic.util.DeferredStorage;
import nl.fontys.epic.util.GameObjectPool;
import nl.fontys.epic.util.SharedGameObjectPool;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML implementation of {
 *
 * @see GameManager}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class XMLGameManager implements GameManager {

    private final DocumentWriter documentWriter;

    private final DocumentFactory documentFactory;

    public XMLGameManager() {
        documentWriter = new SimpleDocumentWriter();
        documentFactory = new SimpleDocumentFactory();
    }

    @Override
    public void save(TextAdventure adventure, OutputStream out) throws IOException {

        GameObjectPool pool = SharedGameObjectPool.getInstance(adventure.getID());

        if (pool == null) {
            throw new IOException("Invalid adventure index: " + adventure.getID());
        }

        Document document = documentFactory.create();
        IOConverter<Node> converter = createConverter(adventure);

        try {

            Node root = converter.toOutput(adventure);

            // Add player
            Node player = converter.toOutput(adventure.getPlayer());
            root.appendChild(player);

            // Add Items
            Collection<Item> items = pool.getAll(Item.class);

            for (Item item : items) {
                root.appendChild(converter.toOutput(item));
            }

            // Add creatures
            Collection<Creature> creatures = pool.getAll(Creature.class);

            for (Creature creature : creatures) {
                root.appendChild(converter.toOutput(creature));
            }

            // Add rooms
            Collection<Room> rooms = pool.getAll(Room.class);

            for (Room room : rooms) {
                root.appendChild(converter.toOutput(room));
            }
        } catch (ConvertException e) {
            throw new IOException(e);
        }

        documentWriter.write(document, out);
    }

    @Override
    public TextAdventure load(InputStream in) throws IOException {

        Document document = documentFactory.create(in);
        SimpleTextAdventure adventure = new SimpleTextAdventure();
        IOConverter<Node> converter = createConverter(adventure);
        DeferredStorage storage = DeferredStorage.getInstance();
        NodeList nodes = document.getChildNodes();
        Node root = nodes.item(0);

        if (root == null) {
            throw new IOException("Document has no root element.");
        } else {
            nodes = root.getChildNodes();
        }

        List<Room> rooms = new ArrayList<>();
        Player player = null;

        String name = retrieveAttribute(Attributes.ATTR_NAME, root);
        String story = retrieveAttribute(Attributes.ATTR_STORY, root);
        String entryID = retrieveAttribute(Attributes.ATTR_ENTRY, root);

        GameObjectPool pool = SharedGameObjectPool.getInstance(name);

        try {
            for (int i = 0; i < nodes.getLength(); ++i) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    switch (node.getNodeName()) {
                        case Attributes.TAG_PLAYER:
                            player = converter.toInput(node, Player.class);
                            pool.add(player.getID(), player);
                            buildCreature(node.getChildNodes(), player, storage, pool, converter);
                            break;
                        case Attributes.TAG_CREATURE:
                            Creature creature = converter.toInput(node, Creature.class);
                            pool.add(creature.getID(), creature);
                            buildCreature(node.getChildNodes(), creature, storage, pool, converter);
                            break;
                        case Attributes.TAG_ITEM_COLLECT:
                        case Attributes.TAG_ITEM_CONSUM:
                        case Attributes.TAG_ITEM_EQUIP:
                            Item item = converter.toInput(node, Item.class);
                            pool.add(item.getID(), item);
                            break;
                        case Attributes.TAG_ROOM:
                            Room room = converter.toInput(node, Room.class);
                            pool.add(room.getID(), room);
                            buildRoom(node.getChildNodes(), room, storage, pool, converter);
                            break;
                    }
                }
            }

            // Fill rooms with content (magic part goes here)
            DeferredEntityLoader entityLoader = new DeferredEntityLoader(pool, storage);

            for (Room room : rooms) {
                entityLoader.load(room);
            }

            // Set the room of the player
            if (player != null) {
                player.setRoom(pool.get(entryID, Room.class));
            } else {
                throw new IOException("Player is not defined.");
            }

        } catch (ConvertException | DeferredEntityLoader.LoadingException e) {
            throw new IOException(e);
        }
        
        adventure.setRooms(rooms)
        .setName(name)
        .setStory(story)
        .setPlayer(player);

        return adventure;
    }

    private IOConverter<Node> createConverter(TextAdventure adventure) {
        IOConverter converter = new SimpleIOConverter<>();

        converter.addContentConverter(new PlayerConverter(adventure), Player.class);
        converter.addContentConverter(new ItemConverter(adventure), Item.class);
        converter.addContentConverter(new CreatureConverter(adventure), Creature.class);
        converter.addContentConverter(new RoomConverter(adventure), Room.class);
        converter.addContentConverter(new DoorConverter(adventure), Door.class);

        return converter;
    }

    // Builds a room with content
    private void buildCreature(NodeList children, Creature creature, DeferredStorage storage, GameObjectPool pool, IOConverter<Node> converter) throws ConvertException {

        for (int i = 0; i < children.getLength(); ++i) {

            Node child = children.item(i);

            if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals(Attributes.TAG_ITEMS)) {

                NodeList items = child.getChildNodes();

                for (int index = 0; index < items.getLength(); ++index) {

                    Node itemNode = items.item(index);

                    if (itemNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) itemNode;
                        String ref = element.getAttribute("ref");

                        if (element.getNodeName().equals(Attributes.TAG_ITEM_REF)) {
                            storage.add(creature.getID(), ref, DeferredStorage.StorageType.ITEM);
                        }
                    }
                }
            }
        }
    }

    // Builds a room with content
    private void buildRoom(NodeList children, Room room, DeferredStorage storage, GameObjectPool pool, IOConverter<Node> converter) throws ConvertException {

        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);

            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) child;
                String ref = element.getAttribute("ref");

                switch (element.getNodeName()) {

                    case Attributes.TAG_ITEM_REF:
                        storage.add(room.getID(), ref, DeferredStorage.StorageType.ITEM);
                        break;
                    case Attributes.TAG_CREATURE_REF:
                        storage.add(room.getID(), ref, DeferredStorage.StorageType.CREATURE);
                        break;
                    case Attributes.TAG_DOOR:
                        Door door = converter.toInput(child, Door.class);
                        pool.add(door.getID(), door);
                        door.setRoom(room);
                        room.addObject(door);
                        break;
                }
            }
        }
    }

    private String retrieveAttribute(String attr, Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            return element.getAttribute(attr);
        } else {
            return null;
        }
    }

}
