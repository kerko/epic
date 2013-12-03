
package nl.fontys.epic.core.impl;

import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.GameObject;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.util.Position;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleGameObject extends SimpleIDProvider implements GameObject {
    private TextAdventure adventure;
    protected Position posi;   
    protected String currentRoomID;

    public SimpleGameObject(TextAdventure adventure, int posix,int posiy, String currentRoomID, String ID) {
        super(ID);
        this.adventure = adventure;
        this.posi.x = posix;
        this.posi.y= posiy;
        this.currentRoomID = currentRoomID;
    }
    
    

    @Override
    public Position getPosition() {
        return posi;
    }

    @Override
    public Room getRoom() {
        return adventure.getRoom(currentRoomID);
    }

    @Override
    public void setPosition(int x, int y) {
        this.posi.x = x;
        this.posi.y = y;
    }

    @Override
    public boolean isInfrontOf(GameObject object) {
        Room otherRoom = object.getRoom();
        if(otherRoom == this.getRoom()){
            if(Math.abs(object.getPosition().y-this.posi.y) == 1 
                    && Math.abs(object.getPosition().x-this.posi.x) == 1){
                return true;
            }
                
        }
        return false;
    }

    @Override
    public TextAdventure getAdventure() {
        return adventure;
    }
    
}
