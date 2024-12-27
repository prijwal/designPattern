public interface AuthenticationMethod {
    void authenticate(String user);
}

public class EmailOTPAuthentication implements AuthenticationMethod {
    @Override
    public void authenticate(String user) {
        System.out.println("Authenticating user " + user + " with OTP sent to email.");
    }
}

public class SecurityQuestionAuthentication implements AuthenticationMethod {
    @Override
    public void authenticate(String user) {
        System.out.println("Authenticating user " + user + " using security questions.");
    }
}

public class MobileAuthenticatorAuthentication implements AuthenticationMethod {
    @Override
    public void authenticate(String user) {
        System.out.println("Authenticating user " + user + " using Mobile Authenticator app.");
    }
}

public class DuoPushAuthentication implements AuthenticationMethod {
    @Override
    public void authenticate(String user) {
        System.out.println("Authenticating user " + user + " using Duo Push.");
    }
}

public class AuthenticationMethodFactory {
    public AuthenticationMethod createAuthenticationMethod(String methodType) {
        if (methodType == null) {
            return null;
        }
        
        if (methodType.equalsIgnoreCase("EMAIL_OTP")) {
            return new EmailOTPAuthentication();
        } else if (methodType.equalsIgnoreCase("SECURITY_QUESTION")) {
            return new SecurityQuestionAuthentication();
        } else if (methodType.equalsIgnoreCase("MOBILE_AUTHENTICATOR")) {
            return new MobileAuthenticatorAuthentication();
        } else if (methodType.equalsIgnoreCase("DUO_PUSH")) {
            return new DuoPushAuthentication();
        }
        
        return null;
    }
}

public class JenkinsTFAExample {
    public static void main(String[] args) {
        AuthenticationMethodFactory factory = new AuthenticationMethodFactory();
        
        String userChosenMethod = "DUO_PUSH";
        
        AuthenticationMethod authMethod = factory.createAuthenticationMethod(userChosenMethod);
        
        if (authMethod != null) {
            authMethod.authenticate("john_doe");
        } else {
            System.out.println("Invalid authentication method.");
        }
    }
}

