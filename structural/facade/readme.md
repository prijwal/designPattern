# Facade Pattern

The **Facade Pattern** is a structural design pattern that provides a simplified interface to a complex subsystem, making it easier to interact with. It doesn't change the underlying system or its components but offers a higher-level interface that clients can use to perform common tasks with fewer steps and less complexity.

## What the Facade Pattern Does:

- **Simplifies Interfaces**: It hides the complexities of the subsystem and exposes a simple, unified interface for the client to interact with. The client doesn't need to understand the internal details of the subsystem, making the system more user-friendly.

- **Reduces Dependencies**: By using the facade, clients don't need to directly interact with the various components of the subsystem, reducing the number of dependencies and making the system easier to maintain and extend.

- **Centralized Access**: It provides a centralized access point to a set of interfaces in the subsystem, thus improving usability by centralizing all operations in a single class.

## When to Use the Facade Pattern:

- **When a system is complex or difficult to use**: If you have a complex set of subsystems that are hard to use, the Facade Pattern can provide a simpler and more manageable interface to them. It makes the system easier to use without exposing all the complexity.

- **To decouple subsystems**: If you want to decouple the client code from the complexities of the subsystem components, the Facade can help. The client only needs to interact with the facade rather than being tightly coupled with the subsystem classes.

- **When you want to provide a simpler interface**: If you want to present a simpler interface to the user, especially if multiple interfaces need to be used together, the facade can combine the operations into a more cohesive API.

- **To improve code readability and reduce complexity**: By providing a single entry point, the Facade Pattern helps reduce the cognitive load required to understand how a system works. It enhances readability and maintainability by simplifying the code interactions.

## Facade Pattern Use Case (Short Example):

Imagine you have a home automation system with multiple subsystems like lighting, heating, and security. Instead of the client interacting with each subsystem individually, a **Facade** can provide a simple interface like `turnOnEverything()` or `turnOffEverything()`. This simplifies the interaction and reduces the need for the client to manage each component directly.

### Example:

```java
// Subsystems
class Lighting {
    public void turnOn() {
        System.out.println("Lighting is ON");
    }

    public void turnOff() {
        System.out.println("Lighting is OFF");
    }
}

class Heating {
    public void turnOn() {
        System.out.println("Heating is ON");
    }

    public void turnOff() {
        System.out.println("Heating is OFF");
    }
}

class Security {
    public void activate() {
        System.out.println("Security System Activated");
    }

    public void deactivate() {
        System.out.println("Security System Deactivated");
    }
}

// Facade
class HomeAutomationFacade {
    private Lighting lighting;
    private Heating heating;
    private Security security;

    public HomeAutomationFacade(Lighting lighting, Heating heating, Security security) {
        this.lighting = lighting;
        this.heating = heating;
        this.security = security;
    }

    public void activateAll() {
        System.out.println("Activating the entire system...");
        lighting.turnOn();
        heating.turnOn();
        security.activate();
    }

    public void deactivateAll() {
        System.out.println("Deactivating the entire system...");
        lighting.turnOff();
        heating.turnOff();
        security.deactivate();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Lighting lighting = new Lighting();
        Heating heating = new Heating();
        Security security = new Security();

        HomeAutomationFacade homeAutomation = new HomeAutomationFacade(lighting, heating, security);
        
        homeAutomation.activateAll();  // Simple interface
        homeAutomation.deactivateAll();  // Simple interface
    }
}
