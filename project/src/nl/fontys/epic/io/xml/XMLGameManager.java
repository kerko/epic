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
import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.io.ConvertException;
import nl.fontys.epic.io.GameManager;
import nl.fontys.epic.util.GameObjectPool;
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
    
    private final SimpleIOConverter<Node> converter;
    
    private final GameObjectPool pool;
    
    public XMLGameManager(GameObjectPool pool) {
        converter = new SimpleIOConverter< >();
        this.pool = pool;
    }

    @Override
    public void save(TextAdventure adventure, OutputStream out) throws IOException {
        
        try {
            
        // Player
        Node node = converter.toOutput(adventure.getPlayer());
            
        // Items
        Collection<Item> items = pool.getAll(Item.class);
        
        for (Item item : items) {
            node = converter.toOutput(item);
            // TODO
        }
        
        // Creatures
        Collection<Creature> creatures = pool.getAll(Creature.class);
        
        for (Creature creature : creatures) {
            node = converter.toOutput(creature);
            // TODO
        }
        
        // Rooms
        Collection<Room> rooms = pool.getAll(Room.class);
        
        for (Room room : rooms) {
            node = converter.toOutput(room);
            // TODO
        }
        
        } catch (ConvertException e) {
            throw new IOException(e);
        }
    }

    @Override
    public TextAdventure load(InputStream in) throws IOException {
        
        NodeList nodes = null; // TODO
        
        for (int i = 0; i < nodes.getLength(); ++i) {
            
            Node node = nodes.item(i);
            
            // TODO
        }        
        
        List<Room> rooms = new ArrayList< >(); // TODO
        Player player = null; // TODO
        
        return new SimpleTextAdventure(rooms, player);
    }
    
}
