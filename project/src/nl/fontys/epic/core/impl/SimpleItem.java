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

    public SimpleItem(String ID,String name, String description, ItemType type) {
        super(ID);
        this.name = name;
        this.description = description;
        this.type = type;
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
}
