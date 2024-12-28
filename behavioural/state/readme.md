# State Design Pattern in a Jira Ticket Workflow System

## Overview

The **State Design Pattern** is a behavioral design pattern that allows an object to change its behavior when its internal state changes, without relying on complex conditional logic (like `if`/`switch` statements). 
## When to Use the State Design Pattern

The **State Pattern** is useful in the following scenarios:
- When an object needs to exhibit different behavior depending on its state.
- When the object's state transitions are complex, and you want to avoid complex if else.
- When each state involves distinct actions that vary widely from one state to another.
- When you need to allow dynamic transitions between states.


## Jira Ticket Workflow Example

Imagine you are implementing a Jira ticket system where tickets progress through various states:

- **To Do**: The ticket has been created but hasn't been worked on yet.
- **In Progress**: A developer is actively working on the ticket.
- **Testing**: The ticket is ready for QA/testing.
- **Released**: The ticket has been tested and is marked as completed and released.

Each state has its own set of actions and behaviors. For example:
- When a ticket is in the **"To Do"** state, you can assign it to a developer.
- When the ticket moves to **"In Progress,"** the developer can start working on it.
- When the ticket is in **"Testing,"** QA can verify if the ticket is fixed.
- When the ticket is **"Released,"** no further actions can be performed on it.


## Code Example: Jira Ticket Workflow Using State Pattern

```java
// State Interface
interface TicketState {
    void handleRequest(TicketContext context);

    String getStateName();
}

// Concrete States
class TodoState implements TicketState {
    public void handleRequest(TicketContext context) {
        System.out.println("Ticket is in 'To Do' state. Assigning to a developer...");
        context.setState(new InProgressState());
    }

    public String getStateName() {
        return "To Do";
    }
}

class InProgressState implements TicketState {
    public void handleRequest(TicketContext context) {
        System.out.println("Ticket is in 'In Progress' state. Developer is working on it...");
        context.setState(new TestingState());
    }

    public String getStateName() {
        return "In Progress";
    }
}

class TestingState implements TicketState {
    public void handleRequest(TicketContext context) {
        System.out.println("Ticket is in 'Testing' state. QA is testing the ticket...");
        context.setState(new ReleasedState());
    }

    public String getStateName() {
        return "Testing";
    }
}

class ReleasedState implements TicketState {
    public void handleRequest(TicketContext context) {
        System.out.println("Ticket is in 'Released' state. No further actions can be performed.");
    }

    public String getStateName() {
        return "Released";
    }
}

// Context Class to manage state transitions
class TicketContext {
    private TicketState state;

    public TicketContext() {
        state = new TodoState(); // Initial state
        System.out.println("Initial State: " + state.getStateName());
    }

    public void setState(TicketState state) {
        this.state = state;
        System.out.println("State changed to: " + state.getStateName());
    }

    public String getCurrentState() {
        return state.getStateName();
    }

    public void moveTicket() {
        state.handleRequest(this);
    }
}

// Usage
public class JiraTicketSystem {
    public static void main(String[] args) {
        TicketContext ticket = new TicketContext(); // Starts in 'To Do'

        // Move ticket through the states
        ticket.moveTicket(); // Moves to 'In Progress'
        ticket.moveTicket(); // Moves to 'Testing'
        ticket.moveTicket(); // Moves to 'Released'

        // Check final state
        System.out.println("Final State: " + ticket.getCurrentState());
    }
}

