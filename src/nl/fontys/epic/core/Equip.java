package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Equip {

    public void add(Item item);

    public void remove(Item item);

    public int getLifeBonus();

    public int getPowerBonus();

    public int getDefenseBonus();

}
