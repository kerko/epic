package nl.fontys.epic;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Creature extends IDProvider {

    public String getName();

    public int getPower();

    public int getDefense();

    public int getLife();

    public Inventory getInventory();
}
