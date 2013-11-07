package nl.fontys.epic;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface GameObject {

    public Room getRoom();

    public Location getFirstLocation();

    public Location getSecondLocation();

    public void setFirstLocation(Location l);

    public void setSecondLocation(Location l);

}
