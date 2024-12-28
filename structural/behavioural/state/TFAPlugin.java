// State Interface
interface TFAState {
    void handleRequest(TFAContext context);
    void enableTFA(TFAContext context);
    void disableTFA(TFAContext context);
    void lockTFA(TFAContext context);
    void unlockTFA(TFAContext context);
}

// Concrete States

// Enabled State
class EnabledState implements TFAState {
    @Override
    public void handleRequest(TFAContext context) {
        System.out.println("TFA is enabled. User can either disable or lock TFA.");
    }

    @Override
    public void enableTFA(TFAContext context) {
        System.out.println("TFA is already enabled.");
    }

    @Override
    public void disableTFA(TFAContext context) {
        System.out.println("Disabling TFA for the user.");
        context.setState(new DisabledState());
    }

    @Override
    public void lockTFA(TFAContext context) {
        System.out.println("Locking TFA for the user.");
        context.setState(new LockedState());
    }

    @Override
    public void unlockTFA(TFAContext context) {
        // No action as TFA is already enabled
    }
}

// Disabled State
class DisabledState implements TFAState {
    @Override
    public void handleRequest(TFAContext context) {
        System.out.println("TFA is disabled. User can re-enable it.");
    }

    @Override
    public void enableTFA(TFAContext context) {
        System.out.println("Enabling TFA for the user.");
        context.setState(new EnabledState());
    }

    @Override
    public void disableTFA(TFAContext context) {
        System.out.println("TFA is already disabled.");
    }

    @Override
    public void lockTFA(TFAContext context) {
        System.out.println("TFA is disabled. Cannot lock a disabled account.");
    }

    @Override
    public void unlockTFA(TFAContext context) {
        // No action as TFA is disabled
    }
}

// Locked State
class LockedState implements TFAState {
    @Override
    public void handleRequest(TFAContext context) {
        System.out.println("TFA is locked. User cannot perform any action until it's unlocked.");
    }

    @Override
    public void enableTFA(TFAContext context) {
        System.out.println("Unlocking TFA and enabling it for the user.");
        context.setState(new EnabledState());
    }

    @Override
    public void disableTFA(TFAContext context) {
        System.out.println("Cannot disable locked TFA. First, unlock the account.");
    }

    @Override
    public void lockTFA(TFAContext context) {
        System.out.println("TFA is already locked.");
    }

    @Override
    public void unlockTFA(TFAContext context) {
        System.out.println("Unlocking TFA for the user.");
        context.setState(new EnabledState());
    }
}

// Context Class
class TFAContext {
    private TFAState state;

    public TFAContext() {
        state = new DisabledState(); // Initially, TFA is disabled
    }

    public void setState(TFAState state) {
        this.state = state;
    }

    public void request() {
        state.handleRequest(this);  // Handle state-specific behavior
    }

    public void enableTFA() {
        state.enableTFA(this);
    }

    public void disableTFA() {
        state.disableTFA(this);
    }

    public void lockTFA() {
        state.lockTFA(this);
    }

    public void unlockTFA() {
        state.unlockTFA(this);
    }
}

// Usage Example
public class TFAPlugin {
    public static void main(String[] args) {
        TFAContext user = new TFAContext(); // Initially TFA is disabled
        
        // User tries to enable TFA
        user.enableTFA();  // Output: Enabling TFA for the user.
        user.request();  // Output: TFA is enabled. User can either disable or lock TFA.

        // User tries to disable TFA
        user.disableTFA();  // Output: Disabling TFA for the user.
        user.request();  // Output: TFA is disabled. User can re-enable it.

        // User tries to lock TFA (Not possible from disabled state)
        user.lockTFA();  // Output: TFA is disabled. Cannot lock a disabled account.

        // User re-enables TFA
        user.enableTFA();  // Output: Enabling TFA for the user.
        user.request();  // Output: TFA is enabled. User can either disable or lock TFA.

        // User locks TFA
        user.lockTFA();  // Output: Locking TFA for the user.
        user.request();  // Output: TFA is locked. User cannot perform any action until it's unlocked.

        // User unlocks TFA
        user.unlockTFA();  // Output: Unlocking TFA for the user.
        user.request();  // Output: TFA is enabled. User can either disable or lock TFA.
    }
}

