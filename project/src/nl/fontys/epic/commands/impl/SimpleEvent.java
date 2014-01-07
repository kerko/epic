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

package nl.fontys.epic.commands.impl;

import nl.fontys.epic.commands.Event;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nl.fontys.epic.commands.Event;

/**
 * Simple implementation of {@see CommandResponse}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleEvent implements Event {
    
    private long timestamp;
    
    private String message;
    
    private EventType type;
    
    private List<String> entries;

    public SimpleEvent(String message, EventType type) {
        this.timestamp = System.currentTimeMillis();
        this.message = message;
        entries = new ArrayList<>();
        this.type = type;
    }
    
    public SimpleEvent(String message) {
        this(message, EventType.INFO);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public Collection<String> getEntries() {
        return entries;
    }

    @Override
    public void addEntry(String entry) {
        entries.add(entry);
    }
    
    
}
