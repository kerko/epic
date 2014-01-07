package nl.fontys.epic.core.impl;

import nl.fontys.epic.impl.SimpleTextAdventure;
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

    private final SimpleTextAdventure adventure;
    protected Position posi;
    protected String currentRoomID;
    private final GameObjectPool pool;

    public SimpleGameObject(SimpleTextAdventure adventure, int posix, int posiy, String currentRoomID, String ID) {
        super(ID);
        pool = SharedGameObjectPool.getInstance(adventure.getID());
        this.adventure = adventure;
        this.posi.x = posix;
        this.posi.y = posiy;
        this.currentRoomID = currentRoomID;
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
    public SimpleTextAdventure getAdventure() {
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

}
