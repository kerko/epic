/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
