package nl.fotnys.epic.core.triggers;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public interface TriggerManager {
    <Type extends Trigger> void register(Class<Type> triggerClass);
    <Type extends Trigger> void unregister(Class<Type> triggerClass);
    void handle(Triggerable t) throws TriggerException;
}
