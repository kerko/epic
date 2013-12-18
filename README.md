![logo](epic.png)

The Epic Text Adventure maker. An API to create simple text adventures based on xml files.

## Getting started

Here is an example how to use the library properly:

```java
IOHandler io = SharedIOHandler.getInstance();

InputStream in = new FileInputStream(new File("my-game.xml"));
TextAdventure adventure = io.read(in);

adventure.addListener(new AdventureListener() {

    @Override
    public void onAction(AdventureEvent event) {
       // Do something with the output
    }
});

// Add command
adventure.command("go west");
```

## Commands

There a several commands which are supported by **epic**:

* go [west — east — north — south]
* attack creature name
* take [item name — all]
* drop item name — all]
* use item name
* open [door name — chest name]

You can pass them as a parameter to the ```command``` method of the ```TextAdventure``` object.



