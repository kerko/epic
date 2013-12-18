package nl.fontys.epic.io;

import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.util.ResourceManager;
import nl.fontys.epic.util.SharedResourceManager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author miguel
 */
public final class FactoryUtils {
    
    public static String getValue(Node node, String id) throws FactoryException {

        if (node.getNodeType() == Node.ELEMENT_NODE) {

            Element element = (Element) node;

            String value = element.getAttribute(id);

            if (value != null) {
                return value;
            } else {
                throw new FactoryException("Can't create element. Attribute `" + id + "` does not exist'");
            }
        } else {
            throw new FactoryException("Can't create element. No valid node");
        }
    }
    
    
    

    public static void validateId(String id, TextAdventure adventure) throws FactoryException {
        ResourceManager mgr = SharedResourceManager.getInstance(adventure.getName());

        if (mgr.get(id, Room.class) != null) {
            throw new FactoryException("Element with id `" + id + "` does already exist");
        }
    }

}
