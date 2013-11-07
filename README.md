![logo](epic.png)

The Epic Text Adventure maker. An API to create simple text adventures based on xml files.

## Getting started

```java
TextAdventure adventure = new TextAdventure(new XMLSource("my-game.xml"));

adventure.addListener(new AdventureListener() {

    @Override
    public void onAction(String text) {
       // Do something with the output
    }
});

// Add command
adventure.command("go west");
```
