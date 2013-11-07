package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Room extends IDProvider {

    public GameObject getObject(Location first, Location second);

    public boolean hasObject(Location first, Location second);

    public String getName();

}
