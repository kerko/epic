
package nl.fotnys.epic.core.triggers;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface TriggerManager <Type extends Trigger> {
    public void register(Class<Type> triggerClass);
    public void unregister(Class<Type> triggerClass);
    public void handle(Triggerable t) throws TriggerException;
}
