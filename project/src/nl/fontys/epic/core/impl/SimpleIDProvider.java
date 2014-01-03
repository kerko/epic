
package nl.fontys.epic.core.impl;

import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.core.IDProvider;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleIDProvider implements IDProvider{
    private String ID;
    

    public SimpleIDProvider(String ID) {
        this.ID = ID;
    }
    
    
    @Override
    public String getID() {
       return ID;
    }
    
}
