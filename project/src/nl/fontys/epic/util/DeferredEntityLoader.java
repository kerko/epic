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

package nl.fontys.epic.util;

import nl.fontys.epic.SimpleTextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.util.DeferredStorage.StorageData;
import nl.fontys.epic.util.DeferredStorage.StorageType;

/**
 * Loaders deferred data into the entities
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class DeferredEntityLoader {
    
    private GameObjectManager resourceManager;
    
    private DeferredStorage entityStorage;
    
    public DeferredEntityLoader(GameObjectManager resourceManager, DeferredStorage entityStorage) {
        this.resourceManager = resourceManager;
        this.entityStorage = entityStorage;
    }
    
    public void load(SimpleTextAdventure adventure) throws LoadingException {
        
        // Fill up all rooms
        for (Room room : adventure.getRooms()) {
            loadRoomData(room);            
        }
    }
    
    private void loadRoomData(Room room) throws LoadingException {
        
        while (entityStorage.hasNext(room)) {
            StorageData data = entityStorage.fetch(room);
            
            // Creatures
            if (data.type.equals(StorageType.CREATURE)) {
                Creature creature = resourceManager.get(data.id, Creature.class);
                
                if (creature != null) {
                    creature.setPosition(data.pos.x, data.pos.y);
                } else {
                    throw new LoadingException("No creature found with id=" + data.id + " in room with id=" + room.getID());
                }
            }
            
            // Items
            if (data.type.equals(StorageType.ITEM)) {
                Item item = resourceManager.get(data.id, Item.class);
                Inventory items = room.getItems(data.pos.x, data.pos.y);
                items.add(item);
            }
        }
    }
    
    public static class LoadingException extends Exception {

        public LoadingException(String message) {
            super(message);
        }

        public LoadingException(String message, Throwable cause) {
            super(message, cause);
        }

        public LoadingException(Throwable cause) {
            super(cause);
        }
        
        
    }
}
