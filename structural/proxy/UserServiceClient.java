import java.util.HashMap;
import java.util.Map;

interface UserService {
    String getUserDetails(String userId);
}

class RealUserService implements UserService {

    @Override
    public String getUserDetails(String userId) {
        System.out.println("Fetching user details for: " + userId);
        return "User details for " + userId;
    }
}

class UserServiceProxy implements UserService {
    private RealUserService realUserService;
    private Map<String, String> cache = new HashMap<>();

    @Override
    public String getUserDetails(String userId) {
        if (cache.containsKey(userId)) {
            System.out.println("Returning cached data for: " + userId);
            return cache.get(userId);
        }

        if (realUserService == null) {
            realUserService = new RealUserService();
        }

        String userDetails = realUserService.getUserDetails(userId);
        cache.put(userId, userDetails);

        return userDetails;
    }
}

public class UserServiceClient {

    public static void main(String[] args) {
        UserService userServiceProxy = new UserServiceProxy();

        System.out.println(userServiceProxy.getUserDetails("user1"));
        System.out.println(userServiceProxy.getUserDetails("user1"));
        System.out.println(userServiceProxy.getUserDetails("user2"));
        System.out.println(userServiceProxy.getUserDetails("user1"));
    }
}

