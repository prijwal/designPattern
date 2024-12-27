
## Problem Overview

When creating an object with many parameters, especially when some of the parameters are optional, using constructors or multiple setter methods can quickly become complex and error-prone. In scenarios like OAuth requests, the parameters can change depending on user needs, and some parameters may not always be required. In such cases, the **Builder Pattern** is a great solution.

### Example: OAuth Request

In OAuth requests, parameters like `client_id`, `redirect_uri`, `scope`, and `state` can vary. Using a Builder Pattern allows us to dynamically construct the request URL by only providing the necessary parameters.

---

## Code Explanation

The code demonstrates the Builder Pattern for creating an OAuth authorization URL. Here's how it works:

### Main Class & OAuthRequestBuilder

```java
class Main {

    public class OAuthRequestBuilder {
        private String clientId;
        private String redirectUri;
        private String scope;
        private String state;
        private String responseType = "code";  // Default value for OAuth
        private String authUrl = "https://okta-848175/oauth2/default/v1/authorize"; // Okta OAuth URL
    
        public OAuthRequestBuilder setClientId(String clientId) {
            this.clientId = clientId;
            return this;  // Returning builder instance to enable method chaining
        }
    
        public OAuthRequestBuilder setRedirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;  // Returning builder instance to enable method chaining
        }
    
        public OAuthRequestBuilder setScope(String scope) {
            this.scope = scope;
            return this;  // Returning builder instance to enable method chaining
        }
    
        public OAuthRequestBuilder setState(String state) {
            this.state = state;
            return this;  // Returning builder instance to enable method chaining
        }
    
        public String build() {
            // Ensure required parameters are set
            if (clientId == null || redirectUri == null) {
                throw new IllegalArgumentException("Client ID and Redirect URI must be set.");
            }
    
            // Build the OAuth URL
            StringBuilder url = new StringBuilder(authUrl);
            url.append("?response_type=").append(responseType);
            url.append("&client_id=").append(clientId);
            url.append("&redirect_uri=").append(redirectUri);
    
            if (scope != null && !scope.isEmpty()) {
                url.append("&scope=").append(scope);
            }
    
            if (state != null && !state.isEmpty()) {
                url.append("&state=").append(state);
            }
    
            return url.toString();  // Return the constructed URL
        }
    }

    public static void main(String[] args) {
        Main mainInstance = new Main();  
        OAuthRequestBuilder builder = mainInstance.new OAuthRequestBuilder();  // Create a builder instance
        String oauthUrl = builder
                .setClientId("fasa-a4afar4-afrafs")  // Required: Set client ID
                .setRedirectUri("https://miniorange/callback")  // Required: Set redirect URI
                .setScope("openid profile email")  // Optional: Set scope
                .setState("xyz123")  // Optional: Set state
                .build();  // Construct the final URL

        System.out.println("OAuth URL is: " + oauthUrl);  // Print the generated URL
    }
}

