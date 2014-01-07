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
package nl.fontys.epic.core.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleInventory implements Inventory,Iterable<Item>{
    
    private final HashMap<String,Item> inventory = new HashMap<>();
    

    @Override
    public boolean remove(String itemId) {
        if(inventory.containsKey(itemId)){
            inventory.remove(itemId);
            return true;
        }
        return false;
    }

    @Override
    public Item fetch(String itemId) {
        if(inventory.containsKey(itemId)){
            Item item = inventory.get(itemId);
           inventory.remove(itemId);
           return item;          
        }
        return null;
    }

    @Override
    public Item get(String itemId) {
        if(inventory.containsKey(itemId)){
           return inventory.get(itemId);        
        }
        return null;
    }

    @Override
    public void add(Item item) {
        inventory.put(item.getID(), item);
    }

    @Override
    public void add(Inventory inventory) {
        for (Item item : inventory) {
            this.add(item);
        }
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public Collection<Item> getItems() {
        return inventory.values();
    }

    @Override
    public boolean contains(Item item) {
        return inventory.containsValue(item);
    }

    @Override
    public boolean contains(String itemId) {
        return inventory.containsKey(itemId);
    }

    @Override
    public Iterator<Item> iterator() {
        return inventory.values().iterator();
    }
    
}
