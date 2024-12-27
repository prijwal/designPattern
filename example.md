# Use Case: Singleton Pattern for SMTP Server

## Example Implementation: SMTP Server Singleton

### 1. **SMTP Server Class**

The `SmtpServer` class is responsible for managing the connection and settings of the SMTP server.

```java
public class SmtpServer {
    private static SmtpServer instance;
    private String host;
    private int port;
    private String username;
    private String password;

    // Private constructor to prevent instantiation from outside
    private SmtpServer(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    // Public method to provide access to the single instance
    public static synchronized SmtpServer getInstance(String host, int port, String username, String password) {
        if (instance == null) {
            instance = new SmtpServer(host, port, username, password);
        }
        return instance;
    }

    // Method to send an email
    public void sendEmail(String to, String subject, String body) {
        // Logic to send an email using the configured SMTP server
        System.out.println("Sending email to " + to + " via " + host + ":" + port);
        // (SMTP email sending logic would go here)
    }
}

