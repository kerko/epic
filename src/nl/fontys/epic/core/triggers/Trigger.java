
package nl.fotnys.epic.core.triggers;

import nl.fontys.epic.TextAdventure;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface Trigger {
    public void action(TextAdventure adventure, Triggerable source,Object[] attributes);
    public String getName();
}
