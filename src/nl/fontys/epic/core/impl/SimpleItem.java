package nl.fontys.epic.core.impl;

import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.ItemType;
import nl.fontys.epic.core.Stats;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleItem extends SimpleIDProvider implements Item {

    private final String name;
    private final String description;
    private final ItemType type;
    private final Stats stats;

    public SimpleItem(String ID,String name, String description, ItemType type, Stats stats) {
        super(ID);
        this.name = name;
        this.description = description;
        this.type = type;
        this.stats = stats;
        
    }

    @Override
    public ItemType getType() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Stats getStats() {
        return this.stats;
    }

}
