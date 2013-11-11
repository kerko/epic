package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface GameObject extends IDProvider {

    /**
     * 
     * 
     * @return 
     */
    Room getRoom();

    /**
     * 
     * 
     * @return 
     */
    Location getFirstLocation();

    /**
     * 
     * 
     * @return 
     */
    Location getSecondLocation();

    /**
     * 
     * 
     * @param l 
     */
    void setFirstLocation(Location l);

    /**
     * 
     * 
     * @param l 
     */
    void setSecondLocation(Location l);

}
