package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Creature extends GameObject {

    /**
     * 
     * 
     * @return 
     */
    String getName();

    /**
     * 
     * 
     * @return 
     */
    int getPower();

    /**
     * 
     * 
     * @return 
     */
    int getDefense();

    /**
     * 
     * 
     * @return 
     */
    int getLife();

    /**
     * 
     * 
     * @return 
     */
    Inventory getInventory();
}
