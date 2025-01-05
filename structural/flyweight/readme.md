# Flyweight Pattern

The **Flyweight Pattern** is a structural design pattern that reduces memory usage by sharing common data among multiple objects instead of creating duplicates.

## When to Use the Flyweight Pattern:

1. **Memory Efficiency**: When the system needs to manage a large number of objects and memory consumption is a concern.
2. **Shared Data**: When objects have common or identical data (intrinsic state), to reduce duplication.
3. **Frequent Object Creation**: When objects are created often and are very similar in structure.
4. **Performance Boost**: In large-scale systems with many objects (e.g., games, simulations).

### Key Concepts:
- **Intrinsic State**: Shared, unchanging data (e.g., color, type).
- **Extrinsic State**: Unique, changing data (e.g., position, size).
- **Flyweight Factory**: A manager that creates and reuses flyweight objects.

## Example:

Imagine a tree-drawing application where many trees share the same species or leaf type but differ in position.

```java
// Flyweight Class
class Tree {
    private String species;

    public Tree(String species) {
        this.species = species;
    }

    public void display(int x, int y) {
        System.out.println("Tree species: " + species + " at position (" + x + ", " + y + ")");
    }
}

// Flyweight Factory
class TreeFactory {
    private Map<String, Tree> treeMap = new HashMap<>();

    public Tree getTree(String species) {
        if (!treeMap.containsKey(species)) {
            treeMap.put(species, new Tree(species));
        }
        return treeMap.get(species);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        TreeFactory factory = new TreeFactory();

        // Shared species
        Tree oak = factory.getTree("Oak");
        oak.display(1, 2);

        Tree pine = factory.getTree("Pine");
        pine.display(3, 4);
        
        // Same species reused
        Tree oak2 = factory.getTree("Oak");
        oak2.display(5, 6);
    }
}
