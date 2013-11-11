
package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Item {
    
    /**
     * 
     * 
     * @return 
     */
    ItemType getType();
    
    /**
     * 
     * 
     * @return 
     */
    String getDescription();
    
    /**
     * 
     * 
     * @return 
     */
    String getName();
}
