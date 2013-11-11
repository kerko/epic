package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Room extends IDProvider {

    /**
     * 
     * 
     * @param first
     * @param second
     * @return 
     */
    GameObject getObject(Location first, Location second);

    /**
     * 
     * 
     * @param first
     * @param second
     * @return 
     */
    boolean hasObject(Location first, Location second);

    /**
     * 
     * 
     * @return 
     */
    String getName();

}
