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
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.io.ConvertException;
import nl.fontys.epic.io.GameManager;
import nl.fontys.epic.io.IOConverter;
import nl.fontys.epic.util.GameObjectPool;
import nl.fontys.epic.util.SharedGameObjectPool;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML implementation of {@see GameManager}
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
        IOConverter<Node> converter = createConverter();
        
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
        IOConverter<Node> converter = createConverter();
        NodeList nodes = document.getChildNodes();
        Node root = nodes.item(0);
        
        if (root == null) {
            throw new IOException("Document has no root element.");
        } else {            
            nodes = root.getChildNodes();
        }
       
        List<Room> rooms = new ArrayList< >();
        Player player = null;
        String name = null;
        String story = null;        
        
        GameObjectPool pool = SharedGameObjectPool.getInstance(name);
        
        try {
            for (int i = 0; i < nodes.getLength(); ++i) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    switch (node.getNodeName()) {
                        case Attributes.TAG_PLAYER:
                            player = converter.toInput(node, Player.class);
                            pool.add(player.getID(), player);
                            break;
                        case Attributes.TAG_CREATURE:
                            Creature creature = converter.toInput(node, Creature.class);
                            pool.add(creature.getID(), creature);
                            break;
                        case Attributes.TAG_ITEM:
                            Item item = converter.toInput(node, Item.class); 
                            pool.add(item.getID(), item);
                            break;
                    }
                }
            }
        } catch (ConvertException e) {
            throw new IOException(e);
        }
        
        return new SimpleTextAdventure(name, story, rooms, player);
    }
    
    private IOConverter<Node> createConverter() {
        IOConverter converter = new SimpleIOConverter< >();
        
        converter.addContentConverter(new AdventureConverter(), TextAdventure.class);
        converter.addContentConverter(new PlayerConverter(), Player.class);
        converter.addContentConverter(new ItemConverter(), Item.class);
        converter.addContentConverter(new CreatureConverter(), Creature.class);
        converter.addContentConverter(new RoomConverter(), Room.class);
        
        return converter;
    }
    
}
