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

import java.util.Objects;
import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.GameObject;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.util.GameObjectPool;
import nl.fontys.epic.util.Position;
import nl.fontys.epic.util.SharedGameObjectPool;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleGameObject extends SimpleIDProvider implements GameObject {

    private final TextAdventure adventure;
    protected Position posi;
    protected String currentRoomID;
    private final GameObjectPool pool;

    public SimpleGameObject(TextAdventure adventure, int posix, int posiy, String ID) {
        super(ID);
        pool = SharedGameObjectPool.getInstance(adventure.getID());
        this.adventure = adventure;
        this.posi.x = posix;
        this.posi.y = posiy;
        this.currentRoomID = null;
    }

    @Override
    public Position getPosition() {
        return posi;
    }

    @Override
    public Room getRoom() {
        return pool.get(currentRoomID, Room.class);
    }

    @Override
    public void setPosition(int x, int y) {
        this.posi.x = x;
        this.posi.y = y;
    }

    @Override
    public boolean isInfrontOf(GameObject object) {
        Room otherRoom = object.getRoom();
        if (otherRoom == this.getRoom()) {
            if (Math.abs(object.getPosition().y - this.posi.y) == 1
                    && Math.abs(object.getPosition().x - this.posi.x) == 1) {
                return true;
            }

        }
        return false;
    }

    @Override
    public TextAdventure getAdventure() {
        return adventure;
    }

    @Override
    public int getX() {
        return posi.x;
    }

    @Override
    public int getY() {
        return posi.y;
    }

    @Override
    public void setRoom(Room room) {
        currentRoomID = room.getID();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.posi);
        hash = 53 * hash + Objects.hashCode(this.currentRoomID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleGameObject other = (SimpleGameObject) obj;
        if (!Objects.equals(this.posi, other.posi)) {
            return false;
        }
        if (!Objects.equals(this.currentRoomID, other.currentRoomID)) {
            return false;
        }
        return true;
    }

    

}
