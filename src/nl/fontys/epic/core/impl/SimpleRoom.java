/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.epic.core.impl;

import java.util.HashMap;
import java.util.logging.Logger;
import nl.fontys.epic.core.GameObject;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.core.RoomException;
import nl.fontys.epic.util.MatrixList;
import nl.fontys.epic.util.Position;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleRoom extends SimpleIDProvider implements Room {

    private final String name, welcomeMessage;
    private int width = 3;
    private int height= 3;
    private final MatrixList<GameObject> objects;

    public SimpleRoom(String name, String welcomeMessage, int width, int height, String ID) {
        this(name,welcomeMessage,ID);
        this.width = width;
        this.height = height;
        
    }

    public SimpleRoom(String name, String welcomeMessage, String ID) {
        super(ID);
        this.name = name;
        this.welcomeMessage = welcomeMessage;
        this.objects = new MatrixList<>();
    }
    

    

    @Override
    public GameObject getObject(int x, int y) {

        if (hasObject(x,y)) {
            return objects.get(x, y);
        }
        return null;
    }

    @Override
    public boolean hasObject(int x, int y) {
        return objects.contains(x, x);
    }

    @Override
    public Inventory getItems(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void moveObject(GameObject object, int x, int y) throws RoomException {
        if(x>this.width || y>this.height){
            throw new RoomException("Index out of Bounds");
        }
        if(object.getRoom() != this){
            throw new RoomException("Object not in this room");
        }
        objects.remove(object);
        object.setPosition(x, y);
        objects.add(object);
    }

    

    @Override
    public void addObject(GameObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(int x, int y) {
        objects.remove(x, y);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public String getWelcomeMessage() {
        return this.welcomeMessage;
    }

}
