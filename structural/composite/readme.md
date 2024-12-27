

### When to Use the Composite Design Pattern

You should use the **Composite Pattern** in situations where you have a complex tree-like structure, lets say

- You have users and groups.
- Both users and groups can have roles.
- A group can contain users or other groups (nested structure).
- You need to perform the same operation (like assigning roles) on individual users and groups in a consistent way.


#### Code Example

```java
// Interface RoleAssigner
public interface RoleAssigner {
    void assignRole(String role);
}

// User class implementing RoleAssigner
public class User implements RoleAssigner {
    private String name;
    private String role;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void assignRole(String role) {
        this.role = role;
        System.out.println(name + " has been assigned the role: " + role);
    }
}

// Group class implementing RoleAssigner
import java.util.ArrayList;
import java.util.List;

public class Group implements RoleAssigner {
    private String name;
    private List<RoleAssigner> members;

    public Group(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(RoleAssigner member) {
        members.add(member);
    }

    public void removeMember(RoleAssigner member) {
        members.remove(member);
    }

    @Override
    public void assignRole(String role) {
        System.out.println("Assigning role " + role + " to all members of group: " + name);
        for (RoleAssigner member : members) {
            member.assignRole(role);
        }
    }
}

// Main class to demonstrate the pattern
public class Main {
    public static void main(String[] args) {
        User user1 = new User("User1");
        User user2 = new User("User2");

        Group group1 = new Group("Group1");
        group1.addMember(user1);
        group1.addMember(user2);

        System.out.println("Assigning role to individual users:");
        user1.assignRole("Admin");
        user2.assignRole("User");

        System.out.println("\nAssigning role to group:");
        group1.assignRole("Manager");
    }
}

