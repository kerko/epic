/* The MIT License (MIT)
 * 
 * Copyright (c) 2013 Jan Kerkenhoff, Miguel Gonzalez
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package nl.fontys.epic.io.xml;

import nl.fontys.epic.Attributes;
import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Stats;
import nl.fontys.epic.core.impl.SimpleCreature;
import nl.fontys.epic.core.impl.SimpleInventory;
import nl.fontys.epic.core.impl.SimpleStats;
import nl.fontys.epic.io.ConvertException;
import nl.fontys.epic.util.DeferredStorage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Converts objects of {
 *
 * @see Creature} to XML nodes and vise versa
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CreatureConverter extends AbstractContentConverter<Node, Creature> {

    private final DeferredStorage storage;

    public CreatureConverter(TextAdventure game, DeferredStorage storage) {
        super(game);
        this.storage = storage;
    }

    @Override
    public Node toOutput(Creature source) throws ConvertException {
        Document doc = documentFactory.create();
        Element creature = doc.createElement(Attributes.TAG_CREATURE);
        Stats stats = source.getStats();

        creature.setAttribute(Attributes.ATTR_ID, source.getID());
        creature.setAttribute(Attributes.ATTR_NAME, source.getName());
        creature.setAttribute(Attributes.ATTR_LIFE, String.valueOf(source.getLife()));
        creature.setAttribute(Attributes.ATTR_MAX_LIFE, String.valueOf(source.getMaxLife()));
        creature.setAttribute(Attributes.ATTR_POWER, String.valueOf(stats.getPower()));
        creature.setAttribute(Attributes.ATTR_DEFENSE, String.valueOf(stats.getDefense()));

        // Add items
        Element itemRefs = doc.createElement(Attributes.TAG_ITEMS);

        for (Item item : source.getInventory()) {
            Element itemRef = doc.createElement(Attributes.TAG_ITEM_REF);
            itemRef.setAttribute(Attributes.ATTR_SOURCE, item.getID());
            itemRefs.appendChild(itemRef);
        }

        creature.appendChild(itemRefs);

        return creature;
    }

    @Override
    public Creature toInput(Node source) throws ConvertException {

        if (source.getNodeType() == Node.ELEMENT_NODE && source.getNodeName().equals(Attributes.TAG_CREATURE)) {
            Element element = (Element) source;

            String ID = element.getAttribute(Attributes.ATTR_ID);
            String name = element.getAttribute(Attributes.ATTR_NAME);
            int life = derriveValue(element.getAttribute(Attributes.ATTR_LIFE));
            int maxLife = derriveValue(element.getAttribute(Attributes.ATTR_MAX_LIFE));
            int power = derriveValue(element.getAttribute(Attributes.ATTR_POWER));
            int defense = derriveValue(element.getAttribute(Attributes.ATTR_DEFENSE));
            Inventory inventory = new SimpleInventory(ID + "$inventory");
            Stats stats = new SimpleStats(power, defense);

            // Fill the inventory
            NodeList content = source.getChildNodes();

            for (int i = 0; i < content.getLength(); ++i) {
                Node node = content.item(i);
                
                if (node.getNodeName().equals(Attributes.TAG_ITEMS)) {
                    for (int index = 0; index < node.getChildNodes().getLength(); ++index) {
                        Node item = node.getChildNodes().item(index);
                        
                        if (item.getNodeName().equals(Attributes.TAG_ITEM_REF) && item.getNodeType() == Node.ELEMENT_NODE) {
                            Element itemRef = (Element)item;
                            String ref = itemRef.getAttribute(Attributes.ATTR_SOURCE);
                            storage.add(inventory.getID(), ref, DeferredStorage.StorageType.ITEM);
                            storage.registerCustomProvider(inventory);
                        }
                    }
                }
            }
            
            return new SimpleCreature(life, maxLife, name, inventory, stats, game, 0, 0, null, ID);
        } else {
            throw new ConvertException(source + " is not a valid node");
        }
    }

}
