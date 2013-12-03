
package nl.fontys.epic.core;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Stats {
    
    int getPower();
    int getDefense();

    void addStats(Stats stats);
    
}
