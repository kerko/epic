package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Equip {

    /**
     * 
     * 
     * @param item 
     */
    void add(Item item);

    /**
     * 
     * 
     * @param item 
     */
    void remove(Item item);

    /**
     * 
     * 
     * @return 
     */
    int getLifeBonus();

    /**
     * 
     * 
     * @return 
     */
    int getPowerBonus();

    /**
     * 
     * 
     * @return 
     */
    int getDefenseBonus();

}
